// 
// Decompiled by Procyon v0.5.30
// 

package mechanalyst;

import sun.audio.AudioPlayer;
import java.io.IOException;
import java.io.FileNotFoundException;
import sun.audio.AudioData;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.net.URL;
import java.awt.Component;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Frame;
import sun.audio.AudioDataStream;

public class Speech extends Thread
{
    final int sound_IY = 0;
    final int sound_EY = 1;
    final int sound_AE = 2;
    final int sound_AO = 3;
    final int sound_UH = 4;
    final int sound_ER = 5;
    final int sound_AH = 6;
    final int sound_AW = 7;
    final int sound_IH = 8;
    final int sound_EH = 9;
    final int sound_AA = 10;
    final int sound_OW = 11;
    final int sound_UW = 12;
    final int sound_AX = 13;
    final int sound_OY = 14;
    final int sound_P = 15;
    final int sound_T = 16;
    final int sound_K = 17;
    final int sound_F = 18;
    final int sound_TH = 19;
    final int sound_S = 20;
    final int sound_SH = 21;
    final int sound_HH = 22;
    final int sound_N = 23;
    final int sound_L = 24;
    final int sound_Y = 25;
    final int sound_CH = 26;
    final int sound_WH = 27;
    final int sound_B = 28;
    final int sound_D = 29;
    final int sound_G = 30;
    final int sound_V = 31;
    final int sound_DH = 32;
    final int sound_Z = 33;
    final int sound_ZH = 34;
    final int sound_M = 35;
    final int sound_NG = 36;
    final int sound_W = 37;
    final int sound_R = 38;
    final int sound_J = 39;
    final int sound_AY = 40;
    final int sound_YU = 41;
    final int SIZE_IY = 178;
    final int SIZE_EY = 280;
    final int SIZE_AE = 280;
    final int SIZE_AO = 460;
    final int SIZE_UH = 340;
    final int SIZE_ER = 420;
    final int SIZE_AH = 320;
    final int SIZE_AW = 450;
    final int SIZE_IH = 280;
    final int SIZE_EH = 290;
    final int SIZE_AA = 330;
    final int SIZE_OW = 418;
    final int SIZE_UW = 430;
    final int SIZE_AX = 450;
    final int SIZE_OY = 570;
    final int SIZE_T = 130;
    final int SIZE_K = 90;
    final int SIZE_F = 210;
    final int SIZE_TH = 250;
    final int SIZE_S = 290;
    final int SIZE_SH = 300;
    final int SIZE_N = 310;
    final int SIZE_L = 320;
    final int SIZE_Y = 300;
    final int SIZE_CH = 120;
    final int SIZE_B = 40;
    final int SIZE_D = 40;
    final int SIZE_G = 90;
    final int SIZE_V = 280;
    final int SIZE_DH = 160;
    final int SIZE_ZH = 190;
    final int SIZE_M = 310;
    final int SIZE_NG = 350;
    final int SIZE_R = 140;
    final int SIZE_J = 130;
    final int SIZE_AY = 300;
    final int SIZE_YU = 440;
    final int SIZE_P = 90;
    final int SIZE_HH = 170;
    final int SIZE_WH = 350;
    final int SIZE_Z = 360;
    final int SIZE_W = 330;
    final int NO_OF_SOUNDS = 42;
    int spacetime_value;
    int t_factor;
    String result_string;
    AudioDataStream[] clip;
    int LEFT_PART;
    int MATCH_PART;
    int RIGHT_PART;
    int OUT_PART;
    String[][] rule;
    String[] tab_0_19;
    String[] tab_20_90;
    
    public void run() {
    }
    
    public void showbug(final String s, final String s2) {
        final Frame frame = new Frame();
        frame.setLayout(new FlowLayout());
        frame.setTitle("Error (mocha talk)");
        frame.add(new Label(s));
        frame.add(new Label(s2));
        frame.resize(250, 200);
        frame.show();
    }
    
