import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

        Filter f = new MinMagFilter(4.0); 
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        for (QuakeEntry qe: m7) { 
            System.out.println(qe);
        } 
        System.out.println("Found "+m7.size()+" that satisfies the given criteria\n");
        
        f = new MagnitudeFilter(3.5,4.5); 
        ArrayList<QuakeEntry>magRange  = filter(list, f); 
        /*for (QuakeEntry qe: magRange) { 
            System.out.println(qe);
        } 
        System.out.println("Found "+magRange.size()+" that satisfies the given criteria\n");
        */
         f = new DepthFilter(-55000.0,-20000.0); 
        ArrayList<QuakeEntry> depRange  = filter(magRange, f); 
        for (QuakeEntry qe: depRange) { 
            System.out.println(qe);
        } 
        System.out.println("Found "+depRange.size()+" that satisfies the given criteria\n");
        
        Location loc = new Location(39.7392, -104.9903);
        f = new DistanceFilter(1000000,loc); 
        ArrayList<QuakeEntry> close  = filter(list, f); 
        /*for (QuakeEntry qe: close) { 
            System.out.println(qe);
        } 
        System.out.println("Found "+close.size()+" that satisfies the given criteria\n");
        */
        f = new PhraseFilter("end","a"); 
        ArrayList<QuakeEntry> Phreq  = filter(close, f); 
        for (QuakeEntry qe: Phreq) { 
            System.out.println(qe);
        } 
        System.out.println("Found "+Phreq.size()+" that satisfies the given criteria\n");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    
    public void testMatchAllFilter(){
       EarthQuakeParser parser = new EarthQuakeParser(); 
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);         
       System.out.println("read data for "+list.size()+" quakes");
       
       MatchAllFilter maf = new MatchAllFilter();
       Filter f = new MagnitudeFilter(1.0,4.0);
       maf.addFilter(f);
       f = new DepthFilter(-180000.0,-30000.0);
       maf.addFilter(f);
       f = new PhraseFilter("any","o");
       maf.addFilter(f);
       ArrayList<QuakeEntry> output = filter(list,maf); 
       System.out.println(maf.getName());      
       for (QuakeEntry qe: output) { 
            System.out.println(qe);
        } 
       System.out.println("Found "+output.size()+" that satisfies the given criteria\n");
    }
    
    public void testMatchAllFilter2(){
      EarthQuakeParser parser = new EarthQuakeParser(); 
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);         
       System.out.println("read data for "+list.size()+" quakes");
       
       MatchAllFilter maf = new MatchAllFilter();
       Filter f = new MagnitudeFilter(0.0,5.0);
       maf.addFilter(f);
       Location loc = new Location(55.7308, 9.1153);
       f = new DistanceFilter(3000000,loc);
       maf.addFilter(f);
       f = new PhraseFilter("any","e");
       maf.addFilter(f);
       ArrayList<QuakeEntry> output = filter(list,maf); 
       
       for (QuakeEntry qe: output) { 
            System.out.println(qe);
        } 
       System.out.println("Found "+output.size()+" that satisfies the given criteria\n");
    
    }
}
