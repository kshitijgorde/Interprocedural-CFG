// 
// Decompiled by Procyon v0.5.30
// 

package action.upload;

import org.json.JSONException;
import jsonutil.JSONUtil;
import action.upload.params.ConnectionParam;
import org.json.JSONArray;
import org.json.JSONObject;

public class UploadHandler
{
    private UploadTaskManeger _UManeger;
    
    public UploadHandler(final UploadTaskManeger uManeger) {
        this._UManeger = uManeger;
    }
    
    public JSONObject startUTask(final JSONObject jsonObject) throws JSONException {
        final String optString;
        final String optString2;
        if (null == (optString = jsonObject.optString("remoteDir", null)) || null == (optString2 = jsonObject.optString("localPaths", null))) {
            throw new IllegalArgumentException();
        }
        this._UManeger.addUploadTasks(optString, new JSONArray(optString2), new ConnectionParam(jsonObject.optBoolean("overwrite", false)));
        return JSONUtil.setSuccess(true);
    }
    
    public JSONObject cancelUTask(final JSONObject jsonObject) throws JSONException, NullPointerException {
        final int optInt;
        if (-1 == (optInt = jsonObject.optInt("id", -1))) {
            throw new IllegalArgumentException();
        }
        this._UManeger.cancelUTask(optInt);
        return JSONUtil.setSuccess(true);
    }
    
    public JSONObject restartUTask(final JSONObject jsonObject) throws JSONException, NullPointerException {
        final int optInt;
        if (-1 == (optInt = jsonObject.optInt("id", -1))) {
            throw new IllegalArgumentException();
        }
        this._UManeger.restartUTask(optInt);
        return JSONUtil.setSuccess(true);
    }
}
