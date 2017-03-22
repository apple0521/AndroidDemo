package com.jxnu.cic.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.View;

public class BitmapUtils {
	/**
     * ��ͼƬ�洢��sdcard��
     */
    public static void storeImageToSDCARD(Bitmap bitmap, String imageName,
                                          String imageFilePath) {
        File file = new File(imageFilePath);
        if (!file.exists()) {
            file.mkdir();
        }
        File imagefile = new File(file, imageName + ".jpg");
        try {
            imagefile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagefile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ͼƬ�洢��sdcard��,�����뵽ϵͳͼ��
     */
public static boolean saveBitmap(Context context,Bitmap bitmap, String dir, String name, boolean isShowPhotos) {
        File path = new File(dir);
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(path + "/" + name);
        if (file.exists()) {
            file.delete();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,
                    fileOutputStream);
            fileOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // ��ΰ��ļ����뵽ϵͳͼ��
        if (isShowPhotos) {
            try {
            	//MyApplication.getIntstance()
                MediaStore.Images.Media.insertImage(context.getContentResolver(),
                        file.getAbsolutePath(), name, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // ���֪ͨͼ�����
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)));
        }

        return true;
    }

    /**
     * sdcardȡͼƬ
     */
    public static Bitmap getBitmapBySDCARD(String imageFilePath, String picName) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        try {
            File file = new File(imageFilePath, picName + ".jpg");
            FileInputStream inputStream = new FileInputStream(file);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * ����ʡ�ڴ�ķ�ʽ��ȡ������Դ��ͼƬ
     */
    public static Bitmap readBitmapById(Context context, int drawableId,
                                        int screenWidth, int screenHight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inInputShareable = true;
        options.inPurgeable = true;
        InputStream stream = context.getResources().openRawResource(drawableId);
        Bitmap bitmap = BitmapFactory.decodeStream(stream, null, options);
        return getBitmap(bitmap, screenWidth, screenHight);
    }

    /**
     * �ȱ���ѹ��ͼƬ
     */
    public static Bitmap getBitmap(Bitmap bitmap, int screenWidth,
                                   int screenHight) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        float scale = (float) screenWidth / w;
        float scale2 = (float) screenHight / h;

        //ȡ����С��ֵ ���԰�ͼƬ��ȫ��������Ļ��
        scale = scale < scale2 ? scale : scale2;

        // �����տ��scale ��֤ͼƬ������.��ݿ����ȷ���߶�
        matrix.postScale(scale, scale);
        // w,h��ԭͼ������.
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    /**
     * ��DrawableתΪBitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * ��BitmapתΪbtye[]
     */
    public static byte[] bmpToByteArray(final Bitmap bmp,
                                        final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 80, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * ����ȡͼƬ getImage ��inputStream �C> byte �C> bitmap������ѷ�����
     */
    public Bitmap getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setReadTimeout(10 * 1000);
        conn.setConnectTimeout(10 * 1000);
        conn.setRequestMethod("GET");
        InputStream in = null;
        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            in = conn.getInputStream();
        } else {
            in = null;
        }
        if (in == null){
            throw new RuntimeException("stream is null");
        } else {
            try {
                // ����getBytes(in)����
                byte[] data = getBytes(in);
                if(data!=null){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    return bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            in.close();
            return null;
        }
    }

    /**
     * ��getImage()�е���
     */
    public static byte[] getBytes(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     * ����ȡͼƬ inputStream --> drawable
     */
    private Drawable loadDrawableImage(String imageUrl) {
        Drawable drawable = null;
        try {
            // ����������ͨ���ļ������жϣ��Ƿ񱾵��д�ͼƬ
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drawable;
    }

    /**
     * ������bitmap������ϲ�����Ϊһ��ͼƬ
     */
    public Bitmap combineBitmap(Bitmap background, Bitmap foreground) {
        //��һ��ͼƬ�Ŀ��
        int bgWidth = background.getWidth();
        int bgHeight = background.getHeight();
        //�ڶ���ͼƬ�Ŀ��
        int fgWidth = foreground.getWidth();
        int fgHeight = foreground.getHeight();
        //����һ���µ�bitmao  �߶ȵ������Ÿ߶ȵ��ܺ� ��������ƴ��
        Bitmap newmap = Bitmap.createBitmap(bgWidth, bgHeight + fgHeight,
                android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newmap);
        //���ϵ�һ��ͼƬ
        canvas.drawBitmap(background, 0, 0, null);
        //�ӵ�һ��ͼƬ���±߿�ʼ����ڶ���ͼƬ
        canvas.drawBitmap(foreground, 0, bgHeight, null);
        return newmap;
    }

    /**
     * Bitmap��תһ���Ƕ�
     */
    public static Bitmap rotate(Bitmap b, int degrees) {
        if (degrees != 0 && b != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) b.getWidth() / 2,
                    (float) b.getHeight() / 2);
            try {
                Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(),
                        b.getHeight(), m, true);
                return b2;// ������·�����ת�Ƕȵ�ͼ
            } catch (OutOfMemoryError ex) {
                return b;// �ڴ��������ԭͼ
            } finally {
                b.recycle();// �ͷ���Դ
            }
        }
        return b;
    }

    /**
     * Bitmap��һ��Բ��ͼ
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
        if (bitmap == null) {
            return bitmap;
        }
        try {
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                    bitmap.getHeight());
            final RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
            paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return output;
        } catch (OutOfMemoryError e) {
            // TODO: handle exception
            System.gc();
            return null;
        }
    }

    /**
     * ��view �õ�ͼƬ
     */
    public static Bitmap getBitmapFromView(View view) {
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache(true);
        return bitmap;
    }

    /**
     * ���ˮӡ
     */
    public static Bitmap watermarkBitmap(Bitmap src, Bitmap watermark,
                                         String title) {
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        // ��Ҫ����ͼƬ̫����ɵ��ڴ泬�������,�����ҵ�ͼƬ��С���Բ�д��Ӧ������
        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);// ����һ���µĺ�SRC���ȿ��һ���λͼ
        Canvas cv = new Canvas(newb);
        cv.drawBitmap(src, 0, 0, null);// �� 0��0��꿪ʼ����src
        Paint paint = new Paint();
        // ����ͼƬ
        if (watermark != null) {
            int ww = watermark.getWidth();
            int wh = watermark.getHeight();
            paint.setAlpha(50);
            cv.drawBitmap(watermark, w - ww + 5, h - wh + 5, paint);// ��src�����½ǻ���ˮӡ
        }
        // ��������
        if (title != null) {
            String familyName = "����";
            Typeface font = Typeface.create(familyName, Typeface.BOLD);
            TextPaint textPaint = new TextPaint();
            textPaint.setColor(Color.RED);
            textPaint.setTypeface(font);
            textPaint.setTextSize(22);
            // �������Զ����е�
            StaticLayout layout = new StaticLayout(title, textPaint, w,
                    Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
            layout.draw(cv);
            // ���־ͼ����Ͻ�����
            // cv.drawText(title,0,40,paint);
        }
        cv.save(Canvas.ALL_SAVE_FLAG);// ����
        cv.restore();// �洢
        return newb;
    }
    
	//��ȡͼƬ��С��ͼƬ
    public static Bitmap scaleBitmap(String src,int max)
    {
        //��ȡͼƬ�ĸߺͿ�
        BitmapFactory.Options options = new BitmapFactory.Options();
        //��һ������ʹ BitmapFactory.decodeFile��õ�ͼƬ�ǿյ�,���ǻὫͼƬ��Ϣд��options��
        options.inJustDecodeBounds = true;        
        BitmapFactory.decodeFile(src, options); 
       // ������� Ϊ����߾���,������Ҫ640 ������Ϊ64
        max=max/10;
        int be = options.outWidth / max;
         if(be%10 !=0)
          be+=10;
         be=be/10;
         if (be <= 0)
          be = 1;
        options.inSampleSize = be;
        //���ÿ��Ի�ȡ���
        options.inJustDecodeBounds = false;
        //��ȡͼƬ
        return BitmapFactory.decodeFile(src, options);        
    }
}
