import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Toolkit;
import java.awt.PopupMenu;
import java.awt.Component;
import java.awt.Graphics;
import java.util.StringTokenizer;
import au.com.rocketdog.project.awc.applet.images.ImageRes;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class bh extends av
{
    private Image a;
    private int b;
    private String c;
    private boolean d;
    private final Dimension e;
    private Image f;
    private String g;
    private String h;
    private cd i;
    
    public String a() {
        return this.g;
    }
    
    public String b() {
        return this.h;
    }
    
    public void a(final Image f) {
        this.e.height = 140;
        if (f.getWidth(null) == 160 && f.getHeight(null) == 120) {
            this.f = f;
        }
        else {
            this.f = f.getScaledInstance(160, 120, 2);
        }
    }
    
    public void c() {
        this.f = null;
        this.e.height = 18;
    }
    
    public void a(final boolean d) {
        this.d = d;
    }
    
    public boolean d() {
        return this.d;
    }
    
    public boolean equals(final Object o) {
        return o.toString().equals(this.toString());
    }
    
    public int e() {
        return this.b;
    }
    
    public void a(final int b) {
        this.b = b;
        switch (this.e()) {
            case 0: {
                this.a = ImageRes.k;
                break;
            }
            case 1: {
                this.a = ImageRes.k;
                break;
            }
            case 2: {
                this.a = ImageRes.m;
                break;
            }
            case 4: {
                this.a = ImageRes.k;
                break;
            }
            case 3: {
                this.a = ImageRes.m;
                break;
            }
        }
    }
    
    public bh(final String c) {
        this.d = false;
        this.e = new Dimension(178, 18);
        final StringTokenizer stringTokenizer = new StringTokenizer(c, ",");
        if (stringTokenizer.hasMoreTokens()) {
            this.a(stringTokenizer.nextToken());
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.a(Integer.parseInt(stringTokenizer.nextToken()));
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.h = stringTokenizer.nextToken();
        }
        if (stringTokenizer.hasMoreTokens()) {
            this.g = stringTokenizer.nextToken();
        }
        this.c = c;
        this.b(this.r().toLowerCase());
        this.b(dj.b);
    }
    
    public String toString() {
        return this.c;
    }
    
    public void b(final Graphics graphics, final Component component, final int n, final int n2) {
        graphics.fillRect(n, n2, this.g().width, this.g().height);
        if (this.d()) {
            graphics.setFont(dj.ak);
        }
        else {
            graphics.setFont(dj.aj);
        }
        int n3 = 0;
        if (this.f != null) {
            n3 = 122;
            graphics.drawImage(this.f, n + 9, n2 + 1, component);
        }
        graphics.drawImage(this.a, n + 2, n2 + 2 + n3, component);
        graphics.setColor(dj.x);
        if (this.r().length() > 34) {
            graphics.drawString(this.r().substring(0, 31) + "...", n + 20, n2 + 14 + n3);
        }
        else {
            graphics.drawString(this.r(), n + 20, n2 + 14 + n3);
        }
    }
    
    public PopupMenu a(final int n, final int n2, final Component component) {
        return null;
    }
    
    public void c(final int n, final int n2) {
        if (this.d()) {
            this.i.show();
        }
        else {
            (this.i = new cd(this)).show();
        }
    }
    
    public void f() {
        this.e.height = 140;
        try {
            this.f = Toolkit.getDefaultToolkit().getImage(new URL(this.h));
            this.f = this.f.getScaledInstance(160, 120, 2);
            final MediaTracker mediaTracker = new MediaTracker(h.f());
            mediaTracker.addImage(this.f, 0);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            this.f = ImageRes.ak;
            ex.printStackTrace();
        }
    }
    
    public Dimension g() {
        return this.e;
    }
    
    public String b(final int n, final int n2, final Component component) {
        return "";
    }
}
