package com.blood.dbframe;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

import androidx.annotation.Nullable;

public class MeasureView extends View {

    private static final String TAG = "MeasureView";

    public MeasureView(Context context) {
        super(context);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MeasureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // wrap_content - AT_MOST
        // 200dp - EXACTLY
        Log.i(TAG, "onMeasure: " + MeasureSpec.getMode(widthMeasureSpec) + " " + MeasureSpec.getSize(widthMeasureSpec));
    }


    public static int getChildMeasureSpec(int spec, int padding, int childDimension) {
        //获取父View的测量模式
        int specMode = MeasureSpec.getMode(spec);
        //获取父View的测量大小
        int specSize = MeasureSpec.getSize(spec);
        //父View计算出的子View的大小，子View不一定用这个值
        int size = Math.max(0, specSize - padding);
        //声明变量用来保存实际计算的到的子View的size和mode即大小和模式
        int resultSize = 0;
        int resultMode = 0;
        switch (specMode) {
            //如果父容器的模式是Exactly即确定的大小
            case MeasureSpec.EXACTLY:
                //子View的高度或宽度>0说明其实一个确切的值，因为match_parent和wrap_content的值是<0的
                if (childDimension >= 0) {
                    resultSize = childDimension;
                    resultMode = MeasureSpec.EXACTLY;
                    //子View的高度或宽度为match_parent
                } else if (childDimension == LayoutParams.MATCH_PARENT) {
                    resultSize = size;//将size即父View的大小减去边距值所得到的值赋值给resultSize
                    resultMode = MeasureSpec.EXACTLY;//指定子View的测量模式为EXACTLY
                    //子View的高度或宽度为wrap_content
                } else if (childDimension == LayoutParams.WRAP_CONTENT) {
                    resultSize = size;//将size赋值给result
                    resultMode = MeasureSpec.AT_MOST;//指定子View的测量模式为AT_MOST
                }
                break;
            //如果父容器的测量模式是AT_MOST
            case MeasureSpec.AT_MOST:
                if (childDimension >= 0) {
                    resultSize = childDimension;
                    resultMode = MeasureSpec.EXACTLY;
                } else if (childDimension == LayoutParams.MATCH_PARENT) {
                    resultSize = size;
                    // 因为父View的大小是受到限制值的限制,所以子View的大小也应该受到父容器的限制并且不能超过父View
                    resultMode = MeasureSpec.AT_MOST;
                } else if (childDimension == LayoutParams.WRAP_CONTENT) {
                    resultSize = size;
                    resultMode = MeasureSpec.AT_MOST;
                }
                break;
            //如果父容器的测量模式是UNSPECIFIED即父容器的大小未受限制
            case MeasureSpec.UNSPECIFIED:
                //如果自View的宽和高是一个精确的值
                if (childDimension >= 0) {
                    //子View的大小为精确值
                    resultSize = childDimension;
                    //测量的模式为EXACTLY
                    resultMode = MeasureSpec.EXACTLY;
                    //子View的宽或高为match_parent
                } else if (childDimension == LayoutParams.MATCH_PARENT) {
                    //因为父View的大小是未定的，所以子View的大小也是未定的
                    resultSize = 0;
                    resultMode = MeasureSpec.UNSPECIFIED;
                } else if (childDimension == LayoutParams.WRAP_CONTENT) {
                    resultSize = 0;
                    resultMode = MeasureSpec.UNSPECIFIED;
                }
                break;
        }
        //根据resultSize和resultMode调用makeMeasureSpec方法得到测量要求，并将其作为返回值
        return MeasureSpec.makeMeasureSpec(resultSize, resultMode);
    }
}
