// 
// Decompiled by Procyon v0.5.30
// 

package org.jbox2d.testbed;

import org.jbox2d.dynamics.contacts.ContactPoint;
import org.jbox2d.dynamics.joints.Joint;
import org.jbox2d.collision.Shape;
import org.jbox2d.dynamics.joints.JointDef;
import org.jbox2d.dynamics.joints.MouseJointDef;
import processing.core.PImage;
import org.jbox2d.collision.ShapeDef;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.dynamics.BodyDef;
import java.util.Iterator;
import java.util.ArrayList;
import org.jbox2d.common.Color3f;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.BoundaryListener;
import org.jbox2d.dynamics.DestructionListener;
import org.jbox2d.collision.AABB;
import org.jbox2d.dynamics.joints.MouseJoint;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.DebugDraw;

public abstract class AbstractExample
{
    public TestbedMain parent;
    public DebugDraw m_debugDraw;
    public boolean[] keyDown;
    public boolean[] newKeyDown;
    public static String instructionString;
    public Vec2 mouseScreen;
    public Vec2 mouseWorld;
    public Vec2 pmouseScreen;
    public boolean pmousePressed;
    protected boolean needsReset;
    protected Vec2 bombSpawnPoint;
    protected boolean bombSpawning;
    protected int m_textLine;
    protected int m_pointCount;
    protected ContactPoint[] m_points;
    protected World m_world;
    protected Body m_bomb;
    protected MouseJoint m_mouseJoint;
    protected TestSettings settings;
    protected AABB m_worldAABB;
    public float memFree;
    protected DestructionListener m_destructionListener;
    protected BoundaryListener m_boundaryListener;
    protected ContactListener m_contactListener;
    public static Color3f white;
    public static Color3f black;
    public static Color3f gray;
    public static Color3f red;
    public static Color3f green;
    public static Color3f blue;
    public float cachedCamX;
    public float cachedCamY;
    public float cachedCamScale;
    public boolean hasCachedCamera;
    public static int textLineHeight;
    protected ArrayList<BoundImage> boundImages;
    static final int k_maxContactPoints = 2048;
    
    static {
        AbstractExample.instructionString = "Press left/right to change test\nUse the mouse to drag objects\nShift+drag to slingshot bomb\nPress 'o' to toggle options panel\n";
        AbstractExample.white = new Color3f(255.0f, 255.0f, 255.0f);
        AbstractExample.black = new Color3f(0.0f, 0.0f, 0.0f);
        AbstractExample.gray = new Color3f(127.5f, 127.5f, 127.5f);
        AbstractExample.red = new Color3f(255.0f, 0.0f, 0.0f);
        AbstractExample.green = new Color3f(0.0f, 255.0f, 0.0f);
        AbstractExample.blue = new Color3f(0.0f, 0.0f, 255.0f);
        AbstractExample.textLineHeight = 12;
    }
    
    public void printInstructions() {
        final String fullString = String.valueOf(AbstractExample.instructionString) + this.getExampleInstructions();
        final String[] instructionLines = fullString.split("\n");
        int currentLine = this.parent.height - instructionLines.length * AbstractExample.textLineHeight;
        for (int i = 0; i < instructionLines.length; ++i) {
            this.m_debugDraw.drawString(5.0f, currentLine, instructionLines[i], AbstractExample.white);
            currentLine += AbstractExample.textLineHeight;
        }
    }
    
    public String getExampleInstructions() {
        return "";
    }
    
    public abstract String getName();
    
    public abstract void create();
    
    public AbstractExample(final TestbedMain _parent) {
        this.keyDown = new boolean[255];
        this.newKeyDown = new boolean[255];
        this.mouseScreen = new Vec2();
        this.mouseWorld = new Vec2();
        this.pmouseScreen = new Vec2();
        this.needsReset = true;
        this.memFree = 0.0f;
        this.hasCachedCamera = false;
        this.boundImages = new ArrayList<BoundImage>();
        this.parent = _parent;
        this.m_debugDraw = this.parent.g;
        this.needsReset = true;
    }
    
    public void createWorld() {
        this.m_worldAABB = new AABB();
        this.m_worldAABB.lowerBound = new Vec2(-200.0f, -100.0f);
        this.m_worldAABB.upperBound = new Vec2(200.0f, 200.0f);
        final Vec2 gravity = new Vec2(0.0f, -10.0f);
        final boolean doSleep = true;
        this.m_world = new World(this.m_worldAABB, gravity, doSleep);
    }
    
