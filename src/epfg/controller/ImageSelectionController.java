package epfg.controller;

import epfg.Contents.ImageComponent;
import java.io.File;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;
import epfg.LanguagePropertyType;
import static epfg.LanguagePropertyType.ERROR_TITLE;
import static epfg.LanguagePropertyType.IMAGE_MISSING;
import static epfg.StartupConstants.PATH_EPORTFOLIO_IMAGES;
import epfg.error.ErrorHandler;
import epfg.model.Page;
import epfg.model.ePortfolioModel;
import epfg.view.PageListEditView;
import epfg.view.ePortfolioGeneratorView;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This controller provides a controller for when the user chooses to
 * select an image for the slide show.
 * 
 * @author Seunghyuk Sean Choi
 */
public class ImageSelectionController {
    
    /**
     * Default contstructor doesn't need to initialize anything
     */
    public ImageSelectionController() {    }
    

    public void processSelectImage(ePortfolioGeneratorView ui) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();	

        
        Stage imagechooser = new Stage();
        imagechooser.setTitle("Choose Image");
        Label label = new Label("Add Image File");
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
        TextField captionField = new TextField("");
        captionHbox.getChildren().addAll(captionlabel,captionField);
        
        Button chooseFileButton = new Button("Choose File");
        
        
        ImageView imageView = new ImageView();
        Label pathLabel = new Label();
        Label fnLabel = new Label();

        
        Label positionlabel = new Label("Choose position of image");
        ObservableList<String> posoptions = 
        FXCollections.observableArrayList(
            "Left",
            "Right",
            "Neither"
        );
        final ComboBox positions = new ComboBox(posoptions);
        HBox optionbox = new HBox();
        optionbox.getChildren().add(positions);
        
        
        
        
        
        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File selectedFile = fileChooser.showOpenDialog(null);
            int i = selectedFile.getName().lastIndexOf('.');
            
