import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ao implements ActionListener
{
    public final void actionPerformed(final ActionEvent actionEvent) {
        try {
            final String selectedItem = aq.hd.getSelectedItem();
            aq.f.ci("proxytype", selectedItem);
            if (!"none".equalsIgnoreCase(selectedItem)) {
                aq.f.ci("proxyhost", aq.hb.getText());
                aq.f.ci("proxyport", aq.ha.getText());
            }
            if (aq.hc.getState()) {
                aq.f.ci("proxyuser", aq.g9.getText());
                aq.f.ci("prxpassword", aq.g8.getText());
            }
            else if ("socks4".equals(selectedItem)) {
                aq.f.ci("proxyuser", aq.g9.getText());
            }
            aq.he.setVisible(false);
        }
        catch (Exception ex) {}
    }
}
