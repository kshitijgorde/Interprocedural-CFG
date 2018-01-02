// 
// Decompiled by Procyon v0.5.30
// 

package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.TimeZone;
import java.util.SimpleTimeZone;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class DERGeneralizedTime extends ASN1Object
{
    String time;
    
    public static DERGeneralizedTime getInstance(final Object o) {
        if (o == null || o instanceof DERGeneralizedTime) {
            return (DERGeneralizedTime)o;
        }
        if (o instanceof ASN1OctetString) {
            return new DERGeneralizedTime(((ASN1OctetString)o).getOctets());
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + o.getClass().getName());
    }
    
    public static DERGeneralizedTime getInstance(final ASN1TaggedObject asn1TaggedObject, final boolean b) {
        return getInstance(asn1TaggedObject.getObject());
    }
    
    public DERGeneralizedTime(final String time) {
        this.time = time;
        try {
            this.getDate();
        }
        catch (ParseException ex) {
            throw new IllegalArgumentException("invalid date string: " + ex.getMessage());
        }
    }
    
    public DERGeneralizedTime(final Date date) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.time = simpleDateFormat.format(date);
    }
    
    DERGeneralizedTime(final byte[] array) {
        final char[] array2 = new char[array.length];
        for (int i = 0; i != array2.length; ++i) {
            array2[i] = (char)(array[i] & 0xFF);
        }
        this.time = new String(array2);
    }
    
    public String getTimeString() {
        return this.time;
    }
    
    public String getTime() {
        if (this.time.charAt(this.time.length() - 1) == 'Z') {
            return this.time.substring(0, this.time.length() - 1) + "GMT+00:00";
        }
        final int n = this.time.length() - 5;
        final char char1 = this.time.charAt(n);
        if (char1 == '-' || char1 == '+') {
            return this.time.substring(0, n) + "GMT" + this.time.substring(n, n + 3) + ":" + this.time.substring(n + 3);
        }
        final int n2 = this.time.length() - 3;
        final char char2 = this.time.charAt(n2);
        if (char2 == '-' || char2 == '+') {
            return this.time.substring(0, n2) + "GMT" + this.time.substring(n2) + ":00";
        }
        return this.time + this.calculateGMTOffset();
    }
    
    private String calculateGMTOffset() {
        String s = "+";
        final TimeZone default1 = TimeZone.getDefault();
        int rawOffset = default1.getRawOffset();
        if (rawOffset < 0) {
            s = "-";
            rawOffset = -rawOffset;
        }
        int n = rawOffset / 3600000;
        final int n2 = (rawOffset - n * 60 * 60 * 1000) / 60000;
        try {
            if (default1.useDaylightTime() && default1.inDaylightTime(this.getDate())) {
                n += (s.equals("+") ? 1 : -1);
            }
        }
        catch (ParseException ex) {}
        return "GMT" + s + this.convert(n) + ":" + this.convert(n2);
    }
    
    private String convert(final int n) {
        if (n < 10) {
            return "0" + n;
        }
        return Integer.toString(n);
    }
    
    public Date getDate() throws ParseException {
        String s = this.time;
        SimpleDateFormat simpleDateFormat;
        if (this.time.endsWith("Z")) {
            if (this.hasFractionalSeconds()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSSS'Z'");
            }
            else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        }
        else if (this.time.indexOf(45) > 0 || this.time.indexOf(43) > 0) {
            s = this.getTime();
            if (this.hasFractionalSeconds()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSSSz");
            }
            else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        }
        else {
            if (this.hasFractionalSeconds()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSSS");
            }
            else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        }
        return simpleDateFormat.parse(s);
    }
    
    private boolean hasFractionalSeconds() {
        return this.time.indexOf(46) == 14;
    }
    
    private byte[] getOctets() {
        final char[] charArray = this.time.toCharArray();
        final byte[] array = new byte[charArray.length];
        for (int i = 0; i != charArray.length; ++i) {
            array[i] = (byte)charArray[i];
        }
        return array;
    }
    
    void encode(final DEROutputStream derOutputStream) throws IOException {
        derOutputStream.writeEncoded(24, this.getOctets());
    }
    
    boolean asn1Equals(final DERObject derObject) {
        return derObject instanceof DERGeneralizedTime && this.time.equals(((DERGeneralizedTime)derObject).time);
    }
    
    public int hashCode() {
        return this.time.hashCode();
    }
}
