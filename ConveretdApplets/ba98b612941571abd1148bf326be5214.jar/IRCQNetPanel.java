import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetPanel extends Panel
{
    public String Label;
    public IRCQNet theApp;
    
    public IRCQNetPanel(final IRCQNet theApp) {
        this.theApp = theApp;
    }
    
    public void setLabel(final String label) {
        this.Label = label;
    }
    
    public String getLabel() {
        return this.Label;
    }
    
    public void Print(final String s) {
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
    }
}
