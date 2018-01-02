// 
// Decompiled by Procyon v0.5.30
// 

package matt;

import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioFormat;

public class TonePlayer
{
    private static final float SAMPLE_RATE = 8000.0f;
    private static final float PI = 3.14159f;
    
    public static void playTone(final float frequency, final float duration, final float amplitude) {
        try {
            System.out.println("Playing tone: " + frequency);
            final byte[] audioData = new byte[(int)(8000.0f * duration)];
            final float xInc = 6.28318f / (8000.0f / frequency);
            float x = 0.0f;
            for (int i = 0; i < audioData.length; ++i) {
                audioData[i] = (byte)(Math.sin(x) * 127.0 * amplitude);
                x += xInc;
            }
            final AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 8000.0f, 8, 1, 1, 8000.0f, false);
            final DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            SourceDataLine line = null;
            line = (SourceDataLine)AudioSystem.getLine(info);
            line.open(audioFormat);
            line.start();
            line.write(audioData, 0, audioData.length);
            line.drain();
            line.flush();
            line.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
