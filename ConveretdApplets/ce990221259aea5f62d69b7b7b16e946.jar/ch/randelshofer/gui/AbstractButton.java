// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.gui;

import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.util.EventListener;
import ch.randelshofer.gui.event.ChangeListener;
import java.awt.Event;
import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import ch.randelshofer.gui.event.EventListenerList;
import java.awt.Dimension;
import ch.randelshofer.gui.event.ChangeEvent;
import java.awt.ItemSelectable;
import java.awt.Canvas;

public class AbstractButton extends Canvas implements ItemSelectable
{
    private ChangeEvent changeEvent_;
    private Dimension preferredSize_;
    private Dimension minimumSize_;
    private boolean isPressed_;
    private boolean isSelected_;
    private boolean isArmed_;
    private EventListenerList listenerList_;
    private String actionCommand_;
    private Icon selectedIcon_;
    private Icon unselectedIcon_;
    protected ButtonGroup group;
    static /* synthetic */ Class class$ch$randelshofer$gui$event$ChangeListener;
    static /* synthetic */ Class class$java$awt$event$ActionListener;
    static /* synthetic */ Class class$java$awt$event$ItemListener;
    
    public AbstractButton() {
        this.listenerList_ = new EventListenerList();
    }
    
    public void setPressed(final boolean isPressed_) {
        if (isPressed_ != this.isPressed_) {
            if (!(this.isPressed_ = isPressed_)) {
                this.fireActionPerformed(new ActionEvent(this, 1001, this.getActionCommand()));
            }
            this.fireStateChanged();
            this.repaint();
        }
    }
    
    public void setGroup(final ButtonGroup group) {
        this.group = group;
    }
    
    public boolean isPressed() {
        return this.isPressed_;
    }
    
    public void setSelected(final boolean isSelected_) {
        if (isSelected_ != this.isSelected_) {
            this.isSelected_ = isSelected_;
            this.fireItemStateChanged(new ItemEvent(this, 701, this, isSelected_ ? 1 : 2));
            this.fireStateChanged();
            this.repaint();
        }
    }
    
    public boolean isSelected() {
        return this.isSelected_;
    }
    
    public void setArmed(final boolean isArmed_) {
        if (isArmed_ != this.isArmed_) {
            this.isArmed_ = isArmed_;
            this.fireStateChanged();
            this.repaint();
        }
    }
    
    public boolean isArmed() {
        return this.isArmed_;
    }
    
    public void setActionCommand(final String actionCommand_) {
        this.actionCommand_ = actionCommand_;
    }
    
    public String getActionCommand() {
        return this.actionCommand_;
    }
    
    public void setSelectedIcon(final Icon selectedIcon_) {
        this.selectedIcon_ = selectedIcon_;
    }
    
    public void setUnselectedIcon(final Icon unselectedIcon_) {
        this.unselectedIcon_ = unselectedIcon_;
    }
    
    public void setIcon(final Icon unselectedIcon) {
        this.setUnselectedIcon(unselectedIcon);
    }
    
    public Icon getIcon() {
        return this.getUnselectedIcon();
    }
    
    public Icon getUnselectedIcon() {
        return this.unselectedIcon_;
    }
    
    public Icon getSelectedIcon() {
        return this.selectedIcon_;
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.size();
        final int width = size.width;
        final int height = size.height;
        if (!this.isEnabled()) {
            graphics.setColor(Color.gray);
        }
        graphics.drawRect(0, 0, width - 1, height - 1);
        if (this.isPressed_ && this.isArmed_) {
            graphics.setColor(Color.gray.darker());
            graphics.fillRect(1, 1, width - 3, height - 3);
            graphics.setColor(Color.darkGray);
            graphics.drawLine(1, 1, width - 2, 1);
            graphics.drawLine(1, 1, 1, height - 2);
            graphics.setColor(Color.gray);
            graphics.drawLine(2, height - 2, width - 2, height - 2);
            graphics.drawLine(width - 2, height - 2, width - 2, 2);
        }
        else {
            graphics.setColor((this.isSelected_ && this.group != null) ? new Color(160, 160, 160) : Color.lightGray);
            graphics.fillRect(1, 1, width - 2, height - 2);
            if (this.isEnabled()) {
                graphics.setColor(this.isSelected_ ? Color.gray : Color.white);
                graphics.drawLine(1, 1, width - 3, 1);
                graphics.drawLine(1, 1, 1, height - 3);
                if (!this.isSelected_ && this.group != null) {
                    graphics.setColor(Color.gray);
                    graphics.drawLine(1, height - 2, width - 2, height - 2);
                    graphics.drawLine(width - 2, height - 2, width - 2, 2);
                }
            }
        }
        final Icon icon = (this.isSelected_ && this.selectedIcon_ != null) ? this.selectedIcon_ : this.unselectedIcon_;
        if (icon != null) {
            graphics.setColor(this.getForeground());
            icon.paintIcon(this, graphics, 2, 1);
        }
    }
    
