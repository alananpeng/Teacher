package com.hanboard.teacher.common.tools;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.hanboard.teacherhd.lib.common.utils.SDCardHelper;

import java.io.File;

/**
 * Created by Administrator on 2016/8/8.
 */
public class ClearSdCard {
    /**
     * 清理sd卡緩存文件
     * @param paths
     * @param context
     */
    public static void clearData(final String paths, final Context context){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                File file = new File(SDCardHelper.getSDCardPath()+File.separator+"毕业论文写作要求.doc");
                delete(file);
                  /* File[] files = file.listFiles();
                if(files != null && files.length > 0){
                 for(File f : files){
                        String name = File.separator + f.getName();
                        //paths集合中包含name
                        if(paths.contains(name)){
                            delete(f);
                        }
                    }

                }*/
                Looper.prepare();
                Toast.makeText(context, "SD卡缓存清理完成", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }).start();
    }

    /**
     * 递归删除文件
     * @param file
     */
    public static void delete(File file){
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                delete(f);
            }
        }else{
            file.delete();
        }
    }

    /**
     * 拷贝数据库，并返回数据库文件对象
     * @return
     */
   /* private File copyDb(){
        //将数据库拷贝到files目录
        File file = new File(getFilesDir(), "clearpath.db");
        if(!file.exists()){
            try {
                InputStream in = getAssets().open(clearpath.db);
                OutputStream out = new FileOutputStream(file);
                byte[] buffer = new byte[1024];
                int len = 0;
                while((len = in.read(buffer)) != -1){
                    out.write(buffer, 0, len);
                }
                out.close();
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }*/

    /**
     * 获取数据库中要清理的文件目录集合
     * @return
     */
    /*private List<String> getPaths(){
        //复制数据库
        List<String>list = new ArrayList<String>();
        File file = copyDb();
        if(file != null){
            SQLiteDatabase db = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
            if(db.isOpen()){
                Cursor c = db.query(softdetail, new String[]{filepath}, null, null, null, null, null);
                while(c.moveToNext()){
                    String path = c.getString(c.getColumnIndex(filepath));
                    list.add(path);
                }
                c.close();
                db.close();
            }
        }
        return list;
    }*/
}
