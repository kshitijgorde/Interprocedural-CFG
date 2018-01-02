import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a_ implements ActionListener
{
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("OK")) {
            a1.ie = a1.ic.getText();
            if (!a1.ie.equals(a1.ib.getText())) {
                a1.ic.setText("");
                a1.ib.setText("");
                return;
            }
        }
        else {
            a1.ie = null;
        }
        a1.ig.setVisible(false);
    }
}
