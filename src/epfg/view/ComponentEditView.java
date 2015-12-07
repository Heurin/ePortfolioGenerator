/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.view;

import epfg.model.ePortfolioModel;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
    int pos;
    
    public ComponentEditView(ePortfolioGeneratorView initUi, String type, int index) {
        ui = initUi;
        ePortfolio = ui.getEPortfolio();
        pos =index;
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
        typeLabel.setPrefWidth(200.00);
        Button edit = new Button("edit");
        Button remove = new Button("remove");
        if(!component.equals("slideshow"))
            this.getChildren().addAll(typeLabel,edit, remove);
        else
            this.getChildren().addAll(typeLabel, remove);
        this.setOnMouseClicked(e-> {
            this.getStyleClass().clear();
            this.getStyleClass().add("page_selection");
            if(e.getClickCount() >1) {
                
                //ui.getEPortfolio().getSelectedPage().setSelectedComponent(type,);
            }
            ui.reloadPageEditorPane(ePortfolio);
        });
        
        edit.setOnMouseClicked(e-> {
            ePortfolio.getSelectedPage().EditComponent(pos, ui);
        });
        
        remove.setOnAction(e -> {
            ePortfolio.getSelectedPage().RemoveComponent(pos);
            ui.reloadPageEditorPane(ePortfolio);
        });
    }

}
