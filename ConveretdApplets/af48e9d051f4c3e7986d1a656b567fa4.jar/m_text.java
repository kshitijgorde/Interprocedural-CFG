import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;

// 
// Decompiled by Procyon v0.5.30
// 

public class m_text
{
    private int NbCurrentStrings;
    private int Wx;
    private int Wy;
    private int Lines;
    private String[] TotalStrings;
    private String[] StringSave;
    private String Cursor;
    private int NbTotalStrings;
    private String FontName;
    private int FontStyle;
    private int FontSize;
    private int FontHeight;
    private Font theFonts;
    private Font theOldFonts;
    private FontMetrics theFontsMetrics;
    private int WinWidth;
    private int WinHeight;
    private int x;
    private int y;
    private int Justif;
    private int tmp;
    private int VJustif;
    private int TxtIndex;
    static int CENTER;
    static int LEFT;
    static int RIGHT;
    static int TOP;
    private Color InitialColor;
    private Color FinalColor;
    private Color CurrentColor;
    private int Ri;
    private int Gi;
    private int Bi;
    private int Rp;
    private int Gp;
    private int Bp;
    private int Transition;
    private int NbInter;
    private int CurrentInter;
    private int FrameCount;
    private int FrameToWait;
    private int Tempo;
    private int TempoTime;
    private int phase;
    static int InitialToFinal;
    static int FinalToInitial;
    static int TempoFinal;
    static int NONE;
    static int NONEX2;
    static int FADE;
    static int FADEX2;
    static int CHARBYCHAR;
    static int ZOOMIN;
    static int ZOOMOUT;
    private String TargetPage;
    private String TargetFrame;
    
