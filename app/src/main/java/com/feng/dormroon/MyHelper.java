package com.feng.dormroon;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feng on 17-12-13.
 */

public class MyHelper extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME="Dromroon";
	private static final String TABLE_NAME="student";
	private static final int VERSION=1;
	private static final String KEY_ID="id";
	private static final String KEY_NAME="name";
	private static final String KEY_NUMBER="number";
	
	//建表语句
	private static final String CREATE_TABLE="create table "+TABLE_NAME+"("+KEY_ID+
			" integer primary key autoincrement,"+KEY_NAME+" text not null,"+
			KEY_NUMBER+" text not null);";
	
	public MyHelper(Context context)
	{
		super(context, "DATABASE_NAME", null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	public void addStudent(Student student)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		//使用ContentValues数据添加
		ContentValues values=new ContentValues();
		values.put(KEY_NAME,student.getName());
		values.put(KEY_NUMBER,student.getNumber());
		db.insert(TABLE_NAME,null,values);
		db.close();
	}
	
	public Student getStudent(String name)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		//Cursor对象的返回查询结果
		Cursor c=db.query(TABLE_NAME,new String[]{KEY_ID,KEY_NAME,KEY_NUMBER},KEY_NAME+"=?",new String[]{name},null,null,null,null);
		Student student=null;
		//注意返回结果有可能为空
		if (c.moveToNext())
		{
			student=new Student(c.getInt(0),c.getString(1),c.getString(2));
			
		}
		return student;
	}
	
	public int getStudentCount()
	{
		String selectQuery="SELECT * FROM "+TABLE_NAME;
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor c=db.rawQuery(selectQuery,null);
		c.close();
		return c.getCount();
	}
	
	//查找所有学生
	public List<Student> getAllStudent()
	{
		List<Student> list=new ArrayList<>();
		String selectQuery="SELECT * FROM "+TABLE_NAME;
		
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor c=db.rawQuery(selectQuery,null);
		if (c.moveToFirst())
		{
			do
			{
				Student student=new Student();
				student.setId(Integer.parseInt(c.getString(0)));
				student.setName(c.getString(1));
				student.setNumber(c.getString(2));
				list.add(student);
			}while (c.moveToNext());
		}
		return list;
	}
	
	//更新
	public int updateStudent(Student student)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(KEY_NAME,student.getName());
		values.put(KEY_NUMBER,student.getNumber());
		return db.update(TABLE_NAME,values,KEY_ID+"=?",new String[]{String.valueOf(student.getId())});
		
	}
	
	public void deleteStudent(Student student)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(student.getId())});
		db.close();
	}
}
