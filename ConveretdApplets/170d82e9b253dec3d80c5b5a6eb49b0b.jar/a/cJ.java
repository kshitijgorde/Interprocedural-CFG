// 
// Decompiled by Procyon v0.5.30
// 

package a;

import com.spilka.client.muc.ChatNames;

public final class cJ extends ChatNames
{
    private static String q;
    private static String w;
    private static String e;
    
    public static void q(final String s) {
    }
    
    public static void w(final String q) {
        if (q != null && q.length() > 0) {
            cJ.q = q;
        }
    }
    
    public static String getChatName() {
        return cJ.q;
    }
    
    public static String getCopyRight() {
        return "Copyright 2005-2009 by " + getChatName() + ", Inc.\nAll rights reserved.";
    }
    
    public static String getURL() {
        return cJ.q;
    }
    
    public static String getDevelopedBy() {
        return getChatName() + " Development Team";
    }
    
    public static String q() {
        return cJ.w;
    }
    
    public static void e(final String w) {
        if (w != null && w.length() > 0) {
            cJ.w = w;
        }
    }
    
    public static String w() {
        return cJ.e;
    }
    
    public static void r(final String e) {
        if (e != null && e.length() > 0) {
            cJ.e = e;
        }
    }
    
    static {
        ChatNames.getChatName();
        cJ.q = ChatNames.getURL();
        cJ.w = null;
        cJ.e = null;
    }
}
