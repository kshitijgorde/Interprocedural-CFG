// 
// Decompiled by Procyon v0.5.30
// 

package org.xidget.swing.image;

import java.util.Iterator;
import java.nio.channels.ReadableByteChannel;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.nio.channels.Channels;
import java.io.IOException;
import org.xmodel.external.CachingException;
import javax.swing.ImageIcon;
import java.io.InputStream;
import org.xmodel.IModelObject;
import org.xmodel.caching.AbstractFileAssociation;

public class ImageFileAssociation extends AbstractFileAssociation
{
    @Override
    public void apply(final IModelObject modelObject, final String s, final InputStream inputStream) throws CachingException {
        try {
            modelObject.setValue(new ImageIcon(this.readAll(inputStream)));
        }
        catch (IOException ex) {
            throw new CachingException("Unable to load image: " + s, ex);
        }
    }
    
    private byte[] readAll(final InputStream inputStream) throws IOException {
        final ReadableByteChannel channel = Channels.newChannel(inputStream);
        int i = 1;
        int n = 0;
        final ArrayList<ByteBuffer> list = new ArrayList<ByteBuffer>();
        while (i > 0) {
            final ByteBuffer allocate = ByteBuffer.allocate(65536);
            i = channel.read(allocate);
            if (i > 0) {
                allocate.flip();
                n += i;
                list.add(allocate);
            }
        }
        int n2 = 0;
        final byte[] array = new byte[n];
        for (final ByteBuffer byteBuffer : list) {
            System.arraycopy(byteBuffer.array(), 0, array, n2, byteBuffer.remaining());
            n2 += byteBuffer.remaining();
        }
        return array;
    }
    
    @Override
    public String[] getExtensions() {
        return new String[] { ".jpg", ".jpeg", ".gif", ".png" };
    }
}
