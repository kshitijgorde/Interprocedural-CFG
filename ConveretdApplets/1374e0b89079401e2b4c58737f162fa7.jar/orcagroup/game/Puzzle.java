// 
// Decompiled by Procyon v0.5.30
// 

package orcagroup.game;

import java.io.InputStream;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Puzzle
{
    String puzzleCode;
    String serviceNumber;
    String originalPuzzle;
    String puzzleId;
    Date puzzleDate;
    boolean debugMode;
    byte[] mapCells;
    byte[] fixedCells;
    byte[][] cellHints;
    boolean[] validDigits;
    int validDigitCount;
    boolean digitHints;
    
    public Puzzle() {
        this.debugMode = true;
        this.mapCells = new byte[] { 0, 0, 9, 0, 0, 2, 0, 0, 3, 0, 0, 8, 9, 0, 0, 4, 0, 2, 0, 7, 0, 0, 3, 0, 0, 9, 0, 0, 0, 0, 0, 0, 4, 0, 6, 0, 1, 0, 5, 0, 0, 3, 7, 0, 4, 0, 6, 0, 0, 1, 0, 0, 0, 0, 0, 8, 0, 0, 6, 0, 0, 3, 0, 7, 0, 6, 0, 0, 8, 1, 0, 0, 9, 0, 0, 7, 0, 0, 6, 0, 0 };
        this.fixedCells = new byte[] { 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0 };
        this.cellHints = new byte[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
        this.digitHints = true;
        this.puzzleCode = new String();
        this.serviceNumber = new String();
        this.puzzleId = new String();
        this.puzzleDate = new Date();
        this.validDigits = new boolean[9];
        this.validDigitCount = 0;
        this.originalPuzzle = new String();
        this.resetValidDigits();
        this.clearCellHints();
    }
    
    public void clear() {
        for (int idx = 0; idx < 81; ++idx) {
            this.mapCells[idx] = 0;
            this.fixedCells[idx] = 0;
        }
        this.resetValidDigits();
        this.clearCellHints();
    }
    
    public void reset() {
        this.clear();
        this.parsePuzzleData(this.originalPuzzle);
    }
    
    public boolean IsFixedCell(final int x, final int y) {
        return this.fixedCells[x + y * 9] == 1;
    }
    
    public boolean mapCompleted() {
        boolean result = true;
        for (int idx = 0; idx < 81; ++idx) {
            if (this.mapCells[idx] == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    public byte getCell(final int x, final int y) {
        return this.mapCells[x + y * 9];
    }
    
    public void setCell(final int x, final int y, final byte cellValue) {
        this.setIndexCell(x + y * 9, cellValue);
    }
    
    public void setIndexCell(final int index, final byte cellValue) {
        this.mapCells[index] = cellValue;
        this.clearCellHint(index);
    }
    
    public byte getIndexCell(final int index) {
        return this.mapCells[index];
    }
    
    public void setIndexFixed(final int index, final byte fixedValue) {
        this.fixedCells[index] = fixedValue;
    }
    
    void parsePuzzleData(final String puzzleData) {
        int idx = 0;
        this.originalPuzzle = puzzleData;
        final String[] values = puzzleData.split(";");
        this.clear();
        while (idx < values.length) {
            if (idx > 2) {
                final String[] cellvalue = values[idx].split("=");
                final int cell = Integer.parseInt(cellvalue[0]);
                final int value = Integer.parseInt(cellvalue[1]);
                this.setIndexCell(cell, (byte)value);
                this.setIndexFixed(cell, (byte)1);
            }
            else if (idx == 0) {
                this.puzzleCode = values[idx];
            }
            else if (idx == 1) {
                this.serviceNumber = values[idx];
            }
            else if (idx == 2) {
                final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                try {
                    this.puzzleDate = sdf.parse(values[idx]);
                }
                catch (ParseException ex) {}
            }
            ++idx;
        }
    }
    
    public int getPuzzleFromURL(final String basePath, final String appId, final String puzzleDate) {
        if (this.debugMode) {
            this.parsePuzzleData("00000;0909-2783658;9/4/2005;0=3;1=2;3=1;4=5;8=4;9=7;11=5;12=9;17=8;24=2;30=8;33=4;38=6;40=3;43=7;46=9;48=4;53=6;57=3;61=9;65=2;68=9;69=7;70=1;72=1;76=6;");
            return 0;
        }
        try {
            this.puzzleId = appId;
            final String urlData = new String(basePath + "sudoku.asp?date=" + puzzleDate + "&id=" + appId);
            final URL puzzleURL = new URL(urlData);
            final URLConnection urlConnection = puzzleURL.openConnection();
            final InputStream content = urlConnection.getInputStream();
            final BufferedReader in = new BufferedReader(new InputStreamReader(content));
            final String puzzleData = in.readLine();
            if (puzzleData.length() != 0) {
                this.parsePuzzleData(puzzleData);
            }
            return 0;
        }
        catch (IOException e) {
            return 1;
        }
    }
    
    public int setPuzzleStats(final String basePath, final int playTime) {
        try {
            final String urlData = new String(basePath + "done.asp?code=" + this.puzzleCode + "&id=" + this.puzzleId + "&playtime=" + String.valueOf(playTime));
            final URL puzzleURL = new URL(urlData);
            final URLConnection urlConnection = puzzleURL.openConnection();
            final InputStream content = urlConnection.getInputStream();
            final BufferedReader in = new BufferedReader(new InputStreamReader(content));
            return 0;
        }
        catch (IOException e) {
            return 1;
        }
    }
    
    public String getPuzzleCode() {
        return this.puzzleCode;
    }
    
    public String getServiceNumber() {
        return this.serviceNumber;
    }
    
    public void resetValidDigits() {
        for (int i = 0; i < 9; ++i) {
            this.validDigits[i] = true;
        }
    }
    
    public void checkValidDigits(final int cX, final int cY) {
        this.resetValidDigits();
        final int rx = cX / 3 * 3;
        final int ry = cY / 3 * 3;
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                final byte v = this.getCell(rx + x, ry + y);
                if (v != 0) {
                    this.validDigits[v - 1] = false;
                }
            }
        }
        for (int i = 0; i < 9; ++i) {
            byte v = this.getCell(cX, i);
            if (v != 0) {
                this.validDigits[v - 1] = false;
            }
            v = this.getCell(i, cY);
            if (v != 0) {
                this.validDigits[v - 1] = false;
            }
        }
        this.validDigitCount = 0;
        for (int i = 0; i < 9; ++i) {
            if (this.validDigits[i]) {
                ++this.validDigitCount;
            }
        }
    }
    
    public int getValidDigitCount() {
        if (this.digitHints) {
            return this.validDigitCount;
        }
        return 9;
    }
    
    public boolean getValidDigit(final int index) {
        return !this.digitHints || this.validDigits[index];
    }
    
    public void solvePuzzle() {
        int prevcount;
        do {
            prevcount = 0;
            int cx = 0;
            int cy = 0;
            for (int i = 0; i < 81; ++i) {
                if (this.getIndexCell(i) == 0) {
                    this.checkValidDigits(cx, cy);
                    if (this.getValidDigitCount() == 1) {
                        for (int j = 0; j < 9; ++j) {
                            if (this.getValidDigit(j)) {
                                this.setIndexCell(i, (byte)(j + 1));
                                ++prevcount;
                                break;
                            }
                        }
                    }
                }
                if (++cx > 8) {
                    cx = 0;
                    ++cy;
                }
            }
        } while (prevcount != 0);
    }
    
    public String getPuzzleAnswerCode() {
        String code = new String();
        for (int i = 72; i < 81; ++i) {
            code += String.valueOf(this.mapCells[i]);
            if (i < 80) {
                code += " ";
            }
        }
        return code;
    }
    
    public void setDigitHints(final boolean value) {
        this.digitHints = value;
    }
    
    public boolean getDigitHints() {
        return this.digitHints;
    }
    
    public void setDebugMode(final boolean value) {
        this.debugMode = value;
    }
    
    void clearCellHint(final int index) {
        for (int i = 0; i < 4; ++i) {
            this.cellHints[i][index] = 0;
        }
    }
    
    public void setCellHint(final int x, final int y, final byte hintValue) {
        final int cellIndex = x + y * 9;
        boolean hintSet = false;
        boolean hintRemoved = false;
        for (int i = 0; i < 4; ++i) {
            if (this.cellHints[i][cellIndex] == hintValue) {
                this.cellHints[i][cellIndex] = 0;
                hintRemoved = true;
                for (int j = i + 1; j < 4; ++j) {
                    this.cellHints[j - 1][cellIndex] = this.cellHints[j][cellIndex];
                }
                this.cellHints[3][cellIndex] = 0;
                break;
            }
        }
        if (!hintRemoved) {
            for (int i = 0; i < 4; ++i) {
                if (this.cellHints[i][cellIndex] == 0) {
                    this.cellHints[i][cellIndex] = hintValue;
                    hintSet = true;
                    break;
                }
            }
            if (!hintSet) {
                for (int i = 1; i < 4; ++i) {
                    this.cellHints[i - 1][cellIndex] = this.cellHints[i][cellIndex];
                }
                this.cellHints[3][cellIndex] = hintValue;
            }
        }
    }
    
    public void clearCellHints() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 81; ++j) {
                this.cellHints[i][j] = 0;
            }
        }
    }
    
    public int getCellHintCount(final int x, final int y) {
        return this.getCellHintCountIndex(x + y * 9);
    }
    
    public int getCellHintCountIndex(final int index) {
        int count = 0;
        for (int i = 0; i < 4; ++i) {
            if (this.cellHints[i][index] != 0) {
                ++count;
            }
        }
        return count;
    }
    
    public byte getCellHint(final int x, final int y, final int index) {
        return this.getCellHintIndex(x + y * 9, index);
    }
    
    public byte getCellHintIndex(final int cellIndex, final int index) {
        return this.cellHints[index][cellIndex];
    }
    
    public Date getPuzzleDate() {
        return this.puzzleDate;
    }
}
