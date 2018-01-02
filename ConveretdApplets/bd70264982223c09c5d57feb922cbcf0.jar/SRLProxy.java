// 
// Decompiled by Procyon v0.5.30
// 

public class SRLProxy
{
    public native long init() throws Error;
    
    public native long destroy() throws Error;
    
    public native long start() throws Error;
    
    public native long stop() throws Error;
    
    public native String getSystem(final String p0) throws Error;
    
    public native String getSysInfo() throws Error;
    
    public native String getValue(final String p0) throws Error;
    
    public native String getAnalysis(final String p0, final String p1, final String p2) throws Error;
    
    public native String setSessionID(final String p0) throws Error;
    
    public native String getSessionID() throws Error;
    
    public native String setUserID(final String p0) throws Error;
    
    public native String getUserID() throws Error;
    
    public native String setReferrerID(final String p0) throws Error;
    
    public native String getReferrerID() throws Error;
    
    public native String setLogging(final String p0) throws Error;
    
    public native String getLogging() throws Error;
    
    public native String getVersion() throws Error;
    
    public native long Detect() throws Error;
    
    public native long Configure(final String p0) throws Error;
    
    public native long Install(final String p0) throws Error;
}
