import java.util.Enumeration;
import russotto.iff.IFFOutputFile;
import russotto.iff.IFFChunkInfo;
import russotto.iff.IFFChunkNotFoundException;
import java.io.IOException;
import russotto.iff.IFFInputFile;
import java.awt.AWTError;
import java.awt.Toolkit;
import java.awt.FileDialog;
import java.awt.Frame;
import java.util.Stack;

// 
// Decompiled by Procyon v0.5.30
// 

class ZState
{
    static final short QUETZAL_PROCEDURE = 16;
    ZMachine zm;
    Stack zstack;
    ZHeader header;
    int pc;
    byte[] dynamic;
    short[] locals;
    short argcount;
    
    ZState(final ZMachine zm) {
        this.zm = zm;
    }
    
    void save_current() {
        this.header = new ZStateHeader(this.zm.memory_image);
        final int dyn_size = this.header.static_base();
        this.zstack = (Stack)this.zm.zstack.clone();
        this.dynamic = new byte[dyn_size];
        System.arraycopy(this.zm.memory_image, 0, this.dynamic, 0, dyn_size);
        this.locals = new short[this.zm.locals.length];
        System.arraycopy(this.zm.locals, 0, this.locals, 0, this.locals.length);
        this.header = new ZStateHeader(this.dynamic);
        this.pc = this.zm.pc;
        if (this.header.version() > 3) {
            this.argcount = ((ZMachine5)this.zm).argcount;
        }
    }
    
    void restore_saved() {
        System.arraycopy(this.dynamic, 0, this.zm.memory_image, 0, this.dynamic.length);
        this.zm.locals = new short[this.locals.length];
        System.arraycopy(this.locals, 0, this.zm.locals, 0, this.locals.length);
        this.zm.zstack = (Stack)this.zstack.clone();
        this.zm.pc = this.pc;
        if (this.header.version() > 3) {
            ((ZMachine5)this.zm).argcount = this.argcount;
        }
    }
    
    String get_save_file_name(final Frame parentframe) {
        String returnval;
        try {
            final FileDialog fd = new FileDialog(parentframe, "Save game as...", 1);
            fd.show();
            Toolkit.getDefaultToolkit().sync();
            returnval = String.valueOf(fd.getDirectory()) + fd.getFile();
        }
        catch (AWTError awtError) {
            returnval = "";
        }
        return returnval;
    }
    
    String get_restore_file_name(final Frame parentframe) {
        String returnval;
        try {
            final FileDialog fd = new FileDialog(parentframe, "Restore game from...", 0);
            fd.show();
            Toolkit.getDefaultToolkit().sync();
            returnval = String.valueOf(fd.getDirectory()) + fd.getFile();
        }
        catch (AWTError awtError) {
            returnval = "";
        }
        return returnval;
    }
    
