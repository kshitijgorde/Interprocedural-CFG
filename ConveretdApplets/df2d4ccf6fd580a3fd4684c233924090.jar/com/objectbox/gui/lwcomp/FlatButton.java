// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import com.sun.java.swing.GrayFilter;
import java.awt.event.MouseEvent;
import java.awt.Container;
import com.objectbox.runner.util.JBLogger;
import java.awt.AWTEvent;
import com.objectbox.runner.gui.JBee;
import java.awt.event.FocusEvent;
import java.awt.Graphics;
import java.io.InputStream;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.beans.PropertyChangeSupport;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Component;

public class FlatButton extends Component implements DelayedInvocationCallBack
{
    boolean toggle;
    boolean pressed;
    boolean hasfocus;
    public boolean rightButtonPush;
    private String label;
    private Image img;
    private Image disabledimg;
    private Image originalimg;
    private Image submenuicon;
    private int imagepos;
    protected transient ActionListener aActionListener;
    public static final int NORTH = 0;
    public static final int SOUTH = 1;
    public static final int EAST = 2;
    public static final int WEST = 3;
    public static final int CENTER = 4;
    protected transient OnActiveListener aOnActiveListener;
    protected transient PropertyChangeSupport propertyChange;
    private Object fieldUserObject;
    private Dimension fieldFixedsize;
    protected boolean usefixedsize;
    private long now;
    private boolean shallfire;
    private Dimension inset;
    private static final Font font;
    private static final Color skin;
    private boolean fieldHasborder;
    private boolean submenu;
    
    static {
        font = new Font("SansSerif", 1, 12);
        skin = new Color(255, 226, 198);
    }
    
    public FlatButton() {
        this("");
    }
    
    public FlatButton(final String label) {
        this.toggle = false;
        this.pressed = false;
        this.hasfocus = false;
        this.rightButtonPush = false;
        this.label = "Button";
        this.img = null;
        this.disabledimg = null;
        this.originalimg = null;
        this.submenuicon = null;
        this.imagepos = 0;
        this.aActionListener = null;
        this.aOnActiveListener = null;
        this.fieldUserObject = new Object();
        this.fieldFixedsize = new Dimension();
        this.usefixedsize = false;
        this.now = -1L;
        this.shallfire = true;
        this.inset = new Dimension(2, 6);
        this.fieldHasborder = false;
        this.submenu = false;
        this.enableEvents(52L);
        this.label = label;
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.aActionListener = AWTEventMulticaster.add(this.aActionListener, actionListener);
    }
    
    public void addNotify() {
        super.addNotify();
        final Dimension size = this.getSize();
        if (size.width == 0 || size.height == 0) {
            this.setSize(this.getPreferredSize());
        }
    }
    
    public void addOnActiveListener(final OnActiveListener onActiveListener) {
        this.aOnActiveListener = OnActiveEventMulticaster.add(this.aOnActiveListener, onActiveListener);
    }
    
