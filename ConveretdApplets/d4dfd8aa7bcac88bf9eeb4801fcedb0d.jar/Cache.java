import java.util.Enumeration;
import java.awt.Event;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class Cache implements Runnable
{
    private Thread m_LoaderThread;
    private ZoomChart m_ZoomChart;
    private String m_TheDataURL;
    private Color[] m_Color_Arr;
    private int m_Max_Records;
    private Vector m_Cache_Name_Vector;
    private Hashtable m_Cache_Curve_Hash;
    private Vector m_New_Name_Vector;
    private Hashtable m_PreloadData_Hash;
    private Date m_StartDate;
    private Date m_EndDate;
    private Curve[] m_CurveArr;
    private boolean m_ShowRequest;
    
    public synchronized void stop() {
        if (this.m_LoaderThread != null) {
            this.m_LoaderThread = null;
            this.m_ZoomChart.postEvent(new Event(this, 1001, ""));
        }
    }
    
    private Curve requestCurveFromCache(final String name, final Date startDate, final Date endDate) {
        final Curve curve = this.m_Cache_Curve_Hash.get(name);
        final Date startDate2 = curve.getStartDate();
        final Date endDate2 = curve.getEndDate();
        final int length = curve.getLength();
        final long time = startDate.getTime();
        final long time2 = startDate2.getTime();
        final long time3 = endDate.getTime();
        final long time4 = endDate2.getTime();
        Curve requestCurveFromURL;
        if (time == time2 && time3 == time4) {
            requestCurveFromURL = curve;
        }
        else if (length <= 1 || length >= this.m_Max_Records || time < time2 || time3 > time4) {
            requestCurveFromURL = this.requestCurveFromURL(name, startDate, endDate);
        }
        else {
            int n = 0;
            for (int i = 0; i < length; ++i) {
                final Date date = curve.getDate(i);
                if (date.getTime() >= startDate.getTime() && date.getTime() <= endDate.getTime()) {
                    ++n;
                }
            }
            requestCurveFromURL = new Curve(n);
            int n2 = 0;
            for (int j = 0; j < length; ++j) {
                final Date date2 = curve.getDate(j);
                if (date2.getTime() >= startDate.getTime() && date2.getTime() <= endDate.getTime()) {
                    requestCurveFromURL.setDate(n2, date2);
                    requestCurveFromURL.setPrice(n2, curve.getPrice(j));
                    ++n2;
                }
            }
            requestCurveFromURL.setStartDate(startDate);
            requestCurveFromURL.setEndDate(endDate);
            requestCurveFromURL.setName(name);
            requestCurveFromURL.setColor(this.getColor(name));
        }
        return requestCurveFromURL;
    }
    
    public void put(final String s, final String s2) {
        if (this.m_New_Name_Vector.indexOf(s) == -1) {
            this.m_New_Name_Vector.addElement(s);
        }
        if (s2 != null) {
            this.m_PreloadData_Hash.put(s, s2);
        }
    }
    
    private void reset() {
        this.m_New_Name_Vector.removeAllElements();
        this.m_PreloadData_Hash.clear();
    }
    
    public Cache(final ZoomChart zoomChart, final Color[] color_Arr, final int max_Records, final String theDataURL, final boolean showRequest) {
        this.m_LoaderThread = null;
        this.m_ZoomChart = zoomChart;
        this.m_Color_Arr = color_Arr;
        this.m_Max_Records = max_Records;
        this.m_TheDataURL = theDataURL;
        this.m_ShowRequest = showRequest;
        this.m_Cache_Name_Vector = new Vector();
        this.m_Cache_Curve_Hash = new Hashtable();
        this.m_New_Name_Vector = new Vector();
        this.m_PreloadData_Hash = new Hashtable();
    }
    
    private Color getColor(final String s) {
        if (this.m_Color_Arr.length == 0) {
            return Color.black;
        }
        Color color = null;
        if (this.m_Cache_Curve_Hash.containsKey(s)) {
            color = this.m_Cache_Curve_Hash.get(s).getColor();
        }
        else {
            for (int n = 0; n < this.m_Color_Arr.length && color == null; ++n) {
                boolean b = false;
                for (int i = 0; i < this.m_Cache_Name_Vector.size(); ++i) {
                    final Curve curve = this.m_Cache_Curve_Hash.get(this.m_Cache_Name_Vector.elementAt(i));
                    if (curve != null && this.m_Color_Arr[n] == curve.getColor()) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    color = this.m_Color_Arr[n];
                }
            }
        }
        return color;
    }
    
    public boolean isBusy() {
        return this.m_LoaderThread != null;
    }
    
    private String getPreloadData(final String s) {
        return this.m_PreloadData_Hash.get(s);
    }
    
    public Date getEndDate() {
        return this.m_EndDate;
    }
    
    public synchronized void start(final Date startDate, final Date endDate) {
        if (this.m_LoaderThread == null) {
            this.m_StartDate = startDate;
            this.m_EndDate = endDate;
            (this.m_LoaderThread = new Thread(this)).start();
        }
    }
    
    private Curve requestCurveFromURL(final String s, final Date startDate, final Date endDate) {
        final HTTPDataSource httpDataSource = new HTTPDataSource(this.m_ShowRequest);
        httpDataSource.setURL(this.m_TheDataURL, this.m_Max_Records);
        httpDataSource.setSymbol(s);
        httpDataSource.setDateRange(startDate, endDate);
        final Curve loadCurve = httpDataSource.loadCurve(this.getPreloadData(s));
        if (loadCurve != null) {
            loadCurve.setStartDate(startDate);
            loadCurve.setEndDate(endDate);
            loadCurve.setName(s);
            loadCurve.setColor(this.getColor(s));
            this.m_Cache_Curve_Hash.put(s, loadCurve);
        }
        return loadCurve;
    }
    
    public void run() {
        if (this.m_LoaderThread == null) {
            return;
        }
        try {
            this.m_CurveArr = this.threadLoad(this.m_StartDate, this.m_EndDate);
        }
        catch (Exception ex) {
            this.m_CurveArr = null;
        }
        this.stop();
    }
    
    public Date getStartDate() {
        return this.m_StartDate;
    }
    
    public Curve[] getCurveArr() {
        return this.m_CurveArr;
    }
    
    private Curve[] threadLoad(final Date date, final Date date2) {
        final int size = this.m_New_Name_Vector.size();
        if (size == 0) {
            return null;
        }
        this.updateNameVector();
        final Curve[] array = new Curve[size];
        for (int i = 0; i < size; ++i) {
            final String s = this.m_Cache_Name_Vector.elementAt(i);
            Curve curve;
            if (this.m_Cache_Curve_Hash.containsKey(s)) {
                curve = this.requestCurveFromCache(s, date, date2);
            }
            else {
                curve = this.requestCurveFromURL(s, date, date2);
            }
            if (curve == null) {
                this.reset();
                return null;
            }
            array[i] = curve;
        }
        this.reset();
        return array;
    }
    
    private void updateNameVector() {
        final Enumeration<Object> elements = this.m_Cache_Name_Vector.elements();
        while (elements.hasMoreElements()) {
            final Object nextElement = elements.nextElement();
            if (this.m_New_Name_Vector.indexOf(nextElement) == -1) {
                this.m_Cache_Curve_Hash.remove(nextElement);
            }
        }
        this.m_Cache_Name_Vector.removeAllElements();
        for (int i = 0; i < this.m_New_Name_Vector.size(); ++i) {
            this.m_Cache_Name_Vector.addElement(this.m_New_Name_Vector.elementAt(i));
        }
    }
}
