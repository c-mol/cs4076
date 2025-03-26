package org.openjfx._23376023client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/**
 * 
 */
public class Client extends Application {
    public boolean stopflagraised=false;
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    
    /**launches the stage.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     *
     * @param primaryStage
     * Creates all the nodes for the GUI
     *
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void start(Stage primaryStage)// other FileNotFoundException
            
    {   ArrayList<Button> btns=new ArrayList<>();
        ArrayList<TextField> txts=new ArrayList<>();
        ArrayList<Label> addremls=new ArrayList<>();
        ArrayList<TextField> addremTF=new ArrayList<>();
        
        String[] daysofweek={"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" , "Sunday"}; 
        TextField module=new TextField();
        Label modulelab=new Label("moduleCode: ");
        addremls.add(modulelab);
        addremTF.add(module);
        TextField moduler=new TextField();
        Label modulelabr=new Label("moduleCode: ");
        addremls.add(modulelabr);

        addremTF.add(moduler);

        
        
        
       
        GridPane home = new GridPane();
        GridPane addpane = new GridPane();
        GridPane removepane = new GridPane();
        GridPane schedulepane = new GridPane();
        ArrayList<Pane> panes=new ArrayList<>();
        panes.add(home);
        panes.add(addpane);
        panes.add(removepane);
        panes.add(schedulepane);

        //home
        home.setStyle("-fx-background-color: linear-gradient(to top,#eae6da ,#79c752);");
        //home.setStyle( "-fx-background-color: white;");
        
        addpane.setStyle("-fx-background-color: linear-gradient(to top,#eae6da ,#79c752);");
        removepane.setStyle("-fx-background-color: linear-gradient(to top,#eae6da ,#79c752);");
        schedulepane.setStyle("-fx-background-color: linear-gradient(to top,#eae6da ,#79c752);");
        
        
        
        Pane pane=new Pane(home);
        ChoiceBox action = new ChoiceBox();
        action.getItems().add("Add a Lecture");
        action.getItems().add("Remove a Lecture");
        action.getItems().add("Display Schedule");
        action.getItems().add("Others");
        
        //Tab home=new Tab();
        Button go = new Button("go");
        go.setOnAction((ActionEvent event) -> {
            String oppick=(String) action.getValue();
            if(oppick!=null){
                //pickdate hours mins room get values
                switch (oppick) {
                    case "Display Schedule":
                        //tabPane.getTabs().add(schedule);
                        pane.getChildren().clear();
                        pane.getChildren().add(schedulepane);
                        break;
                    case "Add a Lecture":
                        //tabPane.getTabs().add(Add);
                        pane.getChildren().clear();
                        pane.getChildren().add(addpane);
                        break;
                    case "Remove a Lecture":
                        //tabPane.getTabs().add(Remove);
                        pane.getChildren().clear();
                        pane.getChildren().add(removepane);
                        break;
                    case "Others":
                    {
                         System.out.println("Sending 'Others' request to server...");
                        String response = sendMessageToServer("Others");
                        System.out.println("Server response: " + response);
                        }
                        break;

                    default:
                        break;
                }
            }
    });
        btns.add(go);
        
        Button Homebuttona = new Button("home");
        Homebuttona.setOnAction((ActionEvent event) -> {
                        //tabPane.getTabs().add(schedule);
                        pane.getChildren().clear();
                        pane.getChildren().add(home);
                       
    });
        Button Homebuttonr = new Button("home");
        Homebuttonr.setOnAction((ActionEvent event) -> {
                        //tabPane.getTabs().add(schedule);
                        pane.getChildren().clear();
                        pane.getChildren().add(home);
                       
    });
         Button Homebuttons = new Button("home");
        Homebuttons.setOnAction((ActionEvent event) -> {
                        //tabPane.getTabs().add(schedule);
                        pane.getChildren().clear();
                        pane.getChildren().add(home);
                       
    });
       btns.add(Homebuttona);
       btns.add(Homebuttonr);
       btns.add(Homebuttons);


        
        Button stoph = new Button("stop");
        stoph.setOnAction(event -> {
                sendMessageToServer("stop");
                
                primaryStage.close();
            }
        );
        
        //btns.add(stoph);

        addpane.addRow(0,modulelab ,module);
        removepane.addRow(0,modulelabr ,moduler);

        home.addRow(2,action ,go);
        home.addRow(3, stoph);
        
        Label pickdaylabel=new Label("Day: ");
        addremls.add(pickdaylabel);

        ChoiceBox pickdate=new ChoiceBox();
        pickdate.setMinWidth(100);
        pickdate.setPrefWidth(200);
        pickdate.setMaxWidth(200);
        pickdate.setStyle(
          "-fx-background-color: white;"+   "-fx-background-insets: 1px;"
            + "-fx-background-radius:10px; -fx-text-fill: green;"
            + " -fx-border-radius: 10px; -fx-border-color: #388E3C;"
        );
        pickdate.getItems().addAll(Arrays.asList(daysofweek));//Adds days of the week to the pick date.
        //hours
        Label hourslabel=new Label("hour of lecture: ");
        addremls.add(hourslabel);
        Spinner<Integer> hours=new Spinner<>(900,1700,9,100);
        
        hours.setMinWidth(100);
        hours.setPrefWidth(200);
        hours.setMaxWidth(200);
        hours.getEditor().setStyle(
          "-fx-background-insets: 1px;"
            + "-fx-background-color: white;"
          + "-fx-background-radius:10px; -fx-text-fill: green; -fx-border-radius: 10px; -fx-border-color: #388E3C;"
        );
        
        
        Label roomlabel=new Label("room: ");
       addremls.add(roomlabel);

        //room number
        TextField room = new TextField();
       addremTF.add(room);
        
        
        Button stop = new Button("stop");
        stop.setOnAction(event -> {
                sendMessageToServer("stop");
                
                primaryStage.close();
            }
        );
        //btns.add(stop);

        
        Label x=new Label();
        Button Submit = new Button("Submit");
        Submit.setOnAction(event -> {
        
        String oppick=(String) action.getValue();
        //pickdate hours mins room get values
        if((oppick.equals("Add a Lecture"))||(oppick.equals("Remove a Lecture"))
                &&(pickdate.getValue()!=null&&hours.getValue()!=null&&room.getText()!=null)){
        String s=sendMessageToServer(oppick+"£"+module.getText()+"£"+pickdate.getValue()+"£"+hours.getValue()+"£"+room.getText());
        x.setText(s);
        }
        });
        btns.add(Submit);

                addremls.add(x);

        // Layout for the scene
        
        addpane.addRow(1,pickdaylabel,pickdate);
        addpane.addRow(2,hourslabel,hours);
        addpane.addRow(3,roomlabel,room);
        addpane.addRow(4,Submit,x);
        addpane.addRow(5,Homebuttona, stop);
        
        
        // Text field for the user to enter the message
        //date,time,and room number 
        Label pickdaylabelr=new Label("Day: ");
        addremls.add(pickdaylabelr);

        ChoiceBox pickdater=new ChoiceBox();
        pickdater.getItems().addAll(Arrays.asList(daysofweek));
        pickdater.setMinWidth(100);
        pickdater.setPrefWidth(200);
        pickdater.setMaxWidth(200);
        pickdater.setStyle(
          "-fx-background-color: white;"  +   "-fx-background-insets: 1px;"
          + "-fx-background-radius:10px; -fx-text-fill: green; -fx-border-radius: 10px; -fx-border-color: #388E3C;"
        );
        
        //hours
        Label hourslabelr=new Label("hours: ");
        addremls.add(hourslabelr);

        Spinner<Integer> hoursr=new Spinner<>(900,1700,900,100);
        //mins
        hoursr.setMinWidth(100);
        hoursr.setPrefWidth(200);
        hoursr.setMaxWidth(200);
        hoursr.getEditor().setStyle(
          "-fx-background-color: white;"   +   "-fx-background-insets: 1px;"
                  + "-fx-background-radius:10px; -fx-text-fill: green; -fx-border-radius: 10px; -fx-border-color: #388E3C;"
        );
        Label roomlabelr=new Label("room: ");
        addremls.add(roomlabelr);

        //room number
        TextField roomr = new TextField();
     addremTF.add(roomr);
        
        Button stopremove = new Button("stop");
        stopremove.setOnAction(event -> {
                sendMessageToServer("stop");
                
                primaryStage.close();
            }
        );
        //btns.add(stopremove);
        
        Label xr=new Label();
        
        Button Submitr = new Button("Submit");
        Submitr.setOnAction(event -> {
       // x.setText((String) action.getValue());
        String oppick="Remove a Lecture";
        //pickdate hours mins room get values
        if((pickdater.getValue()!=null&&hoursr.getValue()!=null&&roomr.getText()!=null)){
        //String s=oppick+"£"+module.getText()+"£"+pickdate.getValue()+"£"+hours.getValue()+"£"+room.getText();
        String s=sendMessageToServer(oppick+"£"+moduler.getText()+"£"+pickdater.getValue()+"£"+hoursr.getValue()+"£"+roomr.getText());
        xr.setText(s);
        }
        });
        btns.add(Submitr);
        addremls.add(xr);
        removepane.addRow(1,pickdaylabelr,pickdater);
        removepane.addRow(2,hourslabelr,hoursr);
        removepane.addRow(3,roomlabelr,roomr);
        removepane.addRow(4,Submitr,xr);
        removepane.addRow(5,Homebuttonr, stopremove);
        
        //displayscedule
        Button stopsced = new Button("stop");
        stopsced.setOnAction(event -> {
                sendMessageToServer("stop");
                
                primaryStage.close();
            }
        );
        //btns.add(stopsced);

        TextField classyear=new TextField();
        addremTF.add(classyear);
        Button display = new Button("Display");
        GridPane timetable=new GridPane();
        
        /*GridPane.setConstraints(closeButton, 8, 8);
        GridPane.setConstraints(label, 4, 4);*/
        display.setOnAction((ActionEvent event) -> {
            timetable.getChildren().clear();
            //x.setText((String) action.getValue());
        //String oppick=(String) action.getValue();
        //pickdate hours mins room get values
        if (!classyear.getText().equals("")) {
            String schedule1 = sendMessageToServer("Display Schedule"+"£"+classyear.getText());
            String[] x1 = schedule1.split("!");
            String[] nine = x1[0].split("£");
            String[] ten = x1[1].split("£");
            String[] eleven = x1[2].split("£");
            String[] twelve = x1[3].split("£");
            String[] one = x1[4].split("£");
            String[] two = x1[5].split("£");
            String[] three = x1[6].split("£");
            String[] four = x1[7].split("£");
            String[] five = x1[8].split("£");
            for(int i2=0; i2<7; i2++){
                Labels(timetable,daysofweek[i2], i2, 3);
                if(!nine[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(nine[i2]), i2, 4);}
                else{Labels(timetable,"", i2, 4);}
                
                if(!ten[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(ten[i2]), i2, 5);}
                else{Labels(timetable,"", i2, 5);}
                
                if(!eleven[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(eleven[i2]), i2, 6);}
                else{Labels(timetable,"", i2, 6);}
                
                if(!twelve[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(twelve[i2]), i2, 7);}
                else{Labels(timetable,"", i2, 7);}
                
                if(!one[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(one[i2]), i2, 8);}
                else{Labels(timetable,"", i2, 8);}
                
                if(!two[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(two[i2]), i2, 9);}
                else{Labels(timetable,"", i2, 9);}
                
                if(!three[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(three[i2]), i2, 10);}
                else{Labels(timetable,"", i2, 10);}
                
                if(!four[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(four[i2]), i2, 11);}
                else{Labels(timetable,"", i2, 11);}
                
                if(!five[i2].equalsIgnoreCase("null")){
                    Labels(timetable,format(five[i2]), i2, 12);}
                else{Labels(timetable,"", i2, 12);}
            }//timetable.setGridLinesVisible(true);
        }
    });
        
