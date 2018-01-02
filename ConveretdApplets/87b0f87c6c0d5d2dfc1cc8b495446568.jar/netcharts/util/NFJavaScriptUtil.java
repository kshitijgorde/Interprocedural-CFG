// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.applet.Applet;
import java.applet.AppletContext;
import java.net.URL;

public class NFJavaScriptUtil
{
    protected static final String JAVASCRIPT_PROTOCOL = "javascript:";
    protected static final String WRAPPER_URL_PROTOCOL = "http";
    protected static final String WRAPPER_URL_HOST = "javascript";
    protected static final String WRAPPER_URL_FILE = "/javascript.html?";
    protected static final String JAVASCRIPT_CLASS = "netscape.javascript.JSObject";
    protected static final String JAVASCRIPT_GET_INSTANCE_METHOD = "getWindow";
    protected static final String JAVASCRIPT_EVAL_METHOD = "eval";
    static /* synthetic */ Class a;
    
    public static boolean isJavaScriptURL(final String s) {
        return s != null && s.trim().toLowerCase().startsWith("javascript:");
    }
    
    public static boolean needsToBeWrapped(final String s) {
        try {
            new URL(s);
            return false;
        }
        catch (Exception ex) {
            return true;
        }
    }
    
    public static boolean isWrappedJavaScriptURL(final URL url) {
        return url != null && url.getProtocol().equals("http") && url.getHost().equals("javascript") && url.getFile().startsWith("/javascript.html?");
    }
    
    public static URL wrapJavaScriptURL(final String s) {
        try {
            return new URL("http", "javascript", "/javascript.html?" + s);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String unwrapJavaScriptURL(final URL url) {
        if (!isWrappedJavaScriptURL(url)) {
            return null;
        }
        return url.getFile().substring("/javascript.html?".length());
    }
    
    public static String getJavaScriptFunctionFromURL(String s) {
        if (s == null) {
            return null;
        }
        if (s.startsWith("javascript:")) {
            s = s.substring("javascript:".length());
        }
        while (s.charAt(0) == '/') {
            s = s.substring(1);
        }
        return s;
    }
    
    public static void showDocument(final AppletContext appletContext, final URL url) {
        try {
            showDocument(appletContext.getApplets().nextElement(), url);
        }
        catch (Exception ex) {}
    }
    
    public static void showDocument(final Applet applet, final URL url) {
        if (applet == null || !isWrappedJavaScriptURL(url)) {
            return;
        }
        final String javaScriptFunctionFromURL = getJavaScriptFunctionFromURL(unwrapJavaScriptURL(url));
        try {
            final Class<?> forName = Class.forName("netscape.javascript.JSObject");
            final Class[] array = { (NFJavaScriptUtil.a == null) ? (NFJavaScriptUtil.a = class$("java.applet.Applet")) : NFJavaScriptUtil.a };
            final Object[] array2 = { applet };
            final Object invoke = forName.getMethod("getWindow", (Class[])array).invoke(null, array2);
            array[0] = javaScriptFunctionFromURL.getClass();
            array2[0] = javaScriptFunctionFromURL;
            forName.getMethod("eval", (Class[])array).invoke(invoke, array2);
        }
        catch (Exception ex) {}
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
