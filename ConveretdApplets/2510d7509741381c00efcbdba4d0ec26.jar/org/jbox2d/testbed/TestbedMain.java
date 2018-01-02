// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed;

import org.jbox2d.common.Vec2;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import org.jbox2d.testbed.tests.DistanceTest;
import org.jbox2d.testbed.tests.CCDTest;
import org.jbox2d.testbed.tests.Circles;
import org.jbox2d.testbed.tests.DominoTower;
import org.jbox2d.testbed.tests.Pyramid;
import org.jbox2d.testbed.tests.ShapeDrawing;
import org.jbox2d.testbed.tests.Gears;
import org.jbox2d.testbed.tests.Bridge;
import org.jbox2d.testbed.tests.Chain;
import org.jbox2d.testbed.tests.CompoundShapes;
import org.jbox2d.testbed.tests.Domino;
import org.jbox2d.testbed.tests.VerticalStack;
import org.jbox2d.testbed.tests.MotorsAndLimits;
import org.jbox2d.testbed.tests.VaryingFriction;
import org.jbox2d.testbed.tests.VaryingRestitution;
import org.jbox2d.testbed.tests.Overhang;
import org.jbox2d.testbed.tests.Pulleys;
import org.jbox2d.testbed.tests.SpriteBinding;
import org.jbox2d.testbed.tests.BipedTest;
import org.jbox2d.dynamics.DebugDraw;
import java.util.ArrayList;
import processing.core.PApplet;

public class TestbedMain extends PApplet
{
    private static final long serialVersionUID = 1712524774634907635L;
    protected ArrayList<AbstractExample> tests;
    protected AbstractExample currentTest;
    protected int currentTestIndex;
    protected boolean handleOptions;
    public boolean shiftKey;
    boolean pmousePressed;
    public TestbedOptions options;
    static final float targetFPS = 60.0f;
    final int fpsAverageCount = 100;
    long[] nanos;
    long nanoStart;
    long frameCount;
    public DebugDraw g;
    
    public TestbedMain() {
        this.tests = new ArrayList<AbstractExample>(0);
        this.currentTest = null;
        this.currentTestIndex = 0;
        this.handleOptions = false;
        this.shiftKey = false;
        this.pmousePressed = false;
        this.frameCount = 0L;
    }
    
    public void setup() {
        this.size(640, 480, "processing.core.PGraphics3D");
        this.frameRate(60.0f);
        this.g = new ProcessingDebugDraw(this);
        for (int i = 0; i < 100; ++i) {
            this.requestFocus();
        }
        this.registerExample(new BipedTest(this));
        this.registerExample(new SpriteBinding(this));
        this.registerExample(new Pulleys(this));
        this.registerExample(new Overhang(this));
        this.registerExample(new VaryingRestitution(this));
        this.registerExample(new VaryingFriction(this));
        this.registerExample(new MotorsAndLimits(this));
        this.registerExample(new VerticalStack(this));
        this.registerExample(new Domino(this));
        this.registerExample(new CompoundShapes(this));
        this.registerExample(new Chain(this));
        this.registerExample(new Bridge(this));
        this.registerExample(new Gears(this));
        this.registerExample(new ShapeDrawing(this));
        this.registerExample(new Pyramid(this));
        this.registerExample(new DominoTower(this));
        this.registerExample(new Circles(this));
        this.registerExample(new CCDTest(this));
        this.registerExample(new DistanceTest(this));
        this.addMouseWheelListener((MouseWheelListener)new MouseWheelListener() {
            public void mouseWheelMoved(final MouseWheelEvent e) {
                if (TestbedMain.this.currentTest != null) {
                    final ProcessingDebugDraw d = (ProcessingDebugDraw)TestbedMain.this.currentTest.m_debugDraw;
                    final int notches = e.getWheelRotation();
                    final Vec2 oldCenter = d.screenToWorld(TestbedMain.this.width / 2.0f, TestbedMain.this.height / 2.0f);
                    if (notches < 0) {
                        d.scaleFactor = TestbedMain.min(300.0f, d.scaleFactor * 1.05f);
                    }
                    else if (notches > 0) {
                        d.scaleFactor = TestbedMain.max(0.02f, d.scaleFactor / 1.05f);
                    }
                    final Vec2 newCenter = d.screenToWorld(TestbedMain.this.width / 2.0f, TestbedMain.this.height / 2.0f);
                    final ProcessingDebugDraw processingDebugDraw = d;
                    processingDebugDraw.transX -= (oldCenter.x - newCenter.x) * d.scaleFactor;
                    final ProcessingDebugDraw processingDebugDraw2 = d;
                    processingDebugDraw2.transY -= (oldCenter.y - newCenter.y) * d.scaleFactor;
                    TestbedMain.this.currentTest.cachedCamScale = d.scaleFactor;
                }
            }
        });
        this.nanos = new long[100];
        final long nanosPerFrameGuess = 16666666L;
        this.nanos[99] = System.nanoTime();
        for (int j = 98; j >= 0; --j) {
            this.nanos[j] = this.nanos[j + 1] - nanosPerFrameGuess;
        }
        this.nanoStart = System.nanoTime();
        this.options = new TestbedOptions(this);
    }
    
