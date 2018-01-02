// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.swing.view.message;

public class ErrorView extends MessageView
{
    private static final long F = 8268631162146549122L;
    public static final String PREFIX = "errorView";
    
    public ErrorView() {
        this.B();
    }
    
    public ErrorView(final String s) {
        super(s);
        this.B();
    }
    
    private void B() {
        this.B.injectProperties(this, null, "errorView");
        this.B.injectProperties(this.A, "errorView", "lblIcon");
        this.B.injectProperties(this.D, "errorView", "lblMessage");
        this.B.injectProperties(this.C, "errorView", "lblOk");
    }
}
