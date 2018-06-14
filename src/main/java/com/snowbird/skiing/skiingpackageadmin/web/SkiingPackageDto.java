package com.snowbird.skiing.skiingpackageadmin.web;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class SkiingPackageDto {
	
	@NotNull
	@Pattern(regexp="[a-zA-Z0-9- ]+")
	@NotEmpty
	private String name;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[a-zA-Z0-9- ]+")
	private String skisType;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[a-zA-Z0-9- & ]+")
	private String liftLevel;
	
	@NotNull
	private boolean hasLesson;

	@NotNull
	private int price;
	
	public SkiingPackageDto() {
		
	}

	public SkiingPackageDto(String name, String skisType, String liftLevel, boolean hasLesson, int price) {
		super();
		this.name = name;
		this.skisType = skisType;
		this.liftLevel = liftLevel;
		this.hasLesson = hasLesson;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSkisType() {
		return skisType;
	}

	public void setSkisType(String skisType) {
		this.skisType = skisType;
	}

	public String getLiftLevel() {
		return liftLevel;
	}

	public void setLiftLevel(String liftLevel) {
		this.liftLevel = liftLevel;
	}

	public boolean isHasLesson() {
		return hasLesson;
	}

	public void setHasLesson(boolean hasLesson) {
		this.hasLesson = hasLesson;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
