import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Auxiliary
{
    final Color AUXILIARY_COLOR;
    public Auxiliary m_auxiliaryNext;
    cgModel m_model;
    public cgObject m_object;
    
    public Auxiliary(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        this.AUXILIARY_COLOR = new Color(16777062);
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length < 9) {
            throw new ParsingException("Auxiliary needs at least 3 vertices");
        }
        if (numericArgs.length % 3 > 1) {
            throw new ParsingException("Invalid number of arguments");
        }
        final int n = numericArgs.length / 3;
        final int[] array = new int[n];
        int n2 = -1;
        if (numericArgs.length % 3 == 1) {
            n2 = (int)numericArgs[numericArgs.length - 1];
            if (n2 < 0 || n2 >= n) {
                throw new ParsingException("Invalid sort-distance vertex index");
            }
        }
        this.m_model = new cgModel(n, 1);
        for (int i = 0; i < n; ++i) {
            this.m_model.specifyVertex(i, new cgVector(numericArgs[i * 3], numericArgs[i * 3 + 1], numericArgs[i * 3 + 2]));
            array[i] = i;
        }
        if (n2 >= 0) {
            this.m_model.specifyFace(0, new cgFace(n, array, this.AUXILIARY_COLOR, null, n2));
        }
        else {
            this.m_model.specifyFace(0, new cgFace(n, array, this.AUXILIARY_COLOR, null));
        }
        (this.m_object = new cgObject(this.m_model)).transform();
    }
}
