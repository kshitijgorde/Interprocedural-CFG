// 
// Decompiled by Procyon v0.5.30
// 

package classes.anaxee.neurotec;

import java.nio.channels.FileChannel;
import java.io.FileOutputStream;
import java.io.File;
import java.nio.ByteBuffer;

public class SaveImage
{
    public void saveImageFromByteBuffer(final ByteBuffer byteBuffer, final String s) {
        final File file = new File(s);
        try {
            final FileChannel channel = new FileOutputStream(file, false).getChannel();
            channel.write(byteBuffer);
            channel.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
