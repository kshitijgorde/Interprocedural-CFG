import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a0 implements ActionListener
{
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("OK")) {
            a1.ii = a1.ih.getText();
        }
        else {
            a1.ii = null;
        }
        a1.ik.setVisible(false);
    }
}
