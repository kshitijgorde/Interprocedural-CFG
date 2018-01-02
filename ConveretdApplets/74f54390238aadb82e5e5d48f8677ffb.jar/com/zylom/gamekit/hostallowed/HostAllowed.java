// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.gamekit.hostallowed;

import com.zylom.cipher.CipherString;
import java.net.URL;
import java.util.Vector;
import java.applet.Applet;

public final class HostAllowed
{
    private static final String STR_HOST_PARAM = "host";
    private static final String STR_ERROR_PAGE_PARAM = "errorPage";
    private static final String STR_DEFAULT_ERROR_PAGE = "http://www.zylom.com/";
    
    public static final void isAllowed(final Applet applet) {
        try {
            final String documentBaseHost = applet.getDocumentBase().getHost();
            final String codeBaseHost = applet.getCodeBase().getHost();
            String errorPage = applet.getParameter("errorPage");
            if (errorPage == null) {
                errorPage = "http://www.zylom.com/";
            }
            final Vector allowedHosts = new Vector();
            int hostNumber = 0;
            for (String allowedHost = applet.getParameter("host".concat(String.valueOf(String.valueOf(hostNumber)))); allowedHost != null; allowedHost = applet.getParameter("host".concat(String.valueOf(String.valueOf(hostNumber))))) {
                allowedHosts.addElement(decode(allowedHost));
                ++hostNumber;
            }
            int di = 0;
            int dj = allowedHosts.size();
            while (di != dj) {
                if (isAllowedHost(allowedHosts.elementAt(di), documentBaseHost)) {
                    dj = di;
                }
                else {
                    ++di;
                }
            }
            int ci = 0;
            int cj = allowedHosts.size();
            while (ci != cj) {
                if (isAllowedHost(allowedHosts.elementAt(ci), codeBaseHost)) {
                    cj = ci;
                }
                else {
                    ++ci;
                }
            }
            if (dj == allowedHosts.size() || cj == allowedHosts.size()) {
                applet.getAppletContext().showDocument(new URL(errorPage));
                final Object o = new Object();
                synchronized (o) {
                    try {
                        o.wait();
                    }
                    catch (InterruptedException ex) {
                    }
                    // monitorexit(o)
                }
            }
        }
        catch (Throwable e) {
            try {
                String errorPage2 = applet.getParameter("errorPage");
                if (errorPage2 == null) {
                    errorPage2 = "http://www.zylom.com/";
                }
                applet.getAppletContext().showDocument(new URL(errorPage2));
            }
            catch (Throwable t) {}
            final Object o2 = new Object();
            synchronized (o2) {
                try {
                    o2.wait();
                }
                catch (InterruptedException ex2) {
                }
                // monitorexit(o2)
            }
        }
    }
    
    private static final boolean isAllowedHost(final String allowedHost, final String checkHost) {
        final int index = checkHost.lastIndexOf(allowedHost);
        return index != -1 && allowedHost.length() > 4 && index == checkHost.length() - allowedHost.length();
    }
    
    private static final String decode(final String encoded) {
        return CipherString.cipher(CipherString.hexDecode(encoded));
    }
}
