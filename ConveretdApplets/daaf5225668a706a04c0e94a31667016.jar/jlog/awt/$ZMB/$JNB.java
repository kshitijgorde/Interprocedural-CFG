// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$ZMB;

import java.awt.event.MouseMotionAdapter;
import java.util.Enumeration;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Vector;
import java.awt.Point;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;

public class $JNB
{
    Component $OU;
    MouseListener $KNB;
    MouseMotionListener $LNB;
    boolean $MNB;
    boolean $NNB;
    Point $BNB;
    public int $ONB;
    Vector v;
    
    private MouseListener $A1() {
        return new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                if (mouseEvent.getComponent() == $JNB.this.$OU && !$JNB.this.$MNB && !mouseEvent.isPopupTrigger() && (mouseEvent.getModifiers() & $JNB.this.$ONB) == $JNB.this.$ONB) {
                    mouseEvent.getComponent().addMouseMotionListener($JNB.this.$LNB);
                    $JNB.this.$MNB = true;
                    $JNB.this.$BNB = mouseEvent.getPoint();
                }
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                if ($JNB.this.$MNB) {
                    mouseEvent.getComponent().removeMouseMotionListener($JNB.this.$LNB);
                    $JNB.this.$MNB = false;
                    if ($JNB.this.$NNB) {
                        $JNB.this.$NNB = false;
                        final $CNB $cnb = new $CNB(this, $JNB.this.$BNB, mouseEvent.getPoint(), mouseEvent.getComponent(), mouseEvent.getModifiers());
                        final Enumeration<$ANB> elements = (Enumeration<$ANB>)$JNB.this.v.elements();
                        while (elements.hasMoreElements()) {
                            elements.nextElement().$DNB($cnb);
                        }
                    }
                }
            }
        };
    }
    
    public void $QNB(final $ANB $anb) {
        this.v.addElement($anb);
    }
    
    public void $RNB(final $ANB $anb) {
        this.v.removeElement($anb);
    }
    
    public boolean $SNB() {
        return this.$NNB;
    }
    
    private MouseMotionListener $Z_() {
        return new MouseMotionAdapter() {
            public void mouseDragged(final MouseEvent mouseEvent) {
                if (mouseEvent.getComponent() == $JNB.this.$OU && $JNB.this.$MNB && !mouseEvent.isPopupTrigger() && (mouseEvent.getModifiers() & $JNB.this.$ONB) == $JNB.this.$ONB) {
                    final $CNB $cnb = new $CNB(this, $JNB.this.$BNB, mouseEvent.getPoint(), mouseEvent.getComponent(), mouseEvent.getModifiers());
                    if (!$JNB.this.$NNB) {
                        $JNB.this.$NNB = true;
                        final Enumeration<$ANB> elements = $JNB.this.v.elements();
                        while (elements.hasMoreElements()) {
                            elements.nextElement().$BNB($cnb);
                        }
                    }
                    else {
                        final Enumeration<$ANB> elements2 = $JNB.this.v.elements();
                        while (elements2.hasMoreElements()) {
                            elements2.nextElement().$ENB($cnb);
                        }
                    }
                }
            }
        };
    }
    
    public $JNB(final Component component) {
        this.$OU = null;
        this.$KNB = null;
        this.$LNB = null;
        this.$MNB = false;
        this.$NNB = false;
        this.$BNB = null;
        this.$ONB = 0;
        this.v = new Vector();
        this.$KNB = this.$A1();
        this.$LNB = this.$Z_();
        this.setComponent(component);
    }
    
    public void setComponent(final Component $ou) {
        final Component $ou2 = this.$OU;
        if ($ou == $ou2) {
            return;
        }
        if ($ou2 != null) {
            $ou2.removeMouseListener(this.$KNB);
            $ou2.removeMouseMotionListener(this.$LNB);
            this.$MNB = false;
            this.$NNB = false;
        }
        if ((this.$OU = $ou) != null) {
            $ou.addMouseListener(this.$KNB);
        }
    }
}
