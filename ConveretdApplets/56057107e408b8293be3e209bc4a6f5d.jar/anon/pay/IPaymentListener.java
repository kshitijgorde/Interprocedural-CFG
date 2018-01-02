// 
// Decompiled by Procyon v0.5.30
// 

package anon.pay;

import anon.util.captcha.IImageEncodedCaptcha;
import anon.util.captcha.ICaptchaSender;
import anon.pay.xml.XMLErrorMessage;
import anon.infoservice.MixCascade;
import java.util.EventListener;

public interface IPaymentListener extends EventListener
{
    int accountCertRequested(final MixCascade p0);
    
    void accountError(final XMLErrorMessage p0, final boolean p1);
    
    void accountActivated(final PayAccount p0);
    
    void accountRemoved(final PayAccount p0);
    
    void accountAdded(final PayAccount p0);
    
    void creditChanged(final PayAccount p0);
    
    void gotCaptcha(final ICaptchaSender p0, final IImageEncodedCaptcha p1);
}
