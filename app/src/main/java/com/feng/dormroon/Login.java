package com.feng.dormroon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity
{
	private Button btn_login;
	private EditText username_edit;
	private EditText pass_edit;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		username_edit=findViewById(R.id.username_edit);
		pass_edit=findViewById(R.id.pass_edit);
		btn_login=findViewById(R.id.btn_login);
		btn_login.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String acc=username_edit.getText().toString().trim();
				String pass=pass_edit.getText().toString().trim();
				if (acc.equals("admin")&&pass.equals("123"))
				{
					Intent intent=new Intent(Login.this,MainActivity.class);
					startActivity(intent);
				}else {
					Toast.makeText(Login.this,"账号，密码不能为空",Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
}
