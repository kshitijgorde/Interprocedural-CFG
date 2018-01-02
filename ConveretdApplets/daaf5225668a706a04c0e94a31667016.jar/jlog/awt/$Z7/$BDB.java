// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$Z7;

import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;

class $BDB implements FocusListener, KeyListener, MouseListener, MouseMotionListener
{
    $QBB $SDB;
    boolean mouseEntered;
    boolean mousePressed;
    boolean keyPressed;
    
    boolean $TDB() {
        return this.$SDB.$ECB != null && this.$SDB.state;
    }
    
    boolean $UDB(final MouseEvent mouseEvent) {
        return this.$SDB.isEnabled() && !mouseEvent.isPopupTrigger() && (mouseEvent.getModifiers() & 0x10) == 0x10;
    }
    
    $BDB(final $QBB $sdb) {
        this.mouseEntered = false;
        this.mousePressed = false;
        this.keyPressed = false;
        (this.$SDB = $sdb).addFocusListener(this);
        $sdb.addMouseListener(this);
        $sdb.addMouseMotionListener(this);
        $sdb.addKeyListener(this);
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.$SDB.$ADB = true;
        if (!this.mousePressed && !this.$TDB()) {
            this.$SDB.$NCB = 1;
        }
        this.$SDB.repaint();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.$SDB.$ADB = false;
        if (!this.$TDB()) {
            this.$SDB.$NCB = 0;
        }
        this.$SDB.repaint();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (this.$TDB()) {
            return;
        }
        if (!this.keyPressed && keyEvent.getKeyCode() == 32 && this.$SDB.isEnabled()) {
            this.keyPressed = true;
            this.$SDB.$NCB = 2;
            this.$SDB.repaint();
        }
        else if (this.keyPressed && keyEvent.getKeyCode() == 27) {
            this.keyPressed = false;
            this.$SDB.$NCB = 1;
            this.$SDB.repaint();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (this.$TDB()) {
            return;
        }
        if (this.keyPressed && keyEvent.getKeyCode() == 32 && this.$SDB.isEnabled()) {
            this.keyPressed = false;
            if (this.$SDB.$ECB != null) {
                this.$SDB.setState(true);
            }
            else {
                this.$SDB.$NCB = 1;
                this.$SDB.repaint();
            }
            this.$SDB.$KDB(keyEvent.getModifiers());
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!this.mousePressed || !this.$SDB.isEnabled() || this.$TDB()) {
            return;
        }
        if (this.$SDB.contains(mouseEvent.getX(), mouseEvent.getY())) {
            if (this.$SDB.$NCB != 2) {
                this.$SDB.$NCB = 2;
                this.$SDB.repaint();
            }
        }
        else if (this.$SDB.$NCB != 0) {
            this.$SDB.$NCB = 0;
            this.$SDB.repaint();
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (!this.$SDB.isEnabled() || this.$TDB()) {
            return;
        }
        this.mouseEntered = true;
        if (this.mousePressed) {
            this.$SDB.$NCB = 2;
        }
        else {
            this.$SDB.$NCB = 1;
        }
        this.$SDB.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.$SDB.isEnabled() || this.$TDB()) {
            return;
        }
        this.mouseEntered = false;
        if (!this.$SDB.$ADB) {
            this.$SDB.$NCB = 0;
            this.$SDB.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (!this.$UDB(mouseEvent) || this.$TDB()) {
            return;
        }
        this.mousePressed = true;
        this.$SDB.$NCB = 2;
        this.$SDB.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!this.mousePressed || !this.$UDB(mouseEvent) || this.$TDB()) {
            return;
        }
        this.mousePressed = false;
        if (this.$SDB.$ECB == null) {
            this.$SDB.$NCB = 1;
            this.$SDB.repaint();
        }
        if (this.$SDB.contains(mouseEvent.getX(), mouseEvent.getY())) {
            if (this.$SDB.isFocusTraversable()) {
                this.$SDB.requestFocus();
            }
            if (this.$SDB.$ECB != null) {
                this.$SDB.setState(true);
            }
            this.$SDB.$KDB(mouseEvent.getModifiers());
        }
    }
}
