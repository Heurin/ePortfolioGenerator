/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.view;

import epfg.model.Page;
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
public class PageEditView extends HBox{
    String component;
    String title;
    Label label;
    public PageEditView(String inputTitle) {
        this.getStyleClass().clear();
        this.getStyleClass().add("page_edit_view");
        title = inputTitle;
        label = new Label(title);
        getChildren().add(label);
        this.setOnMouseClicked(e -> {
            this.getStyleClass().clear();
            this.getStyleClass().add("page_selection");
        });
    }
    public void setTitle(String initTitle) {
        title = initTitle;
        label.setText(title);
    }
            
    
}
