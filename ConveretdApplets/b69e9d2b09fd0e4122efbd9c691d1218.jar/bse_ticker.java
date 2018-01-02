import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Enumeration;
import java.net.URL;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Font;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class bse_ticker extends Applet implements Runnable
{
    private static final long serialVersionUID = 1L;
    private String strWindowOpenType;
    private String strAllParam;
    private Font fntDialog;
    private int intFldFor;
    private String strToAdd;
    private String strIndexOf;
    private String strDataURL;
    public Color i;
    public Color _fldbyte;
    public Color _fldif;
    public Color q;
    public Color b;
    public Color strWaterColor;
    public Color _fldvoid;
    public Color _fldcase;
    public Color _fldgoto;
    public Color m;
    public String[] a;
    public int d;
    public int _flddo;
    public int j;
    public int _fldnull;
    public String currentType;
    MediaTracker objMediaTrack;
    c[] c;
    boolean blnState;
    boolean e;
    b objBObject;
    a objAObject;
    Thread objTimerThread;
    Thread objDataTransThread;
    Thread objTickerThread;
    c objCObject;
    c k;
    boolean g;
    
    public bse_ticker() {
        this.blnState = false;
        this.e = false;
    }
    
    public void a(final Exception ex) {
        System.out.println("Exception :" + ex.getMessage() + ":" + ex.getClass());
        ex.printStackTrace();
        this.showStatus(ex.getMessage());
    }
    
    Color a(final String s, final Color color) {
        final String a = this.a(s);
        int n3;
        int n2;
        int n = n2 = (n3 = -1);
        if (a != null) {
            if (a.startsWith("#")) {
                if (a.length() == 7) {
                    n2 = Integer.parseInt(a.substring(1, 3), 16);
                    n = Integer.parseInt(a.substring(3, 5), 16);
                    n3 = Integer.parseInt(a.substring(5), 16);
                }
            }
            else {
                final int index = a.indexOf(44);
                final int lastIndex = a.lastIndexOf(44);
                if (index > 0 && lastIndex > 0 && index != lastIndex) {
                    n2 = Integer.parseInt(a.substring(0, index));
                    n = Integer.parseInt(a.substring(index + 1, lastIndex));
                    n3 = Integer.parseInt(a.substring(lastIndex + 1));
                }
            }
        }
        return (n2 < 0 || n2 > 255 || n < 0 || n > 255 || n3 < 0 || n3 > 255) ? color : new Color(n2, n, n3);
    }
    
    String a(final String s) {
        String parameter = new String();
        try {
            parameter = this.getParameter(s);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        return parameter;
    }
    
    public URL a(final int n) {
        final int n2 = this.objCObject._fldtry + this.objBObject.q._fldif.width + this.c[6]._fldif.width;
        final int n3 = n2 + this.objCObject._fldfor;
        if (n >= n2 && n <= n3 && n > this.objBObject.q._fldif.width) {
            return this.objCObject.a;
        }
        return null;
    }
    
    public void destroy() {
        this.objTickerThread = null;
        this.objTimerThread = null;
        this.objDataTransThread = null;
        System.gc();
        System.runFinalization();
    }
    
    void a() {
        final Enumeration<c> elements = this.objBObject.vecScripData.elements();
        while (elements.hasMoreElements()) {
            final c c = elements.nextElement();
            final int n = c._fldtry + this.objBObject.q._fldif.width;
            final int n2 = n + c._fldfor;
            if (this.j >= n && this.j <= n2 && this._fldnull <= 18 && this._fldnull >= 0 && this.j > this.objBObject.q._fldif.width + this.objBObject.r && this.j < this.intFldFor) {
                final Graphics graphics = c._flddo.getGraphics();
                if (c._fldint == 1) {
                    graphics.setColor(this.b);
                }
                else {
                    graphics.setColor(this.q);
                }
                if (this.getCurrentType().equals("1") || this.getCurrentType().equals("2")) {
                    continue;
                }
                graphics.drawLine(0, 18, c._fldfor, 18);
            }
            else {
                final Graphics graphics2 = c._flddo.getGraphics();
                graphics2.setColor(this._fldgoto);
                if (this.getCurrentType().equals("1") || this.getCurrentType().equals("1")) {
                    continue;
                }
                graphics2.drawLine(0, 18, c._fldfor, 18);
            }
        }
    }
    
    public boolean _mthif() {
        return this.strAllParam.trim().equalsIgnoreCase("true");
    }
    
    public void init() {
        final boolean d = false;
        this._fldnull = (d ? 1 : 0);
        this.j = (d ? 1 : 0);
        this.d = (d ? 1 : 0);
        this._flddo = 0;
        if (this.getParameter("All") == null) {
            this.strAllParam = "true";
        }
        else {
            this.strAllParam = this.getParameter("All");
        }
        this.strDataURL = this.a("dataurl");
        this.strIndexOf = this.a("INDEXOF");
        this.i = this.a("UPARROWCOLOR", Color.blue);
        this._fldbyte = this.a("DOWNARROWCOLOR", Color.red);
        this._fldif = this.a("NULLARROWCOLOR", Color.black);
        this.q = this.a("FGCOLOR", new Color(222, 255, 0));
        this.b = this.a("FGCOLOR1", new Color(22, 222, 222));
        this.strWaterColor = this.a("watercolor", new Color(222, 255, 0));
        this._fldvoid = this.a("FGCOLOR2", new Color(222, 22, 22));
        this._fldgoto = this.a("BGCOLOR", new Color(100, 151, 161));
        this._fldcase = this.a("FGCOLORSENSEX", new Color(222, 255, 255));
        this.m = this.a("BGCOLORSENSEX", new Color(35, 82, 119));
        (this.a = new String[4])[0] = "1";
        this.a[1] = "2";
        this.a[2] = "3";
        this.a[3] = "4";
        this.strWindowOpenType = this.a("WINDOW");
        if (this.strWindowOpenType == null) {
            this.strWindowOpenType = "_self";
        }
        else {
            this.strWindowOpenType = this.a("WINDOW");
        }
        try {
            this.d = Integer.parseInt(this.getParameter("DEFAULT_TICKER"));
            this._flddo = Integer.parseInt(this.getParameter("thread_sleeptime"));
        }
        catch (NumberFormatException ex) {
            this.a(ex);
        }
        this.setFont(this.fntDialog = new Font("DIALOG", 0, 12));
        this.setBackground(Color.black);
        this.setForeground(Color.black);
        this.objMediaTrack = new MediaTracker(this);
        this.c = new c[24];
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = new c();
        }
        this.c[0]._flddo = this.getImage(this.getCodeBase(), "images/right.gif");
        this.c[1]._flddo = this.getImage(this.getCodeBase(), "images/left.gif");
        this.c[2]._flddo = this.getImage(this.getCodeBase(), "images/pause.gif");
        this.c[3]._flddo = this.getImage(this.getCodeBase(), "images/resume.gif");
        this.c[4]._flddo = this.getImage(this.getCodeBase(), "images/fast.gif");
        this.c[5]._flddo = this.getImage(this.getCodeBase(), "images/slow.gif");
        this.c[6]._flddo = this.getImage(this.getCodeBase(), "images/equ_on.gif");
        this.c[7]._flddo = this.getImage(this.getCodeBase(), "images/equ_off.gif");
        this.c[14]._flddo = this.getImage(this.getCodeBase(), "images/equ_tip.gif");
        this.c[15]._flddo = this.getImage(this.getCodeBase(), "images/ind_tip.gif");
        this.c[16]._flddo = this.getImage(this.getCodeBase(), "images/derf_tip.gif");
        this.c[17]._flddo = this.getImage(this.getCodeBase(), "images/dero_tip.gif");
        this.c[18]._flddo = this.getImage(this.getCodeBase(), "images/uparrow.gif");
        this.c[19]._flddo = this.getImage(this.getCodeBase(), "images/downarrow.gif");
        this.c[20]._flddo = this.getImage(this.getCodeBase(), "images/nullarrow.gif");
        for (int j = 0; j < 8; ++j) {
            this.objMediaTrack.addImage(this.c[j]._flddo, j);
        }
        for (int k = 14; k < 21; ++k) {
            this.objMediaTrack.addImage(this.c[k]._flddo, k);
        }
        try {
            this.objMediaTrack.waitForAll();
        }
        catch (InterruptedException ex2) {
            this.a(ex2);
        }
        if (this.objMediaTrack.isErrorAny()) {
            this.showStatus("Error fetching control image(s)");
        }
        else {
            this.blnState = true;
        }
        for (int l = 8; l < 14; ++l) {
            if (this.blnState) {
                if (l % 2 == 0) {
                    this.c[l]._flddo = this.c[6]._flddo;
                }
                else {
                    this.c[l]._flddo = this.c[7]._flddo;
                }
            }
        }
        this.c[21]._flddo = this.c[18]._flddo;
        this.c[22]._flddo = this.c[19]._flddo;
        this.c[23]._flddo = this.c[20]._flddo;
        for (int n = 0; n < this.c.length; ++n) {
            if (n != 6 && n != 7 && n != 8 && n != 9 && n != 9 && n != 10 && n != 11 && n != 12 && n != 13 && n != 14) {
                this.c[n]._fldif = new Rectangle(new Dimension(this.c[n]._flddo.getWidth(this), this.c[n]._flddo.getHeight(this)));
            }
            else if (n == 6 || n == 7) {
                this.c[n]._fldif = new Rectangle(new Dimension(this.c[n]._flddo.getWidth(this), this.c[n]._flddo.getHeight(this) + 2));
            }
            else {
                this.c[n]._fldif = new Rectangle(new Dimension(this.c[n]._flddo.getWidth(this), this.c[n]._flddo.getHeight(this) + 1));
            }
        }
        this.c[0]._fldif.move(this.size().width - this.c[0]._fldif.width, 0);
        this.c[1]._fldif.move(this.size().width - this.c[0]._fldif.width, 0);
        this.c[2]._fldif.move(this.size().width - this.c[0]._fldif.width, this.c[0]._fldif.height);
        this.c[3]._fldif.move(this.size().width - this.c[0]._fldif.width, this.c[0]._fldif.height);
        this.c[4]._fldif.move(this.size().width - this.c[0]._fldif.width, this.c[0]._fldif.height + this.c[2]._fldif.height);
        this.c[5]._fldif.move(this.size().width - this.c[0]._fldif.width, this.c[0]._fldif.height + this.c[2]._fldif.height);
        this.c[6]._fldif.move(0, 1);
        this.c[7]._fldif.move(0, 1);
        this.c[8]._fldif.move(0, this.c[6]._fldif.height);
        this.c[9]._fldif.move(0, this.c[6]._fldif.height);
        this.c[10]._fldif.move(0, this.c[6]._fldif.height + this.c[8]._fldif.height);
        this.c[11]._fldif.move(0, this.c[6]._fldif.height + this.c[8]._fldif.height);
        this.c[12]._fldif.move(0, this.c[6]._fldif.height + this.c[8]._fldif.height + this.c[10]._fldif.height);
        this.c[13]._fldif.move(0, this.c[6]._fldif.height + this.c[8]._fldif.height + this.c[10]._fldif.height);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.objBObject._fldcase = false;
        if (this.blnState) {
            if (this.c[1]._fldif.inside(n, n2)) {
                switch (this.objBObject._fldtry) {
                    case 1: {
                        this.objBObject._fldtry = 2;
                        break;
                    }
                    case 2: {
                        this.objBObject._fldtry = 1;
                        break;
                    }
                }
                this.repaint(this.c[0]._fldif.x, this.c[0]._fldif.y, this.c[0]._fldif.width, this.c[0]._fldif.height);
                return true;
            }
            if (this.c[5]._fldif.inside(n, n2)) {
                switch (this.objBObject.f) {
                    case 1: {
                        this.objBObject.f = 2;
                        this.objBObject.u = 20;
                        break;
                    }
                    case 2: {
                        this.objBObject.f = 1;
                        this.objBObject.u = 50;
                        break;
                    }
                }
                this.repaint(this.c[4]._fldif.x, this.c[4]._fldif.y, this.c[4]._fldif.width, this.c[4]._fldif.height);
                return true;
            }
            if (this.c[2]._fldif.inside(n, n2)) {
                if (this.objBObject._mthint()) {
                    this.objBObject._mthtry();
                }
                else {
                    this.objBObject._mthnew();
                }
                this.repaint(this.c[2]._fldif.x, this.c[2]._fldif.y, this.c[2]._fldif.width, this.c[2]._fldif.height);
                return true;
            }
            if (this._mthif()) {
                if (this.c[8]._fldif.inside(n, n2)) {
                    this.d = 1;
                }
                else if (this.c[10]._fldif.inside(n, n2)) {
                    this.d = 2;
                }
                else if (this.c[12]._fldif.inside(n, n2)) {
                    this.d = 3;
                }
                else if (this.c[6]._fldif.inside(n, n2)) {
                    this.d = 0;
                }
                this.objBObject.p = 0;
                this._mthif(this.d);
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        final boolean b = false;
        this._fldnull = (b ? 1 : 0);
        this.j = (b ? 1 : 0);
        this.objBObject.d = 0;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int j, final int fldnull) {
        this.j = j;
        this._fldnull = fldnull;
        if (j > 0 && j < this.objBObject.r && this._mthif()) {
            if (fldnull > 0 && fldnull < this.c[6]._fldif.height) {
                this.objBObject.d = 1;
            }
            else if (fldnull > this.c[6]._fldif.height && fldnull < this.c[6]._fldif.height + this.c[8]._fldif.height) {
                this.objBObject.d = 2;
            }
            else if (fldnull > this.c[6]._fldif.height + this.c[8]._fldif.height && fldnull < this.c[6]._fldif.height + this.c[8]._fldif.height + this.c[10]._fldif.height) {
                this.objBObject.d = 3;
            }
            else if (fldnull > this.c[6]._fldif.height + this.c[8]._fldif.height + this.c[10]._fldif.height && fldnull < this.c[6]._fldif.height + this.c[8]._fldif.height + this.c[10]._fldif.height + this.c[12]._fldif.height) {
                this.objBObject.d = 4;
            }
        }
        else {
            this.showStatus("BSE ticker running");
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        URL url = null;
        this.repaint(this.c[3]._fldif.x, this.c[3]._fldif.y, this.c[3]._fldif.width, this.c[3]._fldif.height);
        if (this.objBObject._fldcase) {
            this.objBObject._fldcase = false;
            return true;
        }
        if (this.objBObject.contains(n, n2)) {
            final Enumeration<c> elements = (Enumeration<c>)this.objBObject.vecScripData.elements();
            while (elements.hasMoreElements()) {
                this.objCObject = elements.nextElement();
                final URL a = this.a(this.j);
                final String value = String.valueOf(a);
                String s = this.objCObject.strScripCode;
                if (a != null) {
                    if (s.indexOf("++") != -1) {
                        final String trim = s.trim();
                        String nextToken = "";
                        String nextToken2 = "";
                        String nextToken3 = "";
                        String nextToken4 = "";
                        final StringTokenizer stringTokenizer = new StringTokenizer(trim, "++");
                        if (stringTokenizer.hasMoreTokens()) {
                            nextToken = stringTokenizer.nextToken();
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            nextToken2 = stringTokenizer.nextToken();
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            nextToken3 = stringTokenizer.nextToken();
                        }
                        if (stringTokenizer.hasMoreTokens()) {
                            nextToken4 = stringTokenizer.nextToken();
                        }
                        s = String.valueOf(nextToken) + "&sensid=" + nextToken2 + "&type=" + nextToken3 + "&graphpath=/applet/images/" + nextToken4;
                    }
                    try {
                        url = new URL(String.valueOf(value) + this.strToAdd + s);
                    }
                    catch (MalformedURLException ex) {}
                    this.getAppletContext().showDocument(url, this.strWindowOpenType);
                    break;
                }
            }
        }
        return false;
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, 125, this.size().height);
        if (this.blnState) {
            if (this.objBObject._fldtry == 1) {
                graphics.drawImage(this.c[0]._flddo, this.size().width - this.c[0]._fldif.width, 0, this);
            }
            if (this.objBObject._fldtry == 2) {
                graphics.drawImage(this.c[1]._flddo, this.size().width - this.c[0]._fldif.width, 0, this);
            }
            if (this.objBObject._mthint()) {
                graphics.drawImage(this.c[3]._flddo, this.size().width - this.c[0]._fldif.width, this.c[0]._fldif.height, this);
            }
            else {
                graphics.drawImage(this.c[2]._flddo, this.size().width - this.c[0]._fldif.width, this.c[0]._fldif.height, this);
            }
            if (this.objBObject.f == 1) {
                graphics.drawImage(this.c[5]._flddo, this.size().width - this.c[0]._fldif.width, this.c[0]._fldif.height + this.c[2]._fldif.height, this);
            }
            else {
                graphics.drawImage(this.c[4]._flddo, this.size().width - this.c[0]._fldif.width, this.c[0]._fldif.height + this.c[2]._fldif.height, this);
            }
            this.intFldFor = this.size().width - this.c[0]._fldif.width;
            this.objBObject.a(this.objBObject.getGraphics());
        }
    }
    
    synchronized void _mthif(final int n) {
        if (this.objDataTransThread != null && this.objDataTransThread.isAlive()) {
            this.objDataTransThread.stop();
        }
        this.objAObject = new a(this, this.objBObject, this.a[n]);
        this.setCurrentType(this.a[n]);
        this.objAObject.a(this.fntDialog);
        (this.objDataTransThread = new Thread(this.objAObject, "DataTransfer Thread")).start();
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this._flddo);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.gc();
            System.runFinalization();
            this.objAObject._mthcase();
        }
    }
    
    public void start() {
        try {
            (this.objTimerThread = new Thread(this, "Timer Thread")).setDaemon(true);
            this.objBObject = new b(this);
            this.objTickerThread = new Thread(this.objBObject, "Ticker Thread");
            this.objBObject.setBackground(Color.lightGray);
            this.objBObject.reshape(0, 0, this.size().width - this.c[0]._fldif.width, this.getGraphics().getFontMetrics().getHeight() * 3);
            this.objBObject._mthif();
            this.add(this.objBObject);
            this.objTickerThread.start();
            this.update(this.getGraphics());
            this.objTimerThread.start();
            this._mthif(this.d);
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
            this.a(ex);
        }
    }
    
    public void stop() {
        this.objDataTransThread.stop();
        this.objTickerThread.stop();
        this.objTimerThread.stop();
        super.stop();
    }
    
    public void setStoAdd(final String strToAdd) {
        this.strToAdd = strToAdd;
    }
    
    public String getStrIndexOf() {
        return this.strIndexOf;
    }
    
    public String getDataURL() {
        return this.strDataURL;
    }
    
    public void setDataURL(final String strDataURL) {
        this.strDataURL = strDataURL;
    }
    
    public String getCurrentType() {
        return this.currentType;
    }
    
    public void setCurrentType(final String currentType) {
        this.currentType = currentType;
    }
}
