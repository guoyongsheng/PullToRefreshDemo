package xmen.doshr.com.pulltorefreshdemo.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import xmen.doshr.com.pulltorefreshdemo.R;

/**
 * Created by wesley on 2016/2/29.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{
    private List<String> list;
    private LayoutInflater inflater;

    public RecyclerAdapter(List<String> list, Context context)
    {
        this.list = list;
        if(context != null)
        {
            inflater = LayoutInflater.from(context);
        }
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(inflater == null)
        {
            return null;
        }
        View view = inflater.inflate(R.layout.recycler_view_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int position)
    {
        if(viewHolder == null)
        {
            return;
        }

        String value = list.get(position);
        viewHolder.textContent.setText(value);
    }

    @Override
    public int getItemCount()
    {
        if(list != null)
        {
            return list.size();
        }
        return 0;
    }


    //内部类
    final static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textContent;
        public ViewHolder(View itemView)
        {
            super(itemView);

            textContent = (TextView) itemView.findViewById(R.id.text_content);
        }
    }
}
