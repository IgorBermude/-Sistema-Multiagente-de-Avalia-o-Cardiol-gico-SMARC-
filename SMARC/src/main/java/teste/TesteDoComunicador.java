/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import agentes.AgNicot;
import agentes.AgObesi;
import agentes.AgPressArt;
import agentes.AgSedet;
import agentes.Agente;

/**
 *
 * @author Paulo
 */
public class TesteDoComunicador {
    public static void main(String[] args) {
        // Dados de exemplo do paciente
        Agente agenteObesidade = new AgObesi("Agente Obesidade", 107, 1.70);
        Agente agentePressao = new AgPressArt("Agente Pressao", 150, 95);
        Agente agenteSedetarismo = new AgSedet("Agente Sedentarismo", 3);
        Agente agenteNicotina = new AgNicot("Agente Nicotina", 6);

        // Inicializa os comunicadores para cada agente
        Comunicador comunicadorObesidade = new Comunicador(agenteObesidade);
        Comunicador comunicadorPressao = new Comunicador(agentePressao);
        Comunicador comunicadorSedentarismo = new Comunicador(agenteSedetarismo);
        Comunicador comunicadorNicotina = new Comunicador(agenteNicotina);

        // Inicia as threads para simular a comunicação
        comunicadorObesidade.start();
        comunicadorPressao.start();
        comunicadorSedentarismo.start();
        comunicadorNicotina.start();
        
        // Resultado
        System.out.println();
    }
}
