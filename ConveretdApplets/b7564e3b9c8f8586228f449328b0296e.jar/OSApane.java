import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class OSApane extends Canvas implements MouseListener
{
    public static final int L_PLANE = 0;
    public static final int jg_PLANE_1 = 1;
    public static final int jg_PLANE_2 = 2;
    public static final int Lg_PLANE_1 = 3;
    public static final int Lg_PLANE_2 = 4;
    public static final int Lj_PLANE_1 = 5;
    public static final int Lj_PLANE_2 = 6;
    private static final String[] planeDesc;
    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;
    public static final int Z_AXIS = 2;
    Dimension sizeRect;
    int L;
    int j;
    int g;
    boolean tilt;
    boolean tiltPrev;
    int[] OSAaxis;
    int[] OSAaxisPrev;
    boolean gotPivot;
    OSAUCS osa;
    int mode;
    int planeType;
    Image offScreen;
    private Vector paneListeners;
    
    static {
        planeDesc = new String[] { "L=", "g+j=", "g-j=", "L+g=", "L-g=", "L+j=", "L-j=" };
    }
    
    OSApane() {
        this.sizeRect = null;
        this.L = 0;
        this.j = 0;
        this.g = 0;
        this.tilt = false;
        this.gotPivot = true;
        this.osa = null;
        this.offScreen = null;
        this.paneListeners = new Vector();
        this.setBackground(Color.black);
        this.OSAaxis = new int[3];
        this.OSAaxisPrev = new int[3];
        this.OSAaxis[2] = 1;
        this.OSAaxis[0] = 2;
        this.OSAaxis[1] = 3;
        this.ageAxes();
        this.resetPlaneType();
        this.addMouseListener(this);
        this.osa = new OSAUCS();
    }
    
    public void addOSApaneListener(final OSApaneListener osApaneListener) {
        this.paneListeners.addElement(osApaneListener);
    }
    
    public void ageAxes() {
        this.OSAaxisPrev[0] = this.OSAaxis[0];
        this.OSAaxisPrev[1] = this.OSAaxis[1];
        this.OSAaxisPrev[2] = this.OSAaxis[2];
        this.tiltPrev = this.tilt;
    }
    
    public boolean canLower() {
        switch (this.planeType) {
            case 0: {
                return this.L > -7;
            }
            case 1: {
                return this.g + this.j > -10;
            }
            case 2: {
                return this.g - this.j > -16;
            }
            case 3: {
                return this.L + this.g > -12;
            }
            case 4: {
                return this.L - this.g > -8;
            }
            case 5: {
                return this.L + this.j > -10;
            }
            case 6: {
                return this.L - this.j > -8;
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean canRaise() {
        switch (this.planeType) {
            case 0: {
                return this.L < 5;
            }
            case 1: {
                return this.g + this.j < 12;
            }
            case 2: {
                return this.g - this.j < 8;
            }
            case 3: {
                return this.L + this.g < 8;
            }
            case 4: {
                return this.L - this.g < 10;
            }
            case 5: {
                return this.L + this.j < 16;
            }
            case 6: {
                return this.L - this.j < 6;
            }
            default: {
                return false;
            }
        }
    }
    
    public void decrease() {
        if (!this.canLower()) {
            return;
        }
        final boolean gotPivot = this.gotPivot;
        this.gotPivot = false;
        if (gotPivot) {
            this.repaintCursor();
        }
        switch (this.planeType) {
            case 0: {
                --this.L;
                break;
            }
            case 1:
            case 2: {
                this.g -= 2;
                break;
            }
            default: {
                this.L -= 2;
                break;
            }
        }
        this.repaint();
        this.notifyOSApaneListener(this.generateEvent());
    }
    
    public void drawCursor(final Graphics graphics) {
        Point point;
        if (this.tilt) {
            point = this.getHexPlanePoint(this.L, this.j, this.g, this.OSAaxis);
        }
        else {
            point = this.getSquarePlanePoint(this.L, this.j, this.g, this.OSAaxis);
        }
        graphics.drawLine(point.x - 11, point.y - 11, point.x - 11, point.y - 8);
        graphics.drawLine(point.x - 11, point.y - 11, point.x - 8, point.y - 11);
        graphics.drawLine(point.x + 10, point.y - 11, point.x + 10, point.y - 8);
        graphics.drawLine(point.x + 7, point.y - 11, point.x + 10, point.y - 11);
        graphics.drawLine(point.x + 10, point.y + 7, point.x + 10, point.y + 10);
        graphics.drawLine(point.x + 7, point.y + 10, point.x + 10, point.y + 10);
        graphics.drawLine(point.x - 11, point.y + 10, point.x - 8, point.y + 10);
        graphics.drawLine(point.x - 11, point.y + 7, point.x - 11, point.y + 10);
    }
    
    public void drawHexPlane(final int n, final int n2, final int n3, final int[] array, final Graphics graphics) {
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        for (int i = -12; i <= 12; ++i) {
            for (int j = -12; j <= 12; ++j) {
                if (this.planeType == 3) {
                    final int n7 = n + n3;
                    n4 = i;
                    n5 = j;
                    n6 = n7 - i;
                }
                else if (this.planeType == 4) {
                    final int n8 = n - n3;
                    n4 = i;
                    n5 = j;
                    n6 = i - n8;
                }
                else if (this.planeType == 5) {
                    final int n9 = n + n2;
                    n4 = i;
                    n5 = n9 - i;
                    n6 = j;
                }
                else if (this.planeType == 6) {
                    final int n10 = n - n2;
                    n4 = i;
                    n5 = i - n10;
                    n6 = j;
                }
                if (OSAUCS.inOSA(n4, n5, n6)) {
                    graphics.setColor(this.osa.getColor(n4, n5, n6));
                    this.drawHexPlaneRect(n4, n5, n6, array, graphics);
                }
            }
        }
    }
    
    void drawHexPlaneRect(final int n, final int n2, final int n3, final int[] array, final Graphics graphics) {
        final Point hexPlanePoint = this.getHexPlanePoint(n, n2, n3, array);
        graphics.fillRect(hexPlanePoint.x - 8, hexPlanePoint.y - 8, 16, 16);
    }
    
    public void drawPlane(final Graphics graphics) {
        if (this.tilt) {
            this.drawHexPlane(this.L, this.j, this.g, this.OSAaxis, graphics);
        }
        else {
            this.drawSquarePlane(this.L, this.j, this.g, this.OSAaxis, graphics);
        }
    }
    
    public void drawSquarePlane(final int n, final int n2, final int n3, final int[] array, final Graphics graphics) {
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        for (int i = -12; i <= 12; ++i) {
            for (int j = -12; j <= 12; ++j) {
                if (this.planeType == 0) {
                    n4 = n;
                    n5 = i;
                    n6 = j;
                }
                else if (this.planeType == 1) {
                    final int n7 = n3 + n2;
                    n4 = i;
                    n5 = j;
                    n6 = n7 - j;
                }
                else if (this.planeType == 2) {
                    final int n8 = n3 - n2;
                    n4 = i;
                    n5 = j;
                    n6 = n8 + j;
                }
                if (OSAUCS.inOSA(n4, n5, n6)) {
                    graphics.setColor(this.osa.getColor(n4, n5, n6));
                    this.drawSquarePlaneRect(n4, n5, n6, array, graphics);
                }
            }
        }
    }
    
    void drawSquarePlaneRect(final int n, final int n2, final int n3, final int[] array, final Graphics graphics) {
        final Point squarePlanePoint = this.getSquarePlanePoint(n, n2, n3, array);
        if (graphics.getClipBounds().intersects(new Rectangle(squarePlanePoint.x - 8, squarePlanePoint.y - 8, 16, 16))) {
            graphics.fillRect(squarePlanePoint.x - 8, squarePlanePoint.y - 8, 16, 16);
        }
    }
    
    private OSApaneEvent generateEvent() {
        return new OSApaneEvent(this, this.L, this.j, this.g, this.gotPivot);
    }
    
    Point getHexPlanePoint(final int n, final int n2, final int n3, final int[] array) {
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        if (array[0] == 1) {
            n4 = n + n;
        }
        else if (array[0] == -1) {
            n4 = -(n + n);
        }
        else if (array[0] == 2) {
            n4 = n3 + n2;
        }
        else if (array[0] == -2) {
            n4 = -(n3 + n2);
        }
        else if (array[0] == 3) {
            n4 = n3 - n2;
        }
        else if (array[0] == -3) {
            n4 = -(n3 - n2);
        }
        if (array[1] == 1) {
            n5 = n + n;
        }
        else if (array[1] == -1) {
            n5 = -(n + n);
        }
        else if (array[1] == 2) {
            n5 = n3 + n2;
        }
        else if (array[1] == -2) {
            n5 = -(n3 + n2);
        }
        else if (array[1] == 3) {
            n5 = n3 - n2;
        }
        else if (array[1] == -3) {
            n5 = -(n3 - n2);
        }
        if (array[2] == 1) {
            n6 = n + n;
        }
        else if (array[2] == -1) {
            n6 = -(n + n);
        }
        else if (array[2] == 2) {
            n6 = n3 + n2;
        }
        else if (array[2] == -2) {
            n6 = -(n3 + n2);
        }
        else if (array[2] == 3) {
            n6 = n3 - n2;
        }
        else if (array[2] == -3) {
            n6 = -(n3 - n2);
        }
        final int n7 = n4 - n5;
        final int n8 = n4 + n5 - (n6 + n6);
        final int n9 = n7 / 2;
        int n10;
        if (n8 < 0) {
            n10 = -((2 - n8) / 3);
        }
        else {
            n10 = n8 / 3;
        }
        return new Point(12 * (17 + n9) + 1, 12 * (17 - n10) + 1);
    }
    
    Rectangle getHexPlaneRect(final int n, final int n2, final int n3, final int[] array) {
        final Point hexPlanePoint = this.getHexPlanePoint(n, n2, n3, array);
        return new Rectangle(hexPlanePoint.x - 8, hexPlanePoint.y - 8, 16, 16);
    }
    
    public String getPlaneDesc() {
        String s = OSApane.planeDesc[this.planeType];
        switch (this.planeType) {
            case 0: {
                s = String.valueOf(s) + Integer.toString(this.L);
                break;
            }
            case 1: {
                s = String.valueOf(s) + Integer.toString(this.g + this.j);
                break;
            }
            case 2: {
                s = String.valueOf(s) + Integer.toString(this.g - this.j);
                break;
            }
            case 3: {
                s = String.valueOf(s) + Integer.toString(this.L + this.g);
                break;
            }
            case 4: {
                s = String.valueOf(s) + Integer.toString(this.L - this.g);
                break;
            }
            case 5: {
                s = String.valueOf(s) + Integer.toString(this.L + this.j);
                break;
            }
            case 6: {
                s = String.valueOf(s) + Integer.toString(this.L - this.j);
                break;
            }
        }
        return s;
    }
    
    public Dimension getPreferredSize() {
        if (this.sizeRect == null) {
            this.sizeRect = new Dimension(408, 408);
        }
        return this.sizeRect;
    }
    
    Point getSquarePlanePoint(final int n, final int n2, final int n3, final int[] array) {
        int n4 = 0;
        int n5 = 0;
        if (array[0] == 1) {
            n4 = n + n;
        }
        else if (array[0] == -1) {
            n4 = -(n + n);
        }
        else if (array[0] == 2) {
            n4 = n3 + n2;
        }
        else if (array[0] == -2) {
            n4 = -(n3 + n2);
        }
        else if (array[0] == 3) {
            n4 = n3 - n2;
        }
        else if (array[0] == -3) {
            n4 = -(n3 - n2);
        }
        if (array[1] == 1) {
            n5 = n + n;
        }
        else if (array[1] == -1) {
            n5 = -(n + n);
        }
        else if (array[1] == 2) {
            n5 = n3 + n2;
        }
        else if (array[1] == -2) {
            n5 = -(n3 + n2);
        }
        else if (array[1] == 3) {
            n5 = n3 - n2;
        }
        else if (array[1] == -3) {
            n5 = -(n3 - n2);
        }
        return new Point(12 * (17 + (n4 - n5) / 2) + 1, 12 * (17 - (n4 + n5) / 2) + 1);
    }
    
    Rectangle getSquarePlaneRect(final int n, final int n2, final int n3, final int[] array) {
        final Point squarePlanePoint = this.getSquarePlanePoint(n, n2, n3, array);
        return new Rectangle(squarePlanePoint.x - 8, squarePlanePoint.y - 8, 16, 16);
    }
    
    public boolean getTilt() {
        return this.tilt;
    }
    
    public void increase() {
        if (!this.canRaise()) {
            return;
        }
        final boolean gotPivot = this.gotPivot;
        this.gotPivot = false;
        if (gotPivot) {
            this.repaintCursor();
        }
        switch (this.planeType) {
            case 0: {
                ++this.L;
                break;
            }
            case 1:
            case 2: {
                this.g += 2;
                break;
            }
            default: {
                this.L += 2;
                break;
            }
        }
        this.repaint();
        this.notifyOSApaneListener(this.generateEvent());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.setPivot(mouseEvent.getPoint());
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    private void notifyOSApaneListener(final OSApaneEvent osApaneEvent) {
        for (int i = 0; i < this.paneListeners.size(); ++i) {
            ((OSApaneListener)this.paneListeners.elementAt(i)).paneNavigated(osApaneEvent);
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 408, 408);
        this.drawPlane(graphics);
        if (OSAUCS.inOSA(this.L, this.j, this.g) && this.gotPivot) {
            graphics.setColor(Color.white);
            this.drawCursor(graphics);
        }
        graphics.setColor(Color.white);
        if (this.tilt) {
            graphics.drawString("x", 398, 10);
            graphics.drawString("y", 10, 10);
            graphics.drawString("z", 199, 400);
        }
        else {
            graphics.drawString("x", 398, 10);
            graphics.drawString("y", 10, 10);
        }
    }
    
    public void removeOSApaneListener(final OSApaneListener osApaneListener) {
        this.paneListeners.removeElement(osApaneListener);
    }
    
    public void repaintCursor() {
        Point point;
        if (this.tilt) {
            point = this.getHexPlanePoint(this.L, this.j, this.g, this.OSAaxis);
        }
        else {
            point = this.getSquarePlanePoint(this.L, this.j, this.g, this.OSAaxis);
        }
        this.repaint(point.x - 11, point.y - 11, 22, 22);
    }
    
    private void resetPlaneType() {
        if (this.tilt) {
            int n = 0;
            for (int i = 0; i <= 2; ++i) {
                switch (this.OSAaxis[i]) {
                    case -1: {
                        n += 4;
                        break;
                    }
                    case -2: {
                        n += 2;
                        break;
                    }
                    case -3: {
                        ++n;
                        break;
                    }
                }
            }
            switch (n) {
                case 0:
                case 7: {
                    this.planeType = 3;
                    break;
                }
                case 3:
                case 4: {
                    this.planeType = 4;
                    break;
                }
                case 1:
                case 6: {
                    this.planeType = 5;
                    break;
                }
                case 2:
                case 5: {
                    this.planeType = 6;
                    break;
                }
            }
        }
        else {
            switch (this.OSAaxis[2]) {
                case -1:
                case 1: {
                    this.planeType = 0;
                    break;
                }
                case -2:
                case 2: {
                    this.planeType = 1;
                    break;
                }
                case -3:
                case 3: {
                    this.planeType = 2;
                    break;
                }
            }
        }
    }
    
    public void rotate90(final int n) {
        if (!this.gotPivot) {
            return;
        }
        if (n < 0 || n > 2) {
            return;
        }
        this.ageAxes();
        final int n2 = (n + 1) % 3;
        final int n3 = (n + 2) % 3;
        final int n4 = this.OSAaxis[n2];
        this.OSAaxis[n2] = this.OSAaxis[n3];
        this.OSAaxis[n3] = -n4;
        this.resetPlaneType();
        this.repaint();
        this.notifyOSApaneListener(this.generateEvent());
    }
    
    public void setHexPivot(final Point point) {
        int l = 0;
        int j = 0;
        int g = 0;
        int n = 0;
        for (int i = 0; i <= 2; ++i) {
            final int n2 = this.OSAaxis[i];
            if (n2 == -1) {
                n += 4;
            }
            else if (n2 == -2) {
                n += 2;
            }
            else if (n2 == -3) {
                ++n;
            }
        }
        for (int k = -12; k <= 12; ++k) {
            for (int n3 = -12; n3 <= 12; ++n3) {
                if (n == 0 || n == 7) {
                    final int n4 = this.L + this.g;
                    l = k;
                    j = n3;
                    g = n4 - k;
                }
                else if (n == 3 || n == 4) {
                    final int n5 = this.L - this.g;
                    l = k;
                    j = n3;
                    g = k - n5;
                }
                else if (n == 1 || n == 6) {
                    final int n6 = this.L + this.j;
                    l = k;
                    j = n6 - k;
                    g = n3;
                }
                else if (n == 2 || n == 5) {
                    final int n7 = this.L - this.j;
                    l = k;
                    j = k - n7;
                    g = n3;
                }
                if (OSAUCS.inOSA(l, j, g) && this.getHexPlaneRect(l, j, g, this.OSAaxis).contains(point)) {
                    if (this.gotPivot) {
                        this.repaintCursor();
                    }
                    this.L = l;
                    this.j = j;
                    this.g = g;
                    this.gotPivot = true;
                    this.repaintCursor();
                }
            }
        }
    }
    
    public void setPivot(final Point point) {
        if (this.tilt) {
            this.setHexPivot(point);
        }
        else {
            this.setSquarePivot(point);
        }
        if (this.gotPivot) {
            this.notifyOSApaneListener(this.generateEvent());
        }
    }
    
    public void setSize(final Dimension dimension) {
    }
    
    public void setSquarePivot(final Point point) {
        boolean b = true;
        int l = 0;
        int j = 0;
        int g = 0;
        final int n = this.OSAaxis[2];
        for (int n2 = -12; n2 <= 12 && b; ++n2) {
            for (int n3 = -12; n3 <= 12 && b; ++n3) {
                if (n == 1 || n == -1) {
                    l = this.L;
                    j = n2;
                    g = n3;
                }
                else if (n == 2 || n == -2) {
                    final int n4 = this.g + this.j;
                    l = n2;
                    j = n3;
                    g = n4 - n3;
                }
                else if (n == 3 || n == -3) {
                    final int n5 = this.g - this.j;
                    l = n2;
                    j = n3;
                    g = n5 + n3;
                }
                if (OSAUCS.inOSA(l, j, g) && this.getSquarePlaneRect(l, j, g, this.OSAaxis).contains(point)) {
                    b = false;
                    if (this.gotPivot) {
                        this.repaintCursor();
                    }
                    this.L = l;
                    this.j = j;
                    this.g = g;
                    this.gotPivot = true;
                    this.repaintCursor();
                }
            }
        }
    }
    
    public void setTilt(final boolean tilt) {
        if (this.gotPivot && this.tilt != tilt) {
            this.ageAxes();
            this.tilt = tilt;
            this.resetPlaneType();
            this.repaint();
            this.notifyOSApaneListener(this.generateEvent());
        }
    }
}
