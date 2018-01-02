// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jzlib;

final class Inflate
{
    private static final int MAX_WBITS = 15;
    private static final int PRESET_DICT = 32;
    static final int Z_NO_FLUSH = 0;
    static final int Z_PARTIAL_FLUSH = 1;
    static final int Z_SYNC_FLUSH = 2;
    static final int Z_FULL_FLUSH = 3;
    static final int Z_FINISH = 4;
    private static final int Z_DEFLATED = 8;
    private static final int Z_OK = 0;
    private static final int Z_STREAM_END = 1;
    private static final int Z_NEED_DICT = 2;
    private static final int Z_ERRNO = -1;
    private static final int Z_STREAM_ERROR = -2;
    private static final int Z_DATA_ERROR = -3;
    private static final int Z_MEM_ERROR = -4;
    private static final int Z_BUF_ERROR = -5;
    private static final int Z_VERSION_ERROR = -6;
    private static final int METHOD = 0;
    private static final int FLAG = 1;
    private static final int DICT4 = 2;
    private static final int DICT3 = 3;
    private static final int DICT2 = 4;
    private static final int DICT1 = 5;
    private static final int DICT0 = 6;
    private static final int BLOCKS = 7;
    private static final int CHECK4 = 8;
    private static final int CHECK3 = 9;
    private static final int CHECK2 = 10;
    private static final int CHECK1 = 11;
    private static final int DONE = 12;
    private static final int BAD = 13;
    int mode;
    int method;
    long[] was;
    long need;
    int marker;
    int nowrap;
    int wbits;
    InfBlocks blocks;
    private static byte[] mark;
    
    int inflateSync(final ZStream zStream) {
        if (zStream == null || zStream.istate == null) {
            return -2;
        }
        if (zStream.istate.mode != 13) {
            zStream.istate.mode = 13;
            zStream.istate.marker = 0;
        }
        int avail_in;
        if ((avail_in = zStream.avail_in) == 0) {
            return -5;
        }
        int next_in_index = zStream.next_in_index;
        int marker;
        for (marker = zStream.istate.marker; avail_in != 0 && marker < 4; --avail_in) {
            if (zStream.next_in[next_in_index] == Inflate.mark[marker]) {
                ++marker;
            }
            else if (zStream.next_in[next_in_index] != 0) {
                marker = 0;
            }
            else {
                marker = 4 - marker;
            }
            ++next_in_index;
        }
        zStream.total_in += next_in_index - zStream.next_in_index;
        zStream.next_in_index = next_in_index;
        zStream.avail_in = avail_in;
        if ((zStream.istate.marker = marker) != 4) {
            return -3;
        }
        final long total_in = zStream.total_in;
        final long total_out = zStream.total_out;
        this.inflateReset(zStream);
        zStream.total_in = total_in;
        zStream.total_out = total_out;
        zStream.istate.mode = 7;
        return 0;
    }
    
    int inflateReset(final ZStream zStream) {
        if (zStream == null || zStream.istate == null) {
            return -2;
        }
        final long n = 0L;
        zStream.total_out = n;
        zStream.total_in = n;
        zStream.msg = null;
        zStream.istate.mode = ((zStream.istate.nowrap != 0) ? 7 : 0);
        zStream.istate.blocks.reset(zStream, null);
        return 0;
    }
    
    int inflateSyncPoint(final ZStream zStream) {
        if (zStream == null || zStream.istate == null || zStream.istate.blocks == null) {
            return -2;
        }
        return zStream.istate.blocks.sync_point();
    }
    
    int inflateInit(final ZStream zStream, int wbits) {
        zStream.msg = null;
        this.blocks = null;
        this.nowrap = 0;
        if (wbits < 0) {
            wbits = -wbits;
            this.nowrap = 1;
        }
        if (wbits < 8 || wbits > 15) {
            this.inflateEnd(zStream);
            return -2;
        }
        this.wbits = wbits;
        zStream.istate.blocks = new InfBlocks(zStream, (zStream.istate.nowrap != 0) ? null : this, 1 << wbits);
        this.inflateReset(zStream);
        return 0;
    }
    
    int inflateEnd(final ZStream zStream) {
        if (this.blocks != null) {
            this.blocks.free(zStream);
        }
        this.blocks = null;
        return 0;
    }
    
