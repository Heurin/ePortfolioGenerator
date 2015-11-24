package ssm.controller;

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
import ssm.LanguagePropertyType;
import static ssm.LanguagePropertyType.CANCEL_SELECTION;
import static ssm.LanguagePropertyType.CONGRATURATION;
import static ssm.LanguagePropertyType.CREATED_MESSAGE;
import static ssm.LanguagePropertyType.ERROR_OCCURED;
import static ssm.LanguagePropertyType.LOAD_FAILED;
import static ssm.LanguagePropertyType.NO_SELECTION;
import static ssm.LanguagePropertyType.SAVE_PROMPT;
import static ssm.LanguagePropertyType.SLIDESHOW_DIALOG;
import static ssm.LanguagePropertyType.TITLE_MODIFIER_HEADER;
import static ssm.LanguagePropertyType.TITLE_MODIFIER_WINDOW;
import static ssm.LanguagePropertyType.YES_SELECTION;
import static ssm.StartupConstants.PATH_SLIDE_SHOWS;
import ssm.model.SlideShowModel;
import ssm.error.ErrorHandler;
import ssm.file.SlideShowFileManager;
import static ssm.file.SlideShowFileManager.JSON_EXT;
import static ssm.file.SlideShowFileManager.SLASH;
import ssm.view.SlideShowMakerView;
import static java.nio.file.StandardCopyOption.*;
import javafx.stage.Stage;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import static ssm.StartupConstants.STYLE_SHEET_UI;
/**
 * This class serves as the controller for all file toolbar operations,
 * driving the loading and saving of slide shows, among other things.
 * 
 * @author McKilla Gorilla & Seunghyuk Sean Choi
 */
public class FileController {

    // WE WANT TO KEEP TRACK OF WHEN SOMETHING HAS NOT BEEN SAVED
    private boolean saved;
    private boolean stayOpened;
    private boolean slideshowPossible;

    // THE APP UI
    private SlideShowMakerView ui;
    
    // THIS GUY KNOWS HOW TO READ AND WRITE SLIDE SHOW DATA
    private SlideShowFileManager slideShowIO;

    /**
     * This default constructor starts the program without a slide show file being
     * edited.
     *
     * @param initSlideShowIO The object that will be reading and writing slide show
     * data.
     */
    public FileController(SlideShowMakerView initUI, SlideShowFileManager initSlideShowIO) {
        // NOTHING YET
        saved = true;
	ui = initUI;
        slideShowIO = initSlideShowIO;
    }
    
    public void markAsEdited() {
        saved = false;
        ui.updateToolbarControls(saved);
    }

    /**
     * This method starts the process of editing a new slide show. If a pose is
     * already being edited, it will prompt the user to save it first.
     */
    public void handleNewSlideShowRequest() {
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK
            boolean continueToMakeNew;
            if(!saved && !ui.getSlideShow().getSlides().isEmpty())
            {
                continueToMakeNew = promptToSave();
            }
            else
                continueToMakeNew = true;
            PropertiesManager props = PropertiesManager.getPropertiesManager();   

            
            // IF THE USER REALLY WANTS TO MAKE A NEW COURSE
            if (continueToMakeNew) {
                // RESET THE DATA, WHICH SHOULD TRIGGER A RESET OF THE UI
                SlideShowModel slideShow = ui.getSlideShow();
		slideShow.reset();
                if(slideShow.getSlides().isEmpty())
                {
                    TextInputDialog naming = new TextInputDialog(slideShow.getTitle());
                    naming.getDialogPane().setId("newslideprompt");
                    naming.getDialogPane().getStylesheets().add(STYLE_SHEET_UI);
                    naming.setTitle(props.getProperty(TITLE_MODIFIER_WINDOW));
                    naming.setHeaderText(props.getProperty(TITLE_MODIFIER_HEADER));
                    Optional<String> response = naming.showAndWait();
                    if(response.isPresent())
                    {
                        slideShow.changeSlideTitle(response.get());
                    }
                }
                saved = false;
               

                // REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
                // THE APPROPRIATE CONTROLS
                ui.updateToolbarControls(saved);
                ui.reloadSlideShowPane(slideShow);
                // TELL THE USER THE SLIDE SHOW HAS BEEN CREATED
                // @todo
                Alert notify = new Alert(AlertType.INFORMATION);
                notify.getDialogPane().setId("newslidecreated");
                notify.getDialogPane().getStylesheets().add(STYLE_SHEET_UI);
                notify.setTitle(props.getProperty(SLIDESHOW_DIALOG));
                notify.setHeaderText(props.getProperty(CONGRATURATION));
                notify.setContentText(props.getProperty(CREATED_MESSAGE));
                notify.showAndWait();
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            // @todo provide error message
            eH.processError(LOAD_FAILED, "Error", "Error");
        }
    }

