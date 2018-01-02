// 
// Decompiled by Procyon v0.5.30
// 

package jlog.io;

import java.util.StringTokenizer;
import java.io.File;

public class $JW
{
    public static final String $ZPD = "/\\";
    private static boolean $FQD;
    private static boolean $GQD;
    
    public static String $AX(String replace) {
        replace = replace.trim().replace('\\', '/');
        final String[] $jqd = $JQD(replace);
        final int length = $jqd.length;
        int i = 0;
        int n = -1;
        while (i < length) {
            if ($jqd[i].length() == 0) {
                $jqd[i] = null;
            }
            else if ($jqd[i].equals(".")) {
                if (i != 0) {
                    $jqd[i] = null;
                }
            }
            else if ($jqd[i].equals("..")) {
                if (n != -1) {
                    $jqd[n] = ($jqd[i] = null);
                    while (--n != -1) {
                        if ($jqd[n] != null && !$jqd[n].equals(".") && !$jqd[n].equals("..")) {
                            break;
                        }
                    }
                }
            }
            else {
                n = i;
            }
            ++i;
        }
        final StringBuffer sb = new StringBuffer(128);
        if (replace.startsWith("/") || replace.startsWith(String.valueOf(File.separator))) {
            sb.append('/');
        }
        for (int j = 0; j < length; ++j) {
            if ($jqd[j] != null) {
                sb.append($jqd[j]);
                sb.append('/');
            }
        }
        sb.append(getFile(replace));
        return sb.toString();
    }
    
    public static String $BY(String $ax, String $ax2) {
        $ax = $AX($ax);
        $ax2 = $AX($ax2);
        final String[] directories = getDirectories($ax);
        final String[] directories2 = getDirectories($ax2);
        final int length = directories.length;
        final int length2 = directories2.length;
        int min;
        int i;
        for (min = Math.min(length, length2), i = 0; i < min && compare(directories[i], directories2[i]) == 0; ++i) {}
        if (i == 0) {
            return $ax2;
        }
        final StringBuffer sb = new StringBuffer(128);
        for (int j = i; j < length; ++j) {
            sb.append("../");
        }
        while (i < length2) {
            sb.append(directories2[i]);
            sb.append('/');
            ++i;
        }
        sb.append(getFile($ax2));
        return sb.toString();
    }
    
    static String[] $JQD(final String s) {
        if (s.equals("")) {
            return new String[0];
        }
        final StringTokenizer stringTokenizer = new StringTokenizer($KQD(s), "/\\", false);
        final int countTokens = stringTokenizer.countTokens();
        final String[] array = new String[countTokens];
        for (int i = 0; i < countTokens; array[i++] = (String)stringTokenizer.nextElement()) {}
        return array;
    }
    
    static String $KQD(final String s) {
        final int $lqd = $LQD(s);
        if ($lqd == -1) {
            return "";
        }
        return s.substring(0, $lqd + 1);
    }
    
    static int $LQD(final String s) {
        final int lastIndex = s.lastIndexOf(47);
        final int lastIndex2 = s.lastIndexOf(File.separator);
        if (lastIndex == -1) {
            return lastIndex2;
        }
        if (lastIndex2 == -1) {
            return lastIndex;
        }
        return Math.max(lastIndex, lastIndex2);
    }
    
    public static String $NEC(final String s) {
        return $AX(s).replace('/', File.separatorChar);
    }
    
    static {
        $JW.$FQD = false;
        $JW.$GQD = false;
    }
    
    public static int compare(final Object o, final Object o2) {
        String s = $AX((String)o);
        String s2 = $AX((String)o2);
        if (!$JW.$GQD) {
            $JW.$FQD = (System.getProperty("os.name", "").toLowerCase().indexOf("windows") != -1);
            $JW.$GQD = true;
        }
        if ($JW.$FQD) {
            s = s.toLowerCase();
            s2 = s2.toLowerCase();
        }
        return s.compareTo(s2);
    }
    
    public static String concat(final String s, final String s2) {
        return $AX(String.valueOf(getDir(s)) + s2);
    }
    
    public static String getDir(final String s) {
        return $AX($KQD(s));
    }
    
    public static String[] getDirectories(final String s) {
        return $JQD($AX(s));
    }
    
    public static String getFile(final String s) {
        int $lqd = $LQD(s);
        if ($lqd == -1) {
            return s;
        }
        return s.substring(++$lqd);
    }
    
    public static boolean isDir(final String s) {
        return s.endsWith("/") || s.endsWith(String.valueOf(File.separator)) || s.equals("");
    }
    
    public static boolean isFile(final String s) {
        return isDir(s) ^ true;
    }
    
    public static boolean isFilename(final String s) {
        return !isDir(s) && s.indexOf(47) == -1 && s.indexOf(File.separator) == -1;
    }
}
