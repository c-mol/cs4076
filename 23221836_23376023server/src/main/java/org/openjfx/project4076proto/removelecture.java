/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.project4076proto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author donag
 */
public class removelecture extends Task<String>{
    private String[] splitMessage;

    public removelecture (String[] s) {
    splitMessage=s;
   }

   @Override
   protected String call() throws Exception {
      updateMessage("    Processing... ");
      String result = remove(splitMessage);
      updateMessage("    Done.  ");
      return result;
   }

synchronized  private String remove(String[] li) throws IOException {
        PrintWriter clear=null;
       
            String response="couldn't find this lecture";
            String writein=li[1]+","+li[2]+","+li[3]+","+li[4];// Lecture�CS4012�Tuesday�900�ERB001
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
}
