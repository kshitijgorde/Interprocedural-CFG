// 
// Decompiled by Procyon v0.5.30
// 

package anon.util.captcha;

import java.awt.Image;

public interface IImageEncodedCaptcha
{
    Image getImage();
    
    String getCharacterSet();
    
    int getCharacterNumber();
    
    byte[] solveCaptcha(final String p0, final byte[] p1) throws Exception;
}
