public class RealPerson extends AbstractPerson{
    public RealPerson(String name, String job, String weight, String height){
        this.name = name;
        this.job = job;
        try{
            this.weight = Integer.parseInt(weight);
            this.height = Integer.parseInt(height);
        }catch(NumberFormatException e){
               
        }
    }
}