// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jffi;

import java.nio.Buffer;

final class ObjectBuffer
{
    public static final int IN = 1;
    public static final int OUT = 2;
    public static final int ZERO_TERMINATE = 4;
    public static final int PINNED = 8;
    public static final int CLEAR = 16;
    static final int INDEX_SHIFT = 16;
    static final int INDEX_MASK = 16711680;
    static final int TYPE_SHIFT = 24;
    static final int TYPE_MASK = -16777216;
    static final int PRIM_MASK = 251658240;
    static final int FLAGS_SHIFT = 0;
    static final int FLAGS_MASK = 255;
    static final int ARRAY = 268435456;
    static final int BUFFER = 536870912;
    static final int JNI = 1073741824;
    static final int BYTE = 16777216;
    static final int SHORT = 33554432;
    static final int INT = 50331648;
    static final int LONG = 67108864;
    static final int FLOAT = 83886080;
    static final int DOUBLE = 100663296;
    public static final int JNIENV = 16777216;
    public static final int JNIOBJECT = 33554432;
    private Object[] objects;
    private int[] info;
    private int infoIndex;
    private int objectIndex;
    
    ObjectBuffer() {
        this.objects = new Object[1];
        this.info = new int[this.objects.length * 3];
        this.infoIndex = 0;
        this.objectIndex = 0;
    }
    
    final int objectCount() {
        return this.objectIndex;
    }
    
    final int[] info() {
        return this.info;
    }
    
    final Object[] objects() {
        return this.objects;
    }
    
    private final void ensureSpace() {
        if (this.objects.length <= this.objectIndex + 1) {
            final Object[] newObjects = new Object[this.objects.length << 1];
            System.arraycopy(this.objects, 0, newObjects, 0, this.objectIndex);
            this.objects = newObjects;
            final int[] newInfo = new int[this.objects.length * 3];
            System.arraycopy(this.info, 0, newInfo, 0, this.objectIndex * 3);
            this.info = newInfo;
        }
    }
    
    private static final int makeArrayFlags(final int flags, final int type, final int index) {
        return (flags & 0xFF) | (index << 16 & 0xFF0000) | type;
    }
    
    private static final int makeBufferFlags(final int index) {
        return (index << 16 & 0xFF0000) | 0x20000000;
    }
    
    private static final int makeJNIFlags(final int index, final int type) {
        return (index << 16 & 0xFF0000) | 0x40000000 | type;
    }
    
    public void putArray(final int index, final byte[] array, final int offset, final int length, final int flags) {
        this.putObject(array, offset, length, makeArrayFlags(flags, 285212672, index));
    }
    
    public void putArray(final int index, final short[] array, final int offset, final int length, final int flags) {
        this.putObject(array, offset, length, makeArrayFlags(flags, 301989888, index));
    }
    
    public void putArray(final int index, final int[] array, final int offset, final int length, final int flags) {
        this.putObject(array, offset, length, makeArrayFlags(flags, 318767104, index));
    }
    
    public void putArray(final int index, final long[] array, final int offset, final int length, final int flags) {
        this.putObject(array, offset, length, makeArrayFlags(flags, 335544320, index));
    }
    
    public void putArray(final int index, final float[] array, final int offset, final int length, final int flags) {
        this.putObject(array, offset, length, makeArrayFlags(flags, 352321536, index));
    }
    
    public void putArray(final int index, final double[] array, final int offset, final int length, final int flags) {
        this.putObject(array, offset, length, makeArrayFlags(flags, 369098752, index));
    }
    
    public void putDirectBuffer(final int index, final Buffer obj, final int offset, final int length) {
        this.putObject(obj, offset, length, makeBufferFlags(index));
    }
    
    public void putJNI(final int index, final int type) {
        this.putObject(null, 0, 0, makeJNIFlags(index, type));
    }
    
    private void putObject(final Object array, final int offset, final int length, final int flags) {
        this.ensureSpace();
        this.objects[this.objectIndex++] = array;
        this.info[this.infoIndex++] = flags;
        this.info[this.infoIndex++] = offset;
        this.info[this.infoIndex++] = length;
    }
}
