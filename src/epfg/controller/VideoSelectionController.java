/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import epfg.LanguagePropertyType;
import static epfg.StartupConstants.PATH_VIDEOS;
import epfg.error.ErrorHandler;
import epfg.model.Page;
import epfg.model.ePortfolioModel;
import epfg.view.PageListEditView;
import epfg.view.ePortfolioGeneratorView;
import java.io.File;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;

/**
 *
 * @author choiseu
 */
public class VideoSelectionController {
    public VideoSelectionController() { }
    
    public void processSelectVideo(ePortfolioGeneratorView ui) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        Stage videochooser = new Stage();
        videochooser.setTitle("Choose Video");
        Label label = new Label("Add Video File");
        HBox labelHb = new HBox();
        labelHb.getChildren().add(label);
        
        HBox widthHBox = new HBox(5);
        Label widthlabel = new Label("Width : ");
        TextField widthField = new TextField();
        widthHBox.getChildren().addAll(widthlabel,widthField);
        
        HBox heightHbox = new HBox(5);
        Label heightlabel = new Label("Height : ");
        TextField heightField = new TextField();
        heightHbox.getChildren().addAll(heightlabel,heightField);
        Text ImportStatus = new Text();
        
        HBox captionHbox = new HBox(5);
        Label captionlabel = new Label("Caption : ");
        TextField captionField = new TextField();
        captionHbox.getChildren().addAll(captionlabel,captionField);
        
        

        Text path = new Text();
        Text fn = new Text();        
        
        
        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("MP4 Files", "*.mp4"));
            File selectedFile = fileChooser.showOpenDialog(null);
            path.setText(selectedFile.getPath());
            fn.setText(selectedFile.getName());
            if (selectedFile != null) {
                    path.setText(selectedFile.getPath());
                    fn.setText(selectedFile.getName());
                    ImportStatus.setText("File selected: " + selectedFile.getName());
            }
            else {
            ImportStatus.setText("File selection cancelled.");
            ErrorHandler eH = ui.getErrorHandler();
            //eH.processError(LanguagePropertyType.ERROR_TITLE, PATH_VIDEOS, PATH_VIDEOS);
            }            
        });
        HBox Confirm = new HBox();
        Button OK = new Button("OK");
        Button cancel = new Button("Cancel");
        Confirm.getChildren().addAll(OK,cancel);
        OK.setOnAction(e -> {
            double width = Double.parseDouble(widthField.getText());
            double height = Double.parseDouble(heightField.getText());
            ePortfolioModel ePortfolio = ui.getEPortfolio();
            ePortfolio.getSelectedPage().AddVideo(path.getText(),fn.getText(),captionField.getText(),width,height);
            videochooser.close();
            ui.reloadPageEditorPane(ePortfolio);            
        });
        cancel.setOnAction(e -> {
            videochooser.close();
        });        
        VBox vbox = new VBox(30);
        vbox.getChildren().addAll(labelHb,widthHBox,heightHbox,captionHbox,chooseFileButton,ImportStatus,Confirm);
        Scene FileChooserScene = new Scene(vbox,800,800);
        videochooser.setScene(FileChooserScene);
        videochooser.show();       
    }
    public void processEditVideo(ePortfolioGeneratorView ui) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        Stage videochooser = new Stage();
        videochooser.setTitle("Choose Video");
        Label label = new Label("Add Video File");
        HBox labelHb = new HBox();
        labelHb.getChildren().add(label);
        
        HBox widthHBox = new HBox(5);
        Label widthlabel = new Label("Width : ");
        TextField widthField = new TextField();
        widthHBox.getChildren().addAll(widthlabel,widthField);
        
        HBox heightHbox = new HBox(5);
        Label heightlabel = new Label("Height : ");
        TextField heightField = new TextField();
        heightHbox.getChildren().addAll(heightlabel,heightField);
        Text ImportStatus = new Text();
        
        HBox captionHbox = new HBox(5);
        Label captionlabel = new Label("Caption : ");
        TextField captionField = new TextField();
        captionHbox.getChildren().addAll(captionlabel,captionField);
        
        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("MP4 Files", "*.mp4"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {

                    ImportStatus.setText("File selected: " + selectedFile.getName());
            }
            else {
            ImportStatus.setText("File selection cancelled.");
            ErrorHandler eH = ui.getErrorHandler();
            //eH.processError(LanguagePropertyType.ERROR_TITLE, PATH_VIDEOS, PATH_VIDEOS);
            }            
        });
        HBox Confirm = new HBox();
        Button OK = new Button("OK");
        Button cancel = new Button("Cancel");
        Confirm.getChildren().addAll(OK,cancel);
        OK.setOnAction(e -> {
            videochooser.close();
        });
        cancel.setOnAction(e -> {
            videochooser.close();
        });        
        VBox vbox = new VBox(30);
        vbox.getChildren().addAll(labelHb,widthHBox,heightHbox,captionHbox,chooseFileButton,ImportStatus,Confirm);
        Scene FileChooserScene = new Scene(vbox,800,800);
        videochooser.setScene(FileChooserScene);
        videochooser.show();               
    }
}
