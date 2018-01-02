// 
// Decompiled by Procyon v0.5.30
// 

package com.controlj.green.applets.label;

import com.controlj.green.applets.BNLogRecord;
import java.util.Locale;
import java.util.TimeZone;
import com.controlj.green.applets.TrendResource;

public class LogRecordLabelGenerator extends LabelGenerator
{
    String[] stateText;
    DateLabelGenerator xlabelgen;
    TrendResource internationalizedText;
    
    public LogRecordLabelGenerator(final TimeZone timeZone, final int standardDate, final boolean standardTime, final String separator) {
        this.internationalizedText = new TrendResource();
        this.xlabelgen = new DateLabelGenerator(timeZone, standardDate, standardTime, separator);
    }
    
    public LogRecordLabelGenerator(final Locale loc, final TimeZone timeZone, final int standardDate, final boolean standardTime, final String separator) {
        this.internationalizedText = new TrendResource();
        this.xlabelgen = new DateLabelGenerator(timeZone, standardDate, standardTime, separator);
        this.setLocale(loc);
    }
    
    public TrendResource getInternationalizedText() {
        return this.internationalizedText;
    }
    
    public void setInternationalizedText(final TrendResource internationalizedText) {
        this.internationalizedText = internationalizedText;
    }
    
    public void setLocale(final Locale loc) {
        super.setLocale(loc);
        this.xlabelgen.setLocale(loc);
    }
    
    public void setTimeZone(final TimeZone timezone) {
        this.xlabelgen.setTimeZone(timezone);
    }
    
    public String[] getStateText() {
        return this.stateText;
    }
    
    public void setStateText(final String[] stateText) {
        this.stateText = stateText;
    }
    
    public String makeLabel(final BNLogRecord record) {
        final StringBuffer pointLabel = new StringBuffer(this.getRecordTimeDateLabel(record));
        pointLabel.append("\n");
        pointLabel.append(this.getRecordValueLabel(record));
        if (record.hasAnyFlag()) {
            pointLabel.append("\n");
            pointLabel.append(this.getRecordFlagsLabel(record));
        }
        return pointLabel.toString();
    }
    
    public String getRecordTimeDateLabel(final BNLogRecord record) {
        return this.xlabelgen.makeLabel(record.getXValue());
    }
    
    public String getRecordValueLabel(final BNLogRecord record) {
        final StringBuffer recordValueLabel = new StringBuffer();
        if (record.isLogStatus()) {
            try {
                if (record.isLogStatus_LogDisabled()) {
                    recordValueLabel.append(this.internationalizedText.get("Log_Disabled"));
                }
                else {
                    recordValueLabel.append(this.internationalizedText.get("Log_Enabled"));
                }
                if (record.isLogStatus_BufferPurged()) {
                    recordValueLabel.append(" ");
                    recordValueLabel.append(this.internationalizedText.get("and_Buffer_purged"));
                }
            }
            catch (BNLogRecord.NotALogStatusException e) {
                recordValueLabel.append("This is not a real LogStatus point");
            }
        }
        else if (record.isTimeChange()) {
            final String timeSynchText = this.internationalizedText.get("Time_Synchronization");
            recordValueLabel.append(timeSynchText);
            if (timeSynchText.length() > 15) {
                recordValueLabel.append("\n");
            }
            else {
                recordValueLabel.append(" ");
            }
            recordValueLabel.append(super.makeLabel(record.getYValue()));
            recordValueLabel.append(" ");
            recordValueLabel.append(this.internationalizedText.get("sec"));
        }
        else if (record.isALCStatus()) {
            try {
                switch (record.getALCStatusType()) {
                    case 1: {
                        recordValueLabel.append(this.internationalizedText.get("Historian_Enabled"));
                        break;
                    }
                    case 2: {
                        recordValueLabel.append(this.internationalizedText.get("Trend_source_changed"));
                        break;
                    }
                    case 0: {
                        recordValueLabel.append(this.internationalizedText.get("Historian_Disabled"));
                        break;
                    }
                    default: {
                        recordValueLabel.append(this.internationalizedText.get("CUSTOM_STATUS_" + record.getALCStatusType()));
                        break;
                    }
                }
            }
            catch (BNLogRecord.NotAnALCStatusException e2) {
                recordValueLabel.append("This is not a real ALCStatus point");
            }
        }
        else if (record.isBoolean() && this.stateText != null) {
            final int index = (int)record.getYValue();
            if (index >= 0 && index < this.stateText.length) {
                recordValueLabel.append(this.stateText[index]);
            }
            else {
                recordValueLabel.append(" ");
            }
        }
        else if ((record.isUnsigned() || record.isEnumeration()) && this.stateText != null) {
            final int index = (int)record.getYValue() - 1;
            if (index >= 0 && index < this.stateText.length) {
                recordValueLabel.append(this.stateText[index]);
            }
            else {
                recordValueLabel.append(" ");
            }
        }
        else if (record.isFailure()) {
            final String classPrefix = "failureclass_";
            String failureClass = this.internationalizedText.get(classPrefix + record.getFailureClass());
            if (failureClass.startsWith("??")) {
                failureClass = String.valueOf(record.getFailureClass());
            }
            String codePrefix = "failurecode_";
            if (record.isFailureCcn()) {
                codePrefix = "ccnfailurecode_";
            }
            String failureCode = this.internationalizedText.get(codePrefix + record.getFailureCode());
            if (failureCode.startsWith("??")) {
                failureCode = String.valueOf(record.getFailureCode());
            }
            recordValueLabel.append("Failure\n" + failureClass + " : " + failureCode);
        }
        else {
            recordValueLabel.append(super.makeLabel(record.getYValue()));
        }
        return recordValueLabel.toString();
    }
    
    public String getRecordFlagsLabel(final BNLogRecord record) {
        final StringBuffer recordFlagLabel = new StringBuffer();
        int numOnLine = 0;
        if (record.hasAlarm()) {
            recordFlagLabel.append(this.internationalizedText.get("In_Alarm"));
            recordFlagLabel.append(" ");
            ++numOnLine;
        }
        if (record.hasOverridden()) {
            recordFlagLabel.append(this.internationalizedText.get("Overridden"));
            recordFlagLabel.append(" ");
            ++numOnLine;
        }
        if (record.hasFault()) {
            if (numOnLine >= 2) {
                recordFlagLabel.append("\n");
                numOnLine = 0;
            }
            recordFlagLabel.append(this.internationalizedText.get("In_Fault"));
            recordFlagLabel.append(" ");
            ++numOnLine;
        }
        if (record.hasOutOfService()) {
            if (numOnLine >= 2) {
                recordFlagLabel.append("\n");
                numOnLine = 0;
            }
            recordFlagLabel.append(this.internationalizedText.get("Out_Of_Service"));
            recordFlagLabel.append(" ");
            ++numOnLine;
        }
        return recordFlagLabel.toString();
    }
}
