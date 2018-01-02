import java.awt.Event;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Frame;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class Aboutdialog extends Dialog
{
    Button okButton;
    
    public Aboutdialog(final Applet app, final String version, final boolean isDemo) {
        super(new Frame(), "About", true);
        final Color c = new Color(50, 255, 255);
        this.setBackground(c);
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 10.0;
        gbc.weighty = 2.0;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridy = 0;
        final Label L1 = new Label("");
        L1.setText(version);
        gbl.setConstraints(L1, gbc);
        this.add(L1);
        gbc.gridy = 1;
        final Label L2 = new Label("© 2000-2010 Juergen Giesen");
        gbl.setConstraints(L2, gbc);
        this.add(L2);
        gbc.gridy = 2;
        final URLLabel L3 = new URLLabel(app, "http://www.GeoAstro.de/GeoAstro/GeoAstro.htm", "http://www.GeoAstro.de", "_new");
        gbl.setConstraints(L3, gbc);
        this.add(L3);
        gbc.gridy = 3;
        final URLLabel L4 = new URLLabel(app, "mailto:jgiesen@t-online.de", "jgiesen@t-online.de");
        gbl.setConstraints(L4, gbc);
        this.add(L4);
        gbc.gridy = 4;
        gbl.setConstraints(this.okButton = new Button("close window"), gbc);
        this.add(this.okButton);
        this.setLocation(250, 200);
    }
    
    public boolean action(final Event evt, final Object whichAction) {
        if (evt.target instanceof Button && evt.target == this.okButton) {
            this.hide();
        }
        return true;
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
}
