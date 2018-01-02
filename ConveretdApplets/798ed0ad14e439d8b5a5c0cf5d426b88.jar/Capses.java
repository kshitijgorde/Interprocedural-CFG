// 
// Decompiled by Procyon v0.5.30
// 

public class Capses
{
    public static final AbstractBox id2Capsa(final int n) {
        switch (n) {
            case 0: {
                return new TextBox();
            }
            case 1: {
                new TextBox().inicialitzaModalitat("ident");
                break;
            }
            case 2: {
                return new ArgumentBox();
            }
            case 3: {
                return new FractionBox();
            }
            case 4: {
                return new CapsaObjectAndDomain();
            }
            case 5: {
                return new CapsaDiv();
            }
            case 6: {
                return new CapsaLimit();
            }
        }
        return null;
    }
}
