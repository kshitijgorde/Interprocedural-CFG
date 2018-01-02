// 
// Decompiled by Procyon v0.5.30
// 

package mika.system;

public class S_Timer
{
    private final int ARRAY_SIZE = 5;
    private long m_timerStarted;
    private long m_timerCurrent;
    private long m_lastFPSTime;
    private int m_totalFrameCnt;
    private int m_thisRoundFrameCnt;
    private float m_frameRate;
    private float m_lastDelta;
    private float[] m_frameTimes;
    private boolean m_firstTime;
    
    public S_Timer() {
        this.m_frameTimes = new float[5];
        this.reset();
    }
    
    public final void reset() {
        final boolean b = false;
        this.m_thisRoundFrameCnt = (b ? 1 : 0);
        this.m_totalFrameCnt = (b ? 1 : 0);
        this.m_lastDelta = 0.0f;
        this.m_firstTime = true;
        this.sample();
    }
    
    public final float getAge() {
        return (System.currentTimeMillis() - this.m_timerStarted) / 1000.0f;
    }
    
    public final int getFrameRate() {
        return (int)this.m_frameRate;
    }
    
    public final int getAverageFrameRate() {
        return (int)(this.m_totalFrameCnt / this.getAge());
    }
    
    public final float getDelta() {
        return this.m_lastDelta;
    }
    
    public final float sample() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.m_firstTime) {
            this.m_firstTime = false;
            final long timerStarted = currentTimeMillis;
            this.m_lastFPSTime = timerStarted;
            this.m_timerCurrent = timerStarted;
            this.m_timerStarted = timerStarted;
        }
        ++this.m_thisRoundFrameCnt;
        ++this.m_totalFrameCnt;
        if (currentTimeMillis - this.m_lastFPSTime > 1000L) {
            this.m_frameRate = this.m_thisRoundFrameCnt / ((currentTimeMillis - this.m_lastFPSTime) / 1000L);
            this.m_thisRoundFrameCnt = 0;
            this.m_lastFPSTime = currentTimeMillis;
        }
        this.m_frameTimes[this.m_totalFrameCnt % this.m_frameTimes.length] = (currentTimeMillis - this.m_timerCurrent) / 1000.0f;
        if (this.m_totalFrameCnt > this.m_frameTimes.length) {
            this.m_lastDelta = 0.0f;
            for (int i = 0; i < this.m_frameTimes.length; ++i) {
                this.m_lastDelta += this.m_frameTimes[i];
            }
            this.m_lastDelta /= this.m_frameTimes.length;
        }
        this.m_timerCurrent = currentTimeMillis;
        if (this.m_lastDelta == 0.0f) {
            this.m_lastDelta = 1.0E-4f;
        }
        return this.m_lastDelta;
    }
}
