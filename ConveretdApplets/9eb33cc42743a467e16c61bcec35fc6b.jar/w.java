import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class w implements ActionListener
{
    public final void actionPerformed(final ActionEvent actionEvent) {
        final int selectedIndex = x.fh.getSelectedIndex();
        if (selectedIndex != -1) {
            final b1 b1 = x.e9.lq.elementAt(selectedIndex);
            x.fe.setText(String.valueOf(b1.fe));
            x.ff.setText(String.valueOf(b1.ff));
            x.fg.setText(b1.fg);
            int i;
            for (i = 1; i < x.fa.length; ++i) {
                if (b1.ff == x.fa[i]) {
                    x.fc.select(x.fb[i]);
                    break;
                }
            }
            if (i == x.fa.length) {
                x.fc.select("general");
            }
        }
    }
}
