import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class MultilineSelection extends AbstractSelection
{
    int ActualitzaRect;
    int Afegeix;
    MultilineBox PosAnt_Fora;
    
    public MultilineSelection(final MultilineBox posAnt_Fora, final Stack stack, final Stack stack2, final boolean deldret) {
        this.PosAnt_Fora = posAnt_Fora;
        super.deldret = deldret;
        this.ActualitzaRect = stack.pop();
        this.Afegeix = stack2.pop();
        if (!stack.empty()) {
            super.fille = this.PosAnt_Fora.fill[this.ActualitzaRect].SeleccionaDreta(stack);
            if (super.fille != null) {
                ++this.ActualitzaRect;
            }
        }
        if (!stack2.empty()) {
            super.filld = this.PosAnt_Fora.fill[this.Afegeix].SeleccionaEsquerra(stack2);
            if (super.filld == null) {
                ++this.Afegeix;
            }
        }
        if (this.ActualitzaRect > this.Afegeix) {
            this.ActualitzaRect = this.Afegeix;
        }
    }
    
    private MultilineSelection() {
    }
    
    public final boolean buida() {
        if (super.fille != null) {
            if (!super.fille.buida()) {
                return false;
            }
        }
        if (super.filld != null) {
            if (!super.filld.buida()) {
                return false;
            }
        }
        if (this.ActualitzaRect > this.Afegeix) {
            return true;
        }
        return false;
    }
    
    public final BoxPosition getCurpos() {
        if (super.deldret) {
            if (super.filld != null) {
                return super.filld.getCurpos();
            }
            return this.PosAnt_Fora.fill[this.Afegeix - 1].PosAnt_Fora();
        }
        else {
            if (super.fille != null) {
                return super.fille.getCurpos();
            }
            return this.PosAnt_Fora.fill[this.ActualitzaRect].PosSeg_Fora();
        }
    }
    
    public final Rectangles getRectangles(final BoxComponent boxComponent) {
        final Rectangles rectangles = new Rectangles();
        if (super.fille != null) {
            rectangles.Afegeix(super.fille.getRectangles(boxComponent));
        }
        rectangles.Afegeix(this.PosAnt_Fora.getRectanglesSeleccio(this.ActualitzaRect, this.Afegeix));
        if (super.filld != null) {
            rectangles.Afegeix(super.filld.getRectangles(boxComponent));
        }
        return rectangles;
    }
    
    public final void script(final BoxScripting boxScripting) {
        this.PosAnt_Fora.onScript(boxScripting, true, super.fille, super.filld, this.ActualitzaRect, this.Afegeix);
    }
    
    public final boolean espotborrardirectament() {
        return true;
    }
    
    public final BoxPosition suprimeix(final BoxComponent boxComponent) {
        if (super.fille != null && (super.deldret || !(super.filld instanceof EmptySelection))) {
            super.fille.suprimeix(boxComponent);
        }
        if (super.filld != null && (!super.deldret || !(super.filld instanceof EmptySelection))) {
            super.filld.suprimeix(boxComponent);
        }
        this.PosAnt_Fora.Treure(this.ActualitzaRect, this.Afegeix, boxComponent);
        super.esValida = false;
        return this.PosAnt_Fora.UneixLinees(this.ActualitzaRect - 1, boxComponent);
    }
    
    public final AbstractSelection canviaAtribut(final BoxComponent boxComponent, final Attributes attributes) {
        for (int i = this.ActualitzaRect; i < this.Afegeix; ++i) {
            this.PosAnt_Fora.fill[i].posaAtribut(boxComponent, attributes);
        }
        if (super.fille != null) {
            super.fille = super.fille.canviaAtribut(boxComponent, attributes);
        }
        if (super.filld != null) {
            super.filld = super.filld.canviaAtribut(boxComponent, attributes);
        }
        this.PosAnt_Fora.ActualitzaRect(boxComponent);
        return this;
    }
    
    public final AbstractBox getMama() {
        return this.PosAnt_Fora;
    }
}
