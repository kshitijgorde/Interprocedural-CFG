// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

public final class PlusNumber implements BinaryFunction
{
    private Class mode;
    
    public PlusNumber() {
        this.mode = new Integer(0).getClass();
    }
    
    public PlusNumber(final Class mode) {
        if (!NumberHelper.classNumber.isAssignableFrom(mode)) {
            throw new IllegalArgumentException("discriminator must be an instance of java.lang.Number");
        }
        this.mode = mode;
    }
    
    public Object execute(final Object o, final Object o2) {
        return NumberHelper.plus((Number)o, (Number)o2, this.mode);
    }
}
