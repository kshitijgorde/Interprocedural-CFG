import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Cursor;
import java.util.zip.ZipEntry;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.io.BufferedInputStream;
import java.awt.Event;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.Component;
import java.util.StringTokenizer;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.RenderingHints;
import netscape.javascript.JSObject;
import java.util.Vector;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CgmViewApplet extends Applet implements Runnable, Visibility
{
    String appletVersion;
    Image offScrImage;
    Image offScreenBuffer;
    Thread UpdateThread;
    String filename;
    Hashtable cgmFiles;
    Layer[] Layers;
    Color BackColor;
    boolean keyActions;
    boolean dragged;
    boolean leftmouse;
    boolean ani;
    boolean splash;
    boolean inverseColor;
    int mouseX;
    int mouseY;
    int currX;
    int currY;
    double factor;
    double userFactor;
    Object antialias;
    Object rendering;
    double zoomFactor;
    int canvasWidth;
    int canvasHeight;
    int origWidth;
    int origHeight;
    int origX;
    int origY;
    int canvasX;
    int canvasY;
    int keyMod;
    int Width;
    int Height;
    String BaseControls;
    String Controls;
    URL ImageBase;
    URL SoundBase;
    URL FontBase;
    double clipX;
    double clipY;
    double clipW;
    double clipH;
    String AnimationEvent;
    int AnimationMouseX;
    int AnimationMouseY;
    int AnimationCurrX;
    int AnimationCurrY;
    int AnimationModifier;
    int AnimationMask;
    int ModifiersSet;
    boolean ModifierOnce;
    String AnimationClickPicture;
    String AnimationDragPicture;
    double AnimationFactorX;
    double AnimationFactorY;
    double AnimationOffX;
    double AnimationOffY;
    int ComponentFound;
    int ClickComponent;
    int DragComponent;
    RenderThread renderer;
    Hashtable zipFiles;
    MediaTracker tracker;
    LayerElement LastElementFound;
    int LayerFound;
    int ClickLayer;
    int DragLayer;
    Hashtable Fonts;
    Hashtable Sounds;
    Vector urls;
    int hotspot;
    boolean showHotspots;
    LayerElement mapElement;
    URL targetURL;
    boolean announce;
    int key;
    int kmod;
    String Searchkey;
    int SearchLayer;
    String JSmethod;
    String[] JSmsg;
    JSObject JSwin;
    
    public CgmViewApplet() {
        this.appletVersion = "2.0.0";
        this.offScrImage = null;
        this.offScreenBuffer = null;
        this.UpdateThread = null;
        this.cgmFiles = new Hashtable();
        this.Layers = new Layer[17];
        this.BackColor = null;
        this.keyActions = false;
        this.dragged = false;
        this.leftmouse = true;
        this.ani = false;
        this.splash = true;
        this.inverseColor = false;
        this.factor = 100.0;
        this.userFactor = 1.0;
        this.antialias = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
        this.rendering = RenderingHints.VALUE_RENDER_DEFAULT;
        this.zoomFactor = Math.sqrt(2.0);
        this.canvasX = 0;
        this.canvasY = 0;
        this.keyMod = 0;
        this.BaseControls = "Click/Drag=Zoom in, AltClick=Zoom out, AltDrag=Pan, AltDoubleClick=Reset, ShiftClick=Centre";
        this.Controls = "";
        this.ImageBase = null;
        this.SoundBase = null;
        this.FontBase = null;
        this.clipX = 0.0;
        this.clipY = 0.0;
        this.clipW = 1.0;
        this.clipH = 1.0;
        this.AnimationEvent = "";
        this.AnimationMask = 2;
        this.ModifiersSet = 0;
        this.ModifierOnce = true;
        this.AnimationClickPicture = null;
        this.AnimationDragPicture = null;
        this.AnimationFactorX = 1.0;
        this.AnimationFactorY = 1.0;
        this.AnimationOffX = 0.0;
        this.AnimationOffY = 0.0;
        this.ComponentFound = -1;
        this.ClickComponent = -1;
        this.DragComponent = -1;
        this.zipFiles = null;
        this.tracker = null;
        this.LastElementFound = null;
        this.LayerFound = -1;
        this.ClickLayer = -1;
        this.DragLayer = -1;
        this.Fonts = null;
        this.Sounds = null;
        this.urls = null;
        this.hotspot = -1;
        this.showHotspots = false;
        this.mapElement = null;
        this.targetURL = null;
        this.announce = false;
        this.key = 0;
        this.kmod = 0;
        this.Searchkey = null;
        this.SearchLayer = -1;
        this.JSmethod = "appletEvent";
        this.JSmsg = new String[3];
        this.JSwin = null;
        this.addComponentListener(new ComponentListener() {
            public void componentHidden(final ComponentEvent componentEvent) {
            }
            
            public void componentShown(final ComponentEvent componentEvent) {
            }
            
            public void componentMoved(final ComponentEvent componentEvent) {
            }
            
            public void componentResized(final ComponentEvent componentEvent) {
                if (CgmViewApplet.this.offScreenBuffer != null) {
                    CgmViewApplet.this.initCanvas();
                    CgmViewApplet.this.repaint();
                }
            }
        });
        this.addMouseListener(new MouseListener() {
            public void mouseReleased(final MouseEvent mouseEvent) {
                CgmViewApplet.this.mouseReleased(mouseEvent.getClickCount(), mouseEvent.getX(), mouseEvent.getY());
            }
            
            public void mousePressed(final MouseEvent mouseEvent) {
                CgmViewApplet.this.mousePressed(mouseEvent.getModifiers(), mouseEvent.getX(), mouseEvent.getY());
            }
            
            public void mouseExited(final MouseEvent mouseEvent) {
            }
            
            public void mouseEntered(final MouseEvent mouseEvent) {
                CgmViewApplet.this.requestFocus();
            }
            
            public void mouseClicked(final MouseEvent mouseEvent) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseMoved(final MouseEvent mouseEvent) {
                CgmViewApplet.this.mouseMoved(mouseEvent.getModifiers(), mouseEvent.getX(), mouseEvent.getY());
            }
            
            public void mouseDragged(final MouseEvent mouseEvent) {
                CgmViewApplet.this.mouseDragged(mouseEvent.getX(), mouseEvent.getY());
            }
        });
    }
    
    protected final void addFont(final String s) {
        String string = s;
        if (string.indexOf(".") < 0) {
            string = String.valueOf(string) + ".jhf";
        }
        final String fontname = this.fontname(string);
        if (this.Fonts == null) {
            this.Fonts = new Hashtable();
        }
        if (this.zipFiles == null || this.zipFiles.get(string.toUpperCase()) == null) {
            if (this.announce) {
                this.showStatus("Loading font..." + string);
            }
            this.Fonts.put(fontname, new HersheyFont(this.FontBase, string));
        }
    }
    
    public final void clip(final double n, final double n2, final double n3, final double n4) {
        this.clipX = n * 0.001;
        this.clipY = n2 * 0.001;
        this.clipW = n3 * 0.001;
        this.clipH = n4 * 0.001;
    }
    
    public final void clip(final int n, final double n2, final double n3, final double n4, final double n5) {
        final Layer layer = this.getLayer(n);
        layer.clipx = Math.round(n2 * 0.001);
        layer.clipy = Math.round(n3 * 0.001);
        layer.clipw = Math.round(n4 * 0.001);
        layer.cliph = Math.round(n5 * 0.001);
    }
    
    public void destroy() {
        if (this.UpdateThread != null && this.UpdateThread.isAlive()) {
            this.UpdateThread.stop();
        }
        this.UpdateThread = null;
    }
    
    private final int drawString(final Graphics graphics, final int n, final int n2, final String s, final int n3, final int n4, final Color color, final int n5) {
        final Font font = new Font("Arial", n3, n4);
        graphics.setFont(font);
        graphics.setColor(color);
        int n6 = n;
        final int stringWidth = graphics.getFontMetrics(font).stringWidth(s);
        switch (n5) {
            case 2: {
                n6 -= stringWidth;
                break;
            }
            case 3: {
                n6 -= stringWidth / 2;
                break;
            }
        }
        graphics.drawString(s, n6, n2);
        return n6 + stringWidth;
    }
    
    public final String findPicture(final int n, final double n2, final double n3) {
        if (this.isActive()) {
            final double n4 = this.origX - this.canvasX;
            final double n5 = this.origY - this.canvasY;
            final double n6 = this.factor * this.userFactor;
            this.LastElementFound = null;
            this.LayerFound = -1;
            for (int i = 15; i >= 0; --i) {
                if (n < 0 || i == n) {
                    final Layer layer = this.Layers[i];
                    if (layer != null) {
                        this.LastElementFound = layer.find(n4, n5, n6, n6, n2 / 1000.0 * this.factor, n3 / 1000.0 * this.factor);
                        if (this.LastElementFound != null) {
                            this.ComponentFound = this.LastElementFound.SearchResult;
                            this.LayerFound = i;
                            return this.LastElementFound.clonename;
                        }
                    }
                }
            }
        }
        this.ComponentFound = -1;
        return "";
    }
    
    public final String findPicture(final int n, final String s) {
        final double n2 = 1000.0 / this.factor;
        if (s.equals("Drag") || s.equals("Drop")) {
            if (this.AnimationDragPicture == null) {
                this.AnimationDragPicture = this.findPicture(n, this.AnimationCurrX * n2, this.AnimationCurrY * n2);
                this.DragComponent = this.ComponentFound;
                this.DragLayer = this.LayerFound;
            }
            this.ComponentFound = this.DragComponent;
            this.LayerFound = this.ComponentFound;
            return this.AnimationDragPicture;
        }
        if (s.equals("Move")) {
            return this.findPicture(n, this.AnimationCurrX * n2, this.AnimationCurrY * n2);
        }
        if (this.AnimationClickPicture == null) {
            this.AnimationClickPicture = this.findPicture(n, this.AnimationMouseX * n2, this.AnimationMouseY * n2);
            this.ClickComponent = this.ComponentFound;
            this.ClickLayer = this.LayerFound;
        }
        this.ComponentFound = this.ClickComponent;
        this.LayerFound = this.ClickLayer;
        return this.AnimationClickPicture;
    }
    
    public final String findPicture(final String s) {
        return this.findPicture(-1, s);
    }
    
    public final String findText(final int n, final String s) {
        if (s.length() > 0) {
            return this.findText(n, s, -1);
        }
        if (this.Searchkey == null) {
            this.ComponentFound = -1;
            this.LayerFound = -1;
            return "";
        }
        return this.findText(this.SearchLayer, this.Searchkey, -2);
    }
    
    private final String findText(final int searchLayer, final String searchkey, final int n) {
        this.LayerFound = -1;
        this.ComponentFound = -1;
        String clonename = "";
        if (this.isActive()) {
            int searchResult = n;
            this.LastElementFound = null;
            this.Searchkey = searchkey;
            this.SearchLayer = searchLayer;
            for (int i = 15; i >= 0; --i) {
                if (searchLayer < 0 || i == searchLayer) {
                    final Layer layer = this.Layers[i];
                    if (layer != null) {
                        this.LastElementFound = layer.find(searchkey, searchResult);
                        if (this.LastElementFound != null) {
                            this.ComponentFound = this.LastElementFound.SearchResult;
                            this.LayerFound = i;
                            clonename = this.LastElementFound.clonename;
                        }
                        searchResult = layer.SearchResult;
                    }
                }
            }
        }
        return clonename;
    }
    
    public final String findText(final String s) {
        return this.findText(-1, s);
    }
    
    final String fontname(String s) {
        int max = Math.max(Math.max(s.lastIndexOf(":"), s.lastIndexOf("/")), s.lastIndexOf("\\"));
        if (max >= 0) {
            s = s.substring(++max);
        }
        final int index;
        if ((index = s.indexOf(".")) >= 0) {
            s = s.substring(0, index);
        }
        return s.toUpperCase();
    }
    
    public final String getAppletInfo() {
        return "CgmViewApplet " + this.appletVersion + " \n" + "© 1996 Alexander Larsson (alla@lysator.liu.se) \n" + "© 1998-2010 Berthold Daum (berthold.daum@bdaum.de)";
    }
    
    public final double getCgmX(final int n, final String s) {
        return this.getElement(n, s).x * 1000.0;
    }
    
    public final double getCgmY(final int n, final String s) {
        return this.getElement(n, s).y * 1000.0;
    }
    
    public final int getComponent() {
        return this.ComponentFound;
    }
    
    public final double getComponentX(final int n, final String s, final int n2) {
        final LayerElement element = this.getElement(n, s);
        final CgmPrimitive component = element.cgm.findComponent(n2);
        if (component == null) {
            return -1.0;
        }
        return (element.x + component.x) * 1000.0;
    }
    
    public final double getComponentY(final int n, final String s, final int n2) {
        final LayerElement element = this.getElement(n, s);
        final CgmPrimitive component = element.cgm.findComponent(n2);
        if (component == null) {
            return -1.0;
        }
        return (element.y + component.y) * 1000.0;
    }
    
    private final LayerElement getElement(final int n, final String s) {
        final int index = s.indexOf("#");
        CgmContext cgmContext;
        String substring;
        if (index > 0) {
            cgmContext = this.readFile(s.substring(0, index));
            substring = s.substring(index + 1);
        }
        else {
            cgmContext = this.readFile(s);
            substring = "";
        }
        final Layer layer = this.getLayer(n);
        final LayerElement layerElement = new LayerElement(cgmContext, substring);
        final int index2 = layer.Images.indexOf(layerElement);
        if (index2 >= 0) {
            return (LayerElement)layer.Images.elementAt(index2);
        }
        layer.Images.addElement(layerElement);
        return layerElement;
    }
    
    public final int getKey() {
        final int key = this.key;
        if (this.key > 0) {
            this.key = 0;
        }
        return key;
    }
    
    public final int getKeyModifier() {
        return this.kmod & 0xF;
    }
    
    public final int getLayer() {
        return this.LayerFound;
    }
    
    private final Layer getLayer(final int n) {
        this.waitFor();
        if (this.Layers[n] == null) {
            this.Layers[n] = new Layer();
        }
        return this.Layers[n];
    }
    
    public final double getLayerX(final int n) {
        return this.getLayer(n).x * 1000.0;
    }
    
    public final double getLayerY(final int n) {
        return this.getLayer(n).y * 1000.0;
    }
    
    public final int getModifier() {
        return this.AnimationModifier;
    }
    
    public final double getSceneX() {
        return this.AnimationCurrX * 1000.0 / this.factor;
    }
    
    public final double getSceneY() {
        return this.AnimationCurrY * 1000.0 / this.factor;
    }
    
    private final AudioClip getSound(final String s) {
        String string = s;
        if (string.indexOf(".") < 0) {
            string = String.valueOf(string) + ".au";
        }
        if (this.Sounds == null) {
            this.Sounds = new Hashtable();
        }
        AudioClip audioClip = this.Sounds.get(string);
        if (audioClip == null) {
            if (this.announce) {
                this.showStatus("Loading sound..." + string);
            }
            audioClip = this.getAudioClip(this.SoundBase, string);
            this.Sounds.put(string, audioClip);
        }
        return audioClip;
    }
    
    public final String getVersion() {
        return this.appletVersion;
    }
    
    public final double getX(final int n) {
        return this.getLayerX(n);
    }
    
    public final double getX(final int n, final String s) {
        return this.getCgmX(n, s);
    }
    
    public final double getX(final int n, final String s, final int n2) {
        return this.getComponentX(n, s, n2);
    }
    
    public final double getY(final int n) {
        return this.getLayerY(n);
    }
    
    public final double getY(final int n, final String s) {
        return this.getCgmY(n, s);
    }
    
    public final double getY(final int n, final String s, final int n2) {
        return this.getComponentY(n, s, n2);
    }
    
    public final double getZoom() {
        return this.userFactor;
    }
    
    public final double getZoomFactor() {
        return this.zoomFactor;
    }
    
    public final void hide(final int n) {
        this.getLayer(n).visibility = 0;
    }
    
    public final void hide(final int n, final String s) {
        this.setAttr(n, s, 0);
    }
    
    public final void hide(final int n, final String s, final int n2) {
        this.setAttr(n, s, n2, 0);
    }
    
    public final String[][] getParameterInfo() {
        return new String[][] { { "nosplash", "boolean", "Do not display splash screen" }, { "controls", "string", "Text for status line" }, { "imagebase", "url", "Image directory" }, { "cgmArchive", "url", "Optional ZIP archive" }, { "filename", "url", "Image(s) to draw" }, { "fontbase", "url", "Font directory" }, { "hersheyFonts", "url", "Font(s) to use" }, { "soundbase", "url", "Sound directory" }, { "sound", "url", "Sound file(s) to preload" }, { "imagemap", "string", "Image map file plus targets" }, { "showHotspots", "boolean", "Shows selected hotspots" }, { "bgcolor", "color", "Default background color" }, { "inverse", "boolean", "Inverse colors" }, { "antialiasing", "string", "Antialiasing: on/off" }, { "rendering", "string", "Rendering quality: speed/quality" }, { "zoomFactor", "double", "Zoom step factor" }, { "keyActions", "boolean", "Enable keys for zoom and pan" }, { "eventHandler", "string", "JavaScript event handler" } };
    }
    
    public void init() {
        this.UpdateThread = new Thread(this, "Update-Thread");
        final Dimension size = this.getSize();
        this.Width = size.width;
        this.Height = size.height;
        this.factor = Math.min(this.Width, this.Height);
        this.splash = (this.getParameter("nosplash") == null);
        final String parameter = this.getParameter("controls");
        if (parameter != null) {
            this.BaseControls = parameter;
        }
        this.Controls = this.BaseControls;
        String s = this.getParameter("cgmArchive");
        String s2 = this.getParameter("imagebase");
        if (s2 != null && s2.toLowerCase().endsWith(".zip")) {
            final int n = s2.indexOf("/") + 1;
            s = s2.substring(n);
            s2 = s2.substring(0, n);
        }
        this.ImageBase = this.normBase(s2);
        this.SoundBase = this.normBase(this.getParameter("soundbase"));
        this.FontBase = this.normBase(this.getParameter("fontbase"));
        final String parameter2 = this.getParameter("sound");
        if (parameter2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter2, ",");
            while (stringTokenizer.hasMoreTokens()) {
                this.getSound(stringTokenizer.nextToken());
            }
        }
        if (s != null) {
            if (s.indexOf(".") < 0) {
                s = String.valueOf(s) + ".zip";
            }
            this.loadArchive(s);
        }
        final String parameter3 = this.getParameter("hersheyFonts");
        if (parameter3 != null) {
            final StringTokenizer stringTokenizer2 = new StringTokenizer(parameter3, ",");
            while (stringTokenizer2.hasMoreTokens()) {
                this.addFont(stringTokenizer2.nextToken());
            }
        }
        final String parameter4 = this.getParameter("bgcolor");
        if (parameter4 != null) {
            this.BackColor = Color.decode(parameter4);
        }
        this.inverseColor = (this.getParameter("inverse") != null);
        this.showHotspots = (this.getParameter("showHotspots") != null);
        if (this.getParameter("keyActions") != null) {
            this.keyActions = true;
        }
        final String parameter5 = this.getParameter("antialiasing");
        if (parameter5 != null) {
            this.antialias = (parameter5.toLowerCase().equals("on") ? RenderingHints.VALUE_ANTIALIAS_OFF : RenderingHints.VALUE_ANTIALIAS_ON);
        }
        final String parameter6 = this.getParameter("rendering");
        if (parameter6 != null) {
            this.rendering = (parameter6.toLowerCase().equals("speed") ? RenderingHints.VALUE_RENDER_SPEED : RenderingHints.VALUE_RENDER_QUALITY);
        }
        final String parameter7 = this.getParameter("zoomFactor");
        if (parameter7 != null) {
            this.zoomFactor = Double.valueOf(parameter7);
        }
        final String parameter8 = this.getParameter("eventHandler");
        if (parameter8 != null) {
            this.JSmethod = parameter8;
        }
        final String parameter9 = this.getParameter("filename");
        if (parameter9 == null) {
            System.out.println("No files");
            System.exit(1);
        }
        this.tracker = new MediaTracker(this);
        this.show(0, parameter9);
        final String parameter10 = this.getParameter("imagemap");
        if (parameter10 != null) {
            final StringTokenizer stringTokenizer3 = new StringTokenizer(parameter10, ",");
            this.urls = new Vector(20, 10);
            if (stringTokenizer3.hasMoreTokens()) {
                this.mapElement = this.getElement(0, stringTokenizer3.nextToken());
            }
            if (this.mapElement != null && this.mapElement.cgm != null) {
                this.mapElement.visibility = 1;
                while (stringTokenizer3.hasMoreTokens()) {
                    try {
                        final String nextToken = stringTokenizer3.nextToken();
                        if (nextToken != "") {
                            this.urls.addElement(new URL(this.getDocumentBase(), nextToken));
                        }
                        else {
                            this.urls.addElement(null);
                        }
                    }
                    catch (IOException ex) {
                        System.out.println(ex);
                        if (!this.isActive()) {
                            continue;
                        }
                        this.showStatus(ex + "!");
                    }
                }
            }
            else {
                this.mapElement = null;
            }
        }
        this.announce = true;
        try {
            this.JSwin = JSObject.getWindow(this);
        }
        catch (Throwable t) {}
        this.UpdateThread.start();
    }
    
    final void initCanvas() {
        if (this.offScrImage == null) {
            this.offScrImage = this.createImage(this.Width, this.Height);
        }
        this.canvasX = 0;
        this.canvasY = 0;
        this.canvasWidth = this.Width;
        this.canvasHeight = this.Height;
        final Layer layer = this.Layers[0];
        for (int i = 0; i < layer.Images.size(); ++i) {
            final CgmContext cgm = layer.Images.elementAt(i).cgm;
            if (cgm != null) {
                cgm.finishReading();
                final CgmPicture cgmPicture = cgm.pictures.firstElement();
                if (cgmPicture != null && cgmPicture.Width != 0.0) {
                    if (this.BackColor == null) {
                        this.BackColor = cgmPicture.BackColor;
                    }
                    this.factor = Math.min(Math.abs(this.Width / (cgmPicture.Width * cgmPicture.ax)), Math.abs(this.Height / (cgmPicture.Height * cgmPicture.ay)));
                    this.canvasWidth = (int)(cgmPicture.Width * this.factor * cgmPicture.ax);
                    this.canvasHeight = (int)Math.abs(cgmPicture.Height * this.factor * cgmPicture.ay);
                    break;
                }
            }
        }
        this.origWidth = this.canvasWidth;
        this.origHeight = this.canvasHeight;
        this.origX = (this.Width - this.origWidth) / 2;
        this.origY = (this.Height - this.origHeight) / 2;
        this.clipW = this.Width / this.canvasWidth;
        this.clipH = this.Height / this.canvasHeight;
        for (int j = 0; j < 17; ++j) {
            if (this.Layers[j] != null) {
                this.Layers[j].clipw = this.clipW;
                this.Layers[j].cliph = this.clipH;
            }
        }
    }
    
    public boolean keyDown(final Event event, final int n) {
        this.kmod = (event.modifiers & 0xF);
        if (this.keyActions) {
            final int max = Math.max(1, this.Width / 100);
            final int max2 = Math.max(1, this.Height / 100);
            final int max3 = Math.max(1, this.Width / 5);
            final int max4 = Math.max(1, this.Height / 5);
            int key = event.key;
            final double userFactor = this.userFactor;
            if ((this.kmod & 0x2) != 0x0) {
                switch (key) {
                    case 1006: {
                        key = 1000;
                        break;
                    }
                    case 1007: {
                        key = 1001;
                        break;
                    }
                    case 1004: {
                        key = 1002;
                        break;
                    }
                    case 1005: {
                        key = 1003;
                        break;
                    }
                }
            }
            if (key == 10 && this.targetURL != null) {
                this.getAppletContext().showDocument(this.targetURL);
                return true;
            }
            switch (key) {
                case 43: {
                    this.userFactor = Math.min(16.0, this.userFactor * this.zoomFactor);
                    this.canvasX = (int)((this.canvasX + this.Width / 2 - this.origX) * this.userFactor / userFactor - this.Width / 2 + this.origX);
                    this.canvasY = (int)((this.canvasY + this.Height / 2 - this.origY) * this.userFactor / userFactor - this.Height / 2 + this.origY);
                    break;
                }
                case 45: {
                    this.userFactor = Math.max(1.0, this.userFactor / this.zoomFactor);
                    this.canvasX = (int)((this.canvasX + this.Width / 2 - this.origX) * this.userFactor / userFactor - this.Width / 2 + this.origX);
                    this.canvasY = (int)((this.canvasY + this.Height / 2 - this.origY) * this.userFactor / userFactor - this.Height / 2 + this.origY);
                    break;
                }
                case 1006: {
                    this.canvasX -= max;
                    break;
                }
                case 1007: {
                    this.canvasX += max;
                    break;
                }
                case 1004: {
                    this.canvasY -= max2;
                    break;
                }
                case 1005: {
                    this.canvasY += max2;
                    break;
                }
                case 1000: {
                    this.canvasX -= max3;
                    break;
                }
                case 1001: {
                    this.canvasX += max3;
                    break;
                }
                case 1002: {
                    this.canvasY -= max4;
                    break;
                }
                case 1003: {
                    this.canvasY += max4;
                    break;
                }
                case 33: {
                    if (this.mapElement == null) {
                        return false;
                    }
                    int n2 = (this.hotspot <= 0 || this.hotspot >= this.urls.size()) ? 1 : (this.hotspot + 1);
                    final CgmPrimitive component = this.mapElement.cgm.findComponent(n2);
                    if (component != null) {
                        this.targetURL = (URL)this.urls.elementAt(n2 - 1);
                        final double n3 = this.factor * this.userFactor;
                        final Layer layer = this.Layers[0];
                        this.canvasX = (int)Math.round((component.x + component.Width * 0.5) * n3 * layer.factorX - this.Width / 2);
                        this.canvasY = (int)Math.round((component.y + component.Height * 0.5) * n3 * layer.factorY - this.Height / 2);
                    }
                    else {
                        this.targetURL = null;
                        n2 = -1;
                    }
                    this.updateHyperlink(n2, false);
                    break;
                }
                case 32: {
                    this.userFactor = 1.0;
                    this.canvasWidth = this.origWidth;
                    this.canvasHeight = this.origHeight;
                    this.canvasX = 0;
                    this.canvasY = 0;
                    break;
                }
                default: {
                    key = -1;
                    break;
                }
            }
            if (key >= 0) {
                this.dragged = false;
                this.canvasWidth = (int)Math.round(this.origWidth * this.userFactor);
                this.canvasHeight = (int)Math.round(this.origHeight * this.userFactor);
                this.canvasX = Math.max(Math.min(this.canvasX, this.canvasWidth - this.origWidth), 0);
                this.canvasY = Math.max(Math.min(this.canvasY, this.canvasHeight - this.origHeight), 0);
                this.redrawAll();
                return true;
            }
        }
        this.key = -n;
        this.notifyJS(null);
        if (this.ModifiersSet != 0) {
            this.kmod = (this.ModifiersSet & 0xF);
            if (this.ModifierOnce) {
                this.ModifiersSet = 0;
            }
        }
        return true;
    }
    
    public boolean keyUp(final Event event, final int key) {
        if (key == -this.key || this.key == 0) {
            this.key = key;
        }
        this.notifyJS(null);
        return true;
    }
    
    private final void loadArchive(final String s) {
        if (this.announce) {
            this.showStatus("Loading archive..." + s);
        }
        if (this.zipFiles == null) {
            this.zipFiles = new Hashtable();
        }
        try {
            final InputStream inputStream = new URL(this.ImageBase, s).openConnection().getInputStream();
            final ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(inputStream));
            final DataInputStream dataInputStream = new DataInputStream(zipInputStream);
            while (true) {
                final ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                final String upperCase = nextEntry.getName().toUpperCase();
                if (upperCase.endsWith(".JHF")) {
                    if (this.Fonts == null) {
                        this.Fonts = new Hashtable();
                    }
                    this.Fonts.put(this.fontname(upperCase), new HersheyFont(dataInputStream, upperCase));
                }
                else {
                    final CgmContext cgmContext = new CgmContext(this, upperCase);
                    this.zipFiles.put(upperCase, cgmContext);
                    cgmContext.getReader(dataInputStream);
                    cgmContext.finishReading();
                }
            }
            try {
                inputStream.close();
            }
            catch (IOException ex2) {}
        }
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public final void loopSound(final String s) {
        this.waitFor();
        this.getSound(s).loop();
    }
    
    public final void map(final int n) {
        this.getLayer(n).visibility = 1;
    }
    
    public final void map(final int n, final String s) {
        this.setAttr(n, s, 1);
    }
    
    public final void map(final int n, final String s, final int n2) {
        this.setAttr(n, s, n2, 1);
    }
    
    public final String mouseControl() {
        final String animationEvent = this.AnimationEvent;
        this.AnimationEvent = "";
        return animationEvent;
    }
    
    public final String mouseControl(final String controls) {
        if (controls.length() > 0) {
            if (!this.Controls.equals(controls)) {
                this.showStatus(this.Controls = controls);
            }
        }
        else {
            this.Controls = this.BaseControls;
            this.showStatus(" ");
        }
        return this.mouseControl();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return this.mousePressed(event.modifiers, n, n2);
    }
    
    boolean mousePressed(final int n, final int n2, final int n3) {
        if (this.Controls.length() == 0) {
            return false;
        }
        this.keyMod = (n & 0xF);
        if (this.ModifiersSet != 0) {
            this.keyMod = (this.ModifiersSet & 0x1F);
            this.targetURL = null;
            if (this.ModifierOnce) {
                this.ModifiersSet = 0;
            }
        }
        this.dragged = (this.keyMod == 5);
        this.leftmouse = ((this.keyMod & 0xC) == 0x0 && !this.dragged);
        this.ani = ((this.keyMod & this.AnimationMask) != 0x0 || this.AnimationMask == 0);
        this.mouseX = n2;
        this.mouseY = n3;
        this.notifyJS("Down");
        this.AnimationMouseX = n2;
        this.AnimationMouseY = n3;
        this.AnimationCurrX = n2;
        this.AnimationCurrY = n3;
        this.AnimationModifier = this.keyMod;
        if (this.ani) {
            this.AnimationClickPicture = null;
            return true;
        }
        if (this.BaseControls.length() == 0) {
            return false;
        }
        this.currX = n2;
        this.currY = n3;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        return this.mouseDragged(n, n2);
    }
    
    boolean mouseDragged(final int n, final int n2) {
        if ((!this.ani && this.BaseControls.length() == 0) || this.Controls.length() == 0) {
            return false;
        }
        if (!(this.dragged |= (Math.abs(this.mouseX - n) * 40.0 > this.Width || Math.abs(this.mouseY - n2) * 40.0 > this.Height || !this.leftmouse))) {
            return false;
        }
        this.notifyJS("Drag");
        this.AnimationCurrX = n;
        this.AnimationCurrY = n2;
        if (this.ani) {
            this.AnimationDragPicture = null;
            return true;
        }
        this.currX = n;
        this.currY = n2;
        if (this.leftmouse || this.userFactor > 1.0) {
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.requestFocus();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        return this.mouseMoved(event.modifiers, n, n2);
    }
    
    boolean mouseMoved(final int n, final int animationCurrX, final int animationCurrY) {
        this.targetURL = null;
        if (this.mapElement != null) {
            int searchResult = -1;
            final double n2 = this.factor * this.userFactor;
            final Layer layer = this.getLayer(0);
            if (layer.visibility != 0 && layer.factorX >= 0.001 && layer.factorY >= 0.001 && this.mapElement.find(this.origX - this.canvasX + layer.x * layer.factorX, this.origY - this.canvasY + layer.y * layer.factorY, n2 * layer.factorX, n2 * layer.factorY, animationCurrX, animationCurrY)) {
                this.targetURL = null;
                searchResult = this.mapElement.SearchResult;
                if (searchResult > 0) {
                    if (searchResult <= this.urls.size()) {
                        this.targetURL = (URL)this.urls.elementAt(searchResult - 1);
                    }
                    this.updateHyperlink(searchResult, true);
                    return true;
                }
            }
            this.updateHotspot(searchResult, true);
        }
        this.setCursor(Cursor.getPredefinedCursor(1));
        if (this.Controls.length() == 0) {
            return false;
        }
        if (this.AnimationEvent == "") {
            this.notifyJS("Move");
            this.AnimationCurrX = animationCurrX;
            this.AnimationCurrY = animationCurrY;
            this.AnimationModifier = (n & 0xF);
        }
        int max = 1;
        if (!this.Controls.startsWith(".")) {
            final int n3 = this.Width / 6;
            final int max2 = Math.max(0, this.Controls.length() - 15);
            max = Math.max(0, Math.min(max2, max2 * (animationCurrX - n3) / (this.Width - 2 * n3)));
        }
        this.showStatus(this.Controls.substring(max));
        if ((this.ModifiersSet & 0x4) != 0x0 & (this.ModifiersSet & 0x1) != 0x0) {
            this.setCursor(Cursor.getPredefinedCursor(12));
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        return this.mouseReleased(event.clickCount, n, n2);
    }
    
    boolean mouseReleased(int n, final int animationCurrX, final int animationCurrY) {
        if (!this.ani && !this.dragged && this.targetURL != null && this.leftmouse) {
            this.getAppletContext().showDocument(this.targetURL);
            return true;
        }
        if ((!this.ani && this.BaseControls.length() == 0) || this.Controls.length() == 0) {
            return false;
        }
        try {
            final double userFactor = this.userFactor;
            if ((this.keyMod & 0x10) != 0x0) {
                n = 2;
            }
            if (n > 1 && !this.leftmouse) {
                this.userFactor = 1.0;
                this.canvasWidth = this.origWidth;
                this.canvasHeight = this.origHeight;
                this.canvasX = 0;
                this.canvasY = 0;
            }
            else {
                if (this.dragged) {
                    this.AnimationCurrX = animationCurrX;
                    this.AnimationCurrY = animationCurrY;
                    this.notifyJS("Drop");
                    if (!this.ani) {
                        if (this.leftmouse) {
                            this.userFactor = Math.min(16.0, userFactor * Math.min(this.Width / Math.abs(this.mouseX - animationCurrX), this.Height / Math.abs(this.mouseY - animationCurrY)));
                            this.canvasX = (int)Math.round((this.canvasX + Math.min(this.mouseX, animationCurrX) - this.origX) * this.userFactor / userFactor + this.origX);
                            this.canvasY = (int)Math.round((this.canvasY + Math.min(this.mouseY, animationCurrY) - this.origY) * this.userFactor / userFactor + this.origY);
                        }
                        else {
                            this.canvasX = this.canvasX + this.mouseX - animationCurrX;
                            this.canvasY = this.canvasY + this.mouseY - animationCurrY;
                        }
                    }
                }
                else {
                    this.notifyJS((n <= 1) ? "Click" : "DoubleClick");
                    if (!this.ani) {
                        if ((this.keyMod & 0x1) != 0x0) {
                            this.canvasX = (int)Math.round(this.canvasX + animationCurrX - this.Width * 0.5);
                            this.canvasY = (int)Math.round(this.canvasY + animationCurrY - this.Height * 0.5);
                        }
                        else {
                            this.userFactor = (this.leftmouse ? Math.min(16.0, this.userFactor * this.zoomFactor) : Math.max(1.0, this.userFactor / this.zoomFactor));
                            this.canvasX = (int)Math.round((this.canvasX + animationCurrX - this.origX) * this.userFactor / userFactor - animationCurrX + this.origX);
                            this.canvasY = (int)Math.round((this.canvasY + animationCurrY - this.origY) * this.userFactor / userFactor - animationCurrY + this.origY);
                        }
                    }
                }
                this.canvasWidth = (int)Math.round(this.origWidth * this.userFactor);
                this.canvasHeight = (int)Math.round(this.origHeight * this.userFactor);
                this.canvasX = Math.max(Math.min(this.canvasX, this.canvasWidth - this.origWidth), 0);
                this.canvasY = Math.max(Math.min(this.canvasY, this.canvasHeight - this.origHeight), 0);
            }
            if (!this.ani) {
                this.redrawAll();
            }
            return true;
        }
        finally {
            this.dragged = false;
        }
    }
    
    public final void moveComponent(final int n, final String s, final int n2, final double n3, final double n4) {
        if (n2 >= 0) {
            final CgmPrimitive component = this.getElement(n, s).cgm.findComponent(n2);
            if (component != null) {
                component.move(n3 / 1000.0, n4 / 1000.0);
            }
        }
    }
    
    public final void movePane(final int n, final String s, final int n2, final double n3, final double n4) {
        final CgmContext cgm = this.getElement(n, s).cgm;
        if (n2 >= 0 && n2 < cgm.pictures.size()) {
            final CgmPicture cgmPicture = cgm.pictures.elementAt(n2);
            final double n5 = n3 / 1000.0;
            final double n6 = n4 / 1000.0;
            for (int i = 0; i < cgmPicture.PrimList.size(); ++i) {
                ((CgmPrimitive)cgmPicture.PrimList.elementAt(i)).move(n5, n6);
            }
        }
    }
    
    private final URL normBase(String string) {
        URL documentBase = this.getDocumentBase();
        try {
            if (string != null) {
                if (!string.endsWith("/")) {
                    string = String.valueOf(string) + "/";
                }
                documentBase = new URL(documentBase, string);
            }
        }
        catch (MalformedURLException ex) {
            this.showStatus(ex + "!");
            System.out.println(ex);
            System.exit(1);
        }
        return documentBase;
    }
    
    private void notifyJS(final String animationEvent) {
        try {
            this.JSmsg[0] = "Animation";
            if (animationEvent == null) {
                this.JSmsg[1] = "Key";
                this.JSmsg[2] = ((this.key < 0) ? "Down" : "Up");
            }
            else {
                this.JSmsg[1] = "Mouse";
                this.JSmsg[2] = animationEvent;
                if (this.ani) {
                    this.AnimationEvent = animationEvent;
                }
                else {
                    this.JSmsg[0] = "Navigation";
                }
            }
            if (this.JSwin != null) {
                this.JSwin.call(this.JSmethod, this.JSmsg);
            }
        }
        catch (Exception ex) {}
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.renderer != null && !this.renderer.isAlive()) {
            if (this.dragged && (this.keyMod & 0x2) == 0x0) {
                int n = this.mouseX;
                int n2 = this.mouseY;
                int n3 = this.currX - n;
                int n4 = this.currY - n2;
                if (n3 < 0) {
                    n3 = -n3;
                    n = this.currX;
                }
                if (n4 < 0) {
                    n4 = -n4;
                    n2 = this.currY;
                }
                if (this.leftmouse) {
                    graphics.drawImage(this.offScrImage, 0, 0, this.BackColor, null);
                    graphics.setXORMode(Color.cyan);
                    graphics.drawRect(n, n2, n3, n4);
                    graphics.setPaintMode();
                }
                else {
                    final int max = Math.max(Math.min(this.canvasX + this.mouseX - this.currX, this.canvasWidth - this.origWidth), 0);
                    final int max2 = Math.max(Math.min(this.canvasY + this.mouseY - this.currY, this.canvasHeight - this.origHeight), 0);
                    final int n5 = this.canvasX - max;
                    final int n6 = this.canvasY - max2;
                    graphics.translate(n5, n6);
                    graphics.drawImage(this.offScrImage, 0, 0, this.BackColor, null);
                    graphics.translate(-n5, -n6);
                }
            }
            else {
                graphics.drawImage(this.offScrImage, 0, 0, this.BackColor, null);
            }
            return;
        }
        if (this.splash) {
            this.splash = false;
            graphics.clearRect(0, 0, this.Width, this.Height);
            final int n7 = this.Height / 2;
            this.drawString(graphics, this.drawString(graphics, this.drawString(graphics, this.drawString(graphics, n7 / 10, n7, "cgm", 0, n7 / 2, Color.red, 1) - n7 / 8, n7, "VA", 0, n7 / 2, Color.green, 1), n7, this.appletVersion, 2, n7 / 6, Color.blue, 1), n7 + n7 / 8, "www.bdaum.de", 0, n7 / 12, Color.blue, 2);
        }
    }
    
    public final void playSound(final String s) {
        this.waitFor();
        this.getSound(s).play();
    }
    
    public final void preload(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        while (stringTokenizer.hasMoreTokens()) {
            final String lowerCase = stringTokenizer.nextToken().toLowerCase();
            if (lowerCase.endsWith(".zip")) {
                this.loadArchive(lowerCase);
            }
            else if (lowerCase.endsWith(".au")) {
                this.getSound(lowerCase);
            }
            else if (lowerCase.endsWith(".jhf")) {
                if (this.Fonts == null) {
                    this.Fonts = new Hashtable();
                }
                this.Fonts.put(this.fontname(lowerCase), new HersheyFont(lowerCase));
            }
            else {
                this.cgmFiles.put(lowerCase, this.readFile(lowerCase));
            }
        }
    }
    
    private final CgmContext readFile(final String filename) {
        String string = filename;
        if (string.indexOf(".") < 0) {
            string = String.valueOf(string) + ".cgm";
        }
        final CgmContext cgmContext = this.cgmFiles.get(string);
        if (cgmContext != null) {
            return cgmContext;
        }
        final CgmContext cgmContext2;
        if (this.zipFiles != null && (cgmContext2 = this.zipFiles.get(string.toUpperCase())) != null) {
            cgmContext2.filename = filename;
            this.cgmFiles.put(string, cgmContext2);
            return cgmContext2;
        }
        if (this.announce) {
            this.showStatus("Loading picture..." + string);
        }
        try {
            final CgmContext cgmContext3;
            this.cgmFiles.put(string, cgmContext3 = new CgmContext(this, filename));
            final URL url = new URL(this.ImageBase, string);
            final String lowerCase = string.toLowerCase();
            if (lowerCase.endsWith(".gif") || lowerCase.endsWith(".png") || lowerCase.endsWith(".jpg") || lowerCase.endsWith(".jpeg")) {
                final Image image = this.getImage(url);
                this.tracker.addImage(image, 0);
                cgmContext3.beginPic(filename);
                cgmContext3.currpic.image(image);
                cgmContext3.FinishedReading = true;
                return cgmContext3;
            }
            final InputStream inputStream = url.openConnection().getInputStream();
            cgmContext3.getReader(new DataInputStream(new BufferedInputStream(inputStream)));
            cgmContext3.is = inputStream;
            return cgmContext3;
        }
        catch (IOException ex) {
            System.out.println(ex);
            if (this.isActive()) {
                this.showStatus(ex + "!");
            }
            return null;
        }
    }
    
    private void redrawAll() {
        if (this.renderer != null && this.renderer.isAlive()) {
            this.renderer.stop();
        }
        this.renderScene();
    }
    
    public final void render() {
        if (this.isActive()) {
            this.renderScene();
        }
    }
    
    private final void renderScene() {
        final Graphics2D graphics2D = (Graphics2D)this.offScrImage.getGraphics();
        this.showStatus("Rendering ...");
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, this.antialias);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, this.rendering);
        if (this.BackColor != null) {
            graphics2D.setColor(this.BackColor);
            graphics2D.fillRect(0, 0, this.Width, this.Height);
        }
        else {
            graphics2D.clearRect(0, 0, this.Width, this.Height);
        }
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            return;
        }
        if (this.mapElement != null && this.showHotspots && this.mapElement.visibility != 2) {
            this.mapElement.visibility = 2;
            final CgmContext cgm = this.mapElement.cgm;
            if (cgm != null) {
                cgm.finishReading();
                int n = 0;
                while (true) {
                    final CgmPrimitive component = cgm.findComponent(n);
                    if (component == null) {
                        break;
                    }
                    component.visibility = 1;
                    ++n;
                }
            }
        }
        final double n2 = this.factor * this.userFactor;
        (this.renderer = new RenderThread(this, this.origX - this.canvasX + this.AnimationOffX * n2, this.origY - this.canvasY + this.AnimationOffY * n2, n2 * this.AnimationFactorX, n2 * this.AnimationFactorY, this.offScrImage, this.Layers, new Rectangle((int)(this.clipX * this.factor), (int)(this.clipY * this.factor), (int)(this.clipW * this.factor), (int)(this.clipH * this.factor)), this.factor)).run();
    }
    
    public final void replaceText(final int n, final String s, final int n2, final String s2) {
        this.getElement(n, s).replaceText(n2, s2);
    }
    
    public void run() {
        this.waitFor();
        this.initCanvas();
        this.renderScene();
    }
    
    public final void scale(final double n, final double n2) {
        this.waitFor();
        this.AnimationFactorX = Math.min(Math.min(n, 16.0), 1.0);
        this.AnimationFactorY = Math.min(Math.min(n2, 16.0), 1.0);
    }
    
    public final void scale(final int n, final double n2, final double n3) {
        final Layer layer = this.getLayer(n);
        layer.factorX = Math.min(n2, 16.0);
        layer.factorY = Math.min(n3, 16.0);
    }
    
    public final void scale(final int n, final String s, final double n2, final double n3) {
        final LayerElement element = this.getElement(n, s);
        element.factorX = Math.min(n2, 16.0);
        element.factorY = Math.min(n3, 16.0);
    }
    
    public final void scroll(final int n, final int n2) {
        this.canvasX = Math.max(Math.min(this.canvasX - n * this.canvasWidth / 1000, this.canvasWidth - this.origWidth), 0);
        this.canvasY = Math.max(Math.min(this.canvasY - n2 * this.canvasHeight / 1000, this.canvasHeight - this.origHeight), 0);
    }
    
    private final void setAttr(final int n, final String s, final int visibility) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        while (stringTokenizer.hasMoreTokens()) {
            this.getElement(n, stringTokenizer.nextToken()).visibility = visibility;
        }
    }
    
    private final void setAttr(final int n, final String s, final int n2, final int visibility) {
        final CgmPrimitive component = this.getElement(n, s).cgm.findComponent(n2);
        if (component != null) {
            component.visibility = visibility;
        }
    }
    
    public final void setEventMask(final int n) {
        this.AnimationMask = (n & 0x7);
    }
    
    public final void setModifiers(final int n, final int n2) {
        if (n2 == 0) {
            this.ModifiersSet = 0;
            return;
        }
        this.ModifiersSet = ((n & 0x1F) | 0x8000);
        this.ModifierOnce = (n2 == 1);
    }
    
    public final void setViewX(final double n) {
        this.canvasX = (int)Math.max(Math.min(n * this.canvasWidth * this.userFactor * 0.001, this.canvasWidth * this.userFactor - this.origWidth), 0.0);
    }
    
    public final void setViewY(final double n) {
        this.canvasY = (int)Math.max(Math.min(n * this.canvasHeight * this.userFactor * 0.001, this.canvasHeight * this.userFactor - this.origHeight), 0.0);
    }
    
    public final double getViewX() {
        return 1000.0 * this.canvasX / (this.canvasWidth * this.userFactor);
    }
    
    public final double getViewY() {
        return 1000.0 * this.canvasY / (this.canvasHeight * this.userFactor);
    }
    
    public final double getViewWidth() {
        return 1000.0 * this.Width / (this.canvasWidth * this.userFactor);
    }
    
    public final double getViewHeight() {
        return 1000.0 * this.Height / (this.canvasHeight * this.userFactor);
    }
    
    public final void setZoomFactor(final double zoomFactor) {
        this.zoomFactor = zoomFactor;
    }
    
    public final void show(final int n) {
        this.getLayer(n).visibility = 3;
    }
    
    public final void show(final int n, final String s) {
        this.setAttr(n, s, 3);
    }
    
    private void showHotspot(final int visibility) {
        if (this.hotspot > 0) {
            final CgmPrimitive component = this.mapElement.cgm.findComponent(this.hotspot);
            if (component != null) {
                component.visibility = visibility;
            }
        }
    }
    
    public final void show(final int n, final String s, final int n2) {
        this.setAttr(n, s, n2, 3);
    }
    
    public void start() {
        if (this.UpdateThread != null && this.UpdateThread.isAlive()) {
            this.UpdateThread.resume();
        }
    }
    
    public void stop() {
        if (this.UpdateThread != null && this.UpdateThread.isAlive()) {
            this.UpdateThread.suspend();
        }
    }
    
    public final void stopSound(final String s) {
        this.waitFor();
        this.getSound(s).stop();
    }
    
    public final void translate(final double n, final double n2) {
        this.waitFor();
        this.AnimationOffX = n / 1000.0;
        this.AnimationOffY = n2 / 1000.0;
    }
    
    public final void translate(final int n, final double n2, final double n3) {
        final Layer layer = this.getLayer(n);
        layer.x = n2 / 1000.0;
        layer.y = n3 / 1000.0;
    }
    
    public final void translate(final int n, final String s, final double n2, final double n3) {
        final LayerElement element = this.getElement(n, s);
        element.x = n2 / 1000.0;
        element.y = n3 / 1000.0;
    }
    
    public final void unload(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        while (stringTokenizer.hasMoreTokens()) {
            String s2 = stringTokenizer.nextToken().toLowerCase();
            if (s2.endsWith(".au")) {
                if (this.Sounds == null) {
                    continue;
                }
                this.Sounds.remove(s2);
            }
            else if (s2.endsWith(".jhf")) {
                if (this.Fonts == null) {
                    continue;
                }
                this.Fonts.remove(this.fontname(s2));
            }
            else {
                if (s2.indexOf(".") < 0) {
                    s2 = String.valueOf(s2) + ".cgm";
                }
                if (this.zipFiles != null) {
                    this.zipFiles.remove(s2.toUpperCase());
                }
                final CgmContext cgmContext = this.cgmFiles.get(s2);
                if (cgmContext == null) {
                    continue;
                }
                this.cgmFiles.remove(s2);
                for (int i = 0; i < 16; ++i) {
                    final Layer layer = this.Layers[i];
                    if (layer != null) {
                        for (int j = 0; j < layer.Images.size(); ++j) {
                            final LayerElement layerElement = layer.Images.elementAt(j);
                            if (layerElement.cgm == cgmContext) {
                                layer.Images.removeElementAt(j);
                                if (this.mapElement == layerElement) {
                                    this.mapElement = null;
                                    this.urls = null;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.offScreenBuffer == null || this.offScreenBuffer.getWidth(this) != this.Width || this.offScreenBuffer.getHeight(this) != this.Height) {
            this.offScreenBuffer = this.createImage(this.Width, this.Height);
        }
        this.paint(this.offScreenBuffer.getGraphics());
        graphics.drawImage(this.offScreenBuffer, 0, 0, this);
    }
    
    private void updateHotspot(final int hotspot, final boolean b) {
        if (this.showHotspots && hotspot != this.hotspot) {
            this.showHotspot(1);
            this.hotspot = hotspot;
            this.showHotspot(2);
            if (b) {
                this.redrawAll();
            }
        }
    }
    
    private void updateHyperlink(final int n, final boolean b) {
        this.updateHotspot(n, b);
        if (this.targetURL != null) {
            this.showStatus(this.targetURL.toString());
            this.setCursor(Cursor.getPredefinedCursor(12));
            return;
        }
        this.showStatus("imagemap #" + n);
    }
    
    public final void waitFor() {
        if (this.renderer != null) {
            try {
                while (this.renderer.isAlive()) {
                    this.renderer.join(250L);
                }
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final void wire(final int n) {
        this.getLayer(n).visibility = 2;
    }
    
    public final void wire(final int n, final String s) {
        this.setAttr(n, s, 2);
    }
    
    public final void wire(final int n, final String s, final int n2) {
        this.setAttr(n, s, n2, 2);
    }
    
    public final void zoom(final double n) {
        final double userFactor = this.userFactor;
        final double n2 = this.Width * 0.5;
        final double n3 = this.Height * 0.5;
        this.userFactor = Math.min(16.0, Math.max(1.0, n));
        this.canvasX = (int)Math.round((this.canvasX + n2 - this.origX) * this.userFactor / userFactor - n2 + this.origX);
        this.canvasY = (int)Math.round((this.canvasY + n3 - this.origY) * this.userFactor / userFactor - n3 + this.origY);
        this.canvasWidth = (int)Math.round(this.origWidth * this.userFactor);
        this.canvasHeight = (int)Math.round(this.origHeight * this.userFactor);
        this.canvasX = Math.max(Math.min(this.canvasX, this.canvasWidth - this.origWidth), 0);
        this.canvasY = Math.max(Math.min(this.canvasY, this.canvasHeight - this.origHeight), 0);
    }
    
    public final void zoom(final double n, final double n2, final double n3) {
        this.userFactor = Math.min(16.0, Math.max(1.0, n));
        this.canvasWidth = (int)Math.round(this.origWidth * this.userFactor);
        this.canvasHeight = (int)Math.round(this.origHeight * this.userFactor);
        this.canvasX = (int)Math.round(n2 / 1000.0 * this.canvasWidth - this.Width / 2 + this.userFactor * this.origX);
        this.canvasY = (int)Math.round(n3 / 1000.0 * this.canvasHeight - this.Height / 2 + this.userFactor * this.origY);
        this.canvasX = Math.max(Math.min(this.canvasX, this.canvasWidth - this.origWidth), 0);
        this.canvasY = Math.max(Math.min(this.canvasY, this.canvasHeight - this.origHeight), 0);
    }
}
