import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class u implements ActionListener
{
    public final void actionPerformed(final ActionEvent actionEvent) {
        final String text = x.fg.getText();
        String s = "general";
        int intValue = -1;
        int intValue2;
        try {
            intValue = Integer.valueOf(x.fe.getText());
            intValue2 = Integer.valueOf(x.ff.getText());
            if (intValue < 1 || intValue > 65535) {
                intValue = -1;
                throw new NumberFormatException();
            }
            if (intValue2 < 1 || intValue2 > 65535) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException ex) {
            if (intValue == -1) {
                x.fe.setText("");
                x.fe.requestFocus();
            }
            else {
                x.ff.setText("");
                x.ff.requestFocus();
            }
            return;
        }
        if (x.fc.getSelectedItem().equals("ftp")) {
            s = "ftp";
        }
        try {
            x.f.ci("local" + x.e9.lq.size(), "/" + s + "/" + intValue + ":" + text + ":" + intValue2);
            if (x.e9.kp()) {
                a1.h9("Tunnel Notice", "Tunnel is now open and operational", x.a);
            }
        }
        catch (Throwable t) {
            a1.h9("Tunnel Notice", "Could not open tunnel: " + t.getMessage(), x.a);
        }
        x.d2();
    }
}
