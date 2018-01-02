// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.jadvertise;

import java.io.IOException;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class Launcher
{
    private static int jvm;
    private static Object browser;
    private static boolean loadedWithoutErrors;
    private static Class mrjFileUtilsClass;
    private static Class mrjOSTypeClass;
    private static Class macOSErrorClass;
    private static Class aeDescClass;
    private static Constructor aeTargetConstructor;
    private static Constructor appleEventConstructor;
    private static Constructor aeDescConstructor;
    private static Method findFolder;
    private static Method getFileType;
    private static Method makeOSType;
    private static Method putParameter;
    private static Method sendNoReply;
    private static Object kSystemFolderType;
    private static Integer keyDirectObject;
    private static Integer kAutoGenerateReturnID;
    private static Integer kAnyTransactionID;
    private static final int MRJ_2_0 = 0;
    private static final int MRJ_2_1 = 1;
    private static final int WINDOWS_NT = 2;
    private static final int WINDOWS_9x = 3;
    private static final int OTHER = -1;
    private static final String FINDER_TYPE = "FNDR";
    private static final String FINDER_CREATOR = "MACS";
    private static final String GURL_EVENT = "GURL";
    private static final String FIRST_WINDOWS_PARAMETER = "/c";
    private static final String SECOND_WINDOWS_PARAMETER = "start";
    private static final String NETSCAPE_OPEN_PARAMETER_START = " -remote 'openURL(";
    private static final String NETSCAPE_OPEN_PARAMETER_END = ")'";
    private static String errorMessage;
    static Class class$java$io$File;
    static Class class$java$lang$String;
    
    static {
        Launcher.loadedWithoutErrors = true;
        final String property = System.getProperty("os.name");
        if ("Mac OS".equals(property)) {
            final String property2 = System.getProperty("mrj.version");
            final String substring = property2.substring(0, 3);
            try {
                final double doubleValue = Double.valueOf(substring);
                if (doubleValue == 2.0) {
                    Launcher.jvm = 0;
                }
                else if (doubleValue >= 2.1) {
                    Launcher.jvm = 1;
                }
                else {
                    Launcher.loadedWithoutErrors = false;
                    Launcher.errorMessage = "Unsupported MRJ version: " + doubleValue;
                }
            }
            catch (NumberFormatException ex) {
                Launcher.loadedWithoutErrors = false;
                Launcher.errorMessage = "Invalid MRJ version: " + property2;
            }
        }
        else if (property.startsWith("Windows")) {
            if (property.indexOf("9") != -1) {
                Launcher.jvm = 3;
            }
            else {
                Launcher.jvm = 2;
            }
        }
        else {
            Launcher.jvm = -1;
        }
        if (Launcher.loadedWithoutErrors) {
            Launcher.loadedWithoutErrors = loadClasses();
        }
    }
    
    private static boolean loadClasses() {
        switch (Launcher.jvm) {
            case 0: {
                try {
                    final Class<?> forName = Class.forName("com.apple.MacOS.AETarget");
                    Launcher.macOSErrorClass = Class.forName("com.apple.MacOS.MacOSError");
                    final Class<?> forName2 = Class.forName("com.apple.MacOS.OSUtils");
                    final Class<?> forName3 = Class.forName("com.apple.MacOS.AppleEvent");
                    final Class<?> forName4 = Class.forName("com.apple.MacOS.ae");
                    Launcher.aeDescClass = Class.forName("com.apple.MacOS.AEDesc");
                    Launcher.aeTargetConstructor = forName.getDeclaredConstructor(Integer.TYPE);
                    Launcher.appleEventConstructor = forName3.getDeclaredConstructor(Integer.TYPE, Integer.TYPE, forName, Integer.TYPE, Integer.TYPE);
                    final Class aeDescClass = Launcher.aeDescClass;
                    final Class[] array = { null };
                    final int n = 0;
                    Class class$java$lang$String;
                    if ((class$java$lang$String = Launcher.class$java$lang$String) == null) {
                        try {
                            class$java$lang$String = (Launcher.class$java$lang$String = Class.forName("java.lang.String"));
                        }
                        catch (ClassNotFoundException ex) {
                            throw new NoClassDefFoundError(ex.getMessage());
                        }
                    }
                    array[n] = class$java$lang$String;
                    Launcher.aeDescConstructor = aeDescClass.getDeclaredConstructor((Class[])array);
                    final Class<?> clazz = forName2;
                    final String s = "makeOSType";
                    final Class[] array2 = { null };
                    final int n2 = 0;
                    Class class$java$lang$String2;
                    if ((class$java$lang$String2 = Launcher.class$java$lang$String) == null) {
                        try {
                            class$java$lang$String2 = (Launcher.class$java$lang$String = Class.forName("java.lang.String"));
                        }
                        catch (ClassNotFoundException ex2) {
                            throw new NoClassDefFoundError(ex2.getMessage());
                        }
                    }
                    array2[n2] = class$java$lang$String2;
                    Launcher.makeOSType = clazz.getDeclaredMethod(s, (Class[])array2);
                    Launcher.putParameter = forName3.getDeclaredMethod("putParameter", Integer.TYPE, Launcher.aeDescClass);
                    Launcher.sendNoReply = forName3.getDeclaredMethod("sendNoReply", (Class[])new Class[0]);
                    Launcher.keyDirectObject = (Integer)forName4.getDeclaredField("keyDirectObject").get(null);
                    Launcher.kAutoGenerateReturnID = (Integer)forName3.getDeclaredField("kAutoGenerateReturnID").get(null);
                    Launcher.kAnyTransactionID = (Integer)forName3.getDeclaredField("kAnyTransactionID").get(null);
                }
                catch (ClassNotFoundException ex3) {
                    Launcher.errorMessage = ex3.getMessage();
                    return false;
                }
                catch (NoSuchMethodException ex4) {
                    Launcher.errorMessage = ex4.getMessage();
                    return false;
                }
                catch (NoSuchFieldException ex5) {
                    Launcher.errorMessage = ex5.getMessage();
                    return false;
                }
                catch (IllegalAccessException ex6) {
                    Launcher.errorMessage = ex6.getMessage();
                    return false;
                }
                break;
            }
            case 1: {
                try {
                    Launcher.mrjFileUtilsClass = Class.forName("com.apple.mrj.MRJFileUtils");
                    Launcher.mrjOSTypeClass = Class.forName("com.apple.mrj.MRJOSType");
                    Launcher.kSystemFolderType = Launcher.mrjFileUtilsClass.getDeclaredField("kSystemFolderType").get(null);
                    Launcher.findFolder = Launcher.mrjFileUtilsClass.getDeclaredMethod("findFolder", Launcher.mrjOSTypeClass);
                    final Class mrjFileUtilsClass = Launcher.mrjFileUtilsClass;
                    final String s2 = "getFileType";
                    final Class[] array3 = { null };
                    final int n3 = 0;
                    Class class$java$io$File;
                    if ((class$java$io$File = Launcher.class$java$io$File) == null) {
                        try {
                            class$java$io$File = (Launcher.class$java$io$File = Class.forName("java.io.File"));
                        }
                        catch (ClassNotFoundException ex7) {
                            throw new NoClassDefFoundError(ex7.getMessage());
                        }
                    }
                    array3[n3] = class$java$io$File;
                    Launcher.getFileType = mrjFileUtilsClass.getDeclaredMethod(s2, (Class[])array3);
                }
                catch (ClassNotFoundException ex8) {
                    Launcher.errorMessage = ex8.getMessage();
                    return false;
                }
                catch (NoSuchFieldException ex9) {
                    Launcher.errorMessage = ex9.getMessage();
                    return false;
                }
                catch (NoSuchMethodException ex10) {
                    Launcher.errorMessage = ex10.getMessage();
                    return false;
                }
                catch (SecurityException ex11) {
                    Launcher.errorMessage = ex11.getMessage();
                    return false;
                }
                catch (IllegalAccessException ex12) {
                    Launcher.errorMessage = ex12.getMessage();
                    return false;
                }
                break;
            }
        }
        return true;
    }
    
    private static Object locateBrowser() {
        if (Launcher.browser != null) {
            return Launcher.browser;
        }
        switch (Launcher.jvm) {
            case 0: {
                try {
                    final Object instance = Launcher.aeTargetConstructor.newInstance((Integer)Launcher.makeOSType.invoke(null, "MACS"));
                    final Integer n = (Integer)Launcher.makeOSType.invoke(null, "GURL");
                    return Launcher.appleEventConstructor.newInstance(n, n, instance, Launcher.kAutoGenerateReturnID, Launcher.kAnyTransactionID);
                }
                catch (IllegalAccessException ex) {
                    Launcher.browser = null;
                    Launcher.errorMessage = ex.getMessage();
                    return Launcher.browser;
                }
                catch (InstantiationException ex2) {
                    Launcher.browser = null;
                    Launcher.errorMessage = ex2.getMessage();
                    return Launcher.browser;
                }
                catch (InvocationTargetException ex3) {
                    Launcher.browser = null;
                    Launcher.errorMessage = ex3.getMessage();
                    return Launcher.browser;
                }
            }
            case 1: {
                File file;
                try {
                    file = (File)Launcher.findFolder.invoke(null, Launcher.kSystemFolderType);
                }
                catch (IllegalArgumentException ex4) {
                    Launcher.browser = null;
                    Launcher.errorMessage = ex4.getMessage();
                    return Launcher.browser;
                }
                catch (IllegalAccessException ex5) {
                    Launcher.browser = null;
                    Launcher.errorMessage = ex5.getMessage();
                    return Launcher.browser;
                }
                catch (InvocationTargetException ex6) {
                    Launcher.browser = null;
                    Launcher.errorMessage = ex6.getTargetException().getClass() + ": " + ex6.getTargetException().getMessage();
                    return Launcher.browser;
                }
                final String[] list = file.list();
                for (int i = 0; i < list.length; ++i) {
                    try {
                        final File file2 = new File(file, list[i]);
                        if (file2.isFile()) {
                            if ("FNDR".equals(Launcher.getFileType.invoke(null, file2).toString())) {
                                return Launcher.browser = file2.toString();
                            }
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        Launcher.browser = Launcher.browser;
                        Launcher.errorMessage = ex7.getMessage();
                        return null;
                    }
                    catch (IllegalAccessException ex8) {
                        Launcher.browser = null;
                        Launcher.errorMessage = ex8.getMessage();
                        return Launcher.browser;
                    }
                    catch (InvocationTargetException ex9) {
                        Launcher.browser = null;
                        Launcher.errorMessage = ex9.getTargetException().getClass() + ": " + ex9.getTargetException().getMessage();
                        return Launcher.browser;
                    }
                }
                Launcher.browser = null;
                break;
            }
            case 2: {
                Launcher.browser = "cmd.exe";
                break;
            }
            case 3: {
                Launcher.browser = "command.com";
                break;
            }
            default: {
                Launcher.browser = "netscape";
                break;
            }
        }
        return Launcher.browser;
    }
    
    public static void openURL(final String s) throws IOException {
        if (!Launcher.loadedWithoutErrors) {
            throw new IOException("Exception in finding browser: " + Launcher.errorMessage);
        }
        final Object locateBrowser = locateBrowser();
        if (locateBrowser == null) {
            throw new IOException("Unable to locate browser: " + Launcher.errorMessage);
        }
        switch (Launcher.jvm) {
            case 1: {
                try {
                    Launcher.putParameter.invoke(locateBrowser, Launcher.keyDirectObject, Launcher.aeDescConstructor.newInstance(s));
                    Launcher.sendNoReply.invoke(locateBrowser, new Object[0]);
                }
                catch (InvocationTargetException ex) {
                    throw new IOException("InvocationTargetException while creating AEDesc: " + ex.getMessage());
                }
                catch (IllegalAccessException ex2) {
                    throw new IOException("IllegalAccessException while building AppleEvent: " + ex2.getMessage());
                }
                catch (InstantiationException ex3) {
                    throw new IOException("InstantiationException while creating AEDesc: " + ex3.getMessage());
                }
                break;
            }
            case 2: {
                Runtime.getRuntime().exec(new String[] { (String)locateBrowser, s });
                break;
            }
            case 3:
            case 4: {
                Runtime.getRuntime().exec(new String[] { (String)locateBrowser, "/c", "start", s });
                break;
            }
            case 0: {
                final Process exec = Runtime.getRuntime().exec(String.valueOf(locateBrowser) + " -remote 'openURL(" + s + ")'");
                try {
                    if (exec.waitFor() != 0) {
                        Runtime.getRuntime().exec(new String[] { (String)locateBrowser, s });
                    }
                }
                catch (InterruptedException ex4) {
                    throw new IOException("InterruptedException while launching browser: " + ex4.getMessage());
                }
                break;
            }
            default: {
                Runtime.getRuntime().exec(new String[] { (String)locateBrowser, s });
                break;
            }
        }
    }
}
