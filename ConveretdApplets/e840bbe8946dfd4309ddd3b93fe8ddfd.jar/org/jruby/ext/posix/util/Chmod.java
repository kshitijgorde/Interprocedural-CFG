// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix.util;

import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import java.io.File;
import java.lang.reflect.Method;

public class Chmod
{
    private static final boolean CHMOD_API_AVAILABLE;
    private static final Method setWritable;
    private static final Method setReadable;
    private static final Method setExecutable;
    
    public static int chmod(final File file, final String mode) {
        if (Chmod.CHMOD_API_AVAILABLE) {
            char other = '0';
            if (mode.length() >= 1) {
                other = mode.charAt(mode.length() - 1);
            }
            char user = '0';
            if (mode.length() >= 3) {
                user = mode.charAt(mode.length() - 3);
            }
            if (!setPermissions(file, other, false)) {
                return -1;
            }
            if (!setPermissions(file, user, true)) {
                return -1;
            }
        }
        else {
            try {
                final Process chmod = Runtime.getRuntime().exec("/bin/chmod " + mode + " " + file.getAbsolutePath());
                chmod.waitFor();
                return chmod.exitValue();
            }
            catch (IOException ioe) {}
            catch (InterruptedException ex) {}
        }
        return -1;
    }
    
    private static boolean setPermissions(final File file, final char permChar, final boolean userOnly) {
        final int permValue = Character.digit(permChar, 8);
        try {
            if ((permValue & 0x1) != 0x0) {
                Chmod.setExecutable.invoke(file, Boolean.TRUE, userOnly);
            }
            else {
                Chmod.setExecutable.invoke(file, Boolean.FALSE, userOnly);
            }
            if ((permValue & 0x2) != 0x0) {
                Chmod.setWritable.invoke(file, Boolean.TRUE, userOnly);
            }
            else {
                Chmod.setWritable.invoke(file, Boolean.FALSE, userOnly);
            }
            if ((permValue & 0x4) != 0x0) {
                Chmod.setReadable.invoke(file, Boolean.TRUE, userOnly);
            }
            else {
                Chmod.setReadable.invoke(file, Boolean.FALSE, userOnly);
            }
            return true;
        }
        catch (IllegalAccessException iae) {}
        catch (InvocationTargetException ex) {}
        return false;
    }
    
    static {
        boolean apiAvailable = false;
        Method setWritableVar = null;
        Method setReadableVar = null;
        Method setExecutableVar = null;
        try {
            setWritableVar = File.class.getMethod("setWritable", Boolean.TYPE, Boolean.TYPE);
            setReadableVar = File.class.getMethod("setReadable", Boolean.TYPE, Boolean.TYPE);
            setExecutableVar = File.class.getMethod("setExecutable", Boolean.TYPE, Boolean.TYPE);
            apiAvailable = true;
        }
        catch (Exception ex) {}
        setWritable = setWritableVar;
        setReadable = setReadableVar;
        setExecutable = setExecutableVar;
        CHMOD_API_AVAILABLE = apiAvailable;
    }
}
