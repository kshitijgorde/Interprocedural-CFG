import java.nio.ByteBuffer;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class317_Sub1 extends Class317
{
    private ByteBuffer aByteBuffer5305;
    
    @Override
    final byte[] method3653(final int n, final int n2, final boolean b) {
        byte[] array2;
        try {
            final byte[] array = new byte[n2];
            this.aByteBuffer5305.position(n);
            this.aByteBuffer5305.get(array, 0, n2);
            if (b) {
                this.aByteBuffer5305 = null;
            }
            array2 = array;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return array2;
    }
    
    @Override
    final byte[] method3654(final boolean b) {
        byte[] array2;
        try {
            if (b) {
                this.aByteBuffer5305 = null;
            }
            final byte[] array = new byte[this.aByteBuffer5305.capacity()];
            this.aByteBuffer5305.position(0);
            this.aByteBuffer5305.get(array);
            array2 = array;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        return array2;
    }
    
    @Override
    final void method3652(final byte b, final byte[] array) {
        try {
            (this.aByteBuffer5305 = ByteBuffer.allocateDirect(array.length)).position(0);
            this.aByteBuffer5305.put(array);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
}