    public void initialize() {
        this.needsReset = false;
        this.m_textLine = 15;
        for (int i = 0; i < 255; ++i) {
            this.keyDown[i] = false;
            this.newKeyDown[i] = false;
        }
        this.settings = new TestSettings();
        this.mouseScreen = new Vec2(this.parent.mouseX, this.parent.mouseY);
        this.mouseWorld = new Vec2();
        this.pmouseScreen = new Vec2(this.mouseScreen.x, this.mouseScreen.y);
        this.pmousePressed = false;
        this.createWorld();
        this.m_bomb = null;
        this.m_mouseJoint = null;
        this.bombSpawnPoint = null;
        this.bombSpawning = false;
        this.m_points = new ContactPoint[2048];
        for (int i = 0; i < this.m_points.length; ++i) {
            this.m_points[i] = new ContactPoint();
        }
        this.m_destructionListener = new ConcreteDestructionListener();
        this.m_boundaryListener = new ConcreteBoundaryListener();
        this.m_contactListener = new ConcreteContactListener();
        ((ConcreteDestructionListener)this.m_destructionListener).test = this;
        ((ConcreteBoundaryListener)this.m_boundaryListener).test = this;
        ((ConcreteContactListener)this.m_contactListener).test = this;
        this.m_world.setListener(this.m_destructionListener);
        this.m_world.setListener(this.m_boundaryListener);
        this.m_world.setListener(this.m_contactListener);
        this.m_world.setDebugDraw(this.parent.g);
        if (this.hasCachedCamera) {
            this.m_debugDraw.setCamera(this.cachedCamX, this.cachedCamY, this.cachedCamScale);
        }
        else {
            this.m_debugDraw.setCamera(0.0f, 10.0f, 10.0f);
            this.hasCachedCamera = true;
            this.cachedCamX = 0.0f;
            this.cachedCamY = 10.0f;
            this.cachedCamScale = 10.0f;
        }
        this.boundImages.clear();
        this.create();
    }
    
