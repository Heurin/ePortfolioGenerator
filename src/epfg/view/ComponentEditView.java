/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.view;

import epfg.model.ePortfolioModel;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 *
 * @author choiseu
 */
public class ComponentEditView extends HBox{
    String component;
    ePortfolioGeneratorView ui;
    ArrayList<Integer> position = new ArrayList(3);
    ePortfolioModel ePortfolio;
    Label typeLabel;
    
    public ComponentEditView(ePortfolioGeneratorView initUi, String type) {
        ui = initUi;
        ePortfolio = ui.getEPortfolio();
        this.setPrefWidth(1000.00);
        
        /**
        this.getStyleClass().clear();
        if (ePortfolio.getSelectedPage().getSelectedType() ){
            this.getStyleClass().add("page_edit_view");
        }
        else {
            this.getStyleClass().add("page_selection");
        }
        * **/
        ui = initUi;
        component = type;
        typeLabel = new Label(type);
        this.getChildren().add(typeLabel);
        
        this.setOnMouseClicked(e-> {
            this.getStyleClass().clear();
            this.getStyleClass().add("page_selection");
            if(e.getClickCount() >1) {
                
                //ui.getEPortfolio().getSelectedPage().setSelectedComponent(type,);
            }
        });
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
