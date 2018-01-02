// 
// Decompiled by Procyon v0.5.30
// 

package a;

import com.spilka.client.muc.ChatNames;

public final class ch extends ChatNames
{
    private static String q;
    private static String w;
    private static String e;
    
    public static void q(final String q) {
        if (q != null && q.length() > 0) {
            ch.q = q;
        }
    }
    
    public static String getChatName() {
        return ch.q;
    }
    
    public static String getCopyRight() {
        return "Copyright 2005-2009 by " + getChatName() + ", Inc.\nAll rights reserved.";
    }
    
    public static String getURL() {
        return ch.q;
    }
    
    public static String getDevelopedBy() {
        return getChatName() + " Development Team";
    }
    
    public static String q() {
        return ch.w;
    }
    
    public static void w(final String w) {
        if (w != null && w.length() > 0) {
            ch.w = w;
        }
    }
    
    public static String w() {
        return ch.e;
    }
    
    public static void e(final String e) {
        if (e != null && e.length() > 0) {
            ch.e = e;
        }
    }
    
    static {
        ChatNames.getChatName();
        ch.q = ChatNames.getURL();
        ch.w = null;
        ch.e = null;
    }
}
