// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.force;

public abstract class AbstractForce implements Force
{
    protected float[] params;
    protected float[] minValues;
    protected float[] maxValues;
    
    public void init(final ForceSimulator forceSimulator) {
    }
    
    public int getParameterCount() {
        return (this.params == null) ? 0 : this.params.length;
    }
    
    public float getParameter(final int n) {
        if (n < 0 || this.params == null || n >= this.params.length) {
            throw new IndexOutOfBoundsException();
        }
        return this.params[n];
    }
    
    public float getMinValue(final int n) {
        if (n < 0 || this.params == null || n >= this.params.length) {
            throw new IndexOutOfBoundsException();
        }
        return this.minValues[n];
    }
    
    public float getMaxValue(final int n) {
        if (n < 0 || this.params == null || n >= this.params.length) {
            throw new IndexOutOfBoundsException();
        }
        return this.maxValues[n];
    }
    
    public String getParameterName(final int n) {
        final String[] parameterNames = this.getParameterNames();
        if (n < 0 || parameterNames == null || n >= parameterNames.length) {
            throw new IndexOutOfBoundsException();
        }
        return parameterNames[n];
    }
    
    public void setParameter(final int n, final float n2) {
        if (n < 0 || this.params == null || n >= this.params.length) {
            throw new IndexOutOfBoundsException();
        }
        this.params[n] = n2;
    }
    
    public void setMinValue(final int n, final float n2) {
        if (n < 0 || this.params == null || n >= this.params.length) {
            throw new IndexOutOfBoundsException();
        }
        this.minValues[n] = n2;
    }
    
    public void setMaxValue(final int n, final float n2) {
        if (n < 0 || this.params == null || n >= this.params.length) {
            throw new IndexOutOfBoundsException();
        }
        this.maxValues[n] = n2;
    }
    
    protected abstract String[] getParameterNames();
    
    public boolean isItemForce() {
        return false;
    }
    
    public boolean isSpringForce() {
        return false;
    }
    
    public void getForce(final ForceItem forceItem) {
        throw new UnsupportedOperationException("This class does not support this operation");
    }
    
    public void getForce(final Spring spring) {
        throw new UnsupportedOperationException("This class does not support this operation");
    }
}
