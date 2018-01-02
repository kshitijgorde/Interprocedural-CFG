// 
// Decompiled by Procyon v0.5.30
// 

package com.aviva.framework.security;

import java.util.Random;

public class SecurityUtil
{
    private static Random seeder;
    
    protected static String getSalt(final long startPointTimestamp) {
        final long startPoint = startPointTimestamp * SecurityUtil.seeder.nextInt();
        final long timestampConcated = startPoint + System.currentTimeMillis();
        final long finalRandom = timestampConcated * SecurityUtil.seeder.nextInt();
        return Long.toString(finalRandom);
    }
    
    public static String getDesKey(final String pwdOrSession, final String salt, final int counter) {
        final DigestTool dt = DigestTool.getInstance();
        String concatedStr = dt.digestSHA2(pwdOrSession) + salt;
        for (int i = 0; i < counter; ++i) {
            concatedStr = dt.digestSHA2(concatedStr);
        }
        return concatedStr;
    }
    
    public static void main(final String[] args) {
    }
    
    static {
        SecurityUtil.seeder = new Random(System.currentTimeMillis());
    }
}
