import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

class Component{
    private int id;
    private int naturalSize;
    private int shrinkability;
    private int stretchability;
    private String content;

    public Component(int id, int ns, int shrink, int stretch, String content){
        this.id = id;
        this.naturalSize = ns;
        this.shrinkability = shrink;
        this.stretchability = stretch;
        this.content = content;
    }

    public int getId(){
        return id;
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

    public String getContent(){ return content; }
    public void changeSize(int newSize){
        if (newSize < shrinkability || newSize > stretchability){
            System.out.println("component " + id + " failed to change size");
        }else{
            this.naturalSize = newSize;
            System.out.println("component " + id + " size changed to " + naturalSize);
        }
    }
}

class TextElement extends Component{
    public TextElement(int id, int ns, int shrink, int stretch, String content){
        super(id, ns, shrink, stretch, content);
    }
}

class GraphicalElement extends Component{
    public GraphicalElement(int id, int ns, int shrink, int stretch, String content){
        super(id, ns, shrink, stretch, content);
    }
}

class Composition{
    private List<Component> components = new ArrayList<>();

    public Composition(){}

    public void arrange(LineBreakingStrategy strategy) {
        if (strategy != null) {
            strategy.breakLine(this.components);
        }
    }

    public void addComponent(Component c){
        components.add(c);
    }

    public List<Component> getComponents(){
        return components;
    }

    public void changeSize(int id, int newSize){
        for (Component component: components){
            if (component.getId() == id){
                component.changeSize(newSize);
                break;
            }
        }
    }

}

interface LineBreakingStrategy{
    void breakLine(List<Component> cs);
}

class SimpleComposition implements LineBreakingStrategy{
    @Override
    public void breakLine(List<Component> cs){
        for (Component component: cs){
            System.out.println("[" + component.getNaturalSize() + "]" + component.getContent());
        }
    }
}

class TextComposition implements LineBreakingStrategy{
    @Override
    public void breakLine(List<Component> cs){
        boolean first = true;
        for (Component component: cs){
            String s = "";
            if(!first) s += " ";
            else first = false;
            s += "[" + component.getNaturalSize() + "]" + component.getContent();
            System.out.print(s);
            if (component.getContent().equals("<ParagraphEnd>")){
                System.out.println();
                first = true;
            }
        }
    }
}

class ArrayComposition implements LineBreakingStrategy{
    @Override
    public void breakLine(List<Component> cs){
        int i=0;
        for (; i<cs.size(); i++){
            String s = "";
            if(i % 3 != 0) s += " ";
            s += "[" + cs.get(i).getNaturalSize() + "]" + cs.get(i).getContent();
            System.out.print(s);
            if ((i+1) % 3 == 0){
                System.out.println();
            }
        }

        if (i % 3 != 0){
            System.out.println();
        }
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        String inputFile = args[0];
        File fakeDataFile = new File(inputFile);
        BufferedReader reader = new BufferedReader(new FileReader(fakeDataFile));

        Composition composition = new Composition();
        String line;
        while ((line=reader.readLine()) != null){
            String [] parts = line.split("\\s+");
            if (parts[0].equals("Text")){
                int id = Integer.parseInt(parts[1]);
                int ns = Integer.parseInt(parts[2]);
                int shrink = Integer.parseInt(parts[3]);
                int stretch = Integer.parseInt(parts[4]);
                String content = parts[5];
                TextElement textElement = new TextElement(id, ns, shrink, stretch, content);
                composition.addComponent(textElement);
            }else if(parts[0].equals("GraphicalElement")){
                int id = Integer.parseInt(parts[1]);
                int ns = Integer.parseInt(parts[2]);
                int shrink = Integer.parseInt(parts[3]);
                int stretch = Integer.parseInt(parts[4]);
                String content = parts[5];
                GraphicalElement graphicalElement = new GraphicalElement(id, ns, shrink, stretch, content);
                composition.addComponent(graphicalElement);
            }else if(parts[0].equals("ChangeSize")){
                int id = Integer.parseInt(parts[1]);
                int newSize = Integer.parseInt(parts[2]);
                composition.changeSize(id, newSize);
            }else if (parts[0].equals("Require")) {
                LineBreakingStrategy strategy = null;

                if (parts[1].equals("SimpleComposition")) {
                    strategy = new SimpleComposition();
                } else if (parts[1].equals("TexComposition")) {
                    strategy = new TextComposition();
                } else if (parts[1].equals("ArrayComposition")) {
                    strategy = new ArrayComposition();
                }

                if (strategy != null) {
                    composition.arrange(strategy);
                }
            }
        }
    }
}