import java.security.AccessControlException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public class WCaret implements PaintListener
{
    private Component component;
    private volatile WCaret$CaretTimer timerThread;
    private boolean visible;
    private Rectangle caret;
    private int reflection;
    
    public WCaret(final Component component) {
        this.timerThread = null;
        if (component == null) {
            throw new NullPointerException();
        }
        this.caret = new Rectangle();
        this.component = component;
        this.visible = false;
    }
    
    public final void setVisible(final boolean visible) {
        if (visible && this.timerThread == null) {
            this.start();
        }
        this.visible = visible;
        this.resetTimer();
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        final Rectangle caret = this.caret;
        final Rectangle caret2 = new Rectangle(n, n2, n3, n4);
        if (!caret.equals(caret2)) {
            this.caret = caret2;
            this.resetTimer();
            this.repaint(caret.x, caret.y, caret.width, caret.height);
        }
    }
    
    public final Rectangle getBounds() {
        return new Rectangle(this.caret);
    }
    
    public final void onPaint(final Object o, final Graphics graphics, final boolean b) {
        if (o == this.component && this.visible && !b) {
            graphics.setColor(Color.black);
            final WCaret$CaretTimer timerThread = this.timerThread;
            if (timerThread != null && timerThread.getPaintState()) {
                final Rectangle caret = this.caret;
                graphics.setXORMode(Color.white);
                graphics.fillRect(caret.x, caret.y, caret.width, caret.height);
                graphics.setPaintMode();
            }
        }
    }
    
    public final void start() {
        if (this.timerThread == null) {
            try {
                (this.timerThread = new WCaret$CaretTimer(this)).start();
            }
            catch (AccessControlException ex) {
                ex.getMessage();
            }
        }
    }
    
    public final void stop() {
        this.timerThread = null;
        final Rectangle caret = this.caret;
        this.repaint(caret.x, caret.y, caret.width, caret.height);
    }
    
    private void resetTimer() {
        final WCaret$CaretTimer timerThread = this.timerThread;
        if (timerThread != null) {
            synchronized (timerThread) {
                WCaret$CaretTimer.I(timerThread, true);
                WCaret$CaretTimer.Z(timerThread, true);
                timerThread.notify();
            }
        }
    }
    
    public final void repaint(final int n, final int n2, final int n3, final int n4) {
        if (this.reflection == 0) {
            this.component.repaint(n, n2, n3, n4);
        }
        else {
            this.component.repaint(this.reflection - n3 - n, n2, n3, n4);
        }
    }
    
    public final void setReflection(final int reflection) {
        this.reflection = reflection;
    }
    
    static final WCaret$CaretTimer I(final WCaret wCaret) {
        return wCaret.timerThread;
    }
    
    static final Rectangle Z(final WCaret wCaret) {
        return wCaret.caret;
    }
}
