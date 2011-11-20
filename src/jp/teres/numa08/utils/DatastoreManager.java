package jp.teres.numa08.utils;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import jp.teres.numa08.chofufesdata.Chofufes;
import jp.teres.numa08.chofufesdata.ChofufesKey;

import com.google.appengine.api.datastore.Key;

public class DatastoreManager {
	public static Key saveDate(Chofufes object){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(object);
		} finally {
			pm.close();
		}
		return object.getKey();
	}
	
	public static Long saveChofufesKey(ChofufesKey chofufesKey){
		PersistenceManager pm = null;
		try{
			pm = PMF.get().getPersistenceManager();
			pm.makePersistent(chofufesKey);
		} finally {
			pm.close();
		}
		return chofufesKey.getId();
	}
	
	public static <T> Object loadData(Class<T> cls,Long id){
		Object object;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
			object = pm.getObjectById(cls, id);
		} finally {
			pm.close();
		}
		return object;
	}
	
	public static ChofufesKey loadChofufesKey(Long id){
		ChofufesKey chofufesKey = null;
		PersistenceManager pm = null;
		try{
			pm = PMF.get().getPersistenceManager();
			chofufesKey = pm.getObjectById(ChofufesKey.class, id);
			chofufesKey.setAozoraKeyList(chofufesKey.getAozoraKeyList());
			chofufesKey.setRotenKeyList(chofufesKey.getRotenKeyList());
			chofufesKey.setShitsunaiKeyList(chofufesKey.getShitsunaiKeyList());
			chofufesKey.setUpdateNum(chofufesKey.getUpdateNum());
			return chofufesKey;
		} finally {
			pm.close();
		}
	}
	
	public static Long searchChofufesKey(int UPDATE_NUM){
		PersistenceManager pm = null;
		try{
			pm = PMF.get().getPersistenceManager();
			Query query = pm.newQuery(ChofufesKey.class);
			query.setFilter("UpdateNum == :UPDATE_NUM");
			List<ChofufesKey> chofufesKeyList = (List<ChofufesKey>)query.execute(UPDATE_NUM);
			return chofufesKeyList.get(0).getId();
		} finally {
			pm.close();
		}
	}
}
