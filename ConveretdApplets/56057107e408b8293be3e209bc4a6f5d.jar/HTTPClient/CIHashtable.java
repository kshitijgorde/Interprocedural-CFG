// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.util.Enumeration;
import java.util.Hashtable;

class CIHashtable extends Hashtable
{
    public CIHashtable(final int n, final float n2) {
        super(n, n2);
    }
    
    public CIHashtable(final int n) {
        super(n);
    }
    
    public CIHashtable() {
    }
    
    public Object get(final String s) {
        return super.get(new CIString(s));
    }
    
    public Object put(final String s, final Object o) {
        return super.put(new CIString(s), o);
    }
    
    public boolean containsKey(final String s) {
        return super.contains(new CIString(s));
    }
    
    public Object remove(final String s) {
        return super.remove(new CIString(s));
    }
    
    public Enumeration keys() {
        return new CIHashtableEnumeration(super.keys());
    }
    
    private static class CIHashtableEnumeration implements Enumeration
    {
        Enumeration HTEnum;
        
        public CIHashtableEnumeration(final Enumeration htEnum) {
            this.HTEnum = htEnum;
        }
        
        public boolean hasMoreElements() {
            return this.HTEnum.hasMoreElements();
        }
        
        public Object nextElement() {
            final CIString nextElement = this.HTEnum.nextElement();
            if (nextElement instanceof CIString) {
                return nextElement.getString();
            }
            return nextElement;
        }
    }
    
    private static final class CIString
    {
        private String string;
        private int hash;
        private static final char[] lc;
        
        public CIString(final String string) {
            this.string = string;
            this.hash = calcHashCode(string);
        }
        
        public final String getString() {
            return this.string;
        }
        
        public int hashCode() {
            return this.hash;
        }
        
        private static final int calcHashCode(final String s) {
            int n = 0;
            final char[] lc = CIString.lc;
            for (int length = s.length(), i = 0; i < length; ++i) {
                n = 31 * n + lc[s.charAt(i)];
            }
            return n;
        }
        
        public boolean equals(final Object o) {
            if (o != null) {
                if (o instanceof CIString) {
                    return this.string.equalsIgnoreCase(((CIString)o).string);
                }
                if (o instanceof String) {
                    return this.string.equalsIgnoreCase((String)o);
                }
            }
            return false;
        }
        
        public String toString() {
            return this.string;
        }
        
        static {
            lc = new char[256];
            for (char c = '\0'; c < '\u0100'; ++c) {
                CIString.lc[c] = Character.toLowerCase(c);
            }
        }
    }
}
