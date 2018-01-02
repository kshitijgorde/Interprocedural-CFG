import netscape.javascript.JSObject;

// 
// Decompiled by Procyon v0.5.30
// 

public class else
{
    public boolean yna;
    public boolean zna;
    private Object Ana;
    private String Bna;
    
    public else(final String s, final Object ana) {
        this.yna = true;
        this.zna = false;
        this.Ana = null;
        this.Bna = "\tvar agent=navigator.userAgent.toLowerCase();\tif((agent.indexOf(\"msie\") != -1) && (agent.indexOf(\"opera\") == -1))\t\tdocument.FnChartsApplet.setBrowserType(\"msie\");\telse\t\tdocument.FnChartsApplet.setBrowserType(\"not_msie\");";
        if (s != null && s.length() > 0) {
            this.Bna = catch.a(this.Bna, "FnChartsApplet", s);
        }
        this.Ana = ana;
    }
    
    public void v() {
        try {
            if (this.Ana != null) {
                ((JSObject)this.Ana).eval(this.Bna);
            }
            else {
                this.zna = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean l() {
        if (!this.zna) {
            this.v();
        }
        return this.yna;
    }
    
    public void w(final boolean yna) {
        this.yna = yna;
        this.zna = true;
    }
    
    public boolean m() {
        final String property = System.getProperty("java.vendor");
        return property != null && property.indexOf("Microsoft") != -1;
    }
}
