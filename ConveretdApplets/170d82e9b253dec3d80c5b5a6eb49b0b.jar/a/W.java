// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Frame;
import java.awt.TextField;

public final class W extends Z
{
    private TextField q;
    
    public W(final Frame frame, final String s, final boolean b, final aa aa) {
        super(frame, s, false, aa);
        this.q = new TextField(20);
        this.q(eb.q("Image file name:"), this.q);
        this.pack();
    }
    
    protected final String q() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<img src='").append(cV.a).append("/scroll/").append(this.q.getText()).append("'>");
        return sb.toString();
    }
    
    protected final boolean q() {
        return true;
    }
}
