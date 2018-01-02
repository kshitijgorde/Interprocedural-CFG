// 
// Decompiled by Procyon v0.5.30
// 

package action.upload.params;

import org.json.JSONException;
import java.io.IOException;
import jsonutil.JSONUtil;
import java.io.InputStreamReader;
import java.util.Vector;
import fileutil.filefilter.SFileFilter;
import action.upload.UploadTaskSizeHandler;
import fileutil.filefilter.UploadFileFilter;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
import fileutil.SFile;

public class UploadTaskParam
{
    private Integer _TaskID;
    private SFile _TaskFile;
    private String _RemoteDir;
    private long _TaskSize;
    private TimeAndSpaceParam _TSParam;
    private STATUS _Status;
    private JSONObject _Responese;
    private ConnectionParam _CParam;
    private Integer _UFileSize;
    private Integer _UFileIndex;
    private ConcurrentHashMap<Integer, UploadFileParam> _UFileList;
    private UploadFileParam _CurrentUFile;
    private String _SkipFilenames;
    private UploadFileFilter _UFileFilter;
    
    public UploadTaskParam(final Integer taskID, final String s, final String remoteDir, final ConnectionParam cParam) {
        this._UFileFilter = new UploadFileFilter();
        this._TaskID = taskID;
        this._TaskFile = new SFile(s);
        this._RemoteDir = remoteDir;
        this._TSParam = new TimeAndSpaceParam();
        this._Status = STATUS.NOT_STARTED;
        this._CParam = cParam;
        this._UFileSize = new Integer(0);
        this._UFileIndex = new Integer(0);
        this._UFileList = new ConcurrentHashMap<Integer, UploadFileParam>();
        this._CurrentUFile = null;
    }
    
    public void startUpload(final long n) {
        this.setStatus(STATUS.PROCESSING);
        this._TSParam.setUploadStartTime(System.currentTimeMillis());
        this._TaskSize = new UploadTaskSizeHandler(this).getTaskSize(this._TaskFile);
        this._TSParam.setTaskTotalBytes(this._TaskSize);
    }
    
    public void initUploadFileList() {
        this._SkipFilenames = new String("");
        this.startUpload(System.currentTimeMillis());
        this.createUFileList(this._RemoteDir, this._TaskFile);
    }
    
    private boolean addOneFile(final String s, final SFile sFile) {
        this._UFileList.put(this._UFileSize, new UploadFileParam(s, sFile));
        final Integer uFileSize = this._UFileSize;
        ++this._UFileSize;
        return true;
    }
    
    private void addOneDir(String string, final SFile sFile) {
        final Vector<SFile> listFileVector = sFile.listFileVector(this._UFileFilter);
        if (listFileVector != null) {
            string = string + "/" + sFile.getName();
            for (int i = 0; i < listFileVector.size(); ++i) {
                this.addOneFile(string, listFileVector.get(i));
            }
        }
    }
    
    public void createUFileList(final String s, final SFile sFile) {
        if (!sFile.canRead() || (!sFile.isFile() && !sFile.isDirectory())) {
            return;
        }
        if (!this.addOneFile(s, sFile)) {
            this.skip();
            return;
        }
        if (sFile.isDirectory()) {
            this.addOneDir(s, sFile);
        }
        synchronized (this._UFileIndex) {
            this._CurrentUFile = this._UFileList.get(this._UFileIndex);
            final Integer uFileIndex = this._UFileIndex;
            ++this._UFileIndex;
        }
    }
    
    private void removeUploadFileList(final Integer n) {
        if (this._UFileList.containsKey(n)) {
            this._UFileList.remove(n);
        }
    }
    
    public int getNextUplaodFile() {
        synchronized (this._UFileIndex) {
            if (this._UFileIndex < this._UFileSize) {
                this._CurrentUFile = this._UFileList.get(this._UFileIndex);
                if (this._CurrentUFile.getFile().isDirectory()) {
                    this.addOneDir(this._CurrentUFile.getRemoteDir(), this._CurrentUFile.getFile());
                }
                this.removeUploadFileList(this._UFileIndex - 1);
                final Integer uFileIndex = this._UFileIndex;
                ++this._UFileIndex;
                return 1;
            }
        }
        return -1;
    }
    
    public void updateTimeAndSpace(final long n) {
        this._TSParam.UpdateTimeAndSpace(n);
    }
    
    public void skip() {
        this.setStatus(STATUS.SKIP);
        this._TSParam.stop();
    }
    
    public void success() {
        this.setStatus(STATUS.SUCCESS);
        this._TSParam.success();
    }
    
    public void cancel() {
        this.setStatus(STATUS.CANCEL);
        this._TSParam.stop();
    }
    
    public void fail() {
        this.setStatus(STATUS.FAIL);
        this._TSParam.stop();
    }
    
    public void setStatus(final STATUS status) {
        this._Status = status;
    }
    
    public void setJSONResponese(final InputStreamReader inputStreamReader) throws IOException, JSONException {
        if (inputStreamReader != null) {
            this._Responese = JSONUtil.transStreamToJSON(inputStreamReader);
        }
    }
    
    public void setJSONResponese(final JSONObject responese) {
        if (responese != null) {
            this._Responese = responese;
        }
    }
    
    public int getTaskID() {
        return this._TaskID;
    }
    
    public SFile getTaskFile() {
        return this._TaskFile;
    }
    
    public ConnectionParam getConnectionParam() {
        return this._CParam;
    }
    
    public TimeAndSpaceParam getTimeAndSpaceParam() {
        return this._TSParam;
    }
    
    public boolean isOverwrite() {
        return this._CParam.isOverwrite();
    }
    
    public STATUS getStatus() {
        return this._Status;
    }
    
    public JSONObject getJSONResponese() {
        return this._Responese;
    }
    
    public long getSize() {
        return this._TaskSize;
    }
    
    public String getCurrentFileName() {
        synchronized (this._UFileIndex) {
            if (this._Status == STATUS.SKIP) {
                return this.getSkipFilenames();
            }
            if (null == this._CurrentUFile) {
                return "";
            }
            return this._CurrentUFile.getFile().getAbsolutePath();
        }
    }
    
    public UploadFileParam getCurrentUFile() {
        synchronized (this._UFileIndex) {
            return this._CurrentUFile;
        }
    }
    
    public void setSkipFilenames(final String s) {
        this._SkipFilenames = this._SkipFilenames + s + " ";
    }
    
    public String getSkipFilenames() {
        return this._SkipFilenames;
    }
    
    public String getRemoteDir() {
        return this._RemoteDir;
    }
    
    public enum STATUS
    {
        NOT_STARTED, 
        PROCESSING, 
        SUCCESS, 
        FAIL, 
        CANCEL, 
        SKIP;
    }
}
