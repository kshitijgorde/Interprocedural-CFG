// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;

abstract class WebBrowser
{
    Browser browser;
    Hashtable functions;
    AuthenticationListener[] authenticationListeners;
    CloseWindowListener[] closeWindowListeners;
    LocationListener[] locationListeners;
    OpenWindowListener[] openWindowListeners;
    ProgressListener[] progressListeners;
    StatusTextListener[] statusTextListeners;
    TitleListener[] titleListeners;
    VisibilityWindowListener[] visibilityWindowListeners;
    boolean jsEnabledChanged;
    boolean jsEnabled;
    int nextFunctionIndex;
    Object evaluateResult;
    static final String ERROR_ID = "org.eclipse.swt.browser.error";
    static final String EXECUTE_ID = "SWTExecuteTemporaryFunction";
    static Vector NativePendingCookies;
    static Vector MozillaPendingCookies;
    static String CookieName;
    static String CookieValue;
    static String CookieUrl;
    static boolean CookieResult;
    static Runnable MozillaClearSessions;
    static Runnable NativeClearSessions;
    static Runnable MozillaGetCookie;
    static Runnable NativeGetCookie;
    static Runnable MozillaSetCookie;
    static Runnable NativeSetCookie;
    static final int[][] KeyTable;
    
    static {
        WebBrowser.NativePendingCookies = new Vector();
        WebBrowser.MozillaPendingCookies = new Vector();
        KeyTable = new int[][] { { 18, 65536 }, { 16, 131072 }, { 17, 262144 }, { 224, 4194304 }, { 65, 97 }, { 66, 98 }, { 67, 99 }, { 68, 100 }, { 69, 101 }, { 70, 102 }, { 71, 103 }, { 72, 104 }, { 73, 105 }, { 74, 106 }, { 75, 107 }, { 76, 108 }, { 77, 109 }, { 78, 110 }, { 79, 111 }, { 80, 112 }, { 81, 113 }, { 82, 114 }, { 83, 115 }, { 84, 116 }, { 85, 117 }, { 86, 118 }, { 87, 119 }, { 88, 120 }, { 89, 121 }, { 90, 122 }, { 48, 48 }, { 49, 49 }, { 50, 50 }, { 51, 51 }, { 52, 52 }, { 53, 53 }, { 54, 54 }, { 55, 55 }, { 56, 56 }, { 57, 57 }, { 32, 32 }, { 59, 59 }, { 61, 61 }, { 188, 44 }, { 190, 46 }, { 191, 47 }, { 219, 91 }, { 221, 93 }, { 222, 39 }, { 192, 96 }, { 220, 92 }, { 108, 124 }, { 37, 16777219 }, { 39, 16777220 }, { 38, 16777217 }, { 40, 16777218 }, { 45, 16777225 }, { 36, 16777223 }, { 35, 16777224 }, { 46, 127 }, { 33, 16777221 }, { 34, 16777222 }, { 8, 8 }, { 13, 13 }, { 9, 9 }, { 27, 27 }, { 12, 127 }, { 112, 16777226 }, { 113, 16777227 }, { 114, 16777228 }, { 115, 16777229 }, { 116, 16777230 }, { 117, 16777231 }, { 118, 16777232 }, { 119, 16777233 }, { 120, 16777234 }, { 121, 16777235 }, { 122, 16777236 }, { 123, 16777237 }, { 124, 16777238 }, { 125, 16777239 }, { 126, 16777240 }, { 127, 0 }, { 128, 0 }, { 129, 0 }, { 130, 0 }, { 131, 0 }, { 132, 0 }, { 133, 0 }, { 134, 0 }, { 135, 0 }, { 96, 16777264 }, { 97, 16777265 }, { 98, 16777266 }, { 99, 16777267 }, { 100, 16777268 }, { 101, 16777269 }, { 102, 16777270 }, { 103, 16777271 }, { 104, 16777272 }, { 105, 16777273 }, { 14, 16777296 }, { 107, 16777259 }, { 109, 16777261 }, { 106, 16777258 }, { 111, 16777263 }, { 110, 16777262 }, { 20, 16777298 }, { 144, 16777299 }, { 145, 16777300 }, { 44, 16777303 }, { 6, 16777297 }, { 19, 16777301 }, { 3, 16777302 }, { 186, 59 }, { 187, 61 }, { 189, 45 } };
    }
    