    public boolean do_load_sounds(final URL url, final String s) {
        int n = 0;
        try {
            DataInputStream dataInputStream;
            if (url != null) {
                dataInputStream = new DataInputStream(url.openStream());
            }
            else {
                dataInputStream = new DataInputStream(new FileInputStream(s));
            }
            if (dataInputStream == null) {
                if (url == null) {
                    this.showbug("Cannot open file " + s, "File is missing. (proxy problem ?)");
                }
                else {
                    this.showbug("Cannot open url " + url, "File is missing. (proxy problem ?)");
                }
                return false;
            }
            for (int i = 0; i < 42; ++i) {
                final int int1 = dataInputStream.readInt();
                if (int1 > 16384) {
                    if (url == null) {
                        this.showbug("Cannot open file " + s, "File is missing. (proxy problem ?)");
                    }
                    else {
                        this.showbug("Cannot open url " + url, "File is missing. (proxy problem ?)");
                    }
                    return false;
                }
                final byte[] array = new byte[int1];
                final byte[] array2 = new byte[int1 * 4];
                int n2 = 0;
                dataInputStream.readFully(array, 0, int1);
                for (int j = 0; j < int1; ++j) {
                    if ((array[j] & 0x1) == 0x1) {
                        switch (array[j] & 0x7) {
                            case 1: {
                                ++n;
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                break;
                            }
                            case 3: {
                                n += 2;
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                break;
                            }
                            case 5: {
                                n += 3;
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                break;
                            }
                            case 7: {
                                n += 4;
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                array2[n2++] = (byte)(array[j] & 0xF8);
                                break;
                            }
                        }
                    }
                    else {
                        array2[n2++] = (byte)(array[j] & 0xFE);
                    }
                }
                final byte[] array3 = new byte[n2];
                for (int k = 0; k < n2; ++k) {
                    array3[k] = array2[k];
                }
                this.clip[i] = new AudioDataStream(new AudioData(array3));
            }
            int l = 1;
            final byte[] array4 = new byte[40];
            byte b = dataInputStream.readByte();
            int n3 = 0;
            do {
                for (int n4 = 0; n4 < 4; ++n4) {
                    array4[0] = (byte)(b & 0x7F);
                    int n5 = 1;
                    while (((b = dataInputStream.readByte()) & 0x80) != 0x80) {
                        array4[n5] = b;
                        ++n5;
                    }
                    this.rule[n3][n4] = new String(array4, 0, 0, n5);
                }
                if (this.rule[n3][0].equals("@")) {
                    l = 0;
                }
                ++n3;
            } while (l == 1);
        }
        catch (FileNotFoundException ex2) {
            if (url == null) {
                this.showbug("Cannot open file " + s, "File is missing. (proxy problem ?)");
            }
            else {
                this.showbug("Cannot open url " + url, "File is missing. (proxy problem ?)");
            }
            return false;
        }
        catch (IOException ex) {
            if (url == null) {
                this.showbug("Wrong data in file " + s, String.valueOf(ex));
            }
            else {
                this.showbug("Wrong data in url" + url, String.valueOf(ex));
            }
            return false;
        }
        return true;
    }
    
    public void spacetime(final int spacetime_value) {
        this.spacetime_value = spacetime_value;
    }
    
    public void overload(final int t_factor) {
        this.t_factor = t_factor;
    }
    
    public String talk(final String s) {
        this.result_string = "";
        final int n = s.length() - 1;
        int i = 0;
        while (i <= n) {
            final char char1 = s.charAt(i);
            char char2;
            if (i == n) {
                char2 = ' ';
            }
            else {
                char2 = s.charAt(i + 1);
            }
            if (Character.isDigit(char1)) {
                i = this.do_a_number(s, i, n, false);
            }
            else if (char1 == '$' && Character.isDigit(char2)) {
                i = this.do_a_number(s, i + 1, n, true);
            }
            else {
                i = this.do_text(s, i, n);
            }
        }
        return this.result_string;
    }
    
