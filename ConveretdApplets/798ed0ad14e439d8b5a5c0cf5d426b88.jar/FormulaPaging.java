import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

public class FormulaPaging
{
    private Length printWidth;
    private Length printHeight;
    private float minFillingRatio;
    private static final Length deltaY;
    
    public FormulaPaging() {
        this.printWidth = new Length("180mm");
        this.printHeight = new Length("287mm");
        this.minFillingRatio = 0.75f;
    }
    
    public final void setPrintableArea(final Length printWidth, final Length printHeight) {
        if (printWidth.getValue() <= 0.0 || printHeight.getValue() <= 0.0) {
            throw new IllegalArgumentException();
        }
        this.printWidth = printWidth;
        this.printHeight = printHeight;
    }
    
    public final Rectangles computeBounds(final AbstractBox abstractBox, final int n, final boolean b) {
        final int userUnit = this.printWidth.toUserUnit(abstractBox.EM, n);
        final int userUnit2 = this.printHeight.toUserUnit(abstractBox.EM, n);
        final int userUnit3 = FormulaPaging.deltaY.toUserUnit(abstractBox.EM, n);
        final int round = Math.round(this.minFillingRatio * userUnit2);
        int i = 0;
        final FormulaPaging$SplitFactorVisitor formulaPaging$SplitFactorVisitor = new FormulaPaging$SplitFactorVisitor(this);
        final Rectangles rectangles = new Rectangles();
        while (i < abstractBox.height) {
            int n2 = Integer.MAX_VALUE;
            int n3 = i + userUnit2;
            for (int j = userUnit2; j > round; j -= userUnit3) {
                formulaPaging$SplitFactorVisitor.reset(n2);
                abstractBox.deepExplore(formulaPaging$SplitFactorVisitor, -1, 0, i + j);
                final int factor = formulaPaging$SplitFactorVisitor.getFactor();
                if (factor < n2) {
                    n2 = factor;
                    n3 = i + j;
                }
                if (n2 <= 0) {
                    break;
                }
            }
            rectangles.Afegeix(new Rectangle(0, i, userUnit, n3 - i));
            if (!b && abstractBox.width % userUnit != 0) {
                for (int k = 1; k <= abstractBox.width / userUnit; ++k) {
                    rectangles.Afegeix(new Rectangle(k * userUnit, i, userUnit, n3 - i));
                }
            }
            i = n3;
        }
        return rectangles;
    }
    
    static {
        deltaY = new Length("1px");
    }
}
