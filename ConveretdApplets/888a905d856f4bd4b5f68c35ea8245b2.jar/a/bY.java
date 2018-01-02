// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Frame;
import java.awt.TextField;

public final class bY extends cD
{
    private TextField q;
    
    public bY(final Frame frame, final String s, final boolean b, final dP dp) {
        super(frame, s, false, dp);
        this.q = new TextField(20);
        this.q(be.w("Image file name:"), this.q);
        this.pack();
    }
    
    protected final String q() {
        final StringBuffer sb = new StringBuffer();
        sb.append("<img src='").append(ap.f).append("/scroll/").append(this.q.getText()).append("'>");
        return sb.toString();
    }
    
    protected final boolean q() {
        return true;
    }
}
