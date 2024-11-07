/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

/**
 *
 * @author 2022122760265
 */
public class AgPressArt extends Agente {
    private double pas;
    private double pad;

    public AgPressArt(String nome, double pas, double pad) {
        super(nome);
        this.pas = pas;
        this.pad = pad;
    }

    @Override
    public DadosAgente processarDados() {
        double grauEvidencia = calcularGrauEvidencia(pas, pad);
        return new DadosAgente("Pressao", grauEvidencia);
    }

    private double calcularGrauEvidencia(double pas, double pad) {
        double evidenciaPAS = pas >= 120 && pas <= 140 ? (pas - 120) / (140 - 120) : (pas > 140 ? 1 : 0);
        double evidenciaPAD = pad >= 80 && pad <= 90 ? (pad - 80) / (90 - 80) : (pad > 90 ? 1 : 0);
        return Math.max(evidenciaPAS, evidenciaPAD);
    }
}
