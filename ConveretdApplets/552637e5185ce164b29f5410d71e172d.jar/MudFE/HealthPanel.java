// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.util.StringTokenizer;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Image;

public class HealthPanel extends WPPanel implements Runnable, DisplayInterface
{
    MudFrame theframe;
    Mudgauge b1;
    Mudgauge b2;
    private double oldsta;
    private double oldmag;
    private StringBuffer stored;
    Thread exec;
    private static final boolean debug = false;
    int curstam;
    int maxstam;
    static int hpthread_no;
    
    public void setstam(final int s) {
        if (s <= this.maxstam && this.curstam != s) {
            this.curstam = s;
            final int per = this.curstam * 100 / this.maxstam;
            this.b1.setFillPercent(per);
            this.b1.repaint();
        }
    }
    
    public HealthPanel(final MudFrame fr, final Image im, final LayoutManager la) {
        this.oldsta = 0.0;
        this.oldmag = 0.0;
        this.stored = new StringBuffer(120);
        this.curstam = 0;
        this.maxstam = 0;
        this.theframe = fr;
        this.b1 = new Mudgauge();
        this.b2 = new Mudgauge();
        this.setLayout(la);
        this.setPaper(im);
        this.add("B1", this.b1);
        this.add("B2", this.b2);
    }
    
    public void setmaxstam(final int s) {
        if (s >= this.curstam && this.maxstam != s) {
            this.maxstam = s;
            final int per = this.curstam * 100 / this.maxstam;
            this.b1.setFillPercent(per);
            this.b1.repaint();
        }
    }
    
    public void update() {
        try {
            String s = this.stored.toString();
            this.stored.setLength(0);
            final char[] sb = new char[s.length()];
            for (int i = 0; i < s.length(); ++i) {
                sb[i] = ((s.charAt(i) > '\u009b') ? ' ' : s.charAt(i));
            }
            s = new String(sb);
            final StringTokenizer tok = new StringTokenizer(s);
            if (tok.countTokens() != 14) {
                System.err.println("String tokeniser returned " + tok.countTokens() + " with the following string " + s);
                return;
            }
            String t = tok.nextToken();
            final int staminaval = Integer.parseInt(t);
            this.curstam = staminaval;
            int maxstamina = Integer.parseInt(tok.nextToken());
            if (maxstamina > this.curstam) {
                this.maxstam = maxstamina;
            }
            else {
                this.maxstam = this.curstam;
            }
            if (maxstamina < staminaval) {
                maxstamina = staminaval;
            }
            if (maxstamina == 0) {
                maxstamina = 1;
            }
            int per = staminaval * 100 / maxstamina;
            this.b1.setFillPercent(per);
            this.b1.repaint();
            tok.nextToken();
            tok.nextToken();
            tok.nextToken();
            tok.nextToken();
            t = tok.nextToken();
            final int magicval = Integer.parseInt(t);
            int maxmagic = Integer.parseInt(tok.nextToken());
            if (maxmagic < magicval) {
                maxmagic = magicval;
            }
            if (maxmagic == 0) {
                maxmagic = 1;
            }
            per = magicval * 100 / maxmagic;
            this.b2.setFillPercent(per);
            this.b2.repaint();
            tok.nextToken();
            if (tok.nextToken().equals("Y")) {}
            if (tok.nextToken().equals("Y")) {}
            if (tok.nextToken().equals("Y")) {}
            if (tok.nextToken().equals("Y")) {}
            tok.nextToken();
        }
        catch (Exception e) {
            System.err.println("Exception in FES Update - This has now been dealt with.");
        }
    }
    
    static {
        HealthPanel.hpthread_no = 0;
    }
    
    public void addChar(final char b, final Attribute a) {
        this.stored.append(b);
    }
    
    public void addChar(final char b) {
        this.stored.append(b);
    }
    
    public void cleol() {
    }
    
    public void run() {
        final String command = '\u001b' + "-[fes" + '\u001b' + "-]";
        try {
            while (true) {
                this.theframe.mudbox.sendString(command);
                if (this.exec != null) {
                    Thread.sleep(10000L);
                }
            }
        }
        catch (Exception e) {
            System.err.println("Exception in fesrun");
        }
    }
    
    public void addString(final String b, final Attribute a) {
        this.addString(b);
    }
    
    public void addString(final String b) {
        for (int i = 0; i < b.length(); ++i) {
            this.addChar(b.charAt(i));
        }
    }
    
    public void cls() {
    }
}
