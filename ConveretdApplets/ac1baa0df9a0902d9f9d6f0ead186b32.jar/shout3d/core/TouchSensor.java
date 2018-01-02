// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

import shout3d.math.MatUtil;

public class TouchSensor extends Group implements DeviceObserver
{
    static final String a = "STOP IMMEDIATELY!";
    static final String b = "Copyright (c) 1997-1999 Shout Interactive, Inc.";
    static final String c = "Contents of this file are property of Shout Interactive, Inc.";
    static final String d = "If you are reading this text, then you are in direct violation";
    static final String e = "of the terms of use and the terms of the license agreement.";
    static final String f = "YOU MUST CEASE YOUR EXAMINATION OF THIS FILE AND DESTROY THIS FILE IMMEDIATELY.";
    public final BooleanField enabled;
    public final FloatArrayField hitPoint;
    public final FloatArrayField hitNormal;
    public final BooleanField isOver;
    public final BooleanField isActive;
    public final DoubleField touchTime;
    private Picker g;
    private Node[] defaultChildArray;
    private int k;
    private boolean j;
    private boolean l;
    float[] h;
    float[] p;
    private Searcher i;
    private String m;
    private boolean n;
    
    private boolean a(final Node[] array, final Node[] array2) {
        if (array == null && array2 == null) {
            return true;
        }
        if (array == null || array2 == null) {
            return false;
        }
        if (array.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean a(final int n, final int n2) {
        g g = null;
        if (super.c != null && super.c.getRenderer() != null && super.c.getRenderer() instanceof g) {
            g = (g)super.c.getRenderer();
        }
        return g != null && g.cb && (n != g.cd || n2 != g.ce);
    }
    
    private void a(final boolean b, final boolean b2) {
        if (b && !this.j) {
            this.j = true;
            this.isOver.setValue(true);
        }
        else if (!b && this.j) {
            this.j = false;
            this.isOver.setValue(false);
        }
        if (b2 && this.n) {
            new a().a(super.c, this.j);
        }
    }
    
    public boolean onDeviceInput(final DeviceInput deviceInput, final Object o) {
        if (this.enabled.g) {
            switch (((MouseInput)deviceInput).which) {
                case 0: {
                    return this.d(((MouseInput)deviceInput).x, ((MouseInput)deviceInput).y);
                }
                case 3: {
                    if (this.l) {
                        return true;
                    }
                    return this.b(((MouseInput)deviceInput).x, ((MouseInput)deviceInput).y);
                }
                case 2: {
                    return this.b(((MouseInput)deviceInput).x, ((MouseInput)deviceInput).y);
                }
                case 1: {
                    if (this.l) {
                        this.l = false;
                        this.isActive.setValue(false);
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    public TouchSensor() {
        this(null);
    }
    
    public TouchSensor(final Shout3DViewer viewer) {
        this.enabled = new BooleanField(this, "enabled", 0, true);
        this.hitPoint = new FloatArrayField(this, "hitPoint", 6, null);
        this.hitNormal = new FloatArrayField(this, "hitNormal", 6, null);
        this.isOver = new BooleanField(this, "isOver", 0, false);
        this.isActive = new BooleanField(this, "isActive", 0, false);
        this.touchTime = new DoubleField(this, "touchTime", 14, -1.0);
        this.g = null;
        this.j = false;
        this.l = false;
        this.m = System.getProperty("java.version");
        this.n = (this.m.indexOf("1.0") <= -1);
        this.setViewer(viewer);
    }
    
    private int b(final Node[] array) {
        if (array == null) {
            return 0;
        }
        for (int i = array.length - 1; i >= 0; --i) {
            if (array[i] instanceof Group) {
                final Group group = (Group)array[i];
                int n = 0;
                if (!(group instanceof Anchor)) {
                    for (int j = 0; j < group.children.getValue().length; ++j) {
                        if (group.children.getValue()[j] == this && this.a(this)) {
                            this.k = i;
                            return 1;
                        }
                        if (group.children.getValue()[j] instanceof TouchSensor && this.a((TouchSensor)group.children.getValue()[j])) {
                            n = 1;
                        }
                    }
                }
                if (n == 1) {
                    return 2;
                }
                if (group instanceof TouchSensor) {
                    if (group == this) {
                        this.k = i;
                        return 1;
                    }
                    return 2;
                }
            }
        }
        return 0;
    }
    
    private boolean b(final int n, final int n2) {
        if (this.a(n, n2)) {
            return false;
        }
        this.c(n, n2);
        final int b = this.b(this.defaultChildArray);
        if (b != 0) {
            if (this.d() == null) {
                return false;
            }
            final Node[] defaultChildArray = this.defaultChildArray;
            this.g.setPickInfo(0, true);
            this.g.setPickInfo(1, true);
            this.defaultChildArray = this.g.pickClosest(n, n2);
            if (!this.a(this.defaultChildArray, defaultChildArray)) {
                return false;
            }
            this.h = this.g.getPickInfo(0);
            this.p = this.g.getPickInfo(1);
        }
        if (b == 0) {
            this.a(false, true);
        }
        else if (b == 1) {
            this.a(true, true);
        }
        else {
            this.a(false, false);
        }
        if (this.j) {
            final Node[] array = new Node[this.k];
            System.arraycopy(this.defaultChildArray, 0, array, 0, this.k);
            final float[] a = MatUtil.a(array, false);
            MatUtil.b(a, this.h);
            MatUtil.i(a, this.p);
            MatUtil.normalize(this.p);
            this.hitPoint.setValue(this.h);
            this.hitNormal.setValue(this.p);
            return true;
        }
        if (this.hitPoint.getValue() != null) {
            this.hitPoint.setValue(null);
        }
        if (this.hitNormal.getValue() != null) {
            this.hitNormal.setValue(null);
        }
        return false;
    }
    
    public void setViewer(final Shout3DViewer viewer) {
        if (super.c != null) {
            this.g = null;
            super.c.getDeviceListener().removeDeviceObserver(this, "MouseInput");
        }
        super.setViewer(viewer);
        if (super.c != null) {
            super.c.getDeviceListener().addDeviceObserver(this, "MouseInput", null);
        }
    }
    
    private Picker d() {
        if (this.g != null) {
            return this.g;
        }
        if (super.c == null) {
            return null;
        }
        return this.g = super.c.getNewPicker();
    }
    
    protected void e() {
        this.touchTime.setValue(super.c.getClock().getAbsoluteTime());
        this.isActive.setValue(true);
        this.l = true;
    }
    
    private void c(final int cd, final int ce) {
        g g = null;
        this.defaultChildArray = null;
        if (super.c != null && super.c.getRenderer() != null && super.c.getRenderer() instanceof g) {
            g = (g)super.c.getRenderer();
        }
        if (g != null && g.cb && cd == g.cd && ce == g.ce) {
            this.defaultChildArray = ((g)super.c.getRenderer()).cc;
            return;
        }
        if (this.d() == null) {
            return;
        }
        this.g.setPickInfo(0, false);
        this.g.setPickInfo(1, false);
        this.defaultChildArray = this.g.pickClosest(cd, ce);
        if (g != null) {
            g.cc = this.defaultChildArray;
            g.cd = cd;
            g.ce = ce;
            g.cb = true;
        }
    }
    
    private boolean a(final TouchSensor touchSensor) {
        return touchSensor.children.getValue() == null || touchSensor.children.getValue().length == 0;
    }
    
    protected void finalize() throws Throwable {
        if (super.c != null) {
            super.c.getDeviceListener().removeDeviceObserver(this, "MouseInput");
        }
        super.finalize();
    }
    
    private boolean d(final int n, final int n2) {
        this.c(n, n2);
        if (this.b(this.defaultChildArray) == 1) {
            this.e();
            return true;
        }
        return false;
    }
}
