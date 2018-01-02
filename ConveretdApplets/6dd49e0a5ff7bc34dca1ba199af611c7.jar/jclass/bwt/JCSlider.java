// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.LayoutManager;
import java.applet.Applet;
import jclass.util.JCVector;

public class JCSlider extends JCContainer implements JCAdjustable
{
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    protected ScrollbarThumb thumb;
    int value;
    int min;
    int max;
    int dir;
    int trough_offset;
    protected JCVector adjustmentListeners;
    int line_incr;
    int page_incr;
    long filter_time;
    long last_time;
    int trough_size;
    int slider_size;
    int visible;
    int major;
    int minor;
    private int dragStart;
    private int dragValueStart;
    int thumb_major;
    int thumb_minor;
    int THUMB_SIZE;
    int HALF_THUMB_SIZE;
    static final int TICK_SIZE = 3;
    int num_ticks;
    boolean auto_tick;
    JCLabel min_label;
    JCLabel max_label;
    private static final String base = "slider";
    private static int nameCounter;
    
    public JCSlider() {
        this(0, null, null);
    }
    
    public JCSlider(final int n) {
        this(n, null, null);
    }
    
    public JCSlider(final int n, final int n2, final int n3, final int n4) {
        this(n, null, null);
        this.setValues(n2, n3, n4);
    }
    
    public JCSlider(final int orientation, final Applet applet, final String s) {
        super(applet, s);
        this.max = 100;
        this.dir = -999;
        this.trough_offset = 0;
        this.adjustmentListeners = new JCVector(0);
        this.line_incr = 10;
        this.page_incr = 10;
        this.filter_time = 0L;
        this.last_time = 0L;
        this.trough_size = 0;
        this.slider_size = 0;
        this.dragStart = -999;
        this.dragValueStart = -999;
        this.thumb_major = 21;
        this.thumb_minor = 11;
        this.THUMB_SIZE = Math.min(this.thumb_major, this.thumb_minor);
        this.HALF_THUMB_SIZE = (int)Math.ceil(this.THUMB_SIZE / 2.0);
        this.num_ticks = 0;
        this.auto_tick = true;
        if (s == null) {
            this.setName(String.valueOf("slider").concat(String.valueOf(JCSlider.nameCounter++)));
        }
        this.setLayout(null);
        this.setOrientation(orientation);
        super.double_buffer = true;
        this.add(this.thumb = new SliderThumb(this));
        if (this.getClass().getName().equals("jclass.bwt.JCSlider")) {
            this.getParameters(applet);
        }
        this.enable11Events(32L);
    }
    
    protected void getParameters() {
        super.getParameters();
        SliderConverter.getParams(this);
    }
    
    public synchronized void setValues(int min, final int min2, int max) {
        final int page_incr = this.page_incr;
        this.min = min2;
        this.max = max;
        this.visible = 0;
        if (max < min2) {
            max = min2;
        }
        this.visible = Math.min(max - min2, this.visible);
        min = Math.min(Math.max(min2, min), max - this.visible);
        this.value = min;
        final int visible = this.visible;
        this.visible = visible;
        this.page_incr = visible;
        this.layout();
        this.page_incr = page_incr;
    }
    
    public int getOrientation() {
        return this.dir;
    }
    
    public synchronized void setOrientation(final int dir) {
        ScrollbarConverter.checkOrientation(dir);
        if (this.dir == dir) {
            return;
        }
        this.dir = dir;
    }
    
    public synchronized int getValue() {
        return this.value;
    }
    
    protected int check(final int n) {
        return Math.min(Math.max(this.min, n), this.max - this.visible);
    }
    
    public synchronized void setValue(int check) {
        check = this.check(check);
        if (check != this.value) {
            this.value = check;
            this.moveThumb();
        }
    }
    
    public synchronized void setValue(final int n, final boolean b) {
        this.setValue(n, 605, b);
    }
    
    synchronized void setValue(int check, int n, final boolean b) {
        check = this.check(check);
        if (check == this.value) {
            return;
        }
        this.value = check;
        this.moveThumb();
        if (!b || this.adjustmentListeners.size() == 0) {
            return;
        }
        switch (n) {
            case 601: {
                n = 2;
                break;
            }
            case 602: {
                n = 1;
                break;
            }
            case 603: {
                n = 3;
                break;
            }
            case 604: {
                n = 4;
                break;
            }
            case 605: {
                n = 5;
                break;
            }
        }
        final JCAdjustmentEvent jcAdjustmentEvent = new JCAdjustmentEvent(this, 601, n, check);
        for (int i = 0; i < this.adjustmentListeners.size(); ++i) {
            ((JCAdjustmentListener)this.adjustmentListeners.elementAt(i)).adjustmentValueChanged(jcAdjustmentEvent);
        }
    }
    
    public int getMinimum() {
        return this.min;
    }
    