    public synchronized void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().addPropertyChangeListener(propertyChangeListener);
    }
    
    public boolean contains(final int n, final int n2) {
        return new Rectangle(this.getSize().width, this.getSize().height).contains(n, n2);
    }
    
    public void delayedInvoke() {
        if (this.isVisible()) {
            this.fireOnActive(new OnActiveEvent(this));
        }
    }
    
    public void finalize() {
        if (this.img != null) {
            this.img.flush();
        }
        if (this.disabledimg != null) {
            this.disabledimg.flush();
        }
        if (this.originalimg != null) {
            this.originalimg.flush();
        }
        if (this.submenuicon != null) {
            this.submenuicon.flush();
        }
    }
    
    protected void fireActionPerformed(final ActionEvent actionEvent) {
        if (this.aActionListener == null) {
            return;
        }
        this.aActionListener.actionPerformed(actionEvent);
    }
    
    protected void fireOnActive(final OnActiveEvent onActiveEvent) {
        if (this.aOnActiveListener == null) {
            return;
        }
        this.aOnActiveListener.onActive(onActiveEvent);
    }
    
    public void firePropertyChange(final String s, final Object o, final Object o2) {
        this.getPropertyChange().firePropertyChange(s, o, o2);
    }
    
    public Dimension getFixedsize() {
        return this.fieldFixedsize;
    }
    
    public boolean getHasborder() {
        return this.fieldHasborder;
    }
    
    public Image getImage() {
        return this.img;
    }
    
    public Dimension getInset() {
        return this.inset;
    }
    
    public boolean getIsSubmenu() {
        return this.submenu;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public Dimension getMaximumSize() {
        return this.getTheSize();
    }
    
    public Dimension getMinimumSize() {
        return this.getTheSize();
    }
    
    public Dimension getPreferredSize() {
        return this.getTheSize();
    }
    
    protected PropertyChangeSupport getPropertyChange() {
        if (this.propertyChange == null) {
            this.propertyChange = new PropertyChangeSupport(this);
        }
        return this.propertyChange;
    }
    
    public Dimension getTheSize() {
        final Font font = this.getFont();
        if (this.usefixedsize) {
            return this.fieldFixedsize;
        }
        if (font == null) {
            return this.getMinimumSize();
        }
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        int n = 0;
        int n2 = 0;
        if (this.img != null) {
            final int height = this.img.getHeight(this);
            final int width = this.img.getWidth(this);
            switch (this.imagepos) {
                case 0: {
                    n = height + fontMetrics.getHeight();
                    n2 = Math.max(width, fontMetrics.stringWidth(this.label));
                    break;
                }
                case 1: {
                    n = height + fontMetrics.getHeight();
                    n2 = Math.max(width, fontMetrics.stringWidth(this.label));
                    break;
                }
                case 2: {
                    n = Math.max(height, fontMetrics.getHeight());
                    n2 = width + fontMetrics.stringWidth(this.label);
                    break;
                }
                case 3: {
                    n = Math.max(height, fontMetrics.getHeight());
                    n2 = width + fontMetrics.stringWidth(this.label);
                    break;
                }
                case 4: {
                    n = Math.max(height, fontMetrics.getHeight());
                    n2 = width + fontMetrics.stringWidth(this.label);
                    break;
                }
            }
            return new Dimension(n2 + this.inset.width + this.inset.width, n + this.inset.height + this.inset.height);
        }
        return new Dimension(fontMetrics.stringWidth(this.label) + this.inset.width + this.inset.width, fontMetrics.getHeight() + this.inset.height + this.inset.height);
    }
    
    public Object getUserObject() {
        return this.fieldUserObject;
    }
    
    public Image loadImageFromResource(final String s) {
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
            final DataInputStream dataInputStream = new DataInputStream(resourceAsStream);
            if (resourceAsStream != null) {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    try {
                        byteArrayOutputStream.write(dataInputStream.readByte());
                    }
                    catch (IOException ex) {
                        break;
                    }
                }
                final byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                dataInputStream.close();
                final Image image = Toolkit.getDefaultToolkit().createImage(byteArray);
                final MediaTracker mediaTracker = new MediaTracker(this);
                mediaTracker.addImage(image, 0);
                mediaTracker.waitForID(0);
                return image;
            }
        }
        catch (Exception ex2) {
            return null;
        }
        return null;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setFont(FlatButton.font);
        if (this.getSize().width <= 1 || this.getSize().height <= 1) {
            this.setSize(this.getPreferredSize());
        }
        int n = 0;
        final int n2 = this.getSize().width - 0;
        final int n3 = this.getSize().height - 0;
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int stringWidth = fontMetrics.stringWidth(this.label);
        final int height = fontMetrics.getHeight();
        fontMetrics.getAscent();
        final int descent = fontMetrics.getDescent();
        final Color color = (this.toggle && this.isEnabled()) ? FlatButton.skin : this.getBackground().darker().darker();
        if (this.pressed && this.isEnabled()) {
            graphics.setColor(this.getBackground());
            graphics.fill3DRect(0, 0, n2, n3, false);
            n = 1;
        }
        else if (this.toggle && this.isEnabled()) {
            graphics.setColor(this.getBackground());
            graphics.fill3DRect(0, 0, n2, n3, true);
            n = -1;
        }
        else {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, n2, n3);
        }
        if (this.img != null) {
            final int height2 = this.img.getHeight(this);
            final int width = this.img.getWidth(this);
            graphics.setColor(color);
            switch (this.imagepos) {
                case 0: {
                    graphics.drawImage(this.img, n2 / 2 - width / 2 + n, this.inset.height + n, this);
                    if (!this.isEnabled()) {
                        final Color color2 = graphics.getColor();
                        graphics.setColor(Color.white);
                        graphics.drawString(this.label, n2 / 2 - stringWidth / 2 + n + 1, n3 - this.inset.height - descent + n + 1);
                        graphics.setColor(color2);
                    }
                    graphics.drawString(this.label, n2 / 2 - stringWidth / 2 + n, n3 - this.inset.height - descent + n);
                    break;
                }
                case 1: {
                    graphics.drawImage(this.img, n2 / 2 - width / 2, n3 - this.inset.height - height2 - 4, this);
                    if (!this.isEnabled()) {
                        final Color color3 = graphics.getColor();
                        graphics.setColor(Color.white);
                        graphics.drawString(this.label, n2 / 2 - stringWidth / 2 + n + 1, n3 - this.inset.height - descent + n + 1);
                        graphics.setColor(color3);
                    }
                    graphics.drawString(this.label, n2 / 2 - stringWidth / 2 + n, height + descent + n);
                    break;
                }
                case 2: {
                    graphics.drawImage(this.img, n2 - this.inset.width - width - 4 + 2 + n, n3 / 2 - height2 / 2 + n, this);
                    if (!this.isEnabled()) {
                        final Color color4 = graphics.getColor();
                        graphics.setColor(Color.white);
                        graphics.drawString(this.label, n2 / 2 - stringWidth / 2 + n + 1, n3 - this.inset.height - descent + n + 1);
                        graphics.setColor(color4);
                    }
                    graphics.drawString(this.label, this.inset.width + n, n3 / 2 + height / 2 - descent + n);
                    break;
                }
                case 3: {
                    graphics.drawImage(this.img, this.inset.width + n, n3 / 2 - height2 / 2 + n, this);
                    if (!this.isEnabled()) {
                        final Color color5 = graphics.getColor();
                        graphics.setColor(Color.white);
                        graphics.drawString(this.label, n2 / 2 - stringWidth / 2 + n + 1, n3 - this.inset.height - descent + n + 1);
                        graphics.setColor(color5);
                    }
                    graphics.drawString(this.label, width + this.inset.width + 4 + n, n3 / 2 + height / 2 - descent + n);
                    break;
                }
                case 4: {
                    graphics.drawImage(this.img, n2 / 2 - width / 2 + n, n3 / 2 - height2 / 2 + n, this);
                    if (!this.isEnabled()) {
                        final Color color6 = graphics.getColor();
                        graphics.setColor(Color.white);
                        graphics.drawString(this.label, n2 / 2 - stringWidth / 2 + n + 1, n3 - this.inset.height - descent + n + 1);
                        graphics.setColor(color6);
                    }
                    graphics.drawString(this.label, n2 - this.inset.width - stringWidth - 4 + n, n3 / 2 + height / 2 - descent + n);
                    break;
                }
            }
        }
        else {
            if (!this.isEnabled()) {
                graphics.setColor(Color.white);
                graphics.drawString(this.label, n2 / 2 - stringWidth / 2 + n + 1, n3 / 2 + height / 2 - descent + n + 1);
            }
            graphics.setColor(color);
            graphics.drawString(this.label, n2 / 2 - stringWidth / 2 + n, n3 / 2 + height / 2 - descent + n);
        }
        if (this.fieldHasborder) {
            graphics.setColor(this.getBackground().darker());
            graphics.drawRect(0, 0, n2 - 2, n3 - 2);
            graphics.setColor(this.getBackground().brighter());
            graphics.drawRect(1, 1, n2 - 1, n3 - 1);
        }
        if (this.submenu && this.submenuicon != null) {
            graphics.setColor(this.getBackground().darker());
            graphics.drawImage(this.submenuicon, n2 - this.submenuicon.getWidth(this) - this.inset.width, n3 / 2 - this.submenuicon.getHeight(this) / 2, this);
        }
    }
    
    public void processFocusEvent(final FocusEvent focusEvent) {
        try {
            if (JBee.OS_type == 1) {
                switch (focusEvent.getID()) {
                    case 1004: {
                        final Container parent = this.getParent().getParent();
                        if (parent instanceof JBPopupMenu) {
                            ((JBPopupMenu)parent).processEvent(focusEvent);
                        }
                        break;
                    }
                    case 1005: {
                        final Container parent2 = this.getParent().getParent();
                        if (parent2 instanceof JBPopupMenu) {
                            ((JBPopupMenu)parent2).processEvent(focusEvent);
                            break;
                        }
                        break;
                    }
                }
            }
            else {
                switch (focusEvent.getID()) {
                    case 1005: {
                        final Container parent3 = this.getParent().getParent();
                        if (parent3 instanceof JBee) {
                            ((JBee)parent3).closeMenu();
                            break;
                        }
                        break;
                    }
                }
            }
            super.processFocusEvent(focusEvent);
        }
        catch (Throwable t) {
            JBLogger.log(t.toString());
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (!this.isEnabled()) {
            return;
        }
        switch (mouseEvent.getID()) {
            case 501: {
                this.rightButtonPush = ((mouseEvent.getModifiers() & 0x4) != 0x0);
                this.pressed = true;
                this.toggle = false;
                this.requestFocus();
                this.repaint();
                break;
            }
            case 502: {
                if (this.contains(mouseEvent.getX(), mouseEvent.getY())) {
                    this.fireActionPerformed(new ActionEvent(this, 1001, this.label));
                }
                if (this.pressed) {
                    this.pressed = false;
                    this.toggle = true;
                    this.repaint();
                }
                break;
            }
            case 504: {
                this.shallfire = true;
                this.now = System.currentTimeMillis();
                this.toggle = true;
                this.repaint();
                DelayedInvocation.getInstance().setCallback(this, 500L);
                break;
            }
            case 505: {
                this.now = -1L;
                if (this.pressed) {
                    this.pressed = false;
                    this.repaint();
                    break;
                }
                this.toggle = false;
                this.repaint();
                DelayedInvocation.getInstance().setCallback(null, 10000L);
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            default: {
                super.processMouseEvent(mouseEvent);
            }
        }
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.aActionListener = AWTEventMulticaster.remove(this.aActionListener, actionListener);
    }
    
    public void removeOnActiveListener(final OnActiveListener onActiveListener) {
        this.aOnActiveListener = OnActiveEventMulticaster.remove(this.aOnActiveListener, onActiveListener);
    }
    
    public synchronized void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
        this.getPropertyChange().removePropertyChangeListener(propertyChangeListener);
    }
    
    public void setEnabled(final boolean enabled) {
        if (!enabled) {
            this.img = this.disabledimg;
        }
        else {
            this.img = this.originalimg;
        }
        if (enabled != this.isEnabled()) {
            this.toggle = false;
            super.setEnabled(enabled);
            this.repaint();
        }
    }
    
    public void setFixedsize(final Dimension fieldFixedsize) {
        final Dimension fieldFixedsize2 = this.fieldFixedsize;
        this.fieldFixedsize = fieldFixedsize;
        this.usefixedsize = true;
        this.firePropertyChange("fixedsize", fieldFixedsize2, fieldFixedsize);
        this.repaint();
    }
    
    public void setHasborder(final boolean fieldHasborder) {
        final boolean fieldHasborder2 = this.fieldHasborder;
        this.fieldHasborder = fieldHasborder;
        this.firePropertyChange("hasborder", new Boolean(fieldHasborder2), new Boolean(fieldHasborder));
        this.repaint();
    }
    
    public void setImage(final Image image) {
        this.originalimg = image;
        this.disabledimg = GrayFilter.createDisabledImage(image);
        if (this.isEnabled()) {
            this.img = this.originalimg;
        }
        else {
            this.img = this.disabledimg;
        }
        this.imagepos = 0;
    }
    
    public void setImage(final Image image, final int imagepos) {
        this.originalimg = image;
        this.disabledimg = GrayFilter.createDisabledImage(image);
        if (this.isEnabled()) {
            this.img = this.originalimg;
        }
        else {
            this.img = this.disabledimg;
        }
        this.imagepos = imagepos;
    }
    
    public void setImagePos(final int imagepos) {
        this.imagepos = imagepos;
    }
    
    public boolean setImageResource(final String s, final int n) {
        this.setImage(this.loadImageFromResource(s), n);
        return true;
    }
    
    public void setInset(final Dimension inset) {
        this.inset = inset;
    }
    
    public void setIsSubmenu(final boolean submenu) {
        this.submenu = submenu;
        this.submenuicon = this.loadImageFromResource("/images/TreeCollapsed.gif");
    }
    
    public void setLabel(final String label) {
        this.label = label;
        this.repaint();
    }
    
    public void setUserObject(final Object fieldUserObject) {
        this.firePropertyChange("userObject", this.fieldUserObject, this.fieldUserObject = fieldUserObject);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
