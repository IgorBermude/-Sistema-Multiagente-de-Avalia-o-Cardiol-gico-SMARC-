/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import java.io.Serializable;

/**
 *
 * @author 2022122760265
 */
public abstract class Agente implements Serializable{
    protected String nome;

    public Agente(String nome) {
        this.nome = nome;
    }

    public abstract DadosAgente processarDados(); // Método abstrato para cada agente implementar sua lógica
    
    public abstract String processarResultado(); // Método abstrato para cada agente processar seus resultados

    public String getNome() {
        return nome;
    }
}
