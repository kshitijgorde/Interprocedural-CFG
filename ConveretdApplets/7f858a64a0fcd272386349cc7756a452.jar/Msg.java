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

class Msg extends Frame
{
    public Msg(final String s, final String s2) {
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Msg msg = new Msg(s);
        msg.setLayout(layout);
        final TextArea textArea = new TextArea(s2);
        textArea.resize(500, 900);
        final Button button = new Button("CLOSE - BEENDEN");
        gridBagConstraints.fill = 1;
        gridBagConstraints.anchor = 11;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        layout.setConstraints(textArea, gridBagConstraints);
        msg.add(textArea);
        gridBagConstraints.fill = 2;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        layout.setConstraints(button, gridBagConstraints);
        msg.add(button);
        msg.pack();
        msg.show();
    }
    
    public Msg(final String s) {
        super(s);
    }
    
    public boolean action(final Event event, final Object o) {
        this.hide();
        this.dispose();
        return true;
    }
}
