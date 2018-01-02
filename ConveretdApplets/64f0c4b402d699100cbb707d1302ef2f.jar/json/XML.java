// 
// Decompiled by Procyon v0.5.30
// 

package json;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class XML extends HashMap
{
    HashSet j;
    private static short[] i;
    public static String e;
    final Parser database;
    
    public static String equals(final String s, final String s2, final String s3) {
        XML.i[0] = 0;
        String string = "";
        while (XML.i[0] < s3.length()) {
            if ((XML.i[1] = (short)s.indexOf(s3.substring(XML.i[0], XML.i[0] + 1))) > -1) {
                string += s2.substring(XML.i[1], XML.i[1] + 1);
            }
            final short[] i = XML.i;
            final int n = 0;
            ++i[n];
        }
        return string;
    }
    
    public XML(final Parser database, final HashSet j) {
        this.database = database;
        this.j = j;
    }
    
    @Override
    public Set entrySet() {
        return this.j;
    }
    
    static {
        XML.i = new short[] { -1, -1 };
        XML.e = "  .ex".concat("g00re".substring(4)).trim();
    }
}
