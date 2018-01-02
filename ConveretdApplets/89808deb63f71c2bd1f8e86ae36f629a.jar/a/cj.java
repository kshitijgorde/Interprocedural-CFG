// 
// Decompiled by Procyon v0.5.30
// 

package a;

import com.spilka.client.muc.ChatNames;

public final class cj extends ChatNames
{
    private static String q;
    private static String w;
    private static String e;
    
    public static void q(final String q) {
        if (q != null && q.length() > 0) {
            cj.q = q;
        }
    }
    
    public static String getChatName() {
        return cj.q;
    }
    
    public static String getCopyRight() {
        return "Copyright 2005-2009 by " + getChatName() + ", Inc.\nAll rights reserved.";
    }
    
    public static String getURL() {
        return cj.q;
    }
    
    public static String getDevelopedBy() {
        return getChatName() + " Development Team";
    }
    
    public static String q() {
        return cj.w;
    }
    
    public static void w(final String w) {
        if (w != null && w.length() > 0) {
            cj.w = w;
        }
    }
    
    public static String w() {
        return cj.e;
    }
    
    public static void e(final String e) {
        if (e != null && e.length() > 0) {
            cj.e = e;
        }
    }
    
    static {
        ChatNames.getChatName();
        cj.q = ChatNames.getURL();
        cj.w = null;
        cj.e = null;
    }
}
