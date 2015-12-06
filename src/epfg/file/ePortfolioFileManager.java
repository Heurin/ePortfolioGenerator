/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.file;

import static epfg.StartupConstants.PATH_EPORTFOLIO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;
import epfg.model.Page;
import epfg.model.ePortfolioModel;
import java.math.BigDecimal;
import javafx.collections.ObservableList;

/**
 *
 * @author shcho
 */
public class ePortfolioFileManager {
    public static String JSON_EPORTFOLIO_TITLE = "title";
    public static String JSON_STUDENT_NAME = "name";
    
    
    
    public static String JSON_PAGE_TITLE = "title";
    public static String JSON_PAGES = "pages";
    
    public static String JSON_IMAGE_PATH = "image_path";
    public static String JSON_IMAGE_FILE_NAME = "image_file_name";
    
    
    public static String JSON_VIDEO_PATH = "video_path"; 
    public static String JSON_VIDEO_FILE_NAME = "video_file_name";
    
    public static String JSON_PARAGRAPH = "paragraph";
    public static String JSON_LIST = "list";
    
    public static String JSON_BANNER = "banner";
    public static String JSON_FOOTER = "footer";
    
    public static String JSON_EXT = ".json";
    public static String SLASH = "/";
    public static String JSON_SLIDES = "slides";
    public static String JSON_CAPTION = "caption";  
    
    
    public void saveEPortfolio(ePortfolioModel ePortfolioToSave) throws IOException{
        String ePortfolioTitle = "" + ePortfolioToSave.getTitle();
        String jsonFilePath = PATH_EPORTFOLIO + SLASH + ePortfolioTitle + JSON_EXT; 
             
        
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os); 
        
        JsonArray pagesJsonArray = makePortfolioJsonArray(ePortfolioToSave.getPages());
        
        JsonObject courseJsonObject = Json.createObjectBuilder()
                                    .add(JSON_EPORTFOLIO_TITLE, ePortfolioToSave.getTitle())
                                    .add(JSON_STUDENT_NAME, ePortfolioToSave.getStudent())
                                    .add(JSON_PAGES, pagesJsonArray)
                .build();
        
        jsonWriter.writeObject(courseJsonObject);
    }

    private JsonArray makePortfolioJsonArray(ObservableList<Page> pages) {
        JsonArrayBuilder jsb = Json.createArrayBuilder();
        for (Page page : pages) {
	    JsonObject jso = makePageJsonObject(page);
	    jsb.add(jso);
        }
        JsonArray jA = jsb.build();
        return jA;       
    }

    private JsonObject makePageJsonObject(Page page) {
        JsonObject jso = Json.createObjectBuilder()
		.add(JSON_PAGE_TITLE, page.getTitle())
		.build();
	return jso;
    }
    
    
    public void loadEPortfolio(ePortfolioModel ePortfolioToLoad, String jsonFilePath) throws IOException {
        JsonObject json = loadJSONFile(jsonFilePath);
        ePortfolioToLoad.reset();
        ePortfolioToLoad.setTitle(json.getString(JSON_EPORTFOLIO_TITLE));
        ePortfolioToLoad.setStudentName(json.getString(JSON_STUDENT_NAME));
        JsonArray jsonPagesArray = json.getJsonArray(JSON_PAGES);
        for(int i=0; i< jsonPagesArray.size(); i++) {
            JsonObject pagesJso = jsonPagesArray.getJsonObject(i);
            
        }
    }

    private JsonObject loadJSONFile(String jsonFilePath) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
