// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Container;
import java.awt.Graphics;
import jclass.util.JCEnvironment;
import java.awt.Event;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.applet.Applet;
import jclass.util.JCImageCreator;
import java.awt.Image;
import jclass.util.JCVector;

public class JCScrollbar extends JCContainer implements JCAdjustable, JCActionListener
{
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    protected transient JCScrolledWindow scrolled_window;
    protected JCArrowButton incr_arrow;
    protected JCArrowButton decr_arrow;
    protected ScrollbarThumb thumb;
    static final int MIN_SLIDERSIZE = 8;
    private static final String base = "scrollbar";
    private static int nameCounter;
    int value;
    int min;
    int max;
    int dir;
    protected JCVector adjustmentListeners;
    int line_incr;
    int page_incr;
    long filter_time;
    long last_time;
    int visible;
    int major;
    int minor;
    int trough_size;
    int slider_size;
    transient Image disabled_image;
    private int dragStart;
    private int dragValueStart;
    private transient JCImageCreator creator;
    private transient boolean new_bg;
    private final String[] disabled_pixels;
    
    public JCScrollbar() {
        this(1);
    }
    
    public JCScrollbar(final int n) {
        this(n, null, null);
    }
    
    public JCScrollbar(final int orientation, final Applet applet, final String s) {
        super(applet, s);
        this.scrolled_window = null;
        this.max = 100;
        this.dir = -999;
        this.adjustmentListeners = new JCVector(0);
        this.line_incr = 10;
        this.page_incr = 10;
        this.filter_time = 0L;
        this.last_time = 0L;
        this.trough_size = 0;
        this.slider_size = 0;
        this.dragStart = -999;
        this.dragValueStart = -999;
        this.new_bg = true;
        this.disabled_pixels = new String[] { "wbwbwbwbwbwbwbwb", "bwbwbwbwbwbwbwbw", "wbwbwbwbwbwbwbwb", "bwbwbwbwbwbwbwbw", "wbwbwbwbwbwbwbwb", "bwbwbwbwbwbwbwbw", "wbwbwbwbwbwbwbwb", "bwbwbwbwbwbwbwbw", "wbwbwbwbwbwbwbwb", "bwbwbwbwbwbwbwbw" };
        if (s == null) {
            this.setName(String.valueOf("scrollbar").concat(String.valueOf(JCScrollbar.nameCounter++)));
        }
        this.setLayout(null);
        this.setOrientation(orientation);
        this.add(this.thumb = new ScrollbarThumb(this));
        super.double_buffer = true;
        this.enable11Events(32L);
        if (this.getClass().getName().equals("jclass.bwt.JCScrollbar")) {
            this.getParameters(applet);
        }
    }
    
    public JCScrollbar(final int n, final int n2, final int n3, final int n4, final int n5) {
        this(n);
        this.setValues(n2, n3, n4, n5);
    }
    
    protected void getParameters() {
        super.getParameters();
        ScrollbarConverter.getParams(this);
    }
    
    public void getParameters(final Applet applet) {
        this.getParameters();
    }
    
    String getParam(final String s) {
        return JCContainer.conv.getParam(super.applet, this, this.getName(), s);
    }
    
    public boolean isFocusTraversable() {
        return false;
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
        this.add(this.decr_arrow = new JCArrowButton((dir == 0) ? 0 : 10, super.applet, this.getName()));
        this.decr_arrow.addActionListener(this);
        this.add(this.incr_arrow = new JCArrowButton((dir == 0) ? 2 : 9, super.applet, this.getName()));
        this.incr_arrow.addActionListener(this);
        final JCArrowButton incr_arrow = this.incr_arrow;
        final JCArrowButton decr_arrow = this.decr_arrow;
        final int n = 2;
        decr_arrow.shadow = n;
        incr_arrow.shadow = n;
        final JCArrowButton incr_arrow2 = this.incr_arrow;
        final JCArrowButton decr_arrow2 = this.decr_arrow;
        final Color black = Color.black;
        decr_arrow2.border_color = black;
        incr_arrow2.border_color = black;
    }
    
    public synchronized int getValue() {
        return this.value;
    }
    
    int check(final int n) {
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
        this.setValues(this.value, this.visible, n, this.max);
    }
    
    public int getMaximum() {
        return this.max;
    }
    
    public synchronized void setMaximum(final int n) {
        this.setValues(this.value, this.visible, this.min, n);
    }
    
    public int getVisibleAmount() {
        return this.visible;
    }
    
    public int getVisible() {
        return this.visible;
    }
    
    public synchronized void setVisibleAmount(final int n) {
        this.setValues(this.value, n, this.min, this.max);
    }
    
    public void setUnitIncrement(final int line_incr) {
        this.line_incr = line_incr;
    }
    
    public int getUnitIncrement() {
        return this.line_incr;
    }
    
    public void setBlockIncrement(final int page_incr) {
        this.page_incr = page_incr;
    }
    
    public int getBlockIncrement() {
        return this.page_incr;
    }
    
    public synchronized void setValues(int min, int min2, final int min3, int max) {
        if (max < min3) {
            max = min3;
        }
        min2 = Math.min(max - min3, min2);
        min = Math.min(Math.max(min3, min), max - min2);
        if (this.value != min || this.visible != min2 || this.min != min3 || this.max != max) {
            this.min = min3;
            this.max = max;
            this.value = min;
            final int n = min2;
            this.visible = n;
            this.page_incr = n;
            this.layout();
        }
    }
    
