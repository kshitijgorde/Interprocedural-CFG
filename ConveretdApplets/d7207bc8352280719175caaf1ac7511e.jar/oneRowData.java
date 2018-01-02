import java.util.StringTokenizer;
import java.io.InputStream;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class oneRowData
{
    dataInterface parentmsg;
    long delay;
    Hashtable quotes;
    Hashtable quoteLoader;
    Thread dataThread;
    DecimalFormat df;
    private String dataUrl;
    private String baseDataUrl;
    private String mode;
    private String displayCurrency;
    
    public oneRowData(final dataInterface parentmsg) {
        this.delay = 120000L;
        this.quoteLoader = null;
        this.dataThread = null;
        this.df = new DecimalFormat("#,###,##0.00##");
        this.mode = "both";
        this.displayCurrency = "false";
        this.parentmsg = parentmsg;
        this.quotes = null;
    }
    
    public Hashtable getQuotes() {
        return this.quotes;
    }
    
    public void setDisplayMode(final String mode) {
        this.mode = mode;
    }
    
    public void setBaseUrl(final String baseUrl, final String wmid, final String hostName) {
        final StringBuffer stBuffer = new StringBuffer().append(baseUrl).append("quotetools/getTickerQuotes.csv?host=").append(hostName).append("&");
        if (wmid != null) {
            stBuffer.append("webmasterId=").append(wmid).append("&");
        }
        this.baseDataUrl = stBuffer.toString();
    }
    
    public void setSymbol(final String symbols) {
        this.dataUrl = this.baseDataUrl + symbols;
    }
    
    public void getQuoteFromServer() {
        String strResponse = "";
        BufferedReader ds = null;
        try {
            final URL url = new URL(this.dataUrl);
            final URLConnection connection = url.openConnection();
            int i = 0;
            connection.setRequestProperty("Accept-Encoding", "gzip");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            InputStream rs = null;
            if (connection.getContentEncoding() != null && connection.getContentEncoding().indexOf("gzip") > -1) {
                rs = new GZIPInputStream(connection.getInputStream());
            }
            else {
                rs = connection.getInputStream();
            }
            this.quoteLoader = new Hashtable();
            int j = 0;
            if (rs != null) {
                ds = new BufferedReader(new InputStreamReader(rs));
                while ((strResponse = ds.readLine()) != null) {
                    if (strResponse != null && strResponse.length() > 5) {
                        final String[] strRow = this.getColumns(strResponse, 8);
                        if (strRow != null) {
                            final String[] strLine = new String[6];
                            strLine[0] = strRow[0];
                            try {
                                if (strRow[1].trim().equalsIgnoreCase("n/a")) {
                                    strLine[1] = strRow[0].toUpperCase();
                                }
                                else {
                                    strLine[1] = strRow[1];
                                }
                                if (this.mode.equalsIgnoreCase("symbol")) {
                                    strLine[1] = strRow[0];
                                }
                                try {
                                    if (this.displayCurrency.equalsIgnoreCase("true") && strRow[7] != null) {
                                        strLine[1] = strLine[1] + " (" + strRow[7] + ") ";
                                    }
                                }
                                catch (Exception ex) {}
                                if (!strRow[2].trim().equalsIgnoreCase("n/a")) {
                                    try {
                                        final float fv1 = Float.valueOf(strRow[2]);
                                        strLine[2] = String.valueOf(this.df.format(fv1)).trim();
                                    }
                                    catch (Exception exp) {
                                        strLine[2] = "N/A";
                                    }
                                }
                                else {
                                    strLine[2] = strRow[2].toUpperCase();
                                }
                                float fv2 = 0.0f;
                                if (!strRow[3].trim().equalsIgnoreCase("n/a")) {
                                    try {
                                        fv2 = Float.valueOf(strRow[3]);
                                        strLine[3] = String.valueOf(this.df.format(fv2)).trim();
                                    }
                                    catch (Exception exp2) {
                                        strLine[3] = "N/A";
                                    }
                                }
                                else {
                                    strLine[3] = strRow[3].toUpperCase();
                                }
                                float fv3 = 0.0f;
                                if (!strRow[4].trim().equalsIgnoreCase("n/a")) {
                                    try {
                                        fv3 = Float.valueOf(strRow[4]);
                                        strLine[4] = String.valueOf(this.df.format(fv3)).trim();
                                    }
                                    catch (Exception exp3) {
                                        strLine[4] = "N/A";
                                    }
                                }
                                else {
                                    strLine[4] = "N/A";
                                }
                                if (fv2 > 0.0f) {
                                    strLine[3] = "+" + strLine[3];
                                }
                                if (fv3 > 0.0f) {
                                    strLine[4] = "+" + strLine[4];
                                }
                                else if (fv3 < 0.0f) {
                                    strLine[4] = "-" + strLine[4];
                                }
                                if (strLine[4].indexOf("N/A") < 0) {
                                    final StringBuffer sb = new StringBuffer();
                                    final String[] array = strLine;
                                    final int n = 4;
                                    array[n] = sb.append(array[n]).append("%").toString();
                                }
                                strLine[5] = strRow[6].trim();
                                this.quoteLoader.put(strRow[0], strLine);
                            }
                            catch (Exception ex2) {}
                            ++i;
                        }
                    }
                    ++j;
                }
            }
            ds.close();
            this.quotes = this.quoteLoader;
        }
        catch (Exception exception1) {
            System.out.println(exception1.toString());
        }
    }
    
    public void setDisplayCurrency(final String displayCurrency) {
        this.displayCurrency = displayCurrency;
    }
    
    public String[] getColumns(final String line, final int cols) {
        try {
            int i = 0;
            final String[] str = new String[cols];
            if (line.length() > 3) {
                for (StringTokenizer stk = new StringTokenizer(line, ","); stk.hasMoreTokens() && i < cols; ++i) {
                    final String strTmp = stk.nextToken();
                    if (strTmp != null) {
                        str[i] = strTmp;
                    }
                    else {
                        str[i] = "";
                    }
                }
            }
            return str;
        }
        catch (Exception exception1) {
            return null;
        }
    }
}
