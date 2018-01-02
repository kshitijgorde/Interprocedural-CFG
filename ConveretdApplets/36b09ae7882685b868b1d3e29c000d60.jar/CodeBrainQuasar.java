import java.io.IOException;
import java.io.DataInputStream;
import java.awt.Event;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CodeBrainQuasar extends Applet implements Runnable
{
    boolean pf;
    Thread T;
    MediaTracker track;
    int TC;
    float PI2;
    int aWide;
    int aHigh;
    Color bg;
    AudioClip A;
    String sFile;
    int sMode;
    int FlyHowMany;
    int yPos;
    int xPos;
    int vIndent;
    int hIndent;
    Image i_Move;
    Image i_Fore;
    Image i_Back;
    Image i_Float;
    Image i_Buff;
    Graphics g_Buff;
    float AmpV;
    float workAmpV;
    float AmpH;
    float workAmpH;
    float OffsetV;
    float OffsetH;
    double Sin;
    double SinF;
    double workSep;
    double V;
    double VMax;
    double VMin;
    double V_ROC;
    double workV;
    int V_sw;
    int sw_DirH;
    int sw_DirV;
    int minDevV;
    int minDevH;
    int maxDevV;
    int maxDevH;
    int dir;
    int Sep_sw;
    double SepRate;
    double Sep;
    double SepMax;
    double SepMin;
    boolean AmpOsc;
    int a;
    int f_x;
    int f_y;
    int f_z;
    int FloatMode;
    int Float_sw;
    int FloatCounter;
    int xPosF;
    int yPosF;
    double FloatSpeed;
    int F_RC;
    int F_RL;
    int f_R;
    int f_bx;
    int f_by;
    int f_bxx;
    int f_byy;
    int frontX;
    int frontY;
    int backX;
    int backY;
    String sbTextM;
    String sbTextS;
    String Link;
    String Target;
    
    public void init() {
        this.track = new MediaTracker(this);
        this.aWide = this.size().width;
        this.aHigh = this.size().height;
        this.doK();
        final String parameter = this.getParameter("TimeConstant");
        this.TC = ((parameter == null) ? 15 : Integer.valueOf(parameter));
        final String parameter2 = this.getParameter("FrontImage");
        if (parameter2 != null) {
            this.i_Fore = this.getImage(this.getDocumentBase(), parameter2);
        }
        if (this.i_Fore != null) {
            this.track.addImage(this.i_Fore, 2);
        }
        final String parameter3 = this.getParameter("FrontXY");
        if (parameter3 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter3, ",");
            this.frontX = Integer.parseInt(stringTokenizer.nextToken());
            this.frontY = Integer.parseInt(stringTokenizer.nextToken());
        }
        final String parameter4 = this.getParameter("BackImage");
        if (parameter4 != null) {
            this.i_Back = this.getImage(this.getDocumentBase(), parameter4);
        }
        if (this.i_Back != null) {
            this.track.addImage(this.i_Back, 1);
        }
        final String parameter5 = this.getParameter("BackXY");
        if (parameter5 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter5, ",");
            this.backX = Integer.parseInt(stringTokenizer2.nextToken());
            this.backY = Integer.parseInt(stringTokenizer2.nextToken());
        }
        final String parameter6 = this.getParameter("BackgroundColor");
        if (parameter6 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter6, ",");
            this.bg = new Color(Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()), Integer.parseInt(stringTokenizer3.nextToken()));
        }
        final String parameter7 = this.getParameter("FloatImage");
        if (parameter7 != null) {
            this.i_Float = this.getImage(this.getDocumentBase(), parameter7);
        }
        if (this.i_Float != null) {
            this.track.addImage(this.i_Float, 3);
        }
        this.f_bxx = this.aWide;
        this.f_byy = this.aHigh;
        final String parameter8 = this.getParameter("FloatXXYY");
        if (parameter8 != null) {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter8, ",");
            this.f_bx = Integer.parseInt(stringTokenizer4.nextToken());
            this.f_bxx = Integer.parseInt(stringTokenizer4.nextToken());
            this.f_by = Integer.parseInt(stringTokenizer4.nextToken());
            this.f_byy = Integer.parseInt(stringTokenizer4.nextToken());
        }
        this.f_x = this.f_bx;
        this.f_y = this.f_by;
        final String parameter9 = this.getParameter("FloatZ");
        if (parameter9 != null) {
            this.f_z = Integer.parseInt(parameter9);
        }
        final String parameter10 = this.getParameter("FloatMode");
        if (parameter10 != null) {
            this.FloatMode = Integer.parseInt(parameter10);
        }
        if (this.FloatMode > 9 || this.FloatMode < 0) {
            this.FloatMode = 0;
        }
        final String parameter11 = this.getParameter("FloatSpeed789");
        this.F_RL = ((parameter11 == null) ? 1 : Integer.valueOf(parameter11));
        this.FloatSpeed = 0.04500000178813934;
        if (parameter11 != null) {
            this.FloatSpeed = Float.valueOf(parameter11) / 100.0f;
        }
        final String parameter12 = this.getParameter("FlyImage");
        if (parameter12 != null) {
            this.i_Move = this.getImage(this.getDocumentBase(), parameter12);
        }
        if (this.i_Move != null) {
            this.track.addImage(this.i_Move, 0);
        }
        this.hIndent = 0;
        this.vIndent = 0;
        final String parameter13 = this.getParameter("FlyCenterXY");
        if (parameter13 != null) {
            final StringTokenizer stringTokenizer5 = new StringTokenizer(parameter13, ",");
            this.hIndent = Integer.parseInt(stringTokenizer5.nextToken()) - this.aWide / 2;
            this.vIndent = Integer.parseInt(stringTokenizer5.nextToken()) - this.aHigh / 2;
        }
        final String parameter14 = this.getParameter("FlyHowMany");
        if (parameter14 != null) {
            this.FlyHowMany = Integer.parseInt(parameter14);
        }
        final String parameter15 = this.getParameter("FlyDirection");
        if (parameter15 != null) {
            this.dir = Integer.parseInt(parameter15);
        }
        if (this.dir == 0) {
            this.dir = -1;
        }
        else {
            this.dir = 1;
        }
        this.V = 0.04500000178813934;
        this.VMax = this.V;
        this.VMin = this.V;
        final String parameter16 = this.getParameter("FlySpeedMaxMinROC");
        if (parameter16 != null) {
            final StringTokenizer stringTokenizer6 = new StringTokenizer(parameter16, ",");
            this.VMax = Float.valueOf(stringTokenizer6.nextToken()) / 100.0f;
            this.VMin = Float.valueOf(stringTokenizer6.nextToken()) / 100.0f;
            this.V_ROC = Float.valueOf(stringTokenizer6.nextToken()) / 10000.0f;
        }
        if (this.VMax + this.VMin != 0.0) {
            this.V = (this.VMax + this.VMin) / 2.0;
        }
        this.workV = this.V;
        final String parameter17 = this.getParameter("FlySeparateMaxMinROC");
        if (parameter17 != null) {
            final StringTokenizer stringTokenizer7 = new StringTokenizer(parameter17, ",");
            this.SepMax = Float.valueOf(stringTokenizer7.nextToken());
            this.SepMin = Float.valueOf(stringTokenizer7.nextToken());
            this.SepRate = Float.valueOf(stringTokenizer7.nextToken()) / 1000.0f;
        }
        if (this.SepMax + this.SepMin != 0.0) {
            this.Sep = (this.SepMax + this.SepMin) / 2.0;
        }
        this.workSep = this.Sep;
        final String parameter18 = this.getParameter("FlyOscillateXXYY");
        if (parameter18 != null) {
            this.AmpOsc = true;
            final StringTokenizer stringTokenizer8 = new StringTokenizer(parameter18, ",");
            this.maxDevH = Integer.parseInt(stringTokenizer8.nextToken());
            this.minDevH = Integer.parseInt(stringTokenizer8.nextToken());
            this.maxDevV = Integer.parseInt(stringTokenizer8.nextToken());
            this.minDevV = Integer.parseInt(stringTokenizer8.nextToken());
        }
        final String parameter19 = this.getParameter("SoundFile");
        if (parameter19 != null) {
            this.sFile = parameter19;
            this.A = this.getAudioClip(this.getCodeBase(), this.sFile);
            this.sMode = 1;
        }
        final String parameter20 = this.getParameter("SoundMode");
        if (parameter20 != null) {
            this.sMode = Integer.parseInt(parameter20);
        }
        final String parameter21 = this.getParameter("StatusBarTextS");
        if (parameter21 != null) {
            this.sbTextS = parameter21;
        }
        final String parameter22 = this.getParameter("StatusBarTextM");
        if (parameter22 != null) {
            this.sbTextM = parameter22;
        }
        final String parameter23 = this.getParameter("Link");
        if (parameter23 != null) {
            this.Link = parameter23;
        }
        final String parameter24 = this.getParameter("Target");
        if (parameter24 != null) {
            this.Target = parameter24;
        }
        if (this.FlyHowMany == 0) {
            this.FlyHowMany = 1;
        }
        if (this.i_Move == null && this.i_Float != null && this.f_z != 0) {
            this.f_z = 0;
        }
        if (this.f_z > this.FlyHowMany) {
            this.f_z = this.FlyHowMany;
        }
        this.i_Buff = this.createImage(this.size().width, this.size().height);
        this.g_Buff = this.i_Buff.getGraphics();
    }
    
    public void start() {
        if (this.A != null) {
            this.A.stop();
        }
        try {
            if (this.i_Move != null) {
                this.track.waitForID(0);
            }
            if (this.i_Back != null) {
                this.track.waitForID(1);
            }
            if (this.i_Fore != null) {
                this.track.waitForID(2);
            }
            if (this.i_Float != null) {
                this.track.waitForID(3);
            }
        }
        catch (InterruptedException ex) {
            return;
        }
        if (this.i_Move != null) {
            this.AmpH = (this.aWide - this.i_Move.getWidth(this)) / 2;
            this.AmpV = (this.aHigh - this.i_Move.getHeight(this)) / 2;
        }
        if (!this.AmpOsc && this.i_Move != null) {
            this.workAmpH = this.AmpH;
            this.workAmpV = this.AmpV;
            this.minDevH = (this.aWide - this.i_Move.getWidth(this)) / 2;
            this.maxDevH = this.minDevH;
            this.minDevV = (this.aHigh - this.i_Move.getHeight(this)) / 2;
            this.maxDevV = this.minDevV;
        }
        if (this.AmpOsc && this.i_Move != null) {
            this.minDevH -= this.i_Move.getWidth(this) / 2;
            this.maxDevH -= this.i_Move.getWidth(this) / 2;
            this.minDevV -= this.i_Move.getHeight(this) / 2;
            this.maxDevV -= this.i_Move.getHeight(this) / 2;
        }
        this.OffsetV = this.AmpV;
        this.OffsetH = this.AmpH;
        this.Sin = 0.0;
        this.SinF = 0.0;
        if (this.T == null) {
            (this.T = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.T != null) {
            if (this.A != null) {
                this.A.stop();
            }
            this.T.stop();
            this.T = null;
        }
    }
    
    public void run() {
        if (this.A == null) {
            this.A = this.getAudioClip(this.getCodeBase(), this.sFile);
        }
        if (this.A != null && this.pf) {
            if (this.sMode == 1) {
                this.A.play();
            }
            if (this.sMode == 2) {
                this.A.loop();
            }
        }
        if (this.sbTextS != null) {
            this.getAppletContext().showStatus(this.sbTextS);
        }
        while (true) {
            this.Sin += this.workV;
            if (this.workV >= this.VMax) {
                this.V_sw = 0;
            }
            if (this.workV <= this.VMin) {
                this.V_sw = 1;
            }
            if (this.V_sw == 1) {
                this.workV += this.V_ROC;
            }
            if (this.V_sw == 0) {
                this.workV -= this.V_ROC;
            }
            if (this.Sin > this.PI2) {
                this.Sin -= this.PI2;
            }
            this.repaint();
            try {
                Thread.sleep(this.TC);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        this.g_Buff.setColor(this.bg);
        this.g_Buff.fillRect(0, 0, this.aWide, this.aHigh);
        if (this.i_Back != null) {
            this.g_Buff.drawImage(this.i_Back, this.backX, this.backY, this);
        }
        double sin = this.Sin;
        this.a = 0;
        while (this.a < this.FlyHowMany) {
            this.yPos = (int)(Math.sin(sin) * this.workAmpV / this.dir + this.OffsetV);
            this.xPos = (int)(Math.cos(sin) * this.workAmpH + this.OffsetH);
            sin += this.workSep;
            if (this.i_Float != null && this.f_z == this.a && this.f_z < this.FlyHowMany) {
                this.doFloat();
            }
            if (this.i_Move != null) {
                this.g_Buff.drawImage(this.i_Move, this.xPos + this.hIndent, this.yPos + this.vIndent, this);
            }
            if (this.i_Float != null && this.f_z == this.a + 1 && this.f_z == this.FlyHowMany) {
                this.doFloat();
            }
            ++this.a;
        }
        if (this.workSep >= this.SepMax) {
            this.Sep_sw = 1;
        }
        if (this.workSep <= this.SepMin) {
            this.Sep_sw = 0;
        }
        if (this.Sep_sw == 0) {
            this.workSep += this.SepRate;
        }
        if (this.Sep_sw == 1) {
            this.workSep -= this.SepRate;
        }
        if (this.workAmpV <= this.minDevV) {
            this.sw_DirV = 1;
        }
        if (this.workAmpV >= this.maxDevV) {
            this.sw_DirV = 0;
        }
        if (this.sw_DirV == 1) {
            ++this.workAmpV;
        }
        if (this.sw_DirV == 0) {
            --this.workAmpV;
        }
        if (this.workAmpH <= this.minDevH) {
            this.sw_DirH = 1;
        }
        if (this.workAmpH >= this.maxDevH) {
            this.sw_DirH = 0;
        }
        if (this.sw_DirH == 1) {
            ++this.workAmpH;
        }
        if (this.sw_DirH == 0) {
            --this.workAmpH;
        }
        if (this.i_Fore != null) {
            this.g_Buff.drawImage(this.i_Fore, this.frontX, this.frontY, this);
        }
        if (this.pf) {
            graphics.drawImage(this.i_Buff, 0, 0, this);
            return;
        }
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.aWide, this.aHigh);
        graphics.setColor(Color.red);
        graphics.drawString("Notice or key absent!", 20, 20);
    }
    
    public void doFloat() {
        int n = 666;
        if (this.FloatMode == 9) {
            if (this.F_RC == 0) {
                this.f_R = (int)Math.floor(Math.random() * 6.0);
            }
            ++this.F_RC;
            if (this.F_RC == this.F_RL) {
                this.F_RC = 0;
            }
            this.FloatMode = 6;
            if (this.f_R < 3) {
                this.FloatMode = 3;
            }
            n = 99;
        }
        if (this.FloatMode == 0) {
            this.g_Buff.drawImage(this.i_Float, this.f_x, this.f_y, this);
        }
        if (this.FloatMode == 1) {
            if (this.f_x >= this.f_bxx - this.i_Float.getWidth(this)) {
                this.f_x = this.f_bx;
                if (this.A != null && this.sMode == 3) {
                    this.A.stop();
                    this.A.play();
                }
            }
            this.g_Buff.drawImage(this.i_Float, this.f_x, this.f_y, this);
            ++this.f_x;
        }
        if (this.FloatMode == 2) {
            if (this.f_x <= this.f_bx) {
                this.f_x = this.f_bxx - this.i_Float.getWidth(this);
                if (this.A != null && this.sMode == 3) {
                    this.A.stop();
                    this.A.play();
                }
            }
            this.g_Buff.drawImage(this.i_Float, this.f_x, this.f_y, this);
            --this.f_x;
        }
        if (this.FloatMode == 3) {
            if (this.Float_sw == 0) {
                if (this.f_x >= this.f_bxx - this.i_Float.getWidth(this)) {
                    this.Float_sw = 1;
                    if (this.A != null && this.sMode == 3) {
                        this.A.stop();
                        this.A.play();
                    }
                }
                this.g_Buff.drawImage(this.i_Float, this.f_x, this.f_y, this);
                ++this.f_x;
            }
            if (this.Float_sw == 1) {
                if (this.f_x <= this.f_bx) {
                    this.Float_sw = 0;
                    if (this.A != null && this.sMode == 3) {
                        this.A.stop();
                        this.A.play();
                    }
                }
                this.g_Buff.drawImage(this.i_Float, this.f_x, this.f_y, this);
                --this.f_x;
            }
        }
        if (this.FloatMode == 4) {
            if (this.f_y >= this.f_byy - this.i_Float.getHeight(this)) {
                this.f_y = this.f_by;
                if (this.A != null && this.sMode == 3) {
                    this.A.stop();
                    this.A.play();
                }
            }
            this.g_Buff.drawImage(this.i_Float, this.f_x, this.f_y, this);
            ++this.f_y;
        }
        if (this.FloatMode == 5) {
            if (this.f_y <= this.f_by) {
                this.f_y = this.f_byy - this.i_Float.getHeight(this);
                if (this.A != null && this.sMode == 3) {
                    this.A.stop();
                    this.A.play();
                }
            }
            this.g_Buff.drawImage(this.i_Float, this.f_x, this.f_y, this);
            --this.f_y;
        }
        if (this.FloatMode == 6) {
            if (this.Float_sw == 0) {
                if (this.f_y >= this.f_byy - this.i_Float.getHeight(this)) {
                    this.Float_sw = 1;
                    if (this.A != null && this.sMode == 3) {
                        this.A.stop();
                        this.A.play();
                    }
                }
                this.g_Buff.drawImage(this.i_Float, this.f_x, this.f_y, this);
                ++this.f_y;
            }
            if (this.Float_sw == 1) {
                if (this.f_y <= this.f_by) {
                    this.Float_sw = 0;
                    if (this.A != null && this.sMode == 3) {
                        this.A.stop();
                        this.A.play();
                    }
                }
                this.g_Buff.drawImage(this.i_Float, this.f_x, this.f_y, this);
                --this.f_y;
            }
        }
        if (this.FloatMode == 7 || this.FloatMode == 8) {
            int n2 = 1;
            if (this.FloatMode == 8) {
                n2 = -1;
            }
            final int n3 = (this.f_bxx - this.f_bx) / 2 + this.f_bx - this.i_Float.getWidth(this) / 2;
            final int n4 = (this.f_byy - this.f_by) / 2 + this.f_by - this.i_Float.getHeight(this) / 2;
            this.SinF += this.FloatSpeed;
            if (this.SinF > this.PI2) {
                this.SinF -= this.PI2;
                if (this.A != null && this.sMode == 3) {
                    this.A.stop();
                    this.A.play();
                }
            }
            ++this.FloatCounter;
            if (this.FloatCounter >= this.FlyHowMany) {
                this.FloatCounter = 0;
            }
            final double sinF = this.SinF;
            this.xPosF = (int)(Math.cos(sinF) * ((this.f_bxx - this.f_bx - this.i_Float.getWidth(this)) / 2) / n2);
            this.yPosF = (int)(Math.sin(sinF) * ((this.f_byy - this.f_by - this.i_Float.getHeight(this)) / 2));
            this.g_Buff.drawImage(this.i_Float, n3 + this.xPosF, n4 + this.yPosF, this);
        }
        if (n == 99) {
            this.FloatMode = 9;
        }
    }
    
    private void FS() {
        if (this.A != null && this.sMode == 3) {
            this.A.stop();
            this.A.play();
        }
    }
    
    public final synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    URL getURL(final String s) {
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        return url;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.Link != null) {
            final URL url = this.getURL(this.getParameter("Link"));
            if (this.Target == null) {
                this.getAppletContext().showDocument(url);
            }
            if (this.Target != null) {
                this.getAppletContext().showDocument(url, this.Target);
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.sbTextM != null) {
            this.getAppletContext().showStatus(this.sbTextM);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.sbTextS != null) {
            this.getAppletContext().showStatus(this.sbTextS);
        }
        if (this.sbTextS == null && this.sbTextM != null) {
            this.getAppletContext().showStatus("");
        }
        return true;
    }
    
    private void doK() {
        this.pf = false;
        final String s = "5432154321543215";
        boolean b = false;
        boolean b2 = false;
        String string = "";
        final int[] array = new int[128];
        final int[] array2 = new int[128];
        final int[] array3 = new int[128];
        final String upperCase = String.valueOf(this.getCodeBase()).toUpperCase();
        final String parameter = this.getParameter("Notice");
        if (parameter == null) {
            this.pf = false;
        }
        if (parameter != null && parameter.equalsIgnoreCase("Applet by www.CodeBrain.com")) {
            b = true;
        }
        String line = "";
        try {
            final URL url = new URL(this.getCodeBase(), "CodeBrainQuasar.key");
            try {
                line = new DataInputStream(url.openStream()).readLine();
            }
            catch (IOException ex) {}
        }
        catch (MalformedURLException ex2) {}
        String s2 = s.toUpperCase();
        for (int i = 0; i < 3; ++i) {
            s2 = String.valueOf(s2) + s2;
        }
        for (int j = 0; j < s2.length(); ++j) {
            array3[j] = s2.charAt(j);
        }
        for (int k = 0; k < line.length(); ++k) {
            array2[k] = line.charAt(k);
        }
        for (int l = 0; l < 128; ++l) {
            array[l] = array2[l] - (array3[l] - 48);
            if (array[l] == 34) {
                array[l] = 126;
            }
        }
        for (int n = 0; n < 128; ++n) {
            string = String.valueOf(string) + String.valueOf((char)array[n]);
        }
        final String nextToken = new StringTokenizer(string.substring(string.indexOf("!") + 1), "@").nextToken();
        if (upperCase.indexOf("FILE:") > -1) {
            b2 = true;
        }
        if (upperCase.indexOf(nextToken) > -1) {
            b2 = true;
        }
        if (b && b2) {
            this.pf = true;
        }
    }
    
    public CodeBrainQuasar() {
        this.pf = false;
        this.PI2 = 6.2831855f;
        this.FlyHowMany = 1;
        this.minDevV = 50;
        this.minDevH = 50;
        this.maxDevV = 50;
        this.maxDevH = 50;
        this.dir = -1;
        this.AmpOsc = false;
        this.F_RL = 1;
    }
}
