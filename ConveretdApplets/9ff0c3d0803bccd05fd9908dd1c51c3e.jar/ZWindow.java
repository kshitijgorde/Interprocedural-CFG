import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

class ZWindow
{
    static final int ROMAN = 0;
    static final int REVERSE = 1;
    static final int BOLD = 2;
    static final int ITALIC = 4;
    static final int FIXED = 8;
    static final char FIRST_STYLE = '\u8000';
    static final char BUF_ROMAN = '\u8000';
    static final char BUF_REVERSE = '\u8001';
    static final char BUF_BOLD = '\u8002';
    static final char BUF_ITALIC = '\u8004';
    static final char BUF_FIXED = '\u8008';
    static final char LAST_STYLE = '\u800f';
    static final int NORMAL_FONT = 1;
    static final int PICTURE_FONT = 2;
    static final int GRAPHICS_FONT = 3;
    static final int FIXED_FONT = 4;
    static final char FIRST_FONT = '\u8010';
    static final char BUF_NORMAL_FONT = '\u8010';
    static final char BUF_PICTURE_FONT = '\u8011';
    static final char BUF_GRAPHICS_FONT = '\u8012';
    static final char BUF_FIXED_FONT = '\u8013';
    static final char LAST_FONT = '\u8013';
    ZScreen myscreen;
    int top;
    int left;
    int width;
    int height;
    int cursorx;
    int cursory;
    boolean buffer;
    boolean wrap;
    boolean scroll;
    boolean transcriptmode;
    int curzfont;
    int curzstyle;
    Font curfont;
    String linebuffer;
    int line_counter;
    int zforeground;
    int zbackground;
    int residual;
    
    public ZWindow(final ZScreen screen) {
        this.curzfont = 1;
        this.curzstyle = 0;
        this.top = 0;
        this.left = 0;
        this.width = 10;
        this.height = 10;
        this.cursorx = 0;
        this.cursory = 0;
        this.line_counter = 0;
        this.residual = 0;
        this.myscreen = screen;
        this.curfont = screen.fixedfont;
        this.buffer = true;
        this.wrap = true;
        this.scroll = true;
        this.transcriptmode = true;
        this.linebuffer = "";
        this.zforeground = screen.zforeground;
        this.zbackground = screen.zbackground;
    }
    
    void reset_line_count() {
        this.line_counter = 0;
    }
    
    void count_line() {
        ++this.line_counter;
    }
    
    void check_for_more() {
        final String more = "[MORE]";
        final String blank = "         ";
        if (this.line_counter >= this.height - 1) {
            this.myscreen.settext(this.top + this.cursory, this.left, more.toCharArray(), 0, more.length(), true, this.myscreen.fixedfont);
            this.myscreen.read_code();
            this.myscreen.settext(this.top + this.cursory, this.left, blank.toCharArray(), 0, blank.length(), false, this.myscreen.fixedfont);
            this.line_counter = 0;
        }
    }
    
    void erase_line(final short arg) {
        final char[] spaces = new char[this.width];
        for (short i = 0; i < this.width; ++i) {
            spaces[i] = ' ';
        }
        if (arg == 1) {
            this.myscreen.settext(this.cursory, this.left + this.cursorx, spaces, 0, this.width - this.cursorx);
        }
    }
    
    public void setbuffermode(final boolean buffermode) {
        if (this.buffer && !buffermode) {
            this.flush();
        }
        this.buffer = buffermode;
    }
    
    public void setwrapmode(final boolean wrapmode) {
        if (this.buffer) {
            this.flush();
        }
        this.wrap = wrapmode;
    }
    
    public void set_transcripting(final boolean transcriptmode) {
        this.transcriptmode = transcriptmode;
    }
    
    public boolean transcripting() {
        return this.transcriptmode;
    }
    
    public void setscroll(final boolean newscroll) {
        this.scroll = newscroll;
    }
    
    public void moveto(final int newleft, final int newtop) {
        this.left = newleft;
        this.top = newtop;
    }
    
    public void resize(final int newwidth, final int newheight) {
        this.width = newwidth;
        this.height = newheight;
        if (this.cursorx >= this.width || this.cursory >= this.height) {
            this.movecursor_noflush(0, 0);
        }
    }
    
    public int getLeft() {
        return this.left;
    }
    
