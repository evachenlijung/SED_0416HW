import java.util.*;

public class PieChart implements Chart{
    @Override
    public String depict(Map<String, Integer> data){
        int total = 0;
        for(int value : data.values()) total += value;
        String s = "";
        for(Map.Entry<String, Integer> entry : data.entrySet()){
            double percentage = (double)entry.getValue() / total * 100;
             s += entry.getKey() + " " + String.format("%.1f%%", percentage) + "\n";
        }
        return s;
    }
}