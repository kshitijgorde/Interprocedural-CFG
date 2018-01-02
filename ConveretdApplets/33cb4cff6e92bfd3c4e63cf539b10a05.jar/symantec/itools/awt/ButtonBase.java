// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt;

import java.beans.PropertyChangeEvent;
import java.beans.Beans;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusEvent;
import java.io.Serializable;
import java.awt.event.FocusAdapter;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
import java.beans.PropertyChangeListener;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.beans.VetoableChangeListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import symantec.itools.resources.ErrorsBundle;
import java.awt.Container;
import java.applet.Applet;
import symantec.itools.awt.util.ColorUtils;
import symantec.itools.util.GeneralUtils;
import symantec.itools.lang.OS;
import java.beans.PropertyVetoException;
import symantec.itools.beans.PropertyChangeSupport;
import symantec.itools.beans.VetoableChangeSupport;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.applet.AppletContext;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import symantec.itools.util.Timer;
import java.awt.Color;
import java.awt.Canvas;

public abstract class ButtonBase extends Canvas
{
    protected transient boolean pressed;
    protected transient boolean released;
    protected transient boolean inButton;
    protected boolean notifyWhilePressed;
    protected transient boolean running;
    protected transient boolean notified;
    protected boolean showFocus;
    protected boolean useOffset;
    protected boolean showURLStatus;
    protected transient boolean isAdded;
    protected int bevel;
    protected int tempBevelHeight;
    protected int notifyDelay;
    protected int pressedAdjustment;
    protected String frame;
    protected Color borderColor;
    protected Color buttonColor;
    protected Timer notifyTimer;
    protected transient Image buttonImage;
    protected transient Graphics buttonImageGraphics;
    protected URL linkURL;
    protected transient AppletContext context;
    protected transient ResourceBundle errors;
    String actionCommand;
    ActionListener actionListener;
    transient boolean hasFocus;
    private Color hilightColor;
    private Color pressedHilightColor;
    private Color disabledHilightColor;
    private Color shadowColor;
    private Color pressedShadowColor;
    private Color disabledShadowColor;
    private Color disabledBorderColor;
    private Color disabledButtonColor;
    private Color pressedButtonColor;
    private Key key;
    private Focus focus;
    private Action action;
    private Mouse mouse;
    private BevelVeto bevelVeto;
    private FrameVeto frameVeto;
    private VetoableChangeSupport vetos;
    private PropertyChangeSupport changes;
    
    protected ButtonBase() {
        this.showFocus = false;
        this.hasFocus = false;
        this.action = new Action();
        this.vetos = new VetoableChangeSupport(this);
        this.changes = new PropertyChangeSupport(this);
        this.pressed = false;
        this.released = true;
        this.notifyWhilePressed = false;
        this.running = false;
        this.notified = false;
        this.useOffset = false;
        this.showURLStatus = true;
        this.isAdded = false;
        this.notifyTimer = null;
        this.notifyDelay = 1000;
        this.bevel = 2;
        this.pressedAdjustment = 0;
        this.tempBevelHeight = this.bevel;
        try {
            this.setBorderColor(Color.black);
            this.setButtonColor(Color.lightGray);
        }
        catch (PropertyVetoException ex) {}
        if (OS.isWindows()) {
            try {
                this.setShowFocus(true);
            }
            catch (PropertyVetoException ex2) {}
        }
    }
    
    public void setBevelHeight(final int height) throws PropertyVetoException {
        if (this.isAdded) {
            if (this.bevel != height) {
                final Integer oldValue = new Integer(this.bevel);
                final Integer newValue = new Integer(height);
                this.vetos.fireVetoableChange("bevelHeight", oldValue, newValue);
                this.bevel = height;
                this.tempBevelHeight = height;
                this.repaint();
                this.changes.firePropertyChange("bevelHeight", oldValue, newValue);
            }
        }
        else {
            this.tempBevelHeight = height;
        }
    }
    
    public int getBevelHeight() {
        return this.isAdded ? this.bevel : this.tempBevelHeight;
    }
    
    public void setNotifyWhilePressed(final boolean flag) throws PropertyVetoException {
        if (this.notifyWhilePressed != flag) {
            final Boolean oldValue = new Boolean(this.notifyWhilePressed);
            final Boolean newValue = new Boolean(flag);
            this.vetos.fireVetoableChange("notifyWhilePressed", oldValue, newValue);
            this.notifyWhilePressed = flag;
            if (this.notifyWhilePressed) {
                (this.notifyTimer = new Timer(this.notifyDelay, true)).addActionListener(this.action);
            }
            else if (this.notifyTimer != null) {
                this.notifyTimer = null;
            }
            this.changes.firePropertyChange("notifyWhilePressed", oldValue, newValue);
        }
    }
    
