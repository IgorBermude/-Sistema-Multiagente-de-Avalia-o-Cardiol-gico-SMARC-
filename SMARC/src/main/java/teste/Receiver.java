/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author 2022122760265
 */
public class Receiver {
    public static void main(String[] args) throws IOException {
        //Address
        String multiCastAddress = "224.0.0.1";
        final int multiCastPort = 52684;
        final int bufferSize = 1024 * 4;
        
        //Create Socket
        System.out.println("Create socket on address" + multiCastAddress + " and port " + ".");
        InetAddress group = InetAddress.getByName(multiCastAddress);
        MulticastSocket s = new MulticastSocket(multiCastPort);
        s.joinGroup(group);
        
        while(true){
            System.out.println("Wating for datagram to be received...");
            
            //Create buffer
            byte[] buffer = new byte[bufferSize];
            s.receive(new DatagramPacket(buffer, bufferSize, group, multiCastPort));
            System.out.println("Datagram reeived!");
            
            //Deserialize object
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            try{
                Object readObject = ois.readObject();
                
                if(readObject instanceof String){
                    String message = (String) readObject;
                    System.out.println("Message is: "+message);
                } else{
                    System.out.print("The received object is not of type String!");
                }//if
                
            } catch(Exception e){
                System.out.println("No object could be read from the received UDP datagram.");
            }
        }
    }
}
