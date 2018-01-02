import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URLConnection;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class CacheDownloader
{
    private client client;
    private final int BUFFER = 1024;
    private final int VERSION = 1;
    private String cacheLink;
    private String fileToExtract;
    
    public CacheDownloader(final client client) {
        this.cacheLink = "http://dl.dropbox.com/u/32985851/DevilishpkzCache.zip";
        this.fileToExtract = this.getCacheDir() + this.getArchivedName();
        this.client = client;
    }
    
    private void drawLoadingText(final String s) {
        this.client.drawLoadingText(35, s);
        System.out.println(s);
    }
    
    private void drawLoadingText(final int n, final String s) {
        this.client.drawLoadingText(n, s);
        System.out.println(s);
    }
    
    private String getCacheDir() {
        return SignLink.findcachedir();
    }
    
    private String getCacheLink() {
        return this.cacheLink;
    }
    
    private int getCacheVersion() {
        return 1;
    }
    
    public CacheDownloader downloadCache() {
        try {
            final File file = new File(this.getCacheDir());
            final File file2 = new File(this.getCacheDir() + "/cacheVersion" + this.getCacheVersion() + ".dat");
            if (!file.exists()) {
                this.downloadFile(this.getCacheLink(), this.getArchivedName());
                new BufferedWriter(new FileWriter(this.getCacheDir() + "/cacheVersion" + this.getCacheVersion() + ".dat")).close();
            }
            else {
                if (file2.exists()) {
                    return null;
                }
                this.downloadFile(this.getCacheLink(), this.getArchivedName());
                new BufferedWriter(new FileWriter(this.getCacheDir() + "/cacheVersion" + this.getCacheVersion() + ".dat")).close();
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private void downloadFile(final String s, final String s2) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            final URL url = new URL(s);
            outputStream = new BufferedOutputStream(new FileOutputStream(this.getCacheDir() + "/" + s2));
            final URLConnection openConnection = url.openConnection();
            inputStream = openConnection.getInputStream();
            final byte[] array = new byte[1024];
            long n = 0L;
            final int contentLength = openConnection.getContentLength();
            int read;
            while ((read = inputStream.read(array)) != -1) {
                outputStream.write(array, 0, read);
                n += read;
                final int n2 = (int)(n / contentLength * 100.0);
                this.drawLoadingText(n2, "Downloading Cache " + n2 + "%");
            }
            System.out.println(s2 + "\t" + n);
            this.drawLoadingText("Finished downloading " + this.getArchivedName() + "!");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
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
        this.fileExists();
    }
    
    private String getArchivedName() {
        final int lastIndex = this.getCacheLink().lastIndexOf(47);
        if (lastIndex >= 0 && lastIndex < this.getCacheLink().length() - 1) {
            return this.getCacheLink().substring(lastIndex + 1);
        }
        System.err.println("error retreiving archivaed name.");
        return "";
    }
    
    private void unZip() {
        try {
            final ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(this.fileToExtract)));
            ZipEntry nextEntry;
            while ((nextEntry = zipInputStream.getNextEntry()) != null) {
                if (nextEntry.isDirectory()) {
                    new File(this.getCacheDir() + nextEntry.getName()).mkdir();
                }
                else {
                    if (nextEntry.getName().equals(this.fileToExtract)) {
                        this.unzip(zipInputStream, this.fileToExtract);
                        break;
                    }
                    this.unzip(zipInputStream, this.getCacheDir() + nextEntry.getName());
                }
                this.drawLoadingText("[UN-ZIP]: " + nextEntry.getName());
            }
            zipInputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.unNeededCacheExists();
    }
    
    private void unzip(final ZipInputStream zipInputStream, final String s) throws IOException {
        final FileOutputStream fileOutputStream = new FileOutputStream(s);
        final byte[] array = new byte[1024];
        int read;
        while ((read = zipInputStream.read(array)) != -1) {
            fileOutputStream.write(array, 0, read);
        }
    }
    
    public void fileExists() {
        if (!new File(SignLink.findcachedir()).exists()) {
            System.out.println("The cache was not downloaded correctly.");
            System.out.println("Please download it manually at:");
            System.out.println("http://dl.dropbox.com/u/32985851/DevilishpkzCache.zip");
            this.cacheDownloadError();
        }
        else {
            System.out.println("Your cache is downloaded and ready to un-zip!");
            this.unZip();
        }
    }
    
    public void unNeededCacheExists() {
        if (!new File("CACHE DIRECTORY LINK").exists()) {
            System.out.println("Your Client Just Hates You");
            System.out.println("You are GARBAGE TODAY AYE?!");
        }
        else {
            System.out.println("Your cache is still on your HDD");
            System.out.println("Attempting to delete...");
            this.delete();
        }
    }
    
    public void delete() {
        try {
            final File file = new File("CACHE DIRECTORY LINK");
            if (file.delete()) {
                System.out.println("[SUCCESS]" + file.getName() + " was deleted!");
            }
            else {
                System.out.println("[ERROR]" + file.getName() + " was not deleted.");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void cacheDownloadError() {
        try {
            System.out.println("Cache Download Error: 0x21V82");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
