// 
// Decompiled by Procyon v0.5.30
// 

package newstick.typer;

import java.util.Enumeration;
import newstick.content.IContentParser;
import newstick.content.Content;
import java.net.URL;
import java.util.Vector;

class MagazineRefresher extends Thread implements IFilledContentAsync
{
    private TyperTextMagazine mag;
    private int intervalSeconds;
    public String errorMessage;
    
    public MagazineRefresher(final TyperTextMagazine mag, final int intervalSeconds) {
        this.mag = mag;
        this.intervalSeconds = intervalSeconds;
    }
    
    public void endFillContent(final Object o, final Object o2) {
        final MagazineItem magazineItem = new MagazineItem();
        magazineItem.content = (Vector)o2;
        this.mag.setContent((URL)o, magazineItem);
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.intervalSeconds * 1000);
            }
            catch (InterruptedException ex) {}
            final Enumeration<URL> keys = this.mag.magazine.keys();
            while (keys.hasMoreElements()) {
                final TyperContentParser typerContentParser = new TyperContentParser(this, keys.nextElement());
                typerContentParser.errorMessage = this.errorMessage;
                new Content(typerContentParser).run();
            }
        }
    }
}
