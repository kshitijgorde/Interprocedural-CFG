// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Insets;
import java.awt.Component;
import jclass.util.JCString;
import java.awt.Image;
import java.applet.Applet;
import jclass.util.JCVector;

public class JCButton extends JCLabel
{
    Object arm_label;
    boolean armed;
    protected int old_shadowtype;
    String actionCommand;
    boolean inside_button;
    boolean is_action_button;
    private static final boolean TRACE = false;
    protected JCVector actionListeners;
    protected JCVector buttonListeners;
    private static final String base = "button";
    private static int nameCounter;
    protected int arm_offset;
    
    public JCButton() {
        this(null, null, null);
    }
    
    public JCButton(final Object o) {
        this(o, null, null);
    }
    
    public JCButton(final String name, final Image image, final int n) {
        this(new JCString(name, image, n), null, null);
        this.setName(name);
    }
    
    public JCButton(final String s, final String s2, final Applet applet, final int n) {
        this(null, null, null);
        this.setLabel(new JCString(s, JCComponent.conv.toImage(applet, s2), n));
        super.insets = new Insets(2, 5, 2, 5);
    }
    
    public JCButton(final Object o, final Applet applet, final String s) {
        super(o, applet, s);
        this.armed = false;
        this.inside_button = false;
        this.is_action_button = false;
        this.actionListeners = new JCVector(0);
        this.buttonListeners = new JCVector(0);
        this.arm_offset = 1;
        if (s == null) {
            this.setName("button" + JCButton.nameCounter++);
        }
        super.border_style = 9;
        super.traversable = true;
        super.highlight = 1;
        super.border = 2;
        if (o == null || o instanceof String) {
            super.insets = new Insets(0, 5, 0, 5);
        }
        else {
            super.insets = new Insets(2, 5, 2, 5);
        }
        if (this.getClass().getName().equals("jclass.bwt.JCButton")) {
            this.getParameters(applet);
        }
        this.enableEvents(32L);
    }
    
    protected void getParameters() {
        super.getParameters();
        ButtonConverter.getParams(this);
    }
    
    public boolean getIsActionButton() {
        return this.is_action_button;
    }
    
    public void setIsActionButton(final boolean is_action_button) {
        if (is_action_button != this.is_action_button) {
            this.is_action_button = is_action_button;
            this.repaint();
        }
    }
    
    public Object getArmLabel() {
        return this.arm_label;
    }
    
    public void setArmLabel(final Object arm_label) {
        this.arm_label = arm_label;
        if (this.armed) {
            this.layout();
            this.repaint();
        }
    }
    
    public void setActionCommand(final String actionCommand) {
        this.actionCommand = actionCommand;
    }
    
    public String getActionCommand() {
        if (this.actionCommand == null) {
            return String.valueOf(super.label);
        }
        return this.actionCommand;
    }
    
    public void addActionListener(final JCActionListener jcActionListener) {
        this.actionListeners.add(jcActionListener);
    }
    
    public void removeActionListener(final JCActionListener jcActionListener) {
        this.actionListeners.removeElement(jcActionListener);
    }
    
    public void addButtonListener(final JCButtonListener jcButtonListener) {
        this.buttonListeners.add(jcButtonListener);
    }
    
    public void removeButtonListener(final JCButtonListener jcButtonListener) {
        this.buttonListeners.removeElement(jcButtonListener);
    }
    
    public void armAction(final Event event) {
        this.old_shadowtype = super.border_style;
        super.border_style = 3;
        this.armed = true;
        final JCButtonEvent jcButtonEvent = (this.buttonListeners.size() > 0) ? new JCButtonEvent(event) : null;
        for (int i = 0; i < this.buttonListeners.size(); ++i) {
            ((JCButtonListener)this.buttonListeners.elementAt(i)).buttonArmBegin(jcButtonEvent);
        }
        final Graphics graphics = this.getGraphics();
        final Dimension size = this.size();
        if (graphics != null) {
            this.repaint(graphics, 0, 0, size.width, size.height);
        }
        super.border_style = this.old_shadowtype;
        for (int j = 0; j < this.buttonListeners.size(); ++j) {
            ((JCButtonListener)this.buttonListeners.elementAt(j)).buttonArmEnd(jcButtonEvent);
        }
    }
    
    public void disarmAction(final Event event) {
        final JCButtonEvent jcButtonEvent = (this.buttonListeners.size() > 0) ? new JCButtonEvent(event) : null;
        for (int i = 0; i < this.buttonListeners.size(); ++i) {
            ((JCButtonListener)this.buttonListeners.elementAt(i)).buttonDisarmBegin(jcButtonEvent);
        }
        if (this.armed && this.arm_label != null) {
            this.layout(super.label);
        }
        this.armed = false;
        super.border_style = this.old_shadowtype;
        final Graphics graphics = this.getGraphics();
        final Dimension size = this.size();
        if (graphics != null) {
            this.repaint(graphics, 0, 0, size.width, size.height);
        }
        for (int j = 0; j < this.buttonListeners.size(); ++j) {
            ((JCButtonListener)this.buttonListeners.elementAt(j)).buttonDisarmEnd(jcButtonEvent);
        }
    }
    
