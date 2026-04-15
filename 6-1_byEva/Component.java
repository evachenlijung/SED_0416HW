import java.io.*;
import java.util.*;

public class Component{
    private int ID;
    private int naturalSize;
    private int shrinkability;
    private int stretchability;
    private String content;

    public Component(int ID, int naturalSize, int shrinkability, int stretchability, String content){
        this.ID = ID;
        this.naturalSize = naturalSize;
        this.shrinkability = shrinkability;
        this.stretchability = stretchability;
        this.content = content;
    }

    String changeSize(int newSize){
        if(newSize < shrinkability || newSize > stretchability){
            return "component " + ID + " failed to change size\n";
        }else{
            setNaturalSize(newSize);
            return "component " + ID + " size changed to " + newSize + "\n";
        }
    }

    // getters
    public int getID(){
        return ID;
    }

    public int getNaturalSize(){
        return naturalSize;
    }

    public int getShrinkability(){
        return shrinkability;
    }

    public int getStretchability(){
        return stretchability;
    }

    public String getContent(){
        return content;
    }

    // setters
    public void setNaturalSize(int size){
        this.naturalSize = size;
    }
}