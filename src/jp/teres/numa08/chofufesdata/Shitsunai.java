package jp.teres.numa08.chofufesdata;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Shitsunai extends Chofufes {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private int number;
	
	@Persistent
	private String title;
	
	public Shitsunai(int number, String title){
		this.number = number;
		this.title = title;
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

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}
