// 
// Decompiled by Procyon v0.5.30
// 

package action.upload.params;

public class TimeAndSpaceParam
{
    private long _TotalBytes;
    private long _UploadedBytes;
    private long _StartTime;
    private double _Duration;
    private double _LeftTime;
    private double _Velocity;
    
    public TimeAndSpaceParam() {
        this.setTimeAndSpaceParam();
    }
    
    public void setTaskTotalBytes(final long totalBytes) {
        this._TotalBytes = totalBytes;
    }
    
    public void setUploadStartTime(final long startTime) {
        this._StartTime = startTime;
    }
    
    public TimeAndSpaceParam UpdateTimeAndSpace(final long n) {
        this._UploadedBytes += n;
        this._Duration = (System.currentTimeMillis() - this._StartTime) / 1000.0;
        this._Velocity = ((0.0 < this._Duration) ? (this._UploadedBytes / this._Duration) : 0.0);
        if (0L == this._UploadedBytes) {
            this._LeftTime = -1.0;
        }
        else {
            this._LeftTime = this._Duration * (this._TotalBytes - this._UploadedBytes) / this._UploadedBytes;
            if (Double.valueOf(this._LeftTime).isInfinite() || Double.valueOf(this._LeftTime).isNaN()) {
                this._LeftTime = -1.0;
            }
        }
        return this;
    }
    
    public double getVelocity() {
        return this._Velocity;
    }
    
    public double getLeftTime() {
        return this._LeftTime;
    }
    
    public long getUploadBytes() {
        return this._UploadedBytes;
    }
    
    public void success() {
        this._UploadedBytes = this._TotalBytes;
        this._LeftTime = 0.0;
        this._Velocity = 0.0;
    }
    
    public void stop() {
        this._LeftTime = 0.0;
        this._Velocity = 0.0;
    }
    
    public void setTimeAndSpaceParam() {
        this._TotalBytes = 0L;
        this._UploadedBytes = 0L;
        this._StartTime = 0L;
        this._Duration = 0.0;
        this._LeftTime = -1.0;
        this._Velocity = 0.0;
    }
}
