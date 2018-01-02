// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

import java.util.Enumeration;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

public class PackComposer
{
    public static byte[] genTransPack(final short n, final short n2, final Hashtable hashtable, final short pname, final String pvalue) {
        final ServParam[] array = { new ServParam() };
        array[0].pname = pname;
        array[0].pvalue = pvalue;
        return genTransPack(n, n2, hashtable, array);
    }
    
    public static byte[] genTransPack(final short n, final short n2, final Hashtable hashtable, final short pname, final String pvalue, final short pname2, final String pvalue2) {
        final ServParam[] array = { new ServParam(), null };
        array[0].pname = pname;
        array[0].pvalue = pvalue;
        array[1] = new ServParam();
        array[1].pname = pname2;
        array[1].pvalue = pvalue2;
        return genTransPack(n, n2, hashtable, array);
    }
    
    public static byte[] genTransPack(final short n, final short n2, final Hashtable hashtable, final short pname, final String pvalue, final short pname2, final String pvalue2, final short pname3, final String pvalue3) {
        final ServParam[] array = { new ServParam(), null, null };
        array[0].pname = pname;
        array[0].pvalue = pvalue;
        array[1] = new ServParam();
        array[1].pname = pname2;
        array[1].pvalue = pvalue2;
        array[2] = new ServParam();
        array[2].pname = pname3;
        array[2].pvalue = pvalue3;
        return genTransPack(n, n2, hashtable, array);
    }
    
    public static byte[] genTransPack(final short n, final short n2, Hashtable hashtable, final ServParam[] array) {
        String s = null;
        if (hashtable != null) {
            s = hashtable.get("encoding");
        }
        if (!encText(array, s)) {
            System.err.println("err94834,encoding not supported." + s);
            if (hashtable == null) {
                hashtable = new Hashtable<String, String>();
            }
            hashtable.put("encoding", ((Hashtable<K, String>)System.getProperties()).get("file.encoding"));
        }
        final byte[] service = createService(n, n2, flattenAttr(hashtable), array);
        if (service == null) {
            return null;
        }
        return createTrans(service, TransDef.TYPE_CHAT, (byte)0);
    }
    
    public static byte[] createTrans(final byte[] array, final byte[] array2, final byte b) {
        if (array.length > 32000) {
            return null;
        }
        final byte[] array3 = new byte[array2.length + 1 + 2 + array.length];
        for (int i = 0; i < array2.length; ++i) {
            array3[i] = array2[i];
        }
        array3[array2.length] = b;
        final byte[] bytes = ByteUtil.getBytes((short)array.length);
        array3[array2.length + 1] = bytes[0];
        array3[array2.length + 2] = bytes[1];
        final int n = array2.length + 3;
        for (int j = 0; j < array.length; ++j) {
            array3[j + n] = array[j];
        }
        return array3;
    }
    
    public static byte[] createService(final short n, final short n2, final String s, final ServParam[] array) {
        final ByteChain byteChain = new ByteChain();
        final byte[] attr = createAttr(s);
        int length;
        if (attr == null) {
            length = 0;
        }
        else {
            length = attr.length;
        }
        byteChain.add(ByteUtil.getBytes((short)(length + 1 + 2 + 2 + 2)));
        byteChain.add(ByteUtil.getBytes(n));
        byteChain.add(ByteUtil.getBytes(n2));
        int length2 = 0;
        if (array != null) {
            length2 = array.length;
        }
        byteChain.add((byte)length2);
        if (attr != null) {
            byteChain.add(attr);
        }
        final int addParams = addParams(byteChain, array);
        if (byteChain.isFull()) {
            return null;
        }
        final byte[] value = byteChain.get();
        if (addParams != length2) {
            value[6] = (byte)addParams;
        }
        return value;
    }
    
    private static byte[] createAttr(final String s) {
        if (s == null) {
            return null;
        }
        byte[] bytes;
        try {
            bytes = s.getBytes("US-ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return s.getBytes();
        }
        return bytes;
    }
    
    public static boolean encText(final ServParam[] array, final String s) {
        boolean encParam = true;
        if (array == null) {
            return encParam;
        }
        for (int i = 0; i < array.length; ++i) {
            final ServParam servParam = array[i];
            if (servParam != null) {
                encParam = encParam(servParam, s);
            }
        }
        return encParam;
    }
    
    private static boolean encParam(final ServParam servParam, String s) {
        if (s == null) {
            s = "UTF8";
        }
        final String pvalue = servParam.pvalue;
        if (pvalue == null) {
            servParam.encoded = new byte[0];
            return true;
        }
        try {
            servParam.encoded = pvalue.getBytes(s);
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            servParam.encoded = pvalue.getBytes();
            return false;
        }
        return true;
    }
    
    private static int addParams(final ByteChain byteChain, final ServParam[] array) {
        int n = 0;
        if (array == null) {
            return n;
        }
        for (int i = 0; i < array.length; ++i) {
            final ServParam servParam = array[i];
            if (servParam != null) {
                byteChain.add((byte)35);
                byteChain.add(ByteUtil.getBytes(servParam.pname));
                if (servParam.encoded != null) {
                    byteChain.add(ByteUtil.getBytes((short)servParam.encoded.length));
                    byteChain.add(servParam.encoded);
                }
                else {
                    byteChain.add(ByteUtil.getBytes((short)0));
                }
                ++n;
            }
        }
        return n;
    }
    
    private static String flattenAttr(final Hashtable hashtable) {
        if (hashtable == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        final Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String s2 = hashtable.get(s);
            sb.append(s);
            sb.append(":");
            sb.append(s2);
            sb.append("\n");
        }
        return sb.toString();
    }
}