    m_text(final int winWidth, final int winHeight, final String s, final String s2, final String s3, final String s4, final String s5, final String fontName, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String targetFrame, final String targetPage) {
        this.VJustif = m_text.TOP;
        int n = 0;
        int i = 0;
        this.NbTotalStrings = 0;
        while (i < s.length()) {
            if (s.charAt(i++) == '#') {
                ++this.NbTotalStrings;
            }
        }
        this.TotalStrings = new String[++this.NbTotalStrings];
        for (int j = 0; j < this.NbTotalStrings; ++j) {
            int n2 = s.indexOf("#", n);
            if (n2 == -1) {
                n2 = s.length();
            }
            this.TotalStrings[j] = s.substring(n, n2);
            n = n2 + 1;
        }
        this.WinWidth = winWidth;
        this.WinHeight = winHeight;
        if (s2 == null) {
            this.Wx = this.WinWidth / 2;
        }
        else {
            this.Wx = Math.abs(Integer.parseInt(s2));
        }
        if (s3 == null) {
            this.Wy = this.WinHeight / 2;
        }
        else {
            this.Wy = Math.abs(Integer.parseInt(s3));
        }
        if (s4 == null) {
            this.Lines = 1;
        }
        else {
            this.Lines = Math.abs(Integer.parseInt(s4));
        }
        if (s5 == null) {
            this.Justif = m_text.CENTER;
        }
        else if (s5.toUpperCase().equals("LEFT")) {
            this.Justif = m_text.LEFT;
        }
        else if (s5.toUpperCase().equals("RIGHT")) {
            this.Justif = m_text.RIGHT;
        }
        else {
            this.Justif = m_text.CENTER;
        }
        if (s6 == null) {
            this.FontSize = 12;
        }
        else {
            this.FontSize = Math.abs(Integer.parseInt(s6));
        }
        if (s7 == null) {
            this.FontStyle = 0;
        }
        else if (s7.toUpperCase().equals("BOLD")) {
            this.FontStyle = 1;
        }
        else if (s7.toUpperCase().equals("ITALIC")) {
            this.FontStyle = 2;
        }
        else {
            this.FontStyle = 0;
        }
        if (fontName == null) {
            this.FontName = "Helvetica";
        }
        else {
            this.FontName = fontName;
        }
        this.theFonts = new Font(this.FontName, this.FontStyle, this.FontSize);
        if (s8 == null) {
            this.InitialColor = new Color(0);
        }
        else {
            this.InitialColor = new Color(Integer.parseInt(s8, 16));
        }
        if (s9 == null) {
            this.FinalColor = new Color(16777215);
        }
        else {
            this.FinalColor = new Color(Integer.parseInt(s9, 16));
        }
        this.CurrentColor = this.InitialColor;
        if (s11 == null) {
            this.FrameToWait = 20;
        }
        else {
            this.FrameToWait = Math.abs(Integer.parseInt(s11));
        }
        this.FrameCount = this.FrameToWait;
        if (s10 == null) {
            this.Transition = m_text.NONE;
        }
        else if (s10.toUpperCase().equals("FADE")) {
            this.Transition = m_text.FADE;
            this.NbInter = 30;
            this.Ri = this.InitialColor.getRed();
            this.Gi = this.InitialColor.getGreen();
            this.Bi = this.InitialColor.getBlue();
            this.Rp = (this.FinalColor.getRed() - this.Ri) / (this.NbInter + 1);
            this.Gp = (this.FinalColor.getGreen() - this.Gi) / (this.NbInter + 1);
            this.Bp = (this.FinalColor.getBlue() - this.Bi) / (this.NbInter + 1);
            this.TempoTime = 60;
            this.phase = m_text.InitialToFinal;
        }
        else if (s10.toUpperCase().equals("FADEX2")) {
            this.Transition = m_text.FADEX2;
            this.NbInter = 15;
            this.Ri = this.InitialColor.getRed();
            this.Gi = this.InitialColor.getGreen();
            this.Bi = this.InitialColor.getBlue();
            this.Rp = (this.FinalColor.getRed() - this.Ri) / (this.NbInter + 1);
            this.Gp = (this.FinalColor.getGreen() - this.Gi) / (this.NbInter + 1);
            this.Bp = (this.FinalColor.getBlue() - this.Bi) / (this.NbInter + 1);
            this.TempoTime = 30;
            this.phase = m_text.InitialToFinal;
        }
        else if (s10.toUpperCase().equals("CHARBYCHAR")) {
            this.Transition = m_text.CHARBYCHAR;
            this.Cursor = "_";
            this.StringSave = new String[this.NbTotalStrings];
            for (int k = 0; k < this.NbTotalStrings; ++k) {
                this.StringSave[k] = this.TotalStrings[k];
            }
            for (int l = 0; l < this.NbTotalStrings; ++l) {
                this.TotalStrings[l] = "";
            }
            this.CurrentInter = 0;
            this.NbInter = 0;
        }
        else if (s10.toUpperCase().equals("ZOOMIN")) {
            this.Transition = m_text.ZOOMIN;
            this.CurrentInter = 1;
            this.NbInter = 40;
            this.VJustif = m_text.CENTER;
        }
        else if (s10.toUpperCase().equals("ZOOMOUT")) {
            this.Transition = m_text.ZOOMOUT;
            this.CurrentInter = 1;
            this.NbInter = 40;
            this.VJustif = m_text.CENTER;
        }
        else {
            this.Transition = m_text.NONE;
        }
        this.TargetPage = targetPage;
        this.TargetFrame = targetFrame;
        this.CurrentInter = 0;
    }
    
