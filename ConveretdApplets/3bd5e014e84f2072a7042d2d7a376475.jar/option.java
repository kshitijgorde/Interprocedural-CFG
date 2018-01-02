// 
// Decompiled by Procyon v0.5.30
// 

class option extends cfgitem
{
    String defval;
    
    option(final String s, final String defval, final String s2, final config config) {
        super(s, defval, s2, config);
        this.defval = defval;
    }
    
    protected void set(final String val) {
        if (super.opts.indexOf("F") < 0) {
            super.val = val;
        }
    }
    
    protected void reset() {
        super.val = this.defval;
    }
    
    void print() {
        if (super.opts.indexOf("V") >= 0) {
            super.cfg.msg((super.val == null) ? super.name : (String.valueOf(super.name) + " = " + super.val), "\n");
        }
    }
    
    protected String get() {
        return super.val;
    }
    
    protected int getint() {
        return this.geti(10);
    }
    
    protected int gethex() {
        return this.geti(16);
    }
    
    int geti(final int n) {
        int int1 = 0;
        try {
            int1 = Integer.parseInt(super.val, n);
        }
        catch (Exception ex) {}
        return int1;
    }
    
    protected float getfloat() {
        float floatValue = 0.0f;
        try {
            floatValue = Float.valueOf(super.val);
        }
        catch (Exception ex) {}
        return floatValue;
    }
    
    protected boolean getbool() {
        return this.getint() != 0;
    }
}