        btns.add(display);

        timetable.setMinWidth(450.0);
        timetable.setMinHeight(200.0);
        timetable.setPrefWidth(100.0);
        timetable.setVgap(5); 
        timetable.setHgap(5);
       // timetable.setStyle("-fx-background-color: palegreen;");
      
        
        
        
        schedulepane.add(classyear,0,0);
        schedulepane.add(display,1,0);
        schedulepane.add(timetable,0,1,2,1);
        schedulepane.add(Homebuttons,0,2);//Homebuttons
        schedulepane.add(stopsced,2,2);
        GridPane.setVgrow(stopsced,javafx.scene.layout.Priority.ALWAYS);
        
        
        
        primaryStage.widthProperty().addListener((obs, oldVal, newVal) -> {
        resizeWidth(panes,txts,btns, newVal);
        });

        primaryStage.heightProperty().addListener((obs, oldVal, newVal) -> {
        resizeHeight(panes,txts,btns, newVal);
        });
        
       // addpane.setStyle();
        action.setStyle("-fx-background-color: white;"  +   "-fx-background-insets: 1px;" +
        "-fx-background-radius:10px; -fx-text-fill: white; -fx-border-radius: 10px; -fx-border-color: #388E3C;");
        action.setPrefWidth(200);
        
         for(Button b:btns)
            b.setStyle(
                "-fx-background-radius:15px;"
                        +   "-fx-background-insets: 1px;"

                        + " -fx-text-fill: green;"
                        + " -fx-border-radius: 15px;"
                        + "-fx-border-color: green;"
                        + "-fx-shadow-color: green;");
      
         
         stoph.setStyle("-fx-background-radius:15px;"
                 +"-fx-background-color: red;-fx-text-fill: white;"
                 +   "-fx-background-insets: 1px;"

                  + " -fx-border-radius: 15px;"+
                     "-fx-border-color: darkred;" 
                        );
        stop.setStyle(
                "-fx-background-radius:15px;"
                 +   "-fx-background-insets: 1px;"
                +"-fx-background-color: red;" +
                    " -fx-border-radius: 15px;"+

                     "-fx-border-color: darkred;" +
                        "-fx-text-fill: white;\n" ); 
        stopremove.setStyle("-fx-background-radius:15px;"
                             +   "-fx-background-insets: 1px;"
                             + "-fx-background-color: red;" 
                                  + " -fx-border-radius: 15px;"+
                              "-fx-border-color: darkred;" +
                            "-fx-text-fill: white;\n" );
        stopsced.setStyle("-fx-background-radius:15px;"+
                        "-fx-background-color: red;" 
                +   "-fx-background-insets: 1px;"
                         + " -fx-border-radius: 15px;"+
                         "-fx-border-color: darkred;" +
                        "-fx-text-fill: white;\n" ); 
        timetable.getStyleClass().add("timetable"); 
        addpane.setAlignment(Pos.CENTER);
        removepane.setAlignment(Pos.CENTER);
        home.setAlignment(Pos.CENTER);
        schedulepane.setAlignment(Pos.TOP_LEFT);
        timetable.setAlignment(Pos.CENTER);
        timetable.setHgap(5);
        timetable.setVgap(5);
        addpane.setHgap(5);
        addpane.setVgap(5);
        removepane.setHgap(5);
        removepane.setVgap(5);
        schedulepane.setHgap(5);
        schedulepane.setVgap(5);
        home.setHgap(5);
        home.setVgap(2.5);
        for(Label l:addremls){
            l.setStyle(
            "-fx-font-weight: bold;"
              +  "  -fx-fill: lightseagreen;"+
             " -fx-stroke: black;"+
             " -fx-stroke-width: 2px;"
            );
        l.setTextFill(Color.valueOf("#176106"));}
        for(TextField t:addremTF){
        t.setStyle(
            "-fx-background-color: white;-fx-background-radius:10px; "
                    + "-fx-text-fill: black; -fx-border-radius: 10px; "
                    + "-fx-border-color: #388E3C;"
                    +   "-fx-background-insets: 1px;"
        );
        }

