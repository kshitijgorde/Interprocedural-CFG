// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.bench;

public class BenchGreedyBacktrack extends AbstractBench
{
    public static void main(final String[] args) throws Exception {
        new BenchGreedyBacktrack().bench(".*_p", "_petstore_session_id=1b341ffe23b5298676d535fcabd3d0d7; path=/", 10, 1000000);
    }
}
