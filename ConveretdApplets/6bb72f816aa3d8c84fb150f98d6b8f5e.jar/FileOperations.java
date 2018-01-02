import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class FileOperations
{
    public static int TotalRead;
    public static int TotalWrite;
    public static int CompleteWrite;
    
    public static final byte[] ReadFile(final String s) {
        try {
            final int n = (int)new File(s).length();
            final byte[] array = new byte[n];
            final DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(s)));
            dataInputStream.readFully(array, 0, n);
            dataInputStream.close();
            ++FileOperations.TotalRead;
            return array;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static final void WriteFile(final String s, final byte[] array) {
        try {
            new File(new File(s).getParent()).mkdirs();
            final FileOutputStream fileOutputStream = new FileOutputStream(s);
            fileOutputStream.write(array, 0, array.length);
            fileOutputStream.close();
            ++FileOperations.TotalWrite;
            ++FileOperations.CompleteWrite;
        }
        catch (Throwable t) {
            System.out.println("Write Error: " + s);
        }
    }
    
    public static boolean FileExists(final String s) {
        return new File(s).exists();
    }
    
    static {
        FileOperations.TotalRead = 0;
        FileOperations.TotalWrite = 0;
        FileOperations.CompleteWrite = 0;
    }
}
