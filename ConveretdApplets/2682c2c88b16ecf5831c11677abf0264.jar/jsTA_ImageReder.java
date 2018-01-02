import java.awt.image.ImageObserver;
import java.util.NoSuchElementException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.util.StringTokenizer;
import java.applet.Applet;
import java.awt.Image;
import java.awt.MediaTracker;

// 
// Decompiled by Procyon v0.5.30
// 

class jsTA_ImageReder extends Thread
{
    int m_nImageNum;
    MediaTracker m_Tracker;
    Image[] m_ImageArray;
    Applet m_Applet;
    String m_aFileName;
    boolean m_debug;
    
    private void Debug(final String s) {
        if (this.m_debug) {
            System.out.println(s);
        }
    }
    
    public jsTA_ImageReder(final Applet applet, final String aFileName) {
        this.m_debug = false;
        this.m_nImageNum = 0;
        this.m_Applet = applet;
        this.m_aFileName = aFileName;
    }
    
    public int getNumOfImage() {
        return this.m_nImageNum;
    }
    
    public boolean startImageRead(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        try {
            this.m_ImageArray = new Image[stringTokenizer.countTokens()];
            this.m_Tracker = new MediaTracker(this.m_Applet);
            while (stringTokenizer.hasMoreElements()) {
                final String s2 = (String)stringTokenizer.nextElement();
                URL url;
                try {
                    url = new URL(this.m_Applet.getCodeBase() + s2.trim());
                }
                catch (MalformedURLException ex) {
                    ex.printStackTrace();
                    return false;
                }
                this.m_ImageArray[this.m_nImageNum] = this.m_Applet.getImage(url);
                this.m_Tracker.addImage(this.m_ImageArray[this.m_nImageNum], this.m_nImageNum);
                ++this.m_nImageNum;
            }
            try {
                this.m_Tracker.waitForAll();
                for (int i = 0; i < this.m_nImageNum; ++i) {
                    this.m_Tracker.checkID(i);
                }
            }
            catch (InterruptedException ex2) {
                ex2.printStackTrace();
                return false;
            }
        }
        catch (NoSuchElementException ex3) {
            ex3.printStackTrace();
        }
        return true;
    }
    
    public Image getImage(final int n) {
        if (n < this.m_nImageNum && n >= 0) {
            return this.m_ImageArray[n];
        }
        return null;
    }
    
    public int getImageWidth(final int n) {
        if (n < this.m_nImageNum && n >= 0) {
            return this.m_ImageArray[n].getWidth(this.m_Applet);
        }
        return 0;
    }
    
    public void run() {
        if (this.m_aFileName != null) {
            this.startImageRead(this.m_aFileName);
        }
    }
}
