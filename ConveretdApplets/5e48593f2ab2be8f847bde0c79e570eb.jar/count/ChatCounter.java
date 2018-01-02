// 
// Decompiled by Procyon v0.5.30
// 

package count;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.net.URLEncoder;
import java.io.IOException;
import java.io.DataInputStream;
import java.util.Vector;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
import java.util.Date;
import java.applet.Applet;

public class ChatCounter extends Applet implements Runnable
{
    public static final String VERSION = "7.0";
    public static final String UI_FG = "ui.fg";
    public static final String UI_BG = "ui.bg";
    public static final String UI_FONT_NAME = "ui.FontName";
    public static final String UI_FONT_SIZE = "ui.FontSize";
    public static final String QUERY_SERVER = "query.Server";
    public static final String QUERY_GROUP = "query.Site";
    public static final String QUERY_ROOM = "query.Room";
    public static final String QUERY_CGI = "query.CGI";
    public static final String QUERY_HOST = "query.Host";
    public static final String QUERY_REF = "refresh.On";
    public static final String QUERY_RATE = "refresh.Rate";
    private static final String DEF_CGI = "counter.php";
    private CounterPanel counterPanel;
    private static ChatCounter staticApplet;
    private CounterConf counterConfig;
    private int sleepSeconds;
    private boolean keepRunning;
    private boolean isDebug;
    
    public ChatCounter() {
        this.counterPanel = null;
        this.counterConfig = null;
        this.sleepSeconds = 60;
        this.keepRunning = true;
        this.isDebug = false;
    }
    
    public void init() {
        System.out.println("java:" + System.getProperty("java.vendor", "#70") + " " + System.getProperty("java.version", "#71"));
        System.out.println("OS: " + System.getProperty("os.name", "#72") + " " + System.getProperty("os.version", "#73"));
        System.out.println("ChatCounter Applet 7.0");
        this.counterConfig = new CounterConf(this);
        ChatCounter.staticApplet = this;
        if (this.getParm("ParaBugger") != null) {
            this.isDebug = true;
        }
        this.buildGUI();
        this.sleepSeconds = this.counterConfig.getInt("refresh.Rate", 1);
        this.sleepSeconds *= 60;
        if (this.counterConfig.getBool("refresh.On", true)) {
            new Thread(this).start();
        }
    }
    
    public void start() {
        final int count = this.getCount();
        if (count < 0) {
            this.printDebug("count=" + count);
            return;
        }
        this.counterPanel.display("" + count);
        System.out.println(new Date() + " count:" + count);
    }
    
    public void stop() {
        this.keepRunning = false;
        this.printDebug("counter done.");
    }
    
    public void run() {
        while (this.keepRunning) {
            try {
                Thread.sleep(this.sleepSeconds * 1000);
            }
            catch (InterruptedException ex) {}
            this.start();
        }
    }
    
    public String getParm(final String s) {
        return this.counterConfig.get(s);
    }
    
    public Color parseColor(final String s, final Color color) {
        if (s == null) {
            return color;
        }
        try {
            return new Color(Integer.parseInt(s, 16));
        }
        catch (NumberFormatException ex) {
            return color;
        }
    }
    
    public void cruiseTo(final URL url) {
        final String s = "_blank";
        if (url == null) {
            return;
        }
        this.showStatus("ParaChat: " + url);
        try {
            this.getAppletContext().showDocument(url, s);
        }
        catch (Exception ex) {
            System.out.println("failed to load, " + url);
        }
    }
    
