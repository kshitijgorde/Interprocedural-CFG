// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Component;
import java.awt.Event;
import java.awt.Rectangle;
import jclass.base.Border;
import java.awt.Graphics;
import java.awt.Insets;
import jclass.util.JCEnvironment;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;

public class JCArrowButton extends JCButton implements Runnable
{
    public static final int UP = 10;
    public static final int DOWN = 9;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    int orientation;
    Dimension arrow_size;
    int initial_delay;
    transient Thread thread;
    transient boolean thread_stop;
    transient int thread_counter;
    int tn;
    Color border_color;
    Color right_border_color;
    boolean bg_set;
    private static final String base = "arrowbutton";
    private static int nameCounter;
    private static final boolean TRACE = false;
    
    public JCArrowButton() {
        this(9, null, null);
    }
    
    public JCArrowButton(final int n) {
        this(n, null, null);
    }
    
    public JCArrowButton(final int orientation, final Applet applet, final String s) {
        super(null, applet, s);
        this.orientation = 9;
        this.initial_delay = Integer.MAX_VALUE;
        this.thread_stop = false;
        this.bg_set = false;
        if (s == null) {
            this.setName("arrowbutton" + JCArrowButton.nameCounter++);
        }
        super.highlight = 0;
        super.border = 1;
        if (this.getClass().getName().equals("jclass.bwt.JCArrowButton")) {
            this.getParameters(applet);
        }
        this.setOrientation(orientation);
        if (JCEnvironment.getJavaVersion() >= 110) {
            super.traversable = false;
        }
        super.double_buffer = false;
        super.border_style = 4;
    }
    
    protected void getParameters() {
        super.getParameters();
        ArrowButtonConverter.getParams(this);
    }
    
