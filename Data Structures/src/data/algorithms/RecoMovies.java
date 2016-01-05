import  java.util.*;  
import java.lang.Math;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
public class RecoMovies { 

   public static HashMap<Integer,String> movies = new HashMap<>();
   public static HashMap<Integer,HashMap<Integer,Double>> ratings = new HashMap<>();
   public static HashMap<Integer,Double> ratingsInner = new HashMap<Integer,Double>();
    
   public static HashMap<String,HashMap<String,Double>> dataMap ;
   public static ArrayList<Double> datanb1 ;
   public static ArrayList<Double> rmseArray = new ArrayList<>();
   public static void main(String[] args) throws IOException{ 
     
   
   
   BufferedReader br = new BufferedReader(new FileReader("movies.txt"));

    String line;
    while ((line = br.readLine()) != null) {
      String[] chars = line.split("::");
      Integer x = Integer.valueOf(chars[0]);
      movies.put(x,chars[1]);
       
    }
    BufferedReader brR = new BufferedReader(new FileReader("my_train.txt"));
     String lineR;
    int temp = 1;
    while ((lineR = brR.readLine()) != null) {
      String[] charsR = lineR.split("::");
      Integer user = Integer.valueOf(charsR[0]);
      Integer movie = Integer.valueOf(charsR[1]);
      Double rating = Double.valueOf(charsR[2]);
           if(temp != user){
           ratingsInner = new HashMap<>();
         }
           ratingsInner.put(movie,rating);
           
         if(!ratings.containsKey(user)){
           
                ratings.put(user,ratingsInner);
          
                temp = user;  
            } 
         
      
    }
    

    //System.out.println(ratings);
    
    
   
     
    //HashMap<String,Double> rat1 = outerMap.get("Angelica");
    //HashMap<String,Double> rat2 = outerMap.get("Bill");
    //for(int i=1;i<6041;i++ ){

      //recommend(i);
    //}

     System.out.println("Rmse value is: " + calculateAverage(rmseArray));

     HashMap<String,Double> output = recommend(4002);
    //System.out.println(calculateAverage(rmseArray));
    System.out.println(output);

    File file = new File("d:/newfile1.txt");

    try (FileOutputStream fop = new FileOutputStream(file)) {
 
      // if file doesn't exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }
 
      // get the content in bytes
      //byte[] contentInBytes = content.getBytes();
      int count = 0;
      for (Map.Entry<String,Double> o : output.entrySet()){
         byte[] contentInBytes = o.getKey().getBytes();
         byte[] colon = " : ".getBytes();
         byte[] contentInBytes1 = Double.toString(Math.ceil(o.getValue())).getBytes();
         String newline = System.getProperty("line.separator");
         byte[] nl = newline.getBytes();
        
          if (count < 10){
            count++;
            fop.write(contentInBytes);
            fop.write(colon);
            fop.write(contentInBytes1);
            fop.write(nl);
          //fop.wrie(" : ");
          //fop.write(o.getValue());
                  }
    }
 
 
      //fop.write(contentInBytes);
      fop.flush();
      fop.close();
 
      System.out.println("Done");
 
    } catch (IOException e) {
      e.printStackTrace();
    }
    
   }
   static double pearson(HashMap<Integer,Double> rating1, HashMap<Integer,Double> rating2) {

       float sum_xy = 0;
      float sum_x = 0;
      float sum_y = 0;
      float sum_x2 = 0;
      float sum_y2=0;
      int n =0 ;
      Set set1 = rating1.entrySet();
      Iterator i1 = set1.iterator();
      Set set2 = rating2.entrySet();
      Iterator i2 = set2.iterator();
     
       for(Map.Entry<Integer,Double> h1 : rating1.entrySet()){
         for(Map.Entry<Integer,Double> h2 : rating2.entrySet()){

              if ((h1.getKey()).equals(h2.getKey())){
               
               n++;
               double x = h1.getValue();
               double  y = h2.getValue();
               sum_xy += x * y;
               sum_x += x;
               sum_y += y;
               sum_x2 += Math.pow(x,2);
               sum_y2 += Math.pow(y,2);

              }

         }
          
       }
      
    
     
      if (n == 0){
        return 0;
      } 
      

      double denominator = (Math.sqrt(sum_x2 - Math.pow(sum_x,2)/n)* Math.sqrt(sum_y2 - Math.pow(sum_y,2)/n));
     
      if (denominator == 0){

        return 0;

      } else {
        return ((sum_xy - (sum_x * sum_y) / n) / denominator);
      }
    }
    static HashMap<Integer,Double> computeNearestNeighbour(int user){
       ArrayList<Integer> nb = new ArrayList<>();
        ArrayList<Double> nb1 = new ArrayList<>();

       HashMap<Integer,Double> nbDist = new HashMap<>();
       for(Map.Entry<Integer,HashMap<Integer,Double>> om : ratings.entrySet()){
            if (om.getKey() != user){
                
                HashMap<Integer,Double> rat1 = ratings.get(user);
                HashMap<Integer,Double> rat2 = ratings.get(om.getKey());

                 double distance = pearson(rat1,rat2);
                  //double distance = calculateCosineSim(rat1,rat2);
                //double distance = calculateEuclidean(rat1,rat2);
                nbDist.put(om.getKey(),new Double(distance));
                //nb.add(om.getKey());
                //nb1.add(distance);
            }
       }
       /*for(Map.Entry<Double,String> nbDist1 : nbDist.entrySet()){
          nb.add(nbDist1.getValue());
          nb1.add(nbDist1.getKey());
       }*/

       //datanb1 = nb1;
       HashMap<Integer,Double> nbDist1 = sortByValue(nbDist);
       return nbDist1;

    }

