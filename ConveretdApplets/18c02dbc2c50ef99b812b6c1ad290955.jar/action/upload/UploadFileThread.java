// 
// Decompiled by Procyon v0.5.30
// 

package action.upload;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.MalformedURLException;
import action.FileStationHandler;
import jsonutil.JSONUtil;
import webutil.HTTPStream;
import action.upload.params.UploadTaskParam;

public class UploadFileThread extends Thread
{
    private UploadTaskParam _UTask;
    private HTTPStream _HttpConnect;
    private boolean _isCancel;
    
    public UploadFileThread(final HTTPStream httpConnect, final UploadTaskParam uTask) {
        this._isCancel = false;
        this._HttpConnect = httpConnect;
        this._UTask = uTask;
        this._isCancel = false;
    }
    
    public void cancel() {
        this._UTask.cancel();
        this._isCancel = true;
        this.interrupt();
        this._HttpConnect.closeConnection();
    }
    
    public void fail() {
        this._UTask.fail();
        this.interrupt();
        this._HttpConnect.closeConnection();
    }
    
    public void run() {
        try {
            if (this._HttpConnect.checkSkipHandler(this._UTask)) {
                this._UTask.setJSONResponese(this._HttpConnect.getResponse());
                this._HttpConnect.closeConnection();
                if (null != this._UTask.getJSONResponese().optString("errno", null)) {
                    this.fail();
                    return;
                }
                if (this._UTask.getJSONResponese().optBoolean("isSkip")) {
                    return;
                }
            }
            this._HttpConnect.uploadHander(this._UTask);
            this._UTask.setJSONResponese(this._HttpConnect.getResponse());
            this._HttpConnect.closeConnection();
            if (null != this._UTask.getJSONResponese().optString("errno", null)) {
                this.fail();
            }
        }
        catch (MalformedURLException ex) {
            this._UTask.setJSONResponese(JSONUtil.setError("common", "error_badhost"));
            this.fail();
            FileStationHandler.log(ex);
        }
        catch (ProtocolException ex2) {
            this._UTask.setJSONResponese(JSONUtil.setError("common", "error_badhost"));
            this.fail();
            FileStationHandler.log(ex2);
        }
        catch (IOException ex3) {
            if (!this._isCancel) {
                this._UTask.setJSONResponese(JSONUtil.setError("common", "commfail"));
                this.fail();
            }
            FileStationHandler.log(ex3);
        }
        catch (NullPointerException ex4) {
            this._UTask.setJSONResponese(JSONUtil.setError("error", "error_no_path"));
            this.fail();
            FileStationHandler.log(ex4);
        }
        catch (Exception ex5) {
            if (!this._isCancel) {
                if (null != this._UTask.getJSONResponese().optString("errno", null)) {
                    this._UTask.setJSONResponese(JSONUtil.setError("common", "error_system"));
                }
                this.fail();
            }
            FileStationHandler.log(ex5);
        }
    }
}
