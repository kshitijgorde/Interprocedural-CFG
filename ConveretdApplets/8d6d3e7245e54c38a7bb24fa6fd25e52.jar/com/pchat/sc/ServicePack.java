// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.io.Serializable;

public class ServicePack implements Serializable
{
    public static final int MIN_HEADER_SIZE = 7;
    public static final int PARAM_HEAD = 5;
    public short category;
    public short command;
    public Properties attributeList;
    public String attributes;
    public int paramCount;
    public ServParam[] paramList;
    public boolean isValid;
    public String errorMsg;
    
    public ServicePack() {
        this.category = 0;
        this.command = 0;
        this.isValid = false;
        this.errorMsg = null;
        this.attributeList = new Properties();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("valid=" + this.isValid);
        sb.append(",err=" + this.errorMsg);
        sb.append(",cat=" + this.category);
        sb.append(",com=" + this.command);
        sb.append(",attr=" + this.attributes);
        if (this.paramList == null) {
            sb.append(",params=null ");
            return sb.toString();
        }
        sb.append(",SPack-pLen=" + this.paramCount + " p-array len=" + this.paramList.length + " ");
        for (int i = 0; i < this.paramList.length; ++i) {
            sb.append(this.paramList[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public String getValue(final short n) {
        if (this.paramCount == 0) {
            return null;
        }
        for (int i = 0; i < this.paramList.length; ++i) {
            if (this.paramList[i].pname == n) {
                return this.paramList[i].pvalue;
            }
        }
        return null;
    }
    
    public static ServicePack parse(final TransPack transPack) {
        final ServicePack servicePack = new ServicePack();
        servicePack.isValid = true;
        if (transPack.data == null) {
            servicePack.isValid = false;
            servicePack.errorMsg = "Err8934.";
            return servicePack;
        }
        if (transPack.length != transPack.data.length) {
            servicePack.isValid = false;
            servicePack.errorMsg = "Err93763.LenMis";
            return servicePack;
        }
        if (transPack.length < 7) {
            servicePack.isValid = false;
            servicePack.errorMsg = "Err526.";
            return servicePack;
        }
        final short short1 = ByteUtil.getShort(transPack.data, 0);
        if (short1 < 7 || short1 > transPack.length) {
            servicePack.errorMsg = "Err98573.Range," + short1 + "," + transPack.length;
            servicePack.isValid = false;
            return servicePack;
        }
        final ByteChain byteChain = new ByteChain();
        byteChain.add(transPack.data, 2, short1 - 2);
        final ByteChain byteChain2 = new ByteChain();
        byteChain2.add(transPack.data);
        byteChain2.shiftLeft(short1);
        doHeader(servicePack, byteChain);
        if (!servicePack.isValid) {
            return servicePack;
        }
        doParams(servicePack, byteChain2);
        return servicePack;
    }
    
    private static void doHeader(final ServicePack servicePack, final ByteChain byteChain) {
        final byte[] array = { byteChain.get(0), byteChain.get(1) };
        servicePack.category = ByteUtil.getShort(array);
        array[0] = byteChain.get(2);
        array[1] = byteChain.get(3);
        servicePack.command = ByteUtil.getShort(array);
        servicePack.paramCount = byteChain.get(4);
        if (servicePack.paramCount < 0) {
            servicePack.errorMsg = "Err93428.Cnt";
            servicePack.isValid = false;
            return;
        }
        byteChain.shiftLeft(5);
        doAttr(servicePack, byteChain);
    }
    
    private static void doAttr(final ServicePack servicePack, final ByteChain byteChain) {
        if (byteChain.length() == 0) {
            return;
        }
        byteChain.get();
        String attributes;
        try {
            attributes = new String(byteChain.get(), "US-ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            attributes = new String(byteChain.get());
        }
        servicePack.attributes = attributes;
        final Properties decAttributes = StringUtil.decAttributes(attributes);
        if (decAttributes != null) {
            servicePack.attributeList = decAttributes;
        }
    }
    
    private static void doParams(final ServicePack servicePack, final ByteChain byteChain) {
        final ServParam[] paramList = new ServParam[servicePack.paramCount];
        servicePack.paramList = paramList;
        if (servicePack.paramCount == 0) {
            if (byteChain.length() > 0) {
                servicePack.errorMsg = "Err83782.Over";
                servicePack.isValid = false;
            }
            return;
        }
        for (int i = 0; i < paramList.length; ++i) {
            parseParam(servicePack, byteChain, paramList[i] = new ServParam());
            if (!servicePack.isValid) {
                return;
            }
        }
        if (byteChain.length() != 0) {
            servicePack.errorMsg = "Err82912.leftover";
            servicePack.isValid = false;
        }
    }
    
    private static void parseParam(final ServicePack servicePack, final ByteChain byteChain, final ServParam servParam) {
        if (byteChain.length() == 0) {
            return;
        }
        if (byteChain.length() < 5) {
            servicePack.isValid = false;
            servicePack.errorMsg = "Err99382.shortH";
            return;
        }
        if (byteChain.get(0) != 35) {
            servicePack.errorMsg = "Err93485.Iden";
            servicePack.isValid = false;
            return;
        }
        final byte[] array = { byteChain.get(1), byteChain.get(2) };
        servParam.pname = ByteUtil.getShort(array);
        array[0] = byteChain.get(3);
        array[1] = byteChain.get(4);
        final short short1 = ByteUtil.getShort(array);
        if (short1 < 0 || short1 + 5 > byteChain.length()) {
            servicePack.errorMsg = "Err892372";
            servicePack.isValid = false;
            return;
        }
        servParam.encoded = byteChain.getBytes(5, short1);
        byteChain.shiftLeft(short1 + 5);
        String property = servicePack.attributeList.getProperty("encoding");
        if (StringUtil.isTrimmedEmpty(property)) {
            property = "UTF8";
        }
        try {
            servParam.pvalue = new String(servParam.encoded, property);
        }
        catch (UnsupportedEncodingException ex) {
            servParam.pvalue = new String(servParam.encoded);
            servicePack.errorMsg = "upsupported encoding,[" + property + "]";
        }
    }
}
