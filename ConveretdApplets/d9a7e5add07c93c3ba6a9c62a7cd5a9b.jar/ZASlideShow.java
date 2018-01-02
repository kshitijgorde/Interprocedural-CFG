import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.net.URL;
import java.util.Vector;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZASlideShow extends Applet
{
    Image back;
    Image power;
    Image forward;
    Image pause;
    Image play;
    Image rewind;
    Image about;
    SlideShow ss;
    Vector images;
    URL url;
    int bx;
    int by;
    int pow;
    int pau;
    int noi;
    int sp;
    ImageButton pb;
    ImageButton fb;
    ImageButton pauseb;
    ImageButton playb;
    ImageButton rewindb;
    ImageButton aboutb;
    String speed;
    
    public void init() {
        this.setLayout(null);
        this.back = this.getImage(this.getCodeBase(), "monitor.gif");
        this.power = this.getImage(this.getCodeBase(), "pb.gif");
        this.forward = this.getImage(this.getCodeBase(), "FastForward.gif");
        this.pause = this.getImage(this.getCodeBase(), "Stop.gif");
        this.play = this.getImage(this.getCodeBase(), "Play.gif");
        this.rewind = this.getImage(this.getCodeBase(), "Rewind.gif");
        this.about = this.getImage(this.getCodeBase(), "about.gif");
        this.setBackground(Color.white);
        this.getImages();
        (this.pb = new ImageButton(this.power)).setBackground(new Color(190, 190, 190));
        this.bx = this.size().width / 2;
        this.by = this.size().height - this.size().height / 5 - 7;
        this.pb.setBounds(this.bx + 36, this.by, 16, 16);
        (this.fb = new ImageButton(this.forward)).setBackground(new Color(190, 190, 190));
        this.fb.setBounds(this.bx + 18, this.by, 18, 16);
        (this.pauseb = new ImageButton(this.pause)).setBackground(new Color(190, 190, 190));
        this.pauseb.setBounds(this.bx, this.by, 18, 16);
        (this.playb = new ImageButton(this.play)).setBackground(new Color(190, 190, 190));
        this.playb.setBounds(this.bx - 18, this.by, 18, 16);
        (this.rewindb = new ImageButton(this.rewind)).setBackground(new Color(190, 190, 190));
        this.rewindb.setBounds(this.bx - 36, this.by, 18, 16);
        (this.aboutb = new ImageButton(this.about)).setBackground(new Color(190, 190, 190));
        this.aboutb.setBounds(this.bx - 54, this.by, 18, 16);
        this.add(this.pauseb);
        this.add(this.pb);
        this.add(this.rewindb);
        this.add(this.fb);
        this.add(this.aboutb);
        this.add(this.playb);
        this.add(this.ss);
        this.ss.play();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.back, 0, 0, this.size().width, this.size().height, this);
    }
    
    public boolean action(final Event event, final Object o) {
        if (o == this.power) {
            if (this.pow == 0) {
                this.remove(this.ss);
                this.pow = 1;
            }
            else {
                this.add(this.ss);
                this.pow = 0;
            }
        }
        if (o == this.forward) {
            this.ss.next();
        }
        if (o == this.pause) {
            this.ss.pause(true);
        }
        if (o == this.play) {
            this.ss.pause(false);
        }
        if (o == this.rewind) {
            this.ss.previous();
        }
        if (o == this.about) {
            try {
                this.url = new URL("http://www.zmei-soft.com");
            }
            catch (MalformedURLException ex) {}
            this.getAppletContext().showDocument(this.url, "_blank");
        }
        return true;
    }
    
    public void getImages() {
        this.speed = this.getParameter("speed");
        this.sp = Integer.parseInt(this.speed);
        this.noi = Integer.parseInt(this.getParameter("number_of_images"));
        this.images = new Vector();
        for (int i = 1; i < this.noi + 1; ++i) {
            this.url = this.getClass().getResource(this.getParameter("image" + i));
            this.images.addElement(this.url);
        }
        (this.ss = new SlideShow()).setURLList(this.images);
        this.ss.setBorderVisible(false);
        this.ss.setBounds(this.size().width / 13 - 1, this.size().height / 12, this.size().width - this.size().width / 13 * 2 - 2, this.size().height - this.size().height / 3);
        this.ss.setSwitchSpeed(this.sp);
    }
}
