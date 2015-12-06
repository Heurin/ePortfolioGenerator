/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import epfg.model.Page;
import epfg.model.ePortfolioModel;
import epfg.view.ePortfolioGeneratorView;
import java.util.ArrayList;
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
        
        TextInputDialog dialog = new TextInputDialog("New Page");
        dialog.setTitle("Page Title Dialog");
        dialog.setHeaderText("Enter new Page Title");
        dialog.setContentText("New Page Title : ");
        
        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            ArrayList<String> a = new ArrayList<String>();
            Page n = new Page(result.get(), a);
            ePortfolio.addPage(n);
            ePortfolio.setSelectedPage(n);
        }
        else {
            
        }
        if (ePortfolio.getPages().size() == 1) {
            ui.initPageEditPane();
            ui.initPageComponentEventHandler();
        }
        ui.reloadPageListVBox(ePortfolio);
        ui.reloadPageEditorPane(ePortfolio);
        ui.updatePageListToolbarControls(ePortfolio.isPageSelected());
    }
    
    public void processRemovePageRequst() {
        ePortfolioModel ePortfolio = ui.getEPortfolio();
        ePortfolio.getPages().remove(ePortfolio.getSelectedPage());
        ui.reloadPageListVBox(ePortfolio);
        ui.reloadPageEditorPane(ePortfolio);
        ui.updatePageListToolbarControls(!ePortfolio.isPageSelected());
        if(ePortfolio.getPages().size() ==0) {
            ui.initPageListToolbar();
            ui.initPageListEventHandler();
        }
    }
   
}
