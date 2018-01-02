// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

import java.util.Enumeration;
import java.util.Date;
import java.util.Hashtable;

public class NFLicenseEntry extends Hashtable
{
    String a;
    
    public NFLicenseEntry(final String a) {
        this.a = null;
        this.a = a;
    }
    
    public String getTarget() {
        return this.a;
    }
    
    public Date getExpiration() {
        final String s = this.get("EXPIRATION");
        if (s == null) {
            return null;
        }
        return new Date(s);
    }
    
    public String getRegistration() {
        return this.getField("REGISTRATION");
    }
    
    public String getField(final String s) {
        return this.get(s);
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("Target = ");
        if (this.a != null) {
            sb.append(this.a);
        }
        else {
            sb.append("<null>");
        }
        final Enumeration<String> elements = this.elements();
        while (elements.hasMoreElements()) {
            final String s = elements.nextElement();
            sb.append(" (" + s + " = ");
            sb.append((String)this.get(s) + "),");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
