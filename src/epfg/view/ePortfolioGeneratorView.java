/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.view;

import epfg.Contents.PortfolioComponent;
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
import epfg.LanguagePropertyType;
import static epfg.LanguagePropertyType.TOOLTIP_ADD_PAGE;
import static epfg.LanguagePropertyType.TOOLTIP_EXIT;
import static epfg.LanguagePropertyType.TOOLTIP_LOAD_EPORTFOLIO;
import static epfg.LanguagePropertyType.TOOLTIP_MOVE_UP;
import static epfg.LanguagePropertyType.TOOLTIP_MOVE_DOWN;
import static epfg.LanguagePropertyType.TOOLTIP_NEW_EPORTFOLIO;
import static epfg.LanguagePropertyType.TOOLTIP_REMOVE_PAGE;
import static epfg.LanguagePropertyType.TOOLTIP_SAVE_EPORTFOLIO;
import static epfg.LanguagePropertyType.TOOLTIP_VIEW_EPORTFOLIO;
import static epfg.StartupConstants.CSS_CLASS_BUTTONS;
import static epfg.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static epfg.StartupConstants.CSS_CLASS_PAGE_EDIT_VIEW;
import static epfg.StartupConstants.CSS_CLASS_PAGE_EDIT_VBOX;
import static epfg.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static epfg.StartupConstants.CSS_CLASS_PAGE_SELECTION;
import static epfg.StartupConstants.ICON_APPLICATION;
import static epfg.StartupConstants.ICON_ADD_PAGE;
import static epfg.StartupConstants.ICON_CLEAR;
import static epfg.StartupConstants.ICON_EXIT;
import static epfg.StartupConstants.ICON_EXPORT;
import static epfg.StartupConstants.ICON_IMAGE;
import static epfg.StartupConstants.ICON_LAYOUT;
import static epfg.StartupConstants.ICON_LOAD_EPORTFOLIO;
import static epfg.StartupConstants.ICON_MOVE_UP;
import static epfg.StartupConstants.ICON_MOVE_DOWN;
import static epfg.StartupConstants.ICON_NEW_EPORTFOLIO;
import static epfg.StartupConstants.ICON_REMOVE;
import static epfg.StartupConstants.ICON_SAVE_EPORTFOLIO;
import static epfg.StartupConstants.ICON_VIEW_EPORTFOLIO;
import static epfg.StartupConstants.ICON_REMOVE_PAGE;
import static epfg.StartupConstants.ICON_REVERT;
import static epfg.StartupConstants.ICON_SAVE_AS_EPORTFOLIO;
import static epfg.StartupConstants.ICON_SLIDESHOW;
import static epfg.StartupConstants.ICON_TEXT;
import static epfg.StartupConstants.ICON_TITLECHANGER;
import static epfg.StartupConstants.ICON_VIDEO;
import static epfg.StartupConstants.PATH_ICONS;
import static epfg.StartupConstants.STYLE_SHEET_UI;
import epfg.controller.FileController;
import epfg.controller.PageEditController;
import epfg.controller.ePortfolioEditController;
import epfg.model.Page;
import epfg.model.ePortfolioModel;
import epfg.error.ErrorHandler;
import epfg.file.ePortfolioFileManager;
import static java.awt.Color.white;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 *
 * @author shcho
 */
public class ePortfolioGeneratorView {
    // THIS IS THE MAIN APPLICATION UI WINDOW AND ITS SCENE GRAPH 
    Stage primaryStage;
    Scene primaryScene;
    
    //THIS IS THE MAIN APPLICATION UI WINDOW AND ITS SCENE GRAPH
    BorderPane epfgPane;
    
    FlowPane fileToolbarPane;
    Button newEPortfolioButton;
    Button loadEPortfolioButton;
    Button saveEPortfolioButton;
    Button saveAsEPortfolioButton;
    Button exportButton;
    Button viewEPortfolioButton;
    Button exitButton;
    
    //WORKSPACE
    HBox workspace;
    
