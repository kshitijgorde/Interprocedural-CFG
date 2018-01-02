// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

import java.io.Reader;
import org.jdom.Element;
import org.jdom.Namespace;

class RPC
{
    final String classname;
    final String method;
    final Reply reply;
    static Namespace cnns;
    
    static {
        RPC.cnns = Namespace.getNamespace("cn", "http://www.stonewallnetworks.com/ns/common");
    }
    
    RPC(final Element root) {
        this.classname = root.getChildText("class", RPC.cnns);
        final Element m = root.getChild("method", RPC.cnns);
        this.method = m.getAttributeValue("name");
        this.reply = Reply.newInstance(m.getChild("return", RPC.cnns));
    }
    
    String signature() {
        return signature(this.classname, this.method);
    }
    
    String execute() {
        return this.toString(this.reply.getContent());
    }
    
    String toString(final Reader reader) {
        String result = null;
        final StringBuilder sb = new StringBuilder();
        final char[] bfr = new char[10240];
        try {
            while (true) {
                final int n = reader.read(bfr);
                if (n == -1) {
                    break;
                }
                sb.append(bfr, 0, n);
            }
            result = sb.toString();
            reader.close();
        }
        catch (Exception e) {
            result = e.toString();
        }
        return result;
    }
    
    static String signature(final String c, final String m) {
        return String.valueOf(c) + "." + m;
    }
}
