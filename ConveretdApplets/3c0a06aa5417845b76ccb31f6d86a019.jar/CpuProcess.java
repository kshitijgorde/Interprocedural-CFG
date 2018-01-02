// 
// Decompiled by Procyon v0.5.30
// 

public class CpuProcess
{
    private int _createId;
    private int _procId;
    private int _length;
    private int _toa;
    private int _waitTime;
    private int _runTime;
    private int _startTime;
    private int _endTime;
    
    public CpuProcess(final int createId, final int length, final int toa) {
        this._createId = createId;
        this._length = length;
        this._toa = toa;
        this._procId = -1;
        this._waitTime = 0;
        this._runTime = 0;
        this._startTime = 0;
        this._endTime = 0;
    }
    
    public CpuProcess(final CpuProcess clone) {
        this._createId = clone.getCreateId();
        this._length = clone.getLength();
        this._toa = clone.getTOA();
        this._procId = clone.getProcId();
        this._waitTime = clone.getWaitTime();
        this._runTime = clone.getRunTime();
        this._startTime = clone.getStartTime();
        this._endTime = clone.getEndTime();
    }
    
    public int getProcId() {
        return this._procId;
    }
    
    public void setProcId(final int procId) {
        this._procId = procId;
    }
    
    public int getStartTime() {
        return this._startTime;
    }
    
    public void setStartTime(final int time) {
        this._startTime = time - this._toa;
    }
    
    public int getEndTime() {
        return this._endTime;
    }
    
    public void setEndTime(final int time) {
        this._endTime = time - this._toa;
    }
    
    public int getCreateId() {
        return this._createId;
    }
    
    public int getLength() {
        return this._length;
    }
    
    public int getTOA() {
        return this._toa;
    }
    
    public int getWaitTime() {
        return this._waitTime;
    }
    
    public int getRunTime() {
        return this._runTime;
    }
    
    public int getTimeLeft() {
        return this._length - this._runTime;
    }
    
    public boolean isDone() {
        return this._runTime >= this._length;
    }
    
    public int getTOC() {
        return (this._endTime == 0) ? 0 : (this._endTime - this._startTime);
    }
    
    public int incrRunTime() {
        return ++this._runTime;
    }
    
    public int incrWaitTime() {
        return ++this._waitTime;
    }
    
    public static final String toHeaderString() {
        return "CreateId  ProcId    TofA      Length    Wait      Run      TofC    \n--------  --------- --------- --------- --------- -------- --------";
    }
    
    public String toString() {
        final StringBuffer results = new StringBuffer();
        results.append(("" + this._createId + "           ").substring(0, 10));
        results.append(("" + this._procId + "           ").substring(0, 10));
        results.append(("" + this._toa + "           ").substring(0, 10));
        results.append(("" + this._length + "           ").substring(0, 10));
        results.append(("" + this._waitTime + "           ").substring(0, 10));
        results.append(("" + this._runTime + "           ").substring(0, 10));
        results.append(("" + this.getTOC() + "           ").substring(0, 10));
        return results.toString();
    }
}
