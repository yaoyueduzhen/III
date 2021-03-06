package com.jw.iii.pocketjw;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabWidget;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by End on 2015/5/30.
 */
public class Helper {

    public static String md5(String input) {
        if ( (input == null) || (input.length() == 0) ){
            return "D41D8CD98F00B204E9800998ECF8427E";     //这个值是为了兼容C语言的DLL MD5值而存在
        }
        else{
            StringBuffer sb = new StringBuffer();
            try {
                MessageDigest algorithm = MessageDigest.getInstance("MD5");
                algorithm.reset();
                algorithm.update(input.getBytes());
                byte[] md5 = algorithm.digest();
                String singleByteHex = "";
                for (int i = 0; i < md5.length; i++) {
                    singleByteHex = Integer.toHexString(0xFF & md5[i]);
                    if (singleByteHex.length() == 1) {
                        sb.append("0");
                    }
                    sb.append(singleByteHex.toUpperCase());
                }
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
            return sb.toString();
        }
    }

    public static Bitmap getLocalBitmap(String url) {
        try {
            FileInputStream fileInputStream = new FileInputStream(url);
            return BitmapFactory.decodeStream(fileInputStream);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setTabWidgetTitleColor(Context context, TabWidget tabWidget, int color) {
        TextView textView = null;
        for (int i = 0, count = tabWidget.getChildCount(); i < count; i++) {
            textView = ((TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title));
            textView.setTextColor(context.getResources().getColorStateList(color));
        }
    }
}
