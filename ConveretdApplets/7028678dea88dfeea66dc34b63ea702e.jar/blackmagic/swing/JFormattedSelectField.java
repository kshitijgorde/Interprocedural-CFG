// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.swing;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFormattedTextField;

public class JFormattedSelectField extends JFormattedTextField
{
    private Color vNormalBackground;
    private Color vNormalForeground;
    
    public JFormattedSelectField(final AbstractFormatter abstractFormatter) {
        super(abstractFormatter);
        this.setDisabledTextColor(Color.BLACK);
        this.setFont(new Font("Monospaced", 0, 11));
        this.vNormalBackground = this.getBackground();
    }
    
    protected void processFocusEvent(final FocusEvent focusEvent) {
        super.processFocusEvent(focusEvent);
        if (focusEvent.getID() == 1004) {
            this.selectAll();
        }
    }
    
    public void postActionEvent() {
        super.postActionEvent();
        this.selectAll();
    }
    
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        if (!enabled) {
            this.setBackground(Color.LIGHT_GRAY);
        }
        else {
            this.setBackground(this.vNormalBackground);
        }
    }
    
    protected void invalidEdit() {
        super.invalidEdit();
        this.setBackground(Color.RED);
        this.setForeground(Color.WHITE);
        final Timer timer = new Timer(75, new ActionListener() {
            public void actionPerformed(final ActionEvent actionEvent) {
                JFormattedSelectField.this.setBackground(JFormattedSelectField.this.vNormalBackground);
                JFormattedSelectField.this.setForeground(JFormattedSelectField.this.vNormalForeground);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }
}
