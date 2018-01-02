// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7.$ZAC;

import java.awt.Cursor;
import jlog.$T5.$D7.ContainerSupport;
import java.awt.AWTEvent;
import java.awt.Polygon;
import jlog.awt.$V_;
import java.awt.Color;
import jlog.$BI.$M4;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Component;
import jlog.$H4;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Container;

public class $RMD extends Container implements MouseListener, MouseMotionListener, $H4
{
    Component child;
    int $SMD;
    public int $TMD;
    private Point $UMD;
    private Point $VMD;
    private Rectangle $WMD;
    private $RMD $XMD;
    private int $YMD;
    private boolean $MU;
    private boolean $ZMD;
    private boolean $AND;
    
    int $BND(final Point point) {
        final Dimension size = this.getSize();
        int n = 0;
        if (point.x < this.$SMD) {
            n = 4;
        }
        else if (point.x > size.width - this.$SMD) {
            n = 8;
        }
        int n2 = 0;
        if (point.y < this.$SMD) {
            n2 = 1;
        }
        else if (point.y > size.height - this.$SMD) {
            n2 = 2;
        }
        if ((n == 0 && n2 == 0) || (n != 0 && n2 != 0)) {
            return n + n2;
        }
        if (n != 0) {
            if (point.y < this.$TMD) {
                n2 = 1;
            }
            else if (point.y > size.height - this.$TMD) {
                n2 = 2;
            }
        }
        else if (n2 != 0) {
            if (point.x < this.$TMD) {
                n = 4;
            }
            else if (point.x > size.width - this.$TMD) {
                n = 8;
            }
        }
        return n + n2;
    }
    
    public $RMD(final Component component, final int n) {
        this(component, n, false);
    }
    
    public $RMD(final Component component, final int $smd, final boolean $and) {
        this.$TMD = 8;
        this.$XMD = null;
        this.$YMD = 0;
        this.$MU = false;
        this.$ZMD = false;
        this.$AND = false;
        this.$SMD = $smd;
        if (!(this.$AND = $and)) {
            this.$XMD = new $RMD(null, $smd, true);
        }
        this.setComponent(component);
    }
    
    public boolean contains(final int n, final int n2) {
        if (!super.contains(n, n2)) {
            return false;
        }
        if (this.$BND(new Point(n, n2)) != 0) {
            return true;
        }
        final Point location = this.getLocation();
        final Container parent = this.getParent();
        return parent != null && this.getComponentAt(parent, n + location.x, n2 + location.y) == this.child;
    }
    
