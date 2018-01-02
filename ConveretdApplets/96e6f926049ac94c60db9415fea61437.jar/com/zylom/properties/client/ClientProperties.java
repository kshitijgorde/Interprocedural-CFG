// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.properties.client;

import java.util.Vector;
import java.net.URLConnection;
import com.zylom.cipher.CipherInputStream;
import java.io.BufferedInputStream;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.Enumeration;
import java.awt.Font;
import java.awt.Color;
import java.util.Hashtable;
import java.net.URL;
import java.io.InputStream;

public class ClientProperties
{
    private InputStream bufferedInputStream;
    private InputStream cipherInputStream;
    private URL codeBase;
    private String country;
    private URL fileURL;
    private Hashtable imageTable;
    private String language;
    private String lastReadCountry;
    private String lastReadLanguage;
    private String lastReadLookandfeel;
    private String lookandfeel;
    private String path;
    private Hashtable pixelsTable;
    private Hashtable propertyTable;
    private Hashtable soundTable;
    private XMLInputStream xmlFile;
    
    public ClientProperties(final URL codeBase, final String path, final String language, final String country, final String lookandfeel) throws Exception {
        this.propertyTable = new Hashtable();
        this.imageTable = new Hashtable();
        this.pixelsTable = new Hashtable();
        this.soundTable = new Hashtable();
        this.cipherInputStream = null;
        this.bufferedInputStream = null;
        this.xmlFile = null;
        this.path = "";
        this.codeBase = codeBase;
        this.path = path;
        this.language = language;
        this.country = country;
        this.lookandfeel = lookandfeel;
        this.parseFile(PropertyStrings.DEFAULT_FILE, null, null, null);
        if (!language.equals(this.lastReadLanguage) || !country.equals(this.lastReadCountry)) {
            this.parseFile("prop_" + language + "-" + country + "_" + PropertyStrings.DEFAULT_LOOKANDFEEL, language, country, PropertyStrings.DEFAULT_LOOKANDFEEL);
        }
        if (!lookandfeel.equals(this.lastReadLookandfeel)) {
            this.parseFile("prop_" + language + "-" + country + "_" + lookandfeel, language, country, lookandfeel);
        }
    }
    
    private void checkEndTag(final String tagName) throws Exception {
        final String token = this.xmlFile.getNextToken();
        if (!tagName.equals(this.xmlFile.getTagName()) || !this.xmlFile.isTagClosing()) {
            throw new Exception(PropertyStrings.EXCEPTION_CLOSE_TAG + token);
        }
    }
    
    private void closeFile() throws Exception {
        if (this.xmlFile != null) {
            this.xmlFile.close();
            this.xmlFile = null;
        }
    }
    
