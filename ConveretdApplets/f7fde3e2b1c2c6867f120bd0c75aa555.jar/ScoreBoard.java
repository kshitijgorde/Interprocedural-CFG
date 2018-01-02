import java.awt.image.ImageObserver;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Random;
import java.awt.Color;
import java.util.Vector;
import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScoreBoard extends Applet implements Runnable
{
    Thread me;
    Image offI;
    Graphics offG;
    int width;
    int height;
    Hashtable letters;
    int offset;
    Vector style;
    Vector text;
    int index;
    int speed;
    String tempStyle;
    Color background;
    Color foreground;
    Random random;
    
    public final void init() {
        this.width = this.getSize().width;
        this.letters = new Hashtable(24);
        final boolean[][] array = new boolean[5][9];
        for (int i = 1; i < 9; ++i) {
            array[0][i] = true;
            array[4][i] = true;
        }
        for (int j = 1; j < 4; ++j) {
            array[j][4] = true;
            array[j][0] = true;
        }
        this.letters.put("a", array);
        final boolean[][] array2 = new boolean[5][9];
        for (int k = 0; k < 9; ++k) {
            array2[0][k] = true;
        }
        for (int l = 1; l < 4; ++l) {
            array2[l][0] = true;
            array2[l][4] = true;
            array2[l][8] = true;
            array2[4][l] = true;
            array2[4][l + 4] = true;
        }
        this.letters.put("b", array2);
        final boolean[][] array3 = new boolean[5][9];
        for (int n = 1; n < 8; ++n) {
            array3[0][n] = true;
        }
        for (int n2 = 1; n2 < 4; ++n2) {
            array3[n2][0] = true;
            array3[n2][8] = true;
        }
        array3[4][1] = true;
        array3[4][7] = true;
        this.letters.put("c", array3);
        final boolean[][] array4 = new boolean[5][9];
        for (int n3 = 0; n3 < 9; ++n3) {
            array4[0][n3] = true;
        }
        for (int n4 = 1; n4 < 4; ++n4) {
            array4[n4][0] = true;
            array4[n4][8] = true;
        }
        for (int n5 = 1; n5 < 8; ++n5) {
            array4[4][n5] = true;
        }
        this.letters.put("d", array4);
        final boolean[][] array5 = new boolean[5][9];
        for (int n6 = 0; n6 < 9; ++n6) {
            array5[0][n6] = true;
        }
        for (int n7 = 1; n7 < 5; ++n7) {
            array5[n7][0] = true;
            array5[n7][8] = true;
        }
        for (int n8 = 1; n8 < 4; ++n8) {
            array5[n8][4] = true;
        }
        this.letters.put("e", array5);
        final boolean[][] array6 = new boolean[5][9];
        for (int n9 = 0; n9 < 9; ++n9) {
            array6[0][n9] = true;
        }
        for (int n10 = 1; n10 < 5; ++n10) {
            array6[n10][0] = true;
        }
        for (int n11 = 1; n11 < 4; ++n11) {
            array6[n11][4] = true;
        }
        this.letters.put("f", array6);
        final boolean[][] array7 = new boolean[5][9];
        for (int n12 = 1; n12 < 8; ++n12) {
            array7[0][n12] = true;
        }
        for (int n13 = 1; n13 < 4; ++n13) {
            array7[n13][0] = true;
            array7[n13][8] = true;
            array7[n13 + 1][4] = true;
            array7[4][n13 + 4] = true;
        }
        array7[4][1] = true;
        this.letters.put("g", array7);
        final boolean[][] array8 = new boolean[5][9];
        for (int n14 = 0; n14 < 9; ++n14) {
            array8[0][n14] = true;
            array8[4][n14] = true;
        }
        for (int n15 = 1; n15 < 4; ++n15) {
            array8[n15][4] = true;
        }
        this.letters.put("h", array8);
        final boolean[][] array9 = new boolean[5][9];
        for (int n16 = 0; n16 < 5; ++n16) {
            array9[n16][0] = true;
            array9[n16][8] = true;
        }
        for (int n17 = 1; n17 < 8; ++n17) {
            array9[2][n17] = true;
        }
        this.letters.put("i", array9);
        final boolean[][] array10 = new boolean[5][9];
        for (int n18 = 1; n18 < 5; ++n18) {
            array10[n18][0] = true;
            array10[0][n18 + 3] = true;
        }
        for (int n19 = 1; n19 < 8; ++n19) {
            array10[3][n19] = true;
        }
        array10[1][8] = true;
        array10[2][8] = true;
        this.letters.put("j", array10);
        final boolean[][] array11 = new boolean[5][9];
        for (int n20 = 0; n20 < 9; ++n20) {
            array11[0][n20] = true;
        }
        for (int n21 = 1; n21 < 5; ++n21) {
            array11[n21][4 - n21] = true;
            array11[n21][4 + n21] = true;
        }
        this.letters.put("k", array11);
        final boolean[][] array12 = new boolean[5][9];
        for (int n22 = 0; n22 < 9; ++n22) {
            array12[0][n22] = true;
        }
        for (int n23 = 1; n23 < 5; ++n23) {
            array12[n23][8] = true;
        }
        this.letters.put("l", array12);
        final boolean[][] array13 = new boolean[5][9];
        for (int n24 = 0; n24 < 9; ++n24) {
            array13[0][n24] = true;
            array13[4][n24] = true;
        }
        array13[1][1] = true;
        array13[2][2] = true;
        array13[3][1] = true;
        this.letters.put("m", array13);
        final boolean[][] array14 = new boolean[5][9];
        for (int n25 = 0; n25 < 9; ++n25) {
            array14[0][n25] = true;
            array14[4][n25] = true;
        }
        array14[1][3] = true;
        array14[2][4] = true;
        array14[3][5] = true;
        this.letters.put("n", array14);
        final boolean[][] array15 = new boolean[5][9];
        for (int n26 = 1; n26 < 8; ++n26) {
            array15[0][n26] = true;
            array15[4][n26] = true;
        }
        for (int n27 = 1; n27 < 4; ++n27) {
            array15[n27][0] = true;
            array15[n27][8] = true;
        }
        this.letters.put("o", array15);
        final boolean[][] array16 = new boolean[5][9];
        for (int n28 = 0; n28 < 9; ++n28) {
            array16[0][n28] = true;
        }
        for (int n29 = 1; n29 < 4; ++n29) {
            array16[n29][0] = true;
            array16[n29][4] = true;
            array16[4][n29] = true;
        }
        this.letters.put("p", array16);
        final boolean[][] array17 = new boolean[5][9];
        for (int n30 = 1; n30 < 8; ++n30) {
            array17[0][n30] = true;
            array17[4][n30] = true;
        }
        for (int n31 = 1; n31 < 4; ++n31) {
            array17[n31][0] = true;
            array17[n31][8] = true;
        }
        array17[4][8] = true;
        array17[3][7] = true;
        array17[2][6] = true;
        this.letters.put("q", array17);
        final boolean[][] array18 = new boolean[5][9];
        for (int n32 = 0; n32 < 9; ++n32) {
            array18[0][n32] = true;
        }
        for (int n33 = 1; n33 < 4; ++n33) {
            array18[n33][0] = true;
            array18[n33][4] = true;
            array18[4][n33] = true;
        }
        for (int n34 = 1; n34 < 5; ++n34) {
            array18[n34][n34 + 4] = true;
        }
        this.letters.put("r", array18);
        final boolean[][] array19 = new boolean[5][9];
        for (int n35 = 1; n35 < 4; ++n35) {
            array19[n35][0] = true;
            array19[0][n35] = true;
            array19[n35][4] = true;
            array19[4][n35 + 4] = true;
            array19[n35][8] = true;
        }
        array19[4][1] = true;
        array19[0][7] = true;
        this.letters.put("s", array19);
        final boolean[][] array20 = new boolean[5][9];
        for (int n36 = 0; n36 < 5; ++n36) {
            array20[n36][0] = true;
        }
        for (int n37 = 1; n37 < 9; ++n37) {
            array20[2][n37] = true;
        }
        this.letters.put("t", array20);
        final boolean[][] array21 = new boolean[5][9];
        for (int n38 = 0; n38 < 8; ++n38) {
            array21[0][n38] = true;
            array21[4][n38] = true;
        }
        for (int n39 = 1; n39 < 4; ++n39) {
            array21[n39][8] = true;
        }
        this.letters.put("u", array21);
        final boolean[][] array22 = new boolean[5][9];
        for (int n40 = 0; n40 < 7; ++n40) {
            array22[0][n40] = true;
            array22[4][n40] = true;
        }
        array22[1][7] = true;
        array22[2][8] = true;
        array22[3][7] = true;
        this.letters.put("v", array22);
        final boolean[][] array23 = new boolean[5][9];
        for (int n41 = 0; n41 < 9; ++n41) {
            array23[0][n41] = true;
            array23[4][n41] = true;
        }
        array23[1][7] = true;
        array23[2][6] = true;
        array23[3][7] = true;
        this.letters.put("w", array23);
        final boolean[][] array24 = new boolean[5][9];
        for (int n42 = 0; n42 < 2; ++n42) {
            array24[0][n42] = true;
            array24[1][n42 + 2] = true;
            array24[4][n42] = true;
            array24[3][n42 + 2] = true;
            array24[0][n42 + 7] = true;
            array24[1][n42 + 5] = true;
            array24[4][n42 + 7] = true;
            array24[3][n42 + 5] = true;
        }
        array24[2][4] = true;
        this.letters.put("x", array24);
        final boolean[][] array25 = new boolean[5][9];
        for (int n43 = 4; n43 < 9; ++n43) {
            array25[2][n43] = true;
        }
        for (int n44 = 0; n44 < 2; ++n44) {
            array25[0][n44] = true;
            array25[1][n44 + 2] = true;
            array25[4][n44] = true;
            array25[3][n44 + 2] = true;
        }
        this.letters.put("y", array25);
        final boolean[][] array26 = new boolean[5][9];
        for (int n45 = 0; n45 < 5; ++n45) {
            array26[n45][0] = true;
            array26[n45][8] = true;
        }
        for (int n46 = 0; n46 < 2; ++n46) {
            array26[4][n46] = true;
            array26[3][n46 + 2] = true;
            array26[1][n46 + 5] = true;
            array26[0][n46 + 7] = true;
        }
        array26[2][4] = true;
        this.letters.put("z", array26);
        final boolean[][] array27 = new boolean[5][9];
        for (int n47 = 2; n47 < 7; ++n47) {
            array27[0][n47] = true;
            array27[4][n47] = true;
        }
        array27[1][1] = true;
        array27[2][0] = true;
        array27[3][1] = true;
        array27[1][7] = true;
        array27[2][8] = true;
        array27[3][7] = true;
        this.letters.put("0", array27);
        final boolean[][] array28 = new boolean[5][9];
        for (int n48 = 0; n48 < 8; ++n48) {
            array28[2][n48] = true;
        }
        for (int n49 = 0; n49 < 5; ++n49) {
            array28[n49][8] = true;
        }
        array28[1][1] = true;
        array28[0][2] = true;
        this.letters.put("1", array28);
        final boolean[][] array29 = new boolean[5][9];
        for (int n50 = 1; n50 < 4; ++n50) {
            array29[n50][0] = true;
            array29[4][n50] = true;
            array29[n50][8] = true;
            array29[n50][7 - n50] = true;
        }
        array29[0][1] = true;
        array29[0][7] = true;
        array29[0][8] = true;
        array29[4][8] = true;
        this.letters.put("2", array29);
        final boolean[][] array30 = new boolean[5][9];
        for (int n51 = 1; n51 < 4; ++n51) {
            array30[n51][0] = true;
            array30[n51][4] = true;
            array30[n51][8] = true;
            array30[4][n51] = true;
            array30[4][n51 + 4] = true;
        }
        array30[0][1] = true;
        array30[0][7] = true;
        this.letters.put("3", array30);
        final boolean[][] array31 = new boolean[5][9];
        for (int n52 = 0; n52 < 9; ++n52) {
            array31[3][n52] = true;
        }
        for (int n53 = 0; n53 < 5; ++n53) {
            array31[n53][4] = true;
        }
        array31[0][3] = true;
        array31[1][2] = true;
        array31[2][1] = true;
        this.letters.put("4", array31);
        final boolean[][] array32 = new boolean[5][9];
        for (int n54 = 1; n54 < 4; ++n54) {
            array32[n54][0] = true;
            array32[0][n54] = true;
            array32[n54][4] = true;
            array32[4][n54 + 4] = true;
            array32[n54][8] = true;
        }
        array32[4][0] = true;
        array32[0][0] = true;
        array32[0][4] = true;
        array32[0][7] = true;
        this.letters.put("5", array32);
        final boolean[][] array33 = new boolean[5][9];
        for (int n55 = 1; n55 < 4; ++n55) {
            array33[n55][0] = true;
            array33[0][n55] = true;
            array33[n55][4] = true;
            array33[4][n55 + 4] = true;
            array33[n55][8] = true;
            array33[0][n55 + 4] = true;
        }
        array33[4][1] = true;
        array33[0][4] = true;
        this.letters.put("6", array33);
        final boolean[][] array34 = new boolean[5][9];
        for (int n56 = 0; n56 < 4; ++n56) {
            array34[n56][0] = true;
        }
        for (int n57 = 0; n57 < 2; ++n57) {
            array34[4][n57] = true;
            array34[3][n57 + 2] = true;
            array34[2][n57 + 4] = true;
            array34[1][n57 + 6] = true;
        }
        array34[1][8] = true;
        this.letters.put("7", array34);
        final boolean[][] array35 = new boolean[5][9];
        for (int n58 = 1; n58 < 4; ++n58) {
            array35[n58][0] = true;
            array35[0][n58] = true;
            array35[4][n58] = true;
            array35[n58][4] = true;
            array35[0][n58 + 4] = true;
            array35[4][n58 + 4] = true;
            array35[n58][8] = true;
        }
        this.letters.put("8", array35);
        final boolean[][] array36 = new boolean[5][9];
        for (int n59 = 1; n59 < 4; ++n59) {
            array36[n59][0] = true;
            array36[0][n59] = true;
            array36[n59][4] = true;
            array36[n59][8] = true;
        }
        for (int n60 = 1; n60 < 8; ++n60) {
            array36[4][n60] = true;
        }
        array36[0][7] = true;
        this.letters.put("9", array36);
        final boolean[][] array37 = new boolean[5][9];
        for (int n61 = 0; n61 < 5; ++n61) {
            array37[n61][4] = true;
        }
        this.letters.put("-", array37);
        final boolean[][] array38 = new boolean[5][9];
        array38[1][7] = true;
        array38[1][8] = true;
        array38[2][7] = true;
        array38[2][8] = true;
        this.letters.put(".", array38);
        final boolean[][] array39 = new boolean[5][9];
        for (int n62 = 0; n62 < 2; ++n62) {
            array39[4][n62] = true;
            array39[3][n62 + 2] = true;
            array39[1][n62 + 5] = true;
            array39[0][n62 + 7] = true;
        }
        array39[2][4] = true;
        this.letters.put("/", array39);
        final boolean[][] array40 = new boolean[5][9];
        array40[2][6] = true;
        array40[2][7] = true;
        array40[1][8] = true;
        this.letters.put(",", array40);
        final boolean[][] array41 = new boolean[5][9];
        for (int n63 = 1; n63 < 4; ++n63) {
            array41[n63][0] = true;
            array41[4][n63] = true;
            array41[2][n63 + 3] = true;
        }
        array41[3][4] = true;
        array41[0][1] = true;
        array41[2][8] = true;
        this.letters.put("?", array41);
        final boolean[][] array42 = new boolean[5][9];
        for (int n64 = 0; n64 < 5; ++n64) {
            array42[n64][4] = true;
            array42[2][n64 + 2] = true;
        }
        this.letters.put("+", array42);
        final boolean[][] array43 = new boolean[5][9];
        for (int n65 = 0; n65 < 5; ++n65) {
            array43[n65][3] = true;
            array43[n65][5] = true;
        }
        this.letters.put("=", array43);
        final boolean[][] array44 = new boolean[5][9];
        array44[2][6] = true;
        array44[2][7] = true;
        array44[1][8] = true;
        array44[2][2] = true;
        array44[2][3] = true;
        array44[1][2] = true;
        array44[1][3] = true;
        this.letters.put(";", array44);
        final boolean[][] array45 = new boolean[5][9];
        array45[2][2] = true;
        array45[2][3] = true;
        array45[1][2] = true;
        array45[1][3] = true;
        array45[2][5] = true;
        array45[2][6] = true;
        array45[1][5] = true;
        array45[1][6] = true;
        this.letters.put(":", array45);
        final boolean[][] array46 = new boolean[5][9];
        array46[1][0] = true;
        array46[1][1] = true;
        array46[3][0] = true;
        array46[3][1] = true;
        this.letters.put("\"", array46);
        final boolean[][] array47 = new boolean[5][9];
        array47[2][0] = true;
        array47[2][1] = true;
        this.letters.put("'", array47);
        final boolean[][] array48 = new boolean[5][9];
        for (int n66 = 0; n66 < 6; ++n66) {
            array48[2][n66] = true;
            array48[1][n66] = true;
        }
        array48[1][7] = true;
        array48[1][8] = true;
        array48[2][7] = true;
        array48[2][8] = true;
        this.letters.put("!", array48);
        final boolean[][] array49 = new boolean[5][9];
        for (int n67 = 1; n67 < 4; ++n67) {
            array49[n67][1] = true;
            array49[n67][7] = true;
            array49[0][n67 + 1] = true;
            array49[0][n67 + 3] = true;
            array49[4][n67 + 1] = true;
            array49[2][n67 + 2] = true;
        }
        array49[3][5] = true;
        this.letters.put("@", array49);
        final boolean[][] array50 = new boolean[5][9];
        for (int n68 = 0; n68 < 5; ++n68) {
            array50[1][n68 + 2] = true;
            array50[3][n68 + 2] = true;
            array50[n68][3] = true;
            array50[n68][5] = true;
        }
        this.letters.put("#", array50);
        final boolean[][] array51 = new boolean[5][9];
        for (int n69 = 1; n69 < 4; ++n69) {
            array51[n69][2] = true;
            array51[n69][4] = true;
            array51[n69][6] = true;
        }
        array51[2][1] = true;
        array51[2][3] = true;
        array51[2][5] = true;
        array51[2][7] = true;
        array51[0][3] = true;
        array51[4][5] = true;
        array51[4][2] = true;
        array51[0][6] = true;
        this.letters.put("$", array51);
        final boolean[][] array52 = new boolean[5][9];
        for (int n70 = 0; n70 < 2; ++n70) {
            array52[4][n70] = true;
            array52[3][n70 + 2] = true;
            array52[1][n70 + 5] = true;
            array52[0][n70 + 7] = true;
        }
        array52[2][4] = true;
        array52[0][0] = true;
        array52[1][0] = true;
        array52[0][1] = true;
        array52[1][1] = true;
        array52[4][8] = true;
        array52[4][7] = true;
        array52[3][8] = true;
        array52[3][7] = true;
        this.letters.put("%", array52);
        final boolean[][] array53 = new boolean[5][9];
        array53[1][0] = true;
        array53[0][1] = true;
        array53[0][2] = true;
        array53[2][1] = true;
        array53[2][2] = true;
        array53[1][3] = true;
        array53[2][4] = true;
        array53[2][5] = true;
        array53[3][6] = true;
        array53[3][7] = true;
        array53[4][8] = true;
        for (int n71 = 4; n71 < 8; ++n71) {
            array53[0][n71] = true;
        }
        array53[1][8] = true;
        array53[2][8] = true;
        array53[4][4] = true;
        array53[4][5] = true;
        this.letters.put("&", array53);
        final boolean[][] array54 = new boolean[5][9];
        for (int n72 = 2; n72 < 7; ++n72) {
            array54[1][n72] = true;
        }
        array54[2][1] = true;
        array54[3][0] = true;
        array54[2][7] = true;
        array54[3][8] = true;
        this.letters.put("(", array54);
        final boolean[][] array55 = new boolean[5][9];
        for (int n73 = 2; n73 < 7; ++n73) {
            array55[3][n73] = true;
        }
        array55[2][1] = true;
        array55[1][0] = true;
        array55[2][7] = true;
        array55[1][8] = true;
        this.letters.put(")", array55);
        this.offset = -this.width;
        this.style = new Vector();
        this.text = new Vector();
        this.index = 0;
        this.speed = 2;
        this.tempStyle = "r";
        this.background = Color.black;
        this.foreground = Color.yellow;
        if (this.getParameter("Created by John Morris - www.javaplayground.com") == null) {
            this.addLine("no author tag found!", "r");
        }
        else if (this.getParameter("file") != null) {
            final String string = this.getDocumentBase().toString();
            this.loadFile(String.valueOf(string.substring(0, string.lastIndexOf("/") + 1)) + this.getParameter("file"));
        }
        else {
            this.addLine("no file specified!", "r");
        }
        this.random = new Random();
        this.offI = this.createImage(this.width, this.height);
        this.offG = this.offI.getGraphics();
    }
    
    public final void loadFile(final String s) {
        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(s).openConnection().getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                this.parseLine(line);
            }
            bufferedReader.close();
        }
        catch (Exception ex) {
            this.addLine("error reading file!", "r");
        }
    }
    
    public final void parseLine(final String s) {
        if (s.indexOf("=") >= 0) {
            final String trim = s.substring(0, s.indexOf("=")).trim();
            final String trim2 = s.substring(s.indexOf("=") + 1).trim();
            if (trim.equalsIgnoreCase("effect")) {
                this.tempStyle = trim2;
                return;
            }
            if (trim.equalsIgnoreCase("speed")) {
                this.speed = this.parseSpeed(trim2);
                return;
            }
            if (trim.equalsIgnoreCase("background")) {
                this.background = this.parseColor(trim2);
                return;
            }
            if (trim.equalsIgnoreCase("foreground")) {
                this.foreground = this.parseColor(trim2);
            }
        }
        else {
            this.addLine(s.toLowerCase(), this.tempStyle);
        }
    }
    
    public final Color parseColor(final String s) {
        int int1;
        int int2;
        int int3;
        try {
            final int index = s.indexOf(",");
            int1 = Integer.parseInt(s.substring(0, index));
            int2 = Integer.parseInt(s.substring(index + 1, s.indexOf(",", index + 1)));
            int3 = Integer.parseInt(s.substring(s.indexOf(",", index + 1) + 1));
        }
        catch (Exception ex) {
            int1 = 0;
            int2 = 0;
            int3 = 0;
        }
        return new Color(int1, int2, int3);
    }
    
    public final int parseSpeed(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (Exception ex) {
            int1 = 2;
        }
        if (int1 < 0 || int1 > 5) {
            int1 = 2;
        }
        return int1;
    }
    
    public final void addLine(final String s, final String s2) {
        this.text.addElement(s);
        this.style.addElement(s2);
    }
    
    public final void start() {
        if (this.me == null) {
            (this.me = new Thread(this)).start();
        }
    }
    
    public final void draw() {
        try {
            this.offG.setColor(this.background);
            this.offG.fillRect(0, 0, this.width, this.height);
            this.offG.setColor(Color.gray);
            for (int i = 0; i < this.width; i += 5) {
                for (int j = 0; j < this.height; j += 5) {
                    this.offG.drawOval(i + 1, j + 1, 3, 3);
                }
            }
            this.offG.setColor(this.foreground);
            final String s = this.text.elementAt(this.index);
            for (int k = 0; k < s.length(); ++k) {
                final boolean[][] array;
                if ((array = this.letters.get(s.substring(k, k + 1))) != null) {
                    for (int l = 0; l < 5; ++l) {
                        for (int n = 0; n < 9; ++n) {
                            if (array[l][n]) {
                                switch (this.style.elementAt(this.index).charAt(0)) {
                                    case 'l':
                                    case 'r': {
                                        this.offG.fillOval(k * 5 + this.offset * 5 + k * 25 + l * 5 + 1, n * 5 + 1, 3, 3);
                                        break;
                                    }
                                    case 'd':
                                    case 'u': {
                                        this.offG.fillOval((this.width / 5 - s.length() * 6) / 2 * 5 + k * 25 + k * 5 + l * 5 + 1, this.offset * 5 + n * 5 + 1, 3, 3);
                                        break;
                                    }
                                    case 'h': {
                                        if (n < 4) {
                                            this.offG.fillOval((this.width / 5 - s.length() * 6) / 2 * 5 + k * 25 + k * 5 + l * 5 + 1, (this.offset - 4) * 5 + n * 5 + 1, 3, 3);
                                            break;
                                        }
                                        this.offG.fillOval((this.width / 5 - s.length() * 6) / 2 * 5 + k * 25 + k * 5 + l * 5 + 1, (9 - this.offset) * 5 + (n - 5) * 5 + 1, 3, 3);
                                        break;
                                    }
                                    case 'v': {
                                        if (k < s.length() / 2) {
                                            this.offG.fillOval(k * 5 + (this.offset - s.length() / 2 * 6) * 5 + k * 25 + l * 5 + 1, n * 5 + 1, 3, 3);
                                            break;
                                        }
                                        this.offG.fillOval((k - s.length() / 2) * 5 + (this.width / 5 - this.offset) * 5 + (k - s.length() / 2) * 25 + l * 5 + 1, n * 5 + 1, 3, 3);
                                        break;
                                    }
                                    case 'b': {
                                        if (this.offset == 0) {
                                            this.offG.fillOval((this.width / 5 - s.length() * 6) / 2 * 5 + k * 25 + k * 5 + l * 5 + 1, n * 5 + 1, 3, 3);
                                            break;
                                        }
                                        break;
                                    }
                                    case 't': {
                                        if (this.offset == 1 || (this.offset == 0 && this.random.nextInt() % 2 == 1)) {
                                            this.offG.fillOval((this.width / 5 - s.length() * 6) / 2 * 5 + k * 25 + k * 5 + l * 5 + 1, n * 5 + 1, 3, 3);
                                            break;
                                        }
                                        break;
                                    }
                                    case 'i': {
                                        if ((this.offset <= s.length() && k < this.offset) || (this.offset > s.length() && k > this.offset - s.length())) {
                                            this.offG.fillOval((this.width / 5 - s.length() * 6) / 2 * 5 + k * 25 + k * 5 + l * 5 + 1, n * 5 + 1, 3, 3);
                                            break;
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final void paint(final Graphics graphics) {
        this.draw();
        graphics.drawImage(this.offI, 0, 0, null);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void animate(final int n) {
        try {
            this.repaint();
            Thread.sleep(n);
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final void run() {
        try {
            while (true) {
                this.index = 0;
                while (this.index < this.text.size()) {
                    final String s = this.text.elementAt(this.index);
                    switch (this.style.elementAt(this.index).charAt(0)) {
                        case 'r': {
                            this.offset = this.width / 5;
                            while (this.offset > -(s.length() + 1) * 6) {
                                this.animate(this.speed * 50);
                                --this.offset;
                            }
                            break;
                        }
                        case 'l': {
                            this.offset = -s.length() * 6;
                            while (this.offset < this.width / 5) {
                                this.animate(this.speed * 50);
                                ++this.offset;
                            }
                            break;
                        }
                        case 'u': {
                            this.offset = this.height / 5;
                            while (this.offset > 0) {
                                this.animate(this.speed * 50);
                                --this.offset;
                            }
                            this.offset = 0;
                            this.animate(this.speed * 500);
                            this.offset = 0;
                            while (this.offset > -this.height / 5) {
                                this.animate(this.speed * 50);
                                --this.offset;
                            }
                            break;
                        }
                        case 'd': {
                            this.offset = -this.height / 5;
                            while (this.offset < 0) {
                                this.animate(this.speed * 50);
                                ++this.offset;
                            }
                            this.offset = 0;
                            this.animate(this.speed * 500);
                            this.offset = 0;
                            while (this.offset < this.height / 5) {
                                this.animate(this.speed * 50);
                                ++this.offset;
                            }
                            break;
                        }
                        case 'h': {
                            this.offset = 0;
                            while (this.offset < this.height / 10) {
                                this.animate(this.speed * 50);
                                ++this.offset;
                            }
                            this.animate(this.speed * 500);
                            this.offset = this.height / 10;
                            while (this.offset >= 0) {
                                this.animate(this.speed * 50);
                                --this.offset;
                            }
                            break;
                        }
                        case 'v': {
                            this.offset = 0;
                            while (this.offset < this.width / 10) {
                                this.animate(this.speed * 50);
                                ++this.offset;
                            }
                            this.animate(this.speed * 500);
                            this.offset = this.width / 10;
                            while (this.offset >= 0) {
                                this.animate(this.speed * 50);
                                --this.offset;
                            }
                            break;
                        }
                        case 'b': {
                            for (int i = 0; i < 20; ++i) {
                                this.offset = i % 2;
                                this.animate(this.speed * 50);
                            }
                            break;
                        }
                        case 't': {
                            this.offset = 0;
                            for (int j = 0; j < 20; ++j) {
                                this.animate(this.speed * 50);
                            }
                            this.offset = 1;
                            this.animate(this.speed * 500);
                            break;
                        }
                        case 'i': {
                            this.offset = 0;
                            while (this.offset < s.length()) {
                                this.animate(this.speed * 50);
                                ++this.offset;
                            }
                            this.animate(this.speed * 500);
                            this.offset = s.length();
                            while (this.offset < s.length() * 2) {
                                this.animate(this.speed * 50);
                                ++this.offset;
                            }
                            break;
                        }
                    }
                    ++this.index;
                }
            }
        }
        catch (Exception ex) {
            this.stop();
        }
    }
    
    public final void stop() {
        this.me = null;
    }
    
    public ScoreBoard() {
        this.width = 200;
        this.height = 45;
    }
}