    public void draw() {
        if (this.handleOptions) {
            this.options.handleOptions();
        }
        else {
            this.background(0);
            Vec2.creationCount = 0;
            if (this.currentTest == null) {
                this.currentTestIndex = 0;
                this.currentTest = this.tests.get(this.currentTestIndex);
                this.nanoStart = System.nanoTime();
                this.frameCount = 0L;
            }
            if (this.currentTest.needsReset) {
                final TestSettings s = this.currentTest.settings;
                this.currentTest.initialize();
                if (s != null) {
                    this.currentTest.settings = s;
                }
                this.nanoStart = System.nanoTime();
                this.frameCount = 0L;
            }
            this.currentTest.m_textLine = AbstractExample.textLineHeight;
            this.g.drawString(5.0f, this.currentTest.m_textLine, this.currentTest.getName(), AbstractExample.white);
            final AbstractExample currentTest = this.currentTest;
            currentTest.m_textLine += 2 * AbstractExample.textLineHeight;
            this.currentTest.step();
            this.handleCanvasDrag();
            if (this.currentTest.settings.drawStats) {
                this.g.drawString(5.0f, this.currentTest.m_textLine, "Vec2 creations/frame: " + Vec2.creationCount, AbstractExample.white);
                final AbstractExample currentTest2 = this.currentTest;
                currentTest2.m_textLine += AbstractExample.textLineHeight;
            }
            for (int i = 0; i < 99; ++i) {
                this.nanos[i] = this.nanos[i + 1];
            }
            this.nanos[99] = System.nanoTime();
            final float averagedFPS = (float)(9.9E10 / (this.nanos[99] - this.nanos[0]));
            ++this.frameCount;
            final float totalFPS = (float)(this.frameCount * 1000000000L / (1.0 * (System.nanoTime() - this.nanoStart)));
            if (this.currentTest.settings.drawStats) {
                this.g.drawString(5.0f, this.currentTest.m_textLine, "Average FPS (100 frames): " + averagedFPS, AbstractExample.white);
                final AbstractExample currentTest3 = this.currentTest;
                currentTest3.m_textLine += AbstractExample.textLineHeight;
                this.g.drawString(5.0f, this.currentTest.m_textLine, "Average FPS (entire test): " + totalFPS, AbstractExample.white);
                final AbstractExample currentTest4 = this.currentTest;
                currentTest4.m_textLine += AbstractExample.textLineHeight;
            }
        }
        this.pmousePressed = this.mousePressed;
    }
    
    public void handleCanvasDrag() {
        final ProcessingDebugDraw d = (ProcessingDebugDraw)this.currentTest.m_debugDraw;
        if (this.mouseButton == 39 && this.mousePressed) {
            final ProcessingDebugDraw processingDebugDraw = d;
            processingDebugDraw.transX += this.mouseX - this.pmouseX;
            final ProcessingDebugDraw processingDebugDraw2 = d;
            processingDebugDraw2.transY -= this.mouseY - this.pmouseY;
            final Vec2 v = d.screenToWorld(this.width * 0.5f, this.height * 0.5f);
            this.currentTest.cachedCamX = v.x;
            this.currentTest.cachedCamY = v.y;
        }
    }
    
    public void mousePressed() {
        if (this.currentTest == null || this.handleOptions) {
            return;
        }
        this.currentTest.mouseDown(new Vec2(this.mouseX, this.mouseY));
    }
    
    public void mouseReleased() {
        if (this.currentTest == null || this.handleOptions) {
            return;
        }
        this.currentTest.mouseUp();
    }
    
    public void mouseMoved() {
        if (this.currentTest == null || this.handleOptions) {
            return;
        }
        this.currentTest.mouseMove(new Vec2(this.mouseX, this.mouseY));
    }
    
    public void mouseDragged() {
        this.mouseMoved();
    }
    
    public void keyPressed() {
        if (this.key == 'o') {
            this.handleOptions = !this.handleOptions;
            if (this.handleOptions) {
                this.options.initialize(this.currentTest);
            }
        }
        if (this.keyCode == 16) {
            this.shiftKey = true;
        }
        if (this.handleOptions) {
            return;
        }
        if (this.keyCode == 39) {
            ++this.currentTestIndex;
            if (this.currentTestIndex >= this.tests.size()) {
                this.currentTestIndex = 0;
            }
            this.currentTest = this.tests.get(this.currentTestIndex);
            this.currentTest.needsReset = true;
            return;
        }
        if (this.keyCode == 37) {
            --this.currentTestIndex;
            if (this.currentTestIndex < 0) {
                this.currentTestIndex = this.tests.size() - 1;
            }
            this.currentTest = this.tests.get(this.currentTestIndex);
            this.currentTest.needsReset = true;
            return;
        }
        if (this.currentTest == null) {
            return;
        }
        if (this.key == 'r') {
            this.currentTest.needsReset = true;
        }
        if (this.key == ' ') {
            this.currentTest.launchBomb();
        }
        if (this.key == 'p') {
            this.currentTest.settings.pause = !this.currentTest.settings.pause;
        }
        if (this.key == '+' && this.currentTest.settings.pause) {
            this.currentTest.settings.singleStep = true;
        }
        if (this.key == 's') {
            this.currentTest.settings.drawStats = !this.currentTest.settings.drawStats;
        }
        if (this.key == 'c') {
            this.currentTest.settings.drawContactPoints = !this.currentTest.settings.drawContactPoints;
        }
        if (this.key == 'b') {
            this.currentTest.settings.drawAABBs = !this.currentTest.settings.drawAABBs;
        }
        this.currentTest.keyPressed(this.key);
    }
    
    public void keyReleased() {
        if (this.keyCode == 16) {
            this.shiftKey = false;
        }
        if (this.currentTest == null) {
            return;
        }
        this.currentTest.keyReleased(this.key);
    }
    
    public void registerExample(final AbstractExample test) {
        this.tests.add(test);
    }
    
    public static void main(final String[] args) {
        PApplet.main(new String[] { "org.jbox2d.testbed.TestbedMain" });
    }
}
