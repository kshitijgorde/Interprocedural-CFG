import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpecialSymbolBox extends AbstractBox
{
    public String nom;
    public String value;
    
    public SpecialSymbolBox(final String s, final String value) {
        this(s);
        this.value = value;
    }
    
    public SpecialSymbolBox(String nom) {
        if (nom.equals("\\espai")) {
            nom = "\\space";
        }
        if (nom.equals("\\SIamper")) {
            nom = "\\SIampere";
        }
        if (nom.equals("\\SImol")) {
            nom = "\\SImole";
        }
        this.nom = nom;
        super.cadena_omega = Especials.simbols2wiris.get(nom);
        super.color = 3;
        super.dibs = new AbstractNeuterBox[] { NeuterBox.crea(nom) };
        super.ndibs = super.dibs.length;
    }
    
    public final void enCalculRect() {
        super.width = super.dibs[0].width;
        super.height = super.dibs[0].height;
        super.baseline = super.dibs[0].baseline;
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        final Point posicioReal = this.PosicioReal();
        if (new Point(point.x - posicioReal.x, point.y - posicioReal.y).x < super.width / 2) {
            return this.getParentBox().PosAnt_Fill(super.p_en_pare, array);
        }
        return this.getParentBox().PosSeg_Fill(super.p_en_pare, array);
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return this.nom;
        }
        if (boxScripting.scriptMode != 1) {
            return null;
        }
        if (this.isStyle(128)) {
            return null;
        }
        return MathML2BoxCalc.specialTag(this.nom);
    }
    
    public final void onScript(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1) {
            if (this.isStyle(128) && this.nom.equals("\\space")) {
                boxScripting.appendText(" ");
            }
            else {
                MathML2BoxCalc.special(boxScripting, this.nom);
            }
        }
        else if (boxScripting.scriptMode == 2) {
            boxScripting.appendText(super.cadena_omega);
        }
        else if (boxScripting.scriptMode == -1) {
            final String s = NeuterBox.name2string.get(this.nom);
            if (s != null) {
                boxScripting.appendText(s);
            }
            else {
                boxScripting.appendText(this.nom);
            }
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 800;
    }
    
    public static final void scriptWirisQuotedExpression(final BoxScripting boxScripting, final AbstractBox abstractBox, final AbstractBox[] array, int i, final int n) {
        while (i < n) {
            boolean b = false;
            if (array[i] instanceof TextBox || array[i] instanceof SpaceBox) {
                array[i].script(boxScripting);
                b = true;
            }
            else if (array[i] instanceof SpecialSymbolBox) {
                final String value = ((SpecialSymbolBox)array[i]).value;
                b = true;
                if (value == null) {
                    b = false;
                }
                else if (value.length() == 1) {
                    final char char1 = value.charAt(0);
                    if (char1 == '-' || char1 == '*' || char1 == '+' || char1 == '=') {
                        b = false;
                    }
                    else if (char1 > '\u0080') {
                        b = false;
                    }
                }
                if (b) {
                    array[i].script(boxScripting);
                }
            }
            if (!b) {
                final BoxScripting boxScripting2 = new BoxScripting(1);
                boxScripting.appendText("<math>");
                boxScripting2.beginInheritFormat(abstractBox);
                array[i].script(boxScripting2);
                boxScripting2.endInheritFormat(abstractBox);
                boxScripting.appendText(Utils.substitute(boxScripting2.toString(), '\"', "'"));
                boxScripting.appendText("</math>");
            }
            ++i;
        }
    }
    
    public static final boolean isQuote(final AbstractBox abstractBox) {
        return abstractBox instanceof SpecialSymbolBox && ((SpecialSymbolBox)abstractBox).nom.equals("\\quote");
    }
    
    public static final boolean containsQuote(final AbstractBox abstractBox) {
        if (isQuote(abstractBox)) {
            return true;
        }
        for (int i = 0; i < abstractBox.nfills; ++i) {
            if (containsQuote(abstractBox.fill[i])) {
                return true;
            }
        }
        return false;
    }
}
