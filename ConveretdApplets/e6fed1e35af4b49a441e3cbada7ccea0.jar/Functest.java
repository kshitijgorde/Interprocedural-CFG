import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class Functest
{
    private String f;
    private String paramname;
    double paramvalue;
    Parameter param;
    public Vector Parameters;
    public Vector paramvalues;
    public boolean paramnew;
    public boolean error;
    private double minx;
    private double maxx;
    
    public Functest() {
        this.f = "0";
        this.paramname = "";
        this.paramvalue = 0.0;
        this.Parameters = new Vector();
        this.paramvalues = new Vector();
        this.paramnew = false;
        this.error = false;
    }
    
    public Functest(final String f) {
        this.f = "0";
        this.paramname = "";
        this.paramvalue = 0.0;
        this.Parameters = new Vector();
        this.paramvalues = new Vector();
        this.paramnew = false;
        this.error = false;
        this.f = f;
    }
}