    /**
     * This method lets the user open a slideshow saved to a file. It will also
     * make sure data for the current slideshow is not lost.
     */
    public void handleLoadSlideShowRequest() {
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        try {
            // WE MAY HAVE TO SAVE CURRENT WORK

            boolean continueToOpen = true;
            if (!saved) {
                // THE USER CAN OPT OUT HERE WITH A CANCEL
                continueToOpen = promptToSave();
            }

            // IF THE USER REALLY WANTS TO OPEN A POSE
            if (continueToOpen) {
                // GO AHEAD AND PROCEED MAKING A NEW POSE
                promptToOpen();
            }
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            //@todo provide error message
            eH.processError(LanguagePropertyType.ERROR_OCCURED, props.getProperty(LanguagePropertyType.LOAD_FAILED), props.getProperty(LanguagePropertyType.LOAD_FAILED));
        }
    }

    /**
     * This method will save the current slideshow to a file. Note that we already
     * know the name of the file, so we won't need to prompt the user.
     */
    public boolean handleSaveSlideShowRequest() {
        try {
	    // GET THE SLIDE SHOW TO SAVE
	    SlideShowModel slideShowToSave = ui.getSlideShow();
	    
            // SAVE IT TO A FILE
            slideShowIO.saveSlideShow(slideShowToSave);

            // MARK IT AS SAVED
            saved = true;

            // AND REFRESH THE GUI, WHICH WILL ENABLE AND DISABLE
            // THE APPROPRIATE CONTROLS
            ui.updateToolbarControls(saved);
	    return true;
        } catch (IOException ioe) {
            ErrorHandler eH = ui.getErrorHandler();
            // @todo
            eH.processError(LanguagePropertyType.ERROR_OCCURED, "1", "Error");
	    return false;
        }
    }

     /**
     * This method will exit the application, making sure the user doesn't lose
     * any data first.
     */
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
    
