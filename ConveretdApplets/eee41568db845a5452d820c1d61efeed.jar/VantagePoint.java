// 
// Decompiled by Procyon v0.5.30
// 

class VantagePoint
{
    public VantagePoint m_vantagepointNext;
    double m_dXmin;
    double m_dYmin;
    double m_dXmax;
    double m_dYmax;
    cgVector m_vectorCamera;
    cgVector m_vectorTarget;
    
    public VantagePoint(final String s) throws ParsingException, NumberFormatException, StringIndexOutOfBoundsException {
        final double[] numericArgs = Parse.NumericArgs(s);
        if (numericArgs.length != 9) {
            throw new ParsingException("Vantage Point requires 9 parameters");
        }
        this.m_dXmin = numericArgs[0];
        this.m_dYmin = numericArgs[1];
        this.m_dXmax = numericArgs[2];
        this.m_dYmax = numericArgs[3];
        this.m_vectorCamera = new cgVector(numericArgs[4], numericArgs[5], numericArgs[6]);
        this.m_vectorTarget = new cgVector(numericArgs[7], numericArgs[8], 0.0);
    }
    
    public void set(final cgViewpoint cgViewpoint, final Golfball3D golfball3D) {
        if (this.m_vantagepointNext == null || (golfball3D.m_dX0 >= this.m_dXmin && golfball3D.m_dX0 <= this.m_dXmax && golfball3D.m_dY0 >= this.m_dYmin && golfball3D.m_dY0 <= this.m_dYmax)) {
            cgViewpoint.m_vector.m_dX = this.m_vectorCamera.m_dX;
            cgViewpoint.m_vector.m_dY = this.m_vectorCamera.m_dY;
            cgViewpoint.m_vector.m_dZ = this.m_vectorCamera.m_dZ;
            cgViewpoint.lookAt(this.m_vectorTarget.m_dX, this.m_vectorTarget.m_dY, this.m_vectorTarget.m_dZ);
            return;
        }
        this.m_vantagepointNext.set(cgViewpoint, golfball3D);
    }
}
