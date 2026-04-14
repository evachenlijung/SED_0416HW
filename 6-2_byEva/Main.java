import java.io.*;
import java.util.*;

public class Main{
    private static Map<String, Integer> data = new LinkedHashMap<>();
    private static List<Chart> charts = new ArrayList<>();
    public static void main(String[] args){
        if(args.length < 1){
            System.out.println("Usage: java Main <input_file>");
            return;
        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = reader.readLine()) != null){
                String[] parts = line.split("\\s+");
                if(parts[0].equals("data")){
                    data.put(parts[1], Integer.parseInt(parts[2]));
                }else if(parts[0].equals("addChart")){
                    addChart(parts[1]);
                }else if(parts[0].equals("change")){
                    data.put(parts[2], Integer.parseInt(parts[3]));
                    sb.append(parts[1] + " change " + parts[2] + " " + parts[3] + ".\n");
                    for(Chart chart : charts){
                        sb.append(chart.depict(data));
                    }
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[0].split("\\.")[0] + ".out"));
            String output = sb.toString();
            writer.write(output);
            writer.close();
            System.out.print(output);
            reader.close();
        }catch(Exception e){
            System.out.println("Error processing file.");
        }
    }

    public static void addChart(String chartType){
        switch(chartType){
            case "Spreadsheet":
                charts.add(new Spreadsheet());
                break;
            case "BarChart":
                charts.add(new BarChart());
                break;
            case "PieChart":
                charts.add(new PieChart());
                break;
            default:
                System.out.println("Unknown chart type: " + chartType);
        }
    }
}