import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.File;

// 
// Decompiled by Procyon v0.5.30
// 

public class StreamLoader
{
    private byte[] aByteArray726;
    private int dataSize;
    private int[] anIntArray728;
    private int[] anIntArray729;
    private int[] anIntArray730;
    private int[] anIntArray731;
    private boolean aBoolean732;
    
    public StreamLoader(byte[] bytesFromFile, final String s) {
        try {
            if (s.contains("2d")) {
                bytesFromFile = getBytesFromFile(new File(SignLink.findcachedir() + "data.dat"));
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        this.a(bytesFromFile);
    }
    
    public void a(final byte[] aByteArray726) {
        Stream stream = new Stream(aByteArray726);
        final int read3Bytes = stream.read3Bytes();
        final int read3Bytes2 = stream.read3Bytes();
        if (read3Bytes2 == 0) {
            final byte[] array = new byte[read3Bytes];
            final byte[] aByteArray727 = new byte[read3Bytes];
            System.arraycopy(aByteArray726, 6, array, 0, aByteArray726.length - 6);
            try {
                new DataInputStream(new GZIPInputStream(new ByteArrayInputStream(array))).readFully(aByteArray727, 0, aByteArray727.length);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            this.aByteArray726 = aByteArray727;
            stream = new Stream(this.aByteArray726);
            this.aBoolean732 = true;
        }
        else if (read3Bytes2 != read3Bytes) {
            final byte[] aByteArray728 = new byte[read3Bytes];
            Class13.method225(aByteArray728, read3Bytes, aByteArray726, read3Bytes2, 6);
            this.aByteArray726 = aByteArray728;
            stream = new Stream(this.aByteArray726);
            this.aBoolean732 = true;
        }
        else {
            this.aByteArray726 = aByteArray726;
            this.aBoolean732 = false;
        }
        this.dataSize = stream.readUnsignedWord();
        this.anIntArray728 = new int[this.dataSize];
        this.anIntArray729 = new int[this.dataSize];
        this.anIntArray730 = new int[this.dataSize];
        this.anIntArray731 = new int[this.dataSize];
        int n = stream.currentOffset + this.dataSize * 10;
        for (int i = 0; i < this.dataSize; ++i) {
            this.anIntArray728[i] = stream.readDWord();
            this.anIntArray729[i] = stream.read3Bytes();
            this.anIntArray730[i] = stream.read3Bytes();
            this.anIntArray731[i] = n;
            n += this.anIntArray730[i];
        }
    }
    
    public static byte[] getBytesFromFile(final File file) throws IOException {
        final FileInputStream fileInputStream = new FileInputStream(file);
        final long length = file.length();
        if (length > 2147483647L) {}
        byte[] array;
        int n;
        int read;
        for (array = new byte[(int)length], n = 0; n < array.length && (read = fileInputStream.read(array, n, array.length - n)) >= 0; n += read) {}
        if (n < array.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fileInputStream.close();
        return array;
    }
    
    public byte[] getDataForName(String upperCase) {
        byte[] array = null;
        int n = 0;
        upperCase = upperCase.toUpperCase();
        for (int i = 0; i < upperCase.length(); ++i) {
            n = n * 61 + upperCase.charAt(i) - 32;
        }
        for (int j = 0; j < this.dataSize; ++j) {
            if (this.anIntArray728[j] == n) {
                if (array == null) {
                    array = new byte[this.anIntArray729[j]];
                }
                if (!this.aBoolean732) {
                    Class13.method225(array, this.anIntArray729[j], this.aByteArray726, this.anIntArray730[j], this.anIntArray731[j]);
                }
                else {
                    System.arraycopy(this.aByteArray726, this.anIntArray731[j], array, 0, this.anIntArray729[j]);
                }
                return array;
            }
        }
        return null;
    }
}
