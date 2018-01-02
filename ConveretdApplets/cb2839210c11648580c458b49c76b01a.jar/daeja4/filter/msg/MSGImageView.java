// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.filter.msg;

import javax.swing.text.html.HTMLDocument;
import java.net.MalformedURLException;
import java.net.URLStreamHandler;
import ji.net.jifile.ns;
import javax.swing.text.html.HTML;
import java.net.URL;
import ji.io.h;
import ji.document.ad;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

public class MSGImageView extends ImageView
{
    private nd a;
    private boolean b;
    private Object c;
    
    public MSGImageView(final Element element, final nd a, final boolean b, final ad c) {
        super(element);
        this.a = a;
        this.b = b;
        this.c = c;
        h.f(c.getId(), String.valueOf(String.valueOf(this.getClass().getName())).concat(".<init> ImageView"));
    }
    
    public URL getImageURL() {
        final String s = (String)this.getElement().getAttributes().getAttribute(HTML.Attribute.SRC);
        final String s2 = (String)this.getElement().getAttributes().getAttribute(HTML.Attribute.ID);
        URL url;
        if (s == null) {
            url = null;
        }
        else {
            final String a = this.a.a(s);
            if (a == null) {
                url = null;
            }
            else {
                try {
                    url = new URL(null, a, new ns(this.c));
                }
                catch (MalformedURLException ex) {
                    h.a(this.a.d(), ex);
                    url = null;
                }
            }
        }
        if (this.b && url == null) {
            final URL base = ((HTMLDocument)this.getDocument()).getBase();
            try {
                url = new URL(base, s);
            }
            catch (MalformedURLException ex2) {
                url = null;
            }
        }
        return url;
    }
}
