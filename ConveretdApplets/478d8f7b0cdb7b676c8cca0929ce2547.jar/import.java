import java.io.OutputStream;
import java.io.FileOutputStream;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLClassLoader;
import java.net.URL;
import javax.swing.JApplet;
import java.awt.Component;
import java.util.Properties;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class import
{
    private static String lUa = "\u143e\u1412\u1413\u141b\u1414\u141a\u1408\u140f\u141c\u1409\u1414\u1412\u1413\u145d\u141b\u1414\u1411\u1418\u145d\u141b\u1412\u140f\u145d\u1433\u1438\u1429\u145d\u143e\u1411\u1412\u141e\u1416\u145d\u140d\u140f\u1412\u141a\u140f\u141c\u1410\u1477\u1477";
    private File mUa;
    private Properties nUa;
    private static String T = "\u1438\u140f\u140f\u1412\u140f\u145d\u141e\u1412\u1413\u1413\u1418\u141e\u1409\u1414\u1413\u141a\u145d\u1409\u1412\u145d\u140e\u1418\u140f\u140b\u1418\u140f\u1447\u145d";
    private static String U = "";
    private static String V = "\u142d\u140f\u1412\u140d\u1418\u140f\u1409\u1404\u145d\u140a\u1414\u1409\u1415\u145d\u1413\u141c\u1410\u1418\u1447\u145d";
    private static String W = "\u145d\u1414\u140e\u145d\u1418\u1410\u140d\u1409\u1404\u145d\u1412\u140f\u145d\u1413\u1412\u1409\u145d\u141b\u1412\u1408\u1413\u1419\u145c";
    private static String ba = "\u1438\u140f\u140f\u1412\u140f\u145d\u141e\u140f\u1418\u141c\u1409\u1414\u1413\u141a\u145d\u1431\u1412\u141e\u141c\u1409\u141c\u141f\u1411\u1418\u145d\u1434\u1410\u141c\u141a\u1418\u145d\u1413\u141c\u1410\u1418\u1419\u1447\u145d";
    private static String ca = "\u143e\u1412\u1413\u141b\u1414\u141a\u1408\u140f\u141c\u1409\u1414\u1412\u1413\u145d\u141b\u1414\u1411\u1418\u145d\u141b\u1412\u140f\u145d\u1433\u1438\u1429\u145d\u143e\u1411\u1412\u141e\u1416\u145d\u140d\u140f\u1412\u141a\u140f\u141c\u1410\u1477\u1477";
    private static String da = "\u1408\u140e\u1418\u140f\u1453\u1415\u1412\u1410\u1418";
    private static String ea = "\u141b\u1414\u1411\u1418\u1453\u140e\u1418\u140d\u141c\u140f\u141c\u1409\u1412\u140f";
    private static String ta = "\u1453\u1433\u1438\u1429\u143e\u1411\u1412\u141e\u1416";
    
    public import(final String s, final Component component) {
        this.mUa = null;
        this.nUa = new Properties();
        try {
            InputStream inputStream;
            if (component instanceof JApplet) {
                inputStream = new URL(String.valueOf(String.valueOf(((JApplet)component).getCodeBase())).concat(String.valueOf(String.valueOf(s)))).openConnection().getInputStream();
            }
            else {
                inputStream = ((URLClassLoader)component.getClass().getClassLoader()).getResourceAsStream(s);
            }
            this.nUa.load(inputStream);
        }
        catch (FileNotFoundException ex) {
            throw new IOException(ex.getMessage());
        }
        catch (MalformedURLException ex2) {
            throw new IOException(import.T.concat(String.valueOf(String.valueOf(ex2.getMessage()))));
        }
        catch (Exception ex3) {
            throw new IOException(ex3.getMessage());
        }
    }
    
    public import(final String s) {
        this.mUa = null;
        this.nUa = new Properties();
        try {
            this.mUa = new File(String.valueOf(String.valueOf(this.j())).concat(String.valueOf(String.valueOf(s))));
            this.nUa.load(new FileInputStream(this.mUa));
        }
        catch (FileNotFoundException ex2) {}
        catch (Exception ex) {
            throw new IOException(ex.getMessage());
        }
    }
    
    public String getProperty(final String s) {
        final String property = this.nUa.getProperty(s);
        if (property == null || property == import.U) {
            System.err.println(String.valueOf(String.valueOf(new StringBuffer(import.V).append(s).append(import.W))));
        }
        return property;
    }
    
    public void _(final String s, final String s2) {
        this.nUa.setProperty(s, s2);
    }
    
    public Image _(final String s, final Component component) {
        return new instanceof()._(this.nUa.getProperty(s), component);
    }
    
    public interface _(final String s, final String s2, final String s3, final Component component) {
        if (this.nUa.getProperty(s) == null || this.nUa.getProperty(s2) == null || this.nUa.getProperty(s3) == null) {
            throw new IOException(import.ba.concat(String.valueOf(String.valueOf(s))));
        }
        return new instanceof().b(this.nUa.getProperty(s), Integer.parseInt(this.nUa.getProperty(s2)), Integer.parseInt(this.nUa.getProperty(s3)), component);
    }
    
    public void b(final Component component) {
        try {
            new File(this.j()).mkdirs();
            this.mUa.createNewFile();
            final FileOutputStream fileOutputStream = new FileOutputStream(this.mUa, false);
            this.nUa.store(fileOutputStream, import.ca);
            fileOutputStream.close();
        }
        catch (FileNotFoundException ex) {
            throw new IOException(ex.getMessage());
        }
        catch (IOException ex2) {
            throw new IOException(ex2.getMessage());
        }
    }
    
    private String j() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(System.getProperty(import.da)))).append(System.getProperty(import.ea)).append(import.ta).append(System.getProperty(import.ea))));
    }
    
    static {
        import.lUa = d(import.lUa);
        import.T = d(import.T);
        import.U = d(import.U);
        import.V = d(import.V);
        import.W = d(import.W);
        import.ba = d(import.ba);
        import.ca = d(import.ca);
        import.da = d(import.da);
        import.ea = d(import.ea);
        import.ta = d(import.ta);
        import.lUa = import.ca;
    }
    
    private static String d(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0x1147D);
        }
        return new String(array);
    }
}
