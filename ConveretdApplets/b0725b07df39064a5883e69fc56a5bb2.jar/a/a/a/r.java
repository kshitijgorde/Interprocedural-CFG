// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.SourceDataLine;

public final class r
{
    private SourceDataLine a;
    
    public final void a() {
        try {
            if (this.a == null) {
                final AudioFormat audioFormat = new AudioFormat(22050.0f, 8, 1, true, false);
                (this.a = (SourceDataLine)AudioSystem.getLine(new DataLine.Info(SourceDataLine.class, audioFormat))).open(audioFormat);
                this.a.start();
            }
        }
        catch (Exception ex) {}
    }
    
    public final int a(final byte[] array) {
        int bufferSize = 0;
        if (this.a != null) {
            final int length = array.length;
            if (this.a.getBufferSize() > this.a.getBufferSize() - length) {
                this.a.write(array, 0, length);
            }
            bufferSize = this.a.getBufferSize();
        }
        return bufferSize;
    }
}