    public int do_a_number(final String s, int do_a_number, final int n, final boolean b) {
        int n2 = 0;
        char char1 = ' ';
        int i;
        for (i = do_a_number; i <= n; ++i) {
            char1 = s.charAt(i);
            if (!Character.isDigit(char1)) {
                break;
            }
            n2 = n2 * 10 + (char1 - '0');
        }
        this.say_number(n2);
        if (char1 == '.') {
            if (!b) {
                this.do_word("POINT ");
            }
            else {
                if (n2 == 1) {
                    this.do_word("DOLLAR ");
                }
                else {
                    this.do_word("DOLLARS ");
                }
                if (i + 2 <= n) {
                    final char c = (char)((s.charAt(i + 1) - '0') * '\n' + (s.charAt(i + 2) - '0'));
                    if (c == '\0') {
                        return i + 3;
                    }
                    this.do_word("AND ");
                    do_a_number = this.do_a_number(s, i + 1, n, false);
                    if (c == '\u0001') {
                        this.do_word("CENT ");
                    }
                    else {
                        this.do_word("CENTS ");
                    }
                    return do_a_number;
                }
            }
        }
        if (b) {
            if (n2 == 1) {
                this.do_word("DOLLAR ");
            }
            else {
                this.do_word("DOLLARS ");
            }
        }
        return i + 1;
    }
    
    public void say_number(long n) {
        if (n < 0L) {
            this.do_word("OVERRUN    ");
            return;
        }
        if (n >= 1000000000L) {
            this.say_number(n / 1000000000L);
            this.do_word("BILLIONS");
            n %= 1000000000L;
            if (n == 0L) {
                return;
            }
            if (n < 100L) {
                this.do_word("AND ");
            }
        }
        if (n >= 1000000L) {
            this.say_number(n / 1000000L);
            this.do_word("MILLIONS ");
            n %= 1000000L;
            if (n == 0L) {
                return;
            }
            if (n < 100L) {
                this.do_word("AND ");
            }
        }
        if ((n >= 1000L && n <= 1099L) || n >= 2000L) {
            this.say_number(n / 1000L);
            this.do_word("THOUSAND ");
            n %= 1000L;
            if (n == 0L) {
                return;
            }
            if (n < 100L) {
                this.do_word("AND ");
            }
        }
        if (n >= 100L) {
            this.do_word(this.tab_0_19[(int)(n / 100L)]);
            this.do_word("HUNDRED ");
            n %= 100L;
            if (n == 0L) {
                return;
            }
        }
        if (n >= 20L) {
            this.do_word(this.tab_20_90[(int)((n - 20L) / 10L)]);
            n %= 10L;
            if (n == 0L) {
                return;
            }
        }
        this.do_word(this.tab_0_19[(int)n]);
    }
    
    public int do_text(final String s, final int n, final int n2) {
        char char1 = ' ';
        String string = "";
        int i;
        for (i = n; i <= n2; ++i) {
            char1 = s.charAt(i);
            if (this.isLetter(char1)) {
                string = String.valueOf(string) + Character.toUpperCase(char1);
            }
            else if (char1 != '\'' && char1 != '´') {
                break;
            }
        }
        switch (char1) {
            case '+': {
                if (i == n) {
                    this.do_word("PLUS ");
                    break;
                }
                break;
            }
            case '=': {
                if (i == n) {
                    this.do_word("EQUAL ");
                    break;
                }
                break;
            }
            case '*': {
                if (i == n) {
                    this.do_word("MULTIPLY ");
                    break;
                }
                break;
            }
            case '/': {
                if (i == n) {
                    this.do_word("DIVIDE ");
                    break;
                }
                break;
            }
            case '.': {
                if (string.lastIndexOf("MRS") != -1) {
                    this.do_word("MISSUS ");
                    break;
                }
                if (string.lastIndexOf("MR") != -1) {
                    this.do_word("MISTER ");
                    break;
                }
                if (string.lastIndexOf("DR") != -1) {
                    this.do_word("DOCTOR ");
                    break;
                }
                this.do_word(String.valueOf(string) + " ");
                break;
            }
            default: {
                this.do_word(String.valueOf(string) + " ");
                break;
            }
        }
        return i + 1;
    }
    