    public boolean atEnd() {
        return this.value == 0 || this.value >= this.max - this.visible;
    }
    
    boolean dragging() {
        return this.dragStart != -999;
    }
    
    public long getFilterTime() {
        return this.filter_time;
    }
    
    public void setFilterTime(final long filter_time) {
        this.filter_time = filter_time;
    }
    
    protected int preferredWidth() {
        return (this.getOrientation() == 1) ? 16 : 100;
    }
    
    protected int preferredHeight() {
        return (this.getOrientation() == 0) ? 16 : 100;
    }
    
    public void addAdjustmentListener(final JCAdjustmentListener jcAdjustmentListener) {
        this.adjustmentListeners.add(jcAdjustmentListener);
    }
    
    public void removeAdjustmentListener(final JCAdjustmentListener jcAdjustmentListener) {
        this.adjustmentListeners.removeElement(jcAdjustmentListener);
    }
    
    public void actionPerformed(final JCActionEvent jcActionEvent) {
        if (jcActionEvent.getSource() == this.decr_arrow) {
            this.setValue(this.value - this.line_incr, 601, true);
        }
        else if (jcActionEvent.getSource() == this.incr_arrow) {
            this.setValue(this.value + this.line_incr, 602, true);
        }
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
        if (this.scrolled_window != null && JCEnvironment.getJavaVersion() < 110) {
            this.scrolled_window.getViewport().requestFocus();
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.target != this) {
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
    
    int cvtThumbSizeToPixels(final int n) {
        int n2 = 0;
        if (this.max > this.min) {
            n2 = this.trough_size * n / (this.max - this.min);
            if (n2 < 0) {
                n2 = 0;
            }
        }
        int n3 = Math.min(this.trough_size, Math.max(n2, 6));
        if (this.min >= this.max) {
            n3 = this.trough_size;
        }
        return n3;
    }
    
    private void moveThumb() {
        if (this.getPeer() == null) {
            return;
        }
        final int max = Math.max(0, Math.min(this.toPixels(this.value - this.min), this.trough_size - this.slider_size));
        int n = 0;
        int n2 = 0;
        if (this.dir == 0) {
            n = max + this.decr_arrow.size().width;
        }
        else {
            n2 = max + this.decr_arrow.size().height;
        }
        this.thumb.move(n, n2);
    }
    
    public synchronized void layout() {
        if (this.getPeer() == null) {
            return;
        }
        final int max = Math.max(this.minor, 16);
        this.trough_size = Math.max(8, this.major) - 2 * max;
        this.slider_size = this.cvtThumbSizeToPixels(this.visible);
        if (this.dir == 0) {
            this.thumb.resize(this.slider_size, this.minor);
        }
        else {
            this.thumb.resize(this.minor, this.slider_size);
        }
        if (this.dir == 0) {
            this.decr_arrow.reshape(0, 0, max, this.minor);
            this.incr_arrow.reshape(this.major - max, 0, max, this.minor);
        }
        else {
            this.decr_arrow.reshape(0, 0, this.minor, max);
            this.incr_arrow.reshape(0, this.major - max, this.minor, max);
        }
        this.moveThumb();
        this.thumb.show(this.slider_size != this.trough_size);
        this.enable(this.slider_size != this.trough_size);
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
    
    public void paintInterior(final Graphics graphics) {
        if (!this.decr_arrow.getBackground().equals(this.getBackground())) {
            this.decr_arrow.setBackground(this.getBackground());
            this.incr_arrow.setBackground(this.getBackground());
        }
        if (this.isEnabled()) {
            graphics.setColor(BWTUtil.brighter(this.getBackground()));
            graphics.fillRect(0, 0, this.size().width, this.size().height);
        }
        else {
            if (this.disabled_image == null) {
                this.disabled_image = this.createDisabledImage();
            }
            BWTUtil.wallPaper(this, graphics, this.disabled_image);
        }
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        this.paintThumb(graphics);
    }
    
    private int toPixels(final int n) {
        if (this.max == this.min) {
            return 0;
        }
        if (n >= this.max - this.min - this.visible) {
            return this.trough_size;
        }
        final int n2 = this.trough_size * n / (this.max - this.min);
        return (n2 > 0) ? n2 : false;
    }
    
    private int toValue(final int n) {
        if (this.trough_size == 0) {
            return 0;
        }
        return n * (this.max - this.min) / this.trough_size;
    }
    
    public synchronized void setForeground(final Color foreground) {
        this.incr_arrow.setForeground(foreground);
        this.decr_arrow.setForeground(foreground);
        super.setForeground(foreground);
    }
    
    public synchronized void setBackground(final Color color) {
        this.new_bg = (this.getPeer() == null || color == null || !color.equals(this.getBackground()));
        this.thumb.setBackground(color);
        super.setBackground(color);
    }
    
    private Image createDisabledImage() {
        if (!this.new_bg && this.disabled_image != null) {
            return this.disabled_image;
        }
        if (this.creator == null) {
            this.creator = new JCImageCreator(this);
        }
        final Color background = this.getBackground();
        this.getForeground();
        this.creator.setColor('b', background.equals(Color.white) ? Color.lightGray : background);
        this.creator.setColor('w', Color.white);
        this.new_bg = false;
        this.creator.setSize(this.disabled_pixels[0].length(), this.disabled_pixels.length);
        return this.creator.create(this.disabled_pixels);
    }
    
    public final void setLayout(final LayoutManager layoutManager) {
    }
    
    static {
        JCScrollbar.nameCounter = 0;
    }
}
