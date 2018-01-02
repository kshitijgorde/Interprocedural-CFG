// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

public class ErrorCode
{
    public static final int ERRORCODE_SOURCE = -65536;
    public static final int ERRORCODE_SOURCE_RA = 16777216;
    public static final int ERRORCODE_RA_NORESULTS = 16777217;
    public static final int ERRORCODE_RA_TIMEOUT = 16777218;
    public static final int ERRORCODE_RA_FAILEDSTART = 16777219;
    public static final int ERRORCODE_RA_POSTFAILED = 16777220;
    public static final int ERRORCODE_RA_WRONGPASS = 16777221;
    public static final int ERRORCODE_RA_PLUGINDOWNLOAD = 16777222;
    public static final int ERRORCODE_RA_NOTLICENSED = 16777223;
    public static final int ERRORCODE_RA_UNKNOWN = 16777224;
    public int nCode;
    public String desc;
    public String detail;
    
    public ErrorCode(final int nCode, final String desc) {
        this.nCode = nCode;
        this.desc = desc;
    }
    
    public void addDetail(final String s, final String s2) {
        if (this.detail == null) {
            this.detail = "";
        }
        this.detail = String.valueOf(this.detail) + "&detail." + s + "=" + s2;
    }
}
