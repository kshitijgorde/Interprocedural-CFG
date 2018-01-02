import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Point;
import astro.Planet;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import astro.Matrix;
import astro.Xyz;
import astro.ATime;
import astro.PlanetOrbit;
import astro.CometOrbit;
import astro.Comet;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class OrbitCanvas extends Canvas
{
    private Comet object;
    private Comet object1;
    private Comet object2;
    private CometOrbit objectOrbit;
    private CometOrbit object1Orbit;
    private CometOrbit object2Orbit;
    private PlanetOrbit[] planetOrbit;
    private double epochPlanetOrbit;
    private ATime atime;
    private Xyz objectPos;
    private Xyz object1Pos;
    private Xyz object2Pos;
    private Xyz[] planetPos;
    private int CenterObjectSelected;
    private boolean[] OrbitDisplay;
    private double fRotateH;
    private double fRotateV;
    private double fZoom;
    private Matrix mtxToEcl;
    private double epochToEcl;
    private Matrix mtxRotate;
    private int nX0;
    private int nY0;
    private Dimension sizeCanvas;
    private Color colorObjectOrbitUpper;
    private Color colorObjectOrbitLower;
    private Color colorObject;
    private Color colorObjectName;
    private Color colorPlanetOrbitUpper;
    private Color colorPlanetOrbitLower;
    private Color colorPlanet;
    private Color colorPlanetName;
    private Color colorSun;
    private Color colorAxisPlus;
    private Color colorAxisMinus;
    private Color colorInformation;
    private Font fontObjectName;
    private Font fontPlanetName;
    private Font fontInformation;
    Image offscreen;
    boolean bPlanetName;
    boolean bObjectName;
    boolean bDistanceLabel;
    boolean bDateLabel;
    
    public OrbitCanvas(final Comet object, final Comet object2, final Comet object3, final ATime atime) {
        this.fRotateH = 0.0;
        this.fRotateV = 0.0;
        this.fZoom = 5.0;
        this.colorObjectOrbitUpper = new Color(62975);
        this.colorObjectOrbitLower = new Color(255);
        this.colorObject = new Color(65535);
        this.colorObjectName = new Color(52428);
        this.colorPlanetOrbitUpper = new Color(16777215);
        this.colorPlanetOrbitLower = new Color(8421504);
        this.colorPlanet = new Color(65280);
        this.colorPlanetName = new Color(43520);
        this.colorSun = new Color(13647936);
        this.colorAxisPlus = new Color(16776960);
        this.colorAxisMinus = new Color(5592320);
        this.colorInformation = new Color(16777215);
        this.fontObjectName = new Font("Helvetica", 1, 14);
        this.fontPlanetName = new Font("Helvetica", 0, 14);
        this.fontInformation = new Font("Helvetica", 1, 14);
        this.planetPos = new Xyz[9];
        this.OrbitDisplay = new boolean[11];
        this.object = object;
        this.objectOrbit = new CometOrbit(object, 120);
        this.object1 = object2;
        this.object1Orbit = new CometOrbit(object2, 120);
        this.object2 = object3;
        this.object2Orbit = new CometOrbit(object3, 120);
        this.planetOrbit = new PlanetOrbit[9];
        this.updatePlanetOrbit(atime);
        this.updateRotationMatrix(atime);
        this.setDate(this.atime = atime);
        this.offscreen = null;
        this.bPlanetName = false;
        this.bObjectName = false;
        this.bDistanceLabel = true;
        this.bDateLabel = true;
        this.repaint();
    }
    
    private void updatePlanetOrbit(final ATime aTime) {
        for (int i = 1; i <= 9; ++i) {
            this.planetOrbit[i - 1] = new PlanetOrbit(i, aTime, 48);
        }
        this.epochPlanetOrbit = aTime.getJd();
    }
    
    private void updateRotationMatrix(final ATime aTime) {
        this.mtxToEcl = Matrix.RotateX(ATime.getEp(aTime.getJd())).Mul(Matrix.PrecMatrix(2451545.0, aTime.getJd()));
        this.epochToEcl = aTime.getJd();
    }
    
    public void setRotateHorz(final int n) {
        this.fRotateH = n;
    }
    
    public void setRotateVert(final int n) {
        this.fRotateV = n;
    }
    
    public void setZoom(final int n) {
        this.fZoom = n;
    }
    
    public void setDate(final ATime atime) {
        this.atime = atime;
        this.objectPos = this.object.GetPos(atime.getJd());
        this.object1Pos = this.object1.GetPos(atime.getJd());
        this.object2Pos = this.object2.GetPos(atime.getJd());
        for (int i = 0; i < 9; ++i) {
            this.planetPos[i] = Planet.getPos(1 + i, atime);
        }
    }
    
    public void switchPlanetName(final boolean bPlanetName) {
        this.bPlanetName = bPlanetName;
    }
    
    public void SelectOrbits(final boolean[] array, final int n) {
        for (int i = 0; i < n; ++i) {
            this.OrbitDisplay[i] = array[i];
        }
    }
    
    public void SelectCenterObject(final int centerObjectSelected) {
        this.CenterObjectSelected = centerObjectSelected;
    }
    
    public void switchObjectName(final boolean bObjectName) {
        this.bObjectName = bObjectName;
    }
    
    public void switchDistanceLabel(final boolean bDistanceLabel) {
        this.bDistanceLabel = bDistanceLabel;
    }
    
    public void switchDateLabel(final boolean bDateLabel) {
        this.bDateLabel = bDateLabel;
    }
    
    private Point getDrawPoint(final Xyz xyz) {
        final double n = this.fZoom * this.sizeCanvas.width / 600.0 * (1.0 + xyz.fZ / 250.0);
        return new Point(this.nX0 + (int)Math.round(xyz.fX * n), this.nY0 - (int)Math.round(xyz.fY * n));
    }
    
    private void drawPlanetOrbit(final Graphics graphics, final PlanetOrbit planetOrbit, final Color color, final Color color2) {
        Point drawPoint = this.getDrawPoint(planetOrbit.getAt(0).Rotate(this.mtxToEcl).Rotate(this.mtxRotate));
        for (int i = 1; i <= planetOrbit.getDivision(); ++i) {
            final Xyz rotate = planetOrbit.getAt(i).Rotate(this.mtxToEcl);
            if (rotate.fZ >= 0.0) {
                graphics.setColor(color);
            }
            else {
                graphics.setColor(color2);
            }
            final Point drawPoint2 = this.getDrawPoint(rotate.Rotate(this.mtxRotate));
            graphics.drawLine(drawPoint.x, drawPoint.y, drawPoint2.x, drawPoint2.y);
            drawPoint = drawPoint2;
        }
    }
    
    private void drawEarthOrbit(final Graphics graphics, final PlanetOrbit planetOrbit, final Color color, final Color color2) {
        Point drawPoint = this.getDrawPoint(planetOrbit.getAt(0).Rotate(this.mtxToEcl).Rotate(this.mtxRotate));
        for (int i = 1; i <= planetOrbit.getDivision(); ++i) {
            final Xyz rotate = planetOrbit.getAt(i).Rotate(this.mtxToEcl);
            graphics.setColor(color);
            final Point drawPoint2 = this.getDrawPoint(rotate.Rotate(this.mtxRotate));
            graphics.drawLine(drawPoint.x, drawPoint.y, drawPoint2.x, drawPoint2.y);
            drawPoint = drawPoint2;
        }
    }
    
    private void drawPlanetBody(final Graphics graphics, final Xyz xyz, final String s) {
        final Point drawPoint = this.getDrawPoint(xyz.Rotate(this.mtxRotate));
        graphics.setColor(this.colorPlanet);
        graphics.fillArc(drawPoint.x - 2, drawPoint.y - 2, 5, 5, 0, 360);
        if (this.bPlanetName) {
            graphics.setColor(this.colorPlanetName);
            graphics.drawString(s, drawPoint.x + 5, drawPoint.y);
        }
    }
    
    private void drawEclipticAxis(final Graphics graphics) {
        graphics.setColor(this.colorAxisMinus);
        final Point drawPoint = this.getDrawPoint(new Xyz(-50.0, 0.0, 0.0).Rotate(this.mtxRotate));
        graphics.drawLine(this.nX0, this.nY0, drawPoint.x, drawPoint.y);
        final Point drawPoint2 = this.getDrawPoint(new Xyz(0.0, 0.0, -50.0).Rotate(this.mtxRotate));
        graphics.drawLine(this.nX0, this.nY0, drawPoint2.x, drawPoint2.y);
        graphics.setColor(this.colorAxisPlus);
        final Point drawPoint3 = this.getDrawPoint(new Xyz(50.0, 0.0, 0.0).Rotate(this.mtxRotate));
        graphics.drawLine(this.nX0, this.nY0, drawPoint3.x, drawPoint3.y);
        final Point drawPoint4 = this.getDrawPoint(new Xyz(0.0, 0.0, 50.0).Rotate(this.mtxRotate));
        graphics.drawLine(this.nX0, this.nY0, drawPoint4.x, drawPoint4.y);
    }
    
    public void update(final Graphics graphics) {
        this.mtxRotate = Matrix.RotateX(this.fRotateV * 3.141592653589793 / 180.0).Mul(Matrix.RotateZ(this.fRotateH * 3.141592653589793 / 180.0));
        this.nX0 = this.sizeCanvas.width / 2;
        this.nY0 = this.sizeCanvas.height / 2;
        if (Math.abs(this.epochToEcl - this.atime.getJd()) > 1826.2110000000002) {
            this.updateRotationMatrix(this.atime);
        }
        if (this.CenterObjectSelected == 1) {
            this.objectOrbit.getAt(0).Rotate(this.mtxToEcl).Rotate(this.mtxRotate);
            final Point drawPoint = this.getDrawPoint(this.objectPos.Rotate(this.mtxToEcl).Rotate(this.mtxRotate));
            this.nX0 = this.sizeCanvas.width - drawPoint.x;
            this.nY0 = this.sizeCanvas.height - drawPoint.y;
            if (Math.abs(this.epochToEcl - this.atime.getJd()) > 1826.2110000000002) {
                this.updateRotationMatrix(this.atime);
            }
        }
        else if (this.CenterObjectSelected > 1) {
            final Point drawPoint2 = this.getDrawPoint(this.planetPos[this.CenterObjectSelected - 2].Rotate(this.mtxRotate));
            this.nX0 = this.sizeCanvas.width - drawPoint2.x;
            this.nY0 = this.sizeCanvas.height - drawPoint2.y;
            if (Math.abs(this.epochToEcl - this.atime.getJd()) > 1826.2110000000002) {
                this.updateRotationMatrix(this.atime);
            }
        }
        final Graphics graphics2 = this.offscreen.getGraphics();
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, 0, this.sizeCanvas.width - 1, this.sizeCanvas.height - 1);
        this.drawEclipticAxis(graphics2);
        graphics2.setColor(this.colorSun);
        graphics2.fillArc(this.nX0 - 2, this.nY0 - 2, 5, 5, 0, 360);
        Point drawPoint3 = this.getDrawPoint(this.objectOrbit.getAt(0).Rotate(this.mtxToEcl).Rotate(this.mtxRotate));
        if (this.OrbitDisplay[0] || this.OrbitDisplay[1]) {
            for (int i = 1; i <= this.objectOrbit.getDivision(); ++i) {
                final Xyz rotate = this.objectOrbit.getAt(i).Rotate(this.mtxToEcl);
                if (rotate.fZ >= 0.0) {
                    graphics2.setColor(this.colorObjectOrbitUpper);
                }
                else {
                    graphics2.setColor(this.colorObjectOrbitLower);
                }
                final Point drawPoint4 = this.getDrawPoint(rotate.Rotate(this.mtxRotate));
                graphics2.drawLine(drawPoint3.x, drawPoint3.y, drawPoint4.x, drawPoint4.y);
                drawPoint3 = drawPoint4;
            }
        }
        final Point drawPoint5 = this.getDrawPoint(this.objectPos.Rotate(this.mtxToEcl).Rotate(this.mtxRotate));
        graphics2.setColor(this.colorObject);
        graphics2.fillArc(drawPoint5.x - 2, drawPoint5.y - 2, 5, 5, 0, 360);
        graphics2.setFont(this.fontObjectName);
        if (this.bObjectName) {
            graphics2.setColor(this.colorObjectName);
            graphics2.drawString(this.object.getName(), drawPoint5.x + 5, drawPoint5.y);
        }
        Point drawPoint6 = this.getDrawPoint(this.object1Orbit.getAt(0).Rotate(this.mtxToEcl).Rotate(this.mtxRotate));
        if (this.OrbitDisplay[0] || this.OrbitDisplay[1]) {
            for (int j = 1; j <= this.object1Orbit.getDivision(); ++j) {
                final Xyz rotate2 = this.object1Orbit.getAt(j).Rotate(this.mtxToEcl);
                if (rotate2.fZ >= 0.0) {
                    graphics2.setColor(this.colorObjectOrbitUpper);
                }
                else {
                    graphics2.setColor(this.colorObjectOrbitLower);
                }
                final Point drawPoint7 = this.getDrawPoint(rotate2.Rotate(this.mtxRotate));
                graphics2.drawLine(drawPoint6.x, drawPoint6.y, drawPoint7.x, drawPoint7.y);
                drawPoint6 = drawPoint7;
            }
        }
        final Point drawPoint8 = this.getDrawPoint(this.object1Pos.Rotate(this.mtxToEcl).Rotate(this.mtxRotate));
        graphics2.setColor(this.colorObject);
        graphics2.fillArc(drawPoint8.x - 2, drawPoint8.y - 2, 5, 5, 0, 360);
        graphics2.setFont(this.fontObjectName);
        if (this.bObjectName) {
            graphics2.setColor(this.colorObjectName);
            graphics2.drawString(this.object1.getName(), drawPoint8.x + 5, drawPoint8.y);
        }
        Point drawPoint9 = this.getDrawPoint(this.object2Orbit.getAt(0).Rotate(this.mtxToEcl).Rotate(this.mtxRotate));
        if (this.OrbitDisplay[0] || this.OrbitDisplay[1]) {
            for (int k = 1; k <= this.object2Orbit.getDivision(); ++k) {
                final Xyz rotate3 = this.object2Orbit.getAt(k).Rotate(this.mtxToEcl);
                if (rotate3.fZ >= 0.0) {
                    graphics2.setColor(this.colorObjectOrbitUpper);
                }
                else {
                    graphics2.setColor(this.colorObjectOrbitLower);
                }
                final Point drawPoint10 = this.getDrawPoint(rotate3.Rotate(this.mtxRotate));
                graphics2.drawLine(drawPoint9.x, drawPoint9.y, drawPoint10.x, drawPoint10.y);
                drawPoint9 = drawPoint10;
            }
        }
        final Point drawPoint11 = this.getDrawPoint(this.object2Pos.Rotate(this.mtxToEcl).Rotate(this.mtxRotate));
        graphics2.setColor(this.colorObject);
        graphics2.fillArc(drawPoint11.x - 2, drawPoint11.y - 2, 5, 5, 0, 360);
        graphics2.setFont(this.fontObjectName);
        if (this.bObjectName) {
            graphics2.setColor(this.colorObjectName);
            graphics2.drawString(this.object2.getName(), drawPoint11.x + 5, drawPoint11.y);
        }
        if (Math.abs(this.epochPlanetOrbit - this.atime.getJd()) > 1826.2110000000002) {
            this.updatePlanetOrbit(this.atime);
        }
        graphics2.setFont(this.fontPlanetName);
        if (this.OrbitDisplay[0] || this.OrbitDisplay[10]) {
            this.drawPlanetOrbit(graphics2, this.planetOrbit[8], this.colorPlanetOrbitUpper, this.colorPlanetOrbitLower);
        }
        this.drawPlanetBody(graphics2, this.planetPos[8], "Pluto");
        if (this.OrbitDisplay[0] || this.OrbitDisplay[9]) {
            this.drawPlanetOrbit(graphics2, this.planetOrbit[7], this.colorPlanetOrbitUpper, this.colorPlanetOrbitLower);
        }
        this.drawPlanetBody(graphics2, this.planetPos[7], "Neptune");
        if (this.OrbitDisplay[0] || this.OrbitDisplay[8]) {
            this.drawPlanetOrbit(graphics2, this.planetOrbit[6], this.colorPlanetOrbitUpper, this.colorPlanetOrbitLower);
        }
        this.drawPlanetBody(graphics2, this.planetPos[6], "Uranus");
        if (this.OrbitDisplay[0] || this.OrbitDisplay[7]) {
            this.drawPlanetOrbit(graphics2, this.planetOrbit[5], this.colorPlanetOrbitUpper, this.colorPlanetOrbitLower);
        }
        this.drawPlanetBody(graphics2, this.planetPos[5], "Saturn");
        if (this.OrbitDisplay[0] || this.OrbitDisplay[6]) {
            this.drawPlanetOrbit(graphics2, this.planetOrbit[4], this.colorPlanetOrbitUpper, this.colorPlanetOrbitLower);
        }
        this.drawPlanetBody(graphics2, this.planetPos[4], "Jupiter");
        if (this.fZoom * 1.524 >= 7.5) {
            if (this.OrbitDisplay[0] || this.OrbitDisplay[5]) {
                this.drawPlanetOrbit(graphics2, this.planetOrbit[3], this.colorPlanetOrbitUpper, this.colorPlanetOrbitLower);
            }
            this.drawPlanetBody(graphics2, this.planetPos[3], "Mars");
        }
        if (this.fZoom * 1.0 >= 7.5) {
            if (this.OrbitDisplay[0] || this.OrbitDisplay[4]) {
                this.drawEarthOrbit(graphics2, this.planetOrbit[2], this.colorPlanetOrbitUpper, this.colorPlanetOrbitUpper);
            }
            this.drawPlanetBody(graphics2, this.planetPos[2], "Earth");
        }
        if (this.fZoom * 0.723 >= 7.5) {
            if (this.OrbitDisplay[0] || this.OrbitDisplay[3]) {
                this.drawPlanetOrbit(graphics2, this.planetOrbit[1], this.colorPlanetOrbitUpper, this.colorPlanetOrbitLower);
            }
            this.drawPlanetBody(graphics2, this.planetPos[1], "Venus");
        }
        if (this.fZoom * 0.387 >= 7.5) {
            if (this.OrbitDisplay[0] || this.OrbitDisplay[2]) {
                this.drawPlanetOrbit(graphics2, this.planetOrbit[0], this.colorPlanetOrbitUpper, this.colorPlanetOrbitLower);
            }
            this.drawPlanetBody(graphics2, this.planetPos[0], "Mercury");
        }
        graphics2.setFont(this.fontInformation);
        graphics2.setColor(this.colorInformation);
        final FontMetrics fontMetrics = graphics2.getFontMetrics();
        drawPoint11.x = fontMetrics.charWidth('A');
        drawPoint11.y = 2 * fontMetrics.charWidth('A');
        if (this.bDistanceLabel) {
            final Xyz rotate4 = this.planetPos[2].Rotate(this.mtxRotate);
            final Xyz rotate5 = this.objectPos.Rotate(this.mtxToEcl).Rotate(this.mtxRotate);
            final double n = (int)((Math.sqrt(rotate5.fX * rotate5.fX + rotate5.fY * rotate5.fY + rotate5.fZ * rotate5.fZ) + 5.0E-4) * 1000.0) / 1000.0;
            final double n2 = rotate5.fX - rotate4.fX;
            final double n3 = rotate5.fY - rotate4.fY;
            final double n4 = rotate5.fZ - rotate4.fZ;
            final String string = this.object.getName() + " - Earth: " + (int)((Math.sqrt(n2 * n2 + n3 * n3 + n4 * n4) + 5.0E-4) * 1000.0) / 1000.0 + " AU  - Sun " + n + " AU";
            drawPoint11.x = fontMetrics.charWidth('A');
            drawPoint11.y = this.sizeCanvas.height - 2 * (fontMetrics.getDescent() + fontMetrics.getHeight());
            graphics2.drawString(string, drawPoint11.x, drawPoint11.y);
            final Xyz rotate6 = this.object1Pos.Rotate(this.mtxToEcl).Rotate(this.mtxRotate);
            final double n5 = (int)((Math.sqrt(rotate6.fX * rotate6.fX + rotate6.fY * rotate6.fY + rotate6.fZ * rotate6.fZ) + 5.0E-4) * 1000.0) / 1000.0;
            final double n6 = rotate6.fX - rotate4.fX;
            final double n7 = rotate6.fY - rotate4.fY;
            final double n8 = rotate6.fZ - rotate4.fZ;
            final String string2 = this.object1.getName() + " - Earth: " + (int)((Math.sqrt(n6 * n6 + n7 * n7 + n8 * n8) + 5.0E-4) * 1000.0) / 1000.0 + " AU  - Sun " + n5 + " AU";
            drawPoint11.x = fontMetrics.charWidth('A');
            drawPoint11.y = this.sizeCanvas.height - fontMetrics.getDescent() - fontMetrics.getHeight();
            graphics2.drawString(string2, drawPoint11.x, drawPoint11.y);
            final Xyz rotate7 = this.object2Pos.Rotate(this.mtxToEcl).Rotate(this.mtxRotate);
            final double n9 = (int)((Math.sqrt(rotate7.fX * rotate7.fX + rotate7.fY * rotate7.fY + rotate7.fZ * rotate7.fZ) + 5.0E-4) * 1000.0) / 1000.0;
            final double n10 = rotate7.fX - rotate4.fX;
            final double n11 = rotate7.fY - rotate4.fY;
            final double n12 = rotate7.fZ - rotate4.fZ;
            final String string3 = this.object2.getName() + " - Earth: " + (int)((Math.sqrt(n10 * n10 + n11 * n11 + n12 * n12) + 5.0E-4) * 1000.0) / 1000.0 + " AU  - Sun " + n9 + " AU";
            drawPoint11.x = fontMetrics.charWidth('A');
            drawPoint11.y = this.sizeCanvas.height - fontMetrics.getDescent();
            graphics2.drawString(string3, drawPoint11.x, drawPoint11.y);
        }
        if (this.bDateLabel) {
            final String string4 = ATime.getMonthAbbr(this.atime.getMonth()) + " " + this.atime.getDay() + ", " + this.atime.getYear();
            drawPoint11.x = this.sizeCanvas.width - fontMetrics.stringWidth(string4) - fontMetrics.charWidth('A');
            drawPoint11.y = this.sizeCanvas.height - fontMetrics.getDescent() - fontMetrics.getHeight() / 3;
            graphics2.drawString(string4, drawPoint11.x, drawPoint11.y);
        }
        graphics2.clearRect(0, this.sizeCanvas.height - 1, this.sizeCanvas.width, this.sizeCanvas.height);
        graphics2.clearRect(this.sizeCanvas.width - 1, 0, this.sizeCanvas.width, this.sizeCanvas.height);
        graphics.drawImage(this.offscreen, 0, 0, null);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen == null) {
            this.sizeCanvas = this.size();
            this.offscreen = this.createImage(this.sizeCanvas.width, this.sizeCanvas.height);
            this.update(graphics);
        }
        else {
            graphics.drawImage(this.offscreen, 0, 0, null);
        }
    }
}
