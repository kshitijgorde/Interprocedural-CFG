// 
// Decompiled by Procyon v0.5.30
// 

package en.javafx;

public class Parameter
{
    public dl defaultValue;
    public dl min;
    public dl max;
    public dl target;
    public boolean constrained;
    public String name;
    
    public Parameter(final String name, final dl target) {
        this.constrained = false;
        this.name = name;
        this.constrained = false;
        this.target = target;
    }
    
    public Parameter(final String name, final dl target, final dl defaultValue) {
        this.constrained = false;
        this.name = name;
        this.constrained = false;
        this.target = target;
        this.defaultValue = defaultValue;
    }
    
    public Parameter(final String name, final dl target, final dl defaultValue, final dl min, final dl max) {
        this.constrained = false;
        this.name = name;
        this.constrained = true;
        this.target = target;
        this.defaultValue = defaultValue;
        this.min = min;
        this.max = max;
    }
    
    public final void set(final String s) throws Exception {
        if (s == null) {
            System.out.println(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.name))).append(",").append(this.target.getClass().getName()).append(", default = ").append(this.defaultValue.toString()))));
            this.target.p(this.defaultValue);
        }
        else {
            this.target.p(s);
            if (this.constrained) {
                this.target.p(this.min, this.max);
            }
        }
    }
    
    public final String get() {
        return this.target.toString();
    }
    
    public final void setNormalised(final float n) {
        if (this.constrained) {
            this.target.p(n, ((dc)this.min).p, ((dc)this.max).p);
        }
        else {
            System.out.println("Parameter: cannot setNormalised on unconstrained parameter");
        }
    }
    
    public final float getNormalised() {
        if (this.constrained) {
            return this.target.p(((dc)this.min).p, ((dc)this.max).p);
        }
        System.out.println("Parameter: cannot getNormalised on unconstrained parameter");
        return 0.0f;
    }
}
