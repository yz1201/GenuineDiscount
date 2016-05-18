package com.example.tyz1201.mytablayout.util;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.AbsListView;


/**
 * Created by tyz1201 on 2016/5/9.
 */
public class RefreshLayout extends SwipeRefreshLayout {

    private AbsListView mListView;
    //加载更多的监听器
    private OnLoadListener mOnLoadListener;

    //最小触发滚动的距离
    private final int mTouchSlop;
    //按下时的y坐标
    private float firstTouchY;
    //抬起时的y坐标
    private float lastTouchY;

    //是否在加载中
    private boolean isLoading = false;

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    //设置子控件  AbsListView类型 可设置 gridview和listview，必须在外部调用
    public void setChildView(final AbsListView mListView) {
        this.mListView = mListView;
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            /**
             * AbsListView滚动状态改变时调用
             * SCROLL_STATE_TOUCH_SCROLL(1) 正在滚动
             * SCROLL_STATE_FLING(2) 手指做了抛的动作（手指离开屏幕前，用力滑了一下）
             * SCROLL_STATE_IDLE(0) 停止滚动
             * @param view
             * @param scrollState
             */
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_FLING:
                        break;
                    case SCROLL_STATE_IDLE:
                        //
                        if (canLoadMore()) {
                            loadData();
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                firstTouchY = event.getRawY();
                break;

            case MotionEvent.ACTION_UP:
                lastTouchY = event.getRawY();
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
    }

    private boolean canLoadMore() {
        return isBottom() && !isLoading && isPullingUp();
    }


    private boolean isBottom() {
        if (mListView.getCount() > 0) {
            if (mListView.getLastVisiblePosition() == mListView.getAdapter().getCount() - 1 &&
                    mListView.getChildAt(mListView.getChildCount() - 1).getBottom() <= mListView.getHeight()) {
                //AbsListView中最后一个可见的itemView是其最后一个itemView
                //并且最后一个位置的itemView的底部y坐标（从父控件/AbsListView的顶部往下算）小于AbsListView的高度
                return true;
            }
        }

        return false;
    }

    private boolean isPullingUp() {
        return (firstTouchY - lastTouchY) >= mTouchSlop;
    }

    private void loadData() {
        if (mOnLoadListener != null) {
            setLoading(true);
        }
    }


    public void setLoading(boolean loading) {
        //类似于setefreshing（boolean）
        if (mListView == null) return;
        isLoading = loading;
        if (loading) {
            //若正在刷新则停止刷新
            if (isRefreshing()) {
                setRefreshing(false);
            }
            mListView.setSelection(mListView.getAdapter().getCount() - 1);
            mOnLoadListener.onLoad();
        } else {
            firstTouchY = 0;
            lastTouchY = 0;
        }
    }

    //接口的常规用法，监听器的实现原理
    public void setOnLoadListener(OnLoadListener loadListener) {
        mOnLoadListener = loadListener;
    }

    public interface OnLoadListener {
        public void onLoad();
    }
}
