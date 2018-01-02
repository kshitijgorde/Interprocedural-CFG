// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$XRB;

import java.awt.Component;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.Enumeration;
import java.awt.Rectangle;
import java.awt.Dimension;
import jlog.awt.$ZMB.$CNB;
import java.awt.Point;
import jlog.awt.$ZMB.$FNB;
import jlog.awt.$ZMB.$ANB;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import jlog.awt.$ZMB.$JNB;
import java.util.Vector;

public class $ZRB extends $HSB
{
    Vector v;
    $JNB $UNB;
    
    private MouseListener $A1() {
        return new MouseAdapter() {
            public void mouseExited(final MouseEvent mouseEvent) {
                $ZRB.this.setCursor(Cursor.getPredefinedCursor(0));
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                if (!mouseEvent.isPopupTrigger()) {
                    $ZRB.this.$QSB($ZRB.this.$PSB(mouseEvent.getPoint()));
                }
            }
        };
    }
    
    private $ANB $ISB() {
        return new $FNB() {
            Point start;
            boolean $OSB = false;
            
            public void $BNB(final $CNB $cnb) {
                this.start = new Point($ZRB.this.$DSB.x, $ZRB.this.$DSB.y);
                final Point $psb = $ZRB.this.$PSB($cnb.getActual());
                this.$OSB = ($ZRB.this.$DSB.contains($psb.x, $psb.y) && $ZRB.this.$KSB);
            }
            
            public void $ENB(final $CNB $cnb) {
                if (this.$OSB) {
                    final Point $psb = $ZRB.this.$PSB($cnb.$INB());
                    $psb.translate(this.start.x + $ZRB.this.$DSB.width / 2, this.start.y + $ZRB.this.$DSB.height / 2);
                    $ZRB.this.$QSB($psb);
                }
            }
        };
    }
    
    boolean $JSB() {
        return super.$KSB && (super.$ESB > super.$DSB.width || super.$FSB > super.$DSB.height);
    }
    
    Point $PSB(final Point point) {
        final Dimension size = this.getSize();
        if (size.width < 1 || size.height < 1) {
            return new Point(0, 0);
        }
        return new Point(point.x * (super.$ESB / size.width), point.y * (super.$FSB / size.height));
    }
    
    void $QSB(final Point point) {
        if (super.$KSB) {
            this.$RSB(new Rectangle(Math.max(Math.min(point.x - super.$DSB.width / 2, super.$ESB - super.$DSB.width), 0), Math.max(Math.min(point.y - super.$DSB.height / 2, super.$FSB - super.$DSB.height), 0), super.$DSB.width, super.$DSB.height), super.$ESB, super.$FSB);
            this.$SSB(new $CSB(this, super.$DSB, super.$ESB, super.$FSB));
        }
    }
    
    void $SSB(final $CSB $csb) {
        final Enumeration<$ASB> elements = this.v.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().$BSB($csb);
        }
    }
    
    public void $TSB(final $ASB $asb) {
        this.v.addElement($asb);
    }
    
    public void $USB(final $ASB $asb) {
        this.v.removeElement($asb);
    }
    
    private MouseMotionListener $Z_() {
        return new MouseMotionAdapter() {
            Cursor $LSB = Cursor.getPredefinedCursor(13);
            Cursor $MSB = Cursor.getPredefinedCursor(1);
            Cursor $NSB = Cursor.getPredefinedCursor(0);
            
            public void mouseMoved(final MouseEvent mouseEvent) {
                if ($ZRB.this.$JSB()) {
                    final Dimension size = $ZRB.this.getSize();
                    if ($ZRB.this.$GSB(size.width, size.height).contains(mouseEvent.getX(), mouseEvent.getY())) {
                        $ZRB.this.setCursor(this.$LSB);
                    }
                    else {
                        $ZRB.this.setCursor(this.$MSB);
                    }
                }
                else {
                    $ZRB.this.setCursor(this.$NSB);
                }
            }
        };
    }
    
    public $ZRB() {
        this.v = new Vector();
        (this.$UNB = new $JNB(this)).$QNB(this.$ISB());
        this.addMouseListener(this.$A1());
        this.addMouseMotionListener(this.$Z_());
    }
}
