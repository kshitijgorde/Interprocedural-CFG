// 
// Decompiled by Procyon v0.5.30
// 

package javazoom.jl.decoder;

public class OutputChannels
{
    public static final int BOTH_CHANNELS = 0;
    public static final int LEFT_CHANNEL = 1;
    public static final int RIGHT_CHANNEL = 2;
    public static final int DOWNMIX_CHANNELS = 3;
    public static final OutputChannels LEFT;
    public static final OutputChannels RIGHT;
    public static final OutputChannels BOTH;
    public static final OutputChannels DOWNMIX;
    private int outputChannels;
    
    public static OutputChannels fromInt(final int n) {
        switch (n) {
            case 1: {
                return OutputChannels.LEFT;
            }
            case 2: {
                return OutputChannels.RIGHT;
            }
            case 0: {
                return OutputChannels.BOTH;
            }
            case 3: {
                return OutputChannels.DOWNMIX;
            }
            default: {
                throw new IllegalArgumentException("Invalid channel code: " + n);
            }
        }
    }
    
    private OutputChannels(final int outputChannels) {
        this.outputChannels = outputChannels;
        if (outputChannels < 0 || outputChannels > 3) {
            throw new IllegalArgumentException("channels");
        }
    }
    
    public int getChannelsOutputCode() {
        return this.outputChannels;
    }
    
    public int getChannelCount() {
        return (this.outputChannels == 0) ? 2 : 1;
    }
    
    public boolean equals(final Object o) {
        boolean b = false;
        if (o instanceof OutputChannels) {
            b = (((OutputChannels)o).outputChannels == this.outputChannels);
        }
        return b;
    }
    
    public int hashCode() {
        return this.outputChannels;
    }
    
    static {
        LEFT = new OutputChannels(1);
        RIGHT = new OutputChannels(2);
        BOTH = new OutputChannels(0);
        DOWNMIX = new OutputChannels(3);
    }
}