    public void step() {
        this.preStep();
        this.mouseWorld.set(this.m_debugDraw.screenToWorld(this.mouseScreen));
        float timeStep = (this.settings.hz > 0.0f) ? (1.0f / this.settings.hz) : 0.0f;
        if (this.settings.pause) {
            if (this.settings.singleStep) {
                this.settings.singleStep = false;
            }
            else {
                timeStep = 0.0f;
            }
            this.m_debugDraw.drawString(5.0f, this.m_textLine, "****PAUSED - press '+' to take a single step, 'p' to unpause****", AbstractExample.white);
            this.m_textLine += AbstractExample.textLineHeight;
        }
        this.m_debugDraw.setFlags(0);
        if (this.settings.drawShapes) {
            this.m_debugDraw.appendFlags(1);
        }
        if (this.settings.drawJoints) {
            this.m_debugDraw.appendFlags(2);
        }
        if (this.settings.drawCoreShapes) {
            this.m_debugDraw.appendFlags(4);
        }
        if (this.settings.drawAABBs) {
            this.m_debugDraw.appendFlags(8);
        }
        if (this.settings.drawOBBs) {
            this.m_debugDraw.appendFlags(16);
        }
        if (this.settings.drawPairs) {
            this.m_debugDraw.appendFlags(32);
        }
        if (this.settings.drawCOMs) {
            this.m_debugDraw.appendFlags(64);
        }
        World.ENABLE_WARM_STARTING = this.settings.enableWarmStarting;
        World.ENABLE_POSITION_CORRECTION = this.settings.enablePositionCorrection;
        World.ENABLE_TOI = this.settings.enableTOI;
        this.m_pointCount = 0;
        this.m_world.step(timeStep, this.settings.iterationCount);
        if (this.m_bomb != null && this.m_bomb.isFrozen()) {
            this.m_world.destroyBody(this.m_bomb);
            this.m_bomb = null;
        }
        if (this.settings.drawStats) {
            this.m_debugDraw.drawString(5.0f, this.m_textLine, "proxies(max) = " + this.m_world.m_broadPhase.m_proxyCount + "(" + 2048 + "), pairs(max) = " + this.m_world.m_broadPhase.m_pairManager.m_pairCount + "(" + 16384 + ")", AbstractExample.white);
            this.m_textLine += AbstractExample.textLineHeight;
            this.m_debugDraw.drawString(5.0f, this.m_textLine, "bodies/contacts/joints = " + this.m_world.m_bodyCount + "/" + this.m_world.m_contactCount + "/" + this.m_world.m_jointCount, AbstractExample.white);
            this.m_textLine += AbstractExample.textLineHeight;
            this.m_debugDraw.drawString(5.0f, this.m_textLine, "position iterations = " + this.m_world.m_positionIterationCount, AbstractExample.white);
            this.m_textLine += AbstractExample.textLineHeight;
            final long memTot = Runtime.getRuntime().totalMemory();
            this.memFree = this.memFree * 0.9f + 0.1f * Runtime.getRuntime().freeMemory();
            this.m_debugDraw.drawString(5.0f, this.m_textLine, "total memory: " + memTot, AbstractExample.white);
            this.m_textLine += AbstractExample.textLineHeight;
            this.m_debugDraw.drawString(5.0f, this.m_textLine, "Average free memory: " + (long)this.memFree, AbstractExample.white);
            this.m_textLine += AbstractExample.textLineHeight;
        }
        if (this.m_mouseJoint != null) {
            final Body body = this.m_mouseJoint.m_body2;
            final Vec2 p1 = body.getWorldPoint(this.m_mouseJoint.m_localAnchor);
            final Vec2 p2 = this.m_mouseJoint.m_target;
            this.m_debugDraw.drawSegment(p1, p2, new Color3f(255.0f, 255.0f, 255.0f));
        }
        if (this.bombSpawning) {
            this.m_debugDraw.drawSolidCircle(this.bombSpawnPoint, 0.3f, new Vec2(1.0f, 0.0f), new Color3f(127.5f, 127.5f, 127.5f));
            this.m_debugDraw.drawSegment(this.bombSpawnPoint, this.mouseWorld, new Color3f(27.5f, 27.5f, 127.5f));
        }
        if (this.settings.drawContactPoints) {
            final float k_forceScale = 0.01f;
            final float k_axisScale = 0.3f;
            for (int i = 0; i < this.m_pointCount; ++i) {
                final ContactPoint point = this.m_points[i];
                if (point.state == 0) {
                    this.m_debugDraw.drawPoint(point.position, 0.3f, new Color3f(255.0f, 150.0f, 150.0f));
                }
                else if (point.state == 1) {
                    this.m_debugDraw.drawPoint(point.position, 0.1f, new Color3f(255.0f, 0.0f, 0.0f));
                }
                else {
                    this.m_debugDraw.drawPoint(point.position, 0.5f, new Color3f(0.0f, 155.0f, 155.0f));
                }
                if (this.settings.drawContactNormals) {
                    final Vec2 p3 = point.position;
                    final Vec2 p4 = new Vec2(p3.x + k_axisScale * point.normal.x, p3.y + k_axisScale * point.normal.y);
                    this.m_debugDraw.drawSegment(p3, p4, new Color3f(102.0f, 229.5f, 102.0f));
                }
                else if (this.settings.drawContactForces) {
                    final Vec2 p3 = point.position;
                    final Vec2 p4 = new Vec2(p3.x + k_forceScale * point.normalForce * point.normal.x, p3.y + k_forceScale * point.normalForce * point.normal.y);
                    this.m_debugDraw.drawSegment(p3, p4, new Color3f(229.5f, 229.5f, 76.5f));
                }
                if (this.settings.drawFrictionForces) {
                    final Vec2 tangent = Vec2.cross(point.normal, 1.0f);
                    final Vec2 p5 = point.position;
                    final Vec2 p6 = new Vec2(p5.x + k_forceScale * point.tangentForce * tangent.x, p5.y + k_forceScale * point.tangentForce * tangent.y);
                    this.m_debugDraw.drawSegment(p5, p6, new Color3f(229.5f, 229.5f, 76.5f));
                }
            }
        }
        for (final BoundImage b : this.boundImages) {
            b.draw();
        }
        this.printInstructions();
        this.pmouseScreen.set(this.mouseScreen);
        this.postStep();
        for (int j = 0; j < this.newKeyDown.length; ++j) {
            this.newKeyDown[j] = false;
        }
    }
    
