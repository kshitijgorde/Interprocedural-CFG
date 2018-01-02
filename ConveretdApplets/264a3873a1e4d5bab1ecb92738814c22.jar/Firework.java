import java.awt.Color;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Firework extends Applet implements Runnable
{
    public int AnimationSpeed;
    public int RocketStyleVariability;
    public int MaxRocketNumber;
    public int MaxRocketExplosionEnergy;
    public int MaxRocketPatchNumber;
    public int MaxRocketPatchLength;
    public int Gravity;
    public String RocketSoundtrack;
    private int mx;
    private int my;
    private Thread thread;
    private Rocket[] rocket;
    
    public void init() {
        final String parameter = this.getParameter("AnimationSpeed");
        this.AnimationSpeed = ((parameter == null) ? 100 : Integer.valueOf(parameter));
        this.RocketSoundtrack = this.getParameter("RocketSoundtrack");
        final String parameter2 = this.getParameter("RocketStyleVariability");
        this.RocketStyleVariability = ((parameter2 == null) ? 20 : Integer.valueOf(parameter2));
        final String parameter3 = this.getParameter("MaxRocketNumber");
        this.MaxRocketNumber = ((parameter3 == null) ? 5 : Integer.valueOf(parameter3));
        final String parameter4 = this.getParameter("MaxRocketExplosionEnergy");
        this.MaxRocketExplosionEnergy = ((parameter4 == null) ? 500 : Integer.valueOf(parameter4));
        final String parameter5 = this.getParameter("MaxRocketPatchNumber");
        this.MaxRocketPatchNumber = ((parameter5 == null) ? 50 : Integer.valueOf(parameter5));
        final String parameter6 = this.getParameter("MaxRocketPatchLength");
        this.MaxRocketPatchLength = ((parameter6 == null) ? 100 : Integer.valueOf(parameter6));
        final String parameter7 = this.getParameter("Gravity");
        this.Gravity = ((parameter7 == null) ? 20 : Integer.valueOf(parameter7));
        this.mx = this.size().width - 1;
        this.my = this.size().height - 1;
        this.rocket = new Rocket[this.MaxRocketNumber];
        for (int i = 0; i < this.MaxRocketNumber; ++i) {
            this.rocket[i] = new Rocket(this.mx, this.my, this.Gravity);
        }
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void run() {
        int n = (int)(Math.random() * this.MaxRocketExplosionEnergy * 3.0 / 4.0) + this.MaxRocketExplosionEnergy / 4 + 1;
        int n2 = (int)(Math.random() * this.MaxRocketPatchNumber * 3.0 / 4.0) + this.MaxRocketPatchNumber / 4 + 1;
        int n3 = (int)(Math.random() * this.MaxRocketPatchLength * 3.0 / 4.0) + this.MaxRocketPatchLength / 4 + 1;
        long n4 = (long)(Math.random() * 10000.0);
        final Graphics graphics = this.getGraphics();
        URL url = null;
        while (true) {
            try {
                Thread.sleep(100 / this.AnimationSpeed);
            }
            catch (InterruptedException ex) {}
            boolean b = true;
            for (int i = 0; i < this.MaxRocketNumber; ++i) {
                b = (b && this.rocket[i].sleep);
            }
            if (b && Math.random() * 100.0 < this.RocketStyleVariability) {
                n = (int)(Math.random() * this.MaxRocketExplosionEnergy * 3.0 / 4.0) + this.MaxRocketExplosionEnergy / 4 + 1;
                n2 = (int)(Math.random() * this.MaxRocketPatchNumber * 3.0 / 4.0) + this.MaxRocketPatchNumber / 4 + 1;
                n3 = (int)(Math.random() * this.MaxRocketPatchLength * 3.0 / 4.0) + this.MaxRocketPatchLength / 4 + 1;
                n4 = (long)(Math.random() * 10000.0);
            }
            for (int j = 0; j < this.MaxRocketNumber; ++j) {
                if (this.rocket[j].sleep && Math.random() * this.MaxRocketNumber * n3 < 1.0) {
                    try {
                        url = new URL(this.getDocumentBase(), this.RocketSoundtrack);
                    }
                    catch (MalformedURLException ex2) {}
                    this.play(url);
                    this.rocket[j].init(n, n2, n3, n4);
                    this.rocket[j].start();
                }
                this.rocket[j].show(graphics);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.mx + 1, this.my + 1);
    }
}
