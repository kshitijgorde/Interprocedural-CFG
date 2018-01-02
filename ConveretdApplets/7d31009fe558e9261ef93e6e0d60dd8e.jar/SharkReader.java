// 
// Decompiled by Procyon v0.5.30
// 

class SharkReader extends Thread
{
    private TickerInfo info;
    private long refresh;
    
    public SharkReader(final TickerInfo info, final long refresh) {
        this.info = null;
        this.refresh = 0L;
        this.info = info;
        this.refresh = refresh;
    }
    
    @Override
    public void run() {
    Label_0046:
        while (true) {
            try {
                this.info.update();
                break Label_0046;
            }
            catch (Exception ex) {
                System.out.println(ex.toString() + ": " + ex.getMessage());
            }
            while (true) {
                try {
                    while (true) {
                        if (this.refresh > 0L) {
                            Thread.sleep(this.refresh);
                            this.info.update();
                        }
                        else {
                            Thread.sleep(1000L);
                        }
                    }
                }
                catch (InterruptedException ex2) {
                    System.out.println(ex2.toString() + ": " + ex2.getMessage());
                    continue;
                }
                continue Label_0046;
            }
            break;
        }
    }
}
