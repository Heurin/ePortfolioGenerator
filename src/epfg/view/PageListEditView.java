/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.view;

import epfg.model.Page;
import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author shcho
 */
public class PageListEditView extends HBox{
    String component;
    String title;
    Label label;
    public PageListEditView(ePortfolioGeneratorView ui, Page inputPage) {
        this.getStyleClass().clear();
        if (ui.getEPortfolio().getSelectedPage() != inputPage){
            this.getStyleClass().add("page_edit_view");
        }
        else {
            this.getStyleClass().add("page_selection");
        }
        title = inputPage.getTitle();
        label = new Label(title);
        label.setWrapText(true);
        getChildren().add(label);
        this.setOnMouseClicked(e -> {
            this.getStyleClass().clear();
            this.getStyleClass().add("page_selection");
            ui.getEPortfolio().setSelectedPage(inputPage);
            ui.reloadPageListVBox(ui.getEPortfolio());
            ui.reloadPageEditorPane(ui.getEPortfolio());
            
        });
    }
    public void setTitle(String initTitle) {
        title = initTitle;
        label.setText(title);
    }
            
    
}
