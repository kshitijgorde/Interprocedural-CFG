// 
// Decompiled by Procyon v0.5.30
// 

package irc;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Frame;
import java.awt.event.WindowAdapter;

public class AboutDialog extends WindowAdapter
{
    private Frame _aboutFrame;
    
    public AboutDialog(final IRCConfiguration ircConfiguration) {
        this.displayAboutPage(ircConfiguration);
    }
    
    private Label createLabel(final String s) {
        final Label label = new Label(s, 1);
        label.setFont(new Font("", 0, 12));
        return label;
    }
    
    private void displayAboutPage(final IRCConfiguration ircConfiguration) {
        if (this._aboutFrame != null) {
            return;
        }
        (this._aboutFrame = new Frame()).setTitle(ircConfiguration.getText(1025));
        this._aboutFrame.setLayout(new BorderLayout());
        this._aboutFrame.setFont(new Font("", 0, 12));
        final Panel panel = new Panel();
        panel.setLayout(new GridLayout(20, 1));
        panel.add(this.createLabel("PJIRC v" + ircConfiguration.getVersion()));
        panel.add(new Panel());
        panel.add(this.createLabel(ircConfiguration.getText(1030)));
        panel.add(new Panel());
        panel.add(this.createLabel(ircConfiguration.getText(1026) + " : Philippe Detournay alias Plouf (theplouf@yahoo.com)"));
        panel.add(this.createLabel(ircConfiguration.getText(1027) + " : Raphael Seegmuller chez pixxservices.com (pixxservices@pixxservices.com)"));
        panel.add(new Panel());
        panel.add(this.createLabel(ircConfiguration.getText(1028)));
        panel.add(new Panel());
        panel.add(this.createLabel("Mandragor : www.mandragor.org"));
        panel.add(this.createLabel("Diboo : www.diboo.net"));
        panel.add(this.createLabel("Kombat Falcon.be Jerarckill Red Spider"));
        panel.add(this.createLabel("Ezequiel Jiquera"));
        panel.add(new Panel());
        panel.add(this.createLabel(ircConfiguration.getText(1029)));
        panel.add(new Panel());
        panel.add(this.createLabel(ircConfiguration.getGUIInfoString()));
        panel.add(new Panel());
        panel.add(this.createLabel("http://www.pjirc.com"));
        panel.add(this.createLabel("http://www.pjirc.it"));
        this._aboutFrame.addWindowListener(this);
        this._aboutFrame.add(panel, "Center");
        this._aboutFrame.setSize(500, 300);
        this._aboutFrame.setResizable(false);
        this._aboutFrame.show();
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        this._aboutFrame.removeWindowListener(this);
        this._aboutFrame = null;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this._aboutFrame.hide();
        this._aboutFrame.dispose();
    }
}
