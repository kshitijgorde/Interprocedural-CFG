// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.view.message;

public class WarningView extends MessageView
{
    private static final long G = 8268631162146549122L;
    public static final String PREFIX = "warningView";
    
    public WarningView() {
        this.C();
    }
    
    public WarningView(final String s) {
        super(s);
        this.C();
    }
    
    private void C() {
        this.B.injectProperties(this, null, "warningView");
        this.B.injectProperties(this.A, "warningView", "lblIcon");
        this.B.injectProperties(this.D, "warningView", "lblMessage");
        this.B.injectProperties(this.C, "warningView", "lblOk");
    }
}
