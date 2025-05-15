package ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CheckGameStatusButton extends JButton {

    public CheckGameStatusButton(final ActionListener actionListener) {
        this.setText("Verificar jogo"); // Texto exibido no botão
        this.addActionListener(actionListener); // Define a ação ao clicar
    }
}
