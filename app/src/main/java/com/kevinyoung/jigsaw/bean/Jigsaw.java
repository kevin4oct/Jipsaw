package com.kevinyoung.jigsaw.bean;

/**
 * Created by hebth on 2017-09-30.
 * 拼图的碎片
 */

public class Jigsaw {

    private String name;//名称
    private int _id;//编号
    private int position_horizontal;//水平位置
    private int position_vertical;//竖直位置
    private int id_top;//上拼接碎片id
    private int id_start;//左拼接碎片id
    private int id_end;//右拼接碎片id
    private int id_bottom;//下拼接碎片id
    private boolean couldMove;//是否可以移动

}
