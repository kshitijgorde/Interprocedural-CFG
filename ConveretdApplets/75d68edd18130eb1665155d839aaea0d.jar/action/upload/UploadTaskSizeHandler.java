// 
// Decompiled by Procyon v0.5.30
// 

package action.upload;

import java.util.Vector;
import fileutil.filefilter.SFileFilter;
import fileutil.filefilter.UploadFileFilter;
import fileutil.SFile;
import action.upload.params.UploadTaskParam;

public class UploadTaskSizeHandler
{
    private long _tasksize;
    private UploadTaskParam _UTask;
    
    public UploadTaskSizeHandler(final UploadTaskParam uTask) {
        this._tasksize = 0L;
        this._UTask = uTask;
    }
    
    public long getTaskSize(final SFile sFile) {
        if (!sFile.isDirectory() && !sFile.isFile()) {
            return 0L;
        }
        this._tasksize = sFile.length();
        if (sFile.isDirectory()) {
            this.getRecursiveSize(sFile);
        }
        return this._tasksize;
    }
    
    private void getRecursiveSize(final SFile sFile) {
        final Vector<SFile> listFileVector = sFile.listFileVector(new UploadFileFilter());
        if (null == listFileVector) {
            return;
        }
        for (int i = 0; i < listFileVector.size(); ++i) {
            final SFile sFile2 = listFileVector.get(i);
            this._tasksize += sFile2.length();
            if (sFile2.isDirectory() && this._UTask.getStatus() != UploadTaskParam.STATUS.CANCEL) {
                this.getRecursiveSize(sFile2);
            }
        }
    }
}
