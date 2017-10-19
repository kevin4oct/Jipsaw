package com.kevinyoung.jigsaw.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;

import com.kevinyoung.jigsaw.R;
import com.kevinyoung.jigsaw.view.Jigsaw;
import com.kevinyoung.jigsaw.view.Scene;

import java.util.ArrayList;
import java.util.List;

public class JigsawActivity extends AppCompatActivity {
    public static final String TAG = JigsawActivity.class.getSimpleName();

    private Scene mScene;//场景
    private RecyclerView mRecycler;//存放拼图碎片的容器
    private LinearLayoutManager layoutManager;
    private Adapter adapter;
    private int difficulty = 2;//难度
    private List<Jigsaw> container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jigsaw);
        //
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecycler.setLayoutManager(layoutManager);
        adapter = new Adapter();
        mRecycler.setAdapter(adapter);
        //
        mScene = (Scene) findViewById(R.id.scene);
        //
        container = new ArrayList<>();
        for (int i = 0; i < difficulty * difficulty; i++) {
            Jigsaw jigsaw = new Jigsaw(JigsawActivity.this);
            jigsaw.setImageResource(R.mipmap.ic_launcher);
            container.add(jigsaw);
        }
    }

    private class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements View.OnClickListener {

        private LayoutInflater inflater;

        private int lastX, lastY;
        final static int IMAGE_SIZE = 120;

        public Adapter() {
            this.inflater = LayoutInflater.from(JigsawActivity.this);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.item_recycler, parent, false);
            itemView.setOnClickListener(this);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.js.setImageResource(R.mipmap.ic_launcher);
        }

        @Override
        public int getItemCount() {
            return container.size();
        }

        @Override
        public void onClick(View v) {
            startAnimationSet(v);
            long childItemId = mRecycler.getChildPosition(v);
            container.remove((int) childItemId);
            adapter.notifyDataSetChanged();
        }

        public void startAnimationSet(View v) {
            //创建动画，参数表示他的子动画是否共用一个插值器
            AnimationSet animationSet = new AnimationSet(true);
            //添加动画
            animationSet.addAnimation(new AlphaAnimation(1.0f, 0.0f));
            //设置插值器
            animationSet.setInterpolator(new LinearInterpolator());
            //设置动画持续时长
            animationSet.setDuration(500);
            //设置动画结束之后是否保持动画的目标状态
            animationSet.setFillAfter(true);
            //设置动画结束之后是否保持动画开始时的状态
            animationSet.setFillBefore(false);
            //设置重复模式
            animationSet.setRepeatMode(AnimationSet.REVERSE);
            //设置重复次数
            animationSet.setRepeatCount(AnimationSet.INFINITE);
            //设置动画延时时间
            animationSet.setStartOffset(0);
            //取消动画
            animationSet.cancel();
            //释放资源
            animationSet.reset();
            //开始动画
            v.startAnimation(animationSet);
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {

            Jigsaw js;

            ViewHolder(View itemView) {
                super(itemView);
                js = (Jigsaw) itemView.findViewById(R.id.jigsaw_item);
//                js.setOnTouchListener(this);
            }

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                final int X = (int) event.getRawX();
                final int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
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
//                        int left = view.getLeft() + dx;
//                        int top = view.getTop() + dy;
//                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
//                                .getLayoutParams();
//                        layoutParams.height=IMAGE_SIZE;
//                        layoutParams.width = IMAGE_SIZE;
//                        layoutParams.leftMargin =left;
//                        layoutParams.topMargin =top;
//                        view.setLayoutParams(layoutParams);
                        mScene.addView(new Jigsaw(JigsawActivity.this));
                        mScene.invalidate();
                        container.remove(mRecycler.getChildItemId(view));
                        adapter.notifyDataSetChanged();
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        break;
                }
                return true;
            }
        }
    }

}
