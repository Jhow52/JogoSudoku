package ui.custom.panel;

import ui.custom.input.NumberText;
import ui.custom.input.NumberTextLimit;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

import static java.awt.Color.black;

public class SudokuSector extends JPanel {

    public SudokuSector(final List<? extends JTextField> textFields) {
        var dimension = new Dimension(170, 170); // Define tamanho padrão de um setor
        this.setSize(dimension); // Define tamanho
        this.setPreferredSize(dimension); // Define tamanho preferido
        this.setBorder(new LineBorder(Color.black, 2, true)); // Borda preta arredondada de 2px
        this.setVisible(true); // Torna o painel visível
        textFields.forEach(this::add); // Adiciona o campo de texto para aparecer dentro do painel
    }
}
