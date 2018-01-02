import java.util.StringTokenizer;
import java.net.MalformedURLException;
import java.io.EOFException;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.util.Vector;
import java.net.URL;
import java.util.Observable;

// 
// Decompiled by Procyon v0.5.30
// 

class NetworkTextFile extends Observable implements Runnable
{
    private URL textURL;
    private int maxFileSize;
    byte[] byteBuffer;
    String textString;
    private Vector textVector;
    private boolean notifyOnRead;
    private String header;
    private String lastHeader;
    private FontMetrics fm;
    private int maxWidth;
    private Thread monitor;
    private int milliseconds;
    
    public synchronized String[] getText() {
        final String[] array = new String[this.textVector.size()];
        for (int i = 0; i < this.textVector.size(); ++i) {
            array[i] = (String)this.textVector.elementAt(i);
        }
        return array;
    }
    
    public void setRefreshTime(final int n) {
        this.milliseconds = n * 1000;
    }
    
    public synchronized void startRefresh() {
        this.header = "x";
        this.lastHeader = null;
        (this.monitor = new Thread(this)).start();
    }
    
    public synchronized void stopRefresh() {
        this.monitor.stop();
        this.monitor = null;
    }
    
    private void reportError(final String s) {
        System.out.println("[NetworkTextFile] " + s);
    }
    
    public void run() {
        final String s = "U";
        final String s2 = "C";
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException ex4) {}
        while (true) {
            boolean b = false;
            synchronized (this) {
                DataInputStream dataInputStream;
                try {
                    dataInputStream = new DataInputStream(this.textURL.openStream());
                }
                catch (Exception ex) {
                    dataInputStream = null;
                    this.reportError("Open error - " + ex);
                }
                if (dataInputStream != null) {
                    try {
                        this.header = dataInputStream.readLine();
                        if (this.header != null && !this.header.equals(this.lastHeader)) {
                            b = true;
                            this.lastHeader = this.header;
                            this.header = null;
                            for (int i = 0; i < this.maxFileSize; ++i) {
                                this.byteBuffer[i] = 0;
                            }
                            dataInputStream.readFully(this.byteBuffer);
                        }
                    }
                    catch (EOFException ex5) {}
                    catch (Exception ex2) {
                        this.reportError("Read error - " + ex2);
                    }
                    try {
                        dataInputStream.close();
                    }
                    catch (Exception ex3) {
                        this.reportError("Close error - " + ex3);
                    }
                }
                if (b || this.notifyOnRead) {
                    this.setChanged();
                    if (b) {
                        this.formatText(this.textString = new String(this.byteBuffer, 0).trim());
                        this.textString = null;
                        this.notifyObservers(s2);
                    }
                    else {
                        this.notifyObservers(s);
                    }
                    this.clearChanged();
                }
            }
            try {
                Thread.sleep(this.milliseconds);
            }
            catch (InterruptedException ex6) {}
        }
    }
    
    public NetworkTextFile(final URL url, final String s, final int n, final FontMetrics fm, final int maxWidth, final int refreshTime, final boolean notifyOnRead) throws MalformedURLException {
        this.notifyOnRead = notifyOnRead;
        this.maxFileSize = n * 1024;
        this.byteBuffer = new byte[this.maxFileSize];
        this.maxWidth = maxWidth;
        this.setRefreshTime(refreshTime);
        this.textURL = new URL(url, s);
        this.fm = fm;
        this.textVector = new Vector(10, 10);
    }
    
    private void formatText(final String s) {
        this.textVector.removeAllElements();
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        int stringWidth = 0;
        String nextToken;
        String string2;
        String string = string2 = (nextToken = "");
        while (stringTokenizer.hasMoreTokens()) {
            if (string.equals("")) {
                string = (nextToken = stringTokenizer.nextToken());
            }
            if (string.charAt(0) == '<') {
                final boolean equals = string.equals("<P>");
                if (!equals && !string.equals("<BR>")) {
                    continue;
                }
                if (stringWidth > 0) {
                    this.textVector.addElement(string2);
                }
                if (equals) {
                    this.textVector.addElement(" ");
                }
                string = "";
                string2 = "";
                stringWidth = 0;
            }
            else {
                if (!string2.equals("")) {
                    string = " " + string;
                }
                final int stringWidth2 = this.fm.stringWidth(string);
                if (stringWidth + stringWidth2 < this.maxWidth) {
                    string2 += string;
                    stringWidth += stringWidth2;
                    string = "";
                }
                else {
                    this.textVector.addElement(string2);
                    string2 = nextToken;
                    string = "";
                    stringWidth = this.fm.stringWidth(string2);
                }
            }
        }
        if (stringWidth > 0) {
            this.textVector.addElement(string2);
        }
        this.textVector.trimToSize();
    }
}
