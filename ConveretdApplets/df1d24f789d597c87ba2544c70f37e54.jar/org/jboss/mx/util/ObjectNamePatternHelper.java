// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.Iterator;
import java.util.Hashtable;
import java.util.Map;
import javax.management.ObjectName;

public class ObjectNamePatternHelper
{
    public static boolean patternMatch(final ObjectName test, final ObjectName pattern) {
        if (pattern.equals("*:*")) {
            return true;
        }
        if (patternMatch(test.getDomain(), pattern.getDomain())) {
            final PropertyPattern propertyPattern = new PropertyPattern(pattern);
            return propertyPattern.patternMatch(test);
        }
        return false;
    }
    
    public static boolean patternMatch(final String test, final String pattern) {
        return pattern.equals("*") || patternMatch(test.toCharArray(), 0, pattern.toCharArray(), 0);
    }
    
    public static boolean patternMatch(final char[] test, int tpos, final char[] pattern, int ppos) {
        final int tlen = test.length;
        final int plen = pattern.length;
        while (ppos < plen) {
            final char c = pattern[ppos++];
            if ('?' == c) {
                if (tpos++ == tlen) {
                    return false;
                }
                continue;
            }
            else if ('*' == c) {
                if (ppos == plen) {
                    return true;
                }
                if (tpos == tlen) {
                    return false;
                }
                while (!patternMatch(test, tpos, pattern, ppos)) {
                    if (++tpos >= tlen) {
                        continue Label_0106;
                    }
                }
                return true;
            }
            else {
                if (tpos == tlen || c != test[tpos++]) {
                    return false;
                }
                continue;
            }
            Label_0106:;
        }
        return tpos == tlen;
    }
    
    public static class PropertyPattern
    {
        boolean isPropertyPattern;
        Object[] propertyKeys;
        Object[] propertyValues;
        String canonicalKeyPropertyString;
        
        public PropertyPattern(final ObjectName pattern) {
            this.isPropertyPattern = pattern.isPropertyPattern();
            if (this.isPropertyPattern) {
                final Hashtable patternKPList = pattern.getKeyPropertyList();
                final int length = patternKPList.size();
                this.propertyKeys = new Object[length];
                this.propertyValues = new Object[length];
                int i = 0;
                for (final Map.Entry entry : patternKPList.entrySet()) {
                    this.propertyKeys[i] = entry.getKey();
                    this.propertyValues[i] = entry.getValue();
                    ++i;
                }
            }
            else {
                this.canonicalKeyPropertyString = pattern.getCanonicalKeyPropertyListString();
            }
        }
        
        public boolean patternMatch(final ObjectName name) {
            if (!this.isPropertyPattern) {
                return this.canonicalKeyPropertyString.equals(name.getCanonicalKeyPropertyListString());
            }
            if (this.propertyKeys.length == 0) {
                return true;
            }
            final Hashtable kplist = name.getKeyPropertyList();
            for (int i = 0; i < this.propertyKeys.length; ++i) {
                if (!this.propertyValues[i].equals(kplist.get(this.propertyKeys[i]))) {
                    return false;
                }
            }
            return true;
        }
    }
}
