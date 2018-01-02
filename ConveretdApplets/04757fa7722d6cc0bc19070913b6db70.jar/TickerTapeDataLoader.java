import java.net.URLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.io.InputStreamReader;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

public class TickerTapeDataLoader extends Thread
{
    private String URLString;
    private String dataStream;
    private String[] dataArray1;
    private String[] dataArray2;
    private boolean dataAvailStatus;
    private boolean fetchDataStatus;
    private int refreshTime;
    
    public TickerTapeDataLoader() {
        this.URLString = "";
        this.dataAvailStatus = false;
        this.fetchDataStatus = false;
    }
    
    public void setURL(final String urlString) {
        this.URLString = urlString;
    }
    
    public void setDataStream(final String dataStream) {
        this.dataStream = dataStream;
    }
    
    public void setRefreshTime(final String s) {
        this.refreshTime = Integer.valueOf(s) * 1000;
    }
    
    public void refreshData() {
        this.dataAvailStatus = false;
        this.fetchDataStatus = true;
        String strn;
        if (this.URLString.equals("")) {
            strn = this.dataStream;
        }
        else {
            strn = this.executeQuery();
        }
        final String[] string2Array = Convert.string2Array(strn, ";");
        String string = "";
        String string2 = "";
        for (int i = 0; i < string2Array.length; ++i) {
            if (i % 4 < 2) {
                string = string + string2Array[i] + ";";
            }
            else {
                string2 = string2 + string2Array[i] + ";";
            }
        }
        this.setDataArray(string, string2);
        this.dataAvailStatus = true;
        this.fetchDataStatus = false;
    }
    
    private void setDataArray(final String strn, final String strn2) {
        this.dataArray1 = Convert.string2Array(strn, ";");
        this.dataArray2 = Convert.string2Array(strn2, ";");
    }
    
    public String executeQuery() {
        String string = "";
        try {
            final URLConnection openConnection = new URL(this.URLString).openConnection();
            openConnection.setDoOutput(true);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            openConnection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            final InputStreamReader inputStreamReader = new InputStreamReader(openConnection.getInputStream());
            for (int i = inputStreamReader.read(); i != -1; i = inputStreamReader.read()) {
                string += String.valueOf((char)i);
            }
            inputStreamReader.close();
        }
        catch (MalformedURLException ex) {
            return " ; ; ;" + ex + ";";
        }
        catch (IOException ex2) {
            return " ; ; ;" + ex2 + ";";
        }
        catch (Exception ex3) {
            return " ; ; ;" + ex3 + ";";
        }
        return string;
    }
    
    public String[] getDataStream1() {
        return this.dataArray1;
    }
    
    public String[] getDataStream2() {
        this.dataAvailStatus = false;
        return this.dataArray2;
    }
    
    public boolean dataAvailable() {
        return this.dataAvailStatus;
    }
    
    public boolean fetchingData() {
        return this.fetchDataStatus;
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.refreshTime);
            }
            catch (Exception ex) {}
            if (!this.fetchingData()) {
                this.refreshData();
            }
        }
    }
}
