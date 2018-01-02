// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.utility;

public class Namespace
{
    private String prefix;
    private String uri;
    
    public static Namespace getNamespace(final String prefix, final String uri) {
        return new Namespace(prefix, uri);
    }
    
    private Namespace(final String prefix, final String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getURI() {
        return this.uri;
    }
    
    public String getQualifiedName(final String tag) {
        return String.valueOf(this.prefix) + ":" + tag;
    }
    
    public String getUnqualifiedName(final String tag) {
        return tag.substring(this.prefix.length() + 1, tag.length());
    }
}
