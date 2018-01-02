import java.awt.event.MouseEvent;
import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class DistObj extends PolyObj
{
    protected final String kstrToken = "%s";
    protected final String kstrPixelSizeParam = "PIXEL_SIZE";
    protected final String kstrDistanceMsgParam = "DIST_MSG";
    protected final String kstrDistPrecisionParam = "DIST_PRECISION";
    protected int m_nPrecision;
    protected double m_fPixelSize;
    protected double m_fCurrentDistance;
    protected String m_strMsg;
    protected Rosa2000 m_applet;
    
    public DistObj(final Map parent, final Rosa2000 applet) {
        super(parent, applet);
        this.m_nPrecision = 3;
        this.m_fPixelSize = 0.0;
        this.m_fCurrentDistance = 0.0;
        this.m_strMsg = null;
        this.m_applet = applet;
        this.readPararam();
    }
    
    protected void readPararam() {
        this.m_strMsg = this.m_applet.getParameter("DIST_MSG");
        final String strPrecision = this.m_applet.getParameter("DIST_PRECISION");
        final String strPixelSize = this.m_applet.getParameter("PIXEL_SIZE");
        if (strPixelSize != null) {
            try {
                if (strPrecision != null) {
                    this.m_nPrecision = Integer.valueOf(strPrecision);
                }
                this.m_fPixelSize = Double.valueOf(strPixelSize);
            }
            catch (NumberFormatException e) {
                System.err.println("Invalid value " + strPixelSize + " or " + strPrecision + " in the parameter " + "PIXEL_SIZE" + ". The default value will be set. One pixel = one unit.");
            }
        }
    }
    
    public String getSubmitValue() {
        final String strRetVal = new String();
        return strRetVal;
    }
    
    public double getDistance(final Point pt1, final Point pt2) {
        final double dfDelta = Math.pow(pt2.x - pt1.x, 2.0) + Math.pow(pt2.y - pt1.y, 2.0);
        return Math.sqrt(dfDelta) * this.m_fPixelSize;
    }
    
    protected String getValueString(final double fValue) {
        String str = new String();
        str += fValue;
        final int nIndex = str.indexOf(".");
        if (nIndex + this.m_nPrecision + 1 < str.length()) {
            str = str.substring(0, nIndex + this.m_nPrecision + 1);
        }
        return str;
    }
    
    public String getDistanceStringMsg(final double fValue) {
        String strVal = new String();
        if (this.m_strMsg != null) {
            final int nIndex = this.m_strMsg.indexOf("%s");
            if (nIndex != -1) {
                strVal = this.m_strMsg.substring(0, nIndex);
                strVal += this.getValueString(fValue);
                if (this.m_strMsg.length() > nIndex + 2) {
                    strVal += this.m_strMsg.substring(nIndex + 2);
                }
            }
        }
        else {
            strVal = this.getValueString(fValue);
        }
        return strVal;
    }
    
    public void mouseMoved(final MouseEvent evt) {
        if (super.m_poly.npoints > 0) {
            final Point ptLastPt = new Point(super.m_poly.xpoints[super.m_poly.npoints - 1], super.m_poly.ypoints[super.m_poly.npoints - 1]);
            final double fDist = this.getDistance(ptLastPt, evt.getPoint());
            this.m_applet.showText(this.getDistanceStringMsg(fDist + this.m_fCurrentDistance));
        }
        super.mouseMoved(evt);
    }
    
    public void mousePressed(final MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            this.endProcessing();
        }
        else {
            if (super.m_poly.npoints > 0) {
                final Point ptLastPt = new Point(super.m_poly.xpoints[super.m_poly.npoints - 1], super.m_poly.ypoints[super.m_poly.npoints - 1]);
                this.m_fCurrentDistance += this.getDistance(ptLastPt, evt.getPoint());
                this.m_applet.showText(this.getDistanceStringMsg(this.m_fCurrentDistance));
            }
            super.mousePressed(evt);
        }
    }
    
    public void destroyEvent() {
        this.m_applet.showText("");
        super.destroyEvent();
    }
    
    public void mouseReleased(final MouseEvent evt) {
        if (super.m_poly.npoints > 0) {
            final Point ptLastPt = new Point(super.m_poly.xpoints[super.m_poly.npoints - 1], super.m_poly.ypoints[super.m_poly.npoints - 1]);
            this.m_fCurrentDistance += this.getDistance(ptLastPt, evt.getPoint());
            this.m_applet.showText(this.getDistanceStringMsg(this.m_fCurrentDistance));
        }
        super.mouseReleased(evt);
    }
}
