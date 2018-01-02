// 
// Decompiled by Procyon v0.5.30
// 

package mapapplet;

import java.util.StringTokenizer;
import mapapplet.imageload.ImageMessage;
import java.util.Observable;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.LayoutManager;
import java.text.DecimalFormat;
import netscape.javascript.JSObject;
import java.util.Hashtable;
import java.awt.Label;
import mapapplet.imageload.ImageLoader;
import java.util.Observer;
import java.applet.Applet;

public class Main extends Applet implements Observer
{
    public Toolbar toolbar;
    public static ImageLoader imgLoader;
    public Data data;
    public MapTool map;
    public Label statusBar;
    public Label s;
    public Label n;
    public Label w;
    public Label e;
    public Label nwExt;
    public Label seExt;
    protected Hashtable modules;
    protected boolean isInitialised;
    public JSObject win;
    public DecimalFormat formatter;
    static /* synthetic */ Class class$java$lang$Object;
    
    public Main() {
        this.isInitialised = false;
    }
    
    public void init() {
        this.setName("Main");
        this.setLayout(null);
        this.data = new Data();
        (Main.imgLoader = new ImageLoader(this)).addObserver(this);
        this.formatter = new DecimalFormat("##0.###");
        try {
            this.win = JSObject.getWindow(this);
        }
        catch (Exception e) {
            System.out.println("Error creating JSObject");
            e.printStackTrace();
        }
        this.modules = new Hashtable();
        this.setBackground(Color.white);
        final String[] prefferedFonts = { "arial", "times", "courier", "lucida", "dialog", "sans" };
        final String[] fontsArray = Toolkit.getDefaultToolkit().getFontList();
        boolean foundFont = false;
        for (int curFamily = 0; !foundFont && curFamily < prefferedFonts.length; ++curFamily) {
            for (int i = 0; i < fontsArray.length; ++i) {
                if (!foundFont && fontsArray[i].toLowerCase().indexOf(prefferedFonts[curFamily]) >= 0) {
                    this.setFont(new Font(fontsArray[i], 0, 12));
                    foundFont = true;
                }
            }
        }
        this.setElements();
        Main.imgLoader.addObserver(this.toolbar);
        this.map.setName("Map");
        this.setGlobalMap();
        this.determineModules();
    }
    
    private void setElements() {
        this.toolbar = (Toolbar)this.add(new Toolbar(this));
        this.statusBar = (Label)this.add(new Label());
        this.s = (Label)this.add(new Label("S", 1));
        this.n = (Label)this.add(new Label("N", 1));
        this.w = (Label)this.add(new Label("W", 1));
        this.e = (Label)this.add(new Label("E", 1));
        this.nwExt = (Label)this.add(new Label("", 0));
        this.seExt = (Label)this.add(new Label("", 2));
        this.map = (MapTool)this.add(new MapTool(this));
    }
    
    private void setGlobalMap() {
        int projection = 0;
        String paramStr = this.getParameter("Projection");
        if (paramStr != null && paramStr.compareTo("polar") == 0) {
            projection = 1;
        }
        this.data.projection = projection;
        paramStr = this.getParameter("GlobalMapURL");
        if (paramStr == null) {
            return;
        }
        String imgURLStr;
        int i;
        for (imgURLStr = paramStr; imgURLStr.indexOf(" ") != -1; imgURLStr = imgURLStr.substring(0, i) + "+" + imgURLStr.substring(i + 1, imgURLStr.length())) {
            i = imgURLStr.indexOf(" ");
        }
        paramStr = this.getParameter("GlobalMapExtend");
        if (paramStr != null) {
            final String imgExt = paramStr;
            this.newMap(imgURLStr, imgExt);
        }
    }
    
