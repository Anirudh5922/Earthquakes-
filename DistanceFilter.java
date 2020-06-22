
/**
 * Write a description of DistanceFilter here.
 * 
 * @author Anirudh Maheshwari
 * @version 1.0
 */
public class DistanceFilter implements Filter
{
    private double maxDis; 
    private Location loc;
    public DistanceFilter(double max,Location source) { 
        maxDis = max;
        loc = source;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getLocation().distanceTo(loc) < maxDis; 
    } 
    
    public String getName(){
      return "Distance";
    }
}