    public synchronized void setBackground(final Color background) {
        this.bg_set = true;
        super.setBackground(background);
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public synchronized void setOrientation(final int orientation) {
        ArrowButtonConverter.checkOrientation(orientation);
        this.orientation = orientation;
        if (orientation == 10 || orientation == 9) {
            super.insets = new Insets(3, 2, 3, 3);
            this.arrow_size = new Dimension(7, 4);
        }
        else {
            this.arrow_size = new Dimension(4, 7);
            super.insets = new Insets(2, 3, 3, 3);
        }
        this.repaint();
    }
    
    public Dimension getArrowSize() {
        return this.arrow_size;
    }
    
    public synchronized void setArrowSize(final Dimension arrow_size) {
        this.arrow_size = arrow_size;
        this.repaint();
    }
    
    public int getInitialRepeatDelay() {
        return this.initial_delay;
    }
    
    public void setInitialRepeatDelay(final int initial_delay) {
        this.initial_delay = initial_delay;
    }
    
    protected int preferredWidth() {
        return this.arrow_size.width;
    }
    
    protected int preferredHeight() {
        return this.arrow_size.height;
    }
    
    protected void drawHighlight(final Graphics graphics, final boolean b) {
    }
    
    protected void drawBorder(final Graphics graphics) {
        super.drawBorder(graphics);
        if (this.border_color != null) {
            graphics.setColor(this.border_color);
            graphics.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        }
        if (this.right_border_color != null) {
            Border.drawBottomLines(graphics, 1, 0, 0, this.size().width, this.size().height, this.right_border_color);
        }
    }
    
    protected void paintComponent(final Graphics graphics) {
        if (!this.bg_set) {
            this.setBackground(this.getParent().getBackground());
        }
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        final Rectangle drawingArea = this.getDrawingArea();
        switch (this.orientation) {
            case 10: {
                array[0] = this.arrow_size.width / 2;
                array[1] = (array2[0] = 0);
                array2[1] = this.arrow_size.height - 1;
                array[2] = this.arrow_size.width - 1;
                array2[2] = this.arrow_size.height - 1;
                break;
            }
            case 9: {
                array[0] = this.arrow_size.width / 2;
                array2[0] = this.arrow_size.height - 1;
                array[1] = 0;
                array[2] = this.arrow_size.width - 1;
                array2[1] = (array2[2] = 0);
                break;
            }
            case 0: {
                array2[0] = this.arrow_size.height / 2;
                array2[1] = (array[0] = 0);
                array2[2] = this.arrow_size.height - 1;
                array[1] = (array[2] = this.arrow_size.width - 1);
                break;
            }
            case 2: {
                array[0] = this.arrow_size.width - 1;
                array2[0] = this.arrow_size.height / 2;
                array[1] = (array[2] = 0);
                array2[1] = 0;
                array2[2] = this.arrow_size.height - 1;
                break;
            }
        }
        final int n = (drawingArea.width - this.arrow_size.width) / 2;
        final int n2 = (drawingArea.height - this.arrow_size.height) / 2;
        for (int i = 0; i < 3; ++i) {
            final int[] array3 = array;
            final int n3 = i;
            array3[n3] += drawingArea.x + n;
            final int[] array4 = array2;
            final int n4 = i;
            array4[n4] += drawingArea.y + n2;
        }
        if (super.armed) {
            graphics.translate(super.arm_offset, super.arm_offset);
        }
        if (!this.isEnabled()) {
            graphics.setColor(Color.lightGray.darker());
        }
        if (this.orientation == 10 || this.orientation == 9) {
            int n5 = array[0];
            int n6 = array[0];
            int n7 = array2[0];
            final double n8 = (array2[1] != array2[0]) ? ((array[1] - array[0]) / (array2[1] - array2[0])) : 0.0;
            final double n9 = (array2[2] != array2[0]) ? ((array[2] - array[0]) / (array2[2] - array2[0])) : 0.0;
            while (true) {
                graphics.drawLine(n5, n7, n6, n7);
                if (array2[1] > array2[0]) {
                    if (++n7 > array2[1]) {
                        break;
                    }
                }
                else if (--n7 < array2[1]) {
                    break;
                }
                n5 = (int)((n7 - array2[0]) * n8 + array[0]);
                n6 = (int)((n7 - array2[0]) * n9 + array[0]);
            }
        }
        else {
            int n10 = array2[0];
            int n11 = array2[0];
            int n12 = array[0];
            final double n13 = (array[1] != array[0]) ? ((array2[1] - array2[0]) / (array[1] - array[0])) : 0.0;
            final double n14 = (array[2] != array[0]) ? ((array2[2] - array2[0]) / (array[2] - array[0])) : 0.0;
            while (true) {
                graphics.drawLine(n12, n10, n12, n11);
                if (array[1] > array[0]) {
                    if (++n12 > array[1]) {
                        break;
                    }
                }
                else if (--n12 < array[1]) {
                    break;
                }
                n10 = (int)((n12 - array[0]) * n13 + array2[0]);
                n11 = (int)((n12 - array[0]) * n14 + array2[0]);
            }
        }
        graphics.translate(-super.arm_offset, -super.arm_offset);
    }
    
    public void getDrawingArea(final Rectangle rectangle) {
        final int n = super.highlight + super.border;
        rectangle.reshape(n, n, Math.max(0, this.size().width - 2 * n), Math.max(0, this.size().height - 2 * n));
    }
    
    public synchronized void disable() {
        if (this.isEnabled() && this.thread != null) {
            if (this.thread.equals(Thread.currentThread())) {
                this.thread_stop = true;
                super.disarmAction(null);
            }
            else {
                this.disarmAction(null);
            }
        }
        super.disable();
    }
    
    public void armAction(final Event event) {
        super.armAction(event);
        synchronized (this) {
            if (this.initial_delay != Integer.MAX_VALUE) {
                if (this.thread != null && !this.thread.equals(Thread.currentThread())) {
                    this.thread_stop = true;
                    try {
                        while (this.thread != null) {
                            if (!this.thread.isAlive()) {
                                break;
                            }
                            this.thread.join(1000L);
                        }
                    }
                    catch (Exception ex) {}
                    this.thread = null;
                }
                if (this.thread == null) {
                    this.thread = new Thread(this, String.valueOf(this.getName()) + "-created-thread" + this.thread_counter++);
                    if (JCEnvironment.getBrowser(this) != 3) {
                        this.thread.setPriority(1);
                    }
                    this.thread_stop = false;
                    this.thread.start();
                }
            }
        }
    }
    
    public void disarmAction(final Event event) {
        synchronized (this) {
            if (this.thread != null) {
                this.thread_stop = true;
                this.thread = null;
            }
        }
        super.disarmAction(event);
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        return super.mouseDrag(event, n, n2);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (BWTUtil.getMouseButton(event) != 1) {
            return false;
        }
        if (super.armed) {
            return true;
        }
        if (!this.inside(n, n2)) {
            return false;
        }
        super.mouseDown(event, n, n2);
        this.clickAction(event);
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (BWTUtil.getMouseButton(event) != 1) {
            return false;
        }
        if (super.armed) {
            this.disarmAction(event);
        }
        return !BWTUtil.instanceOf(this.getParent(), "JCScrollbar");
    }
    
    public void run() {
        int initial_delay = this.initial_delay;
        ++this.tn;
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                Thread.sleep(initial_delay);
            }
            catch (Exception ex) {}
            if (this.thread == null || this.thread_stop || !Thread.currentThread().isAlive()) {
                return;
            }
            if (this.isEnabled() && System.currentTimeMillis() - currentTimeMillis >= initial_delay) {
                this.clickAction(null);
            }
            if (this.thread == null || this.thread_stop) {
                return;
            }
            initial_delay = 50;
        }
    }
}
