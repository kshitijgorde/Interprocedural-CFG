// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Frame;
import java.awt.TextField;

public final class b extends cD
{
    private TextField q;
    private TextField w;
    
    public b(final Frame frame, final String s, final boolean b, final dP dp) {
        super(frame, s, false, dp);
        this.q = new TextField(40);
        this.w = new TextField(40);
        this.q(be.w("Link label:"), this.q);
        this.q(be.w("Link url:"), this.w);
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
