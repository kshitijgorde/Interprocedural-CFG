// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import borland.jbcl.model.ItemPainter;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Dimension;
import borland.jbcl.model.SingletonModelEvent;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.awt.SystemColor;
import borland.jbcl.model.SingletonModelMulticaster;
import borland.jbcl.model.SingletonViewManager;
import borland.jbcl.model.WritableSingletonModel;
import borland.jbcl.model.SingletonModel;
import borland.jbcl.model.ItemPaintSite;
import borland.jbcl.model.SingletonModelListener;

public class ButtonView extends BeanPanel implements SingletonView, SingletonModelListener, ItemPaintSite
{
    private transient SingletonModel model;
    private transient WritableSingletonModel writeModel;
    private transient SingletonViewManager viewManager;
    private boolean readOnly;
    private transient SingletonModelMulticaster modelMulticaster;
    private boolean showRollover;
    protected String actionCommand;
    protected ButtonItemPainter borderPainter;
    protected int state;
    protected int alignment;
    protected boolean mouseDown;
    protected boolean mouseOver;
    
    public ButtonView() {
        this.modelMulticaster = new SingletonModelMulticaster();
        this.showRollover = false;
        this.alignment = 34;
        this.borderPainter = new ButtonItemPainter();
        this.setBackground(SystemColor.control);
    }
    
    public Insets getItemMargins() {
        return new Insets(0, 0, 0, 0);
    }
    
    public Component getSiteComponent() {
        return this;
    }
    
    public String getLabel() {
        final Object contents = (this.model != null) ? this.model.get() : null;
        return (contents != null) ? contents.toString() : "";
    }
    
    public void setLabel(final String label) {
        if (!this.isReadOnly()) {
            this.writeModel.set(label);
            this.repaint(100L);
        }
    }
    
    public void setFocusAware(final boolean aware) {
        super.setFocusAware(aware);
        this.repaint(100L);
    }
    
    public boolean isFocusAware() {
        return super.isFocusAware();
    }
    
    public void setAlignment(final int alignment) {
        this.alignment = alignment;
    }
    
    public int getAlignment() {
        return this.alignment;
    }
    
    public void setShowRollover(final boolean showRollover) {
        this.showRollover = showRollover;
        this.borderPainter.setShowRollover(showRollover);
        this.repaint(100L);
    }
    
    public boolean isShowRollover() {
        return this.showRollover;
    }
    
    public boolean isSelected() {
        return (this.state & 0x4) != 0x0;
    }
    
    public void setSelected(final boolean selected) {
        if (selected) {
            this.state |= 0x4;
        }
        else {
            this.state &= 0xFFFFFFFB;
        }
        this.repaint(100L);
    }
    
    public void setEnabled(final boolean enable) {
        if (this.isEnabled() != enable) {
            if (enable) {
                this.state &= 0xFFFFFFFE;
            }
            else {
                this.state |= 0x1;
                this.state &= 0xFFFFFFBF;
                this.state &= 0xFFFFFFFB;
            }
            super.setEnabled(enable);
            this.repaint(100L);
        }
    }
    
    public void setModel(final SingletonModel sm) {
        if (this.model != null) {
            this.model.removeModelListener(this);
            this.model.removeModelListener(this.modelMulticaster);
        }
        this.model = sm;
        if (this.model != null) {
            this.model.addModelListener(this);
            this.model.addModelListener(this.modelMulticaster);
        }
        this.writeModel = ((this.model instanceof WritableSingletonModel) ? ((WritableSingletonModel)this.model) : null);
        this.repaint(100L);
    }
    
    public SingletonModel getModel() {
        return this.model;
    }
    
    public WritableSingletonModel getWriteModel() {
        return this.readOnly ? null : this.writeModel;
    }
    
    public void addModelListener(final SingletonModelListener l) {
        this.modelMulticaster.add(l);
    }
    
    public void removeModelListener(final SingletonModelListener l) {
        this.modelMulticaster.remove(l);
    }
    
    public void setReadOnly(final boolean ro) {
        this.readOnly = ro;
    }
    
    public boolean isReadOnly() {
        return this.readOnly || this.writeModel == null;
    }
    
    public SingletonViewManager getViewManager() {
        return this.viewManager;
    }
    
    public void setViewManager(final SingletonViewManager vm) {
        this.viewManager = vm;
    }
    
    public void modelContentChanged(final SingletonModelEvent e) {
        this.repaint(100L);
    }
    
    public void setActionCommand(final String command) {
        this.actionCommand = command;
    }
    
    public String getActionCommand() {
        return this.actionCommand;
    }
    
