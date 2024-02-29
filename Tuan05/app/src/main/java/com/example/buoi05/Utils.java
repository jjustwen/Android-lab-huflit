package com.example.buoi05;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.IOException;
import java.io.InputStream;
import android.util.Log;


public class Utils {
    public static Bitmap converToBitmapFromAssets(Context context, String nameImage) {
        AssetManager assetManager = context.getAssets();
        try {
            // Kiểm tra đường dẫn đầy đủ
            String fullPath = "images/" + nameImage;
            InputStream inputStream = assetManager.open(fullPath);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Utils", "Error loading image from assets: " + nameImage, e);
        }
        return null;
    }
}

