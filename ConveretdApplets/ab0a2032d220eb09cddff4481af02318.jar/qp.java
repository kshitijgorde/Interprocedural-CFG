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

class qp implements ActionListener, KeyListener, FocusListener
{
    private final var n;
    
    qp(final var n) {
        this.n = n;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this._(keyEvent.getSource());
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void _(final Object o) {
        synchronized (this.n) {
            var._(this.n).a(true);
            new tq(this, o).start();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (var._(this.n).getText().length() > 0) {
            this._(actionEvent.getSource());
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        final TextField textField = (TextField)focusEvent.getSource();
        if (textField.getText().equals(var._(this.n).b()._("strEnterSymbolHere"))) {
            textField.setText("");
        }
        else {
            textField.selectAll();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        final TextField textField = (TextField)focusEvent.getSource();
        textField.setText(textField.getText().toUpperCase());
    }
    
    static var _(final qp qp) {
        return qp.n;
    }
}
