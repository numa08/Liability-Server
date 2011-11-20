package jp.teres.numa08.chofufesdata;

import com.google.appengine.api.datastore.Key;


abstract public class Chofufes {
	abstract public Key getKey();
	
	abstract public void setKey(Key key);
}
