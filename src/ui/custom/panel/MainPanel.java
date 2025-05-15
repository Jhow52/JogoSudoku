package ui.custom.panel;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(final Dimension dimension){
        this.setSize(dimension); // Define o tamanho do painel
        this.setPreferredSize(dimension); // Define o tamanho preferido
    }
}
