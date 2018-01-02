// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.r;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PCMDataTypeAttributes
{
    public String dataLinkName;
    public String dataPackingOption;
    public String typeFormat;
    
    public PCMDataTypeAttributes() {
        this.dataLinkName = "";
        this.dataPackingOption = "";
        this.typeFormat = "";
    }
    
    public void set(final String input, final String rIndex, final String dataIndex) throws Exception {
        Matcher matcher = Pattern.compile("R-" + rIndex + "\\\\PDLN-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.dataLinkName = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\PDP-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.dataPackingOption = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\PTF-" + dataIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.typeFormat = matcher.group(1);
        }
    }
}
