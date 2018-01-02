// 
// Decompiled by Procyon v0.5.30
// 

package com.skype;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Date;
import com.skype.connector.Connector;
import com.skype.connector.TimeOutException;
import com.skype.connector.NotAttachedException;
import com.skype.connector.ConnectorException;

final class Utils
{
    static String convertNullToEmptyString(final String value) {
        if (value == null) {
            return "";
        }
        return value;
    }
    
    static void convertToSkypeException(final ConnectorException e) throws SkypeException {
        SkypeException r;
        if (e instanceof NotAttachedException) {
            r = new com.skype.NotAttachedException();
        }
        else if (e instanceof TimeOutException) {
            r = new com.skype.TimeOutException(e.getMessage());
        }
        else {
            r = new SkypeException(e.getMessage());
        }
        r.initCause(e);
        throw r;
    }
    
    static void checkError(final String response) throws SkypeException {
        if (response == null) {
            return;
        }
        if (response.startsWith("ERROR ")) {
            throw new CommandFailedException(response);
        }
    }
    
    static String getPropertyWithCommandId(final String type, final String id, final String name) throws SkypeException {
        try {
            final String command = "GET " + type + " " + id + " " + name;
            final String responseHeader = String.valueOf(type) + " " + id + " " + name + " ";
            final String response = Connector.getInstance().executeWithId(command, responseHeader);
            checkError(response);
            return response.substring(responseHeader.length());
        }
        catch (ConnectorException e) {
            convertToSkypeException(e);
            return null;
        }
    }
    
    static String getProperty(final String type, final String id, final String name) throws SkypeException {
        try {
            final String command = "GET " + type + " " + id + " " + name;
            final String responseHeader = String.valueOf(type) + " " + id + " " + name + " ";
            final String response = Connector.getInstance().execute(command, responseHeader);
            checkError(response);
            return response.substring(responseHeader.length());
        }
        catch (ConnectorException e) {
            convertToSkypeException(e);
            return null;
        }
    }
    
    static String getProperty(final String type, final String name) throws SkypeException {
        try {
            final String command = "GET " + type + " " + name;
            final String responseHeader = String.valueOf(type) + " " + name + " ";
            final String response = Connector.getInstance().execute(command, responseHeader);
            checkError(response);
            return response.substring(responseHeader.length());
        }
        catch (ConnectorException e) {
            convertToSkypeException(e);
            return null;
        }
    }
    
    static String getProperty(final String name) throws SkypeException {
        try {
            final String command = "GET " + name + " ";
            final String responseHeader = String.valueOf(name) + " ";
            final String response = Connector.getInstance().execute(command, responseHeader);
            checkError(response);
            return response.substring(responseHeader.length());
        }
        catch (ConnectorException e) {
            convertToSkypeException(e);
            return null;
        }
    }
    
    static void setProperty(final String type, final String id, final String name, final String value) throws SkypeException {
        try {
            final String command = "SET " + type + " " + id + " " + name + " " + value;
            final String responseHeader = String.valueOf(type) + " " + id + " " + name + " " + value;
            final String response = Connector.getInstance().execute(command, responseHeader);
            checkError(response);
        }
        catch (ConnectorException e) {
            convertToSkypeException(e);
        }
    }
    
    static void setProperty(final String type, final String name, final String value) throws SkypeException {
        try {
            final String command = "SET " + type + " " + name + " " + value;
            final String responseHeader = String.valueOf(type) + " " + name + " " + value;
            final String response = Connector.getInstance().execute(command, responseHeader);
            checkError(response);
        }
        catch (ConnectorException e) {
            convertToSkypeException(e);
        }
    }
    
    static void setProperty(final String name, final String value) throws SkypeException {
        try {
            final String command = "SET " + name + " " + value;
            final String responseHeader = String.valueOf(name) + " " + value;
            final String response = Connector.getInstance().execute(command, responseHeader);
            checkError(response);
        }
        catch (ConnectorException e) {
            convertToSkypeException(e);
        }
    }
    
    static void executeWithErrorCheck(final String command) throws SkypeException {
        try {
            final String response = Connector.getInstance().execute(command);
            checkError(response);
        }
        catch (ConnectorException e) {
            convertToSkypeException(e);
        }
    }
    
    static void checkNotNull(final String name, final Object value) {
        if (value == null) {
            throw new NullPointerException("The " + name + " must not be null.");
        }
    }
    
    static String[] convertToArray(final String listString) {
        if ("".equals(listString)) {
            return new String[0];
        }
        return listString.split(", ");
    }
    
    static String convertToCommaSeparatedString(final String[] array) {
        final StringBuffer builder = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i != 0) {
                builder.append(", ");
            }
            builder.append(array[i]);
        }
        return builder.toString();
    }
    
    static Date parseUnixTime(final String time) {
        final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(Long.parseLong(time) * 1000L);
        calendar.setTimeZone(TimeZone.getDefault());
        return calendar.getTime();
    }
    
    static void handleUncaughtException(final Throwable e, final SkypeExceptionHandler exceptionHandler) {
        if (exceptionHandler != null) {
            exceptionHandler.uncaughtExceptionHappened(e);
            return;
        }
        Skype.handleUncaughtException(e);
    }
}
