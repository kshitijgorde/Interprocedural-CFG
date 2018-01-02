// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.investment.calculators.compound;

import blackmagic.finance.investment.calculators.foundations.CalculatorApplet;

public class CompounderApplet extends CalculatorApplet
{
    static /* synthetic */ Class class$blackmagic$finance$investment$calculators$compound$CompounderFactory;
    
    public String getAppletInfo() {
        return "A compound interest calculator.";
    }
    
    protected String getButtonString() {
        return "Click for new Compounder";
    }
    
    protected Class getCalculatorClass() {
        return (CompounderApplet.class$blackmagic$finance$investment$calculators$compound$CompounderFactory == null) ? (CompounderApplet.class$blackmagic$finance$investment$calculators$compound$CompounderFactory = class$("blackmagic.finance.investment.calculators.compound.CompounderFactory")) : CompounderApplet.class$blackmagic$finance$investment$calculators$compound$CompounderFactory;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
}
