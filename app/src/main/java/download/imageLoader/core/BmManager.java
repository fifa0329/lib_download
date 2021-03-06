package download.imageLoader.core;

import android.graphics.Bitmap;
import android.util.Log;

import download.imageLoader.util.FaceCropper;

/**
 * 为了调用更简单添加此门面
 * path示例："http://img.blog.csdn.net/20160114230048304",//gif图
 * "assets//:test.png",
 * "drawable//:"+R.drawable.common_logo,
 * "file:///mnt/sdcard/paint.png",
 * @author lizhiyun
 *
 */
public class BmManager {

	/**
	 * preload
	 * 
	 * @param path
	 */
	public static void preLoad(String path) {
		download.imageLoader.core.ImageLoader.getInstance().preLoad(path);
	}


	/**
	 * 设置是否仅wifi下下载模式
	 * @param b
	 */
	public static void setOnlyWifiMode(Boolean b){
		ImageLoader.getInstance().getConfig().setOnlyWifiMode(b);
	}

	/**
	 * 设置是否仅使用缓存模式
	 * @param b
	 */
	public static void setOnlyMemoryMode(Boolean b){
		ImageLoader.getInstance().getConfig().setOnlyMemoryMode(b);
	}

	/**
	 * clear all memory
	 */
	public static void clearAllMemory(){
		ImageLoader.getInstance().getConfig().cache.clearMemory();
		ImageLoader.getInstance().getConfig().cache.clearDiskMemory();
	}


	static FaceCropper fc = null;
	public static synchronized Bitmap face(Bitmap bitmap){
		try{
			if (fc == null){
				fc = new FaceCropper();
				fc.setDebug(false);
			}
			Log.e("test", "face");
			Bitmap faceBitmap = fc.cropFace(bitmap);
			return faceBitmap;
		}catch (Exception e){
			Log.e("test","face exception "+e.getMessage());
			return null;
		}
	}

}
