
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author Anirudh Maheshwari
 * @version 1.0
 */
public class MinMagFilter implements Filter
{
    private double magMin; 
    
    public MinMagFilter(double min) { 
        magMin = min;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 
    
    public String getName(){
      return "MinMag";
    }

}
