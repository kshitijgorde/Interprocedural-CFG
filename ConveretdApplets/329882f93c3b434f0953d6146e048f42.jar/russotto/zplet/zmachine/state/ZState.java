// 
// Decompiled by Procyon v0.5.30
// 

package russotto.zplet.zmachine.state;

import java.util.Enumeration;
import russotto.iff.IFFOutputFile;
import russotto.iff.IFFChunkInfo;
import russotto.zplet.zmachine.ZFrameBound;
import russotto.iff.IFFChunkNotFoundException;
import java.io.IOException;
import russotto.iff.IFFInputFile;
import java.awt.AWTError;
import java.awt.Toolkit;
import java.awt.FileDialog;
import java.awt.Frame;
import russotto.zplet.zmachine.zmachine5.ZMachine5;
import russotto.zplet.zmachine.ZHeader;
import java.util.Stack;
import russotto.zplet.zmachine.ZMachine;

public class ZState
{
    static final short QUETZAL_PROCEDURE = 16;
    ZMachine zm;
    Stack zstack;
    public ZHeader header;
    int pc;
    byte[] dynamic;
    short[] locals;
    short argcount;
    
    public ZState(final ZMachine zm) {
        this.zm = zm;
    }
    
    public void save_current() {
        this.header = new ZStateHeader(this.zm.memory_image);
        final int static_base = this.header.static_base();
        this.zstack = (Stack)this.zm.zstack.clone();
        this.dynamic = new byte[static_base];
        System.arraycopy(this.zm.memory_image, 0, this.dynamic, 0, static_base);
        this.locals = new short[this.zm.locals.length];
        System.arraycopy(this.zm.locals, 0, this.locals, 0, this.locals.length);
        this.header = new ZStateHeader(this.dynamic);
        this.pc = this.zm.pc;
        if (this.header.version() > 3) {
            this.argcount = ((ZMachine5)this.zm).argcount;
        }
    }
    
    public void restore_saved() {
        System.arraycopy(this.dynamic, 0, this.zm.memory_image, 0, this.dynamic.length);
        this.zm.locals = new short[this.locals.length];
        System.arraycopy(this.locals, 0, this.zm.locals, 0, this.locals.length);
        this.zm.zstack = (Stack)this.zstack.clone();
        this.zm.pc = this.pc;
        if (this.header.version() > 3) {
            ((ZMachine5)this.zm).argcount = this.argcount;
        }
    }
    
    String get_save_file_name(final Frame frame) {
        String string;
        try {
            final FileDialog fileDialog = new FileDialog(frame, "Save game as...", 1);
            fileDialog.show();
            Toolkit.getDefaultToolkit().sync();
            string = fileDialog.getDirectory() + fileDialog.getFile();
        }
        catch (AWTError awtError) {
            string = "";
        }
        return string;
    }
    
    public String get_restore_file_name(final Frame frame) {
        String string;
        try {
            final FileDialog fileDialog = new FileDialog(frame, "Restore game from...", 0);
            fileDialog.show();
            Toolkit.getDefaultToolkit().sync();
            string = fileDialog.getDirectory() + fileDialog.getFile();
        }
        catch (AWTError awtError) {
            string = "";
        }
        return string;
    }
    
