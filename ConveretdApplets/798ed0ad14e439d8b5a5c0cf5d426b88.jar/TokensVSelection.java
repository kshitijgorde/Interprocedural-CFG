import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class TokensVSelection extends AbstractSelection
{
    int Actualitza;
    int ActualitzaRect;
    public TokensVBox TV;
    
    public TokensVSelection(final TokensVBox tv, final Stack stack, final Stack stack2, final boolean deldret) {
        this.TV = tv;
        super.deldret = deldret;
        this.Actualitza = stack.pop();
        this.ActualitzaRect = stack2.pop();
        if (!stack2.empty()) {
            ++this.ActualitzaRect;
        }
    }
    
    public final boolean buida() {
        return this.Actualitza >= this.ActualitzaRect;
    }
    
    public final BoxPosition getCurpos() {
        if (super.deldret) {
            return this.TV.fill[this.ActualitzaRect - 1].PosAnt_Fora();
        }
        return this.TV.fill[this.Actualitza].PosSeg_Fora();
    }
    
    public final BoxPosition getSelpos() {
        if (super.deldret) {
            return new BoxPosition(this.TV, this.ActualitzaRect);
        }
        return new BoxPosition(this.TV, this.Actualitza);
    }
    
    public final Rectangles getRectangles(final BoxComponent boxComponent) {
        final Rectangles rectangles = new Rectangles();
        rectangles.Afegeix(this.TV.getRectanglesSeleccio(this.Actualitza, this.ActualitzaRect));
        return rectangles;
    }
    
    public final void script(final BoxScripting boxScripting) {
        this.TV.script(boxScripting, this.Actualitza, this.ActualitzaRect);
    }
    
    public final BoxPosition suprimeix(final BoxComponent boxComponent) {
        this.TV.Treure(this.Actualitza, this.ActualitzaRect, boxComponent);
        if (this.TV.nfills == 0) {
            this.TV.inicialitzaFills();
        }
        this.TV.Actualitza(boxComponent, 0);
        super.esValida = false;
        return this.TV.fill[Math.max(0, this.Actualitza - 1)].PosAnt_Fora();
    }
    
    public final AbstractBox getMama() {
        return this.TV;
    }
    
    public final boolean espotsubstituir() {
        return false;
    }
    
    public final AbstractSelection canviaAtribut(final BoxComponent boxComponent, final Attributes attributes) {
        for (int i = this.Actualitza; i < this.ActualitzaRect; ++i) {
            this.TV.fill[i].posaAtribut(boxComponent, attributes);
        }
        this.TV.ActualitzaRect(boxComponent);
        return this;
    }
}
