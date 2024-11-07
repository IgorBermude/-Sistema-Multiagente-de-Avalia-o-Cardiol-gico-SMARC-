/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

/**
 *
 * @author 2022122760265
 */
public class AgObesi extends Agente {
    private double peso;
    private double altura;

    public AgObesi(String nome, double peso, double altura) {
        super(nome);
        this.peso = peso;
        this.altura = altura;
    }

    @Override
    public DadosAgente processarDados() {
        double imc = peso / (altura * altura);
        double grauEvidencia = calcularGrauEvidencia(imc);
        return new DadosAgente("Obesidade", grauEvidencia);
    }

    private double calcularGrauEvidencia(double imc) {
        if (imc >= 25 && imc <= 40) {
            return (imc - 25) / (40 - 25);
        } else if (imc > 40) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}
