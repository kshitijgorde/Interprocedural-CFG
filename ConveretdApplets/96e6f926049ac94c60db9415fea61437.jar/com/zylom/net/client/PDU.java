// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.net.client;

import java.util.Enumeration;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

public class PDU
{
    public static final int VALUE_TYPE_STRING = 0;
    public static final int VALUE_TYPE_INTEGER = 1;
    public static final int VALUE_TYPE_DOUBLE = 2;
    public static final int VALUE_TYPE_BOOLEAN = 3;
    public static final int VALUE_TYPE_LONG = 4;
    public static final int VALUE_TYPE_BYTEARRAY = 5;
    public static final int PACKET_TYPE_CUSTOM = 0;
    public static final int PACKET_TYPE_REQUEST_GAME = 1;
    public static final int PACKET_TYPE_LEAVE_GAME = 2;
    public static final int PACKET_TYPE_PING = 3;
    public static final int PACKET_TYPE_CUSTOM_TARGETED = 4;
    public static final int PACKET_TYPE_DISCONNECT_WARNING = 5;
    public static final int PACKET_TYPE_PLAYER_JOINED = 101;
    public static final int PACKET_TYPE_PLAYER_LEFT = 102;
    public static final int PACKET_TYPE_GAME_INFO = 103;
    public static final int DISCONNECT_REASON_DUPLICATE_CONNECTIONS = 1;
    private int type;
    private long serverReceiveTime;
    private long serverSendTime;
    private final Hashtable hashtable;
    
    public PDU() {
        this.hashtable = new Hashtable();
    }
    
    public static String decode(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                case 43: {
                    sb.append(' ');
                    break;
                }
                case 37: {
                    try {
                        sb.append((char)Integer.parseInt(s.substring(i + 1, i + 3), 16));
                    }
                    catch (NumberFormatException ex) {
                        throw new IllegalArgumentException();
                    }
                    i += 2;
                    break;
                }
                default: {
                    sb.append(char1);
                    break;
                }
            }
        }
        String string = sb.toString();
        try {
            string = new String(string.getBytes("8859_1"));
        }
        catch (UnsupportedEncodingException ex2) {}
        return string;
    }
    
    public PDU(final String s, final byte[] array) {
        this.hashtable = new Hashtable();
        if (s.equalsIgnoreCase("application/x-www-form-urlencoded")) {
            try {
                final String line = new BufferedReader(new StringReader(new String(array))).readLine();
                while (line != null) {
                    final int index = line.indexOf("=");
                    if (index > 0) {
                        this.hashtable.put(decode(line.substring(0, index)), decode(line.substring(index + 1)));
                    }
                }
                return;
            }
            catch (IOException ex) {
                return;
            }
        }
        this.hashtable.put(s, array);
    }
    
    public int getType() {
        return this.type;
    }
    
    public void setType(final int type) {
        this.type = type;
    }
    
    public long getServerReceiveTime() {
        return this.serverReceiveTime;
    }
    
    public void setServerReceiveTime(final long serverReceiveTime) {
        this.serverReceiveTime = serverReceiveTime;
    }
    
    public long getServerSendTime() {
        return this.serverSendTime;
    }
    
    public void setServerSendTime(final long serverSendTime) {
        this.serverSendTime = serverSendTime;
    }
    
    public void add(final String s, final String s2) {
        if (s2 != null) {
            this.hashtable.put(s, s2);
        }
    }
    
    public void add(final String s, final byte[] array) {
        if (array != null) {
            this.hashtable.put(s, array);
        }
    }
    
    public void add(final String s, final int n) {
        this.hashtable.put(s, new Integer(n));
    }
    
    public void add(final String s, final double n) {
        this.hashtable.put(s, new Double(n));
    }
    
    public void add(final String s, final long n) {
        this.hashtable.put(s, new Long(n));
    }
    
    public void add(final String s, final boolean b) {
        this.hashtable.put(s, new Boolean(b));
    }
    
    public Enumeration names() {
        return this.hashtable.keys();
    }
    
    public int size() {
        return this.hashtable.size();
    }
    
    public Object getObject(final String s) throws RuntimeException {
        final Object value = this.hashtable.get(s);
        if (value == null) {
            throw new NullPointerException();
        }
        return value;
    }
    
    public String getString(final String s) throws RuntimeException {
        final Object value = this.hashtable.get(s);
        if (value == null) {
            throw new NullPointerException();
        }
        return value.toString();
    }
    
    public byte[] getByteArray(final String s) throws RuntimeException {
        final byte[] value = this.hashtable.get(s);
        if (value == null || !(value instanceof byte[])) {
            throw new NullPointerException();
        }
        return value;
    }
    
    public int getInt(final String s) throws RuntimeException {
        final Object value = this.hashtable.get(s);
        if (!(value instanceof Integer)) {
            return Integer.parseInt(value.toString());
        }
        return this.hashtable.get(s);
    }
    
    public long getLong(final String s) throws RuntimeException {
        final Object value = this.hashtable.get(s);
        if (!(value instanceof Long)) {
            return Long.parseLong(value.toString());
        }
        return this.hashtable.get(s);
    }
    
    public double getDouble(final String s) throws RuntimeException {
        if (!(this.hashtable.get(s) instanceof Double)) {
            throw new RuntimeException("No double value found in PDU");
        }
        return this.hashtable.get(s);
    }
    
    public boolean getBoolean(final String s) throws RuntimeException {
        final Object value = this.hashtable.get(s);
        if (!(value instanceof Boolean)) {
            return value.toString().equalsIgnoreCase("true");
        }
        return this.hashtable.get(s);
    }
}
