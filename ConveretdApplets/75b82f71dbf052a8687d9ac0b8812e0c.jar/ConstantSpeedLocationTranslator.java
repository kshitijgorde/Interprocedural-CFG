import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// 
// Decompiled by Procyon v0.5.30
// 

public class ConstantSpeedLocationTranslator extends Translator
{
    public static final boolean LOG_DEBUG = false;
    public static final int ROTATE_LEFT = 0;
    public static final int ROTATE_RIGHT = 1;
    public static final int ROTATE_SHORTEST = 2;
    protected float[] m_target;
    protected float[] m_startVP;
    protected int m_dir;
    protected float m_speed;
    protected boolean m_panDone;
    protected boolean m_tiltDone;
    protected boolean m_rollDone;
    protected boolean m_zoomDone;
    protected List m_updateFrequency;
    protected long m_lastUpdate;
    
    public ConstantSpeedLocationTranslator(final Controller controller, final float[] vpTarget, final int method) {
        this.m_target = new float[] { 0.0f, 0.0f, 0.0f, 1.0f };
        this.m_startVP = null;
        this.m_dir = 2;
        this.m_speed = 0.20943952f;
        this.m_panDone = false;
        this.m_tiltDone = false;
        this.m_rollDone = false;
        this.m_zoomDone = false;
        this.m_updateFrequency = new LinkedList();
        this.m_lastUpdate = 0L;
        this.m_controller = controller;
        (this.m_target = Util.copy(vpTarget))[3] = Math.max(this.m_target[3], 0.4f);
        this.m_dir = method;
        this.m_updateFrequency.add(new Float(30.0f));
    }
    
    boolean updateViewpoint(float[] vp) {
        if (this.m_startVP == null) {
            this.m_startVP = Util.copy(vp);
        }
        if (!this.m_controller.isActive(this)) {
            return false;
        }
        if (this.compare(vp, this.m_target, new float[] { 1.0E-4f, 1.0E-4f, 1.0E-4f, 1.0E-4f })) {
            vp = this.m_target;
            this.m_controller.retireControl(this);
            return true;
        }
        final long now = queryHiResCounter();
        if (this.m_lastUpdate != 0L) {
            final long elapsed = now - this.m_lastUpdate;
            this.m_updateFrequency.add(new Float(elapsed));
        }
        this.m_lastUpdate = now;
        float panRight;
        for (panRight = this.m_target[0] - vp[0]; panRight < 0.0f; panRight += 6.283185307179586) {}
        while (panRight > 6.283185307179586) {
            panRight -= 6.283185307179586;
        }
        float panLeft;
        for (panLeft = this.m_target[0] - vp[0]; panLeft < -6.283185307179586; panLeft += 6.283185307179586) {}
        while (panLeft > 0.0f) {
            panLeft -= 6.283185307179586;
        }
        float deltaP = 0.0f;
        float deltaT = 0.0f;
        float deltaR = 0.0f;
        final float[] delta = { 0.0f, 0.0f, 0.0f, 1.0f };
        switch (this.m_dir) {
            case 0: {
                deltaP = panLeft;
                break;
            }
            case 1: {
                deltaP = panRight;
                break;
            }
            case 2: {
                deltaP = ((Math.abs(panRight) > Math.abs(panLeft)) ? panLeft : panRight);
                break;
            }
        }
        deltaR = this.m_target[2] - vp[2];
        deltaT = this.m_target[1] - vp[1];
        final float speed = this.getSpeed();
        if (speed <= 0.0f) {
            vp = this.m_target;
            this.m_controller.retireControl(this);
            return true;
        }
        final float frequency = this.getUpdateFrequency();
        float denom = Math.max(Math.abs(deltaP), Math.abs(deltaT));
        denom = Math.max(denom, Math.abs(deltaR));
        if (denom == 0.0f) {
            delta[0] = 0.0f;
        }
        else {
            delta[0] = deltaP / denom * speed * frequency;
        }
        denom = Math.max(Math.abs(deltaP), Math.abs(deltaT));
        denom = Math.max(denom, Math.abs(deltaR));
        if (denom == 0.0f) {
            delta[1] = 0.0f;
        }
        else {
            delta[1] = deltaT / denom * speed * frequency;
        }
        denom = Math.max(Math.abs(deltaP), Math.abs(deltaT));
        denom = Math.max(denom, Math.abs(deltaR));
        if (denom == 0.0f) {
            delta[2] = 0.0f;
        }
        else {
            delta[2] = deltaR / denom * speed * frequency;
        }
        float steps = Math.max(Math.abs(deltaP), Math.abs(deltaT));
        steps = Math.max(steps, Math.abs(deltaR));
        steps /= speed * frequency;
        if (steps == 0.0f) {
            steps = Math.abs(this.m_target[3] - vp[3]) / (speed * frequency);
        }
        final float ratio = (steps - 1.0f) / steps;
        final float zoom = (float)Math.exp(Math.log(this.m_target[3]) - (Math.log(this.m_target[3]) - Math.log(vp[3])) * ratio);
        delta[3] = zoom - vp[3];
        if (this.compare(vp, this.m_target, delta)) {
            vp = this.m_target;
            this.m_controller.retireControl(this);
            return true;
        }
        if (!this.m_panDone) {
            final float[] array = vp;
            final int n = 0;
            array[n] += delta[0];
        }
        if (!this.m_tiltDone) {
            final float[] array2 = vp;
            final int n2 = 1;
            array2[n2] += delta[1];
        }
        if (!this.m_rollDone) {
            final float[] array3 = vp;
            final int n3 = 2;
            array3[n3] += delta[2];
        }
        if (!this.m_zoomDone) {
            vp[3] = zoom;
        }
        return true;
    }
    
    public float getSpeed() {
        return this.m_speed;
    }
    
    public final boolean compare(final float[] currentVP, final float[] targetVP, final float[] delta) {
        final float deltaP = delta[0];
        final float deltaT = delta[1];
        final float deltaR = delta[2];
        final float deltaZ = delta[3];
        float dp = Math.abs(currentVP[0] - targetVP[0]);
        if (dp <= 1.0E-4f) {
            dp = 0.0f;
        }
        float dt = Math.abs(currentVP[1] - targetVP[1]);
        if (dt <= 1.0E-4f) {
            dt = 0.0f;
        }
        float dr = Math.abs(currentVP[2] - targetVP[2]);
        if (dr <= 1.0E-4f) {
            dr = 0.0f;
        }
        float dz = Math.abs(currentVP[3] - targetVP[3]);
        if (dz <= 1.0E-4f) {
            dz = 0.0f;
        }
        if (dp <= Math.abs(deltaP)) {
            this.m_panDone = true;
        }
        if (dt <= Math.abs(deltaT)) {
            this.m_tiltDone = true;
        }
        if (dr <= Math.abs(deltaR)) {
            this.m_rollDone = true;
        }
        if (dz <= Math.abs(deltaZ)) {
            this.m_zoomDone = true;
        }
        return this.m_panDone && this.m_tiltDone && this.m_rollDone && this.m_zoomDone;
    }
    
    protected float getUpdateFrequency() {
        float sum = 0.0f;
        final Iterator iter = this.m_updateFrequency.iterator();
        while (iter.hasNext()) {
            sum += iter.next();
        }
        final float avarage = sum / this.m_updateFrequency.size();
        return avarage * 0.001f;
    }
    
    public static long queryHiResCounter() {
        return new Date().getTime();
    }
}