    static void fillRect(final Graphics graphics, final Rectangle rectangle) {
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    Component getComponentAt(final Container container, final int n, final int n2) {
        final Component[] components = container.getComponents();
        int i = 0;
        final Point point = new Point();
        while (i < components.length) {
            if (!(components[i] instanceof $RMD)) {
                final Point location = components[i].getLocation();
                if (components[i].contains(n - location.x, n2 - location.y)) {
                    return components[i];
                }
            }
            ++i;
        }
        return container;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public synchronized void mouseDragged(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        if (!this.$MU) {
            this.$MU = true;
            if (this.$BND(point) == 0) {
                this.setCursor(13);
            }
            this.$XMD.setComponent(this.child, false);
            this.setVisible(false);
            this.$VMD = new Point(point);
            this.$UMD = new Point(point);
            this.$WMD = this.getBounds();
            this.$YMD = this.$BND(this.$UMD);
        }
        final int n = point.x - this.$VMD.x;
        final int n2 = point.y - this.$VMD.y;
        if (n == 0 && n2 == 0) {
            return;
        }
        if (this.$YMD == 0) {
            final Point location = this.$XMD.getLocation();
            location.translate(n, n2);
            this.$XMD.setLocation(location);
            final Point location2 = this.child.getLocation();
            location2.translate(n, n2);
            this.child.setLocation(location2);
        }
        else {
            int n3 = 0;
            int n4 = 0;
            int n5 = 0;
            int n6 = 0;
            switch (this.$YMD) {
                case 1: {
                    n5 = -n2;
                    break;
                }
                case 2: {
                    n6 = n2;
                    break;
                }
                case 4: {
                    n3 = -n;
                    break;
                }
                case 5: {
                    n5 = -n2;
                    n3 = -n;
                    break;
                }
                case 6: {
                    n6 = n2;
                    n3 = -n;
                    break;
                }
                case 8: {
                    n4 = n;
                    break;
                }
                case 9: {
                    n5 = -n2;
                    n4 = n;
                    break;
                }
                case 10: {
                    n6 = n2;
                    n4 = n;
                    break;
                }
            }
            if (mouseEvent.isControlDown()) {
                n5 = (n6 = ((n5 != 0) ? n5 : n6));
                n3 = (n4 = ((n3 != 0) ? n3 : n4));
            }
            if (n3 + n4 + n5 + n6 != 0) {
                final Rectangle bounds;
                final Rectangle rectangle = bounds = this.$XMD.getBounds();
                bounds.width += n4 + n3;
                final Rectangle rectangle2 = rectangle;
                rectangle2.height += n6 + n5;
                final Rectangle rectangle3 = rectangle;
                rectangle3.x -= n3;
                final Rectangle rectangle4 = rectangle;
                rectangle4.y -= n5;
                if (rectangle.width > 2 * this.$SMD && rectangle.height > 2 * this.$SMD) {
                    this.$XMD.setBounds(rectangle);
                    rectangle.grow(-this.$SMD, -this.$SMD);
                    this.child.setBounds(rectangle);
                }
            }
        }
        this.$VMD = point;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (this.$MU) {
            return;
        }
        this.setCursor(0);
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.$MU) {
            return;
        }
        this.setCursor((new int[] { 0, 8, 9, 0, 10, 6, 4, 0, 11, 7, 5 })[this.$BND(mouseEvent.getPoint())]);
    }
    
    public synchronized void mousePressed(final MouseEvent mouseEvent) {
        this.$MU = false;
    }
    
    public synchronized void mouseReleased(final MouseEvent mouseEvent) {
        try {
            this.$ZMD = false;
            this.setCursor(0);
            if (!this.$MU) {
                return;
            }
            this.$MU = false;
            final Rectangle bounds = this.$XMD.getBounds();
            this.$XMD.setComponent(null);
            final Point point = mouseEvent.getPoint();
            final int n = point.x - this.$UMD.x;
            final int n2 = point.y - this.$UMD.y;
            if (n == 0 && n2 == 0) {
                return;
            }
            this.setBounds(bounds);
            bounds.grow(-this.$SMD, -this.$SMD);
            this.child.setBounds(bounds);
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
        finally {
            this.setVisible(true);
        }
    }
    
    public void paint(Graphics create) {
        create = create.create();
        final Dimension size = this.getSize();
        create.setColor(Color.red);
        final Rectangle rectangle = new Rectangle(this.$TMD + 2, 0, size.width - 2 * this.$TMD - 4, this.$SMD);
        fillRect(create, rectangle);
        rectangle.translate(0, size.height - this.$SMD);
        fillRect(create, rectangle);
        rectangle.setBounds(0, this.$TMD + 2, this.$SMD, size.height - 2 * this.$TMD - 4);
        fillRect(create, rectangle);
        rectangle.translate(size.width - this.$SMD, 0);
        fillRect(create, rectangle);
        final $V_ $v_ = new $V_(new int[] { 0, 0, this.$TMD, 0, this.$TMD, this.$SMD, this.$SMD, this.$SMD, this.$SMD, this.$TMD, 0, this.$TMD });
        create.fillPolygon($v_);
        $v_.$P5();
        create.translate(size.width - this.$TMD, 0);
        create.fillPolygon($v_);
        $v_.$O5();
        create.translate(0, size.height - this.$TMD);
        create.fillPolygon($v_);
        $v_.$P5();
        create.translate(-size.width + this.$TMD, 0);
        create.fillPolygon($v_);
        create.translate(0, -size.height + this.$TMD);
        create.dispose();
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        try {
            if (mouseEvent.isPopupTrigger() || mouseEvent.getClickCount() > 1) {
                this.child.dispatchEvent(new MouseEvent(mouseEvent.getComponent(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()));
                return;
            }
        }
        catch (Exception ex) {
            $M4.print(ex);
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public synchronized void setComponent(final Component component) {
        this.setComponent(component, true);
    }
    
    public synchronized void setComponent(final Component child, final boolean b) {
        this.$MU = false;
        if (this.child != null) {
            this.removeMouseListener(this);
            this.removeMouseMotionListener(this);
            ContainerSupport.$UJD(this);
        }
        if (this.$XMD != null) {
            this.$XMD.setComponent(null);
        }
        this.child = child;
        if (this.child == null) {
            return;
        }
        final Rectangle bounds = this.child.getBounds();
        bounds.grow(this.$SMD, this.$SMD);
        this.setBounds(bounds);
        this.child.getParent().add(this, 0);
        this.repaint();
        if (b) {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }
    }
    
    void setCursor(final int n) {
        this.setCursor(Cursor.getPredefinedCursor(n));
    }
}
