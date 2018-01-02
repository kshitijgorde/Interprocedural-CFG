import java.util.StringTokenizer;
import java.io.DataInputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Vector;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

public class HTTPDataSource
{
    private String m_URL;
    private String m_Symbol;
    private Date m_StartDate;
    private Date m_EndDate;
    private int m_MaxRecords;
    private boolean m_ShowRequest;
    
    public void setSymbol(final String symbol) {
        this.m_Symbol = symbol;
    }
    
    public HTTPDataSource(final boolean showRequest) {
        this.m_ShowRequest = showRequest;
    }
    
    private String getStringFromDate(final Date date) {
        return new Integer(1900 + date.getYear()).toString() + ((date.getMonth() < 9) ? ("0" + new Integer(date.getMonth() + 1).toString()) : new Integer(date.getMonth() + 1).toString()) + ((date.getDate() < 10) ? ("0" + new Integer(date.getDate()).toString()) : new Integer(date.getDate()).toString());
    }
    
    public static Date getDateFromString(final String s) {
        final char[] array = { '0', '0', '0', '0' };
        final char[] array2 = { '0', '0' };
        final char[] array3 = { '0', '0' };
        array[0] = s.charAt(0);
        array[1] = s.charAt(1);
        array[2] = s.charAt(2);
        array[3] = s.charAt(3);
        array2[0] = s.charAt(4);
        array2[1] = s.charAt(5);
        array3[0] = s.charAt(6);
        array3[1] = s.charAt(7);
        return new Date(Integer.parseInt(new String(array)) - 1900, Integer.parseInt(new String(array2)) - 1, Integer.parseInt(new String(array3)));
    }
    
    public Curve loadCurve(final String s) {
        try {
            final Vector vector = new Vector<String>();
            if (s == null) {
                final String string = this.m_URL + "?" + "symbol=" + URLEncoder.encode(this.m_Symbol) + "&" + "start=" + URLEncoder.encode(this.getStringFromDate(this.m_StartDate)) + "&" + "end=" + URLEncoder.encode(this.getStringFromDate(this.m_EndDate)) + "&" + "maxrecords=" + String.valueOf(this.m_MaxRecords);
                if (this.m_ShowRequest) {
                    System.out.println(string);
                }
                final DataInputStream dataInputStream = new DataInputStream(new URL(string).openConnection().getInputStream());
                String line;
                while ((line = dataInputStream.readLine()) != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(line, "|");
                    while (stringTokenizer.hasMoreTokens()) {
                        vector.addElement(stringTokenizer.nextToken());
                    }
                }
                dataInputStream.close();
            }
            else {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(s, ",");
                while (stringTokenizer2.hasMoreTokens()) {
                    final StringTokenizer stringTokenizer3 = new StringTokenizer(stringTokenizer2.nextToken(), "|");
                    while (stringTokenizer3.hasMoreTokens()) {
                        vector.addElement(stringTokenizer3.nextToken());
                    }
                }
            }
            final int n = vector.size() / 2;
            final Curve curve = (n < 3) ? new Curve(1) : new Curve(n);
            curve.setIsMA(this.getIsMovingAverage());
            int n2 = 0;
            for (int n3 = 0; n3 < 2 * n - 1 && curve.getLength() != 1; ++n3) {
                if (n3 % 2 == 0) {
                    final Date dateFromString = getDateFromString(vector.elementAt(n3));
                    final double doubleValue = new Double(vector.elementAt(n3 + 1));
                    curve.setDate(n2, dateFromString);
                    curve.setPrice(n2, doubleValue);
                    ++n2;
                }
            }
            return curve;
        }
        catch (Exception ex) {
            ex.printStackTrace(System.out);
            return null;
        }
    }
    
    public void setDateRange(final Date startDate, final Date endDate) {
        this.m_StartDate = startDate;
        this.m_EndDate = endDate;
    }
    
    public void setURL(final String url, final int maxRecords) {
        this.m_URL = url;
        this.m_MaxRecords = maxRecords;
    }
    
    private boolean getIsMovingAverage() {
        return this.m_Symbol.indexOf("(") != -1 || this.m_Symbol.indexOf(")") != -1;
    }
}
