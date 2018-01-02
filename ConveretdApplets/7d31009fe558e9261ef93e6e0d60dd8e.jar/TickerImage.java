import java.awt.Graphics;
import java.net.MalformedURLException;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class TickerImage extends TickerData
{
    private Image image;
    
    public TickerImage(final TickerInfo ticker, final String s) {
        this.image = null;
        this.ticker = ticker;
        this.parse(s);
        final String field = this.getField("SOURCE");
        if (field != null) {
            try {
                this.image = ticker.app.getImage(new URL(ticker.app.getDocumentBase(), field)).getScaledInstance(-1, ticker.app.getSize().height, 4);
                if (this.image != null) {
                    final MediaTracker mediaTracker = new MediaTracker(ticker.app);
                    mediaTracker.addImage(this.image, 0);
                    mediaTracker.waitForID(0);
                    if (!mediaTracker.isErrorID(0)) {
                        this.width = this.image.getWidth(ticker.app);
                        this.height = this.image.getHeight(ticker.app);
                    }
                    else {
                        System.out.println("error loading image " + field);
                    }
                }
                else {
                    System.out.println("Image " + field + " not found.");
                }
            }
            catch (MalformedURLException ex) {
                System.out.println("no such image: " + field);
                ex.printStackTrace(System.out);
            }
            catch (InterruptedException ex2) {
                System.out.println("interrupted during load of " + field);
                ex2.printStackTrace(System.out);
            }
        }
    }
    
    @Override
    protected int redraw(final Graphics graphics, final int n, final int n2, final boolean b) {
        graphics.drawImage(this.image, n, n2, this.width, this.height, this.ticker.bgColor, this.ticker.app);
        if (b) {
            return this.width;
        }
        return this.height;
    }
}
