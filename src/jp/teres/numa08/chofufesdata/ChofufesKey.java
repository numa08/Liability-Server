package jp.teres.numa08.chofufesdata;

import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ChofufesKey {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private ArrayList<Key> aozoraKeyList;
	
	@Persistent
	private ArrayList<Key> rotenKeyList;
	
	@Persistent
	private ArrayList<Key> shitsunaiKeyList;
	
	@Persistent
	private int UpdateNum;
	
	public ChofufesKey(ArrayList<Key> aozoraKeyList, ArrayList<Key> rotenKeyList, ArrayList<Key> shitsunaiKeyList, int UpdateNum){
		this.aozoraKeyList = aozoraKeyList;
		this.rotenKeyList = rotenKeyList;
		this.shitsunaiKeyList = shitsunaiKeyList;
		this.UpdateNum = UpdateNum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ArrayList<Key> getAozoraKeyList() {
		return aozoraKeyList;
	}

	public void setAozoraKeyList(ArrayList<Key> aozoraKeyList) {
		this.aozoraKeyList = aozoraKeyList;
	}

	public ArrayList<Key> getRotenKeyList() {
		return rotenKeyList;
	}

	public void setRotenKeyList(ArrayList<Key> rotenKeyList) {
		this.rotenKeyList = rotenKeyList;
	}

	public ArrayList<Key> getShitsunaiKeyList() {
		return shitsunaiKeyList;
	}

	public void setShitsunaiKeyList(ArrayList<Key> shitsunaiKeyList) {
		this.shitsunaiKeyList = shitsunaiKeyList;
	}

	public int getUpdateNum() {
		return UpdateNum;
	}

	public void setUpdateNum(int updateNum) {
		UpdateNum = updateNum;
	}

}
