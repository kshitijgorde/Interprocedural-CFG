// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Enumeration;
import java.applet.AppletContext;
import java.util.Hashtable;
import java.awt.Component;
import java.net.URL;
import java.applet.Applet;
import java.applet.AppletStub;

public class NFContext implements AppletStub
{
    protected Applet applet;
    protected URL documentBase;
    protected URL codeBase;
    protected Component topLevel;
    protected Hashtable authInfo;
    protected static NFContext defaultContext;
    public static final int NETCHARTS = 0;
    public static final String NETCHARTS_AGENT;
    public static final int DESIGNER = 1;
    public static final String DESIGNER_AGENT = "ChartWorks Designer";
    public static final int VIEWER = 2;
    public static final String VIEWER_AGENT = "ChartWorks Viewer";
    public static final int SERVER = 3;
    public static final String SERVER_AGENT = "NetCharts Server";
    public static final int CUSTOM = 100;
    public static final String CUSTOM_AGENT = "ChartWorks Custom Application";
    protected static int userAgentType;
    protected static String userAgent;
    protected static Hashtable agents;
    
    public NFContext() {
        this.applet = null;
        this.documentBase = null;
        this.codeBase = null;
        this.topLevel = null;
        this.authInfo = new Hashtable();
    }
    
    public NFContext(final Applet applet) {
        this();
        this.setApplet(applet);
    }
    
    public NFContext(final URL url) {
        this(url, null);
    }
    
    public NFContext(final URL documentBase, final URL codeBase) {
        this.applet = null;
        this.documentBase = null;
        this.codeBase = null;
        this.topLevel = null;
        this.authInfo = new Hashtable();
        this.documentBase = documentBase;
        this.codeBase = codeBase;
    }
    
    public static NFContext getDefault() {
        return NFContext.defaultContext;
    }
    
    public static void setDefault(final NFContext defaultContext) {
        NFContext.defaultContext = defaultContext;
    }
    
    public boolean isActive() {
        this.a(this + " isActive");
        return true;
    }
    
    public URL getDocumentBase() {
        this.a(this + " getDocumentBase");
        this.a("returning: " + this.documentBase);
        return this.documentBase;
    }
    
    public void setDocumentBase(final URL documentBase) {
        this.documentBase = documentBase;
    }
    
    public URL getCodeBase() {
        this.a(this + " getCodeBase");
        this.a("returning: " + this.codeBase);
        return this.codeBase;
    }
    
    public void setCodeBase(final URL codeBase) {
        this.codeBase = codeBase;
    }
    
    public String getParameter(final String s) {
        this.a(this + " getParameter(" + s + ")");
        return null;
    }
    
    public AppletContext getAppletContext() {
        this.a(this + " getAppletContext()");
        return null;
    }
    
    public void appletResize(final int n, final int n2) {
        this.a(this + " appletResize(" + n + "," + n2 + ")");
    }
    
    public static String getUserAgent() {
        return NFContext.userAgent;
    }
    
    public static void setUserAgent(final String userAgent) {
        NFContext.userAgent = userAgent;
    }
    
    public static int getUserAgentType() {
        return NFContext.userAgentType;
    }
    
    public static void setUserAgentType(final int userAgentType) {
        NFContext.userAgentType = userAgentType;
        final String userAgent;
        if ((userAgent = NFContext.agents.get(new Integer(NFContext.userAgentType))) != null) {
            setUserAgent(userAgent);
        }
    }
    
    public Applet getApplet() {
        return this.applet;
    }
    
    public void setApplet(final Applet applet) {
        this.applet = applet;
        if (this.topLevel == null) {
            this.setTopLevel(applet);
        }
        try {
            this.setDocumentBase(applet.getDocumentBase());
            this.setCodeBase(applet.getCodeBase());
        }
        catch (Exception ex) {}
    }
    
    public Component getTopLevel() {
        return this.topLevel;
    }
    
    public void setTopLevel(final Component topLevel) {
        if (topLevel == null || topLevel.getParent() == null) {
            this.topLevel = topLevel;
            return;
        }
        this.setTopLevel(topLevel.getParent());
    }
    
    public void setDefaultAuthInfo(final String s, final String s2) {
        this.authInfo.put("*", new String[] { s, s2 });
    }
    
    public void setAuthInfo(final String s, final int n, final String s2, final String s3) {
        this.authInfo.put(s + ":" + n, new String[] { s2, s3 });
    }
    
    public String[] getAuthInfo(final String s, final int n) {
        final String[] array = this.authInfo.get(s + ":" + n);
        if (array == null) {
            return this.authInfo.get("*");
        }
        return array;
    }
    
    public void mergeAuthInfo(final NFContext nfContext) {
        mergeAuthInfo(this, nfContext);
    }
    
    public static void mergeAuthInfo(final NFContext nfContext, final NFContext nfContext2) {
        final Enumeration<String> keys = nfContext2.authInfo.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            if (nfContext.authInfo.get(s) != null) {
                continue;
            }
            final String[] array = nfContext2.authInfo.get(s);
            if (array == null) {
                continue;
            }
            nfContext.authInfo.put(new String(s), new String[] { new String(array[0]), new String(array[1]) });
        }
    }
    
    private void a(final String s) {
        NFDebug.print(64L, s);
    }
    
    static {
        NFContext.defaultContext = new NFContext();
        NETCHARTS_AGENT = "NetCharts " + NFVersion.version;
        NFContext.userAgentType = 0;
        NFContext.userAgent = NFContext.NETCHARTS_AGENT;
        (NFContext.agents = new Hashtable()).put(new Integer(0), NFContext.NETCHARTS_AGENT);
        NFContext.agents.put(NFContext.NETCHARTS_AGENT, new Integer(0));
        NFContext.agents.put(new Integer(2), "ChartWorks Viewer");
        NFContext.agents.put("ChartWorks Viewer", new Integer(2));
        NFContext.agents.put(new Integer(1), "ChartWorks Designer");
        NFContext.agents.put("ChartWorks Designer", new Integer(1));
        NFContext.agents.put(new Integer(3), "NetCharts Server");
        NFContext.agents.put("NetCharts Server", new Integer(3));
        NFContext.agents.put(new Integer(100), "ChartWorks Custom Application");
        NFContext.agents.put("ChartWorks Custom Application", new Integer(100));
    }
}
