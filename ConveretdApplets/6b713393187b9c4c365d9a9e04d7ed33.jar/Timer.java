import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Timer extends Applet implements Runnable
{
    Thread Clock_animation;
    private Image Buffer;
    private Graphics gBuffer;
    long currTime;
    long startTime;
    long diffTime;
    long pauseValue;
    long timeSet;
    int hours;
    int minutes;
    int seconds;
    int runner;
    int soundRunner;
    boolean pressedStart;
    boolean pressedStop;
    boolean pressedReset;
    boolean pressedEnlarge;
    boolean countDown;
    boolean paused;
    boolean enlarged;
    boolean running;
    boolean playSound;
    boolean input;
    boolean blink;
    boolean framed;
    boolean soundDelay;
    int ox;
    int oy;
    double radians;
    double cos;
    double sin;
    AudioClip alarmSound;
    Font font1;
    Font font2;
    Font font3;
    Font font4;
    Font font5;
    Color color1;
    Color color2;
    Color bgColor;
    SpinButton sb1;
    SpinButton sb2;
    SpinButton sb3;
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    String message;
    
    public boolean keyDown(final Event e, final int key) {
        if (key == 10) {
            this.input = false;
        }
        else if (this.input) {
            this.message += (char)key;
        }
        return true;
    }
    
    public void stop() {
        if (this.Clock_animation != null) {
            this.Clock_animation.stop();
            this.Clock_animation = null;
        }
    }
    
    void DrawTextArea() {
        if (this.soundRunner > 0 && this.blink) {
            this.gBuffer.setColor(Color.red);
        }
        else {
            this.gBuffer.setColor(Color.white);
        }
        this.gBuffer.fillRect(20, 200, 150, 20);
        this.gBuffer.setColor(Color.orange);
        if (this.framed) {
            this.gBuffer.drawRect(18, 198, 153, 23);
        }
        if (this.soundRunner > 0 && this.blink) {
            this.gBuffer.setColor(Color.white);
        }
        else {
            this.gBuffer.setColor(Color.black);
        }
        this.gBuffer.setFont(this.font5);
        final FontMetrics fm = this.gBuffer.getFontMetrics(this.font5);
        final int stringWidth = fm.stringWidth(this.message);
        if (this.input && this.blink) {
            this.gBuffer.drawString(this.message + "|", 25, 215);
        }
        else if (this.input && !this.blink) {
            this.gBuffer.drawString(this.message, 25, 215);
        }
        else {
            this.gBuffer.drawString(this.message, 95 - stringWidth / 2, 215);
        }
    }
    
    void DrawBackground() {
        this.gBuffer.setColor(this.bgColor);
        this.gBuffer.fillRect(0, 0, 190, 350);
        this.gBuffer.setColor(this.color1);
        if (this.enlarged) {
            this.gBuffer.fillRoundRect(0, 0, 190, 350, 32, 32);
        }
        else {
            this.gBuffer.fillRoundRect(0, 0, 190, 270, 32, 32);
        }
        this.gBuffer.setColor(Color.lightGray);
        this.gBuffer.fill3DRect(22, 90, 46, 20, !this.pressedStart);
        this.gBuffer.fill3DRect(72, 90, 46, 20, !this.pressedStop);
        this.gBuffer.fill3DRect(122, 90, 46, 20, !this.pressedReset);
        this.gBuffer.setFont(this.font2);
        this.gBuffer.setColor(Color.black);
        this.gBuffer.drawString("START", 24, 104);
        this.gBuffer.drawString("STOP", 78, 104);
        this.gBuffer.drawString("RESET", 125, 104);
        this.gBuffer.setColor(this.color2);
        this.gBuffer.fill3DRect(100, 242, 19, 18, !this.pressedEnlarge);
        this.gBuffer.setColor(Color.black);
        this.DrawTriangle(104, 256, 10, 8);
        this.gBuffer.setFont(this.font2);
        this.gBuffer.setColor(Color.white);
        this.gBuffer.drawString("Set Timer", 125, 255);
        if (this.enlarged) {
            this.gBuffer.drawString("Hours", 30, 295);
            this.gBuffer.drawString("Minutes", 30, 315);
            this.gBuffer.drawString("Seconds", 30, 335);
            this.sb1.DrawControl(this.gBuffer);
            this.sb2.DrawControl(this.gBuffer);
            this.sb3.DrawControl(this.gBuffer);
        }
        else {
            this.gBuffer.setFont(this.font4);
            this.gBuffer.setColor(Color.black);
            this.gBuffer.drawString("© 2000 by J. Wallroth", 2, 322);
            this.gBuffer.setColor(Color.white);
            this.gBuffer.drawString("© 2000 by J. Wallroth", 0, 320);
        }
        this.cb1.DrawControl(this.gBuffer);
        this.cb2.DrawControl(this.gBuffer);
        this.cb3.DrawControl(this.gBuffer);
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.Buffer, 0, 0, this);
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        final boolean b = false;
        this.pressedEnlarge = b;
        this.pressedReset = b;
        this.pressedStop = b;
        this.pressedStart = b;
        this.sb1.mouseUp();
        this.sb2.mouseUp();
        this.sb3.mouseUp();
        this.cb1.mouseUp();
        this.cb2.mouseUp();
        this.cb3.mouseUp();
        this.repaint();
        return true;
    }
    
    void DrawCenteredArc(final int cx, final int cy, final int r, final int beginArc, final int arc) {
        final int x = cx - r;
        final int y = cy - r;
        this.gBuffer.drawArc(x - 87, y - 87, r * 2, r * 2, beginArc, arc);
    }
    
    void CalculateTime() {
        this.currTime = System.currentTimeMillis();
        if (!this.countDown) {
            this.diffTime = (this.currTime - this.startTime) / 1000L + this.pauseValue;
        }
        else {
            this.diffTime = (this.timeSet - (this.currTime - this.startTime)) / 1000L - this.pauseValue;
        }
        this.hours = (int)(this.diffTime - this.diffTime % 3600L) / 3600;
        this.minutes = (int)((this.diffTime - this.diffTime % 60L) / 60L) % 60;
        this.seconds = (int)this.diffTime % 60;
    }
    
    void DrawTriangle(final int x, final int y, final int w, final int h) {
        final int[] a = { x, x + w / 2, x + w };
        final int[] b = { y - h, y, y - h };
        this.gBuffer.fillPolygon(a, b, 3);
    }
    
    void StartTimer() {
        final long h = this.sb1.GetNumber();
        final long m = this.sb2.GetNumber();
        final long s = this.sb3.GetNumber();
        this.timeSet = (h * 3600L + m * 60L + s) * 1000L;
        if ((!this.running || this.paused) && (this.timeSet != 0L || !this.countDown)) {
            this.paused = false;
            this.running = true;
            this.startTime = System.currentTimeMillis();
        }
    }
    
    void StopTimer() {
        if (this.running) {
            if (!this.paused) {
                if (!this.countDown) {
                    this.pauseValue = this.diffTime;
                }
                else {
                    this.pauseValue += (this.currTime - this.startTime) / 1000L;
                }
            }
            this.paused = true;
        }
    }
    
    int rotate_x(final int x, final int y) {
        return (int)(this.ox + x * this.cos - y * this.sin);
    }
    
    void RotatePolygon(final int[] x, final int[] y, final int n) {
        final int[] new_x = new int[3];
        final int[] new_y = new int[3];
        for (int i = 0; i < n; ++i) {
            new_x[i] = this.rotate_x(x[i], y[i]);
            new_y[i] = this.rotate_y(x[i], y[i]);
        }
        this.gBuffer.fillPolygon(new_x, new_y, n);
    }
    
    void DrawClock() {
        if (this.soundRunner > 0 && this.blink) {
            this.gBuffer.setColor(Color.red);
        }
        else {
            this.gBuffer.setColor(Color.white);
        }
        this.gBuffer.fillRoundRect(38, 45, 112, 30, 15, 15);
        this.gBuffer.setFont(this.font3);
        if (this.soundRunner > 0 && this.blink) {
            this.gBuffer.setColor(Color.white);
        }
        else {
            this.gBuffer.setColor(Color.black);
        }
        String s1;
        if (this.hours < 10) {
            s1 = "0";
        }
        else {
            s1 = "";
        }
        String s2;
        if (this.minutes < 10) {
            s2 = "0";
        }
        else {
            s2 = "";
        }
        String s3;
        if (this.seconds < 10) {
            s3 = "0";
        }
        else {
            s3 = "";
        }
        final String output = s1 + this.hours + ":" + s2 + this.minutes + ":" + s3 + this.seconds;
        if (output.length() <= 8) {
            this.gBuffer.drawString(output, 45, 69);
        }
        else {
            this.gBuffer.drawString("00:00:00", 45, 69);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    int rotate_y(final int x, final int y) {
        return (int)(this.oy + y * this.cos + x * this.sin);
    }
    
    public void start() {
        if (this.Clock_animation == null) {
            (this.Clock_animation = new Thread(this)).start();
        }
    }
    
    void DrawCircle() {
        this.gBuffer.setColor(Color.red);
        this.gBuffer.drawOval(7, 12, 174, 174);
        this.gBuffer.setColor(new Color(0, 210, 120));
        final int unit = this.minutes;
        int beginArc;
        int arc;
        if (this.countDown) {
            beginArc = unit * 6 + 90;
            arc = -unit * 6;
            if (this.hours > 0) {
                arc = 360;
            }
            else {
                this.SetAngle(360 - unit * 6);
            }
        }
        else {
            beginArc = 90;
            arc = -unit * 6;
            this.SetAngle(unit * 6);
        }
        int i = 0;
        do {
            this.DrawCenteredArc(182, 186, 86 + i, beginArc, arc);
        } while (++i < 3);
        final int centerX = 94;
        final int centerY = 99;
        final int radius = 87;
        this.ox = centerX;
        this.oy = centerY;
        final int[] x = new int[3];
        final int[] y = new int[3];
        x[0] = -7;
        x[1] = 7;
        x[2] = -7;
        y[0] = -radius - 7;
        y[2] = (y[1] = -radius) + 7;
        this.RotatePolygon(x, y, 3);
    }
    
    void ObserveTimer() {
        if (this.countDown && this.running && this.hours == 0 && this.minutes == 0 && this.seconds == 0) {
            if (this.playSound) {
                this.alarmSound.loop();
            }
            this.soundDelay = true;
            this.running = false;
        }
    }
    
    void ResetTimer() {
        this.running = false;
        final long n = 0L;
        this.diffTime = n;
        this.pauseValue = n;
        this.startTime = System.currentTimeMillis();
        if (this.countDown) {
            final long h = this.sb1.GetNumber();
            final long m = this.sb2.GetNumber();
            final long s = this.sb3.GetNumber();
            this.timeSet = (h * 3600L + m * 60L + s) * 1000L;
            this.hours = (int)h;
            this.minutes = (int)m;
            this.seconds = (int)s;
        }
        else {
            final boolean hours = false;
            this.seconds = (hours ? 1 : 0);
            this.minutes = (hours ? 1 : 0);
            this.hours = (hours ? 1 : 0);
        }
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        this.sb1.mouseDown(x, y);
        this.sb2.mouseDown(x, y);
        this.sb3.mouseDown(x, y);
        this.cb1.mouseDown(x, y);
        this.cb2.mouseDown(x, y);
        this.cb3.mouseDown(x, y);
        if (this.cb1.clicked) {
            this.ResetTimer();
            this.cb1.checked = true;
            this.cb2.checked = false;
            this.countDown = true;
            if (!this.enlarged) {
                this.enlarged = true;
            }
            this.pressedReset = true;
        }
        else if (this.cb2.clicked) {
            this.ResetTimer();
            this.cb2.checked = true;
            this.cb1.checked = false;
            this.countDown = false;
            this.pressedReset = true;
        }
        if (this.cb3.clicked) {
            final CheckBox cb3 = this.cb3;
            cb3.checked ^= true;
            this.playSound ^= true;
        }
        if (x > 22 && x < 68 && y > 90 && y < 110) {
            this.pressedStart = true;
            this.StartTimer();
        }
        if (x > 72 && x < 118 && y > 90 && y < 110) {
            this.pressedStop = true;
            this.StopTimer();
        }
        if (x > 122 && x < 168 && y > 90 && y < 110) {
            this.pressedReset = true;
            this.ResetTimer();
        }
        if (x > 100 && x < 120 && y > 242 && y < 262) {
            this.enlarged ^= true;
            this.pressedEnlarge = true;
        }
        if (x > 20 && x < 170 && y > 200 && y < 220) {
            this.input = true;
            this.message = "";
        }
        else {
            this.input = false;
        }
        if (this.soundDelay) {
            this.alarmSound.stop();
            this.soundRunner = 0;
            this.soundDelay = false;
        }
        this.repaint();
        return true;
    }
    
    void DrawGauge() {
        this.gBuffer.setColor(Color.green);
        this.gBuffer.fillRect(20, 222, 150, 10);
        int barL;
        if (this.diffTime > 0L && this.timeSet > 0L) {
            final float frac = (this.timeSet - this.diffTime * 1000L) / this.timeSet;
            barL = (int)(frac * 150.0f);
        }
        else {
            barL = 0;
        }
        if (this.paused) {
            this.gBuffer.setColor(Color.blue);
        }
        else {
            this.gBuffer.setColor(Color.red);
        }
        if (this.countDown) {
            this.gBuffer.fillRect(20, 222, barL, 10);
        }
        else if (this.running) {
            int i = -5;
            do {
                this.gBuffer.fillRect(20 + this.runner + i, 222, 3, 10);
                i += 6;
            } while (i < 150);
            this.gBuffer.setColor(this.color1);
            this.gBuffer.fillRect(15, 222, 5, 10);
            this.gBuffer.fillRect(170, 222, 5, 10);
        }
    }
    
    public Timer() {
        this.cos = 1.0;
        this.font1 = new Font("Dialog", 0, 14);
        this.font2 = new Font("Dialog", 1, 11);
        this.font3 = new Font("Helvetica", 1, 25);
        this.font4 = new Font("Helvetica", 1, 9);
        this.font5 = new Font("Helvetica", 1, 16);
        this.color1 = new Color(25, 55, 135);
        this.color2 = new Color(225, 225, 225);
        this.bgColor = new Color(0, 0, 0);
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
            ++this.runner;
            if (this.runner > 5) {
                this.runner = 0;
            }
            if (this.soundDelay) {
                ++this.soundRunner;
                if (this.soundRunner > 50) {
                    this.alarmSound.stop();
                    this.soundRunner = 0;
                    this.soundDelay = false;
                }
            }
            if (this.runner < 3) {
                this.blink = true;
            }
            else {
                this.blink = false;
            }
            this.sb1.Update();
            this.sb2.Update();
            this.sb3.Update();
            this.DrawBackground();
            this.DrawTextArea();
            this.DrawGauge();
            this.DrawClock();
            this.DrawCircle();
            if (!this.paused && this.running) {
                this.CalculateTime();
            }
            this.ObserveTimer();
            this.repaint();
        }
    }
    
    public void init() {
        this.Buffer = this.createImage(this.size().width, this.size().height);
        this.gBuffer = this.Buffer.getGraphics();
        try {
            this.alarmSound = this.getAudioClip(this.getCodeBase(), "alarm.au");
        }
        catch (Exception ex) {}
        this.sb1 = new SpinButton(110, 280, 99);
        this.sb2 = new SpinButton(110, 300, 59);
        this.sb3 = new SpinButton(110, 320, 59);
        this.cb1 = new CheckBox(52, 124, "Count Down", false);
        this.cb2 = new CheckBox(52, 144, "Count Up", true);
        this.cb3 = new CheckBox(10, 244, "Play Sound", true);
        this.playSound = true;
        final long currTime = 0L;
        this.timeSet = currTime;
        this.pauseValue = currTime;
        this.diffTime = currTime;
        this.startTime = currTime;
        this.currTime = currTime;
        this.message = this.getParameter("timer_name");
        final String clockColorStr = this.getParameter("clock_color");
        String r = clockColorStr.substring(0, 2);
        String g = clockColorStr.substring(2, 4);
        String b = clockColorStr.substring(4);
        int rt = Integer.parseInt(r, 16);
        int gr = Integer.parseInt(g, 16);
        int bl = Integer.parseInt(b, 16);
        this.color1 = new Color(rt, gr, bl);
        final String bgColorStr = this.getParameter("bg_color");
        r = bgColorStr.substring(0, 2);
        g = bgColorStr.substring(2, 4);
        b = bgColorStr.substring(4);
        rt = Integer.parseInt(r, 16);
        gr = Integer.parseInt(g, 16);
        bl = Integer.parseInt(b, 16);
        this.bgColor = new Color(rt, gr, bl);
    }
    
    void SetAngle(final double a) {
        this.radians = a * 2.0 * 3.141592653589793 / 360.0;
        this.cos = Math.cos(this.radians);
        this.sin = Math.sin(this.radians);
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        if (x > 20 && x < 170 && y > 200 && y < 220) {
            this.framed = true;
        }
        else {
            this.framed = false;
        }
        return true;
    }
}
