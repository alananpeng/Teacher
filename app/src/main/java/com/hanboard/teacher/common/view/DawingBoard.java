package com.hanboard.teacher.common.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 项目名称：DawingBoard
 * 类描述：
 * 创建人：kun.ren@hanboard.com
 * 作者单位：四川汉博德信息技术有限公司
 * 创建时间：2016/7/26
 **/
public class DawingBoard extends View {
    private Context context;
    public static final int SHAPE_CURVE = 1;
    public static final int SHAPE_LINE = 2;
    public static final int SHAPE_SQUARE = 3;
    public static final int SHAPE_OVAL = 4;
    public static final int SHAPE_CIRCLE = 5;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    private int mShape = SHAPE_CURVE;
    //真实的画笔
    private Paint mPaint;
    //画布的画笔
    private Paint mBitmapPaint;
    //临时点坐标
    private float startX;
    private float startY;
    private float stopX;
    private float stopY;
    private boolean isPaint = false;
    //private boolean isClear = false;
    //保存Path路径的集合，用List
    private static List savePath;
    //保存已删除Path路径的集合
    private static List deletePath;
    //记录Path路径的对象
    private DrawPath dp;
    private int screenWidth, screenHeight;
    private int currentColor = Color.RED;
    private int currentSize = 10;
    private int currentStyle = 1;
    //画笔的颜色集合
    private int[] paintColor;

    public DawingBoard(Context context, int h, int w) {
        super(context);
            this.context = context;
            screenHeight = h + 100;
            screenWidth = w + 300;
            paintColor = new int[]{
                    Color.RED, Color.WHITE, Color.BLACK, Color.BLUE, Color.GREEN,
                    Color.YELLOW, Color.GRAY, Color.CYAN, Color.MAGENTA};

            //设置默认样式，去除黑色方框以及clear模式
            setLayerType(LAYER_TYPE_SOFTWARE, null);
            initCanvas();
            savePath = new ArrayList();
            deletePath = new ArrayList();
    }

    public void saveToSDCard() {
        //以时间作为文件名
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd HHmmss");
        Date date = new Date(System.currentTimeMillis());
        String str = format.format(date) + ".png";
        File file = new File("sdcard/" + str);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
    }

    //获得SD卡的根目录
    public String getSDPath(){
        File sdDir = null;
        //判断sd卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);

        //如果SD卡存在，则获取跟目录
        if   (sdCardExist) {
            //获取跟目录
            sdDir = Environment.getExternalStorageDirectory();
        }
        return sdDir.toString();
    }


    private class DrawPath{
        public Path path;
        public Paint paint;

    }

    private void initCanvas() {
        setPaintStyle();
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);

        //画布大小
        mBitmap = Bitmap.createBitmap(screenWidth,screenHeight, Bitmap.Config.ARGB_8888);
        mBitmap.eraseColor(Color.argb(0, 0, 0, 0));

