import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.Image;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class MinuteThread extends Thread
{
    int i;
    Date now;
    Image newMinuteImage;
    int delay;
    ImageFilter filter;
    Clock clocky;
    
    public MinuteThread(final String str, final Clock clk) {
        super(str);
        this.delay = 10000;
        this.clocky = clk;
    }
    
    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            this.now = new Date();
            final int min = this.now.getMinutes();
            final int sec = this.now.getSeconds();
            if ((sec >= 0 && sec <= 10) || (sec >= 30 && sec <= 40)) {
                ++this.i;
                if (sec <= 10) {
                    this.filter = new RotateFilter(-min * 6 * Clock.radiansPerDegree);
                }
                else {
                    this.filter = new RotateFilter((-min * 6 - 3) * Clock.radiansPerDegree);
                }
                final ImageProducer producer = new FilteredImageSource(Clock.minImage.getSource(), this.filter);
                this.newMinuteImage = this.clocky.createImage(producer);
            }
            else if (((sec >= 20 && sec <= 30) || (sec >= 49 && sec <= 59)) && this.i > 0) {
                this.clocky.minuteImage = this.newMinuteImage;
            }
            try {
                startTime += this.delay;
                Thread.sleep(Math.max(0L, startTime - System.currentTimeMillis()));
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }
}