    boolean restore_from_disk(final Frame parentframe) {
        IFFInputFile infile = null;
        boolean returnvalue = false;
        final byte[] serial = new byte[6];
        short[] lastlocals = new short[0];
        byte lastargmask = 0;
        final int version = this.zm.header.version();
        final String fname = this.get_restore_file_name(parentframe);
        try {
            infile = new IFFInputFile(fname);
            final String formtype = infile.readFORM();
            if (formtype.equals("IFZS")) {
                IFFChunkInfo chunkinfo = infile.skipToChunk("IFhd");
                final short release = infile.readShort();
                infile.read(serial, 0, 6);
                final short checksum = infile.readShort();
                if (release != this.zm.header.release()) {
                    throw new IOException("Release # did not match");
                }
                if (checksum != this.zm.header.checksum()) {
                    throw new IOException("Checksum did not match");
                }
                for (int i = 0; i < serial.length; ++i) {
                    if (this.zm.memory_image[18 + i] != serial[i]) {
                        throw new IOException("Serial # did not match");
                    }
                }
                this.pc = (infile.readByte() & 0xFF) << 16;
                this.pc |= (infile.readShort() & 0xFFFF);
                infile.closeChunk();
                final long ifhdend = infile.getFilePointer();
                try {
                    chunkinfo = infile.skipToChunk("UMem");
                    if (chunkinfo.chunklength != this.zm.header.static_base()) {
                        throw new IOException("Dynamic memory area is " + chunkinfo.chunklength + " expected " + this.zm.header.static_base());
                    }
                    infile.read(this.dynamic = new byte[chunkinfo.chunklength], 0, chunkinfo.chunklength);
                }
                catch (IFFChunkNotFoundException ex) {
                    infile.seek(ifhdend);
                    chunkinfo = infile.skipToChunk("CMem");
                    this.dynamic = new byte[this.zm.header.static_base()];
                    System.arraycopy(this.zm.restart_state.dynamic, 0, this.dynamic, 0, this.zm.header.static_base());
                    int length = chunkinfo.chunklength;
                    int nbytesout = 0;
                    boolean runmode = false;
                    while (length-- > 0) {
                        if (nbytesout >= this.zm.header.static_base()) {
                            throw new IOException("CMem exceeded dynamic memory size");
                        }
                        final byte ch = infile.readByte();
                        if (runmode) {
                            runmode = false;
                            nbytesout += (ch & 0xFF) + 1;
                        }
                        else if (ch != 0) {
                            final byte[] dynamic = this.dynamic;
                            final int n = nbytesout++;
                            dynamic[n] ^= ch;
                        }
                        else {
                            runmode = true;
                        }
                    }
                }
                this.header = new ZStateHeader(this.dynamic);
                infile.closeChunk();
                infile.seek(ifhdend);
                chunkinfo = infile.skipToChunk("Stks");
                this.zstack = new Stack();
                int frameno = 0;
                while (infile.getChunkPointer() < chunkinfo.chunklength) {
                    int framepc = (infile.readByte() & 0xFF) << 16;
                    framepc |= (infile.readShort() & 0xFFFF);
                    final byte flags = infile.readByte();
                    final byte resultvar = infile.readByte();
                    final byte argmask = infile.readByte();
                    final int evalwords = infile.readShort();
                    final int numlocals = flags & 0xF;
                    final short[] framelocals = new short[numlocals];
                    for (int i = 0; i < numlocals; ++i) {
                        framelocals[i] = infile.readShort();
                    }
                    if (frameno > 0) {
                        if ((flags & 0x10) == 0x10) {
                            this.zstack.push(new ZFrameBound(false));
                            if (version > 3) {
                                this.zstack.push(new Integer(249));
                            }
                        }
                        else {
                            this.zstack.push(new ZFrameBound(true));
                            this.zstack.push(new Integer(resultvar));
                            if (version > 3) {
                                this.zstack.push(new Integer(136));
                            }
                        }
                        if ((lastargmask & lastargmask + 1) != 0x0) {
                            throw new IOException("This implementation does not support noncontiguous arguments");
                        }
                        this.zstack.push(new Integer(framepc));
                        if (version > 3) {
                            short argcount = 0;
                            while (lastargmask != 0) {
                                ++argcount;
                                lastargmask &= (byte)(lastargmask - 1);
                            }
                            this.zstack.push(new Integer(argcount));
                        }
                        this.zstack.push(lastlocals);
                    }
                    lastargmask = argmask;
                    lastlocals = framelocals;
                    for (int i = 0; i < evalwords; ++i) {
                        this.zstack.push(new Integer(infile.readShort()));
                    }
                    ++frameno;
                }
                this.locals = lastlocals;
                if (version > 3) {
                    short argcount = 0;
                    while (lastargmask != 0) {
                        ++argcount;
                        lastargmask &= (byte)(lastargmask - 1);
                    }
                    this.argcount = argcount;
                }
                infile.closeChunk();
                returnvalue = true;
            }
            infile.close();
        }
        catch (IOException excpt) {
            System.err.println(excpt);
            try {
                if (infile != null) {
                    infile.close();
                }
            }
            catch (IOException ex2) {}
        }
        catch (IFFChunkNotFoundException cnfexcpt) {
            System.err.println(cnfexcpt);
            try {
                if (infile != null) {
                    infile.close();
                }
            }
            catch (IOException ex3) {}
        }
        catch (SecurityException sexcpt) {
            System.err.println(sexcpt);
            try {
                if (infile != null) {
                    infile.close();
                }
            }
            catch (IOException ex4) {}
            catch (SecurityException ex5) {}
        }
        finally {
            if (!returnvalue) {
                infile = null;
                this.dynamic = null;
                this.zstack = null;
                final short[] framelocals = null;
                lastlocals = null;
                this.header = null;
            }
        }
        return returnvalue;
    }
    
    void write_cmem_chunk(final IFFOutputFile outfile) throws IOException {
        outfile.openChunk("CMem");
        int runsize = 0;
        for (int i = 0; i < this.zm.header.static_base(); ++i) {
            if (this.zm.memory_image[i] == this.zm.restart_state.dynamic[i]) {
                ++runsize;
            }
            else {
                while (runsize > 0) {
                    outfile.writeByte(0);
                    if (runsize >= 256) {
                        outfile.writeByte(-1);
                        runsize -= 256;
                    }
                    else {
                        outfile.writeByte((byte)(runsize - 1));
                        runsize = 0;
                    }
                }
                outfile.writeByte(this.zm.memory_image[i] ^ this.zm.restart_state.dynamic[i]);
            }
        }
        outfile.closeChunk();
    }
    
