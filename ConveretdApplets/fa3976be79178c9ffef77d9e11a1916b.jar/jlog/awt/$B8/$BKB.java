// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$B8;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.AWTEventMulticaster;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

class $BKB extends MouseAdapter
{
    $GJB $WIB;
    boolean mousePressed;
    ActionListener $LJB;
    
    $BKB(final $GJB $wib) {
        this.mousePressed = false;
        this.$LJB = null;
        this.$WIB = $wib;
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.$LJB = AWTEventMulticaster.add(this.$LJB, actionListener);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mousePressed = (mouseEvent.isPopupTrigger() ^ true);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.mousePressed) {
            this.mousePressed = false;
            final Rectangle bounds = this.$WIB.getBounds();
            bounds.translate(-bounds.x, -bounds.y);
            if (this.$LJB != null && bounds.contains(mouseEvent.getPoint()) && !mouseEvent.isPopupTrigger()) {
                this.$LJB.actionPerformed(new ActionEvent(this.$WIB, 1001, this.$WIB.$NJB));
            }
        }
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.$LJB = AWTEventMulticaster.remove(this.$LJB, actionListener);
    }
}
