import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class ThreeDText
{
    final int SPIN = 90;
    final int TIP = -90;
    final int VX = 100;
    final int VY = 1;
    final int VZ = 1;
    final double RD = 0.017453277777777776;
    final int m_nStateZoomIn = 0;
    final int m_nStateStay = 1;
    final int m_nStateZoomOut = 2;
    int m_nState;
    int m_nHalfX;
    int m_nHalfY;
    int m_nOrigoX;
    int m_nOrigoY;
    ThreeDTextParams m_Params;
    boolean m_bTimeToDie;
    int m_nStayCounter;
    double m_c11;
    double m_c12;
    double m_c13;
    double m_c21;
    double m_c22;
    double m_c23;
    double m_c31;
    double m_c32;
    double m_c33;
    double m_size;
    double m_spin;
    double m_tip;
    int[][] m_vertice_projected;
    Math m_math;
    int m_nWidth;
    int m_nHeight;
    WireFrameString m_string;
    
    void RotateAndProject(final int[][] array, final int[][] array2, final int n) {
        for (int i = 0; i < n; ++i) {
            final double n2 = array[i][0];
            final double n3 = array[i][1];
            final double n4 = array[i][2];
            final double n5 = this.m_c11 * n2 + this.m_c12 * n3 + this.m_c13 * n4;
            final double n6 = this.m_c21 * n2 + this.m_c22 * n3 + this.m_c23 * n4;
            final double n7 = this.m_c31 * n2 + this.m_c32 * n3 + this.m_c33 * n4;
            final double n8 = (n6 - 1.0) / (n5 - 100.0);
            final double n9 = (n7 - 1.0) / (n5 - 100.0);
            array2[i][0] = (int)((n6 - n8 * n5) * this.m_size) + this.m_nOrigoX;
            array2[i][1] = (int)((n7 - n9 * n5) * this.m_size) + this.m_nOrigoY;
            array2[i][2] = (int)n7;
        }
    }
    
    void CalculateRotationMatrix() {
        final double n = this.m_spin * 0.017453277777777776;
        final double n2 = this.m_tip * 0.017453277777777776;
        this.m_c11 = Math.cos(n);
        this.m_c12 = 0.0;
        this.m_c13 = -Math.sin(n);
        this.m_c21 = -Math.sin(n2) * Math.sin(n);
        this.m_c22 = Math.cos(n2);
        this.m_c23 = -Math.sin(n2) * Math.cos(n);
        this.m_c31 = Math.cos(n2) * Math.sin(n);
        this.m_c32 = Math.sin(n2);
        this.m_c33 = Math.cos(n2) * Math.cos(n);
    }
    
    public void SetColor(final String s) {
        this.m_Params.m_Color = ThreeDTextParams.GetColorFromString(s);
    }
    
    public ThreeDText(final String s, final ThreeDTextParams params, final String s2, final int nWidth, final int nHeight) {
        this.m_spin = 90.0;
        this.m_tip = -90.0;
        this.m_nWidth = nWidth;
        this.m_nHeight = nHeight;
        this.m_nHalfX = this.m_nWidth / 2;
        this.m_nHalfY = this.m_nHeight / 2;
        this.m_nOrigoX = this.m_nHalfX;
        this.m_nOrigoY = this.m_nHalfY;
        this.m_size = 0.0;
        this.m_nState = 0;
        this.m_bTimeToDie = false;
        this.m_Params = params;
        this.m_string = new WireFrameString(s);
        this.m_vertice_projected = new int[this.m_string.GetMaxVerticeCount()][3];
    }
    
    void IncrementViewVariables() {
        switch (this.m_nState) {
            case 0: {
                this.m_size += this.m_Params.m_nZoomInStep;
                if (this.m_size > this.m_Params.m_nDestinationZ) {
                    this.m_nState = 1;
                    this.m_nStayCounter = 0;
                    break;
                }
                break;
            }
            case 1: {
                ++this.m_nStayCounter;
                if (this.m_nStayCounter == this.m_Params.m_nDelay) {
                    this.m_nState = 2;
                    break;
                }
                break;
            }
            case 2: {
                this.m_size += this.m_Params.m_nZoomOutStep;
                if (this.m_Params.m_nZoomOutStep < 0.0) {
                    if (this.m_size < this.m_Params.m_nTimeToDieZ) {
                        this.m_bTimeToDie = true;
                        break;
                    }
                    break;
                }
                else {
                    if (this.m_size > this.m_Params.m_nTimeToDieZ) {
                        this.m_bTimeToDie = true;
                        break;
                    }
                    break;
                }
                break;
            }
        }
        this.m_spin += this.m_Params.m_nSpin;
        this.m_tip += this.m_Params.m_nTip;
    }
    
    void DrawVertice(final int[][] array, final int n, final int n2, final Graphics graphics) {
        graphics.drawLine(array[n][0], array[n][1], array[n2][0], array[n2][1]);
    }
    
    public boolean DrawPlane(final Graphics graphics) {
        this.CalculateRotationMatrix();
        graphics.setColor(this.m_Params.m_Color);
        for (int i = 0; i < this.m_string.Length(); ++i) {
            final WireFrameChar char1 = this.m_string.CharAt(i);
            this.RotateAndProject(char1.m_vertices, this.m_vertice_projected, char1.GetVerticeCount());
            for (int j = 0; j < char1.GetEdgeCount(); ++j) {
                this.DrawVertice(this.m_vertice_projected, char1.GetStartEdge(j), char1.GetEndEdge(j), graphics);
            }
        }
        this.IncrementViewVariables();
        return this.m_bTimeToDie;
    }
}
