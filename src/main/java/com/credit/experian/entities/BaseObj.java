package com.credit.experian.entities;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.engine.jdbc.SerializableBlobProxy;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class BaseObj implements Serializable{

	@Id
	  @Column(name = "id")
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Basic
	  @Column(name = "created_at")
	  @CreationTimestamp
	  private Timestamp createdAt;

	  @Basic
	  @Column(name = "last_updated_at")
	  @UpdateTimestamp
	  private Timestamp lastUpdatedAt;

	  @Basic
	  @Column(name = "deleted", nullable = false)
	  private Boolean isDeleted = Boolean.FALSE;

	  public BaseObj(Timestamp createdAt,Timestamp lastUpdatedAt,Boolean isDeleted ){
	  	this.createdAt=new Timestamp(System.currentTimeMillis());
	  	this.lastUpdatedAt=new Timestamp(System.currentTimeMillis());
	  	this.isDeleted=false;

	  }


	public BaseObj() {
		this.createdAt=new Timestamp(System.currentTimeMillis());
		this.lastUpdatedAt=new Timestamp(System.currentTimeMillis());
		this.isDeleted=false;
	}
}