import java.util.*;
import java.io.*;

public class TextComposition implements LinebreakingStrategy{
    public String breakline(List<Component> components){
        String s = "";
        boolean first = true;
        for(Component c : components){
            if(!first) s += " ";
            else first = false;
            s += String.format("[%d]%s", c.getNaturalSize(), c.getContent());
            if(c.getContent().equals("<ParagraphEnd>")){
                s += "\n";
                first = true;
            }
        }
        return s;
    };
}