    public boolean restore_from_disk(final Frame frame) {
        IFFInputFile iffInputFile = null;
        boolean b = false;
        final byte[] array = new byte[6];
        short[] locals = new short[0];
        int i = 0;
        final int version = this.zm.header.version();
        final String get_restore_file_name = this.get_restore_file_name(frame);
        try {
            iffInputFile = new IFFInputFile(get_restore_file_name);
            if (iffInputFile.readFORM().equals("IFZS")) {
                iffInputFile.skipToChunk("IFhd");
                final short short1 = iffInputFile.readShort();
                iffInputFile.read(array, 0, 6);
                final short short2 = iffInputFile.readShort();
                if (short1 != this.zm.header.release()) {
                    throw new IOException("Release # did not match");
                }
                if (short2 != this.zm.header.checksum()) {
                    throw new IOException("Checksum did not match");
                }
                for (int j = 0; j < array.length; ++j) {
                    if (this.zm.memory_image[18 + j] != array[j]) {
                        throw new IOException("Serial # did not match");
                    }
                }
                this.pc = (iffInputFile.readByte() & 0xFF) << 16;
                this.pc |= (iffInputFile.readShort() & 0xFFFF);
                iffInputFile.closeChunk();
                final long filePointer = iffInputFile.getFilePointer();
                try {
                    final IFFChunkInfo skipToChunk = iffInputFile.skipToChunk("UMem");
                    if (skipToChunk.chunklength != this.zm.header.static_base()) {
                        throw new IOException("Dynamic memory area is " + skipToChunk.chunklength + " expected " + this.zm.header.static_base());
                    }
                    iffInputFile.read(this.dynamic = new byte[skipToChunk.chunklength], 0, skipToChunk.chunklength);
                }
                catch (IFFChunkNotFoundException ex4) {
                    iffInputFile.seek(filePointer);
                    final IFFChunkInfo skipToChunk2 = iffInputFile.skipToChunk("CMem");
                    this.dynamic = new byte[this.zm.header.static_base()];
                    System.arraycopy(this.zm.restart_state.dynamic, 0, this.dynamic, 0, this.zm.header.static_base());
                    int chunklength = skipToChunk2.chunklength;
                    int n = 0;
                    int n2 = 0;
                    while (chunklength-- > 0) {
                        if (n >= this.zm.header.static_base()) {
                            throw new IOException("CMem exceeded dynamic memory size");
                        }
                        final byte byte1 = iffInputFile.readByte();
                        if (n2 != 0) {
                            n2 = 0;
                            n += (byte1 & 0xFF) + 1;
                        }
                        else if (byte1 != 0) {
                            final byte[] dynamic = this.dynamic;
                            final int n3 = n++;
                            dynamic[n3] ^= byte1;
                        }
                        else {
                            n2 = 1;
                        }
                    }
                }
                this.header = new ZStateHeader(this.dynamic);
                iffInputFile.closeChunk();
                iffInputFile.seek(filePointer);
                final IFFChunkInfo skipToChunk3 = iffInputFile.skipToChunk("Stks");
                this.zstack = new Stack();
                int n4 = 0;
                while (iffInputFile.getChunkPointer() < skipToChunk3.chunklength) {
                    final int n5 = (iffInputFile.readByte() & 0xFF) << 16 | (iffInputFile.readShort() & 0xFFFF);
                    final byte byte2 = iffInputFile.readByte();
                    final byte byte3 = iffInputFile.readByte();
                    final byte byte4 = iffInputFile.readByte();
                    final short short3 = iffInputFile.readShort();
                    final byte b2 = (byte)(byte2 & 0xF);
                    final short[] array2 = new short[b2];
                    for (byte b3 = 0; b3 < b2; ++b3) {
                        array2[b3] = iffInputFile.readShort();
                    }
                    if (n4 > 0) {
                        if ((byte2 & 0x10) == 0x10) {
                            this.zstack.push(new ZFrameBound(false));
                            if (version > 3) {
                                this.zstack.push(new Integer(249));
                            }
                        }
                        else {
                            this.zstack.push(new ZFrameBound(true));
                            this.zstack.push(new Integer(byte3));
                            if (version > 3) {
                                this.zstack.push(new Integer(136));
                            }
                        }
                        if ((i & i + 1) != 0x0) {
                            throw new IOException("This implementation does not support noncontiguous arguments");
                        }
                        this.zstack.push(new Integer(n5));
                        if (version > 3) {
                            int n6 = 0;
                            while (i != 0) {
                                n6 = (short)(n6 + 1);
                                i = (byte)(i & i - 1);
                            }
                            this.zstack.push(new Integer(n6));
                        }
                        this.zstack.push(locals);
                    }
                    i = byte4;
                    locals = array2;
                    for (short n7 = 0; n7 < short3; ++n7) {
                        this.zstack.push(new Integer(iffInputFile.readShort()));
                    }
                    ++n4;
                }
                this.locals = locals;
                if (version > 3) {
                    short argcount = 0;
                    while (i != 0) {
                        ++argcount;
                        i = (byte)(i & i - 1);
                    }
                    this.argcount = argcount;
                }
                iffInputFile.closeChunk();
                b = true;
            }
            iffInputFile.close();
        }
        catch (IOException ex) {
            System.err.println(ex);
            try {
                if (iffInputFile != null) {
                    iffInputFile.close();
                }
            }
            catch (IOException ex5) {}
        }
        catch (IFFChunkNotFoundException ex2) {
            System.err.println(ex2);
            try {
                if (iffInputFile != null) {
                    iffInputFile.close();
                }
            }
            catch (IOException ex6) {}
        }
        catch (SecurityException ex3) {
            System.err.println(ex3);
            try {
                if (iffInputFile != null) {
                    iffInputFile.close();
                }
            }
            catch (IOException ex7) {}
            catch (SecurityException ex8) {}
        }
        finally {
            if (!b) {
                this.dynamic = null;
                this.zstack = null;
                this.header = null;
            }
        }
        return b;
    }
    
    private void write_cmem_chunk(final IFFOutputFile iffOutputFile) throws IOException {
        iffOutputFile.openChunk("CMem");
        int i = 0;
        for (int j = 0; j < this.zm.header.static_base(); ++j) {
            if (this.zm.memory_image[j] == this.zm.restart_state.dynamic[j]) {
                ++i;
            }
            else {
                while (i > 0) {
                    iffOutputFile.writeByte(0);
                    if (i >= 256) {
                        iffOutputFile.writeByte(-1);
                        i -= 256;
                    }
                    else {
                        iffOutputFile.writeByte((byte)(i - 1));
                        i = 0;
                    }
                }
                iffOutputFile.writeByte(this.zm.memory_image[j] ^ this.zm.restart_state.dynamic[j]);
            }
        }
        iffOutputFile.closeChunk();
    }
    
