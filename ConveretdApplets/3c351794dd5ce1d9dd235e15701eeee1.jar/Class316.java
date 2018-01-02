import java.io.RandomAccessFile;
import java.io.File;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class Class316
{
    private static String aString2693;
    private static Hashtable aHashtable2694;
    private static boolean aBoolean2695;
    private static int anInt2696;
    private static String aString2697;
    
    public static File method3648(final int n, final int n2, final String s, final String s2) {
        try {
            if (!Class316.aBoolean2695) {
                throw new RuntimeException("");
            }
            final File file = Class316.aHashtable2694.get(s);
            if (file != null) {
                return file;
            }
            final String[] array = { "c:/rscache/", "/rscache/", "c:/windows/", "c:/winnt/", "c:/", Class316.aString2693, "/tmp/", "" };
            final String[] array2 = { ".dementhium_cache_" + n, ".file_store_" + n };
            for (int n3 = n2; ~n3 > -3; ++n3) {
                for (int n4 = 0; ~n4 > ~array2.length; ++n4) {
                    for (int n5 = 0; array.length > n5; ++n5) {
                        final String string = array[n5] + array2[n4] + "/" + ((s2 != null) ? (s2 + "/") : "") + s;
                        RandomAccessFile randomAccessFile = null;
                        try {
                            final File file2 = new File(string);
                            if (~n3 != -1 || file2.exists()) {
                                final String s3 = array[n5];
                                if (~n3 != 0xFFFFFFFE || s3.length() <= 0 || new File(s3).exists()) {
                                    new File(array[n5] + array2[n4]).mkdir();
                                    if (s2 != null) {
                                        new File(array[n5] + array2[n4] + "/" + s2).mkdir();
                                    }
                                    randomAccessFile = new RandomAccessFile(file2, "rw");
                                    final int read = randomAccessFile.read();
                                    randomAccessFile.seek(0L);
                                    randomAccessFile.write(read);
                                    randomAccessFile.seek(0L);
                                    randomAccessFile.close();
                                    Class316.aHashtable2694.put(s, file2);
                                    return file2;
                                }
                            }
                        }
                        catch (Exception ex2) {
                            try {
                                if (randomAccessFile != null) {
                                    randomAccessFile.close();
                                }
                            }
                            catch (Exception ex3) {}
                        }
                    }
                }
            }
            throw new RuntimeException();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static File method3649(final String s, final int n) {
        try {
            return method3648(Class316.anInt2696, 0, s, Class316.aString2697);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static void method3650(final boolean b, final int anInt2696, final String aString2697) {
        try {
            Class316.anInt2696 = anInt2696;
            Class316.aString2697 = aString2697;
            try {
                if (b) {
                    return;
                }
                Class316.aString2693 = System.getProperty("user.home");
                if (Class316.aString2693 != null) {
                    Class316.aString2693 += "/";
                }
            }
            catch (Exception ex2) {}
            Class316.aBoolean2695 = true;
            if (Class316.aString2693 == null) {
                Class316.aString2693 = "~/";
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static {
        Class316.aBoolean2695 = false;
        Class316.aHashtable2694 = new Hashtable(16);
    }
}
