
/**
 * Write a description of PhraseFilter here.
 * 
 * @author Anirudh Maheshwari
 * @version 1.0
 */
public class PhraseFilter implements Filter
 {
    private String req; 
    private String phrase;
    public PhraseFilter(String request,String phr) { 
        req = request;
        phrase = phr;
    } 
    
    public String getName(){
      return "Phrase";
    }
    
    public boolean satisfies(QuakeEntry qe) { 
        String title = qe.getInfo();
          String t;
          if(req.equals("start")){
             if(title.length()>=phrase.length())
               t=title.substring(0,phrase.length());
             else
                return false;
            }
            
          else if(req.equals("end")){
             if(title.length()>=phrase.length())
               t=title.substring(title.length()-phrase.length(),title.length());
             else
                return false;
            }
          
          else {
              t=title;
            }
          
        int idx = t.indexOf(phrase);
        if(idx!=-1)
          return true;
        else
          return false;
    } 

}
