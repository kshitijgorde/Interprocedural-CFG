// 
// Decompiled by Procyon v0.5.30
// 

package jsutil;

import action.FileStationHandler;
import jsonutil.JSONUtil;
import netscape.javascript.JSObject;
import action.upload.params.UploadTaskParam;
import java.applet.Applet;

public class JSUtil
{
    private static String JSNAME;
    
    public static boolean evalJSON(final Applet applet, final String s, final UploadTaskParam uploadTaskParam) {
        try {
            if (uploadTaskParam != null) {
                JSObject.getWindow(applet).eval(JSUtil.JSNAME + s + "(" + JSONUtil.setUploadTaskParam(uploadTaskParam) + ");");
            }
            return true;
        }
        catch (Exception ex) {
            FileStationHandler.log(ex);
            return false;
        }
    }
    
    public static boolean eval(final Applet applet, final String s, final Object o) {
        try {
            JSObject.getWindow(applet).eval(JSUtil.JSNAME + s + "(" + o + ");");
            return true;
        }
        catch (Exception ex) {
            FileStationHandler.log(ex);
            return false;
        }
    }
    
    public static boolean eval(final Applet applet, final String s, final String s2, final Object o) {
        try {
            JSObject.getWindow(applet).eval(s + s2 + "(" + o + ");");
            return true;
        }
        catch (Exception ex) {
            FileStationHandler.log(ex);
            return false;
        }
    }
    
    static {
        JSUtil.JSNAME = "SYNO.FileStation.instance().getUploadInstance().";
    }
}