    public void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public boolean isvowel(final char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'Y';
    }
    
    public boolean isconsonant(final char c) {
        return Character.isUpperCase(c) && !this.isvowel(c);
    }
    
    public void do_play(final String s) {
        int n = 0;
        this.result_string = String.valueOf(this.result_string) + " " + s;
        final int n2 = s.length() - 1;
        int i = 0;
        while (i <= n2) {
            int char1;
            if (i + 1 <= n2) {
                char1 = s.charAt(i + 1);
            }
            else {
                char1 = 32;
            }
            int n3 = -1;
            Label_1129: {
                switch (s.charAt(i)) {
                    case ' ': {
                        try {
                            Thread.sleep(this.spacetime_value);
                        }
                        catch (InterruptedException ex) {
                            break;
                        }
                        ++i;
                        break;
                    }
                    case 'A': {
                        switch (char1) {
                            case 65: {
                                n3 = 10;
                                n = 330;
                                i += 2;
                                break Label_1129;
                            }
                            case 88: {
                                n3 = 13;
                                n = 450;
                                i += 2;
                                break Label_1129;
                            }
                            case 89: {
                                n3 = 40;
                                n = 300;
                                i += 2;
                                break Label_1129;
                            }
                            case 72: {
                                n3 = 6;
                                n = 320;
                                i += 2;
                                break Label_1129;
                            }
                            case 87: {
                                n3 = 7;
                                n = 450;
                                i += 2;
                                break Label_1129;
                            }
                            case 69: {
                                n3 = 2;
                                n = 280;
                                i += 2;
                                break Label_1129;
                            }
                            case 79: {
                                n3 = 3;
                                n = 460;
                                i += 2;
                                break Label_1129;
                            }
                        }
                        break;
                    }
                    case 'C': {
                        n3 = 26;
                        n = 120;
                        i += 2;
                        break;
                    }
                    case 'D': {
                        n3 = 32;
                        n = 160;
                        i += 2;
                        break;
                    }
                    case 'E': {
                        switch (char1) {
                            case 72: {
                                n3 = 9;
                                n = 290;
                                i += 2;
                                break Label_1129;
                            }
                            case 89: {
                                n3 = 1;
                                n = 280;
                                i += 2;
                                break Label_1129;
                            }
                            case 82: {
                                n3 = 5;
                                n = 420;
                                i += 2;
                                break Label_1129;
                            }
                        }
                        break;
                    }
                    case 'H': {
                        n3 = 22;
                        n = 170;
                        i += 2;
                        break;
                    }
                    case 'I': {
                        switch (char1) {
                            case 89: {
                                n3 = 0;
                                n = 178;
                                i += 2;
                                break Label_1129;
                            }
                            case 72: {
                                n3 = 8;
                                n = 280;
                                i += 2;
                                break Label_1129;
                            }
                        }
                        break;
                    }
                    case 'N': {
                        n3 = 36;
                        n = 350;
                        i += 2;
                        break;
                    }
                    case 'O': {
                        switch (char1) {
                            case 89: {
                                n3 = 14;
                                n = 570;
                                i += 2;
                                break Label_1129;
                            }
                            case 87: {
                                n3 = 11;
                                n = 418;
                                i += 2;
                                break Label_1129;
                            }
                        }
                        break;
                    }
                    case 'S': {
                        n3 = 21;
                        n = 300;
                        i += 2;
                        break;
                    }
                    case 'T': {
                        n3 = 19;
                        n = 250;
                        i += 2;
                        break;
                    }
                    case 'U': {
                        switch (char1) {
                            case 72: {
                                n3 = 4;
                                n = 340;
                                i += 2;
                                break Label_1129;
                            }
                            case 87: {
                                n3 = 12;
                                n = 430;
                                i += 2;
                                break Label_1129;
                            }
                        }
                        break;
                    }
                    case 'W': {
                        n3 = 27;
                        n = 350;
                        i += 2;
                        break;
                    }
                    case 'Y': {
                        n3 = 41;
                        n = 440;
                        i += 2;
                        break;
                    }
                    case 'Z': {
                        n3 = 34;
                        n = 190;
                        i += 2;
                        break;
                    }
                    case 'p': {
                        n3 = 15;
                        n = 90;
                        ++i;
                        break;
                    }
                    case 't': {
                        n3 = 16;
                        n = 130;
                        ++i;
                        break;
                    }
                    case 'k': {
                        n3 = 17;
                        n = 90;
                        ++i;
                        break;
                    }
                    case 'f': {
                        n3 = 18;
                        n = 210;
                        ++i;
                        break;
                    }
                    case 's': {
                        n3 = 20;
                        n = 290;
                        ++i;
                        break;
                    }
                    case 'n': {
                        n3 = 23;
                        n = 310;
                        ++i;
                        break;
                    }
                    case 'l': {
                        n3 = 24;
                        n = 320;
                        ++i;
                        break;
                    }
                    case 'y': {
                        n3 = 25;
                        n = 300;
                        ++i;
                        break;
                    }
                    case 'b': {
                        n3 = 28;
                        n = 40;
                        ++i;
                        break;
                    }
                    case 'd': {
                        n3 = 29;
                        n = 40;
                        ++i;
                        break;
                    }
                    case 'g': {
                        n3 = 30;
                        n = 90;
                        ++i;
                        break;
                    }
                    case 'v': {
                        n3 = 31;
                        n = 280;
                        ++i;
                        break;
                    }
                    case 'z': {
                        n3 = 33;
                        n = 360;
                        ++i;
                        break;
                    }
                    case 'm': {
                        n3 = 35;
                        n = 310;
                        ++i;
                        break;
                    }
                    case 'w': {
                        n3 = 37;
                        n = 330;
                        ++i;
                        break;
                    }
                    case 'r': {
                        n3 = 38;
                        n = 140;
                        ++i;
                        break;
                    }
                    case 'j': {
                        n3 = 39;
                        n = 130;
                        ++i;
                        break;
                    }
                    default: {
                        ++i;
                        break;
                    }
                }
            }
            if (n3 != -1) {
                this.clip[n3].reset();
                AudioPlayer.player.start(this.clip[n3]);
                try {
                    Thread.sleep(this.t_factor * n / 100);
                }
                catch (InterruptedException ex2) {}
                AudioPlayer.player.stop(this.clip[n3]);
            }
        }
    }
    
