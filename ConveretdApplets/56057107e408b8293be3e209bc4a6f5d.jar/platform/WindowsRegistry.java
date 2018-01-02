// 
// Decompiled by Procyon v0.5.30
// 

package platform;

import java.lang.reflect.InvocationTargetException;
import logging.LogHolder;
import logging.LogType;
import java.lang.reflect.Method;

public class WindowsRegistry
{
    private static final int NATIVE_HANDLE = 0;
    private static final int ERROR_CODE = 1;
    private static Class ms_windowsPreferencesClass;
    private static Method ms_openKeyMethod;
    private static Method ms_queryValueMethod;
    private static Method ms_closeKeyMethod;
    static /* synthetic */ Class array$B;
    
    public static int openKey(final int n, final String s, final int n2) {
        try {
            final int[] array = (int[])WindowsRegistry.ms_openKeyMethod.invoke(null, new Integer(n), (s + "\u0000").getBytes(), new Integer(n2));
            if (array != null && array[1] == 0) {
                return array[0];
            }
        }
        catch (InvocationTargetException ex) {
            LogHolder.log(2, LogType.GUI, "Error while accessing windows registry.", ex);
        }
        catch (IllegalAccessException ex2) {
            LogHolder.log(2, LogType.GUI, "Error while accessing windows registry.", ex2);
        }
        return -1;
    }
    
    public static String queryValue(final int n, final String s) {
        try {
            final byte[] array = (byte[])WindowsRegistry.ms_queryValueMethod.invoke(null, new Integer(n), (s + "\u0000").getBytes());
            if (array != null) {
                String substring = new String(array);
                if (substring.charAt(substring.length() - 1) == '\0') {
                    substring = substring.substring(0, substring.length() - 1);
                }
                return substring;
            }
        }
        catch (InvocationTargetException ex) {
            LogHolder.log(2, LogType.GUI, "Error while accessing windows registry.", ex);
        }
        catch (IllegalAccessException ex2) {
            LogHolder.log(2, LogType.GUI, "Error while accessing windows registry.", ex2);
        }
        return null;
    }
    
    public static int closeKey(final int n) {
        try {
            final Integer n2 = (Integer)WindowsRegistry.ms_closeKeyMethod.invoke(null, new Integer(n));
            if (n2 != null) {
                return n2;
            }
        }
        catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        }
        return -1;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        if (System.getProperty("os.name", "").toLowerCase().indexOf("win") != -1) {
            try {
                final Method method = Class.forName("java.lang.reflect.AccessibleObject").getMethod("setAccessible", Boolean.TYPE);
                WindowsRegistry.ms_windowsPreferencesClass = Class.forName("java.util.prefs.WindowsPreferences");
                WindowsRegistry.ms_openKeyMethod = WindowsRegistry.ms_windowsPreferencesClass.getDeclaredMethod("WindowsRegOpenKey", Integer.TYPE, (WindowsRegistry.array$B == null) ? (WindowsRegistry.array$B = class$("[B")) : WindowsRegistry.array$B, Integer.TYPE);
                WindowsRegistry.ms_queryValueMethod = WindowsRegistry.ms_windowsPreferencesClass.getDeclaredMethod("WindowsRegQueryValueEx", Integer.TYPE, (WindowsRegistry.array$B == null) ? (WindowsRegistry.array$B = class$("[B")) : WindowsRegistry.array$B);
                WindowsRegistry.ms_closeKeyMethod = WindowsRegistry.ms_windowsPreferencesClass.getDeclaredMethod("WindowsRegCloseKey", Integer.TYPE);
                method.invoke(WindowsRegistry.ms_openKeyMethod, Boolean.TRUE);
                method.invoke(WindowsRegistry.ms_queryValueMethod, Boolean.TRUE);
                method.invoke(WindowsRegistry.ms_closeKeyMethod, Boolean.TRUE);
            }
            catch (Throwable t) {
                LogHolder.log(2, LogType.GUI, "Error while accessing windows registry.", t);
            }
        }
    }
}
