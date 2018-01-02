// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components.JLimitedTextField;

import javax.swing.text.Document;
import javax.swing.JTextField;

public class JLimitedStringField extends JTextField
{
    private int nb;
    private boolean upper;
    
    public JLimitedStringField() {
        this.nb = 1;
        this.upper = false;
        this.setDocument(this.createDefaultModel());
    }
    
    public JLimitedStringField(final int n) {
        this.jbinit(n, false);
    }
    
    public JLimitedStringField(final int n, final boolean b) {
        this.jbinit(n, b);
    }
    
    @Override
    protected Document createDefaultModel() {
        return new LimitedStringCaseDocument(this.nb);
    }
    
    public String getString() {
        return super.getText().trim();
    }
    
    public void jbinit(final int nb, final boolean upper) {
        if (nb > 1) {
            this.nb = nb;
        }
        else {
            this.nb = 1;
        }
        this.upper = upper;
        this.setDocument(this.createDefaultModel());
    }
    
    public void setString(final String s) {
        if (s.trim().length() > this.nb) {
            super.setText(s.trim().substring(0, this.nb));
        }
        else {
            super.setText(s.trim());
        }
    }
}
