/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.smarc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author 2022122760265
 */
public class MulticastPeer {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        String msg;
        MulticastSocket s = null;
        
        try {
            System.out.println("\n\nEscreva sua mensagem agora: ");
            
            msg = input.next();
            
            InetAddress group = InetAddress.getByName("Usuario-PC");
            
            s = new MulticastSocket(6789);
            
            s.joinGroup(group);
            
            byte []m = msg.getBytes();
            DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6789);
            s.send(messageOut);
            
            byte [ ] buffer = new byte[1000];
            
            for(int i = 0; i < 1000; i++){
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                
                System.out.println("Recebido: "+new String(messageIn.getData()));
            }
            
            s.leaveGroup(group);
        } catch(SocketException e){
            System.out.println("Socket: "+e.getMessage());
        } catch(IOException e){
            System.out.println("IO: "+e.getMessage()); 
        } finally{
            if(s!= null)s.close();
        }
    }
}
