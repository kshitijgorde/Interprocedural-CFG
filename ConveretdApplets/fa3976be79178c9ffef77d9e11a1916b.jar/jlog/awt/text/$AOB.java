// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.text;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;
import java.util.Enumeration;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Hashtable;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.Color;

public class $AOB extends $HPB
{
    public Color $DQB;
    Vector $EQB;
    MouseListener $KNB;
    MouseMotionListener $LNB;
    Hashtable $FQB;
    
    void $A1() {
        this.$KNB = new MouseAdapter() {
            String $IQB = null;
            
            public void mousePressed(final MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    return;
                }
                this.$IQB = $AOB.this.$JQB(mouseEvent.getX(), mouseEvent.getY());
            }
            
            public void mouseReleased(final MouseEvent mouseEvent) {
                if (mouseEvent.isPopupTrigger()) {
                    return;
                }
                final String $jqb = $AOB.this.$JQB(mouseEvent.getX(), mouseEvent.getY());
                if ($jqb == null || !$jqb.equals(this.$IQB)) {
                    return;
                }
                this.$IQB = null;
                final $UOB $uob = new $UOB(this, $jqb);
                final Enumeration<$XOB> elements = (Enumeration<$XOB>)$AOB.this.$EQB.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().$APB($uob);
                }
            }
        };
        this.$LNB = new MouseMotionAdapter() {
            String $LQB = null;
            
            public void mouseMoved(final MouseEvent mouseEvent) {
                final String $jqb = $AOB.this.$JQB(mouseEvent.getX(), mouseEvent.getY());
                if ($jqb != null) {
                    $AOB.this.setCursor(Cursor.getPredefinedCursor(12));
                    this.$LQB = $jqb;
                    final $UOB $uob = new $UOB(this, $jqb);
                    final Enumeration<$XOB> elements = (Enumeration<$XOB>)$AOB.this.$EQB.elements();
                    while (elements.hasMoreElements()) {
                        elements.nextElement().$YOB($uob);
                    }
                }
                else if (this.$LQB != null) {
                    $AOB.this.setCursor(Cursor.getPredefinedCursor(0));
                    final $UOB $uob2 = new $UOB(this, this.$LQB);
                    this.$LQB = null;
                    final Enumeration<$XOB> elements2 = (Enumeration<$XOB>)$AOB.this.$EQB.elements();
                    while (elements2.hasMoreElements()) {
                        elements2.nextElement().$ZOB($uob2);
                    }
                }
            }
        };
    }
    
    public void $GQB(final $XOB $xob) {
        if (this.$EQB == null) {
            this.$EQB = new Vector();
        }
        if (this.$EQB.isEmpty()) {
            if (this.$KNB == null) {
                this.$A1();
            }
            this.addMouseListener(this.$KNB);
            this.addMouseMotionListener(this.$LNB);
        }
        this.$EQB.addElement($xob);
    }
    
    public void $HQB(final $XOB $xob) {
        if (this.$EQB == null) {
            return;
        }
        this.$EQB.removeElement($xob);
        if (this.$EQB.isEmpty() && this.$KNB != null) {
            this.removeMouseListener(this.$KNB);
            this.removeMouseMotionListener(this.$LNB);
        }
    }
    
    public String $JQB(final int n, final int n2) {
        if (this.$EQB == null || this.$EQB.isEmpty() || this.$FQB == null || this.$FQB.isEmpty()) {
            return null;
        }
        final Enumeration<Rectangle> keys = ((Hashtable)this.$FQB.clone()).keys();
        while (keys.hasMoreElements()) {
            final Rectangle rectangle = keys.nextElement();
            if (rectangle.contains(n, n2)) {
                return (String)this.$FQB.get(rectangle);
            }
        }
        return null;
    }
    
    public void $VPB(Graphics create, final String s, final Vector vector, final int n, final int n2) {
        super.$VPB(create, s, vector, n, n2);
        final FontMetrics fontMetrics = create.getFontMetrics();
        if (fontMetrics == null || this.$EQB == null || this.$EQB.isEmpty() || vector == null) {
            return;
        }
        create = create.create();
        try {
            if (this.$FQB == null) {
                this.$FQB = new Hashtable();
            }
            create.setColor(this.$DQB);
            final Enumeration<$ROB> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final $ROB $rob = elements.nextElement();
                final int n3 = n + fontMetrics.stringWidth(s.substring(0, $rob.start));
                final String substring = s.substring($rob.start, $rob.$SOB);
                final int stringWidth = fontMetrics.stringWidth(substring);
                create.drawString(substring, n3, n2);
                create.drawLine(n3, n2 + 2, n3 + stringWidth, n2 + 2);
                this.$FQB.put(new Rectangle(n3, n2 - fontMetrics.getMaxAscent(), stringWidth, fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent()), $rob.$TOB);
            }
        }
        finally {
            create.dispose();
        }
    }
    
    public $AOB(final String s, final int n) {
        super(s, n);
        this.$DQB = Color.blue;
        this.$EQB = null;
        this.$KNB = null;
        this.$LNB = null;
        this.$FQB = null;
    }
    
    public void setText(final String text) {
        if (this.$FQB != null) {
            this.$FQB.clear();
        }
        else {
            this.$FQB = new Hashtable();
        }
        super.setText(text);
    }
}
