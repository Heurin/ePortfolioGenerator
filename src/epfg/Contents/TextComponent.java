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
public class TextComponent{
    

    String font;
    String text;
    
    public TextComponent(String newfont, String newText){

        text = newText;
        font = newfont;
    }
    public String getText() {
        return text;
    }
    public String getFont() {
        return font;
    }
    public void changeText(String newFont, String newText){
        text = newText;
        font = newFont;
    }
}
