package com.kevinyoung.jigsaw.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hebth on 2017-09-30.
 * 拼图的碎片
 */

public class Jigsaw extends android.support.v7.widget.AppCompatImageView {

    private String name;//名称
    private int _id;//编号
    private int position_horizontal;//水平位置
    private int position_vertical;//竖直位置
    private int id_top;//上拼接碎片id
    private int id_start;//左拼接碎片id
    private int id_end;//右拼接碎片id
    private int id_bottom;//下拼接碎片id
    private boolean couldMove;//是否可以移动
    private float roll_orientation;//旋转的角度

    public Jigsaw(Context context) {
        super(context, null);
    }

    public Jigsaw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public Jigsaw(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void roll90() {
        roll_orientation += 90;
        if (roll_orientation==360) {
            roll_orientation = 0;
        }
        setPivotX(getWidth() / 2);
        setPivotY(getHeight() / 2);//支点在图片中心
        setRotation(roll_orientation);
    }


}
