import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Font;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Five0 extends Applet
{
    static final int Dwc = 0;
    static final int Ewc = 1;
    static final int Fwc = 2;
    Image Gwc;
    Graphics Hwc;
    Dimension size;
    FontMetrics Iwc;
    Color Jwc;
    Color Kwc;
    boolean Lwc;
    boolean Mwc;
    int Nwc;
    Image Owc;
    Image[] S;
    AudioClip Pwc;
    AudioClip Qwc;
    AudioClip Rwc;
    AudioClip Swc;
    public[] Twc;
    return Uwc;
    static Vwc;
    super Qa;
    switch Wwc;
    private static String ya = "\u0668\u0660\u0676\u0676\u0664\u0662\u0660\u0663\u066c\u0669\u0660";
    private static String za = "\u0668\u0660\u0676\u0676\u0664\u0662\u0660\u0676\u065a\u0660\u066b\u062b\u0661\u0664\u0671";
    private static String Aa = "\u0677\u066a\u0669\u0669\u0661\u066c\u0666\u0660\u062b\u0664\u0670";
    private static String Ba = "\u0663\u0664\u066c\u0669\u0676\u0666\u066a\u0677\u0660\u062b\u0664\u0670";
    private static String Ca = "\u0676\u0666\u066a\u0677\u0660\u062b\u0664\u0670";
    private static String Da = "\u0676\u066c\u0669\u0660\u066b\u0671\u062b\u0664\u0670";
    private static String Ea = "\u0667\u0664\u0666\u066e\u0646\u066a\u0669\u066a\u0677";
    private static String Ta = "\u0669\u0663\u066b\u0671\u0646\u066a\u0669\u066a\u0677";
    private static String Ua = "\u0644\u0677\u066c\u0664\u0669";
    private static String Va = "\u0676\u0671\u0664\u0677\u0671\u0670\u0675\u065a\u0668\u0676\u0662";
    private static String Wa = "\u0663\u066a\u066b\u0671\u0646\u066a\u0669\u066a\u0677";
    private static String Xa = "\u0663\u066c\u0673\u0660\u0635\u065a\u0667\u0662\u062b\u0662\u066c\u0663";
    private static String Ya = "\u0661\u066c\u0666\u0660\u0676\u0671\u0677\u066c\u0675\u062b\u0662\u066c\u0663";
    private static String Za = "\u0662\u0664\u0668\u066a\u0673\u0660\u0677\u065a\u0668\u0676\u0662";
    private static String _b = "\u066b\u0660\u0672\u0677\u066a\u0669\u0669\u065a\u0668\u0676\u0662";
    private static String ab = "\u0676\u0660\u0669\u0676\u0666\u066a\u0677\u065a\u0668\u0676\u0662";
    private static String bb = "\u0666\u066d\u066a\u066c\u0666\u0660\u0676\u065a\u0668\u0676\u0662";
    
    public void init() {
        String s = this.getParameter(Five0.ya);
        if (s == null) {
            s = Five0.za;
        }
        this.Qa = new super(this.getCodeBase(), s);
        this.Wwc = new switch(this, this.Qa);
        this.Pwc = this.Wwc._(Five0.Aa);
        this.Qwc = this.Wwc._(Five0.Ba);
        this.Rwc = this.Wwc._(Five0.Ca);
        if (this.Wwc._()) {
            (this.Swc = this.Wwc._(Five0.Da)).loop();
        }
        this.size = this.getSize();
        this.Jwc = this.Wwc.b(Five0.Ea, Color.white);
        this.Kwc = this.Wwc.b(Five0.Ta, Color.black);
        this.setBackground(this.Jwc);
        this.setFont(new Font(Five0.Ua, 1, 12));
        this.Iwc = this.getFontMetrics(this.getFont());
    }
    
    public void start() {
        synchronized.reset();
    }
    
    private boolean i() {
        if (this.Wwc.b() && !this.Wwc.a(switch.Na)) {
            this.Wwc.a(true);
            return false;
        }
        if (!this.j()) {
            return false;
        }
        this.Gwc = this.createImage(this.size.width, this.size.height);
        this.Hwc = this.Gwc.getGraphics();
        this.Iwc = this.getFontMetrics(this.Hwc.getFont());
        this.Twc = new public[5];
        final Rectangle rectangle = new Rectangle(26, 344, 39, 39);
        for (int i = 0; i < this.Twc.length; ++i) {
            this.Twc[i] = new public(i, this.S, rectangle);
            final Rectangle rectangle2 = rectangle;
            rectangle2.x += 47;
        }
        this.Uwc = new return(this.Qa);
        synchronized.oa = new Rectangle(270, 344, 50, 39);
        (this.Vwc = new static(new Rectangle(10, 95, 320, 35))).a(this.Qa.m(Five0.Va));
        this.Kwc = this.Wwc.b(Five0.Wa, Color.black);
        this.setBackground(this.Jwc);
        this.enableEvents(48L);
        return !this.Wwc.g();
    }
    
    private boolean j() {
        final Vector<Image> vector = new Vector<Image>();
        vector.addElement(this.Owc = this.getImage(this.getCodeBase(), Five0.Xa));
        final Image image = this.getImage(this.getCodeBase(), Five0.Ya);
        vector.addElement(image);
        this.Wwc.b(vector, 0);
        if (!this.Wwc.g()) {
            final ImageProducer source = image.getSource();
            this.S = new Image[6];
            final MediaTracker mediaTracker = new MediaTracker(this);
            for (int i = 0; i < this.S.length; ++i) {
                mediaTracker.addImage(this.S[i] = this.createImage(new FilteredImageSource(source, new CropImageFilter(i * 39, 0, 39, 39))), 1);
            }
            try {
                mediaTracker.waitForID(1);
            }
            catch (InterruptedException ex) {}
            return true;
        }
        return false;
    }
    
    private void a(final Graphics graphics) {
        this.Wwc._(graphics);
        final String a = this.Qa.a();
        graphics.setColor(this.Kwc);
        graphics.drawString(a, this.Wwc.b(a, true, graphics), this.Wwc.b(a, false, graphics));
        if (this.i()) {
            this.Lwc = true;
            this.Wwc._(graphics);
            this.repaint();
            return;
        }
        this.Wwc._(graphics);
        String s;
        if (this.Wwc.a()) {
            s = switch.La;
        }
        else {
            s = this.Qa.i();
        }
        graphics.setColor(this.Kwc);
        graphics.drawString(s, this.Wwc.b(s, true, graphics), this.Wwc.b(s, false, graphics));
    }
    
    public void update(final Graphics graphics) {
        this.Hwc.drawImage(this.Owc, 0, 0, this);
        if (this.Nwc != 0) {
            for (int i = 0; i < this.Twc.length; ++i) {
                if (this.Nwc == 1) {
                    if (synchronized.Ia[i]) {
                        this.Twc[i].b(this.Hwc);
                    }
                }
                else {
                    this.Twc[i].b(this.Hwc);
                }
            }
        }
        this.Uwc.b(this.Hwc);
        synchronized.b(this.Hwc);
        this.Vwc.b(this.Hwc);
        graphics.drawImage(this.Gwc, 0, 0, this);
        if (this.Wwc.g()) {
            this.showStatus(this.Qa._());
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.Lwc) {
            this.a(graphics);
            return;
        }
        this.update(graphics);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.b(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            case 502: {
                this._(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            default: {
                mouseEvent.consume();
            }
        }
    }
    
    public boolean b(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.Mwc) {
            return true;
        }
        if (synchronized.contains(n, n2) && synchronized.Ja < 3) {
            this.Mwc = true;
            if (this.Uwc.h()) {
                this.Uwc.reset();
            }
            this.Vwc.a(null);
            this.Nwc = 1;
            this.repaint();
            return true;
        }
        if (this.Uwc.contains(n, n2) && synchronized.Ja > 0) {
            this.Uwc._();
            final int _;
            if ((_ = this.Uwc._(n, n2, false)) != -1) {
                synchronized.reset();
                if (_ == 0) {
                    switch.a(this.Qwc);
                }
                else {
                    switch.a(this.Rwc);
                }
                if (this.Uwc.h()) {
                    this.Vwc.a(this.Qa.m(Five0.Za));
                }
                else {
                    this.Vwc.a(this.Qa.m(Five0._b));
                }
                this.Nwc = 0;
                this.repaint();
            }
            return true;
        }
        if (synchronized.Ja > 0 && synchronized.Ja < 3) {
            for (int i = 0; i < this.Twc.length; ++i) {
                if (this.Twc[i].contains(n, n2)) {
                    synchronized.b(i);
                    this.repaint();
                    break;
                }
            }
        }
        return true;
    }
    
    public boolean _(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.Mwc) {
            this.Mwc = false;
            switch.a(this.Pwc);
            try {
                Thread.sleep(2000L);
            }
            catch (InterruptedException ex) {}
            synchronized.a();
            if (synchronized.Ja == 3) {
                synchronized.b();
                this.Vwc.a(this.Qa.m(Five0.ab));
            }
            else {
                this.Vwc.a(this.Qa.m(Five0.bb));
            }
            this.Nwc = 2;
            this.repaint();
            return true;
        }
        return true;
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 503: {
                this.a(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            default: {
                mouseEvent.consume();
            }
        }
    }
    
    public boolean a(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.Mwc) {
            return true;
        }
        if (this.Uwc.contains(n, n2) && synchronized.Ja > 0) {
            this.Uwc._();
            this.Uwc._(n, n2, true);
            this.repaint();
            return true;
        }
        if (n2 < 305) {
            this.Uwc._();
            this.repaint();
            return true;
        }
        return true;
    }
    
    public void destroy() {
        switch.b((Object)this.Pwc);
        switch.b((Object)this.Qwc);
        switch.b((Object)this.Rwc);
        switch.b((Object)this.Swc);
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x10605);
        }
        return new String(array);
    }
    
    static {
        Five0.ya = _(Five0.ya);
        Five0.za = _(Five0.za);
        Five0.Aa = _(Five0.Aa);
        Five0.Ba = _(Five0.Ba);
        Five0.Ca = _(Five0.Ca);
        Five0.Da = _(Five0.Da);
        Five0.Ea = _(Five0.Ea);
        Five0.Ta = _(Five0.Ta);
        Five0.Ua = _(Five0.Ua);
        Five0.Va = _(Five0.Va);
        Five0.Wa = _(Five0.Wa);
        Five0.Xa = _(Five0.Xa);
        Five0.Ya = _(Five0.Ya);
        Five0.Za = _(Five0.Za);
        Five0._b = _(Five0._b);
        Five0.ab = _(Five0.ab);
        Five0.bb = _(Five0.bb);
    }
}
