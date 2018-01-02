import java.awt.Polygon;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

public class Wireframe
{
    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;
    private int xPosition;
    private int yPosition;
    private int zPosition;
    private int xAngle;
    private int yAngle;
    private int zAngle;
    private int[][] local;
    private int[][] world;
    private int[][] view;
    private int[][] surface;
    private boolean valid;
    
    public Wireframe(final int[][] local, final int[][] surface, final int xPosition, final int yPosition, final int zPosition, final int xAngle, final int yAngle, final int zAngle) {
        this.local = local;
        this.world = new int[local.length][3];
        this.surface = surface;
        this.setXPosition(xPosition);
        this.setYPosition(yPosition);
        this.setZPosition(zPosition);
        this.setXAngle(xAngle);
        this.setYAngle(yAngle);
        this.setZAngle(zAngle);
    }
    
    private void worldcoords() {
        final int[][] array = new int[3][3];
        array[0][0] = (FastMath.cos(this.yAngle - this.zAngle) + FastMath.cos(this.yAngle + this.zAngle)) / 2;
        array[0][1] = ((FastMath.cos(this.xAngle - this.yAngle - this.zAngle) + FastMath.cos(this.xAngle - this.yAngle + this.zAngle) - FastMath.cos(this.xAngle + this.yAngle - this.zAngle) - FastMath.cos(this.xAngle + this.yAngle + this.zAngle)) / 2 - FastMath.sin(this.xAngle - this.zAngle) + FastMath.sin(this.xAngle + this.zAngle)) / 2;
        array[0][2] = ((FastMath.sin(this.xAngle - this.yAngle + this.zAngle) + FastMath.sin(this.xAngle - this.yAngle - this.zAngle) - FastMath.sin(this.xAngle + this.yAngle - this.zAngle) - FastMath.sin(this.xAngle + this.yAngle + this.zAngle)) / 2 + FastMath.cos(this.xAngle - this.zAngle) - FastMath.cos(this.xAngle + this.zAngle)) / 2;
        array[1][0] = (FastMath.sin(this.yAngle - this.zAngle) - FastMath.sin(this.yAngle + this.zAngle)) / 2;
        array[1][1] = ((FastMath.sin(this.xAngle - this.yAngle - this.zAngle) - FastMath.sin(this.xAngle - this.yAngle + this.zAngle) - FastMath.sin(this.xAngle + this.yAngle - this.zAngle) + FastMath.sin(this.xAngle + this.yAngle + this.zAngle)) / 2 + FastMath.cos(this.xAngle - this.zAngle) + FastMath.cos(this.xAngle + this.zAngle)) / 2;
        array[1][2] = ((FastMath.cos(this.xAngle - this.yAngle + this.zAngle) + FastMath.cos(this.xAngle + this.yAngle - this.zAngle) - FastMath.cos(this.xAngle - this.yAngle - this.zAngle) - FastMath.cos(this.xAngle + this.yAngle + this.zAngle)) / 2 + FastMath.sin(this.xAngle - this.zAngle) + FastMath.sin(this.xAngle + this.zAngle)) / 2;
        array[2][0] = FastMath.sin(this.yAngle);
        array[2][1] = (FastMath.sin(this.yAngle - this.xAngle) - FastMath.sin(this.xAngle + this.yAngle)) / 2;
        array[2][2] = (FastMath.cos(this.xAngle - this.yAngle) + FastMath.cos(this.xAngle + this.yAngle)) / 2;
        for (int i = 0; i < this.local.length; ++i) {
            final int n = this.local[i][0];
            final int n2 = this.local[i][1];
            final int n3 = this.local[i][2];
            this.world[i][0] = this.xPosition + (array[0][0] * n + array[0][1] * n2 + array[0][2] * n3 >> 8);
            this.world[i][1] = this.yPosition + (array[1][0] * n + array[1][1] * n2 + array[1][2] * n3 >> 8);
            this.world[i][2] = this.zPosition + (array[2][0] * n + array[2][1] * n2 + array[2][2] * n3 >> 8);
        }
    }
    
    public void viewcoords(final FixedCamera fixedCamera) {
        this.validate();
        this.view = fixedCamera.viewcoords(this.world);
    }
    
    public boolean isValid() {
        return this.valid;
    }
    
    private void invalidate() {
        this.valid = false;
    }
    
    private void validate() {
        if (!this.valid) {
            this.worldcoords();
            this.valid = true;
        }
    }
    
    public void draw(final Graphics graphics) {
        for (int i = 0; i < this.surface.length; ++i) {
            final Polygon polygon = new Polygon();
            for (int j = 0; j < this.surface[i].length; ++j) {
                polygon.addPoint(this.view[this.surface[i][j]][0], this.view[this.surface[i][j]][1]);
            }
            graphics.drawPolygon(polygon);
        }
    }
    
    public int getXPosition() {
        return this.xPosition;
    }
    
    public void setXPosition(final int xPosition) {
        this.xPosition = xPosition;
        this.invalidate();
    }
    
    public int getYPosition() {
        return this.yPosition;
    }
    
    public void setYPosition(final int yPosition) {
        this.yPosition = yPosition;
        this.invalidate();
    }
    
    public int getZPosition() {
        return this.zPosition;
    }
    
    public void setZPosition(final int zPosition) {
        this.zPosition = zPosition;
        this.invalidate();
    }
    
    public void move(final int n, final int n2, final int n3) {
        this.xPosition += n;
        this.yPosition += n2;
        this.zPosition += n3;
        this.invalidate();
    }
    
    public int getXAngle() {
        return this.xAngle;
    }
    
    public void setXAngle(final int xAngle) {
        this.xAngle = xAngle;
        this.invalidate();
    }
    
    public int getYAngle() {
        return this.yAngle;
    }
    
    public void setYAngle(final int yAngle) {
        this.yAngle = yAngle;
        this.invalidate();
    }
    
    public int getZAngle() {
        return this.zAngle;
    }
    
    public void setZAngle(final int zAngle) {
        this.zAngle = zAngle;
        this.invalidate();
    }
    
    public void rotate(final int n, final int n2, final int n3) {
        this.xAngle += n;
        this.yAngle += n2;
        this.zAngle += n3;
        this.invalidate();
    }
}
