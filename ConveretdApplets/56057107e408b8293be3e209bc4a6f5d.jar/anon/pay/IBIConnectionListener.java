// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay;

import anon.util.captcha.IImageEncodedCaptcha;
import anon.util.captcha.ICaptchaSender;

public interface IBIConnectionListener
{
    void gotCaptcha(final ICaptchaSender p0, final IImageEncodedCaptcha p1);
}
