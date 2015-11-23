/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;
import epfg.LanguagePropertyType;
import static epfg.LanguagePropertyType.CANCEL_SELECTION;
import static epfg.LanguagePropertyType.CONGRATURATION;
import static epfg.LanguagePropertyType.CREATED_MESSAGE;
import static epfg.LanguagePropertyType.ERROR_OCCURED;
import static epfg.LanguagePropertyType.LOAD_FAILED;
import static epfg.LanguagePropertyType.NO_SELECTION;
import static epfg.LanguagePropertyType.SAVE_PROMPT;
import static epfg.LanguagePropertyType.SLIDESHOW_DIALOG;
import static epfg.LanguagePropertyType.TITLE_MODIFIER_HEADER;
import static epfg.LanguagePropertyType.TITLE_MODIFIER_WINDOW;
import static epfg.LanguagePropertyType.YES_SELECTION;
import epfg.model.ePortfolioModel;
import epfg.error.ErrorHandler;
import epfg.file.ePortfolioFileManager;
//import static epfg.file.ePortfolioFileManager.JSON_EXT;
//import static epfg.file.ePortfolioFileManager.SLASH;
import epfg.view.ePortfolioGeneratorView;
import static java.nio.file.StandardCopyOption.*;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import static epfg.StartupConstants.STYLE_SHEET_UI;

/**
 *
 * @author seunchoi
 */
public class FileController {
    
    private boolean saved;
    private boolean stayOpened;
    private boolean ePortfolioViewerPossible;
    
    private ePortfolioGeneratorView ui;
    
    private ePortfolioFileManager ePortfolioIO;
    
    public FileController(ePortfolioGeneratorView initUI, ePortfolioFileManager initEPortfolioIO) {
        saved = true;
        ui = initUI;
        ePortfolioIO = initEPortfolioIO;
    }
    public void markAsEdited() {
        saved = false;
        ui.updateToolbarControls(saved);
    } 
    
    public void handleNewEPortfolioRequest() {
        ePortfolioModel ePortfolio = ui.getEPortfolio();

        
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        TextInputDialog dialog = new TextInputDialog("New ePortfolio");
        dialog.setTitle("ePortfolio Title Dialog");
        dialog.setHeaderText("Enter new ePortfolio Title");
        dialog.setContentText("New ePortfolio Title : ");
        
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            ePortfolio.setTitle(result.get());
        }
        else {
            
        }
        ui.initPageListToolbar();
        ui.initPageListEventHandler();        

    }
    public void handleLoadEPortfolioRequest() {
        
    }
    public void handleSaveEPortfolioRequest() {
        
    }
    public void handleSaveAsEPortfolioRequest() {
        
    }
    public void handleExportEPortfolioRequest() {
        
    }
    public void handleExitRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToExit = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE
                promptToSave();
                continueToExit = !stayOpened;
            }

            // IF THE USER REALLY WANTS TO EXIT THE APP
            if (continueToExit) {
                // EXIT THE APPLICATION
                System.exit(0);
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            // @todo
            eH.processError(LanguagePropertyType.ERROR_OCCURED, "2", "Error");
        }
        
    }

    private boolean promptToSave() throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
/**        PropertiesManager props = PropertiesManager.getPropertiesManager();
 
        Alert prompt = new Alert(AlertType.CONFIRMATION);
        prompt.setTitle(props.getProperty(SAVE_PROMPT));
        prompt.setHeaderText(props.getProperty(SAVE_PROMPT));
        prompt.setContentText(null);
        ButtonType YesButton = new ButtonType(props.getProperty(YES_SELECTION));
        ButtonType NoButton = new ButtonType(props.getProperty(NO_SELECTION));
        ButtonType CancelButton = new ButtonType(props.getProperty(CANCEL_SELECTION),ButtonData.CANCEL_CLOSE);
        prompt.getButtonTypes().clear();
        prompt.getButtonTypes().addAll(YesButton,NoButton,CancelButton);
        Optional<ButtonType> result = prompt.showAndWait();
        boolean saveWork;
        if (result.get() == YesButton){
            saveWork = true;
            stayOpened = false;
        } else if (result.get() == NoButton) {
            saveWork = false;
            stayOpened = false;
        } else {
            saveWork = false;
            stayOpened = true;
        }
        
        // IF THE USER SAID YES, THEN SAVE BEFORE MOVING ON
        if (saveWork) {
            SlideShowModel slideShow = ui.getSlideShow();
            slideShowIO.saveSlideShow(slideShow);
            saved = true;
        } // IF THE USER SAID CANCEL, THEN WE'LL TELL WHOEVER
        // CALLED THIS THAT THE USER IS NOT INTERESTED ANYMORE
        else if (!true) {
            return false;
        }

        // IF THE USER SAID NO, WE JUST GO ON WITHOUT SAVING
        // BUT FOR BOTH YES AND NO WE DO WHATEVER THE USER
        // HAD IN MIND IN THE FIRST PLACE **/
        return true; 
    }    
}
