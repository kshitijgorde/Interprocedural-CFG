import java.awt.TextArea;

// 
// Decompiled by Procyon v0.5.30
// 

public class Scroller extends TextArea
{
    static final String NEWLINE = "\r\n";
    
    Scroller(final String s, final int n, final int n2) {
        super(s, n, n2);
        this.setEditable(false);
    }
    
    public void newLine() {
        super.appendText("\r\n");
    }
}
