import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.Image;
import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class HourThread extends Thread
{
    int i;
    Date now;
    Image newHourImage;
    int delay;
    ImageFilter filter;
    Clock clocky;
    
    public void run() {
        long startTime = System.currentTimeMillis();
        Thread.currentThread().setPriority(1);
        while (true) {
            this.now = new Date();
            final int min = this.now.getMinutes();
            final int hour = this.now.getHours();
            if (min % 2 == 0) {
                ++this.i;
                this.filter = new RotateFilter((-hour * 60 - min) / 2 * Clock.radiansPerDegree);
                final ImageProducer producer = new FilteredImageSource(Clock.hImage.getSource(), this.filter);
                this.newHourImage = this.clocky.createImage(producer);
            }
            else if (this.i > 0) {
                this.clocky.hourImage = this.newHourImage;
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
    
    public HourThread(final String str, final Clock clk) {
        super(str);
        this.delay = 60000;
        this.clocky = clk;
    }
}
