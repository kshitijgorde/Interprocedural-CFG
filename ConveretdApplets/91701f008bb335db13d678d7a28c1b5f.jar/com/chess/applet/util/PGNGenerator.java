// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.applet.util;

import java.text.SimpleDateFormat;
import com.chess.chessboard.ChessboardModel;
import java.util.Date;

public class PGNGenerator
{
    public static String generatePGN(final String siteName, final Date date, final String whitePlayerName, final String blackPlayerName, final ChessboardModel.PieceColor winner, final String moves) {
        final StringBuilder builder = new StringBuilder();
        builder.append("[Site \"").append(siteName).append("\"]\n");
        builder.append("[Date \"").append(new SimpleDateFormat("yyyy.MM.dd").format(date)).append("\"]\n");
        builder.append("[White \"").append(whitePlayerName).append("\"]\n");
        builder.append("[Black \"").append(blackPlayerName).append("\"]\n");
        if (winner != null) {
            String result = null;
            switch (winner) {
                case WHITE: {
                    result = "1-0";
                    break;
                }
                case BLACK: {
                    result = "0-1";
                    break;
                }
                default: {
                    result = "½-½";
                    break;
                }
            }
            builder.append("[Result \"").append(result).append("\"]\n");
        }
        builder.append(moves).append("\n");
        return builder.toString();
    }
}
