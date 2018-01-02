import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ai implements ActionListener
{
    public final void actionPerformed(final ActionEvent actionEvent) {
        a.g = !a.g;
        if (a.g) {
            a.l.setText("Copy from local files/directories:");
            a.k.setText("To directory/file on server:");
        }
        else {
            a.l.setText("Copy to local directory/file:");
            a.k.setText("From files/directories on server:");
        }
    }
}
