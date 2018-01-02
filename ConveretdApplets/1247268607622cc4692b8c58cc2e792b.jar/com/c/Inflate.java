// 
// Decompiled by Procyon v0.5.30
// 

package com.c;

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
    b blocks;
    private static byte[] mark;
    
    Inflate() {
        this.was = new long[1];
    }
    
    int inflate(final s s, int n) {
        if (s == null || s.istate == null || s.next_in == null) {
            return -2;
        }
        n = ((n == 4) ? -5 : 0);
        int proc = -5;
        Label_0615: {
            Label_0536: {
                Label_0457: {
                Label_0383:
                    while (true) {
                        switch (s.istate.mode) {
                            case 0: {
                                if (s.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --s.avail_in;
                                ++s.total_in;
                                final Inflate istate = s.istate;
                                final byte method = s.next_in[s.next_in_index++];
                                istate.method = method;
                                if ((method & 0xF) != 0x8) {
                                    s.istate.mode = 13;
                                    s.msg = "unknown compression method";
                                    s.istate.marker = 5;
                                    continue;
                                }
                                if ((s.istate.method >> 4) + 8 > s.istate.wbits) {
                                    s.istate.mode = 13;
                                    s.msg = "invalid window size";
                                    s.istate.marker = 5;
                                    continue;
                                }
                                s.istate.mode = 1;
                            }
                            case 1: {
                                if (s.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --s.avail_in;
                                ++s.total_in;
                                final int n2 = s.next_in[s.next_in_index++] & 0xFF;
                                if (((s.istate.method << 8) + n2) % 31 != 0) {
                                    s.istate.mode = 13;
                                    s.msg = "incorrect header check";
                                    s.istate.marker = 5;
                                    continue;
                                }
                                if ((n2 & 0x20) == 0x0) {
                                    s.istate.mode = 7;
                                    continue;
                                }
                                s.istate.mode = 2;
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
                                s.istate.mode = 13;
                                s.msg = "need dictionary";
                                s.istate.marker = 0;
                                return -2;
                            }
                            case 7: {
                                proc = s.istate.blocks.proc(s, proc);
                                if (proc == -3) {
                                    s.istate.mode = 13;
                                    s.istate.marker = 0;
                                    continue;
                                }
                                if (proc == 0) {
                                    proc = n;
                                }
                                if (proc != 1) {
                                    return proc;
                                }
                                proc = n;
                                s.istate.blocks.reset(s, s.istate.was);
                                if (s.istate.nowrap != 0) {
                                    s.istate.mode = 12;
                                    continue;
                                }
                                s.istate.mode = 8;
                            }
                            case 8: {
                                if (s.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --s.avail_in;
                                ++s.total_in;
                                s.istate.need = ((s.next_in[s.next_in_index++] & 0xFF) << 24 & 0xFF000000L);
                                s.istate.mode = 9;
                            }
                            case 9: {
                                if (s.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --s.avail_in;
                                ++s.total_in;
                                final Inflate istate2 = s.istate;
                                istate2.need += ((s.next_in[s.next_in_index++] & 0xFF) << 16 & 0xFF0000L);
                                s.istate.mode = 10;
                            }
                            case 10: {
                                if (s.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --s.avail_in;
                                ++s.total_in;
                                final Inflate istate3 = s.istate;
                                istate3.need += ((s.next_in[s.next_in_index++] & 0xFF) << 8 & 0xFF00L);
                                s.istate.mode = 11;
                            }
                            case 11: {
                                if (s.avail_in == 0) {
                                    return proc;
                                }
                                proc = n;
                                --s.avail_in;
                                ++s.total_in;
                                final Inflate istate4 = s.istate;
                                istate4.need += (s.next_in[s.next_in_index++] & 0xFFL);
                                if ((int)s.istate.was[0] != (int)s.istate.need) {
                                    s.istate.mode = 13;
                                    s.msg = "incorrect data check";
                                    s.istate.marker = 5;
                                    continue;
                                }
                                s.istate.mode = 12;
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
                    if (s.avail_in == 0) {
                        return proc;
                    }
                    proc = n;
                    --s.avail_in;
                    ++s.total_in;
                    s.istate.need = ((s.next_in[s.next_in_index++] & 0xFF) << 24 & 0xFF000000L);
                    s.istate.mode = 3;
                }
                if (s.avail_in == 0) {
                    return proc;
                }
                proc = n;
                --s.avail_in;
                ++s.total_in;
                final Inflate istate5 = s.istate;
                istate5.need += ((s.next_in[s.next_in_index++] & 0xFF) << 16 & 0xFF0000L);
                s.istate.mode = 4;
            }
            if (s.avail_in == 0) {
                return proc;
            }
            proc = n;
            --s.avail_in;
            ++s.total_in;
            final Inflate istate6 = s.istate;
            istate6.need += ((s.next_in[s.next_in_index++] & 0xFF) << 8 & 0xFF00L);
            s.istate.mode = 5;
        }
        if (s.avail_in == 0) {
            return proc;
        }
        --s.avail_in;
        ++s.total_in;
        final Inflate istate7 = s.istate;
        istate7.need += (s.next_in[s.next_in_index++] & 0xFFL);
        s.adler = s.istate.need;
        s.istate.mode = 6;
        return 2;
    }
    
    int inflateEnd(final s s) {
        if (this.blocks != null) {
            this.blocks.free(s);
        }
        this.blocks = null;
        return 0;
    }
    
    int inflateInit(final s s, int wbits) {
        s.msg = null;
        this.blocks = null;
        this.nowrap = 0;
        if (wbits < 0) {
            wbits = -wbits;
            this.nowrap = 1;
        }
        if (wbits < 8 || wbits > 15) {
            this.inflateEnd(s);
            return -2;
        }
        this.wbits = wbits;
        s.istate.blocks = new b(s, (s.istate.nowrap != 0) ? null : this, 1 << wbits);
        this.inflateReset(s);
        return 0;
    }
    
    int inflateReset(final s s) {
        if (s == null || s.istate == null) {
            return -2;
        }
        final long n = 0L;
        s.total_out = n;
        s.total_in = n;
        s.msg = null;
        s.istate.mode = ((s.istate.nowrap != 0) ? 7 : 0);
        s.istate.blocks.reset(s, null);
        return 0;
    }
    
    int inflateSetDictionary(final s s, final byte[] array, final int n) {
        int n2 = 0;
        int n3 = n;
        if (s == null || s.istate == null || s.istate.mode != 6) {
            return -2;
        }
        if (s._adler.a(1L, array, 0, n) != s.adler) {
            return -3;
        }
        s.adler = s._adler.a(0L, null, 0, 0);
        if (n3 >= 1 << s.istate.wbits) {
            n3 = (1 << s.istate.wbits) - 1;
            n2 = n - n3;
        }
        s.istate.blocks.set_dictionary(array, n2, n3);
        s.istate.mode = 7;
        return 0;
    }
    
    int inflateSync(final s s) {
        if (s == null || s.istate == null) {
            return -2;
        }
        if (s.istate.mode != 13) {
            s.istate.mode = 13;
            s.istate.marker = 0;
        }
        int avail_in;
        if ((avail_in = s.avail_in) == 0) {
            return -5;
        }
        int next_in_index = s.next_in_index;
        int marker;
        for (marker = s.istate.marker; avail_in != 0 && marker < 4; --avail_in) {
            if (s.next_in[next_in_index] == Inflate.mark[marker]) {
                ++marker;
            }
            else if (s.next_in[next_in_index] != 0) {
                marker = 0;
            }
            else {
                marker = 4 - marker;
            }
            ++next_in_index;
        }
        s.total_in += next_in_index - s.next_in_index;
        s.next_in_index = next_in_index;
        s.avail_in = avail_in;
        if ((s.istate.marker = marker) != 4) {
            return -3;
        }
        final long total_in = s.total_in;
        final long total_out = s.total_out;
        this.inflateReset(s);
        s.total_in = total_in;
        s.total_out = total_out;
        s.istate.mode = 7;
        return 0;
    }
    
    int inflateSyncPoint(final s s) {
        if (s == null || s.istate == null || s.istate.blocks == null) {
            return -2;
        }
        return s.istate.blocks.sync_point();
    }
    
    static {
        Inflate.mark = new byte[] { 0, 0, -1, -1 };
    }
}
