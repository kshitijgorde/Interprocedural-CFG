import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class ThreeDTextParams
{
    double m_nZoomInStep;
    double m_nDestinationZ;
    double m_nZoomOutStep;
    double m_nTimeToDieZ;
    int m_nDelay;
    int m_nSpin;
    int m_nTip;
    Color m_Color;
    
    public ThreeDTextParams(final double nZoomInStep, final double nDestinationZ, final double nZoomOutStep, final double nTimeToDieZ, final int nDelay, final int nSpin, final int nTip, final String s) {
        this.m_nZoomInStep = nZoomInStep;
        this.m_nDestinationZ = nDestinationZ;
        this.m_nZoomOutStep = nZoomOutStep;
        this.m_nTimeToDieZ = nTimeToDieZ;
        this.m_nDelay = nDelay;
        this.m_nSpin = nSpin;
        this.m_nTip = nTip;
        this.m_Color = GetColorFromString(s);
    }
    
    public static Color GetColorFromString(final String s) {
        Color color = Color.gray;
        if (s.equalsIgnoreCase("Blue")) {
            color = Color.blue;
        }
        else if (s.equalsIgnoreCase("Cyan")) {
            color = Color.cyan;
        }
        else if (s.equalsIgnoreCase("Dark Gray")) {
            color = Color.darkGray;
        }
        else if (s.equalsIgnoreCase("Gray")) {
            color = Color.gray;
        }
        else if (s.equalsIgnoreCase("Green")) {
            color = Color.green;
        }
        else if (s.equalsIgnoreCase("Light Gray")) {
            color = Color.lightGray;
        }
        else if (s.equalsIgnoreCase("Magenta")) {
            color = Color.magenta;
        }
        else if (s.equalsIgnoreCase("Orange")) {
            color = Color.orange;
        }
        else if (s.equalsIgnoreCase("Pink")) {
            color = Color.pink;
        }
        else if (s.equalsIgnoreCase("Red")) {
            color = Color.red;
        }
        else if (s.equalsIgnoreCase("White")) {
            color = Color.white;
        }
        else if (s.equalsIgnoreCase("Yellow")) {
            color = Color.yellow;
        }
        return color;
    }
}