    public void preStep() {
    }
    
    public void postStep() {
    }
    
    public void launchBomb() {
        final Vec2 pos = new Vec2(this.parent.random(-15.0f, 15.0f), 30.0f);
        final Vec2 vel = pos.mul(-5.0f);
        this.launchBomb(pos, vel);
    }
    
    public void launchBomb(final Vec2 position, final Vec2 velocity) {
        if (this.m_bomb != null) {
            this.m_world.destroyBody(this.m_bomb);
            this.m_bomb = null;
        }
        final BodyDef bd = new BodyDef();
        bd.allowSleep = true;
        bd.position = position.clone();
        bd.isBullet = true;
        (this.m_bomb = this.m_world.createDynamicBody(bd)).setLinearVelocity(velocity);
        final CircleDef sd = new CircleDef();
        sd.radius = 0.3f;
        sd.density = 20.0f;
        sd.restitution = 0.1f;
        final Vec2 minV = position.sub(new Vec2(0.3f, 0.3f));
        final Vec2 maxV = position.add(new Vec2(0.3f, 0.3f));
        final AABB aabb = new AABB(minV, maxV);
        final boolean inRange = this.m_world.m_broadPhase.inRange(aabb);
        if (inRange) {
            this.m_bomb.createShape(sd);
            this.m_bomb.setMassFromShapes();
        }
        else {
            System.out.println("Bomb not created - out of world AABB");
        }
    }
    
    public void spawnBomb(final Vec2 worldPt) {
        this.bombSpawnPoint = worldPt.clone();
        this.bombSpawning = true;
    }
    
    public void completeBombSpawn() {
        if (!this.bombSpawning) {
            return;
        }
        final float multiplier = 30.0f;
        final Vec2 mouseW = this.m_debugDraw.screenToWorld(this.mouseScreen);
        final Vec2 vel = this.bombSpawnPoint.sub(mouseW);
        vel.mulLocal(30.0f);
        this.launchBomb(this.bombSpawnPoint, vel);
        this.bombSpawning = false;
    }
    
    public void bindImage(final PImage p, final Vec2 localOffset, final float localRotation, final float localScale, final Body b) {
        this.boundImages.add(new BoundImage(p, localOffset, localRotation, localScale, b));
    }
    
    public void keyPressed(final int key) {
        if (key >= 0 && key < 255) {
            if (!this.keyDown[key]) {
                this.newKeyDown[key] = true;
            }
            this.keyDown[key] = true;
        }
    }
    
    public void keyReleased(final int key) {
        if (key >= 0 && key < 255) {
            this.keyDown[key] = false;
        }
    }
    
    public void mouseDown(Vec2 p) {
        if (this.parent.shiftKey) {
            this.spawnBomb(this.m_debugDraw.screenToWorld(p));
            return;
        }
        p = this.m_debugDraw.screenToWorld(p);
        assert this.m_mouseJoint == null;
        final Vec2 d = new Vec2(0.001f, 0.001f);
        final AABB aabb = new AABB(p.sub(d), p.add(d));
        final int k_maxCount = 10;
        final Shape[] shapes = this.m_world.query(aabb, k_maxCount);
        Body body = null;
        for (int j = 0; j < shapes.length; ++j) {
            final Body shapeBody = shapes[j].getBody();
            if (!shapeBody.isStatic()) {
                final boolean inside = shapes[j].testPoint(shapeBody.getXForm(), p);
                if (inside) {
                    body = shapes[j].m_body;
                    break;
                }
            }
        }
        if (body != null) {
            final MouseJointDef md = new MouseJointDef();
            md.body1 = this.m_world.m_groundBody;
            md.body2 = body;
            md.target.set(p);
            md.maxForce = 1000.0f * body.m_mass;
            this.m_mouseJoint = (MouseJoint)this.m_world.createJoint(md);
            body.wakeUp();
        }
    }
    
    public void mouseUp() {
        if (this.m_mouseJoint != null) {
            this.m_world.destroyJoint(this.m_mouseJoint);
            this.m_mouseJoint = null;
        }
        if (this.bombSpawning) {
            this.completeBombSpawn();
        }
    }
    
