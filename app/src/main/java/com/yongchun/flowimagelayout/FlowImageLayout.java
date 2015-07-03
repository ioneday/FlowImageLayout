package com.yongchun.flowimagelayout;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class FlowImageLayout extends ViewGroup {
    private static final int MAX_IMAGE_SIZE = 9;
    private int oneImageWidth = 800;
    private int oneImageHeight = 640;
    private int imageColumn = 3;
    private int imageNum = 1;
    private int verticalSpacing = 2;
    private int horizontalSpacing = 2;// dp
    private int imageWidth = 0;
    private int imageHeight = 0;
    private int groupWidth = 0;

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
        for (int i = 0; i < MAX_IMAGE_SIZE; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            imageView.setScaleType(ScaleType.CENTER_CROP);
            addView(imageView);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (imageNum <= 1) {
            getChildAt(0).layout(0, 0, imageWidth, imageHeight);
        } else {
            int iLeft = 0;
            int iTop = 0;
            int cRow = 1;
            for (int i = 0; i < imageNum; i++) {
                View child = getChildAt(i);
                if (child.getVisibility() != View.GONE) {
                    int width = imageWidth;
                    int height = imageWidth;
                    System.out.println("w-----"+width+"----h-----"+height);
                    child.layout(iLeft, iTop, iLeft + width, iTop + height);
                    // 换行（计算下一个view的left）
                    if ((imageNum == 4 && i == 1)
                            || (imageNum != 4 && (i + 1) % imageColumn == 0)) {
                        iLeft = 0;
                    } else {
                        iLeft += width + dip2px(horizontalSpacing);
                    }
                    // （计算下一个view的top）
                    int row = (imageNum == 4 && i == 1) ? 2 : getRowNums(i + 2);
                    if (cRow != row) {
                        iTop += height + dip2px(verticalSpacing);
                        cRow = row;
                    }
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
        if(groupWidth == 0){
            groupWidth = MeasureSpec.getSize(widthMeasureSpec);
        }
        getNewSize(groupWidth, oneImageWidth, oneImageHeight);
        // 一张图片显示原始尺寸
        int parentWidth = oneImageWidth;
        int parentHeight = oneImageHeight;

        // 显示的行数
        int rowNums = getRowNums(imageNum);
        // 上下间距总和
        int horizontalSpacingCount = ((rowNums - 1) * dip2px(verticalSpacing));
        // 左右间距总和
        int verticalSpacingCount = ((imageNum == 2 || imageNum == 4 ? 1
                : (imageColumn - 1)) * dip2px(horizontalSpacing));
        // 获取单张图片的宽度（不算间距，超过一张图片显示为正方形）((view宽度 - 左右间距总和) / 列数)
        imageWidth = imageNum == 1 ? oneImageWidth
                : (groupWidth - verticalSpacingCount) / imageColumn;
        System.out.println("widthMe-----"+groupWidth+"----verSpaCount----"+verticalSpacingCount+"-----imaeNum-----"+imageColumn);
        // 获取单张图片高度
        imageHeight = imageNum == 1 ? oneImageHeight : imageWidth;

        // 获取总高度
        if (rowNums > 1) {
            parentHeight = imageWidth * rowNums + horizontalSpacingCount;
        } else if (imageNum > 1) {
            parentHeight = imageWidth * rowNums;
        }
        // 获取总宽度
        if (imageNum == 2 || imageNum == 4) {
            parentWidth = imageWidth * 2 + verticalSpacingCount;
        } else if (imageNum > 1) {
            parentWidth = groupWidth;
        }
        if (parentWidth == 0) {
            parentWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        }
        if (parentHeight == 0) {
            parentHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        }

        measureChildren(imageWidth, imageHeight);
        setMeasuredDimension(parentWidth, parentHeight);
    }

    @Override
    protected void measureChildren(int imageWidth, int imageHeight) {
        int size = getChildCount();
        for (int i = 0; i < size; ++i) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                measureChild(child, imageWidth, imageHeight);
            }
        }
    }

    /**
     * 确定子View的大小
     */
    @Override
    protected void measureChild(View child, int imageWidth, int imageHeight) {
        child.measure(imageWidth, imageHeight);
    }

    /**
     * 获取图片行数
     *
     * @return
     */
    private int getRowNums(int imageNum) {
        int nums = imageNum / imageColumn;
        return imageNum % imageColumn == 0 ? nums : nums + 1;
    }

    private int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 图片太大，确定新的图片大小
     *
     * @param width
     * @param oldWidth
     * @param oldHeight
     */
    private void getNewSize(int width, int oldWidth, int oldHeight) {
        float nWidth = oldWidth;
        float nHeight = oldHeight;
        if (oldWidth >= oldHeight && oldWidth >= width) {
            nWidth = width;
            float scale = nWidth / oldWidth;
            nHeight = (int) (oldHeight * scale);
        } else if (oldHeight >= width) {
            nHeight = width;
            float scale = nHeight / oldHeight;
            nWidth = (int) (oldWidth * scale);
        }
        oneImageWidth = (int) nWidth;
        oneImageHeight = (int) nHeight;
    }

    private void setImageView() {
        int childCount = getChildCount();
        List<ImageView> list = new ArrayList<ImageView>();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) getChildAt(i);
            if (i + 1 <= imageNum) {
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

    /**
     * public
     */
    private void setImagesNum(int imageNum) {
        if (imageNum > MAX_IMAGE_SIZE) {
            this.imageNum = MAX_IMAGE_SIZE;
        } else {
            this.imageNum = imageNum;
        }
    }

    public void setLoadImage(int imageNum, OnImageLayoutFinishListener finishListener) {
        setOnImageLayoutFinishListener(finishListener);
        setImagesNum(imageNum);
        setImageView();
        requestLayout();
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
