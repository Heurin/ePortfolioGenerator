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

/**
 *
 * @author shcho
 */
public class ePortfolioFileManager {
    public static String JSON_EPORTFOLIO_TITLE = "title";
    public static String JSON_PAGE_TITLE = "title";
    public static String JSON_PAGES = "pages";
    public static String JSON_IMAGE_FILE_NAME = "image_file_name";
    public static String JSON_IMAGE_PATH = "image_path";
    public static String JSON_VIDEO_FILE_NAME = "video_file_name";
    public static String JSON_EXT = ".json";
    public static String SLASH = "/";
    public static String JSON_SLIDES = "slides";
    public static String JSON_CAPTION = "caption";  
    
    
    public void saveEPortfolio(ePortfolioModel ePortfolioToSave) throws IOException{
        String ePortfolioTitle = "" + ePortfolioToSave.getTitle();
        String jsonFilePath = PATH_EPORTFOLIO + SLASH + ePortfolioTitle + JSON_EXT; 
        
        OutputStream os = new FileOutputStream(jsonFilePath);
        JsonWriter jsonWriter = Json.createWriter(os); 
        
    }
}