        Scene scene = new Scene(pane, 600, 400);
        
        primaryStage.setTitle("Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
   

        
/**
 * @param timetable a GridPane.
 * @param labelname names to be set to labels.
 * @param columnIndex indexes of columns.
 * @param rowIndex indexes of rows.
 * Gets a GridPane and creates labels to fill it with
 */
public void Labels(GridPane timetable,String labelname, int columnIndex, int rowIndex) {
            Label label = new Label();
            String daysofweek="MondayTuesdayWednesdayThursdayFridaySaturdaySunday"; 
            //gridPane.setColumnIndex(label, columnIndex);
            //gridPane.setRowIndex(label, rowIndex);
            timetable.add(label, columnIndex, rowIndex);
            label.setId(labelname+columnIndex);
            label.setVisible(true);
            
            //label.setId(null+columnIndex);
            //System.out.print(labelname+columnIndex);
            if(//labelname.equalsIgnoreCase("Monday")
                  daysofweek.contains(labelname)   ){
            label.setFont(Font.font(null, FontWeight.BOLD,14));
            label.setText(labelname);
            }else{
            //label.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            //label.setBorder(new Border(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            label.setText(labelname);
            label.setAlignment(Pos.CENTER);
            }
            //label.setBorder(new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));

    }
/** * @param s schedule string 
 * arranges the string to be put into the schedule
 * @return String after it was arranged
 * 
 */
    public String format(String s) {
        String[] arrs=s.split(",");
        String temp="";
        if(arrs.length==4){
        temp=arrs[2]+"\n"+arrs[0]+"\n"+arrs[3]+"\n";
        }
        s=temp;
        return s;
    }
    /**
     * @param message
     * @return String
     * creates a socket and
     * Sends a message to the server and returns message sent back.
     * */

    private String sendMessageToServer(String message) {
        
    if(!stopflagraised){
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) 
        {
            // Send the message to the server
            
            
            writer.println(message);
            System.out.println("Message sent to server: " + message);

            // Optionally, read response from server
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                String response = reader.readLine();
                System.out.println("Server response: " + response);
                
                return response;
                
            }
            
        } catch (IOException e) {
        }
    }
    return "";
    }
/**@patam ArrayList<TextField> 
 * @patam ArrayList<Button>
 * @patam newVal
 * gets array lists and resizes them*/
    private void resizeWidth( ArrayList<Pane> panes,ArrayList<TextField> txts, ArrayList<Button> btns, Number newVal) {
   
    for(Pane p:panes)
     p.setMinWidth(newVal.doubleValue());
    }
    /**@patam ArrayList<TextField> 
    * @patam ArrayList<Button>
    * @patam newVal
    * gets array lists and resizes them*/
    private void resizeHeight( ArrayList<Pane> panes,ArrayList<TextField> txts, ArrayList<Button> btns, Number newVal) {
   
    for(Pane p:panes)
     p.setMinHeight(newVal.doubleValue());
    }
    
}