/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

/**
 *
 * @author 2022122760265
 */
public class AgSedet extends Agente {
    private int atividadesPorSemana;

    public AgSedet(String nome, int atividadesPorSemana) {
        super(nome);
        this.atividadesPorSemana = atividadesPorSemana;
    }

    @Override
    public DadosAgente processarDados() {
        double grauEvidencia = calcularGrauEvidencia();
        return new DadosAgente("Sedentarismo", grauEvidencia);
    }

    private double calcularGrauEvidencia() {
        if (atividadesPorSemana >= 4) {
            return 0.0; // Sem risco de sedentarismo
        } else if (atividadesPorSemana <= 0) {
            return 1.0; // Totalmente sedentário
        } else {
            // Grau de evidência proporcional entre 0 e 4 atividades
            return 1.0 - (atividadesPorSemana / 4.0);
        }
    }
}
