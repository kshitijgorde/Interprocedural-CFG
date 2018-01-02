// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jorbis;

class InfoFloor1
{
    static final int VIF_POSIT = 63;
    static final int VIF_CLASS = 16;
    static final int VIF_PARTS = 31;
    int partitions;
    int[] partitionclass;
    int[] class_dim;
    int[] class_subs;
    int[] class_book;
    int[][] class_subbook;
    int mult;
    int[] postlist;
    float maxover;
    float maxunder;
    float maxerr;
    int twofitminsize;
    int twofitminused;
    int twofitweight;
    float twofitatten;
    int unusedminsize;
    int unusedmin_n;
    int n;
    
    InfoFloor1() {
        this.partitionclass = new int[31];
        this.class_dim = new int[16];
        this.class_subs = new int[16];
        this.class_book = new int[16];
        this.class_subbook = new int[16][];
        this.postlist = new int[65];
        for (int i = 0; i < this.class_subbook.length; ++i) {
            this.class_subbook[i] = new int[8];
        }
    }
    
    void free() {
        this.partitionclass = null;
        this.class_dim = null;
        this.class_subs = null;
        this.class_book = null;
        this.class_subbook = null;
        this.postlist = null;
    }
    
    Object copy_info() {
        final InfoFloor1 ret = new InfoFloor1();
        ret.partitions = this.partitions;
        System.arraycopy(this.partitionclass, 0, ret.partitionclass, 0, 31);
        System.arraycopy(this.class_dim, 0, ret.class_dim, 0, 16);
        System.arraycopy(this.class_subs, 0, ret.class_subs, 0, 16);
        System.arraycopy(this.class_book, 0, ret.class_book, 0, 16);
        for (int j = 0; j < 16; ++j) {
            System.arraycopy(this.class_subbook[j], 0, ret.class_subbook[j], 0, 8);
        }
        ret.mult = this.mult;
        System.arraycopy(this.postlist, 0, ret.postlist, 0, 65);
        ret.maxover = this.maxover;
        ret.maxunder = this.maxunder;
        ret.maxerr = this.maxerr;
        ret.twofitminsize = this.twofitminsize;
        ret.twofitminused = this.twofitminused;
        ret.twofitweight = this.twofitweight;
        ret.twofitatten = this.twofitatten;
        ret.unusedminsize = this.unusedminsize;
        ret.unusedmin_n = this.unusedmin_n;
        ret.n = this.n;
        return ret;
    }
}
