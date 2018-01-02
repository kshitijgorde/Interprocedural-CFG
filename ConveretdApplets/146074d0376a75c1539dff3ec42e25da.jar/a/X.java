// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Frame;
import java.awt.TextField;

public final class X extends Z
{
    private TextField q;
    private TextField w;
    
    public X(final Frame frame, final String s, final boolean b, final aa aa) {
        super(frame, s, false, aa);
        this.q = new TextField(40);
        this.w = new TextField(40);
        this.q(eb.q("Link label:"), this.q);
        this.q(eb.q("Link url:"), this.w);
        this.pack();
    }
    
    protected final String q() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<a href='").append(this.w.getText()).append("' value='").append(this.q.getText()).append("' target='_blank'>");
        return sb.toString();
    }
    
    protected final boolean q() {
        return true;
    }
}
