// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.r;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordingEvent
{
    public String eventID;
    public String eventDescription;
    public String eventType;
    public String eventPriority;
    public String recordingEventLimitCount;
    public String eventMeasurementSource;
    public String eventMeasurementName;
    
    public RecordingEvent() {
        this.eventID = "";
        this.eventDescription = "";
        this.eventType = "";
        this.eventPriority = "";
        this.recordingEventLimitCount = "";
        this.eventMeasurementSource = "";
        this.eventMeasurementName = "";
    }
    
    void set(final String input, final String rIndex, final String recordingEventIndex) throws Exception {
        Matcher matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\ID-" + recordingEventIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.eventID = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\D-" + recordingEventIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.eventDescription = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\T-" + recordingEventIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.eventType = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\P-" + recordingEventIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.eventPriority = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\LC-" + recordingEventIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.recordingEventLimitCount = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\MS-" + recordingEventIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.eventMeasurementSource = matcher.group(1);
        }
        matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\MN-" + recordingEventIndex + ":(.*);").matcher(input);
        if (matcher.find()) {
            this.eventMeasurementName = matcher.group(1);
        }
    }
}
