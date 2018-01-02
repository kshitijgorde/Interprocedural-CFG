// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.compress;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Set;
import java.io.DataOutput;
import java.io.DataInput;
import java.util.Iterator;
import java.util.Collection;
import org.xmodel.Xlate;
import org.xmodel.ModelAlgorithms;
import java.io.DataInputStream;
import java.util.zip.InflaterInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.xmodel.IModelObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.xmodel.ModelObjectFactory;
import java.util.Map;
import java.util.List;

public class TabularCompressor extends AbstractCompressor
{
    private List<String> C;
    private Map<String, Integer> E;
    private int B;
    private boolean D;
    private PostCompression A;
    
    public TabularCompressor() {
        this(PostCompression.zip);
    }
    
    public TabularCompressor(final PostCompression a) {
        this.A = a;
        this.factory = new ModelObjectFactory();
        this.E = new LinkedHashMap<String, Integer>();
        this.C = new ArrayList<String>();
        this.D = false;
    }
    
    public void defineTagTable(final List<String> c) {
        this.C = c;
        this.E.clear();
        for (int i = 0; i < c.size(); ++i) {
            this.E.put(c.get(i), i);
        }
        this.D = true;
    }
    
    public void clearTagTable() {
        this.E.clear();
        this.C.clear();
        this.D = false;
    }
    