    public int getTop() {
        return this.top;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public int getlines() {
        return this.height;
    }
    
    public int getchars() {
        return this.width;
    }
    
    public int getx() {
        return this.cursorx;
    }
    
    public int gety() {
        return this.cursory;
    }
    
    void movecursor_noflush(final int x, final int y) {
        this.cursorx = x;
        this.cursory = y;
    }
    
    public void movecursor(final int x, final int y) {
        this.flush();
        this.cursorx = x;
        this.cursory = y;
    }
    
    public void printzascii(final short ascii) {
        final short[] zascii = { ascii };
        this.printzascii(zascii);
    }
    
    public void printzascii(final short[] ascii) {
        final char[] unicode = new char[ascii.length];
        for (int i = 0; i < unicode.length; ++i) {
            unicode[i] = ZScreen.zascii_to_unicode(ascii[i]);
        }
        if (this.buffer) {
            this.bufferchars(unicode);
        }
        else {
            this.drawchars(unicode, 0, unicode.length);
        }
    }
    
    public void flush() {
        this.residual = this.charsWidth(this.linebuffer.toCharArray(), 0, this.linebuffer.length());
        this.drawstring(this.linebuffer);
        this.linebuffer = "";
    }
    
    public void newline() {
        this.newline(true);
    }
    
    protected void newline(final boolean flushbuffer) {
        if (this.myscreen.zforeground != this.zforeground) {
            this.myscreen.setZForeground(this.zforeground);
        }
        if (this.myscreen.zbackground != this.zbackground) {
            this.myscreen.setZBackground(this.zbackground);
        }
        if (flushbuffer) {
            this.flush();
        }
        this.residual = 0;
        if (this.cursory == this.height - 1) {
            if (this.scroll) {
                this.myscreen.scrollLines(this.top, this.height, 1);
            }
            this.movecursor_noflush(0, this.cursory);
        }
        else {
            this.movecursor_noflush(0, this.cursory + 1);
        }
        this.count_line();
    }
    
    public synchronized void bufferchars(final char[] chars) {
        this.linebuffer += chars;
        if (this.wrap) {
            int last = this.linebuffer.length();
            while (this.residual + this.charsWidth(this.linebuffer.toCharArray(), 0, last) > this.myscreen.size().width) {
                int space = this.linebuffer.lastIndexOf(32, last);
                if (space == -1) {
                    while (this.residual + this.charsWidth(this.linebuffer.toCharArray(), 0, last) > this.width * this.myscreen.charwidth()) {
                        --last;
                    }
                    this.drawchars(this.linebuffer.toCharArray(), 0, last);
                    this.linebuffer = this.linebuffer.substring(last);
                    this.newline(false);
                    last = this.linebuffer.length();
                }
                else if (this.residual + this.charsWidth(this.linebuffer.toCharArray(), 0, space) <= this.myscreen.size().width) {
                    final String printstring = this.linebuffer.substring(0, space);
                    this.drawstring(printstring);
                    while (space < this.linebuffer.length() && this.linebuffer.charAt(space) == ' ') {
                        ++space;
                    }
                    this.linebuffer = this.linebuffer.substring(space);
                    last = this.linebuffer.length();
                    this.newline(false);
                }
                else {
                    last = space - 1;
                }
            }
        }
    }
    
    public void clear() {
        final char[] spaces = new char[this.width];
        for (int i = 0; i < this.width; ++i) {
            spaces[i] = ' ';
        }
        for (int i = this.top; i < this.top + this.height; ++i) {
            this.myscreen.settext(i, this.left, spaces, 0, this.width);
        }
    }
    
    private void calculate_font() {
        int style = 0;
        final Font basefont = this.myscreen.fixedfont;
        if ((this.curzstyle & 0x2) != 0x0) {
            style |= 0x1;
        }
        if ((this.curzstyle & 0x4) != 0x0) {
            style |= 0x2;
        }
        this.curfont = new Font(basefont.getName(), style, basefont.getSize());
    }
    
    public void set_color(final int foreground, final int background) {
        this.flush();
        if (foreground != 0) {
            this.zforeground = foreground;
        }
        if (background != 0) {
            this.zbackground = background;
        }
    }
    
    public void set_text_style(final int style) {
        this.set_text_style(style, false);
    }
    
    protected void set_text_style(final int style, final boolean immediate) {
        final char[] thecode = { '\0' };
        if (immediate || !this.buffer) {
            if (style == 0) {
                this.curzstyle = 0;
            }
            else {
                this.curzstyle |= style;
            }
            this.calculate_font();
        }
        else {
            thecode[0] = (char)(style | 0x8000);
            this.bufferchars(thecode);
        }
    }
    
    private boolean is_control(final char ch) {
        return ch >= '\u8000';
    }
    
    private void parse_control(final char control) {
        if (control >= '\u8000' && control <= '\u800f') {
            this.set_text_style(control & 0xFFFF7FFF, true);
        }
    }
    
    public void drawchars(final char[] chars, final int offset, final int length) {
        char control = '\0';
        if (length != 0) {
            if (this.myscreen.zforeground != this.zforeground) {
                this.myscreen.setZForeground(this.zforeground);
            }
            if (this.myscreen.zbackground != this.zbackground) {
                this.myscreen.setZBackground(this.zbackground);
            }
            if (this.scroll && this.cursorx == 0) {
                this.check_for_more();
            }
            for (int firstchar = offset, runlength = 0; firstchar < offset + length; firstchar += runlength + 1, this.cursorx += runlength, runlength = 0) {
                for (int i = firstchar - offset; i < length; ++i, ++runlength) {
                    if (this.is_control(chars[offset + i])) {
                        control = chars[offset + i];
                        break;
                    }
                }
                this.myscreen.settext(this.top + this.cursory, this.left + this.cursorx, chars, firstchar, runlength, (this.curzstyle & 0x1) == 0x1, this.curfont);
                this.parse_control(control);
                control = '\0';
            }
        }
    }
    
    public void drawstring(final String text) {
        this.drawchars(text.toCharArray(), 0, text.length());
    }
    
    public int charsWidth(final char[] line, final int offset, final int length) {
        return this.myscreen.charwidth() * length;
    }
}
