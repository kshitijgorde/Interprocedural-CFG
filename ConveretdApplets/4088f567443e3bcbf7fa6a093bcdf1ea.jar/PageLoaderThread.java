import java.util.Vector;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.net.URL;
import teletext.TeletextPage;

// 
// Decompiled by Procyon v0.5.30
// 

class PageLoaderThread implements Runnable
{
    TeletextEmulator teletextEmulator;
    int pageNumber;
    String baseURL;
    
    public TeletextPage loadTeletextPage(final int n, final int n2) {
        final TeletextPage teletextPage = new TeletextPage();
        try {
            String s;
            if (n2 >= 0) {
                s = "http://" + this.baseURL + "/" + n + "s" + (n2 + 1) + ".tlv";
            }
            else {
                s = "http://" + this.baseURL + "/" + n + ".tlv";
            }
            System.out.println("Path: " + s);
            final InputStreamReader inputStreamReader = new InputStreamReader(new URL(s).openStream());
            int n3 = 0;
            int read;
            while ((read = inputStreamReader.read()) != -1) {
                teletextPage.setCharacterAt(n3 % 40, n3 / 40, (char)read);
                ++n3;
            }
            teletextPage.customToAscii();
            inputStreamReader.close();
        }
        catch (MalformedURLException ex) {
            System.out.println("Error: MalformedURLException");
            teletextPage.print(0, 1, "MalformedURLException");
            return teletextPage;
        }
        catch (IOException ex2) {
            System.out.println("IOException");
            return null;
        }
        return teletextPage;
    }
    
    public void run() {
        final Vector teletextPages = this.teletextEmulator.getTeletextPages();
        final TeletextPage loadTeletextPage = this.loadTeletextPage(this.pageNumber, -1);
        if (loadTeletextPage != null) {
            teletextPages.removeAllElements();
            teletextPages.addElement(loadTeletextPage);
            this.teletextEmulator.setPageFound(true);
        }
        else {
            int n = 0;
            TeletextPage teletextPage = this.loadTeletextPage(this.pageNumber, n++);
            System.out.println("first page: " + teletextPage);
            if (teletextPage != null) {
                this.teletextEmulator.setPageFound(true);
                teletextPages.removeAllElements();
                while (teletextPage != null) {
                    teletextPages.addElement(teletextPage);
                    teletextPage = this.loadTeletextPage(this.pageNumber, n++);
                }
            }
            else {
                this.teletextEmulator.setPageFound(false);
            }
        }
        this.teletextEmulator.setRollingNumber(0);
        this.teletextEmulator.render();
        this.teletextEmulator.repaint();
    }
    
    public void setPageNumber(final int pageNumber) {
        this.pageNumber = pageNumber;
    }
    
    public PageLoaderThread(final TeletextEmulator teletextEmulator, final String baseURL) {
        this.teletextEmulator = teletextEmulator;
        this.pageNumber = 100;
        this.baseURL = baseURL;
    }
}
