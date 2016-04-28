package cn.geminiwen.cube.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 创建日期： 2016/4/28.
 */
public class CubeTopOutAnimation extends Animation {
    private Camera mCamera;
    private Matrix mMatrix;
    private int mWidth;
    private int mHeight;
    private static final int sFinalDegree = 90;

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
        mMatrix = new Matrix();
        mWidth = width;
        mHeight = height;
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float rotate = (sFinalDegree * interpolatedTime);
        mCamera.save();
        // 在变换的时候，画面已经被移出绘制范围，这里把绘制范围移到画面位置，不过只是一点一点移，到最后依然是会在画面外
        mCamera.translate(0,-(mHeight - interpolatedTime * mHeight), 0);
        mCamera.rotateX(rotate);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        // 在完成变换以后，将画面移动回原来的高度，x 轴不移动，应该是因为要保证整个画面都能被绘制出来
        mMatrix.postTranslate(mWidth / 2, 0);
        // 变换是以0,0为中心的，为了保持中间移动，移动到坐标 -mWidth, -mHeight / 2 做变换
        mMatrix.preTranslate(- mWidth / 2, - mHeight);

        t.getMatrix().postConcat(mMatrix);
    }
}
