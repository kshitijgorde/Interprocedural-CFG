import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.Component;
import java.awt.Choice;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class goURL extends Applet
{
    Choice page;
    String webpage;
    String pageURL;
    String[] holdPage;
    String[] holdURL;
    int NUMBER_OF_URLS;
    int i;
    
    public void init() {
        this.page = new Choice();
        this.holdPage = new String[this.NUMBER_OF_URLS];
        this.holdURL = new String[this.NUMBER_OF_URLS];
        this.i = 0;
        while (this.i < this.NUMBER_OF_URLS) {
            this.webpage = this.getParameter("page" + this.i);
            this.pageURL = this.getParameter("url" + this.i);
            if (this.webpage == null) {
                break;
            }
            if (this.pageURL == null) {
                break;
            }
            this.holdPage[this.i] = this.webpage;
            this.holdURL[this.i] = this.pageURL;
            this.page.addItem(this.holdPage[this.i]);
            ++this.i;
        }
        this.add(this.page);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target.equals(this.page)) {
            final int selectedIndex = this.page.getSelectedIndex();
            try {
                this.getAppletContext().showDocument(new URL(this.holdURL[selectedIndex]));
            }
            catch (MalformedURLException ex) {
                System.err.println(ex);
            }
        }
        return true;
    }
    
    public goURL() {
        this.NUMBER_OF_URLS = 256;
    }
}
