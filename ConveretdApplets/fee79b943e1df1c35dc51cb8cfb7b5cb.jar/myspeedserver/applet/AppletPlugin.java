// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.util.Properties;
import java.util.Hashtable;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

public abstract class AppletPlugin
{
    private myspeed TU;
    private String UU;
    private String SS;
    private StringBuffer SU;
    
    public AppletPlugin(final Applet applet, final String uu) {
        this.SU = new StringBuffer();
        this.TU = (myspeed)applet;
        this.UU = uu;
    }
    
    public int iniGetInteger(final String s, final int n) {
        return this.TU.iniGetInteger(s, null, n);
    }
    
    public int iniGetInteger(final String s, final String s2, final int n) {
        return this.TU.iniGetInteger(s, s2, n);
    }
    
    public long iniGetLong(final String s, final long n) {
        return this.TU.iniGetLong(s, null, n);
    }
    
    public long iniGetLong(final String s, final String s2, final long n) {
        return this.TU.iniGetLong(s, s2, n);
    }
    
    public String iniGetString(final String s) {
        return this.TU.iniGetString(s, null);
    }
    
    public String iniGetString(final String s, final String s2) {
        return this.TU.iniGetString(s, s2);
    }
    
    public String iniGetProfessionalString(final String s) {
        return this.TU.iniGetProfessionalString(s);
    }
    
    public void addGraphResults(final String s, final long[] array, final int[] array2, final float[] array3, final boolean[] array4) {
        this.TU.addGraphResults(s, array, array2, array3, array4);
    }
    
    public void addGraphResults(final String s, final long[] array, final int[] array2, final boolean b, final int n) {
        this.TU.addGraphResults(s, array, array2, b, n);
    }
    
    public String RC(final String s) {
        return this.TU.RC(s);
    }
    
    public String RC(final String s, final String s2) {
        return this.TU.RC(s, s2);
    }
    
    public String RC(final String s, final String[] array) {
        return this.TU.RC(s, array);
    }
    
    public URL base() {
        return this.TU.base();
    }
    
    public Image getImage(final String s) {
        return this.TU.getImageFromJar(s);
    }
    
    public String getName() {
        return this.UU;
    }
    
    public void setMSSID(final String ss) {
        this.SS = ss;
    }
    
    public String getMSSID() {
        return this.SS;
    }
    
    public AppletTest getTest() {
        return null;
    }
    
    public void setTest(final AppletTest appletTest) {
    }
    
    public String getLog() {
        return this.SU.toString();
    }
    
    public void resetLog() {
        this.SU.setLength(0);
    }
    
    public void OUT(final String s) {
        this.SU.append(s.endsWith("\n") ? s : (String.valueOf(s) + "\n"));
        this.TU.OUT(s);
    }
    
    public void ERR(final String s) {
        this.SU.append(s.endsWith("\n") ? s : (String.valueOf(s) + "\n"));
        this.TU.ERR(s);
    }
    
    public void logException(final String s, final Throwable t) {
        this.TU.logException(s, t);
    }
    
    public void addTabToApplet(final AppletTab appletTab, final boolean b) {
        this.TU.addTab(appletTab, b);
    }
    
    public AppletPlugin[] getPlugins() {
        return this.TU.getPlugins();
    }
    
    public int getTestSpecId() {
        return this.TU.getTestSpecId();
    }
    
    protected Applet getApplet() {
        return this.TU;
    }
    
    protected void doAppletDelaySelectTab(final AppletTab appletTab) {
        this.TU.delaySelectTab(appletTab);
    }
    
    protected void doReportResults() {
        this.TU.doReportResults(this);
    }
    
    public abstract void doFirstTimeInit();
    
    public abstract String doMySpeedVariableSubstitution(final String p0, final boolean p1);
    
    public abstract String getReportMSS();
    
    public ErrorCode getErrorCode() {
        return null;
    }
    
    public abstract String getDetailResults();
    
    public AppletTest createTest(final Hashtable hashtable, final Hashtable hashtable2) {
        return null;
    }
    
    public Properties getOverrideSettings() {
        return null;
    }
    
    public abstract void runTest();
    
    public abstract void notifyTestBegin();
}
