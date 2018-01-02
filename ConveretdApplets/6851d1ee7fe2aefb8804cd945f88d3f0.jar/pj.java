import java.awt.TextField;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class pj implements ActionListener, KeyListener, FocusListener
{
    private final n ta;
    
    pj(final n ta) {
        this.ta = ta;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.ta.a(keyEvent.getSource());
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (n._(this.ta).getText().length() > 0) {
            this.ta.a(actionEvent.getSource());
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        final TextField textField = (TextField)focusEvent.getSource();
        if (textField.getText().equals(n.a(this.ta).a().b("strEnterSymbolHere"))) {
            textField.setText("");
        }
        else {
            textField.selectAll();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        final TextField textField = (TextField)focusEvent.getSource();
        if (n.f(this.ta)) {
            textField.setText(textField.getText().toUpperCase());
        }
    }
}
