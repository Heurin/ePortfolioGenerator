/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import epfg.view.ePortfolioGeneratorView;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author shcho
 */
public class HeaderInputController {

    public HeaderInputController() {
        
    }
    public void processData(ePortfolioGeneratorView ui) {
        Stage stage = new Stage();
        
        VBox vbox = new VBox(5);
        
        Label label = new Label("Enter Header");
        
        Label headerlabel = new Label("choose Header");
        
        ObservableList<String> headeroptions = observableArrayList(
            "h1",
            "h2",
            "h3",
            "h4",
            "h5",
            "h6"
        );
        final ComboBox headers = new ComboBox(headeroptions);
        HBox hoptionbox = new HBox();
        hoptionbox.getChildren().add(headers);
        
        Label fontlabel = new Label("Choose Font");

        ObservableList<String> fontoptions = observableArrayList(
            "Montserrat",
            "Indie Flower",
            "Sigmar One",
            "Pacifico",
            "Open Sans"
        );
        final ComboBox fonts = new ComboBox(fontoptions);
        HBox foptionbox = new HBox();
        foptionbox.getChildren().add(fonts);
            
        
        TextField input = new TextField();
        
        
        
        
        HBox confirmation = new HBox(5);
        Button OK = new Button("OK");
        Button Cancel = new Button("Cancel");
        OK.setOnAction(e -> {
           ui.getEPortfolio().getSelectedPage().AddHeader(input.getText(),headers.getValue().toString(), fonts.getValue().toString());
           //ui.getEPortfolio().getSelectedPage().AddParagraph(htmlEditor.getHtmlText());
           ui.reloadPageEditorPane(ui.getEPortfolio());
           stage.close();
            
        });
        Cancel.setOnAction(e -> {
            stage.close();
        });
        confirmation.getChildren().addAll(OK,Cancel);
        
        vbox.getChildren().addAll(headerlabel,hoptionbox,fontlabel,foptionbox,label,input,confirmation);
        Scene scene = new Scene(vbox,800,800);
        stage.setScene(scene);
        stage.show();        
    }
}
