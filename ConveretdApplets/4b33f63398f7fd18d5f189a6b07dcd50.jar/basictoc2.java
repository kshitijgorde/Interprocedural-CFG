import java.awt.Component;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class basictoc2 extends Applet implements Runnable
{
    static final int MAXLINES = 200;
    char[] buf;
    Character ch;
    int layoutcount;
    int urlcount;
    String tok0;
    String tok1;
    String tok2;
    String tok3;
    int elemcount;
    int txtcolor;
    int highlightcolor;
    int symbolcolor;
    int tailcount;
    Thread thread;
    Font fm3;
    Font fm;
    Font fm2;
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
    Vector elems;
    Vector urls;
    String selhead;
    String curhead;
    int headisopen;
    int gifw;
    int gifh;
    int openmsg;
    int closemsg;
    int urlmsg;
    String msg1;
    String msg2;
    String txtfile;
    String isimgstrip;
    String bgimgfile;
    MediaTracker mt;
    boolean isopenfound;
    int docidx;
    StringTokenizer st;
    StringTokenizer st2;
    StringTokenizer st3;
    StringTokenizer sg;
    int fontpointsize;
    Image bgimg;
    int font1psize;
    int font2psize;
    int spacing;
    int spacing2;
    
    public void init() {
        String s = "";
        String s2 = "";
        String trim = "";
        String trim2 = "";
        String trim3 = "";
        String trim4 = "";
        this.setBackground(Color.white);
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
        final String s3 = ((parameter3 = this.getParameter("ITEMHEIGHT")) != null) ? parameter3 : "25";
        if (s3.indexOf(124) < 0) {
            this.gifh = Integer.parseInt(s3);
        }
        this.setBackground(new Color(n));
        final String parameter4;
        final String s4 = ((parameter4 = this.getParameter("AUTHORINFO")) != null) ? parameter4 : "";
        final String parameter5;
        this.isimgstrip = (((parameter5 = this.getParameter("isIMAGESTRIP")) != null) ? parameter5 : "yes");
        if (this.isimgstrip.equals("no")) {
            final String parameter6;
            this.txtcolor = (((parameter6 = this.getParameter("TEXTCOLOR")) != null) ? Integer.parseInt(parameter6, 16) : Integer.parseInt("aa6666", 16));
            final String parameter7;
            this.symbolcolor = (((parameter7 = this.getParameter("SYMBOLCOLOR")) != null) ? Integer.parseInt(parameter7, 16) : Integer.parseInt("aa6666", 16));
            final String parameter8;
            this.highlightcolor = (((parameter8 = this.getParameter("HIGHLIGHTCOLOR")) != null) ? Integer.parseInt(parameter8, 16) : Integer.parseInt("000088", 16));
            final String parameter9;
            s = (((parameter9 = this.getParameter("FONT1")) != null) ? parameter9 : "");
            final String parameter10;
            s2 = (((parameter10 = this.getParameter("FONT2")) != null) ? parameter10 : "");
            final String parameter11;
            this.spacing = (((parameter11 = this.getParameter("SPACING1")) != null) ? Integer.parseInt(parameter11) : 4);
            final String parameter12;
            this.spacing2 = (((parameter12 = this.getParameter("SPACING2")) != null) ? Integer.parseInt(parameter12) : 2);
        }
        if (s.length() > 0) {
            this.sg = new StringTokenizer(s, ";");
            try {
                while (this.sg.hasMoreTokens()) {
                    trim = this.sg.nextToken().trim();
                    trim3 = this.sg.nextToken().trim();
                    this.font1psize = Integer.parseInt(this.sg.nextToken().trim());
                }
            }
            catch (NoSuchElementException ex) {}
            if (trim3.equals("bold")) {
                this.fm = new Font(trim, 1, this.font1psize);
            }
            else if (trim3.equals("italic")) {
                this.fm = new Font(trim, 2, this.font1psize);
            }
            else if (trim3.equals("plain")) {
                this.fm = new Font(trim, 0, this.font1psize);
            }
            else {
                this.fm = new Font(trim, 3, this.font1psize);
            }
        }
        if (s2.length() > 0) {
            this.sg = new StringTokenizer(s2, ";");
            try {
                while (this.sg.hasMoreTokens()) {
                    trim2 = this.sg.nextToken().trim();
                    trim4 = this.sg.nextToken().trim();
                    this.font2psize = Integer.parseInt(this.sg.nextToken().trim());
                }
            }
            catch (NoSuchElementException ex2) {}
            if (trim4.equals("bold")) {
                this.fm2 = new Font(trim2, 1, this.font2psize);
            }
            else if (trim4.equals("italic")) {
                this.fm2 = new Font(trim2, 2, this.font2psize);
            }
            else if (trim4.equals("plain")) {
                this.fm2 = new Font(trim2, 0, this.font2psize);
            }
            else {
                this.fm2 = new Font(trim2, 3, this.font2psize);
            }
        }
        String s5;
        if (s4.indexOf("Nalla Senthilnathan") >= 0 && s4.indexOf("nrsenthil.tripod.com") >= 0) {
            final String parameter13;
            s5 = (((parameter13 = this.getParameter("TOCDATA")) != null) ? parameter13 : "");
        }
        else {
            s5 = "";
        }
        final String parameter14;
        final String s6 = ((parameter14 = this.getParameter("CURRENTDOC")) != null) ? parameter14 : "";
        this.st = new StringTokenizer(s5, "|");
        try {
            while (this.st.hasMoreTokens()) {
                this.st2 = new StringTokenizer(this.st.nextToken(), ";");
                try {
                    if (!this.st2.hasMoreTokens()) {
                        continue;
                    }
                    this.elems.addElement(new basictoc2Rec());
                    this.elems.elementAt(this.elemcount).level = Integer.parseInt(this.st2.nextToken().trim());
                    final String trim5 = this.st2.nextToken().trim();
                    this.elems.elementAt(this.elemcount).helpstr = trim5;
                    if (this.isimgstrip.equals("yes")) {
                        this.elems.elementAt(this.elemcount).altstr = this.st2.nextToken().trim();
                        this.elems.elementAt(this.elemcount).itemh = this.gifh;
                    }
                    else if (trim5.indexOf(44) > 0) {
                        this.st3 = new StringTokenizer(trim5, ",");
                        try {
                            while (this.st3.hasMoreTokens()) {
                                this.elems.elementAt(this.elemcount).helpstr = this.st3.nextToken().trim();
                                this.elems.elementAt(this.elemcount).altstr = this.st3.nextToken().trim();
                            }
                        }
                        catch (NoSuchElementException ex3) {}
                        if (this.elems.elementAt(this.elemcount).level == 1) {
                            this.elems.elementAt(this.elemcount).itemh = 2 * this.spacing + 2 * this.font1psize + this.spacing2;
                        }
                        else {
                            this.elems.elementAt(this.elemcount).itemh = 2 * this.spacing + 2 * this.font2psize + this.spacing2;
                        }
                    }
                    else if (this.elems.elementAt(this.elemcount).level == 1) {
                        this.elems.elementAt(this.elemcount).itemh = this.font1psize + this.spacing * 2;
                    }
                    else {
                        this.elems.elementAt(this.elemcount).itemh = this.font2psize + this.spacing * 2;
                    }
                    this.elems.elementAt(this.elemcount).target = this.st2.nextToken().trim();
                    if (this.elems.elementAt(this.elemcount).level == 1) {
                        this.elems.elementAt(this.elemcount).ishead = true;
                        this.elems.elementAt(this.elemcount).isopen = false;
                        this.elems.elementAt(this.elemcount).isvisible = true;
                        final int elemcount = this.elemcount;
                    }
                    else {
                        this.elems.elementAt(this.elemcount).ishead = false;
                        this.elems.elementAt(this.elemcount).isopen = false;
                        this.elems.elementAt(this.elemcount).isvisible = false;
                    }
                    final String trim6 = this.st2.nextToken().trim();
                    if (trim6.length() == 1) {
                        this.elems.elementAt(this.elemcount).urlstr = "";
                        ++this.elemcount;
                    }
                    else {
                        this.elems.elementAt(this.elemcount).urlstr = trim6;
                        ++this.elemcount;
                    }
                }
                catch (NoSuchElementException ex4) {}
            }
        }
        catch (NoSuchElementException ex5) {}
        try {
            for (int i = 0; i < this.elemcount; ++i) {
                if (((basictoc2Rec)this.elems.elementAt(i)).urlstr.length() > 0) {
                    this.urls.addElement(new URL(this.getDocumentBase(), ((basictoc2Rec)this.elems.elementAt(i)).urlstr));
                }
                else {
                    this.urls.addElement(null);
                }
            }
        }
        catch (MalformedURLException ex6) {}
        int n2 = 0;
        if (this.isimgstrip.equals("yes")) {
            for (int j = 0; j < this.elemcount; ++j) {
                ((basictoc2Rec)this.elems.elementAt(j)).defimg = this.getImage(this.getCodeBase(), ((basictoc2Rec)this.elems.elementAt(j)).helpstr);
                this.mt.addImage(((basictoc2Rec)this.elems.elementAt(j)).defimg, n2);
                ++n2;
                ((basictoc2Rec)this.elems.elementAt(j)).altimg = this.getImage(this.getCodeBase(), ((basictoc2Rec)this.elems.elementAt(j)).altstr);
                this.mt.addImage(((basictoc2Rec)this.elems.elementAt(j)).altimg, n2);
                ++n2;
            }
            if (s3.indexOf(124) > 0) {
                this.st3 = new StringTokenizer(s3, "|");
                for (int k = 0; k < this.elemcount; ++k) {
                    try {
                        if (this.st3.hasMoreTokens()) {
                            ((basictoc2Rec)this.elems.elementAt(k)).itemh = Integer.parseInt(this.st3.nextToken());
                        }
                    }
                    catch (NoSuchElementException ex7) {}
                }
            }
            else {
                for (int l = 0; l < this.elemcount; ++l) {
                    ((basictoc2Rec)this.elems.elementAt(l)).itemh = this.gifh;
                }
            }
        }
        if (s6.length() > 0) {
            for (int docidx = 0; docidx < this.elemcount; ++docidx) {
                if (((basictoc2Rec)this.elems.elementAt(docidx)).ishead) {
                    if (((basictoc2Rec)this.elems.elementAt(docidx)).urlstr.equals(s6)) {
                        this.docidx = docidx;
                        return;
                    }
                    for (int docidx2 = docidx + 1; docidx2 < this.elemcount && !((basictoc2Rec)this.elems.elementAt(docidx2)).ishead; ++docidx2) {
                        if (((basictoc2Rec)this.elems.elementAt(docidx2)).urlstr.equals(s6)) {
                            this.docidx = docidx2;
                            ((basictoc2Rec)this.elems.elementAt(docidx)).isopen = true;
                            this.openAllTails(docidx);
                            break;
                        }
                    }
                }
            }
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
        if (event.id == 501 && event.clickCount == 1) {
            int n3 = 0;
            int n4 = -1;
            for (int i = 0; i < this.elemcount; ++i) {
                if (((basictoc2Rec)this.elems.elementAt(i)).isvisible) {
                    final int itemh = this.elems.elementAt(i).itemh;
                    if (event.y >= n3 && event.y <= n3 + itemh) {
                        if (((basictoc2Rec)this.elems.elementAt(i)).urlstr.length() > 0) {
                            this.getAppletContext().showDocument((URL)this.urls.elementAt(i), ((basictoc2Rec)this.elems.elementAt(i)).target);
                            this.docidx = i;
                            if (((basictoc2Rec)this.elems.elementAt(i)).ishead) {
                                this.closeAllTails2();
                            }
                            return true;
                        }
                        if (((basictoc2Rec)this.elems.elementAt(i)).ishead) {
                            n4 = i;
                            break;
                        }
                    }
                    n3 += itemh;
                }
            }
            int j = 0;
            if (n4 >= 0) {
                while (j < this.elemcount) {
                    if (((basictoc2Rec)this.elems.elementAt(j)).ishead) {
                        if (j == n4) {
                            if (((basictoc2Rec)this.elems.elementAt(j)).isopen) {
                                ((basictoc2Rec)this.elems.elementAt(j)).isopen = false;
                                this.closeAllTails(j);
                            }
                            else {
                                ((basictoc2Rec)this.elems.elementAt(j)).isopen = true;
                                this.openAllTails(j);
                            }
                        }
                        else if (((basictoc2Rec)this.elems.elementAt(j)).isopen) {
                            ((basictoc2Rec)this.elems.elementAt(j)).isopen = false;
                            this.closeAllTails(j);
                        }
                    }
                    ++j;
                }
            }
            return true;
        }
        return false;
    }
    
    public void closeAllTails2() {
        for (int i = 0; i < this.elemcount; ++i) {
            if (((basictoc2Rec)this.elems.elementAt(i)).ishead && ((basictoc2Rec)this.elems.elementAt(i)).isopen) {
                ((basictoc2Rec)this.elems.elementAt(i)).isopen = false;
                this.closeAllTails(i);
            }
        }
    }
    
    public void closeAllTails(final int n) {
        for (int n2 = n + 1; n2 < this.elemcount && !((basictoc2Rec)this.elems.elementAt(n2)).ishead; ++n2) {
            ((basictoc2Rec)this.elems.elementAt(n2)).isvisible = false;
            ((basictoc2Rec)this.elems.elementAt(n2)).isopen = false;
        }
    }
    
    public void openAllTails(final int n) {
        for (int n2 = n + 1; n2 < this.elemcount && !((basictoc2Rec)this.elems.elementAt(n2)).ishead; ++n2) {
            ((basictoc2Rec)this.elems.elementAt(n2)).isvisible = true;
            ((basictoc2Rec)this.elems.elementAt(n2)).isopen = true;
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 505) {
            this.mousemoved = 0;
        }
        return super.handleEvent(event);
    }
    
    public void paint(final Graphics graphics) {
        int n = 0;
        if (this.mousemoved == 1 && this.urlmsg >= 0) {
            this.showStatus(this.elems.elementAt(this.urlmsg).urlstr);
        }
        this.openmsg = 0;
        this.closemsg = 0;
        this.urlmsg = -1;
        if (!this.isimgstrip.equals("yes")) {
            if (this.bgimgfile.length() > 0 && this.mt.checkAll(true)) {
                graphics.drawImage(this.bgimg, 0, 0, this);
            }
            for (int i = 0; i < this.elemcount; ++i) {
                if (((basictoc2Rec)this.elems.elementAt(i)).isvisible) {
                    final int itemh = this.elems.elementAt(i).itemh;
                    if (this.mousemoved == 1 && this.mousey >= n && this.mousey <= n + itemh) {
                        this.urlmsg = i;
                    }
                    int n2;
                    int n3;
                    if (((basictoc2Rec)this.elems.elementAt(i)).ishead) {
                        graphics.setFont(this.fm);
                        n2 = n + this.spacing + this.font1psize;
                        n3 = this.font1psize;
                    }
                    else {
                        graphics.setFont(this.fm2);
                        n2 = n + this.spacing + this.font2psize;
                        n3 = this.font2psize;
                    }
                    graphics.setColor(new Color(this.symbolcolor));
                    if (((basictoc2Rec)this.elems.elementAt(i)).ishead) {
                        if (((basictoc2Rec)this.elems.elementAt(i)).urlstr.length() > 0) {
                            if ((this.mousemoved == 1 && this.mousey >= n && this.mousey <= n + itemh) || this.docidx == i) {
                                graphics.fillRect(10, n2 - (int)(this.font1psize * 0.8), (int)(this.font1psize * 0.5), (int)(this.font1psize * 0.8));
                            }
                            graphics.drawRect(10, n2 - (int)(this.font1psize * 0.8), (int)(this.font1psize * 0.5), (int)(this.font1psize * 0.8));
                        }
                        else if (((basictoc2Rec)this.elems.elementAt(i)).isopen) {
                            this.drawFillOpentria(graphics, n2);
                            this.drawOpentria(graphics, n2);
                        }
                        else {
                            if (this.mousemoved == 1 && this.mousey >= n && this.mousey <= n + itemh) {
                                this.drawFillClosetria(graphics, n2);
                            }
                            this.drawClosetria(graphics, n2);
                        }
                    }
                    if ((this.mousemoved == 1 && this.mousey >= n && this.mousey <= n + itemh) || this.docidx == i) {
                        graphics.setColor(new Color(this.highlightcolor));
                    }
                    else {
                        graphics.setColor(new Color(this.txtcolor));
                    }
                    graphics.drawString(this.elems.elementAt(i).helpstr, 25, n2);
                    n = n2 + this.spacing;
                    if (((basictoc2Rec)this.elems.elementAt(i)).altstr.length() > 0) {
                        final int n4 = n2 + this.spacing2;
                        graphics.drawString(this.elems.elementAt(i).altstr, 25, n4 + n3);
                        n = n4 + n3 + this.spacing;
                    }
                }
            }
            return;
        }
        if (this.mt.checkAll(true)) {
            for (int j = 0; j < this.elemcount; ++j) {
                if (((basictoc2Rec)this.elems.elementAt(j)).isvisible) {
                    final int itemh2 = this.elems.elementAt(j).itemh;
                    if (this.mousemoved == 1 && this.mousey >= n && this.mousey <= n + itemh2) {
                        graphics.drawImage(((basictoc2Rec)this.elems.elementAt(j)).altimg, 0, n, this);
                        this.urlmsg = j;
                    }
                    else if (this.docidx == j || (((basictoc2Rec)this.elems.elementAt(j)).ishead && ((basictoc2Rec)this.elems.elementAt(j)).isopen)) {
                        graphics.drawImage(((basictoc2Rec)this.elems.elementAt(j)).altimg, 0, n, this);
                    }
                    else {
                        graphics.drawImage(((basictoc2Rec)this.elems.elementAt(j)).defimg, 0, n, this);
                    }
                    n += itemh2;
                }
            }
            return;
        }
        graphics.setColor(Color.black);
        graphics.drawString("Loading TOC...,", this.bounds().width / 5, this.bounds().height / 2);
    }
    
    public void drawClosetria(final Graphics graphics, final int n) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        this.gifh = this.font1psize * 3 / 2;
        array[1] = (array[0] = 10);
        array[2] = 10 + (int)(this.font1psize * 0.6);
        array[3] = 10;
        array2[0] = n - (int)(this.font1psize * 0.9);
        array2[1] = n;
        array2[2] = n - (int)(this.font1psize * 0.9 / 2.0);
        array2[3] = n - (int)(this.font1psize * 0.9);
        graphics.drawPolygon(array, array2, 4);
    }
    
    public void drawOpentria(final Graphics graphics, final int n) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        array[0] = 10;
        array[1] = 10 + (int)(this.font1psize * 0.7);
        array[2] = 10 + (int)(this.font1psize * 0.7 / 2.0);
        array[3] = 10;
        array2[0] = n - (int)(this.font1psize * 0.7);
        array2[1] = n - (int)(this.font1psize * 0.7);
        array2[2] = n;
        array2[3] = n - (int)(this.font1psize * 0.7);
        graphics.drawPolygon(array, array2, 4);
    }
    
    public void drawFillClosetria(final Graphics graphics, final int n) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        this.gifh = this.font1psize * 3 / 2;
        array[1] = (array[0] = 10);
        array[2] = 10 + (int)(this.font1psize * 0.6);
        array[3] = 10;
        array2[0] = n - (int)(this.font1psize * 0.9);
        array2[1] = n;
        array2[2] = n - (int)(this.font1psize * 0.9 / 2.0);
        array2[3] = n - (int)(this.font1psize * 0.9);
        graphics.fillPolygon(array, array2, 4);
    }
    
    public void drawFillOpentria(final Graphics graphics, final int n) {
        final int[] array = new int[4];
        final int[] array2 = new int[4];
        array[0] = 10;
        array[1] = 10 + (int)(this.font1psize * 0.7);
        array[2] = 10 + (int)(this.font1psize * 0.7 / 2.0);
        array[3] = 10;
        array2[0] = n - (int)(this.font1psize * 0.7);
        array2[1] = n - (int)(this.font1psize * 0.7);
        array2[2] = n;
        array2[3] = n - (int)(this.font1psize * 0.7);
        graphics.fillPolygon(array, array2, 4);
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
    
    public basictoc2() {
        this.buf = new char[1];
        this.ch = new Character('©');
        this.fm3 = new Font("Helvectica", 0, 8);
        this.fm = new Font("Helvectica", 0, 14);
        this.fm2 = new Font("Helvectica", 1, 14);
        this.basex = 35;
        this.elems = new Vector(50, 10);
        this.urls = new Vector(50, 10);
        this.selhead = "";
        this.curhead = "";
        this.urlmsg = -1;
        this.msg1 = "";
        this.msg2 = "";
        this.mt = new MediaTracker(this);
        this.isopenfound = false;
        this.docidx = -1;
        this.font1psize = 12;
        this.font2psize = 12;
        this.spacing = 4;
        this.spacing2 = 2;
    }
}
