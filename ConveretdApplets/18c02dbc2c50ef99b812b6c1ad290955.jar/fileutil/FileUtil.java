// 
// Decompiled by Procyon v0.5.30
// 

package fileutil;

import java.io.File;
import java.text.Collator;
import javax.swing.JFileChooser;

public class FileUtil
{
    private static final String[] SUPPORTED_ICON;
    private static final String DIR_IMG = "folder.png";
    private static final String FILE_IMG = "misc.png";
    private static final String[] SUPPORTED_ARCH;
    public static JFileChooser FILETYPE_TABLE;
    public static Collator LOCALE;
    public static OS_TYPE OS;
    
    public static String returnFileExt(final String s) {
        if (null == s) {
            return null;
        }
        int lastIndex = s.lastIndexOf(".");
        if (-1 != lastIndex && ++lastIndex != s.length()) {
            return s.substring(lastIndex);
        }
        return "";
    }
    
    public static String getImgFileExt(final String s, final boolean b) {
        if (null == s) {
            return "misc.png";
        }
        if (b) {
            return "folder.png";
        }
        final String returnFileExt = returnFileExt(s);
        if (null == returnFileExt) {
            return "misc.png";
        }
        final String lowerCase = returnFileExt.toLowerCase();
        for (int i = 0; i < FileUtil.SUPPORTED_ICON.length; ++i) {
            if (FileUtil.SUPPORTED_ICON[i].equals(lowerCase)) {
                return lowerCase + ".png";
            }
        }
        return "misc.png";
    }
    
    public static String getMimetype(final String s) {
        return "application/octet-stream";
    }
    
    public static boolean isCompressedFile(final String s, final boolean b) {
        if (b || null == s || -1 == s.indexOf(".")) {
            return false;
        }
        final String returnFileExt = returnFileExt(s);
        for (int i = 0; i < FileUtil.SUPPORTED_ARCH.length; ++i) {
            if (0 == returnFileExt.compareToIgnoreCase(FileUtil.SUPPORTED_ARCH[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static String getTypeDescription(final SFile sFile) {
        try {
            return (FileUtil.OS != OS_TYPE.WINDOWS || FileUtil.FILETYPE_TABLE == null) ? returnFileExt(sFile.getName()) : FileUtil.FILETYPE_TABLE.getTypeDescription(sFile);
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    static {
        SUPPORTED_ICON = new String[] { "3fr", "3g2", "3gp", "7z", "ac3", "actproj", "ad", "akp", "amr", "applescript", "applix", "arw", "as", "asax", "asc", "ascii", "ascx", "asf", "asm", "asmx", "asp", "aspx", "asr", "avi", "bin", "binary", "bmp", "bz2", "c", "cc", "cdr", "core", "cpp", "cr2", "crw", "cs", "csv", "cxx", "daa", "dat", "dcr", "deb", "divx", "dng", "doc", "docx", "dvi", "dvr-ms", "erf", "exe", "f", "flv", "font", "gf", "gif", "gz", "h", "htm", "html", "ico", "ifo", "img", "info", "iso", "java", "jpe", "jpeg", "jpg", "js", "jsx", "k25", "kdc", "l", "log", "m2t", "m2ts", "m4v", "make", "man", "mds", "mef", "mhtml", "midi", "mkv", "moc", "mos", "mov", "mp4", "mpe", "mpeg", "mpg", "mrw", "mts", "nef", "nrg", "o", "orf", "otf", "pdf", "pef", "php", "pk", "pl", "png", "ppt", "pptx", "ps", "psd", "ptx", "py", "qt", "raf", "rar", "raw", "rm", "rmvb", "rpm", "rtf", "rw2", "s", "sr2", "srf", "swf", "tar", "tbz", "tex", "tgz", "tif", "tiff", "tp", "trp", "ts", "ttc", "ttf", "txt", "txt2", "ufo", "vob", "wmv", "wri", "x3f", "xhtml", "xls", "xlst", "xvid", "y", "zip" };
        SUPPORTED_ARCH = new String[] { "zip", "gz", "tar", "tgz", "bz2", "rar" };
        FileUtil.LOCALE = Collator.getInstance();
        try {
            FileUtil.FILETYPE_TABLE = new JFileChooser();
        }
        catch (Throwable t) {
            FileUtil.FILETYPE_TABLE = null;
        }
        FileUtil.OS = ((-1 == System.getProperty("os.name").indexOf("Windows")) ? OS_TYPE.OTHERS : OS_TYPE.WINDOWS);
    }
    
    public enum OS_TYPE
    {
        WINDOWS, 
        OTHERS;
    }
}
