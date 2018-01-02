// 
// Decompiled by Procyon v0.5.30
// 

package photo;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class HashClass extends HashMap
{
    HashSet hashField;
    final Photo mClass;
    
    public static String getHash(final String s, final String s2, final String s3) {
        String concat = "";
        for (int i = 0; i < s3.length(); ++i) {
            final int index;
            if ((index = s.indexOf(s3.substring(i, i + 1))) > -1) {
                concat = concat.concat(s2.substring(index, index + 1));
            }
        }
        return concat;
    }
    
    public HashClass(final Photo mClass, final HashSet hashField) {
        this.mClass = mClass;
        this.hashField = hashField;
    }
    
    @Override
    public Set entrySet() {
        return this.hashField;
    }
}
