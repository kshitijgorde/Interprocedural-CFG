// 
// Decompiled by Procyon v0.5.30
// 

package com.lotus.elearn.tracking;

public interface TrackingConstants
{
    public static final String PARAMETER_COMMAND = "COMMAND";
    public static final String PARAMETER_CONTENT_URL = "CONTENT_URL";
    public static final String PARAMETER_TRACKING_TYPE = "TRACKING_TYPE";
    public static final String PARAMETER_SESSION_ID = "SESSION_ID";
    public static final String PARAMETER_TRACKING_ID = "AICC_SID";
    public static final String PARAMETER_TRACKING_URL = "AICC_URL";
    public static final String PARAMETER_CHARACTER_ENCODING = "CHARACTER_ENCODING";
    public static final int TRACKING_TYPE_UNDEFINED = 0;
    public static final int TRACKING_TYPE_SCORM = 1;
    public static final int TRACKING_TYPE_HACP = 2;
    public static final String ERROR_BUNDLE = "com.lotus.elearn.tracking.TrackingErrors";
    public static final int NO_ERROR = 0;
    public static final int ERROR_LMS_INVALID_REQUEST = 900;
    public static final int ERROR_LMS_NOT_FOUND = 904;
    public static final int ERROR_LMS_INTERNAL_ERROR = 1000;
    public static final int ERROR_LMS_NOT_IMPLEMENTED = 1001;
}
