import java.util.*;
import java.io.*;
/**
 * Write a description of LargestQuakes here.
 * 
 * @author Anirudh Maheshwari
 * @version 1.0
 */
public class LargestQuakes {

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData,int howMany) {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        for(int i=0;i<howMany;i++){
          int maxIndex= indexOfLargest(copy);
          ret.add(copy.get(maxIndex));
          copy.remove(maxIndex);
        }
        return ret;
    }

    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
       // String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        int idx = indexOfLargest(list);
        System.out.println(idx+" :  "+list.get(idx));
       // Location jakarta  = new Location(-6.211,106.845);

       ArrayList<QuakeEntry> Bigmag = getLargest(list,50);
        for(int k=1; k < Bigmag.size(); k++){
            QuakeEntry entry = Bigmag.get(k);
            System.out.println(entry);
        }
        System.out.println("number found: "+Bigmag.size());
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> data){
      int maxIndex=0;
      for(int i=0;i<data.size();i++){
        double mag = data.get(i).getMagnitude();
        double maxmag=data.get(maxIndex).getMagnitude();
        if(maxmag<mag)
         maxIndex=i;
        }
      return maxIndex;
    }
    
}
