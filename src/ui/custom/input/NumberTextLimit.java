package ui.custom.input;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.util.List;

import static java.util.Objects.isNull;

public class NumberTextLimit extends PlainDocument {

    // Lista de números válidos (1 a 9)
    private final List<String> NUMBERS = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9");

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        // Ignora entradas inválidas ou nulas
        if(isNull(str) || (!NUMBERS.contains(str))) return;

        // Permite apenas um número na célula
        if(getLength() + str.length() <= 1){
            super.insertString(offs, str, a);
        }
    }
}
