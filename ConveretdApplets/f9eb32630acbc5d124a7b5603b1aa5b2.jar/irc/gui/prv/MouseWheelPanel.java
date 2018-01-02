// 
// Decompiled by Procyon v0.5.30
// 

package irc.gui.prv;

import java.awt.event.MouseWheelEvent;
import irc.gui.common.MouseWheelPanelListener;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import irc.ListenerGroup;
import java.awt.event.MouseWheelListener;
import java.awt.Panel;

public class MouseWheelPanel extends Panel implements MouseWheelListener
{
    private ListenerGroup _listeners;
    
    public MouseWheelPanel() {
        this._listeners = new ListenerGroup();
        this.addMouseWheelListener(this);
        this.setLayout(new GridLayout(1, 1));
    }
    
    public void addMouseWheelPanelListener(final MouseWheelPanelListener mouseWheelPanelListener) {
        this._listeners.addListener(mouseWheelPanelListener);
    }
    
    public void removeMouseWheelPanelListener(final MouseWheelPanelListener mouseWheelPanelListener) {
        this._listeners.removeListener(mouseWheelPanelListener);
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        this._listeners.sendEventAsync("mouseWheelMoved", new Integer(mouseWheelEvent.getWheelRotation()));
    }
}
