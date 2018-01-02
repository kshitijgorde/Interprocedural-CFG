// 
// Decompiled by Procyon v0.5.30
// 

package org.joni.bench;

public class BenchSeveralRegexps extends AbstractBench
{
    public static void main(final String[] args) throws Exception {
        final int BASE = 1000000;
        new BenchSeveralRegexps().benchBestOf("a", " a", 10, 4 * BASE);
        new BenchSeveralRegexps().benchBestOf(".*?=", "_petstore_session_id=1b341ffe23b5298676d535fcabd3d0d7; path=/", 10, BASE);
        new BenchSeveralRegexps().benchBestOf("^(.*?)=(.*?);", "_petstore_session_id=1b341ffe23b5298676d535fcabd3d0d7; path=/", 10, BASE);
        new BenchSeveralRegexps().benchBestOf(".*_p", "_petstore_session_id=1b341ffe23b5298676d535fcabd3d0d7; path=/", 10, 4 * BASE);
        new BenchSeveralRegexps().benchBestOf(".*=", "_petstore_session_id=1b341ffe23b5298676d535fcabd3d0d7; path=/", 10, 4 * BASE);
    }
}
