package com.github.androidify;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class BitmapUtils {

    public static Bitmap getBitmap(Resources resources, int drawableResourceId) {
        return BitmapFactory.decodeResource(resources,
                drawableResourceId);
    }

    public static Bitmap combineDrawables(Resources resources, int head, int body, int legs) {
        Bitmap headBitmap = getBitmap(resources, head);
        Bitmap bodyBitmap = getBitmap(resources, body);
        Bitmap legsBitmap = getBitmap(resources, legs);

        int height = headBitmap.getHeight() + bodyBitmap.getHeight() + legsBitmap.getHeight();
        int width = Math.max(headBitmap.getWidth(), Math.max(bodyBitmap.getWidth(), legsBitmap.getWidth()));

        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas comboImage = new Canvas(result);
        comboImage.drawBitmap(headBitmap, 0f, 0f, null);
        comboImage.drawBitmap(bodyBitmap, 0f, headBitmap.getHeight(), null);
        comboImage.drawBitmap(legsBitmap, 0f, headBitmap.getHeight() + bodyBitmap.getHeight(), null);

        return result;
    }
}
