public abstract class AbstractPerson{
    protected String name;
    protected String job;
    protected int weight;
    protected int height;

    public static AbstractPerson create(String name, String job, String weight, String height){
        if(!isPositiveInteger(weight) || !isPositiveInteger(height)){
            return new NullPerson(name);
        }
        return new RealPerson(name, job, weight, height);
    }

    public String getName(){
        return name;
    }
    public String getJob(){
        return job;
    }
    public int getWeight(){
        return weight;
    }
    public int getHeight(){
        return height;
    }
    
    public void setName(String name){
        this.name = name;
    }
    public void setJob(String job){
        this.job = job;
    }
    public void setWeight(int weight){
        this.weight = weight;
    }
    public void setHeight(int height){
        this.height = height;
    }

    public static boolean isPositiveInteger(String str){
        try{
            int num = Integer.parseInt(str);
            return num > 0;
        }catch(NumberFormatException e){
            return false;
        }
    }
}