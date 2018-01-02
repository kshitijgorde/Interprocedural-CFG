// 
// Decompiled by Procyon v0.5.30
// 

package com.agentcy.applet.matrixcode;

import java.awt.Graphics2D;

public class LineOfCode
{
    private static final int WAITING_STATE = 0;
    private static final int DRAWING_STATE = 1;
    private static final int FADING_STATE = 2;
    int _column;
    CharacterPainter _charPainter;
    int _currentRow;
    int _tailRow;
    int _firstChar;
    int _numOfCharacters;
    int _numOfRows;
    int _state;
    int _waitCount;
    int _speed;
    int _skipCount;
    
    public LineOfCode(final int column, final CharacterPainter charPainter, final int numOfRows) {
        this._column = column;
        this._charPainter = charPainter;
        this._numOfRows = numOfRows;
        this._numOfCharacters = this._charPainter.getNumOfCharacters();
        this._currentRow = 0;
        this._tailRow = 0;
        this._firstChar = (int)(Math.round(Math.random() * this._numOfCharacters) % this._numOfCharacters);
        this._state = 0;
        this._waitCount = (int)Math.round(Math.random() * 20.0 + 1.0);
        this._speed = (int)(Math.random() * 10.0 % 5.0);
        this._skipCount = 0;
    }
    
    public void paint(final Graphics2D graphics2D) {
        if (this._skipCount < this._speed) {
            ++this._skipCount;
            return;
        }
        this._skipCount = 0;
        switch (this._state) {
            case 2: {
                this._charPainter.eraseCharacter(graphics2D, this._column, this._tailRow++);
                if (this._tailRow > this._numOfRows) {
                    this._currentRow = 0;
                    this._state = 0;
                    this._waitCount = (int)Math.round(Math.random() * 20.0 + 1.0);
                    break;
                }
                break;
            }
            case 1: {
                if (this._currentRow > 0) {
                    this._charPainter.paintCharacter(this._firstChar, graphics2D, this._column, this._currentRow - 1, false);
                    this._firstChar = (int)(Math.round(Math.random() * this._numOfCharacters) % this._numOfCharacters);
                    this._charPainter.paintCharacter(this._firstChar, graphics2D, this._column, this._currentRow++, true);
                }
                else {
                    this._charPainter.paintCharacter(this._firstChar, graphics2D, this._column, this._currentRow++, true);
                }
                if (this._currentRow > this._numOfRows + 1) {
                    this._tailRow = 0;
                    this._state = 2;
                    break;
                }
                break;
            }
            case 0: {
                --this._waitCount;
                if (this._waitCount == 0) {
                    this._state = 1;
                    this._speed = (int)(Math.random() * 10.0 % 5.0);
                    break;
                }
                break;
            }
        }
    }
}
