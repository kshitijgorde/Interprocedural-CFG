// 
// Decompiled by Procyon v0.5.30
// 

package action.upload;

import org.json.JSONException;
import action.upload.params.ConnectionParam;
import org.json.JSONArray;
import jsonutil.JSONUtil;
import jsutil.JSUtil;
import action.FileStationHandler;
import webutil.HTTPStream;
import action.upload.params.UploadTaskParam;
import java.util.concurrent.ConcurrentHashMap;
import java.applet.Applet;

public class UploadTaskThread extends Thread
{
    private Applet _Applet;
    private UploadTaskManeger _UTaskManeger;
    private boolean _isUpdateUTaskList;
    private ConcurrentHashMap<Integer, UploadTaskParam> _UTaskList;
    private UploadFileThread _CurrentUFileThread;
    private UploadTaskParam _CurrentUTask;
    private HTTPStream _HttpConnect;
    private long _TimeBeforeCheckNextFile;
    
    public UploadTaskThread(final UploadTaskManeger uTaskManeger, final Applet applet, final ConcurrentHashMap<Integer, UploadTaskParam> uTaskList, final HTTPStream httpConnect) {
        this._UTaskManeger = uTaskManeger;
        this._Applet = applet;
        this._HttpConnect = httpConnect;
        this._UTaskList = uTaskList;
        this._CurrentUTask = null;
        this._TimeBeforeCheckNextFile = 100L;
    }
    
    private synchronized void createUploadFileThread(final UploadTaskParam uploadTaskParam) {
        this._CurrentUFileThread = new UploadFileThread(this._HttpConnect, uploadTaskParam);
    }
    
    public synchronized UploadTaskParam getNextUploadFile() {
        while (this._UTaskManeger.getUTaskIndex() < this._UTaskManeger.getUTaskSize()) {
            while (this._isUpdateUTaskList) {
                try {
                    Thread.sleep(60L);
                }
                catch (InterruptedException ex) {
                    FileStationHandler.log(ex);
                }
            }
            final int intValue = this._UTaskManeger.getUTaskIndex();
            if (!this._UTaskList.containsKey(intValue)) {
                this._UTaskManeger.increaseUTaskIndex();
            }
            else {
                this._CurrentUTask = this._UTaskList.get(intValue);
                if (this._CurrentUTask.getStatus() == UploadTaskParam.STATUS.NOT_STARTED) {
                    this._CurrentUTask.initUploadFileList();
                    if (!JSUtil.evalJSON(this._Applet, "onOpen", this._CurrentUTask)) {
                        this._CurrentUFileThread.fail();
                    }
                }
                else if (this._UTaskList.get(intValue).getStatus() == UploadTaskParam.STATUS.PROCESSING) {
                    if (-1 == this._CurrentUTask.getNextUplaodFile()) {
                        if (this._CurrentUTask.getSkipFilenames().equals("")) {
                            this._CurrentUTask.success();
                            if (!JSUtil.evalJSON(this._Applet, "onComplete", this._CurrentUTask)) {
                                this._CurrentUFileThread.fail();
                            }
                            this._UTaskManeger.increaseUTaskIndex();
                            continue;
                        }
                        this._CurrentUTask.skip();
                    }
                }
                else if (this._CurrentUTask.getStatus() == UploadTaskParam.STATUS.CANCEL) {
                    this._UTaskManeger.increaseUTaskIndex();
                    continue;
                }
                if (this._CurrentUTask.getStatus() == UploadTaskParam.STATUS.FAIL) {
                    if (!JSUtil.evalJSON(this._Applet, "onError", this._CurrentUTask)) {
                        this._CurrentUFileThread.fail();
                    }
                    this._UTaskManeger.increaseUTaskIndex();
                }
                else {
                    if (this._CurrentUTask.getStatus() != UploadTaskParam.STATUS.SKIP) {
                        return this._CurrentUTask;
                    }
                    this._CurrentUTask.setJSONResponese(JSONUtil.setError("mvcp", "mvcp_file_too_big"));
                    if (!JSUtil.evalJSON(this._Applet, "onError", this._CurrentUTask)) {
                        this._CurrentUFileThread.fail();
                    }
                    this._UTaskManeger.increaseUTaskIndex();
                }
            }
        }
        return null;
    }
    
    public synchronized void cancelTask(final int n) throws NullPointerException {
        if (this._UTaskList.containsKey(n)) {
            final UploadTaskParam uploadTaskParam = this._UTaskList.get(n);
            if (uploadTaskParam.getStatus() == UploadTaskParam.STATUS.PROCESSING) {
                this._CurrentUFileThread.cancel();
                this._UTaskManeger.increaseUTaskIndex();
                this._CurrentUTask = null;
            }
            uploadTaskParam.cancel();
            this._UTaskList.remove(n);
            return;
        }
        throw new NullPointerException();
    }
    
    public synchronized void addUploadTasks(final String s, final JSONArray jsonArray, final ConnectionParam connectionParam) throws JSONException {
        this._isUpdateUTaskList = true;
        final int intValue = this._UTaskManeger.getUTaskSize();
        for (int i = 0; i < jsonArray.length(); ++i) {
            final int intValue2 = this._UTaskManeger.getUTaskSize();
            final UploadTaskParam uploadTaskParam = new UploadTaskParam(intValue2, jsonArray.getString(i), s, connectionParam);
            this._UTaskList.put(intValue2, uploadTaskParam);
            if (!JSUtil.evalJSON(this._Applet, "onSelect", uploadTaskParam)) {
                this._CurrentUFileThread.fail();
            }
            this._UTaskManeger.increaseUTaskSize();
        }
        if (!JSUtil.eval(this._Applet, "onAllSelect", intValue + "," + this._UTaskManeger.getUTaskSize())) {
            this._CurrentUFileThread.fail();
        }
        this._isUpdateUTaskList = false;
    }
    
    public void run() {
        while (true) {
            if (this._CurrentUFileThread != null && this._CurrentUFileThread.isAlive()) {
                if (!JSUtil.evalJSON(this._Applet, "onProgress", this._CurrentUTask)) {
                    this._CurrentUFileThread.fail();
                }
                try {
                    Thread.sleep(this._TimeBeforeCheckNextFile);
                }
                catch (InterruptedException ex) {
                    FileStationHandler.log(ex);
                }
            }
            else {
                if (null == (this._CurrentUTask = this.getNextUploadFile())) {
                    break;
                }
                this.createUploadFileThread(this._CurrentUTask);
                this._TimeBeforeCheckNextFile = ((this._CurrentUTask.getCurrentUFile().getFile().length() > 8388608L) ? 1000L : 60L);
                this._CurrentUFileThread.start();
            }
        }
        if (!JSUtil.eval(this._Applet, "onAllComplete", null)) {
            this._CurrentUFileThread.fail();
        }
    }
}
