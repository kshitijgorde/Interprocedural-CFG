// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.irig106.chapter9.r;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.Collection;
import java.util.Hashtable;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class RecordingEventDefinitions
{
    public String recordingEventsEnabled;
    public ArrayList<RecordingEvent> recordingEvents;
    
    public RecordingEventDefinitions() {
        this.recordingEventsEnabled = "";
        this.recordingEvents = new ArrayList<RecordingEvent>();
    }
    
    public void set(final String input, final String rIndex) throws Exception {
        Matcher matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\E:(.*);").matcher(input);
        if (matcher.find()) {
            this.recordingEventsEnabled = matcher.group(1);
        }
        final Hashtable<String, RecordingEvent> recordingEvents = new Hashtable<String, RecordingEvent>();
        matcher = Pattern.compile("R-" + rIndex + "\\\\EV\\\\.*-(\\d+).*:.*;").matcher(input);
        while (matcher.find()) {
            RecordingEvent recordingEvent = recordingEvents.get(matcher.group(1));
            if (recordingEvent == null) {
                recordingEvent = new RecordingEvent();
                recordingEvents.put(matcher.group(1), recordingEvent);
            }
        }
        for (final String recordingEventIndex : recordingEvents.keySet()) {
            recordingEvents.get(recordingEventIndex).set(input, rIndex, recordingEventIndex);
        }
        this.recordingEvents = new ArrayList<RecordingEvent>(recordingEvents.values());
    }
}
