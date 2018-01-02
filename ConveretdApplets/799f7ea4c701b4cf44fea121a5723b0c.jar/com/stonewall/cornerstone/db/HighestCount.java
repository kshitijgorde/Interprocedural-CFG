// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.db;

import java.util.Comparator;

class HighestCount implements Comparator<Statistic>
{
    @Override
    public int compare(final Statistic a, final Statistic b) {
        if (a.count > b.count) {
            return -1;
        }
        if (a.count < b.count) {
            return 1;
        }
        if (a.avg() > b.avg()) {
            return -1;
        }
        if (a.avg() < b.avg()) {
            return 1;
        }
        return 0;
    }
}
