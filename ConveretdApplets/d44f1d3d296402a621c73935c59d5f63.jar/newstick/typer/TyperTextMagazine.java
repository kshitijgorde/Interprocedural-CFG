// 
// Decompiled by Procyon v0.5.30
// 

package newstick.typer;

import newstick.content.IContentParser;
import java.util.Vector;
import java.net.URL;
import newstick.content.Content;
import java.util.Hashtable;

public class TyperTextMagazine implements IFilledContentAsync
{
    protected Hashtable magazine;
    private Content threadGetter;
    private MagazineRefresher threadRefresher;
    public String errorMessage;
    private Typer typer;
    private URL url;
    
    public void stop() {
        if (this.threadGetter != null) {
            this.threadGetter.stop();
            this.threadGetter = null;
        }
        if (this.threadRefresher != null) {
            this.threadRefresher.stop();
            this.threadRefresher = null;
        }
    }
    
    public boolean isFresh(final URL url) {
        return url != null && this.magazine.containsKey(url) && this.magazine.get(url).fresh;
    }
    
    public void setContent(final URL url, final MagazineItem magazineItem) {
        magazineItem.fresh = true;
        this.magazine.put(url, magazineItem);
    }
    
    public TyperTextMagazine(int n) {
        this.magazine = new Hashtable();
        if (n < 60) {
            n = 60;
        }
        this.threadRefresher = new MagazineRefresher(this, n);
        this.threadRefresher.errorMessage = this.errorMessage;
        this.threadRefresher.start();
    }
    
    private void fillTyper(final MagazineItem magazineItem, final Typer typer) {
        final Vector content = magazineItem.content;
        if (content != null) {
            magazineItem.fresh = false;
            final TyperDataText[] array = new TyperDataText[content.size()];
            for (int i = 0; i < content.size(); ++i) {
                final String[] array2 = content.elementAt(i);
                array[i] = new TyperDataText(array2[2], array2[1], array2[0]);
            }
            typer.newContent(array);
        }
    }
    
    public void endFillContent(final Object o, final Object o2) {
        final MagazineItem magazineItem = new MagazineItem();
        magazineItem.content = (Vector)o2;
        this.setContent((URL)o, magazineItem);
        this.fillTyper(magazineItem, this.typer);
    }
    
    public void fillText(final URL url, final Typer typer) {
        if (this.magazine.containsKey(url)) {
            this.fillTyper(this.magazine.get(url), typer);
            return;
        }
        this.typer = typer;
        this.url = url;
        if (this.threadGetter != null) {
            this.threadGetter.stop();
        }
        final TyperContentParser typerContentParser = new TyperContentParser(this, url);
        typerContentParser.errorMessage = this.errorMessage;
        (this.threadGetter = new Content(typerContentParser)).start();
    }
}
