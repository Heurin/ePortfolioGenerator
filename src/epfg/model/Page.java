/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.model;

import epfg.Contents.PortfolioComponent;
import javafx.collections.ObservableList;

/**
 *
 * @author seunchoi
 */
public class Page {
    String pageTitle;
    String componentType;
    ObservableList<PortfolioComponent> components;
    //public Page(String newPageTitle)
    
    public String getTitle() {
        return pageTitle;
    }
    public ObservableList<PortfolioComponent> getComponents() {
        return components;
    }
    //public Slide(String initPageTitle, String[] init)
}