    public void do_word(final String s) {
        final int n = s.length() - 1;
        int i = 0;
        int n2;
        for (n2 = n; n2 > 0 && s.charAt(n2) == ' '; --n2) {}
        do {
            int n3 = 1;
            for (int n4 = 0; n3 == 1 && this.rule[n4][this.MATCH_PART].charAt(0) != '@'; ++n4) {
                final int length = this.rule[n4][this.MATCH_PART].length();
                if (this.rule[n4][this.MATCH_PART].regionMatches(0, s, i, length) && this.leftmatch(n4, i, s) && this.rightmatch(n4, i + length, n2, s)) {
                    i += length;
                    this.do_play(this.rule[n4][this.OUT_PART]);
                    n3 = 0;
                }
            }
            if (n3 == 1) {
                ++i;
            }
        } while (i <= n);
    }
    
    public boolean isLetter(final char c) {
        boolean letter;
        try {
            letter = Character.isLetter(c);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            letter = ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
        }
        return letter;
    }
    
    public boolean leftmatch(final int n, final int n2, final String s) {
        if (this.rule[n][this.LEFT_PART].charAt(0) == '*') {
            return true;
        }
        if (this.rule[n][this.LEFT_PART].charAt(0) == ' ' && n2 == 0) {
            return true;
        }
        int length;
        int i;
        for (length = this.rule[n][this.LEFT_PART].length(), i = n2 - 1; length > 0 && i >= 0; --length) {
            final char char1 = this.rule[n][this.LEFT_PART].charAt(length - 1);
            final char char2 = s.charAt(i);
            if (this.isLetter(char1)) {
                if (char1 != char2) {
                    return false;
                }
                --i;
            }
            else {
                switch (char1) {
                    case '#': {
                        if (!this.isvowel(char2)) {
                            return false;
                        }
                        --i;
                        while (i >= 0) {
                            if (!this.isvowel(s.charAt(i))) {
                                break;
                            }
                            --i;
                        }
                        break;
                    }
                    case ':': {
                        while (i >= 0) {
                            if (!this.isconsonant(s.charAt(i))) {
                                break;
                            }
                            --i;
                        }
                        break;
                    }
                    case '^': {
                        if (!this.isconsonant(char2)) {
                            return false;
                        }
                        --i;
                        break;
                    }
                    case '.': {
                        if (char2 != 'B' && char2 != 'D' && char2 != 'V' && char2 != 'G' && char2 != 'J' && char2 != 'L' && char2 != 'M' && char2 != 'N' && char2 != 'R' && char2 != 'W' && char2 != 'Z') {
                            return false;
                        }
                        --i;
                        break;
                    }
                    case '+': {
                        if (char2 != 'E' && char2 != 'I' && char2 != 'Y') {
                            return false;
                        }
                        --i;
                        break;
                    }
                    default: {
                        return false;
                    }
                }
            }
        }
        return length == 0 || (i < 0 && this.rule[n][this.LEFT_PART].charAt(0) == ' ');
    }
    
