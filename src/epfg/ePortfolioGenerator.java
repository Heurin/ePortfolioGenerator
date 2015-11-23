package epfg;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import xml_utilities.InvalidXMLFileFormatException;
import properties_manager.PropertiesManager;
import static epfg.LanguagePropertyType.TITLE_WINDOW;
import static epfg.StartupConstants.PATH_DATA;
import static epfg.StartupConstants.PROPERTIES_SCHEMA_FILE_NAME;
import static epfg.StartupConstants.UI_PROPERTIES_FILE_NAME;
import epfg.error.ErrorHandler;
import epfg.file.ePortfolioFileManager;
import epfg.view.ePortfolioGeneratorView;

import java.awt.Dialog;
import javafx.scene.control.ChoiceDialog;
import static epfg.StartupConstants.STYLE_SHEET_UI;
/**
 * ePortfolioGenerator is a program for making custom image ePortfolios. It will allow
 * the user to name their ePortfolio, select images to use, select captions for
 * the images, and the order of appearance for slides.
 *
 * @author Seunghyuk Sean Choi
 */
public class ePortfolioGenerator extends Application {
    // THIS WILL PERFORM ePortfolio READING AND WRITING
    ePortfolioFileManager fileManager = new ePortfolioFileManager();

    // THIS HAS THE FULL USER INTERFACE AND ONCE IN EVENT
    // HANDLING MODE, BASICALLY IT BECOMES THE FOCAL
    // POINT, RUNNING THE UI AND EVERYTHING ELSE
    ePortfolioGeneratorView ui = new ePortfolioGeneratorView(fileManager);
    @Override
    public void start(Stage primaryStage) throws Exception {
        // LOAD APP SETTINGS INTO THE GUI AND START IT UP
/**
        List<String> choices = new ArrayList<>();
        choices.add("English(Default)");
        choices.add("Korean(한글)");
        
        String selectedLanguage = "";
        ChoiceDialog<String> dialog = new ChoiceDialog<>("English(Default)", choices);
        dialog.getDialogPane().setId("language_dialog");
        dialog.setTitle("Language Selection");
        dialog.setHeaderText("Select Language");
        dialog.setContentText("Chooce your language");
        dialog.getDialogPane().getStylesheets().add(STYLE_SHEET_UI);
        
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent())
        {
            String a = result.get();
            if (a.equals("English(Default)"))
                UI_PROPERTIES_FILE_NAME = "properties_EN.xml";
            else
            {
                UI_PROPERTIES_FILE_NAME = "properties_KO.xml";
            }
        }
        else
            System.exit(0);
            * */
        UI_PROPERTIES_FILE_NAME = "properties_EN.xml";
        boolean success = loadProperties();
        if (success) {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            String appTitle = props.getProperty(TITLE_WINDOW);

	    // NOW START THE UI IN EVENT HANDLING MODE
	    ui.startUI(primaryStage, appTitle);
	} // THERE WAS A PROBLEM LOADING THE PROPERTIES FILE
	else {
	    // LET THE ERROR HANDLER PROVIDE THE RESPONSE
	    ErrorHandler errorHandler = ui.getErrorHandler();
	    errorHandler.processError(LanguagePropertyType.ERROR_DATA_FILE_LOADING, "todo", "todo");
	    System.exit(0);
	}

    }
    
    /**
     * Loads this application's properties file, which has a number of settings
     * for initializing the user interface.
     * 
     * @return true if the properties file was loaded successfully, false otherwise.
     */
    public boolean loadProperties() {
        try {
            // LOAD THE SETTINGS FOR STARTING THE APP
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            props.addProperty(PropertiesManager.DATA_PATH_PROPERTY, PATH_DATA);
	    props.loadProperties(UI_PROPERTIES_FILE_NAME, PROPERTIES_SCHEMA_FILE_NAME);
            return true;
       } catch (InvalidXMLFileFormatException ixmlffe) {
            // SOMETHING WENT WRONG INITIALIZING THE XML FILE
            ErrorHandler eH = ui.getErrorHandler();
            eH.processError(LanguagePropertyType.ERROR_PROPERTIES_FILE_LOADING, "todo", "todo");
            return false;
        }        
    }

    /**
     * This is where the application starts execution. We'll load the
     * application properties and then use them to build our user interface and
     * start the window in event handling mode. Once in that mode, all code
     * execution will happen in response to user requests.
     *
     * @param args This application does not use any command line arguments.
     */
    public static void main(String[] args) {
	launch(args);
    }
}
