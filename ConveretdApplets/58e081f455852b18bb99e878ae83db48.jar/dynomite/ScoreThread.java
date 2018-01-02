// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import java.net.URL;
import java.applet.Applet;
import netscape.javascript.JSObject;
import sexy.util.MD5;
import java.util.Date;
import java.util.Random;

public class ScoreThread implements Runnable
{
    DynomiteApplet mApplet;
    int mScore;
    
    public ScoreThread(final DynomiteApplet mApplet, final int mScore) {
        this.mApplet = mApplet;
        this.mScore = mScore;
        new Thread(this).start();
    }
    
    public void run() {
        final int abs = Math.abs((new Random().nextInt() + this.mApplet.mStartRandom.nextInt() + this.mScore) % 1000000000);
        final Date date = new Date();
        date.toString();
        final String string = "?game=DYNO&score=" + this.mScore;
        int hours = date.getHours();
        if (hours > 12) {
            hours -= 12;
        }
        String s = string + "&time=" + hours + ":";
        if (date.getMinutes() < 10) {
            s += "0";
        }
        String s2 = s + date.getMinutes() + ":";
        if (date.getSeconds() < 10) {
            s2 += "0";
        }
        final String string2 = s2 + date.getSeconds();
        String s3;
        if (date.getHours() >= 12) {
            s3 = string2 + " PM";
        }
        else {
            s3 = string2 + " AM";
        }
        String s4 = s3 + "&date=" + (date.getMonth() + 1) + "/" + date.getDate() + "/";
        if (date.getYear() % 100 < 10) {
            s4 += "0";
        }
        final String string3 = s4 + date.getYear() % 100 + "&rnd=" + abs;
        String s5 = string3 + "&ds=" + new MD5(string3 + "bT3.4e.j*").GetDigestString();
        final int index = s5.indexOf(" ");
        if (index > 0) {
            s5 = s5.substring(0, index) + "%20" + s5.substring(index + 1);
        }
        try {
            JSObject.getWindow((Applet)this.mApplet).call("SendScore", new Object[] { s5 });
        }
        catch (Exception ex2) {
            try {
                this.mApplet.getAppletContext().showDocument(new URL(s5), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
