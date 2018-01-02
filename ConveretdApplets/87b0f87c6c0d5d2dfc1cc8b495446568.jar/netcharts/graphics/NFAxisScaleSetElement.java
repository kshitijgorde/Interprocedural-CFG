// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.graphics;

class NFAxisScaleSetElement
{
    public double percent;
    public Object max;
    public Object min;
    public Object step;
    
    public NFAxisScaleSetElement() {
        this.percent = 0.0;
        this.max = null;
        this.min = null;
        this.step = null;
    }
    
    public NFAxisScaleSetElement(final NFAxisScaleSetElement nfAxisScaleSetElement) {
        this.percent = 0.0;
        this.max = null;
        this.min = null;
        this.step = null;
        this.percent = nfAxisScaleSetElement.percent;
        this.min = nfAxisScaleSetElement.min;
        this.max = nfAxisScaleSetElement.max;
        this.step = nfAxisScaleSetElement.step;
    }
    
    public String toString() {
        return "(" + this.min + "," + this.max + "," + this.step + "," + this.percent + ")";
    }
}
