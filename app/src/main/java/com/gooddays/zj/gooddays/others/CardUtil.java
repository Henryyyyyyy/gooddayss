package com.gooddays.zj.gooddays.others;

import android.content.Context;
import android.util.Log;

/**
 * Created by Henry.zengj on 2018/11/16.
 */

public class CardUtil {
    public static boolean isICCardFormat(Context context, String card) {
        String rules = "abcdefABCDEF0123456789";
        boolean isCorrect = true;
        if (card == null || card.length() == 0) {
            return false;
        }
        if (card.length() != 8) {
            Log.d("henry","卡号长度必须等于8位");
            return false;
        }
        for (int i = 0; i < card.length(); i++) {
            if (rules.contains(String.valueOf(card.charAt(i)))) {
                continue;
            } else {
                isCorrect = false;
                break;
            }
        }

        return isCorrect;
    }

    public static boolean isBlueToothCardFormat(Context context,String card) {
        String rules = "abcdefABCDEF0123456789";
        boolean isCorrect = true;
        if (card == null || card.length() == 0) {
            return false;
        }
        if (card.length() != 12) {
            Log.d("henry","卡号长度必须等于12位");
            return false;
        }
        for (int i = 0; i < card.length(); i++) {
            if (rules.contains(String.valueOf(card.charAt(i)))) {
                continue;
            } else {
                isCorrect = false;
                break;
            }
        }

        return isCorrect;
    }
}
