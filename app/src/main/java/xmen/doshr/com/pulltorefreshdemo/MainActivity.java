package xmen.doshr.com.pulltorefreshdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import xmen.doshr.com.pulltorefreshdemo.adapter.PullToRefreshAdapter;
import xmen.doshr.com.pulltorefreshdemo.adapter.RecyclerAdapter;

/**
 * 下拉刷新
 * <p/>
 * 1: swiperefreshLayout + recyclerview
 * <p/>
 * 2:android-PullToRefresh
 */
public class MainActivity extends Activity
{
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<String> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private int lastPostion; //最后的位置


    private PullToRefreshListView pullToRefreshListView;
    private PullToRefreshAdapter pullToRefreshAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pull_to_refresh);
        // initView();
        initViewPuToRefresh();
    }


    //初始化
    private void initViewPuToRefresh()
    {
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);
        pullToRefreshAdapter = new PullToRefreshAdapter(getDataSource(), this);

        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        ILoadingLayout startLabels = pullToRefreshListView.getLoadingLayoutProxy(true, false);
        startLabels.setPullLabel("下拉刷新...");// 刚下拉时，显示的提示
        startLabels.setRefreshingLabel("正在载入...");// 刷新时
        startLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        ILoadingLayout endLabels = pullToRefreshListView.getLoadingLayoutProxy(false, true);
        endLabels.setPullLabel("上拉刷新...");// 刚下拉时，显示的提示
        endLabels.setRefreshingLabel("正在载入...");// 刷新时
        endLabels.setReleaseLabel("放开刷新...");// 下来达到一定距离时，显示的提示

        pullToRefreshListView.setAdapter(pullToRefreshAdapter);
        pullToRefreshListView.setOnRefreshListener(new OnRefreshPullTo());
    }

    //初始化
    private void initView()
    {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        recyclerView = (RecyclerView) findViewById(R.id.recyler_view);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerAdapter = new RecyclerAdapter(getDataSource(), this);
        recyclerView.setAdapter(recyclerAdapter);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light, android.R.color.holo_green_light, android.R.color.holo_blue_bright, android.R.color.holo_orange_light);
        swipeRefreshLayout.setOnRefreshListener(new OnRefreshRecylerView());
        recyclerView.addOnScrollListener(new OnScrollRecyclerView());
    }


    //设置数据源
    private List<String> getDataSource()
    {
        list.add("鸣人");
        list.add("小樱");
        list.add("佐助");
        list.add("卡卡西");
        list.add("雏田");
        list.add("鹿丸");
        list.add("井野");
        list.add("丁次");
        return list;
    }


    //内部类
    private final class OnRefreshRecylerView implements SwipeRefreshLayout.OnRefreshListener
    {
        @Override
        public void onRefresh()
        {
            swipeRefreshLayout.setRefreshing(true);

            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerAdapter.notifyDataSetChanged();
                }
            }, 500);
        }
    }

    //内部类
    private final class OnScrollRecyclerView extends RecyclerView.OnScrollListener
    {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy)
        {
            super.onScrolled(recyclerView, dx, dy);
            lastPostion = layoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState)
        {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerAdapter.getItemCount() == lastPostion + 1)
            {
                getDataSource();
                recyclerAdapter.notifyDataSetChanged();
            }
        }
    }


    //内部类
    private final class OnRefreshPullToListView implements PullToRefreshBase.OnRefreshListener
    {
        @Override
        public void onRefresh(PullToRefreshBase refreshView)
        {
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    pullToRefreshListView.onRefreshComplete();
                    getDataSource();
                    pullToRefreshAdapter.notifyDataSetChanged();
                }
            }, 500);
        }
    }

    //内部类
    private final class OnRefreshPullTo implements PullToRefreshBase.OnRefreshListener2
    {

        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView)
        {
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    pullToRefreshListView.onRefreshComplete();
                    pullToRefreshAdapter.notifyDataSetChanged();
                }
            }, 500);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView)
        {
            new Handler().postDelayed(new Runnable()
            {
                public void run()
                {
                    pullToRefreshListView.onRefreshComplete();
                    getDataSource();
                    pullToRefreshAdapter.notifyDataSetChanged();
                }
            }, 500);
        }
    }
}
