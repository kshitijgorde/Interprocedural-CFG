// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol.njar;

import org.jboss.util.ThrowableHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import org.jboss.util.stream.Streams;
import java.io.FileOutputStream;
import java.io.File;
import java.net.URLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.jboss.logging.Logger;
import java.net.URLStreamHandler;

public class Handler extends URLStreamHandler
{
    public static final String PROTOCOL = "njar";
    public static final String NJAR_SEPARATOR = "^/";
    public static final String JAR_SEPARATOR = "!/";
    private static final Logger log;
    protected Map savedJars;
    static /* synthetic */ Class class$org$jboss$net$protocol$njar$Handler;
    
    public Handler() {
        this.savedJars = new HashMap();
    }
    
    public URLConnection openConnection(final URL url) throws IOException {
        String embeddedURL;
        final String file = embeddedURL = url.getFile();
        String jarPath = "";
        final boolean trace = Handler.log.isTraceEnabled();
        final int pos = file.lastIndexOf("^/");
        if (pos >= 0) {
            embeddedURL = file.substring(0, pos);
            if (file.length() > pos + "^/".length()) {
                jarPath = file.substring(pos + "^/".length());
            }
        }
        if (embeddedURL.startsWith("njar")) {
            if (trace) {
                Handler.log.trace("Opening next  nested jar: " + embeddedURL);
            }
            File tempJar = this.savedJars.get(embeddedURL);
            if (tempJar == null) {
                final URLConnection embededDataConnection = new URL(embeddedURL).openConnection();
                if (trace) {
                    Handler.log.trace("Content length: " + embededDataConnection.getContentLength());
                }
                final InputStream embededData = embededDataConnection.getInputStream();
                tempJar = File.createTempFile("nested-", ".jar");
                tempJar.deleteOnExit();
                if (trace) {
                    Handler.log.trace("temp file location : " + tempJar);
                }
                final OutputStream output = new FileOutputStream(tempJar);
                try {
                    final long bytes = Streams.copyb(embededData, output);
                    if (trace) {
                        Handler.log.trace("copied " + bytes + " bytes");
                    }
                }
                finally {
                    Streams.flush(output);
                    Streams.close(embededData);
                    Streams.close(output);
                }
                this.savedJars.put(embeddedURL, tempJar);
            }
            String t = tempJar.getCanonicalFile().toURL().toExternalForm();
            if (trace) {
                Handler.log.trace("file URL : " + t);
            }
            t = "njar:" + t + "^/" + jarPath;
            if (trace) {
                Handler.log.trace("Opening saved jar: " + t);
            }
            final URL u = new URL(t);
            if (trace) {
                Handler.log.trace("Using URL: " + u);
            }
            return u.openConnection();
        }
        if (trace) {
            Handler.log.trace("Opening final nested jar: " + embeddedURL);
        }
        final URL u2 = new URL("jar:" + embeddedURL + "!/" + jarPath);
        if (trace) {
            Handler.log.trace("Using URL: " + u2);
        }
        return u2.openConnection();
    }
    
    public static URL njarToFile(final URL url) {
        if (url.getProtocol().equals("njar")) {
            try {
                final URL dummy = new URL("njar:" + url.toString() + "^/" + "dummy.jar");
                String tmp = dummy.openConnection().getURL().toString();
                tmp = tmp.substring("jar:".length());
                tmp = tmp.substring(0, tmp.length() - "!/dummy.jar".length());
                return new URL(tmp);
            }
            catch (Exception ignore) {
                ThrowableHandler.addWarning(ignore);
            }
        }
        return url;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        log = Logger.getLogger((Handler.class$org$jboss$net$protocol$njar$Handler == null) ? (Handler.class$org$jboss$net$protocol$njar$Handler = class$("org.jboss.net.protocol.njar.Handler")) : Handler.class$org$jboss$net$protocol$njar$Handler);
    }
}
