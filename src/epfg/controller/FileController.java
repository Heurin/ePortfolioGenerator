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
    
}
