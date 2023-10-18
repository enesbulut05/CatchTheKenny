package com.enesbulut.catchthekenny;
import android.content.Context;
import android.content.SharedPreferences;

public class BestScore {
    private static int bestScore;

    // Bu metodu çağırarak SharedPreferences üzerine skoru kaydedebilirsiniz
    public static void saveBestScore(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BestScore", bestScore);
        editor.apply();
    }

    // Bu metodu çağırarak SharedPreferences'den skoru geri yükleyebilirsiniz
    public static void loadBestScore(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        bestScore = sharedPreferences.getInt("BestScore", 0);
    }

    public static int getBestScore() {
        return bestScore;
    }

    public static void setBestScore(int value) {
        bestScore = value;
    }
}

