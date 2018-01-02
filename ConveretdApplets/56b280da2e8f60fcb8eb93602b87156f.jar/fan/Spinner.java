// 
// Decompiled by Procyon v0.5.30
// 

package fan;

class Spinner extends Thread
{
    private Fan fan;
    static boolean isRunning;
    static boolean isSuspending;
    static int angle_1;
    static int angle_2;
    static int angle_3;
    static int speed;
    
    Spinner() {
        this.fan = new Fan();
        Spinner.speed = 9;
        Spinner.angle_1 = 0;
        Spinner.angle_2 = 120;
        Spinner.angle_3 = 240;
        Spinner.isRunning = true;
        Spinner.isSuspending = false;
    }
    
    public void run() {
        this.spinTheFan();
    }
    
    private synchronized void spinTheFan() {
        try {
            while (Spinner.isRunning) {
                if (Spinner.isSuspending) {
                    this.wait();
                }
                --Spinner.angle_1;
                --Spinner.angle_2;
                --Spinner.angle_3;
                Thread.sleep(Spinner.speed);
                if (Spinner.angle_1 == -360 || Spinner.angle_2 == -240 || Spinner.angle_3 == -120) {
                    Spinner.angle_1 = 0;
                    Spinner.angle_2 = 120;
                    Spinner.angle_3 = 240;
                }
            }
        }
        catch (InterruptedException ex) {}
    }
}
