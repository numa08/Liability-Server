package jp.teres.numa08.csvloader;

import java.util.ArrayList;
import java.util.StringTokenizer;

import jp.teres.numa08.chofufesdata.Shitsunai;

public class ShitsunaiCSV extends CSVLoader {
	private static final String TAG = ShitsunaiCSV.class.getSimpleName();
	private ArrayList<Shitsunai>shitsunaiList;
	
	public ShitsunaiCSV(String filename) throws Exception{
		super(filename);
		ArrayList<StringTokenizer> tokenizerList = super.loadCsv(filename);
		this.shitsunaiList = analyzeCsv(tokenizerList);
	}
	
	private ArrayList<Shitsunai> analyzeCsv(ArrayList<StringTokenizer> tokenizerList){
		ArrayList<Shitsunai> shitsunaiList = new ArrayList<Shitsunai>();
		for(StringTokenizer tokenizer : tokenizerList){
			int number= Integer.parseInt(tokenizer.nextToken());
			String title = tokenizer.nextToken();
			shitsunaiList.add(new Shitsunai(number, title));
		}
		return shitsunaiList;
	}

	public ArrayList<Shitsunai> getShitsunaiList() {
		return shitsunaiList;
	}

	public void setShitsunaiList(ArrayList<Shitsunai> shitsunaiList) {
		this.shitsunaiList = shitsunaiList;
	}
}
