package xmen.doshr.com.pulltorefreshdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import xmen.doshr.com.pulltorefreshdemo.R;

/**
 * Created by wesley on 2016/2/29.
 */
public class PullToRefreshAdapter extends BaseAdapter
{

    private List<String> list;
    private LayoutInflater inflater;

    public PullToRefreshAdapter(List<String> list, Context context)
    {
        this.list = list;
        if(context != null)
        {
            inflater = LayoutInflater.from(context);
        }
    }

    @Override
    public int getCount()
    {
        if(list != null)
        {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position)
    {
        if(list != null && list.size() > position)
        {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if(list == null || inflater == null)
        {
            return null;
        }

        ViewHolder viewHolder;
        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.recycler_view_item, null);
            viewHolder.textContent = (TextView) convertView.findViewById(R.id.text_content);
            convertView.setTag(viewHolder);
        }else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String value = list.get(position);
        viewHolder.textContent.setText(value);
        return convertView;
    }


    //内部类
    private final static class ViewHolder
    {
        private TextView textContent;
    }
}
