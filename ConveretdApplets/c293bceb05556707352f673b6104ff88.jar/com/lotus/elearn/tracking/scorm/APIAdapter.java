// 
// Decompiled by Procyon v0.5.30
// 

package com.lotus.elearn.tracking.scorm;

import java.io.OutputStream;
import java.net.URLConnection;
import java.net.MalformedURLException;
import org.jdom.JDOMException;
import java.io.IOException;
import java.io.InputStream;
import org.jdom.input.SAXBuilder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import org.jdom.output.XMLOutputter;
import java.io.DataOutputStream;
import java.net.URL;
import java.util.MissingResourceException;
import com.lotus.elearn.tracking.TrackingUtil;
import java.util.StringTokenizer;
import com.sun.java.util.collections.Iterator;
import com.sun.java.util.collections.List;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.jdom.Element;
import org.jdom.Document;
import java.applet.Applet;

public class APIAdapter extends Applet implements SCORMConstants
{
    private Document mXMLData;
    private boolean mDebug;
    private boolean mInitialized;
    private String mTrackingURL;
    private String mTrackingID;
    private String mDiagnosticData;
    private String mCharacterEncoding;
    private int mErrorCode;
    private static final String FIELD_CONTENT_TYPE = "Content-Type";
    private static final String TRACKING_CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String PARAMETER_DEBUG = "DEBUG";
    
    private boolean appendValue(final Element element, String value) {
        boolean retVal = false;
        final String type = element.getAttributeValue("type");
        if (type != null) {
            String elementText = element.getText();
            switch (Integer.parseInt(type)) {
                case 6:
                case 7: {
                    int elementValue = 0;
                    final int appendValue = Integer.parseInt(value);
                    if (elementText.length() > 0) {
                        elementValue = Integer.parseInt(elementText);
                    }
                    element.setText(String.valueOf(elementValue + appendValue));
                    retVal = true;
                    break;
                }
                case 4:
                case 8:
                case 9: {
                    element.setText(elementText = String.valueOf(elementText) + value);
                    retVal = true;
                    break;
                }
                case 10:
                case 11: {
                    long elementTime = 0L;
                    final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SS", Locale.ENGLISH);
                    try {
                        if (elementText.length() > 0) {
                            elementTime = simpleDateFormat.parse(elementText).getTime();
                        }
                        if (value.indexOf(".") == -1) {
                            value = String.valueOf(value) + ".0";
                        }
                        final long appendTime = simpleDateFormat.parse(value).getTime();
                        final Date date = new Date(elementTime + appendTime);
                        element.setText(simpleDateFormat.format(date));
                        retVal = true;
                    }
                    catch (ParseException ex) {
                        retVal = false;
                    }
                    break;
                }
            }
        }
        return retVal;
    }
    
    public String getAppletInfo() {
        return "Copyright (c) 2002, IBM Corporation.  All rights reserved.";
    }
    
    private String getChildren(Element element) {
        final StringBuffer buffer = new StringBuffer(128);
        final String typeAttribute = element.getAttributeValue("type");
        if (typeAttribute != null && typeAttribute.equals(String.valueOf(0))) {
            element = element.getChild("n");
        }
        if (element == null) {
            this.setErrorCode(201);
        }
        else {
            final String accessAttribute = element.getAttributeValue("access");
            if (accessAttribute == null) {
                final List children = element.getChildren();
                final Iterator iterator = children.iterator();
                boolean isFirstElement = true;
                while (iterator.hasNext()) {
                    final Element childElement = (Element)iterator.next();
                    final String implementedAttribute = childElement.getAttributeValue("implemented");
                    if (implementedAttribute == null || implementedAttribute.equals("true")) {
                        if (isFirstElement) {
                            isFirstElement = false;
                        }
                        else {
                            buffer.append(",");
                        }
                        buffer.append(childElement.getName());
                    }
                }
            }
            else {
                this.setErrorCode(202);
            }
        }
        return buffer.toString();
    }
    
    private String getCount(Element element) {
        String retVal = "";
        final String typeAttribute = element.getAttributeValue("type");
        if (typeAttribute != null && typeAttribute.equals(String.valueOf(0))) {
            final List list = element.getChildren();
            final Iterator iterator = list.iterator();
            int index = 0;
            while (iterator.hasNext()) {
                element = (Element)iterator.next();
                if (element.getName().equals("n" + index)) {
                    ++index;
                }
            }
            retVal = String.valueOf(index);
        }
        else {
            this.setErrorCode(203);
        }
        return retVal;
    }
    
