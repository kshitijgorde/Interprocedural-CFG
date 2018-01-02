import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class slider extends Applet implements Runnable
{
    Thread T;
    boolean pf;
    boolean pfK;
    boolean pfN;
    boolean FreeVersion;
    String hostName;
    boolean fromDisk;
    boolean FirstPass;
    int aWide;
    int aHigh;
    int howFast;
    AudioClip A;
    Image[] i_Array;
    String[] i_File;
    String[] Link;
    String[] Targ;
    int i_total;
    int waiting;
    Image i_Buff;
    Graphics g_Buff;
    boolean m_Good;
    int m_x;
    int m_y;
    int i_Focus;
    int i_count;
    int offset;
    int b_Thick;
    Color b_Color;
    int f_Thick;
    Color f_Color;
    Color l_Color;
    int l_Mode;
    Color bgColor;
    int v_Mode;
    int theDirection;
    
    public void init() {
        this.aWide = Integer.valueOf(this.getParameter("width"));
        this.aHigh = Integer.valueOf(this.getParameter("height"));
        this.DoK();
        final String parameter = this.getParameter("SoundFile");
        if (parameter != null) {
            this.A = this.getAudioClip(this.getCodeBase(), parameter);
        }
        final String parameter2 = this.getParameter("Speed");
        if (parameter2 != null) {
            this.howFast = Integer.parseInt(parameter2);
        }
        if (this.howFast < 1) {
            this.howFast = 1;
        }
        final String parameter3 = this.getParameter("BackgroundColor");
        if (parameter3 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter3, ",");
            this.bgColor = new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        final String parameter4 = this.getParameter("Direction");
        if (parameter4 != null) {
            this.theDirection = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("VerticalMode");
        if (parameter5 != null) {
            this.v_Mode = Integer.parseInt(parameter5);
        }
        final String parameter6 = this.getParameter("FrameThickness");
        if (parameter6 != null) {
            this.f_Thick = Integer.parseInt(parameter6);
        }
        --this.f_Thick;
        final String parameter7 = this.getParameter("FrameColor");
        if (parameter7 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter7, ",");
            this.f_Color = new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()));
        }
        final String parameter8 = this.getParameter("BorderThickness");
        if (parameter8 != null) {
            this.b_Thick = Integer.parseInt(parameter8);
        }
        --this.b_Thick;
        final String parameter9 = this.getParameter("BorderColor");
        if (parameter9 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter9, ",");
            this.b_Color = new Color(Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()));
        }
        final String parameter10 = this.getParameter("LinkMode");
        if (parameter10 != null) {
            this.l_Mode = Integer.parseInt(parameter10);
        }
        final String parameter11 = this.getParameter("LinkColor");
        if (parameter11 != null) {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter11, ",");
            this.l_Color = new Color(Integer.parseInt(stringTokenizer4.nextToken()), Integer.parseInt(stringTokenizer4.nextToken()), Integer.parseInt(stringTokenizer4.nextToken()));
        }
        for (int i = 0; i < 100; ++i) {
            this.i_File[i] = this.getParameter("Image" + (i + 1));
            this.Link[i] = this.getParameter("Link" + (i + 1));
            this.Targ[i] = this.getParameter("Target" + (i + 1));
            if (this.i_File[i] == null) {
                break;
            }
            ++this.i_total;
        }
        this.i_Buff = this.createImage(this.aWide + 1, this.aHigh);
        this.g_Buff = this.i_Buff.getGraphics();
    }
    
    public void start() {
        if (this.T == null) {
            (this.T = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.T != null) {
            this.T.stop();
            this.T = null;
        }
    }
    
    public void run() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        for (int i = 0; i < this.i_total; ++i) {
            mediaTracker.addImage(this.i_Array[i] = this.getImage(this.getCodeBase(), this.i_File[i]), 0);
        }
        this.waiting = 1;
        while (true) {
            if (this.waiting == 1) {
                if (mediaTracker.checkAll(true)) {
                    this.waiting = 0;
                }
            }
            else if (this.waiting == 0) {
                ++this.offset;
                if (this.offset == this.i_Array[this.i_count].getWidth(this)) {
                    if (this.i_total > 1) {
                        ++this.i_count;
                        this.i_count %= this.i_total;
                    }
                    this.offset = 0;
                }
                if (this.m_Good) {
                    int aWide = 0;
                    if (this.theDirection == 0) {
                        aWide = 0;
                    }
                    if (this.theDirection == 1) {
                        aWide = this.aWide;
                    }
                    int i_count = this.i_count;
                    boolean b = true;
                    while (b) {
                        if (this.theDirection == 0) {
                            b = false;
                            if (aWide - this.offset < this.aWide) {
                                b = true;
                            }
                            if (this.m_x < aWide - this.offset + this.i_Array[i_count].getWidth(this)) {
                                break;
                            }
                        }
                        if (this.theDirection == 1) {
                            b = false;
                            if (aWide + this.offset > 0) {
                                b = true;
                            }
                            if (this.m_x > aWide + this.offset - this.i_Array[i_count].getWidth(this)) {
                                break;
                            }
                        }
                        if (this.theDirection == 0) {
                            aWide += this.i_Array[i_count].getWidth(this);
                        }
                        if (this.theDirection == 1) {
                            aWide -= this.i_Array[i_count].getWidth(this);
                        }
                        if (this.i_total > 1) {
                            i_count = ++i_count % this.i_total;
                        }
                    }
                    this.i_Focus = i_count;
                }
                else {
                    this.i_Focus = -1;
                }
            }
            this.repaint();
            try {
                Thread.sleep(this.howFast);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(this.g_Buff);
        graphics.drawImage(this.i_Buff, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
        if (this.FreeVersion && !this.fromDisk && this.FirstPass) {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.aWide, this.aHigh);
            graphics.setColor(Color.green);
            graphics.setFont(new Font("Dialog", 0, 12));
            graphics.drawString("Slider Slideshow Applet", 5, 14);
            graphics.drawString("By www.CodeBrain.com", 5, 27);
            graphics.drawString("Free Version 3.0.2", 5, 40);
            graphics.drawString("Private / Non-Profit Use Only", 5, 53);
            graphics.setColor(this.bgColor);
            this.FirstPass = false;
            try {
                Thread.sleep(20000L);
            }
            catch (InterruptedException ex) {}
        }
        if (this.pf || !this.pf) {
            graphics.setColor(this.bgColor);
            graphics.fillRect(0, 0, this.aWide, this.aHigh);
            if (this.waiting == 0) {
                int aWide = 0;
                if (this.theDirection == 0) {
                    aWide = 0;
                }
                if (this.theDirection == 1) {
                    aWide = this.aWide;
                }
                int n = 0;
                int i_count = this.i_count;
                boolean b = true;
                while (b) {
                    if (this.theDirection == 0) {
                        b = false;
                        if (aWide - this.offset < this.aWide) {
                            b = true;
                        }
                    }
                    if (this.theDirection == 1) {
                        b = false;
                        if (aWide + this.offset > 0) {
                            b = true;
                        }
                    }
                    if (this.v_Mode == 1) {
                        n = (this.aHigh - this.i_Array[i_count].getHeight(this)) / 2;
                    }
                    if (this.v_Mode == 2) {
                        n = this.aHigh - this.i_Array[i_count].getHeight(this);
                    }
                    if (this.theDirection == 1) {
                        aWide -= this.i_Array[i_count].getWidth(this);
                    }
                    if (this.theDirection == 0) {
                        graphics.drawImage(this.i_Array[i_count], aWide - this.offset, n, null);
                    }
                    if (this.theDirection == 1) {
                        graphics.drawImage(this.i_Array[i_count], aWide + this.offset, n, null);
                    }
                    int n2 = aWide;
                    int n3 = n;
                    int width = this.i_Array[i_count].getWidth(this);
                    int height = this.i_Array[i_count].getHeight(this);
                    graphics.setColor(this.f_Color);
                    if ((this.l_Mode == 0 || this.l_Mode == 1) && this.i_Focus == i_count && this.Link[this.i_Focus] != null) {
                        graphics.setColor(this.l_Color);
                    }
                    for (int i = 0; i <= this.f_Thick; ++i) {
                        if (this.theDirection == 0) {
                            graphics.drawRect(n2 - this.offset, n3, width - 1, height - 1);
                        }
                        if (this.theDirection == 1) {
                            graphics.drawRect(n2 + this.offset, n3, width - 1, height - 1);
                        }
                        ++n2;
                        ++n3;
                        width -= 2;
                        height -= 2;
                    }
                    graphics.setColor(this.b_Color);
                    if ((this.l_Mode == 0 || this.l_Mode == 2) && this.i_Focus == i_count && this.Link[this.i_Focus] != null) {
                        graphics.setColor(this.l_Color);
                    }
                    for (int j = 0; j <= this.b_Thick; ++j) {
                        if (this.theDirection == 0) {
                            graphics.drawRect(n2 - this.offset, n3, width - 1, height - 1);
                        }
                        if (this.theDirection == 1) {
                            graphics.drawRect(n2 + this.offset, n3, width - 1, height - 1);
                        }
                        ++n2;
                        ++n3;
                        width -= 2;
                        height -= 2;
                    }
                    if (this.theDirection == 0) {
                        aWide += this.i_Array[i_count].getWidth(this);
                    }
                    if (this.i_total > 1) {
                        i_count = ++i_count % this.i_total;
                    }
                    if (!this.pf) {
                        graphics.setColor(Color.black);
                        graphics.fill3DRect(1, 1, this.aWide - 4, 28, true);
                        graphics.setColor(Color.white);
                        graphics.setFont(new Font("Dialog", 0, 10));
                        if (!this.pfN && this.pfK) {
                            graphics.drawString("Notice wrong", 4, 13);
                            graphics.drawString("or missing...", 4, 24);
                        }
                        if (!this.pfK && this.pfN) {
                            graphics.drawString("Need key for:", 4, 13);
                            graphics.drawString(this.hostName, 4, 24);
                        }
                        if (!this.pfK && !this.pfN) {
                            graphics.drawString("Errors:", 4, 13);
                            graphics.drawString("Key & Notice", 4, 24);
                        }
                        graphics.setColor(Color.red);
                        graphics.draw3DRect(1, 1, this.aWide - 4, 28, true);
                        graphics.setColor(this.bgColor);
                    }
                }
                return;
            }
            if (this.waiting == 1) {
                graphics.setColor(Color.red);
                graphics.drawString("Loading...", 20, 20);
            }
        }
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.i_Focus >= 0 && this.Link[this.i_Focus] != null) {
            try {
                final URL url = new URL(this.Link[this.i_Focus]);
                final String s = this.Targ[this.i_Focus];
                final AppletContext appletContext = this.getAppletContext();
                if (s == null) {
                    appletContext.showDocument(url);
                }
                else {
                    appletContext.showDocument(url, s);
                }
                return true;
            }
            catch (MalformedURLException ex) {
                return false;
            }
        }
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.A != null) {
            this.A.play();
        }
        return false;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        this.m_Good = true;
        this.m_x = x;
        this.m_y = y;
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int x, final int y) {
        this.m_Good = true;
        this.m_x = x;
        this.m_y = y;
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.m_Good = false;
        return true;
    }
    
    private void DoK() {
        this.pf = false;
        this.pfN = false;
        this.pfK = false;
        final int int1 = Integer.parseInt("452617".substring(0, 1));
        final int int2 = Integer.parseInt("452617".substring(1, 2));
        final int int3 = Integer.parseInt("452617".substring(2, 3));
        final int int4 = Integer.parseInt("452617".substring(3, 4));
        final int int5 = Integer.parseInt("452617".substring(4, 5));
        final int int6 = Integer.parseInt("452617".substring(5, 6));
        final URL documentBase = this.getDocumentBase();
        this.hostName = documentBase.getHost();
        if (documentBase.getProtocol().toUpperCase().indexOf("FILE") > -1) {
            this.pf = true;
            this.pfK = true;
            this.fromDisk = true;
        }
        final String parameter = this.getParameter("Notice");
        if (parameter == null) {
            this.pf = false;
        }
        if (parameter != null) {
            if (parameter.equalsIgnoreCase("Applet by www.CodeBrain.com")) {
                this.pfN = true;
            }
            else {
                this.pfN = false;
                this.pf = false;
            }
        }
        String string = "CodeBrainRocks";
        final String parameter2 = this.getParameter("KeyCode");
        if (parameter2 == null || parameter2.startsWith("FREE")) {
            this.FreeVersion = true;
        }
        if (parameter2 != null && parameter2.length() > 10) {
            string = parameter2;
        }
        int n = 0;
        for (int i = 0; i < string.length(); ++i) {
            if (string.substring(i, i + 1).indexOf("|") > -1) {
                ++n;
            }
        }
        if (n == 0) {
            string += "|CodeBrainRocks";
            n = 1;
        }
        final String[] array = new String[13];
        final StringTokenizer stringTokenizer = new StringTokenizer(string, "|");
        final int n2 = n + 1;
        for (int j = 0; j < n2; ++j) {
            array[j] = stringTokenizer.nextToken();
        }
        final String[] array2 = new String[13];
        for (int k = 0; k < n2; ++k) {
            final String substring = array[k].substring(3);
            final String substring2 = substring.substring(0, substring.length() - 3);
            String string2 = "";
            int n3 = 0;
            for (int l = 0; l < substring2.length(); ++l) {
                int char1 = substring2.charAt(l);
                if (n3 == 0) {
                    char1 += int1;
                }
                if (n3 == 1) {
                    char1 += int2;
                }
                if (n3 == 2) {
                    char1 += int3;
                }
                if (n3 == 3) {
                    char1 += int4;
                }
                if (n3 == 4) {
                    char1 += int5;
                }
                if (n3 == 5) {
                    char1 += int6;
                }
                string2 += String.valueOf((char)char1);
                if (++n3 > 5) {
                    n3 = 0;
                }
            }
            String string3 = "";
            int n4 = 0;
            for (int n5 = 0; n5 < string2.length(); ++n5) {
                int n6 = string2.charAt(n5) - '\u0003';
                if (n6 == 118) {
                    n6 = 126;
                }
                string3 += String.valueOf((char)n6);
                if (++n4 > 5) {
                    n4 = 0;
                }
            }
            array2[k] = string3;
        }
        final String upperCase = String.valueOf(this.getDocumentBase()).toUpperCase();
        for (int n7 = 0; n7 < n2; ++n7) {
            if (upperCase.indexOf(array2[n7]) > -1) {
                this.pfK = true;
            }
        }
        if (this.FreeVersion) {
            this.pfK = true;
        }
        if (this.pfN && this.pfK) {
            this.pf = true;
        }
    }
    
    public slider() {
        this.pf = false;
        this.pfK = false;
        this.pfN = false;
        this.FreeVersion = false;
        this.hostName = "This URL";
        this.fromDisk = false;
        this.FirstPass = true;
        this.howFast = 19;
        this.i_Array = new Image[100];
        this.i_File = new String[100];
        this.Link = new String[100];
        this.Targ = new String[100];
        this.waiting = -1;
        this.m_Good = false;
        this.b_Thick = 5;
        this.b_Color = Color.orange;
        this.f_Thick = 5;
        this.f_Color = Color.darkGray;
        this.l_Color = Color.red;
        this.bgColor = Color.white;
        this.v_Mode = 1;
    }
}
