// 
// Decompiled by Procyon v0.5.30
// 

package bbpi.dnc.simstats;

import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Vector;
import java.net.URL;

class RecordSender extends Thread
{
    public static final String VERSION = "1.0.2.1";
    private URL url;
    String sessionId;
    private int siteId;
    private int simuladorId;
    private String ip;
    private String jvm;
    private Vector lista;
    private boolean debug;
    private boolean errorOcurred;
    
    public RecordSender(final int n, final int n2, final URL url) {
        this(n, n2, url, "");
    }
    
    public RecordSender(final int siteId, final int simuladorId, final URL url, final String ip) {
        this.ip = "";
        this.debug = false;
        this.errorOcurred = false;
        this.siteId = siteId;
        this.simuladorId = simuladorId;
        this.url = url;
        if (this.debug) {
            System.out.println(String.valueOf(this.url.getHost()) + this.url.getFile());
        }
        this.lista = new Vector();
        this.jvm = String.valueOf(System.getProperty("os.name")) + "|" + System.getProperty("java.vendor") + "|" + System.getProperty("java.version") + "|" + System.getProperty("java.class.version");
        this.ip = ip;
    }
    
    private synchronized Record addOrGetRecord(final Record record) {
        if (record != null) {
            this.lista.addElement(record);
            return null;
        }
        if (this.lista.size() == 0) {
            return null;
        }
        final Record record2 = this.lista.elementAt(0);
        this.lista.removeElementAt(0);
        return record2;
    }
    
    public void addRecord(final Record record) {
        this.addOrGetRecord(record);
        synchronized (this) {
            this.notify();
        }
    }
    
    public static final String getVERSION() {
        return "1.0.2.1";
    }
    
    public boolean hasErrorOcurred() {
        return this.errorOcurred;
    }
    
    public void run() {
        try {
            while (true) {
                while (this.lista.size() == 0) {
                    synchronized (this) {
                        this.wait();
                    }
                }
                this.sendSimulationRecords();
            }
        }
        catch (InterruptedException ex) {
            System.out.println("ie=" + ex);
        }
    }
    
    private void sendSimulationRecords() {
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        Record addOrGetRecord;
        while ((addOrGetRecord = this.addOrGetRecord(null)) != null) {
            sb.append("&r" + n + "=" + addOrGetRecord.toString());
            ++n;
        }
        if (n == 0) {
            return;
        }
        if (this.debug) {
            System.out.println("Records to send: " + n);
            System.out.println("Site Id: " + this.siteId + " | Sim Id: " + this.simuladorId + " | sessionID: " + this.sessionId);
            System.out.println("\t" + sb.toString());
        }
        try {
            final String string = "size=" + n + "&siteId=" + this.siteId + "&simuladorId=" + this.simuladorId + ((this.sessionId != null) ? ("&id=" + this.sessionId) : "") + "&ip=" + this.ip + "&jvm=" + this.jvm + sb.toString();
            if (this.debug) {
                System.out.println(String.valueOf(this.url.getHost()) + this.url.getFile() + string);
            }
            final URLConnection openConnection = this.url.openConnection();
            openConnection.setDoOutput(true);
            final PrintWriter printWriter = new PrintWriter(openConnection.getOutputStream());
            printWriter.println(string);
            if (this.debug) {
                System.out.println("Write OK");
            }
            printWriter.close();
            if (this.sessionId == null) {
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
                final String line = bufferedReader.readLine();
                bufferedReader.close();
                if (line.charAt(0) == '!') {
                    System.out.println("StatError:" + line);
                    this.errorOcurred = true;
                }
                else {
                    this.sessionId = line;
                    if (this.sessionId == null) {
                        System.out.println("Session Id not returned.");
                        this.errorOcurred = true;
                    }
                    else {
                        System.out.println("Session ID: " + this.sessionId);
                        this.errorOcurred = false;
                    }
                }
            }
        }
        catch (IOException ex) {
            if (this.debug) {
                System.out.println(ex);
                ex.printStackTrace();
                this.errorOcurred = true;
            }
        }
        if (this.hasErrorOcurred()) {
            System.out.println("Error!");
        }
    }
    
    public void setDebug(final boolean debug) {
        this.debug = debug;
    }
}
