// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jmdns;

import java.util.logging.Logger;

class DNSEntry
{
    private static Logger logger;
    String key;
    String name;
    int type;
    int clazz;
    boolean unique;
    
    DNSEntry(final String name, final int type, final int clazz) {
        this.key = name.toLowerCase();
        this.name = name;
        this.type = type;
        this.clazz = (clazz & 0x7FFF);
        this.unique = ((clazz & 0x8000) != 0x0);
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof DNSEntry) {
            final DNSEntry other = (DNSEntry)obj;
            return this.name.equals(other.name) && this.type == other.type && this.clazz == other.clazz;
        }
        return false;
    }
    
    public String getName() {
        return this.name;
    }
    
    public int getType() {
        return this.type;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode() + this.type + this.clazz;
    }
    
    static String getClazz(final int clazz) {
        switch (clazz & 0x7FFF) {
            case 1: {
                return "in";
            }
            case 2: {
                return "cs";
            }
            case 3: {
                return "ch";
            }
            case 4: {
                return "hs";
            }
            case 254: {
                return "none";
            }
            case 255: {
                return "any";
            }
            default: {
                return "?";
            }
        }
    }
    
    static String getType(final int type) {
        switch (type) {
            case 1: {
                return "a";
            }
            case 28: {
                return "aaaa";
            }
            case 2: {
                return "ns";
            }
            case 3: {
                return "md";
            }
            case 4: {
                return "mf";
            }
            case 5: {
                return "cname";
            }
            case 6: {
                return "soa";
            }
            case 7: {
                return "mb";
            }
            case 8: {
                return "mg";
            }
            case 9: {
                return "mr";
            }
            case 10: {
                return "null";
            }
            case 11: {
                return "wks";
            }
            case 12: {
                return "ptr";
            }
            case 13: {
                return "hinfo";
            }
            case 14: {
                return "minfo";
            }
            case 15: {
                return "mx";
            }
            case 16: {
                return "txt";
            }
            case 33: {
                return "srv";
            }
            case 255: {
                return "any";
            }
            default: {
                return "?";
            }
        }
    }
    
    public String toString(final String hdr, final String other) {
        return hdr + "[" + getType(this.type) + "," + getClazz(this.clazz) + (this.unique ? "-unique," : ",") + this.name + ((other != null) ? ("," + other + "]") : "]");
    }
    
    static {
        DNSEntry.logger = Logger.getLogger(DNSEntry.class.toString());
    }
}