    boolean disk_save(final Frame parentframe, final int save_pc) {
        IFFOutputFile outfile = null;
        boolean returnvalue = false;
        try {
            final String fname = this.get_save_file_name(parentframe);
            if (fname.equals("") || fname.equals("nullnull")) {
                throw new IOException("No file picked");
            }
            outfile = new IFFOutputFile(fname, "IFZS");
            outfile.openChunk("IFhd");
            outfile.write(this.zm.memory_image, 2, 2);
            outfile.write(this.zm.memory_image, 18, 6);
            outfile.write(this.zm.memory_image, 28, 2);
            outfile.writeByte((save_pc & 0xFF0000) >>> 16);
            outfile.writeShort(save_pc & 0xFFFF);
            outfile.closeChunk();
            this.write_cmem_chunk(outfile);
            outfile.openChunk("Stks");
            final int version = this.zm.header.version();
            final Enumeration f = this.zm.zstack.elements();
            while (f.hasMoreElements()) {
                final Object el2 = f.nextElement();
                if (el2 instanceof ZFrameBound) {
                    break;
                }
            }
            final Enumeration e = this.zm.zstack.elements();
            Object el3 = null;
            Object el2 = null;
            outfile.writeByte(0);
            outfile.writeShort(0);
            outfile.writeByte(0);
            outfile.writeByte(0);
            outfile.writeByte(0);
            long evalstackloc = outfile.getFilePointer();
            outfile.writeShort(0);
            int i = 0;
            while (e.hasMoreElements()) {
                el3 = e.nextElement();
                if (el3 instanceof ZFrameBound) {
                    break;
                }
                outfile.writeShort((short)(int)el3);
                ++i;
            }
            long placeholder = outfile.getFilePointer();
            outfile.seek(evalstackloc);
            outfile.writeByte((byte)i);
            outfile.seek(placeholder);
            while (e.hasMoreElements()) {
                if (f.hasMoreElements()) {
                    do {
                        el2 = f.nextElement();
                    } while (f.hasMoreElements() && !(el2 instanceof ZFrameBound));
                }
                short argcount;
                short[] locals;
                if (f.hasMoreElements()) {
                    if (((ZFrameBound)el2).isstore()) {
                        el2 = f.nextElement();
                    }
                    if (version > 3) {
                        el2 = f.nextElement();
                    }
                    el2 = f.nextElement();
                    if (version > 3) {
                        el2 = f.nextElement();
                        argcount = (short)(int)el2;
                    }
                    else {
                        argcount = 4;
                    }
                    el2 = f.nextElement();
                    locals = (short[])el2;
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
                final short numlocals = (short)locals.length;
                if (version <= 3 && numlocals < argcount) {
                    argcount = numlocals;
                }
                final boolean framestore = ((ZFrameBound)el3).isstore();
                short storevar;
                if (framestore) {
                    el3 = e.nextElement();
                    storevar = (short)(int)el3;
                }
                else {
                    storevar = 0;
                }
                if (version > 3) {
                    el3 = e.nextElement();
                }
                el3 = e.nextElement();
                final int framepc = (int)el3;
                if (version > 3) {
                    el3 = e.nextElement();
                }
                el3 = e.nextElement();
                byte frameflags = (byte)numlocals;
                if (!framestore) {
                    frameflags |= 0x10;
                }
                final byte argmask = (byte)((1 << argcount) - 1);
                outfile.writeByte((framepc & 0xFF0000) >>> 16);
                outfile.writeShort(framepc & 0xFFFF);
                outfile.writeByte(frameflags);
                outfile.writeByte((byte)storevar);
                outfile.writeByte(argmask);
                evalstackloc = outfile.getFilePointer();
                outfile.writeShort(0);
                for (i = 0; i < numlocals; ++i) {
                    outfile.writeShort(locals[i]);
                }
                i = 0;
                while (e.hasMoreElements()) {
                    el3 = e.nextElement();
                    if (el3 instanceof ZFrameBound) {
                        break;
                    }
                    outfile.writeShort((short)(int)el3);
                    ++i;
                }
                placeholder = outfile.getFilePointer();
                outfile.seek(evalstackloc);
                outfile.writeByte((byte)i);
                outfile.seek(placeholder);
            }
            outfile.closeChunk();
            outfile.close();
            returnvalue = true;
        }
        catch (IOException ex) {
            try {
                if (outfile != null) {
                    outfile.close();
                    return returnvalue;
                }
                return returnvalue;
            }
            catch (IOException ex2) {}
        }
        catch (SecurityException ex3) {
            try {
                if (outfile != null) {
                    outfile.close();
                    return returnvalue;
                }
                return returnvalue;
            }
            catch (IOException ex4) {}
            catch (SecurityException ex5) {}
        }
        return returnvalue;
    }
}