    WebBrowser() {
        this.functions = new Hashtable();
        this.authenticationListeners = new AuthenticationListener[0];
        this.closeWindowListeners = new CloseWindowListener[0];
        this.locationListeners = new LocationListener[0];
        this.openWindowListeners = new OpenWindowListener[0];
        this.progressListeners = new ProgressListener[0];
        this.statusTextListeners = new StatusTextListener[0];
        this.titleListeners = new TitleListener[0];
        this.visibilityWindowListeners = new VisibilityWindowListener[0];
        this.jsEnabled = true;
        this.nextFunctionIndex = 1;
    }
    
    public void addAuthenticationListener(final AuthenticationListener authenticationListener) {
        final AuthenticationListener[] authenticationListeners = new AuthenticationListener[this.authenticationListeners.length + 1];
        System.arraycopy(this.authenticationListeners, 0, authenticationListeners, 0, this.authenticationListeners.length);
        (this.authenticationListeners = authenticationListeners)[this.authenticationListeners.length - 1] = authenticationListener;
    }
    
    public void addCloseWindowListener(final CloseWindowListener closeWindowListener) {
        final CloseWindowListener[] closeWindowListeners = new CloseWindowListener[this.closeWindowListeners.length + 1];
        System.arraycopy(this.closeWindowListeners, 0, closeWindowListeners, 0, this.closeWindowListeners.length);
        (this.closeWindowListeners = closeWindowListeners)[this.closeWindowListeners.length - 1] = closeWindowListener;
    }
    
    public void addLocationListener(final LocationListener locationListener) {
        final LocationListener[] locationListeners = new LocationListener[this.locationListeners.length + 1];
        System.arraycopy(this.locationListeners, 0, locationListeners, 0, this.locationListeners.length);
        (this.locationListeners = locationListeners)[this.locationListeners.length - 1] = locationListener;
    }
    
    public void addOpenWindowListener(final OpenWindowListener openWindowListener) {
        final OpenWindowListener[] openWindowListeners = new OpenWindowListener[this.openWindowListeners.length + 1];
        System.arraycopy(this.openWindowListeners, 0, openWindowListeners, 0, this.openWindowListeners.length);
        (this.openWindowListeners = openWindowListeners)[this.openWindowListeners.length - 1] = openWindowListener;
    }
    
    public void addProgressListener(final ProgressListener progressListener) {
        final ProgressListener[] progressListeners = new ProgressListener[this.progressListeners.length + 1];
        System.arraycopy(this.progressListeners, 0, progressListeners, 0, this.progressListeners.length);
        (this.progressListeners = progressListeners)[this.progressListeners.length - 1] = progressListener;
    }
    
    public void addStatusTextListener(final StatusTextListener statusTextListener) {
        final StatusTextListener[] statusTextListeners = new StatusTextListener[this.statusTextListeners.length + 1];
        System.arraycopy(this.statusTextListeners, 0, statusTextListeners, 0, this.statusTextListeners.length);
        (this.statusTextListeners = statusTextListeners)[this.statusTextListeners.length - 1] = statusTextListener;
    }
    
    public void addTitleListener(final TitleListener titleListener) {
        final TitleListener[] titleListeners = new TitleListener[this.titleListeners.length + 1];
        System.arraycopy(this.titleListeners, 0, titleListeners, 0, this.titleListeners.length);
        (this.titleListeners = titleListeners)[this.titleListeners.length - 1] = titleListener;
    }
    
