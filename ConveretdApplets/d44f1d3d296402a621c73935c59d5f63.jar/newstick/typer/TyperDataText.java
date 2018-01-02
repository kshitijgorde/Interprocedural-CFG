// 
// Decompiled by Procyon v0.5.30
// 

package newstick.typer;

import java.net.MalformedURLException;
import java.net.URL;

public class TyperDataText
{
    public String title;
    public String text;
    public URL url;
    
    public boolean isUrl() {
        return this.url != null;
    }
    
    public TyperDataText(final String text, final String title, final String url) {
        if (text != null && text.trim().length() > 0) {
            this.text = text;
        }
        if (title != null && title.trim().length() > 0) {
            this.title = title;
        }
        if (url != null && url.trim().length() > 0) {
            this.setUrl(url);
        }
    }
    
    public void setUrl(final URL url) {
        this.url = url;
    }
    
    public void setUrl(final String s) {
        try {
            this.url = new URL(s);
        }
        catch (MalformedURLException ex) {
            this.url = null;
        }
    }
    
    public boolean isTitle() {
        return this.title != null;
    }
    
    public boolean isText() {
        return this.text != null;
    }
}
