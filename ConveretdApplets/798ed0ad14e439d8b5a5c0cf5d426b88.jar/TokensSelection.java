import java.awt.Rectangle;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class TokensSelection extends AbstractSelection
{
    private TokensBox box;
    private int inici;
    private int fi;
    
    public TokensSelection(final TokensBox box, final Stack stack, final Stack stack2, final boolean deldret) {
        this.box = box;
        super.deldret = deldret;
        this.inici = stack.pop();
        this.fi = stack2.pop();
        if (!stack.empty()) {
            super.fille = this.box.fill[this.inici].SeleccionaDreta(stack);
            if (super.fille != null && super.fille.buida()) {
                super.fille = null;
            }
            if (super.fille != null) {
                ++this.inici;
            }
        }
        if (!stack2.empty()) {
            super.filld = this.box.fill[this.fi].SeleccionaEsquerra(stack2);
            if (super.filld != null && super.filld.buida()) {
                super.filld = null;
            }
            if (super.filld == null) {
                ++this.fi;
            }
        }
        if (this.inici > this.fi) {
            this.inici = this.fi;
        }
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
        if (this.inici >= this.fi) {
            return true;
        }
        return false;
    }
    
    public final boolean espotborrardirectament(final BoxComponent boxComponent) {
        return (super.fille == null || super.fille.espotborrardirectament()) && (super.filld == null || super.filld.espotborrardirectament()) && (this.inici >= this.fi || (this.fi - this.inici == 1 && boxComponent instanceof FormulaEditor && ((FormulaEditor)boxComponent).espotborrardirectament(this.box.fill[this.inici])));
    }
    
    public final boolean espotsubstituir() {
        return true;
    }
    
    public final BoxPosition getCurpos() {
        if (super.deldret) {
            if (super.filld != null) {
                return super.filld.getCurpos();
            }
            return new BoxPosition(this.box, this.fi);
        }
        else {
            if (super.fille != null) {
                return super.fille.getCurpos();
            }
            return new BoxPosition(this.box, this.inici);
        }
    }
    
    public final Rectangles getRectangles(final BoxComponent boxComponent) {
        int lineIndex = this.box.getLineIndex(this.inici);
        final Rectangles rectangles = new Rectangles();
        Rectangle rectangle = null;
        if (lineIndex != -1) {
            TokensBox$Line tokensBox$Line = this.box.lines.elementAt(lineIndex);
            if (super.fille != null) {
                if (super.fille instanceof TextSelection && super.fille.getMama().p_en_pare >= tokensBox$Line.firstIndex) {
                    rectangle = super.fille.getRectangles(boxComponent).rectangleAt(0);
                }
                else {
                    rectangles.Afegeix(super.fille.getRectangles(boxComponent));
                }
            }
            for (int i = this.inici; i < this.fi; ++i) {
                if (tokensBox$Line.lastIndex == i - 1) {
                    rectangles.Afegeix(rectangle);
                    rectangle = null;
                    ++lineIndex;
                    tokensBox$Line = this.box.lines.elementAt(lineIndex);
                }
                rectangle = Utils.unio(rectangle, this.box.fill[i].RectangleReal());
            }
            if (super.filld != null) {
                if (super.filld instanceof TextSelection && super.filld.getMama().p_en_pare <= tokensBox$Line.lastIndex) {
                    rectangle = Utils.unio(rectangle, super.filld.getRectangles(boxComponent).rectangleAt(0));
                }
                else {
                    rectangles.Afegeix(super.filld.getRectangles(boxComponent));
                }
            }
            if (rectangle != null) {
                rectangles.Afegeix(rectangle);
            }
        }
        else {
            if (super.fille != null) {
                rectangles.Afegeix(super.fille.getRectangles(boxComponent));
            }
            if (super.filld != null) {
                rectangles.Afegeix(super.filld.getRectangles(boxComponent));
            }
        }
        return rectangles;
    }
    
    public final void script(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            boxScripting.openTag("mrow");
        }
        if (super.fille != null) {
            super.fille.script(boxScripting);
        }
        this.box.script(boxScripting, this.inici, this.fi);
        if (super.filld != null) {
            super.filld.script(boxScripting);
        }
        if (boxScripting.scriptMode == 1) {
            boxScripting.closeTag();
        }
    }
    
    public final AbstractSelection canviaAtribut(final BoxComponent boxComponent, final Attributes attributes) {
        final BoxScripting boxScripting = boxComponent.getBoxScripting();
        if (super.fille != null) {
            super.fille.script(boxScripting);
        }
        this.box.onScript(boxScripting, this.inici, this.fi);
        if (super.filld != null) {
            super.filld.script(boxScripting);
        }
        final AbstractBox parse = boxComponent.parse("<mo>beginselection</mo>" + boxScripting.toString() + "<mo>endselection</mo>");
        parse.posaAtribut(boxComponent, attributes);
        final BoxPosition suprimeix = this.suprimeix(boxComponent);
        final AbstractBox parentBox = suprimeix.c.getParentBox();
        if (boxComponent instanceof FormulaEditor) {
            final FormulaEditor formulaEditor = (FormulaEditor)boxComponent;
            formulaEditor.InsertString(formulaEditor, suprimeix, parse);
        }
        return AbstractSelection.I(boxComponent, parentBox);
    }
    
    public final BoxPosition suprimeix(final BoxComponent boxComponent) {
        if (super.fille != null) {
            super.fille.esborra(boxComponent);
        }
        if (super.filld != null) {
            super.filld.suprimeix(boxComponent);
        }
        String string = "";
        final boolean b = this.inici > 0 && this.box.fill[this.inici - 1] instanceof TextBox;
        final boolean b2 = this.fi < this.box.nfills && (this.box.fill[this.fi] instanceof TextBox || this.box.fill[this.fi] instanceof EmptyBox);
        if (b) {
            string += boxComponent.script(this.box.fill[this.inici - 1]);
        }
        String s = string + "<mo>caret</mo>";
        if (b2) {
            s += boxComponent.script(this.box.fill[this.fi]);
        }
        this.box.Treure(this.inici, this.fi, boxComponent);
        if (b) {
            --this.inici;
            this.box.Treure(this.inici, boxComponent);
        }
        if (b2) {
            this.box.Treure(this.inici, boxComponent);
        }
        super.esValida = false;
        return this.box.InsertStringAndFindCaret(boxComponent, this.inici, s);
    }
    
    public final AbstractBox getMama() {
        return this.box;
    }
    
    public final AbstractBox getContents() {
        if (this.inici + 1 == this.fi && super.fille == null && super.filld == null) {
            return this.box.fill[this.inici];
        }
        return null;
    }
}
