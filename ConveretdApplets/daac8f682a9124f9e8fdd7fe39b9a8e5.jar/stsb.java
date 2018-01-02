import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Event;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class stsb extends Panel implements Runnable
{
    public static final int SCROLL_LINE_UP = 601;
    public static final int SCROLL_LINE_DOWN = 602;
    public static final int SCROLL_PAGE_UP = 603;
    public static final int SCROLL_PAGE_DOWN = 604;
    public static final int SCROLL_ABSOLUTE = 605;
    public static final int MOUSE_ENTER = 611;
    public static final int MOUSE_EXIT = 612;
    public static final int MOUSE_DOWN = 613;
    public static final int MOUSE_UP = 614;
    public int sbHeight;
    public int sbWidth;
    private int sbValue;
    private int pageIncrement;
    private int sbMax;
    private int sbIncrement;
    private Thread goauto;
    private boolean paint_called;
    private boolean first_run;
    private boolean initialized;
    private boolean bubble_hl;
    private boolean on_top;
    private boolean on_bottom;
    private boolean m_down;
    private boolean dragging_bubble;
    private boolean bad_scroll;
    private boolean mouse_in;
    private boolean m_off;
    private boolean bypass;
    private boolean indent_bubble;
    private Event the_e;
    private int auto_type;
    private int mx;
    private int my;
    private float scrollpos;
    private float lineinc;
    private int drag_move;
    private float hbp;
    private boolean flat;
    private Color button_color;
    private Color arrow_color;
    private Color harrow_color;
    private Color bg_color;
    private Color slider_color;
    private Color outline_color;
    private Color middle_arrow_color;
    private Color unactive_color;
    private Color middle_arrowhl_color;
    private int slider_inset;
    private int last_button;
    private int auto_delay;
    private int auto_rate;
    private int bubble_range;
    private float bubble_pos;
    private float oldbubble_pos;
    private float bubble_inc;
    private int position;
    private Image sboff;
    private Graphics g_sboff;
    private Image slider;
    private Graphics g_slider;
    private Image[] topb;
    private Image[] botb;
    private Image[] bubble;
    private boolean top_graphic;
    private boolean bottom_graphic;
    private boolean bubble_graphic;
    private Image barbg;
    
    private void initButtons() {
        if (this.top_graphic && this.bottom_graphic && this.bubble_graphic) {
            return;
        }
        int n = 0;
        do {
            if (!this.top_graphic) {
                this.topb[n] = this.createImage(this.sbWidth, this.sbWidth);
            }
            if (!this.bottom_graphic) {
                this.botb[n] = this.createImage(this.sbWidth, this.sbWidth);
            }
            if (n < 3 && !this.bubble_graphic) {
                this.bubble[n] = this.createImage(this.sbWidth, this.sbWidth);
            }
        } while (++n < 4);
        if (!this.top_graphic) {
            if (this.barbg != null) {
                int n2 = 0;
                do {
                    this.topb[n2].getGraphics().drawImage(this.barbg, 0, 0, this);
                } while (++n2 < 4);
            }
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 1, this.arrow_color, this.topb[0].getGraphics());
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 1, this.harrow_color, this.topb[1].getGraphics());
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 3, this.harrow_color, this.topb[2].getGraphics());
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 1, this.unactive_color, this.topb[3].getGraphics());
        }
        if (!this.bottom_graphic) {
            if (this.barbg != null) {
                int n3 = 0;
                do {
                    this.botb[n3].getGraphics().drawImage(this.barbg, 0, -(this.sbHeight - this.sbWidth), this);
                } while (++n3 < 4);
            }
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 2, this.arrow_color, this.botb[0].getGraphics());
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 2, this.harrow_color, this.botb[1].getGraphics());
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 4, this.harrow_color, this.botb[2].getGraphics());
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 2, this.unactive_color, this.botb[3].getGraphics());
        }
        if (!this.bubble_graphic) {
            if (this.barbg != null) {
                int n4 = 0;
                do {
                    this.bubble[n4].getGraphics().drawImage(this.barbg, 0, -this.sbWidth, this);
                } while (++n4 < 3);
            }
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 5, this.middle_arrow_color, this.bubble[0].getGraphics());
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 5, this.middle_arrowhl_color, this.bubble[1].getGraphics());
            final Graphics graphics = this.bubble[2].getGraphics();
            if (this.indent_bubble) {
                this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 6, this.middle_arrowhl_color, graphics);
                return;
            }
            this.initDrawButtons(0, 0, this.sbWidth, this.sbWidth, 5, this.middle_arrowhl_color, graphics);
        }
    }
    
    stsb() {
        this.sbIncrement = 1;
        this.first_run = true;
        this.bad_scroll = true;
        this.button_color = Color.lightGray;
        this.arrow_color = Color.black;
        this.harrow_color = Color.yellow;
        this.bg_color = Color.white;
        this.slider_color = Color.black;
        this.outline_color = Color.lightGray;
        this.middle_arrow_color = Color.lightGray;
        this.unactive_color = Color.lightGray;
        this.middle_arrowhl_color = Color.lightGray;
        this.auto_delay = 5;
        this.auto_rate = 40;
        this.topb = new Image[4];
        this.botb = new Image[4];
        this.bubble = new Image[3];
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.m_down || this.bad_scroll) {
            return true;
        }
        if (this.cButtons(n2)) {
            return true;
        }
        this.syncGraphics(9);
        return true;
    }
    
    private void undoHButtons() {
        if (this.on_bottom) {
            this.drawButton(1, 0);
            this.paintIt();
            this.on_bottom = false;
        }
        if (this.on_top) {
            this.drawButton(0, 0);
            this.paintIt();
            this.on_top = false;
        }
        if (this.bubble_hl) {
            this.drawButton(2, 0);
            this.paintIt();
            this.bubble_hl = false;
        }
    }
    
    private void DrawBubble() {
        if (!this.bad_scroll) {
            this.drawSlider();
            this.bubble_pos = this.hbp;
            this.sbValue = (int)((this.bubble_pos - this.sbWidth) / this.bubble_range * this.sbMax);
            this.drawButton(2, 2);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int my) {
        this.postEvent(new Event(this, 613, null));
        this.m_down = true;
        if (this.bad_scroll) {
            return true;
        }
        if (this.on_bottom) {
            this.syncGraphics(3);
            this.last_button = 1;
            this.checkDbutton(event, this.auto_type = 1);
            this.the_e = event;
            this.start();
            return true;
        }
        if (this.on_top) {
            this.syncGraphics(4);
            this.last_button = 2;
            this.checkDbutton(event, this.auto_type = 2);
            this.the_e = event;
            this.start();
            return true;
        }
        if (n >= 0 && n <= this.sbWidth) {
            if (my >= this.bubble_pos && my < this.bubble_pos + this.sbWidth) {
                this.drawButton(2, 2);
                this.paintIt();
                this.dragging_bubble = true;
                this.drag_move = my;
                return true;
            }
            if (my < this.bubble_pos) {
                this.my = my;
                this.mx = n;
                this.checkDbutton(event, this.auto_type = 3);
                this.the_e = event;
                this.start();
            }
            else {
                this.my = my;
                this.mx = n;
                this.checkDbutton(event, this.auto_type = 4);
                this.the_e = event;
                this.start();
            }
        }
        return true;
    }
    
    private synchronized void paintIt() {
        if (!this.paint_called || this.sbWidth == 0 || this.sbHeight == 0) {
            return;
        }
        this.getGraphics();
        if (!this.first_run) {
            this.getGraphics().drawImage(this.sboff, 0, 0, this);
            return;
        }
        this.setBackground(this.bg_color);
        if (this.sbWidth * this.sbHeight <= 0) {
            return;
        }
        this.sboff = this.createImage(this.sbWidth, this.sbHeight);
        this.g_sboff = this.sboff.getGraphics();
        this.syncGraphics(12);
        this.first_run = false;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.postEvent(new Event(this, 611, null));
        return this.mouse_in = true;
    }
    
    public void resize(final int sbWidth, final int sbHeight) {
        if (sbWidth * sbHeight <= 0) {
            return;
        }
        this.sbWidth = sbWidth;
        this.sbHeight = sbHeight;
        this.redo();
        super.resize(sbWidth, sbHeight);
    }
    
    public void resize(final Dimension dimension) {
        if (dimension.width * dimension.height <= 0) {
            return;
        }
        this.sbWidth = dimension.width;
        this.sbHeight = dimension.height;
        this.redo();
        super.resize(dimension);
    }
    
    public boolean mouseDrag(final Event event, final int mx, final int n) {
        this.mx = mx;
        this.my = n;
        if (this.bad_scroll) {
            return true;
        }
        if (this.dragging_bubble) {
            if (this.drag_move != n) {
                this.hbp = this.bubble_pos + (n - this.drag_move);
                if (this.hbp > this.bubble_range + this.sbWidth) {
                    this.hbp = this.bubble_range + this.sbWidth;
                }
                if (this.hbp < this.sbWidth) {
                    this.hbp = this.sbWidth;
                }
                this.syncGraphics(6);
                event.id = 605;
                this.postEvent(event);
                this.drag_move = n;
            }
        }
        else {
            if (this.auto_type > 2) {
                return true;
            }
            if (mx > 0 && mx < this.sbWidth && this.cButtons(n)) {
                this.m_off = false;
                return true;
            }
            this.m_off = true;
            this.syncGraphics(7);
        }
        return true;
    }
    
    private void drawSlider() {
        if (this.slider != null) {
            this.g_sboff.drawImage(this.slider, 0, this.sbWidth, this);
        }
    }
    
    public void setValues(final int sbIncrement, final int pageIncrement, final int n, final int sbValue) {
        this.scrollpos = sbValue;
        this.lineinc = sbIncrement;
        this.sbIncrement = sbIncrement;
        this.pageIncrement = pageIncrement;
        this.sbMax = n - pageIncrement;
        this.sbValue = sbValue;
        this.bubble_pos = 0.0f;
        if (this.sbMax < 1) {
            this.bad_scroll = true;
            this.bubble_pos = this.sbWidth;
        }
        else {
            this.bad_scroll = false;
        }
        this.initialized = true;
        this.figureBubble();
        this.paintIt();
        this.syncGraphics(0);
    }
    
    private void initDrawScrollBar() {
        this.g_sboff.setColor(this.bg_color);
        this.g_sboff.fillRect(0, 0, this.sbWidth, this.sbHeight);
        final int n = this.sbHeight - this.sbWidth * 2;
        this.slider = this.createImage(this.sbWidth, n);
        this.g_slider = this.slider.getGraphics();
        if (this.barbg != null) {
            this.g_slider.drawImage(this.barbg, 0, -this.sbWidth, this);
        }
        if (this.slider_color != null) {
            this.g_slider.setColor(this.slider_color);
            this.g_slider.fillRect(this.slider_inset, 0, this.sbWidth - (this.slider_inset * 2 + 1), n);
        }
        if (this.outline_color != null) {
            this.g_slider.setColor(this.outline_color);
            this.g_slider.drawLine(this.slider_inset, 0, this.slider_inset, n);
            this.g_slider.drawLine(this.sbWidth - (this.slider_inset + 1), 0, this.sbWidth - (this.slider_inset + 1), n);
        }
        this.drawSlider();
        this.initButtons();
        if (!this.bad_scroll) {
            this.drawButton(0, 0);
            this.drawButton(1, 0);
            this.drawButton(2, 0);
        }
        else {
            this.drawButton(0, 3);
            this.drawButton(1, 3);
        }
        this.getGraphics().drawImage(this.sboff, 0, 0, this);
    }
    
    private void drawButton(final int n, final int n2) {
        if (n == 0) {
            if (this.topb[n2] != null) {
                this.g_sboff.drawImage(this.topb[n2], 0, 0, this);
            }
        }
        else if (n == 1) {
            if (this.botb[n2] != null) {
                this.g_sboff.drawImage(this.botb[n2], 0, this.sbHeight - this.sbWidth, this);
            }
        }
        else if (n == 2 && this.bubble[n2] != null) {
            this.g_sboff.drawImage(this.bubble[n2], 0, (int)this.bubble_pos, this);
        }
    }
    
    public void figureBubble() {
        if (!this.initialized) {
            return;
        }
        this.bubble_range = this.sbHeight - this.sbWidth * 3;
        this.bubble_inc = this.bubble_range / this.sbMax;
        this.bubble_pos = this.sbValue * this.bubble_inc + this.sbWidth;
        this.oldbubble_pos = this.bubble_pos;
    }
    
    public void paint(final Graphics graphics) {
        this.paint_called = true;
        this.paintIt();
    }
    
    public void redo() {
        this.paint_called = false;
        this.first_run = true;
        this.figureBubble();
        this.paintIt();
    }
    
    public void start() {
        if (this.goauto == null) {
            (this.goauto = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.goauto != null) {
            this.goauto.stop();
            this.goauto = null;
        }
    }
    
    public void reshape(final int n, final int n2, final int sbWidth, final int sbHeight) {
        if (sbWidth * sbHeight <= 0) {
            return;
        }
        this.sbWidth = sbWidth;
        this.sbHeight = sbHeight;
        this.redo();
        super.reshape(n, n2, sbWidth, sbHeight);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.postEvent(new Event(this, 612, null));
        if (this.bad_scroll) {
            return true;
        }
        this.mouse_in = false;
        this.m_off = true;
        this.syncGraphics(9);
        return true;
    }
    
    private void checkDbutton(final Event event, final int n) {
        if (n == 1) {
            this.sbValue += this.sbIncrement;
            if (this.sbValue > this.sbMax) {
                this.sbValue = this.sbMax;
            }
            this.syncGraphics(5);
            event.id = 602;
            this.postEvent(event);
            return;
        }
        if (n == 2) {
            this.sbValue -= this.sbIncrement;
            if (this.sbValue < 0) {
                this.sbValue = 0;
            }
            this.syncGraphics(5);
            event.id = 601;
            this.postEvent(event);
            return;
        }
        if (n == 3) {
            this.sbValue -= this.pageIncrement;
            if (this.sbValue < 0) {
                this.sbValue = 0;
            }
            this.syncGraphics(5);
            event.id = 603;
            this.postEvent(event);
            return;
        }
        if (n == 4) {
            this.sbValue += this.pageIncrement;
            if (this.sbValue > this.sbMax) {
                this.sbValue = this.sbMax;
            }
            this.syncGraphics(5);
            event.id = 604;
            this.postEvent(event);
        }
    }
    
    public void setBarStyle(final int slider_inset, final Color button_color, final Color arrow_color, final Color harrow_color, final Color bg_color, final Color slider_color, final Color outline_color, final Color middle_arrow_color, final Color middle_arrowhl_color, final Color unactive_color, final boolean flat, final boolean indent_bubble) {
        this.button_color = button_color;
        this.arrow_color = arrow_color;
        this.harrow_color = harrow_color;
        this.bg_color = bg_color;
        this.slider_color = slider_color;
        this.outline_color = outline_color;
        this.middle_arrow_color = middle_arrow_color;
        this.unactive_color = unactive_color;
        this.middle_arrowhl_color = middle_arrowhl_color;
        this.slider_inset = slider_inset;
        this.flat = flat;
        this.indent_bubble = indent_bubble;
    }
    
    public int getValue() {
        return this.sbValue;
    }
    
    public void setBarButtonImages(final Image[] topb, final Image[] botb, final Image[] bubble) {
        if (topb != null) {
            this.topb = topb;
            this.top_graphic = true;
        }
        if (botb != null) {
            this.botb = botb;
            this.bottom_graphic = true;
        }
        if (this.bubble != null) {
            this.bubble = bubble;
            this.bubble_graphic = true;
        }
    }
    
    public Dimension preferredSize() {
        return new Dimension(14, 75);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.bad_scroll) {
            this.postEvent(new Event(this, 614, null));
            return true;
        }
        this.m_down = false;
        if (this.dragging_bubble) {
            this.dragging_bubble = false;
            if (n >= 0 && n <= this.sbWidth && n2 >= this.bubble_pos && n2 < this.bubble_pos + this.sbWidth) {
                this.drawButton(2, 1);
                this.paintIt();
            }
        }
        this.last_button = 0;
        this.stop();
        this.auto_type = 0;
        if (this.m_off) {
            this.syncGraphics(8);
            this.postEvent(new Event(this, 614, null));
            return true;
        }
        if (this.on_bottom) {
            this.syncGraphics(10);
            this.postEvent(new Event(this, 614, null));
            return true;
        }
        if (this.on_top) {
            this.syncGraphics(11);
            this.postEvent(new Event(this, 614, null));
            return true;
        }
        this.postEvent(new Event(this, 614, null));
        return true;
    }
    
    public void setBarBgImage(final Image barbg) {
        this.barbg = barbg;
    }
    
    private synchronized void syncGraphics(final int n) {
        if (n == 0) {
            if (!this.bad_scroll) {
                this.drawSlider();
                this.drawButton(2, 0);
                this.drawButton(0, 0);
                this.drawButton(1, 0);
            }
            else {
                this.drawSlider();
                this.drawButton(0, 3);
                this.drawButton(1, 3);
            }
            this.paintIt();
            return;
        }
        if (n == 3) {
            this.drawButton(1, 2);
            return;
        }
        if (n == 4) {
            this.drawButton(0, 2);
            return;
        }
        if (n == 5) {
            this.drawSlider();
            this.bubble_pos = this.sbValue * this.bubble_inc + this.sbWidth;
            this.drawButton(2, 0);
            this.paintIt();
            return;
        }
        if (n == 6) {
            this.DrawBubble();
            this.paintIt();
            return;
        }
        if (n == 7) {
            this.drawButton(1, 0);
            this.on_bottom = false;
            this.drawButton(0, 0);
            this.on_top = false;
            this.stop();
            this.auto_type = 0;
            this.paintIt();
            return;
        }
        if (n == 8) {
            this.drawButton(1, 0);
            this.drawButton(0, 0);
            this.paintIt();
            this.on_bottom = false;
            this.on_top = false;
            this.m_off = false;
            return;
        }
        if (n == 9) {
            this.undoHButtons();
            return;
        }
        if (n == 10) {
            this.drawButton(1, 1);
            this.paintIt();
            return;
        }
        if (n == 11) {
            this.drawButton(0, 1);
            this.paintIt();
            return;
        }
        if (n == 12) {
            this.initDrawScrollBar();
        }
    }
    
    public void run() {
        while (true) {
            if (!this.bypass) {
                try {
                    Thread.sleep(this.auto_delay * 100);
                }
                catch (InterruptedException ex) {}
            }
            else {
                this.bypass = false;
            }
            while (this.m_down) {
                if ((this.auto_type != 3 && this.auto_type != 4) || (this.mx > 0 && this.mx < this.sbWidth && this.my <= this.sbHeight - this.sbWidth && this.my >= this.sbWidth && (this.auto_type != 3 || this.my <= this.bubble_pos) && (this.auto_type != 4 || this.my >= this.bubble_pos + this.sbWidth))) {
                    this.checkDbutton(this.the_e, this.auto_type);
                    try {
                        Thread.sleep(this.auto_rate);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
        }
    }
    
    private void initDrawButtons(final int n, final int n2, final int n3, final int n4, final int n5, final Color color, final Graphics graphics) {
        if (this.button_color != null) {
            graphics.setColor(this.button_color);
            graphics.fillRect(n, n2, n3 + 1, n4 + 1);
        }
        if (this.flat && this.outline_color != null) {
            graphics.setColor(this.outline_color);
            graphics.drawRect(n, n2, n3 - 1, n4 - 1);
        }
        if (n5 > 0 && color != null) {
            graphics.setColor(color);
            int n6 = 0;
            int n7 = 0;
            int n8;
            if (n3 < 10) {
                n8 = 2;
            }
            else if (n3 < 14) {
                n8 = 4;
            }
            else if (n3 < 17) {
                n8 = 6;
            }
            else {
                n8 = 8;
            }
            final int n9 = n8 + 1;
            final int n10 = n8 / 2 + 1;
            int n11 = (int)(n3 / 2.0f - n9 / 2.0f);
            int n12 = (int)(n4 / 2.0f - n10 / 2.0f);
            if (n5 == 1 || n5 == 3) {
                n12 = n4 - n12 - 1;
            }
            if (n5 == 5 || n5 == 6) {
                n8 -= 2;
                n11 = (int)(n3 / 2.0f - (n8 + 1) / 2.0f);
                n12 = n4 / 2 + 1;
                n6 = n11;
                n7 = n12 - 3;
            }
            if (n5 == 3 || n5 == 4) {
                ++n11;
                ++n12;
            }
            if (n5 == 6) {
                ++n12;
                ++n7;
            }
            if (n5 == 5 || n5 == 6) {
                int n13 = 0;
                do {
                    for (int i = n8; i >= 0; i -= 2) {
                        graphics.drawLine(n11, n12, n11 + i, n12);
                        ++n11;
                        if (n13 == 0) {
                            ++n12;
                        }
                        else {
                            --n12;
                        }
                    }
                    n11 = n6;
                    n12 = n7;
                } while (++n13 < 2);
            }
            else {
                for (int j = n8; j >= 0; j -= 2) {
                    graphics.drawLine(n11, n12, n11 + j, n12);
                    ++n11;
                    if (n5 == 2 || n5 == 4) {
                        ++n12;
                    }
                    else {
                        --n12;
                    }
                }
            }
        }
        if (!this.flat) {
            if (n5 == 0 || n5 == 1 || n5 == 2 || n5 == 5) {
                graphics.setColor(Color.lightGray);
                graphics.drawRect(0, 0, n3 - 1, n4 - 1);
                graphics.setColor(Color.black);
                graphics.drawLine(0, n4 - 1, n3 - 1, n4 - 1);
                graphics.drawLine(n3 - 1, 0, n3 - 1, n4 - 1);
                graphics.setColor(Color.white);
                graphics.drawRect(1, 1, n3 - 3, n4 - 3);
                graphics.setColor(Color.gray);
                graphics.drawLine(1, n4 - 2, n3 - 2, n4 - 2);
                graphics.drawLine(n3 - 2, 1, n3 - 2, n4 - 2);
                return;
            }
            if (n5 == 6) {
                graphics.setColor(Color.white);
                graphics.drawRect(0, 0, n3 - 1, n4 - 1);
                graphics.setColor(Color.black);
                graphics.drawLine(0, n4 - 1, n3 - 1, n4 - 1);
                graphics.drawLine(n3 - 1, 0, n3 - 1, n4 - 1);
                return;
            }
            graphics.setColor(Color.gray);
            graphics.drawRect(n, n2, n3 - 1, n4 - 1);
        }
    }
    
    private boolean cButtons(final int n) {
        if (n < this.sbWidth && n >= 0) {
            if (!this.on_top) {
                this.undoHButtons();
                this.on_top = true;
                if (!this.m_down) {
                    this.drawButton(0, 1);
                }
                else if (this.last_button != 1) {
                    this.drawButton(0, 1);
                    this.bypass = true;
                    this.auto_type = 2;
                    this.start();
                }
                else {
                    this.on_top = false;
                }
                this.paintIt();
            }
            return true;
        }
        if (n > this.sbHeight - this.sbWidth && n <= this.sbHeight) {
            if (!this.on_bottom) {
                this.undoHButtons();
                this.on_bottom = true;
                if (!this.m_down) {
                    this.drawButton(1, 1);
                }
                else if (this.last_button != 2) {
                    this.drawButton(1, 1);
                    this.bypass = true;
                    this.auto_type = 1;
                    this.start();
                }
                else {
                    this.on_bottom = false;
                }
                this.paintIt();
            }
            return true;
        }
        if (n >= this.bubble_pos && n < this.bubble_pos + this.sbWidth) {
            if (!this.bubble_hl) {
                this.undoHButtons();
                this.drawButton(2, 1);
                this.paintIt();
                this.bubble_hl = true;
            }
            return true;
        }
        return false;
    }
}
