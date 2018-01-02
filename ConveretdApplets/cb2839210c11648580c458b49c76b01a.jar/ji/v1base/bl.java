// 
// Decompiled by Procyon v0.5.30
// 

package ji.v1base;

import java.awt.Container;
import java.awt.Frame;
import java.awt.Dialog;

public class bl extends Dialog
{
    public bl(final Frame frame, final String s, final boolean b) {
        super(frame, s, b);
    }
    
    public bl(final Dialog dialog, final String s, final boolean b) {
        super(dialog, s, b);
    }
    
    public Container d() {
        return this;
    }
    
    public boolean e() {
        return false;
    }
}
