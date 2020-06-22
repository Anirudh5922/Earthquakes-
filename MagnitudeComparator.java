
/**
 * Write a description of class MagnitudeComparator here.
 * 
 * @author Anirudh Maheshwari 
 * @version 1.0
 */

import java.util.*;

public class MagnitudeComparator implements Comparator<QuakeEntry> {
    public int compare(QuakeEntry qe1, QuakeEntry qe2) {
        return Double.compare(qe1.getMagnitude(), qe2.getMagnitude());
    }
    
}
