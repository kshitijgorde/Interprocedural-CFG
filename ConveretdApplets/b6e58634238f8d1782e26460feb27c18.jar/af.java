import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public final class af extends Component implements MouseListener
{
    public d a;
    public q b;
    public int c;
    public int d;
    public boolean e;
    
    public af(final d a, final q b, final boolean e) {
        this.a = a;
        this.setCursor(Cursor.getDefaultCursor());
        this.addMouseListener(this);
        this.b = b;
        if (!(this.e = e)) {
            this.c = b.f().c().af;
        }
        else {
            final int ae = b.f().c().ae;
            this.c = ae;
            this.d = ae;
        }
        this.setBackground(this.a.i().f);
        this.a.d();
        this.setForeground(f.a(this.getBackground()));
    }
    
    public final boolean a() {
        return this.e;
    }
    
    public void a(final boolean b) {
        if (this.e) {
            if (b) {
                this.d = Math.max(this.d, this.c);
                this.c = 0;
            }
            else {
                this.c = this.d;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void a(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, size.width, size.height);
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        if (this.getParent() instanceof MouseListener) {
            ((MouseListener)this.getParent()).mousePressed(new MouseEvent(this.getParent(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), this.getBounds().x + mouseEvent.getX(), this.getBounds().y + mouseEvent.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.getParent() instanceof MouseListener) {
            ((MouseListener)this.getParent()).mouseReleased(new MouseEvent(this.getParent(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), this.getBounds().x + mouseEvent.getX(), this.getBounds().y + mouseEvent.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        if (this.getParent() instanceof MouseListener) {
            ((MouseListener)this.getParent()).mouseClicked(new MouseEvent(this.getParent(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), this.getBounds().x + mouseEvent.getX(), this.getBounds().y + mouseEvent.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
        }
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
}