    private void determineModules() {
        final String paramStr = this.getParameter("usedModules");
        if (paramStr == null) {
            return;
        }
        int i = 1;
        for (String module_name = getToken(paramStr, i); module_name != null; module_name = getToken(paramStr, i)) {
            final String classPath = this.getParameter(module_name + "_classpath");
            if (classPath == null) {
                System.err.println("Error: No " + module_name + "_classpath parameter.");
                return;
            }
            try {
                final Class moduleClass = Class.forName(classPath);
                final Constructor con = moduleClass.getConstructor((Main.class$java$lang$Object == null) ? (Main.class$java$lang$Object = class$("java.lang.Object")) : Main.class$java$lang$Object);
                final Object module = con.newInstance(this);
                this.map.addMouseMotionListener((MouseMotionListener)module);
                this.map.addMouseListener((MouseListener)module);
                this.modules.put(((Module)module).name(), module);
            }
            catch (ClassNotFoundException e) {
                System.err.println("Error loading object: " + e);
                e.printStackTrace();
            }
            catch (InstantiationException e2) {
                System.err.println("InstantiationException: " + e2);
                e2.printStackTrace();
            }
            catch (IllegalAccessException e3) {
                System.err.println("IllegalAccessException: " + e3);
                e3.printStackTrace();
            }
            catch (NoSuchMethodException e4) {
                System.err.println("NoSuchMethodException: " + e4);
                e4.printStackTrace();
            }
            catch (InvocationTargetException e5) {
                System.err.println("InvocationTargetException: " + e5);
                e5.getTargetException().printStackTrace();
            }
            ++i;
        }
        if (this.isInitialised) {
            this.modulesUpdate();
        }
    }
    
    public synchronized void newMap(final String imgURLString, final String imgExt) {
        this.data.setTempExtend(imgExt);
        Main.imgLoader.loadImage(imgURLString, "MainMapImage");
    }
    
    private void newMapFinish(final Image img) {
        this.data.setExtend(this.data.tempExtend);
        this.nwExt.setText("N=" + this.formatter.format(this.data.maplatup) + "; W=" + this.formatter.format(this.data.maplonleft));
        this.seExt.setText("S=" + this.formatter.format(this.data.maplatdown) + "; E=" + this.formatter.format(this.data.maplonright));
        this.map.changeMap(img);
        this.data.setMapSize(new Dimension(img.getWidth(null), img.getHeight(null)));
        if (this.data.prj.projection == MapProjection.POLAR_PROJECTION) {
            if (this.data.prj.hemisphere.compareTo("n") == 0) {
                this.s.setText("0");
                this.n.setText("180");
                this.w.setText("270");
                this.e.setText("90");
            }
            else {
                this.s.setText("0");
                this.n.setText("180");
                this.w.setText("90");
                this.e.setText("270");
            }
        }
        this.modulesUpdate();
        this.repaint();
        this.isInitialised = true;
    }
    
    private void modulesUpdate() {
        final Enumeration en = this.modules.elements();
        while (en.hasMoreElements()) {
            final Module m = en.nextElement();
            m.newMap(this.data.prj);
        }
    }
    
    private void stopModules() {
        final Enumeration en = this.modules.elements();
        while (en.hasMoreElements()) {
            final Module m = en.nextElement();
            try {
                final Method[] met = m.getClass().getMethods();
                for (int i = 0; i < met.length; ++i) {
                    if (met[i].getName() == "stop") {
                        m.getClass().getMethod("stop", (Class<?>[])new Class[0]).invoke(m, new Object[0]);
                        break;
                    }
                }
            }
            catch (InvocationTargetException ex) {
                ex.printStackTrace();
            }
            catch (IllegalArgumentException ex2) {
                ex2.printStackTrace();
            }
            catch (IllegalAccessException ex3) {
                ex3.printStackTrace();
            }
            catch (NoSuchMethodException ex4) {
                ex4.printStackTrace();
            }
            catch (SecurityException ex5) {
                ex5.printStackTrace();
            }
        }
    }
    
    public void repaintMap(final Graphics g) {
        final String paramStr = this.getParameter("usedModules");
        int i = 1;
        for (String module_name = getToken(paramStr, i); module_name != null; module_name = getToken(paramStr, i)) {
            final Module m = this.modules.get(module_name);
            if (m != null && m.isPaintable()) {
                m.paint(g);
            }
            ++i;
        }
    }
    
