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
        System.out.println("Init HSTool");
    }
    
    public void doHigh() {
        URL url = null;
        final String string = "http://www.mycgiserver.com/servlet/realapplets.ServletHS2?Score=" + this.getScore() + "&Level=" + this.getLevel() + "&Code=" + this.getCode(this.getScore(), this.getLevel()) + "&Dir=" + this.getDir() + "&File=" + this.getFile() + "&Applet=" + this.getApplet();
        try {
            url = new URL(string);
            JSObject.getWindow(this.ap).eval("window.open('" + string + "','High_score','status=yes,width=450,height=450, toolbar=no, location=no,menubar=no,scrollbars=no,resize=yes,copyhistory=no')");
        }
        catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Does your applet tag include MAYSCRIPT?");
            this.ap.getAppletContext().showDocument(url, "high");
        }
    }
    
    private String getApplet() {
        return this.applet;
    }
    
    private String getCode(final int n, final int n2) {
        final int n3 = (n * n2 + 13101) % 10000;
        String s;
        for (s = String.valueOf(n3 / 100); s.length() < 2; s = String.valueOf(0) + s) {}
        String s2;
        for (s2 = String.valueOf(n3 % 100); s2.length() < 2; s2 = String.valueOf(0) + s2) {}
        final String string = String.valueOf(s2) + s;
        final String value = String.valueOf((int)(Math.random() * 10.0));
        String s3 = String.valueOf((int)(Math.random() * 1000.0));
        for (int i = 3; i > s3.length(); s3 = String.valueOf(s3) + value, --i) {}
        return String.valueOf(value) + string + s3;
    }
    
    private String getDir() {
        return this.dir;
    }
    
    private String getEmail() {
        return this.email;
    }
    
    private String getFile() {
        return this.file;
    }
    
    private int getLevel() {
        return this.level;
    }
    
    private int getScore() {
        return this.score;
    }
    
    public void setApplet(final String applet) {
        this.applet = applet;
    }
    
    public void setDir(final String dir) {
        this.dir = dir;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public void setFile(final String file) {
        this.file = file;
    }
    
    public void setLevel(final int level) {
        this.level = level;
    }
    
    public void setScore(final int score) {
        this.score = score;
    }
}