    public boolean rightmatch(final int n, final int n2, final int n3, final String s) {
        if (this.rule[n][this.RIGHT_PART].charAt(0) == '*') {
            return true;
        }
        if (this.rule[n][this.RIGHT_PART].charAt(0) == ' ' && n2 > n3) {
            return true;
        }
        if (n2 > n3) {
            return false;
        }
        int n4;
        int n5;
        int i;
        for (n4 = this.rule[n][this.RIGHT_PART].length() - 1, n5 = 0, i = n2; n5 <= n4 && i <= n3; ++n5) {
            final char char1 = this.rule[n][this.RIGHT_PART].charAt(n5);
            final char char2 = s.charAt(i);
            if (this.isLetter(char1)) {
                if (char1 != char2) {
                    return false;
                }
                ++i;
            }
            else {
                switch (char1) {
                    case '#': {
                        if (!this.isvowel(char2)) {
                            return false;
                        }
                        ++i;
                        while (i <= n3) {
                            if (!this.isvowel(s.charAt(i))) {
                                break;
                            }
                            ++i;
                        }
                        break;
                    }
                    case ':': {
                        while (i <= n3) {
                            if (!this.isconsonant(s.charAt(i))) {
                                break;
                            }
                            ++i;
                        }
                        break;
                    }
                    case '^': {
                        if (!this.isconsonant(char2)) {
                            return false;
                        }
                        ++i;
                        break;
                    }
                    case '.': {
                        if (char2 != 'B' && char2 != 'D' && char2 != 'V' && char2 != 'G' && char2 != 'J' && char2 != 'L' && char2 != 'M' && char2 != 'N' && char2 != 'R' && char2 != 'W' && char2 != 'Z') {
                            return false;
                        }
                        ++i;
                        break;
                    }
                    case '+': {
                        if (char2 != 'E' && char2 != 'I' && char2 != 'Y') {
                            return false;
                        }
                        ++i;
                        break;
                    }
                    case '%': {
                        if (i + 2 <= n3 && (s.regionMatches(i, "ING", 0, 3) || s.regionMatches(i, "ELY", 0, 3))) {
                            i += 3;
                            break;
                        }
                        if (i + 1 <= n3 && (s.regionMatches(i, "ER", 0, 2) || s.regionMatches(i, "ES", 0, 2) || s.regionMatches(i, "ED", 0, 2))) {
                            i += 2;
                            break;
                        }
                        if (char2 == 'E') {
                            ++i;
                            break;
                        }
                        return false;
                    }
                    default: {
                        return false;
                    }
                }
            }
        }
        return n5 > n4 || (i > n3 && this.rule[n][this.RIGHT_PART].charAt(n5) == ' ');
    }
    
