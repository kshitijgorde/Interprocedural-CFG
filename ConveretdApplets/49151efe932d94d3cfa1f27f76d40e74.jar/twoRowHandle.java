import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.util.Hashtable;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class twoRowHandle extends Canvas implements dataInterface, Runnable
{
    useHandleInterface showbr;
    public boolean calculate;
    Image offImage;
    Graphics offGrfx;
    public Vector quoteList;
    int maxAscent;
    int maxDescent;
    int hgap;
    int vgap;
    int[] yOffset;
    Thread aTh;
    int mouseX;
    int mouseY;
    int delay;
    int[] columnOffsets;
    FontMetrics fm;
    FontMetrics fm1;
    int currentindex;
    public Color bg;
    public Color fg;
    public Color highlight;
    public Color qup;
    public Color qdown;
    int[] xoffset;
    public int[] newxoffset;
    String[] datas;
    int maxwidth;
    int ypos;
    public Font outFont;
    public Font symbolFont;
    public String msg;
    public boolean marketcanvas;
    public String direction;
    String[] disdata;
    private final String[] middle;
    int discounter;
    public boolean mouseinside;
    int width;
    int height;
    private Vector symbolVector;
    private boolean disclaimer;
    private Hashtable quoteData;
    String mode;
    int dataCol;
    int startingCol;
    Image rt;
    Image eod;
    Image pd;
    
    public twoRowHandle(final useHandleInterface showbr, final int fontsize, final int height, final int width, final boolean symbolBold, final boolean disclaimer) {
        this.calculate = false;
        this.quoteList = null;
        this.hgap = 5;
        this.vgap = 1;
        this.aTh = null;
        this.delay = 35;
        this.currentindex = 0;
        this.bg = Color.decode("#836FFF");
        this.fg = Color.white;
        this.highlight = Color.blue;
        this.qup = Color.decode("#008000");
        this.qdown = new Color(206, 64, 64);
        this.msg = "Loading Data ...";
        this.marketcanvas = false;
        this.direction = "LEFT";
        this.disdata = new String[6];
        this.middle = new String[] { "Powered by Quotemedia.com", "Data Delayed 15-20m" };
        this.discounter = 0;
        this.mouseinside = false;
        this.symbolVector = new Vector();
        this.quoteData = new Hashtable();
        this.mode = "name";
        this.dataCol = 4;
        this.startingCol = 1;
        this.showbr = showbr;
        this.height = height;
        this.width = width;
        this.setBackground(this.bg);
        this.outFont = new Font("Helvetica", 0, fontsize);
        this.fm = this.getFontMetrics(this.outFont);
        this.maxAscent = this.fm.getMaxAscent();
        this.maxDescent = this.fm.getMaxDescent();
        this.ypos = this.maxAscent + (height - this.maxAscent - this.maxDescent) / 2;
        int symface = 0;
        if (symbolBold) {
            symface = 1;
        }
        this.symbolFont = new Font("Helvetica", symface, fontsize);
        this.fm1 = this.getFontMetrics(this.symbolFont);
        this.disclaimer = disclaimer;
    }
    
    public void setSymbolList(final Vector symbolVector) {
        this.symbolVector = symbolVector;
    }
    
    public void setDisplayMode(final String mode) {
        this.mode = mode;
        if (mode.equals("both")) {
            this.dataCol = 5;
            this.startingCol = 0;
        }
    }
    
    public void start() {
        this.offImage = null;
        this.quoteList = null;
        this.repaint();
        if (this.aTh == null) {
            this.aTh = new Thread(this);
        }
        this.aTh.start();
    }
    
    public void stop() {
        if (this.aTh != null) {
            this.aTh.stop();
            this.aTh = null;
        }
    }
    
    public void destroy() {
        if (this.aTh != null) {
            this.aTh = null;
        }
    }
    
    public void startThread() {
        if (this.aTh == null) {
            this.aTh = new Thread(this);
        }
        this.aTh.start();
    }
    
    public void setDirection(final String direction) {
        this.direction = direction;
    }
    
    public void paint(final Graphics graphics1) {
        try {
            if (this.offImage == null) {
                this.offImage = this.createImage(this.width, this.height);
            }
        }
        catch (Exception ex) {}
        if (this.offImage != null) {
            graphics1.drawImage(this.offImage, 0, 0, this);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    private Vector getFreshData() {
        final Vector dV = new Vector();
        final Enumeration enu = this.symbolVector.elements();
        int k = 0;
        if (this.quoteData != null && this.symbolVector != null) {
            while (enu.hasMoreElements() && k < 52) {
                final String as = enu.nextElement();
                String[] data = this.quoteData.get(as);
                if (data == null) {
                    data = new String[] { as, as, "N/A", "N/A", "N/A", "N/A" };
                }
                try {
                    if (Float.valueOf(data[3]) < 0.0f) {
                        data[4] = "-" + data[4];
                    }
                }
                catch (Exception ex) {}
                dV.addElement(data);
                ++k;
            }
            final int nodata = this.symbolVector.size();
            if (nodata < 3) {
                final Vector tdata = (Vector)dV.clone();
                final Enumeration enu2 = tdata.elements();
                while (enu2.hasMoreElements()) {
                    final String[] as2 = enu2.nextElement();
                    dV.addElement(as2);
                }
                final Enumeration enu3 = tdata.elements();
                while (enu3.hasMoreElements()) {
                    final String[] as3 = enu3.nextElement();
                    dV.addElement(as3);
                }
            }
        }
        return dV;
    }
    
    public void drawTicker() {
        if (this.offImage == null) {
            this.offImage = this.createImage(this.width, this.height);
        }
        (this.offGrfx = this.offImage.getGraphics()).setColor(this.getBackground());
        this.offGrfx.fillRect(0, 0, this.width, this.height);
        this.offGrfx.setColor(this.fg);
        try {
            this.offGrfx.setFont(this.outFont);
        }
        catch (NullPointerException ex) {}
        int j = 0;
        int quoteIndex = 0;
        if (this.quoteList != null) {
            final Enumeration enumeration1 = this.quoteList.elements();
            final int arrowy = 9 + (this.height - 8) / 2;
            while (enumeration1.hasMoreElements()) {
                String[] as;
                int i;
                int arrowheight;
                int[] x;
                int[] y;
                for (as = enumeration1.nextElement(), i = this.startingCol, i = this.startingCol; i < as.length - 1; ++i) {
                    if (i <= this.startingCol) {
                        try {
                            this.offGrfx.setFont(this.symbolFont);
                        }
                        catch (NullPointerException unused6) {}
                    }
                    else {
                        try {
                            this.offGrfx.setFont(this.outFont);
                        }
                        catch (NullPointerException ex2) {}
                    }
                    if (i == 3) {
                        arrowheight = 4;
                        if (as[i].charAt(0) == '+') {
                            x = new int[3];
                            y = new int[] { arrowy - 2 * arrowheight, arrowy, 0 };
                            y[2] = y[1];
                            x[0] = this.newxoffset[j] + 3 - arrowheight;
                            x[1] = this.newxoffset[j] + 3 - 2 * arrowheight;
                            x[2] = this.newxoffset[j] + 3;
                            this.offGrfx.setColor(this.qup);
                            this.offGrfx.fillPolygon(x, y, 3);
                        }
                        if (as[i].charAt(0) == '-') {
                            x = new int[3];
                            y = new int[] { arrowy, arrowy - 2 * arrowheight, 0 };
                            y[2] = y[1];
                            x[0] = this.newxoffset[j] - arrowheight - 3;
                            x[1] = this.newxoffset[j] - 2 * arrowheight - 3;
                            x[2] = this.newxoffset[j] - 3;
                            this.offGrfx.setColor(this.qdown);
                            this.offGrfx.fillPolygon(x, y, 3);
                        }
                    }
                    this.offGrfx.setColor(this.fg);
                    if (this.mouseinside && this.mouseX > this.newxoffset[j] && this.mouseX < this.newxoffset[j] + this.xoffset[j]) {
                        this.currentindex = quoteIndex;
                        this.offGrfx.setColor(this.highlight);
                    }
                    if (as[i].charAt(0) == '-' || as[i].charAt(0) == '+') {
                        if (as[i].charAt(0) == '-') {
                            this.offGrfx.drawString(as[i].substring(1), this.newxoffset[j] + this.fm.charWidth('-'), this.ypos);
                        }
                        else if (as[i].charAt(0) == '+') {
                            this.offGrfx.drawString(as[i].substring(1), this.newxoffset[j] + this.fm.charWidth('+'), this.ypos);
                        }
                    }
                    else {
                        this.offGrfx.drawString(as[i], this.newxoffset[j], this.ypos);
                    }
                    if (i == 1) {
                        if (this.rt != null && (as[5].equalsIgnoreCase("RT") || as[5].equalsIgnoreCase("REALTIME"))) {
                            this.offGrfx.drawImage(this.rt, this.newxoffset[j + 1] - 21, this.ypos - 11, this);
                        }
                        else if (this.eod != null && (as[5].equalsIgnoreCase("EOD") || as[5].equalsIgnoreCase("end-of-day"))) {
                            this.offGrfx.drawImage(this.eod, this.newxoffset[j + 1] - 21, this.ypos - 11, this);
                        }
                        else if (this.pd != null && (as[5].equalsIgnoreCase("PD") || as[5].equalsIgnoreCase("previous-day"))) {
                            this.offGrfx.drawImage(this.pd, this.newxoffset[j + 1] - 21, this.ypos - 11, this);
                        }
                    }
                    ++j;
                }
                ++quoteIndex;
            }
        }
        else {
            this.offGrfx.setColor(this.fg);
            this.offGrfx.drawString(this.msg, (this.getSize().width - this.fm.stringWidth(this.msg)) / 2, this.ypos);
        }
    }
    
    public void refreshData() {
        this.offImage = null;
        this.quoteList = null;
        this.repaint();
        this.stop();
        this.start();
    }
    
    public void setData(final Hashtable quoteData) {
        this.quoteData = quoteData;
    }
    
    public synchronized void getQuotes() {
        this.discounter = 0;
        final Vector currQuotes = this.getFreshData();
        if (currQuotes != null && currQuotes != this.quoteList && currQuotes.size() > 0) {
            if (this.marketcanvas && this.disclaimer) {
                this.disdata[0] = " ";
                this.disdata[1] = " ";
                this.disdata[2] = this.middle[this.discounter];
                this.disdata[3] = " ";
                this.disdata[4] = " ";
                this.disdata[5] = " ";
                currQuotes.addElement(this.disdata);
            }
            final int totalColumnSize = 0;
            final int noofdata = currQuotes.size();
            this.xoffset = new int[noofdata * this.dataCol];
            this.newxoffset = new int[noofdata * this.dataCol];
            this.xoffset[0] = this.width;
            this.newxoffset[0] = this.getSize().width;
            int k = 0;
            final Enumeration enumeration1 = currQuotes.elements();
            while (enumeration1.hasMoreElements()) {
                final String[] as = enumeration1.nextElement();
                for (int i = this.startingCol; i < as.length - 1; ++i) {
                    FontMetrics tmpFm;
                    if (i <= 1) {
                        tmpFm = this.fm1;
                    }
                    else {
                        tmpFm = this.fm;
                    }
                    if (i == 2) {
                        this.xoffset[k] = tmpFm.stringWidth(as[i]);
                        if (as[5] != null && !as[5].equalsIgnoreCase("DELAYED") && !as[5].equalsIgnoreCase("DL")) {
                            final int[] xoffset = this.xoffset;
                            final int n = k - 1;
                            xoffset[n] += 25;
                        }
                    }
                    else if (as[i].charAt(0) == '-' || as[i].charAt(0) == '+') {
                        if (as[i].charAt(0) == '-') {
                            this.xoffset[k] = tmpFm.charWidth('-') + this.fm.stringWidth(as[i].substring(1));
                        }
                        else if (as[i].charAt(0) == '+') {
                            this.xoffset[k] = tmpFm.charWidth('+') + this.fm.stringWidth(as[i].substring(1));
                        }
                    }
                    else {
                        this.xoffset[k] = tmpFm.stringWidth(as[i]);
                    }
                    if (k == 0 && !as[5].equalsIgnoreCase("DELAYED") && !as[5].equalsIgnoreCase("DL")) {
                        final int[] xoffset2 = this.xoffset;
                        final int n2 = k;
                        xoffset2[n2] += 5;
                    }
                    if (this.maxwidth < this.xoffset[k]) {
                        this.maxwidth = this.xoffset[k];
                    }
                    ++k;
                }
                final int[] xoffset3 = this.xoffset;
                final int n3 = k - 1;
                xoffset3[n3] += 43;
            }
            for (int j = 1; j < this.xoffset.length; ++j) {
                this.newxoffset[j] = this.newxoffset[j - 1] + this.xoffset[j - 1] + 15;
            }
            this.calculate = true;
            if (currQuotes != this.quoteList) {
                this.quoteList = currQuotes;
            }
        }
    }
    
    public void run() {
        int refreshcount = 0;
        while (this.aTh != null) {
            long time = System.currentTimeMillis();
            try {
                time += this.delay;
                Thread.sleep(Math.max(0L, time - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
            if (this.quoteList == null) {
                this.getQuotes();
            }
            refreshcount += this.delay;
            if (refreshcount > 30000) {
                refreshcount = 0;
                if (this.marketcanvas && this.disclaimer) {
                    ++this.discounter;
                    if (this.discounter > 1) {
                        this.discounter = 0;
                    }
                    this.disdata[2] = this.middle[this.discounter];
                }
            }
            if (this.quoteList != null) {
                if (this.direction.equals("LEFT")) {
                    for (int i = 0; i < this.newxoffset.length; ++i) {
                        final int[] newxoffset = this.newxoffset;
                        final int n = i;
                        --newxoffset[n];
                        if (this.newxoffset[i] < -(this.maxwidth + this.xoffset[i] + 60)) {
                            if (i == 0) {
                                this.newxoffset[i] = this.newxoffset[this.newxoffset.length - 1] + this.xoffset[this.xoffset.length - 1] + 15;
                            }
                            else {
                                this.newxoffset[i] = this.newxoffset[i - 1] + this.xoffset[i - 1] + 15;
                            }
                        }
                    }
                }
                else if (this.direction.equals("RIGHT")) {
                    for (int i = 0; i < this.newxoffset.length; ++i) {
                        final int[] newxoffset2 = this.newxoffset;
                        final int n2 = i;
                        ++newxoffset2[n2];
                        if (this.newxoffset[i] > this.getSize().width + this.xoffset[i] + 60) {
                            if (i == this.newxoffset.length - 1) {
                                this.newxoffset[i] = this.newxoffset[0] - this.xoffset[i] - 15;
                            }
                            else {
                                this.newxoffset[i] = this.newxoffset[i + 1] - this.xoffset[i] - 15;
                            }
                        }
                    }
                }
            }
            this.drawTicker();
            this.repaint();
        }
    }
    
    public void StopScrolling(final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.mouseinside = true;
        this.drawTicker();
        this.repaint();
        this.stop();
    }
    
    public void StartScrolling(final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.mouseinside = false;
        this.startThread();
    }
    
    public void RepaintTicker(final int mouseX, final int mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.drawTicker();
        this.repaint();
    }
    
    public String getCurrentSymbol() {
        final String[] sym = this.quoteList.elementAt(this.currentindex);
        if (sym != null) {
            return sym[0];
        }
        return null;
    }
    
    public void updateMessage(final String msg) {
        this.quoteList = null;
        this.msg = msg;
        this.repaint();
    }
}
