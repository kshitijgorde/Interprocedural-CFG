// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.StringReader;
import java.io.Reader;
import org.jdom.Element;
import org.xmodel.log.Log;

class Reply
{
    final String content;
    static final Log log;
    
    static {
        log = Log.getLog("DeviceSimulator");
    }
    
    static Reply newInstance(final Element root) {
        Reply result = null;
        final String type = (root == null) ? Type.text.toString() : root.getAttributeValue("type");
        switch (Type.valueOf(type)) {
            case text: {
                result = new Reply(root);
                break;
            }
            case url: {
                result = new URLReply(root);
                break;
            }
            case file: {
                result = new FileReply(root);
                break;
            }
        }
        return result;
    }
    
    Reply(final Element root) {
        this.content = ((root != null) ? root.getText() : null);
    }
    
    Reader getContent() {
        return (this.content == null) ? null : new StringReader(this.content);
    }
    
    enum Type
    {
        text("text", 0), 
        url("url", 1), 
        file("file", 2);
        
        private Type(final String s, final int n) {
        }
    }
}
