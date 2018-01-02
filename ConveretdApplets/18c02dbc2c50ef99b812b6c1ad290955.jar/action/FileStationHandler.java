// 
// Decompiled by Procyon v0.5.30
// 

package action;

import jsonutil.JSONUtil;
import action.upload.UploadHandler;
import action.list.ListHandler;
import org.json.JSONObject;
import action.upload.UploadTaskManeger;
import java.security.PrivilegedExceptionAction;

public class FileStationHandler implements PrivilegedExceptionAction<Object>
{
    private UploadTaskManeger _UManeger;
    private String _JsonParams;
    
    public FileStationHandler(final String jsonParams, final UploadTaskManeger uManeger) {
        this._JsonParams = jsonParams;
        this._UManeger = uManeger;
    }
    
    public Object run() {
        try {
            final JSONObject jsonObject = new JSONObject(this._JsonParams);
            final String optString = jsonObject.optString("action");
            if (optString.equals("getfiles")) {
                return new ListHandler().enumDirAndFileHandler(jsonObject);
            }
            if (optString.equals("getdirectories")) {
                return new ListHandler().enumDirHandler(jsonObject);
            }
            if (optString.equals("gethostname")) {
                return new ListHandler().getHostName();
            }
            if (optString.equals("getuserpath")) {
                return new ListHandler().getUserPath();
            }
            if (optString.equals("getfs")) {
                return new ListHandler().getSpaceInfo(jsonObject);
            }
            if (optString.equals("uploadprogress")) {
                return new UploadHandler(this._UManeger).startUTask(jsonObject);
            }
            if (optString.equals("cancelupload")) {
                return new UploadHandler(this._UManeger).cancelUTask(jsonObject);
            }
            if (optString.equals("restartupload")) {
                return new UploadHandler(this._UManeger).restartUTask(jsonObject);
            }
            return JSONUtil.setError("error", "error_system_busy");
        }
        catch (NullPointerException ex) {
            log(ex);
            return JSONUtil.setError("error", "error_no_path");
        }
        catch (Exception ex2) {
            log(ex2);
            return JSONUtil.setError("error", "error_system_busy");
        }
    }
    
    public static void log(final Exception ex) {
    }
}
