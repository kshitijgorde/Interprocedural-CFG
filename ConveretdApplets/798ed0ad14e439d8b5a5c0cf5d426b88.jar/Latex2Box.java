// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Latex2Box
{
    private static Class latex2BoxClass;
    
    public static final Latex2Box newLatex2Box() {
        try {
            return Latex2Box.latex2BoxClass.newInstance();
        }
        catch (Exception ex) {
            throw new Error(ex);
        }
    }
    
    public static final void setLatex2Box(final Class latex2BoxClass) {
        Latex2Box.latex2BoxClass = latex2BoxClass;
    }
    
    public abstract AbstractBox parse(final String p0);
}
