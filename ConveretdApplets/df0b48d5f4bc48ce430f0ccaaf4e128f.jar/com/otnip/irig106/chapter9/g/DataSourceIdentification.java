// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.g;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataSourceIdentification
{
    public String dataSourceID;
    public String dataSourceType;
    
    public DataSourceIdentification() {
        this.dataSourceID = "";
        this.dataSourceType = "";
    }
    
    public void set(final String input, final String[] patterns) {
        Matcher matcher = Pattern.compile(patterns[0]).matcher(input);
        if (matcher.find()) {
            this.dataSourceID = matcher.group(1);
        }
        matcher = Pattern.compile(patterns[1]).matcher(input);
        if (matcher.find()) {
            this.dataSourceType = matcher.group(1);
        }
    }
    
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DATA SOURCE IDENTIFICATION\n");
        stringBuilder.append("\tData Source ID:  " + this.dataSourceID + "\n");
        stringBuilder.append("\tData Source Type:  " + this.dataSourceType);
        return stringBuilder.toString();
    }
}
