import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class FaetonWriter extends Applet implements Runnable
{
    private Image offImage;
    private Graphics offGrfx;
    private Thread m_TypeWriter;
    private Color m_fClr;
    private Color m_bClr;
    private String[] m_Text;
    private int s_Count;
    private Font m_TTF;
    private int m_Speed;
    private int m_Delay;
    private String[] c_Text;
    private int l_Count;
    private int f_Count;
    private int n_Text;
    private int t_Count;
    private String FontFace;
    private int FontStyle;
    private int FontSize;
    private String m_BaseURL;
    private String m_FrameURL;
    
    public void stop() {
        this.s_Count = 0;
        this.l_Count = 0;
        this.f_Count = 1;
        if (this.m_TypeWriter != null) {
            this.m_TypeWriter.stop();
            this.m_TypeWriter = null;
        }
    }
    
    public void paint(final Graphics screen) {
        this.offGrfx.setColor(this.m_bClr);
        this.offGrfx.fillRect(0, 0, this.size().width, this.size().height);
        this.offGrfx.setColor(this.m_fClr);
        this.offGrfx.setFont(this.m_TTF);
        for (int i = 0; i <= this.l_Count; ++i) {
            final Double hLL = new Double(this.FontSize * (1.0 + 1.25 * i));
            final int hL = (int)(Object)hLL;
            this.offGrfx.drawString(this.c_Text[i], 10, hL);
        }
        screen.drawImage(this.offImage, 0, 0, this);
    }
    
    public void destroy() {
    }
    
    private final int SetNewInteger(final String intString, final int min_Value, final int max_Value) {
        if (this.getParameter(intString) != null) {
            final int ini_Value = Integer.parseInt(this.getParameter(intString));
            int new_Value = (ini_Value < min_Value) ? min_Value : ini_Value;
            new_Value = ((new_Value > max_Value) ? max_Value : new_Value);
            return new_Value;
        }
        return min_Value;
    }
    
    public void update(final Graphics screen) {
        this.paint(screen);
    }
    
    private void beginNew() {
        for (int i = 0; i <= this.l_Count; ++i) {
            this.c_Text[i] = null;
        }
        this.l_Count = 0;
        this.f_Count = 0;
        this.s_Count = 0;
        ++this.t_Count;
        if (this.t_Count > this.n_Text) {
            this.t_Count = 1;
        }
        this.repaint();
    }
    
    private final Color SetNewColor(final String clrString, final Color def_Color) {
        if (this.getParameter(clrString) != null) {
            final String ini_String = this.getParameter(clrString);
            final int i = ini_String.indexOf(44);
            final int cR = Integer.parseInt(ini_String.substring(0, i));
            final int j = ini_String.indexOf(44, i + 1);
            final int cG = Integer.parseInt(ini_String.substring(i + 1, j));
            final int cB = Integer.parseInt(ini_String.substring(j + 1, ini_String.length()));
            final Color SetNewColor = new Color(cR, cG, cB);
            return SetNewColor;
        }
        return def_Color;
    }
    
    public void start() {
        this.s_Count = 0;
        this.l_Count = 0;
        this.f_Count = 0;
        this.t_Count = 1;
        if (this.m_TypeWriter == null) {
            (this.m_TypeWriter = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: FaetonWriter\r\n" + "Version: 1.1, 12 March 2000\r\n" + "Author: Victor Waldberg\r\n" + "Created y Victor Waldberg, Faeton\r\n" + "All right reserved\r\n" + "info@faetonus.com";
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (!this.m_BaseURL.equalsIgnoreCase("none")) {
            URL url = null;
            try {
                String newUrl = this.getCodeBase() + this.m_BaseURL;
                newUrl = newUrl + "?FrameNumber=" + this.t_Count;
                url = new URL(newUrl);
            }
            catch (MalformedURLException ex) {}
            if (this.m_FrameURL != "none") {
                this.getAppletContext().showDocument(url, this.m_FrameURL);
            }
            else {
                this.getAppletContext().showDocument(url);
            }
            this.repaint();
        }
        return true;
    }
    
    public void run() {
        while (true) {
            try {
                ++this.s_Count;
                if (this.s_Count == this.m_Text[this.t_Count].length()) {
                    this.c_Text[this.l_Count] = this.m_Text[this.t_Count].substring(this.f_Count, this.s_Count);
                    this.repaint();
                    Thread.sleep(this.m_Delay);
                    this.beginNew();
                }
                if (this.m_Text[this.t_Count].charAt(this.s_Count) != '*') {
                    this.c_Text[this.l_Count] = this.m_Text[this.t_Count].substring(this.f_Count, this.s_Count) + "_";
                }
                else {
                    this.c_Text[this.l_Count] = this.m_Text[this.t_Count].substring(this.f_Count, this.s_Count);
                }
                boolean shortSleep;
                if (this.m_Text[this.t_Count].charAt(this.s_Count) == '*') {
                    ++this.l_Count;
                    ++this.s_Count;
                    this.f_Count = this.s_Count;
                    shortSleep = true;
                }
                else {
                    shortSleep = false;
                }
                this.repaint();
                if (!shortSleep) {
                    Thread.sleep(this.m_Speed);
                }
                else {
                    Thread.sleep(10 * this.m_Speed);
                }
            }
            catch (InterruptedException e) {
                this.stop();
            }
        }
    }
    
    public void init() {
        this.c_Text = new String[25];
        this.m_Text = new String[25];
        this.n_Text = 1;
        this.FontSize = this.SetNewInteger("FontSize", 10, 24);
        this.FontStyle = this.SetNewInteger("FontStyle", 0, 3);
        this.FontFace = this.SetNewString("FontFace", "Tahoma");
        this.m_TTF = new Font(this.FontFace, this.FontStyle, this.FontSize);
        if (this.getParameter("Text1") == null) {
            this.m_Text[1] = "This applet submitted to Victor Waldberg, Faeton*info@faetonus.com*www.faetonus.com";
            this.n_Text = 1;
        }
        else {
            while (this.getParameter("Text" + this.n_Text) != null) {
                this.m_Text[this.n_Text] = this.getParameter("Text" + this.n_Text);
                ++this.n_Text;
            }
            --this.n_Text;
        }
        this.m_fClr = this.SetNewColor("ForeColor", Color.white);
        this.m_bClr = this.SetNewColor("BackColor", Color.blue);
        this.m_Speed = this.SetNewInteger("Speed", 50, 500);
        this.m_Delay = this.SetNewInteger("Delay", 1000, 10000);
        this.m_BaseURL = this.SetNewString("URL", "none");
        this.m_FrameURL = this.SetNewString("Frame", "none");
        this.offImage = this.createImage(this.size().width, this.size().height);
        this.offGrfx = this.offImage.getGraphics();
    }
    
    private final String SetNewString(final String strString, final String def_String) {
        if (this.getParameter(strString) != null) {
            final String new_String = this.getParameter(strString);
            return new_String;
        }
        return def_String;
    }
}
