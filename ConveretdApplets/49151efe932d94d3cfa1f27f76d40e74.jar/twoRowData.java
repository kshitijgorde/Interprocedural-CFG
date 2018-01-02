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

public class twoRowData
{
    dataInterface parentmsg;
    long delay;
    Hashtable quotes;
    Hashtable quoteLoader;
    Thread dataThread;
    public String strSymbols;
    DecimalFormat df;
    private String dataUrl;
    private String baseDataUrl;
    String mode;
    private String displayCurrency;
    
    public twoRowData(final dataInterface parentmsg) {
        this.delay = 120000L;
        this.quoteLoader = null;
        this.dataThread = null;
        this.df = new DecimalFormat("#,###,##0.00##");
        this.mode = "name";
        this.displayCurrency = "false";
        this.parentmsg = parentmsg;
        this.quotes = null;
    }
    
    public Hashtable getQuotes() {
        return this.quotes;
    }
    
    public void setBaseUrl(final String baseUrl, final String dow, final String wmid) {
        final StringBuffer stBuffer = new StringBuffer().append(baseUrl).append("streamer/getTickerQuotes.csv?");
        if (dow != null && !dow.equals("null")) {
            stBuffer.append("dji=").append(dow).append("&");
        }
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
                            final String[] strLine = new String[4];
                            strLine[0] = strRow[0];
                            if (strRow[1].indexOf("N/A") >= 0) {
                                strLine[1] = strRow[0];
                            }
                            else {
                                strLine[1] = strRow[1];
                            }
                            if (this.mode.equals("symbol")) {
                                strLine[1] = strRow[0];
                            }
                            if (strRow[2].toUpperCase().indexOf("N/A") >= 0) {
                                strRow[2] = "0.00";
                            }
                            if (strRow[3].toUpperCase().indexOf("N/A") >= 0) {
                                strRow[3] = "0.00";
                            }
                            final float fv1 = Float.valueOf(strRow[2]);
                            final float fv2 = Float.valueOf(strRow[3]);
                            strLine[2] = String.valueOf(this.df.format(fv1)).trim();
                            strLine[3] = String.valueOf(this.df.format(fv2)).trim();
                            if (strRow[4].charAt(0) == '+' || fv2 > 0.0f) {
                                strLine[3] = "+" + strLine[3];
                            }
                            this.quoteLoader.put(strRow[0], strLine);
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
