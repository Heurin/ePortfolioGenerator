/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import epfg.view.ePortfolioGeneratorView;

/**
 *
 * @author choiseu
 */
public class PageEditController {
    ePortfolioGeneratorView ui;
    
    public PageEditController(ePortfolioGeneratorView initUI) {
        ui = initUI;
    }

    public void processRevertChangeRequest() {
    }

    public void processClearComponentsRequest() {
        
    }

    public void processAddSlideShowRequest() {
    }

    public void processAddTextRequest() {
        ParagraphInputController textController = new ParagraphInputController();
        textController.processUserInput(ui);
    }

    public void processAddVideoRequest() {
    }

    public void processAddImageRequest() {
        ImageSelectionController imageController = new ImageSelectionController();
        imageController.processSelectImage(ui);        
    }

    public void processRemoveComponentRequest() {
        
    }
    
}
