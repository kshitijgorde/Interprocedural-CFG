import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class MazeCanvas extends Canvas
{
    private boolean invalidated;
    public Maze3D maze3D;
    public PaintScreen p;
    public int previousHeight;
    public int previousWidth;
    public Rectangle rectangle;
    public boolean resize;
    
    MazeCanvas(final Maze3D maze3D) {
        this.maze3D = maze3D;
        this.invalidated = false;
        this.previousWidth = 0;
        this.previousHeight = 0;
        (this.p = new PaintScreen(this)).start();
    }
    
    public void paint(final Graphics graphics) {
        if (this.invalidated) {
            this.invalidated = false;
            this.rectangle = this.bounds();
            if (this.previousWidth == this.rectangle.width && this.previousHeight == this.rectangle.height) {
                this.resize = false;
            }
            else {
                if (this.maze3D.hexagonalRooms) {
                    this.maze3D.message.setText("Use Home, Up Arrow, PgUp, End, Down Arrow, or PgDn to solve.");
                }
                else {
                    this.maze3D.message.setText("Use the arrow keys to solve.");
                }
                this.maze3D.solutionDisplayed = false;
                this.resize = true;
                this.previousWidth = this.rectangle.width;
                this.previousHeight = this.rectangle.height;
            }
            this.p.startOver(true);
            return;
        }
        this.invalidated = true;
        this.hide();
        this.show();
    }
}