    public static void fireURL(final String s) {
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            return;
        }
        try {
            ChatCounter.staticApplet.getAppletContext().showDocument(url, "_blank");
        }
        catch (Exception ex2) {}
    }
    
    public Vector getData(final String s) {
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            System.out.println("Error#573," + s);
            return null;
        }
        return this.getWebData(url);
    }
    
    public Vector getWebData(final URL url) {
        final Vector<String> vector = new Vector<String>(8);
        try {
            String line;
            while ((line = new DataInputStream(url.openStream()).readLine()) != null) {
                this.printDebug("line," + line);
                vector.addElement(line);
            }
            return vector;
        }
        catch (IOException ex) {
            System.out.println("Error#473," + url);
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            System.out.println("Error#474," + url);
            ex2.printStackTrace();
        }
        return null;
    }
    
    private String getLookup() {
        final String parm = this.getParm("query.Server");
        if (parm != null) {
            return "?server=" + URLEncoder.encode(parm);
        }
        final String parm2 = this.getParm("query.Site");
        if (parm2 == null) {
            return null;
        }
        String s = "?site=" + URLEncoder.encode(parm2);
        final String parm3 = this.getParm("query.Room");
        if (parm3 != null) {
            s = s + "&room=" + URLEncoder.encode(parm3);
        }
        return s;
    }
    
    private String getTarget() {
        final String parm = this.getParm("query.Server");
        if (parm != null) {
            return parm;
        }
        final String parm2 = this.getParm("query.Room");
        if (parm2 != null) {
            return parm2;
        }
        final String parm3 = this.getParm("query.Site");
        if (parm3 != null) {
            return parm3;
        }
        return null;
    }
    
    private String getQuery() {
        final String lookup = this.getLookup();
        if (lookup == null) {
            return null;
        }
        return lookup;
    }
    
    private URL constructURL() {
        final String query = this.getQuery();
        if (query == null) {
            return null;
        }
        String parm = this.getParm("query.CGI");
        if (parm == null) {
            parm = "counter.php";
        }
        final String string = parm + query;
        String s = this.getParm("query.Host");
        if (s == null) {
            s = this.getCodeBase().toExternalForm();
        }
        String s2;
        if (s.endsWith("/")) {
            s2 = s + string;
        }
        else {
            s2 = s + "/" + string;
        }
        this.printDebug("URL," + s2);
        URL url;
        try {
            url = new URL(s2);
        }
        catch (MalformedURLException ex) {
            System.out.println("Error#353," + ex);
            return null;
        }
        this.printDebug("query," + url);
        return url;
    }
    
    private int getCount() {
        if (this.getLookup() == null) {
            this.printDebug("no lookup");
            return -1;
        }
        final URL constructURL = this.constructURL();
        if (constructURL == null) {
            this.printDebug("url=" + constructURL);
            return -1;
        }
        final Vector webData = this.getWebData(constructURL);
        if (webData == null) {
            this.printDebug("no data");
            return -1;
        }
        int param = -1;
        for (int i = 0; i < webData.size(); ++i) {
            final String s = webData.elementAt(i);
            if (!s.startsWith("#")) {
                param = this.parseParam(s, this.getTarget());
                if (param >= 0) {
                    return param;
                }
            }
        }
        return param;
    }
    
    private int parseParam(final String s, final String s2) {
        final int index = s.indexOf("=");
        if (index < 0) {
            this.printDebug("param=" + s);
            return -1;
        }
        final String substring = s.substring(0, index);
        if (index + 1 >= s.length()) {
            this.printDebug("parameter=" + s);
            return -1;
        }
        final String substring2 = s.substring(index + 1);
        int int1;
        try {
            int1 = Integer.parseInt(substring);
        }
        catch (NumberFormatException ex) {
            System.out.println("#Err847. num," + substring);
            int1 = -1;
        }
        this.printDebug("got," + substring2 + ". queried," + s2);
        if (substring2.equals(s2)) {
            return int1;
        }
        this.printDebug("Err746, got," + substring2 + ". queried," + s2);
        return -1;
    }
    
    private void buildGUI() {
        this.setColors();
        this.counterPanel = new CounterPanel(this);
        this.setLayout(new BorderLayout(1, 1));
        this.add("Center", this.counterPanel);
    }
    
    private void setColors() {
        final String parm = this.getParm("ui.fg");
        final String parm2 = this.getParm("ui.bg");
        Color color = null;
        if (parm != null) {
            color = this.parseColor(parm, Color.black);
        }
        Color color2 = null;
        if (parm2 != null) {
            color2 = this.parseColor(parm2, Color.white);
        }
        if (color != null) {
            this.setForeground(color);
        }
        if (color2 != null) {
            this.setBackground(color2);
        }
    }
    
    private void printDebug(final String s) {
        if (!this.isDebug) {
            return;
        }
        System.out.println(s);
    }
}
