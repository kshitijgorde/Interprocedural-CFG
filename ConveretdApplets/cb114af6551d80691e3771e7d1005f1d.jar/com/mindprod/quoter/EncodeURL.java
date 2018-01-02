// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import java.net.URISyntaxException;
import java.net.MalformedURLException;
import java.net.URI;

public final class EncodeURL extends Translator
{
    public String process(final String raw) {
        assert raw != null : "EncodeURL.process raw must not be null";
        try {
            return new URI(raw).toURL().toString();
        }
        catch (MalformedURLException e) {
            return "invalid URL cannot be encoded";
        }
        catch (URISyntaxException e2) {
            return "invalid URL cannot be encoded";
        }
    }
}
