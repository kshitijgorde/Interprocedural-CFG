import java.awt.Event;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class alert extends Dialog
{
    Button okButton;
    boolean tf;
    
    public alert(final String text) {
        super(new Frame(), "Message", true);
        this.tf = false;
        this.setBackground(new Color(50, 255, 255));
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weighty = 2.0;
        gbc.gridy = 0;
        final Label L1 = new Label(String.valueOf(text) + "\n" + "Select this date.");
        gbl.setConstraints(L1, gbc);
        this.add(L1);
        gbc.gridy = 4;
        gbl.setConstraints(this.okButton = new Button("close"), gbc);
        this.add(this.okButton);
        this.setLocation(250, 200);
    }
    
    public boolean action(final Event evt, final Object whichAction) {
        if (evt.target instanceof Button) {
            if (evt.target == this.okButton) {
                this.hide();
            }
            this.tf = true;
        }
        return this.tf;
    }
    
    public boolean getValue() {
        return this.tf;
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
}