    public void mouseMove(final Vec2 p) {
        this.mouseScreen.set(p);
        if (this.m_mouseJoint != null) {
            this.m_mouseJoint.setTarget(this.m_debugDraw.screenToWorld(p));
        }
    }
    
    public void setCamera(final float x, final float y, final float scale) {
        this.m_debugDraw.setCamera(x, y, scale);
        this.hasCachedCamera = true;
        this.cachedCamX = x;
        this.cachedCamY = y;
        this.cachedCamScale = scale;
    }
    
    public void jointDestroyed(final Joint joint) {
    }
    
    public void boundaryViolated(final Body body) {
    }
    
    class ConcreteDestructionListener implements DestructionListener
    {
        public AbstractExample test;
        
        public void sayGoodbye(final Shape shape) {
        }
        
        public void sayGoodbye(final Joint joint) {
            if (this.test.m_mouseJoint == joint) {
                this.test.m_mouseJoint = null;
            }
            else {
                this.test.jointDestroyed(joint);
            }
        }
    }
    
    class ConcreteBoundaryListener implements BoundaryListener
    {
        public AbstractExample test;
        
        public void violation(final Body body) {
            if (this.test.m_bomb != body) {
                this.test.boundaryViolated(body);
            }
        }
    }
    
    class ConcreteContactListener implements ContactListener
    {
        public AbstractExample test;
        
        public void add(final org.jbox2d.dynamics.contacts.ContactPoint point) {
            if (this.test.m_pointCount == 2048) {
                return;
            }
            final ContactPoint cp = this.test.m_points[this.test.m_pointCount];
            cp.shape1 = point.shape1;
            cp.shape2 = point.shape2;
            cp.position = point.position.clone();
            cp.normal = point.normal.clone();
            cp.normalForce = point.normalForce;
            cp.tangentForce = point.tangentForce;
            cp.state = 0;
            final AbstractExample test = this.test;
            ++test.m_pointCount;
        }
        
        public void persist(final org.jbox2d.dynamics.contacts.ContactPoint point) {
            if (this.test.m_pointCount == 2048) {
                return;
            }
            final ContactPoint cp = this.test.m_points[this.test.m_pointCount];
            cp.shape1 = point.shape1;
            cp.shape2 = point.shape2;
            cp.position = point.position.clone();
            cp.normal = point.normal.clone();
            cp.normalForce = point.normalForce;
            cp.tangentForce = point.tangentForce;
            cp.state = 1;
            final AbstractExample test = this.test;
            ++test.m_pointCount;
        }
        
        public void remove(final org.jbox2d.dynamics.contacts.ContactPoint point) {
            if (this.test.m_pointCount == 2048) {
                return;
            }
            final ContactPoint cp = this.test.m_points[this.test.m_pointCount];
            cp.shape1 = point.shape1;
            cp.shape2 = point.shape2;
            cp.position = point.position.clone();
            cp.normal = point.normal.clone();
            cp.normalForce = point.normalForce;
            cp.tangentForce = point.tangentForce;
            cp.state = 2;
            final AbstractExample test = this.test;
            ++test.m_pointCount;
        }
    }
    
    class ContactPoint
    {
        public Shape shape1;
        public Shape shape2;
        public Vec2 normal;
        public Vec2 position;
        public float normalForce;
        public float tangentForce;
        public int state;
    }
    
    class BoundImage
    {
        private PImage image;
        private float halfImageWidth;
        private float halfImageHeight;
        private Body body;
        private Vec2 localOffset;
        private float localRotation;
        private float localScale;
        private ProcessingDebugDraw p;
        
        public BoundImage(final PImage _image, final Vec2 _localOffset, final float _localRotation, final float _localScale, final Body _body) {
            this.image = _image;
            this.localOffset = _localOffset.clone();
            this.localRotation = _localRotation;
            this.localScale = _localScale;
            this.body = _body;
            this.halfImageWidth = this.image.width / 2.0f;
            this.halfImageHeight = this.image.height / 2.0f;
            this.p = (ProcessingDebugDraw)AbstractExample.this.m_debugDraw;
        }
        
        public void draw() {
            this.p.drawImage(this.image, this.body.getPosition(), this.body.getAngle() + this.localRotation, this.localScale, this.localOffset, this.halfImageWidth, this.halfImageHeight);
        }
    }
}
