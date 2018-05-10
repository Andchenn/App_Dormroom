package com.feng.dormroon;

import java.io.Serializable;

/**
 * Created by feng on 17-12-13.
 */

public class Student implements Serializable
{
	private int id;
	private String name;
	private String number;
	
	public Student(){}
	
	public Student(int id, String name, String number)
	{
		this.id = id;
		this.name = name;
		this.number = number;
	}
	
	public Student(String name, String number)
	{
		this.name = name;
		this.number = number;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getNumber()
	{
		return number;
	}
	
	public void setNumber(String number)
	{
		this.number = number;
	}
}
