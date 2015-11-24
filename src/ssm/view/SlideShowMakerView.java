package ssm.view;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import ssm.LanguagePropertyType;
import static ssm.LanguagePropertyType.TOOLTIP_ADD_SLIDE;
import static ssm.LanguagePropertyType.TOOLTIP_EXIT;
import static ssm.LanguagePropertyType.TOOLTIP_LOAD_SLIDE_SHOW;
import static ssm.LanguagePropertyType.TOOLTIP_MOVE_UP;
import static ssm.LanguagePropertyType.TOOLTIP_MOVE_DOWN;
import static ssm.LanguagePropertyType.TOOLTIP_NEW_SLIDE_SHOW;
import static ssm.LanguagePropertyType.TOOLTIP_REMOVE_SLIDE;
import static ssm.LanguagePropertyType.TOOLTIP_SAVE_SLIDE_SHOW;
import static ssm.LanguagePropertyType.TOOLTIP_VIEW_SLIDE_SHOW;
import static ssm.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static ssm.StartupConstants.CSS_CLASS_SLIDE_EDIT_VIEW;
import static ssm.StartupConstants.CSS_CLASS_SLIDE_SHOW_EDIT_VBOX;
import static ssm.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static ssm.StartupConstants.CSS_CLASS_SLIDE_SELECTION;
import static ssm.StartupConstants.ICON_APPLICATION;
import static ssm.StartupConstants.ICON_ADD_SLIDE;
import static ssm.StartupConstants.ICON_EXIT;
import static ssm.StartupConstants.ICON_LOAD_SLIDE_SHOW;
import static ssm.StartupConstants.ICON_MOVE_UP;
import static ssm.StartupConstants.ICON_MOVE_DOWN;
import static ssm.StartupConstants.ICON_NEW_SLIDE_SHOW;
import static ssm.StartupConstants.ICON_SAVE_SLIDE_SHOW;
import static ssm.StartupConstants.ICON_VIEW_SLIDE_SHOW;
import static ssm.StartupConstants.ICON_REMOVE_SLIDE;
import static ssm.StartupConstants.ICON_TITLECHANGER;
import static ssm.StartupConstants.PATH_ICONS;
import static ssm.StartupConstants.STYLE_SHEET_UI;
import ssm.controller.FileController;
import ssm.controller.SlideShowEditController;
import ssm.model.Slide;
import ssm.model.SlideShowModel;
import ssm.error.ErrorHandler;
import ssm.file.SlideShowFileManager;

/**
 * This class provides the User Interface for this application,
 * providing controls and the entry points for creating, loading, 
 * saving, editing, and viewing slide shows.
 * 
 * @author McKilla Gorilla & Seunghyuk Sean Choi
 */
public class SlideShowMakerView {

    // THIS IS THE MAIN APPLICATION UI WINDOW AND ITS SCENE GRAPH
    Stage primaryStage;
    Scene primaryScene;

    // THIS PANE ORGANIZES THE BIG PICTURE CONTAINERS FOR THE
    // APPLICATION GUI
    BorderPane ssmPane;

    // THIS IS THE TOP TOOLBAR AND ITS CONTROLS
    
    // WORKSPACE
    HBox workspace;

    // THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
    VBox slideEditToolbar;
    Button addSlideButton;
    Button removeSlideButton;
    Button moveUpSlideButton;
    Button moveDownSlideButton;
    Button setTitleButton;
    
    // AND THIS WILL GO IN THE CENTER
    ScrollPane slidesEditorScrollPane;
    VBox slidesEditorPane;

    // THIS IS THE SLIDE SHOW WE'RE WORKING WITH
    SlideShowModel slideShow;

    // THIS IS FOR SAVING AND LOADING SLIDE SHOWS
    SlideShowFileManager fileManager;

    // THIS CLASS WILL HANDLE ALL ERRORS FOR THIS PROGRAM
    private ErrorHandler errorHandler;

    // THIS CONTROLLER WILL ROUTE THE PROPER RESPONSES
    // ASSOCIATED WITH THE FILE TOOLBAR
    private FileController fileController;
    
    // THIS CONTROLLER RESPONDS TO SLIDE SHOW EDIT BUTTONS
    private SlideShowEditController editController;
    
