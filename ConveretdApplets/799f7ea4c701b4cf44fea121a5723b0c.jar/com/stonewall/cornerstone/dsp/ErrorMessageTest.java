// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dsp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import junit.framework.TestCase;

public class ErrorMessageTest extends TestCase
{
    public void testFailedCommand() {
        final String s = "Duplicate entry    ailed Command";
        final String regex = "[F|f]ailed";
        final Pattern p = Pattern.compile(regex);
        final Matcher m = p.matcher(s);
        System.out.println(m.find());
    }
}
