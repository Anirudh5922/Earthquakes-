import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe:quakeData){
          double mag = qe.getMagnitude();
          if(mag>magMin)
             answer.add(qe);    
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe:quakeData){
          double dist = qe.getLocation().distanceTo(from);
          if(dist<distMax)
             answer.add(qe);    
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry>bigqe = filterByMagnitude(list,5.0);
        for(QuakeEntry qe : bigqe){
          System.out.println(qe);
        }
        System.out.println();
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list,1000000,city);
        for(QuakeEntry qe : close){
           double dist = qe.getLocation().distanceTo(city);
           String s= qe.getInfo();
           System.out.println(dist+" "+s);
        }
        System.out.println("Found "+close.size()+" quakes that match that criteria");
        System.out.println();
    
        System.out.println();
        // This location is Bridgeport, CA
        city =  new Location(38.17, -118.82);
        close = filterByDistanceFrom(list,1000000,city);
        for(QuakeEntry qe : close){
           double dist = qe.getLocation().distanceTo(city);
           String s= qe.getInfo();
           System.out.println(dist/1000+" "+s);
        }
        System.out.println("Found "+close.size()+" quakes that match that criteria");
        // TODO
        System.out.println("\n");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData,double minDepth,double maxDepth){
       ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
       for(QuakeEntry qe:quakeData){
          double depth = qe.getDepth();
          if(depth>maxDepth&&depth<minDepth)
             answer.add(qe);    
        }
        return answer;
    }
    
    public void quakesOfDepth(){
       EarthQuakeParser parser = new EarthQuakeParser();
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);
       System.out.println("read data for "+list.size()+" quakes");
       ArrayList<QuakeEntry> RangeDep=filterByDepth(list,-10000.0,-12000.0);
       System.out.println("Find quakes with depth between -10000.0 and -5000");
       for(QuakeEntry qe : RangeDep ){
           System.out.println(qe);
        }
       System.out.println("Found "+RangeDep.size()+" quakes that match that criteria");
        System.out.println();
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData,String where,String phrase){
      ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
       for(QuakeEntry qe:quakeData){
          String title = qe.getInfo();
          String t;
          if(where.equals("start")){
             if(title.length()>=phrase.length())
               t=title.substring(0,phrase.length());
             else
                continue;
            }
            
          else if(where.equals("end")){
             if(title.length()>=phrase.length())
               t=title.substring(title.length()-phrase.length(),title.length());
             else
                continue;
            }
          
          else {
              t=title;
            }
          
          int idx = t.indexOf(phrase);
          if(idx!=-1)
             answer.add(qe);
        }
        return answer;
    
    }
    
    public void quakesByPhrase(){
       EarthQuakeParser parser = new EarthQuakeParser();
       String source = "data/nov20quakedata.atom";
       ArrayList<QuakeEntry> list  = parser.read(source);
       System.out.println("read data for "+list.size()+" quakes");
       ArrayList<QuakeEntry> Phquake =filterByPhrase(list,"any","Can");
       System.out.println("Find quakes with given phrase");
       for(QuakeEntry qe : Phquake ){
           System.out.println(qe);
        }
       System.out.println("Found "+Phquake.size()+" quakes that match California at end");
       System.out.println();
    }
}

