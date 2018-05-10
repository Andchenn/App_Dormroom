	package com.feng.dormroon;
	
	import android.content.Context;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.BaseAdapter;
	import android.widget.ImageView;
	import android.widget.TextView;
	import android.widget.ViewFlipper;
	
	import java.util.List;
	
	/**
	 * Created by feng on 17-12-13.
	 */
	
	public class StudentAdapter extends BaseAdapter
	{
		private List<Student> list;
		private Context context;
		
		public StudentAdapter(Context context,List<Student> list)
		{
			super();
			this.list=list;
			this.context=context;
		}
		@Override
		public int getCount()
		{
			return list.size();
		}
		
		@Override
		public Object getItem(int position)
		{
			return list.get(position);
		}
		
		@Override
		public long getItemId(int position)
		{
			return position;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			if (convertView==null)
			{
				convertView= LayoutInflater.from(context).inflate(R.layout.item,null);
			}
			ImageView imageView=convertView.findViewById(R.id.img);
			TextView tv_name=convertView.findViewById(R.id.tv_name);
			TextView tv_number=convertView.findViewById(R.id.tv_number);
			
			//随机为学生匹配头像
			if (list.get(position).getId()%2==0)
			{
				imageView.setImageResource(R.mipmap.ic_launcher);
			}else {
				imageView.setImageResource(R.mipmap.ic_launcher);
			}
			
			tv_name.setText("姓   名：    "+list.get(position).getName());
			tv_number.setText("宿舍号：    "+list.get(position).getNumber());
			return convertView;
		}
	}
