/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author Paulo
 */
public class Envia {
    public static void main(String[] args) throws IOException{
        int porta = 6868;
        InetAddress ipGrupo = null;
        MulticastSocket s = null;
        
        String msg = "mensagem default";
        
        try{
            ipGrupo = InetAddress.getByName("224.225.226.227");
            s = new MulticastSocket(porta);
            
            s.joinGroup(ipGrupo);
        }catch(SocketException e){
            s.joinGroup(ipGrupo);
        }catch(UnknownHostException e){
            System.out.println("FALHA no UnknownHostException: " +e.getMessage());
        }catch(IOException e){
            System.out.println("FALHA no IOException: " +e.getMessage());
        }
        
        DatagramPacket dtgrm = new DatagramPacket(msg.getBytes(), msg.length(), ipGrupo, porta);
        
        try{
            s.send(dtgrm);
        }catch(IOException e){
            System.out.println("FALHA 2 IOException: " +e.getMessage());
        }
        
        try{
            s.leaveGroup(ipGrupo);
            if(s != null) s.close();
        }catch(IOException e){
            System.out.println("FALHA 3 IOException: "+e.getMessage());
        }
    }
}
