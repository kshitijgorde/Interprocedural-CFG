// 
// Decompiled by Procyon v0.5.30
// 

package sprite;

class GameCanvas$RenderThread extends Thread
{
    private GameCanvas \u00d2;
    private boolean m_bStopped;
    final GameCanvas \u00e7;
    
    void \u00e7() {
        this.m_bStopped = true;
    }
    
    GameCanvas$RenderThread(final GameCanvas \u00e7, final GameCanvas \u00f2) {
        (this.\u00e7 = \u00e7).getClass();
        this.\u00d2 = \u00f2;
        this.m_bStopped = false;
        this.start();
    }
    
    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        long n = 0L;
        int nFrameRate = 0;
        while (!this.m_bStopped) {
            if (nFrameRate != this.\u00e7.m_nFrameRate) {
                nFrameRate = this.\u00e7.m_nFrameRate;
                currentTimeMillis = System.currentTimeMillis() - n * this.\u00e7.m_nFrameRate;
            }
            try {
                this.\u00d2.nextFrame();
                ++n;
                long n2 = n * nFrameRate + currentTimeMillis - System.currentTimeMillis();
                if (n2 > -40L || !this.\u00e7.m_bDropFrames) {
                    this.\u00d2.doUpdate();
                }
                else if (n2 < -100L) {
                    currentTimeMillis = System.currentTimeMillis() - n * nFrameRate;
                }
                if (n2 > 20L) {
                    if ((n & 0x3FFL) == 0x0L) {
                        System.gc();
                        System.runFinalization();
                        n2 = n * nFrameRate + currentTimeMillis - System.currentTimeMillis();
                    }
                    if (n2 > nFrameRate * 2) {
                        currentTimeMillis = System.currentTimeMillis() - n * nFrameRate;
                        n2 = nFrameRate;
                    }
                }
                if (n2 <= 0L) {
                    continue;
                }
                Thread.sleep(n2);
            }
            catch (InterruptedException ex2) {}
            catch (Exception ex) {
                try {
                    ex.printStackTrace();
                    System.out.println(ex.toString());
                    Thread.sleep(nFrameRate);
                }
                catch (InterruptedException ex3) {}
            }
        }
    }
}