            if (selectedFile != null) {
                    String path = selectedFile.getPath().substring(0, selectedFile.getPath().indexOf(selectedFile.getName()));
                    String fileName = selectedFile.getName(); 
                    pathLabel.setText(path);
                    fnLabel.setText(fileName);
                    try {
                        URL fileURL = selectedFile.toURI().toURL();
                        Image image = new Image(fileURL.toExternalForm());
                        
                        imageView.setImage(image);
                        double scaledWidth = 500;
                        double perc = scaledWidth / image.getWidth();
                        double scaledHeight = image.getHeight() * perc;
                        imageView.setFitWidth(scaledWidth);
                        imageView.setFitHeight(scaledHeight);
                        widthField.setText(String.valueOf(image.getWidth()));
                        heightField.setText(String.valueOf(image.getHeight()));
                        
                        
                        ImportStatus.setText("File selected: " + selectedFile.getName());
                    } catch (Exception ex) {
                        
                    }
            }
            else {
            ImportStatus.setText("File selection cancelled.");
	    // @todo provide error message for no files selected
            
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(IMAGE_MISSING, props.getProperty(ERROR_TITLE), props.getProperty(ERROR_TITLE));
            }           
        });
        HBox Confirm = new HBox();
        Button OK = new Button("OK");
        Button cancel = new Button("Cancel");
        Confirm.getChildren().addAll(OK,cancel);
        
        //ActionHandler for Image Submit Button
        
        OK.setOnAction(e -> {
            double width = Double.parseDouble(widthField.getText());
            double height = Double.parseDouble(heightField.getText());
            String p = positions.getValue().toString();
            ePortfolioModel ePortfolio = ui.getEPortfolio();
            ePortfolio.getSelectedPage().AddImage(pathLabel.getText(),fnLabel.getText(),captionField.getText(),width,height,p);
            imagechooser.close();
            ui.reloadPageEditorPane(ePortfolio);
        });
        cancel.setOnAction(e -> {
            imagechooser.close();
        });        
        VBox vbox = new VBox(30);
        vbox.getChildren().addAll(labelHb,widthHBox,heightHbox,positionlabel,positions,captionHbox,chooseFileButton,imageView,ImportStatus,Confirm);
        Scene FileChooserScene = new Scene(vbox,800,900);
        imagechooser.setScene(FileChooserScene);
        imagechooser.show();    

    }

    public void EditImage(Page a, ImageComponent initImage, int index, ePortfolioGeneratorView ui) {
        PropertiesManager props = PropertiesManager.getPropertiesManager();	

        
        Stage imagechooser = new Stage();
        imagechooser.setTitle("Choose Image");
        Label label = new Label("Add Image File");
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
        TextField captionField = new TextField(initImage.getCaption());
        captionHbox.getChildren().addAll(captionlabel,captionField);
        
        Button chooseFileButton = new Button("Choose File");
        
        
        ImageView imageView = new ImageView();
        Label pathLabel = new Label();
        Label fnLabel = new Label();

        
        Label positionlabel = new Label("Choose position of image");
        ObservableList<String> posoptions = 
        FXCollections.observableArrayList(
            "Left",
            "Right",
            "Neither"
        );
        final ComboBox positions = new ComboBox(posoptions);
        HBox optionbox = new HBox();
        optionbox.getChildren().add(positions);
        
        pathLabel.setText(initImage.getPath());
        fnLabel.setText(initImage.getFileName());
        
        Image temp = new Image("file:"+initImage.getPath()+initImage.getFileName());
        imageView.setImage(temp);

                        double scaleW = 500;
                        double ratio = scaleW / temp.getWidth();
                        double scaleH = temp.getHeight() * ratio;
                        imageView.setFitWidth(scaleW);
                        imageView.setFitHeight(scaleH);        
        
        
        
        
        widthField.setText(Double.toString(initImage.getWidth()));
        heightField.setText(Double.toString(initImage.getHeight()));
        
        
        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File selectedFile = fileChooser.showOpenDialog(null);
            int i = selectedFile.getName().lastIndexOf('.');
            
            if (selectedFile != null) {
                    String path = selectedFile.getPath().substring(0, selectedFile.getPath().indexOf(selectedFile.getName()));
                    String fileName = selectedFile.getName(); 
                    pathLabel.setText(path);
                    fnLabel.setText(fileName);
                    try {
                        URL fileURL = selectedFile.toURI().toURL();
                        Image image = new Image(fileURL.toExternalForm());
                        
                        imageView.setImage(image);
                        double scaledWidth = 500;
                        double perc = scaledWidth / image.getWidth();
                        double scaledHeight = image.getHeight() * perc;
                        imageView.setFitWidth(scaledWidth);
                        imageView.setFitHeight(scaledHeight);
                        widthField.setText(String.valueOf(image.getWidth()));
                        heightField.setText(String.valueOf(image.getHeight()));
                        
                        
                        ImportStatus.setText("File selected: " + selectedFile.getName());
                    } catch (Exception ex) {
                        
                    }
            }
            else {
            ImportStatus.setText("File selection cancelled.");
	    // @todo provide error message for no files selected
            
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(IMAGE_MISSING, props.getProperty(ERROR_TITLE), props.getProperty(ERROR_TITLE));
            }           
        });
        HBox Confirm = new HBox();
        Button OK = new Button("OK");
        Button cancel = new Button("Cancel");
        Confirm.getChildren().addAll(OK,cancel);
        
        //ActionHandler for Image Submit Button
        
        OK.setOnAction(e -> {
            double width = Double.parseDouble(widthField.getText());
            double height = Double.parseDouble(heightField.getText());
            String p = positions.getValue().toString();
            ePortfolioModel ePortfolio = ui.getEPortfolio();
            a.EditImage(pathLabel.getText(),fnLabel.getText(),captionField.getText(),width,height,p,index);
            imagechooser.close();
            ui.reloadPageEditorPane(ePortfolio);
        });
        cancel.setOnAction(e -> {
            imagechooser.close();
        });        
        VBox vbox = new VBox(30);
        vbox.getChildren().addAll(labelHb,widthHBox,heightHbox,positionlabel,positions,captionHbox,chooseFileButton,imageView,ImportStatus,Confirm);
        Scene FileChooserScene = new Scene(vbox,800,900);
        imagechooser.setScene(FileChooserScene);
        imagechooser.show();    
    }
}