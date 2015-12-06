/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.Contents;

import javafx.scene.image.Image;

/**
 *
 * @author shcho
 */
public class ImageComponent{
    String imagePath;
    String imageFileName;
    double height;
    double width;
    String caption;
    public ImageComponent(String inPath, String inName, String incaption, double customheight, double customwidth){
        imagePath = inPath;
        imageFileName = inName;
        caption = incaption;
        height = customheight;
        width = customwidth;
    }
}