    public static HashMap<Integer,Double> sortByValueAsc(HashMap<Integer,Double> unsortMap) {   
          List list = new LinkedList(unsortMap.entrySet());
 
       Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
       public int compare(Map.Entry<Integer,Double> o1, Map.Entry<Integer,Double> o2) {
              return (o1.getValue()).compareTo(o2.getValue());
             }
           });
 
        HashMap<Integer,Double> sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
             Map.Entry<Integer,Double> entry = (Map.Entry<Integer,Double>) it.next();
              sortedMap.put(entry.getKey(), entry.getValue());
           }
          return sortedMap;
       }


    public static HashMap<Integer,Double> sortByValue(HashMap<Integer,Double> unsortMap) {   
          List list = new LinkedList(unsortMap.entrySet());
 
       Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
       public int compare(Map.Entry<Integer,Double> o1, Map.Entry<Integer,Double> o2) {
              return (o2.getValue()).compareTo(o1.getValue());
             }
           });
 
        HashMap<Integer,Double> sortedMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
             Map.Entry<Integer,Double> entry = (Map.Entry<Integer,Double>) it.next();
              sortedMap.put(entry.getKey(), entry.getValue());
           }
          return sortedMap;
       }

       public static HashMap<String,Double> sortByValueOut(HashMap<String,Double> unsortMap) {   
          List list = new LinkedList(unsortMap.entrySet());
 
       Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
       public int compare(Map.Entry<String,Double> o1, Map.Entry<String,Double> o2) {
              return (o2.getValue()).compareTo(o1.getValue());
             }
           });
 
        HashMap<String,Double> sortedMap = new LinkedHashMap(); 
        for (Iterator it = list.iterator(); it.hasNext();) {
             Map.Entry<String,Double> entry = (Map.Entry<String,Double>) it.next();
              sortedMap.put(entry.getKey(), entry.getValue());
           }
          return sortedMap;
       }

    static HashMap<String,Double> recommend(int user){

          HashMap<Integer,Double> recommendations = new HashMap<>();
          HashMap<Integer,Double> nearest;
          ArrayList<Integer> nearestNb = new ArrayList<>();
          ArrayList<Double> nearestDist = new ArrayList<>();
          nearest = computeNearestNeighbour(user);
          //System.out.println(nearest);
          for(Map.Entry<Integer,Double> temp : nearest.entrySet()){
                nearestNb.add(temp.getKey());
                nearestDist.add(temp.getValue());

          }
         
          //System.out.println(nearestNb);
          //System.out.println(nearestDist);
          HashMap<Integer,Double> userRatings = ratings.get(user);
          double totalDist = 0;
          int k=3;
          for(int i=0; i<k;i++){
            totalDist += nearestDist.get(i);
          }

          for(int i =0; i<k;i++){
            double weight = nearestDist.get(i)/totalDist;

            if (totalDist == 0){
               weight = 0;
            }
            //System.out.println(weight);
            int name = nearestNb.get(i);
            HashMap<Integer,Double> nbRatings = ratings.get(name);
              
            for(Map.Entry<Integer,Double> nbR : nbRatings.entrySet()){
              
               //if(!userRatings.containsKey(nbR.getKey())){
                   if(!recommendations.containsKey(nbR.getKey())){
                     recommendations.put(nbR.getKey(),nbR.getValue()*weight);
                    
                   }
                   else{
                     recommendations.put(nbR.getKey(), recommendations.get(nbR.getKey()) + (nbR.getValue()*weight));

                   }
               //} 
            }
          }

           HashMap<Integer,Double> reco = sortByValue(recommendations);
           //reco1.put(outp.getKey());System.out.println(reco);
           HashMap<String,Double> reco1 = new HashMap<>();
          
           for(Map.Entry<Integer,Double> outp : reco.entrySet()){
               
                 
                 reco1.put(movies.get(outp.getKey()),outp.getValue());
                 
               }


           //System.out.println(reco);
              //System.out.println(reco);
            
            double rmseAvg = calculateRMSE(userRatings,reco);
           
            rmseArray.add(rmseAvg);
           //System.out.println("RMSE is " + calculateRMSE(userRatings,reco));
           return sortByValueOut(reco1);


    }


    public static double calculateRMSE(HashMap<Integer,Double> ratings, HashMap<Integer,Double> predicted){
    
    double rms =0.0;
    ArrayList<Double> squareerror= new ArrayList<Double>();
    
    for(Map.Entry<Integer, Double> inputmap : ratings.entrySet()){
      int inputkey=inputmap.getKey();
      Double output= predicted.get(inputkey);

      if(output!=null){
      output = Math.ceil(output);
      double input=ratings.get(inputkey);
      double diff=input-output;
      diff=diff*diff;
      squareerror.add(diff);
      }
      else{
        continue;
      }
    }
    rms=calculateAverage(squareerror);
    rms=Math.sqrt(rms);
    
    return rms;
  }
  
  public static double calculateAverage(ArrayList<Double> input){
    
    int size = input.size();
    double avg=0.0;
    for(int j=0;j<size;j++){
      avg+=input.get(j);
    }
    if(size!=0){
    avg=avg/size;
    }
    else{
      avg=0.0;
    }
    return avg;
  }

  public static double calculateEuclidean(HashMap<Integer,Double> rating1,HashMap<Integer,Double> rating2){
  
  double distance=0.0;
  for(Map.Entry<Integer, Double> inputmap : rating1.entrySet()){
    int inputkey= inputmap.getKey();
    Double outputvalue= rating2.get(inputkey);
    if(outputvalue!=null){
      double inputvalue=rating1.get(inputkey);
      double diff= inputvalue-outputvalue;
      diff=diff*diff;
      distance+=diff;
    }
    else{
      continue;
    }
  }
  return Math.sqrt(distance);
  
}

