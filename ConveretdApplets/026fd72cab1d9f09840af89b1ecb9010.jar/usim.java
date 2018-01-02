import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class usim extends Applet implements Runnable
{
    int ApplWidth;
    int ApplHeight;
    int hleiste;
    int hoehe;
    int breite;
    int BioMass;
    int AnzIndiv;
    int MaxIndiv;
    int MaxFutter;
    int AnzFutter;
    int FaktorBioMassFutter;
    int SumSegmente;
    Image offScreenImage;
    Graphics offScreenGraphic;
    Color bgcol;
    Color[] farbe;
    int zaehler;
    Thread mth;
    Individ[] indi;
    int[] xFutter;
    int[] yFutter;
    Random rand;
    int i;
    int j;
    Button butt1;
    Button butt2;
    
    public String getAppletInfo() {
        return "Name: USIM\r\nAuthor: Dietmar Jarosch";
    }
    
    public void init() {
        this.resize(this.ApplWidth, this.ApplHeight);
        this.setBackground(this.bgcol);
        this.offScreenImage = this.createImage(this.breite, this.hoehe);
        this.offScreenGraphic = this.offScreenImage.getGraphics();
        this.farbe[0] = new Color(0, 0, 0);
        this.farbe[1] = new Color(255, 192, 0);
        this.farbe[2] = new Color(192, 0, 0);
        this.farbe[3] = new Color(0, 128, 20);
        this.butt1 = new Button("  Start  ");
        this.butt2 = new Button("Reset All");
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(this.hoehe, 0, 0, 0);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(this.butt1, gridBagConstraints);
        this.add(this.butt1);
        layout.setConstraints(this.butt2, gridBagConstraints);
        this.add(this.butt2);
        this.add(this.butt1);
        this.add(this.butt2);
        this.init_sim();
    }
    
    void init_sim() {
        this.BioMass = 1500;
        this.AnzIndiv = 15;
        this.MaxIndiv = 50;
        this.MaxFutter = 100;
        this.AnzFutter = 0;
        this.offScreenGraphic.setColor(this.farbe[0]);
        this.offScreenGraphic.drawRect(0, 0, this.breite - 1, this.hoehe - 1);
        this.offScreenGraphic.setColor(this.bgcol);
        this.offScreenGraphic.fillRect(1, 1, this.breite - 2, this.hoehe - 2);
        this.repaint();
        this.SumSegmente = 0;
        this.i = 0;
        while (this.i < this.AnzIndiv) {
            (this.indi[this.i] = new Individ()).newCode();
            if (this.i > 0) {
                while (this.indi[this.i].GenCode.compareTo(this.indi[this.i - 1].GenCode) == 0) {
                    this.indi[this.i].newCode();
                }
            }
            this.indi[this.i].init(this.breite, this.hoehe);
            this.SumSegmente += this.indi[this.i].SumLaenge;
            System.out.println(this.indi[this.i].GenCode);
            ++this.i;
        }
        this.AnzFutter = (this.BioMass - this.SumSegmente) / this.FaktorBioMassFutter;
        this.i = 0;
        while (this.i < this.MaxFutter) {
            this.xFutter[this.i] = Math.abs(this.rand.nextInt() % this.breite);
            this.yFutter[this.i] = Math.abs(this.rand.nextInt() % this.hoehe);
            ++this.i;
        }
    }
    
    public void run() {
        while (true) {
            try {
                while (true) {
                    Thread.sleep(40L);
                    this.doOneStep();
                }
            }
            catch (InterruptedException ex) {
                this.mth.stop();
                continue;
            }
            break;
        }
    }
    
    void doOneStep() {
        this.offScreenGraphic.setColor(this.bgcol);
        this.offScreenGraphic.fillRect(1, 1, this.breite - 2, this.hoehe - 2);
        this.offScreenGraphic.setColor(this.farbe[0]);
        this.offScreenGraphic.drawRect(0, 0, this.breite - 1, this.hoehe - 1);
        this.AnzFutter = (this.BioMass - this.SumSegmente) / this.FaktorBioMassFutter;
        this.offScreenGraphic.setColor(this.farbe[3]);
        this.i = 0;
        while (this.i < this.AnzFutter) {
            this.offScreenGraphic.drawRect(this.xFutter[this.i] - 1, this.yFutter[this.i] - 1, 3, 3);
            ++this.i;
        }
        this.offScreenGraphic.setColor(this.farbe[0]);
        this.SumSegmente = 0;
        this.i = 0;
        while (this.i < this.AnzIndiv) {
            this.SumSegmente += this.indi[this.i].SumLaenge;
            if (this.indi[this.i].AnzSegmente == 0) {
                if (this.i < this.AnzIndiv - 1) {
                    this.indi[this.i].GenCode = this.indi[this.AnzIndiv - 1].GenCode;
                    this.indi[this.i].init(this.breite, this.hoehe);
                    this.indi[this.i].xPos = this.indi[this.AnzIndiv - 1].xPos;
                    this.indi[this.i].yPos = this.indi[this.AnzIndiv - 1].yPos;
                    this.indi[this.i].Winkel = this.indi[this.AnzIndiv - 1].Winkel;
                    this.indi[this.i].Alter = this.indi[this.AnzIndiv - 1].Alter;
                    this.indi[this.i].Energie = this.indi[this.AnzIndiv - 1].Energie;
                }
                --this.AnzIndiv;
            }
            this.j = 0;
            while (this.j < this.AnzFutter) {
                if (Math.abs(this.xFutter[this.j] - this.indi[this.i].xPos) < 4 && Math.abs(this.yFutter[this.j] - this.indi[this.i].yPos) < 4) {
                    this.xFutter[this.j] = Math.abs(this.rand.nextInt() % this.breite);
                    this.yFutter[this.j] = Math.abs(this.rand.nextInt() % this.hoehe);
                    final Individ individ = this.indi[this.i];
                    individ.Energie += 1000;
                    this.indi[this.i].Winkel = Math.abs(this.rand.nextInt() % 360);
                }
                ++this.j;
            }
            final Individ individ2 = this.indi[this.i];
            ++individ2.Alter;
            if (this.indi[this.i].Alter <= 0) {
                this.offScreenGraphic.setColor(this.farbe[1]);
                this.offScreenGraphic.fillRect(this.indi[this.i].xPos - 2, this.indi[this.i].yPos - 2, 5, 5);
            }
            else {
                this.offScreenGraphic.setColor(this.farbe[0]);
                this.offScreenGraphic.drawRect(this.indi[this.i].xPos, this.indi[this.i].yPos, 1, 1);
                this.j = 0;
                while (this.j < this.indi[this.i].AnzSegmente) {
                    this.offScreenGraphic.drawLine(this.indi[this.i].xPos + this.indi[this.i].seg[this.j].xPosAnf, this.indi[this.i].yPos + this.indi[this.i].seg[this.j].yPosAnf, this.indi[this.i].xPos + this.indi[this.i].seg[this.j].xPosEnd, this.indi[this.i].yPos + this.indi[this.i].seg[this.j].yPosEnd);
                    ++this.j;
                }
                this.indi[this.i].moveIndiv(this.breite, this.hoehe);
            }
            if (this.indi[this.i].Energie + Math.abs(this.rand.nextInt() % 4000) > 5000 && this.AnzIndiv < this.MaxIndiv && this.indi[this.i].Alter > this.indi[this.i].maxAlter / 5) {
                ++this.AnzIndiv;
                this.indi[this.AnzIndiv - 1] = new Individ();
                this.indi[this.AnzIndiv - 1].GenCode = this.indi[this.i].GenCode;
                this.mutiere(this.indi[this.AnzIndiv - 1]);
                this.indi[this.AnzIndiv - 1].init(this.breite, this.hoehe);
                this.indi[this.AnzIndiv - 1].Alter = -(this.indi[this.AnzIndiv - 1].maxAlter / 5);
                this.indi[this.AnzIndiv - 1].xPos = this.indi[this.i].xPos;
                this.indi[this.AnzIndiv - 1].yPos = this.indi[this.i].yPos;
                this.indi[this.AnzIndiv - 1].Winkel = Math.abs(this.rand.nextInt() % 360);
                final Individ individ3 = this.indi[this.i];
                individ3.Energie -= 1000;
            }
            ++this.i;
        }
        this.repaint();
    }
    
    void mutiere(final Individ individ) {
        switch (Math.abs(this.rand.nextInt() % 10)) {
            case 0: {
                final int abs = Math.abs(this.rand.nextInt() % individ.GenCode.length());
                individ.GenCode = individ.GenCode.substring(0, abs) + Math.abs(this.rand.nextInt() % 10) + individ.GenCode.substring(abs + 1);
            }
            case 1: {
                individ.GenCode = individ.GenCode.substring(0, individ.GenCode.length() - 5);
            }
            case 2: {
                for (int i = 0; i < 5; ++i) {
                    individ.GenCode += Math.abs(this.rand.nextInt() % 10);
                }
            }
            default: {}
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target.equals(this.butt1)) {
            if (this.mth == null) {
                (this.mth = new Thread(this)).start();
                this.butt1.setLabel("  Stop  ");
            }
            else {
                this.mth.stop();
                this.mth = null;
                this.butt1.setLabel("  Start  ");
            }
            return true;
        }
        if (event.target.equals(this.butt2)) {
            if (this.mth != null) {
                this.mth.stop();
                this.mth = null;
                this.butt1.setLabel("  Start  ");
            }
            this.init_sim();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.offScreenImage, 0, 0, this);
    }
    
    public void pause(final long n) {
        for (long n2 = 0L; n2 < n * 1000L; ++n2) {}
    }
    
    public usim() {
        this.ApplWidth = 600;
        this.ApplHeight = 400;
        this.hleiste = 20;
        this.hoehe = this.ApplHeight - this.hleiste;
        this.breite = this.ApplWidth;
        this.BioMass = 1500;
        this.AnzIndiv = 15;
        this.MaxIndiv = 50;
        this.MaxFutter = 100;
        this.FaktorBioMassFutter = 20;
        this.bgcol = new Color(200, 210, 215);
        this.farbe = new Color[4];
        this.indi = new Individ[this.MaxIndiv];
        this.xFutter = new int[this.MaxFutter];
        this.yFutter = new int[this.MaxFutter];
        this.rand = new Random();
    }
}
