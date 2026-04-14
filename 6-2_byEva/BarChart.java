import java.util.*;

public class BarChart implements Chart{
    @Override
    public String depict(Map<String, Integer> data){
        String s = "";
        for(Map.Entry<String, Integer> entry : data.entrySet()){
            String bar = "";
            for(int i=0; i<entry.getValue(); i++) bar += "=";
            s += bar + " " + entry.getKey() + "\n";
        }
        return s;
    }
}