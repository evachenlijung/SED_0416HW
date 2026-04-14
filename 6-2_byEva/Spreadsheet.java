import java.util.*;

public class Spreadsheet implements Chart{
    @Override
    public String depict(Map<String, Integer> data){
        String s = "";
        for(Map.Entry<String, Integer> entry : data.entrySet()){
            s += entry.getKey() + " " + entry.getValue() + "\n";
        }
        return s;
    }
}