import damkjer.ocd.Camera;
import processing.core.PApplet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Feed extends PApplet
{
    Camera camera1;
    Camera camera2;
    
    public void setup() {
        this.size(100, 100, "processing.core.PGraphics3");
        this.camera1 = new Camera(this, 100.0f, -125.0f, 150.0f);
        this.camera2 = new Camera(this, 100.0f);
    }
    
    public void draw() {
        this.background(204);
        this.lights();
        if (!this.mousePressed) {
            this.camera1.feed();
        }
        else {
            this.camera2.feed();
        }
        this.rotateY(1.0471976f);
        this.box(50.0f);
    }
}
