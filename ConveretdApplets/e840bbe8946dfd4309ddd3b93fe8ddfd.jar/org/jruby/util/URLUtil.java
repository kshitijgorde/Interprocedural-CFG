// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.net.URISyntaxException;
import java.net.URL;

public class URLUtil
{
    public static String getPath(final URL url) {
        try {
            return url.toURI().getSchemeSpecificPart();
        }
        catch (URISyntaxException use) {
            return url.getPath();
        }
    }
}
