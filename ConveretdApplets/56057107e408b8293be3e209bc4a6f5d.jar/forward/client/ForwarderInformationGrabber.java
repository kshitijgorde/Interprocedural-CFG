// 
// Decompiled by Procyon v0.5.30
// 

package forward.client;

import logging.LogHolder;
import logging.LogType;
import anon.util.captcha.ZipBinaryImageCaptchaClient;
import anon.util.XMLUtil;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import anon.infoservice.InfoServiceHolder;
import anon.util.captcha.IImageEncodedCaptcha;

public class ForwarderInformationGrabber
{
    public static final int RETURN_SUCCESS = 0;
    public static final int RETURN_INFOSERVICE_ERROR = 1;
    public static final int RETURN_UNKNOWN_ERROR = 2;
    public static final int RETURN_NO_CAPTCHA_IMPLEMENTATION = 3;
    private int m_errorCode;
    private IImageEncodedCaptcha m_captcha;
    
    public ForwarderInformationGrabber() {
        this.m_captcha = null;
        final Element forwarder = InfoServiceHolder.getInstance().getForwarder();
        if (forwarder != null) {
            final NodeList elementsByTagName = forwarder.getElementsByTagName("CaptchaEncoded");
            if (elementsByTagName.getLength() > 0) {
                this.m_errorCode = this.findCaptchaImplementation((Element)elementsByTagName.item(0));
            }
            else {
                this.m_errorCode = 2;
            }
        }
        else {
            this.m_errorCode = 1;
        }
    }
    
    public ForwarderInformationGrabber(final String s) {
        this.m_captcha = null;
        try {
            final NodeList elementsByTagName = XMLUtil.toXMLDocument(s).getElementsByTagName("JapForwarder");
            if (elementsByTagName.getLength() > 0) {
                final NodeList elementsByTagName2 = ((Element)elementsByTagName.item(0)).getElementsByTagName("CaptchaEncoded");
                if (elementsByTagName2.getLength() > 0) {
                    this.m_errorCode = this.findCaptchaImplementation((Element)elementsByTagName2.item(0));
                }
                else {
                    this.m_errorCode = 2;
                }
            }
            else {
                this.m_errorCode = 2;
            }
        }
        catch (Exception ex) {
            this.m_errorCode = 2;
        }
    }
    
    public int getErrorCode() {
        return this.m_errorCode;
    }
    
    public IImageEncodedCaptcha getCaptcha() {
        return this.m_captcha;
    }
    
    private int findCaptchaImplementation(final Element element) {
        final NodeList elementsByTagName = element.getElementsByTagName("CaptchaDataFormat");
        int n;
        if (elementsByTagName.getLength() > 0) {
            if ("ZIP_BINARY_IMAGE".equals(((Element)elementsByTagName.item(0)).getFirstChild().getNodeValue())) {
                try {
                    this.m_captcha = new ZipBinaryImageCaptchaClient(element);
                    n = 0;
                }
                catch (Exception ex) {
                    LogHolder.log(3, LogType.MISC, "Error while creating the captcha implementation!", ex);
                    n = 2;
                }
            }
            else {
                n = 3;
            }
        }
        else {
            n = 2;
        }
        return n;
    }
}
