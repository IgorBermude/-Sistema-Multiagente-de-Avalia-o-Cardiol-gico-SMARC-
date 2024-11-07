/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            soc.receive(new DatagramPacket(buffer, tamanho, enderecoGrupo, porta));
            
            //Deserialização do Objeto:
            ByteArrayInputStream bAEntrada = new ByteArrayInputStream(buffer);
            entrada = new ObjectInputStream(bAEntrada);
            
            Object objetoLido = entrada.readObject();
            
            if(objetoLido instanceof Pessoa){
                pessoa = (Pessoa) objetoLido;
            }
        }catch(IOException e){
            System.out.println("recebe() : FALHA IOException: "+e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println("recebe() : FALHA ClassNotFoundException: "+e.getMessage());
        }
        return pessoa;
    }
    
    public void envia(Pessoa pessoa){
        try{
            ByteArrayOutputStream bASaida = new ByteArrayOutputStream();
            saida = new ObjectOutputStream(bASaida);
            
            saida.writeObject(pessoa);
            
            byte[] data = bASaida.toByteArray();
            soc.send(new DatagramPacket(data, data.length, enderecoGrupo, porta));
        }catch(IOException e){
            System.out.println("IOException: "+e.getMessage());
        }
    }
    
    public void run(){
        int contador = 0;
        Pessoa p = null;
        
        try{
            Thread.sleep(10000);
        }catch(InterruptedException ex){
            Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while(contador < 7){
            try{
                switch(contador){
                    case 0: p = new Pessoa("Rodrigo Bivar", 'M'); break;
                    case 1: p = new Pessoa("Ximene Bivar", 'F'); break;
                    case 2: p = new Pessoa("Urraca Sanchez", 'F'); break;
                    case 3: p = new Pessoa("Ordonez", 'M'); break;
                    case 4: p = new Pessoa("Afonso Sanchez", 'M'); break;
                    case 5: p = new Pessoa("Sancho Sanchez", 'M'); break;
                    case 6: p = new Pessoa("Diego Bivar", 'M'); break;
                }
                if( p != null) System.out.println("Enviando pessoa: " +p.getNome());
                
                envia(p);
                
                Thread.sleep(2000);
                
                p = recebe();
                
                System.out.append("DADO RECEBIDO por "+nome+": ");
                System.out.append("\tNOME: "+p.getNome());
                System.out.append("\tSEXO: "+p.getSexo());
                System.out.append("\tCLASSE: "+p.getClass());

            }catch(InterruptedException ex){
                Logger.getLogger(Comunicador.class.getName()).log(Level.SEVERE, null, ex);
            }
            contador++;
        }
    }
}
