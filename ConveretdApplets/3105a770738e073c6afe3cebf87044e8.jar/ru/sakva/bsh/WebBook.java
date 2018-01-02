// 
// Decompiled by Procyon v0.5.30
// 

package ru.sakva.bsh;

public class WebBook extends Book
{
    public String ref;
    String aut;
    
    public WebBook() {
        this.aut = "";
    }
    
    public String getAuthor() {
        return this.aut;
    }
    
    public void setReference(final String s) {
        this.ref = new String(s);
    }
    
    public WebBook(final String s, final String s2) {
        super(s);
        this.aut = "";
        this.ref = new String(s2);
    }
}
