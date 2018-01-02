// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.Iterator;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;
import java.util.regex.Pattern;
import javax.management.ObjectName;

public class ObjectNameMatch
{
    public static boolean match(final ObjectName n0, final ObjectName n1) {
        boolean match = n0.equals(n1);
        if (match) {
            return true;
        }
        final String d0 = n0.getDomain();
        final String d2 = n1.getDomain();
        final int star0 = d0.indexOf(42);
        final int star2 = d2.indexOf(42);
        if (star0 >= 0) {
            if (star2 >= 0) {
                match = d0.equals(d2);
            }
            else {
                try {
                    final Pattern domainRE = Pattern.compile(d0);
                    final Matcher m = domainRE.matcher(d2);
                    match = m.matches();
                }
                catch (PatternSyntaxException e) {}
            }
        }
        else if (star2 >= 0) {
            if (star0 >= 0) {
                match = d0.equals(d2);
            }
            else {
                try {
                    final Pattern domainRE = Pattern.compile(d2);
                    final Matcher m = domainRE.matcher(d0);
                    match = m.matches();
                }
                catch (PatternSyntaxException e) {}
            }
        }
        else {
            match = d0.equals(d2);
        }
        if (!match) {
            return false;
        }
        if (n0.isPropertyPattern()) {
            final Hashtable props0 = n0.getKeyPropertyList();
            final Hashtable props2 = n1.getKeyPropertyList();
            String key;
            String value;
            for (Iterator iter = props0.keySet().iterator(); match && iter.hasNext(); match &= value.equals(props2.get(key))) {
                key = iter.next();
                value = props0.get(key);
            }
        }
        else if (n1.isPropertyPattern()) {
            final Hashtable props0 = n0.getKeyPropertyList();
            final Hashtable props2 = n1.getKeyPropertyList();
            for (final String key : props2.keySet()) {
                final String value = props2.get(key);
                match &= value.equals(props0.get(key));
            }
        }
        return match;
    }
}
