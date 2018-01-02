// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.Container;
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
    
    public synchronized void addActionListener(final ActionListener actionListener) {
        if (this.actionAdapter == null) {
            this.actionAdapter = new ActionMulticaster();
        }
        this.actionAdapter.add(actionListener);
    }
    
    public synchronized void removeActionListener(final ActionListener actionListener) {
        if (this.actionAdapter != null) {
            this.actionAdapter.remove(actionListener);
        }
    }
    
    protected boolean isFocusAware() {
        return this.focusAware;
    }
    
    protected void setFocusAware(final boolean focusAware) {
        this.focusAware = focusAware;
    }
    
    protected void processEvent(final AWTEvent awtEvent) {
        if (awtEvent instanceof ActionEvent) {
            this.processActionEvent((ActionEvent)awtEvent);
        }
        else {
            super.processEvent(awtEvent);
        }
    }
    
    protected void processActionEvent(final ActionEvent actionEvent) {
        if (this.actionAdapter != null) {
            this.actionAdapter.dispatch(actionEvent);
        }
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        switch (keyEvent.getID()) {
            case 401: {
                this.processKeyPressed(keyEvent);
                break;
            }
            case 400: {
                this.processKeyTyped(keyEvent);
                break;
            }
            case 402: {
                this.processKeyReleased(keyEvent);
                break;
            }
        }
        super.processKeyEvent(keyEvent);
    }
    
    protected void processKeyPressed(final KeyEvent keyEvent) {
    }
    
    protected void processKeyTyped(final KeyEvent keyEvent) {
    }
    
    protected void processKeyReleased(final KeyEvent keyEvent) {
    }
    
    protected void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.processMousePressed(mouseEvent);
                break;
            }
            case 502: {
                this.processMouseReleased(mouseEvent);
                break;
            }
            case 500: {
                this.processMouseClicked(mouseEvent);
                break;
            }
            case 504: {
                this.processMouseEntered(mouseEvent);
                break;
            }
            case 505: {
                this.processMouseExited(mouseEvent);
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    protected void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 503: {
                this.processMouseMoved(mouseEvent);
                break;
            }
            case 506: {
                this.processMouseDragged(mouseEvent);
                break;
            }
        }
        super.processMouseMotionEvent(mouseEvent);
    }
    
    protected void processMousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1 && this.isFocusTraversable()) {
            this.requestFocus();
        }
    }
    
    protected void processMouseReleased(final MouseEvent mouseEvent) {
    }
    
    protected void processMouseClicked(final MouseEvent mouseEvent) {
    }
    
    protected void processMouseEntered(final MouseEvent mouseEvent) {
    }
    
    protected void processMouseExited(final MouseEvent mouseEvent) {
    }
    
    protected void processMouseMoved(final MouseEvent mouseEvent) {
    }
    
    protected void processMouseDragged(final MouseEvent mouseEvent) {
    }
    
    protected void processFocusEvent(final FocusEvent focusEvent) {
        if (this.focusAware) {
            switch (focusEvent.getID()) {
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
        super.processFocusEvent(focusEvent);
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public boolean isFocusTraversable() {
        return this.focusAware;
    }
    
    public void windowActiveChanged(final boolean b) {
        if (b) {
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
        Container container;
        for (container = this.getParent(); container != null && !(container instanceof Window); container = container.getParent()) {}
        if (container instanceof Window) {
            ((Window)container).addWindowListener(new BeanPanel_WindowAdapter(this));
            this.foundParentWindow = true;
        }
    }
}
