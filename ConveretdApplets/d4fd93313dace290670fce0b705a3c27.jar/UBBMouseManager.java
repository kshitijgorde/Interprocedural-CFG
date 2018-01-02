import java.util.Vector;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

// 
// Decompiled by Procyon v0.5.30
// 

public class UBBMouseManager implements MouseListener, MouseMotionListener
{
    protected UBB owner;
    protected Rectangle[] mouseArea;
    protected UBBComponent[] mouseListener;
    protected boolean[] mouseEntered;
    protected boolean[] mouseInComponent;
    protected boolean[] componentWantsMoves;
    protected boolean[] componentHidden;
    protected UBBErrorHandler error;
    protected Point lastPoint;
    protected String[][] enter;
    protected String[][] exit;
    public static final int MOVE = 0;
    public static final int ENTER = 1;
    public static final int EXIT = 2;
    public static final int PRESSED = 4;
    public static final int RELEASED = 8;
    public static final int CLICK = 16;
    public static final int RELEASE_CLEAR = 32;
    public static final int MODIFIER_MASK = 127;
    public static final int MODIFIER_NONE = 0;
    public static final int MODIFIER_NO_CLICK = 128;
    
    public void queryHidden() {
        if (this.mouseArea != null) {
            for (int i = 0; i < this.mouseListener.length; ++i) {
                this.componentHidden[i] = this.mouseListener[i].isHidden();
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.sendMouseActionEvent(mouseEvent, 16);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.sendMouseActionEvent(mouseEvent, 4);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.mouseMoved(mouseEvent);
    }
    
    public UBBMouseManager(final UBB owner, final UBBErrorHandler error) {
        this.lastPoint = new Point(0, 0);
        this.enter = new String[][] { { "enter", "-" } };
        this.exit = new String[][] { { "exit", "-" } };
        this.error = error;
        this.owner = owner;
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.sendMouseActionEvent(mouseEvent, 8);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (point.equals(this.lastPoint)) {
            return;
        }
        this.lastPoint = point;
        if (this.mouseArea != null && point != null) {
            final int length = this.mouseArea.length;
            for (int i = 0; i < length; ++i) {
                if (!this.componentHidden[i]) {
                    if (this.mouseArea[i].contains(point)) {
                        this.mouseEntered[i] = true;
                        if (this.componentWantsMoves[i]) {
                            this.mouseListener[i].mouseEvent(0, point);
                        }
                    }
                    else {
                        this.mouseEntered[i] = false;
                        if (this.mouseInComponent[i]) {
                            this.mouseInComponent[i] = false;
                            this.mouseListener[i].mouseEvent(2, point);
                        }
                    }
                }
            }
            for (int j = 0; j < length; ++j) {
                if (!this.componentHidden[j] && this.mouseEntered[j] && !this.mouseInComponent[j]) {
                    this.mouseInComponent[j] = true;
                    try {
                        this.mouseListener[j].mouseEvent(1, point);
                    }
                    catch (Exception ex) {
                        this.error.notify("UBBMouse", 0, this.mouseListener[j].getName() + " mouseMove", ex);
                    }
                }
            }
        }
    }
    
    public void destroy() {
        this.mouseArea = null;
        this.mouseListener = null;
        this.mouseEntered = null;
        this.mouseInComponent = null;
        this.componentWantsMoves = null;
        this.componentHidden = null;
    }
    
    public void sendMouseActionEvent(final MouseEvent mouseEvent, int n) {
        if (this.mouseArea != null) {
            final Point point = mouseEvent.getPoint();
            final boolean b = n == 8;
            for (int i = 0; i < this.mouseArea.length; ++i) {
                if (!this.componentHidden[i] && this.mouseArea[i].contains(point)) {
                    n |= this.mouseListener[i].mouseEvent(n, point);
                }
                if (b) {
                    this.mouseListener[i].mouseEvent(32, point);
                }
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.owner.ubbEvent(null, this.enter);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.owner.ubbEvent(null, this.exit);
        if (this.mouseArea != null && this.componentHidden != null) {
            try {
                for (int i = 0; i < this.mouseArea.length; ++i) {
                    if (!this.componentHidden[i] && this.mouseInComponent[i]) {
                        this.mouseInComponent[i] = false;
                        this.mouseListener[i].mouseEvent(2, mouseEvent.getPoint());
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void start(final Vector vector) {
        int n = 0;
        if (vector != null) {
            n = vector.size() / 3;
            this.mouseArea = new Rectangle[n];
            this.mouseListener = new UBBComponent[n];
            this.mouseEntered = new boolean[n];
            this.mouseInComponent = new boolean[n];
            this.componentWantsMoves = new boolean[n];
            this.componentHidden = new boolean[n];
        }
        if (this.mouseListener == null || this.mouseEntered == null || this.mouseInComponent == null || this.componentWantsMoves == null || this.componentHidden == null || vector == null) {
            this.mouseArea = null;
            this.error.notify("UBBMouse", 0, "Init UBBMouseManager", null);
            return;
        }
        for (int i = 0; i < n; ++i) {
            this.mouseListener[i] = vector.elementAt(i * 3);
            final Point point = (Point)vector.elementAt(i * 3 + 1);
            this.componentWantsMoves[i] = (boolean)vector.elementAt(i * 3 + 2);
            final Rectangle bounds = this.mouseListener[i].getBounds();
            this.mouseArea[i] = new Rectangle(point.x, point.y, bounds.width, bounds.height);
            this.mouseEntered[i] = (this.mouseInComponent[i] = false);
            this.componentHidden[i] = this.mouseListener[i].isHidden();
        }
    }
}
