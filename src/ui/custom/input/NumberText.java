package ui.custom.input;

import model.Space;
import service.EventEnum;
import service.EventListener;
import service.BoardService;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

import static java.awt.Font.PLAIN;
import static service.EventEnum.CLEAR_SPACE;

public class NumberText extends JTextField implements EventListener {

    private final Space space;
    private final BoardService boardService;

    public NumberText(Space space,BoardService boardService) {
        this.space = space;
        this.boardService = boardService;

        var dimension = new Dimension(50,50); // Tamanho padrão da célula
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", PLAIN, 20)); // Define a fonte
        this.setHorizontalAlignment(CENTER); // Centraliza o texto
        this.setDocument(new NumberTextLimit()); // Limita a entrada (só 1 número)
        this.setEnabled(!space.isValorFixo()); // Desabilita se for valor fixo

        // Se for valor fixo, já exibe o número correto
        if(space.isValorFixo()){
            this.setText(space.getValorAtual().toString());
        }

        // Adiciona listener para atualizar o valor no Space quando o usuário digitar
        this.getDocument().addDocumentListener(new DocumentListener() {
            private void changeSpace(){
                String text = getText();
                if(boardService.isModoRascunho()){
                    space.clearRascunhos(); // Limpa rascunhos existentes ao digitar
                    if (!text.isEmpty()) {
                        for (char c : text.toCharArray()) {
                            if (Character.isDigit(c)) {
                                space.addRascunho(Integer.parseInt(String.valueOf(c)));
                            }
                        }
                    }
                    repaint(); // Força a repintura para exibir os rascunhos
                } else {
                    if(text.isEmpty()){
                        space.clearSpace();
                        return;
                    }
                    try {
                        int value = Integer.parseInt(text);
                        space.setValorAtual(value);
                    } catch (NumberFormatException e) {
                        // Ignora se a entrada não for um número válido
                        setText(""); // Limpa o campo se não for um número
                        space.clearSpace();
                    }
                }
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSpace();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeSpace();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (boardService.isModoRascunho() && !space.getRascunhos().isEmpty() && space.getValorAtual() == null) {
            g.setFont(new Font("Arial", Font.PLAIN, 10)); // Fonte menor para os rascunhos
            g.setColor(Color.GRAY); // Cor dos rascunhos
            int xOffset = 5;
            int yOffset = 15;
            int count = 0;
            for (Integer rascunho : space.getRascunhos()) {
                g.drawString(rascunho.toString(), xOffset + (count % 3) * 15, yOffset + (count / 3) * 15);
                count++;
            }
        }
    }

    @Override
    public void update(EventEnum eventType) {
        if(eventType.equals(CLEAR_SPACE) && (this.isEnabled())){
            this.setText("");
            space.clearRascunhos(); // Limpa os rascunhos também ao resetar
            repaint();
        }
    }
}
