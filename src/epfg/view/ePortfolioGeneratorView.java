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
import static epfg.LanguagePropertyType.TOOLTIP_NEW_PAGE;
import static epfg.LanguagePropertyType.TOOLTIP_REMOVE_PAGE;
import static epfg.LanguagePropertyType.TOOLTIP_SAVE_EPORTFOLIO;
import static epfg.LanguagePropertyType.TOOLTIP_VIEW_EPORTFOLIO;
import static epfg.StartupConstants.CSS_CLASS_HORIZONTAL_TOOLBAR_BUTTON;
import static epfg.StartupConstants.CSS_CLASS_PAGE_EDIT_VIEW;
import static epfg.StartupConstants.CSS_CLASS_PAGE_EDIT_VBOX;
import static epfg.StartupConstants.CSS_CLASS_VERTICAL_TOOLBAR_BUTTON;
import static epfg.StartupConstants.CSS_CLASS_PAGE_SELECTION;
import static epfg.StartupConstants.ICON_APPLICATION;
import static epfg.StartupConstants.ICON_ADD_PAGE;
import static epfg.StartupConstants.ICON_EXIT;
import static epfg.StartupConstants.ICON_LOAD_EPORTFOLIO;
import static epfg.StartupConstants.ICON_MOVE_UP;
import static epfg.StartupConstants.ICON_MOVE_DOWN;
import static epfg.StartupConstants.ICON_NEW_PAGE;
import static epfg.StartupConstants.ICON_SAVE_EPORTFOLIO;
import static epfg.StartupConstants.ICON_VIEW_EPORTFOLIO;
import static epfg.StartupConstants.ICON_REMOVE_PAGE;
import static epfg.StartupConstants.ICON_TITLECHANGER;
import static epfg.StartupConstants.PATH_ICONS;
import static epfg.StartupConstants.STYLE_SHEET_UI;
import epfg.controller.FileController;
import epfg.controller.ePortfolioEditController;
import epfg.model.Page;
import epfg.model.ePortfolioModel;
import epfg.error.ErrorHandler;
import epfg.file.ePortfolioFileManager;

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
    Button saveAsEPortfoliobutton;
    Button exportButton;
    Button exitButton;
    
    //WORKSPACE
    HBox workspace;
    
    //PageListEditor
    VBox PageListVBox;
    FlowPane PageListEditPane;
    ScrollPane PageListPane; 
    Button AddPageButton;
    Button RemovePageButton;
    
    //ePortfolio Information and Page editor
    VBox pageEditorPane;
    HBox ePortfolioInroPane;
    FlowPane pageToolbarPane;
    ScrollPane pageComponentsPane;
    
    
    ePortfolioModel ePortfolio;
    ePortfolioFileManager fileManager;
    private ErrorHandler errorHandler;
    private ePortfolioEditController editController;
    
    
    public ePortfolioGeneratorView(ePortfolioFileManager initfileManager) {
        fileManager = initfileManager;
        
        ePortfolio = new ePortfolioModel(this);
        
        errorHandler = new ErrorHandler(this);
        
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void startUI(Stage initPrimaryStage, String appTitle) {
        initWorkspace();
        initEventHandlers();
        primaryStage = initPrimaryStage;
        initWindow(appTitle);
    }
    private void initWorkspace() {
        
    }
    private void initEventHandlers(){
        
    }
    private void initFileToolbar(){
	fileToolbarPane = new FlowPane();
        fileToolbarPane.setId("filetoolbar");
        fileToolbarPane.getStylesheets().add(STYLE_SHEET_UI);
        // HERE ARE OUR FILE TOOLBAR BUTTONS, NOTE THAT SOME WILL
	// START AS ENABLED (false), WHILE OTHERS DISABLED (true)
	PropertiesManager props = PropertiesManager.getPropertiesManager();
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
    
}
