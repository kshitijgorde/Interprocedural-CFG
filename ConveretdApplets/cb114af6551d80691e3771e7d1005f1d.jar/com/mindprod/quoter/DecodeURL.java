// 
// Decompiled by Procyon v0.5.30
// 

package com.mindprod.quoter;

import java.net.URISyntaxException;
import java.net.MalformedURLException;
import java.net.URL;

public final class DecodeURL extends Translator
{
    public String process(final String raw) {
        assert raw != null : "DecodeURL.process raw must not be null";
        try {
            return new URL(raw).toURI().toString();
        }
        catch (MalformedURLException e) {
            return "invalid URL cannot be encoded";
        }
        catch (URISyntaxException e2) {
            return "invalid URL cannot be encoded";
        }
    }
}