    public String[][] getParameterInfo() {
        final String[][] parameterInfo = { { "AICC_SID", "tracking-identifier", "tracking context ID" }, { "AICC_URL", "url", "tracking engine URL" }, { "CHARACTER_ENCODING", "string", "client-side character encoding (optional)" }, { "DEBUG", "boolean", "debug information output flag" } };
        return parameterInfo;
    }
    
    private String getValue(final Element element) {
        String retVal = "";
        final String accessAttribute = element.getAttributeValue("access");
        if (accessAttribute == null) {
            this.setErrorCode(204);
        }
        else if (accessAttribute.equals("w")) {
            this.setErrorCode(404);
        }
        else {
            retVal = element.getText();
            if (retVal.length() == 0 && !isImplemented(element)) {
                this.setErrorCode(401);
            }
        }
        return retVal;
    }
    
    public void init() {
        this.mTrackingID = this.getParameter("AICC_SID");
        this.mTrackingURL = this.getParameter("AICC_URL");
        final String characterEncoding = this.getParameter("CHARACTER_ENCODING");
        if (characterEncoding != null) {
            this.mCharacterEncoding = characterEncoding;
        }
        final String debug = this.getParameter("DEBUG");
        if (debug != null) {
            this.mDebug = debug.equals("true");
        }
    }
    
    private static boolean isDecimal(final String value) {
        boolean retVal = true;
        try {
            Double.valueOf(value);
        }
        catch (NumberFormatException ex) {
            retVal = false;
        }
        return retVal;
    }
    
    private static boolean isImplemented(final Element element) {
        boolean retVal = true;
        final String implementedAttribute = element.getAttributeValue("implemented");
        if (implementedAttribute != null && implementedAttribute.equals(String.valueOf(false))) {
            retVal = false;
        }
        return retVal;
    }
    
    private boolean isInitialized() {
        return this.mInitialized;
    }
    
    private static boolean isInteger(final String value) {
        boolean retVal = true;
        try {
            Integer.parseInt(value);
        }
        catch (NumberFormatException ex) {
            retVal = false;
        }
        return retVal;
    }
    
    private static boolean isKeyword(final String elementName) {
        boolean retVal = false;
        if (elementName != null && (elementName.equals("_version") || elementName.equals("_children") || elementName.equals("_count"))) {
            retVal = true;
        }
        return retVal;
    }
    
    private static boolean isValidRange(final double value, final String range) {
        boolean retVal = false;
        final StringTokenizer tokenizer = new StringTokenizer(range, ":");
        final int minimumValue = Integer.parseInt(tokenizer.nextToken());
        final int maximumValue = Integer.parseInt(tokenizer.nextToken());
        if (value >= minimumValue && value <= maximumValue) {
            retVal = true;
        }
        return retVal;
    }
    
