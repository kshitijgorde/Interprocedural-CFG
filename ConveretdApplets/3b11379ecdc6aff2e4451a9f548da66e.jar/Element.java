import java.awt.Image;
import java.applet.Applet;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Element
{
    public String m_text;
    public int m_level;
    boolean m_visible;
    public String m_url;
    public int m_textWidth;
    public String m_target;
    public int m_isNew;
    public int m_imgNum;
    public Vector m_related;
    public boolean m_merge;
    public String m_seeAlso;
    
    public void hide() {
        this.m_visible = false;
    }
    
    public boolean isVisible() {
        return this.m_visible;
    }
    
    public void show() {
        this.m_visible = true;
    }
    
    Element() {
        this.m_url = "";
        this.m_target = "";
        this.m_imgNum = -1;
        this.m_seeAlso = "";
    }
    
    Element(final String s, final int n, final Applet applet) {
        this(s, n, null, "", applet);
    }
    
    Element(final String s, final int n, final Image image, final Applet applet) {
        this(s, n, image, "", applet);
    }
    
    Element(final String text, final int level, final Image image, final String url, final Applet applet) {
        this.m_url = "";
        this.m_target = "";
        this.m_imgNum = -1;
        this.m_seeAlso = "";
        this.m_text = text;
        this.m_level = level;
        this.m_url = url;
        if (level < 2) {
            this.m_visible = true;
        }
    }
    
    Element(final String s, final int n, final Image image, final String s2, final Applet applet, final int n2) {
        this(s, n, image, s2, "", applet, n2);
    }
    
    Element(final String s, final int n, final Image image, final String s2, final String target, final Applet applet, final int n2) {
        this(s, n, image, s2, applet);
        if (n < n2) {
            this.m_visible = true;
        }
        else {
            this.m_visible = false;
        }
        this.m_target = target;
    }
    
    public String getText() {
        return this.m_text;
    }
}
