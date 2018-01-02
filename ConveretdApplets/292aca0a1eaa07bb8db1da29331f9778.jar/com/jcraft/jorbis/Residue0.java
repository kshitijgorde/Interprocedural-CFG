// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jorbis;

import com.jcraft.jogg.Buffer;

class Residue0 extends FuncResidue
{
    static int[][][] partword;
    
    void pack(final Object vr, final Buffer opb) {
        final InfoResidue0 info = (InfoResidue0)vr;
        int acc = 0;
        opb.write(info.begin, 24);
        opb.write(info.end, 24);
        opb.write(info.grouping - 1, 24);
        opb.write(info.partitions - 1, 6);
        opb.write(info.groupbook, 8);
        for (int j = 0; j < info.partitions; ++j) {
            if (ilog(info.secondstages[j]) > 3) {
                opb.write(info.secondstages[j], 3);
                opb.write(1, 1);
                opb.write(info.secondstages[j] >>> 3, 5);
            }
            else {
                opb.write(info.secondstages[j], 4);
            }
            acc += icount(info.secondstages[j]);
        }
        for (int j = 0; j < acc; ++j) {
            opb.write(info.booklist[j], 8);
        }
    }
    
    Object unpack(final Info vi, final Buffer opb) {
        int acc = 0;
        final InfoResidue0 info = new InfoResidue0();
        info.begin = opb.read(24);
        info.end = opb.read(24);
        info.grouping = opb.read(24) + 1;
        info.partitions = opb.read(6) + 1;
        info.groupbook = opb.read(8);
        for (int j = 0; j < info.partitions; ++j) {
            int cascade = opb.read(3);
            if (opb.read(1) != 0) {
                cascade |= opb.read(5) << 3;
            }
            info.secondstages[j] = cascade;
            acc += icount(cascade);
        }
        for (int j = 0; j < acc; ++j) {
            info.booklist[j] = opb.read(8);
        }
        if (info.groupbook >= vi.books) {
            this.free_info(info);
            return null;
        }
        for (int j = 0; j < acc; ++j) {
            if (info.booklist[j] >= vi.books) {
                this.free_info(info);
                return null;
            }
        }
        return info;
    }
    
    Object look(final DspState vd, final InfoMode vm, final Object vr) {
        final InfoResidue0 info = (InfoResidue0)vr;
        final LookResidue0 look = new LookResidue0();
        int acc = 0;
        int maxstage = 0;
        look.info = info;
        look.map = vm.mapping;
        look.parts = info.partitions;
        look.fullbooks = vd.fullbooks;
        look.phrasebook = vd.fullbooks[info.groupbook];
        final int dim = look.phrasebook.dim;
        look.partbooks = new int[look.parts][];
        for (int j = 0; j < look.parts; ++j) {
            final int stages = ilog(info.secondstages[j]);
            if (stages != 0) {
                if (stages > maxstage) {
                    maxstage = stages;
                }
                look.partbooks[j] = new int[stages];
                for (int k = 0; k < stages; ++k) {
                    if ((info.secondstages[j] & 1 << k) != 0x0) {
                        look.partbooks[j][k] = info.booklist[acc++];
                    }
                }
            }
        }
        look.partvals = (int)Math.rint(Math.pow(look.parts, dim));
        look.stages = maxstage;
        look.decodemap = new int[look.partvals][];
        for (int j = 0; j < look.partvals; ++j) {
            int val = j;
            int mult = look.partvals / look.parts;
            look.decodemap[j] = new int[dim];
            for (int i = 0; i < dim; ++i) {
                final int deco = val / mult;
                val -= deco * mult;
                mult /= look.parts;
                look.decodemap[j][i] = deco;
            }
        }
        return look;
    }
    
    void free_info(final Object i) {
    }
    
    void free_look(final Object i) {
    }
    
    int forward(final Block vb, final Object vl, final float[][] in, final int ch) {
        System.err.println("Residue0.forward: not implemented");
        return 0;
    }
    
