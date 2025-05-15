package model;

import java.util.HashSet;
import java.util.Set;

public class Space {
    private Integer valorAtual;         // Valor atual inserido na célula
    private final int valorEsperado;    // Valor correto para a célula
    private final boolean valorFixo;    // Indica se o valor é fixo (pré-definido)
    private final Set<Integer> rascunhos = new HashSet<>();

    public Space(int valorEsperado, boolean valorFixo) {
        this.valorEsperado = valorEsperado;
        this.valorFixo = valorFixo;
        if(valorFixo){
            this.valorAtual = valorEsperado; // Inicializa valorAtual se for fixo
        }
    }

    public Integer getValorAtual() {
        return valorAtual;
    }

    public void setValorAtual(Integer valorAtual) {
        if(valorFixo) return; // Impede alteração se for fixo
        this.valorAtual = valorAtual;
    }

    public void clearSpace(){
        setValorAtual(null); // Limpa o valor atual
    }

    public int getValorEsperado() {
        return valorEsperado;
    }

    public boolean isValorFixo() {
        return valorFixo;
    }

    public Set<Integer> getRascunhos() {
        return rascunhos;
    }

    public void addRascunho(Integer rascunho) {
        if (rascunho >= 1 && rascunho <= 9) {
            this.rascunhos.add(rascunho);
        }
    }

    public void removeRascunho(Integer rascunho) {
        this.rascunhos.remove(rascunho);
    }

    public void clearRascunhos() {
        this.rascunhos.clear();
    }
}

