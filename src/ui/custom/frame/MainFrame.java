package ui.custom.frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // Construtor recebe o tamanho da janela e o painel principal
    public MainFrame(final Dimension dimension, final JPanel mainPanel){
        super("Sudoku"); // Define o título da janela como "Sudoku"
        this.setSize(dimension); // Define o tamanho da janela
        this.setPreferredSize(dimension); // Define o tamanho preferido
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // Fecha o app ao clicar no X
        this.setVisible(true); // Torna a janela visível
        this.setLocationRelativeTo(null); // Centraliza a janela na tela
        this.setResizable(false); // Impede o redimensionamento
        this.add(mainPanel); // Adiciona o painel principal à janela
    }
}