    public void handleSlideShowRequest() {
        try {
            if (!saved) {
                promptToSave();
            }
        } catch (Exception ex) {
            ErrorHandler eH = ui.getErrorHandler();
        }
        if (!ui.getSlideShow().getSlides().isEmpty()){
            File defaultdir = new File("././sites");
            String slidename = ui.getSlideShow().getTitle();
            if(!defaultdir.exists()){
                defaultdir.mkdir();
            }
            String slideloc = "././sites/"+slidename;
            File slidedir = new File(slideloc);
            if(!slidedir.exists())
                slidedir.mkdir();
            try {
                File htmlorigin = new File("././src/webdata/index.html");
                File htmldest = new File(slideloc+"/index.html");
                Files.copy(htmlorigin.toPath(),htmldest.toPath(),REPLACE_EXISTING);
            } catch (IOException e) {
                    System.out.println("error");
            }
            
            File cssdir = new File(slideloc + "/css");
            if(!cssdir.exists())
                cssdir.mkdir();
            File sourcecss = new File("././src/webdata/css/");
            try {
                for (File f : sourcecss.listFiles()){
                    String fileName = f.getName();           
                    Files.copy(f.toPath(), Paths.get(cssdir.toString() +'/' + fileName) ,REPLACE_EXISTING);
                }
            }catch (IOException e) {
            }
            File icondir = new File(slideloc + "/icons");
            if(!icondir.exists())
                icondir.mkdir();
            File sourceicon = new File("././src/webdata/icons/");
             try {
                for (File f : sourceicon.listFiles()){
                    String fileName = f.getName();           
                    Files.copy(f.toPath(), Paths.get(icondir.toString() +'/' + fileName) ,REPLACE_EXISTING);
                }
            }catch (IOException e) {
            }           
                        
            File jsdir = new File(slideloc +"/js");
            if(!jsdir.exists())
                jsdir.mkdir();
            try {
                File jsorigin = new File("././src/webdata/js/SlideShow.js");
                File jsdest = new File(slideloc+"/js/SlideShow.js");
                File jqueryorigin = new File("././src/webdata/js/jquery-2.1.4.js");
                File jquerydest = new File(slideloc + "/js/jquery-2.1.4.js");
                Files.copy(jsorigin.toPath(),jsdest.toPath(),REPLACE_EXISTING);
                Files.copy(jqueryorigin.toPath(),jquerydest.toPath());
            } catch (IOException e) {
                
            }
            File imgdir = new File(slideloc +"/img");
            if(!imgdir.exists())
                imgdir.mkdir();
            else if(imgdir.exists()){
                if(!imgdir.isDirectory()){
                    imgdir.delete();
                    imgdir.mkdir();
                }
                else {
                    for (File f : imgdir.listFiles()) {
                        f.delete();
                    }
                }
            }
            File jsondir = new File(slideloc +"/json");
            if(!jsondir.exists())
                jsondir.mkdir();
            else if(jsondir.exists()){
                if(!jsondir.isDirectory()){
                    jsondir.delete();
                    jsondir.mkdir();
                }
                else {
                    for (File f : jsondir.listFiles()) {
                        f.delete();
                    }
                }
            }
            String jsonFilePath = PATH_SLIDE_SHOWS + SLASH + slidename + JSON_EXT;
                try {
                    JsonObject json = loadJSONFile(jsonFilePath);
                    JsonArray jsonSlidesArray = json.getJsonArray("slides");
                    for (int i = 0; i < jsonSlidesArray.size(); i++) {
                        JsonObject slideJso = jsonSlidesArray.getJsonObject(i);
                        File imgorigin = new File(slideJso.getString("image_path") + SLASH +slideJso.getString("image_file_name"));
			File imgdest = new File(slideloc+"/img/"+slideJso.getString("image_file_name"));
                        Files.copy(imgorigin.toPath(),imgdest.toPath(),REPLACE_EXISTING);
                    }
                    File jsonorigin = new File(jsonFilePath);
                    File jsondest = new File(slideloc+ "/json/slidedata.json");
                    Files.copy(jsonorigin.toPath(),jsondest.toPath(),REPLACE_EXISTING);               
                } catch (IOException e) {
                    
                }
        }
       /** else{
                File defaultdir = new File("././sites");
                defaultdir.delete();
                PropertiesManager props = PropertiesManager.getPropertiesManager();
                Alert notify = new Alert(AlertType.INFORMATION);
                notify.setTitle(props.getProperty(SLIDESHOW_DIALOG));
                notify.setHeaderText(props.getProperty(CONGRATURATION));
                notify.setContentText(props.getProperty(CREATED_MESSAGE));
                notify.showAndWait();
        }
*/
    }
    
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
        InputStream is = new FileInputStream(jsonFilePath);
        JsonReader jsonReader = Json.createReader(is);
        JsonObject json = jsonReader.readObject();
        jsonReader.close();
        is.close();
        ui.updateToolbarControls(true);
        saved = true;
        return json;
    }        
    /**
     * This helper method verifies that the user really wants to save their
     * unsaved work, which they might not want to do. Note that it could be used
     * in multiple contexts before doing other actions, like creating a new
     * pose, or opening another pose, or exiting. Note that the user will be
     * presented with 3 options: YES, NO, and CANCEL. YES means the user wants
     * to save their work and continue the other action (we return true to
     * denote this), NO means don't save the work but continue with the other
     * action (true is returned), CANCEL means don't save the work and don't
     * continue with the other action (false is retuned).
     *
     * @return true if the user presses the YES option to save, true if the user
     * presses the NO option to not save, false if the user presses the CANCEL
     * option to not continue.
     */
    private boolean promptToSave() throws IOException {
        // PROMPT THE USER TO SAVE UNSAVED WORK
        PropertiesManager props = PropertiesManager.getPropertiesManager();
 
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
        // HAD IN MIND IN THE FIRST PLACE
        return true;
    }

    /**
     * This helper method asks the user for a file to open. The user-selected
     * file is then loaded and the GUI updated. Note that if the user cancels
     * the open process, nothing is done. If an error occurs loading the file, a
     * message is displayed, but nothing changes.
     */
    private void promptToOpen() {
        // AND NOW ASK THE USER FOR THE COURSE TO OPEN       
        FileChooser slideShowFileChooser = new FileChooser();
        slideShowFileChooser.setInitialDirectory(new File(PATH_SLIDE_SHOWS));
        File selectedFile = slideShowFileChooser.showOpenDialog(ui.getWindow());
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        // ONLY OPEN A NEW FILE IF THE USER SAYS OK
        if (selectedFile != null) {
            try {
		SlideShowModel slideShowToLoad = ui.getSlideShow();
                slideShowIO.loadSlideShow(slideShowToLoad, selectedFile.getAbsolutePath());
                ui.reloadSlideShowPane(slideShowToLoad);
                saved = true;
                ui.updateToolbarControls(saved);
            } catch (Exception e) {
                ErrorHandler eH = ui.getErrorHandler();
                // @todo
                eH.processError(LOAD_FAILED, props.getProperty(ERROR_OCCURED), props.getProperty(LOAD_FAILED));
            }
        }
    }

    /**
     * This mutator method marks the file as not saved, which means that when
     * the user wants to do a file-type operation, we should prompt the user to
     * save current work first. Note that this method should be called any time
     * the pose is changed in some way.
     */
    public void markFileAsNotSaved() {
        saved = false;
    }

    /**
     * Accessor method for checking to see if the current pose has been saved
     * since it was last editing. If the current file matches the pose data,
     * we'll return true, otherwise false.
     *
     * @return true if the current pose is saved to the file, false otherwise.
     */
    public boolean isSaved() {
        return saved;
    }
}

