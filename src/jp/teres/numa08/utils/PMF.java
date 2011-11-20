package jp.teres.numa08.utils;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {
	public static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	private PMF(){};
	
	public static PersistenceManagerFactory get(){
		return pmfInstance;
	}
	
}
