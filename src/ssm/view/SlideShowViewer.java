/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ssm.view;

import java.io.File;
import java.net.URL;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.Box;
import static ssm.StartupConstants.DEFAULT_SLIDE_SHOW_HEIGHT;
import static ssm.StartupConstants.DEFAULT_THUMBNAIL_WIDTH;
import static ssm.StartupConstants.ICON_APPLICATION;
import static ssm.StartupConstants.ICON_SLIDESHOW;
import static ssm.StartupConstants.PATH_ICONS;
import static ssm.StartupConstants.STYLE_SHEET_UI;
import static ssm.file.SlideShowFileManager.SLASH;
import ssm.model.Slide;
import ssm.model.SlideShowModel;

/**
 *
 * @author Seunghyuk
 */
public class SlideShowViewer {
    
    Stage primaryStage;
    Scene primaryScene;
    
    HBox pancake;
    HBox seaweed;
    VBox rice;
    
    Button NextButton;
    Button PrevButton;
    Button SlideshowLaunch;
    Label caption;
    ImageView slideShowView;
    
    
    private SlideShowModel slideshow;
    private SlideShowMakerView ui;
    private int current;
    //String imagePath = slide.getImagePath() + SLASH + slide.getImageFileName();
    
    public SlideShowViewer(SlideShowMakerView initUi)
    {

        ui = initUi;
        slideshow=initUi.getSlideShow();
        current = 0;
    }
    public void startSlideShow()
    {
        primaryStage = new Stage();
        pancake = new HBox();
        slideShowView = new ImageView();
        rice = new VBox();
        seaweed = new HBox();
        initButtons();
        initWindow();
/**

   **/     
    }
    private void initWindow() {
	// SET THE WINDOW TITLE
	primaryStage.setTitle("SlideShow");
        Image icon = new Image("file:"+PATH_ICONS+ICON_SLIDESHOW);
        primaryStage.getIcons().add(icon);

	// GET THE SIZE OF THE SCREEN
	Screen screen = Screen.getPrimary();
	
        // SETUP THE UI, NOTE WE'LL ADD THE WORKSPACE LATER
       /**
        sausage = new ScrollPane();
        sausage.setStyle("-fx-background-color:transparent;");
        pancake.getChildren().add(sausage);
	primaryScene = new Scene(sausage);
	**/
        slideShowView = new ImageView();
        caption = new Label();
        reloadSlideShow(slideshow, current);
        updateButtons();
        
        pancake.getChildren().addAll(PrevButton,NextButton);
        rice.getChildren().addAll(seaweed,pancake);
        primaryScene = new Scene(rice);
        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
	// WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
	primaryScene.getStylesheets().add(STYLE_SHEET_UI);
	primaryStage.setScene(primaryScene);
	primaryStage.show();
    }
    public void initButtons()
    {
        NextButton = new Button("Next");
        NextButton.setOnAction(e -> {
            this.nextSlide();
        });
        PrevButton = new Button("Prev");
        PrevButton.setOnAction(e -> {
            this.prevSlide();
        });
    }
    public void updateButtons()
    {
        PrevButton.setDisable(current==0 || slideshow.getSlides().size() ==0);
        NextButton.setDisable(current==slideshow.getSlides().size()-1 || slideshow.getSlides().size() ==0);
    }
    public void nextSlide()
    {
        current +=1;
        reloadSlideShow(slideshow,current);
        this.updateButtons();
    }
    public void prevSlide()
    {
        current -= 1;
        reloadSlideShow(slideshow,current);
        this.updateButtons();
    }
    public void reloadSlideShow(SlideShowModel slideShowtoLoad, int index)
    {
        seaweed.getChildren().clear();
        Slide slide = slideShowtoLoad.getSlides().get(index);
        String imagePath = slide.getImagePath() + slide.getImageFileName(); 
        ScrollPane captionpane = new ScrollPane(caption);
        caption.setText(slide.getCaption());
        caption.setWrapText(true);
        captionpane.setFitToHeight(true);
        captionpane.prefWidth(1000.00);
	File file = new File(imagePath);
	try {
	    // GET AND SET THE IMAGE
	    URL fileURL = file.toURI().toURL();
	    Image slideImage = new Image(fileURL.toExternalForm());
	    slideShowView.setImage(slideImage);
	    // AND RESIZE IT
	    double scaledHeight = DEFAULT_SLIDE_SHOW_HEIGHT;
	    double perc = scaledHeight / slideImage.getHeight();
	    double scaledWidth = slideImage.getWidth() * perc;
	    slideShowView.setFitWidth(scaledWidth);
	    slideShowView.setFitHeight(scaledHeight);
            
	} catch (Exception e) {
	    // @todo - use Error handler to respond to missing image
	}
        seaweed.getChildren().addAll(slideShowView,caption);
    }
}