    public Dimension getPreferredSize() {
        return this.preferredSize();
    }
    
    public Dimension preferredSize() {
        if (this.preferredSize_ != null) {
            return this.preferredSize_;
        }
        final Icon icon = this.getIcon();
        if (icon != null) {
            return new Dimension(icon.getIconWidth() + 4, icon.getIconHeight() + 4);
        }
        return super.preferredSize();
    }
    
    public void setPreferredSize(final Dimension preferredSize_) {
        this.preferredSize_ = preferredSize_;
    }
    
    public Dimension getMinimumSize() {
        return this.minimumSize();
    }
    
    public Dimension minimumSize() {
        if (this.minimumSize_ == null) {
            return super.minimumSize();
        }
        return this.minimumSize_;
    }
    
    public void setMinimumSize(final Dimension minimumSize_) {
        this.minimumSize_ = minimumSize_;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.setArmed(true);
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setArmed(false);
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.setArmed(true);
        this.setPressed(true);
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.setPressed(false);
        this.repaint();
        return true;
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList_.add((AbstractButton.class$ch$randelshofer$gui$event$ChangeListener == null) ? (AbstractButton.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : AbstractButton.class$ch$randelshofer$gui$event$ChangeListener, changeListener);
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList_.remove((AbstractButton.class$ch$randelshofer$gui$event$ChangeListener == null) ? (AbstractButton.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : AbstractButton.class$ch$randelshofer$gui$event$ChangeListener, changeListener);
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.listenerList_.add((AbstractButton.class$java$awt$event$ActionListener == null) ? (AbstractButton.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")) : AbstractButton.class$java$awt$event$ActionListener, actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.listenerList_.remove((AbstractButton.class$java$awt$event$ActionListener == null) ? (AbstractButton.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")) : AbstractButton.class$java$awt$event$ActionListener, actionListener);
    }
    
    public void addItemListener(final ItemListener itemListener) {
        this.listenerList_.add((AbstractButton.class$java$awt$event$ItemListener == null) ? (AbstractButton.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")) : AbstractButton.class$java$awt$event$ItemListener, itemListener);
    }
    
    public void removeItemListener(final ItemListener itemListener) {
        this.listenerList_.remove((AbstractButton.class$java$awt$event$ItemListener == null) ? (AbstractButton.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")) : AbstractButton.class$java$awt$event$ItemListener, itemListener);
    }
    
    protected void fireActionPerformed(final ActionEvent actionEvent) {
        final Object[] listenerList = this.listenerList_.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractButton.class$java$awt$event$ActionListener == null) ? (AbstractButton.class$java$awt$event$ActionListener = class$("java.awt.event.ActionListener")) : AbstractButton.class$java$awt$event$ActionListener)) {
                ((ActionListener)listenerList[i + 1]).actionPerformed(actionEvent);
            }
        }
    }
    
    protected void fireItemStateChanged(final ItemEvent itemEvent) {
        final Object[] listenerList = this.listenerList_.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractButton.class$java$awt$event$ItemListener == null) ? (AbstractButton.class$java$awt$event$ItemListener = class$("java.awt.event.ItemListener")) : AbstractButton.class$java$awt$event$ItemListener)) {
                ((ItemListener)listenerList[i + 1]).itemStateChanged(itemEvent);
            }
        }
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = this.listenerList_.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((AbstractButton.class$ch$randelshofer$gui$event$ChangeListener == null) ? (AbstractButton.class$ch$randelshofer$gui$event$ChangeListener = class$("ch.randelshofer.gui.event.ChangeListener")) : AbstractButton.class$ch$randelshofer$gui$event$ChangeListener)) {
                if (this.changeEvent_ == null) {
                    this.changeEvent_ = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent_);
            }
        }
    }
    
    public void stateChanged(final ChangeEvent changeEvent) {
        this.repaint();
        this.fireStateChanged();
    }
    
    public Object[] getSelectedObjects() {
        return null;
    }
    
    public void setEnabled(final boolean enabled) {
        if (enabled != this.isEnabled()) {
            super.setEnabled(enabled);
            this.repaint();
        }
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
