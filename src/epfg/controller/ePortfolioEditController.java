/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import epfg.model.ePortfolioModel;
import epfg.view.ePortfolioGeneratorView;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;
import properties_manager.PropertiesManager;


/**
 *
 * @author seunchoi
 */
public class ePortfolioEditController {
    ePortfolioGeneratorView ui;
    
    public ePortfolioEditController(ePortfolioGeneratorView initUI){
        ui = initUI;
    }
    
    public void processAddPageRequest() {
        ePortfolioModel ePortfolio = ui.getEPortfolio();
	PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        TextInputDialog dialog = new TextInputDialog("New PAge");
        dialog.setTitle("Page Title Dialog");
        dialog.setHeaderText("Enter new Page Title");
        dialog.setContentText("New Page Title : ");
        
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            ePortfolio.addPage(result.get());
        }
        else {
            
        }        
        
    }
    
    public void processRemovePageRequst() {
        ePortfolioModel ePortfolio = ui.getEPortfolio();
    }
   
}
