import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.event.InternalFrameListener;
import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.awt.MenuComponent;
import java.awt.Component;
import java.awt.ActiveEvent;
import javax.swing.SwingUtilities;
import javax.swing.Action;
import javax.swing.KeyStroke;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_bW extends rp_bm
{
    protected boolean b;
    
    public rp_bW(final String s) {
        super(s, true);
        this.b = false;
        this.putClientProperty("JInternalFrame.frameType", "optionDialog");
        rp_au.a().a(this);
        this.getInputMap(1).put(KeyStroke.getKeyStroke(10, 0), "ENTERPRESSED");
        this.getInputMap(2).put(KeyStroke.getKeyStroke(27, 0), "ESCAPEPRESSED");
        this.getActionMap().put("ENTERPRESSED", new rp_af(this));
        this.getActionMap().put("ESCAPEPRESSED", new rp_P(this));
    }
    
    public void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            this.c();
            return;
        }
        this.d();
    }
    
    private synchronized void c() {
        try {
            if (!SwingUtilities.isEventDispatchThread()) {
                while (this.isVisible()) {
                    this.wait();
                }
                return;
            }
            final EventQueue systemEventQueue = this.getToolkit().getSystemEventQueue();
            while (this.isVisible()) {
                final AWTEvent nextEvent;
                final Object source = (nextEvent = systemEventQueue.getNextEvent()).getSource();
                if (nextEvent instanceof ActiveEvent) {
                    ((ActiveEvent)nextEvent).dispatch();
                }
                else if (source instanceof Component) {
                    ((Component)source).dispatchEvent(nextEvent);
                }
                else if (source instanceof MenuComponent) {
                    ((MenuComponent)source).dispatchEvent(nextEvent);
                }
                else {
                    System.err.println("Unable to dispatch: " + nextEvent);
                }
            }
        }
        catch (InterruptedException ex) {}
    }
    
    private synchronized void d() {
        this.notifyAll();
    }
    
    public void dispose() {
        try {
            this.setClosed(true);
        }
        catch (Throwable t) {
            System.err.println("Failed to close dlg due to: " + t.getMessage());
        }
        this.setVisible(false);
        super.dispose();
    }
    
    public void a() {
        this.addInternalFrameListener(new rp_O(this));
    }
    
    public final void b() {
        final Dimension size = this.getSize();
        final Dimension size2 = rp_au.a().getSize();
        this.setLocation((size2.width - size.width) / 2, (size2.height - size.height) / 2);
    }
    
    public void a(final JPanel panel) {
    }
    
    protected boolean b() {
        return this.a();
    }
    
    protected boolean c() {
        return true;
    }
    
    public boolean a() {
        return this.b = true;
    }
    
    public final boolean d() {
        return this.b;
    }
}
