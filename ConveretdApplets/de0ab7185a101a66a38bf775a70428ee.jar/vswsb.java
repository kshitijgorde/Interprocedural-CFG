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

class vswsb extends Panel implements Runnable
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
    private boolean on_top;
    private boolean on_bottom;
    private boolean m_down;
    private boolean dragging_bubble;
    private boolean bad_scroll;
    private boolean mouse_in;
    private boolean m_off;
    private boolean bypass;
    private Event the_e;
    private int auto_type;
    private int mx;
    private int my;
    private float scrollpos;
    private float lineinc;
    private int drag_move;
    private float hbp;
    private Color button_color;
    private Color arrow_color;
    private Color harrow_color;
    private Color bg_color;
    private Color slider_color;
    private Color outline_color;
    private int slider_inset;
    private int last_button;
    private int auto_delay;
    private int auto_rate;
    private int bubble_range;
    private float bubble_pos;
    private float bubble_inc;
    private int position;
    private Image sboff;
    private Graphics g_sboff;
    
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
        this.figureBubble(true);
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
            this.drawButton(0, this.sbHeight - this.sbWidth, this.sbWidth - 1, this.sbWidth - 1, 2, this.arrow_color);
            this.paintIt(this.getGraphics());
            this.on_bottom = false;
        }
        if (this.on_top) {
            this.drawButton(0, 0, this.sbWidth - 1, this.sbWidth - 1, 1, this.arrow_color);
            this.paintIt(this.getGraphics());
            this.on_top = false;
        }
    }
    
    private void DrawBubble(final boolean b) {
        if (!this.bad_scroll) {
            this.g_sboff.setColor(this.bg_color);
            this.g_sboff.fillRect(0, (int)this.bubble_pos, this.sbWidth, this.sbWidth);
            this.drawSlider();
            if (!b) {
                this.bubble_pos = this.sbValue * this.bubble_inc + this.sbWidth;
            }
            else {
                this.bubble_pos = this.hbp;
                this.sbValue = (int)((this.bubble_pos - this.sbWidth) / this.bubble_range * this.sbMax);
            }
            this.drawButton(0, (int)this.bubble_pos, this.sbWidth - 1, this.sbWidth - 1, 0, this.arrow_color);
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
    
    public void setBarStyle(final int slider_inset, final Color button_color, final Color arrow_color, final Color harrow_color, final Color bg_color, final Color slider_color, final Color outline_color) {
        this.button_color = button_color;
        this.arrow_color = arrow_color;
        this.harrow_color = harrow_color;
        this.bg_color = bg_color;
        this.slider_color = slider_color;
        this.outline_color = outline_color;
        this.slider_inset = slider_inset;
    }
    
    private synchronized void paintIt(final Graphics graphics) {
        if (!this.paint_called) {
            return;
        }
        if (this.first_run) {
            graphics.drawImage(this.sboff, 0, 0, this);
            return;
        }
        this.setBackground(this.bg_color);
        if (this.sbWidth * this.sbHeight <= 0) {
            return;
        }
        this.sboff = this.createImage(this.sbWidth, this.sbHeight);
        this.g_sboff = this.sboff.getGraphics();
        this.drawBar(graphics);
        this.first_run = true;
    }
    
    private void drawBar(final Graphics graphics) {
        this.g_sboff.setColor(this.bg_color);
        this.g_sboff.fillRect(0, 0, this.sbWidth, this.sbHeight);
        this.drawSlider();
        if (!this.bad_scroll) {
            this.drawButton(0, 0, this.sbWidth - 1, this.sbWidth - 1, 1, this.arrow_color);
            this.drawButton(0, this.sbHeight - this.sbWidth, this.sbWidth - 1, this.sbWidth - 1, 2, this.arrow_color);
            this.DrawBubble(false);
        }
        else {
            this.drawButton(0, 0, this.sbWidth - 1, this.sbWidth - 1, 1, Color.gray);
            this.drawButton(0, this.sbHeight - this.sbWidth, this.sbWidth - 1, this.sbWidth - 1, 2, Color.gray);
        }
        graphics.drawImage(this.sboff, 0, 0, this);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.postEvent(new Event(this, 611, null));
        return this.mouse_in = true;
    }
    
    public void resize(final Dimension dimension) {
        if (dimension.width * dimension.height <= 0) {
            return;
        }
        this.sbWidth = dimension.width;
        this.sbHeight = dimension.height;
        this.figureBubble(true);
        super.resize(dimension);
    }
    
    public void resize(final int sbWidth, final int sbHeight) {
        if (sbWidth * sbHeight <= 0) {
            return;
        }
        this.sbWidth = sbWidth;
        this.sbHeight = sbHeight;
        this.figureBubble(true);
        super.resize(sbWidth, sbHeight);
    }
    
    public int getValue() {
        return this.sbValue;
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
    
    public Dimension preferredSize() {
        return new Dimension(14, 75);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.bad_scroll) {
            this.postEvent(new Event(this, 614, null));
            return true;
        }
        this.m_down = false;
        this.dragging_bubble = false;
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
    
    vswsb() {
        this.sbIncrement = 1;
        this.first_run = true;
        this.bad_scroll = true;
        this.button_color = Color.lightGray;
        this.arrow_color = Color.black;
        this.harrow_color = Color.yellow;
        this.bg_color = Color.white;
        this.slider_color = Color.black;
        this.outline_color = Color.lightGray;
        this.auto_delay = 5;
        this.auto_rate = 40;
    }
    
    vswsb(final int sbWidth, final int sbHeight) {
        this.sbIncrement = 1;
        this.first_run = true;
        this.bad_scroll = true;
        this.button_color = Color.lightGray;
        this.arrow_color = Color.black;
        this.harrow_color = Color.yellow;
        this.bg_color = Color.white;
        this.slider_color = Color.black;
        this.outline_color = Color.lightGray;
        this.auto_delay = 5;
        this.auto_rate = 40;
        this.sbWidth = sbWidth;
        this.sbHeight = sbHeight;
        this.figureBubble(false);
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
    
    private synchronized void syncGraphics(final int n) {
        if (n == 0) {
            if ((int)(Math.abs(this.scrollpos) / this.lineinc * this.bubble_inc + this.sbWidth) != (int)this.bubble_pos) {
                this.g_sboff.setColor(this.bg_color);
                this.g_sboff.fillRect(0, (int)this.bubble_pos, this.sbWidth, this.sbWidth);
                this.drawSlider();
                this.bubble_pos = Math.abs(this.scrollpos) / this.lineinc * this.bubble_inc + this.sbWidth;
                this.drawButton(0, (int)this.bubble_pos, this.sbWidth - 1, this.sbWidth - 1, 0, this.arrow_color);
                this.getGraphics().drawImage(this.sboff, 0, 0, this);
            }
        }
        else {
            if (n == 3) {
                this.drawButton(0, this.sbHeight - this.sbWidth, this.sbWidth - 1, this.sbWidth - 1, 4, this.harrow_color);
                return;
            }
            if (n == 4) {
                this.drawButton(0, 0, this.sbWidth - 1, this.sbWidth - 1, 3, this.harrow_color);
                return;
            }
            if (n == 5) {
                this.DrawBubble(false);
                this.paintIt(this.getGraphics());
                return;
            }
            if (n == 6) {
                this.DrawBubble(true);
                this.paintIt(this.getGraphics());
                return;
            }
            if (n == 7) {
                this.drawButton(0, this.sbHeight - this.sbWidth, this.sbWidth - 1, this.sbWidth - 1, 2, this.arrow_color);
                this.on_bottom = false;
                this.drawButton(0, 0, this.sbWidth - 1, this.sbWidth - 1, 1, this.arrow_color);
                this.on_top = false;
                this.stop();
                this.auto_type = 0;
                this.paintIt(this.getGraphics());
                return;
            }
            if (n == 8) {
                this.drawButton(0, this.sbHeight - this.sbWidth, this.sbWidth - 1, this.sbWidth - 1, 2, this.arrow_color);
                this.drawButton(0, 0, this.sbWidth - 1, this.sbWidth - 1, 1, this.arrow_color);
                this.paintIt(this.getGraphics());
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
                this.drawButton(0, this.sbHeight - this.sbWidth, this.sbWidth - 1, this.sbWidth - 1, 2, this.harrow_color);
                this.paintIt(this.getGraphics());
                return;
            }
            if (n == 11) {
                this.drawButton(0, 0, this.sbWidth - 1, this.sbWidth - 1, 1, this.harrow_color);
                this.paintIt(this.getGraphics());
            }
        }
    }
    
    private void drawSlider() {
        this.g_sboff.setColor(this.slider_color);
        this.g_sboff.fillRect(this.slider_inset, this.sbWidth, this.sbWidth - (this.slider_inset * 2 + 1), this.sbHeight - this.sbWidth * 2);
        this.g_sboff.setColor(this.outline_color);
        this.g_sboff.drawLine(this.slider_inset, this.sbWidth, this.slider_inset, this.sbHeight - (this.sbWidth + 1));
        this.g_sboff.drawLine(this.sbWidth - (this.slider_inset + 1), this.sbWidth, this.sbWidth - (this.slider_inset + 1), this.sbHeight - (this.sbWidth + 1));
    }
    
    public void setValues(final int pageIncrement, final int sbMax) {
        this.sbMax = sbMax;
        this.pageIncrement = pageIncrement;
        if (this.sbMax < 1) {
            this.bad_scroll = true;
            this.bubble_pos = this.sbWidth;
        }
        else {
            this.bad_scroll = false;
        }
        this.bubble_pos = 0.0f;
        this.sbValue = 0;
        this.figureBubble(true);
    }
    
    private void drawButton(final int n, final int n2, final int n3, final int n4, final int n5, final Color color) {
        this.g_sboff.setColor(this.button_color);
        if (n5 == 0 || n5 == 1 || n5 == 2) {
            this.g_sboff.fillRect(n + 2, n2 + 2, n3 - 2, n4 - 2);
        }
        else {
            this.g_sboff.fillRect(n + 1, n2 + 1, n3 - 1, n4 - 1);
        }
        if (n5 == 1 || n5 == 2 || n5 == 3 || n5 == 4) {
            int n6 = 6;
            int n7 = 2;
            int n8;
            if (n3 < 11) {
                n6 = 4;
                n7 = 1;
                n8 = n3 / 2 - 2;
            }
            else {
                n8 = n3 / 2 - 3;
            }
            int n9;
            if (n5 == 2 || n5 == 4) {
                n9 = this.sbHeight - n4 / 2 - (n7 + 2);
            }
            else {
                n9 = n4 / 2 + n7;
            }
            this.g_sboff.setColor(color);
            if (n5 == 3 || n5 == 4) {
                ++n8;
                ++n9;
            }
            for (int i = n6; i >= 0; i -= 2) {
                this.g_sboff.drawLine(n8, n9, n8 + i, n9);
                ++n8;
                if (n5 == 2 || n5 == 4) {
                    ++n9;
                }
                else {
                    --n9;
                }
            }
        }
        if (n5 == 0 || n5 == 1 || n5 == 2) {
            this.g_sboff.setColor(Color.lightGray);
            this.g_sboff.drawRect(n + 1, n2 + 1, n3 - 1, n4 - 1);
            this.g_sboff.setColor(Color.gray);
            this.g_sboff.drawLine(n + 1, n2 + n4 - 1, n + n3 - 1, n2 + n4 - 1);
            this.g_sboff.drawLine(n + n3 - 1, n2 + 1, n + n3 - 1, n2 + n4 - 1);
            this.g_sboff.setColor(Color.white);
            this.g_sboff.drawRect(n, n2, n3, n4);
            this.g_sboff.setColor(Color.black);
            this.g_sboff.drawLine(n, n2 + n4, n + n3, n2 + n4);
            this.g_sboff.drawLine(n + n3, n2, n + n3, n2 + n4);
            return;
        }
        this.g_sboff.setColor(Color.gray);
        this.g_sboff.drawRect(n, n2, n3, n4);
    }
    
    public void figureBubble(final boolean b) {
        this.bubble_range = this.sbHeight - this.sbWidth * 3;
        this.bubble_inc = this.bubble_range / this.sbMax;
        this.bubble_pos = this.sbValue * this.bubble_inc + this.sbWidth;
        if (b) {
            this.first_run = false;
            this.paintIt(this.getGraphics());
        }
    }
    
    public void paint(final Graphics graphics) {
        this.paint_called = true;
        this.paintIt(this.getGraphics());
    }
    
    public void setBubblePos(final int n, final int n2) {
        this.scrollpos = n;
        this.lineinc = n2;
        this.sbValue = (int)(Math.abs(this.scrollpos) / this.lineinc);
        if (this.sbValue < 0) {
            this.sbValue = 0;
        }
        if (this.sbValue > this.sbMax) {
            this.sbValue = this.sbMax;
        }
        this.syncGraphics(0);
    }
    
    private boolean cButtons(final int n) {
        if (n < this.sbWidth && n >= 0) {
            if (!this.on_top) {
                this.on_top = true;
                if (!this.m_down) {
                    this.drawButton(0, 0, this.sbWidth - 1, this.sbWidth - 1, 1, this.harrow_color);
                }
                else if (this.last_button != 1) {
                    this.drawButton(0, 0, this.sbWidth - 1, this.sbWidth - 1, 3, this.harrow_color);
                    this.bypass = true;
                    this.auto_type = 2;
                    this.start();
                }
                else {
                    this.on_top = false;
                }
                this.paintIt(this.getGraphics());
            }
            return true;
        }
        if (n > this.sbHeight - this.sbWidth && n <= this.sbHeight) {
            if (!this.on_bottom) {
                this.on_bottom = true;
                if (!this.m_down) {
                    this.drawButton(0, this.sbHeight - this.sbWidth, this.sbWidth - 1, this.sbWidth - 1, 2, this.harrow_color);
                }
                else if (this.last_button != 2) {
                    this.drawButton(0, this.sbHeight - this.sbWidth, this.sbWidth - 1, this.sbWidth - 1, 4, this.harrow_color);
                    this.bypass = true;
                    this.auto_type = 1;
                    this.start();
                }
                else {
                    this.on_bottom = false;
                }
                this.paintIt(this.getGraphics());
            }
            return true;
        }
        return false;
    }
}
