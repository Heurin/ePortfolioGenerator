/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import static epfg.StartupConstants.CSS_CLASS_BUTTONS;
import static epfg.StartupConstants.ICON_REMOVE;
import static epfg.StartupConstants.PATH_ICONS;
import epfg.model.Page;
import epfg.model.ePortfolioModel;
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
    int current;
    
    public ListInputController() {
        
    }
    public void MakeList(ePortfolioGeneratorView ui) {
        Stage TextStage = new Stage();
        
        FlowPane mainpane = new FlowPane();
        VBox vbox = new VBox(5);
        
        Label label = new Label("Make a List");

        HBox toolbar = new HBox(10);
        
        Listvbox = new VBox(5);
        
        Button AddList = new Button("Add List");
        toolbar.getChildren().add(AddList);
        
        AddList.setOnAction(e -> {
            fields.add("");
            this.reloadListFields(fields);
        });
                
        HBox confirmation = new HBox(5);
        Button OK = new Button("OK");
        Button Cancel = new Button("Cancel");
        confirmation.getChildren().addAll(OK,Cancel);
        OK.setOnAction(e -> {
            ePortfolioModel ePortfolio = ui.getEPortfolio();
            ePortfolio.getSelectedPage().AddList(fields);
            ui.reloadPageEditorPane(ePortfolio);            
            TextStage.close();
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
    private void reloadListFields(ArrayList<String> lists) {
        Listvbox.getChildren().clear();
        //ArrayList<String> copy = new ArrayList();
        for(int i = 0; i<fields.size(); i++) {
            listbox list = new listbox(i,lists);


            Listvbox.getChildren().add(list);
           // copy.add(a);
            

        }
    }
    
    public void ModifyList(Page a, ArrayList<String> input,int index,ePortfolioGeneratorView ui) {
        Stage TextStage = new Stage();
        
        FlowPane mainpane = new FlowPane();
        VBox vbox = new VBox(5);
        
        Label label = new Label("Make a List");

        HBox toolbar = new HBox(10);
        
        Listvbox = new VBox(5);
        
        Button AddList = new Button("Add List");
        toolbar.getChildren().add(AddList);
        
        AddList.setOnAction(e -> {
            fields.add("");
            this.reloadListFields(fields);
        });
                
        HBox confirmation = new HBox(5);
        Button OK = new Button("OK");
        Button Cancel = new Button("Cancel");
        confirmation.getChildren().addAll(OK,Cancel);
        OK.setOnAction(e -> {
            ePortfolioModel ePortfolio = ui.getEPortfolio();
            a.EditList(fields,index);
            ui.reloadPageEditorPane(ePortfolio);            
            TextStage.close();
        });
        Cancel.setOnAction(e -> {
            TextStage.close();
        });        
        vbox.getChildren().addAll(label,toolbar,Listvbox,confirmation);
        mainpane.getChildren().add(vbox);
        Scene TextScene = new Scene(mainpane,800,800);
        fields = input;
        this.reloadListFields(fields);
        TextStage.setScene(TextScene);
        TextStage.show();
    }
    
    
    
    
    
    
    
    
    private class listbox extends HBox {
        private listbox(int pos,ArrayList<String> fields) {
            int position = pos;
            TextField element = new TextField(fields.get(pos));
            String imagePath = "file:" + PATH_ICONS + ICON_REMOVE;
            Image buttonImage = new Image(imagePath);
            listnumb = new Label();
            listnumb.setText(new Integer(position+1).toString());            
            Button button = new Button();
            button.getStyleClass().add(CSS_CLASS_BUTTONS);
            button.setGraphic(new ImageView(buttonImage));            
            getChildren().addAll(listnumb,element,button);  
            
            element.setOnKeyTyped(e-> {
                fields.set(position, element.getText());
            });
            button.setOnAction(e -> {
                fields.remove(position);
                reloadListFields(fields);
            });            
        }
        
    }
}
