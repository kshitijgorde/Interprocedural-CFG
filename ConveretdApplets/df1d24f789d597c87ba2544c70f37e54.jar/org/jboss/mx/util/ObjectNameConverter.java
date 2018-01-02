// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Hashtable;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

public class ObjectNameConverter
{
    public static ObjectName convert(String pObjectName) throws MalformedObjectNameException {
        if (pObjectName == null) {
            throw new MalformedObjectNameException("null name");
        }
        if (pObjectName.length() == 0) {
            pObjectName = "*:*";
        }
        final int lIndex = pObjectName.indexOf(":");
        if (lIndex < 0) {
            throw new MalformedObjectNameException("missing domain");
        }
        final String lDomain = pObjectName.substring(0, lIndex);
        if (lIndex + 1 < pObjectName.length()) {
            return createObjectName(lDomain, pObjectName.substring(lIndex + 1));
        }
        throw new MalformedObjectNameException("properties missing");
    }
    
    public static ObjectName convert(final String pDomainName, final Hashtable pProperties) throws MalformedObjectNameException {
        if (pDomainName == null) {
            throw new MalformedObjectNameException("missing domain");
        }
        if (pProperties == null || pProperties.size() == 0) {
            throw new MalformedObjectNameException(" null or empty properties");
        }
        return createObjectName(pDomainName, pProperties, false);
    }
    
    public static Hashtable getProperties(final ObjectName pObjectName) {
        final Hashtable lReturn = reverseProperties(pObjectName.getKeyPropertyList());
        if (pObjectName.isPropertyPattern()) {
            lReturn.put("*", "*");
        }
        return lReturn;
    }
    
    public static String getString(final ObjectName pObjectName) {
        String lReturn = pObjectName.getDomain() + ":" + reverseString(pObjectName.getKeyPropertyList());
        if (pObjectName.isPropertyPattern()) {
            lReturn += ",*";
        }
        return lReturn;
    }
    
