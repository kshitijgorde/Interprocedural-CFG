import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class AboutBox extends JDialog
{
    final int ABOUT = -1;
    final int WHAT = -2;
    static boolean boxExists;
    static AboutBox thisBox;
    int type;
    
    public AboutBox(final Frame f, final String s) {
        super(f, s);
        AboutBox.boxExists = true;
        AboutBox.thisBox = this;
    }
    
    public void setType(final int mode) {
        this.type = mode;
    }
    
    public void dispose() {
        AboutBox.boxExists = false;
        AboutBox.thisBox = null;
        super.dispose();
    }
}
