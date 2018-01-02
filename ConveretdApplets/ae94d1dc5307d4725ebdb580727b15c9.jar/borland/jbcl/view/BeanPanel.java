// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.Component;
import java.awt.event.WindowListener;
import java.awt.Window;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.AWTEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import borland.jbcl.util.ActionMulticaster;
import java.awt.Panel;

public class BeanPanel extends Panel
{
    protected transient ActionMulticaster actionAdapter;
    protected boolean focusAware;
    protected int focusState;
    protected boolean foundParentWindow;
    
    public BeanPanel() {
        this.focusAware = true;
        this.foundParentWindow = false;
        this.enableEvents(60L);
    }
    
    public BeanPanel(final LayoutManager layout) {
        this.focusAware = true;
        this.foundParentWindow = false;
        super.setLayout(layout);
        this.enableEvents(60L);
    }
    
    public synchronized void addActionListener(final ActionListener l) {
        if (this.actionAdapter == null) {
            this.actionAdapter = new ActionMulticaster();
        }
        this.actionAdapter.add(l);
    }
    
    public synchronized void removeActionListener(final ActionListener l) {
        if (this.actionAdapter != null) {
            this.actionAdapter.remove(l);
        }
    }
    
    protected boolean isFocusAware() {
        return this.focusAware;
    }
    
    protected void setFocusAware(final boolean aware) {
        this.focusAware = aware;
    }
    
    protected void processEvent(final AWTEvent e) {
        if (e instanceof ActionEvent) {
            this.processActionEvent((ActionEvent)e);
        }
        else {
            super.processEvent(e);
        }
    }
    
    protected void processActionEvent(final ActionEvent e) {
        if (this.actionAdapter != null) {
            this.actionAdapter.dispatch(e);
        }
    }
    
    protected void processKeyEvent(final KeyEvent e) {
        switch (e.getID()) {
            case 401: {
                this.processKeyPressed(e);
                break;
            }
            case 400: {
                this.processKeyTyped(e);
                break;
            }
            case 402: {
                this.processKeyReleased(e);
                break;
            }
        }
        super.processKeyEvent(e);
    }
    
    protected void processKeyPressed(final KeyEvent e) {
    }
    
    protected void processKeyTyped(final KeyEvent e) {
    }
    
    protected void processKeyReleased(final KeyEvent e) {
    }
    
    protected void processMouseEvent(final MouseEvent e) {
        switch (e.getID()) {
            case 501: {
                this.processMousePressed(e);
                break;
            }
            case 502: {
                this.processMouseReleased(e);
                break;
            }
            case 500: {
                this.processMouseClicked(e);
                break;
            }
            case 504: {
                this.processMouseEntered(e);
                break;
            }
            case 505: {
                this.processMouseExited(e);
                break;
            }
        }
        super.processMouseEvent(e);
    }
    
    protected void processMouseMotionEvent(final MouseEvent e) {
        switch (e.getID()) {
            case 503: {
                this.processMouseMoved(e);
                break;
            }
            case 506: {
                this.processMouseDragged(e);
                break;
            }
        }
        super.processMouseMotionEvent(e);
    }
    
    protected void processMousePressed(final MouseEvent e) {
        if (e.getClickCount() == 1 && this.isFocusTraversable()) {
            this.requestFocus();
        }
    }
    
    protected void processMouseReleased(final MouseEvent e) {
    }
    
    protected void processMouseClicked(final MouseEvent e) {
    }
    
    protected void processMouseEntered(final MouseEvent e) {
    }
    
    protected void processMouseExited(final MouseEvent e) {
    }
    
    protected void processMouseMoved(final MouseEvent e) {
    }
    
    protected void processMouseDragged(final MouseEvent e) {
    }
    
    protected void processFocusEvent(final FocusEvent e) {
        if (this.focusAware) {
            switch (e.getID()) {
                case 1004: {
                    this.focusState |= 0x2;
                    break;
                }
                case 1005: {
                    this.focusState &= 0xFFFFFFFD;
                    break;
                }
            }
        }
        super.processFocusEvent(e);
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public boolean isFocusTraversable() {
        return this.focusAware;
    }
    
    public void windowActiveChanged(final boolean active) {
        if (active) {
            this.focusState &= 0xFFFFFFDF;
        }
        else {
            this.focusState |= 0x20;
        }
    }
    
    public void addNotify() {
        super.addNotify();
        this.findParentWindow();
    }
    
    public void removeNotify() {
        super.removeNotify();
        this.foundParentWindow = false;
    }
    
    protected void findParentWindow() {
        if (this.foundParentWindow) {
            return;
        }
        Component c;
        for (c = this.getParent(); c != null && !(c instanceof Window); c = c.getParent()) {}
        if (c instanceof Window) {
            ((Window)c).addWindowListener(new BeanPanel_WindowAdapter(this));
            this.foundParentWindow = true;
        }
    }
}
