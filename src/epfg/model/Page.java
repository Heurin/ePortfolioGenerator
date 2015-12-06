/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epfg.model;

import epfg.Contents.ImageComponent;
import epfg.Contents.SlideShowComponent;
import epfg.Contents.TextComponent;
import epfg.Contents.VideoComponent;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author seunchoi
 */
public class Page {
    String pageTitle;
    boolean hasFooter;
    List<String> components;
    List<Integer> componentsIndex = new ArrayList();
    List<ImageComponent> images = new ArrayList();
    List<SlideShowComponent> slides = new ArrayList();
    List<TextComponent> texts = new ArrayList();
    List<VideoComponent> videos = new ArrayList();
    String font, footer,layout,backgroundColor;
    
    //public Page(String newPageTitle)
    
    public Page(String initTitle, List<String> initcomponents) {
        pageTitle = initTitle;
        components = initcomponents;
        hasFooter = false;
        for(int i=0 ; i<initcomponents.size(); i++) {
            
           // AddComponent(i,initcomponents.get(i));
        }

    }
    
    public Page(String initTitle) {
        pageTitle = initTitle;
        components = new ArrayList<> ();
        hasFooter = false;
        layout = "1";
        backgroundColor = "default";
        font = "default";
    }
    
    public String getTitle() {
        return pageTitle;
    }
    public List<String> getComponents() {
        return components;
    }
    
    
    //public Slide(String initPageTitle, String[] init)

    public void setTitle(String ntitle) {
        pageTitle = ntitle;
    }
  
    /** #deleted because this function was not enough for my needs
    public void AddComponent(String nComponent) {
        if(!nComponent.equals("footer")) {
            components.add(nComponent);
            
        }
        
        if(nComponent.equals("footer")){
            hasFooter = true;
        }
        
        
        if(nComponent.equals("image")) {
            
        } 
        else if(nComponent.equals("video")){
            
        }
        else if(nComponent.equals("slideshow")){
            
        }
        else if(nComponent.equals("header")){
            
        }
        else if(nComponent.equals("text")){
            
        }
        else if(nComponent.equals("list")){
            
        }
        else if(nComponent.equals("banner")){
            
        }
   }
  **/  
    public void AddImage(String imagePath, String imageFileName, String caption, double height, double width) {
        ImageComponent imgComp = new ImageComponent(imagePath, imageFileName, caption,  height, width);
        components.add("image");
        int index = images.size();
        componentsIndex.add(index);
        images.add(imgComp);
    }
    
    public void AddVideo() {
        VideoComponent vidComp = new VideoComponent();
        components.add("video");
        int index = videos.size();
        componentsIndex.add(index);
        videos.add(vidComp);
    }
    
    public void AddParagraph(String inputP) {
        TextComponent paraComp = new TextComponent("paragraph",inputP);
        components.add("paragraph");
        int index = texts.size();
        componentsIndex.add(index);
        texts.add(paraComp);        
    }
    public void AddSlideShow() {
        //SlideShowComponent slidecomp = new SlideShowComponent();
        
    }
    

    public void setLayout(String customLayout) {
        layout = customLayout;
    }

    public void setFont(String inputFont) {
            font = inputFont;
    }

    public void setBackgroundColor(String inputColor) {
        backgroundColor = inputColor;
    }

    public void setFooter(String inputfooter) {
        footer = inputfooter;
    }

    public String getFooter() {
        return footer;
    }
    public String getLayout() {
        return layout;
    }
    public String getFont() {
        return font;
    }
}