    public void clickAction(Event event) {
        final String actionCommand = this.getActionCommand();
        if (event == null) {
            event = new Event(this, 0, actionCommand);
        }
        final JCActionEvent jcActionEvent = new JCActionEvent(this, event.id, actionCommand, event.modifiers);
        for (int i = 0; i < this.actionListeners.size(); ++i) {
            ((JCActionListener)this.actionListeners.elementAt(i)).actionPerformed(jcActionEvent);
        }
    }
    
    public synchronized void layout() {
        super.layout();
        if (this.getPeer() == null) {
            return;
        }
        if (BWTUtil.isRight(super.alignment)) {
            final Rectangle label_rect = super.label_rect;
            label_rect.x -= this.arm_offset;
        }
        else if (BWTUtil.isCenter(super.alignment)) {
            final Rectangle label_rect2 = super.label_rect;
            label_rect2.x -= this.arm_offset / 2;
        }
        if (BWTUtil.isMiddle(super.alignment)) {
            final Rectangle label_rect3 = super.label_rect;
            label_rect3.y -= this.arm_offset / 2;
            return;
        }
        if (BWTUtil.isBottom(super.alignment)) {
            final Rectangle label_rect4 = super.label_rect;
            label_rect4.y -= this.arm_offset;
        }
    }
    
    protected void drawValue(final Graphics graphics, final Object o) {
        if (this.armed && this.arm_label != null) {
            this.layout(this.arm_label);
            graphics.translate(this.arm_offset, this.arm_offset);
            super.drawValue(graphics, this.arm_label);
        }
        else {
            if (this.armed) {
                graphics.translate(this.arm_offset, this.arm_offset);
            }
            super.drawValue(graphics, o);
        }
        if (this.armed) {
            graphics.translate(-this.arm_offset, -this.arm_offset);
        }
    }
    
    protected void drawHighlight(final Graphics graphics, final boolean b) {
        super.drawHighlight(graphics, b);
        final int n = 4;
        final int n2 = 4;
        final int width = this.size().width;
        final int height = this.size().height;
        Color foreground = b ? super.highlight_color : this.getBackground();
        if (foreground == null) {
            foreground = this.getForeground();
        }
        graphics.setColor(foreground);
        if (b) {
            BWTUtil.drawDashedRect(graphics, n, n2, width - 2 * n - 1, height - 2 * n2 - 1);
        }
        else {
            graphics.drawRect(n, n2, width - 2 * n - 1, height - 2 * n2 - 1);
        }
        if (this.is_action_button && super.highlight > 0) {
            int n3 = 0;
            int n4 = 0;
            int width2 = this.size().width;
            int height2 = this.size().height;
            graphics.setColor((this.getHighlightColor() != null) ? this.getHighlightColor() : Color.black);
            for (int i = 0; i < super.highlight; ++i, ++n3, ++n4, width2 -= 2, height2 -= 2) {
                graphics.drawRect(n3, n4, width2 - 1, height2 - 1);
            }
        }
    }
    
    public boolean isArmed() {
        return this.armed;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.inside_button = true;
        if (!this.isEnabled()) {
            return false;
        }
        if (!this.armed) {
            return false;
        }
        this.armAction(event);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.inside_button = false;
        if (!this.armed) {
            return false;
        }
        this.disarmAction(event);
        return this.armed = true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (this.inside(n, n2) && !this.inside_button) {
            this.mouseEnter(event, n, n2);
        }
        else if (!this.inside(n, n2) && this.inside_button) {
            this.mouseExit(event, n, n2);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (BWTUtil.getMouseButton(event) != 1) {
            return false;
        }
        if (this.armed) {
            return true;
        }
        if (!this.inside(n, n2)) {
            return false;
        }
        if (!this.isEnabled()) {
            return false;
        }
        super.mouseDown(event, n, n2);
        this.armAction(event);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (BWTUtil.getMouseButton(event) != 1) {
            return false;
        }
        if (this.inside(n, n2) && this.armed) {
            this.disarmAction(event);
            this.clickAction(event);
        }
        else if (this.armed) {
            this.armed = false;
            this.repaint();
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        super.keyDown(event, n);
        if (event.key == 10 || (char)event.key == ' ') {
            this.armAction(event);
            this.getToolkit().sync();
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex) {}
            this.disarmAction(event);
            this.clickAction(event);
            this.getToolkit().sync();
            return true;
        }
        return false;
    }
    
    protected int preferredWidth() {
        return Math.max(20, Math.max(super.label_width + this.arm_offset, BWTUtil.getWidth(this.arm_label, this) + this.arm_offset));
    }
    
    protected int preferredHeight() {
        return Math.max(20, Math.max(super.label_height + this.arm_offset, BWTUtil.getHeight(this.arm_label, this) + this.arm_offset));
    }
}
