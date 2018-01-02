// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.jdisplay;

import com.mindprod.jtokens.NL;
import com.mindprod.jtokens.Token;
import javax.swing.JTextArea;

public final class BWTokenPanel extends JTextArea
{
    static final long serialVersionUID = 1L;
    
    public void setTokens(final Token[] tokens) {
        final StringBuilder sb = new StringBuilder(tokens.length * 15);
        for (final Token t : tokens) {
            if (t instanceof NL) {
                for (int nls = Math.min(((NL)t).getCount(), 2), j = 0; j < nls; ++j) {
                    sb.append('\n');
                }
            }
            else {
                sb.append(t.getText());
            }
        }
        this.setText(sb.toString());
    }
}
