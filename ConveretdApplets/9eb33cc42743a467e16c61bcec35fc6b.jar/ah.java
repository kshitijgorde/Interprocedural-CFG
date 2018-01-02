import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ah implements ActionListener
{
    public final void actionPerformed(final ActionEvent actionEvent) {
        try {
            final ag ag = new ag(a.f.gb(), a.f.ga(), a.f, a.f, a.c, a.a, a.h.getDirectory(), a.o.getText(), a.m.getText(), a.j.getState(), a.i.getState(), a.g);
        }
        catch (Exception ex) {
            a1.h9("MindTerm - Alert", "Error starting SCP-tread: " + ex.getMessage(), a.a);
        }
    }
}