        //所画的东西保存在mBitmap中
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(Color.TRANSPARENT);
    }

    //初始化画笔样式
    private void setPaintStyle() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        if (currentStyle == 1){
            mPaint.setStrokeWidth(currentSize);
            mPaint.setColor(currentColor);
        //橡皮擦
        }else {
            mPaint.setAlpha(0);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            mPaint.setColor(Color.TRANSPARENT);
            mPaint.setStrokeWidth(80);
        }

    }

    @Override
    public void onDraw(Canvas canvas){

        //显示之前所画过的
        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        if (mPath != null){
            canvas.drawPath(mPath, mPaint);
        }
    }

    private void touch_start(float x,  float y){
            startX = x;
            startY = y;
            stopX = x;
            stopY = y;
            mPath.moveTo(startX, startY);
    }

    private void touch_move(float x, float y){
        if (mShape == SHAPE_CURVE){
            float dx = Math.abs(x - startX);
            float dy = Math.abs(y - startY);
            if (dx > 0 || dy > 0){
                mPath.quadTo(startX, startY, (x + startX) / 2, (y + startY) / 2);
                startX = x;
                startY = y;
            }
            mCanvas.drawPath(mPath, mPaint);
        }else {
            redrawOnBitmap();
            stopX = x;
            stopY = y;
            switch (mShape){
                case SHAPE_LINE:
                    stopX = x;
                    stopY = y;
                    mCanvas.drawLine(startX, startY, stopX, stopY, mPaint);
                    break;
                case SHAPE_CIRCLE:
                    stopX = x;
                    stopY = y;
                    float radius = (float) Math.sqrt(Math.pow(startX - stopX, 2) + Math.pow(startY - stopY, 2)) / 2;
                    mCanvas.drawCircle((startX + stopX) / 2, (startY + stopY) / 2, radius, mPaint);
                    break;
                case SHAPE_SQUARE:
                    stopX = x;
                    stopY = y;
                    RectF rect = new RectF(Math.min( startX, stopX),Math.min(startY, stopY),Math.max(startX, stopX),Math.max(startY, stopY));
                    mCanvas.drawRect(rect, mPaint);
                    break;
                case SHAPE_OVAL:
                    stopX = x;
                    stopY = y;
                    RectF oval = new RectF(Math.min(startX, stopX),Math.min(startY, stopY),Math.max(startX, stopX), Math.max(startY, stopY));
                    mCanvas.drawOval(oval, mPaint);
                    break;
            }
        }
    }

    private void touch_up(float x, float y){
        switch (mShape){

            case SHAPE_LINE:
                stopX = x;
                stopY = y;
                mPath.lineTo(x, y);
                mCanvas.drawLine(startX, startY, stopX, stopY, mPaint);
                break;
            case SHAPE_CIRCLE:
                stopX = x;
                stopY = y;
                float radius = (float) Math.sqrt(Math.pow(startX - stopX, 2) + Math.pow(startY - stopY, 2)) / 2;
                mPath.addCircle((startX + stopX) / 2, (startY + stopY) / 2, radius, Path.Direction.CW);
                mCanvas.drawCircle((startX + stopX) / 2, (startY + stopY) / 2, radius, mPaint);
                break;
            case SHAPE_SQUARE:
                stopX = x;
                stopY = y;
                RectF rect = new RectF(Math.min((int) startX, (int)stopX),Math.min((int)startY, (int)stopY),Math.max((int)startX, (int)stopX),Math.max((int)startY, (int)stopY));
                mPath.addRect(rect, Path.Direction.CW);
                mCanvas.drawRect(rect, mPaint);
                break;
            case SHAPE_OVAL:
                stopX = x;
                stopY = y;
                RectF oval = new RectF(Math.min(startX, stopX),Math.min(startY, stopY),Math.max(startX, stopX), Math.max(startY, stopY));
                mPath.addOval(oval, Path.Direction.CW);
                mCanvas.drawOval(oval, mPaint);
                break;
        }
        //将一条完整的路径保存下来
        savePath.add(dp);
        mPath = null;
    }
    public void setShape(int shape){
        mShape = shape;
        isPaint = false;
    }

    /**
     * 撤销
     * 就是将画布清空
     * 将保存下来的Path路径移除
     * 重新将路径画在画布上
     */
    public void undo(){
        if (savePath != null && savePath.size() > 0){
            DrawPath drawPath = (DrawPath) savePath.get(savePath.size()-1);
            deletePath.add(drawPath);
            savePath.remove(savePath.size() - 1);
            redrawOnBitmap();
        }
    }
    /**
     * 重做
     */
    public void redo(){
        if (savePath != null && savePath.size() > 0){
            savePath.clear();
            redrawOnBitmap();
        }
    }

    private void redrawOnBitmap() {
        //重新设置画布
        //mBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.RGB_565);
        //mCanvas.setBitmap(mBitmap);
        initCanvas();
        Iterator iter = savePath.iterator();
        while (iter.hasNext()){
            DrawPath drawPath = (DrawPath) iter.next();
            mCanvas.drawPath(drawPath.path, drawPath.paint);
        }
        //刷新
        invalidate();
    }

    /*
    *恢复，把删除的路径重新添加到savePath里重新绘画
    */
    public void recover(){
        if (deletePath.size() > 0){
            //将删除的路径的最顶端去除，添加到保存列表中
            DrawPath dp = (DrawPath) deletePath.get(deletePath.size() - 1);
            savePath.add(dp);
            //将取出的路径重绘到画布上
            mCanvas.drawPath(dp.path, dp.paint);
            //将该路径从删除路径中去除
            deletePath.remove(deletePath.size() - 1);
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
        //isPaint = true;
        //isClear = false;
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //每次down就new一个path
                mPath = new Path();
                //每次记录的路径对象是不一样的
                dp = new DrawPath();
                dp.paint = mPaint;
                dp.path = mPath;
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up(x, y);
                invalidate();
                break;
        }
        return true;
    }



    //修改画笔样式
    public void selectPaintStyle(int which){
        if (which == 0){
            currentStyle = 1;
            setPaintStyle();
        }

        //选择橡皮擦时，设置为白色
        if (which == 1){
            currentStyle = 2;
            setPaintStyle();
        }
    }

    //选择画笔大小
    public void selectPaintSize(int which){
        currentSize = which;
        setPaintStyle();
    }


    //设置画笔颜色
    public void selectPaintColor(int which){
        currentColor = paintColor[which];
        setPaintStyle();
    }

}