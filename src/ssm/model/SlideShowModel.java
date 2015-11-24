package ssm.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;
import ssm.LanguagePropertyType;
import ssm.view.SlideShowMakerView;

/**
 * This class manages all the data associated with a slideshow.
 * 
 * @author McKilla Gorilla & Seunghyuk Sean Choi
 */
public class SlideShowModel {
    SlideShowMakerView ui;
    String title;
    String caption;
    ObservableList<Slide> slides;
    Slide selectedSlide;
    
    public SlideShowModel(SlideShowMakerView initUI) {
	ui = initUI;
	slides = FXCollections.observableArrayList();
	reset();	
    }

    // ACCESSOR METHODS
    public boolean isSlideSelected() {
	return selectedSlide != null;
    }
    
    public ObservableList<Slide> getSlides() {
	return slides;
    }
    
    public Slide getSelectedSlide() {
	return selectedSlide;
    }

    public String getTitle() { 
	return title; 
    }

    // MUTATOR METHODS
    public void setSelectedSlide(Slide initSelectedSlide) {
	selectedSlide = initSelectedSlide;
    }
    
    public void setTitle(String initTitle) { 
	title = initTitle; 
    }

    // SERVICE METHODS
    
    /**
     * Resets the slide show to have no slides and a default title.
     */
    public void reset() {
	slides.clear();
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	title = props.getProperty(LanguagePropertyType.DEFAULT_SLIDE_SHOW_TITLE);
	selectedSlide = null;
    }

    /**
     * Adds a slide to the slide show with the parameter settings.
     * @param initImageFileName File name of the slide image to add.
     * @param initImagePath File path for the slide image to add.
     */
    public void addSlide(   String initImageFileName,
			    String initImagePath,
                            String initCaption) {
        Slide slideToAdd = new Slide(initImageFileName, initImagePath, initCaption);
       // slideToAdd.addEventHandler(MounseEvent.MOUSE_CLICKED,new SlideEventHandler())
	slides.add(slideToAdd);
	ui.reloadSlideShowPane(this);
    }
    /**
     * Removes a slide from the slide show with the parameter settings
     */
    public void removeSlide()
    {
        Slide slideToRemove = selectedSlide;
        slides.remove(slideToRemove);
        selectedSlide = null;
        ui.reloadSlideShowPane(this);
    }
    
    public void moveSlideUp()
    {
        Slide temp;
        int origin = this.getSlides().indexOf(selectedSlide);
        if (origin >0)
        {
            temp = this.getSlides().get(origin-1);
            this.getSlides().set(origin-1, selectedSlide);
            this.getSlides().set(origin, temp);
            ui.reloadSlideShowPane(this);
        }
        
    }
    public void moveSlideDown()
    {
        Slide temp;
        int origin = this.getSlides().indexOf(selectedSlide);
        if (origin < this.getSlides().size()-1)
        {
            temp = this.getSlides().get(origin+1);
            this.getSlides().set(origin+1, selectedSlide);
            this.getSlides().set(origin, temp);
            ui.reloadSlideShowPane(this);
        }
                
    }
    public void changeSlideTitle(String newTitle)
    {
        title = newTitle;
    }
  /**  
    public void addSlide(String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    **/
    
}