    @Override
    public void compress(final IModelObject modelObject, final OutputStream outputStream) throws CompressorException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        final DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            this.C(dataOutputStream, modelObject);
            final boolean b = byteArrayOutputStream.size() > 256;
            int n = 0;
            if (b && this.A == PostCompression.zip) {
                n = (byte)(n | 0x80);
            }
            if (b && this.A == PostCompression.bzip2) {
                n = (byte)(n | 0x40);
            }
            if (this.D) {
                n = (byte)(n | 0x20);
            }
            outputStream.write(n);
            OutputStream outputStream2 = outputStream;
            if (b && this.A == PostCompression.zip) {
                outputStream2 = new DeflaterOutputStream(outputStream);
            }
            if (!this.D) {
                final DataOutputStream dataOutputStream2 = new DataOutputStream(outputStream2);
                this.A(dataOutputStream2);
                dataOutputStream2.flush();
            }
            dataOutputStream.flush();
            outputStream2.write(byteArrayOutputStream.toByteArray());
            outputStream2.flush();
            outputStream2.close();
        }
        catch (IOException ex) {
            throw new CompressorException(ex);
        }
        this.D = true;
    }
    
    @Override
    public IModelObject decompress(final InputStream inputStream) throws CompressorException {
        try {
            final byte b = (byte)inputStream.read();
            final boolean b2 = (b & 0x20) != 0x0;
            PostCompression postCompression = PostCompression.none;
            if ((b & 0x80) != 0x0) {
                postCompression = PostCompression.zip;
            }
            if ((b & 0x40) != 0x0) {
                postCompression = PostCompression.bzip2;
            }
            InputStream inputStream2 = inputStream;
            if (postCompression == PostCompression.zip) {
                inputStream2 = new InflaterInputStream(inputStream);
            }
            final DataInputStream dataInputStream = new DataInputStream(inputStream2);
            if (!b2) {
                this.F(dataInputStream);
            }
            final IModelObject c = this.C(dataInputStream);
            dataInputStream.close();
            return c;
        }
        catch (IOException ex) {
            throw new CompressorException("Error in data stream: ", ex);
        }
    }
    
    private IModelObject C(final DataInputStream dataInputStream) throws IOException, CompressorException {
        final IModelObject object = this.factory.createObject(null, this.D(dataInputStream));
        this.B(dataInputStream, object);
        this.A(dataInputStream, object);
        return object;
    }
    
    private void C(final DataOutputStream dataOutputStream, final IModelObject modelObject) throws IOException, CompressorException {
        this.B(dataOutputStream, modelObject.getType());
        this.B(dataOutputStream, modelObject);
        this.A(dataOutputStream, modelObject);
    }
    
    private void B(final DataInputStream dataInputStream, final IModelObject modelObject) throws IOException, CompressorException {
        boolean b = false;
        int read = dataInputStream.read();
        if (read > 127) {
            read -= 128;
            b = true;
        }
        if (b) {
            for (int i = 0; i < read; ++i) {
                final String d = this.D(dataInputStream);
                try {
                    modelObject.setAttribute(d, this.A(dataInputStream));
                }
                catch (ClassNotFoundException ex) {
                    throw new CompressorException(String.format("Unable to deserialize attribute, %s.", d), ex);
                }
            }
        }
        else {
            for (int j = 0; j < read; ++j) {
                modelObject.setAttribute(this.D(dataInputStream), this.E(dataInputStream));
            }
        }
    }
    
    private void A(final DataInputStream dataInputStream, final IModelObject modelObject) throws IOException, CompressorException {
        for (int b = this.B(dataInputStream), i = 0; i < b; ++i) {
            modelObject.addChild(this.C(dataInputStream));
        }
    }
    
    private void B(final DataOutputStream dataOutputStream, final IModelObject modelObject) throws IOException, CompressorException {
        final Collection<String> attributeNames = modelObject.getAttributeNames();
        boolean b = false;
        final int size = attributeNames.size();
        if (size > 127) {
            throw new CompressorException(String.format("Element has more than 127 attributes, %s.", ModelAlgorithms.createIdentityPath(modelObject).toString()));
        }
        final Iterator<String> iterator = attributeNames.iterator();
        while (iterator.hasNext()) {
            if (!(modelObject.getAttribute(iterator.next()) instanceof CharSequence)) {
                b = true;
                break;
            }
        }
        if (b) {
            dataOutputStream.write(size + 128);
            for (final String s : attributeNames) {
                this.B(dataOutputStream, s);
                this.A(dataOutputStream, modelObject.getAttribute(s));
            }
        }
        else {
            dataOutputStream.write(size);
            for (final String s2 : attributeNames) {
                this.B(dataOutputStream, s2);
                this.A(dataOutputStream, Xlate.get(modelObject, s2, ""));
            }
        }
    }
    
    private void A(final DataOutputStream dataOutputStream, final IModelObject modelObject) throws IOException, CompressorException {
        final List<IModelObject> children = modelObject.getChildren();
        this.A(dataOutputStream, children.size());
        final Iterator<IModelObject> iterator = children.iterator();
        while (iterator.hasNext()) {
            this.C(dataOutputStream, iterator.next());
        }
    }
    
    private String D(final DataInputStream dataInputStream) throws IOException, CompressorException {
        final int b = this.B(dataInputStream);
        if (b >= this.C.size()) {
            throw new CompressorException("Table entry not found.");
        }
        return this.C.get(b);
    }
    
    private void B(final DataOutputStream dataOutputStream, final String s) throws IOException, CompressorException {
        Integer value = this.E.get(s);
        if (value == null) {
            value = this.B++;
            this.C.add(s);
            this.E.put(s, value);
            this.D = false;
        }
        this.A(dataOutputStream, (int)value);
    }
    
    private String E(final DataInputStream dataInputStream) throws IOException {
        final byte[] array = new byte[this.B(dataInputStream)];
        dataInputStream.readFully(array);
        return new String(array);
    }
    
    private void A(final DataOutputStream dataOutputStream, final String s) throws CompressorException, IOException {
        this.A(dataOutputStream, s.length());
        dataOutputStream.writeBytes(s);
    }
    
    private Object A(final DataInputStream dataInputStream) throws IOException, ClassNotFoundException {
        return this.serializer.readObject(dataInputStream);
    }
    
    private int A(final DataOutputStream dataOutputStream, final Object o) throws IOException {
        return this.serializer.writeObject(dataOutputStream, o);
    }
    
    private void F(final DataInputStream dataInputStream) throws IOException, CompressorException {
        this.C = new ArrayList<String>();
        final int b = this.B(dataInputStream);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b; ++i) {
            sb.setLength(0);
            for (byte b2 = dataInputStream.readByte(); b2 != 0; b2 = dataInputStream.readByte()) {
                sb.append((char)b2);
            }
            this.C.add(sb.toString());
        }
    }
    
    private void A(final DataOutputStream dataOutputStream) throws IOException, CompressorException {
        final Set<String> keySet = this.E.keySet();
        this.A(dataOutputStream, keySet.size());
        final Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            dataOutputStream.writeBytes(iterator.next());
            dataOutputStream.write(0);
        }
    }
    
    private int B(final DataInputStream dataInputStream) throws IOException {
        final int unsignedByte = dataInputStream.readUnsignedByte();
        if ((unsignedByte & 0x80) != 0x0) {
            return (unsignedByte & 0x7F) << 24 | dataInputStream.readUnsignedByte() << 16 | dataInputStream.readUnsignedByte() << 8 | dataInputStream.readUnsignedByte();
        }
        return unsignedByte;
    }
    
    private void A(final DataOutputStream dataOutputStream, int n) throws IOException, CompressorException {
        if (n > 127) {
            n |= Integer.MIN_VALUE;
            dataOutputStream.writeInt(n);
        }
        else {
            dataOutputStream.writeByte(n);
        }
    }
    
    public enum PostCompression
    {
        none("none", 0), 
        zip("zip", 1), 
        bzip2("bzip2", 2);
        
        static {
            A = new PostCompression[] { PostCompression.none, PostCompression.zip, PostCompression.bzip2 };
        }
        
        private PostCompression(final String s, final int n) {
        }
    }
}