    private static boolean isValidTime(String time, final String pattern) {
        boolean retVal = true;
        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
            if (time.indexOf(".") == -1) {
                time = String.valueOf(time) + ".0";
            }
            dateFormat.parse(time);
            final StringTokenizer tokenizer = new StringTokenizer(time, ":");
            final String hoursText = tokenizer.nextToken();
            final String minutesText = tokenizer.nextToken();
            final String secondsText = tokenizer.nextToken();
            final int hoursLength = hoursText.length();
            final int minutesLength = minutesText.length();
            final int hours = Integer.parseInt(hoursText);
            final int minutes = Integer.parseInt(minutesText);
            final boolean isTimespan = pattern.startsWith("HH");
            if (isTimespan) {
                retVal = (hoursLength >= 2 && hoursLength <= 4);
            }
            else {
                retVal = (hoursLength == 2 && hours < 24);
            }
            if (retVal) {
                if (isTimespan) {
                    retVal = (minutesLength == 2);
                }
                else {
                    retVal = (minutesLength == 2 && minutes < 60);
                }
            }
            if (retVal) {
                final StringTokenizer secondsTokenizer = new StringTokenizer(secondsText, ".");
                final String secondsToken = secondsTokenizer.nextToken();
                final String millisecondsToken = secondsTokenizer.nextToken();
                final int secondsLength = secondsToken.length();
                final int millisecondsLength = millisecondsToken.length();
                final int seconds = Integer.parseInt(secondsToken);
                retVal = (secondsLength == 2 && seconds < 60 && millisecondsLength <= 2);
            }
        }
        catch (ParseException ex) {
            retVal = false;
        }
        return retVal;
    }
    
    private static boolean isValidType(final Element element, final String value) {
        boolean retVal = false;
        final String type = element.getAttributeValue("type");
        if (type != null) {
            switch (Integer.parseInt(type)) {
                case 1: {
                    if (value.length() == 0) {
                        retVal = true;
                        break;
                    }
                    break;
                }
                case 2: {
                    if (value.equals(String.valueOf(true)) || value.equals(String.valueOf(false))) {
                        retVal = true;
                        break;
                    }
                    break;
                }
                case 3: {
                    if (!isDecimal(value)) {
                        break;
                    }
                    final String range = element.getAttributeValue("range");
                    if (range != null) {
                        final double decimal = Double.valueOf(value);
                        retVal = isValidRange(decimal, range);
                        break;
                    }
                    retVal = true;
                    break;
                }
                case 13: {
                    if (value.length() == 0) {
                        retVal = true;
                        break;
                    }
                    if (!isDecimal(value)) {
                        break;
                    }
                    final String range = element.getAttributeValue("range");
                    if (range != null) {
                        final double decimal = Double.valueOf(value);
                        retVal = isValidRange(decimal, range);
                        break;
                    }
                    retVal = true;
                    break;
                }
                case 6: {
                    if (!isInteger(value)) {
                        break;
                    }
                    final String range = element.getAttributeValue("range");
                    final int integer = Integer.parseInt(value);
                    if (range != null) {
                        retVal = isValidRange(integer, range);
                        break;
                    }
                    retVal = (integer >= 0);
                    break;
                }
                case 7: {
                    if (!isInteger(value)) {
                        break;
                    }
                    final String range = element.getAttributeValue("range");
                    if (range != null) {
                        final int integer = Integer.parseInt(value);
                        retVal = isValidRange(integer, range);
                        break;
                    }
                    retVal = true;
                    break;
                }
                case 5: {
                    final int valueLength = value.length();
                    if (valueLength > 0 && valueLength < 256 && value.indexOf(" ") == -1) {
                        retVal = true;
                        break;
                    }
                    break;
                }
                case 4:
                case 8: {
                    if (value.length() < 256) {
                        retVal = true;
                        break;
                    }
                    break;
                }
                case 10: {
                    retVal = isValidTime(value, "H:mm:ss.SS");
                    break;
                }
                case 11: {
                    retVal = isValidTime(value, "HH:mm:ss.SS");
                    break;
                }
                case 12: {
                    final String vocabulary = element.getAttributeValue("vocabulary");
                    if (vocabulary != null) {
                        final StringTokenizer tokenizer = new StringTokenizer(vocabulary, ";");
                        retVal = isValidVocabularyMember(tokenizer, value);
                        break;
                    }
                    break;
                }
                case 9: {
                    if (value.length() <= 4096) {
                        retVal = true;
                        break;
                    }
                    break;
                }
            }
        }
        return retVal;
    }
    
    private static boolean isValidVocabularyMember(final StringTokenizer vocabulary, final String member) {
        boolean retVal = false;
        while (!retVal && vocabulary.hasMoreTokens()) {
            final String token = vocabulary.nextToken();
            if (isInteger(token)) {
                final Element element = new Element("temporaryElement");
                element.setAttribute("type", token);
                retVal = isValidType(element, member);
            }
            else {
                retVal = member.equals(token);
            }
        }
        return retVal;
    }
    
    public String LMSCommit(final String parameter) {
        boolean retVal = false;
        if (parameter == null || parameter.length() == 0) {
            if (this.isInitialized()) {
                final String[] parameterValues = { parameter };
                this.resetErrorCode("LMSCommit", parameterValues);
                this.mXMLData = this.postTrackingCommand("COMMIT");
                retVal = (this.mXMLData != null);
            }
            else {
                this.setErrorCode(301);
            }
        }
        else {
            this.setErrorCode(201);
        }
        return String.valueOf(retVal);
    }
    
    public String LMSFinish(final String parameter) {
        boolean retVal = false;
        if (parameter == null || parameter.length() == 0) {
            if (this.isInitialized()) {
                final String[] parameterValues = { parameter };
                this.resetErrorCode("LMSFinish", parameterValues);
                this.postTrackingCommand("FINISH");
                this.mInitialized = false;
                retVal = true;
            }
            else {
                this.setErrorCode(301);
            }
        }
        else {
            this.setErrorCode(201);
        }
        return String.valueOf(retVal);
    }
    
    public String LMSGetDiagnostic(final String errorCode) {
        String retVal = "";
        if (errorCode == null || errorCode.length() == 0 || errorCode.equals(String.valueOf(this.mErrorCode))) {
            retVal = this.mDiagnosticData;
        }
        return retVal;
    }
    
    public String LMSGetErrorString(final String errorCode) {
        String retVal = "";
        try {
            retVal = TrackingUtil.getError(errorCode);
        }
        catch (MissingResourceException ex) {}
        return retVal;
    }
    
    public String LMSGetLastError() {
        return String.valueOf(this.mErrorCode);
    }
    
    public String LMSGetValue(final String elementName) {
        String retVal = "";
        if (this.isInitialized()) {
            final String[] parameterValues = { elementName };
            this.resetErrorCode("LMSGetValue", parameterValues);
            boolean getCount = false;
            boolean getChildren = false;
            Element element = this.mXMLData.getRootElement();
            final StringTokenizer tokenizer = new StringTokenizer(elementName, ".");
            while (tokenizer.hasMoreTokens() && element != null) {
                String elementToken = tokenizer.nextToken();
                if (elementToken == null || elementToken.equals("n")) {
                    element = null;
                }
                else if (elementToken.equals("_count")) {
                    getCount = true;
                }
                else if (elementToken.equals("_children")) {
                    getChildren = true;
                }
                else {
                    if (isInteger(elementToken)) {
                        elementToken = "n" + elementToken;
                    }
                    final Element childElement = element.getChild(elementToken);
                    if (childElement == null) {
                        element = element.getChild("n");
                    }
                    else {
                        element = childElement;
                    }
                }
            }
            if (element == null || element.isRootElement()) {
                this.setErrorCode(201);
            }
            else if (getCount) {
                retVal = this.getCount(element);
            }
            else if (getChildren) {
                retVal = this.getChildren(element);
            }
            else {
                retVal = this.getValue(element);
            }
        }
        else {
            this.setErrorCode(301);
        }
        return retVal;
    }
    
    public String LMSInitialize(final String parameter) {
        boolean retVal = false;
        final String[] parameterValues = { parameter };
        this.resetErrorCode("LMSInitialize", parameterValues);
        if (parameter == null || parameter.length() == 0) {
            this.mXMLData = this.postTrackingCommand("INITIALIZE");
            if (this.isInitialized()) {
                this.setErrorCode(101);
            }
            else if (this.mXMLData != null) {
                this.mInitialized = true;
                retVal = true;
            }
        }
        else {
            this.setErrorCode(201);
        }
        return String.valueOf(retVal);
    }
    
    public String LMSSetValue(final String elementName, String elementValue) {
        boolean retVal = false;
        if (this.isInitialized()) {
            final String[] parameterValues = { elementName, elementValue };
            this.resetErrorCode("LMSSetValue", parameterValues);
            Element element = this.mXMLData.getRootElement();
            final StringTokenizer tokenizer = new StringTokenizer(elementName, ".");
            String elementToken = null;
            while (tokenizer.hasMoreTokens() && element != null) {
                elementToken = tokenizer.nextToken();
                if (elementToken == null || elementToken.equals("n")) {
                    element = null;
                }
                else {
                    if (isInteger(elementToken)) {
                        final int elementIndex = Integer.parseInt(elementToken);
                        elementToken = "n" + elementToken;
                        final Element childElement = element.getChild(elementToken);
                        if (childElement == null) {
                            if (elementIndex == 0 || element.getChild("n" + (elementIndex - 1)) != null) {
                                final Element templateElement = element.getChild("n");
                                if (templateElement != null) {
                                    final Element copyElement = (Element)templateElement.clone();
                                    copyElement.setName(elementToken);
                                    element.addContent(copyElement);
                                }
                            }
                        }
                        else if (childElement.getAttribute("dirty") != null) {
                            childElement.setAttribute("dirty", SCORMConstants.TRUE);
                        }
                    }
                    element = element.getChild(elementToken);
                }
            }
            if (element == null || element.isRootElement()) {
                if (isKeyword(elementToken)) {
                    this.setErrorCode(402);
                }
                else {
                    this.setErrorCode(201);
                }
            }
            else {
                if (elementValue == null) {
                    elementValue = "";
                }
                retVal = this.setValue(element, elementValue);
            }
        }
        else {
            this.setErrorCode(301);
        }
        return String.valueOf(retVal);
    }
    
    private Document postTrackingCommand(final String command) {
        Document retVal = null;
        try {
            final URL trackingURL = new URL(this.mTrackingURL);
            try {
                final URLConnection connection = trackingURL.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                final OutputStream outputStream = connection.getOutputStream();
                final DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                String parameters = "COMMAND=" + command + "&" + "SESSION_ID" + "=" + this.mTrackingID + "&" + "TRACKING_TYPE" + "=" + 1 + "&" + "CHARACTER_ENCODING" + "=" + this.mCharacterEncoding;
                if (!command.equals("INITIALIZE")) {
                    final XMLOutputter xmlOutputter = new XMLOutputter();
                    final String xmlData = xmlOutputter.outputString(this.mXMLData);
                    parameters = String.valueOf(parameters) + "&SCORM_DATA=" + TrackingUtil.encode(xmlData, this.mCharacterEncoding);
                }
                try {
                    dataOutputStream.writeBytes(parameters);
                    dataOutputStream.flush();
                }
                finally {
                    dataOutputStream.close();
                    outputStream.close();
                }
                InputStream inputStream = null;
                try {
                    inputStream = connection.getInputStream();
                    if (!command.equals("FINISH")) {
                        final DataInputStream dataInputStream = new DataInputStream(inputStream);
                        final int size = 4096;
                        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(size);
                        byte[] bytes = new byte[size];
                        try {
                            int count;
                            while ((count = dataInputStream.read(bytes)) > 0) {
                                byteArrayOutputStream.write(bytes, 0, count);
                            }
                            bytes = byteArrayOutputStream.toByteArray();
                        }
                        finally {
                            byteArrayOutputStream.close();
                            dataInputStream.close();
                        }
                        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                        final SAXBuilder xmlBuilder = new SAXBuilder();
                        retVal = xmlBuilder.build(byteArrayInputStream);
                    }
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
            catch (IOException e) {
                this.setErrorCode(101);
                e.printStackTrace(System.out);
            }
            catch (JDOMException e2) {
                this.mInitialized = false;
                this.setErrorCode(101);
                e2.printStackTrace(System.out);
            }
        }
        catch (MalformedURLException e3) {
            this.setErrorCode(900);
            e3.printStackTrace(System.out);
        }
        return retVal;
    }
    
    private void resetErrorCode(final String methodName, final String[] parameterValues) {
        this.setErrorCode(0);
        this.mDiagnosticData = methodName;
        for (int arrayLength = parameterValues.length, i = 0; i < arrayLength; ++i) {
            String parameterValue = parameterValues[i];
            if (parameterValue == null) {
                parameterValue = "";
            }
            if (i == 0) {
                this.mDiagnosticData = String.valueOf(this.mDiagnosticData) + "(";
            }
            else {
                this.mDiagnosticData = String.valueOf(this.mDiagnosticData) + ", ";
            }
            this.mDiagnosticData = String.valueOf(this.mDiagnosticData) + "\"" + parameterValue + "\"";
        }
        this.mDiagnosticData = String.valueOf(this.mDiagnosticData) + ");";
        if (this.mDebug) {
            System.out.println(this.mDiagnosticData);
        }
    }
    
    private void setErrorCode(final int errorCode) {
        this.mErrorCode = errorCode;
    }
    
    private boolean setValue(final Element element, final String value) {
        boolean retVal = false;
        final String accessAttribute = element.getAttributeValue("access");
        if (accessAttribute == null) {
            this.setErrorCode(204);
        }
        else if (accessAttribute.equals("r")) {
            this.setErrorCode(403);
        }
        else if (isValidType(element, value)) {
            if (isImplemented(element)) {
                final String operationAttribute = element.getAttributeValue("operation");
                if (operationAttribute == null || operationAttribute.equals("replace")) {
                    element.setText(value);
                    retVal = true;
                }
                else if (operationAttribute.equals("append")) {
                    retVal = this.appendValue(element, value);
                }
            }
            else {
                this.setErrorCode(401);
            }
        }
        else {
            this.setErrorCode(405);
        }
        return retVal;
    }
    
    public void stop() {
        this.mInitialized = false;
        super.stop();
    }
    
    public APIAdapter() {
        this.mDebug = false;
        this.mInitialized = false;
        this.mDiagnosticData = "";
        this.mCharacterEncoding = "UTF8";
        this.mErrorCode = 301;
    }
}
