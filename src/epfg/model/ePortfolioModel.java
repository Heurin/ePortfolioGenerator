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
import java.util.ArrayList;
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
    String studentName;
    String font;
    
    
    public ePortfolioModel(ePortfolioGeneratorView initUI) {
        ui = initUI;
        pages = FXCollections.observableArrayList();
        studentName = "";
        reset();
    }
    ///
    public boolean isPageSelected() {
	return selectedPage != null;
    }
    public void addPage(String initTitle){
        ArrayList<String> a = new ArrayList<String>();
        Page cr = new Page(initTitle, a);
        pages.add(cr);
        
        
    }
    public void addPage(Page initPage){
        pages.add(initPage);
    }
    
    public ObservableList<Page> getPages() {
	return pages;
    }
    
    /**
     * 
     * @return selected Page Object
     */
    public Page getSelectedPage() {
	return selectedPage;
    }
    /**
     * 
     * @return title of ePortfolio
     */
    public String getTitle() { 
	return title; 
    }
    
    /**
     * 
     * @return student Name;
     */
    
    public String getStudent(){
        return studentName;
    }
    
    
    // MUTATOR METHODS
    public void setSelectedPage(Page initSelectedPage) {
	selectedPage = initSelectedPage;
    }
    
    public void setTitle(String initTitle) { 
	title = initTitle; 
    }
    
    //change Student NAme
    public void updateStudentName(String inputName){
        studentName = inputName;
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
        studentName = "";
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
    
    public void changeStudentName(String name) {
        studentName = name;
    }
    
  /**  
    public void addSlide(String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    **/
    
}
