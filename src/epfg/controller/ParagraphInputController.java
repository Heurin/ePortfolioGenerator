/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import epfg.Contents.TextComponent;
import epfg.model.Page;
import epfg.view.ePortfolioGeneratorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author shcho
 */
public class ParagraphInputController  {
    public ParagraphInputController() {
        
    }
    public void processUserInput(ePortfolioGeneratorView ui) {
        Stage TextStage = new Stage();
        
        VBox vbox = new VBox(5);
        
        Label label = new Label("Enter Paragraph");
        
        ObservableList<String> fontoptions = 
        FXCollections.observableArrayList(
            "Montserrat",
            "Indie Flower",
            "Sigmar One",
            "Pacifico",
            "Open Sans"
        );
        final ComboBox fonts = new ComboBox(fontoptions);
        HBox optionbox = new HBox();
        optionbox.getChildren().add(fonts);
        
        Label fontlabel = new Label("Choose Font");
        
        TextArea input = new TextArea();
        input.setPrefSize(500,500);
        input.setWrapText(true);
        
        
        
        
        HBox confirmation = new HBox(5);
        Button OK = new Button("OK");
        Button Cancel = new Button("Cancel");
        OK.setOnAction(e -> {
           ui.getEPortfolio().getSelectedPage().AddParagraph(input.getText(), fonts.getValue().toString());
           //ui.getEPortfolio().getSelectedPage().AddParagraph(htmlEditor.getHtmlText());
           ui.reloadPageEditorPane(ui.getEPortfolio());
           TextStage.close();
            
        });
        Cancel.setOnAction(e -> {
            TextStage.close();
        });
        confirmation.getChildren().addAll(OK,Cancel);
        
        vbox.getChildren().addAll(fontlabel,optionbox,label,input,confirmation);
        Scene TextScene = new Scene(vbox,800,800);
        TextStage.setScene(TextScene);
        TextStage.show();
    }
    public void EditParagraph(Page a, TextComponent t,int index,ePortfolioGeneratorView ui) {
        Stage TextStage = new Stage();
        
        VBox vbox = new VBox(5);
        
        Label label = new Label("Enter Paragraph");
        
        ObservableList<String> fontoptions = 
        FXCollections.observableArrayList(
            "Montserrat",
            "Indie Flower",
            "Sigmar One",
            "Pacifico",
            "Open Sans"
        );
        final ComboBox fonts = new ComboBox(fontoptions);
        fonts.setValue(t.getFont());
        HBox optionbox = new HBox();
        optionbox.getChildren().add(fonts);
        
        Label fontlabel = new Label("Choose Font");
        
        TextArea input = new TextArea();
        input.setText(t.getText());
        input.setPrefSize(500,500);
        input.setWrapText(true);
        
        
        
        
        HBox confirmation = new HBox(5);
        Button OK = new Button("OK");
        Button Cancel = new Button("Cancel");
        OK.setOnAction(e -> {
           a.EditParagraph(input.getText(), fonts.getValue().toString(),index);
           //ui.getEPortfolio().getSelectedPage().AddParagraph(htmlEditor.getHtmlText());
           ui.reloadPageEditorPane(ui.getEPortfolio());
           TextStage.close();
            
        });
        Cancel.setOnAction(e -> {
            TextStage.close();
        });
        confirmation.getChildren().addAll(OK,Cancel);
        
        vbox.getChildren().addAll(fontlabel,optionbox,label,input,confirmation);
        Scene TextScene = new Scene(vbox,800,800);
        TextStage.setScene(TextScene);
        TextStage.show();        
    }
    
}

