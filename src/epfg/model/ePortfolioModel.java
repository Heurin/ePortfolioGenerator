/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;
import epfg.LanguagePropertyType;
import epfg.view.ePortfolioGeneratorView;

import epfg.view.ePortfolioGeneratorView;
import javafx.collections.ObservableList;

/**
 *
 * @author seunchoi
 */
public class ePortfolioModel {
    ePortfolioGeneratorView ui;
    String title;
    ObservableList<Page> pages;
    Page selectedPage;
    
    public ePortfolioModel(ePortfolioGeneratorView initUI) {
        ui = initUI;
        pages = FXCollections.observableArrayList();
        reset();
    }
    ///
    public boolean isPageSelected() {
	return selectedPage != null;
    }
    public void addPage(String initTitle){
        title = initTitle;        
    }
    
    public ObservableList<Page> getPages() {
	return pages;
    }
    
    public Page getSelectedPage() {
	return selectedPage;
    }

    public String getTitle() { 
	return title; 
    }

    // MUTATOR METHODS
    public void setSelectedPage(Page initSelectedPage) {
	selectedPage = initSelectedPage;
    }
    
    public void setTitle(String initTitle) { 
	title = initTitle; 
    }

    // SERVICE METHODS
    
    /**
     * Resets the slide show to have no slides and a default title.
     */
    public void reset() {
	pages.clear();
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	title = props.getProperty(LanguagePropertyType.DEFAULT_EPORTFOLIO_TITLE);
	selectedPage = null;
    }

    /**
     * Adds a slide to the slide show with the parameter settings.
     * @param initImageFileName File name of the slide image to add.
     * @param initImagePath File path for the slide image to add.
     */

    public void changePageTitle(String newTitle)
    {
        title = newTitle;
    }
  /**  
    public void addSlide(String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    **/
    
}
