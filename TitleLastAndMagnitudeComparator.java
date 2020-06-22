import java.util.*;
/**
 * Write a description of TitleLastAndMagnitudeComparator here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */
public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{
   public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        String[] words1 = qe1.getInfo().split("\\s");
        String word1 = words1[words1.length-1];
        
        String[] words2 = qe2.getInfo().split("\\s");
        String word2 = words2[words2.length-1];
        
        if(word1.compareTo(word2)>0)
                return 1;
        if(word1.compareTo(word2)<0)
                return -1;
        
        if(qe1.getMagnitude()>qe2.getMagnitude()) 
            return 1;
        if(qe1.getMagnitude()<qe2.getMagnitude()) 
           return -1;
        
        return 0;   
    }
}
