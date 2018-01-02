import java.awt.FontMetrics;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Font;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class URLLab_int extends Canvas implements Runnable
{
    Font font;
    String IP;
    private String IK;
    String URLFrame;
    Image IO;
    MediaTracker IJ;
    boolean kc;
    boolean kd;
    boolean Paused;
    String[] IL;
    int IM;
    int IN;
    private transient Thread t;
    private long StartTime;
    public int ke;
    boolean Center;
    boolean Right;
    int TimeOffset;
    private boolean MouseOver;
    int BaseLine;
    int URLLen;
    
    public void setDoneTime(final int ke) {
        this.TimeOffset = ke * 1000;
        this.ke = ke;
    }
    
    URLLab_int(final Image io) {
        this.font = null;
        this.IK = null;
        this.URLFrame = null;
        this.IO = null;
        this.IJ = null;
        this.kc = false;
        this.kd = false;
        this.Paused = false;
        this.IN = 0;
        this.t = null;
        this.StartTime = 0L;
        this.ke = 0;
        this.Center = false;
        this.Right = false;
        this.TimeOffset = 0;
        this.MouseOver = false;
        this.IO = io;
        (this.IJ = new MediaTracker(this)).addImage(io, 0);
    }
    
    void startTimer() {
        if (this.t == null) {
            this.StartTime = new Date().getTime() - this.TimeOffset;
            (this.t = new Thread(this)).start();
            this.TimeOffset = 0;
            this.Paused = false;
        }
    }
    
    URLLab_int() {
        this.font = null;
        this.IK = null;
        this.URLFrame = null;
        this.IO = null;
        this.IJ = null;
        this.kc = false;
        this.kd = false;
        this.Paused = false;
        this.IN = 0;
        this.t = null;
        this.StartTime = 0L;
        this.ke = 0;
        this.Center = false;
        this.Right = false;
        this.TimeOffset = 0;
        this.MouseOver = false;
        this.kc = true;
    }
    
    public void run() {
        Label_0077: {
            if (this.IJ != null) {
                try {
                    this.IJ.waitForAll();
                    this.IJ = null;
                    this.repaint();
                    break Label_0077;
                }
                catch (InterruptedException ex) {
                    return;
                }
            }
            if (this.t != null) {
                while (!this.kd && !this.Paused) {
                    try {
                        Thread.sleep(1000L);
                        this.update(this.getGraphics());
                    }
                    catch (Exception ex2) {
                        this.kd = true;
                    }
                }
            }
        }
        this.t = null;
    }
    
    public URLLab_int(final String ip) {
        this.font = null;
        this.IK = null;
        this.URLFrame = null;
        this.IO = null;
        this.IJ = null;
        this.kc = false;
        this.kd = false;
        this.Paused = false;
        this.IN = 0;
        this.t = null;
        this.StartTime = 0L;
        this.ke = 0;
        this.Center = false;
        this.Right = false;
        this.TimeOffset = 0;
        this.MouseOver = false;
        this.IP = ip;
    }
    
    private static final String ConvSt(final int n) {
        String s = Integer.toString(n);
        if (s.length() < 2) {
            s = '0' + s;
        }
        return s;
    }
    
    public void paint(final Graphics graphics) {
        if (this.IO != null) {
            graphics.drawImage(this.IO, 0, 0, this);
        }
        else if (this.kc) {
            if (!this.kd && !this.Paused && this.t != null) {
                this.ke = (int)((new Date().getTime() - this.StartTime) / 1000L);
            }
            String s = ConvSt(this.ke % 3600 / 60) + ':' + ConvSt(this.ke % 60);
            if (this.ke > 3600) {
                s = Integer.toString(this.ke / 3600) + ':' + s;
            }
            final int stringWidth = graphics.getFontMetrics().stringWidth(s);
            if (this.Paused) {
                graphics.setColor(Color.gray);
            }
            graphics.drawString(s, this.Center ? ((this.getSize().width - stringWidth) / 2) : (this.Right ? (this.getSize().width - stringWidth) : 0), this.BaseLine);
        }
        else if (this.IN != 0) {
            final Dimension size = this.getSize();
            graphics.setColor(Color.red);
            graphics.fillRect(0, 0, size.width, size.height);
            graphics.setColor(Color.white);
            graphics.fillRect(4, 4, size.width - 8, size.height - 8);
            graphics.setColor(Color.black);
            graphics.setFont(this.font);
            for (int i = 0; i < this.IN; ++i) {
                if (this.IL[i] != null) {
                    graphics.drawString(this.IL[i], 20, 15 + (i + 1) * this.IM);
                }
            }
        }
        else {
            if (this.MouseOver) {
                if (this.getBackground().getGreen() < 160 && this.getBackground().getRed() < 160) {
                    graphics.setColor(Color.white);
                }
                else {
                    graphics.setColor(Color.blue);
                }
            }
            else if (this.getBackground().getGreen() < 130 && this.getBackground().getRed() < 130 && this.getBackground().getBlue() < 130) {
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            graphics.drawString(this.IP, 0, this.BaseLine);
            if (this.MouseOver) {
                graphics.drawLine(0, this.BaseLine + 1, this.URLLen, this.BaseLine + 1);
            }
        }
    }
    
    void IG(final String ik) {
        this.IK = ik;
        if (ik != null) {
            this.setCursor(new Cursor(12));
        }
    }
    
    void IH() {
        if (this.IK != null) {
            ((Grid_int)this.getParent()).IF(this.IK, this.URLFrame, false);
        }
        else if (this.kc && !this.kd && ((Grid_int)this.getParent()).getParameter("NOPAUSE") == null) {
            this.Paused = !this.Paused;
            this.TimeOffset = this.ke * 1000;
            this.update(this.getGraphics());
        }
    }
    
    void SetMouseOver(final boolean b) {
        this.MouseOver = (b && this.IK != null);
        this.update(this.getGraphics());
    }
    
    URLLab_int(final String s, final Font font) {
        this.font = null;
        this.IK = null;
        this.URLFrame = null;
        this.IO = null;
        this.IJ = null;
        this.kc = false;
        this.kd = false;
        this.Paused = false;
        this.IN = 0;
        this.t = null;
        this.StartTime = 0L;
        this.ke = 0;
        this.Center = false;
        this.Right = false;
        this.TimeOffset = 0;
        this.MouseOver = false;
        String substring = s;
        this.IN = 0;
        int n = 20;
        int n2 = 0;
        this.font = font;
        if (this.font.getSize() < 13) {
            this.font = new Font(this.font.getName(), 1, 13);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(this.font);
        this.IM = fontMetrics.getHeight() + 4;
        this.IL = new String[50];
        this.IN = 0;
        while (substring != null) {
            final int index = substring.indexOf(String.valueOf('\r') + String.valueOf('\n'));
            String substring2;
            if (index != -1) {
                substring2 = substring.substring(0, index);
            }
            else {
                substring2 = substring;
            }
            this.IL[this.IN] = substring2;
            ++this.IN;
            final int stringWidth = fontMetrics.stringWidth(substring2);
            if (stringWidth > n2) {
                n2 = stringWidth;
            }
            n += this.IM;
            if (index != -1 && index + 2 < substring.length()) {
                substring = substring.substring(index + 2);
            }
            else {
                substring = null;
            }
        }
        final int n3 = n;
        n2 += 40;
        this.setSize(n2, n3 + 20);
    }
}
