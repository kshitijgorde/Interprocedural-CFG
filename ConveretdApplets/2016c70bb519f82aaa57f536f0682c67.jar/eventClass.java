import java.awt.event.MouseEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

public class eventClass extends MouseAdapter implements FocusListener, MouseMotionListener
{
    boolean focus;
    boolean mou_down;
    boolean mou_click;
    boolean mou_enter;
    int xpos;
    int ypos;
    int xclick;
    int yclick;
    int xdown;
    int ydown;
    int YesNo;
    
    eventClass() {
        final boolean mou_down = false;
        this.mou_enter = mou_down;
        this.mou_click = mou_down;
        this.mou_down = mou_down;
        this.focus = true;
        this.YesNo = 0;
    }
    
    public void focusGained(final FocusEvent focusevent) {
        this.focus = true;
    }
    
    public void focusLost(final FocusEvent focusevent) {
        this.focus = false;
    }
    
    public boolean getMouseClick() {
        final boolean flag = this.mou_click;
        this.mou_click = false;
        return flag;
    }
    
    public void mouseClicked(final MouseEvent mouseevent) {
        this.mou_click = true;
        this.xclick = mouseevent.getX();
        this.yclick = mouseevent.getY();
    }
    
    public void mouseDragged(final MouseEvent mouseevent) {
        this.xpos = mouseevent.getX();
        this.ypos = mouseevent.getY();
    }
    
    public void mouseEntered(final MouseEvent mouseevent) {
        this.mou_enter = true;
    }
    
    public void mouseExited(final MouseEvent mouseevent) {
        this.mou_enter = false;
    }
    
    public void mouseMoved(final MouseEvent mouseevent) {
        this.xpos = mouseevent.getX();
        this.ypos = mouseevent.getY();
    }
    
    public void mousePressed(final MouseEvent mouseevent) {
        this.mou_down = true;
        this.xdown = mouseevent.getX();
        this.ydown = mouseevent.getY();
    }
    
    public void mouseReleased(final MouseEvent mouseevent) {
        this.mou_down = false;
    }
    
    public void mouseReset() {
        this.mou_down = false;
        this.mou_click = false;
        this.YesNo = 0;
    }
}
