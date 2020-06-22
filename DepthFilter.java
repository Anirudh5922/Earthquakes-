
/**
 * Write a description of DepthFilter here.
 * 
 * @author Anirudh Maheshwari
 * @version 1.0
 */
public class DepthFilter implements Filter {
   private double min;
   private double max;
   public DepthFilter(double mindep,double maxdep){
      min = mindep;
      max = maxdep;
    } 
    
   public  boolean satisfies(QuakeEntry qe){
     return (qe.getDepth()>=min&&qe.getDepth()<=max);
    } 
   
   public String getName(){
     return "Depth";
    } 
}
