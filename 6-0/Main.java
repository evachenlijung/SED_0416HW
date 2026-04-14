import java.util.*;
import java.io.*;
import java.math.*;

public class Main{
    private static List<AbstractPerson> persons = new ArrayList<>();
    public static void main(String[] args){
        if(args.length < 1){
            System.out.println("Usage: java Main <input_file>");
            return;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(args[0]))){
            String line;
            StringBuilder sb = new StringBuilder();
            AbstractPerson p;
            while((line = reader.readLine()) != null){
                // parse line logic
                String[] parts = line.split("\\s");
                if(parts[0].equals("Person")){
                    AbstractPerson person = AbstractPerson.create(parts[1], parts[2], parts[3], parts[4]);
                    persons.add(person);
                }else if(parts[0].equals("Job")){
                    p = getPersonByName(parts[1]);
                    sb.append(p.getJob());
                    sb.append("\n"); 
                }else if(parts[0].equals("WeightAverage")){
                    int sum = 0;
                    for(int i=1; i<parts.length; i++){
                        sum += getPersonByName(parts[i]).getWeight();
                    }
                    sb.append(Math.round(sum/(parts.length-1)));
                    sb.append("\n"); 
                }else if(parts[0].equals("WeightSum")){
                    int sum = 0;
                    for(int i=1; i<parts.length; i++){
                        sum += getPersonByName(parts[i]).getWeight();
                    }
                    sb.append(sum);
                    sb.append("\n");
                }else if(parts[0].equals("HeightSum")){
                    int sum = 0;
                    for(int i=1; i<parts.length; i++){
                        sum += getPersonByName(parts[i]).getHeight();
                    }
                    sb.append(sum);
                    sb.append("\n"); 
                }else if(parts[0].equals("HeightAverage")){
                    int sum = 0;
                    for(int i=1; i<parts.length; i++){
                        sum += getPersonByName(parts[i]).getHeight();
                    }
                    sb.append(Math.round(sum/(parts.length-1)));
                    sb.append("\n"); 
                }
            }
            String outputString = sb.toString();
            BufferedWriter writer = new BufferedWriter(new FileWriter(args[0].split("\\.")[0] + ".out"));
            writer.write(outputString);
            writer.close();
            System.out.print(outputString);
            reader.close();
        }catch(IOException e){
            System.out.println("Error reading file.");
        }
    }

    public static AbstractPerson getPersonByName(String name){
        for(AbstractPerson p : persons){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }
}