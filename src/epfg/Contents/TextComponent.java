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
    
    String type;
    String text;
    
    public TextComponent(String newtype,String newText){
        type = newtype;
        text = newText;
    }
    public String getType(){
        return type;
    }
    public String getText() {
        return text;
    }
    public void changeText(String newText){
        text = newText;
    }
}
