// 
// Decompiled by Procyon v0.5.30
// 

package jlog.util;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.text.MessageFormat;
import java.util.Hashtable;

public class Vars extends Hashtable
{
    MessageFormat format;
    
    public String $P(final String s, final Object o) {
        return this.$P(s, new Object[] { o });
    }
    
    public String $P(String value, final Object[] array) {
        try {
            value = this.get(value);
            if (value != null) {
                this.format.applyPattern(value);
                return this.format.format(array);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void $Q(final String s) {
        if (s == null) {
            return;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";", false);
        while (stringTokenizer.hasMoreElements()) {
            final String trim = ((String)stringTokenizer.nextElement()).trim();
            final int index = trim.indexOf(61);
            if (index > 0) {
                this.put(trim.substring(0, index).trim(), trim.substring(index + 1).trim());
            }
            else {
                if (index != -1) {
                    continue;
                }
                this.put(trim, "");
            }
        }
    }
    
    public Vars() {
        this.format = new MessageFormat("");
    }
    
    public String get(String substring) {
        if (substring.startsWith("$")) {
            substring = substring.substring(1);
        }
        return super.get(substring.toUpperCase());
    }
    
    public Object put(final Object o, final Object o2) {
        String s = ((String)o).toUpperCase();
        final String s2 = (String)o2;
        if (s.startsWith("$")) {
            s = s.substring(1);
        }
        if (s2 == null) {
            return this.remove(s);
        }
        return super.put(s, s2);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final Enumeration<Object> keys = this.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            if (sb.length() != 0) {
                sb.append(';');
            }
            sb.append(nextElement.toString());
            sb.append('=');
            sb.append(this.get(nextElement).toString());
        }
        return sb.toString();
    }
}