    //PageListEditor
    VBox PageListVBox;
    VBox PageListEditToolbarVBox;
    ScrollPane PageListPane; 
    Button AddPageButton;
    Button RemovePageButton;
    
    //ePortfolio Information and Page editor
    VBox pageEditorPane;
    FlowPane pageToolbarPane;
    ScrollPane pageComponentsPane;
    VBox pageComponentVBox;
    
    TextField ePortfolioTitleField;
    TextField pageTitleField;
    Button ePortfolioTitleSubmitButton;
    Button pageTitleSubmitButton;
    
    Button AddImageButton;
    Button AddVideoButton;
    Button AddTextButton;
    Button AddSlideShowButton;
    Button RemoveComponentButton;
    Button ClearComponentButton;
    Button RevertButton;
    Button SelectLayoutButton;
    Button SelectFontButton;
    Button EnterStudentInfoButton;
    Button AddFooterButton;
    Button SelectBannerImageButton;
    ePortfolioModel ePortfolio;
    ePortfolioFileManager fileManager;
    private ErrorHandler errorHandler;
    private ePortfolioEditController portfolioEditController;
    private FileController fileController;
    private PageEditController pageEditController;
    
    ePortfolioWebView webview;
    
    public ePortfolioGeneratorView(ePortfolioFileManager initfileManager) {
        fileManager = initfileManager;
        
        ePortfolio = new ePortfolioModel(this);
        
        errorHandler = new ErrorHandler(this);
        
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void startUI(Stage initPrimaryStage, String appTitle) {
        initFileToolbar();
        initPageListToolbar();
        initPageEditPane();
        initWorkspace();
        initEventHandlers();
        primaryStage = initPrimaryStage;
        initWindow(appTitle);
        	epfgPane.setCenter(workspace);
    }
    private void initWorkspace() {
        workspace = new HBox();
        workspace.getChildren().add(PageListEditToolbarVBox);
        PageListEditToolbarVBox.getStyleClass().add(CSS_CLASS_PAGE_EDIT_VBOX);
        workspace.getChildren().add(PageListPane);
        workspace.getChildren().add(pageEditorPane);
        
        
    }
    private void initEventHandlers(){
	// FIRST THE FILE CONTROLS
	fileController = new FileController(this, fileManager);
        newEPortfolioButton.setOnAction(e -> {
            fileController.handleNewEPortfolioRequest();
        });
        loadEPortfolioButton.setOnAction(e -> {
            fileController.handleLoadEPortfolioRequest();
        });
        saveEPortfolioButton.setOnAction(e -> {
            fileController.handleSaveEPortfolioRequest();
        });
        saveAsEPortfolioButton.setOnAction(e -> {
            fileController.handleSaveAsEPortfolioRequest();
        });
        exportButton.setOnAction(e -> {
            fileController.handleExportEPortfolioRequest();
        });
        viewEPortfolioButton.setOnAction( e-> {
           Stage webviewexample = new Stage();
           webview = new ePortfolioWebView(this, "ePortfolioWebview");
           webview.start(webviewexample);
        });
        exitButton.setOnAction(e -> {
            fileController.handleExitRequest();
        });
        
        pageEditController = new PageEditController(this);
        AddImageButton.setOnAction(e -> {
            //pageEditController.processAddImageRequest();
            AddImage();
        });
        AddVideoButton.setOnAction(e -> {
            //pageEditController.processAddVideoRequest();
            AddVideo();
        });
        AddTextButton.setOnAction(e -> {
            //pageEditController.processAddTextRequest();
            AddText();
        });
        AddSlideShowButton.setOnAction(e -> {
            //pageEditController.processAddSlideShowRequest();
            AddSlideShow();
        });
        RemoveComponentButton.setOnAction(e -> {
            //pageEditController.processRemoveComponentRequest();
        });
        ClearComponentButton.setOnAction(e -> {
            //pageEditController.processClearComponentsRequest();
        });
        RevertButton.setOnAction(e -> {
            pageEditController.processRevertChangeRequest();
        });
        SelectLayoutButton.setOnAction (e -> {
           layoutSelector(); 
        });
        SelectFontButton.setOnAction (e -> {
           FontSelector(); 
        });
        EnterStudentInfoButton.setOnAction(e -> {
            EnterStudent();
        });
        AddFooterButton.setOnAction(e -> {
            AddFooter();
        });
        SelectBannerImageButton.setOnAction (e-> {
            SelectBannerImage();
        });
        
    }
    private void initFileToolbar(){
	fileToolbarPane = new FlowPane();
        fileToolbarPane.setHgap(30);
        fileToolbarPane.setId("filetoolbar");
        fileToolbarPane.getStylesheets().add(STYLE_SHEET_UI);
        fileToolbarPane.setAlignment(Pos.CENTER);
        
        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
	// START AS ENABLED (false), WHILE OTHERS DISABLED (true)
	PropertiesManager props = PropertiesManager.getPropertiesManager();
        newEPortfolioButton = initChildButton(fileToolbarPane, ICON_NEW_EPORTFOLIO,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        loadEPortfolioButton = initChildButton(fileToolbarPane, ICON_LOAD_EPORTFOLIO,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveEPortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_EPORTFOLIO,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        saveAsEPortfolioButton = initChildButton(fileToolbarPane, ICON_SAVE_AS_EPORTFOLIO,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        exportButton = initChildButton(fileToolbarPane, ICON_EXPORT,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        viewEPortfolioButton = initChildButton(fileToolbarPane, ICON_VIEW_EPORTFOLIO,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        exitButton = initChildButton(fileToolbarPane, ICON_EXIT,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
    }
    private void initWindow(String appTitle){
        primaryStage.setTitle(appTitle);
        Image icon = new Image("file:"+PATH_ICONS+ICON_APPLICATION);
        primaryStage.getIcons().add(icon);
        
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        
	primaryStage.setX(bounds.getMinX());
	primaryStage.setY(bounds.getMinY());
	primaryStage.setWidth(bounds.getWidth());
	primaryStage.setHeight(bounds.getHeight());
        
        epfgPane = new BorderPane();
        epfgPane.setId("main-application");
        epfgPane.getStylesheets().add(STYLE_SHEET_UI);
        epfgPane.setTop(fileToolbarPane);
        primaryScene = new Scene(epfgPane);
        
        primaryScene.getStylesheets().add(STYLE_SHEET_UI);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }
    
    private Button initChildButton(
            Pane toolbar,
            String iconFileName,
            LanguagePropertyType tooltip,
            String cssClass,
            boolean disabled
            ) {
	PropertiesManager props = PropertiesManager.getPropertiesManager();
	String imagePath = "file:" + PATH_ICONS + iconFileName;
	Image buttonImage = new Image(imagePath);
	Button button = new Button();
	button.getStyleClass().add(CSS_CLASS_BUTTONS);
	button.setDisable(disabled);
	button.setGraphic(new ImageView(buttonImage));
	Tooltip buttonTooltip = new Tooltip(props.getProperty(tooltip.toString()));
	button.setTooltip(buttonTooltip);
	toolbar.getChildren().add(button);
	return button;        
    }

    private void initPageListToolbar() {
        PageListVBox = new VBox(5);
        PageListEditToolbarVBox = new VBox(10);
        PageListEditToolbarVBox.setAlignment(Pos.TOP_CENTER);
        PageListEditToolbarVBox.setId("PageListToolbarVBox");
        PageListVBox.setId("PageListVBox");
        PageListVBox.getStylesheets().add(STYLE_SHEET_UI);
        PageListEditToolbarVBox.getStylesheets().add(STYLE_SHEET_UI);
        
        PageListPane = new ScrollPane();
        PageListPane.getStyleClass().add("transparent");
        PageListPane.setContent(PageListVBox);
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        AddPageButton = initChildButton(PageListEditToolbarVBox, ICON_ADD_PAGE,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        RemovePageButton = initChildButton(PageListEditToolbarVBox, ICON_REMOVE_PAGE,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        //AddPageButton = new Button();
        //RemovePageButton = new Button();
	/**fileToolbarPane = new FlowPane();
        fileToolbarPane.setId("filetoolbar");
        fileToolbarPane.getStylesheets().add(STYLE_SHEET_UI);
        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
	// START AS ENABLED (false), WHILE OTHERS DISABLED (true)
	
        newEPortfolioButton = initChildButton(fileToolbarPane, ICON_NEW_EPORTFOLIO,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        **/ 
            PageEditView view = new PageEditView("afs;laksjdf;alksdjf;das");
            PageListVBox.getChildren().add(view);        
    }
    
    private void initPageEditPane() {
        pageEditorPane = new VBox(5);
        HBox epfgTitleHBox = new HBox();
        HBox pageTitleHBox = new HBox();
        Label entertitle = new Label("ePortfolio Title : ");
        entertitle.setId("titlelabel");
        ePortfolioTitleField = new TextField();
        Label enterPageTitle = new Label("Page Title : ");
        enterPageTitle.setId("titlelabel");
        pageTitleField = new TextField();
        ePortfolioTitleSubmitButton = new Button("submit");
        pageTitleSubmitButton = new Button("submit");
        
        HBox titleHBox = new HBox();
        VBox titlelableVBox = new VBox();
        VBox titleEnterVBox = new VBox();
        
        epfgTitleHBox.getChildren().add(ePortfolioTitleField);
        epfgTitleHBox.getChildren().add(ePortfolioTitleSubmitButton);
 
        pageTitleHBox.getChildren().add(pageTitleField);
        pageTitleHBox.getChildren().add(pageTitleSubmitButton);

        titlelableVBox.getChildren().addAll(entertitle,enterPageTitle);
        titleEnterVBox.getChildren().addAll(epfgTitleHBox,pageTitleHBox);
        titleHBox.getChildren().addAll(titlelableVBox,titleEnterVBox);
        
        pageToolbarPane = new FlowPane();
        HBox pageToolbarHBox = new HBox(5);
        pageToolbarPane.getChildren().add(pageToolbarHBox);
        AddImageButton = initChildButton(pageToolbarHBox,ICON_IMAGE, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
                //new Button(ICON_IMAGE);
        AddVideoButton = initChildButton(pageToolbarHBox,ICON_VIDEO, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        AddTextButton  = initChildButton(pageToolbarHBox,ICON_TEXT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        AddSlideShowButton = initChildButton(pageToolbarHBox,ICON_SLIDESHOW, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        RemoveComponentButton = initChildButton(pageToolbarHBox,ICON_REMOVE, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        ClearComponentButton = initChildButton(pageToolbarHBox,ICON_CLEAR, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        RevertButton = initChildButton(pageToolbarHBox,ICON_REVERT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        SelectLayoutButton = initChildButton(pageToolbarHBox,ICON_LAYOUT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        SelectFontButton = initChildButton(pageToolbarHBox,ICON_LAYOUT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        EnterStudentInfoButton = initChildButton(pageToolbarHBox,ICON_LAYOUT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
//pageToolbarPane.getChildren().addAll(AddImageButton,AddVideoButton,AddTextButton,AddSlideShowButton,RemoveComponentButton,ClearComponentButton,RevertButton);
        AddFooterButton = initChildButton(pageToolbarHBox,ICON_LAYOUT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        SelectBannerImageButton = initChildButton(pageToolbarHBox,ICON_LAYOUT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        pageComponentsPane = new ScrollPane();
        pageComponentVBox = new VBox();
        pageComponentsPane.setContent(pageComponentVBox);
            
            Text ntitle = new Text("test");
            Rectangle frame = new Rectangle();
            frame.setWidth(500);
            frame.setHeight(60);
            frame.setFill(Color.RED);
            StackPane stack = new StackPane();
            stack.getChildren().addAll(frame,ntitle);
            pageComponentVBox.getChildren().add(stack);
        //pageEditorPane.getChildren().add(epfgTitleHBox);
        //pageEditorPane.getChildren().add(pageTitleHBox);
        pageEditorPane.getChildren().add(titleHBox);
        pageEditorPane.getChildren().add(pageToolbarPane);
        pageEditorPane.getChildren().add(pageComponentsPane);
     
        
        
        
    }
    
    public void updateToolbarControls(boolean saved) {
	// FIRST MAKE SURE THE WORKSPACE IS THERE
	epfgPane.setCenter(workspace);
	
	// NEXT ENABLE/DISABLE BUTTONS AS NEEDED IN THE FILE TOOLBAR
/**	saveEPortfolioButton.setDisable(saved);
	//viewSlideShowButton.setDisable(slideShow.getSlides().size() ==0);
	
        boolean selected = ePortfolio.isPageSelected();
      //  int index = ePortfolio.getPages().indexOf(slideShow.getSelectedSlide());
        int pageSize = ePortfolio.getPages().size();
	// AND THE SLIDESHOW EDIT TOOLBAR
        newEPortfolioButton.setDisable(false);
        loadEPortfolioButton.setDisable(false);
        saveEPortfolioButton.setDisable(false);
        saveAsEPortfoliobutton.setDisable(false);
        exportButton.setDisable(false);
        exitButton.setDisable(false);
**/        
    }
    public void reloadPageListVBox(ePortfolioModel ePortfolioToLoad) {
        PageListVBox.getChildren().clear();
        ePortfolio = ePortfolioToLoad;
        ObservableList<Page> list = ePortfolio.getPages();
        for (Page page : list) {
            Text ntitle = new Text(page.getTitle());
            Rectangle frame = new Rectangle();
            frame.setWidth(60);
            frame.setHeight(60);
            frame.setArcWidth(60);
            frame.setArcHeight(60);
            StackPane stack = new StackPane();
            stack.getChildren().addAll(frame,ntitle);
            PageListVBox.getChildren().add(stack);
        }
    }
    public void reloadPageEditorPane(ePortfolioModel ePortfolioToLoad) {
        PageListVBox.getChildren().clear();
        ePortfolio = ePortfolioToLoad;
        ePortfolioTitleField.setText(ePortfolio.getTitle());
        pageTitleField.setText("text");       
        Page selected = ePortfolio.getSelectedPage();
        ObservableList<PortfolioComponent> list = selected.getComponents();
        for (PortfolioComponent component : list) {
            
       }
        
        
    }    
    //temporal for implementation HW6
    private void layoutSelector() {
        List<String> choices = new ArrayList<>();
        choices.add("Layout A");
        choices.add("Layout B");
        choices.add("Layout C");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Layout A", choices);
        dialog.setTitle("Choose Layout");
        dialog.setHeaderText("Choose Layout");
        dialog.setContentText("Select :");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
           
        }
        else {
            
        }
           
    }

    private void FontSelector() {
        List<String> choices = new ArrayList<>();
        choices.add("Font A");
        choices.add("Font B");
        choices.add("Font C");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Font A", choices);
        dialog.setTitle("Choose Font");
        dialog.setHeaderText("Choose Font");
        dialog.setContentText("Select :");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
           
        }
        else {
            
        }
           
    }
    private void EnterStudent() {
        TextInputDialog dialog = new TextInputDialog("Anonymous");
        dialog.setTitle("Enter Student Info");
        dialog.setHeaderText("Put your NAme");
        dialog.setContentText("Please enter your name:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
}

    }
    
    private void AddFooter() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Add Footer Text");
        dialog.setHeaderText("Enter Footer Text");

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {

        }
    }
    private void SelectBannerImage() {
        Dialog dialog = new Dialog();
        dialog.setTitle("Choose Banner Image");
        dialog.setHeaderText("Choose Banner Image");
        
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {

        }        
    }

    private void AddSlideShow() {
    }

    private void AddText() {
    }

    private void AddVideo() {
    }

    private void AddImage() {
    }
}