    public void paint(final Graphics g) {
        final FontMetrics fm = this.getFontMetrics(this.getFont());
        final int strHeight = fm.getHeight();
        this.s.setBounds(this.getSize().width / 2 - this.s.getSize().width / 2, this.getSize().height - strHeight - 3, fm.stringWidth(this.s.getText()), strHeight);
        this.n.setBounds(this.getSize().width / 2 - this.n.getSize().width / 2, 2, fm.stringWidth(this.n.getText()), strHeight);
        this.w.setBounds(0, this.map.getLocation().y + this.map.getSize().height / 2 - strHeight / 2, 25, strHeight);
        this.e.setBounds(this.getSize().width - this.e.getSize().width, this.map.getLocation().y + this.map.getSize().height / 2 - strHeight / 2, 25, strHeight);
        this.nwExt.setBounds(0, 2, this.getSize().width / 3, strHeight);
        final int seWidth = fm.stringWidth("S=-180.0; E=-180.0");
        this.seExt.setBounds(this.getSize().width - seWidth, this.getSize().height - strHeight, seWidth, strHeight);
        this.toolbar.setLocation(this.getSize().width - this.toolbar.getSize().width, 0);
        this.map.setLocation(this.getSize().width / 2 - this.map.getSize().width / 2, this.getSize().height / 2 - this.map.getSize().height / 2);
        this.map.repaint();
        this.statusBar.setBounds(0, this.getSize().height - strHeight, this.getSize().width / 2 - this.s.getSize().width, strHeight);
    }
    
    public void update(final Observable o, final Object arg) {
        if (arg.getClass().getName().compareTo("mapapplet.imageload.ImageMessage") == 0 && ((ImageMessage)arg).ID == "MainMapImage") {
            this.newMapFinish(((ImageMessage)arg).image);
        }
    }
    
    protected void drawCoords(final int x, final int y) {
        if (this.data.initialized) {
            float[] cursorPos = new float[2];
            cursorPos = this.data.prj.xy2ll(x, y);
            this.statusBar.setText("Lat= " + this.formatter.format(cursorPos[0]) + " Lon= " + this.formatter.format(cursorPos[1]));
        }
    }
    
    public void stop() {
        this.map.stopWaiter();
        this.stopModules();
    }
    
    public String queryParam(final String paramName) {
        final Enumeration en = this.modules.elements();
        while (en.hasMoreElements()) {
            final Module m = en.nextElement();
            final String s = m.queryParam(paramName);
            if (s != null) {
                return s;
            }
        }
        return "";
    }
    
    public void setParam(final String paramName, final String value) {
        if (!this.isInitialised) {
            System.err.println("Error calling setParam: applet is not initialized.");
            this.waiter(false);
            return;
        }
        final Enumeration en = this.modules.elements();
        while (en.hasMoreElements()) {
            final Module m = en.nextElement();
            final boolean b = m.setParam(paramName, value);
            if (b) {
                return;
            }
        }
    }
    
    public void call(final String methodName) {
        final Enumeration en = this.modules.elements();
        while (en.hasMoreElements()) {
            final Module m = en.nextElement();
            final boolean b = m.call(methodName);
            if (b) {
                return;
            }
        }
    }
    
    public void waiter(final boolean start) {
        if (start) {
            this.map.startWaiter();
        }
        else {
            this.map.stopWaiter();
        }
    }
    
    public String getCurLocation() {
        final String loc = this.getDocumentBase().toString();
        return loc.substring(0, loc.lastIndexOf(47) + 1);
    }
    
    public static String getToken(final String s, final int n) {
        final StringTokenizer st = new StringTokenizer(s);
        if (st.countTokens() < n) {
            return null;
        }
        for (int i = 1; i < n; ++i) {
            st.nextToken();
        }
        return st.nextToken();
    }
    
    public static int atoi(final String s) {
        int n = 0;
        try {
            n = new Integer(s.trim());
        }
        catch (NumberFormatException ex) {}
        return n;
    }
    
    public static float atof(final String s) {
        float n = 0.0f;
        try {
            n = new Float(s.trim());
        }
        catch (NumberFormatException ex) {}
        return n;
    }
    
    public static String replaceSpaces(final String oldValue) {
        String value;
        int i;
        for (value = new String(oldValue); value.indexOf(" ") != -1; value = value.substring(0, i) + "%20" + value.substring(i + 1, value.length())) {
            i = value.indexOf(" ");
        }
        return value;
    }
    
    public static String getUrlParameter(final String request, String paramName) {
        paramName = "&" + paramName + "=";
        if (request.lastIndexOf(paramName) >= 0) {
            final int valStart = request.lastIndexOf(paramName) + paramName.length();
            final int valEnd = request.indexOf(38, valStart);
            return (valEnd >= valStart) ? request.substring(valStart, valEnd) : request.substring(valStart);
        }
        return null;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