    public static String convertCharacters(final String pValue, final boolean pEncrypt) {
        String lReturn = pValue;
        if (pEncrypt) {
            for (int lIndex = lReturn.indexOf("%"); lIndex >= 0; lIndex = lReturn.indexOf("%", lIndex + 2)) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "%25" + ((lIndex + 1 < lReturn.length()) ? lReturn.substring(lIndex + 1) : "");
            }
            for (int lIndex = lReturn.indexOf("*"); lIndex >= 0; lIndex = lReturn.indexOf("*")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "%2a" + ((lIndex + 1 < lReturn.length()) ? lReturn.substring(lIndex + 1) : "");
            }
            for (int lIndex = lReturn.indexOf(":"); lIndex >= 0; lIndex = lReturn.indexOf(":")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "%3a" + ((lIndex + 1 < lReturn.length()) ? lReturn.substring(lIndex + 1) : "");
            }
            for (int lIndex = lReturn.indexOf("?"); lIndex >= 0; lIndex = lReturn.indexOf("?")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "%3f" + ((lIndex + 1 < lReturn.length()) ? lReturn.substring(lIndex + 1) : "");
            }
            for (int lIndex = lReturn.indexOf("="); lIndex >= 0; lIndex = lReturn.indexOf("=")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "%3d" + ((lIndex + 1 < lReturn.length()) ? lReturn.substring(lIndex + 1) : "");
            }
            for (int lIndex = lReturn.indexOf(","); lIndex >= 0; lIndex = lReturn.indexOf(",")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "%2c" + ((lIndex + 1 < lReturn.length()) ? lReturn.substring(lIndex + 1) : "");
            }
            for (int lIndex = lReturn.indexOf("'"); lIndex >= 0; lIndex = lReturn.indexOf("'")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "%27" + ((lIndex + 1 < lReturn.length()) ? lReturn.substring(lIndex + 1) : "");
            }
            for (int lIndex = lReturn.indexOf("\""); lIndex >= 0; lIndex = lReturn.indexOf("\"")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "%22" + ((lIndex + 1 < lReturn.length()) ? lReturn.substring(lIndex + 1) : "");
            }
        }
        else {
            for (int lIndex = lReturn.indexOf("%2a"); lIndex >= 0; lIndex = lReturn.indexOf("%2a")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "*" + ((lIndex + 3 < lReturn.length()) ? lReturn.substring(lIndex + 3) : "");
            }
            for (int lIndex = lReturn.indexOf("%3a"); lIndex >= 0; lIndex = lReturn.indexOf("%3a")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + ":" + ((lIndex + 3 < lReturn.length()) ? lReturn.substring(lIndex + 3) : "");
            }
            for (int lIndex = lReturn.indexOf("%3f"); lIndex >= 0; lIndex = lReturn.indexOf("%3f")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "?" + ((lIndex + 3 < lReturn.length()) ? lReturn.substring(lIndex + 3) : "");
            }
            for (int lIndex = lReturn.indexOf("%3d"); lIndex >= 0; lIndex = lReturn.indexOf("%3d")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "=" + ((lIndex + 3 < lReturn.length()) ? lReturn.substring(lIndex + 3) : "");
            }
            for (int lIndex = lReturn.indexOf("%2c"); lIndex >= 0; lIndex = lReturn.indexOf("%2c")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "," + ((lIndex + 3 < lReturn.length()) ? lReturn.substring(lIndex + 3) : "");
            }
            for (int lIndex = lReturn.indexOf("%25"); lIndex >= 0; lIndex = lReturn.indexOf("%25")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "%" + ((lIndex + 3 < lReturn.length()) ? lReturn.substring(lIndex + 3) : "");
            }
            for (int lIndex = lReturn.indexOf("%27"); lIndex >= 0; lIndex = lReturn.indexOf("%27")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "'" + ((lIndex + 3 < lReturn.length()) ? lReturn.substring(lIndex + 3) : "");
            }
            for (int lIndex = lReturn.indexOf("%22"); lIndex >= 0; lIndex = lReturn.indexOf("%22")) {
                lReturn = ((lIndex > 0) ? lReturn.substring(0, lIndex) : "") + "\"" + ((lIndex + 3 < lReturn.length()) ? lReturn.substring(lIndex + 3) : "");
            }
        }
        return lReturn;
    }
    
    private static ObjectName createObjectName(final String domain, final String properties) throws MalformedObjectNameException {
        if (null == properties || properties.length() < 1) {
            throw new MalformedObjectNameException("null or empty properties");
        }
        if (properties.startsWith(",") || properties.endsWith(",") || properties.indexOf(",,") != -1) {
            throw new MalformedObjectNameException("empty key/value pair in properties string");
        }
        final Hashtable ptable = new Hashtable();
        final StringTokenizer tokenizer = new StringTokenizer(properties, ",");
        boolean lPattern = false;
        while (tokenizer.hasMoreTokens()) {
            final String chunk = tokenizer.nextToken();
            if (chunk.equals("*")) {
                lPattern = true;
            }
            else {
                final int keylen = chunk.length();
                final int eqpos = chunk.indexOf(61);
                if (eqpos < 1 || keylen == eqpos + 1) {
                    throw new MalformedObjectNameException("malformed key/value pair: " + chunk);
                }
                final String key = chunk.substring(0, eqpos);
                if (ptable.containsKey(key)) {
                    throw new MalformedObjectNameException("duplicate key: " + key);
                }
                ptable.put(key, chunk.substring(eqpos + 1, keylen));
            }
        }
        return createObjectName(domain, ptable, lPattern);
    }
    
    private static ObjectName createObjectName(final String domain, final Hashtable properties, final boolean pPattern) throws MalformedObjectNameException {
        if (null == properties || (!pPattern && properties.size() < 1)) {
            throw new MalformedObjectNameException("null or empty properties");
        }
        final Iterator it = properties.keySet().iterator();
        final Hashtable lReturn = new Hashtable(properties.size());
        while (it.hasNext()) {
            String key = null;
            try {
                key = it.next();
            }
            catch (ClassCastException e) {
                throw new MalformedObjectNameException("key is not a string");
            }
            String val = null;
            try {
                val = properties.get(key);
            }
            catch (ClassCastException e2) {
                throw new MalformedObjectNameException("value is not a string");
            }
            final String lKey = convertCharacters(key, true);
            final String lValue = convertCharacters(val, true);
            lReturn.put(lKey, lValue);
        }
        final ObjectName result = new ObjectName(domain, lReturn);
        if (pPattern) {
            return new ObjectName(result.getCanonicalName() + ",*");
        }
        return result;
    }
    
    private static Hashtable reverseProperties(final Hashtable pProperties) {
        final Hashtable lReturn = new Hashtable(pProperties.size());
        for (String lKey : pProperties.keySet()) {
            String lValue = pProperties.get(lKey);
            lKey = convertCharacters(lKey, false);
            lValue = convertCharacters(lValue, false);
            lReturn.put(lKey, lValue);
        }
        return lReturn;
    }
    
    private static String reverseString(final Hashtable pProperties) {
        final StringBuffer lReturn = new StringBuffer();
        for (String lKey : pProperties.keySet()) {
            String lValue = pProperties.get(lKey);
            lKey = convertCharacters(lKey, false);
            lValue = convertCharacters(lValue, false);
            if (lReturn.length() > 0) {
                lReturn.append(",");
            }
            lReturn.append(lKey);
            lReturn.append("=");
            lReturn.append(lValue);
        }
        return lReturn.toString();
    }
}
