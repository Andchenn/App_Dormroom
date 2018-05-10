package com.feng.dormroon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by feng on 17-12-13.
 */

public class StudentActivity extends AppCompatActivity implements View.OnClickListener
{
	private EditText name,number;
	private ImageView imageView;
	private Button btnChange,btnDelete,btnAdd;
	private int id;
	private MyHelper helper;
	private Intent intent;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dromroon);
		
		name=findViewById(R.id.name);
		number=findViewById(R.id.number);
		btnAdd=findViewById(R.id.btn_add);
		btnChange=findViewById(R.id.btn_change);
		btnDelete=findViewById(R.id.btn_delete);
		imageView=findViewById(R.id.drom_image);
		helper=new MyHelper(this);
		//获取传递过来的intent
		intent=getIntent();
		
		//通过request判断，是通过那个button点击，之后隐藏或者显示相应的button
		String request=intent.getStringExtra("request");
		switch (request)
		{
			//点击添加按钮进入的，则只显示btnAdd
			case "Add":
				btnChange.setVisibility(View.GONE);
				btnDelete.setVisibility(View.GONE);
				btnAdd.setVisibility(View.VISIBLE);
				break;
				//通过listview item进入的
			case "Look":
				id=intent.getExtras().getInt("id");
				name.setText(intent.getStringExtra("name"));
				number.setText(intent.getStringExtra("number"));
				
				
				//随机设定头像
				if (id%2==0)
				{
					imageView.setImageResource(R.mipmap.ic_launcher);
				}else {
					imageView.setImageResource(R.mipmap.ic_launcher);
				}
				break;
		}
		
		btnAdd.setOnClickListener(this);
		btnChange.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btn_add:
				Student newStudent = new Student(id, name.getText().toString(), number.getText().toString());
				helper.addStudent(newStudent);
				setResult(RESULT_OK, intent);
				finish();
				break;
			
			case R.id.btn_change:
				Student student = new Student(id, name.getText().toString(), number.getText().toString());
				helper.updateStudent(student);
				//这里设置resultCode是为了区分是修改后返回主界面的还是删除后返回主界面的。
				setResult(2, intent);
				finish();
				break;
			
			case R.id.btn_delete:
				Student s = new Student(id, name.getText().toString(), number.getText().toString());
				helper.deleteStudent(s);
				setResult(3, intent);
				finish();
				break;
		}
	}
}
