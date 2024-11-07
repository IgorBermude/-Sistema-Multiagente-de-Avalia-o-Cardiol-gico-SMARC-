/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

/**
 *
 * @author Paulo
 */
public class TesteDoComunicador {
    public static void main(String[] args){
        Comunicador c1 = new Comunicador("Com-01");
        Comunicador c2 = new Comunicador("Com-02");
        Comunicador c3 = new Comunicador("Com-03");
        
        c1.start();
        c2.start();
        c3.start();
    }
}
