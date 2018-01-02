// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public class Comment
{
    String[] user_comments;
    String vendor;
    
    public void clear() {
        this.user_comments = null;
        this.vendor = null;
    }
    
    public String getComment(final int n) {
        if (this.user_comments.length <= n) {
            return null;
        }
        return this.user_comments[n];
    }
    
    public String toString() {
        String s = "Vendor: " + this.vendor;
        for (int i = 0; i < this.user_comments.length; ++i) {
            s = s + "\nComment: " + this.user_comments[i];
        }
        return s + "\n";
    }
}
