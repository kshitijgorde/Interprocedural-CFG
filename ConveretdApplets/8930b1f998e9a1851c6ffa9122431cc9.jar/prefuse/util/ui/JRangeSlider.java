// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import java.awt.event.KeyEvent;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.util.Iterator;
import javax.swing.DefaultBoundedRangeModel;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import javax.swing.BoundedRangeModel;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public class JRangeSlider extends JComponent implements MouseListener, MouseMotionListener, KeyListener
{
    public static final int VERTICAL = 0;
    public static final int HORIZONTAL = 1;
    public static final int LEFTRIGHT_TOPBOTTOM = 0;
    public static final int RIGHTLEFT_BOTTOMTOP = 1;
    public static final int PREFERRED_BREADTH = 16;
    public static final int PREFERRED_LENGTH = 300;
    protected static final int ARROW_SZ = 16;
    protected static final int ARROW_WIDTH = 8;
    protected static final int ARROW_HEIGHT = 4;
    protected BoundedRangeModel model;
    protected int orientation;
    protected int direction;
    protected boolean empty;
    protected int increment;
    protected int minExtent;
    protected ArrayList listeners;
    protected ChangeEvent changeEvent;
    protected ChangeListener lstnr;
    protected Color thumbColor;
    static final int PICK_NONE = 0;
    static final int PICK_LEFT_OR_TOP = 1;
    static final int PICK_THUMB = 2;
    static final int PICK_RIGHT_OR_BOTTOM = 3;
    int pick;
    int pickOffsetLow;
    int pickOffsetHigh;
    int mouse;
    
    public JRangeSlider(final int n, final int n2, final int n3, final int n4, final int n5) {
        this(new DefaultBoundedRangeModel(n3, n4, n, n2), n5, 0);
    }
    
    public JRangeSlider(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this(new DefaultBoundedRangeModel(n3, n4, n, n2), n5, n6);
    }
    
    public JRangeSlider(final BoundedRangeModel model, final int orientation, final int direction) {
        this.increment = 1;
        this.minExtent = 0;
        this.listeners = new ArrayList();
        this.changeEvent = null;
        this.thumbColor = new Color(150, 180, 220);
        super.setFocusable(true);
        this.model = model;
        this.orientation = orientation;
        this.direction = direction;
        this.setForeground(Color.LIGHT_GRAY);
        model.addChangeListener(this.lstnr = this.createListener());
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }
    
    protected ChangeListener createListener() {
        return new RangeSliderChangeListener();
    }
    
    public int getLowValue() {
        return this.model.getValue();
    }
    
    public void setLowValue(final int value) {
        this.model.setRangeProperties(value, this.model.getValue() - value + this.model.getExtent(), this.model.getMinimum(), this.model.getMaximum(), false);
        this.model.setValue(value);
    }
    
    public int getHighValue() {
        return this.model.getValue() + this.model.getExtent();
    }
    
    public void setHighValue(final int n) {
        this.model.setExtent(n - this.model.getValue());
    }
    
    public void setRange(final int n, final int n2) {
        this.model.setRangeProperties(n, n2 - n, this.model.getMinimum(), this.model.getMaximum(), false);
    }
    
    public int getMinimum() {
        return this.model.getMinimum();
    }
    
    public void setMinimum(final int minimum) {
        this.model.setMinimum(minimum);
    }
    
    public int getMaximum() {
        return this.model.getMaximum();
    }
    
    public void setMaximum(final int maximum) {
        this.model.setMaximum(maximum);
    }
    
    public void setMinExtent(final int minExtent) {
        this.minExtent = minExtent;
    }
    
    public void setEmpty(final boolean empty) {
        this.empty = empty;
        this.repaint();
    }
    
    public Color getThumbColor() {
        return this.thumbColor;
    }
    
    public void setThumbColor(final Color thumbColor) {
        this.thumbColor = thumbColor;
    }
    
    public BoundedRangeModel getModel() {
        return this.model;
    }
    
    public void setModel(final BoundedRangeModel model) {
        this.model.removeChangeListener(this.lstnr);
        (this.model = model).addChangeListener(this.lstnr);
        this.repaint();
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        if (!this.listeners.contains(changeListener)) {
            this.listeners.add(changeListener);
        }
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listeners.remove(changeListener);
    }
    
    protected void fireChangeEvent() {
        this.repaint();
        if (this.changeEvent == null) {
            this.changeEvent = new ChangeEvent(this);
        }
        final Iterator<ChangeListener> iterator = this.listeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().stateChanged(this.changeEvent);
        }
    }
    
    public Dimension getPreferredSize() {
        if (this.orientation == 0) {
            return new Dimension(16, 300);
        }
        return new Dimension(300, 16);
    }
    
    protected void customPaint(final Graphics2D graphics2D, final int n, final int n2) {
    }
    
    public void paintComponent(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        final int n = (int)bounds.getWidth() - 1;
        final int n2 = (int)bounds.getHeight() - 1;
        int screen = this.toScreen(this.getLowValue());
        int screen2 = this.toScreen(this.getHighValue());
        if (this.empty) {
            if (this.direction == 0) {
                screen = 16;
                screen2 = ((this.orientation == 0) ? (n2 - 16) : (n - 16));
            }
            else {
                screen = ((this.orientation == 0) ? (n2 - 16) : (n - 16));
                screen2 = 16;
            }
        }
        final Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setColor(this.getBackground());
        graphics2D.fillRect(0, 0, n, n2);
        graphics2D.setColor(this.getForeground());
        graphics2D.drawRect(0, 0, n, n2);
        this.customPaint(graphics2D, n, n2);
        graphics2D.setStroke(new BasicStroke(1.0f));
        if (this.orientation == 0) {
            if (this.direction == 0) {
                graphics2D.setColor(this.getForeground());
                graphics2D.fillRect(0, screen - 16, n, 15);
                this.paint3DRectLighting(graphics2D, 0, screen - 16, n, 15);
                if (this.thumbColor != null) {
                    graphics2D.setColor(this.thumbColor);
                    graphics2D.fillRect(0, screen, n, screen2 - screen - 1);
                    this.paint3DRectLighting(graphics2D, 0, screen, n, screen2 - screen - 1);
                }
                graphics2D.setColor(this.getForeground());
                graphics2D.fillRect(0, screen2, n, 15);
                this.paint3DRectLighting(graphics2D, 0, screen2, n, 15);
                graphics2D.setColor(Color.black);
                this.paintArrow(graphics2D, (n - 8) / 2.0, screen - 16 + 6.0, 8, 4, true);
                this.paintArrow(graphics2D, (n - 8) / 2.0, screen2 + 6.0, 8, 4, false);
            }
            else {
                graphics2D.setColor(this.getForeground());
                graphics2D.fillRect(0, screen, n, 15);
                this.paint3DRectLighting(graphics2D, 0, screen, n, 15);
                if (this.thumbColor != null) {
                    graphics2D.setColor(this.thumbColor);
                    graphics2D.fillRect(0, screen2, n, screen - screen2 - 1);
                    this.paint3DRectLighting(graphics2D, 0, screen2, n, screen - screen2 - 1);
                }
                graphics2D.setColor(this.getForeground());
                graphics2D.fillRect(0, screen2 - 16, n, 15);
                this.paint3DRectLighting(graphics2D, 0, screen2 - 16, n, 15);
                graphics2D.setColor(Color.black);
                this.paintArrow(graphics2D, (n - 8) / 2.0, screen + 6.0, 8, 4, false);
                this.paintArrow(graphics2D, (n - 8) / 2.0, screen2 - 16 + 6.0, 8, 4, true);
            }
        }
        else if (this.direction == 0) {
            graphics2D.setColor(this.getForeground());
            graphics2D.fillRect(screen - 16, 0, 15, n2);
            this.paint3DRectLighting(graphics2D, screen - 16, 0, 15, n2);
            if (this.thumbColor != null) {
                graphics2D.setColor(this.thumbColor);
                graphics2D.fillRect(screen, 0, screen2 - screen - 1, n2);
                this.paint3DRectLighting(graphics2D, screen, 0, screen2 - screen - 1, n2);
            }
            graphics2D.setColor(this.getForeground());
            graphics2D.fillRect(screen2, 0, 15, n2);
            this.paint3DRectLighting(graphics2D, screen2, 0, 15, n2);
            graphics2D.setColor(Color.black);
            this.paintArrow(graphics2D, screen - 16 + 6.0, (n2 - 8) / 2.0, 4, 8, true);
            this.paintArrow(graphics2D, screen2 + 6.0, (n2 - 8) / 2.0, 4, 8, false);
        }
        else {
            graphics2D.setColor(this.getForeground());
            graphics2D.fillRect(screen, 0, 15, n2);
            this.paint3DRectLighting(graphics2D, screen, 0, 15, n2);
            if (this.thumbColor != null) {
                graphics2D.setColor(this.thumbColor);
                graphics2D.fillRect(screen2, 0, screen - screen2 - 1, n2);
                this.paint3DRectLighting(graphics2D, screen2, 0, screen - screen2 - 1, n2);
            }
            graphics2D.setColor(this.getForeground());
            graphics2D.fillRect(screen2 - 16, 0, 15, n2);
            this.paint3DRectLighting(graphics2D, screen2 - 16, 0, 15, n2);
            graphics2D.setColor(Color.black);
            this.paintArrow(graphics2D, screen + 6.0, (n2 - 8) / 2.0, 4, 8, true);
            this.paintArrow(graphics2D, screen2 - 16 + 6.0, (n2 - 8) / 2.0, 4, 8, false);
        }
    }
    
    protected void paintArrow(final Graphics2D graphics2D, final double n, final double n2, int n3, int n4, final boolean b) {
        final int n5 = (int)(n + 0.5);
        final int n6 = (int)(n2 + 0.5);
        if (this.orientation == 0) {
            if (n3 % 2 == 0) {
                --n3;
            }
            if (b) {
                for (int i = 0; i < n3 / 2 + 1; ++i) {
                    graphics2D.drawLine(n5 + i, n6 + i, n5 + n3 - i - 1, n6 + i);
                }
            }
            else {
                for (int j = 0; j < n3 / 2 + 1; ++j) {
                    graphics2D.drawLine(n5 + n3 / 2 - j, n6 + j, n5 + n3 - n3 / 2 + j - 1, n6 + j);
                }
            }
        }
        else {
            if (n4 % 2 == 0) {
                --n4;
            }
            if (b) {
                for (int k = 0; k < n4 / 2 + 1; ++k) {
                    graphics2D.drawLine(n5 + k, n6 + k, n5 + k, n6 + n4 - k - 1);
                }
            }
            else {
                for (int l = 0; l < n4 / 2 + 1; ++l) {
                    graphics2D.drawLine(n5 + l, n6 + n4 / 2 - l, n5 + l, n6 + n4 - n4 / 2 + l - 1);
                }
            }
        }
    }
    
    protected void paint3DRectLighting(final Graphics2D graphics2D, final int n, final int n2, final int n3, final int n4) {
        graphics2D.setColor(Color.white);
        graphics2D.drawLine(n + 1, n2 + 1, n + 1, n2 + n4 - 1);
        graphics2D.drawLine(n + 1, n2 + 1, n + n3 - 1, n2 + 1);
        graphics2D.setColor(Color.gray);
        graphics2D.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
        graphics2D.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 1);
        graphics2D.setColor(Color.darkGray);
        graphics2D.drawLine(n, n2 + n4, n + n3, n2 + n4);
        graphics2D.drawLine(n + n3, n2, n + n3, n2 + n4);
    }
    
    protected int toLocal(final int n) {
        final Dimension size = this.getSize();
        final int minimum = this.getMinimum();
        double n2;
        if (this.orientation == 0) {
            n2 = (size.height - 32) / (this.getMaximum() - minimum);
        }
        else {
            n2 = (size.width - 32) / (this.getMaximum() - minimum);
        }
        if (this.direction == 0) {
            return (int)((n - 16) / n2 + minimum + 0.5);
        }
        if (this.orientation == 0) {
            return (int)((size.height - n - 16) / n2 + minimum + 0.5);
        }
        return (int)((size.width - n - 16) / n2 + minimum + 0.5);
    }
    
    protected int toScreen(final int n) {
        final Dimension size = this.getSize();
        final int minimum = this.getMinimum();
        double n2;
        if (this.orientation == 0) {
            n2 = (size.height - 32) / (this.getMaximum() - minimum);
        }
        else {
            n2 = (size.width - 32) / (this.getMaximum() - minimum);
        }
        if (this.direction == 0) {
            return (int)(16.0 + (n - minimum) * n2 + 0.5);
        }
        if (this.orientation == 0) {
            return (int)(size.height - (n - minimum) * n2 - 16.0 + 0.5);
        }
        return (int)(size.width - (n - minimum) * n2 - 16.0 + 0.5);
    }
    
    protected double toScreenDouble(final int n) {
        final Dimension size = this.getSize();
        final int minimum = this.getMinimum();
        double n2;
        if (this.orientation == 0) {
            n2 = (size.height - 32) / (this.getMaximum() + 1 - minimum);
        }
        else {
            n2 = (size.width - 32) / (this.getMaximum() + 1 - minimum);
        }
        if (this.direction == 0) {
            return 16.0 + (n - minimum) * n2;
        }
        if (this.orientation == 0) {
            return size.height - (n - minimum) * n2 - 16.0;
        }
        return size.width - (n - minimum) * n2 - 16.0;
    }
    
    private int pickHandle(final int n) {
        final int screen = this.toScreen(this.getLowValue());
        final int screen2 = this.toScreen(this.getHighValue());
        int n2 = 0;
        if (this.direction == 0) {
            if (n > screen - 16 && n < screen) {
                n2 = 1;
            }
            else if (n >= screen && n <= screen2) {
                n2 = 2;
            }
            else if (n > screen2 && n < screen2 + 16) {
                n2 = 3;
            }
        }
        else if (n > screen && n < screen + 16) {
            n2 = 1;
        }
        else if (n <= screen && n >= screen2) {
            n2 = 2;
        }
        else if (n > screen2 - 16 && n < screen2) {
            n2 = 3;
        }
        return n2;
    }
    
    private void offset(final int n) {
        this.model.setValue(this.model.getValue() + n);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.orientation == 0) {
            this.pick = this.pickHandle(mouseEvent.getY());
            this.pickOffsetLow = mouseEvent.getY() - this.toScreen(this.getLowValue());
            this.pickOffsetHigh = mouseEvent.getY() - this.toScreen(this.getHighValue());
            this.mouse = mouseEvent.getY();
        }
        else {
            this.pick = this.pickHandle(mouseEvent.getX());
            this.pickOffsetLow = mouseEvent.getX() - this.toScreen(this.getLowValue());
            this.pickOffsetHigh = mouseEvent.getX() - this.toScreen(this.getHighValue());
            this.mouse = mouseEvent.getX();
        }
        this.repaint();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.requestFocus();
        final int n = (this.orientation == 0) ? mouseEvent.getY() : mouseEvent.getX();
        final int minimum = this.getMinimum();
        final int maximum = this.getMaximum();
        final int lowValue = this.getLowValue();
        final int highValue = this.getHighValue();
        switch (this.pick) {
            case 1: {
                int local = this.toLocal(n - this.pickOffsetLow);
                if (local < minimum) {
                    local = minimum;
                }
                if (local > maximum) {
                    local = maximum;
                }
                if (local > highValue - this.minExtent) {
                    local = highValue - this.minExtent;
                }
                this.setLowValue(local);
                break;
            }
            case 3: {
                int local2 = this.toLocal(n - this.pickOffsetHigh);
                if (local2 < minimum) {
                    local2 = minimum;
                }
                if (local2 > maximum) {
                    local2 = maximum;
                }
                if (local2 < lowValue + this.minExtent) {
                    local2 = lowValue + this.minExtent;
                }
                this.setHighValue(local2);
                break;
            }
            case 2: {
                int n2 = this.toLocal(n - this.pickOffsetLow) - lowValue;
                if (n2 < 0 && lowValue + n2 < minimum) {
                    n2 = minimum - lowValue;
                }
                if (n2 > 0 && highValue + n2 > maximum) {
                    n2 = maximum - highValue;
                }
                if (n2 != 0) {
                    this.offset(n2);
                    break;
                }
                break;
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.pick = 0;
        this.repaint();
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.orientation == 0) {
            switch (this.pickHandle(mouseEvent.getY())) {
                case 1: {
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 3: {
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 2: {
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 0: {
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
            }
        }
        else {
            switch (this.pickHandle(mouseEvent.getX())) {
                case 1: {
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 3: {
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 2: {
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
                case 0: {
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    break;
                }
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private void grow(final int n) {
        this.model.setRangeProperties(this.model.getValue() - n, this.model.getExtent() + 2 * n, this.model.getMinimum(), this.model.getMaximum(), false);
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();
        final boolean b = this.orientation == 0;
        final boolean b2 = keyCode == 40;
        final boolean b3 = keyCode == 38;
        final boolean b4 = keyCode == 37;
        final boolean b5 = keyCode == 39;
        final int minimum = this.getMinimum();
        final int maximum = this.getMaximum();
        final int lowValue = this.getLowValue();
        final int highValue = this.getHighValue();
        if ((b && b5) || (!b && b3)) {
            if (lowValue - this.increment >= minimum && highValue + this.increment <= maximum) {
                this.grow(this.increment);
            }
        }
        else if ((b && b4) || (!b && b2)) {
            if (highValue - lowValue >= 2 * this.increment) {
                this.grow(-1 * this.increment);
            }
        }
        else if ((b && b2) || (!b && b4)) {
            if (lowValue - this.increment >= minimum) {
                this.offset(-this.increment);
            }
        }
        else if (((b && b3) || (!b && b5)) && highValue + this.increment <= maximum) {
            this.offset(this.increment);
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    protected class RangeSliderChangeListener implements ChangeListener
    {
        public void stateChanged(final ChangeEvent changeEvent) {
            JRangeSlider.this.fireChangeEvent();
        }
    }
}
