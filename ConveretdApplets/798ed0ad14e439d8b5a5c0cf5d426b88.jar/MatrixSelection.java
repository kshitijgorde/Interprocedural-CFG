import java.util.Stack;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class MatrixSelection extends AbstractSelection
{
    Point ActualitzaRect;
    Point EliminaColumnes;
    int i;
    int f;
    AbstractMatrixBox M;
    
    public MatrixSelection(final AbstractMatrixBox m, final Stack stack, final Stack stack2, final boolean deldret) {
        this.M = m;
        super.deldret = deldret;
        this.i = stack.pop();
        this.f = stack2.pop();
        this.ActualitzaRect = new Point(this.i % this.M.columnes, this.i / this.M.columnes);
        this.EliminaColumnes = new Point(this.f % this.M.columnes, this.f / this.M.columnes);
    }
    
    public final boolean buida() {
        return false;
    }
    
    public final BoxPosition getCurpos() {
        return this.getSelpos();
    }
    
    public final BoxPosition getSelpos() {
        if (super.deldret) {
            return this.M.fill[this.f].PosAnt_Fora();
        }
        return this.M.fill[this.i].PosAnt_Fora();
    }
    
    public final Rectangles getRectangles(final BoxComponent boxComponent) {
        return new Rectangles(this.M.getRectangleSeleccio(this.i, this.f + 1, boxComponent));
    }
    
    public final void script(final BoxScripting boxScripting) {
        int n;
        int n2;
        if (this.ActualitzaRect.x < this.EliminaColumnes.x) {
            n = this.ActualitzaRect.x;
            n2 = this.EliminaColumnes.x + 1;
        }
        else {
            n = this.EliminaColumnes.x;
            n2 = this.ActualitzaRect.x + 1;
        }
        int n3;
        int n4;
        if (this.ActualitzaRect.y < this.EliminaColumnes.y) {
            n3 = this.ActualitzaRect.y;
            n4 = this.EliminaColumnes.y + 1;
        }
        else {
            n3 = this.EliminaColumnes.y;
            n4 = this.ActualitzaRect.y + 1;
        }
        boxScripting.openTag(this.M.scriptCommand(boxScripting));
        this.M.submatrix(boxScripting, n, n3, n2, n4);
        boxScripting.closeTag();
    }
    
    public final BoxPosition suprimeix(final BoxComponent boxComponent) {
        int n;
        int n2;
        if (this.ActualitzaRect.x < this.EliminaColumnes.x) {
            n = this.ActualitzaRect.x;
            n2 = this.EliminaColumnes.x + 1;
        }
        else {
            n = this.EliminaColumnes.x;
            n2 = this.ActualitzaRect.x + 1;
        }
        int n3;
        int n4;
        if (this.ActualitzaRect.y < this.EliminaColumnes.y) {
            n3 = this.ActualitzaRect.y;
            n4 = this.EliminaColumnes.y + 1;
        }
        else {
            n3 = this.EliminaColumnes.y;
            n4 = this.ActualitzaRect.y + 1;
        }
        if (!this.ActualitzaRect()) {
            for (int i = n; i < n2; ++i) {
                for (int j = n3; j < n4; ++j) {
                    this.M.SubstitueixCapsa(new EmptyBox(), i + j * this.M.columnes, boxComponent);
                }
            }
            return this.getCurpos();
        }
        super.esValida = false;
        if (n == 0 && n2 == this.M.columnes && this.M.files + n4 - n3 - 1 > this.M.row_minoccur) {
            return this.M.EliminaFiles(boxComponent, n3, n4);
        }
        if (n3 == 0 && n4 == this.M.files && this.M.columnes + n2 - n - 1 > this.M.column_minoccur) {
            return this.M.EliminaColumnes(boxComponent, n, n2);
        }
        return this.getCurpos();
    }
    
    private final boolean ActualitzaRect() {
        int n;
        int n2;
        if (this.ActualitzaRect.x < this.EliminaColumnes.x) {
            n = this.ActualitzaRect.x;
            n2 = this.EliminaColumnes.x + 1;
        }
        else {
            n = this.EliminaColumnes.x;
            n2 = this.ActualitzaRect.x + 1;
        }
        int n3;
        int n4;
        if (this.ActualitzaRect.y < this.EliminaColumnes.y) {
            n3 = this.ActualitzaRect.y;
            n4 = this.EliminaColumnes.y + 1;
        }
        else {
            n3 = this.EliminaColumnes.y;
            n4 = this.ActualitzaRect.y + 1;
        }
        for (int i = n; i < n2; ++i) {
            for (int j = n3; j < n4; ++j) {
                if (!(this.M.fill[i + j * this.M.columnes] instanceof EmptyBox)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public final AbstractSelection canviaAtribut(final BoxComponent boxComponent, final Attributes attributes) {
        int n;
        int n2;
        if (this.ActualitzaRect.x < this.EliminaColumnes.x) {
            n = this.ActualitzaRect.x;
            n2 = this.EliminaColumnes.x + 1;
        }
        else {
            n = this.EliminaColumnes.x;
            n2 = this.ActualitzaRect.x + 1;
        }
        int n3;
        int n4;
        if (this.ActualitzaRect.y < this.EliminaColumnes.y) {
            n3 = this.ActualitzaRect.y;
            n4 = this.EliminaColumnes.y + 1;
        }
        else {
            n3 = this.EliminaColumnes.y;
            n4 = this.ActualitzaRect.y + 1;
        }
        for (int i = n; i < n2; ++i) {
            for (int j = n3; j < n4; ++j) {
                this.M.fill[i + j * this.M.columnes].posaAtribut(boxComponent, attributes);
            }
        }
        this.M.ActualitzaRect(boxComponent);
        return this;
    }
    
    public final AbstractBox getMama() {
        return this.M;
    }
}
