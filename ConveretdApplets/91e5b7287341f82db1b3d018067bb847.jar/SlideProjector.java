import java.awt.Graphics;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Image;
import java.awt.Label;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SlideProjector extends Applet implements Runnable
{
    public static final int NEXT_IMAGE = 1001;
    public static final int PREV_IMAGE = 1002;
    public static final int RANDOM_TRANSITION = 1003;
    public static final int NORMAL_TRANSITION = 1004;
    ToolBar tb;
    SilverScreen ss;
    Label label;
    int width;
    int height;
    Image[] images;
    Vector imageNames;
    Vector imageDisplayTitle;
    int numImages;
    int curImage;
    String fontName;
    String fontSize;
    int transitionType;
    String copyRight;
    String separator;
    MediaTracker tracker;
    Thread ssThread;
    Font f;
    int delay;
    
    public void init() {
        this.curImage = -1;
        this.imageNames = new Vector();
        this.imageDisplayTitle = new Vector();
        final String parameter = this.getParameter("WIDTH");
        if (parameter != null) {
            this.width = Integer.valueOf(parameter);
        }
        final String parameter2 = this.getParameter("HEIGHT");
        if (parameter2 != null) {
            this.height = Integer.valueOf(parameter2);
        }
        this.copyRight = this.getParameter("URL");
        this.separator = this.getParameter("SEPARATOR");
        if (this.separator == null) {
            this.separator = "|";
        }
        String parameter3 = this.getParameter("TRANSITION");
        if (parameter3 == null) {
            parameter3 = "RANDOM";
        }
        if (parameter3 == "RANDOM") {
            this.transitionType = 1003;
        }
        else {
            this.transitionType = 1004;
        }
        final String parameter4 = this.getParameter("TRANSITIONDELAY");
        if (parameter4 != null) {
            this.delay = Integer.valueOf(parameter4);
        }
        else {
            this.delay = 10000;
        }
        int i;
        for (int n = i = Integer.parseInt(this.getParameter("FONTHEIGHT")); i > 10; --i) {
            this.f = new Font(this.getParameter("font"), 0, i);
            if (this.getFontMetrics(this.f).getHeight() <= n) {
                break;
            }
        }
        try {
            String parameter5;
            for (int n2 = 0; (parameter5 = this.getParameter("SLIDE" + n2)) != null; ++n2) {
                final int index = parameter5.indexOf(this.separator);
                final String substring = parameter5.substring(0, index);
                System.out.println("Image Name = " + substring + " " + n2);
                this.showStatus("Image Name = " + substring + " i");
                this.imageNames.addElement(substring);
                final String substring2 = parameter5.substring(index + 1);
                this.imageDisplayTitle.addElement(substring2);
                this.showStatus("Display Name = " + substring2);
            }
        }
        catch (Exception ex) {
            this.showStatus("SlideProjector: Error in SLIDE PARAMETER");
        }
        this.numImages = this.imageNames.size();
        this.tracker = new MediaTracker(this);
        this.images = new Image[this.numImages];
        String parameter6 = this.getParameter("RIGHTARROWIMAGE");
        if (parameter6 == null) {
            parameter6 = "forward.gif";
        }
        String parameter7 = this.getParameter("LEFTARROWIMAGE");
        if (parameter7 == null) {
            parameter7 = "backward.gif";
        }
        String parameter8 = this.getParameter("TITLEIMAGE");
        if (parameter8 == null) {
            parameter8 = "title.gif";
        }
        this.tb = new ToolBar(this, this.width, parameter6, parameter7, parameter8, 50);
        this.ss = new SilverScreen(this.width, this.height - 90, this);
        (this.label = new Label("Click on right arrow to see next slide", 1)).setFont(this.f);
        final Border border = new Border(this.tb, 2);
        border.setLineColor(Color.black);
        final Border border2 = new Border(this.ss, 2);
        border2.setLineColor(Color.black);
        final Border border3 = new Border(this.label, 2);
        border3.setLineColor(Color.black);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.add("North", border2);
        panel.add("Center", border3);
        panel.add("South", border);
        this.add(panel);
    }
    
    public void start() {
        if (this.ssThread == null) {
            (this.ssThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.ssThread != null && this.ssThread.isAlive()) {
            this.ssThread.stop();
        }
        this.ssThread = null;
    }
    
    public void run() {
        this.ssThread.setPriority(10);
        for (int i = 0; i < this.numImages; ++i) {
            new ImageLoader(this, i, (String)this.imageNames.elementAt(i));
        }
    }
    
    public void showNextImage() {
        ++this.curImage;
        if (this.curImage > this.images.length - 1) {
            this.curImage = 0;
        }
        this.showMessage("Next: " + this.curImage);
        this.ss.displayImage(this.images[this.curImage]);
        this.label.setText(this.imageDisplayTitle.elementAt(this.curImage));
    }
    
    public void showPrevImage() {
        --this.curImage;
        if (this.curImage < 0) {
            this.curImage = this.images.length - 1;
        }
        this.showMessage("Prev: " + this.curImage + "images.length=" + this.images.length);
        if (!this.tracker.checkID(this.curImage, true)) {
            try {
                this.tracker.waitForID(0);
            }
            catch (Exception ex) {
                this.showStatus("Error waiting for Image " + this.curImage);
            }
        }
        this.ss.displayImage(this.images[this.curImage]);
        this.label.setText(this.imageDisplayTitle.elementAt(this.curImage));
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 1001) {
            this.showNextImage();
            return true;
        }
        if (event.id == 1002) {
            this.showPrevImage();
            return true;
        }
        return false;
    }
    
    public void showMessage(final String s) {
        this.showStatus(s);
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public String getAppletInfo() {
        return "SlideProjector Applet by Jayakrishnan Nair jk@csr.UVic.CA http://csr.csc.UVic.CA/~jk";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "WIDTH", "int", "the width of the applet" }, { "HEIGHT", "int", "the height of the applet" }, { "FONT", "String", "a valid Java Font name" }, { "FONTHEIGHT", "int", "size of the font" }, { "LEFTARROWIMAGE", "String", "URL of the left arrow in the toolbutton" }, { "RIGHTARROWIMAGE", "String", "URL of the right arrow in the toolbutton" }, { "TITLEIMAGE", "String", "URL of the title image - one which says SlideProjector" }, { "SEPARATOR", "String", "Separator used the SLIDE parameter" }, { "SLIDE1..N", "String", "URL of image <separator> title of image" } };
    }
    
    int getProjectorWidth() {
        return this.width;
    }
    
    int getProjectorHeight() {
        return this.height;
    }
    
    int getTransitionType() {
        return this.transitionType;
    }
    
    int getDelay() {
        return this.delay;
    }
}
