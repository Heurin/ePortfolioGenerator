/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.view;

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
import static epfg.StartupConstants.ICON_BANNER;
import static epfg.StartupConstants.ICON_CLEAR;
import static epfg.StartupConstants.ICON_COLOR;
import static epfg.StartupConstants.ICON_DOT;
import static epfg.StartupConstants.ICON_EXIT;
import static epfg.StartupConstants.ICON_EXPORT;
import static epfg.StartupConstants.ICON_FONT;
import static epfg.StartupConstants.ICON_FOOTER;
import static epfg.StartupConstants.ICON_HEADER;
import static epfg.StartupConstants.ICON_HYPERLINK;
import static epfg.StartupConstants.ICON_IMAGE;
import static epfg.StartupConstants.ICON_LAYOUT;
import static epfg.StartupConstants.ICON_LIST;
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
import static epfg.StartupConstants.ICON_STUDENT;
import static epfg.StartupConstants.ICON_TEXT;
import static epfg.StartupConstants.ICON_TITLECHANGER;
import static epfg.StartupConstants.ICON_TRANSITION;
import static epfg.StartupConstants.ICON_VIDEO;
import static epfg.StartupConstants.PATH_ICONS;
import static epfg.StartupConstants.PATH_IMAGES;
import static epfg.StartupConstants.STYLE_SHEET_UI;
import epfg.controller.FileController;
import epfg.controller.PageEditController;
import epfg.controller.ePortfolioEditController;
import epfg.model.Page;
import epfg.model.ePortfolioModel;
import epfg.error.ErrorHandler;
import epfg.file.ePortfolioFileManager;
import static java.awt.Color.white;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import ssm.view.SlideShowMakerView;

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
    Button webTransitionButton;
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
    Button ColorChooserButton;
    Button AddListButton;
    Button AddHyperLinkButton;
    Button AddHeaderButton;
    ePortfolioModel ePortfolio;
    ePortfolioFileManager fileManager;
    int transitionstate;
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
        //initPageListToolbar();
        //initPageEditPane();
        initWorkspace();
        initFileToolbarEventHandlers();
        primaryStage = initPrimaryStage;
        initWindow(appTitle);
        	epfgPane.setCenter(workspace);
    }
    private void initWorkspace() {
        workspace = new HBox();

   
    }
    
    private void initTotalEventHandlers() {
        initFileToolbarEventHandlers();
        initPageListEventHandler();
        initPageComponentEventHandler();
    }
    
    
    private void initFileToolbarEventHandlers(){
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
    }
    public void initPageListEventHandler() {
        portfolioEditController = new ePortfolioEditController(this);
        
        AddPageButton.setOnAction(e -> {
            portfolioEditController.processAddPageRequest();
        });
        RemovePageButton.setOnAction(e -> {
            portfolioEditController.processRemovePageRequst();
        });
    }
    
    public void initPageComponentEventHandler() {
        
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
            pageComponentVBox.getChildren().clear();
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
        ColorChooserButton.setOnAction (e-> {
            ColorSelector();
        });
        webTransitionButton.setOnAction (e -> {
            webtransition();
        });
        AddListButton.setOnAction (e -> {
            AddList();
        });
        AddHyperLinkButton.setOnAction(e -> {
            InputHyperLink();
        });
        AddHeaderButton.setOnAction(e -> {
            
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

    public void initPageListToolbar() {
        workspace.getChildren().clear();
        PageListVBox = new VBox(5);
        PageListEditToolbarVBox = new VBox(10);
        PageListEditToolbarVBox.setAlignment(Pos.TOP_CENTER);
        PageListEditToolbarVBox.setId("PageListToolbarVBox");
        PageListVBox.setId("PageListVBox");
        PageListVBox.getStylesheets().add(STYLE_SHEET_UI);
        PageListEditToolbarVBox.getStylesheets().add(STYLE_SHEET_UI);
        
        PageListPane = new ScrollPane();
        PageListPane.getStyleClass().add("transparent");
        PageListPane.setId("PageListPane");
        PageListPane.setContent(PageListVBox);
        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        AddPageButton = initChildButton(PageListEditToolbarVBox, ICON_ADD_PAGE,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        RemovePageButton = initChildButton(PageListEditToolbarVBox, ICON_REMOVE_PAGE,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);

        workspace.getChildren().add(PageListEditToolbarVBox);
        PageListEditToolbarVBox.getStyleClass().add(CSS_CLASS_PAGE_EDIT_VBOX);        
        workspace.getChildren().add(PageListPane);

//AddPageButton = new Button();
        //RemovePageButton = new Button();
	/**fileToolbarPane = new FlowPane();
        fileToolbarPane.setId("filetoolbar");
        fileToolbarPane.getStylesheets().add(STYLE_SHEET_UI);
        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
	// START AS ENABLED (false), WHILE OTHERS DISABLED (true)
	
        newEPortfolioButton = initChildButton(fileToolbarPane, ICON_NEW_EPORTFOLIO,	TOOLTIP_NEW_EPORTFOLIO,	    CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        **/    
    }
    
    public void initPageEditPane() {
        pageEditorPane = new VBox(5);
        HBox epfgTitleHBox = new HBox();
        HBox pageTitleHBox = new HBox();
        Label entertitle = new Label("ePortfolio Title : ");
        entertitle.setId("titlelabel");
        ePortfolioTitleField = new TextField(ePortfolio.getTitle());
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
        AddHeaderButton = initChildButton(pageToolbarHBox, ICON_HEADER, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        AddTextButton  = initChildButton(pageToolbarHBox,ICON_TEXT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        AddListButton = initChildButton(pageToolbarHBox,ICON_LIST, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        AddHyperLinkButton = initChildButton(pageToolbarHBox,ICON_HYPERLINK, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        AddSlideShowButton = initChildButton(pageToolbarHBox,ICON_SLIDESHOW, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        RemoveComponentButton = initChildButton(pageToolbarHBox,ICON_REMOVE, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        ClearComponentButton = initChildButton(pageToolbarHBox,ICON_CLEAR, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        RevertButton = initChildButton(pageToolbarHBox,ICON_REVERT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        SelectLayoutButton = initChildButton(pageToolbarHBox,ICON_LAYOUT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        SelectFontButton = initChildButton(pageToolbarHBox,ICON_FONT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        EnterStudentInfoButton = initChildButton(pageToolbarHBox,ICON_STUDENT, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
//pageToolbarPane.getChildren().addAll(AddImageButton,AddVideoButton,AddTextButton,AddSlideShowButton,RemoveComponentButton,ClearComponentButton,RevertButton);
        AddFooterButton = initChildButton(pageToolbarHBox,ICON_FOOTER, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        SelectBannerImageButton = initChildButton(pageToolbarHBox,ICON_BANNER, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        ColorChooserButton = initChildButton(pageToolbarHBox,ICON_COLOR, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        webTransitionButton = initChildButton(pageToolbarHBox,ICON_TRANSITION, TOOLTIP_NEW_EPORTFOLIO,CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON, false);
        
        transitionstate =0;
        
        pageComponentsPane = new ScrollPane();
        pageComponentVBox = new VBox();
        pageComponentsPane.setContent(pageComponentVBox);
/**            
            Text ntitle = new Text("test");
            Rectangle frame = new Rectangle();
            frame.setWidth(500);
            frame.setHeight(60);
            frame.setFill(Color.RED);
            StackPane stack = new StackPane();
            stack.getChildren().addAll(frame,ntitle);
            pageComponentVBox.getChildren().add(stack);
            * */
        //pageEditorPane.getChildren().add(epfgTitleHBox);
        //pageEditorPane.getChildren().add(pageTitleHBox);
        pageEditorPane.getChildren().add(titleHBox);
        pageEditorPane.getChildren().add(pageToolbarPane);
        pageEditorPane.getChildren().add(pageComponentsPane);
        
        workspace.getChildren().add(pageEditorPane); 
     
        
        
        
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
            PageEditView listview = new PageEditView(this,page);
            
            PageListVBox.getChildren().add(listview);
        }
    }
   // public void reloadPageEditorVBox(ePortfolioModel ePortfolioToLoad){
     //   PageEditorVbox.getChildren().clear();
       // ePortfolio = ePortfolioToLoad;
        
    //}
    public void reloadPageEditorPane(ePortfolioModel ePortfolioToLoad) {
        ePortfolio = ePortfolioToLoad;
        ePortfolioTitleField.setText(ePortfolio.getTitle());
        pageTitleField.setText(ePortfolio.getSelectedPage().getTitle());       
        Page selected = ePortfolio.getSelectedPage();
        ArrayList<String> list = selected.getComponents();
        for (String component : list) {
            
       }
        
        
    }
    
    public ePortfolioModel getEPortfolio() {
        return ePortfolio;
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
    private void ColorSelector() {
        TextInputDialog dialog = new TextInputDialog("blue");
        dialog.setTitle("Enter Color Code (RGB, HEX, default Color name");
        dialog.setHeaderText("Put Color Code");
        dialog.setContentText("Please enter Color Code:");

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            
    }
    }    
    private void EnterStudent() {
        TextInputDialog dialog = new TextInputDialog("Anonymous");
        dialog.setTitle("Enter Student Info");
        dialog.setHeaderText("Put your Name");
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
        Stage imagechooser = new Stage();
        imagechooser.setTitle("Choose Banner Image");
        Label label = new Label("Add Banner Image File");
        HBox labelHb = new HBox();
        labelHb.getChildren().add(label);
        

        Text ImportStatus = new Text();
        
        
        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JPG Files", "*.jpg"),
                    new ExtensionFilter("PNG Files", "*.png"),
                    new ExtensionFilter("GIF Files", "*.gif"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {

                    ImportStatus.setText("File selected: " + selectedFile.getName());
            }
            else {
            ImportStatus.setText("File selection cancelled.");
            }            
        });
        HBox Confirm = new HBox();
        Button OK = new Button("OK");
        Button cancel = new Button("Cancel");
        Confirm.getChildren().addAll(OK,cancel);
        OK.setOnAction(e -> {
            imagechooser.close();
        });
        cancel.setOnAction(e -> {
            imagechooser.close();
        });        
        VBox vbox = new VBox(30);
        vbox.getChildren().addAll(labelHb,chooseFileButton,ImportStatus,Confirm);
        Scene FileChooserScene = new Scene(vbox,800,800);
        imagechooser.setScene(FileChooserScene);
        imagechooser.show();   
    }

    private void AddSlideShow() {
        SlideShowMakerView view = new SlideShowMakerView();
        Stage SlideStage = new Stage();
        view.startUI(SlideStage,"Make SlideShow");
        
    }

    private void AddText() {
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
    private void InputHyperLink(){
        Stage TextStage = new Stage();
        HBox confirmation = new HBox(5);
        Button OK = new Button("OK");
        Button Cancel = new Button("Cancel");
        confirmation.getChildren().addAll(OK,Cancel);        
        VBox vbox = new VBox(5);
        Label label = new Label("Enter HyperLink");
        TextField input = new TextField();
        OK.setOnAction(e -> {
            TextStage.close();
        });
        Cancel.setOnAction(e -> {
            TextStage.close();
        });        
        vbox.getChildren().addAll(label,input,confirmation);
        Scene scene = new Scene(vbox,300,200);
        TextStage.setScene(scene);
        TextStage.show();
    }
    private void AddList() {
        Stage TextStage = new Stage();
        
        VBox vbox = new VBox(5);
        
        Label label = new Label("Make a List");

        
        
        TextField input1 = new TextField();
        
        TextField input2 = new TextField();
        
        TextField input3 = new TextField();
        
        TextField input4 = new TextField();
        
        TextField input5 = new TextField();
        HBox confirmation = new HBox(5);
        Button OK = new Button("OK");
        Button Cancel = new Button("Cancel");
        confirmation.getChildren().addAll(OK,Cancel);
        OK.setOnAction(e -> {
            TextStage.close();
        });
        Cancel.setOnAction(e -> {
            TextStage.close();
        });        
        vbox.getChildren().addAll(label,input1,input2,input3,input4,input5,confirmation);
        Scene TextScene = new Scene(vbox,800,800);
        TextStage.setScene(TextScene);
        TextStage.show();
    }
    private void AddVideo() {
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

    private void AddImage() {
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
        TextField captionField = new TextField();
        captionHbox.getChildren().addAll(captionlabel,captionField);
        
        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new ExtensionFilter("JPG Files", "*.jpg"),
                    new ExtensionFilter("PNG Files", "*.png"),
                    new ExtensionFilter("GIF Files", "*.gif"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {

                    ImportStatus.setText("File selected: " + selectedFile.getName());
            }
            else {
            ImportStatus.setText("File selection cancelled.");
            }            
        });
        HBox Confirm = new HBox();
        Button OK = new Button("OK");
        Button cancel = new Button("Cancel");
        Confirm.getChildren().addAll(OK,cancel);
        OK.setOnAction(e -> {
            imagechooser.close();
        });
        cancel.setOnAction(e -> {
            imagechooser.close();
        });        
        VBox vbox = new VBox(30);
        vbox.getChildren().addAll(labelHb,widthHBox,heightHbox,captionHbox,chooseFileButton,ImportStatus,Confirm);
        Scene FileChooserScene = new Scene(vbox,800,800);
        imagechooser.setScene(FileChooserScene);
        imagechooser.show();
    }
    
    private void webtransition() {
        if (transitionstate ==0) {
            workspace.getChildren().clear();
            workspace.getChildren().add(webTransitionButton);
            try {  
                WebView web = new WebView();     
               // String path =  "././sites/";
               // path += ePortfolioName + "/index.html";  
                //File loc = new File("././src/webdata/index.html");
                //web.getEngine().load(loc.toURI().toURL().toString());
                web.getEngine().load("https://www.google.com");
                web.getEngine().setJavaScriptEnabled(true);
                web.minWidth(1920);
                workspace.getChildren().add(web);
            } catch(Exception e) {  
                e.printStackTrace();  
            }          
            
            transitionstate =1;
        }
        else {
            
            workspace.getChildren().clear();
            initPageListToolbar();
            initPageEditPane();
            reloadPageListVBox(ePortfolio);
            reloadPageEditorPane(ePortfolio);
            initTotalEventHandlers();
            transitionstate = 0;
        }
    }
}
