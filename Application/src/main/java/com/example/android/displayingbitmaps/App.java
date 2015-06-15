package com.example.android.displayingbitmaps;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.android.displayingbitmaps.provider.Images;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by recoilme on 10/06/15.
 */
public class App extends Application {
    public String[] listOfAllImages;


    public void onCreate() {
        super.onCreate();
        ArrayList<String> list = getAllImagesPath(this);
        list.addAll(Arrays.asList(Images.imageUrls));
        listOfAllImages = list.toArray(new String[list.size()]);
    }

    public ArrayList<String> getAllImagesPath(Context context) {
        Uri uri;
        Cursor cursor = null;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        try {

            int column_index_data;

            String absolutePathOfImage = null;
            uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

            String[] projection = {MediaStore.MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

            cursor = context.getContentResolver().query(uri, projection, null,
                    null, null);

            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            while (cursor.moveToNext()) {
                absolutePathOfImage = cursor.getString(column_index_data);
                //if (absolutePathOfImage.contains("DCIM") || absolutePathOfImage.contains("Camera")) {
                listOfAllImages.add(absolutePathOfImage);
                //}
            }
        }
        catch (Exception e) {
        }
        finally {
            try {
                if( cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }

            } catch(Exception ex) {}

        }
        return listOfAllImages;
    }
}
