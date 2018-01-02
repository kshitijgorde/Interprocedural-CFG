// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class TimeSensorPauser extends Node implements FieldObserver
{
    public final NodeField timeSensor;
    public final BooleanField reset;
    public final BooleanField setPaused;
    double a;
    
    public TimeSensorPauser() {
        this.timeSensor = new NodeField(this, "timeSensor", 0, null);
        this.reset = new BooleanField(this, "reset", 0, false);
        this.setPaused = new BooleanField(this, "setPaused", 0, false);
        this.a = 0.0;
        this.reset.addFieldObserver(this, null);
        this.setPaused.addFieldObserver(this, null);
    }
    
    public void onFieldChange(final Field field, final Object o) {
        if (this.timeSensor != null && this.timeSensor.getValue() instanceof TimeSensor) {
            final TimeSensor timeSensor = (TimeSensor)this.timeSensor.getValue();
            if (field == this.setPaused) {
                final boolean value = ((BooleanField)field).getValue();
                if (!value && !timeSensor.isPaused()) {
                    if (this.getViewer() != null) {
                        timeSensor.startTime.setValue(this.getViewer().getClock().getAbsoluteTime());
                    }
                    timeSensor.enabled.setValue(true);
                }
                timeSensor.setPaused(value);
                return;
            }
            if (field == this.reset && this.getViewer() != null) {
                timeSensor.setPaused(false);
                this.a();
                timeSensor.update();
                timeSensor.stop();
                this.a();
                timeSensor.update();
                timeSensor.startTime.setValue(this.getViewer().getClock().getAbsoluteTime());
                timeSensor.enabled.setValue(true);
            }
        }
    }
    
    private void a() {
        while (this.a == this.getViewer().getClock().getAbsoluteTime()) {
            this.getViewer().getClock().tick();
        }
        this.a = this.getViewer().getClock().getAbsoluteTime();
    }
    
    protected void finalize() throws Throwable {
        this.reset.removeFieldObserver(this);
        this.setPaused.removeFieldObserver(this);
        super.finalize();
    }
}