public static double calculateCosineSim(HashMap<Integer,Double> rating1,HashMap<Integer,Double> rating2){
  ArrayList<Double> inputvalues = new ArrayList<Double>();
  ArrayList<Double> outputvalues = new ArrayList<Double>();
  double distance=0.0;
  double dotproduct=0.0;
  double inputmagnitude=0.0;
  double outputmagnitude=0.0;
  for(Map.Entry<Integer, Double> inputmap : rating1.entrySet()){
    int inputkey= inputmap.getKey();
    Double outputvalue= rating2.get(inputkey);

    if(outputvalue!=null){
      double inputvalue=rating1.get(inputkey);
      dotproduct=dotproduct+(outputvalue*inputvalue);
      inputvalues.add(inputvalue);
      outputvalues.add(outputvalue);
      inputmagnitude=calculateMagnitude(inputvalues);
      outputmagnitude=calculateMagnitude(outputvalues);
      distance = dotproduct/(inputmagnitude * outputmagnitude);
    }
    else{
      continue;
    }
  }
  
  return distance;
  
}

public static double calculateMagnitude(ArrayList<Double> list){
  double magnitude=0.0;
  for(Double d: list){
    magnitude=magnitude+(d*d);
  }
  return Math.sqrt(magnitude);
}
} 

