package jp.teres.numa08.chofufesdata;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class TimeTable extends Chofufes{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private Long startDate;
	
	@Persistent
	private Long endDate;
	
	@Persistent
	private int field;
	
	@Persistent
	private String title;
	
	@Persistent
	private String description;

	public TimeTable(Long startDate, Long endDate, int field, String title, String description){
		this.startDate = startDate;
		this.endDate = endDate;
		this.field = field;
		this.title =title;
		this.description = description;
	}

	@Override
	public Key getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}


	@Override
	public void setKey(Key key) {
		// TODO Auto-generated method stub
		this.key = key;
	}


	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public int getField() {
		return field;
	}

	public void setField(int field) {
		this.field = field;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
