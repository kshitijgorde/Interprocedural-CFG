import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Color;
import netscape.javascript.JSObject;
import java.util.StringTokenizer;
import java.net.URL;
import java.awt.Component;
import java.util.Hashtable;
import java.applet.AudioClip;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UBBApplet extends Applet implements UBBListener
{
    private static final String name = "UBBApplet";
    private UBB buttonBar;
    private boolean quickInit;
    private boolean initialized;
    private String definition;
    private String newDefn;
    private String jsMode;
    private String jsStart;
    private String jsStop;
    private String jsEnter;
    private String jsExit;
    private boolean newDefnIsFile;
    private AudioClip playingClip;
    private Hashtable eventObjects;
    UBBErrorHandler errorHandler;
    
    public synchronized void stop() {
        if (this.buttonBar != null) {
            if (this.jsStop != null) {
                this.ubbEvent(null, "javascript", this.jsStop);
            }
            if (this.playingClip != null) {
                this.playingClip.stop();
            }
            this.buttonBar.stop();
            if (!this.quickInit || this.newDefn != null) {
                this.remove(this.buttonBar);
                this.eventObjects.clear();
                this.buttonBar.destroy();
                this.buttonBar = null;
            }
        }
        System.gc();
    }
    
    public void ubbEvent(final String s, String s2, String s3) {
        if (s2 != null && s3 != null) {
            if (s2.equals("enter")) {
                if (this.jsEnter == null) {
                    return;
                }
                s2 = "javascript";
                s3 = this.jsEnter;
            }
            else if (s2.equals("exit")) {
                if (this.jsExit == null) {
                    return;
                }
                s2 = "javascript";
                s3 = this.jsExit;
            }
            if (s2.startsWith("play")) {
                if (this.playingClip != null) {
                    this.playingClip.stop();
                }
                if (s2.endsWith("stop")) {
                    if (this.playingClip != null) {
                        this.playingClip = null;
                        return;
                    }
                    this.errorHandler.notify(s, 1, "playStop: Audio not playing", null);
                }
                else {
                    this.playingClip = this.eventObjects.get(s3);
                    if (this.playingClip == null) {
                        this.playingClip = this.getAudioClip(this.getCodeBase(), s3);
                        this.eventObjects.put(s3, this.playingClip);
                    }
                    if (this.playingClip != null) {
                        if (s2.endsWith("loop")) {
                            this.playingClip.loop();
                            return;
                        }
                        this.playingClip.play();
                    }
                }
            }
            else if (s2.equals("load")) {
                String substring = null;
                int index = 0;
                if (!s3.regionMatches(true, 0, "mailto:", 0, 7)) {
                    index = s3.indexOf(64);
                }
                String s4;
                if (index > 0) {
                    s4 = s3.substring(0, index);
                    substring = s3.substring(index + 1);
                }
                else {
                    s4 = s3;
                }
                URL url = this.eventObjects.get(s3);
                if (url == null) {
                    URL url2;
                    if (s4.charAt(0) == '$') {
                        url2 = this.getCodeBase();
                        s4 = s4.substring(1);
                    }
                    else {
                        url2 = this.getDocumentBase();
                    }
                    try {
                        url = new URL(url2, s4);
                        this.eventObjects.put(s3, url);
                    }
                    catch (Exception ex) {
                        this.errorHandler.notify("UBBApplet", 0, "Bad URL " + s4, ex);
                        return;
                    }
                }
                if (substring != null) {
                    this.getAppletContext().showDocument(url, substring);
                    return;
                }
                this.getAppletContext().showDocument(url);
            }
            else {
                if (s2.equals("status")) {
                    if (s3.toLowerCase().equals("none")) {
                        s3 = "";
                    }
                    this.showStatus(s3);
                    return;
                }
                if (s2.startsWith("javascript")) {
                    String substring2 = null;
                    if (s2.length() > 10) {
                        substring2 = s2.substring(10);
                    }
                    if (substring2 != null) {
                        if (this.jsMode == null || this.jsMode.indexOf(substring2) <= -1) {
                            return;
                        }
                    }
                    try {
                        final int index2 = s3.indexOf(40);
                        final int index3 = s3.indexOf(41);
                        if (index2 < 1 || index3 == -1) {
                            throw new Exception("Badly formed function call");
                        }
                        Object[] array = null;
                        if (s3.charAt(0) == '1') {
                            array = new String[] { s3.trim().substring(index2 + 1, s3.length() - 1) };
                        }
                        else if (index2 < index3 - 1) {
                            final StringTokenizer stringTokenizer = new StringTokenizer(s3.substring(index2 + 1, index3).trim(), ",");
                            final int countTokens = stringTokenizer.countTokens();
                            if (countTokens > 0) {
                                array = new String[countTokens];
                                for (int i = 0; i < countTokens; ++i) {
                                    array[i] = stringTokenizer.nextToken();
                                }
                            }
                        }
                        JSObject.getWindow((Applet)this).call(((s3.charAt(0) == '1') ? s3.substring(1, index2) : s3.substring(0, index2)).trim(), array);
                        return;
                    }
                    catch (Exception ex2) {
                        this.errorHandler.notify(s, 0, "JavaScript function \"" + s3 + "\"", ex2);
                        return;
                    }
                }
                if (s2.startsWith("js")) {
                    if (s2.endsWith("enter")) {
                        this.jsEnter = s3;
                        return;
                    }
                    if (s2.endsWith("exit")) {
                        this.jsExit = s3;
                    }
                }
                else {
                    if (s2.startsWith("log")) {
                        this.errorHandler.notify(s, 2, s3, null);
                        return;
                    }
                    this.errorHandler.notify("UBBApplet", 0, "Unknown action \"" + s2 + "=" + s3 + "\" for " + s, null);
                }
            }
        }
    }
    
    public UBBApplet() {
        this.eventObjects = new Hashtable(23, 0.5f);
        this.errorHandler = new UBBErrorHandler("UBB");
    }
    
    public void destroy() {
        if (this.buttonBar != null) {
            this.remove(this.buttonBar);
            this.eventObjects.clear();
            this.buttonBar.destroy();
            this.buttonBar = null;
        }
    }
    
    public synchronized void command(String lowerCase, final String newDefn) {
        if (this.buttonBar == null) {
            this.errorHandler.notify("UBBApplet", 0, "No button bar for " + lowerCase + "=" + newDefn, null);
            return;
        }
        lowerCase = lowerCase.toLowerCase();
        if (!lowerCase.startsWith("set")) {
            this.buttonBar.ubbEvent(null, new String[][] { { lowerCase, newDefn } });
            return;
        }
        if (lowerCase.endsWith("jsmode")) {
            this.jsMode = newDefn.toLowerCase();
            return;
        }
        if (lowerCase.endsWith("file")) {
            this.newDefnIsFile = true;
        }
        else {
            this.newDefnIsFile = false;
        }
        this.newDefn = newDefn;
        this.stop();
        this.start();
        this.newDefn = null;
    }
    
    public void start() {
        String s = null;
        try {
            synchronized (this) {
                if (!this.initialized || !this.quickInit || this.newDefn != null) {
                    s = this.getParameter("UBBjsInit");
                    this.jsStart = this.getParameter("UBBjsStart");
                    this.jsStop = this.getParameter("UBBjsStop");
                    this.jsEnter = this.getParameter("UBBjsEnter");
                    this.jsExit = this.getParameter("UBBjsExit");
                    String parameter = this.getParameter("UBBappletBg");
                    if (parameter != null) {
                        try {
                            this.setBackground(UBBImageFactory.parseColor(parameter));
                        }
                        catch (Exception ex2) {
                            parameter = "ffffff";
                        }
                    }
                    else {
                        parameter = "ffffff";
                        this.setBackground(Color.white);
                    }
                    int int1 = 0;
                    final String parameter2 = this.getParameter("UBBoption");
                    if (parameter2 != null) {
                        if (parameter2.toLowerCase().charAt(0) == 'd') {
                            int1 = 15;
                        }
                        else {
                            try {
                                int1 = Integer.parseInt(parameter2);
                            }
                            catch (Exception ex3) {
                                int1 = 0;
                            }
                        }
                    }
                    this.errorHandler.reportWarnings((int1 & 0x4) != 0x0);
                    if (int1 == 15) {
                        this.errorHandler.notify("UBBApplet", 1, "DEVELOP mode", null);
                    }
                    if ((int1 & 0x2) == 0x0) {
                        this.quickInit = true;
                    }
                    boolean newDefnIsFile = true;
                    String s2 = null;
                    if (this.newDefn == null) {
                        this.definition = this.getParameter("UBBdefnFile");
                        if (this.definition == null) {
                            newDefnIsFile = false;
                            this.definition = this.getParameter("UBBdefn");
                            if (this.definition == null) {
                                this.definition = "[area bgColor=" + parameter + "][/area]";
                            }
                        }
                    }
                    else {
                        this.definition = this.newDefn;
                        newDefnIsFile = this.newDefnIsFile;
                    }
                    if (!newDefnIsFile) {
                        s2 = "[]^";
                    }
                    int int2 = 100;
                    final String parameter3 = this.getParameter("UBBtickTime");
                    if (parameter3 != null) {
                        try {
                            int2 = Integer.parseInt(parameter3);
                        }
                        catch (Exception ex4) {}
                    }
                    final String parameter4 = this.getParameter("UBBdelimiters");
                    if (parameter4 != null) {
                        s2 = parameter4;
                    }
                    this.buttonBar = new UBB(this.definition, newDefnIsFile, s2, new Dimension(this.getBounds().width, this.getBounds().height), this.getCodeBase(), int2, int1, this.errorHandler);
                    if (this.buttonBar != null) {
                        this.add(this.buttonBar);
                        this.buttonBar.addUBBListener(this);
                    }
                    this.initialized = true;
                }
                if (this.buttonBar != null) {
                    this.buttonBar.start();
                }
            }
        }
        catch (Exception ex) {
            this.buttonBar = null;
            s = ex.toString();
        }
        catch (Error error) {
            this.buttonBar = null;
            s = error.toString();
        }
        if (this.buttonBar != null) {
            final String s3 = (s == null) ? this.jsStart : s;
            if (s3 != null) {
                this.ubbEvent(null, "javascript", s3);
            }
        }
        else {
            final String parameter5 = this.getParameter("UBBinitErr");
            if (parameter5 != null) {
                if (parameter5.indexOf(".htm") > 0) {
                    this.ubbEvent(null, "load", parameter5);
                }
                else {
                    this.ubbEvent(null, "javascript", '1' + parameter5.trim() + '(' + s + ')');
                }
            }
            this.errorHandler.notify("UBBApplet", 0, "Can't initialize because of " + s, null);
        }
    }
    
    public String getDefn() {
        return this.definition;
    }
    
    public void init() {
        this.setLayout(null);
    }
}
