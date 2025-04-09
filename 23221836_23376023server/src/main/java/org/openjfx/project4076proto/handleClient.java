/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.project4076proto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author donag
 */
public class handleClient extends Thread{
    private Socket socket;
    handleClient(Socket s){
    socket=s;
    }
    @Override
  public void run() {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // Continuously read messages from the client
            String message;
            while ((message = reader.readLine()) != null) {
                String[] splitMessage=message.split("£");
                if(splitMessage[0].equalsIgnoreCase("EarlyLect")){
                System.out.println("Scheduling early lecture...");
                SendEarlyTask task=new SendEarlyTask(splitMessage[1]);
                 task.setOnSucceeded((succeededEvent) -> {
                 String comfimdoness =task.getValue().toString();
                  writer.println(comfimdoness);
               });
                ExecutorService executorService
                  = Executors.newFixedThreadPool(1);
               executorService.execute(task);
               executorService.shutdown();
                }
                else if(splitMessage[0].equalsIgnoreCase("stop")){
                
                System.out.println("Received: " + message);
                message="TERMINATE";
                writer.println(message);
                }
                else if(splitMessage[0].equalsIgnoreCase("Add a Lecture")){
                System.out.println("adding lecture");
                addlecture task=new addlecture(splitMessage);
                //String comfimdoness=addlecture(splitMessage);
                 
               
                task.setOnSucceeded((succeededEvent) -> {
                 String comfimdoness =task.getValue().toString();
                  writer.println(comfimdoness);
               });
                ExecutorService executorService
                  = Executors.newFixedThreadPool(1);
               executorService.execute(task);
               executorService.shutdown();
                
                }
                else if(splitMessage[0].equalsIgnoreCase("Remove a lecture")){
                System.out.println("removing lecture");/*
                String comfimdoness= removelecture(splitMessage);
                writer.println(comfimdoness);*/
               
                removelecture task=new removelecture(splitMessage);
                //String comfimdoness=addlecture(splitMessage);
                 
               
                task.setOnSucceeded((succeededEvent) -> {
                 String comfimdoness =task.getValue().toString();
                  writer.println(comfimdoness);
               });
                ExecutorService executorService
                  = Executors.newFixedThreadPool(1);
               executorService.execute(task);
               executorService.shutdown();
                
                }
                else if(splitMessage[0].equalsIgnoreCase("Display Schedule")){
               
                
                Schedulecture task=new Schedulecture(splitMessage[1]);                 
               
                task.setOnSucceeded((succeededEvent) -> {
                 String comfimdoness =task.getValue().toString();
                  writer.println(comfimdoness);
               });
                ExecutorService executorService
                  = Executors.newFixedThreadPool(1);
               executorService.execute(task);
               executorService.shutdown();
                }
                else {
                    System.out.println("Received unrecognized request: " + splitMessage[0]);
                    String errorMessage = "Error: Unsupported action requested -> " + splitMessage[0];

                    writer.println(errorMessage);
                    writer.flush();
                    System.out.println("Sent error response to client.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Client connection closed.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
  

     
}
