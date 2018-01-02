// 
// Decompiled by Procyon v0.5.30
// 

package com.revfad.search;

public final class Match
{
    private final String pattern;
    private final Document document;
    private final String context;
    private final String nearestAnchor;
    
    public Match(final String pattern, final Document document, final String context, final String nearestAnchor) {
        this.pattern = pattern;
        this.document = document;
        this.context = context;
        this.nearestAnchor = nearestAnchor;
    }
    
    public String pattern() {
        return this.pattern;
    }
    
    public Document document() {
        return this.document;
    }
    
    public String context() {
        return this.context;
    }
    
    public String nearestAnchor() {
        return this.nearestAnchor;
    }
    
    private String nullOrQuoted(final String s) {
        if (s == null) {
            return "null";
        }
        return "\"" + s + "\"";
    }
    
    public String toString() {
        return this.document + " pattern = \"" + this.pattern() + "\"" + "; context = " + this.nullOrQuoted(this.context()) + "; nearestAnchor = " + this.nullOrQuoted(this.nearestAnchor());
    }
    
    public int compareTo(final Object o) {
        final Match match = (Match)o;
        final String titleOrFileNameIfNull = this.document.titleOrFileNameIfNull();
        final String fileName = this.document.fileName();
        final String titleOrFileNameIfNull2 = match.document().titleOrFileNameIfNull();
        final String fileName2 = match.document().fileName();
        int n = titleOrFileNameIfNull.compareTo(titleOrFileNameIfNull2);
        if (n == 0) {
            n = fileName.compareTo(fileName2);
        }
        return n;
    }
    
    public int compareDateTo(final Object o) {
        final long n = ((Match)o).document().lastModified() - this.document().lastModified();
        if (n < 0L) {
            return -1;
        }
        if (n > 0L) {
            return 1;
        }
        return this.compareTo(o);
    }
    
    private boolean bothNullOrEqual(final Object o, final Object o2) {
        if (o == null) {
            return o2 == null;
        }
        return o.equals(o2);
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof Match)) {
            return false;
        }
        final Match match = (Match)o;
        return this.document().equals(match.document()) && this.pattern().equals(match.pattern()) && this.bothNullOrEqual(this.nearestAnchor(), match.nearestAnchor());
    }
    
    private int hashCodeOrOneIfNull(final Object o) {
        if (o == null) {
            return 1;
        }
        return o.hashCode();
    }
    
    public int hashCode() {
        return 50653 * this.document().url().hashCode() + 1369 * this.pattern().hashCode() + 37 * this.hashCodeOrOneIfNull(this.nearestAnchor());
    }
}
