// 
// Decompiled by Procyon v0.5.30
// 

package a;

import com.spilka.client.muc.ChatNames;

public final class bw extends ChatNames
{
    private static String q;
    private static String w;
    private static String e;
    
    public static void q(final String s) {
    }
    
    public static void w(final String q) {
        if (q != null && q.length() > 0) {
            bw.q = q;
        }
    }
    
    public static String getChatName() {
        return bw.q;
    }
    
    public static String getCopyRight() {
        return "Copyright 2005-2009 by " + getChatName() + ", Inc.\nAll rights reserved.";
    }
    
    public static String getURL() {
        return bw.q;
    }
    
    public static String getDevelopedBy() {
        return getChatName() + " Development Team";
    }
    
    public static String q() {
        return bw.w;
    }
    
    public static void e(final String w) {
        if (w != null && w.length() > 0) {
            bw.w = w;
        }
    }
    
    public static String w() {
        return bw.e;
    }
    
    public static void r(final String e) {
        if (e != null && e.length() > 0) {
            bw.e = e;
        }
    }
    
    static {
        ChatNames.getChatName();
        bw.q = ChatNames.getURL();
        bw.w = null;
        bw.e = null;
    }
}