    private String escapeString(final String s) throws Exception {
        if (s == null) {
            return null;
        }
        final StringBuffer temp = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);
            if (c == '\\') {
                if (i + 1 >= s.length()) {
                    throw new Exception(PropertyStrings.EXCEPTION_MISSING_ESCAPE);
                }
                switch (s.charAt(i + 1)) {
                    case '\\': {
                        ++i;
                        temp.append('\\');
                        break;
                    }
                    case 'g': {
                        ++i;
                        temp.append('>');
                        break;
                    }
                    case 'l': {
                        ++i;
                        temp.append('<');
                        break;
                    }
                    case 'n': {
                        ++i;
                        temp.append('\n');
                        break;
                    }
                    case 'r': {
                        ++i;
                        temp.append('\r');
                        break;
                    }
                    case 't': {
                        ++i;
                        temp.append('\t');
                        break;
                    }
                    case '+': {
                        ++i;
                        temp.append('&');
                        break;
                    }
                    default: {
                        throw new Exception(PropertyStrings.EXCEPTION_ILLEGAL_ESCAPE + s.charAt(i + 1));
                    }
                }
            }
            else {
                if (c == '>' || c == '<' || c == '&') {
                    throw new Exception(PropertyStrings.EXCEPTION_ILLEGAL_CHARACTER + c);
                }
                temp.append(c);
            }
        }
        return temp.toString();
    }
    
    public int[] getArrayInteger(final String id) throws Exception {
        return (int[])this.getProperty(id);
    }
    
    public String[] getArrayString(final String id) throws Exception {
        return (String[])this.getProperty(id);
    }
    
    public boolean getBoolean(final String id) throws Exception {
        return (boolean)this.getProperty(id);
    }
    
    public Color getColor(final String id) throws Exception {
        return (Color)this.getProperty(id);
    }
    
    public double getDouble(final String id) throws Exception {
        return (double)this.getProperty(id);
    }
    
    public Font getFont(final String id) throws Exception {
        return (Font)this.getProperty(id);
    }
    
    private String getID() throws Exception {
        final String id = this.xmlFile.getAttributeValue(PropertyStrings.ATTRIBUTE_ID);
        if (id == null || id.length() == 0) {
            throw new Exception(PropertyStrings.EXCEPTION_INVALID_ID + id);
        }
        return id;
    }
    
    public Enumeration getImageIDs() {
        return this.imageTable.keys();
    }
    
    public String getImageName(final String id) throws Exception {
        if (this.imageTable.containsKey(id)) {
            return this.imageTable.get(id);
        }
        throw new Exception(PropertyStrings.EXCEPTION_ID_NOT_FOUND + id);
    }
    
    public Enumeration getImageNames() {
        return this.imageTable.elements();
    }
    
    public int getInteger(final String id) throws Exception {
        return (int)this.getProperty(id);
    }
    
    public long getLong(final String id) throws Exception {
        return (long)this.getProperty(id);
    }
    
    public Enumeration getPixelsIDs() {
        return this.pixelsTable.keys();
    }
    
    public String getPixelsName(final String id) throws Exception {
        if (this.pixelsTable.containsKey(id)) {
            return this.pixelsTable.get(id);
        }
        throw new Exception(PropertyStrings.EXCEPTION_ID_NOT_FOUND + id);
    }
    
    public Enumeration getPixelsNames() {
        return this.pixelsTable.elements();
    }
    
    public Point getPoint(final String id) throws Exception {
        return (Point)this.getProperty(id);
    }
    
    public Object getProperty(final String id) throws Exception {
        if (this.propertyTable.containsKey(id)) {
            return this.propertyTable.get(id);
        }
        throw new Exception(PropertyStrings.EXCEPTION_ID_NOT_FOUND + id);
    }
    
    public Enumeration getPropertyKeys() {
        return this.propertyTable.keys();
    }
    
    public Rectangle getRectangle(final String id) throws Exception {
        return (Rectangle)this.getProperty(id);
    }
    
    public Enumeration getSoundIDs() {
        return this.soundTable.keys();
    }
    
    public String getSoundName(final String id) throws Exception {
        if (this.soundTable.containsKey(id)) {
            return this.soundTable.get(id);
        }
        throw new Exception(PropertyStrings.EXCEPTION_ID_NOT_FOUND + id);
    }
    
    public Enumeration getSoundNames() {
        return this.soundTable.elements();
    }
    
    public String getString(final String id) throws Exception {
        return (String)this.getProperty(id);
    }
    
    private void openFile(final String fileName) throws Exception {
        try {
            this.fileURL = new URL(this.codeBase, this.path + fileName + PropertyStrings.EXTENTION_PROTECTED);
            final URLConnection urlConnection = this.fileURL.openConnection();
            urlConnection.setUseCaches(true);
            this.bufferedInputStream = new BufferedInputStream(urlConnection.getInputStream());
            this.cipherInputStream = new CipherInputStream(this.bufferedInputStream);
            this.xmlFile = new XMLInputStream(this.cipherInputStream);
        }
        catch (Exception ex) {
            this.fileURL = new URL(this.codeBase, this.path + fileName + PropertyStrings.EXTENTION_UNPROTECTED);
            final URLConnection urlConnection2 = this.fileURL.openConnection();
            urlConnection2.setUseCaches(true);
            this.bufferedInputStream = new BufferedInputStream(urlConnection2.getInputStream());
            this.xmlFile = new XMLInputStream(this.bufferedInputStream);
            System.out.println(PropertyStrings.WARNING_UPROTECTED_FILE + fileName + PropertyStrings.EXTENTION_UNPROTECTED);
        }
    }
    
    private void parseArrayInteger() throws Exception {
        final String id = this.getID();
        String token = this.xmlFile.getNextToken();
        final Vector tempIntegers = new Vector();
        while (PropertyStrings.TAG_INTEGER.equals(this.xmlFile.getTagName())) {
            final String value = this.xmlFile.getNextToken();
            tempIntegers.addElement(new Integer(value));
            this.checkEndTag(PropertyStrings.TAG_INTEGER);
            token = this.xmlFile.getNextToken();
        }
        if (!PropertyStrings.TAG_ARRAYINTEGER.equals(this.xmlFile.getTagName()) || !this.xmlFile.isTagClosing()) {
            throw new Exception(PropertyStrings.EXCEPTION_CLOSE_TAG + token);
        }
        final int[] result = new int[tempIntegers.size()];
        for (int i = 0; i < tempIntegers.size(); ++i) {
            result[i] = tempIntegers.elementAt(i);
        }
        this.putPropertyTable(id, result);
    }
    
    private void parseArrayString() throws Exception {
        final String id = this.getID();
        String token = this.xmlFile.getNextToken();
        final Vector tempStrings = new Vector();
        while (PropertyStrings.TAG_STRING.equals(this.xmlFile.getTagName())) {
            final String value = this.xmlFile.getNextToken();
            if (PropertyStrings.TAG_STRING.equals(this.xmlFile.getTagName())) {
                tempStrings.addElement("");
            }
            else {
                tempStrings.addElement(this.escapeString(value));
                this.checkEndTag(PropertyStrings.TAG_STRING);
            }
            token = this.xmlFile.getNextToken();
        }
        if (!PropertyStrings.TAG_ARRAYSTRING.equals(this.xmlFile.getTagName()) || !this.xmlFile.isTagClosing()) {
            throw new Exception(PropertyStrings.EXCEPTION_CLOSE_TAG + token);
        }
        final String[] result = new String[tempStrings.size()];
        tempStrings.copyInto(result);
        this.putPropertyTable(id, result);
    }
    
    private void parseBoolean() throws Exception {
        final String id = this.getID();
        final String value = this.xmlFile.getNextToken();
        if (value.equals("true")) {
            this.putPropertyTable(id, new Boolean(value));
        }
        else {
            if (!value.equals("false")) {
                throw new Exception(PropertyStrings.EXCEPTION_INVALID_BOOLEANVALUE + value);
            }
            this.putPropertyTable(id, new Boolean(value));
        }
        this.checkEndTag(PropertyStrings.TAG_BOOLEAN);
    }
    
    private void parseColor() throws Exception {
        final String id = this.getID();
        final int r = Integer.parseInt(this.parseValue("red"));
        final int g = Integer.parseInt(this.parseValue("green"));
        final int b = Integer.parseInt(this.parseValue("blue"));
        this.putPropertyTable(id, new Color(r, g, b));
        this.checkEndTag(PropertyStrings.TAG_COLOR);
    }
    
    private void parseDouble() throws Exception {
        final String id = this.getID();
        final String value = this.xmlFile.getNextToken();
        if (value.equals("NaN")) {
            this.putPropertyTable(id, new Double(Double.NaN));
        }
        else if (value.equals("INF")) {
            this.putPropertyTable(id, new Double(Double.POSITIVE_INFINITY));
        }
        else if (value.equals("-INF")) {
            this.putPropertyTable(id, new Double(Double.NEGATIVE_INFINITY));
        }
        else {
            this.putPropertyTable(id, new Double(value));
        }
        this.checkEndTag(PropertyStrings.TAG_DOUBLE);
    }
    
    private void parseFile(final String fileName, final String language, final String country, final String lookandfeel) throws Exception {
        try {
            this.openFile(fileName);
        }
        catch (Exception ex) {
            throw new Exception(PropertyStrings.EXCEPTION_COULD_OPEN_FILE + this.codeBase + this.path + fileName);
        }
        String token = this.xmlFile.getNextToken().trim();
        if (PropertyStrings.TAG_XML.equals(this.xmlFile.getTagName())) {
            throw new Exception(PropertyStrings.EXCEPTION_NO_XML_FILE + fileName);
        }
        token = this.xmlFile.getNextToken().trim();
        if (!PropertyStrings.TAG_PROPERTIES.equals(this.xmlFile.getTagName())) {
            throw new Exception(PropertyStrings.EXCEPTION_NO_PROPERTIES_FILE + fileName);
        }
        if (!PropertyStrings.SCHEMA_LOCATION.equals(this.xmlFile.getAttributeValue(PropertyStrings.ATTRIBUTE_SCHEMA_LOCATION))) {
            throw new Exception(PropertyStrings.EXCEPTION_SCHEMA_LOCATION + this.xmlFile.getAttributeValue(PropertyStrings.ATTRIBUTE_SCHEMA_LOCATION));
        }
        try {
            final double version = new Double(this.xmlFile.getAttributeValue(PropertyStrings.ATTRIBUTE_VERSION));
            if (version > PropertyStrings.VERSION) {
                throw new Exception(PropertyStrings.EXCEPTION_VERSION + version);
            }
        }
        catch (Exception ex2) {
            throw new Exception(PropertyStrings.EXCEPTION_VERSION + this.xmlFile.getAttributeValue(PropertyStrings.ATTRIBUTE_VERSION));
        }
        this.lastReadLanguage = this.xmlFile.getAttributeValue(PropertyStrings.ATTRIBUTE_LANGUAGE);
        if (this.lastReadLanguage == null || this.lastReadLanguage.length() != 2 || !this.lastReadLanguage.toLowerCase().equals(this.lastReadLanguage)) {
            throw new Exception(PropertyStrings.EXCEPTION_LANGUAGE + this.lastReadLanguage);
        }
        if (!this.lastReadLanguage.equals(language) && fileName.indexOf(PropertyStrings.DEFAULT_FILE) < 0) {
            throw new Exception(PropertyStrings.EXCEPTION_LANGUAGE + this.lastReadLanguage);
        }
        this.lastReadCountry = this.xmlFile.getAttributeValue(PropertyStrings.ATTRIBUTE_COUNTRY);
        if (this.lastReadCountry == null || this.lastReadCountry.length() != 2 || !this.lastReadCountry.toUpperCase().equals(this.lastReadCountry)) {
            throw new Exception(PropertyStrings.EXCEPTION_COUNTRY + this.lastReadCountry);
        }
        if (!this.lastReadCountry.equals(country) && fileName.indexOf(PropertyStrings.DEFAULT_FILE) < 0) {
            throw new Exception(PropertyStrings.EXCEPTION_COUNTRY + this.lastReadCountry);
        }
        this.lastReadLookandfeel = this.xmlFile.getAttributeValue(PropertyStrings.ATTRIBUTE_LOOKANDFEEL);
        if (this.lastReadLookandfeel == null || this.lastReadLookandfeel.length() == 0 || !this.lastReadLookandfeel.toUpperCase().equals(this.lastReadLookandfeel)) {
            throw new Exception(PropertyStrings.EXCEPTION_LOOKANDFEEL + this.lastReadLookandfeel);
        }
        if (!this.lastReadLookandfeel.equals(lookandfeel) && fileName.indexOf(PropertyStrings.DEFAULT_FILE) < 0) {
            throw new Exception(PropertyStrings.EXCEPTION_LOOKANDFEEL + this.lastReadLookandfeel);
        }
        token = this.xmlFile.getNextToken();
        while (!PropertyStrings.TAG_PROPERTIES.equals(this.xmlFile.getTagName())) {
            if (PropertyStrings.TAG_STRING.equals(this.xmlFile.getTagName())) {
                this.parseString();
            }
            else if (PropertyStrings.TAG_INTEGER.equals(this.xmlFile.getTagName())) {
                this.parseInteger();
            }
            else if (PropertyStrings.TAG_LONG.equals(this.xmlFile.getTagName())) {
                this.parseLong();
            }
            else if (PropertyStrings.TAG_DOUBLE.equals(this.xmlFile.getTagName())) {
                this.parseDouble();
            }
            else if (PropertyStrings.TAG_RECTANGLE.equals(this.xmlFile.getTagName())) {
                this.parseRectangle();
            }
            else if (PropertyStrings.TAG_POINT.equals(this.xmlFile.getTagName())) {
                this.parsePoint();
            }
            else if (PropertyStrings.TAG_COLOR.equals(this.xmlFile.getTagName())) {
                this.parseColor();
            }
            else if (PropertyStrings.TAG_FONT.equals(this.xmlFile.getTagName())) {
                this.parseFont();
            }
            else if (PropertyStrings.TAG_BOOLEAN.equals(this.xmlFile.getTagName())) {
                this.parseBoolean();
            }
            else if (PropertyStrings.TAG_ARRAYSTRING.equals(this.xmlFile.getTagName())) {
                this.parseArrayString();
            }
            else if (PropertyStrings.TAG_ARRAYINTEGER.equals(this.xmlFile.getTagName())) {
                this.parseArrayInteger();
            }
            else if (PropertyStrings.TAG_IMAGE.equals(this.xmlFile.getTagName())) {
                this.parseImage();
            }
            else if (PropertyStrings.TAG_PIXELS.equals(this.xmlFile.getTagName())) {
                this.parsePixels();
            }
            else {
                if (!PropertyStrings.TAG_SOUND.equals(this.xmlFile.getTagName())) {
                    throw new Exception(PropertyStrings.EXCEPTION_UNEXPECTED_TOKEN + token);
                }
                this.parseSound();
            }
            token = this.xmlFile.getNextToken().trim();
        }
        if (!PropertyStrings.TAG_PROPERTIES.equals(this.xmlFile.getTagName())) {
            throw new Exception(PropertyStrings.EXCEPTION_BAD_END_OF_FILE);
        }
        try {
            this.closeFile();
        }
        catch (Exception ex2) {
            throw new Exception(PropertyStrings.EXCEPTION_COULD_CLOSE_FILE + fileName);
        }
    }
    
    private void parseFont() throws Exception {
        final String id = this.getID();
        final String type = this.parseValue("Type");
        final String tempStyle = this.parseValue("Style");
        int style = 0;
        if (tempStyle.indexOf("bold") >= 0) {
            ++style;
        }
        if (tempStyle.indexOf("italic") >= 0) {
            style += 2;
        }
        final int size = Integer.parseInt(this.parseValue("Size"));
        this.putPropertyTable(id, new Font(type, style, size));
        this.checkEndTag(PropertyStrings.TAG_FONT);
    }
    
    private void parseImage() throws Exception {
        final String id = this.getID();
        final String value = this.xmlFile.getNextToken();
        this.putImageTable(id, value);
        this.checkEndTag(PropertyStrings.TAG_IMAGE);
    }
    
    private void parseInteger() throws Exception {
        final String id = this.getID();
        final String value = this.xmlFile.getNextToken();
        if (value.startsWith("#")) {
            this.putPropertyTable(id, new Integer(Integer.parseInt(value.substring(1), 16)));
        }
        else {
            this.putPropertyTable(id, new Integer(value));
        }
        this.checkEndTag(PropertyStrings.TAG_INTEGER);
    }
    
    private void parseLong() throws Exception {
        final String id = this.getID();
        final String value = this.xmlFile.getNextToken();
        this.putPropertyTable(id, new Long(value));
        this.checkEndTag(PropertyStrings.TAG_LONG);
    }
    
    private void parsePixels() throws Exception {
        final String id = this.getID();
        final String value = this.xmlFile.getNextToken();
        this.putPixelsTable(id, value);
        this.checkEndTag(PropertyStrings.TAG_PIXELS);
    }
    
    private void parsePoint() throws Exception {
        final String id = this.getID();
        final int x = Integer.parseInt(this.parseValue("x"));
        final int y = Integer.parseInt(this.parseValue("y"));
        this.putPropertyTable(id, new Point(x, y));
        this.checkEndTag(PropertyStrings.TAG_POINT);
    }
    
    private void parseRectangle() throws Exception {
        final String id = this.getID();
        final int x = Integer.parseInt(this.parseValue("x"));
        final int y = Integer.parseInt(this.parseValue("y"));
        final int w = Integer.parseInt(this.parseValue("width"));
        final int h = Integer.parseInt(this.parseValue("height"));
        this.putPropertyTable(id, new Rectangle(x, y, w, h));
        this.checkEndTag(PropertyStrings.TAG_RECTANGLE);
    }
    
    private void parseSound() throws Exception {
        final String id = this.getID();
        final String value = this.xmlFile.getNextToken();
        this.putSoundTable(id, value);
        this.checkEndTag(PropertyStrings.TAG_SOUND);
    }
    
    private void parseString() throws Exception {
        final String id = this.getID();
        final String value = this.xmlFile.getNextToken();
        if (PropertyStrings.TAG_STRING.equals(this.xmlFile.getTagName())) {
            this.putPropertyTable(id, "");
        }
        else {
            this.putPropertyTable(id, this.escapeString(value));
            this.checkEndTag(PropertyStrings.TAG_STRING);
        }
    }
    
    private String parseValue(final String tagName) throws Exception {
        String token = this.xmlFile.getNextToken().trim();
        if (!tagName.equals(this.xmlFile.getTagName())) {
            throw new Exception(PropertyStrings.EXCEPTION_TAG_NOT_FOUND + tagName);
        }
        final String temp = this.xmlFile.getNextToken().trim();
        token = this.xmlFile.getNextToken().trim();
        if (!tagName.equals(this.xmlFile.getTagName())) {
            throw new Exception(PropertyStrings.EXCEPTION_TAG_NOT_CLOSED + tagName);
        }
        return temp;
    }
    
    protected void putImageTable(final Object key, final Object value) {
        this.imageTable.put(key, value);
    }
    
    protected void putPixelsTable(final Object key, final Object value) {
        this.pixelsTable.put(key, value);
    }
    
    protected void putPropertyTable(final Object key, final Object value) {
        this.propertyTable.put(key, value);
    }
    
    protected void putSoundTable(final Object key, final Object value) {
        this.soundTable.put(key, value);
    }
}