    /**
     * Default constructor, it initializes the GUI for use, but does not yet
     * load all the language-dependent controls, that needs to be done via the
     * startUI method after the user has selected a language.
     */
    public SlideShowMakerView() {
	// FIRST HOLD ONTO THE FILE MANAGER
	
	// MAKE THE DATA MANAGING MODEL
	slideShow = new SlideShowModel(this);

	// WE'LL USE THIS ERROR HANDLER WHEN SOMETHING GOES WRONG
	errorHandler = new ErrorHandler(this);
    }

    // ACCESSOR METHODS
    public SlideShowModel getSlideShow() {
	return slideShow;
    }

    public Stage getWindow() {
	return primaryStage;
    }

    public ErrorHandler getErrorHandler() {
	return errorHandler;
    }

    /**
     * Initializes the UI controls and gets it rolling.
     * 
     * @param initPrimaryStage The window for this application.
     * 
     * @param windowTitle The title for this window.
     */
    public void startUI(Stage initPrimaryStage, String windowTitle) {
	// THE TOOLBAR ALONG THE NORTH

        // INIT THE CENTER WORKSPACE CONTROLS BUT DON'T ADD THEM
	// TO THE WINDOW YET
	initWorkspace();

	// NOW SETUP THE EVENT HANDLERS
	initEventHandlers();
        

	// AND FINALLY START UP THE WINDOW (WITHOUT THE WORKSPACE)
	// KEEP THE WINDOW FOR LATER
	primaryStage = initPrimaryStage;
	initWindow(windowTitle);
        this.updateToolbarControls(false);
        this.reloadSlideShowPane(slideShow);
    }

