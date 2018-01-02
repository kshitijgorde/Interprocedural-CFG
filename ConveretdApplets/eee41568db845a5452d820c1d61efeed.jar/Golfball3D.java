import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Golfball3D
{
    public final Color GOLFBALL_COLOR;
    public static final double BALL_RADIUS = 0.075;
    public static final double MAX_VELOCITY = 10.0;
    public final double DECELERATION = 1.6;
    public final int MIN_DIAMETER = 3;
    public final int MAX_STROKES = 7;
    public double m_dX0;
    public double m_dY0;
    public double m_dZ0;
    public double m_dVelocity;
    public double m_dAngle;
    public int m_nStrokes;
    public String m_string;
    long m_lStringTime;
    Tee3D m_tee;
    public boolean m_bVisible;
    public boolean m_bWaterBall;
    public boolean m_bHoledOut;
    public boolean m_bStrokeLimit;
    public Cup3D m_cup;
    public Duct3D m_duct;
    public House3D m_house;
    public Windmill m_windmill;
    public SlidingBridge m_slidingbridge;
    public double m_dX1;
    public double m_dY1;
    public double m_dXmin;
    public double m_dXmax;
    public double m_dYmin;
    public double m_dYmax;
    public double m_dDistance;
    public double m_dFinalVelocity;
    static cgModel m_model;
    public cgObject m_object;
    
    public void drawString(final Graphics graphics) {
        if (this.m_string != null) {
            graphics.setColor(Color.black);
            graphics.setFont(new Font("Helvetica", 1, 40));
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            int n = (CarpetGolf3D.m_nPlayfieldWidth - fontMetrics.stringWidth(this.m_string)) / 2;
            int n2 = (CarpetGolf3D.m_nPlayfieldHeight - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
            graphics.drawString(this.m_string, n, n2);
            graphics.setColor(Color.white);
            n -= 4;
            n2 -= 4;
            graphics.drawString(this.m_string, n, n2);
        }
    }
    
    public Golfball3D(final Tee3D tee) {
        this.GOLFBALL_COLOR = Color.white;
        this.m_bVisible = true;
        this.m_tee = tee;
        this.m_dX0 = this.m_tee.m_dX0 + 0.375;
        this.m_dY0 = this.m_tee.m_dY0 + 0.375;
        this.m_dZ0 = this.m_tee.m_dZ;
        this.m_dX1 = this.m_dX0;
        this.m_dY1 = this.m_dY0;
        if (Golfball3D.m_model == null) {
            (Golfball3D.m_model = new cgModel(1, 0)).specifyVertex(0, new cgVector(0.0, 0.0, 0.0));
        }
        this.m_object = new cgObject(Golfball3D.m_model);
        this.m_object.m_vector.m_dX = this.m_dX0;
        this.m_object.m_vector.m_dY = this.m_dY0;
        this.m_object.m_vector.m_dZ = this.m_dZ0;
        this.m_object.transform();
    }
    
    public void drawBall(final Graphics graphics) {
        if (this.m_string != null) {
            if (System.currentTimeMillis() <= this.m_lStringTime) {
                return;
            }
            this.m_string = null;
        }
        if (this.m_house != null || this.m_duct != null || this.m_cup != null || this.m_windmill != null) {
            return;
        }
        graphics.setColor(this.GOLFBALL_COLOR);
        graphics.fillOval(this.m_object.m_nXview[0] - 1, this.m_object.m_nYview[0] - 1, 3, 3);
    }
    
    public void render(final cgViewpoint cgViewpoint) {
        this.m_object.m_vector.m_dX = this.m_dX0;
        this.m_object.m_vector.m_dY = this.m_dY0;
        this.m_object.m_vector.m_dZ = this.m_dZ0;
        this.m_object.transform();
        cgViewpoint.renderObject(this.m_object);
    }
    
    public void Reset() {
        this.m_dX0 = this.m_tee.m_dX0 + 0.375;
        this.m_dY0 = this.m_tee.m_dY0 + 0.375;
        this.m_dZ0 = this.m_tee.m_dZ;
        this.m_dX1 = this.m_dX0;
        this.m_dY1 = this.m_dY0;
        this.m_dVelocity = 0.0;
        this.m_nStrokes = 0;
        this.m_string = null;
        this.m_dDistance = 0.0;
        this.m_dFinalVelocity = 0.0;
        this.m_cup = null;
        this.m_duct = null;
        this.m_house = null;
        this.m_windmill = null;
        this.m_slidingbridge = null;
        this.m_bVisible = true;
        this.m_bWaterBall = false;
        this.m_bHoledOut = false;
        this.m_bStrokeLimit = false;
    }
    
    public void SetString(final String string) {
        this.m_string = string;
        this.m_lStringTime = System.currentTimeMillis() + 4000L;
    }
}