    public Speech() {
        this.sound_EY = 1;
        this.sound_AE = 2;
        this.sound_AO = 3;
        this.sound_UH = 4;
        this.sound_ER = 5;
        this.sound_AH = 6;
        this.sound_AW = 7;
        this.sound_IH = 8;
        this.sound_EH = 9;
        this.sound_AA = 10;
        this.sound_OW = 11;
        this.sound_UW = 12;
        this.sound_AX = 13;
        this.sound_OY = 14;
        this.sound_P = 15;
        this.sound_T = 16;
        this.sound_K = 17;
        this.sound_F = 18;
        this.sound_TH = 19;
        this.sound_S = 20;
        this.sound_SH = 21;
        this.sound_HH = 22;
        this.sound_N = 23;
        this.sound_L = 24;
        this.sound_Y = 25;
        this.sound_CH = 26;
        this.sound_WH = 27;
        this.sound_B = 28;
        this.sound_D = 29;
        this.sound_G = 30;
        this.sound_V = 31;
        this.sound_DH = 32;
        this.sound_Z = 33;
        this.sound_ZH = 34;
        this.sound_M = 35;
        this.sound_NG = 36;
        this.sound_W = 37;
        this.sound_R = 38;
        this.sound_J = 39;
        this.sound_AY = 40;
        this.sound_YU = 41;
        this.SIZE_IY = 178;
        this.SIZE_EY = 280;
        this.SIZE_AE = 280;
        this.SIZE_AO = 460;
        this.SIZE_UH = 340;
        this.SIZE_ER = 420;
        this.SIZE_AH = 320;
        this.SIZE_AW = 450;
        this.SIZE_IH = 280;
        this.SIZE_EH = 290;
        this.SIZE_AA = 330;
        this.SIZE_OW = 418;
        this.SIZE_UW = 430;
        this.SIZE_AX = 450;
        this.SIZE_OY = 570;
        this.SIZE_T = 130;
        this.SIZE_K = 90;
        this.SIZE_F = 210;
        this.SIZE_TH = 250;
        this.SIZE_S = 290;
        this.SIZE_SH = 300;
        this.SIZE_N = 310;
        this.SIZE_L = 320;
        this.SIZE_Y = 300;
        this.SIZE_CH = 120;
        this.SIZE_B = 40;
        this.SIZE_D = 40;
        this.SIZE_G = 90;
        this.SIZE_V = 280;
        this.SIZE_DH = 160;
        this.SIZE_ZH = 190;
        this.SIZE_M = 310;
        this.SIZE_NG = 350;
        this.SIZE_R = 140;
        this.SIZE_J = 130;
        this.SIZE_AY = 300;
        this.SIZE_YU = 440;
        this.SIZE_P = 90;
        this.SIZE_HH = 170;
        this.SIZE_WH = 350;
        this.SIZE_Z = 360;
        this.SIZE_W = 330;
        this.NO_OF_SOUNDS = 42;
        this.spacetime_value = 300;
        this.t_factor = 70;
        this.result_string = "";
        this.clip = new AudioDataStream[42];
        this.MATCH_PART = 1;
        this.RIGHT_PART = 2;
        this.OUT_PART = 3;
        this.rule = new String[400][4];
        this.tab_0_19 = new String[] { "ZERO ", "ONE ", "TWO ", "THREE ", "FOUR ", "FIVE ", "SIX ", "SEVEN ", "EIGHT ", "NINE ", "TEN ", "ELEVEN ", "TWELVE ", "THIRTEEN ", "FOURTEEN ", "FIFTEEN ", "SIXTEEN ", "SEVENTEEN ", "EIGHTEEN ", "NINETEEN " };
        this.tab_20_90 = new String[] { "TWENTY ", "THIRTY ", "FORTY ", "FIFTY ", "SIXTY ", "SEVENTY ", "EIGHTY ", "NINETY " };
    }
}
