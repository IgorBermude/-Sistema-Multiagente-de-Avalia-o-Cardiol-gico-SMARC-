/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Desnecessario;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *
 * @author 2022122760265
 */
public class Send {
    public static void main(String[] args) {
        //Endereço:
        String multiCastAddress = "224.0.0.1";
        final int multiCastPort = 52684;
        
        try{
            //Create Socket
            System.out.println("Create socket on address " + multiCastAddress + " and port " + multiCastPort + ".");
            InetAddress group = InetAddress.getByName(multiCastAddress);
            MulticastSocket s = new MulticastSocket(multiCastPort);
            s.joinGroup(group);
            
            //Prepare Data
            String message = "Hello world!";
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oss = new ObjectOutputStream(baos);
            oss.writeObject(message);
            
            byte[] data = baos.toByteArray();
            
            s.send(new DatagramPacket(data, data.length, group, multiCastPort));
        } catch(UnknownHostException e){
            System.out.println("UnknownHostException: "+e.getMessage());
        } catch(IOException e){
            System.out.println("IOException: "+e.getMessage());
        }
    }  
}
