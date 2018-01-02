// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.Component;
import COM.NextBus.util.f;
import java.util.Collections;
import java.util.HashMap;
import java.awt.Image;
import java.util.Map;

public class Applet extends java.applet.Applet
{
    private static final long serialVersionUID = 5986177923281883027L;
    private static final String[][] a;
    private static final String[][] b;
    private boolean _started;
    private O _map;
    private String _webServer;
    private Map _options;
    private Image _vehicleImage;
    private Image _signImage;
    
    public Applet() {
        this._started = false;
    }
    
    public void init() {
        this.requestFocus();
        if (this._vehicleImage == null) {
            this._vehicleImage = this.getImage(this.getDocumentBase(), "busImage.gif");
        }
        if (this._signImage == null) {
            this._signImage = this.getImage(this.getDocumentBase(), "stopImage.gif");
        }
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    public T a() {
        return T.b;
    }
    
    public synchronized void start() {
        this.requestFocus();
        if (this._started) {
            return;
        }
        this._map = null;
        this._started = true;
        try {
            this._webServer = this.getParameter("webServer");
            if (this._webServer == null) {
                this._webServer = this.getDocumentBase().getHost();
            }
            if (this._webServer == null || this._webServer.length() < 1) {
                this._webServer = "localhost";
                this._webServer = System.getProperty("nextbus.webserver", this._webServer);
            }
            final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
            for (int i = 0; i < Applet.a.length; ++i) {
                final String s = Applet.a[i][0];
                final String parameter;
                if ((parameter = this.getParameter(s)) != null) {
                    hashMap.put(s, parameter);
                }
            }
            for (int j = 0; j < Applet.b.length; ++j) {
                final String s2 = Applet.b[j][0];
                final String parameter2;
                if ((parameter2 = this.getParameter(s2)) != null) {
                    hashMap.put(s2, parameter2);
                }
            }
            this._options = Collections.unmodifiableMap((Map<?, ?>)hashMap);
            COM.NextBus.AdminMap.a.a(this._options.get("lang"));
            (this._map = new O(this, this._webServer, this._options, this.a())).a();
            this._map.H();
            this._map.f.a(this._map.J().d());
            if (!f.a() && !this._map.h()) {
                System.out.println("Notice: An old version of Java is installed on your computer which contains outdated timezone information.  NextBus recommends that you upgrade to Java 1.5.0 update 11 or newer, so that dates and times will be displayed properly on the map.\n\nYou can download Java from: http://java.com/getjava/index.jsp\n\nNew Daylight Saving Time rules go into effect March 11, 2007.");
                OKDialog.a("Notice", "Notice: An old version of Java is installed on your computer which contains outdated timezone information.  NextBus recommends that you upgrade to Java 1.5.0 update 11 or newer, so that dates and times will be displayed properly on the map.\n\nYou can download Java from: http://java.com/getjava/index.jsp\n\nNew Daylight Saving Time rules go into effect March 11, 2007.", this._map.f, this._map.x());
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            if (this._map != null) {
                this._map.I();
                this._map = null;
            }
            this._started = false;
        }
        this.notifyAll();
    }
    
    public void stop() {
    }
    
    public void destroy() {
        this._webServer = null;
        while (this._started && this._map == null) {
            try {
                this.wait();
            }
            catch (InterruptedException ex) {
                this._started = false;
            }
        }
        this._started = false;
        if (this._map != null) {
            this._map.I();
        }
        this._map = null;
        super.destroy();
    }
    
    public String[][] getParameterInfo() {
        return Applet.a;
    }
    
    public final Image b() {
        return this._vehicleImage;
    }
    
    public final Image c() {
        return this._signImage;
    }
    
    static {
        a = new String[][] { { "agency", "string", "The name of the agency (can be a colon-separated list)." }, { "agencyTitle", "string", "The title of the agency." }, { "timezone", "string", "The timezone for the agency (default is: PST8PDT)" }, { "localcache", "string", "Directory where map tiles are cached" }, { "includeroutes", "string", "A colon-separated list of route names to include (i.e., K:L:M)." }, { "useShortRouteNamesForIcons", "boolean", "For MUNI. Uses short route names for public map" }, { "kioskMode", "boolean", "For kiosks don't do popups etc." }, { "alternativeNameForJob", "string", "So MUNI can get \"train/run/line\" terminology." }, { "alternativeNameForRoute", "string", "So MUNI can get \"train/run/line\" terminology." }, { "wordForArriving", "string", "So TTC can use the word Due instead of Arriving." }, { "wordForDeparting", "string", "So TTC can use the word Due instead of Departing." }, { "lang", "string", "So can use user specified language." } };
        b = new String[][] { { "extraload", "integer", "A load-testing parameter." }, { "interval", "integer", "Run-time control over the polling interval." }, { "replay", "boolean", "Replay." }, { "ssl", "boolean", "Use secure connection." }, { "JSESSIONID", "string", "Session id." }, { "authenticate", "boolean", "Authenticate." }, { "adherence", "boolean", "Enables adherence view checkbox." }, { "login", "string", "For when applet not run in browser and login not stored in session." }, { "webServer", "string", "For when map applet is loaded from a different server than for the html page" }, { "shouldShowPredictions", "boolean", "Set to false if want to not display predictions" }, { "test", "boolean", "Test mode, displays additional test info" }, { "muniCustomMap", "boolean", "For custom Muni displays" }, { "muniCustomMapScheduleAdherence", "boolean", "For custom Muni displays" }, { "muniCustomMapHideLabels", "boolean", "For custom Muni displays" }, { "headwayMinRange", "string", "Bottom or range of optimal headway" }, { "headwayMaxRange", "string", "Top of range of optimal headway" } };
    }
}
