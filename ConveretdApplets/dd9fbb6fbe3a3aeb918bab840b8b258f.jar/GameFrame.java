import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class GameFrame extends Frame
{
    public GameFrame(final Applet app, final int width, final int height) {
        super("IQ Tester !! -- By Jason Wang --");
        this.addNotify();
        this.setLayout(new BorderLayout(10, 10));
        this.add("center", app);
        final Insets insets = this.insets();
        this.resize(insets.left + insets.right + width, insets.top + insets.bottom + height);
        this.setResizable(false);
        this.setCursor(12);
        this.show();
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return false;
    }
}
