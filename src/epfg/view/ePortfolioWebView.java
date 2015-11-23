/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package epfg.view;
import java.io.File;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import static epfg.StartupConstants.PATH_EPORTFOLIO;
import static epfg.file.ePortfolioFileManager.JSON_EXT;
import static epfg.file.ePortfolioFileManager.SLASH;

 
/**
 *
 * @author Sean Seunghyuk choi
 */
public class ePortfolioWebView extends Application{
    private String ePortfolioName;
    private ePortfolioGeneratorView ui;
    private Scene scene;
    @Override

    public void start(Stage stage)
    {
        stage.setTitle(ePortfolioName);
        try {  
            WebView web = new WebView();     
           // String path =  "././sites/";
           // path += ePortfolioName + "/index.html";  
            File loc = new File("././src/webdata/index.html");
            web.getEngine().load(loc.toURI().toURL().toString());
            web.getEngine().setJavaScriptEnabled(true);
            scene = new Scene(web);   
            stage.setScene(scene);   
            stage.show();   
        } catch(Exception e) {  
            e.printStackTrace();  
        }          


    }
    public ePortfolioWebView(ePortfolioGeneratorView initUi, String initEPortfolioName){
        ui = initUi;
        ePortfolioName = initEPortfolioName;
    }
}
