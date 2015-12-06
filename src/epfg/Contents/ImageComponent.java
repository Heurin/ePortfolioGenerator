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
    String position;
    public ImageComponent(String inPath, String inName, String incaption, double customheight, double customwidth, String inPosition){
        imagePath = inPath;
        imageFileName = inName;
        caption = incaption;
        height = customheight;
        width = customwidth;
        position = inPosition;
    }
    public String getPath() {
        return imagePath;
    }
    public String getFileName() {
        return imageFileName;
    }
    public String getCaption() {
        return caption;
    }
    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    public String getPosition() {
        return position;
    }
    
    public void setHeight(double customheight) {
        height = customheight;
    }
    
    public void setWidth(double customwidth) {
        width = customwidth;
    }
    public void setCaption(String inCaption) {
        caption = inCaption;
    }
    public void setPosition(String inPosition){
        position = inPosition;
    }
}
