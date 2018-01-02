// 
// Decompiled by Procyon v0.5.30
// 

package TestAndDevelopment;

import java.nio.MappedByteBuffer;
import java.io.RandomAccessFile;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.nio.ByteBuffer;
import com.otnip.irig106.chapter10.Packet;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import com.otnip.tools.StopWatch;
import java.io.File;

public class MappedFileStuff
{
    private static final long serialVersionUID = 0L;
    
    public static void main(final String[] args) {
        try {
            final File file = new File("/home/dlpinto/development/otnip/otnip/resources/data/chapter 10/Heim/METSAnalogVoice.C10");
            for (int xxx = 0; xxx < 300; ++xxx) {
                System.out.println();
                for (int x = 0; x < 3; ++x) {
                    final StopWatch sw = new StopWatch();
                    sw.start();
                    switch (x) {
                        case 0: {
                            final FileChannel fileChannel = new FileInputStream(file).getChannel();
                            final MappedByteBuffer mbb = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, file.length());
                            final Packet packet = new Packet();
                            for (int i = 0; i < 50000; ++i) {
                                packet.read(mbb);
                            }
                            break;
                        }
                        case 1: {
                            final FileInputStream fis = new FileInputStream(file);
                            final BufferedInputStream bis = new BufferedInputStream(fis);
                            final Packet packet = new Packet();
                            for (int i = 0; i < 50000; ++i) {
                                packet.read(bis);
                            }
                            break;
                        }
                        case 2: {
                            final RandomAccessFile ras = new RandomAccessFile(file, "r");
                            final Packet packet2 = new Packet();
                            for (int j = 0; j < 50000; ++j) {
                                packet2.read(ras);
                            }
                            break;
                        }
                    }
                    sw.stop();
                }
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
