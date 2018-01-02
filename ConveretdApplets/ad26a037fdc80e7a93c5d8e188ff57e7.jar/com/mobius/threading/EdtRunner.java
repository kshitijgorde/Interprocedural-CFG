// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.threading;

public class EdtRunner
{
    public static void runMethodOnEDT(final Object o, final String s) {
        runMethodOnEDT(o, s, null);
    }
    
    public static void runMethodOnEDT(final Object o, final String s, final Object[] array) {
        EdtUtilities.runOnEdt(o, MethodFinder.findMethod(o, s, array), array);
    }
    
    public static Object runMethodOnEDTandWait(final Object o, final String s) {
        return runMethodOnEDTandWait(o, s, null);
    }
    
    public static Object runMethodOnEDTandWait(final Object o, final String s, final Object[] array) {
        return EdtUtilities.runOnEdtAndWait(o, MethodFinder.findMethod(o, s, array), array);
    }
    
    public static void runMethodOffEDT(final Object o, final String s) {
        runMethodOffEDT(o, s, null, false);
    }
    
    public static void runMethodOffEDT(final Object o, final String s, final Object[] array) {
        runMethodOffEDT(o, s, array, false);
    }
    
    public static void runMethodOffEDT(final Object o, final String s, final Object[] array, final boolean b) {
        EdtUtilities.runOffEdt(o, MethodFinder.findMethod(o, s, array), array, b);
    }
    
    public static void runMethodOffEDT(final Object o, final String s, final Object[] array, final Object o2, final String s2, final Object[] array2, final boolean b) {
        EdtUtilities.runOffEdt(o, MethodFinder.findMethod(o, s, array), array, o2, MethodFinder.findMethod(o2, s2, array2), array2, b);
    }
    
    public static boolean isAbleToUseEDT() {
        try {
            return Class.forName("java.awt.EventQueue").getMethod("invokeAndWait", Class.forName("java.lang.Runnable")) != null;
        }
        catch (NoSuchMethodException ex) {}
        catch (ClassNotFoundException ex2) {}
        return false;
    }
}
