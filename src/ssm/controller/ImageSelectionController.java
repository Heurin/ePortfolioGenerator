package ssm.controller;

import java.io.File;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;
import ssm.LanguagePropertyType;
import static ssm.LanguagePropertyType.ERROR_TITLE;
import static ssm.LanguagePropertyType.IMAGE_MISSING;
import static ssm.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import ssm.error.ErrorHandler;
import ssm.model.Slide;
import ssm.view.SlideEditView;
import ssm.view.SlideShowMakerView;

/**
 * This controller provides a controller for when the user chooses to
 * select an image for the slide show.
 * 
 * @author McKilla Gorilla & Seunghyuk Sean Choi
 */
public class ImageSelectionController {
    
    /**
     * Default contstructor doesn't need to initialize anything
     */
    public ImageSelectionController() {    }
    
    /**
     * This function provides the response to the user's request to
     * select an image.
     * 
     * @param slideToEdit - Slide for which the user is selecting an image.
     * 
     * @param view The user interface control group where the image
     * will appear after selection.
     * @param ui
     */
    public void processSelectImage(Slide slideToEdit, SlideEditView view, SlideShowMakerView ui) {
	FileChooser imageFileChooser = new FileChooser();
        PropertiesManager props = PropertiesManager.getPropertiesManager();	
	// SET THE STARTING DIRECTORY
	imageFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOW_IMAGES));
	
	// LET'S ONLY SEE IMAGE FILES
	FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	FileChooser.ExtensionFilter gifFilter = new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.GIF");
	imageFileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter, gifFilter);
	
	// LET'S OPEN THE FILE CHOOSER
	File file = imageFileChooser.showOpenDialog(null);
	if (file != null) {
	    String path = file.getPath().substring(0, file.getPath().indexOf(file.getName()));
	    String fileName = file.getName();
	    slideToEdit.setImage(path, fileName);
	    view.updateSlideImage();
	}	    
	else {
	    // @todo provide error message for no files selected
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(IMAGE_MISSING, props.getProperty(ERROR_TITLE), props.getProperty(ERROR_TITLE));
            
	}
    }
}
