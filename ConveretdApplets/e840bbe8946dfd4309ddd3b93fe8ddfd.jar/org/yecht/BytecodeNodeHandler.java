// 
// Decompiled by Procyon v0.5.30
// 

package org.yecht;

public class BytecodeNodeHandler implements NodeHandler
{
    public static byte[] bytes(final String s) {
        try {
            return s.getBytes("ISO-8859-1");
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public Object handle(final Parser p, final Node n) {
        final Bytestring val = new Bytestring();
        if (n.anchor != null) {
            val.append((byte)65, bytes(n.anchor), 0, -1);
        }
        if (n.type_id != null) {
            if (p.taguri_expansion) {
                val.append((byte)84, bytes(n.type_id), 0, -1);
            }
            else {
                val.append((byte)84, bytes("!" + n.type_id), 0, -1);
            }
        }
        switch (n.kind) {
            case Str: {
                byte nextcode = 83;
                final Data.Str dd = (Data.Str)n.data;
                final byte[] buf = dd.ptr.buffer;
                int start = dd.ptr.start;
                final int finish = start + dd.len - 1;
                int current = start;
                while (true) {
                    final byte ch = buf[current];
                    if (10 == ch || 0 == ch || current > finish) {
                        if (current >= start) {
                            val.append(nextcode, buf, start, current);
                            nextcode = 67;
                        }
                        start = current + 1;
                        if (current > finish) {
                            break;
                        }
                        if (10 == ch) {
                            val.append((byte)78, null, 0, -1);
                        }
                        else if (0 == ch) {
                            val.append((byte)90, null, 0, -1);
                        }
                    }
                    ++current;
                }
                break;
            }
            case Seq: {
                val.append((byte)81, null, 0, -1);
                final Data.Seq dd2 = (Data.Seq)n.data;
                for (int i = 0; i < dd2.idx; ++i) {
                    val.extend((Bytestring)n.seqRead(i));
                }
                val.append((byte)69, null, 0, -1);
                break;
            }
            case Map: {
                val.append((byte)77, null, 0, -1);
                final Data.Map dd3 = (Data.Map)n.data;
                for (int i = 0; i < dd3.idx; ++i) {
                    val.extend((Bytestring)n.mapRead(MapPart.Key, i));
                    val.extend((Bytestring)n.mapRead(MapPart.Value, i));
                }
                val.append((byte)69, null, 0, -1);
                break;
            }
        }
        return val;
    }
}
