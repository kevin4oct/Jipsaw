package com.kevinyoung.jigsaw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by hebth on 2017-09-30.
 * 拼图的背景
 */

public class Scene extends RelativeLayout implements ScaleGestureDetector.OnScaleGestureListener,
        View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener  {

    private List<Jigsaw> jigsawList;//存放拼图碎片的集合
    private Paint mPaint;//画笔

    private int lastX, lastY;
    final static int IMAGE_SIZE = 100;

    /**
     * 添加拼图碎片
     *
     * @param jigsaw
     */
    public void addJigsaw(Jigsaw jigsaw) {
        jigsaw.setOnTouchListener(this);
        jigsawList.add(jigsaw);

    }

    /**
     * 减少拼图碎片
     *
     * @param jigsaw
     */
    public void reduceJigsaw(Jigsaw jigsaw) {
        jigsawList.remove(jigsaw);
    }

    public Scene(Context context) {
        super(context, null);
    }

    public Scene(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public Scene(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        jigsawList = new LinkedList<>();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }


    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return false;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                int left = view.getLeft() + dx;
                int top = view.getTop() + dy;
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.height=IMAGE_SIZE;
                layoutParams.width = IMAGE_SIZE;
                layoutParams.leftMargin =left;
                layoutParams.topMargin =top;
                view.setLayoutParams(layoutParams);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
        }
        this.invalidate();
        return true;
    }

    @Override
    public void onGlobalLayout() {

    }
}
