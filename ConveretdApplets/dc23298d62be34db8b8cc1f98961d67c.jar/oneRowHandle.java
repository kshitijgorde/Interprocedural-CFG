import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class oneRowHandle extends Canvas implements MouseListener, MouseMotionListener, dataInterface, Runnable
{
    useHandleInterface showbr;
    public boolean calculate;
    Image offImage;
    Graphics offGrfx;
    oneRowData qg;
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
    private String mainsymbollist;
    public Color bg;
    public Color fg;
    public Color highlight;
    public Color qup;
    public Color qdown;
    public Color changecolorup;
    public Color changecolordown;
    int[] xoffset;
    public int[] newxoffset;
    String[] datas;
    private int noofdata;
    int maxwidth;
    int ypos;
    public Font dataFont;
    public Font symbolFont;
    public String msg;
    public String direction;
    String[] disdata;
    private final String[] middle;
    int discounter;
    public boolean mouseinside;
    int width;
    int height;
    private Vector symbolVector;
    private boolean disclaimer;
    private boolean verticalLine;
    String mode;
    int dataCol;
    int startingCol;
    Image rt;
    Image eod;
    Image pd;
    
    public oneRowHandle(final useHandleInterface showbr, final Font symbolFont, final Font dataFont, final int height, final int width, final boolean disclaimer, final boolean verticalLine) {
        this.calculate = false;
        this.quoteList = null;
        this.hgap = 5;
        this.vgap = 1;
        this.aTh = null;
        this.delay = 35;
        this.currentindex = 0;
        this.mainsymbollist = "";
        this.bg = Color.decode("#836FFF");
        this.fg = Color.white;
        this.highlight = Color.blue;
        this.qup = Color.decode("#008000");
        this.qdown = new Color(206, 64, 64);
        this.changecolorup = Color.white;
        this.changecolordown = Color.white;
        this.msg = "Loading Data ...";
        this.direction = "LEFT";
        this.disdata = new String[6];
        this.middle = new String[] { "Powered by Quotemedia.com", "Data Delayed 15-20m" };
        this.discounter = 1;
        this.mouseinside = false;
        this.symbolVector = new Vector();
        this.mode = "name";
        this.dataCol = 4;
        this.startingCol = 1;
        this.showbr = showbr;
        this.height = height;
        this.width = width;
        this.qg = new oneRowData(this);
        this.setBackground(this.bg);
        this.dataFont = dataFont;
        this.symbolFont = symbolFont;
        this.verticalLine = verticalLine;
        this.fm = this.getFontMetrics(dataFont);
        this.fm1 = this.getFontMetrics(symbolFont);
        this.maxAscent = this.fm.getMaxAscent();
        this.maxDescent = this.fm.getMaxDescent();
        this.ypos = this.maxAscent + (height - this.maxAscent - this.maxDescent) / 2;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.disclaimer = disclaimer;
    }
    
    public void setDisplayMode(final String mode) {
        this.mode = mode;
        if (mode.equals("both")) {
            this.dataCol = 5;
            this.startingCol = 0;
        }
        this.qg.setDisplayMode(mode);
    }
    
    public void setSymbol(final String ssm) {
        final Vector tsymbolVector = new Vector();
        this.mainsymbollist = ssm;
        final StringTokenizer tok = new StringTokenizer(this.mainsymbollist, ",");
        String msym = "";
        int counter = 0;
        while (tok.hasMoreTokens()) {
            final String tsy = tok.nextToken();
            if (counter < 101) {
                tsymbolVector.addElement(tsy);
                msym = msym + "symbols=" + tsy + "&";
            }
            ++counter;
        }
        this.qg.setSymbol(msym);
        this.symbolVector = tsymbolVector;
    }
    
    public void start() {
        this.offImage = null;
        if (this.aTh == null) {
            this.aTh = new Thread(this);
        }
        try {
            this.aTh.start();
        }
        catch (Exception ex) {}
    }
    
    public void stop() {
        if (this.aTh != null && this.aTh.isAlive()) {
            this.aTh.interrupt();
        }
        this.aTh = null;
    }
    
    public void destroy() {
        if (this.aTh != null) {
            this.aTh.interrupt();
            this.aTh = null;
            this.removeMouseListener(this);
            this.removeMouseMotionListener(this);
        }
    }
    
    public void stopThread() {
        if (this.aTh != null && this.aTh.isAlive()) {
            this.aTh.interrupt();
        }
        this.aTh = null;
    }
    
    public void startThread() {
        if (this.aTh == null) {
            this.aTh = new Thread(this);
        }
        try {
            this.aTh.start();
        }
        catch (Exception ex) {}
    }
    
    public void setDirection(final String direction) {
        this.direction = direction;
    }
    
    public void paint(final Graphics graphics1) {
        if (this.offImage == null) {
            this.offImage = this.createImage(this.width, this.height);
        }
        graphics1.drawImage(this.offImage, 0, 0, this);
    }
    
    public void drawTicker() {
        if (this.offImage == null) {
            this.offImage = this.createImage(this.width, this.height);
        }
        (this.offGrfx = this.offImage.getGraphics()).setColor(this.getBackground());
        this.offGrfx.fillRect(0, 0, this.width, this.height);
        this.offGrfx.setColor(this.fg);
        try {
            this.offGrfx.setFont(this.dataFont);
        }
        catch (NullPointerException ex) {}
        int j = 0;
        int quoteIndex = 0;
        if (this.quoteList != null) {
            final Enumeration enumeration1 = this.quoteList.elements();
            final int arrowy = 9 + (this.height - 8) / 2;
            while (enumeration1.hasMoreElements()) {
                final String[] as = enumeration1.nextElement();
                int i = this.startingCol;
                if (this.verticalLine) {
                    this.offGrfx.setColor(this.fg);
                    this.offGrfx.drawLine(this.newxoffset[j] - 6, 3, this.newxoffset[j] - 6, this.ypos);
                }
                for (i = this.startingCol; i < as.length - 1; ++i) {
                    if (i <= 1) {
                        try {
                            this.offGrfx.setFont(this.symbolFont);
                        }
                        catch (NullPointerException unused6) {}
                    }
                    else {
                        try {
                            this.offGrfx.setFont(this.dataFont);
                        }
                        catch (NullPointerException ex2) {}
                    }
                    if (i == 3) {
                        final int arrowheight = 4;
                        if (as[3].charAt(0) == '+') {
                            final int[] x = new int[3];
                            final int[] y = { arrowy - 2 * arrowheight, arrowy, 0 };
                            y[2] = y[1];
                            x[0] = this.newxoffset[j] + 3 - arrowheight;
                            x[1] = this.newxoffset[j] + 3 - 2 * arrowheight;
                            x[2] = this.newxoffset[j] + 3;
                            this.offGrfx.setColor(this.qup);
                            this.offGrfx.fillPolygon(x, y, 3);
                            this.offGrfx.setColor(this.changecolorup);
                        }
                        if (as[3].charAt(0) == '-') {
                            final int[] x = new int[3];
                            final int[] y = { arrowy, arrowy - 2 * arrowheight, 0 };
                            y[2] = y[1];
                            x[0] = this.newxoffset[j] - arrowheight - 2;
                            x[1] = this.newxoffset[j] - 2 * arrowheight - 2;
                            x[2] = this.newxoffset[j] - 2;
                            this.offGrfx.setColor(this.qdown);
                            this.offGrfx.fillPolygon(x, y, 3);
                            this.offGrfx.setColor(this.changecolordown);
                        }
                    }
                    if (i != 3) {
                        this.offGrfx.setColor(this.fg);
                    }
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
                            this.offGrfx.drawImage(this.rt, this.newxoffset[j + 1] - 21, this.ypos - 10, this);
                        }
                        else if (this.eod != null && (as[5].equalsIgnoreCase("EOD") || as[5].equalsIgnoreCase("end-of-day"))) {
                            this.offGrfx.drawImage(this.eod, this.newxoffset[j + 1] - 21, this.ypos - 10, this);
                        }
                        else if (this.pd != null && (as[5].equalsIgnoreCase("PD") || as[5].equalsIgnoreCase("previous-day"))) {
                            this.offGrfx.drawImage(this.pd, this.newxoffset[j + 1] - 21, this.ypos - 10, this);
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
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public Vector getData() {
        return this.getFreshData();
    }
    
    private Vector getFreshData() {
        final Vector dV = new Vector();
        this.qg.getQuoteFromServer();
        final Hashtable ht = this.qg.getQuotes();
        final Enumeration enu = this.symbolVector.elements();
        int j = 0;
        if (ht != null) {
            while (enu.hasMoreElements() && j < 52) {
                final String as = enu.nextElement();
                String[] data = ht.get(as);
                if (data == null) {
                    data = new String[] { as, as, "N/A", "N/A", "N/A", "N/A" };
                }
                dV.addElement(data);
                ++j;
            }
            final int nodata = this.symbolVector.size();
            if (nodata < 6) {
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
    
    public void refreshData() {
        this.offImage = null;
        this.quoteList = null;
        this.repaint();
        this.stop();
        this.start();
    }
    
    public void getQuotes() {
        this.discounter = 1;
        final Vector currQuotes = this.getFreshData();
        if (currQuotes != null && currQuotes != this.quoteList && currQuotes.size() > 0) {
            if (this.disclaimer) {
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
                        this.xoffset[k] = tmpFm.stringWidth(as[i].trim());
                        if (as[5] != null && !as[5].equalsIgnoreCase("DELAYED") && !as[5].equalsIgnoreCase("DL")) {
                            final int[] xoffset = this.xoffset;
                            final int n = k - 1;
                            xoffset[n] += 25;
                        }
                    }
                    else if (as[i].charAt(0) == '-' || as[i].charAt(0) == '+') {
                        if (as[i].charAt(0) == '-') {
                            this.xoffset[k] = tmpFm.charWidth('-') + tmpFm.stringWidth(as[i].substring(1));
                        }
                        else if (as[i].charAt(0) == '+') {
                            this.xoffset[k] = tmpFm.charWidth('+') + tmpFm.stringWidth(as[i].substring(1));
                        }
                    }
                    else {
                        this.xoffset[k] = tmpFm.stringWidth(as[i]);
                    }
                    if (k == 0) {
                        final int[] xoffset2 = this.xoffset;
                        final int n2 = k;
                        xoffset2[n2] += 15;
                    }
                    if (this.maxwidth < this.xoffset[k]) {
                        this.maxwidth = this.xoffset[k];
                    }
                    ++k;
                }
                final int[] xoffset3 = this.xoffset;
                final int n3 = k - 1;
                xoffset3[n3] += 45;
            }
            this.newxoffset[1] = this.newxoffset[0] + this.xoffset[0];
            for (int j = 2; j < this.xoffset.length; ++j) {
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
        for (int counter = 0; this.quoteList == null && counter < 5; ++counter) {
            this.getQuotes();
            ++counter;
            try {
                Thread.sleep(2000L);
            }
            catch (Exception ex) {}
        }
        if (this.quoteList == null) {
            this.aTh = null;
        }
        while (this.aTh != null) {
            long time = System.currentTimeMillis();
            try {
                time += this.delay;
                Thread.sleep(Math.max(0L, time - System.currentTimeMillis()));
            }
            catch (InterruptedException ex2) {}
            if (this.quoteList != null) {
                if (this.disclaimer) {
                    refreshcount += this.delay;
                    if (refreshcount > 20000) {
                        refreshcount = 0;
                        ++this.discounter;
                        if (this.discounter > 1) {
                            this.discounter = 0;
                        }
                        this.disdata[2] = this.middle[this.discounter];
                    }
                }
                if (this.direction.equals("LEFT")) {
                    for (int i = 0; i < this.newxoffset.length; ++i) {
                        final int[] newxoffset = this.newxoffset;
                        final int n = i;
                        --newxoffset[n];
                        if (this.newxoffset[i] < -(this.maxwidth + this.xoffset[i] + 45)) {
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
                        if (this.newxoffset[i] > this.getSize().width + this.xoffset[i] + 45) {
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
            else if (this.quoteList == null) {
                this.aTh = null;
            }
            this.drawTicker();
            this.repaint();
        }
        this.aTh = null;
    }
    
    public void updateData() {
        this.quoteList = null;
        this.drawTicker();
        this.qg.getQuoteFromServer();
        this.repaint();
    }
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
        this.setCursor(new Cursor(12));
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        this.mouseinside = true;
        this.drawTicker();
        this.repaint();
        this.stopThread();
    }
    
    public void mouseExited(final MouseEvent e) {
        this.setCursor(new Cursor(0));
        this.mouseX = 0;
        this.mouseY = 0;
        this.mouseinside = false;
        this.drawTicker();
        this.repaint();
        this.startThread();
    }
    
    public void mousePressed(final MouseEvent e) {
    }
    
    public void mouseReleased(final MouseEvent e) {
        final String[] sym = this.quoteList.elementAt(this.currentindex);
        if (sym[0] == null || sym[0].trim().equals("") || sym[0].equals("n/a") || sym[0].trim().indexOf("*") >= 0) {
            this.showbr.showBrowser("http://www.quotemedia.com", "_self");
        }
        else {
            this.showbr.showSymbolDetail(sym[0]);
        }
    }
    
    public void mouseDragged(final MouseEvent e) {
    }
    
    public void mouseMoved(final MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        this.drawTicker();
        this.repaint();
    }
    
    public void updateMessage(final String msg) {
        this.quoteList = null;
        this.msg = msg;
        this.repaint();
    }
}
