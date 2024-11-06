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
import java.net.UnknownHostException;

/**
 *
 * @author Paulo
 */
public class ProgramaRecebe {
    public void  main(String[] args){
        MulticastSocket s = null;
        InetAddress group = null;
        String multiCastAddress = "224.0.0.1";
        final int multiCastPort = 52684;
        final int bufferSize = 1024 * 4;
        
        try{
            System.out.println("Create socket on address " + multiCastAddress + "and port " + multiCastPort + ".");
            group = InetAddress.getByName(multiCastAddress);
            s = new MulticastSocket(multiCastPort);
            s.joinGroup(group);
        
        }catch(UnknownHostException e){
            System.out.println("ProgramaRecebe - UnknownHostException: "+e.getMessage());
        }catch(IOException e){
            System.out.println("ProgramaRecebe - IOExceprion: "+e.getMessage());
        }
        
        while(true){
            System.out.println("Wating for datagram to be received ...");
            
            try{
                byte[] buffer = new byte[bufferSize];
                s.receive(new DatagramPacket(buffer, bufferSize, group, multiCastPort));
                System.out.println("Datagram received!");   
                
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream ois = new ObjectInputStream(bais);
                
                Object readObject = ois.readObject();
                
                if(readObject instanceof Pessoa){
                    Pessoa p = (Pessoa)readObject;
                    
                    System.out.println("Objeto Recebido!!");
                    System.out.println("Nome: "+p.getNome());
                    System.out.println("Sexo: "+p.getSexo());
                }else{
                    System.out.println("The received object is not of class Pessoa!");
                }
            }catch(Exception e){
                System.out.println("No object could be read from the received UDP datagram");
            }
        }
    }
}
