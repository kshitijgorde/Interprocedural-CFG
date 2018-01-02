// 
// Decompiled by Procyon v0.5.30
// 

package texttwist;

import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.zip.ZipInputStream;
import java.net.URL;
import java.io.IOException;
import inknet.User;
import java.util.Vector;

public class TwistLocal implements TwistNet
{
    protected wordtree m_tree;
    private Vector \u00d6;
    private Vector \u00d8;
    protected User m_user;
    
    public void initialize(final User user) {
        this.m_tree = new wordtree();
        this.m_user = user;
        try {
            this.readWordList();
        }
        catch (IOException ex) {
            System.out.println("Reading word list: " + ex.toString());
        }
    }
    
    public Vector getWordList(final String s) {
        return this.m_tree.getWordList(s);
    }
    
    public void end() {
    }
    
    public TwistLocal() {
        this.\u00d8 = null;
        this.\u00d6 = new Vector();
    }
    
    public void readWordList() throws IOException {
        final InputStream openStream = new URL(this.m_user.getApplet().getCodeBase(), "wordtree.bin.zip").openStream();
        final ZipInputStream zipInputStream = new ZipInputStream(openStream);
        zipInputStream.getNextEntry();
        final BufferedInputStream bufferedInputStream = new BufferedInputStream(zipInputStream);
        this.m_tree.readNode(bufferedInputStream);
        bufferedInputStream.close();
        zipInputStream.close();
        openStream.close();
    }
    
    public String getRandomLetters() {
        if (this.\u00d8 == null || this.\u00d8.size() == 0) {
            this.\u00d8 = this.m_tree.get6LetterWords();
        }
        final int size = this.\u00d8.size();
        if (size == 0) {
            return null;
        }
        int i;
        do {
            i = (int)(Math.random() * size);
        } while (i >= size);
        final String s = this.\u00d8.elementAt(i);
        this.\u00d8.removeElementAt(i);
        return s;
    }
}
