import java.util.Vector;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class AbstractSelection
{
    public boolean deldret;
    public boolean esValida;
    public AbstractSelection fille;
    public AbstractSelection filld;
    public AbstractSelection excludeSel;
    
    public AbstractSelection() {
        this.esValida = true;
        this.excludeSel = null;
    }
    
    public abstract boolean buida();
    
    public boolean espotborrardirectament(final BoxComponent boxComponent) {
        return this.espotborrardirectament();
    }
    
    protected boolean espotborrardirectament() {
        return false;
    }
    
    public boolean espotsubstituir() {
        return false;
    }
    
    public final boolean espotcopiar() {
        return !this.buida();
    }
    
    public AbstractBox getContents() {
        return null;
    }
    
    public abstract BoxPosition getCurpos();
    
    public BoxPosition getSelpos() {
        return this.getCurpos();
    }
    
    public abstract Rectangles getRectangles(final BoxComponent p0);
    
    public abstract void script(final BoxScripting p0);
    
    public abstract BoxPosition suprimeix(final BoxComponent p0);
    
    public AbstractSelection canviaAtribut(final BoxComponent boxComponent, final Attributes attributes) {
        return this;
    }
    
    public void esborra(final BoxComponent boxComponent) {
        this.suprimeix(boxComponent);
    }
    
    public abstract AbstractBox getMama();
    
    public static final AbstractSelection Selecciona(BoxPosition boxPosition, BoxPosition boxPosition2, final BoxComponent boxComponent) {
        if (boxPosition.equals(boxPosition2)) {
            return null;
        }
        final Stack<Integer> stack = new Stack<Integer>();
        final Stack<Integer> stack2 = new Stack<Integer>();
        AbstractBox abstractBox = BoxPosition.commonAncestor(boxPosition, boxPosition2, null, null);
        if (abstractBox == null) {
            return null;
        }
        final boolean b = boxPosition.compareTo(boxPosition2) < 0;
        if (!b) {
            final BoxPosition boxPosition3 = boxPosition;
            boxPosition = boxPosition2;
            boxPosition2 = boxPosition3;
        }
        stack.push(new Integer(boxPosition.x));
        for (AbstractBox abstractBox2 = boxPosition.c; abstractBox2 != abstractBox; abstractBox2 = abstractBox2.getParentBox()) {
            stack.push(new Integer(abstractBox2.p_en_pare));
        }
        stack2.push(new Integer(boxPosition2.x));
        for (AbstractBox abstractBox3 = boxPosition2.c; abstractBox3 != abstractBox; abstractBox3 = abstractBox3.getParentBox()) {
            stack2.push(new Integer(abstractBox3.p_en_pare));
        }
        AbstractSelection abstractSelection;
        for (abstractSelection = abstractBox.Selecciona(stack, stack2, b); abstractSelection == null && abstractBox.getParentBox() != null; abstractSelection = abstractBox.getParentBox().Selecciona(stack, stack2, b), abstractBox = abstractBox.getParentBox()) {
            stack.removeAllElements();
            stack2.removeAllElements();
            stack.push(new Integer(abstractBox.p_en_pare));
            stack2.push(new Integer(abstractBox.p_en_pare + 1));
        }
        return abstractSelection;
    }
    
    static final AbstractSelection I(final BoxComponent boxComponent, final AbstractBox abstractBox) {
        final String[] array = { "\\beginselection", "\\endselection" };
        final BoxPosition[] array2 = new BoxPosition[2];
        abstractBox.findAndRemoveMarkups(boxComponent, array, array2);
        if (array2[0] == null || array2[1] == null) {
            return null;
        }
        return Selecciona(array2[0], array2[1], boxComponent);
    }
}
