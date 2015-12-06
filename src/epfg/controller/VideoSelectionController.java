/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import static epfg.StartupConstants.PATH_VIDEOS;
import epfg.error.ErrorHandler;
import epfg.model.Page;
import epfg.view.PageListEditView;
import epfg.view.ePortfolioGeneratorView;
import java.io.File;
import javafx.stage.FileChooser;
import properties_manager.PropertiesManager;

/**
 *
 * @author choiseu
 */
public class VideoSelectionController {
    public VideoSelectionController() { }
    
    public void processSelectVideo(Page pageToEdit, PageListEditView view, ePortfolioGeneratorView ui) {
        FileChooser videoFileChooser = new FileChooser();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        videoFileChooser.setInitialDirectory(new File(PATH_VIDEOS));
        
        FileChooser.ExtensionFilter mp4Filter = new FileChooser.ExtensionFilter("MP4 files (*.mp4)", "*,MP4");
        File file = videoFileChooser.showOpenDialog(null);
        if (file !=null) {
            String path = file.getPath().substring(0,file.getPath().indexOf(file.getName()));
            String fileName = file.getName();
            
            
        }
        else {
            ErrorHandler eH = ui.getErrorHandler();
            
        }
    }
}
