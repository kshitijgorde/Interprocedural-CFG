// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class TimeSensor extends Node
{
    public final DoubleField cycleInterval;
    public final DoubleField cycleTime;
    public final DoubleField elapsedSeconds;
    public final BooleanField enabled;
    public final FloatField fraction;
    public final BooleanField isActive;
    public final BooleanField loop;
    public final IntField numLoops;
    public final DoubleField startTime;
    public final DoubleField stopTime;
    public final DoubleField time;
    protected Clock a;
    protected boolean b;
    protected double c;
    protected boolean d;
    protected double e;
    protected int f;
    protected double g;
    protected int h;
    protected double i;
    protected double j;
    private boolean k;
    private double l;
    
    public void stop() {
        this.stopTime.setValue(System.currentTimeMillis() / 1000.0);
    }
    
    public void setPaused(final boolean b) {
        if (!this.d || this.a == null) {
            return;
        }
        if (!this.b) {
            if (b) {
                this.b = true;
                this.c = this.a.getAbsoluteTime();
            }
        }
        else if (!b) {
            this.b = false;
            this.e += this.a.getAbsoluteTime() - this.c;
        }
    }
    
    public TimeSensor() {
        this(null);
    }
    
    public TimeSensor(final Shout3DViewer viewer) {
        this.cycleInterval = new DoubleField(this, "cycleInterval", 14, 1.0);
        this.cycleTime = new DoubleField(this, "cycleTime", 14, 0.0);
        this.elapsedSeconds = new DoubleField(this, "elapsedSeconds", 14, 0.0);
        this.enabled = new BooleanField(this, "enabled", 0, true);
        this.fraction = new FloatField(this, "fraction", 15, 0.0f);
        this.isActive = new BooleanField(this, "isActive", 0, false);
        this.loop = new BooleanField(this, "loop", 0, false);
        this.numLoops = new IntField(this, "numLoops", 0, 1);
        this.startTime = new DoubleField(this, "startTime", 0, 0.0);
        this.stopTime = new DoubleField(this, "stopTime", 0, 0.0);
        this.time = new DoubleField(this, "time", 14, 0.0);
        this.b = false;
        this.c = 0.0;
        this.d = false;
        this.e = 0.0;
        this.f = 0;
        this.g = 0.0;
        this.h = 0;
        this.k = true;
        this.l = -1.0;
        this.setViewer(viewer);
    }
    
    public boolean isPaused() {
        return this.b;
    }
    
    protected boolean a(final double n) {
        return this.d && ((n >= this.stopTime.a && this.stopTime.a > this.startTime.a) || (this.f != -1 && !this.loop.g && n >= this.e + this.f * this.g));
    }
    
    protected void a() {
        this.h = 0;
        this.d = false;
        this.b = false;
        this.isActive.setValue(false);
    }
    
    public void setViewer(final Shout3DViewer viewer) {
        if (super.c != null) {
            this.a = null;
        }
        super.setViewer(viewer);
        if (super.c != null) {
            this.a = super.c.getClock();
        }
    }
    
    public void update() {
        if (this.a == null) {
            return;
        }
        if (!this.k && !this.enabled.g) {
            this.l = -1.0;
            return;
        }
        this.k = this.enabled.g;
        this.i = this.a.getAbsoluteTime();
        if (this.i == this.j) {
            return;
        }
        if (this.b(this.i)) {
            this.b();
        }
        if (this.d && !this.b) {
            final float n = (float)(this.i - this.e);
            final double value = n % this.g;
            final int n2 = (int)(n / this.g);
            boolean b = false;
            if (n2 > this.h) {
                ++this.h;
                this.cycleTime.setValue(this.i);
                if (!this.loop.g && this.f > 0 && this.h >= this.f) {
                    b = true;
                }
            }
            if (b) {
                this.fraction.setValue(1.0f);
                this.elapsedSeconds.setValue(this.g);
            }
            else {
                this.fraction.setValue((float)(value / this.g));
                this.elapsedSeconds.setValue(value);
            }
            this.time.setValue(this.i);
        }
        if (this.a(this.i)) {
            this.a();
        }
        this.l = this.i;
        this.j = this.i;
    }
    
    public Node[] a(final Node[] array) {
        Node[] array2;
        if (array == null) {
            array2 = new Node[] { null };
        }
        else {
            array2 = new Node[array.length + 1];
            System.arraycopy(array, 0, array2, 0, array.length);
        }
        array2[array2.length - 1] = this;
        return array2;
    }
    
    public void start() {
        this.startTime.setValue(System.currentTimeMillis() / 1000.0);
    }
    
    protected boolean b(final double n) {
        return !this.d && n >= this.startTime.a && ((this.l != -1.0 && this.l != n && this.l <= this.startTime.a && n >= this.startTime.a) || ((this.stopTime.a <= this.startTime.a || this.stopTime.a >= n) && (this.numLoops.b < 0 || this.loop.g || n <= this.startTime.a + this.numLoops.b * this.cycleInterval.a)));
    }
    
    protected void b() {
        if (this.a == null) {
            return;
        }
        this.e = this.a.getAbsoluteTime();
        this.g = this.cycleInterval.a;
        this.f = this.numLoops.b;
        this.h = 0;
        this.d = true;
        this.isActive.setValue(true);
        this.cycleTime.setValue(this.e);
    }
}
