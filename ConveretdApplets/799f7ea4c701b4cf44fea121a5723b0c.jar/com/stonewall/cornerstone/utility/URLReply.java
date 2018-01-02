// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.StringReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.Reader;
import org.jdom.Element;

class URLReply extends Reply
{
    URLReply(final Element root) {
        super(root);
    }
    
    @Override
    Reader getContent() {
        Reader result = null;
        try {
            final EncodedString ec = new EncodedString(this.content);
            final URL url = new URL(ec.toString());
            result = new InputStreamReader(url.openStream());
        }
        catch (Exception e) {
            URLReply.log.error("Reply Failed", e);
            result = new StringReader(e.toString());
        }
        return result;
    }
}
