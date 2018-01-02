// 
// Decompiled by Procyon v0.5.30
// 

class FormulaPaging$SplitFactorVisitor implements BoxVisitor
{
    private int maxFactor;
    private int factorCut;
    private final FormulaPaging this$0;
    
    public FormulaPaging$SplitFactorVisitor(final FormulaPaging this$0) {
        this.this$0 = this$0;
        this.maxFactor = 0;
        this.factorCut = Integer.MAX_VALUE;
    }
    
    public final void reset(final int factorCut) {
        this.maxFactor = 0;
        this.factorCut = factorCut;
    }
    
    public final int getFactor() {
        return this.maxFactor;
    }
    
    public final int preVisitBox(final AbstractBox abstractBox, final int n, final int n2, final int n3) {
        if (n3 <= 0 || n3 >= abstractBox.height) {
            return -1;
        }
        final int splitFactor = abstractBox.getSplitFactor(n3);
        if (splitFactor > this.maxFactor) {
            this.maxFactor = splitFactor;
        }
        if (this.maxFactor >= this.factorCut) {
            this.maxFactor = Integer.MAX_VALUE;
            return 1;
        }
        return 0;
    }
    
    public final int postVisitBox(final AbstractBox abstractBox, final int n, final int n2, final int n3) {
        return 0;
    }
}
