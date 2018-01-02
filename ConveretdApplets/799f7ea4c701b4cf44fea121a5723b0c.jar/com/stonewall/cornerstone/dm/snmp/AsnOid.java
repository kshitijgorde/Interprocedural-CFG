// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.snmp;

public class AsnOid
{
    public static String getPrefix(final String oid) {
        if (oid != null) {
            final int start = 0;
            final int end = oid.lastIndexOf(46);
            if (end != -1) {
                return oid.substring(start, end);
            }
        }
        return oid;
    }
    
    public static String getSuffix(final String oid) {
        if (oid != null) {
            final int start = oid.lastIndexOf(46);
            final int end = oid.length();
            if (start != -1) {
                return oid.substring(start + 1, end);
            }
        }
        return oid;
    }
    
    public static String getIndex(final String base, final String oid) {
        final int baseLen = base.length();
        if (oid.length() > baseLen + 1) {
            return oid.substring(baseLen + 1);
        }
        return "";
    }
    
    public static String stripIndex(final String oid, final String index) {
        final int pos = oid.lastIndexOf(index);
        if (pos < 1) {
            return oid;
        }
        return oid.substring(0, pos - 1);
    }
}
