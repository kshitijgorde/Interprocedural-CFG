// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Event;
import java.awt.Component;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class NFEventAdapter implements MouseListener, MouseMotionListener, KeyListener, FocusListener
{
    protected Component c;
    private static final int[][] a;
    
    public NFEventAdapter(final Component c) {
        this.c = null;
        this.register(this.c = c);
    }
    
    protected void register(final Component component) {
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        component.addKeyListener(this);
        component.addFocusListener(this);
    }
    
    protected static void postEvent(final Component component, final Event event) {
        if (event != null) {
            component.postEvent(event);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.c.requestFocus();
        postEvent(this.c, convertMouseEvent(this.c, mouseEvent));
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.c.requestFocus();
        postEvent(this.c, convertMouseEvent(this.c, mouseEvent));
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        postEvent(this.c, convertMouseEvent(this.c, mouseEvent));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.c.requestFocus();
        postEvent(this.c, convertMouseEvent(this.c, mouseEvent));
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        postEvent(this.c, convertMouseEvent(this.c, mouseEvent));
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        postEvent(this.c, convertMouseEvent(this.c, mouseEvent));
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        postEvent(this.c, convertMouseEvent(this.c, mouseEvent));
    }
    
    public static Event convertMouseEvent(final Component component, final MouseEvent mouseEvent) {
        final Event event = new Event(component, mouseEvent.getWhen(), mouseEvent.getID(), mouseEvent.getX(), mouseEvent.getY(), 0, mouseEvent.getModifiers() & 0xFFFFFFEF);
        event.clickCount = mouseEvent.getClickCount();
        return event;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        postEvent(this.c, convertKeyEvent(this.c, keyEvent));
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        postEvent(this.c, convertKeyEvent(this.c, keyEvent));
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        postEvent(this.c, convertKeyEvent(this.c, keyEvent));
    }
    
    public static int getOldEventKey(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        for (int i = 0; i < NFEventAdapter.a.length; ++i) {
            if (NFEventAdapter.a[i][0] == keyCode) {
                return NFEventAdapter.a[i][1];
            }
        }
        return keyEvent.getKeyChar();
    }
    
    public static Event convertKeyEvent(final Component component, final KeyEvent keyEvent) {
        int n = 0;
        final int id = keyEvent.getID();
        switch (id) {
            case 401:
            case 402: {
                if (keyEvent.isActionKey()) {
                    n = ((id == 401) ? 403 : 404);
                }
                final int keyCode = keyEvent.getKeyCode();
                if (keyCode == 16 || keyCode == 17 || keyCode == 18) {
                    return null;
                }
                return new Event(component, keyEvent.getWhen(), n, 0, 0, getOldEventKey(keyEvent), keyEvent.getModifiers() & 0xFFFFFFEF);
            }
            default: {
                return null;
            }
        }
    }
    
    public static Event convertActionEvent(final Component component, final ActionEvent actionEvent) {
        return new Event(component, System.currentTimeMillis(), actionEvent.getID(), 0, 0, 0, 0);
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        postEvent(this.c, convertFocusEvent(this.c, focusEvent));
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        postEvent(this.c, convertFocusEvent(this.c, focusEvent));
    }
    
    public static Event convertFocusEvent(final Component component, final FocusEvent focusEvent) {
        switch (focusEvent.getID()) {
            case 1004: {
                return new Event(component, 1004, null);
            }
            case 1005: {
                return new Event(component, 1005, null);
            }
            default: {
                return null;
            }
        }
    }
    
    static {
        a = new int[][] { { 36, 1000 }, { 35, 1001 }, { 33, 1002 }, { 34, 1003 }, { 38, 1004 }, { 40, 1005 }, { 37, 1006 }, { 39, 1007 }, { 112, 1008 }, { 113, 1009 }, { 114, 1010 }, { 115, 1011 }, { 116, 1012 }, { 117, 1013 }, { 118, 1014 }, { 119, 1015 }, { 120, 1016 }, { 121, 1017 }, { 122, 1018 }, { 123, 1019 }, { 154, 1020 }, { 145, 1021 }, { 20, 1022 }, { 144, 1023 }, { 19, 1024 }, { 155, 1025 } };
    }
}
