import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class az implements ActionListener
{
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("Yes")) {
            a1.h8 = true;
        }
        else {
            a1.h8 = false;
        }
        a1.ia.setVisible(false);
    }
}
