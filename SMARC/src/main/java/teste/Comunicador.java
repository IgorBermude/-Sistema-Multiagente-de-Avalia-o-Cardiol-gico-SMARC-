/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

/**
 *
 * @author 2022122760265
 */
public class Comunicador extends Thread{
    private String nome;
    private String multiCastAddress = "224.0.0.1";
    private static int porta;
    private static InetAddress enderecoGrupo = null;
    private static MulticastSocket soc = null;
    private static ObjectOutputStream saida = null;
    private static ObjectInputStream entrada = null;
    private final int tamanho = 1024 * 4; //tamanho maximo de transferencia de objeto.
    
    public Comunicador(String nome){
        this.nome = nome;
        try{
            porta = 52684;
            enderecoGrupo = InetAddress.getByName(multiCastAddress);
            soc = new MulticastSocket(porta);
            soc.joinGroup(enderecoGrupo);
        } catch (UnknownHostException e){
            System.out.println("FALHA UnknowHostException: "+e.getMessage());
        } catch (IOException e){
            System.out.println("FALHA IOException: "+e.getMessage());
        }
    }
    
    public Pessoa recebe(){
        byte[] buffer = new byte[tamanho];
        Pessoa pessoa = null;
        
        try{
            //recebe uma solicitação/resposta:
            soc.receive(new DatagramPacket(buffer, tamanho, enderecoGroupo, porta));
            
            //Deserialização do Objeto:
            ByteArrayInputStream bAEntrada = new
        }
    }
}
