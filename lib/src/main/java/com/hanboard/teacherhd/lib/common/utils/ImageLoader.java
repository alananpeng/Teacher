package com.hanboard.teacherhd.lib.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 项目名称：TeacherHD1.0
 * 类描述：
 * 创建人：peng.an@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/8/12 0012 20:59
 */
public class ImageLoader {

    // 获取网络图片，如果缓存里面有就从缓存里面获取
    public static  String getImagePath(Context context, String url) {
        if(url == null )
            return "";
        String imagePath = "";
        String   fileName   = "";

        // 获取url中图片的文件名与后缀
        if(url!=null&&url.length()!=0){
            fileName  = url.substring(url.lastIndexOf("/")+1);
        }

        // 图片在手机本地的存放路径,注意：fileName为空的情况
        imagePath = context.getCacheDir() + "/" + fileName;
        File file = new File(context.getCacheDir(),fileName);// 保存文件,
        if(!file.exists())
        {
            try {
                byte[] data =  readInputStream(getRequest(url));
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
                        data.length);

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(
                        file));

                imagePath = file.getAbsolutePath();

            } catch (Exception e) {
            }
        }
        return imagePath;
    } // getImagePath( )结束。
    public static InputStream getRequest(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000); // 5秒
        if(conn.getResponseCode() == 200){
            return conn.getInputStream();
        }
        return null;

    }
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len = 0;
        while( (len = inStream.read(buffer)) != -1 ){
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }
}
