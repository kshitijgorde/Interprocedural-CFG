// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.StringReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.Reader;
import org.jdom.Element;

class FileReply extends Reply
{
    FileReply(final Element root) {
        super(root);
    }
    
    @Override
    Reader getContent() {
        Reader result = null;
        try {
            final EncodedString ec = new EncodedString(this.content);
            final File f = new File(ec.toString());
            result = new InputStreamReader(new FileInputStream(f));
        }
        catch (Exception e) {
            FileReply.log.error("Reply Failed", e);
            result = new StringReader(e.toString());
        }
        return result;
    }
}
