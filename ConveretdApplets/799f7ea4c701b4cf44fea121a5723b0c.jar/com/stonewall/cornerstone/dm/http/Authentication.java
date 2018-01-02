// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.dm.http;

import java.util.Date;
import org.xmodel.log.Log;

public class Authentication
{
    static final Log log;
    private static CookieHandler cookieHandler;
    
    static {
        log = Log.getLog(Authentication.class);
        Authentication.cookieHandler = null;
    }
    
    public static void setCookieHandler(final CookieHandler handler) {
        Authentication.cookieHandler = handler;
    }
    
    public static String xor(final char[] dataArray, final char[] patternArray) {
        String strResult = new String();
        if (dataArray.length != patternArray.length) {
            return strResult;
        }
        for (int i = 0; i < dataArray.length; ++i) {
            final int dat = Integer.parseInt(new StringBuilder(String.valueOf(dataArray[i])).toString());
            final int pat = Integer.parseInt(new StringBuilder(String.valueOf(patternArray[i])).toString());
            final int index = dat ^ pat;
            strResult = String.valueOf(strResult) + (char)index;
        }
        return strResult;
    }
    
    public static void setEncryptSeed(final String strPassPhrase, final String randomNumber) {
        String strInternalPageSeedHash = "";
        if (strPassPhrase.length() > 0) {
            strInternalPageSeedHash = MD5.calcMD5(String.valueOf(randomNumber) + strPassPhrase);
            Authentication.cookieHandler.setCookie("192.168.2.26", "PageSeed", strInternalPageSeedHash, "/", null, false);
        }
    }
    
    public static String verifyPassword(final String strPassPhrase, final String randonNumber1, final String randomNumber2) {
        String strInternalPageHash = new String();
        if (strPassPhrase.length() > 0) {
            strInternalPageHash = MD5.calcMD5(String.valueOf(randonNumber1) + strPassPhrase);
            setEncryptSeed(strPassPhrase, randomNumber2);
        }
        return strInternalPageHash;
    }
    
    public static String encryptUserPassword(final String strPassword, final String randomNumber) {
        String strPageSeedHash = new String();
        strPageSeedHash = Authentication.cookieHandler.getCookie("PageSeed");
        if (strPageSeedHash == null) {
            return "Error";
        }
        return changePassword(strPageSeedHash, randomNumber, strPassword, strPassword);
    }
    
    public static String changePassword(final String strOldPassword, final String randomNumber, final String strNewPassword, final String strConfirmPassword) {
        String strNewPasswordXOR = new String();
        final char[] newPasswordArray = new char[32];
        final char[] oldPasswordHashArray = new char[32];
        int i;
        for (i = 0, i = 0; i < 32; ++i) {
            oldPasswordHashArray[i] = (newPasswordArray[i] = '\0');
        }
        for (i = 0; i < strNewPassword.length(); ++i) {
            newPasswordArray[i] = strNewPassword.charAt(i);
        }
        String strOldPassHashb;
        for (strOldPassHashb = new String(MD5.calcMD5(String.valueOf(randomNumber) + strOldPassword)), i = 0; i < strOldPassHashb.length(); ++i) {
            oldPasswordHashArray[i] = strOldPassHashb.charAt(i);
        }
        strNewPasswordXOR = xor(oldPasswordHashArray, newPasswordArray);
        return strNewPasswordXOR;
    }
    
    public static String chapDigest(final String strId, final String strPass, final String strChal) {
        final int[] id = getBytes(strId);
        final int[] pass = getChars(strPass);
        final int[] chal = getBytes(strChal);
        final int[] inBuff = new int[id.length + pass.length + chal.length];
        System.arraycopy(id, 0, inBuff, 0, id.length);
        System.arraycopy(pass, 0, inBuff, id.length, pass.length);
        System.arraycopy(chal, 0, inBuff, id.length + pass.length, chal.length);
        final String strDigest = new String(MD5.calcMD5_2(inBuff));
        Authentication.log.debug(strDigest);
        return strDigest;
    }
    
    public static int[] getBytes(final String str) {
        final int[] buf = new int[str.length() / 2];
        int j = 0;
        for (int i = 0; i < str.length(); i += 2) {
            buf[j++] = Integer.parseInt(str.substring(i, i + 2), 16);
        }
        return buf;
    }
    
    public static int[] getChars(final String str) {
        final int[] buf = new int[str.length()];
        for (int i = 0; i < str.length(); ++i) {
            buf[i] = str.charAt(i);
        }
        return buf;
    }
}
