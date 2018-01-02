import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class FileIO
{
    private static final String FILE_NOT_FOUND = ") FileNotFound";
    private static final String IO_EXCEPTION = ") IOException";
    private static final String OPEN_FILE = "Open File (";
    private static final String SAVE_TO_FILE = "Save to File (";
    
    public static char[] readFile(final String s) {
        final File file = new File(s);
        char[] array;
        try {
            final int n = (int)file.length();
            if (n <= 0) {
                System.err.println("Open File (" + s + ") FileNotFound");
                return null;
            }
            array = new char[n];
            if (new BufferedReader(new FileReader(file)).read(array, 0, n) != n) {
                System.err.println("Open File (" + s + ") FileSizeError");
            }
        }
        catch (FileNotFoundException ex) {
            System.err.println("Open File (" + s + ") FileNotFound" + ex);
            return null;
        }
        catch (IOException ex2) {
            System.err.println("Open File (" + s + ") IOException" + ex2);
            return null;
        }
        return array;
    }
    
    public static boolean saveToFile(final String s, final String s2) {
        try {
            final FileWriter fileWriter = new FileWriter(s);
            fileWriter.write(s2);
            fileWriter.close();
        }
        catch (IOException ex) {
            System.err.println("Save to File (" + s + ") IOException" + ex);
            return false;
        }
        return true;
    }
}
