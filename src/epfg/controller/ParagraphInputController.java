/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import epfg.view.ePortfolioGeneratorView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
public class ParagraphInputController {
    ParagraphInputController() {
        
    }
    public void processUserInput(ePortfolioGeneratorView ui) {
        Stage TextStage = new Stage();
        
        VBox vbox = new VBox(5);
        
        Label label = new Label("Enter Paragraph");
        
        TextArea input = new TextArea();
        input.setPrefSize(500,500);
        input.setWrapText(true);

        
        HBox confirmation = new HBox(5);
        Button OK = new Button("OK");
        Button Cancel = new Button("Cancel");
        OK.setOnAction(e -> {
           ui.getEPortfolio().getSelectedPage().AddParagraph(input.getText());
           ui.reloadPageEditorPane(ui.getEPortfolio());
           TextStage.close();
            
        });
        Cancel.setOnAction(e -> {
            TextStage.close();
        });
        confirmation.getChildren().addAll(OK,Cancel);
        
        vbox.getChildren().addAll(label,input,confirmation);
        Scene TextScene = new Scene(vbox,800,800);
        TextStage.setScene(TextScene);
        TextStage.show();
    }
}