    public boolean isNotifyWhilePressed() {
        return this.notifyWhilePressed;
    }
    
    public boolean getNotifyWhilePressed() {
        return this.isNotifyWhilePressed();
    }
    
    public void setNotifyDelay(final int delay) throws PropertyVetoException {
        if (this.notifyDelay != delay) {
            final Integer oldValue = new Integer(this.notifyDelay);
            final Integer newValue = new Integer(delay);
            this.vetos.fireVetoableChange("notifyDelay", oldValue, newValue);
            this.notifyDelay = delay;
            if (this.notifyTimer != null) {
                this.notifyTimer.setDelay(this.notifyDelay);
            }
            this.changes.firePropertyChange("notifyDelay", oldValue, newValue);
        }
    }
    
    public int getNotifyDelay() {
        return this.notifyDelay;
    }
    
    public void setUseOffset(final boolean flag) throws PropertyVetoException {
        if (this.useOffset != flag) {
            final Boolean oldValue = new Boolean(this.useOffset);
            final Boolean newValue = new Boolean(flag);
            this.vetos.fireVetoableChange("useOffset", oldValue, newValue);
            this.useOffset = flag;
            this.repaint();
            this.changes.firePropertyChange("useOffset", oldValue, newValue);
        }
    }
    
    public boolean isUseOffset() {
        return this.useOffset;
    }
    
    public void setShowFocus(final boolean flag) throws PropertyVetoException {
        if (this.showFocus != flag) {
            final Boolean oldValue = new Boolean(this.showFocus);
            final Boolean newValue = new Boolean(flag);
            this.vetos.fireVetoableChange("showFocus", oldValue, newValue);
            this.showFocus = flag;
            this.changes.firePropertyChange("showFocus", oldValue, newValue);
        }
    }
    
    public boolean isShowFocus() {
        return this.showFocus;
    }
    
    public boolean getShowFocus() {
        return this.isShowFocus();
    }
    
    public void setShowURLStatus(final boolean flag) throws PropertyVetoException {
        if (this.showURLStatus != flag) {
            final Boolean oldValue = new Boolean(this.showURLStatus);
            final Boolean newValue = new Boolean(flag);
            this.vetos.fireVetoableChange("showURLStatus", oldValue, newValue);
            this.showURLStatus = flag;
            this.changes.firePropertyChange("showURLStatus", oldValue, newValue);
        }
    }
    
    public boolean isShowURLStatus() {
        return this.showURLStatus;
    }
    
