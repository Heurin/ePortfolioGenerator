package ssm.view;

import java.awt.Color;
import static java.awt.Color.black;
import java.io.File;
import java.net.URL;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.swing.BorderFactory;
import properties_manager.PropertiesManager;
import ssm.LanguagePropertyType;
import static ssm.LanguagePropertyType.ERROR_OCCURED;
import static ssm.LanguagePropertyType.IMAGE_MISSING;
import static ssm.StartupConstants.CSS_CLASS_SLIDE_EDIT_VIEW;
import static ssm.StartupConstants.CSS_CLASS_SLIDE_SELECTION;
import static ssm.StartupConstants.DEFAULT_SLIDE_IMAGE;
import static ssm.StartupConstants.DEFAULT_THUMBNAIL_WIDTH;
import static ssm.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import ssm.controller.ImageSelectionController;
import ssm.model.Slide;
import static ssm.file.SlideShowFileManager.SLASH;

/**
 * This UI component has the controls for editing a single slide
 * in a slide show, including controls for selected the slide image
 * and changing its caption.
 * 
 * @author McKilla Gorilla & Seunghyuk Sean Choi
 */
public class SlideEditView extends HBox {
    // SLIDE THIS COMPONENT EDITS
    Slide slide;
    
    // DISPLAYS THE IMAGE FOR THIS SLIDE
    ImageView imageSelectionView;
    
    // CONTROLS FOR EDITING THE CAPTION
    VBox captionVBox;
    Label captionLabel;
    TextField captionTextField;
    
    // PROVIDES RESPONSES FOR IMAGE SELECTION
    ImageSelectionController imageController;
    SlideShowMakerView ui;
    private int current;
    /**
     * THis constructor initializes the full UI for this component, using
     * the initSlide data for initializing values./
     * 
     * @param initSlide The slide to be edited by this component.
     */
    public SlideEditView(Slide initSlide, SlideShowMakerView initUi) {
	ui = initUi;
        // FIRST SELECT THE CSS STYLE CLASS FOR THIS CONTAINER
        if(ui.getSlideShow().getSelectedSlide() != initSlide)
            this.getStyleClass().add(CSS_CLASS_SLIDE_EDIT_VIEW);
        else
             this.getStyleClass().add(CSS_CLASS_SLIDE_SELECTION);
	
	// KEEP THE SLIDE FOR LATER
	slide = initSlide;
	
	// MAKE SURE WE ARE DISPLAYING THE PROPER IMAGE
	imageSelectionView = new ImageView();
	updateSlideImage();
	// SETUP THE CAPTION CONTROLS
	captionVBox = new VBox();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        Button submitCaption = new Button(props.getProperty(LanguagePropertyType.SUBMIT_CAPTION));

	captionLabel = new Label(props.getProperty(LanguagePropertyType.LABEL_CAPTION));
	captionTextField = new TextField();
	captionVBox.getChildren().add(captionLabel);
	captionVBox.getChildren().add(captionTextField);
        captionVBox.getChildren().add(submitCaption);
        
	// LAY EVERYTHING OUT INSIDE THIS COMPONENT
	getChildren().add(imageSelectionView);
	getChildren().add(captionVBox);
        captionTextField.setText(initSlide.getCaption());
	// SETUP THE EVENT HANDLERS
        
        
	imageController = new ImageSelectionController();
	imageSelectionView.setOnMousePressed(e -> {
            ui.getSlideShow().setSelectedSlide(initSlide);
	    imageController.processSelectImage(slide, this, ui);
	});

        submitCaption.setOnMousePressed(e -> {
            slide.setCaption(captionTextField.getText());
            ui.updateToolbarControls(false);
        });
   
        captionVBox.setOnMouseClicked(e -> {
            ui.clearCSS();
            ui.getSlideShow().setSelectedSlide(initSlide);
            this.getStyleClass().clear();
            this.getStyleClass().add(CSS_CLASS_SLIDE_SELECTION);
            ui.updateToolbarControls(false);
            ui.reloadSlideShowPane(ui.getSlideShow());
        });
    }
    
    /**
     * This function gets the image for the slide and uses it to
     * update the image displayed.
     */
    public void updateSlideImage() {
	String imagePath = slide.getImagePath() + SLASH + slide.getImageFileName();
	File file = new File(imagePath);
	try {
	    // GET AND SET THE IMAGE
	    URL fileURL = file.toURI().toURL();
	    Image slideImage = new Image(fileURL.toExternalForm());
	    imageSelectionView.setImage(slideImage);
	    
	    // AND RESIZE IT
	    double scaledWidth = DEFAULT_THUMBNAIL_WIDTH;
	    double perc = scaledWidth / slideImage.getWidth();
	    double scaledHeight = slideImage.getHeight() * perc;
	    imageSelectionView.setFitWidth(scaledWidth);
	    imageSelectionView.setFitHeight(scaledHeight);
            ui.updateToolbarControls(false);
	} catch (Exception e) {
	    // @todo - use Error handler to respond to missing image
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            ui.getErrorHandler().processError(IMAGE_MISSING,props.getProperty(ERROR_OCCURED) ,props.getProperty(ERROR_OCCURED));
	}
    }

}