    public void addVisibilityWindowListener(final VisibilityWindowListener visibilityWindowListener) {
        final VisibilityWindowListener[] visibilityWindowListeners = new VisibilityWindowListener[this.visibilityWindowListeners.length + 1];
        System.arraycopy(this.visibilityWindowListeners, 0, visibilityWindowListeners, 0, this.visibilityWindowListeners.length);
        (this.visibilityWindowListeners = visibilityWindowListeners)[this.visibilityWindowListeners.length - 1] = visibilityWindowListener;
    }
    
    public abstract boolean back();
    
    public static void clearSessions() {
        if (WebBrowser.NativeClearSessions != null) {
            WebBrowser.NativeClearSessions.run();
        }
        if (WebBrowser.MozillaClearSessions != null) {
            WebBrowser.MozillaClearSessions.run();
        }
    }
    
    public static String GetCookie(final String cookieName, final String cookieUrl) {
        WebBrowser.CookieName = cookieName;
        WebBrowser.CookieUrl = cookieUrl;
        WebBrowser.CookieValue = null;
        if (WebBrowser.NativeGetCookie != null) {
            WebBrowser.NativeGetCookie.run();
        }
        if (WebBrowser.CookieValue == null && WebBrowser.MozillaGetCookie != null) {
            WebBrowser.MozillaGetCookie.run();
        }
        final String cookieValue = WebBrowser.CookieValue;
        WebBrowser.CookieName = (WebBrowser.CookieValue = (WebBrowser.CookieUrl = null));
        return cookieValue;
    }
    
    public static boolean SetCookie(final String cookieValue, final String cookieUrl, final boolean b) {
        WebBrowser.CookieValue = cookieValue;
        WebBrowser.CookieUrl = cookieUrl;
        WebBrowser.CookieResult = false;
        if (WebBrowser.NativeSetCookie != null) {
            WebBrowser.NativeSetCookie.run();
        }
        else if (b && WebBrowser.NativePendingCookies != null) {
            WebBrowser.NativePendingCookies.add(new String[] { cookieValue, cookieUrl });
        }
        if (WebBrowser.MozillaSetCookie != null) {
            WebBrowser.MozillaSetCookie.run();
        }
        else if (b && WebBrowser.MozillaPendingCookies != null) {
            WebBrowser.MozillaPendingCookies.add(new String[] { cookieValue, cookieUrl });
        }
        WebBrowser.CookieValue = (WebBrowser.CookieUrl = null);
        return WebBrowser.CookieResult;
    }
    
