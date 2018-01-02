import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class PGC_Variable
{
    public int index;
    public String name;
    public boolean is_input;
    public boolean is_function;
    public boolean is_autoplot_var;
    public boolean is_autoincr_var;
    public boolean is_displayed;
    public Complex coordinates;
    public String expression;
    public Vector plotting_vec;
    
    PGC_Variable() {
        this.is_input = false;
        this.is_function = false;
        this.is_autoplot_var = false;
        this.is_autoincr_var = false;
        this.is_displayed = false;
    }
}
