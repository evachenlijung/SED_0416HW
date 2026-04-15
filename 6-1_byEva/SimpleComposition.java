import java.util.*;
import java.io.*;

public class SimpleComposition implements LinebreakingStrategy{
    public String breakline(List<Component> components){
        String s = "";
        for(Component c : components){
            s += String.format("[%d]%s\n", c.getNaturalSize(), c.getContent());
        }
        return s;
    };
}