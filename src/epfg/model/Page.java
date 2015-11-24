/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.model;

import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author seunchoi
 */
public class Page {
    String pageTitle;
    ArrayList<String> components;
    //public Page(String newPageTitle)
    
    public Page(String initTitle, ArrayList<String> initcomponents) {
        pageTitle = initTitle;
        components = initcomponents;
    }
    
    public String getTitle() {
        return pageTitle;
    }
    public ArrayList<String> getComponents() {
        return components;
    }
    
    
    //public Slide(String initPageTitle, String[] init)
}
