import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStream;
import java.net.URLConnection;
import java.net.URL;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class MyConnecter extends Thread
{
    private byte[] bImage;
    private String m_addr;
    private int m_port;
    private String m_sFileName;
    private int iLen;
    private WebCam m_ap;
    private Image m_image;
    public boolean m_bRun;
    private int m_iErrorCount;
    private boolean m_bLoading;
    private long m_lTimeTick;
    private String m_strBuf;
    private URL m_url;
    private URLConnection m_urlCon;
    private InputStream is;
    
    public MyConnecter(final String addr, final int port, final String sFileName, final WebCam ap) {
        this.bImage = new byte[360000];
        this.m_bRun = false;
        this.m_iErrorCount = 0;
        this.m_bLoading = true;
        this.m_lTimeTick = 0L;
        this.m_addr = addr;
        this.m_port = port;
        this.m_sFileName = sFileName;
        final int index = this.m_sFileName.indexOf("//");
        if (index != -1) {
            this.m_sFileName = this.m_sFileName.substring(index + 2);
            this.m_sFileName = this.m_sFileName.substring(this.m_sFileName.indexOf("/"));
        }
        this.m_bRun = false;
        this.m_ap = ap;
    }
    
    public void setImage(final String sFileName) {
        this.m_sFileName = sFileName;
        final int index = this.m_sFileName.indexOf("//");
        if (index != -1) {
            this.m_sFileName = this.m_sFileName.substring(index + 2);
            this.m_sFileName = this.m_sFileName.substring(this.m_sFileName.indexOf("/"));
        }
    }
    
    public Image getImage() {
        return this.m_image;
    }
    
    protected boolean request() {
        try {
            this.m_url = new URL("http", this.m_addr, this.m_port, this.m_sFileName);
            (this.m_urlCon = this.m_url.openConnection()).setUseCaches(false);
            this.m_urlCon.setRequestProperty("User-Agent", "GeoVision (Compatible; MSIE 5.00; Windows 98)");
            this.m_urlCon.connect();
            return true;
        }
        catch (MalformedURLException ex) {
            return this.m_bRun = false;
        }
        catch (IOException ex2) {
            return this.m_bRun = false;
        }
    }
    
    public void startThread() {
        this.m_bRun = true;
        if (this.isAlive()) {
            return;
        }
        this.start();
    }
    
    public void stopThread() {
        this.m_bRun = false;
    }
    
    public void run() {
        while (this.m_bRun) {
            if (System.currentTimeMillis() - this.m_lTimeTick < 50L) {
                try {
                    Thread.sleep(50L);
                }
                catch (Exception ex2) {}
            }
            this.m_lTimeTick = System.currentTimeMillis();
            if (this.m_bLoading) {
                this.m_ap.showLoading();
            }
            if (!this.request()) {
                ++this.m_iErrorCount;
                if (this.m_iErrorCount >= 10) {
                    this.m_ap.showError();
                    this.m_bRun = false;
                }
                else {
                    this.m_ap.showLoading();
                    try {
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex3) {}
                }
            }
            else {
                try {
                    System.gc();
                    this.iLen = this.m_urlCon.getContentLength();
                    this.is = this.m_urlCon.getInputStream();
                    final int n = 0;
                    for (int i = n + this.is.read(this.bImage, n, this.iLen - n); i != this.iLen; i += this.is.read(this.bImage, i, this.iLen - i)) {}
                    this.is.close();
                }
                catch (Exception ex) {
                    System.out.println("read exception!! ".concat(String.valueOf(String.valueOf(ex))));
                    ++this.m_iErrorCount;
                    if (this.m_iErrorCount >= 3) {
                        this.m_ap.showError();
                        this.m_bRun = false;
                        continue;
                    }
                    this.m_ap.showLoading();
                    try {
                        this.is.close();
                        Thread.sleep(1000L);
                    }
                    catch (Exception ex4) {}
                    continue;
                }
                this.m_image = Toolkit.getDefaultToolkit().createImage(this.bImage);
                final MediaTracker mediaTracker = new MediaTracker(this.m_ap);
                mediaTracker.addImage(this.m_image, 0);
                try {
                    mediaTracker.waitForID(0);
                    if (mediaTracker.isErrorAny()) {
                        ++this.m_iErrorCount;
                        if (this.m_iErrorCount < 30) {
                            continue;
                        }
                        this.m_bLoading = false;
                        this.m_ap.showError();
                        this.m_iErrorCount = 0;
                    }
                    else {
                        this.m_bLoading = false;
                        this.m_iErrorCount = 0;
                        this.m_ap.showImage(this.m_image);
                    }
                }
                catch (Exception ex5) {
                    this.m_bLoading = false;
                    this.m_iErrorCount = 0;
                    this.m_ap.showError();
                }
            }
        }
    }
}
