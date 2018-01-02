// 
// Decompiled by Procyon v0.5.30
// 

package com.electa.installer;

import java.util.regex.Pattern;
import java.util.logging.Logger;

public class Utils
{
    private static Logger log;
    public static String ENV_SYSTEMROOT;
    public static String ENV_WINDIR;
    public static String ENV_SYSTEM;
    public static String ENV_SYSTEM32;
    public static String ENV_PROGRAMFILES;
    public static String ENV_USERPROFILE;
    public static String ENV_TEMP;
    public static String ENV_TMP;
    public static final Pattern PATTERN_SYSTEM;
    public static final Pattern PATTERN_SYSTEMROOT;
    public static final Pattern PATTERN_SYSTEM32;
    public static final Pattern PATTERN_PROGRAMFILES;
    public static final Pattern PATTERN_USERPROFILE;
    public static final Pattern PATTERN_TEMP;
    
    static {
        Utils.log = Logger.getLogger(InstallerCtrl.class.getName());
        Utils.ENV_SYSTEMROOT = escapeBackSlash(System.getenv("SystemRoot"));
        Utils.ENV_WINDIR = escapeBackSlash(System.getenv("windir"));
        Utils.ENV_SYSTEM = escapeBackSlash(System.getenv("System"));
        Utils.ENV_SYSTEM32 = escapeBackSlash(System.getenv("System32"));
        Utils.ENV_PROGRAMFILES = escapeBackSlash(System.getenv("ProgramFiles"));
        Utils.ENV_USERPROFILE = escapeBackSlash(System.getenv("userprofile"));
        Utils.ENV_TEMP = escapeBackSlash(System.getenv("temp"));
        Utils.ENV_TMP = escapeBackSlash(System.getenv("tmp"));
        if (isEmpty(Utils.ENV_SYSTEMROOT) && !isEmpty(Utils.ENV_WINDIR)) {
            Utils.ENV_SYSTEMROOT = Utils.ENV_WINDIR;
        }
        if (isEmpty(Utils.ENV_SYSTEM)) {
            Utils.ENV_SYSTEM = Utils.ENV_SYSTEMROOT;
        }
        if (isEmpty(Utils.ENV_SYSTEMROOT)) {
            Utils.ENV_SYSTEMROOT = Utils.ENV_SYSTEM;
        }
        if (isEmpty(Utils.ENV_SYSTEM32)) {
            Utils.ENV_SYSTEM32 = String.valueOf(Utils.ENV_SYSTEMROOT) + "\\\\System32";
        }
        if (isEmpty(Utils.ENV_TEMP)) {
            Utils.ENV_TEMP = Utils.ENV_TMP;
        }
        Utils.log.info("ENV_SYSTEMROOT=" + Utils.ENV_SYSTEMROOT);
        Utils.log.info("ENV_SYSTEM=" + Utils.ENV_SYSTEM);
        Utils.log.info("ENV_SYSTEM32=" + Utils.ENV_SYSTEM32);
        Utils.log.info("ENV_PROGRAMFILES=" + Utils.ENV_PROGRAMFILES);
        Utils.log.info("ENV_USERPROFILE=" + Utils.ENV_USERPROFILE);
        Utils.log.info("ENV_TEMP=" + Utils.ENV_TEMP);
        PATTERN_SYSTEM = Pattern.compile("%system%", 16);
        PATTERN_SYSTEMROOT = Pattern.compile("%systemroot%", 16);
        PATTERN_SYSTEM32 = Pattern.compile("%system32%", 16);
        PATTERN_PROGRAMFILES = Pattern.compile("%programfiles%", 16);
        PATTERN_USERPROFILE = Pattern.compile("%userprofile%", 16);
        PATTERN_TEMP = Pattern.compile("%temp%", 16);
    }
    
    public static boolean isEmpty(final String s) {
        return s == null || s.length() <= 0;
    }
    
    public static String checkEmpty(final String s, final String def) {
        return (s == null || s.length() <= 0) ? def : s;
    }
    
    public static boolean isTrue(final String s) {
        return !isEmpty(s) && s.equalsIgnoreCase("true");
    }
    
    public static String escapeBackSlash(final String s) {
        return updateNullString2Empty(s).replaceAll("\\\\", "\\\\\\\\");
    }
    
    public static String updateNullString2Empty(final String s) {
        return (s == null) ? "" : s;
    }
    
    public static String parsePath(final String path) throws Throwable {
        try {
            String ret = updateNullString2Empty(path).toLowerCase();
            ret = Utils.PATTERN_SYSTEM.matcher(ret).replaceAll(Utils.ENV_SYSTEM);
            ret = Utils.PATTERN_SYSTEM32.matcher(ret).replaceAll(Utils.ENV_SYSTEM32);
            ret = Utils.PATTERN_SYSTEMROOT.matcher(ret).replaceAll(Utils.ENV_SYSTEMROOT);
            ret = Utils.PATTERN_PROGRAMFILES.matcher(ret).replaceAll(Utils.ENV_PROGRAMFILES);
            ret = Utils.PATTERN_USERPROFILE.matcher(ret).replaceAll(Utils.ENV_USERPROFILE);
            ret = Utils.PATTERN_TEMP.matcher(ret).replaceAll(Utils.ENV_TEMP);
            return ret;
        }
        catch (Throwable T) {
            throw new Throwable("Error");
        }
    }
}