    static void SetPendingCookies(final Vector vector) {
        final Enumeration<String[]> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final String[] array = elements.nextElement();
            SetCookie(array[0], array[1], false);
        }
    }
    
    public abstract void create(final Composite p0, final int p1);
    
    static String CreateErrorString(final String s) {
        return "org.eclipse.swt.browser.error" + s;
    }
    
    static String ExtractError(final String s) {
        return s.substring("org.eclipse.swt.browser.error".length());
    }
    
    public boolean close() {
        return true;
    }
    
    public void createFunction(final BrowserFunction browserFunction) {
        final Enumeration<Object> keys = this.functions.keys();
        while (keys.hasMoreElements()) {
            final BrowserFunction browserFunction2 = this.functions.get(keys.nextElement());
            if (browserFunction2.name.equals(browserFunction.name)) {
                this.deregisterFunction(browserFunction2);
                break;
            }
        }
        browserFunction.index = this.getNextFunctionIndex();
        this.registerFunction(browserFunction);
        final StringBuffer sb = new StringBuffer("window.");
        sb.append(browserFunction.name);
        sb.append(" = function ");
        sb.append(browserFunction.name);
        sb.append("() {var result = window.external.callJava(");
        sb.append(browserFunction.index);
        sb.append(",Array.prototype.slice.call(arguments)); if (typeof result == 'string' && result.indexOf('");
        sb.append("org.eclipse.swt.browser.error");
        sb.append("') == 0) {var error = new Error(result.substring(");
        sb.append("org.eclipse.swt.browser.error".length());
        sb.append(")); throw error;} return result;};");
        sb.append("for (var i = 0; i < frames.length; i++) {try { frames[i].");
        sb.append(browserFunction.name);
        sb.append(" = window.");
        sb.append(browserFunction.name);
        sb.append(";} catch (e) {} };");
        this.execute(browserFunction.functionString = sb.toString());
    }
    
    void deregisterFunction(final BrowserFunction browserFunction) {
        this.functions.remove(new Integer(browserFunction.index));
    }
    
    public void destroyFunction(final BrowserFunction browserFunction) {
        final String deleteFunctionString = this.getDeleteFunctionString(browserFunction.name);
        final StringBuffer sb = new StringBuffer("for (var i = 0; i < frames.length; i++) {try {frames[i].eval(\"");
        sb.append(deleteFunctionString);
        sb.append("\");} catch (e) {}}");
        this.execute(sb.toString());
        this.execute(deleteFunctionString);
        this.deregisterFunction(browserFunction);
    }
    
    public abstract boolean execute(final String p0);
    
    public Object evaluate(final String s) throws SWTException {
        final EvaluateFunction evaluateFunction = new EvaluateFunction(this.browser, "");
        final int nextFunctionIndex = this.getNextFunctionIndex();
        evaluateFunction.index = nextFunctionIndex;
        evaluateFunction.isEvaluate = true;
        this.registerFunction(evaluateFunction);
        final String string = "SWTExecuteTemporaryFunction" + nextFunctionIndex;
        final StringBuffer sb = new StringBuffer("window.");
        sb.append(string);
        sb.append(" = function ");
        sb.append(string);
        sb.append("() {\n");
        sb.append(s);
        sb.append("\n};");
        this.execute(sb.toString());
        final StringBuffer sb2 = new StringBuffer("if (window.");
        sb2.append(string);
        sb2.append(" == undefined) {window.external.callJava(");
        sb2.append(nextFunctionIndex);
        sb2.append(", ['");
        sb2.append("org.eclipse.swt.browser.error");
        sb2.append("']);} else {try {var result = ");
        sb2.append(string);
        sb2.append("(); window.external.callJava(");
        sb2.append(nextFunctionIndex);
        sb2.append(", [result]);} catch (e) {window.external.callJava(");
        sb2.append(nextFunctionIndex);
        sb2.append(", ['");
        sb2.append("org.eclipse.swt.browser.error");
        sb2.append("' + e.message]);}}");
        this.execute(sb2.toString());
        this.execute(this.getDeleteFunctionString(string));
        this.deregisterFunction(evaluateFunction);
        final Object evaluateResult = this.evaluateResult;
        this.evaluateResult = null;
        if (evaluateResult instanceof SWTException) {
            throw (SWTException)evaluateResult;
        }
        return evaluateResult;
    }
    
    public abstract boolean forward();
    
    public abstract String getBrowserType();
    
    String getDeleteFunctionString(final String s) {
        return "delete window." + s;
    }
    
    int getNextFunctionIndex() {
        return this.nextFunctionIndex++;
    }
    
    public abstract String getText();
    
    public abstract String getUrl();
    
    public Object getWebBrowser() {
        return null;
    }
    
    public abstract boolean isBackEnabled();
    
    public boolean isFocusControl() {
        return false;
    }
    
    public abstract boolean isForwardEnabled();
    
    public abstract void refresh();
    
    void registerFunction(final BrowserFunction browserFunction) {
        this.functions.put(new Integer(browserFunction.index), browserFunction);
    }
    
    public void removeAuthenticationListener(final AuthenticationListener authenticationListener) {
        if (this.authenticationListeners.length == 0) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.authenticationListeners.length; ++i) {
            if (authenticationListener == this.authenticationListeners[i]) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        if (this.authenticationListeners.length == 1) {
            this.authenticationListeners = new AuthenticationListener[0];
            return;
        }
        final AuthenticationListener[] authenticationListeners = new AuthenticationListener[this.authenticationListeners.length - 1];
        System.arraycopy(this.authenticationListeners, 0, authenticationListeners, 0, n);
        System.arraycopy(this.authenticationListeners, n + 1, authenticationListeners, n, this.authenticationListeners.length - n - 1);
        this.authenticationListeners = authenticationListeners;
    }
    
    public void removeCloseWindowListener(final CloseWindowListener closeWindowListener) {
        if (this.closeWindowListeners.length == 0) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.closeWindowListeners.length; ++i) {
            if (closeWindowListener == this.closeWindowListeners[i]) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        if (this.closeWindowListeners.length == 1) {
            this.closeWindowListeners = new CloseWindowListener[0];
            return;
        }
        final CloseWindowListener[] closeWindowListeners = new CloseWindowListener[this.closeWindowListeners.length - 1];
        System.arraycopy(this.closeWindowListeners, 0, closeWindowListeners, 0, n);
        System.arraycopy(this.closeWindowListeners, n + 1, closeWindowListeners, n, this.closeWindowListeners.length - n - 1);
        this.closeWindowListeners = closeWindowListeners;
    }
    
    public void removeLocationListener(final LocationListener locationListener) {
        if (this.locationListeners.length == 0) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.locationListeners.length; ++i) {
            if (locationListener == this.locationListeners[i]) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        if (this.locationListeners.length == 1) {
            this.locationListeners = new LocationListener[0];
            return;
        }
        final LocationListener[] locationListeners = new LocationListener[this.locationListeners.length - 1];
        System.arraycopy(this.locationListeners, 0, locationListeners, 0, n);
        System.arraycopy(this.locationListeners, n + 1, locationListeners, n, this.locationListeners.length - n - 1);
        this.locationListeners = locationListeners;
    }
    
    public void removeOpenWindowListener(final OpenWindowListener openWindowListener) {
        if (this.openWindowListeners.length == 0) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.openWindowListeners.length; ++i) {
            if (openWindowListener == this.openWindowListeners[i]) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        if (this.openWindowListeners.length == 1) {
            this.openWindowListeners = new OpenWindowListener[0];
            return;
        }
        final OpenWindowListener[] openWindowListeners = new OpenWindowListener[this.openWindowListeners.length - 1];
        System.arraycopy(this.openWindowListeners, 0, openWindowListeners, 0, n);
        System.arraycopy(this.openWindowListeners, n + 1, openWindowListeners, n, this.openWindowListeners.length - n - 1);
        this.openWindowListeners = openWindowListeners;
    }
    
    public void removeProgressListener(final ProgressListener progressListener) {
        if (this.progressListeners.length == 0) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.progressListeners.length; ++i) {
            if (progressListener == this.progressListeners[i]) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        if (this.progressListeners.length == 1) {
            this.progressListeners = new ProgressListener[0];
            return;
        }
        final ProgressListener[] progressListeners = new ProgressListener[this.progressListeners.length - 1];
        System.arraycopy(this.progressListeners, 0, progressListeners, 0, n);
        System.arraycopy(this.progressListeners, n + 1, progressListeners, n, this.progressListeners.length - n - 1);
        this.progressListeners = progressListeners;
    }
    
    public void removeStatusTextListener(final StatusTextListener statusTextListener) {
        if (this.statusTextListeners.length == 0) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.statusTextListeners.length; ++i) {
            if (statusTextListener == this.statusTextListeners[i]) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        if (this.statusTextListeners.length == 1) {
            this.statusTextListeners = new StatusTextListener[0];
            return;
        }
        final StatusTextListener[] statusTextListeners = new StatusTextListener[this.statusTextListeners.length - 1];
        System.arraycopy(this.statusTextListeners, 0, statusTextListeners, 0, n);
        System.arraycopy(this.statusTextListeners, n + 1, statusTextListeners, n, this.statusTextListeners.length - n - 1);
        this.statusTextListeners = statusTextListeners;
    }
    
    public void removeTitleListener(final TitleListener titleListener) {
        if (this.titleListeners.length == 0) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.titleListeners.length; ++i) {
            if (titleListener == this.titleListeners[i]) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        if (this.titleListeners.length == 1) {
            this.titleListeners = new TitleListener[0];
            return;
        }
        final TitleListener[] titleListeners = new TitleListener[this.titleListeners.length - 1];
        System.arraycopy(this.titleListeners, 0, titleListeners, 0, n);
        System.arraycopy(this.titleListeners, n + 1, titleListeners, n, this.titleListeners.length - n - 1);
        this.titleListeners = titleListeners;
    }
    
    public void removeVisibilityWindowListener(final VisibilityWindowListener visibilityWindowListener) {
        if (this.visibilityWindowListeners.length == 0) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.visibilityWindowListeners.length; ++i) {
            if (visibilityWindowListener == this.visibilityWindowListeners[i]) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            return;
        }
        if (this.visibilityWindowListeners.length == 1) {
            this.visibilityWindowListeners = new VisibilityWindowListener[0];
            return;
        }
        final VisibilityWindowListener[] visibilityWindowListeners = new VisibilityWindowListener[this.visibilityWindowListeners.length - 1];
        System.arraycopy(this.visibilityWindowListeners, 0, visibilityWindowListeners, 0, n);
        System.arraycopy(this.visibilityWindowListeners, n + 1, visibilityWindowListeners, n, this.visibilityWindowListeners.length - n - 1);
        this.visibilityWindowListeners = visibilityWindowListeners;
    }
    
    boolean sendKeyEvent(final Event event) {
        int n = 0;
        boolean doit = true;
        switch (event.keyCode) {
            case 27: {
                n = 2;
                doit = true;
                break;
            }
            case 13: {
                n = 4;
                doit = false;
                break;
            }
            case 16777218:
            case 16777220: {
                n = 64;
                doit = false;
                break;
            }
            case 16777217:
            case 16777219: {
                n = 32;
                doit = false;
                break;
            }
            case 9: {
                n = (((event.stateMask & 0x20000) != 0x0) ? 8 : 16);
                doit = ((event.stateMask & 0x40000) != 0x0);
                break;
            }
            case 16777222: {
                if ((event.stateMask & 0x40000) != 0x0) {
                    n = 512;
                    doit = true;
                    break;
                }
                break;
            }
            case 16777221: {
                if ((event.stateMask & 0x40000) != 0x0) {
                    n = 256;
                    doit = true;
                    break;
                }
                break;
            }
            default: {
                if (this.translateMnemonics() && event.character != '\0' && (event.stateMask & 0x50000) == 0x10000) {
                    n = 128;
                    doit = true;
                    break;
                }
                break;
            }
        }
        boolean doit2 = true;
        if (n != 0) {
            final boolean doit3 = event.doit;
            event.doit = doit;
            doit2 = !this.browser.traverse(n, event);
            event.doit = doit3;
        }
        if (doit2) {
            this.browser.notifyListeners(event.type, event);
            doit2 = event.doit;
        }
        return doit2;
    }
    
    public void setBrowser(final Browser browser) {
        this.browser = browser;
    }
    
    public abstract boolean setText(final String p0, final boolean p1);
    
    public abstract boolean setUrl(final String p0, final String p1, final String[] p2);
    
    public abstract void stop();
    
    int translateKey(final int n) {
        for (int i = 0; i < WebBrowser.KeyTable.length; ++i) {
            if (WebBrowser.KeyTable[i][0] == n) {
                return WebBrowser.KeyTable[i][1];
            }
        }
        return 0;
    }
    
    boolean translateMnemonics() {
        return true;
    }
    
    public class EvaluateFunction extends BrowserFunction
    {
        public EvaluateFunction(final Browser browser, final String s) {
            super(browser, s, false);
        }
        
        public Object function(final Object[] array) {
            if (array[0] instanceof String) {
                final String s = (String)array[0];
                if (s.startsWith("org.eclipse.swt.browser.error")) {
                    final String extractError = WebBrowser.ExtractError(s);
                    if (extractError.length() > 0) {
                        WebBrowser.this.evaluateResult = new SWTException(50, extractError);
                    }
                    else {
                        WebBrowser.this.evaluateResult = new SWTException(50);
                    }
                    return null;
                }
            }
            WebBrowser.this.evaluateResult = array[0];
            return null;
        }
    }
}
