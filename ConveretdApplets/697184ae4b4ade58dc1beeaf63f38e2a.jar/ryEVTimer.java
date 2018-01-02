// 
// Decompiled by Procyon v0.5.30
// 

public class ryEVTimer extends Thread
{
    private ryElevator \u0172;
    private ryEVCatcher \u0171;
    String[] \u0170;
    
    public ryEVTimer(final ryElevator \u0173) {
        this.\u0172 = \u0173;
        this.\u0170 = new String[3];
    }
    
    public void run() {
        while (true) {
            if (ryEVCatcher.\u0162 || ryEVCatcher.\u0161) {
                this.\u0170 = ryEVCatcher.receive();
                if (this.\u0170[0] == null || this.\u0170[1] == null || this.\u0170[2] == null || !this.\u0170[2].equalsIgnoreCase(this.\u0172.\u010e)) {
                    continue;
                }
                if (!this.\u0172.received.equals(String.valueOf(this.\u0170[0]) + this.\u0170[1] + this.\u0170[2])) {
                    this.\u0172.setItem(this.\u0170[0], this.\u0170[1], this.\u0170[2]);
                }
                ryEVCatcher.\u0162 = false;
                ryEVCatcher.\u0161 = false;
            }
            else {
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public void destroy() {
        System.gc();
    }
}
