/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.controller;

import epfg.Contents.TextComponent;
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


    public void processAddSlideShowRequest() {
    }

    public void processAddTextRequest() {
        ParagraphInputController textController = new ParagraphInputController();
        textController.processUserInput(ui);
    }

    public void processAddVideoRequest() {
        VideoSelectionController videoController = new VideoSelectionController();
        videoController.processSelectVideo(ui);
    }

    public void processAddImageRequest() {
        ImageSelectionController imageController = new ImageSelectionController();
        imageController.processSelectImage(ui);        
    }

    public void processAddListRequest() {
        ListInputController listcon = new ListInputController();
        listcon.MakeList(ui);
    }
    
    public void processAddHeaderRequest() {
        HeaderInputController headercon = new HeaderInputController();
        headercon.processData(ui);
    }
    

    

    
}
