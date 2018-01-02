import java.util.Date;
import java.net.URLConnection;
import java.io.IOException;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import netscape.javascript.JSObject;
import java.util.Enumeration;
import java.io.BufferedInputStream;
import java.util.Vector;
import java.net.URL;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ViewerApplet extends Applet
{
    long lastMod2;
    public Color ZoomBoothColor;
    URL base;
    public boolean UnrentedBoothsHighlighted;
    public static boolean DoneLoading;
    public static String appletContextName;
    public int BoothCount;
    public double version;
    public static String ExhibitorList;
    public boolean zoomBoothHighlighted;
    public static long LastModifiedDate;
    long lastMod1;
    URL drawingURL;
    URL drawing1;
    Vector widgets;
    public boolean IsBlackAndWhite;
    public static final boolean debug = false;
    BufferedInputStream inS;
    public boolean FavoriteBoothsHighlighted;
    public String BoothNums;
    String a;
    public static String StoredViews;
    public String FavoriteExhibitor;
    C09 view;
    public C45 drawingPanel;
    public static int ExhibitorCount;
    String format;
    public boolean RentedBoothsHighlighted;
    URL drawing2;
    long lastStartEnded;
    public static String BoothNumbers;
    
    public void HighlightRentedBooths() {
        if (this.RentedBoothsHighlighted) {
            this.RentedBoothsHighlighted = false;
        }
        else {
            this.RentedBoothsHighlighted = true;
            this.FavoriteBoothsHighlighted = false;
        }
        this.drawingPanel.B.repaint();
    }
    
    public void ZoomBooth(final String s) {
        if (s != null && !s.equals("")) {
            this.zoomBoothHighlighted = true;
            final Enumeration<C28> elements = this.widgets.elements();
            while (elements.hasMoreElements()) {
                final C28 c28 = elements.nextElement();
                if (c28.e().o) {
                    c28.e().o = false;
                }
            }
            this.drawingPanel.K.m(s);
            this.drawingPanel.B.repaint();
        }
    }
    
    public void zoomOut() {
        this.drawingPanel.B.q();
    }
    
    public void Copyright() {
        JSObject.getWindow(this).eval("GoVRWebsite()");
    }
    
    public void LoadList(final boolean b) {
        JSObject.getWindow(this).eval("InitJS(" + b + ")");
    }
    
    public void zoomAll() {
        this.drawingPanel.B.i();
    }
    
    static {
        ViewerApplet.DoneLoading = false;
        ViewerApplet.BoothNumbers = "";
        ViewerApplet.ExhibitorList = "";
        ViewerApplet.StoredViews = "";
    }
    
    public void HighlightFavoriteBoothsMenuOption() {
        JSObject.getWindow(this).eval("HighlightFavorites()");
    }
    
    public void AddBoothToFavorites() {
        JSObject.getWindow(this).eval("AddBoothToFavorites()");
    }
    
    public void refresh() {
        this.drawingPanel.g(null);
    }
    
    public int getExhibitorCount() {
        return ViewerApplet.ExhibitorCount;
    }
    
    public void EnableZoomOnClick() {
        this.drawingPanel.K.z = true;
    }
    
    public void HighlightFavoriteBooths(String substring) {
        if (substring != null && substring != "") {
            int n = 0;
            int n2 = 0;
            final Enumeration<C28> elements = (Enumeration<C28>)this.widgets.elements();
            for (int i = 0; i < substring.length(); ++i) {
                if (substring.charAt(i) == ' ') {
                    ++n;
                }
            }
            final String[] array = new String[++n];
            while (substring.indexOf(" ") > -1) {
                array[n2] = substring.substring(0, substring.indexOf(" "));
                substring = substring.substring(substring.indexOf(" ") + 1);
                ++n2;
            }
            array[n2] = substring;
            while (elements.hasMoreElements()) {
                final C28 c28 = elements.nextElement();
                if (c28.e().q) {
                    c28.e().q = false;
                }
            }
            if (this.FavoriteBoothsHighlighted) {
                this.FavoriteBoothsHighlighted = false;
            }
            else {
                this.FavoriteBoothsHighlighted = true;
                this.UnrentedBoothsHighlighted = false;
                this.RentedBoothsHighlighted = false;
                final Enumeration<C28> elements2 = (Enumeration<C28>)this.widgets.elements();
                while (elements2.hasMoreElements()) {
                    final C28 c29 = elements2.nextElement();
                    final String h = c29.e().h();
                    for (int j = 0; j < n; ++j) {
                        if (h.equals(array[j])) {
                            c29.e().q = true;
                        }
                    }
                }
            }
            this.drawingPanel.B.repaint();
        }
    }
    
    public String GetFavoriteExhibitor() {
        return this.FavoriteExhibitor;
    }
    
    public void DisableZoomOnClick() {
        this.drawingPanel.K.z = false;
    }
    
    public void EnableLinks() {
        this.drawingPanel.K.t = true;
    }
    
    public void DisableLinks() {
        this.drawingPanel.K.t = false;
    }
    
    public void ZoomView(final String s) {
        this.drawingPanel.H.a(s);
    }
    
    public String LoadStoredViews() {
        ViewerApplet.StoredViews = "";
        for (int i = 0; i < this.drawingPanel.H.f.getItemCount(); ++i) {
            if (i != this.drawingPanel.H.f.getItemCount() - 1) {
                ViewerApplet.StoredViews = ViewerApplet.StoredViews + this.drawingPanel.H.f.getItem(i) + "|";
            }
            else {
                ViewerApplet.StoredViews += this.drawingPanel.H.f.getItem(i);
            }
        }
        return ViewerApplet.StoredViews;
    }
    
    public void zoomIn() {
        this.drawingPanel.B.f();
    }
    
    public void loadedDrawing(final URL url, final long n) {
        URL url2 = null;
        if (url.toString().indexOf("?") != -1) {
            try {
                url2 = new URL(url.toString().substring(0, url.toString().indexOf("?")));
            }
            catch (MalformedURLException ex) {
                System.out.println("Err $0002" + ex);
            }
        }
        if (url2.equals(this.drawing1)) {
            this.lastMod1 = n;
        }
        else {
            this.lastMod2 = n;
        }
    }
    
    public void start() {
        if (!C12.f()) {
            try {
                final URL codeBase = this.getCodeBase();
                if (codeBase != null && !codeBase.getProtocol().toLowerCase().equals("http")) {
                    System.out.println("Stand alone version");
                }
                else if (codeBase == null || !C12.g(codeBase.getHost(), this)) {
                    System.out.println("This applet is not being used on the correct host.");
                    return;
                }
                System.out.println("HOST VERIFIED");
            }
            catch (Throwable t2) {
                System.out.println("Failed to verify host.");
            }
        }
        this.repaint();
        if (System.currentTimeMillis() - this.lastStartEnded < 2000L) {
            System.out.println("Ignored start");
            return;
        }
        this.inS = null;
        ViewerApplet.appletContextName = (this.getAppletContext() + "").toLowerCase();
        try {
            this.a = this.getParameter("drawing");
            this.base = this.getDocumentBase();
            this.format = this.getParameter("format");
            URL drawingURL = null;
            if (this.a == null) {
                drawingURL = null;
            }
            else if (this.a.startsWith("http://")) {
                try {
                    drawingURL = new URL(this.a);
                    if (!drawingURL.getHost().toLowerCase().equals(this.getCodeBase().getHost().toLowerCase())) {
                        System.out.println("The drawing must reside on the same host as the viewer.");
                        return;
                    }
                }
                catch (MalformedURLException ex2) {
                    System.out.println("The URL for the drawing is not valid.");
                }
            }
            else if (this.a.startsWith("/")) {
                int port = this.base.getPort();
                if (port == -1) {
                    port = 80;
                }
                drawingURL = new URL(this.base.getProtocol(), this.base.getHost(), port, this.a);
            }
            else {
                drawingURL = new URL(this.base, this.a);
            }
            System.out.println("\nRETRIEVING VR2 FILE: " + drawingURL);
            if (drawingURL != null) {
                this.drawingURL = drawingURL;
                try {
                    try {
                        final URLConnection openConnection = drawingURL.openConnection();
                        openConnection.setAllowUserInteraction(true);
                        openConnection.connect();
                        this.inS = new BufferedInputStream(new C23(openConnection.getInputStream(), openConnection.getContentLength(), this));
                    }
                    catch (Throwable t3) {
                        final String host = this.base.getHost();
                        final String protocol = this.base.getProtocol();
                        final int port2 = this.base.getPort();
                        final String parent = new File(this.base.getFile()).getParent();
                        if (port2 == -1) {
                            drawingURL = new URL(protocol, host, parent);
                        }
                        else {
                            drawingURL = new URL(protocol, host, port2, parent);
                        }
                        drawingURL = new URL(drawingURL, this.a);
                        final URLConnection openConnection2 = drawingURL.openConnection();
                        openConnection2.setAllowUserInteraction(true);
                        openConnection2.connect();
                        this.inS = new BufferedInputStream(new C23(openConnection2.getInputStream(), openConnection2.getContentLength(), this));
                    }
                }
                catch (Throwable t) {
                    try {
                        final URL url = new URL(drawingURL.getProtocol(), this.getCodeBase().getHost(), drawingURL.getPort(), drawingURL.getFile());
                        System.out.println("Attempt #2 " + url);
                        try {
                            final URLConnection openConnection3 = url.openConnection();
                            openConnection3.setAllowUserInteraction(true);
                            openConnection3.connect();
                            this.inS = new BufferedInputStream(new C23(openConnection3.getInputStream(), openConnection3.getContentLength(), this));
                        }
                        catch (Throwable t4) {
                            final String host2 = this.base.getHost();
                            final String protocol2 = this.base.getProtocol();
                            final int port3 = this.base.getPort();
                            final String parent2 = new File(this.base.getFile()).getParent();
                            URL url2;
                            if (port3 == -1) {
                                url2 = new URL(protocol2, host2, parent2);
                            }
                            else {
                                url2 = new URL(protocol2, host2, port3, parent2);
                            }
                            final URLConnection openConnection4 = new URL(url2, this.a).openConnection();
                            openConnection4.setAllowUserInteraction(true);
                            openConnection4.connect();
                            this.inS = new BufferedInputStream(new C23(openConnection4.getInputStream(), openConnection4.getContentLength(), this));
                        }
                    }
                    catch (Throwable t5) {
                        System.out.println("Failed to fetch the drawing from the server.");
                        System.out.println("error fetching drawing " + t);
                        return;
                    }
                }
            }
            this.setLayout(new BorderLayout());
            this.removeAll();
            this.add("Center", this.drawingPanel = new C45(this.inS, this.a, this.format, this, null));
            this.layout();
            this.invalidate();
            this.validate();
            this.repaint();
            this.repaint();
        }
        catch (IOException ex) {
            System.out.println(ex + " viewer caught exception");
            ex.printStackTrace();
            System.out.println("Viewer caught exception " + ex.getMessage());
        }
        finally {
            this.lastStartEnded = System.currentTimeMillis();
        }
    }
    
    public static boolean getAppletStatus() {
        return ViewerApplet.DoneLoading;
    }
    
    public void stop() {
        if (System.currentTimeMillis() - this.lastStartEnded < 2000L) {
            System.out.println("Ignored stop");
            return;
        }
        System.out.println("Stop");
        if (this.view != null) {
            this.view.b();
        }
        else if (this.drawingPanel != null) {
            this.drawingPanel.h();
        }
        this.view = null;
        this.drawingPanel = null;
        System.gc();
    }
    
    public String LoadExhibitorList() {
        ViewerApplet.ExhibitorList = "";
        ViewerApplet.ExhibitorCount = this.drawingPanel.y.getItemCount();
        for (int i = 0; i < ViewerApplet.ExhibitorCount; ++i) {
            if (i != ViewerApplet.ExhibitorCount - 1) {
                ViewerApplet.ExhibitorList = ViewerApplet.ExhibitorList + this.drawingPanel.y.getItem(i) + "|";
            }
            else {
                ViewerApplet.ExhibitorList += this.drawingPanel.y.getItem(i);
            }
        }
        return ViewerApplet.ExhibitorList;
    }
    
    public ViewerApplet() {
        this.FavoriteExhibitor = "";
        this.RentedBoothsHighlighted = false;
        this.UnrentedBoothsHighlighted = false;
        this.FavoriteBoothsHighlighted = false;
        this.zoomBoothHighlighted = false;
        this.IsBlackAndWhite = false;
        this.BoothCount = 0;
        this.BoothNums = "";
        this.version = -1.0;
        this.lastStartEnded = 0L;
    }
    
    public void OpenURL(final String s) {
        JSObject.getWindow(this).eval("OpenURL('" + s + "')");
    }
    
    public void zoomWindow() {
        final C49 b = this.drawingPanel.B;
        final C45 drawingPanel = this.drawingPanel;
        b.cl = 0;
    }
    
    public void Pan(final String s, final String s2) {
        this.drawingPanel.B.C(Integer.parseInt(s), Integer.parseInt(s2));
    }
    
    public void SetHighlightColor(final String s, final String s2, final String s3) {
        try {
            final int int1 = Integer.parseInt(s);
            final int int2 = Integer.parseInt(s2);
            final int int3 = Integer.parseInt(s3);
            if (this.version >= 1.2) {
                this.ZoomBoothColor = new Color(int1, int2, int3, 100);
            }
            else {
                this.ZoomBoothColor = new Color(int1, int2, int3);
            }
        }
        catch (Throwable t) {
            System.out.println("Error setting highlight color.");
        }
    }
    
    public void setSize(final String s, final String s2) {
        super.setSize(Integer.parseInt(s), Integer.parseInt(s2));
        if (this.RentedBoothsHighlighted || this.UnrentedBoothsHighlighted || this.FavoriteBoothsHighlighted || this.zoomBoothHighlighted) {
            this.drawingPanel.B.repaint();
        }
    }
    
    public String getParameter(final String s) {
        if (s.equals("name")) {
            return this.getAppletInfo();
        }
        if (s.equals("drawing") && super.getParameter("drawing2") != null) {
            try {
                final C25 c25 = new C25(this.getDocumentBase(), super.getParameter("drawing2"));
                final URLConnection openConnection = c25.g().openConnection();
                openConnection.setAllowUserInteraction(true);
                openConnection.setUseCaches(false);
                openConnection.setIfModifiedSince(0L);
                openConnection.connect();
                openConnection.getInputStream();
                this.lastMod2 = openConnection.getLastModified();
                final C25 c26 = new C25(this.getDocumentBase(), super.getParameter("drawing"));
                final URLConnection openConnection2 = c26.g().openConnection();
                openConnection2.setAllowUserInteraction(true);
                openConnection2.setUseCaches(false);
                openConnection2.setIfModifiedSince(0L);
                openConnection2.connect();
                openConnection2.getInputStream();
                this.lastMod1 = openConnection2.getLastModified();
                this.drawing1 = c26.g();
                this.drawing2 = c25.g();
                if (this.lastMod1 < this.lastMod2) {
                    return super.getParameter("drawing2");
                }
                return super.getParameter("drawing");
            }
            catch (Throwable t) {
                System.out.println("deny " + t);
            }
        }
        if (s != null && s.equals("origDrawingURL")) {
            if (this.drawing1 == null) {
                try {
                    this.drawing1 = new C25(this.getDocumentBase(), super.getParameter("drawing")).g();
                }
                catch (MalformedURLException ex) {
                    System.out.println("Failed to read drawing URL.");
                    System.out.println("err $$3344 " + ex);
                }
            }
            URL drawingURL = this.drawing1;
            long ifModifiedSince = this.lastMod1;
            if (this.lastMod1 > this.lastMod2 && this.drawing2 != null) {
                drawingURL = this.drawing2;
                ifModifiedSince = this.lastMod2;
            }
            try {
                if (drawingURL.toString().indexOf("?") == -1) {
                    drawingURL = new URL(drawingURL + "?rt=" + System.currentTimeMillis());
                }
                else {
                    drawingURL = new URL(drawingURL + "&rt=" + System.currentTimeMillis());
                }
            }
            catch (MalformedURLException ex2) {
                System.out.println("ERR $$%#656");
            }
            System.out.println(new Date(this.lastMod1) + " " + this.drawing1);
            try {
                final URLConnection openConnection3 = drawingURL.openConnection();
                openConnection3.setAllowUserInteraction(true);
                openConnection3.setUseCaches(false);
                openConnection3.setIfModifiedSince(ifModifiedSince);
                openConnection3.connect();
                openConnection3.getInputStream();
                final long lastModified = openConnection3.getLastModified();
                if (lastModified > ifModifiedSince) {
                    if (ifModifiedSince == this.lastMod1) {
                        this.lastMod1 = lastModified;
                    }
                    else {
                        this.lastMod2 = lastModified;
                    }
                    this.drawingURL = drawingURL;
                }
            }
            catch (Throwable t2) {
                System.out.println("Error #4343444 " + t2);
            }
            return this.drawingURL + "";
        }
        String parameter = null;
        if (super.getParameter(s) != null) {
            parameter = super.getParameter(s);
        }
        return parameter;
    }
    
    public String GetBoothNumbers() {
        return ViewerApplet.BoothNumbers;
    }
    
    public void SetWhiteBackground() {
        if (this.IsBlackAndWhite) {
            this.IsBlackAndWhite = false;
        }
        else {
            this.IsBlackAndWhite = true;
        }
        this.drawingPanel.B.r();
    }
    
    public void HighlightAvailableBooths() {
        if (this.UnrentedBoothsHighlighted) {
            this.UnrentedBoothsHighlighted = false;
        }
        else {
            this.UnrentedBoothsHighlighted = true;
            this.FavoriteBoothsHighlighted = false;
        }
        this.drawingPanel.B.repaint();
    }
}
