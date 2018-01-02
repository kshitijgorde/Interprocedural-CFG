// 
// Decompiled by Procyon v0.5.30
// 

package com.lotus.elearn.tracking.scorm;

import com.lotus.elearn.tracking.TrackingConstants;

public interface SCORMConstants extends TrackingConstants
{
    public static final String COMMAND_INITIALIZE = "INITIALIZE";
    public static final String COMMAND_COMMIT = "COMMIT";
    public static final String COMMAND_FINISH = "FINISH";
    public static final String PARAMETER_DATA = "SCORM_DATA";
    public static final String ROOT_SCORM = "scorm";
    public static final String DATA_MODEL_CMI = "cmi";
    public static final String DATA_MODEL_ERROR_DATA = "error_data";
    public static final String CATEGORY_CORE = "core";
    public static final String CATEGORY_SCORE = "score";
    public static final String CATEGORY_OBJECTIVES = "objectives";
    public static final String CATEGORY_STUDENT_DATA = "student_data";
    public static final String CATEGORY_STUDENT_PREFERENCE = "student_preference";
    public static final String CATEGORY_INTERACTIONS = "interactions";
    public static final String CATEGORY_CORRECT_RESPONSES = "correct_responses";
    public static final String CATEGORY_INDEX = "n";
    public static final String KEYWORD_VERSION = "_version";
    public static final String KEYWORD_CHILDREN = "_children";
    public static final String KEYWORD_COUNT = "_count";
    public static final String ELEMENT_LAUNCH_DATA = "launch_data";
    public static final String ELEMENT_SUSPEND_DATA = "suspend_data";
    public static final String ELEMENT_COMMENTS = "comments";
    public static final String ELEMENT_COMMENTS_FROM_LMS = "comments_from_lms";
    public static final String ELEMENT_CREDIT = "credit";
    public static final String ELEMENT_ENTRY = "entry";
    public static final String ELEMENT_EXIT = "exit";
    public static final String ELEMENT_LESSON_LOCATION = "lesson_location";
    public static final String ELEMENT_LESSON_MODE = "lesson_mode";
    public static final String ELEMENT_LESSON_STATUS = "lesson_status";
    public static final String ELEMENT_RAW = "raw";
    public static final String ELEMENT_MINIMUM = "min";
    public static final String ELEMENT_MAXIMUM = "max";
    public static final String ELEMENT_SESSION_TIME = "session_time";
    public static final String ELEMENT_STUDENT_ID = "student_id";
    public static final String ELEMENT_STUDENT_NAME = "student_name";
    public static final String ELEMENT_TOTAL_TIME = "total_time";
    public static final String ELEMENT_ID = "id";
    public static final String ELEMENT_STATUS = "status";
    public static final String ELEMENT_MASTERY_SCORE = "mastery_score";
    public static final String ELEMENT_MAXIMUM_TIME_ALLOWED = "max_time_allowed";
    public static final String ELEMENT_TIME_LIMIT_ACTION = "time_limit_action";
    public static final String ELEMENT_AUDIO = "audio";
    public static final String ELEMENT_LANGUAGE = "language";
    public static final String ELEMENT_SPEED = "speed";
    public static final String ELEMENT_TEXT = "text";
    public static final String ELEMENT_TIME = "time";
    public static final String ELEMENT_TYPE = "type";
    public static final String ELEMENT_PATTERN = "pattern";
    public static final String ELEMENT_WEIGHTING = "weighting";
    public static final String ELEMENT_STUDENT_RESPONSE = "student_response";
    public static final String ELEMENT_RESULT = "result";
    public static final String ELEMENT_LATENCY = "latency";
    public static final String VALUE_VERSION = "3.4";
    public static final String VALUE_CREDIT = "credit";
    public static final String VALUE_NO_CREDIT = "no-credit";
    public static final String VALUE_PASSED = "passed";
    public static final String VALUE_COMPLETED = "completed";
    public static final String VALUE_FAILED = "failed";
    public static final String VALUE_INCOMPLETE = "incomplete";
    public static final String VALUE_BROWSED = "browsed";
    public static final String VALUE_NOT_ATTEMPTED = "not attempted";
    public static final String VALUE_AB_INITIO = "ab-initio";
    public static final String VALUE_RESUME = "resume";
    public static final String VALUE_NORMAL = "normal";
    public static final String VALUE_REVIEW = "review";
    public static final String VALUE_BROWSE = "browse";
    public static final String VALUE_TIME_OUT = "time-out";
    public static final String VALUE_SUSPEND = "suspend";
    public static final String VALUE_LOGOUT = "logout";
    public static final String VALUE_TRUE_FALSE = "true-false";
    public static final String VALUE_CHOICE = "choice";
    public static final String VALUE_FILL_IN = "fill-in";
    public static final String VALUE_MATCHING = "matching";
    public static final String VALUE_PERFORMANCE = "performance";
    public static final String VALUE_LIKERT = "likert";
    public static final String VALUE_SEQUENCING = "sequencing";
    public static final String VALUE_UNIQUE = "unique";
    public static final String VALUE_NUMERIC = "numeric";
    public static final String VALUE_CORRECT = "correct";
    public static final String VALUE_WRONG = "wrong";
    public static final String VALUE_UNANTICIPATED = "unanticipated";
    public static final String VALUE_NEUTRAL = "neutral";
    public static final String VALUE_EXIT_MESSAGE = "exit,message";
    public static final String VALUE_EXIT_NO_MESSAGE = "exit,no message";
    public static final String VALUE_CONTINUE_MESSAGE = "continue,message";
    public static final String VALUE_CONTINUE_NO_MESSAGE = "continue,no message";
    public static final String ATTRIBUTE_ACCESS = "access";
    public static final String ATTRIBUTE_DIRTY = "dirty";
    public static final String ATTRIBUTE_IMPLEMENTED = "implemented";
    public static final String ATTRIBUTE_OPERATION = "operation";
    public static final String ATTRIBUTE_RANGE = "range";
    public static final String ATTRIBUTE_TYPE = "type";
    public static final String ATTRIBUTE_VOCABULARY = "vocabulary";
    public static final String ACCESS_READ_ONLY = "r";
    public static final String ACCESS_WRITE_ONLY = "w";
    public static final String ACCESS_READ_WRITE = "rw";
    public static final String TRUE = String.valueOf(true);
    public static final String FALSE = String.valueOf(false);
    public static final String OPERATION_APPEND = "append";
    public static final String OPERATION_REPLACE = "replace";
    public static final String RANGE_SEPARATOR = ":";
    public static final int TYPE_ARRAY = 0;
    public static final int TYPE_BLANK = 1;
    public static final int TYPE_BOOLEAN = 2;
    public static final int TYPE_DECIMAL = 3;
    public static final int TYPE_FEEDBACK = 4;
    public static final int TYPE_IDENTIFIER = 5;
    public static final int TYPE_INTEGER = 6;
    public static final int TYPE_SIGNED_INTEGER = 7;
    public static final int TYPE_STRING = 8;
    public static final int TYPE_MEMO = 9;
    public static final int TYPE_TIME = 10;
    public static final int TYPE_TIMESPAN = 11;
    public static final int TYPE_VOCABULARY = 12;
    public static final int TYPE_BLANK_OR_DECIMAL = 13;
    public static final String[] VOCABULARY_MODE = { "normal", "review", "browse" };
    public static final String[] VOCABULARY_LESSON_STATUS = { "passed", "completed", "failed", "incomplete", "browsed" };
    public static final String[] VOCABULARY_OBJECTIVE_STATUS = { "passed", "completed", "failed", "incomplete", "browsed", "not attempted" };
    public static final String[] VOCABULARY_EXIT = { "time-out", "suspend", "logout", String.valueOf(1) };
    public static final String[] VOCABULARY_CREDIT = { "credit", "no-credit" };
    public static final String[] VOCABULARY_ENTRY = { "ab-initio", "resume", String.valueOf(1) };
    public static final String[] VOCABULARY_INTERACTION_TYPE = { "true-false", "choice", "fill-in", "matching", "performance", "likert", "sequencing", "unique", "numeric" };
    public static final String[] VOCABULARY_INTERACTION_RESULT = { "correct", "wrong", "unanticipated", "neutral", String.valueOf(3) };
    public static final String[] VOCABULARY_TIME_LIMIT_ACTION = { "exit,message", "exit,no message", "continue,message", "continue,no message" };
    public static final String VOCABULARY_LIST_SEPARATOR = ";";
    public static final int ERROR_GENERAL_EXCEPTION = 101;
    public static final int ERROR_INVALID_ARGUMENT = 201;
    public static final int ERROR_CHILDREN_NOT_ALLOWED = 202;
    public static final int ERROR_ARRAY_NOT_ALLOWED = 203;
    public static final int ERROR_VALUE_NOT_ALLOWED = 204;
    public static final int ERROR_NOT_INITIALIZED = 301;
    public static final int ERROR_NOT_IMPLEMENTED = 401;
    public static final int ERROR_RESERVED_ELEMENT = 402;
    public static final int ERROR_READ_ONLY_ELEMENT = 403;
    public static final int ERROR_WRITE_ONLY_ELEMENT = 404;
    public static final int ERROR_INCORRECT_DATA_TYPE = 405;
    public static final String FORMAT_TIME = "H:mm:ss.SS";
    public static final String FORMAT_TIMESPAN = "HH:mm:ss.SS";
}
