// 
// Decompiled by Procyon v0.5.30
// 

package action.upload;

import org.json.JSONException;
import action.upload.params.ConnectionParam;
import org.json.JSONArray;
import action.upload.params.UploadTaskParam;
import java.util.concurrent.ConcurrentHashMap;
import webutil.HTTPStream;
import java.applet.Applet;

public class UploadTaskManeger
{
    private UploadTaskThread _UTaskThread;
    private Applet _Applet;
    private HTTPStream _HttpConnect;
    private Integer _UTaskSize;
    private Integer _UTaskIndex;
    private ConcurrentHashMap<Integer, UploadTaskParam> _UTaskList;
    
    public UploadTaskManeger(final Applet applet) {
        this._Applet = applet;
        this._HttpConnect = new HTTPStream(applet.getParameter("baseURL") + "webUI/java_upload.cgi");
        this._UTaskList = new ConcurrentHashMap<Integer, UploadTaskParam>();
        this._UTaskSize = new Integer(0);
        this._UTaskIndex = new Integer(0);
    }
    
    public synchronized void addUploadTasks(final String s, final JSONArray jsonArray, final ConnectionParam connectionParam) throws JSONException {
        if (null == this._UTaskThread || this._UTaskThread.isInterrupted() || !this._UTaskThread.isAlive()) {
            this.createUploadTaskThread();
            this._UTaskThread.addUploadTasks(s, jsonArray, connectionParam);
            this._UTaskThread.setDaemon(true);
            this._UTaskThread.start();
        }
        else {
            this._UTaskThread.addUploadTasks(s, jsonArray, connectionParam);
        }
    }
    
    public synchronized void cancelUTask(final int n) throws NullPointerException {
        this._UTaskThread.cancelTask(n);
    }
    
    public synchronized void restartUTask(final int n) throws JSONException, NullPointerException {
        if (this._UTaskList.containsKey(n)) {
            final JSONArray jsonArray = new JSONArray();
            final UploadTaskParam uploadTaskParam = this._UTaskList.get(n);
            jsonArray.put(uploadTaskParam.getTaskFile().getAbsolutePath());
            if (uploadTaskParam.getStatus() == UploadTaskParam.STATUS.FAIL) {
                this.addUploadTasks(uploadTaskParam.getRemoteDir(), jsonArray, uploadTaskParam.getConnectionParam());
                this.cancelUTask(n);
            }
            return;
        }
        throw new NullPointerException();
    }
    
    public void createUploadTaskThread() {
        this._UTaskThread = new UploadTaskThread(this, this._Applet, this._UTaskList, this._HttpConnect);
    }
    
    public Integer getUTaskIndex() {
        synchronized (this._UTaskIndex) {
            return this._UTaskIndex;
        }
    }
    
    public Integer getUTaskSize() {
        synchronized (this._UTaskSize) {
            return this._UTaskSize;
        }
    }
    
    public void increaseUTaskIndex() {
        synchronized (this._UTaskIndex) {
            final Integer uTaskIndex = this._UTaskIndex;
            ++this._UTaskIndex;
        }
    }
    
    public void increaseUTaskSize() {
        synchronized (this._UTaskSize) {
            final Integer uTaskSize = this._UTaskSize;
            ++this._UTaskSize;
        }
    }
}
