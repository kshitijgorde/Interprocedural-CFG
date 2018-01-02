// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.view.message;

public class InfoView extends MessageView
{
    private static final long H = 8268631162146549122L;
    public static final String PREFIX = "infoView";
    
    public InfoView() {
        this.D();
    }
    
    public InfoView(final String s) {
        super(s);
        this.D();
    }
    
    private void D() {
        this.B.injectProperties(this, null, "infoView");
        this.B.injectProperties(this.A, "infoView", "lblIcon");
        this.B.injectProperties(this.D, "infoView", "lblMessage");
        this.B.injectProperties(this.C, "infoView", "lblOk");
    }
}
