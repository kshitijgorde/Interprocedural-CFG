import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Corf_Scroller extends Applet implements Runnable
{
    private Thread m_Corf_Scroller;
    private final String Scroller_Address = "http://www.corfman.com/robert/Java/Corf_Scroller";
    private String m_Corf_Link;
    private String m_Corf_Target;
    private String m_Corf_Text;
    private int m_Corf_Timer;
    private Color m_Corf_BackGround;
    private Color m_Corf_ForeGround;
    private int m_Corf_Thickness;
    private Color m_Corf_Border;
    private boolean m_Corf_Status;
    private boolean m_Corf_Jump;
    private boolean m_Corf_Alt_Status;
    private String m_Corf_Status_Msg;
    private String m_Corf_Font;
    private int i_Corf_Pos;
    private int i_Corf_Len;
    private int i_Corf_y;
    private int i_Corf_Width;
    private int i_Corf_Height;
    private Font f_Corf_New;
    private Image im_Corf_Buf;
    private Graphics g_Corf_Buf;
    private Image im_Corf_Text;
    private Graphics g_Corf_Text;
    
    public void stop() {
        if (this.m_Corf_Scroller != null) {
            this.m_Corf_Scroller.stop();
            this.m_Corf_Scroller = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.m_Corf_Status) {
            this.getAppletContext().showStatus(this.m_Corf_Alt_Status ? this.m_Corf_Status_Msg : this.m_Corf_Link);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.m_Corf_Status) {
            this.getAppletContext().showStatus(" ");
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (++this.i_Corf_Pos > this.i_Corf_Len) {
            this.i_Corf_Pos = 1;
        }
        int i = this.i_Corf_Pos * -1;
        do {
            this.g_Corf_Buf.drawImage(this.im_Corf_Text, i, 0, this);
            i += this.i_Corf_Len;
        } while (i < this.i_Corf_Width);
        this.g_Corf_Buf.setColor(this.m_Corf_Border);
        for (int j = 0; j < this.m_Corf_Thickness; ++j) {
            this.g_Corf_Buf.draw3DRect(j, j, this.i_Corf_Width - (j << 1) - 1, this.i_Corf_Height - (j << 1) - 1, false);
        }
        graphics.drawImage(this.im_Corf_Buf, 0, 0, this);
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "Corf_Link", "URL", "Targe Link when applet is clicked" }, { "Corf_Target", "String", "Target window when jumping to Link" }, { "Corf_Text", "String", "Text to Scroll in the Applet Area" }, { "Corf_Timer", "int", "Time in Milliseconds between scrolling pixel" }, { "Corf_Background", "Color", "Background Color in HEX format of RRGGBB.  white-FFFFFF, black-000000" }, { "Corf_Foreground", "Color", "Foreground Color in HEX format(See BackGround)" }, { "Corf_Thickness", "String", "Applet Border Thickness" }, { "Corf_Border", "String", "Applet Border Color" }, { "Corf_Status", "boolean", "<>0 Changes status line on mouse over, 0 does nothing" }, { "Corf_Jump", "boolean", "<>0 Jumps to Link on click, 0 does nothing" }, { "Corf_Alt_Status", "boolean", "<>0 Displays Als Msg Below, 0 displays Link param" }, { "Corf_Status_Msg", "String", "Alt Msg to display in status line on mouse over" }, { "Corf_Font", "String", "Name of the Font to use(Can be unpredictable to override font name)" } };
    }
    
    public boolean fit_font() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.f_Corf_New);
        return this.i_Corf_Height - (this.m_Corf_Thickness << 1) > fontMetrics.getMaxAscent() + fontMetrics.getMaxDescent();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.m_Corf_Scroller == null) {
            (this.m_Corf_Scroller = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: Corf_Scroller\r\nAuthor: Robert Corfman\r\nVersion: 1.4\r\nCreated: May 28,2000\r\nE-Mail: Robert@corfman.com";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m_Corf_Jump) {
            try {
                this.getAppletContext().showDocument(new URL(this.m_Corf_Link), this.m_Corf_Target);
            }
            catch (MalformedURLException ex) {
                this.m_Corf_Text = "Error|MalformedURLException|Target URL invalid|";
            }
        }
        return true;
    }
    
    public Corf_Scroller() {
        this.m_Corf_Text = "Robert Corfman's Scrolling Applet. Email: Robert@Corfman.com for more info ";
        this.m_Corf_Timer = 50;
        this.m_Corf_BackGround = Color.white;
        this.m_Corf_ForeGround = Color.black;
        this.m_Corf_Thickness = 1;
        this.m_Corf_Border = Color.gray;
        this.m_Corf_Status = true;
        this.m_Corf_Jump = true;
        this.i_Corf_Pos = 1;
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    this.repaint();
                    Thread.sleep(this.m_Corf_Timer);
                }
            }
            catch (InterruptedException ex) {
                this.stop();
                continue;
            }
            break;
        }
    }
    
    public void init() {
        this.i_Corf_Width = this.size().width;
        this.i_Corf_Height = this.size().height;
        this.f_Corf_New = this.getFont();
        final String parameter = this.getParameter("Corf_Link");
        this.m_Corf_Link = parameter;
        if (parameter == null) {
            this.m_Corf_Link = "http://www.corfman.com/robert/Java/Corf_Scroller";
        }
        if ((this.m_Corf_Target = this.getParameter("Corf_Target")) == null) {
            this.m_Corf_Target = "_self";
        }
        final String parameter2;
        if ((parameter2 = this.getParameter("Corf_Text")) != null) {
            this.m_Corf_Text = parameter2.concat(" ");
        }
        final String parameter3;
        if ((parameter3 = this.getParameter("Corf_Timer")) != null && (this.m_Corf_Timer = Integer.parseInt(parameter3)) < 10) {
            this.m_Corf_Timer = 10;
        }
        final String parameter4;
        if ((parameter4 = this.getParameter("Corf_Background")) != null) {
            this.m_Corf_BackGround = new Color(Integer.parseInt(parameter4, 16));
        }
        final String parameter5;
        if ((parameter5 = this.getParameter("Corf_Foreground")) != null) {
            this.m_Corf_ForeGround = new Color(Integer.parseInt(parameter5, 16));
        }
        final String parameter6;
        if ((parameter6 = this.getParameter("Corf_Thickness")) != null && (this.m_Corf_Thickness = Integer.parseInt(parameter6)) < 0) {
            this.m_Corf_Thickness = 0;
        }
        final String parameter7;
        if ((parameter7 = this.getParameter("Corf_Border")) != null) {
            this.m_Corf_Border = new Color(Integer.parseInt(parameter7, 16));
        }
        final String parameter8;
        if ((parameter8 = this.getParameter("Corf_Status")) != null) {
            this.m_Corf_Status = (Integer.parseInt(parameter8) != 0);
        }
        final String parameter9;
        if ((parameter9 = this.getParameter("Corf_Jump")) != null) {
            this.m_Corf_Jump = (Integer.parseInt(parameter9) != 0);
        }
        final String parameter10;
        if ((parameter10 = this.getParameter("Corf_Alt_Status")) != null) {
            this.m_Corf_Alt_Status = (Integer.parseInt(parameter10) != 0);
        }
        if ((this.m_Corf_Status_Msg = this.getParameter("Corf_Status_Msg")) == null) {
            this.m_Corf_Status_Msg = "http://www.corfman.com/robert/Java/Corf_Scroller";
        }
        final String parameter11;
        this.m_Corf_Font = (((parameter11 = this.getParameter("Corf_Font")) != null) ? parameter11 : this.f_Corf_New.getName());
        int size = this.f_Corf_New.getSize();
        do {
            this.f_Corf_New = new Font(this.m_Corf_Font, 0, ++size);
        } while (this.fit_font());
        while (!this.fit_font() && size > 2) {
            this.f_Corf_New = new Font(this.m_Corf_Font, 0, --size);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.f_Corf_New);
        this.i_Corf_Len = fontMetrics.stringWidth(this.m_Corf_Text);
        final int ascent = fontMetrics.getAscent();
        this.i_Corf_y = (this.i_Corf_Height >> 1) - (ascent + fontMetrics.getDescent() >> 1) + ascent;
        this.im_Corf_Text = this.createImage(this.i_Corf_Len, this.i_Corf_Height);
        (this.g_Corf_Text = this.im_Corf_Text.getGraphics()).setColor(this.m_Corf_BackGround);
        this.g_Corf_Text.fillRect(0, 0, this.i_Corf_Len, this.i_Corf_Height);
        this.g_Corf_Text.setColor(this.m_Corf_ForeGround);
        this.g_Corf_Text.setFont(this.f_Corf_New);
        this.g_Corf_Text.drawString(this.m_Corf_Text, 0, this.i_Corf_y);
        if (this.im_Corf_Buf == null) {
            this.im_Corf_Buf = this.createImage(this.i_Corf_Width, this.i_Corf_Height);
        }
        this.g_Corf_Buf = this.im_Corf_Buf.getGraphics();
    }
}
