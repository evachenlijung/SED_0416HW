import java.util.*;
import java.io.*;

public class ArrayComposition implements LinebreakingStrategy{
    public String breakline(List<Component> components){
        String s = "";
        boolean first = true;
        int count = 0;
        for(Component c : components){
            if(!first) s += " ";
            else first = false;
            s += String.format("[%d]%s", c.getNaturalSize(), c.getContent());
            count++;
            if(count % 3 == 0){
                s += "\n";
                first = true;
            }
        }
        if(count % 3 != 0) s += "\n";
        return s;
    };
}