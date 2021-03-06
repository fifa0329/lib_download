package download.imageLoader.config;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;


import java.io.InputStream;

public class LoadingDrawable extends Drawable {
    private Paint mPaint;
    private int txsize;
    private Bitmap bitmap;

    final Rect r = null;
    float cx;
    float cy;
    int size;
    Rect src;
    RectF dst;

    public LoadingDrawable() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = getBitmapFormSrc("image/loading.png");
    }

    @Override
    public void draw(Canvas canvas) {
        Rect r = getBounds();
        cx = r.exactCenterX();
        cy = r.exactCenterY();
        size = (int) Math.min(cx,cy);
        src = new Rect(0,0, bitmap.getWidth(),bitmap.getHeight());
        dst = new RectF(cx - size / 2, cy - size / 2,cx + size / 2, cy + size / 2);
        canvas.drawBitmap(bitmap,src,dst,mPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
        invalidateSelf();

    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint.setColorFilter(cf);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        // not sure, so be safe
        return PixelFormat.TRANSLUCENT;
    }
    public static Bitmap getBitmapFormSrc(String src){
        Bitmap bit = null;
        try {
            InputStream is = ImageConfig.class.getResourceAsStream(src);
            bit = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
        }
        return bit;
    }
}