    public synchronized void setMinimum(final int n) {
        this.setValues(this.value, n, this.max);
    }
    
    public synchronized void setMaximum(final int n) {
        this.setValues(this.value, this.min, n);
    }
    
    public int getBlockIncrement() {
        return this.page_incr;
    }
    
    public void addAdjustmentListener(final JCAdjustmentListener jcAdjustmentListener) {
        this.adjustmentListeners.add(jcAdjustmentListener);
    }
    
    public void removeAdjustmentListener(final JCAdjustmentListener jcAdjustmentListener) {
        this.adjustmentListeners.removeElement(jcAdjustmentListener);
    }
    
    public boolean dragging() {
        return this.dragStart != -999;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.dragging()) {
            final int value = this.value;
            this.value = -999;
            this.setValue(value, 605, true);
        }
        final int n3 = -999;
        this.dragValueStart = n3;
        this.dragStart = n3;
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.slider_size == this.trough_size || event.target != this) {
            return true;
        }
        if (event.when - this.last_time < 20) {
            return true;
        }
        this.last_time = event.when;
        final int dragStart = (this.dir == 0) ? n : n2;
        final int n3 = (this.dir == 0) ? this.thumb.location().x : this.thumb.location().y;
        if (dragStart >= n3 && dragStart <= n3 + this.slider_size) {
            this.dragStart = dragStart;
            this.dragValueStart = this.value;
            return true;
        }
        if (event.controlDown()) {
            if (dragStart < n3) {
                this.setValue(this.min, 605, true);
            }
            else {
                this.setValue(this.max - this.visible, 605, true);
            }
            return true;
        }
        if (dragStart < n3) {
            this.setValue(this.value - this.page_incr, 603, true);
        }
        else {
            this.setValue(this.value + this.page_incr, 604, true);
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        if (!this.dragging()) {
            return true;
        }
        if (this.slider_size == this.trough_size) {
            return true;
        }
        final int n3 = (this.dir == 0) ? event.x : event.y;
        final boolean b = event.when - this.last_time > this.filter_time;
        if (b) {
            this.last_time = event.when;
        }
        this.setValue(this.dragValueStart + this.toValue(n3 - this.dragStart), b);
        this.last_time = event.when;
        return true;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.slider_size == this.trough_size) {
            return true;
        }
        if (n == 1002) {
            this.setValue(this.value - this.page_incr, 603, true);
            return true;
        }
        if (n == 1003) {
            this.setValue(this.value + this.page_incr, 604, true);
            return true;
        }
        if (n == 1000) {
            this.setValue(this.min, 605, true);
            return true;
        }
        if (n == 1001) {
            this.setValue(this.max - this.slider_size, 605, true);
            return true;
        }
        if (n == 1004 || n == 1006) {
            this.setValue(this.value - this.line_incr, 601, true);
            return true;
        }
        if (n == 1005 || n == 1007) {
            this.setValue(this.value + this.line_incr, 602, true);
            return true;
        }
        return super.keyDown(event, n);
    }
    
    public synchronized void reshape(final int n, final int n2, final int n3, final int n4) {
        this.major = ((this.dir == 0) ? n3 : n4);
        this.minor = ((this.dir == 0) ? n4 : n3);
        super.reshape(n, n2, n3, n4);
    }
    
    protected void moveThumb() {
        if (this.getPeer() == null) {
            return;
        }
        final int max = Math.max(0, Math.min(this.toPixels(this.value - this.min), this.trough_size - this.slider_size));
        int n = 0;
        int n2 = 0;
        if (this.dir == 0) {
            n = max;
        }
        else {
            n2 = max;
        }
        this.thumb.move(n, n2);
    }
    
    protected void paintThumb(final Graphics graphics) {
        if (!this.thumb.isVisible()) {
            return;
        }
        final Point translateToParent = BWTUtil.translateToParent(this, this.thumb, 0, 0);
        final Dimension size = this.thumb.size();
        if (!this.getPaintRect().intersects(new Rectangle(translateToParent, size))) {
            return;
        }
        this.thumb.setDoubleBuffer(false);
        final Point location = this.thumb.location();
        graphics.setColor(this.getBackground());
        final Graphics create = graphics.create(location.x, location.y, size.width, size.height);
        this.thumb.paint(create);
        create.dispose();
        this.thumb.setDoubleBuffer(true);
    }
    
    protected int toPixels(final int n) {
        if (this.max == this.min) {
            return 0;
        }
        if (n >= this.max - this.min - this.visible) {
            return this.trough_size;
        }
        final int n2 = this.trough_size * n / (this.max - this.min);
        return (n2 > 0) ? n2 : false;
    }
    
    protected int toValue(final int n) {
        if (this.trough_size == 0) {
            return 0;
        }
        return n * (this.max - this.min) / this.trough_size;
    }
    
    public synchronized void setBackground(final Color color) {
        this.thumb.setBackground(color);
        super.setBackground(color);
    }
    
    public final void setLayout(final LayoutManager layoutManager) {
    }
    
    public int getMaximum() {
        return this.max - this.visible;
    }
    
    public Dimension getThumbSize() {
        return (this.dir == 0) ? new Dimension(this.thumb_minor, this.thumb_major) : new Dimension(this.thumb_major, this.thumb_minor);
    }
    
    public void setThumbSize(final Dimension dimension) {
        this.thumb_minor = ((this.dir == 0) ? dimension.width : dimension.height);
        this.thumb_major = ((this.dir == 0) ? dimension.height : dimension.width);
        this.layout();
    }
    
    public void setUnitIncrement(final int line_incr) {
        this.line_incr = line_incr;
    }
    
    public int getUnitIncrement() {
        return this.line_incr;
    }
    
    public int getVisibleAmount() {
        return this.visible;
    }
    
    public synchronized void setVisibleAmount(final int n) {
        this.setValues(this.value, this.min, this.max);
    }
    
    public void setBlockIncrement(final int n) {
        this.line_incr = n;
        this.page_incr = n;
    }
    
    public boolean getAutoTick() {
        return this.auto_tick;
    }
    
    public void setAutoTick(final boolean auto_tick) {
        this.auto_tick = auto_tick;
        this.repaint();
    }
    
    public int getNumTicks() {
        return this.num_ticks;
    }
    
    public void setNumTicks(final int num_ticks) {
        this.num_ticks = num_ticks;
        this.repaint();
    }
    
    public JCLabel getMinimumLabel() {
        return this.min_label;
    }
    
    public void setMinimumLabel(final JCLabel min_label) {
        if (this.min_label != null && min_label == null) {
            this.remove(this.min_label);
        }
        this.min_label = min_label;
        this.layout();
        this.repaint();
    }
    
    public String getMinimumLabelString() {
        return (this.min_label != null) ? this.min_label.getText() : null;
    }
    
    public void setMinimumLabelString(final String s) {
        this.setMinimumLabel(new JCLabel(s));
    }
    
    public String getMaximumLabelString() {
        return (this.max_label != null) ? this.max_label.getText() : null;
    }
    
    public void setMaximumLabelString(final String s) {
        this.setMaximumLabel(new JCLabel(s));
    }
    
    public JCLabel getMaximumLabel() {
        return this.max_label;
    }
    
    public void setMaximumLabel(final JCLabel max_label) {
        if (this.max_label != null && max_label == null) {
            this.remove(this.max_label);
        }
        this.max_label = max_label;
        this.layout();
        this.repaint();
    }
    
    protected int preferredWidth() {
        int width = 0;
        int width2 = 0;
        if (this.min_label != null) {
            width = this.min_label.preferredSize().width;
        }
        if (this.max_label != null) {
            width2 = this.max_label.preferredSize().width;
        }
        if (this.dir == 0) {
            return width + width2 + 200;
        }
        return Math.max(Math.max(width, width2), this.thumb_major + ((this.num_ticks > 0 || this.auto_tick) ? 4 : 0));
    }
    
    protected int preferredHeight() {
        int height = 0;
        int height2 = 0;
        if (this.min_label != null) {
            height = this.min_label.preferredSize().height;
        }
        if (this.max_label != null) {
            height2 = this.max_label.preferredSize().height;
        }
        if (this.dir == 1) {
            return height + height2 + 200;
        }
        return Math.max(Math.max(height, height2), this.thumb_major + ((this.num_ticks > 0 || this.auto_tick) ? 4 : 0));
    }
    
    protected void resizeThumb(final int n, final int n2) {
        if (this.dir == 0) {
            this.thumb.resize(this.thumb_minor, this.thumb_major);
        }
        else {
            this.thumb.resize(this.thumb_major, this.thumb_minor);
        }
    }
    
    public synchronized void layout() {
        if (this.getPeer() == null) {
            return;
        }
        final int left = this.insets().left;
        final int top = this.insets().top;
        int n = 0;
        this.trough_offset = ((this.dir == 0) ? left : top);
        if (this.min_label != null) {
            this.add(this.min_label);
            final int width = this.min_label.preferredSize().width;
            final int height = this.min_label.preferredSize().height;
            this.min_label.reshape(left, top, width, height);
            if (this.dir == 0) {
                this.trough_offset = left + width;
                n += width;
            }
            else {
                this.trough_offset = top + height;
                n += height;
            }
        }
        if (this.max_label != null) {
            this.add(this.max_label);
            final int width2 = this.max_label.preferredSize().width;
            final int height2 = this.max_label.preferredSize().height;
            if (this.dir == 0) {
                this.max_label.reshape(this.major - this.insets().right - width2, top, width2, height2);
                n += width2;
            }
            else {
                this.max_label.reshape(left, this.major - this.insets().bottom - height2, width2, height2);
                n += height2;
            }
        }
        Math.max(this.minor, 16);
        this.trough_size = this.major;
        this.slider_size = this.thumb_minor;
        if (this.dir == 0) {
            this.resizeThumb(this.slider_size, this.minor);
        }
        else {
            this.resizeThumb(this.minor, this.slider_size);
        }
        this.moveThumb();
        this.trough_size = Math.max(5, this.major - n);
        this.max -= this.visible;
        final int max = this.max;
        final int value = this.toValue(this.THUMB_SIZE);
        this.visible = value;
        this.max = max + value;
        this.moveThumb();
    }
    
    protected void drawTicks(final Graphics graphics, final int n) {
        graphics.setColor(this.getForeground());
        final double n2 = (n > 1) ? ((this.trough_size - this.THUMB_SIZE) / (n - 1.0)) : 0.0;
        final int n3 = this.trough_offset + this.trough_size - this.HALF_THUMB_SIZE;
        if (this.dir == 0) {
            double n4 = this.trough_offset + this.THUMB_SIZE / 2.0;
            final int n5 = this.thumb.bounds().y + this.thumb_major + 1;
            for (int i = 0; i < n - 1; ++i, n4 += n2) {
                graphics.drawLine((int)n4, n5, (int)n4, n5 + 3);
            }
            if (n > 1) {
                graphics.drawLine(n3, n5, n3, n5 + 3);
            }
        }
        else {
            final int n6 = this.thumb.bounds().x + this.thumb_major + 1;
            double n7 = this.trough_offset + this.THUMB_SIZE / 2.0;
            for (int j = 0; j < n - 1; ++j, n7 += n2) {
                graphics.drawLine(n6, (int)n7, n6 + 3, (int)n7);
            }
            if (n > 1) {
                graphics.drawLine(n6, n3, n6 + 3, n3);
            }
        }
    }
    
    public void paintInterior(final Graphics graphics) {
        if (this.dir == 0) {
            final int trough_offset = this.trough_offset;
            int n = this.thumb_major / 2 - 2;
            graphics.setColor(BWTUtil.darker(this.getBackground()));
            graphics.drawLine(trough_offset, n, trough_offset + this.trough_size - 1, n);
            graphics.drawLine(trough_offset, n, trough_offset, n + 3);
            ++n;
            graphics.setColor(Color.black);
            graphics.drawLine(trough_offset + 1, n, trough_offset + this.trough_size - 2, n);
            ++n;
            graphics.setColor(this.getBackground().brighter().darker());
            graphics.drawLine(trough_offset + 1, n, trough_offset + this.trough_size - 1, n);
            graphics.drawLine(trough_offset + this.trough_size - 1, n - 1, trough_offset + this.trough_size - 1, n);
            ++n;
            graphics.setColor(BWTUtil.brighter(graphics.getColor()));
            graphics.drawLine(trough_offset, n, trough_offset + this.trough_size, n);
            graphics.drawLine(trough_offset + this.trough_size - 1, n - 3, trough_offset + this.trough_size - 1, n);
        }
        else {
            final int trough_offset2 = this.trough_offset;
            int n2 = this.thumb_major / 2 - 2;
            graphics.setColor(BWTUtil.darker(this.getBackground()));
            graphics.drawLine(n2, trough_offset2, n2, trough_offset2 + this.trough_size - 1);
            graphics.drawLine(n2, trough_offset2, n2 + 3, trough_offset2);
            ++n2;
            graphics.setColor(Color.black);
            graphics.drawLine(n2, trough_offset2 + 1, n2, trough_offset2 + this.trough_size - 2);
            ++n2;
            graphics.setColor(this.getBackground().brighter().darker());
            graphics.drawLine(n2, trough_offset2 + 1, n2, trough_offset2 + this.trough_size - 1);
            graphics.drawLine(n2 - 1, trough_offset2 + this.trough_size - 1, n2, trough_offset2 + this.trough_size - 1);
            ++n2;
            graphics.setColor(BWTUtil.brighter(graphics.getColor()));
            graphics.drawLine(n2, trough_offset2, n2, trough_offset2 + this.trough_size);
            graphics.drawLine(n2 - 3, trough_offset2 + this.trough_size - 1, n2, trough_offset2 + this.trough_size - 1);
        }
        final int n3 = (this.num_ticks > 0) ? this.num_ticks : ((this.auto_tick && this.page_incr > 0) ? ((this.max - this.visible - this.min) / this.page_incr + 1) : 0);
        if (n3 > 0) {
            this.drawTicks(graphics, n3);
        }
        this.paintThumb(graphics);
    }
    
    static {
        JCSlider.nameCounter = 0;
    }
}
