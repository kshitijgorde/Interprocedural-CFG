import netscape.javascript.JSObject;

// 
// Decompiled by Procyon v0.5.30
// 

public class f
{
    public boolean _ta;
    public boolean ata;
    private Object bta;
    private String cta;
    
    public f(final String s, final Object bta) {
        this._ta = true;
        this.ata = false;
        this.bta = null;
        this.cta = "\tvar agent=navigator.userAgent.toLowerCase();\tif((agent.indexOf(\"msie\") != -1) && (agent.indexOf(\"opera\") == -1))\t\tdocument.FnChartsApplet.setBrowserType(\"msie\");\telse\t\tdocument.FnChartsApplet.setBrowserType(\"not_msie\");";
        if (s != null && s.length() > 0) {
            this.cta = a.b(this.cta, "FnChartsApplet", s);
        }
        this.bta = bta;
    }
    
    public void _a() {
        try {
            if (this.bta != null) {
                ((JSObject)this.bta).eval(this.cta);
            }
            else {
                this.ata = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean c() {
        if (!this.ata) {
            this._a();
        }
        return this._ta;
    }
    
    public void L(final boolean ta) {
        this._ta = ta;
        this.ata = true;
    }
    
    public boolean d() {
        final String property = System.getProperty("java.vendor");
        return property != null && property.indexOf("Microsoft") != -1;
    }
}
