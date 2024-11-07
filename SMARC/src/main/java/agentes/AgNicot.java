/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

/**
 *
 * @author 2022122760265
 */
public class AgNicot extends Agente {
    private int pontuacaoFagerstrom;

    public AgNicot(String nome, int pontuacaoFagerstrom) {
        super(nome);
        this.pontuacaoFagerstrom = pontuacaoFagerstrom;
    }

    @Override
    public DadosAgente processarDados() {
        double grauEvidencia = calcularGrauEvidencia();
        String resultado =
        return new DadosAgente("Nicotina", grauEvidencia);
    }

    private double calcularGrauEvidencia() {
        // De acordo com os slides:
        // Pontuação 10: Grau de evidência = 1.0
        // Pontuação 8: Grau de evidência = 0.75
        // Pontuação 5: Grau de evidência = 0.5
        // Pontuação 3: Grau de evidência = 0.25
        // Pontuação 0: Grau de evidência = 0.0
        if (pontuacaoFagerstrom >= 10) {
            return 1.0;
        } else if (pontuacaoFagerstrom >= 8) {
            return 0.75;
        } else if (pontuacaoFagerstrom >= 5) {
            return 0.5;
        } else if (pontuacaoFagerstrom >= 3) {
            return 0.25;
        } else {
            return 0.0;
        }
    }
    
    @Override
    private String processarResultado(){
        
    }
}
