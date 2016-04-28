package cn.geminiwen.cube.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 创建日期： 2016/4/28.
 */
public class CubeBottomInAnimation extends Animation {
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

        float rotate = -(sFinalDegree - sFinalDegree * interpolatedTime);
        mCamera.save();
        mCamera.translate(0, (mHeight * interpolatedTime), 0);
        mCamera.rotateX(rotate);
        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        mMatrix.postTranslate(mWidth / 2, mHeight);
        mMatrix.preTranslate(-mWidth / 2, 0);

        t.getMatrix().postConcat(mMatrix);
    }
}
