import java.awt.Component;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;
import java.util.Hashtable;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.MediaTracker;
import java.awt.Font;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.TextField;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class cuppatoc_try extends Applet implements Runnable
{
    TextField textbox;
    TextField textbox2;
    Checkbox translate;
    Button resetbutton;
    Button searchbutton;
    Button openallbutton;
    Panel menupanel;
    static final int MAXLINES = 49;
    cuppatoc_tryRec[] elem;
    int layoutcount;
    int urlcount;
    String tok0;
    String tok1;
    String tok2;
    String tok3;
    int elemcount;
    int argselected;
    int foundstrindex;
    double scale;
    double pixelfactor;
    Thread thread;
    int basex;
    int basey;
    int prev_x;
    int prev_y;
    int mousemoved;
    int mousex;
    int mousey;
    Image offscreen;
    Dimension offscreensize;
    Graphics offgraphics;
    URL[] url;
    int[] foundstrindexarray;
    String baseurl;
    String selhead;
    String curhead;
    int headvisible;
    int lastidx;
    boolean baseurlisOK;
    Font fm;
    Font fm2;
    String msg1;
    String msg2;
    String txtfile;
    String keepbuttons;
    String bgimgfile;
    int fontpointsize;
    int itemheight;
    int txtcolor;
    int highlightcolor;
    int symbolcolor;
    Image bgimg;
    MediaTracker mt;
    int docidx;
    StringTokenizer st;
    StringTokenizer st2;
    boolean oops;
    
    public void init() {
        final String parameter;
        final int n = ((parameter = this.getParameter("BGCOLOR")) != null) ? Integer.parseInt(parameter, 16) : Integer.parseInt("ffffff", 16);
        final String parameter2;
        this.bgimgfile = (((parameter2 = this.getParameter("BGIMAGE")) != null) ? parameter2 : "");
        if (this.bgimgfile.length() == 0) {
            this.setBackground(new Color(n));
        }
        else {
            this.bgimg = this.getImage(this.getCodeBase(), this.bgimgfile);
            this.mt.addImage(this.bgimg, 1);
        }
        final String parameter3;
        this.symbolcolor = (((parameter3 = this.getParameter("SYMBOLCOLOR")) != null) ? Integer.parseInt(parameter3, 16) : Integer.parseInt("000088", 16));
        final String parameter4;
        this.itemheight = (((parameter4 = this.getParameter("ITEMHEIGHT")) != null) ? Integer.parseInt(parameter4) : 12);
        final String parameter5;
        this.txtcolor = (((parameter5 = this.getParameter("TEXTCOLOR")) != null) ? Integer.parseInt(parameter5, 16) : Integer.parseInt("aa6666", 16));
        final String parameter6;
        this.highlightcolor = (((parameter6 = this.getParameter("HIGHLIGHTCOLOR")) != null) ? Integer.parseInt(parameter6, 16) : Integer.parseInt("000088", 16));
        final String parameter7;
        final String s = ((parameter7 = this.getParameter("FONT1NAME")) != null) ? parameter7 : "Helvectica";
        final String parameter8;
        final String s2 = ((parameter8 = this.getParameter("FONT2NAME")) != null) ? parameter8 : "Helvectica";
        final String parameter9;
        final String s3 = ((parameter9 = this.getParameter("FONT1STYLE")) != null) ? parameter9 : "bold";
        final String parameter10;
        final String s4 = ((parameter10 = this.getParameter("FONT2STYLE")) != null) ? parameter10 : "bold";
        final String parameter11;
        this.fontpointsize = (((parameter11 = this.getParameter("FONTPOINTSIZE")) != null) ? Integer.parseInt(parameter11) : 14);
        if (s3.equals("bold")) {
            this.fm2 = new Font(s, 1, this.fontpointsize);
        }
        else if (s3.equals("italic")) {
            this.fm2 = new Font(s, 2, this.fontpointsize);
        }
        else if (s3.equals("plain")) {
            this.fm2 = new Font(s, 0, this.fontpointsize);
        }
        else {
            this.fm2 = new Font(s, 3, this.fontpointsize);
        }
        if (s4.equals("bold")) {
            this.fm = new Font(s2, 1, this.fontpointsize);
        }
        else if (s4.equals("italic")) {
            this.fm = new Font(s2, 2, this.fontpointsize);
        }
        else if (s4.equals("plain")) {
            this.fm = new Font(s2, 0, this.fontpointsize);
        }
        else {
            this.fm = new Font(s2, 3, this.fontpointsize);
        }
        this.basey = 0;
        final String parameter12;
        String s5;
        if ((((parameter12 = this.getParameter("AUTHORINFO")) != null) ? parameter12 : "").indexOf("Nalla Senthilnathan") >= 0) {
            final String parameter13;
            s5 = (((parameter13 = this.getParameter("TOCDATA")) != null) ? parameter13 : "");
        }
        else {
            s5 = "";
        }
        final Hashtable<Integer, Integer> hashtable = new Hashtable<Integer, Integer>(101);
        int n2 = 0;
        int intValue = -1;
        this.st = new StringTokenizer(s5, "|");
        try {
            while (this.st.hasMoreTokens()) {
                this.st2 = new StringTokenizer(this.st.nextToken(), ";");
                try {
                    if (!this.st2.hasMoreTokens()) {
                        continue;
                    }
                    this.elem[this.elemcount] = new cuppatoc_tryRec();
                    this.elem[this.elemcount].level = Integer.parseInt(this.st2.nextToken().trim());
                    final int level = this.elem[this.elemcount].level;
                    if (n2 > 0 && level > n2) {
                        intValue = this.elemcount - 1;
                        hashtable.put(new Integer(level), new Integer(intValue));
                    }
                    if (n2 > 0 && level < n2) {
                        if (hashtable.get(new Integer(level)) != null) {
                            intValue = hashtable.get(new Integer(level));
                        }
                        else {
                            intValue = -1;
                        }
                    }
                    this.elem[this.elemcount].headidx = intValue;
                    n2 = level;
                    this.elem[this.elemcount].tocstr = this.st2.nextToken().trim();
                    this.elem[this.elemcount].tailcount = 0;
                    this.elem[this.elemcount].visible = false;
                    this.elem[this.elemcount].tailopen = false;
                    this.elem[this.elemcount].targetframe = this.st2.nextToken().trim();
                    if (this.elem[this.elemcount].level == 1) {
                        this.elem[this.elemcount].head = null;
                    }
                    else {
                        this.elem[this.elemcount].head = "head";
                    }
                    if (n2 > 1 && intValue >= 0) {
                        this.elem[intValue].tailidx[this.elem[intValue].tailcount++] = this.elemcount;
                    }
                    final String trim = this.st2.nextToken().trim();
                    if (trim.length() == 1) {
                        this.elem[this.elemcount].urlstr = "";
                        ++this.elemcount;
                    }
                    else {
                        this.elem[this.elemcount].urlstr = trim;
                        ++this.elemcount;
                    }
                }
                catch (NoSuchElementException ex) {}
            }
        }
        catch (NoSuchElementException ex2) {}
        try {
            for (int i = 0; i < this.elemcount; ++i) {
                if (this.elem[i].urlstr.length() > 0) {
                    this.url[i] = new URL(this.getDocumentBase(), this.elem[i].urlstr);
                }
                else {
                    this.url[i] = null;
                }
            }
        }
        catch (MalformedURLException ex3) {}
        for (int j = 0; j < this.elemcount; ++j) {
            if (this.elem[j].head == null) {
                this.elem[j].visible = true;
            }
            this.elem[j].tailopen = false;
        }
    }
    
    public void update(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offscreen == null || size.width != this.offscreensize.width || size.height != this.offscreensize.height) {
            this.offscreen = this.createImage(size.width, size.height);
            this.offscreensize = size;
            this.offgraphics = this.offscreen.getGraphics();
        }
        this.offgraphics.setColor(this.getBackground());
        this.offgraphics.fillRect(0, 0, size.width, size.height);
        this.paint(this.offgraphics);
        graphics.drawImage(this.offscreen, 0, 0, null);
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (event.clickCount == 1) {
            int n3 = 0;
            for (int i = 0; i < this.elemcount; ++i) {
                if (this.elem[i].visible) {
                    if (this.elem[i].tailcount > 0) {
                        if (!this.elem[i].tailopen) {
                            if (event.y >= this.basey + this.itemheight * (n3 + 1) - 10 && event.y <= this.basey + this.itemheight * (n3 + 1) + 5) {
                                for (int j = 0; j < this.elemcount; ++j) {
                                    if (!this.elem[j].tailopen) {
                                        this.elem[j].visible = false;
                                    }
                                }
                                this.elem[i].tailopen = true;
                                this.elem[i].visible = true;
                                for (int k = 0; k < this.elem[i].tailcount; ++k) {
                                    this.elem[this.elem[i].tailidx[k]].visible = true;
                                }
                                return true;
                            }
                            ++n3;
                        }
                        else if (this.elem[i].tailopen) {
                            if (event.y >= this.basey + this.itemheight * (n3 + 1) - 10 && event.y <= this.basey + this.itemheight * (n3 + 1) + 5) {
                                this.elem[i].tailopen = false;
                                if (this.elem[i].head == null) {
                                    for (int l = 0; l < this.elemcount; ++l) {
                                        if (this.elem[l].head == null) {
                                            this.elem[l].visible = true;
                                        }
                                    }
                                }
                                else {
                                    for (int headidx = this.elem[i].headidx, n4 = 0; n4 < this.elem[headidx].tailcount; ++n4) {
                                        this.elem[this.elem[headidx].tailidx[n4]].visible = true;
                                    }
                                }
                                for (int n5 = 0; n5 < this.elem[i].tailcount; ++n5) {
                                    final int n6 = this.elem[i].tailidx[n5];
                                    this.elem[n6].visible = false;
                                    if (this.elem[n6].tailcount > 0 && this.elem[n6].tailopen) {
                                        this.elem[n6].tailopen = false;
                                        this.closetails(n6);
                                    }
                                }
                                return true;
                            }
                            ++n3;
                        }
                    }
                    else {
                        if (event.y >= this.basey + this.itemheight * (n3 + 1) - 10 && event.y <= this.basey + this.itemheight * (n3 + 1) + 5) {
                            this.getAppletContext().showDocument(this.url[i], this.elem[i].targetframe);
                            this.docidx = i;
                            return true;
                        }
                        ++n3;
                    }
                }
            }
        }
        return true;
    }
    
    public void closetails(final int n) {
        for (int i = 0; i < this.elem[n].tailcount; ++i) {
            final int n2 = this.elem[n].tailidx[i];
            this.elem[n2].visible = false;
            if (this.elem[n2].tailcount > 0 && this.elem[n2].tailopen) {
                this.elem[n2].tailopen = false;
                this.closetails(n2);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        int n = 0;
        if (this.mt.checkAll(true) && this.bgimgfile.length() > 0) {
            graphics.drawImage(this.bgimg, 0, 0, this);
        }
        graphics.setColor(new Color(this.txtcolor));
        for (int i = 0; i < this.elemcount; ++i) {
            final int offset = this.getOffset(i);
            if (this.mousemoved == 1 && this.mousey >= this.basey + this.itemheight * (n + 1) - 10 && this.mousey <= this.basey + this.itemheight * (n + 1) + 5) {
                graphics.setColor(new Color(this.highlightcolor));
            }
            else {
                graphics.setColor(new Color(this.txtcolor));
            }
            if (this.elem[i].visible) {
                if (this.elem[i].head == null) {
                    if (this.elem[i].tailcount > 0) {
                        graphics.setFont(this.fm2);
                    }
                    else {
                        graphics.setFont(this.fm);
                    }
                    if (this.elem[i].tailcount > 0 && this.elem[i].tailopen) {
                        this.drawOpentria(graphics, this.basey + this.itemheight * (n + 1), 0, graphics.getColor());
                    }
                    else {
                        final Color color = graphics.getColor();
                        if (this.elem[i].urlstr.length() > 0) {
                            graphics.setColor(new Color(this.symbolcolor));
                            graphics.drawRect(15, this.basey + this.itemheight * (n + 1) - (int)(this.fontpointsize * 0.8), (int)(this.fontpointsize * 0.43), (int)(this.fontpointsize * 0.8));
                            graphics.setColor(color);
                        }
                        else {
                            this.drawClosetria(graphics, this.basey + this.itemheight * (n + 1), 0, graphics.getColor());
                        }
                    }
                    if (this.elem[i].urlstr.length() > 0 && this.docidx == i) {
                        graphics.setColor(new Color(this.highlightcolor));
                    }
                    graphics.drawString(this.elem[i].tocstr.trim(), offset + 15 + (int)(this.fontpointsize * 0.9), this.basey + this.itemheight * (n + 1));
                    this.elem[i].visible = true;
                    ++n;
                }
                else {
                    if (this.elem[i].tailcount > 0) {
                        graphics.setFont(this.fm2);
                    }
                    else {
                        graphics.setFont(this.fm);
                    }
                    final Color color2 = graphics.getColor();
                    if (this.elem[i].urlstr.length() > 0) {
                        graphics.setColor(new Color(this.symbolcolor));
                        graphics.drawRect(offset + 15, this.basey + this.itemheight * (n + 1) - (int)(this.fontpointsize * 0.8), (int)(this.fontpointsize * 0.43), (int)(this.fontpointsize * 0.8));
                        graphics.setColor(color2);
                    }
                    if (this.elem[i].tailcount > 0) {
                        if (this.elem[i].tailopen) {
                            this.drawOpentria(graphics, this.basey + this.itemheight * (n + 1), offset, graphics.getColor());
                        }
                        else {
                            this.drawClosetria(graphics, this.basey + this.itemheight * (n + 1), offset, graphics.getColor());
                        }
                    }
                    if (this.elem[i].urlstr.length() > 0 && this.docidx == i) {
                        graphics.setColor(new Color(this.highlightcolor));
                    }
                    graphics.drawString(this.elem[i].tocstr.trim(), offset + 15 + (int)(this.fontpointsize * 0.9), this.basey + this.itemheight * (n + 1));
                    this.elem[i].visible = true;
                    ++n;
                }
            }
        }
    }
    
    public int getOffset(final int n) {
        return (this.elem[n].level - 1) * this.fontpointsize;
    }
    
    public void drawOpentria(final Graphics graphics, final int n, final int n2, final Color color) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        array[0] = 12 + n2;
        array[1] = 12 + (int)(this.fontpointsize * 0.7) + n2;
        array[2] = 12 + (int)(this.fontpointsize * 0.7 / 2.0) + n2;
        array[3] = 12 + n2;
        array2[0] = n - (int)(this.fontpointsize * 0.7);
        array2[1] = n - (int)(this.fontpointsize * 0.7);
        array2[2] = n;
        array2[3] = n - (int)(this.fontpointsize * 0.7);
        graphics.setColor(new Color(this.symbolcolor));
        graphics.drawPolygon(array, array2, 4);
        graphics.setColor(color);
    }
    
    public void drawClosetria(final Graphics graphics, final int n, final int n2, final Color color) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        array[1] = (array[0] = 15 + n2);
        array[2] = 15 + (int)(this.fontpointsize / 2.2) + n2;
        array[3] = 15 + n2;
        array2[0] = n - this.itemheight / 2;
        array2[1] = n;
        array2[2] = n - this.itemheight / 4;
        array2[3] = n - this.itemheight / 2;
        graphics.setColor(new Color(this.symbolcolor));
        graphics.drawPolygon(array, array2, 4);
        graphics.setColor(color);
    }
    
    public void start() {
        (this.thread = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            try {
                Thread.currentThread();
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public synchronized boolean mouseMove(final Event event, final int mousex, final int mousey) {
        this.mousemoved = 1;
        this.mousex = mousex;
        this.mousey = mousey;
        return true;
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public cuppatoc_try() {
        this.menupanel = new Panel();
        this.elem = new cuppatoc_tryRec[49];
        this.argselected = -1;
        this.scale = 1.0;
        this.pixelfactor = 2.2;
        this.basex = 35;
        this.basey = 50;
        this.url = new URL[49];
        this.foundstrindexarray = new int[49];
        this.baseurl = "";
        this.selhead = "";
        this.curhead = "";
        this.baseurlisOK = true;
        this.fm = new Font("Helvectica", 0, (int)(14.0 / this.scale));
        this.fm2 = new Font("Helvectica", 1, (int)(14.0 / this.scale));
        this.msg1 = "";
        this.msg2 = "";
        this.mt = new MediaTracker(this);
        this.docidx = -1;
    }
}
