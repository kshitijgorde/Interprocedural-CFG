import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Tee3D
{
    final Color TEE_COLOR;
    public final double m_dTeeSide = 0.75;
    public double m_dX0;
    public double m_dY0;
    public double m_dZ;
    static cgModel m_model;
    public cgObject m_object;
    
    public double getXcoord() {
        return this.m_dX0 + 0.375;
    }
    
    public double getYcoord() {
        return this.m_dY0 + 0.375;
    }
    
    public Tee3D(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        this.TEE_COLOR = new Color(6736998);
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length < 2) {
            throw new ParsingException("Tee requires at least 2 arguments");
        }
        if (numericArgs.length > 3) {
            throw new ParsingException("Tee accepts no more than 3 arguments");
        }
        this.m_dX0 = numericArgs[0] - 0.375;
        this.m_dY0 = numericArgs[1] - 0.375;
        if (numericArgs.length > 2) {
            this.m_dZ = numericArgs[2];
        }
        else {
            this.m_dZ = 0.0;
        }
        if (Tee3D.m_model == null) {
            (Tee3D.m_model = new cgModel(4, 1)).specifyVertex(0, new cgVector(-0.375, -0.375, 0.0));
            Tee3D.m_model.specifyVertex(1, new cgVector(0.375, -0.375, 0.0));
            Tee3D.m_model.specifyVertex(2, new cgVector(0.375, 0.375, 0.0));
            Tee3D.m_model.specifyVertex(3, new cgVector(-0.375, 0.375, 0.0));
            Tee3D.m_model.specifyFace(0, new cgFace(4, new int[] { 3, 2, 1, 0 }, this.TEE_COLOR, null));
        }
        this.m_object = new cgObject(Tee3D.m_model);
        this.m_object.m_vector.m_dX = numericArgs[0];
        this.m_object.m_vector.m_dY = numericArgs[1];
        this.m_object.m_vector.m_dZ = this.m_dZ;
        this.m_object.transform();
    }
}
