import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Lights implements Manager
{
    public static final int ALTERNATE = 0;
    public static final int SEQUENTIAL = 1;
    public Image lightOn;
    public Image lightOff;
    Graphic[] lights;
    Counter counter;
    int mode;
    
    public Lights(final Graphic[] lights) {
        this.lights = lights;
        this.counter = new Counter();
        this.set(0);
    }
    
    public final void increment() {
        this.counter.increment();
        switch (this.mode) {
            case 0: {
                for (int i = 0; i < this.lights.length; ++i) {
                    if (this.counter.toggle) {
                        if (i % 2 == 0) {
                            this.lights[i].image = this.lightOn;
                        }
                        else {
                            this.lights[i].image = this.lightOff;
                        }
                    }
                    else if (i % 2 == 1) {
                        this.lights[i].image = this.lightOn;
                    }
                    else {
                        this.lights[i].image = this.lightOff;
                    }
                }
            }
            case 1: {
                for (int j = 0; j < this.lights.length; ++j) {
                    if (j % (this.counter.maxCount + 1) == this.counter.count) {
                        this.lights[j].image = this.lightOn;
                    }
                    else {
                        this.lights[j].image = this.lightOff;
                    }
                }
            }
            default: {}
        }
    }
    
    public final void set(final int mode) {
        switch (this.mode = mode) {
            case 0: {
                this.counter.set(5, 0, true, true);
                break;
            }
            case 1: {
                this.counter.set(3, 5, false, true);
                break;
            }
        }
        this.counter.reset();
    }
}
