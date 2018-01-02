import java.awt.Event;
import java.awt.Component;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class zzzx extends Frame
{
    public zzzx(final String s, final String s2) {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final zzzx zzzx = new zzzx(s);
        zzzx.setLayout(layout);
        final TextArea textArea = new TextArea(s2);
        textArea.resize(250, 250);
        final Button button = new Button("OK, Close This Window");
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(textArea, gridBagConstraints);
        zzzx.add(textArea);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(button, gridBagConstraints);
        zzzx.add(button);
        zzzx.pack();
        zzzx.show();
    }
    
    public zzzx(final String s) {
        super(s);
    }
    
    public boolean action(final Event event, final Object o) {
        this.hide();
        this.dispose();
        return true;
    }
}
