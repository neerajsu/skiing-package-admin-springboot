package com.snowbird.skiing.skiingpackageadmin.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class SkiingPackage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(unique = true)
	private String name;
	
	@Column
	private String skisType;
	
	@Column
	private String liftLevel;
	
	@Column
	private boolean hasLesson;
	
	@Column
	private int price;
	
	@Column
	@CreationTimestamp
	private LocalDateTime createDateTime;

	@Column
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	protected SkiingPackage() {
	}

	public SkiingPackage(String name, String skisType, String liftLevel, boolean hasLesson, int price) {
		super();
		this.name = name;
		this.skisType = skisType;
		this.liftLevel = liftLevel;
		this.hasLesson = hasLesson;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (hasLesson ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((liftLevel == null) ? 0 : liftLevel.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + price;
		result = prime * result + ((skisType == null) ? 0 : skisType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkiingPackage other = (SkiingPackage) obj;
		if (hasLesson != other.hasLesson)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (liftLevel == null) {
			if (other.liftLevel != null)
				return false;
		} else if (!liftLevel.equals(other.liftLevel))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		if (skisType == null) {
			if (other.skisType != null)
				return false;
		} else if (!skisType.equals(other.skisType))
			return false;
		return true;
	}
	
}
