// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.screenmodel;

import java.awt.Font;

public class ZWindow
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
    
    public ZWindow(final ZScreen myscreen) {
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
        this.myscreen = myscreen;
        this.curfont = myscreen.fixedfont;
        this.buffer = true;
        this.wrap = true;
        this.scroll = true;
        this.transcriptmode = true;
        this.linebuffer = "";
        this.zforeground = myscreen.zforeground;
        this.zbackground = myscreen.zbackground;
    }
    
    public void reset_line_count() {
        this.line_counter = 0;
    }
    
    void count_line() {
        ++this.line_counter;
    }
    
    void check_for_more() {
        final String s = "[MORE]";
        final String s2 = "         ";
        if (this.line_counter >= this.height - 1) {
            this.myscreen.settext(this.top + this.cursory, this.left, s.toCharArray(), 0, s.length(), true, this.myscreen.fixedfont);
            this.myscreen.read_code();
            this.myscreen.settext(this.top + this.cursory, this.left, s2.toCharArray(), 0, s2.length(), false, this.myscreen.fixedfont);
            this.line_counter = 0;
        }
    }
    
    public void erase_line(final short n) {
        final char[] array = new char[this.width];
        for (int i = 0; i < this.width; i = (short)(i + 1)) {
            array[i] = ' ';
        }
        if (n == 1) {
            this.myscreen.settext(this.cursory, this.left + this.cursorx, array, 0, this.width - this.cursorx);
        }
    }
    
    public void setbuffermode(final boolean buffer) {
        if (this.buffer && !buffer) {
            this.flush();
        }
        this.buffer = buffer;
    }
    
    public void setwrapmode(final boolean wrap) {
        if (this.buffer) {
            this.flush();
        }
        this.wrap = wrap;
    }
    
    public void set_transcripting(final boolean transcriptmode) {
        this.transcriptmode = transcriptmode;
    }
    
    public boolean transcripting() {
        return this.transcriptmode;
    }
    
    public void setscroll(final boolean scroll) {
        this.scroll = scroll;
    }
    
    public void moveto(final int left, final int top) {
        this.left = left;
        this.top = top;
    }
    
    public void resize(final int width, final int height) {
        this.width = width;
        this.height = height;
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
    
    void movecursor_noflush(final int cursorx, final int cursory) {
        this.cursorx = cursorx;
        this.cursory = cursory;
    }
    
    public void movecursor(final int cursorx, final int cursory) {
        this.flush();
        this.cursorx = cursorx;
        this.cursory = cursory;
    }
    
    public void printzascii(final short n) {
        this.printzascii(new short[] { n });
    }
    
    public void printzascii(final short[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = ZScreen.zascii_to_unicode(array[i]);
        }
        if (this.buffer) {
            this.bufferchars(array2);
        }
        else {
            this.drawchars(array2, 0, array2.length);
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
    
    protected void newline(final boolean b) {
        if (this.myscreen.zforeground != this.zforeground) {
            this.myscreen.setZForeground(this.zforeground);
        }
        if (this.myscreen.zbackground != this.zbackground) {
            this.myscreen.setZBackground(this.zbackground);
        }
        if (b) {
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
    
    public synchronized void bufferchars(final char[] array) {
        this.linebuffer += array;
        if (this.wrap) {
            int n = this.linebuffer.length();
            while (this.residual + this.charsWidth(this.linebuffer.toCharArray(), 0, n) > this.myscreen.size().width) {
                int lastIndex = this.linebuffer.lastIndexOf(32, n);
                if (lastIndex == -1) {
                    while (this.residual + this.charsWidth(this.linebuffer.toCharArray(), 0, n) > this.width * this.myscreen.charwidth()) {
                        --n;
                    }
                    this.drawchars(this.linebuffer.toCharArray(), 0, n);
                    this.linebuffer = this.linebuffer.substring(n);
                    this.newline(false);
                    n = this.linebuffer.length();
                }
                else if (this.residual + this.charsWidth(this.linebuffer.toCharArray(), 0, lastIndex) <= this.myscreen.size().width) {
                    this.drawstring(this.linebuffer.substring(0, lastIndex));
                    while (lastIndex < this.linebuffer.length() && this.linebuffer.charAt(lastIndex) == ' ') {
                        ++lastIndex;
                    }
                    this.linebuffer = this.linebuffer.substring(lastIndex);
                    n = this.linebuffer.length();
                    this.newline(false);
                }
                else {
                    n = lastIndex - 1;
                }
            }
        }
    }
    
    public void clear() {
        final char[] array = new char[this.width];
        for (int i = 0; i < this.width; ++i) {
            array[i] = ' ';
        }
        for (int j = this.top; j < this.top + this.height; ++j) {
            this.myscreen.settext(j, this.left, array, 0, this.width);
        }
    }
    
    private void calculate_font() {
        int n = 0;
        final Font fixedfont = this.myscreen.fixedfont;
        if ((this.curzstyle & 0x2) != 0x0) {
            n |= 0x1;
        }
        if ((this.curzstyle & 0x4) != 0x0) {
            n |= 0x2;
        }
        this.curfont = new Font(fixedfont.getName(), n, fixedfont.getSize());
    }
    
    public void set_color(final int zforeground, final int zbackground) {
        this.flush();
        if (zforeground != 0) {
            this.zforeground = zforeground;
        }
        if (zbackground != 0) {
            this.zbackground = zbackground;
        }
    }
    
    public void set_text_style(final int n) {
        this.set_text_style(n, false);
    }
    
    protected void set_text_style(final int n, final boolean b) {
        final char[] array = { '\0' };
        if (b || !this.buffer) {
            if (n == 0) {
                this.curzstyle = 0;
            }
            else {
                this.curzstyle |= n;
            }
            this.calculate_font();
        }
        else {
            array[0] = (char)(n | 0x8000);
            this.bufferchars(array);
        }
    }
    
    private boolean is_control(final char c) {
        return c >= '\u8000';
    }
    
    private void parse_control(final char c) {
        if (c >= '\u8000' && c <= '\u800f') {
            this.set_text_style(c & 0xFFFF7FFF, true);
        }
    }
    
    public void drawchars(final char[] array, final int n, final int n2) {
        char c = '\0';
        if (n2 != 0) {
            if (this.myscreen.zforeground != this.zforeground) {
                this.myscreen.setZForeground(this.zforeground);
            }
            if (this.myscreen.zbackground != this.zbackground) {
                this.myscreen.setZBackground(this.zbackground);
            }
            if (this.scroll && this.cursorx == 0) {
                this.check_for_more();
            }
            for (int i = n, n3 = 0; i < n + n2; i += n3 + 1, this.cursorx += n3, n3 = 0) {
                for (int j = i - n; j < n2; ++j, ++n3) {
                    if (this.is_control(array[n + j])) {
                        c = array[n + j];
                        break;
                    }
                }
                this.myscreen.settext(this.top + this.cursory, this.left + this.cursorx, array, i, n3, (this.curzstyle & 0x1) == 0x1, this.curfont);
                this.parse_control(c);
                c = '\0';
            }
        }
    }
    
    public void drawstring(final String s) {
        this.drawchars(s.toCharArray(), 0, s.length());
    }
    
    public int charsWidth(final char[] array, final int n, final int n2) {
        return this.myscreen.charwidth() * n2;
    }
}
