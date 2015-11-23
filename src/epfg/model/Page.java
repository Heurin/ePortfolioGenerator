/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.model;

import javafx.collections.ObservableList;

/**
 *
 * @author seunchoi
 */
public class Page {
    String pageTitle;
    ObservableList<String> components;
    //public Page(String newPageTitle)
    
    public Page(String initTitle, ObservableList<String> initcomponents) {
        pageTitle = initTitle;
        components = initcomponents;
    }
    
    public String getTitle() {
        return pageTitle;
    }
    public ObservableList<String> getComponents() {
        return components;
    }
    
    
    //public Slide(String initPageTitle, String[] init)
}
