// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.r;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordingIndex
{
    public String recordingIndexEnabled;
    public String recordingIndexType;
    public String timeIndexTypeAttribute;
    public String countIndexTypeAttribute;
    
    public RecordingIndex() {
        this.recordingIndexEnabled = "";
        this.recordingIndexType = "";
        this.timeIndexTypeAttribute = "";
        this.countIndexTypeAttribute = "";
    }
    
    public void set(final String input, final String rIndex) throws Exception {
        Matcher matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\IDX\\\\E:(.*);").matcher(input);
        if (matcher.find()) {
            this.recordingIndexEnabled = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\IDX\\\\E:(.*);").matcher(input);
        if (matcher.find()) {
            this.recordingIndexType = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\IDX\\\\IT:(.*);").matcher(input);
        if (matcher.find()) {
            this.timeIndexTypeAttribute = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\IDX\\\\ICV:(.*);").matcher(input);
        if (matcher.find()) {
            this.countIndexTypeAttribute = matcher.group(1);
        }
    }
}
