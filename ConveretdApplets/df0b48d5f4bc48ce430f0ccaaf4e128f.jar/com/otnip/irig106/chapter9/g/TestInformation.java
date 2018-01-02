// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.g;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestInformation
{
    public String testDuration;
    public String preTestRequirement;
    public String postTestRequirement;
    public String securityClassification;
    
    public TestInformation() {
        this.testDuration = "";
        this.preTestRequirement = "";
        this.postTestRequirement = "";
        this.securityClassification = "";
    }
    
    public void set(final String input) {
        Matcher matcher = Pattern.compile("G\\\\TI1:(.*);").matcher(input);
        if (matcher.find()) {
            this.testDuration = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\TI2:(.*);").matcher(input);
        if (matcher.find()) {
            this.preTestRequirement = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\TI3:(.*);").matcher(input);
        if (matcher.find()) {
            this.postTestRequirement = matcher.group(1);
        }
        matcher = Pattern.compile("G\\\\SC:(.*);").matcher(input);
        if (matcher.find()) {
            this.securityClassification = matcher.group(1);
        }
    }
    
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("TEST INFORMATION\n");
        stringBuilder.append("\tTest Duration:  " + this.testDuration + "\n");
        stringBuilder.append("\tPre-Test Requirement:  " + this.preTestRequirement + "\n");
        stringBuilder.append("\tPost-Test Requirement:  " + this.postTestRequirement + "\n");
        stringBuilder.append("\tSecurity Classification:  " + this.securityClassification);
        return stringBuilder.toString();
    }
}
