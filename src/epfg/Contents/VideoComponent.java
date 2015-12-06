/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.Contents;

/**
 *
 * @author shcho
 */
public class VideoComponent{
    String videoPath;
    String videoFileName;
    String caption;
    double height;
    double width;
    public VideoComponent(String inPath, String inName, String inCaption, double customheight, double customwidth){
        videoPath = inPath;
        videoFileName = inName;
        caption = inCaption;
        height = customheight;
        width = customwidth;
    }
    public String getPath() {
        return videoPath;
    }
    public String getFileName() {
        return videoFileName;
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
    
    public void setCaption(String nCaption) {
        caption = nCaption;
    }
    
    
    public void setHeight(double customheight) {
        height = customheight;
    }
    
    public void setWidth(double customwidth) {
        width = customwidth;
    }    
}
