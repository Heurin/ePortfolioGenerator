package epfg.controller;

import java.io.File;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;
import epfg.LanguagePropertyType;
import static epfg.LanguagePropertyType.ERROR_TITLE;
import static epfg.LanguagePropertyType.IMAGE_MISSING;
import static epfg.StartupConstants.PATH_EPORTFOLIO_IMAGES;
import epfg.error.ErrorHandler;
import epfg.model.Page;
import epfg.view.PageListEditView;
import epfg.view.ePortfolioGeneratorView;

/**
 * This controller provides a controller for when the user chooses to
 * select an image for the slide show.
 * 
 * @author Seunghyuk Sean Choi
 */
public class ImageSelectionController {
    
    /**
     * Default contstructor doesn't need to initialize anything
     */
    public ImageSelectionController() {    }
    

    public void processSelectImage(Page pageToEdit, PageListEditView view, ePortfolioGeneratorView ui) {
	FileChooser imageFileChooser = new FileChooser();
        PropertiesManager props = PropertiesManager.getPropertiesManager();	
	// SET THE STARTING DIRECTORY
	imageFileChooser.setInitialDirectory(new File(PATH_EPORTFOLIO_IMAGES));
	
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

	}	    
	else {
	    // @todo provide error message for no files selected
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(IMAGE_MISSING, props.getProperty(ERROR_TITLE), props.getProperty(ERROR_TITLE));
            
	}
    }
}
