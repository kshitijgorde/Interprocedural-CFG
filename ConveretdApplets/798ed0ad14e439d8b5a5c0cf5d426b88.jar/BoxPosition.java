import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class BoxPosition
{
    public AbstractBox c;
    public int x;
    
    public BoxPosition(final AbstractBox c, final int x) {
        this.c = c;
        this.x = x;
    }
    
    public BoxPosition(final BoxPosition boxPosition) {
        this.c = boxPosition.c;
        this.x = boxPosition.x;
    }
    
    public final BoxPosition copy(BoxPosition boxPosition) {
        if (boxPosition == null) {
            boxPosition = new BoxPosition(this);
        }
        else {
            boxPosition.c = this.c;
            boxPosition.x = this.x;
        }
        return boxPosition;
    }
    
    public final boolean equals(final Object o) {
        return this.equals((BoxPosition)o);
    }
    
    public final boolean equals(final BoxPosition boxPosition) {
        return boxPosition != null && this.c == boxPosition.c && this.x == boxPosition.x;
    }
    
    public final AbstractBox BuscaPareTipus(final Class clazz) {
        AbstractBox abstractBox;
        for (abstractBox = this.c; abstractBox != null && !clazz.isInstance(abstractBox); abstractBox = abstractBox.getParentBox()) {}
        return abstractBox;
    }
    
    public final BoxPosition BuscaPosicioTipus(final Class clazz) {
        final BoxPosition boxPosition = new BoxPosition(this);
        while (boxPosition.c != null && !clazz.isInstance(boxPosition.c)) {
            boxPosition.posicioPare();
        }
        return boxPosition;
    }
    
    public final void posicioPare() {
        this.x = this.c.p_en_pare;
        this.c = this.c.getParentBox();
    }
    
    public final BoxPosition BuscaTokensVTipus(final Class clazz) {
        final BoxPosition boxPosition = new BoxPosition(this);
        while (boxPosition.c.getParentBox() != null && (!clazz.isInstance(boxPosition.c.getParentBox()) || !(boxPosition.c instanceof TokensVBox))) {
            boxPosition.x = boxPosition.c.p_en_pare;
            boxPosition.c = boxPosition.c.getParentBox();
        }
        return boxPosition;
    }
    
    public final BoxPosition searchTokensVPosition(final Class clazz) {
        final BoxPosition boxPosition = new BoxPosition(this);
        while (boxPosition.c != null && boxPosition.c.getParentBox() != null) {
            if (clazz.isInstance(boxPosition.c.getParentBox()) && boxPosition.c instanceof TokensVBox) {
                return boxPosition;
            }
            boxPosition.posicioPare();
        }
        return null;
    }
    
    public final BoxPosition searchPosition(final Class clazz) {
        final BoxPosition boxPosition = new BoxPosition(this);
        while (boxPosition.c != null) {
            if (clazz.isInstance(boxPosition.c)) {
                return boxPosition;
            }
            boxPosition.posicioPare();
        }
        return null;
    }
    
    public final int[] coordinates(final AbstractBox abstractBox) {
        final int[] array = new int[100];
        array[0] = this.x;
        AbstractBox abstractBox2 = this.c;
        int n = 1;
        while (abstractBox2 != abstractBox) {
            if (abstractBox2 == null) {
                return null;
            }
            array[n++] = abstractBox2.p_en_pare;
            abstractBox2 = abstractBox2.getParentBox();
            if (abstractBox2 == null) {
                return null;
            }
        }
        final int n2 = n;
        final int[] array2 = new int[n2];
        for (int i = 0; i < n2; ++i) {
            array2[i] = array[n2 - i - 1];
        }
        return array2;
    }
    
    public BoxPosition(AbstractBox c, final int[] array) {
        for (int i = 0; i < array.length - 1; ++i) {
            c = c.fill[array[i]];
        }
        this.c = c;
        this.x = array[array.length - 1];
    }
    
    public final int compareTo(final Object o) {
        final BoxPosition boxPosition = (BoxPosition)o;
        if (boxPosition.c == this.c) {
            return this.x - boxPosition.x;
        }
        final BoxPosition boxPosition2 = new BoxPosition((BoxPosition)o);
        final BoxPosition boxPosition3 = new BoxPosition(this);
        final int depth = boxPosition3.c.getDepth();
        final int depth2 = boxPosition2.c.getDepth();
        int n = depth - depth2;
        BoxPosition boxPosition4;
        if (n > 0) {
            boxPosition4 = boxPosition3;
        }
        else {
            boxPosition4 = boxPosition2;
            n = -n;
        }
        for (int i = 0; i < n; ++i) {
            boxPosition4.posicioPare();
        }
        while (boxPosition3.c != boxPosition2.c) {
            boxPosition3.posicioPare();
            boxPosition2.posicioPare();
        }
        final int n2 = boxPosition3.x - boxPosition2.x;
        if (n2 == 0) {
            return depth - depth2;
        }
        return n2;
    }
    
    public static final AbstractBox commonAncestor(final BoxPosition boxPosition, final BoxPosition boxPosition2, Vector vector, final Vector vector2) {
        if (vector == null) {
            vector = new Vector<AbstractBox>();
        }
        for (AbstractBox abstractBox = boxPosition.c; abstractBox != null; abstractBox = abstractBox.getParentBox()) {
            vector.addElement(abstractBox);
        }
        AbstractBox abstractBox2;
        for (abstractBox2 = boxPosition2.c; abstractBox2 != null; abstractBox2 = abstractBox2.getParentBox()) {
            if (vector2 != null) {
                vector2.addElement(abstractBox2);
            }
            if (vector.indexOf(abstractBox2) != -1) {
                break;
            }
        }
        return abstractBox2;
    }
}