    public boolean disk_save(final Frame frame, final int n) {
        IFFOutputFile iffOutputFile = null;
        boolean b = false;
        try {
            final String get_save_file_name = this.get_save_file_name(frame);
            if (get_save_file_name.equals("") || get_save_file_name.equals("nullnull")) {
                throw new IOException("No file picked");
            }
            iffOutputFile = new IFFOutputFile(get_save_file_name, "IFZS");
            iffOutputFile.openChunk("IFhd");
            iffOutputFile.write(this.zm.memory_image, 2, 2);
            iffOutputFile.write(this.zm.memory_image, 18, 6);
            iffOutputFile.write(this.zm.memory_image, 28, 2);
            iffOutputFile.writeByte((n & 0xFF0000) >>> 16);
            iffOutputFile.writeShort(n & 0xFFFF);
            iffOutputFile.closeChunk();
            this.write_cmem_chunk(iffOutputFile);
            iffOutputFile.openChunk("Stks");
            final int version = this.zm.header.version();
            final Enumeration elements = this.zm.zstack.elements();
            while (elements.hasMoreElements() && !(elements.nextElement() instanceof ZFrameBound)) {}
            final Enumeration elements2 = this.zm.zstack.elements();
            Object o = null;
            Object o2 = null;
            iffOutputFile.writeByte(0);
            iffOutputFile.writeShort(0);
            iffOutputFile.writeByte(0);
            iffOutputFile.writeByte(0);
            iffOutputFile.writeByte(0);
            final long filePointer = iffOutputFile.getFilePointer();
            iffOutputFile.writeShort(0);
            int n2 = 0;
            while (elements2.hasMoreElements()) {
                o = elements2.nextElement();
                if (o instanceof ZFrameBound) {
                    break;
                }
                iffOutputFile.writeShort((short)(int)o);
                ++n2;
            }
            final long filePointer2 = iffOutputFile.getFilePointer();
            iffOutputFile.seek(filePointer);
            iffOutputFile.writeShort((short)n2);
            iffOutputFile.seek(filePointer2);
            while (elements2.hasMoreElements()) {
                if (elements.hasMoreElements()) {
                    do {
                        o2 = elements.nextElement();
                    } while (elements.hasMoreElements() && !(o2 instanceof ZFrameBound));
                }
                short argcount;
                short[] locals;
                if (elements.hasMoreElements()) {
                    if (((ZFrameBound)o2).isstore()) {
                        elements.nextElement();
                    }
                    if (version > 3) {
                        elements.nextElement();
                    }
                    elements.nextElement();
                    if (version > 3) {
                        argcount = (short)(int)elements.nextElement();
                    }
                    else {
                        argcount = 4;
                    }
                    o2 = elements.nextElement();
                    locals = (short[])o2;
                }
                else {
                    if (version > 3) {
                        argcount = ((ZMachine5)this.zm).argcount;
                    }
                    else {
                        argcount = 4;
                    }
                    locals = this.zm.locals;
                }
                final short n3 = (short)locals.length;
                if (version <= 3 && n3 < argcount) {
                    argcount = n3;
                }
                final boolean isstore = ((ZFrameBound)o).isstore();
                int n4;
                if (isstore) {
                    n4 = (short)(int)elements2.nextElement();
                }
                else {
                    n4 = 0;
                }
                if (version > 3) {
                    elements2.nextElement();
                }
                final int intValue = elements2.nextElement();
                if (version > 3) {
                    elements2.nextElement();
                }
                o = elements2.nextElement();
                byte b2 = (byte)n3;
                if (!isstore) {
                    b2 |= 0x10;
                }
                final byte b3 = (byte)((1 << argcount) - 1);
                iffOutputFile.writeByte((intValue & 0xFF0000) >>> 16);
                iffOutputFile.writeShort(intValue & 0xFFFF);
                iffOutputFile.writeByte(b2);
                iffOutputFile.writeByte((byte)n4);
                iffOutputFile.writeByte(b3);
                final long filePointer3 = iffOutputFile.getFilePointer();
                iffOutputFile.writeShort(0);
                for (short n5 = 0; n5 < n3; ++n5) {
                    iffOutputFile.writeShort(locals[n5]);
                }
                int n6 = 0;
                while (elements2.hasMoreElements()) {
                    o = elements2.nextElement();
                    if (o instanceof ZFrameBound) {
                        break;
                    }
                    iffOutputFile.writeShort((short)(int)o);
                    ++n6;
                }
                final long filePointer4 = iffOutputFile.getFilePointer();
                iffOutputFile.seek(filePointer3);
                iffOutputFile.writeShort((short)n6);
                iffOutputFile.seek(filePointer4);
            }
            iffOutputFile.closeChunk();
            iffOutputFile.close();
            b = true;
        }
        catch (IOException ex) {
            try {
                if (iffOutputFile != null) {
                    iffOutputFile.close();
                }
            }
            catch (IOException ex2) {}
        }
        catch (SecurityException ex3) {
            try {
                if (iffOutputFile != null) {
                    iffOutputFile.close();
                }
            }
            catch (IOException ex4) {}
            catch (SecurityException ex5) {}
        }
        return b;
    }
}
