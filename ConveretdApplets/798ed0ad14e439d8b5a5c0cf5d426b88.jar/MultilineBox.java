import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public class MultilineBox extends TokensVBox
{
    public MultilineBox() {
        super.Z = true;
        this.setBaselineType(null);
        super.showEmptyChildren = false;
    }
    
    public final BoxPosition UneixLinees(final int n, final BoxComponent boxComponent) {
        if (super.nfills == 0) {
            this.Afegir(new EmptyBox(), boxComponent);
        }
        if (n < 0) {
            return this.PosSeg_Fora();
        }
        if (n + 1 >= super.nfills) {
            return this.PosAnt_Fora();
        }
        final AbstractBox parse = boxComponent.parse(boxComponent.script(super.fill[n]) + "<mo>caret</mo>" + boxComponent.script(super.fill[n + 1]));
        this.Treure(n, n + 1, boxComponent);
        this.SubstitueixCapsa(parse, n, boxComponent);
        return this.findAndRemoveMarkup(boxComponent, "\\caret");
    }
    
    public final AbstractSelection Selecciona(final Stack stack, final Stack stack2, final boolean b) {
        return new MultilineSelection(this, stack, stack2, b);
    }
    
    public final int getType() {
        if (this.isStyle(128)) {
            return 10;
        }
        if (super.nfills <= 1) {
            return 0;
        }
        return 1;
    }
    
    public final String scriptCommand(final BoxScripting boxScripting) {
        if (boxScripting.scriptMode == 1 && this.getType() == 10) {
            return "div";
        }
        return super.scriptCommand(boxScripting);
    }
    
    public final void onScript(final BoxScripting boxScripting, final int n, final int n2) {
        if (boxScripting.scriptMode == 1) {
            if (this.getType() != 1) {
                XMLBoxUtils.onScript(boxScripting, super.fill, n, n2, false);
            }
            else {
                super.onScript(boxScripting, n, n2);
            }
        }
        else if (boxScripting.scriptMode == 0) {
            boxScripting.appendScript("\\beginmultiline ");
            for (int i = n; i < n2; ++i) {
                if (i > n) {
                    boxScripting.appendScript("\\cr ");
                }
                super.fill[i].script(boxScripting);
            }
            boxScripting.appendScript("\\endmultiline ");
        }
        else if (boxScripting.scriptMode == -1 || boxScripting.scriptMode == 2) {
            super.onScript(boxScripting, n, n2);
        }
    }
    
    public final void onScript(final BoxScripting boxScripting, final boolean b, final AbstractSelection abstractSelection, final AbstractSelection abstractSelection2, final int n, final int n2) {
        boolean b2 = true;
        if (boxScripting.scriptMode == 2) {
            super.onScript(boxScripting, n, n2);
        }
        else {
            this.beginEndScript(true, boxScripting, b);
            if (abstractSelection != null) {
                this.scriptCell(boxScripting, abstractSelection, b, b2);
                b2 = false;
            }
            for (int i = n; i < n2; ++i) {
                this.scriptCell(boxScripting, super.fill[i], b, b2);
                b2 = false;
            }
            if (abstractSelection2 != null) {
                this.scriptCell(boxScripting, abstractSelection2, b, b2);
            }
            this.beginEndScript(false, boxScripting, b);
        }
    }
    
    private final void beginEndScript(final boolean b, final BoxScripting boxScripting, final boolean b2) {
        if (boxScripting.scriptMode == 0) {
            if (b) {
                boxScripting.appendScript("\\beginmultiline ");
            }
            else {
                boxScripting.appendScript("\\endmultiline ");
            }
        }
        if (boxScripting.scriptMode == 1 && b2) {
            if (b) {
                boxScripting.beginBox(this, "mtable");
            }
            else {
                boxScripting.endBox(this, "mtable");
            }
        }
    }
    
    private final void scriptCell(final BoxScripting boxScripting, final Object o, final boolean b, final boolean b2) {
        if (boxScripting.scriptMode == 1 && b) {
            boxScripting.openTag("mtr");
            boxScripting.openTag("mtd");
        }
        if (boxScripting.scriptMode == 0 && !b2) {
            boxScripting.appendScript("\\cr ");
        }
        if (o instanceof AbstractBox) {
            ((AbstractBox)o).script(boxScripting);
        }
        if (o instanceof AbstractSelection) {
            ((AbstractSelection)o).script(boxScripting);
        }
        if (boxScripting.scriptMode == 1 && b) {
            boxScripting.closeTag();
            boxScripting.closeTag();
        }
    }
    
    public final int getSplitFactor(final int n) {
        return 300;
    }
    
    public final boolean isSelectable(final int n) {
        return false;
    }
}
