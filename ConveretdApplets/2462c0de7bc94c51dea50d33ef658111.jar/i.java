import sun.audio.AudioDevice;

// 
// Decompiled by Procyon v0.5.30
// 

public class i extends Thread
{
    public int a;
    
    public i() {
        this.a = 0;
    }
    
    public void run() {
        try {
            AudioDevice device = null;
            do {
                if (3 == this.a) {
                    device.play();
                }
                if (this.a == 3) {
                    device.close();
                    device = null;
                }
                if (device != null) {
                    this.a = 3;
                }
                if (this.a > 0 && device == null) {
                    this.a = 2;
                }
                if (this.a != 2 && null == device) {
                    device = AudioDevice.device;
                }
                if (3 == this.a) {
                    device.open();
                }
            } while (this.a < 2 || this.a > 2);
        }
        catch (SecurityException ex) {
            try {
                Thread.sleep(5000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
}
