package ssm.controller;

import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import properties_manager.PropertiesManager;
import ssm.LanguagePropertyType;
import static ssm.LanguagePropertyType.DEFAULT_IMAGE_CAPTION;
import static ssm.LanguagePropertyType.TITLE_MODIFIER_HEADER;
import static ssm.LanguagePropertyType.TITLE_MODIFIER_WINDOW;
import static ssm.StartupConstants.DEFAULT_SLIDE_IMAGE;
import static ssm.StartupConstants.PATH_SLIDE_SHOW_IMAGES;
import static ssm.StartupConstants.UI_PROPERTIES_FILE_NAME;
import ssm.model.SlideShowModel;
import ssm.view.SlideShowMakerView;

/**
 * This controller provides responses for the slideshow edit toolbar,
 * which allows the user to add, remove, and reorder slides.
 * 
 * @author McKilla Gorilla & Seunghyuk Sean Choi
 */
public class SlideShowEditController {
    // APP UI
    private SlideShowMakerView ui;
    
    /**
     * This constructor keeps the UI for later.
     */
    public SlideShowEditController(SlideShowMakerView initUI) {
	ui = initUI;
    }
    
    /**
     * Provides a response for when the user wishes to add a new
     * slide to the slide show.
     */
    public void processAddSlideRequest() {
	SlideShowModel slideShow = ui.getSlideShow();
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	slideShow.addSlide(DEFAULT_SLIDE_IMAGE, PATH_SLIDE_SHOW_IMAGES, "");
    }
    public void processRemoveSlideRequest()
    {
        SlideShowModel slideShow = ui.getSlideShow();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        slideShow.removeSlide();
    }
    public void processMoveUpSlideRequest()
    {
        SlideShowModel slideShow = ui.getSlideShow();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        slideShow.moveSlideUp();
    }
    public void processMoveDownSlideRequest()
    {
        SlideShowModel slideShow = ui.getSlideShow();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        slideShow.moveSlideDown();
    }
    public void processChangeSlideTitleRequest()
    {
        SlideShowModel slideShow = ui.getSlideShow();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        TextInputDialog naming = new TextInputDialog(slideShow.getTitle());
        naming.setTitle(props.getProperty(TITLE_MODIFIER_WINDOW.toString()));
        naming.setHeaderText(props.getProperty(TITLE_MODIFIER_HEADER.toString()));
        Optional<String> result = naming.showAndWait();
        if(result.isPresent())
        {
            slideShow.changeSlideTitle(result.get());
        }
        
    }
}
