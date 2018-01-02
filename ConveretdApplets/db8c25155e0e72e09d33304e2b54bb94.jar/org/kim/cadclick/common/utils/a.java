// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.common.utils;

public class a
{
    private static String a;
    
    static {
        org.kim.cadclick.common.utils.a.a = "info";
    }
    
    public static void int(final String a) {
        a.a = a;
    }
    
    public static void do(final String s) {
        a("(INFO): " + s, "info");
    }
    
    public static void a(final String s) {
        a("(DEBUG): " + s, "debug");
    }
    
    public static void for(final String s) {
        a("(WARN): " + s, "warn");
    }
    
    public static void new(final String s) {
        a("(ERROR): " + s, "error");
    }
    
    public static void if(final String s) {
        System.out.println(s);
    }
    
    private static void a(final String s, final String s2) {
        if (org.kim.cadclick.common.utils.a.a.toLowerCase().indexOf(s2) > -1 || org.kim.cadclick.common.utils.a.a.toLowerCase().indexOf("all") > -1) {
            System.out.println(s);
        }
    }
}
