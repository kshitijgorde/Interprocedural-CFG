// 
// Decompiled by Procyon v0.5.30
// 

package modules;

import java.util.Enumeration;
import java.awt.Frame;
import java.awt.TextField;
import java.util.Hashtable;

public class Script extends Hashtable implements Module
{
    private telnet applet;
    
    public Script() {
        this.applet = null;
    }
    
    public void connect(final String host, final int port) {
        final String tmp = this.applet.getParameter("script");
        this.clear();
        if (tmp != null) {
            int idx = tmp.indexOf(124);
            int oldidx = 0;
            while (idx >= 0) {
                final String match = tmp.substring(oldidx, idx);
                oldidx = idx;
                idx = tmp.indexOf(124, idx + 1);
                idx = ((idx < 0) ? (idx = tmp.length()) : idx);
                final String send = tmp.substring(oldidx + 1, idx);
                this.put(match, send);
                oldidx = idx + 1;
                idx = tmp.indexOf(124, idx + 1);
            }
        }
    }
    
    public void disconnect() {
    }
    
    public String receive(final String s) {
        if (this.isEmpty()) {
            return s;
        }
        final Enumeration match = this.keys();
        while (match.hasMoreElements()) {
            final String key = match.nextElement();
            if (s.indexOf(key) != -1) {
                String value = this.get(key);
                if (value.indexOf("[") == 0 || value.indexOf("{") == 0) {
                    final TextField input = new TextField(20);
                    if (value.startsWith("{")) {
                        input.setEchoCharacter('*');
                    }
                    if ("[]".equals(value) || "{}".equals(value)) {
                        value = key;
                    }
                    else {
                        value = value.substring(1, value.length() - 1);
                    }
                    final Thread current = Thread.currentThread();
                    new UserDialog(new Frame(), value, false, current, input);
                    current.suspend();
                    value = input.getText();
                }
                this.applet.send(String.valueOf(value) + "\r");
                this.remove(key);
            }
        }
        return s;
    }
    
    public void setLoader(final Object o) {
        this.applet = (telnet)o;
    }
}