    public void setBorderColor(final Color color) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.borderColor, color)) {
            final Color oldValue = this.borderColor;
            this.vetos.fireVetoableChange("borderColor", oldValue, color);
            this.borderColor = color;
            try {
                this.disabledBorderColor = ColorUtils.lighten(this.borderColor, 0.466);
            }
            catch (IllegalArgumentException ex) {}
            this.repaint();
            this.changes.firePropertyChange("borderColor", oldValue, color);
        }
    }
    
    public Color getBorderColor() {
        return this.borderColor;
    }
    
    public void setButtonColor(final Color color) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.buttonColor, color)) {
            final Color oldValue = this.buttonColor;
            this.vetos.fireVetoableChange("buttonColor", oldValue, color);
            this.buttonColor = color;
            try {
                this.hilightColor = ColorUtils.lighten(this.buttonColor, 0.6);
                this.pressedHilightColor = ColorUtils.darken(this.buttonColor, 0.58);
                this.disabledHilightColor = ColorUtils.lighten(this.buttonColor, 0.666);
                this.shadowColor = ColorUtils.darken(this.buttonColor, 0.25);
                this.pressedShadowColor = ColorUtils.darken(this.buttonColor, 0.1);
                this.disabledShadowColor = ColorUtils.darken(this.buttonColor, 0.166);
                this.disabledButtonColor = ColorUtils.lighten(this.buttonColor, 0.333);
                this.pressedButtonColor = ColorUtils.darken(this.buttonColor, 0.25);
            }
            catch (IllegalArgumentException ex) {}
            this.repaint();
            this.changes.firePropertyChange("buttonColor", oldValue, color);
        }
    }
    
    public Color getButtonColor() {
        return this.buttonColor;
    }
    
    public void setLinkURL(final URL url) throws PropertyVetoException {
        if (!GeneralUtils.objectsEqual(this.linkURL, url)) {
            final URL oldValue = this.linkURL;
            this.vetos.fireVetoableChange("linkURL", oldValue, url);
            this.linkURL = url;
            this.context = null;
            this.changes.firePropertyChange("linkURL", oldValue, url);
        }
    }
    
    public URL getLinkURL() {
        return this.linkURL;
    }
    
    public void setFrame(final String newFrame) throws PropertyVetoException {
        final String oldValue = this.frame;
        this.vetos.fireVetoableChange("frame", oldValue, newFrame);
        this.frame = newFrame;
        this.changes.firePropertyChange("frame", oldValue, newFrame);
    }
    
    public String getFrame() {
        return this.frame;
    }
    
    public void validate() {
        if (this.context == null) {
            for (Container c = this.getParent(); c != null; c = c.getParent()) {
                if (c instanceof Applet) {
                    this.setAppletContext(((Applet)c).getAppletContext());
                    break;
                }
            }
        }
    }
    
    public void setEnabled(final boolean flag) {
        if (this.isEnabled() != flag) {
            if (flag) {
                super.enable();
                this.pressed = false;
                this.pressedAdjustment = 0;
            }
            else {
                super.disable();
                if (this.notifyTimer != null) {
                    this.notifyTimer.stop();
                }
                this.pressed = false;
                this.pressedAdjustment = 0;
            }
            this.repaint();
        }
    }
    
    public void enable() {
        this.setEnabled(true);
    }
    
    public void disable() {
        this.setEnabled(false);
    }
    
    public synchronized void addNotify() {
        try {
            this.errors = ResourceBundle.getBundle("symantec.itools.resources.ErrorsBundle");
        }
        catch (Throwable t) {
            this.errors = new ErrorsBundle();
        }
        if (this.focus == null) {
            this.addFocusListener(this.focus = new Focus());
        }
        if (this.key == null) {
            this.addKeyListener(this.key = new Key());
        }
        if (this.mouse == null) {
            this.addMouseListener(this.mouse = new Mouse());
        }
        if (this.bevelVeto == null) {
            this.addBevelHeightListener(this.bevelVeto = new BevelVeto());
        }
        if (this.frameVeto == null) {
            this.addFrameListener(this.frameVeto = new FrameVeto());
        }
        super.addNotify();
        this.isAdded = true;
        this.verifyContstrainedPropertyValues();
        if (this.context == null) {
            for (Container c = this.getParent(); c != null; c = c.getParent()) {
                if (c instanceof Applet) {
                    this.setAppletContext(((Applet)c).getAppletContext());
                    break;
                }
            }
        }
    }
    
    public synchronized void removeNotify() {
        if (this.focus != null) {
            this.removeFocusListener(this.focus);
            this.focus = null;
        }
        if (this.key != null) {
            this.removeKeyListener(this.key);
            this.key = null;
        }
        if (this.mouse != null) {
            this.removeMouseListener(this.mouse);
            this.mouse = null;
        }
        if (this.bevelVeto != null) {
            this.removeBevelHeightListener(this.bevelVeto);
            this.bevelVeto = null;
        }
        if (this.frameVeto != null) {
            this.removeFrameListener(this.frameVeto);
            this.frameVeto = null;
        }
        super.removeNotify();
        this.isAdded = false;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        this.updateButtonImage();
        g.drawImage(this.buttonImage, 0, 0, this);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.bevel + this.bevel + 2, this.bevel + this.bevel + 2);
    }
    
    public Dimension preferredSize() {
        return this.getPreferredSize();
    }
    
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }
    
    public Dimension minimumSize() {
        return this.getMinimumSize();
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public void simulateClick() {
        this.requestFocus();
        this.inButton = true;
        this.pressed = true;
        this.released = false;
        if (this.useOffset) {
            this.pressedAdjustment = this.bevel;
        }
        this.paint(this.getGraphics());
        try {
            Thread.sleep(120L);
        }
        catch (InterruptedException ex) {}
        this.inButton = false;
        this.pressed = false;
        this.pressedAdjustment = 0;
        this.linkToURL();
        this.sourceActionEvent();
        this.released = true;
        this.repaint();
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener(listener);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener(listener);
    }
    
    public synchronized void addVetoableChangeListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener(listener);
    }
    
    public synchronized void removeVetoableChangeListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener(listener);
    }
    
    public synchronized void addBevelHeightListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("bevelHeight", listener);
    }
    
    public synchronized void removeBevelHeightListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("bevelHeight", listener);
    }
    
    public synchronized void addBevelHeightListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("bevelHeight", listener);
    }
    
    public synchronized void removeBevelHeightListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("bevelHeight", listener);
    }
    
    public synchronized void addFrameListener(final PropertyChangeListener listener) {
        this.changes.addPropertyChangeListener("frame", listener);
    }
    
    public synchronized void removeFrameListener(final PropertyChangeListener listener) {
        this.changes.removePropertyChangeListener("frame", listener);
    }
    
    public synchronized void addFrameListener(final VetoableChangeListener listener) {
        this.vetos.addVetoableChangeListener("frame", listener);
    }
    
    public synchronized void removeFrameListener(final VetoableChangeListener listener) {
        this.vetos.removeVetoableChangeListener("frame", listener);
    }
    
    public void setActionCommand(final String command) throws PropertyVetoException {
        final String oldValue = this.actionCommand;
        this.vetos.fireVetoableChange("actionCommand", oldValue, command);
        this.actionCommand = command;
        this.changes.firePropertyChange("actionCommand", oldValue, command);
    }
    
    public String getActionCommand() {
        return this.actionCommand;
    }
    
    public synchronized void addActionListener(final ActionListener l) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, l);
    }
    
    public synchronized void removeActionListener(final ActionListener l) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, l);
    }
    
    protected void sourceActionEvent() {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, this.actionCommand));
        }
    }
    
    protected boolean isValidBevelSize(final int i) {
        final Dimension s = this.getSize();
        return i >= 0 && i < s.width / 2 && i < s.height / 2;
    }
    
    protected boolean isValidFrame(final String string) {
        return string == null || string.equals("") || (string.equals(GeneralUtils.frameTarget_self) || string.equals(GeneralUtils.frameTarget_parent) || string.equals(GeneralUtils.frameTarget_top) || string.equals(GeneralUtils.frameTarget_blank));
    }
    
    protected void linkToURL() {
        if (this.context != null && this.linkURL != null) {
            if (this.frame == null || this.frame.length() == 0) {
                this.context.showDocument(this.linkURL);
            }
            else {
                this.context.showDocument(this.linkURL, this.frame);
            }
        }
    }
    
    protected void verifyContstrainedPropertyValues() {
        try {
            this.setBevelHeight(this.tempBevelHeight);
        }
        catch (PropertyVetoException ex) {}
    }
    
    protected void setAppletContext(final AppletContext c) {
        this.context = c;
    }
    
    protected void updateButtonImage() {
        final Dimension s = this.getSize();
        final int width = s.width;
        final int height = s.height;
        int x = this.bevel + 1;
        int y = this.bevel + 1;
        final int w = width - 1;
        final int h = height - 1;
        final boolean raised = !this.pressed || !this.inButton;
        if (this.isButtonImageInvalid()) {
            this.buttonImage = this.createImage(width, height);
            try {
                final MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(this.buttonImage, 0);
                tracker.waitForID(0);
            }
            catch (InterruptedException ex) {}
        }
        this.buttonImageGraphics = this.buttonImage.getGraphics();
        final Color oldColor = this.buttonImageGraphics.getColor();
        Color tempBorderColor;
        Color fillColor;
        Color highlight1;
        Color highlight2;
        if (this.isEnabled()) {
            tempBorderColor = this.borderColor;
            if (raised) {
                fillColor = this.buttonColor;
                highlight1 = this.hilightColor;
                highlight2 = this.shadowColor;
            }
            else {
                fillColor = this.pressedButtonColor;
                highlight1 = this.pressedHilightColor;
                highlight2 = this.pressedShadowColor;
            }
        }
        else {
            tempBorderColor = this.disabledBorderColor;
            fillColor = this.disabledButtonColor;
            highlight1 = this.disabledHilightColor;
            highlight2 = this.disabledShadowColor;
        }
        if (!raised && this.useOffset) {
            this.buttonImageGraphics.setColor(fillColor);
            this.buttonImageGraphics.fillRect(x, y, w - x, h - y);
            this.buttonImageGraphics.setColor(highlight1);
            for (int i = 1; i <= this.bevel; ++i) {
                this.buttonImageGraphics.drawLine(i, i, i, h);
                this.buttonImageGraphics.drawLine(i, i, w, i);
            }
        }
        if (raised || !this.useOffset) {
            this.buttonImageGraphics.setColor(fillColor);
            this.buttonImageGraphics.fillRect(x, y, w - x, h - y);
            this.buttonImageGraphics.setColor(highlight1);
            for (int i = 1; i <= this.bevel; ++i) {
                this.buttonImageGraphics.drawLine(i, i, i, h - i);
                this.buttonImageGraphics.drawLine(i, i, w - i, i);
            }
            this.buttonImageGraphics.setColor(highlight2);
            for (int i = 1; i <= this.bevel; ++i) {
                this.buttonImageGraphics.drawLine(i, h - i, w - i, h - i);
                this.buttonImageGraphics.drawLine(w - i, i, w - i, h - i);
            }
        }
        this.buttonImageGraphics.setColor(tempBorderColor);
        this.buttonImageGraphics.drawLine(1, 0, w - 1, 0);
        this.buttonImageGraphics.drawLine(0, 1, 0, h - 1);
        this.buttonImageGraphics.drawLine(1, h, w - 1, h);
        this.buttonImageGraphics.drawLine(w, h - 1, w, 1);
        if (this.hasFocus && this.showFocus) {
            this.buttonImageGraphics.setColor(Color.darkGray);
            for (x = 3; x <= w - 3; x += 3) {
                this.buttonImageGraphics.drawLine(x, 3, x + 1, 3);
            }
            for (y = 3; y <= h - 3; y += 3) {
                this.buttonImageGraphics.drawLine(3, y, 3, y + 1);
            }
            for (x = 3; x <= w - 3; x += 3) {
                this.buttonImageGraphics.drawLine(x, h - 3, x + 1, h - 3);
            }
            for (y = 3; y <= h - 3; y += 3) {
                this.buttonImageGraphics.drawLine(w - 3, y, w - 3, y + 1);
            }
        }
        this.buttonImageGraphics.clipRect(this.bevel + 1, this.bevel + 1, width - this.bevel - this.bevel - 2, height - this.bevel - this.bevel - 2);
        this.buttonImageGraphics.setColor(oldColor);
    }
    
    protected boolean isButtonImageInvalid() {
        final Dimension s = this.getSize();
        return this.buttonImage == null || s.width != this.buttonImage.getWidth(this) || s.height != this.buttonImage.getHeight(this);
    }
    
    class Focus extends FocusAdapter implements Serializable
    {
        public void focusGained(final FocusEvent e) {
            ButtonBase.this.hasFocus = true;
            ButtonBase.this.repaint();
        }
        
        public void focusLost(final FocusEvent e) {
            ButtonBase.this.hasFocus = false;
            ButtonBase.this.repaint();
        }
    }
    
    class Key extends KeyAdapter implements Serializable
    {
        public void keyPressed(final KeyEvent evt) {
            final boolean isSpaceBar = (evt.getKeyCode() & 0x20) == 0x20;
            if (isSpaceBar && ButtonBase.this.hasFocus && ButtonBase.this.showFocus && !OS.isMacintosh()) {
                ButtonBase.this.inButton = true;
                ButtonBase.this.notified = false;
                if (ButtonBase.this.notifyTimer != null && ButtonBase.this.notifyWhilePressed && !ButtonBase.this.running) {
                    ButtonBase.this.running = true;
                    ButtonBase.this.notifyTimer.start();
                }
                ButtonBase.this.pressed = true;
                ButtonBase.this.released = false;
                if (ButtonBase.this.useOffset) {
                    ButtonBase.this.pressedAdjustment = ButtonBase.this.bevel;
                }
                ButtonBase.this.repaint();
            }
        }
        
        public void keyReleased(final KeyEvent evt) {
            final boolean isSpaceBar = (evt.getKeyCode() & 0x20) == 0x20;
            if (isSpaceBar && ButtonBase.this.hasFocus && ButtonBase.this.showFocus && !OS.isMacintosh()) {
                ButtonBase.this.inButton = false;
                if (ButtonBase.this.notifyTimer != null && ButtonBase.this.running) {
                    ButtonBase.this.running = false;
                    ButtonBase.this.notifyTimer.stop();
                }
                if (ButtonBase.this.pressed) {
                    ButtonBase.this.pressed = false;
                    ButtonBase.this.pressedAdjustment = 0;
                    if (!ButtonBase.this.notifyWhilePressed || !ButtonBase.this.notified) {
                        ButtonBase.this.linkToURL();
                        ButtonBase.this.sourceActionEvent();
                    }
                }
                ButtonBase.this.released = true;
                ButtonBase.this.repaint();
            }
        }
    }
    
    class Mouse extends MouseAdapter implements Serializable
    {
        public void mousePressed(final MouseEvent e) {
            ButtonBase.this.requestFocus();
            ButtonBase.this.notified = false;
            if (ButtonBase.this.notifyTimer != null && ButtonBase.this.notifyWhilePressed && !ButtonBase.this.running) {
                ButtonBase.this.running = true;
                ButtonBase.this.notifyTimer.start();
            }
            ButtonBase.this.pressed = true;
            ButtonBase.this.released = false;
            if (ButtonBase.this.useOffset) {
                ButtonBase.this.pressedAdjustment = ButtonBase.this.bevel;
            }
            ButtonBase.this.repaint();
        }
        
        public void mouseReleased(final MouseEvent e) {
            if (ButtonBase.this.notifyTimer != null && ButtonBase.this.running) {
                ButtonBase.this.running = false;
                ButtonBase.this.notifyTimer.stop();
            }
            if (ButtonBase.this.pressed) {
                ButtonBase.this.pressed = false;
                ButtonBase.this.pressedAdjustment = 0;
                if (!ButtonBase.this.notifyWhilePressed || !ButtonBase.this.notified) {
                    ButtonBase.this.linkToURL();
                    ButtonBase.this.sourceActionEvent();
                }
            }
            ButtonBase.this.released = true;
            if (ButtonBase.this.inButton) {
                ButtonBase.this.repaint();
            }
        }
        
        public void mouseEntered(final MouseEvent e) {
            ButtonBase.this.inButton = true;
            if (ButtonBase.this.showURLStatus && ButtonBase.this.context != null && ButtonBase.this.linkURL != null) {
                ButtonBase.this.context.showStatus(ButtonBase.this.linkURL.toString());
            }
            if (!ButtonBase.this.released) {
                ButtonBase.this.pressed = true;
                if (ButtonBase.this.useOffset) {
                    ButtonBase.this.pressedAdjustment = ButtonBase.this.bevel;
                }
                if (ButtonBase.this.notifyTimer != null && ButtonBase.this.notifyWhilePressed && !ButtonBase.this.running) {
                    ButtonBase.this.running = true;
                    ButtonBase.this.notifyTimer.start();
                }
                ButtonBase.this.repaint();
            }
        }
        
        public void mouseExited(final MouseEvent e) {
            ButtonBase.this.inButton = false;
            if (ButtonBase.this.notifyTimer != null && ButtonBase.this.running) {
                ButtonBase.this.running = false;
                ButtonBase.this.notifyTimer.stop();
            }
            if (ButtonBase.this.pressed) {
                ButtonBase.this.pressed = false;
                ButtonBase.this.pressedAdjustment = 0;
                ButtonBase.this.repaint();
            }
            if (ButtonBase.this.showURLStatus && ButtonBase.this.context != null && ButtonBase.this.linkURL != null) {
                ButtonBase.this.context.showStatus("");
            }
        }
    }
    
    class Action implements ActionListener, Serializable
    {
        public void actionPerformed(final ActionEvent e) {
            if (e.getSource() == ButtonBase.this.notifyTimer && ButtonBase.this.notifyWhilePressed && !Beans.isDesignTime()) {
                ButtonBase.this.notified = true;
                ButtonBase.this.sourceActionEvent();
            }
        }
    }
    
    class BevelVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final int i = (int)e.getNewValue();
            if (!ButtonBase.this.isValidBevelSize(i)) {
                throw new PropertyVetoException(String.valueOf(ButtonBase.this.errors.getString("InvalidBevelSize")) + i, e);
            }
        }
    }
    
    class FrameVeto implements VetoableChangeListener, Serializable
    {
        public void vetoableChange(final PropertyChangeEvent e) throws PropertyVetoException {
            final String string = (String)e.getNewValue();
            if (!ButtonBase.this.isValidFrame(string)) {
                throw new PropertyVetoException(String.valueOf(ButtonBase.this.errors.getString("InvalidFrame")) + string, e);
            }
        }
    }
}
