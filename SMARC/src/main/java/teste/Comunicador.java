/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import agentes.Agente;
import agentes.DadosAgente;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 *
 * @author 2022122760265
 */
public class Comunicador extends Thread {

    private String multiCastAddress = "224.0.0.1";
    private static int porta = 52684;
    private static InetAddress enderecoGrupo;
    private static MulticastSocket soc;
    private final Agente agente;

    public Comunicador(Agente agente) {
        this.agente = agente;
        try {
            enderecoGrupo = InetAddress.getByName(multiCastAddress);
            soc = new MulticastSocket(porta);
            soc.joinGroup(enderecoGrupo);
        } catch (IOException e) {
            System.out.println("FALHA IOException"+e.getMessage());
        }
    }

    public void envia(DadosAgente dados) {
        try {
            ByteArrayOutputStream bASaida = new ByteArrayOutputStream();
            ObjectOutputStream saida = new ObjectOutputStream(bASaida);
            saida.writeObject(dados);
            byte[] data = bASaida.toByteArray();
            soc.send(new DatagramPacket(data, data.length, enderecoGrupo, porta));
            System.out.println("Enviado: " + dados);
        } catch (IOException e) {
            System.out.println("IOException"+e.getMessage());
        }
    }

    public DadosAgente recebe() {
        byte[] buffer = new byte[4096];
        DadosAgente dados = null;
        try {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            soc.receive(packet);
            ByteArrayInputStream bAEntrada = new ByteArrayInputStream(buffer);
            ObjectInputStream entrada = new ObjectInputStream(bAEntrada);
            dados = (DadosAgente) entrada.readObject();
            System.out.println("Recebido: " + dados);
        } catch (IOException e) {
            System.out.println("FALHA IOException"+e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("FALHA ClassNotFoundException"+e.getMessage());
        }
        return dados;
    }

    public void run() {
        DadosAgente dados = agente.processarDados();
        envia(dados);
    }
}
