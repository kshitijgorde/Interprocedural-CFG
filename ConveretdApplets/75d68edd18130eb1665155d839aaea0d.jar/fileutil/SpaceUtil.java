// 
// Decompiled by Procyon v0.5.30
// 

package fileutil;

import java.lang.reflect.Method;

public class SpaceUtil
{
    private String _FileName;
    private SFile _File;
    private static final Method _getFreeSpaceMethod;
    private static final Method _getTotalSpaceMethod;
    public static final boolean _isRootAccessible;
    
    public SpaceUtil(final String fileName) {
        this._FileName = fileName;
        this._File = new SFile(fileName);
    }
    
    public String getPartition() {
        if (FileUtil.OS == FileUtil.OS_TYPE.WINDOWS) {
            final int index;
            if ((index = this._FileName.indexOf("/", 1)) != -1) {
                return this._FileName.substring(0, index);
            }
        }
        else {
            final int index2;
            if ((index2 = this._FileName.indexOf(":\\")) != -1) {
                return this._FileName.substring(0, index2 + 1);
            }
        }
        return null;
    }
    
    public String getFreeSpace() {
        if (SpaceUtil._getFreeSpaceMethod == null) {
            return "";
        }
        try {
            return getFileUnit((long)SpaceUtil._getFreeSpaceMethod.invoke(this._File, new Object[0]));
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public String getUsedSpace() {
        if (SpaceUtil._getFreeSpaceMethod == null || SpaceUtil._getTotalSpaceMethod == null) {
            return "";
        }
        try {
            return getFileUnit((long)SpaceUtil._getTotalSpaceMethod.invoke(this._File, new Object[0]) - (long)SpaceUtil._getFreeSpaceMethod.invoke(this._File, new Object[0]));
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public static long getTotalSpace(final SFile sFile) {
        if (SpaceUtil._getTotalSpaceMethod == null) {
            return 0L;
        }
        try {
            return (long)SpaceUtil._getTotalSpaceMethod.invoke(sFile, new Object[0]);
        }
        catch (Exception ex) {
            return 0L;
        }
    }
    
    public static String getFileUnit(final long n) {
        if (0L >= n) {}
        String s;
        if (n >> 10 < 1L) {
            s = String.format("%5.2f Bytes", n);
        }
        else if (n >> 20 < 1L) {
            s = String.format("%5.2f KB", n / 1024.0f);
        }
        else if (n >> 30 < 1L) {
            s = String.format("%5.2f MB", n / 1048576.0f);
        }
        else {
            s = String.format("%5.2f GB", n / 1.07374182E9f);
        }
        return s;
    }
    
    static {
        Method declaredMethod = null;
        Method declaredMethod2 = null;
        boolean isRootAccessible = false;
        try {
            final Class<?> forName = Class.forName("java.io.File");
            declaredMethod = forName.getDeclaredMethod("getFreeSpace", (Class<?>[])new Class[0]);
            declaredMethod2 = forName.getDeclaredMethod("getTotalSpace", (Class<?>[])new Class[0]);
            isRootAccessible = true;
        }
        catch (Throwable t) {}
        _getFreeSpaceMethod = declaredMethod;
        _getTotalSpaceMethod = declaredMethod2;
        _isRootAccessible = isRootAccessible;
    }
}
