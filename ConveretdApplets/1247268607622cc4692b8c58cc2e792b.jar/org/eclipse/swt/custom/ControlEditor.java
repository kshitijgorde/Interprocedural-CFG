// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Composite;

public class ControlEditor
{
    public int horizontalAlignment;
    public boolean grabHorizontal;
    public int minimumWidth;
    public int verticalAlignment;
    public boolean grabVertical;
    public int minimumHeight;
    Composite parent;
    Control editor;
    private boolean hadFocus;
    private Listener controlListener;
    private Listener scrollbarListener;
    private static final int[] EVENTS;
    
    static {
        EVENTS = new int[] { 1, 2, 3, 4, 11 };
    }
    
    public ControlEditor(final Composite parent) {
        this.horizontalAlignment = 16777216;
        this.grabHorizontal = false;
        this.minimumWidth = 0;
        this.verticalAlignment = 16777216;
        this.grabVertical = false;
        this.minimumHeight = 0;
        this.parent = parent;
        this.controlListener = new Listener() {
            public void handleEvent(final Event event) {
                ControlEditor.this.layout();
            }
        };
        for (int i = 0; i < ControlEditor.EVENTS.length; ++i) {
            parent.addListener(ControlEditor.EVENTS[i], this.controlListener);
        }
        this.scrollbarListener = new Listener() {
            public void handleEvent(final Event event) {
                ControlEditor.this.scroll(event);
            }
        };
        final ScrollBar horizontalBar = parent.getHorizontalBar();
        if (horizontalBar != null) {
            horizontalBar.addListener(13, this.scrollbarListener);
        }
        final ScrollBar verticalBar = parent.getVerticalBar();
        if (verticalBar != null) {
            verticalBar.addListener(13, this.scrollbarListener);
        }
    }
    
    Rectangle computeBounds() {
        final Rectangle clientArea = this.parent.getClientArea();
        final Rectangle rectangle = new Rectangle(clientArea.x, clientArea.y, this.minimumWidth, this.minimumHeight);
        if (this.grabHorizontal) {
            rectangle.width = Math.max(clientArea.width, this.minimumWidth);
        }
        if (this.grabVertical) {
            rectangle.height = Math.max(clientArea.height, this.minimumHeight);
        }
        switch (this.horizontalAlignment) {
            case 131072: {
                final Rectangle rectangle2 = rectangle;
                rectangle2.x += clientArea.width - rectangle.width;
                break;
            }
            case 16384: {
                break;
            }
            default: {
                final Rectangle rectangle3 = rectangle;
                rectangle3.x += (clientArea.width - rectangle.width) / 2;
                break;
            }
        }
        switch (this.verticalAlignment) {
            case 1024: {
                final Rectangle rectangle4 = rectangle;
                rectangle4.y += clientArea.height - rectangle.height;
                break;
            }
            case 128: {
                break;
            }
            default: {
                final Rectangle rectangle5 = rectangle;
                rectangle5.y += (clientArea.height - rectangle.height) / 2;
                break;
            }
        }
        return rectangle;
    }
    
    public void dispose() {
        if (this.parent != null && !this.parent.isDisposed()) {
            for (int i = 0; i < ControlEditor.EVENTS.length; ++i) {
                this.parent.removeListener(ControlEditor.EVENTS[i], this.controlListener);
            }
            final ScrollBar horizontalBar = this.parent.getHorizontalBar();
            if (horizontalBar != null) {
                horizontalBar.removeListener(13, this.scrollbarListener);
            }
            final ScrollBar verticalBar = this.parent.getVerticalBar();
            if (verticalBar != null) {
                verticalBar.removeListener(13, this.scrollbarListener);
            }
        }
        this.parent = null;
        this.editor = null;
        this.hadFocus = false;
        this.controlListener = null;
        this.scrollbarListener = null;
    }
    
    public Control getEditor() {
        return this.editor;
    }
    
    public void layout() {
        if (this.editor == null || this.editor.isDisposed()) {
            return;
        }
        if (this.editor.getVisible()) {
            this.hadFocus = this.editor.isFocusControl();
        }
        this.editor.setBounds(this.computeBounds());
        if (this.hadFocus) {
            if (this.editor == null || this.editor.isDisposed()) {
                return;
            }
            this.editor.setFocus();
        }
    }
    
    void scroll(final Event event) {
        if (this.editor == null || this.editor.isDisposed()) {
            return;
        }
        this.layout();
    }
    
    public void setEditor(final Control editor) {
        if (editor == null) {
            this.editor = null;
            return;
        }
        this.editor = editor;
        this.layout();
        if (this.editor == null || this.editor.isDisposed()) {
            return;
        }
        editor.setVisible(true);
    }
}
