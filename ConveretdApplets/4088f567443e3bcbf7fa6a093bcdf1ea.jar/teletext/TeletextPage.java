// 
// Decompiled by Procyon v0.5.30
// 

package teletext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.util.Vector;

public class TeletextPage
{
    public static final int DEFAULT_WIDTH = 40;
    public static final int DEFAULT_HEIGHT = 24;
    int width;
    int height;
    char[][] contents;
    boolean changed;
    Vector accessList;
    
    public void finalize() throws Throwable {
        this.contents = null;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public boolean installAccessProvider(final TeletextPageAccessInterface teletextPageAccessInterface) {
        this.accessList.addElement(teletextPageAccessInterface);
        return true;
    }
    
    public boolean uninstallAccessProvider(final TeletextPageAccessInterface teletextPageAccessInterface) {
        this.accessList.removeElement(teletextPageAccessInterface);
        return true;
    }
    
    public boolean checkWritePermission(final int n, final int n2) {
        for (int i = 0; i < this.accessList.size(); ++i) {
            if (!((TeletextPageAccessInterface)this.accessList.elementAt(i)).checkWritePermission(this, n, n2)) {
                return false;
            }
        }
        return true;
    }
    
    public void setContents(final char[] array) {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.setCharacterAt(j, i, array[i * this.getWidth() + j]);
            }
        }
    }
    
    public void setContents(final String s) {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.setCharacterAt(j, i, s.charAt(i * this.getWidth() + j));
            }
        }
    }
    
    public char[] getContents() {
        final char[] array = new char[this.getWidth() * this.getHeight()];
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                array[i * this.getWidth() + j] = this.contents[i][j];
            }
        }
        return array;
    }
    
    public String getContentsAsString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.getContents());
        return sb.toString();
    }
    
    public void setCharacterAt(final int n, final int n2, final char c) {
        if (this.checkWritePermission(n, n2)) {
            this.changed = true;
            this.contents[n2][n] = c;
        }
    }
    
    public void insertCharacterAt(final int n, final int n2, final char c) {
        this.insertCharacterAt(n, n2, c, this.getWidth() - 1);
    }
    
    public void insertCharacterAt(final int n, final int n2, final char c, final int n3) {
        for (int i = n3; i > n; --i) {
            this.setCharacterAt(i, n2, this.contents[n2][i - 1]);
        }
        this.setCharacterAt(n, n2, c);
    }
    
    public void deleteCharacterAt(final int n, final int n2) {
        for (int n3 = this.getWidth() - 1, i = n; i < n3; ++i) {
            this.setCharacterAt(i, n2, this.contents[n2][i + 1]);
        }
        this.setCharacterAt(this.getWidth() - 1, n2, ' ');
    }
    
    public char getCharacterAt(final int n, final int n2) {
        return this.contents[n2][n];
    }
    
    public boolean isChanged() {
        return this.changed;
    }
    
    public void setChangeFlag(final boolean changed) {
        this.changed = changed;
    }
    
    public void setPixelAt(final int n, final int n2, final boolean b) {
        final int[] decomposeGraphX = TeletextFont.decomposeGraphX(n);
        final int[] decomposeGraphY = TeletextFont.decomposeGraphY(n2);
        final char character = this.getCharacterAt(decomposeGraphX[0], decomposeGraphY[0]);
        if (!isGraphicsCharacter(asciiToCustom(character))) {
            return;
        }
        final char c = (char)(customToGraph(asciiToCustom(character)) - '\u0080');
        final int n3 = 1 << decomposeGraphX[1] << 2 * decomposeGraphY[1];
        int n4;
        if (b) {
            n4 = (c | n3);
        }
        else {
            n4 = (c & ~n3);
        }
        int customToAscii;
        if (n4 == 0) {
            customToAscii = 32;
        }
        else {
            customToAscii = customToAscii(graphToCustom((char)(n4 + 128)));
        }
        this.setCharacterAt(decomposeGraphX[0], decomposeGraphY[0], (char)customToAscii);
    }
    
    public boolean getPixelAt(final int n, final int n2) {
        final int[] decomposeGraphX = TeletextFont.decomposeGraphX(n);
        final int[] decomposeGraphY = TeletextFont.decomposeGraphY(n2);
        final char character = this.getCharacterAt(decomposeGraphX[0], decomposeGraphY[0]);
        if (!isGraphicsCharacter(asciiToCustom(character))) {
            return false;
        }
        final char c = (char)(customToGraph(asciiToCustom(character)) - '\u0080');
        final int n3 = 1 << decomposeGraphX[1] << 2 * decomposeGraphY[1];
        return (c & n3) == n3;
    }
    
    void drawIndependentXLine(final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int abs = Math.abs(n3 - n);
        final int abs2 = Math.abs(n4 - n2);
        final int n5 = (n3 > n) ? 1 : -1;
        final int n6 = (n4 > n2) ? 1 : -1;
        int n7 = 2 * abs2 - abs;
        int n8 = n2;
        int n9 = n;
        for (int i = 0; i <= abs; ++i) {
            this.setPixelAt(n9, n8, b);
            if (n7 < 0) {
                n7 += 2 * abs2;
            }
            else {
                n8 += n6;
                n7 += 2 * (abs2 - abs);
            }
            n9 += n5;
        }
    }
    
    void drawIndependentYLine(final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int abs = Math.abs(n3 - n);
        final int abs2 = Math.abs(n4 - n2);
        final int n5 = (n3 > n) ? 1 : -1;
        final int n6 = (n4 > n2) ? 1 : -1;
        int n7 = 2 * abs - abs2;
        int n8 = n2;
        int n9 = n;
        for (int i = 0; i <= abs2; ++i) {
            this.setPixelAt(n9, n8, b);
            if (n7 < 0) {
                n7 += 2 * abs;
            }
            else {
                n9 += n5;
                n7 += 2 * (abs - abs2);
            }
            n8 += n6;
        }
    }
    
    void drawHorizontalLine(final int n, final int n2, final int n3, final boolean b) {
        final int n4 = (n2 > n) ? 1 : -1;
        final int abs = Math.abs(n2 - n);
        int n5 = n;
        for (int i = 0; i <= abs; ++i) {
            this.setPixelAt(n5, n3, b);
            n5 += n4;
        }
    }
    
    void drawVerticalLine(final int n, final int n2, final int n3, final boolean b) {
        final int n4 = (n2 > n) ? 1 : -1;
        final int abs = Math.abs(n2 - n);
        int n5 = n;
        for (int i = 0; i <= abs; ++i) {
            this.setPixelAt(n3, n5, b);
            n5 += n4;
        }
    }
    
    public void drawLine(final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int abs = Math.abs(n3 - n);
        final int abs2 = Math.abs(n4 - n2);
        if (abs2 == 0) {
            this.drawHorizontalLine(n, n3, n2, b);
            return;
        }
        if (abs == 0) {
            this.drawVerticalLine(n2, n4, n, b);
        }
        else if (abs >= abs2) {
            this.drawIndependentXLine(n, n2, n3, n4, b);
        }
        else {
            this.drawIndependentYLine(n, n2, n3, n4, b);
        }
    }
    
    public void drawRectangle(final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.drawLine(n, n2, n3, n2, b);
        this.drawLine(n, n2, n, n4, b);
        this.drawLine(n, n4, n3, n4, b);
        this.drawLine(n3, n2, n3, n4, b);
    }
    
    void drawSymmetricPixels(final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.setPixelAt(n + n3, n2 + n4, b);
        this.setPixelAt(n - n3, n2 + n4, b);
        this.setPixelAt(n + n3, n2 - n4, b);
        this.setPixelAt(n - n3, n2 - n4, b);
    }
    
    public void drawEllipse(final int n, final int n2, final int n3, final int n4, final boolean b) {
        final int n5 = n3 * n3;
        final int n6 = 2 * n5;
        final int n7 = n4 * n4;
        final int n8 = 2 * n7;
        int n9 = n7 - n5 * n4 + n5 / 4;
        int i = 0;
        int n10 = n6 * n4;
        int n11 = 0;
        int j = n4;
        while (i < n10) {
            if (n9 > 0) {
                --j;
                n10 -= n6;
                final int n12 = n9 - n10;
                this.drawSymmetricPixels(n, n2, n11, j, b);
                ++n11;
                i += n8;
                n9 = n12 + (n7 + i);
            }
            else {
                this.drawSymmetricPixels(n, n2, n11, j, b);
                ++n11;
                i += n8;
                n9 += n7 + i;
            }
        }
        int n13 = n9 + (3 * (n5 - n7) / 2 - (i + n10)) / 2;
        while (j > 0) {
            if (n13 < 0) {
                this.drawSymmetricPixels(n, n2, n11, j, b);
                ++n11;
                i += n8;
                n13 += i;
            }
            --j;
            this.drawSymmetricPixels(n, n2, n11, j, b);
            n10 -= n6;
            n13 += n5 - n10;
        }
    }
    
    public void drawEllipseInRectangle(final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.drawEllipse(n + (n3 - n) / 2, n2 + (n4 - n2) / 2, Math.abs(n3 - n) / 2, Math.abs(n4 - n2) / 2, b);
    }
    
    public void fill(final int n, final int n2, final int n3, final int n4, final char c) {
        this.changed = true;
        for (int i = n2; i <= n4; ++i) {
            for (int j = n; j <= n3; ++j) {
                this.setCharacterAt(j, i, c);
            }
        }
    }
    
    public void fill(final char c) {
        this.fill(0, 0, this.getWidth() - 1, this.getHeight() - 1, c);
    }
    
    public void clear(final int n, final int n2, final int n3, final int n4) {
        this.fill(n, n2, n3, n4, ' ');
    }
    
    public void clear() {
        this.fill(' ');
    }
    
    public void fillRow(final int n, final char c) {
        this.fill(0, n, this.getWidth() - 1, n, c);
    }
    
    public boolean isRowEmpty(final int n) {
        for (int i = 0; i < this.width; ++i) {
            if (this.contents[n][i] != ' ') {
                return false;
            }
        }
        return true;
    }
    
    public void print(final int n, final int n2, final String s) {
        for (int i = 0; i < s.length(); ++i) {
            this.setCharacterAt(n + i, n2, s.charAt(i));
        }
    }
    
    public boolean read(final String s) {
        this.changed = true;
        try {
            final FileInputStream fileInputStream = new FileInputStream(s);
            for (int i = 0; i < this.height; ++i) {
                for (int j = 0; j < this.width; ++j) {
                    this.contents[i][j] = customToAscii((char)fileInputStream.read());
                }
            }
            fileInputStream.close();
        }
        catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    public boolean write(final String s) {
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(s);
            for (int i = 0; i < this.height; ++i) {
                for (int j = 0; j < this.width; ++j) {
                    fileOutputStream.write(asciiToCustom(this.contents[i][j]));
                }
            }
            fileOutputStream.close();
        }
        catch (IOException ex) {
            return false;
        }
        return true;
    }
    
    public void customToAscii() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.contents[i][j] = customToAscii(this.contents[i][j]);
            }
        }
    }
    
    public void asciiToCustom() {
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                this.contents[i][j] = asciiToCustom(this.contents[i][j]);
            }
        }
    }
    
    private void allocate() {
        this.contents = new char[this.height][this.width];
        this.clear();
        this.changed = false;
    }
    
    public static int getDefaultWidth() {
        return 40;
    }
    
    public static int getDefaultHeight() {
        return 24;
    }
    
    public static char customToAscii(final char c) {
        switch (c) {
            case '\\': {
                return '\u0087';
            }
            case '_': {
                return '#';
            }
            case '#': {
                return '\u009c';
            }
            case '`': {
                return '\u0097';
            }
            case '{': {
                return '\u0085';
            }
            case '|': {
                return '\u0095';
            }
            case '[': {
                return '\u00f8';
            }
            case '@': {
                return '\u0082';
            }
            case '}': {
                return '\u008a';
            }
            case '~': {
                return '\u008d';
            }
            default: {
                return c;
            }
        }
    }
    
    public static char asciiToCustom(final char c) {
        switch (c) {
            case '\u0087': {
                return '\\';
            }
            case '#': {
                return '_';
            }
            case '\u009c': {
                return '#';
            }
            case '\u0097': {
                return '`';
            }
            case '\u0085': {
                return '{';
            }
            case '\u0095': {
                return '|';
            }
            case '\u00f8': {
                return '[';
            }
            case '\u0082': {
                return '@';
            }
            case '\u008a': {
                return '}';
            }
            case '\u008d': {
                return '~';
            }
            default: {
                return c;
            }
        }
    }
    
    public static boolean isGraphicsCharacter(final char c) {
        return (c >= ' ' && c <= '?') || (c >= '`' && c <= '\u007f');
    }
    
    public static char customToGraph(final char c) {
        int n = c;
        if (n >= 32 && n <= 63) {
            n += 96;
        }
        else if (n >= 96 && n <= 127) {
            n += 64;
        }
        return (char)n;
    }
    
    public static char graphToCustom(final char c) {
        int n = c;
        if (n >= 128 && n <= 159) {
            n -= 96;
        }
        else if (n >= 160 && n <= 191) {
            n -= 64;
        }
        return (char)n;
    }
    
    public boolean isDigit(final char c) {
        return c >= '0' && c <= '9';
    }
    
    public int numberHitTest(final int n, final int n2) {
        final StringBuffer sb = new StringBuffer();
        int n3 = n;
        int n4 = n;
        while (n3 > 0 && this.isDigit(this.contents[n2][n3 - 1])) {
            --n3;
        }
        while (n4 < this.getWidth() - 1 && this.isDigit(this.contents[n2][n4 + 1])) {
            ++n4;
        }
        for (int i = n3; i <= n4; ++i) {
            sb.append(this.contents[n2][i]);
        }
        int int1;
        try {
            int1 = Integer.parseInt(sb.toString());
        }
        catch (NumberFormatException ex) {
            int1 = 0;
        }
        if (int1 >= 100 && int1 <= 899) {
            return int1;
        }
        return 0;
    }
    
    public String siteHitTest(final int n, final int n2) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.getWidth(); ++i) {
            sb.append(this.contents[n2][i]);
        }
        int n3 = n;
        int j = n;
        while (n3 >= 0 && !sb.toString().substring(n3).toString().startsWith("www.")) {
            --n3;
        }
        if (n3 < 0) {
            return null;
        }
        while (j < this.getWidth() - 1) {
            if (sb.charAt(j) <= ' ' || sb.charAt(j) > '\u007f') {
                return sb.toString().substring(n3, j).toString();
            }
            ++j;
        }
        return null;
    }
    
    public TeletextPage(final int width, final int height) {
        this.accessList = new Vector();
        this.width = width;
        this.height = height;
        this.changed = false;
        this.allocate();
    }
    
    public TeletextPage(final TeletextPage teletextPage) {
        this.accessList = new Vector();
        this.width = teletextPage.getWidth();
        this.height = teletextPage.getHeight();
        this.changed = false;
        this.allocate();
        this.setContents(teletextPage.getContents());
    }
    
    public TeletextPage() {
        this.accessList = new Vector();
        this.width = 40;
        this.height = 24;
        this.changed = false;
        this.allocate();
    }
}
