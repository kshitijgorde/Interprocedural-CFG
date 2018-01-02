import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class TextSelection extends AbstractSelection
{
    TextBox ChangeByString;
    int Composa;
    int InsertString;
    
    public TextSelection(final TextBox changeByString, final Stack stack, final Stack stack2, final boolean deldret) {
        this.ChangeByString = changeByString;
        super.deldret = deldret;
        this.Composa = stack.pop();
        this.InsertString = stack2.pop();
    }
    
    public final boolean buida() {
        return this.Composa >= this.InsertString;
    }
    
    public final boolean espotborrardirectament() {
        return true;
    }
    
    public final boolean espotsubstituir() {
        return true;
    }
    
    public final BoxPosition getCurpos() {
        if (super.deldret) {
            return new BoxPosition(this.ChangeByString, this.InsertString);
        }
        return new BoxPosition(this.ChangeByString, this.Composa);
    }
    
    public final Rectangles getRectangles(final BoxComponent boxComponent) {
        return new Rectangles(this.ChangeByString.getRectangleSeleccio(this.Composa, this.InsertString, boxComponent));
    }
    
    public final void script(final BoxScripting boxScripting) {
        this.ChangeByString.script(boxScripting, this.Composa, this.InsertString);
    }
    
    public final BoxPosition suprimeix(final BoxComponent boxComponent) {
        final String string = BoxScripting.left(boxComponent, this.ChangeByString, this.Composa) + "<mo>caret</mo>" + BoxScripting.right(boxComponent, this.ChangeByString, this.InsertString);
        super.esValida = false;
        if (this.ChangeByString.hasBeginEndPositions()) {
            this.ChangeByString.cadena = this.ChangeByString.cadena.substring(0, this.Composa) + this.ChangeByString.cadena.substring(this.InsertString);
            this.ChangeByString.update(boxComponent);
            return new BoxPosition(this.ChangeByString, this.Composa);
        }
        if (this.ChangeByString.getParentBox() instanceof TokensBox) {
            return ((TokensBox)this.ChangeByString.getParentBox()).ChangeByString(boxComponent, this.ChangeByString.p_en_pare, string);
        }
        return new BoxPosition(this.ChangeByString, this.Composa);
    }
    
    public final AbstractSelection canviaAtribut(final BoxComponent boxComponent, final Attributes attributes) {
        final TokensBox tokensBox = (TokensBox)this.ChangeByString.getParentBox();
        super.esValida = false;
        final BoxScripting boxScripting = boxComponent.getBoxScripting();
        boxScripting.appendScript("<mo>beginselection</mo>");
        this.ChangeByString.script(boxScripting, this.Composa, this.InsertString);
        boxScripting.appendScript("<mo>endselection</mo>");
        final AbstractBox parse = boxComponent.parse(boxScripting.toString());
        parse.posaAtribut(boxComponent, attributes);
        final BoxPosition suprimeix = this.suprimeix(boxComponent);
        if (boxComponent instanceof FormulaEditor) {
            final FormulaEditor formulaEditor = (FormulaEditor)boxComponent;
            formulaEditor.InsertString(formulaEditor, suprimeix, parse);
        }
        return AbstractSelection.I(boxComponent, tokensBox);
    }
    
    public final void esborra(final BoxComponent boxComponent) {
        this.ChangeByString.cadena = this.ChangeByString.cadena.substring(0, this.Composa) + this.ChangeByString.cadena.substring(this.InsertString);
        final int type = this.ChangeByString.type;
        final TextBox changeByString = this.ChangeByString;
        if (type == 1 && TextBox.isNumber(this.ChangeByString.cadena)) {
            final TextBox changeByString2 = this.ChangeByString;
            final TextBox changeByString3 = this.ChangeByString;
            changeByString2.type = 2;
        }
        this.ChangeByString.Composa(boxComponent);
    }
    
    public final AbstractBox getMama() {
        return this.ChangeByString;
    }
}
