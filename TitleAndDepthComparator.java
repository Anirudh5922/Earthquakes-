import java.util.*;
/**
 * Write a description of TitleAndDepthComparator here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */
public class TitleAndDepthComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        if(qe1.getInfo().compareTo(qe2.getInfo())<0)
                return -1;
        if(qe1.getInfo().compareTo(qe2.getInfo())>0)
                return 1;
        
        if(qe1.getDepth()>qe2.getDepth()) 
            return 1;
        if(qe1.getDepth()<qe2.getDepth()) 
           return -1;
        
        return 0;   
    }
    
}
