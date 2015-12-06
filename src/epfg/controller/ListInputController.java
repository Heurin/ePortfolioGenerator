/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import static epfg.StartupConstants.CSS_CLASS_BUTTONS;
import static epfg.StartupConstants.ICON_REMOVE;
import static epfg.StartupConstants.PATH_ICONS;
import epfg.view.ePortfolioGeneratorView;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author shcho
 */
public class ListInputController {
    
    ArrayList<String> fields = new ArrayList();    
    VBox Listvbox;
    Label listnumb;
    
    public ListInputController() {
        
    }
    public void MakeList(ePortfolioGeneratorView ui) {
        Stage TextStage = new Stage();
        
        FlowPane mainpane = new FlowPane();
        VBox vbox = new VBox(5);
        
        Label label = new Label("Make a List");

        HBox toolbar = new HBox(10);
        
        Listvbox = new VBox(5);
        
        Button AddList = new Button();
        toolbar.getChildren().add(AddList);
        
        AddList.setOnAction(e -> {
            fields.add("");
            reloadListFields();
        });
                
        HBox confirmation = new HBox(5);
        Button OK = new Button("OK");
        Button Cancel = new Button("Cancel");
        confirmation.getChildren().addAll(OK,Cancel);
        OK.setOnAction(e -> {
            
            ui.reloadPageEditorPane(ui.getEPortfolio());
        });
        Cancel.setOnAction(e -> {
            TextStage.close();
        });        
        vbox.getChildren().addAll(label,toolbar,Listvbox,confirmation);
        mainpane.getChildren().add(vbox);
        Scene TextScene = new Scene(mainpane,800,800);
        TextStage.setScene(TextScene);
        TextStage.show();
    }
    private void reloadListFields() {
        Listvbox.getChildren().clear();
        ArrayList<String> copy = new ArrayList();
        for(int i = 0; i<fields.size(); i++) {
            String a = fields.get(i);
            HBox list = new HBox();
            listnumb = new Label();
            listnumb.setText(new Integer(i+1).toString());
            TextField element = new TextField(a);
            String imagePath = "file:" + PATH_ICONS + ICON_REMOVE;
            Image buttonImage = new Image(imagePath);
            Button button = new Button();
            button.getStyleClass().add(CSS_CLASS_BUTTONS);
            button.setGraphic(new ImageView(buttonImage));            
            list.getChildren().addAll(listnumb,element,button);
            copy.add(a);
            button.setOnAction(e -> {
                copy.remove(Integer.parseInt(listnumb.getText()));
                reloadListFields();
            });
        }
    }
    
}