    public void Progress(final Graphics graphics) {
        this.theOldFonts = graphics.getFont();
        graphics.setFont(this.theFonts);
        this.theFontsMetrics = graphics.getFontMetrics();
        this.FontHeight = this.theFontsMetrics.getMaxDescent() + this.theFontsMetrics.getMaxAscent();
        this.tmp = this.FontHeight;
        for (int i = 0; i < this.Lines; ++i) {
            if (this.TxtIndex + i < this.NbTotalStrings) {
                if (this.Justif == m_text.CENTER) {
                    this.x = this.Wx - this.theFontsMetrics.stringWidth(this.TotalStrings[this.TxtIndex + i]) / 2;
                }
                else if (this.Justif == m_text.RIGHT) {
                    this.x = this.Wx - this.theFontsMetrics.stringWidth(this.TotalStrings[this.TxtIndex + i]);
                }
                else {
                    this.x = this.Wx;
                }
                if (this.VJustif == m_text.CENTER) {
                    this.y = Math.round(this.Wy + (this.Lines / 2.0f - i) * this.FontHeight);
                }
                else {
                    this.y = this.Wy + i * this.FontHeight;
                }
                graphics.setColor(this.CurrentColor);
                graphics.drawString(this.TotalStrings[this.TxtIndex + i], this.x, this.y);
            }
        }
        if (this.FrameCount > 0) {
            --this.FrameCount;
        }
        else {
            this.FrameCount = this.FrameToWait;
            if (this.Transition == m_text.FADE || this.Transition == m_text.FADEX2) {
                this.CurrentColor = new Color(Math.round(this.Rp * this.CurrentInter + this.Ri), Math.round(this.Gp * this.CurrentInter + this.Gi), Math.round(this.Bp * this.CurrentInter + this.Bi));
                if (this.phase == m_text.InitialToFinal) {
                    if (this.CurrentInter < this.NbInter) {
                        ++this.CurrentInter;
                    }
                    else {
                        this.phase = m_text.TempoFinal;
                        this.Tempo = this.TempoTime;
                    }
                }
                else if (this.phase == m_text.FinalToInitial) {
                    if (this.CurrentInter > 0) {
                        --this.CurrentInter;
                    }
                    else {
                        this.phase = m_text.InitialToFinal;
                        this.TxtIndex += this.Lines;
                        if (this.TxtIndex >= this.NbTotalStrings) {
                            this.TxtIndex = 0;
                        }
                    }
                }
                else if (this.Tempo > 0) {
                    --this.Tempo;
                }
                else {
                    this.phase = m_text.FinalToInitial;
                }
            }
            else if (this.Transition == m_text.CHARBYCHAR) {
                ++this.CurrentInter;
                for (int j = this.NbInter + 1; j < this.NbTotalStrings; ++j) {
                    this.TotalStrings[j] = "";
                }
                if (this.CurrentInter <= this.StringSave[this.NbInter].length()) {
                    this.TotalStrings[this.NbInter] = this.StringSave[this.NbInter].substring(0, this.CurrentInter);
                }
                else {
                    this.CurrentInter = 0;
                    if (this.NbInter - this.TxtIndex < this.Lines - 1 && this.NbInter < this.NbTotalStrings - 1) {
                        ++this.NbInter;
                    }
                    else {
                        this.TxtIndex += this.Lines;
                        if (this.TxtIndex >= this.NbTotalStrings) {
                            this.TxtIndex = 0;
                            for (int k = 0; k < this.NbTotalStrings; ++k) {
                                this.TotalStrings[k] = "";
                            }
                        }
                        if (this.NbInter < this.NbTotalStrings - 1) {
                            ++this.NbInter;
                        }
                        else {
                            this.NbInter = 0;
                        }
                    }
                }
            }
            else if (this.Transition == m_text.ZOOMIN) {
                if (this.CurrentInter < this.NbInter) {
                    ++this.CurrentInter;
                }
                else {
                    this.CurrentInter = 1;
                    this.TxtIndex += this.Lines;
                    if (this.TxtIndex >= this.NbTotalStrings) {
                        this.TxtIndex = 0;
                    }
                }
                this.theFonts = new Font(this.FontName, this.FontStyle, this.FontSize * this.CurrentInter / this.NbInter);
            }
            else if (this.Transition == m_text.ZOOMOUT) {
                if (this.CurrentInter < this.NbInter - 1) {
                    ++this.CurrentInter;
                }
                else {
                    this.CurrentInter = 0;
                    this.TxtIndex += this.Lines;
                    if (this.TxtIndex >= this.NbTotalStrings) {
                        this.TxtIndex = 0;
                    }
                }
                this.theFonts = new Font(this.FontName, this.FontStyle, this.FontSize * (this.NbInter - this.CurrentInter) / this.NbInter);
            }
            else {
                this.TxtIndex += this.Lines;
                if (this.TxtIndex >= this.NbTotalStrings) {
                    this.TxtIndex = 0;
                }
            }
        }
        graphics.setFont(this.theOldFonts);
    }
    
    public int getX() {
        return this.Wx;
    }
    
    public int getY() {
        return this.Wy;
    }
    
    public int getCount() {
        return this.y;
    }
    
    public int getCount2() {
        return this.tmp;
    }
    
    public String getTargetFrame() {
        return this.TargetFrame;
    }
    
    public String getTargetPage() {
        return this.TargetPage;
    }
    
    static {
        m_text.LEFT = 1;
        m_text.RIGHT = 2;
        m_text.TOP = 3;
        m_text.FinalToInitial = 1;
        m_text.TempoFinal = 2;
        m_text.NONEX2 = 1;
        m_text.FADE = 2;
        m_text.FADEX2 = 3;
        m_text.CHARBYCHAR = 4;
        m_text.ZOOMIN = 5;
        m_text.ZOOMOUT = 6;
    }
}
