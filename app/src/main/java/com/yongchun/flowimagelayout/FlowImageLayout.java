package com.yongchun.flowimagelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import java.util.ArrayList;
import java.util.List;

public class FlowImageLayout extends ViewGroup {
    private static final int MAX_IMAGE_NUM = 9;
    private static final int COLUMN_NUM = 3;

    private int verticalSpacing = 2;
    private int horizontalSpacing = 2;// dp

    private int rowNum = 0;
    private int imageNum = 9;

    private int parentLayoutWidth = 0;
    private int parentLayoutHeight = 0;

    private int imageWidth = 0;
    private int imageHeight = 0;

    private int oneImageWidth = 0;
    private int oneImageHeight = 0;

    private OnImageLayoutFinishListener finishListener;

    public FlowImageLayout(Context context) {
        super(context);
        initView();
    }

    public FlowImageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public FlowImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        for (int i = 0; i < MAX_IMAGE_NUM; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ScaleType.CENTER_CROP);
            addView(imageView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = 0;
        for (int i = 0; i < imageNum; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != View.GONE) {
                child.layout(left, top, imageWidth + left, imageHeight + top);
                if ((imageNum == 4 && i == 1 ) || (imageNum != 4 && (i + 1) % COLUMN_NUM == 0 )) {
                    top += imageHeight + dip2px(verticalSpacing);
                    left = 0;
                } else {
                    left += imageWidth + dip2px(horizontalSpacing);
                }
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (imageNum <= 0) {
            setMeasuredDimension(0, 0);
            return;
        }
        parentLayoutWidth = MeasureSpec.getSize(widthMeasureSpec);
        calculateImageSize();
        if (imageNum != 1) {
            int verticalSpacingCount = ((rowNum - 1) * dip2px(verticalSpacing));
            parentLayoutHeight = imageHeight * rowNum + verticalSpacingCount;
        }
        measureChildren(imageWidth, imageHeight);
        setMeasuredDimension(parentLayoutWidth, parentLayoutHeight);
    }


    /**
     * 计算图片高宽
     */
    private void calculateImageSize() {
        if (imageNum == 1) {
            parentLayoutWidth = imageWidth = oneImageWidth;
            parentLayoutHeight = imageHeight = oneImageHeight;
        } else {
            // 左右间距总和
            int horizontalSpacingCount = (COLUMN_NUM - 1) * dip2px(horizontalSpacing);
            imageHeight = imageWidth = (parentLayoutWidth - horizontalSpacingCount) / COLUMN_NUM;
        }
    }

    /**
     * 获取图片行数
     */
    private int getRowNums() {
        int rowNum = imageNum / COLUMN_NUM;
        return imageNum % COLUMN_NUM == 0 ? rowNum : rowNum + 1;
    }

    public void setLoadImage(int imageNum, OnImageLayoutFinishListener finishListener) {
        setOnImageLayoutFinishListener(finishListener);
        this.imageNum = imageNum;
        rowNum = getRowNums();
        resetImageLayout();
        requestLayout();
    }

    private void resetImageLayout() {
        List<ImageView> list = new ArrayList<ImageView>();
        int childNum = getChildCount();
        for (int i = 0; i < childNum; i++) {
            ImageView imageView = (ImageView) getChildAt(i);
            if (i < imageNum) {
                imageView.setVisibility(View.VISIBLE);
                list.add(imageView);
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
        if (finishListener != null) {
            finishListener.layoutFinish(list);
        }
    }
    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    /**
     * 设置只有一张图片时显示的大小
     *
     * @param width
     * @param height
     */
    public void setOneImageWidthHeight(int width, int height) {
        this.oneImageWidth = width;
        this.oneImageHeight = height;
    }

    /**
     * 设置上下间距
     *
     * @param verticalSpacing
     */
    public void setVerticalSpacing(int verticalSpacing) {
        this.verticalSpacing = verticalSpacing;
    }

    /**
     * 设置左右间距
     *
     * @param horizontalSpacing
     */
    public void setHorizontalSpacing(int horizontalSpacing) {
        this.horizontalSpacing = horizontalSpacing;
    }

    private void setOnImageLayoutFinishListener(OnImageLayoutFinishListener finishListener) {
        this.finishListener = finishListener;
    }

    public interface OnImageLayoutFinishListener {
        void layoutFinish(List<ImageView> images);
    }
}
