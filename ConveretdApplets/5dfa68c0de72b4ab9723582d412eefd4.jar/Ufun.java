// 
// Decompiled by Procyon v0.5.30
// 

class Ufun extends Primitives
{
    Object[] arglist;
    Object[] body;
    
    Ufun(final Object[] arglist, final Object[] body) {
        this.arglist = arglist;
        this.body = body;
    }
    
    public Object dispatch(final int n, final Object[] array, final LContext lContext) {
        Object ufunresult = null;
        final Object[] array2 = new Object[this.arglist.length];
        final Symbol ufun = lContext.ufun;
        lContext.ufun = lContext.cfun;
        final Object[] locals = lContext.locals;
        lContext.locals = null;
        for (int i = 0; i < this.arglist.length; ++i) {
            array2[i] = ((Symbol)this.arglist[i]).value;
            ((Symbol)this.arglist[i]).value = array[i];
        }
        try {
            Logo.runCommand(this.body, lContext);
            if (lContext.ufunresult != null && lContext.ufunresult != lContext.juststop) {
                ufunresult = lContext.ufunresult;
            }
        }
        finally {
            lContext.ufun = ufun;
            for (int j = 0; j < this.arglist.length; ++j) {
                ((Symbol)this.arglist[j]).value = array2[j];
            }
            if (lContext.locals != null) {
                for (int k = 0; k < lContext.locals.length; k += 2) {
                    ((Symbol)lContext.locals[k]).value = lContext.locals[k + 1];
                }
            }
            lContext.locals = locals;
            lContext.ufunresult = null;
        }
        return ufunresult;
    }
}
