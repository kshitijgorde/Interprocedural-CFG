// 
// Decompiled by Procyon v0.5.30
// 

package newstick.menu;

import java.io.IOException;
import java.net.URL;
import newstick.content.ContentParserBase;

public class MenuContentParser extends ContentParserBase
{
    private Menu menu;
    private URL url;
    public String errorOption;
    private String display;
    private String command;
    
    public void reset() {
        this.menu.clear();
    }
    
    public MenuContentParser(final Menu menu, final URL url) {
        this.errorOption = "error";
        this.menu = menu;
        this.url = url;
        this.setFieldCount(2);
    }
    
    protected void onField(final String s, final int n, final int n2) {
        switch (n2) {
            case 0: {
                this.display = s;
            }
            case 1: {
                this.command = s;
                break;
            }
        }
    }
    
    public void onIOException(final IOException ex) {
        this.menu.add(new MenuItem(this.errorOption, null));
        this.menu.init();
    }
    
    public void afterReading() {
        super.afterReading();
        this.menu.init();
    }
    
    protected void onLine(final String s, final int n) {
        this.menu.add(new MenuItem(this.display, this.command));
    }
    
    public URL getUrl() {
        return this.url;
    }
}