    public Dimension getPreferredSize() {
        final Graphics g = this.getGraphics();
        final Dimension borderSize = this.borderPainter.getPreferredSize(null, g, this.state | super.focusState, this);
        if (this.model != null && this.viewManager != null) {
            final Object contents = this.model.get();
            if (contents != null) {
                final Dimension innerSize = this.viewManager.getPainter(contents, this.state | super.focusState).getPreferredSize(contents, g, this.state | super.focusState, this);
                return new Dimension(innerSize.width + borderSize.width, innerSize.height + borderSize.height);
            }
        }
        return borderSize;
    }
    
    public void setBounds(final int x, final int y, final int width, final int height) {
        super.setBounds(x, y, width, height);
        this.repaint(300L);
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        final Dimension outerSize = this.getSize();
        final Dimension borderSize = this.borderPainter.getPreferredSize(null, g, this.state | super.focusState, this);
        final Dimension innerSize = new Dimension(outerSize.width - borderSize.width, outerSize.height - borderSize.height);
        this.borderPainter.paint(null, g, new Rectangle(0, 0, outerSize.width, outerSize.height), this.state | super.focusState, this);
        final boolean pressed = (this.state & 0x4) != 0x0;
        final int left = borderSize.width / 2 + (pressed ? 1 : 0);
        final int top = borderSize.height / 2 + (pressed ? 1 : 0);
        final Object contents = (this.model != null) ? this.model.get() : null;
        final ItemPainter painter = (this.viewManager != null) ? this.viewManager.getPainter(contents, this.state | super.focusState) : null;
        if (painter != null) {
            final Rectangle r = new Rectangle(left, top, innerSize.width, innerSize.height);
            g.clipRect(r.x, r.y, r.width, r.height);
            g.setColor(this.getBackground());
            g.setFont(this.getFont());
            painter.paint(contents, g, r, this.state | super.focusState, this);
        }
    }
    
    public void setVisible(final boolean visible) {
        if (!visible) {
            this.setSelected(this.mouseDown = false);
        }
        super.setVisible(visible);
    }
    
    protected void processFocusEvent(final FocusEvent e) {
        super.processFocusEvent(e);
        if (e.getID() == 1005) {
            this.state &= 0xFFFFFFFB;
        }
        if (super.focusAware) {
            this.repaint(100L);
        }
    }
    
    protected void processKeyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case 32: {
                this.state |= 0x4;
                this.repaint();
                break;
            }
        }
    }
    
    protected void processKeyReleased(final KeyEvent e) {
        if (e.getKeyCode() == 32) {
            this.state &= 0xFFFFFFFB;
            this.repaint();
            this.processActionEvent(new ActionEvent(this, 1001, this.getActionCommand()));
        }
    }
    
    protected void processMousePressed(final MouseEvent event) {
        if (this.contains(event.getPoint())) {
            this.state |= 0x4;
            this.state &= 0xFFFFFFBF;
            this.mouseDown = true;
            this.mouseOver = true;
            this.repaint();
        }
    }
    
    protected void processMouseReleased(final MouseEvent event) {
        final boolean action = (this.state & 0x4) != 0x0;
        this.state &= 0xFFFFFFFB;
        this.mouseDown = false;
        if (this.mouseOver && this.showRollover) {
            this.state |= 0x40;
        }
        this.repaint();
        if (action) {
            this.processActionEvent(new ActionEvent(this, 1001, this.getActionCommand()));
        }
    }
    
    protected void processMouseDragged(final MouseEvent event) {
        this.state &= 0xFFFFFFBF;
        super.processMouseDragged(event);
    }
    
    protected void processMouseEntered(final MouseEvent event) {
        this.mouseOver = true;
        boolean paint = false;
        if (this.mouseDown) {
            this.state |= 0x4;
            paint = true;
        }
        if (this.showRollover) {
            this.state |= 0x40;
            paint = true;
        }
        if (paint) {
            this.repaint();
        }
    }
    
    protected void processMouseExited(final MouseEvent event) {
        this.mouseOver = false;
        boolean paint = false;
        if (this.mouseDown) {
            this.state &= 0xFFFFFFFB;
            paint = true;
        }
        if (this.showRollover && !this.mouseDown) {
            this.state &= 0xFFFFFFBF;
            paint = true;
        }
        if (paint) {
            this.repaint();
        }
    }
    
    protected String paramString() {
        return String.valueOf(String.valueOf(String.valueOf(String.valueOf(super.paramString()).concat(String.valueOf(",label="))).concat(String.valueOf(this.getLabel()))).concat(String.valueOf(",state="))).concat(String.valueOf(this.state));
    }
}
