import java.net.MalformedURLException;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Image;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CodeBrainVoice extends Applet implements Runnable
{
    Thread T;
    boolean pf;
    Graphics g;
    AudioClip A;
    String F;
    int R;
    int J;
    int Je;
    int Jloop;
    int Me;
    boolean GoodP;
    boolean GoodS;
    boolean GoodL;
    int mpX;
    int mpY;
    int mpW;
    int mpH;
    int mlX;
    int mlY;
    int mlW;
    int mlH;
    int msX;
    int msY;
    int msW;
    int msH;
    Color bg;
    Color fg;
    Image i_BG;
    
    public void init() {
        this.doK();
        final String parameter = this.getParameter("BackgroundColor");
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",");
            this.bg = new Color(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }
        this.setBackground(this.bg);
        final String parameter2 = this.getParameter("BoxColor");
        if (parameter2 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter2, ",");
            this.fg = new Color(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()));
        }
        final String parameter3 = this.getParameter("BackgroundImage");
        if (parameter3 != null) {
            this.i_BG = this.getImage(this.getCodeBase(), parameter3);
        }
        final String parameter4 = this.getParameter("Sound");
        if (parameter4 != null) {
            this.F = parameter4;
        }
        this.A = this.getAudioClip(this.getCodeBase(), this.F);
        final String parameter5 = this.getParameter("Repeat");
        if (parameter5 != null && parameter5.equalsIgnoreCase("YES")) {
            this.R = 99;
        }
        final String parameter6 = this.getParameter("JavaScriptMode");
        if (parameter6 != null && parameter6.equalsIgnoreCase("YES")) {
            this.J = 99;
        }
        final String parameter7 = this.getParameter("MouseMode");
        if (parameter7 != null && parameter7.equalsIgnoreCase("YES")) {
            this.Me = 99;
        }
        final String parameter8 = this.getParameter("PlayXYWH");
        if (parameter8 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter8, ",");
            this.mpX = Integer.parseInt(stringTokenizer3.nextToken());
            this.mpY = Integer.parseInt(stringTokenizer3.nextToken());
            this.mpW = Integer.parseInt(stringTokenizer3.nextToken());
            this.mpH = Integer.parseInt(stringTokenizer3.nextToken());
        }
        final String parameter9 = this.getParameter("LoopXYWH");
        if (parameter9 != null) {
            final StringTokenizer stringTokenizer4 = new StringTokenizer(parameter9, ",");
            this.mlX = Integer.parseInt(stringTokenizer4.nextToken());
            this.mlY = Integer.parseInt(stringTokenizer4.nextToken());
            this.mlW = Integer.parseInt(stringTokenizer4.nextToken());
            this.mlH = Integer.parseInt(stringTokenizer4.nextToken());
        }
        final String parameter10 = this.getParameter("StopXYWH");
        if (parameter10 != null) {
            final StringTokenizer stringTokenizer5 = new StringTokenizer(parameter10, ",");
            this.msX = Integer.parseInt(stringTokenizer5.nextToken());
            this.msY = Integer.parseInt(stringTokenizer5.nextToken());
            this.msW = Integer.parseInt(stringTokenizer5.nextToken());
            this.msH = Integer.parseInt(stringTokenizer5.nextToken());
        }
    }
    
    public void start() {
        if (this.A != null) {
            this.A.stop();
        }
        if (this.T == null) {
            this.T = new Thread(this);
        }
        this.T.start();
    }
    
    public void run() {
        if (this.A == null) {
            this.A = this.getAudioClip(this.getCodeBase(), this.F);
        }
        if (this.A != null && this.pf && this.J == 0) {
            if (this.R == 0) {
                this.A.play();
            }
            if (this.R == 99) {
                this.A.loop();
            }
        }
        if (this.A != null && this.pf && this.J == 99 && this.Je == 99 && this.Jloop == 0) {
            this.A.play();
        }
        if (this.A != null && this.pf && this.J == 99 && this.Je == 99 && this.Jloop == 99) {
            this.A.loop();
            this.Jloop = 0;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.pf) {
            if (this.i_BG != null) {
                graphics.drawImage(this.i_BG, 0, 0, this);
            }
            if (this.GoodP) {
                graphics.setColor(this.fg);
                graphics.drawRect(this.mpX, this.mpY, this.mpW, this.mpH);
            }
            if (this.GoodL) {
                graphics.setColor(this.fg);
                graphics.drawRect(this.mlX, this.mlY, this.mlW, this.mlH);
            }
            if (this.GoodS) {
                graphics.setColor(this.fg);
                graphics.drawRect(this.msX, this.msY, this.msW, this.msH);
            }
        }
        else {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.size().width, this.size().height);
            graphics.setColor(Color.red);
            graphics.drawString("Notice or key absent!", 20, 20);
        }
    }
    
    public void stop() {
        if (this.T != null) {
            this.Je = 0;
            this.A.stop();
            this.T.stop();
            this.T = null;
        }
    }
    
    public void cbvStop() {
        this.Je = 0;
        this.GoodS = true;
        if (this.A != null) {
            this.A.stop();
        }
        this.repaint();
        this.doWait();
        this.repaint();
        this.stop();
    }
    
    public void cbvPlay() {
        this.Je = 99;
        if (this.J == 99 && this.pf) {
            if (this.T != null) {
                this.A.stop();
                this.T.stop();
                this.T = null;
            }
            this.GoodP = true;
            this.repaint();
            this.doWait();
            this.repaint();
            this.start();
        }
    }
    
    public void cbvLoop() {
        this.Je = 99;
        this.Jloop = 99;
        if (this.J == 99 && this.pf) {
            if (this.T != null) {
                this.A.stop();
                this.T.stop();
                this.T = null;
            }
            this.GoodL = true;
            this.repaint();
            this.doWait();
            this.repaint();
            this.start();
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.Me == 99) {
            if (n > this.mpX && n < this.mpX + this.mpW && n2 > this.mpY && n2 < this.mpY + this.mpH && this.A != null && this.pf) {
                this.A.play();
                this.GoodP = true;
            }
            if (n > this.mlX && n < this.mlX + this.mlW && n2 > this.mlY && n2 < this.mlY + this.mlH && this.A != null && this.pf) {
                this.A.loop();
                this.GoodL = true;
            }
            if (n > this.msX && n < this.msX + this.msW && n2 > this.msY && n2 < this.msY + this.msH) {
                if (this.A != null) {
                    this.A.stop();
                }
                this.GoodS = true;
            }
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.repaint();
        this.doWait();
        this.repaint();
        return true;
    }
    
    private void doWait() {
        try {
            Thread.sleep(400L);
        }
        catch (InterruptedException ex) {}
        this.GoodP = false;
        this.GoodL = false;
        this.GoodS = false;
        this.repaint();
    }
    
    private void doK() {
        this.pf = false;
        final String s = "1234512345321451";
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
            final URL url = new URL(this.getCodeBase(), "CodeBrainVoice.key");
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
    
    public CodeBrainVoice() {
        this.pf = false;
        this.GoodP = false;
        this.GoodS = false;
        this.GoodL = false;
        this.mpX = -10;
        this.mpY = -10;
        this.mpW = -10;
        this.mpH = -10;
        this.mlX = -10;
        this.mlY = -10;
        this.mlW = -10;
        this.mlH = -10;
        this.msX = -10;
        this.msY = -10;
        this.msW = -10;
        this.msH = -10;
        this.bg = Color.black;
        this.fg = Color.red;
    }
}
