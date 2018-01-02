import java.net.URLConnection;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.MalformedURLException;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class ListDataRefresher extends Thread
{
    SetTogether \u00fc;
    String \u0124;
    boolean \u0125;
    String \u0126;
    String \u0127;
    
    public ListDataRefresher(final SetTogether \u00fc, final String \u0125) {
        this.\u0125 = false;
        this.\u00fc = \u00fc;
        this.\u0124 = \u0125;
    }
    
    public void run() {
        while (true) {
            if (this.\u0125) {
                this.\u0126();
            }
            try {
                Thread.sleep(5000L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    void \u0124() {
        this.\u0125 = true;
    }
    
    void \u0125() {
        this.\u0125 = false;
    }
    
    void \u0126() {
        this.\u00fc.\u0276(this.getLists());
    }
    
    public String getLists() {
        final StringBuffer sb = new StringBuffer();
        URL url = null;
        final String s = "/nytimes-settogether/multiplayerservletny?messagetype=getlists";
        try {
            url = new URL("http", this.\u0124, 80, s);
        }
        catch (MalformedURLException ex) {
            System.out.println("UNABLE TO DETECT SERVER");
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            for (String s2 = dataInputStream.readLine(); s2 != null; s2 = dataInputStream.readLine()) {
                sb.append(s2);
            }
            dataInputStream.close();
        }
        catch (IOException ex2) {
            System.out.println("Getting lists failed");
        }
        return sb.toString();
    }
    
    public boolean haalKamerlijst() {
        URL url = null;
        final String s = "/nytimes-settogether/multiplayerservletny?messagetype=roomlist";
        try {
            url = new URL("http", this.\u0124, 80, s);
        }
        catch (MalformedURLException ex) {
            System.out.println("UNABLE TO DETECT SERVER");
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            this.\u0127 = dataInputStream.readLine().trim();
            dataInputStream.close();
        }
        catch (IOException ex2) {
            System.out.println("Getting roomList failed");
            return false;
        }
        return this.\u0127 != null;
    }
    
    public boolean haalWachtlijst() {
        URL url = null;
        final String s = "/nytimes-settogether/multiplayerservletny?messagetype=waitinguserlist";
        try {
            url = new URL("http", this.\u0124, 80, s);
        }
        catch (MalformedURLException ex) {
            System.out.println("UNABLE TO DETECT SERVER");
        }
        try {
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            final DataInputStream dataInputStream = new DataInputStream(openConnection.getInputStream());
            this.\u0126 = dataInputStream.readLine().trim();
            dataInputStream.close();
        }
        catch (IOException ex2) {
            System.out.println("Getting waitingList failed");
            return false;
        }
        return this.\u0126 != null;
    }
}