    int inflate(final ZStream zStream, int n) {
        if (zStream == null || zStream.istate == null || zStream.next_in == null) {
            return -2;
        }
        n = ((n == 4) ? -5 : 0);
        int proc = -5;
        Label_0615: {
            Label_0536: {
                Label_0457: {
                Label_0383:
                    while (true) {
                        switch (zStream.istate.mode) {
                            case 0: {
                                if (zStream.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --zStream.avail_in;
                                ++zStream.total_in;
                                final Inflate istate = zStream.istate;
                                final byte method = zStream.next_in[zStream.next_in_index++];
                                istate.method = method;
                                if ((method & 0xF) != 0x8) {
                                    zStream.istate.mode = 13;
                                    zStream.msg = "unknown compression method";
                                    zStream.istate.marker = 5;
                                    continue;
                                }
                                if ((zStream.istate.method >> 4) + 8 > zStream.istate.wbits) {
                                    zStream.istate.mode = 13;
                                    zStream.msg = "invalid window size";
                                    zStream.istate.marker = 5;
                                    continue;
                                }
                                zStream.istate.mode = 1;
                            }
                            case 1: {
                                if (zStream.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --zStream.avail_in;
                                ++zStream.total_in;
                                final int n2 = zStream.next_in[zStream.next_in_index++] & 0xFF;
                                if (((zStream.istate.method << 8) + n2) % 31 != 0) {
                                    zStream.istate.mode = 13;
                                    zStream.msg = "incorrect header check";
                                    zStream.istate.marker = 5;
                                    continue;
                                }
                                if ((n2 & 0x20) == 0x0) {
                                    zStream.istate.mode = 7;
                                    continue;
                                }
                                zStream.istate.mode = 2;
                                break Label_0383;
                            }
                            case 2: {
                                break Label_0383;
                            }
                            case 3: {
                                break Label_0457;
                            }
                            case 4: {
                                break Label_0536;
                            }
                            case 5: {
                                break Label_0615;
                            }
                            case 6: {
                                zStream.istate.mode = 13;
                                zStream.msg = "need dictionary";
                                zStream.istate.marker = 0;
                                return -2;
                            }
                            case 7: {
                                proc = zStream.istate.blocks.proc(zStream, proc);
                                if (proc == -3) {
                                    zStream.istate.mode = 13;
                                    zStream.istate.marker = 0;
                                    continue;
                                }
                                if (proc == 0) {
                                    proc = n;
                                }
                                if (proc != 1) {
                                    return proc;
                                }
                                proc = n;
                                zStream.istate.blocks.reset(zStream, zStream.istate.was);
                                if (zStream.istate.nowrap != 0) {
                                    zStream.istate.mode = 12;
                                    continue;
                                }
                                zStream.istate.mode = 8;
                            }
                            case 8: {
                                if (zStream.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --zStream.avail_in;
                                ++zStream.total_in;
                                zStream.istate.need = ((zStream.next_in[zStream.next_in_index++] & 0xFF) << 24 & 0xFF000000L);
                                zStream.istate.mode = 9;
                            }
                            case 9: {
                                if (zStream.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --zStream.avail_in;
                                ++zStream.total_in;
                                final Inflate istate2 = zStream.istate;
                                istate2.need += ((zStream.next_in[zStream.next_in_index++] & 0xFF) << 16 & 0xFF0000L);
                                zStream.istate.mode = 10;
                            }
                            case 10: {
                                if (zStream.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --zStream.avail_in;
                                ++zStream.total_in;
                                final Inflate istate3 = zStream.istate;
                                istate3.need += ((zStream.next_in[zStream.next_in_index++] & 0xFF) << 8 & 0xFF00L);
                                zStream.istate.mode = 11;
                            }
                            case 11: {
                                if (zStream.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --zStream.avail_in;
                                ++zStream.total_in;
                                final Inflate istate4 = zStream.istate;
                                istate4.need += (zStream.next_in[zStream.next_in_index++] & 0xFFL);
                                if ((int)zStream.istate.was[0] != (int)zStream.istate.need) {
                                    zStream.istate.mode = 13;
                                    zStream.msg = "incorrect data check";
                                    zStream.istate.marker = 5;
                                    continue;
                                }
                                zStream.istate.mode = 12;
                                return 1;
                            }
                            case 12: {
                                return 1;
                            }
                            case 13: {
                                return -3;
                            }
                            default: {
                                return -2;
                            }
                        }
                    }
                    if (zStream.avail_in == 0) {
                        return proc;
                    }
                    proc = n;
                    --zStream.avail_in;
                    ++zStream.total_in;
                    zStream.istate.need = ((zStream.next_in[zStream.next_in_index++] & 0xFF) << 24 & 0xFF000000L);
                    zStream.istate.mode = 3;
                }
                if (zStream.avail_in == 0) {
                    return proc;
                }
                proc = n;
                --zStream.avail_in;
                ++zStream.total_in;
                final Inflate istate5 = zStream.istate;
                istate5.need += ((zStream.next_in[zStream.next_in_index++] & 0xFF) << 16 & 0xFF0000L);
                zStream.istate.mode = 4;
            }
            if (zStream.avail_in == 0) {
                return proc;
            }
            proc = n;
            --zStream.avail_in;
            ++zStream.total_in;
            final Inflate istate6 = zStream.istate;
            istate6.need += ((zStream.next_in[zStream.next_in_index++] & 0xFF) << 8 & 0xFF00L);
            zStream.istate.mode = 5;
        }
        if (zStream.avail_in == 0) {
            return proc;
        }
        --zStream.avail_in;
        ++zStream.total_in;
        final Inflate istate7 = zStream.istate;
        istate7.need += (zStream.next_in[zStream.next_in_index++] & 0xFFL);
        zStream.adler = zStream.istate.need;
        zStream.istate.mode = 6;
        return 2;
    }
    
    int inflateSetDictionary(final ZStream zStream, final byte[] array, final int n) {
        int n2 = 0;
        int n3 = n;
        if (zStream == null || zStream.istate == null || zStream.istate.mode != 6) {
            return -2;
        }
        if (zStream._adler.adler32(1L, array, 0, n) != zStream.adler) {
            return -3;
        }
        zStream.adler = zStream._adler.adler32(0L, null, 0, 0);
        if (n3 >= 1 << zStream.istate.wbits) {
            n3 = (1 << zStream.istate.wbits) - 1;
            n2 = n - n3;
        }
        zStream.istate.blocks.set_dictionary(array, n2, n3);
        zStream.istate.mode = 7;
        return 0;
    }
    
    Inflate() {
        this.was = new long[1];
    }
    
    static {
        Inflate.mark = new byte[] { 0, 0, -1, -1 };
    }
}
