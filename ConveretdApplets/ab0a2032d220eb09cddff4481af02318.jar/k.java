import netscape.javascript.JSObject;

// 
// Decompiled by Procyon v0.5.30
// 

public class k
{
    public boolean LIb;
    public boolean MIb;
    private Object NIb;
    private String OIb;
    
    public k(final String s, final Object nIb) {
        this.LIb = true;
        this.MIb = false;
        this.NIb = null;
        this.OIb = "\tvar agent=navigator.userAgent.toLowerCase();\tif((agent.indexOf(\"msie\") != -1) && (agent.indexOf(\"opera\") == -1))\t\tdocument.FnChartsApplet.setBrowserType(\"msie\");\telse\t\tdocument.FnChartsApplet.setBrowserType(\"not_msie\");";
        if (s != null && s.length() > 0) {
            this.OIb = i.b(this.OIb, "FnChartsApplet", s);
        }
        this.NIb = nIb;
    }
    
    public void E() {
        try {
            if (this.NIb != null) {
                ((JSObject)this.NIb).eval(this.OIb);
            }
            else {
                this.MIb = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean Y() {
        if (!this.MIb) {
            this.E();
        }
        return this.LIb;
    }
    
    public void G(final boolean lIb) {
        this.LIb = lIb;
        this.MIb = true;
    }
    
    public boolean Z() {
        final String property = System.getProperty("java.vendor");
        return property != null && property.indexOf("Microsoft") != -1;
    }
}
