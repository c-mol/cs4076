package org.openjfx.project4076proto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.concurrent.Task;

public class SendEarlyTask extends Task<String>
{

   String[] daysofweek={"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" , "Sunday"}; 
    int i=0;
   String schedule;
   String course;
SendEarlyTask(String c){
    course=c;
}
   @Override
   protected String call() throws Exception {
      updateMessage("    Processing... ");
      String result = sendtoserver();
      updateMessage("    Done.  ");
      return result;
   }

  synchronized public String sendtoserver()  throws FileNotFoundException, InterruptedException, IOException
   //public static void main(String[]Args) throws FileNotFoundException, InterruptedException
   {
   String[] array ={"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" , "Sunday"}; 
   sendEarly[] ts = new sendEarly[7];
   for(int i=0;i<7;i++){
   ts[i] = new sendEarly(array[i],this.course); 
   ts[i].start();
   }
   String days="";
   for(int i=0; i < 7; i++) {
    ts[i].join();
    
    }
   
    for(int i=0; i < 7; i++) {
     for(int j=0;j<9;j++){
   
    if(!(ts[i].ans[j+9]==null))
    remove(ts[i].ans[j+9]);
     }
    }
    for(int i=0; i < 7; i++) {
     for(int j=0;j<9;j++){
        if(!(ts[i].ans[j]==null))
         add(ts[i].ans[j]);
    
    }}
   return days;
   
   }

      
synchronized  private String remove(String takeout) throws IOException {
        PrintWriter clear=null;
      String li[]=takeout.split(",");
            String response="couldn't find this lecture";
            String writein=li[0]+","+li[1]+","+li[2]+","+li[3];// Lecture�CS4012�Tuesday�900�ERB001
            ArrayList<String> temp = new ArrayList<>();
            Scanner scedscan = new Scanner(new File("src/scedule.csv"));
            while(scedscan.hasNext()) {
                String i = scedscan.nextLine();
                
                if((i).equalsIgnoreCase(writein))
                {
                    response="lecture removed";
                }
                else {temp.add(i);}
            }
            clear = new PrintWriter("src/scedule.csv");
            for(String x:temp) {
                PrintWriter writer;
                
                    writer = new PrintWriter(new FileWriter("src/scedule.csv",true));
                
                writer.println(x);
                writer.close();
                
            }
            scedscan.close();
            return response;
        
 }
synchronized public String add(String writein) throws IOException{
        
     
     System.out.print(writein);
    
    PrintWriter w=new PrintWriter(new FileWriter("src/scedule.csv",true));
    
    w.print(writein);
    w.println();
    w.close();
    return "done";
     
    }

}