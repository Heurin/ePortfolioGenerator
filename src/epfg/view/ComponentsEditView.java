/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.view;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

/**
 *
 * @author choiseu
 */
public class ComponentsEditView extends HBox{
    String component;
    ePortfolioGeneratorView ui;
    
    public ComponentsEditView(ePortfolioGeneratorView initUi, String type) {
        ui = initUi;
        component = type;
    }
    
    public void changeText(String newText) {
        
    }
    public void changeImage(Image newImage) {
        
    }
    public void changeVideo() {
        
    }
    public void changeFooter() {
        
    }

}
