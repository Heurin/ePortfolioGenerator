package ssm.model;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.input.MouseEvent;

/**
 * This class represents a single slide in a slide show.
 * 
 * @author McKilla Gorilla & Seunghyuk Sean Choi
 */
public class Slide {
    String imageFileName;
    String imagePath;
    String caption="";
     
    /**
     * Constructor, it initializes all slide data.
     * @param initImageFileName File name of the image.
     * 
     * @param initImagePath File path for the image.
     * 
     */
    public Slide(String initImageFileName, String initImagePath, String initCaption) {
	imageFileName = initImageFileName;
	imagePath = initImagePath;
        caption = initCaption;
    }
    
    // ACCESSOR METHODS
    public String getImageFileName() { return imageFileName; }
    public String getImagePath() { return imagePath; }
    public String getCaption()
    {
        return caption;
    }
    
    // MUTATOR METHODS
    public void setImageFileName(String initImageFileName) {
	imageFileName = initImageFileName;
    }
    
    public void setImagePath(String initImagePath) {
	imagePath = initImagePath;
    }
    
    public void setImage(String initPath, String initFileName) {
	imagePath = initPath;
	imageFileName = initFileName;
    }
    
    public void setCaption(String inputCaption)
    {
        caption = inputCaption;
    }

    public void addEventHandler(EventType<MouseEvent> MOUSE_CLICKED, EventHandler<MouseEvent> eventHandler) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
