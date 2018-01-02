import netscape.javascript.JSObject;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

final class HighScoreTool
{
    private Applet ap;
    private String email;
    private int score;
    private int level;
    private String file;
    private String dir;
    private String applet;
    
    public HighScoreTool(final Applet ap) {
        this.ap = null;
        this.email = "";
        this.score = 0;
        this.level = 0;
        this.file = "highsc";
        this.dir = "";
        this.applet = "HighScoreHS";
        this.ap = ap;
    }
    
    private int getScore() {
        return this.score;
    }
    
    public void setScore(final int score) {
        this.score = score;
    }
    
    private int getLevel() {
        return this.level;
    }
    
    public void setLevel(final int level) {
        this.level = level;
    }
    
    private String getFile() {
        return this.file;
    }
    
    public void setFile(final String file) {
        this.file = file;
    }
    
    private String getDir() {
        return this.dir;
    }
    
    public void setDir(final String dir) {
        this.dir = dir;
    }
    
    private String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    private String getApplet() {
        return this.applet;
    }
    
    public void setApplet(final String applet) {
        this.applet = applet;
    }
    
    public void doHigh() {
        URL url = null;
        final String string = "http://www.mycgiserver.com/servlet/realapplets.ServletHS2?Score=" + this.getScore() + "&Level=" + this.getLevel() + "&Code=" + this.getCode(this.getScore(), this.getLevel()) + "&Dir=" + this.getDir() + "&File=" + this.getFile() + "&Applet=" + this.getApplet();
        try {
            url = new URL(string);
            JSObject.getWindow(this.ap).eval("window.open('" + string + "','High_score','status=yes,width=450,height=450, toolbar=no, location=no,menubar=no,scrollbars=no,resize=yes,copyhistory=no')");
        }
        catch (Exception ex) {
            System.out.println("Does your applet tag include MAYSCRIPT?");
            System.out.println(ex);
            this.ap.getAppletContext().showDocument(url, "high");
        }
    }
    
    private String getCode(final int n, final int n2) {
        final int n3 = (n * n2 + 13101) % 10000;
        String s;
        for (s = "" + n3 / 100; s.length() < 2; s = 0 + s) {}
        String s2;
        for (s2 = "" + n3 % 100; s2.length() < 2; s2 = 0 + s2) {}
        final String string = s2 + s;
        final String string2 = "" + (int)(Math.random() * 10.0);
        String s3 = "" + (int)(Math.random() * 1000.0);
        for (int i = 3; i > s3.length(); s3 += string2, --i) {}
        return string2 + string + s3;
    }
}