    // UI SETUP HELPER METHODS
    private void initWorkspace() {
	// FIRST THE WORKSPACE ITSELF, WHICH WILL CONTAIN TWO REGIONS
	workspace = new HBox();
	
	// THIS WILL GO IN THE LEFT SIDE OF THE SCREEN
	slideEditToolbar = new VBox();
	slideEditToolbar.getStyleClass().add(CSS_CLASS_SLIDE_SHOW_EDIT_VBOX);
	addSlideButton = this.initChildButton(slideEditToolbar,		ICON_ADD_SLIDE,	    TOOLTIP_ADD_SLIDE,	    CSS_CLASS_VERTICAL_TOOLBAR_BUTTON,  false);
	removeSlideButton=this.initChildButton(slideEditToolbar, ICON_REMOVE_SLIDE, TOOLTIP_REMOVE_SLIDE, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        moveUpSlideButton = this.initChildButton(slideEditToolbar,   ICON_MOVE_UP,   TOOLTIP_MOVE_UP, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
	moveDownSlideButton = this.initChildButton(slideEditToolbar,   ICON_MOVE_DOWN, TOOLTIP_MOVE_DOWN, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        setTitleButton = this.initChildButton(slideEditToolbar,   ICON_TITLECHANGER, TOOLTIP_MOVE_DOWN, CSS_CLASS_VERTICAL_TOOLBAR_BUTTON, false);
        // AND THIS WILL GO IN THE CENTER
	slidesEditorPane = new VBox();
	slidesEditorScrollPane = new ScrollPane(slidesEditorPane);
        slidesEditorScrollPane.setStyle("-fx-background-color:transparent;");
	
	// NOW PUT THESE TWO IN THE WORKSPACE
	workspace.getChildren().add(slideEditToolbar);
	workspace.getChildren().add(slidesEditorScrollPane);
    }

    private void initEventHandlers() {
	// FIRST THE FILE CONTROLS
	
	// THEN THE SLIDE SHOW EDIT CONTROLS
	editController = new SlideShowEditController(this);
	addSlideButton.setOnAction(e -> {
	    editController.processAddSlideRequest();
            updateToolbarControls(false);
	});
        removeSlideButton.setOnAction (e -> 
        {
            editController.processRemoveSlideRequest();
            updateToolbarControls(false);
        });
        moveUpSlideButton.setOnAction (e ->
        {
            editController.processMoveUpSlideRequest();
            updateToolbarControls(false);
        });
        moveDownSlideButton.setOnAction (e ->
        {
            editController.processMoveDownSlideRequest();
            updateToolbarControls(false);
        });
        setTitleButton.setOnAction (e ->
        {    editController.processChangeSlideTitleRequest();
        updateToolbarControls(false);
        });
        
        
    }

    /**
     * This function initializes all the buttons in the toolbar at the top of
     * the application window. These are related to file management.
     */

    private void initWindow(String windowTitle) {
	// SET THE WINDOW TITLE
	primaryStage.setTitle(windowTitle);
        Image icon = new Image("file:"+PATH_ICONS+ICON_APPLICATION);
        primaryStage.getIcons().add(icon);
        
	// GET THE SIZE OF THE SCREEN
	Screen screen = Screen.getPrimary();
	Rectangle2D bounds = screen.getVisualBounds();

	// AND USE IT TO SIZE THE WINDOW
	primaryStage.setX(bounds.getMinX());
	primaryStage.setY(bounds.getMinY());
	primaryStage.setWidth(800);
	primaryStage.setHeight(800);

        // SETUP THE UI, NOTE WE'LL ADD THE WORKSPACE LATER
	ssmPane = new BorderPane();
        ssmPane.setId("main-application");
        ssmPane.getStylesheets().add(STYLE_SHEET_UI);	
	primaryScene = new Scene(ssmPane);
        HBox Confirmation = new HBox();
        Button OK = new Button("OK");
        Button Cancel = new Button("Cancel");
        OK.setOnAction(e -> {
            primaryStage.close();
        });
        Cancel.setOnAction(e -> {
            primaryStage.close();
        });
        Confirmation.getChildren().addAll(OK,Cancel);
	ssmPane.setBottom(Confirmation);
        // NOW TIE THE SCENE TO THE WINDOW, SELECT THE STYLESHEET
	// WE'LL USE TO STYLIZE OUR GUI CONTROLS, AND OPEN THE WINDOW
	primaryScene.getStylesheets().add(STYLE_SHEET_UI);
	primaryStage.setScene(primaryScene);
	primaryStage.show();
    }
    
    /**
     * This helps initialize buttons in a toolbar, constructing a custom button
     * with a customly provided icon and tooltip, adding it to the provided
     * toolbar pane, and then returning it.
     */
    public Button initChildButton(
	    Pane toolbar, 
	    String iconFileName, 
	    LanguagePropertyType tooltip, 
	    String cssClass,
	    boolean disabled) {
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	String imagePath = "file:" + PATH_ICONS + iconFileName;
	Image buttonImage = new Image(imagePath);
	Button button = new Button();
	button.getStyleClass().add(cssClass);
	button.setDisable(disabled);
	button.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
	button.setTooltip(buttonTooltip);
	toolbar.getChildren().add(button);
	return button;
    }
    
    /**
     * Updates the enabled/disabled status of all toolbar
     * buttons.
     * 
     * @param saved 
     */
    public void updateToolbarControls(boolean saved) {
	// FIRST MAKE SURE THE WORKSPACE IS THERE
	ssmPane.setCenter(workspace);
	
	// NEXT ENABLE/DISABLE BUTTONS AS NEEDED IN THE FILE TOOLBAR
	
        boolean selected = slideShow.isSlideSelected();
        int index = slideShow.getSlides().indexOf(slideShow.getSelectedSlide());
        int slideSize = slideShow.getSlides().size();
	// AND THE SLIDESHOW EDIT TOOLBAR
	addSlideButton.setDisable(false);
        removeSlideButton.setDisable(!selected);
        moveUpSlideButton.setDisable(!selected || index == 0 || index == slideSize);
        moveDownSlideButton.setDisable(!selected || index >=slideSize-1 );
        setTitleButton.setDisable(slideShow.getSlides().size() == 0);
        
    }

    /**
     * Uses the slide show data to reload all the components for
     * slide editing.
     * 
     * @param slideShowToLoad SLide show being reloaded.
     */
    public void reloadSlideShowPane(SlideShowModel slideShowToLoad) {
	slidesEditorPane.getChildren().clear();
        slideShow = slideShowToLoad;
        ObservableList<Slide> list = slideShowToLoad.getSlides();
        for (Slide slide : list) {
            SlideEditView slideEditor = new SlideEditView(slide, this);
            slidesEditorPane.getChildren().add(slideEditor);
        }
    }
    public void clearCSS() {
	slidesEditorPane.getChildren().clear();
        ObservableList<Slide> list = slideShow.getSlides();
        for (Slide slide : list) {
            SlideEditView slideEditor = new SlideEditView(slide, this);
            slideEditor.getStyleClass().clear();
            //slideEditor.getStyleClass().add(CSS_CLASS_SLIDE_EDIT_VIEW);
            //slidesEditorPane.getChildren().add(slideEditor);
        }
    }
}
    
