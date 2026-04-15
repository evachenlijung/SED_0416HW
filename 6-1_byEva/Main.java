import java.io.*;
import java.util.*;

public class Main{
    static List<Component> components = new ArrayList<>();
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
                if(parts[0].equals("Text")){
                    components.add(new Text(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5]));
                }else if(parts[0].equals("GraphicalElement")){
                    components.add(new GraphicalElement(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), parts[5]));
                }else if(parts[0].equals("ChangeSize")){
                    Component c = getComponentByID(Integer.parseInt(parts[1]));
                    if(c != null){
                        sb.append(c.changeSize(Integer.parseInt(parts[2])));
                    }
                }else if(parts[0].equals("Require")){
                    switch(parts[1]){
                        case "SimpleComposition":
                            sb.append(new SimpleComposition().breakline(components));
                            break;
                        case "TexComposition":
                            sb.append(new TextComposition().breakline(components));
                            break;
                        case "ArrayComposition":
                            sb.append(new ArrayComposition().breakline(components));
                            break;
                    }
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("%s.out", args[0].split("\\.")[0])));
            String output = sb.toString();
            writer.write(output);
            writer.close();
            System.out.print(output);
            reader.close(); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Component getComponentByID(int ID){
        for(Component c : components){
            if(c.getID() == ID) return c;
        }
        return null;
    }
}