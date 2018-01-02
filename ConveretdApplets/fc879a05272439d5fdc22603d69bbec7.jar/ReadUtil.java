import java.util.StringTokenizer;
import java.util.Vector;
import java.io.InputStream;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class ReadUtil extends Thread
{
    String file;
    GConsole console;
    Thread t;
    
    public ReadUtil(final GConsole console, final String file) {
        this.file = "na.txt";
        this.file = file;
        this.console = console;
        this.t = new Thread(this);
    }
    
    public void loadAll() {
        this.t.start();
    }
    
    private String readFile(final String s) {
        int i = 0;
        String string = "";
        try {
            final InputStream openStream = new URL(s).openStream();
            while (i != -1) {
                i = openStream.read();
                if (i != -1 && (char)i != '\r') {
                    string = String.valueOf(string) + (char)i;
                }
            }
            openStream.close();
        }
        catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Corrupted data file or file is missing");
        }
        return string;
    }
    
    private void returnData(final Vector vector) {
        this.console.giveData(vector);
    }
    
    public void run() {
        if (this.file == null) {
            this.file = "data.txt";
        }
        final Vector<Game> vector = new Vector<Game>();
        final StringTokenizer stringTokenizer = new StringTokenizer(this.readFile(String.valueOf(String.valueOf(this.console.getCodeBase())) + this.file), "\n");
        while (stringTokenizer.hasMoreTokens()) {
            final Game game = new Game();
            game.setDir(stringTokenizer.nextToken());
            final StringTokenizer stringTokenizer2 = new StringTokenizer(this.readFile(String.valueOf(String.valueOf(this.console.getCodeBase())) + game.getDir() + "/" + "info.txt"), "\n");
            game.setName(stringTokenizer2.nextToken());
            game.setFontWidth(this.console.getFontWidth(game.getName()));
            game.setHTML(stringTokenizer2.nextToken());
            game.setDimension(Integer.parseInt(stringTokenizer2.nextToken()), Integer.parseInt(stringTokenizer2.nextToken()));
            while (stringTokenizer2.hasMoreTokens()) {
                game.addToDescription(stringTokenizer2.nextToken());
            }
            vector.addElement(game);
            System.out.println(String.valueOf(game.getName()) + " " + game.getWidth());
        }
        this.returnData(vector);
    }
}
