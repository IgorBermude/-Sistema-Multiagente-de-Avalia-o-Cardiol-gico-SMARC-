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
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 *
 * @author Paulo
 */
public class Observador {
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
        
        byte[] buf = new byte[1512];
        
        while (true){
            DatagramPacket recibo = new DatagramPacket(buf, buf.length);
            
            try{
                s.setSoTimeout(120000);
                s.receive(recibo);
            }catch(SocketTimeoutException e){
                System.out.println("Observador FALHA SocketTimeoutException: "+e.getMessage());
                break;
            }catch(IOException e){
                System.out.println("Observador FALHA IOException: "+e.getMessage());
            }
            
            String str = new String(recibo.getData());
            System.out.println("(" + recibo.getAddress().getHostAddress()+ ":" + recibo.getPort() + ") <<" + str.trim());
        }
    }
}
