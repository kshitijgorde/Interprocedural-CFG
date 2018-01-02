import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class InterfaceBox extends AbstractBox
{
    static Class PosicioMesProperaGlobal;
    
    public final void inicialitzaFills() {
        final TokensVBox tokensVBox = new TokensVBox();
        tokensVBox.setDefaultChildClass((InterfaceBox.PosicioMesProperaGlobal == null) ? (InterfaceBox.PosicioMesProperaGlobal = I("CapsaComandes")) : InterfaceBox.PosicioMesProperaGlobal);
        tokensVBox.inicialitzaFills();
        this.AfegirNoCalc(tokensVBox);
    }
    
    public final void enAfegir(final BoxComponent boxComponent, final AbstractBox abstractBox, final int n) {
        if (abstractBox instanceof TokensVBox) {
            final TokensVBox tokensVBox = (TokensVBox)abstractBox;
            tokensVBox.setDefaultChildClass((InterfaceBox.PosicioMesProperaGlobal == null) ? (InterfaceBox.PosicioMesProperaGlobal = I("CapsaComandes")) : InterfaceBox.PosicioMesProperaGlobal);
            tokensVBox.setType(0);
            tokensVBox.interSpace = new Length("0.2em");
            tokensVBox.paddingLeft = new Length("0.1em");
            tokensVBox.paddingRight = new Length("0.1em");
            if (boxComponent != null) {
                ((FormulaEditorCalc)boxComponent).firePropertyChange("groupAdded", Boolean.TRUE, new Integer(n));
            }
        }
    }
    
    public final int nombreMinimDeFills() {
        return 1;
    }
    
    public final void enCalculRect() {
        super.height = super.fill[0].height;
        super.fill[0].x = 0;
        super.fill[0].y = 0;
        super.width = super.fill[0].width;
        super.baseline = super.fill[0].baseline;
    }
    
    public final Length getRecommendedWidth(final int n, final BoxComponent boxComponent) {
        return super.recommendedWidth;
    }
    
    public final BoxPosition PosicioMesPropera(final Point point, final AbstractBox[] array) {
        return super.fill[0].PosicioMesProperaGlobal(point, array);
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 0) {
            return "interface";
        }
        if (boxScripting.scriptMode == 1) {
            return "session";
        }
        return null;
    }
    
    public final void attributes(final BoxScripting boxScripting) {
        boxScripting.appendAttribute("version", "2.0");
        boxScripting.appendAttribute("lang", "en");
        super.attributes(boxScripting);
    }
    
    public final int getBlockCount() {
        return super.fill[0].nfills;
    }
    
    public final AbstractBox getBlock(final int n) {
        return super.fill[0].fill[n];
    }
    
    static final Class I(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
