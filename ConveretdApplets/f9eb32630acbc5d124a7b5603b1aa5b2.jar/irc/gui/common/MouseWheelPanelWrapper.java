// 
// Decompiled by Procyon v0.5.30
// 

package irc.gui.common;

import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.lang.reflect.Method;
import java.awt.Panel;

public class MouseWheelPanelWrapper extends Panel
{
    private Object _panel;
    private Method _add;
    private Method _remove;
    static /* synthetic */ Class class$irc$gui$common$MouseWheelPanelListener;
    static /* synthetic */ Class class$java$awt$Component;
    
    public MouseWheelPanelWrapper(final Component component) {
        this.setLayout(new GridLayout(1, 1));
        try {
            final Class<?> forName = Class.forName("irc.gui.prv.MouseWheelPanel");
            this._panel = forName.newInstance();
            final Class[] array = { (MouseWheelPanelWrapper.class$irc$gui$common$MouseWheelPanelListener == null) ? (MouseWheelPanelWrapper.class$irc$gui$common$MouseWheelPanelListener = class$("irc.gui.common.MouseWheelPanelListener")) : MouseWheelPanelWrapper.class$irc$gui$common$MouseWheelPanelListener };
            this._add = forName.getMethod("addMouseWheelPanelListener", (Class[])array);
            this._remove = forName.getMethod("removeMouseWheelPanelListener", (Class[])array);
            this.add((Component)this._panel);
            array[0] = ((MouseWheelPanelWrapper.class$java$awt$Component == null) ? (MouseWheelPanelWrapper.class$java$awt$Component = class$("java.awt.Component")) : MouseWheelPanelWrapper.class$java$awt$Component);
            forName.getMethod("add", (Class[])array).invoke(this._panel, component);
        }
        catch (Throwable t) {
            this.add(component);
        }
    }
    
    public void addMouseWheelPanelListener(final MouseWheelPanelListener mouseWheelPanelListener) {
        try {
            this._add.invoke(this._panel, mouseWheelPanelListener);
        }
        catch (Throwable t) {}
    }
    
    public void removeMouseWheelPanelListener(final MouseWheelPanelListener mouseWheelPanelListener) {
        try {
            this._remove.invoke(this._panel, mouseWheelPanelListener);
        }
        catch (Throwable t) {}
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