    static synchronized int _01inverse(final Block vb, final Object vl, final float[][] in, final int ch, final int decodepart) {
        final LookResidue0 look = (LookResidue0)vl;
        final InfoResidue0 info = look.info;
        final int samples_per_partition = info.grouping;
        final int partitions_per_word = look.phrasebook.dim;
        final int n = info.end - info.begin;
        final int partvals = n / samples_per_partition;
        final int partwords = (partvals + partitions_per_word - 1) / partitions_per_word;
        if (Residue0.partword.length < ch) {
            Residue0.partword = new int[ch][][];
            for (int j = 0; j < ch; ++j) {
                Residue0.partword[j] = new int[partwords][];
            }
        }
        else {
            for (int j = 0; j < ch; ++j) {
                if (Residue0.partword[j] == null || Residue0.partword[j].length < partwords) {
                    Residue0.partword[j] = new int[partwords][];
                }
            }
        }
        for (int s = 0; s < look.stages; ++s) {
            int i = 0;
            int l = 0;
            while (i < partvals) {
                if (s == 0) {
                    for (int j = 0; j < ch; ++j) {
                        final int temp = look.phrasebook.decode(vb.opb);
                        if (temp == -1) {
                            return 0;
                        }
                        Residue0.partword[j][l] = look.decodemap[temp];
                        if (Residue0.partword[j][l] == null) {
                            return 0;
                        }
                    }
                }
                for (int k = 0; k < partitions_per_word && i < partvals; ++k, ++i) {
                    for (int j = 0; j < ch; ++j) {
                        final int offset = info.begin + i * samples_per_partition;
                        if ((info.secondstages[Residue0.partword[j][l][k]] & 1 << s) != 0x0) {
                            final CodeBook stagebook = look.fullbooks[look.partbooks[Residue0.partword[j][l][k]][s]];
                            if (stagebook != null) {
                                if (decodepart == 0) {
                                    if (stagebook.decodevs_add(in[j], offset, vb.opb, samples_per_partition) == -1) {
                                        return 0;
                                    }
                                }
                                else if (decodepart == 1 && stagebook.decodev_add(in[j], offset, vb.opb, samples_per_partition) == -1) {
                                    return 0;
                                }
                            }
                        }
                    }
                }
                ++l;
            }
        }
        return 0;
    }
    
    static int _2inverse(final Block vb, final Object vl, final float[][] in, final int ch) {
        final LookResidue0 look = (LookResidue0)vl;
        final InfoResidue0 info = look.info;
        final int samples_per_partition = info.grouping;
        final int partitions_per_word = look.phrasebook.dim;
        final int n = info.end - info.begin;
        final int partvals = n / samples_per_partition;
        final int partwords = (partvals + partitions_per_word - 1) / partitions_per_word;
        final int[][] partword = new int[partwords][];
        for (int s = 0; s < look.stages; ++s) {
            int i = 0;
            int l = 0;
            while (i < partvals) {
                if (s == 0) {
                    final int temp = look.phrasebook.decode(vb.opb);
                    if (temp == -1) {
                        return 0;
                    }
                    partword[l] = look.decodemap[temp];
                    if (partword[l] == null) {
                        return 0;
                    }
                }
                for (int k = 0; k < partitions_per_word && i < partvals; ++k, ++i) {
                    final int offset = info.begin + i * samples_per_partition;
                    if ((info.secondstages[partword[l][k]] & 1 << s) != 0x0) {
                        final CodeBook stagebook = look.fullbooks[look.partbooks[partword[l][k]][s]];
                        if (stagebook != null && stagebook.decodevv_add(in, offset, ch, vb.opb, samples_per_partition) == -1) {
                            return 0;
                        }
                    }
                }
                ++l;
            }
        }
        return 0;
    }
    
    int inverse(final Block vb, final Object vl, final float[][] in, final int[] nonzero, final int ch) {
        int used = 0;
        for (int i = 0; i < ch; ++i) {
            if (nonzero[i] != 0) {
                in[used++] = in[i];
            }
        }
        if (used != 0) {
            return _01inverse(vb, vl, in, used, 0);
        }
        return 0;
    }
    
    private static int ilog(int v) {
        int ret = 0;
        while (v != 0) {
            ++ret;
            v >>>= 1;
        }
        return ret;
    }
    
    private static int icount(int v) {
        int ret = 0;
        while (v != 0) {
            ret += (v & 0x1);
            v >>>= 1;
        }
        return ret;
    }
    
    static {
        Residue0.partword = new int[2][][];
    }
}
