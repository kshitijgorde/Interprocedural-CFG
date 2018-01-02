import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class aj implements ActionListener
{
    public final void actionPerformed(final ActionEvent actionEvent) {
        a.h.setVisible(true);
        if (a.h.getFile() != null && a.h.getFile().length() > 0) {
            String text = String.valueOf(a.h.getDirectory()) + a.h.getFile();
            if (text.indexOf(32) != -1) {
                text = "\"" + text + "\"";
            }
            a.o.setText(text);
        }
    }
}
