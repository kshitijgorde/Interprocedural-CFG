// 
// Decompiled by Procyon v0.5.30
// 

package irc.gui.common;

import irc.EventDispatcher;
import java.awt.event.WindowEvent;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import irc.ListenerGroup;
import java.awt.Frame;
import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.Panel;

public class DockablePanel extends Panel implements WindowListener, Runnable
{
    private Component _comp;
    private boolean _docked;
    private Frame _frame;
    private int _behaviour;
    private ListenerGroup _listeners;
    public static final int DOCK_ON_CLOSE = 0;
    public static final int DO_NOTHING_ON_CLOSE = 1;
    
    public DockablePanel(final Component comp, final Color background) {
        this.setBackground(background);
        this.setLayout(new BorderLayout());
        this._comp = comp;
        this._docked = true;
        this.add(this._comp, "Center");
        this.validate();
        (this._frame = new Frame()).setLayout(new BorderLayout());
        this._frame.addWindowListener(this);
        this._behaviour = 0;
        this._listeners = new ListenerGroup();
    }
    
    public void addDockablePanelListener(final DockablePanelListener dockablePanelListener) {
        this._listeners.addListener(dockablePanelListener);
    }
    
    public void removeDockablePanelListener(final DockablePanelListener dockablePanelListener) {
        this._listeners.removeListener(dockablePanelListener);
    }
    
    public void setClosingBehaviour(final int behaviour) {
        this._behaviour = behaviour;
    }
    
    public int getClosingBehaviour() {
        return this._behaviour;
    }
    
    public Component getComponent() {
        return this._comp;
    }
    
    public void undock(final String title) {
        if (this._comp == null) {
            return;
        }
        if (!this._docked) {
            return;
        }
        this._docked = false;
        this._comp.setVisible(true);
        this.remove(this._comp);
        this.validate();
        this._frame.add(this._comp, "Center");
        this._frame.pack();
        this._frame.setTitle(title);
        this._frame.show();
    }
    
    public void dock() {
        if (this._comp == null) {
            return;
        }
        if (this._docked) {
            return;
        }
        this._docked = true;
        this._comp.setVisible(false);
        this._frame.hide();
        this._frame.remove(this._comp);
        this.add(this._comp, "Center");
        this._comp.setVisible(this.isVisible());
        this.validate();
    }
    
    public boolean isDocked() {
        return this._docked;
    }
    
    public void bring() {
        this._frame.toFront();
    }
    
    public void run() {
        if (this._frame != null) {
            this._frame.dispose();
        }
        this._frame = null;
    }
    
    public void release() {
        if (this._frame == null) {
            return;
        }
        this.dock();
        this._frame.removeAll();
        this.removeAll();
        this._frame.removeWindowListener(this);
        new Thread(this, "Frame disposal thread").start();
        this._comp = null;
    }
    
    public void setVisible(final boolean b) {
        if (this._comp == null) {
            return;
        }
        if (this._docked) {
            this._comp.setVisible(b);
        }
        super.setVisible(b);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
        this._listeners.sendEventAsync("DockablePanelWindowClosed", this);
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this._listeners.sendEventAsync("DockablePanelWindowClosing", this);
        if (this._behaviour == 0) {
            EventDispatcher.dispatchEventAsync(this, "dock", new Object[0]);
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}
