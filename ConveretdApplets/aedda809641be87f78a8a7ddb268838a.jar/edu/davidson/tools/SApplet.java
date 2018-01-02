// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.tools;

import edu.davidson.display.Format;
import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.applet.Applet;

public class SApplet extends Applet
{
    public static Hashtable dataSources;
    public static Hashtable dataListeners;
    static Object runningID;
    private SClock localClock;
    public SClock clock;
    public BusyFlag lock;
    public Vector dataConnections;
    protected String oneShotMsg;
    protected boolean autoRefresh;
    public int debugLevel;
    public static int staticDebugLevel;
    
    public SApplet() {
        this.localClock = new SClock(this);
        this.clock = this.localClock;
        this.lock = new BusyFlag();
        this.dataConnections = new Vector();
        this.oneShotMsg = null;
        this.autoRefresh = true;
        this.debugLevel = 0;
    }
    
    public synchronized void destroy() {
        this.deleteDataConnections();
        this.localClock.panicStopClock();
        Hashtable hashtable;
        synchronized (SApplet.dataSources) {
            hashtable = (Hashtable)SApplet.dataSources.clone();
        }
        // monitorexit(SApplet.dataSources)
        final Enumeration<Object> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final Object nextElement = keys.nextElement();
            final SDataSource sDataSource = SApplet.dataSources.get(nextElement);
            if (sDataSource != null && sDataSource.getOwner() == this) {
                SApplet.dataSources.remove(nextElement);
            }
        }
        synchronized (SApplet.dataListeners) {
            hashtable = (Hashtable)SApplet.dataListeners.clone();
        }
        // monitorexit(SApplet.dataListeners)
        final Enumeration<Object> keys2 = hashtable.keys();
        while (keys2.hasMoreElements()) {
            final Object nextElement2 = keys2.nextElement();
            final SDataListener sDataListener = SApplet.dataListeners.get(nextElement2);
            if (sDataListener != null && sDataListener.getOwner() == this) {
                SApplet.dataListeners.remove(nextElement2);
            }
        }
        this.localClock.removeAllClockListeners();
    }
    
    private SDataConnection getExistingConnection(final int n, final int n2, final int n3) {
        return null;
    }
    
    public int makeDataConnection(final int n, final int n2, final int n3, String s, String s2) {
        if (s == null) {
            s = "0";
        }
        if (s2 == null) {
            s2 = "0";
        }
        if (this.debugLevel > 7) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer("making connection. SourceID=").append(n).append("ListenerID=").append(n2))));
        }
        final SDataListener dataListener = getDataListener(n2);
        final SDataSource dataSource = getDataSource(n);
        if (dataSource != null && dataListener != null) {
            SDataConnection existingConnection = this.getExistingConnection(n, n2, n3);
            if (existingConnection == null) {
                if (this.debugLevel > 7) {
                    System.out.println(String.valueOf(String.valueOf(new StringBuffer("xStr=").append(s).append(" yStr=").append(s2))));
                }
                existingConnection = new SDataConnection(dataSource, dataListener, n3, s, s2);
                this.dataConnections.addElement(existingConnection);
            }
            else {
                System.out.println("Warning: Data connection already exists.");
            }
            return existingConnection.hashCode();
        }
        System.out.println(String.valueOf(String.valueOf(new StringBuffer("DataConnection not made.  Listener:").append(dataListener).append("  Source:").append(dataSource))));
        return 0;
    }
    
    public void clearAllData() {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().clearData();
        }
    }
    
    public void clearData(final int n) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (sDataConnection.getDataSource().hashCode() == n) {
                sDataConnection.clearData();
            }
        }
    }
    
    public void updateDataConnection(final int n) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (sDataConnection.getDataSource().hashCode() == n) {
                sDataConnection.registerDatum();
            }
        }
    }
    
    public void updateDataConnections() {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (sDataConnection.getDataSource() != this.clock) {
                sDataConnection.registerDatum();
            }
        }
    }
    
    public void deleteDataConnections() {
        this.dataConnections.removeAllElements();
    }
    
    public void deleteDataConnection(final int n) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (n == sDataConnection.hashCode()) {
                this.dataConnections.removeElement(sDataConnection);
            }
        }
    }
    
    private SDataConnection getDataConnection(final int n) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (n == sDataConnection.hashCode()) {
                return sDataConnection;
            }
        }
        return null;
    }
    
    public SDataConnection getDataConnectionFromDS(final SDataSource sDataSource) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (sDataSource == sDataConnection.getDataSource()) {
                return sDataConnection;
            }
        }
        return null;
    }
    
    public String getDataFromDS(final int n) {
        final Format format = new Format("%10.5g");
        final SDataSource dataSource = getDataSource(n);
        final String[] varStrings = dataSource.getVarStrings();
        final double[][] variables = dataSource.getVariables();
        if (variables == null || varStrings == null) {
            return "";
        }
        final int length = varStrings.length;
        final int length2 = variables.length;
        final StringBuffer sb = new StringBuffer((length + 1) * length2 * 10);
        for (int i = 0; i < length; ++i) {
            sb.append(varStrings[i]);
            for (int j = 0; j < length2; ++j) {
                sb.append(",");
                sb.append(format.form(variables[j][i]));
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    
    public SDataConnection getDataConnectionFromDL(final SDataListener sDataListener) {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            if (sDataListener == sDataConnection.getDataListener()) {
                return sDataConnection;
            }
        }
        return null;
    }
    
    public final boolean isValidFunction(final String s, final String s2) {
        return SUtil.isValidFunction(s, s2);
    }
    
    public final void cleanupDataConnections() {
        final Enumeration<SDataConnection> elements = this.dataConnections.elements();
        while (elements.hasMoreElements()) {
            final SDataConnection sDataConnection = elements.nextElement();
            final SDataListener dataListener = sDataConnection.getDataListener();
            final SDataSource dataSource = sDataConnection.getDataSource();
            if (!SApplet.dataListeners.contains(dataListener) || !SApplet.dataSources.contains(dataSource)) {
                this.dataConnections.removeElement(sDataConnection);
            }
        }
    }
    
    public final Object getRunningID() {
        return SApplet.runningID;
    }
    
    public final void setRunningID(final Object runningID) {
        SApplet.runningID = runningID;
    }
    
    public final void setRunningID() {
        SApplet.runningID = this;
    }
    
    public final int getDebugLevel() {
        return this.debugLevel;
    }
    
    public final void setDebugLevel(final int n) {
        this.debugLevel = n;
        SApplet.staticDebugLevel = n;
    }
    
    public final int getID() {
        return this.hashCode();
    }
    
    public final boolean setConnectionSmoothing(final int n, final int smooth) {
        final SDataConnection dataConnection = this.getDataConnection(n);
        if (dataConnection == null) {
            System.out.println("Error: DataConnection not found in setConnectionSmoothing.");
            return false;
        }
        dataConnection.setSmooth(smooth);
        return true;
    }
    
    public final boolean setConnectionStride(final int n, final int stride) {
        final SDataConnection dataConnection = this.getDataConnection(n);
        if (dataConnection == null) {
            System.out.println("Error: DataConnection not found in setConnectionStride.");
            return false;
        }
        dataConnection.setStride(stride);
        return true;
    }
    
    public final boolean setConnectionBlock(final int n, final boolean block) {
        final SDataConnection dataConnection = this.getDataConnection(n);
        if (dataConnection == null) {
            System.out.println("Error: DataConnection not found in setConnectionBlock.");
            return false;
        }
        dataConnection.block = block;
        return true;
    }
    
    public final boolean setConnectionSource(final int n) {
        final SDataConnection dataConnection = this.getDataConnection(n);
        if (dataConnection == null) {
            System.out.println("Error: DataConnection not found in setConnectionSource.");
            return false;
        }
        dataConnection.ds = getDataSource(n);
        return true;
    }
    
    public final boolean setConnectionListener(final int n) {
        final SDataConnection dataConnection = this.getDataConnection(n);
        if (dataConnection == null) {
            System.out.println("Error: DataConnection not found in setConnectionSource.");
            return false;
        }
        dataConnection.dl = getDataListener(n);
        return true;
    }
    
    public void setAutoRefresh(final boolean autoRefresh) {
        this.autoRefresh = autoRefresh;
    }
    
    public void forward() {
        this.setRunningID();
        this.clock.dt = Math.abs(this.clock.dt);
        this.clock.startClock();
    }
    
    public void reverse() {
        this.setRunningID();
        this.clock.dt = -Math.abs(this.clock.dt);
        this.clock.startClock();
    }
    
    public void pause() {
        this.clock.stopClock();
    }
    
    public void reset() {
        this.pause();
        this.clock.setTime(0.0);
    }
    
    public void setDefault() {
        this.pause();
        this.clock.setTime(0.0);
        this.deleteDataConnections();
    }
    
    public void setDt(final double dt) {
        this.clock.setDt(dt);
    }
    
    public void setFPS(final double fps) {
        this.clock.setFPS(fps);
    }
    
    public void setTimeContinuous() {
        this.clock.setContinuous();
    }
    
    public final int getClockID() {
        return this.clock.hashCode();
    }
    
    public final boolean setExternalClock(final int n) {
        final SDataSource dataSource = getDataSource(n);
        if (dataSource == null) {
            return false;
        }
        if (dataSource instanceof SClock) {
            final SClock clock = (SClock)dataSource;
            this.clock.stopClock();
            clock.stopClock();
            final Enumeration<SStepable> elements = this.clock.clockListeners.elements();
            while (elements.hasMoreElements()) {
                clock.addClockListener(elements.nextElement());
            }
            this.removeDataSource(this.clock.getID());
            this.clock = clock;
            return true;
        }
        return false;
    }
    
    public void stepClock() {
        this.clock.doStep();
    }
    
    public void stepTimeForward() {
        this.clock.setDt(Math.abs(this.clock.getDt()));
        this.clock.doStep();
    }
    
    public void stepTimeBack() {
        this.clock.setDt(-Math.abs(this.clock.getDt()));
        this.clock.doStep();
    }
    
    public void stepTime() {
        this.clock.doStep();
    }
    
    public double getClockTime() {
        return this.clock.getTime();
    }
    
    public void setClockTime(final double time) {
        this.clock.setTime(time);
    }
    
    public void setTimeOneShot(final double n, final String oneShotMsg) {
        this.oneShotMsg = oneShotMsg;
        this.clock.setOneShot(0.0, n);
        this.clock.setTime(0.0);
    }
    
    public void setClockOneShot(final double time, final double n) {
        this.clock.setOneShot(time, n);
        this.clock.setTime(time);
    }
    
    public void setClockContinous() {
        this.clock.oneShot = false;
        this.clock.cycle = false;
    }
    
    public void setTimeCycle(final double n) {
        this.clearAllData();
        this.clock.setCycle(0.0, n);
        this.clock.setTime(0.0);
    }
    
    public void setClockCycle(final double time, final double n) {
        this.clearAllData();
        this.clock.setCycle(time, n);
        this.clock.setTime(time);
    }
    
    public void startClock() {
        this.setRunningID();
        this.clock.startClock();
    }
    
    public boolean isAutoRefresh() {
        return this.autoRefresh;
    }
    
    public boolean isClockRunning() {
        return this.clock.isRunning();
    }
    
    protected void stoppingClock() {
    }
    
    protected void cyclingClock() {
    }
    
    protected void pausingClock() {
    }
    
    public void stopClock() {
        this.clock.stopClock();
    }
    
    public static final void addDataSource(final Object o) {
        if (o instanceof SDataSource && !SApplet.dataSources.contains(o)) {
            SApplet.dataSources.put(Integer.toString(o.hashCode()), o);
            if (SApplet.staticDebugLevel > 8) {
                System.out.println("data source added: ".concat(String.valueOf(String.valueOf(o.hashCode()))));
            }
        }
        else {
            System.out.println("Error: ds is not a DataSource in SApplet.addDataSource:".concat(String.valueOf(String.valueOf(o.hashCode()))));
        }
    }
    
    public final void removeDataSource(final int n) {
        final SDataSource dataSource = getDataSource(n);
        SDataConnection dataConnectionFromDS;
        do {
            dataConnectionFromDS = this.getDataConnectionFromDS(dataSource);
            if (dataConnectionFromDS != null) {
                this.dataConnections.removeElement(dataConnectionFromDS);
            }
        } while (dataConnectionFromDS != null);
        SApplet.dataSources.remove(Integer.toString(n));
    }
    
    public static final SDataSource getDataSource(final String s) {
        return SApplet.dataSources.get(s);
    }
    
    public static final SDataSource getDataSource(final int n) {
        return SApplet.dataSources.get(Integer.toString(n));
    }
    
    public String getSourceVariables(final int n) {
        String value = "";
        final SDataSource sDataSource = SApplet.dataSources.get(Integer.toString(n));
        if (sDataSource == null) {
            return "Data source not found.";
        }
        final String[] varStrings = sDataSource.getVarStrings();
        if (varStrings == null) {
            return "Variables not found.";
        }
        final int length = varStrings.length;
        for (int i = 0; i < length - 1; ++i) {
            value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append(varStrings[i]).append(", ")));
        }
        return String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(varStrings[length - 1])));
    }
    
    public String getSourceData(final int n, final String s) {
        int n2 = -1;
        final SDataSource sDataSource = SApplet.dataSources.get(Integer.toString(n));
        if (sDataSource == null) {
            return "Data source not found.";
        }
        final String[] varStrings = sDataSource.getVarStrings();
        if (varStrings == null) {
            return "Variables not found.";
        }
        for (int length = varStrings.length, i = 0; i < length; ++i) {
            if (varStrings[i].equals(s)) {
                n2 = i;
                break;
            }
        }
        if (n2 == -1) {
            return "Data source variable not found.";
        }
        final double[][] variables = sDataSource.getVariables();
        if (variables == null) {
            return "Values not found.";
        }
        final int length2 = variables.length;
        StringBuffer append = new StringBuffer(length2 * 10);
        for (int j = 0; j < length2 - 1; ++j) {
            append = append.append(variables[j][n2]);
            append.append(", ");
        }
        if (length2 <= 0) {
            return "";
        }
        append.append(variables[length2 - 1][n2]);
        return append.toString();
    }
    
    public final void removeDataListener(final int n) {
        final SDataListener dataListener = getDataListener(n);
        SDataConnection dataConnectionFromDL;
        do {
            dataConnectionFromDL = this.getDataConnectionFromDL(dataListener);
            if (dataConnectionFromDL != null) {
                this.dataConnections.removeElement(dataConnectionFromDL);
            }
        } while (dataConnectionFromDL != null);
        SApplet.dataListeners.remove(Integer.toString(n));
    }
    
    public static final void addDataListener(final Object o) {
        if (o instanceof SDataListener) {
            SApplet.dataListeners.put(Integer.toString(o.hashCode()), o);
            if (SApplet.staticDebugLevel > 8) {
                System.out.println("data listener added: ".concat(String.valueOf(String.valueOf(o.hashCode()))));
            }
        }
        else {
            System.out.println("Error: dl is not a DataListener in SApplet.addDataListener:".concat(String.valueOf(String.valueOf(o.hashCode()))));
        }
    }
    
    public static final SDataListener getDataListener(final String s) {
        return SApplet.dataListeners.get(s);
    }
    
    public static final SDataListener getDataListener(final int n) {
        return SApplet.dataListeners.get(Integer.toString(n));
    }
    
    static {
        SApplet.dataSources = new Hashtable(50);
        SApplet.dataListeners = new Hashtable(20);
        SApplet.runningID = null;
        SApplet.staticDebugLevel = 0;
    }
}
