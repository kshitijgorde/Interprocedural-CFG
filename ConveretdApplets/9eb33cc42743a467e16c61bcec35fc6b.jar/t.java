import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class t implements ActionListener
{
    public final void actionPerformed(final ActionEvent actionEvent) {
        final int selectedIndex = x.fh.getSelectedIndex();
        if (selectedIndex != -1) {
            x.f.gq(selectedIndex, true);
        }
        x.d2();
    }
}
