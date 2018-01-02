// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.applet.Applet;

public class Anchor extends TouchSensor
{
    public final String defaultDescription = "";
    public final StringField description;
    public final StringArrayField parameter;
    public final StringArrayField url;
    protected Searcher i;
    
    public Anchor() {
        this(null);
    }
    
    public Anchor(final Shout3DViewer viewer) {
        this.description = new StringField(this, "description", 0, "");
        this.parameter = new StringArrayField(this, "parameter", 0, null);
        this.url = new StringArrayField(this, "url", 27, null);
        this.setViewer(viewer);
    }
    
    protected void e() {
        super.e();
        String s = null;
        if (this.url.a != null) {
            for (int i = 0; i < this.url.a.length; ++i) {
                if (this.url.a[i] != null && !this.url.a.equals("")) {
                    s = this.url.a[i];
                    break;
                }
            }
        }
        if (s != null) {
            if (s.charAt(0) == '#') {
                this.a(s.substring(1));
                return;
            }
            if (s.indexOf(".s3d") == -1 && s.indexOf(".s3z") == -1 && s.indexOf(".wrl") == -1 && s.indexOf(".wrlz") == -1) {
                if (super.c.b() instanceof Applet) {
                    final Applet applet = (Applet)super.c.b();
                    URL url = null;
                    try {
                        if (s.startsWith("http") || s.startsWith("HTTP")) {
                            url = new URL(s);
                        }
                        else {
                            url = new URL(applet.getCodeBase() + s);
                        }
                    }
                    catch (MalformedURLException ex) {
                        System.err.println(ex);
                    }
                    if (url != null) {
                        String substring = null;
                        if (this.parameter.a != null) {
                            for (int j = 0; j < this.parameter.a.length; ++j) {
                                if (this.parameter.a[j] != null && this.parameter.a[j].startsWith("target=")) {
                                    substring = this.parameter.a[j].substring(7);
                                    break;
                                }
                            }
                        }
                        if (substring == null) {
                            applet.getAppletContext().showDocument(url);
                            return;
                        }
                        applet.getAppletContext().showDocument(url, substring);
                    }
                }
            }
            else {
                super.c.setSceneFromURL(new String[] { s });
                final int lastIndex = s.lastIndexOf(35);
                if (lastIndex != -1) {
                    this.a(s.substring(lastIndex + 1));
                }
            }
        }
    }
    
    protected void a(final String defName) {
        if (defName == null || defName.equals("")) {
            return;
        }
        if (this.i == null) {
            this.i = super.c.getNewSearcher();
        }
        this.i.setDefName(defName);
        this.i.setType("Viewpoint");
        final Node[] searchFirst = this.i.searchFirst(super.c.getScene());
        if (searchFirst != null) {
            ((Viewpoint)searchFirst[searchFirst.length - 1]).isBound.setValue(true);
        }
    }
}
