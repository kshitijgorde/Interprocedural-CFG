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

class tea implements ActionListener, KeyListener, FocusListener
{
    private final super da;
    
    tea(final super da) {
        this.da = da;
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
        synchronized (this.da) {
            this.da.a(false);
            new Tea(this, o).start();
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (super.a(this.da).getText().length() > 0) {
            this._(actionEvent.getSource());
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        final TextField textField = (TextField)focusEvent.getSource();
        if (textField.getText().equals(super.b(this.da).a().a("strEnterSymbolHere"))) {
            textField.setText("");
        }
        else {
            textField.selectAll();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        final TextField textField = (TextField)focusEvent.getSource();
        if (super.b(this.da)) {
            textField.setText(textField.getText().toUpperCase());
        }
    }
    
    static super b(final tea tea) {
        return tea.da;
    }
}
