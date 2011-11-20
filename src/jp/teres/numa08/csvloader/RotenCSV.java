package jp.teres.numa08.csvloader;

import java.util.ArrayList;
import java.util.StringTokenizer;

import jp.teres.numa08.chofufesdata.Roten;

public class RotenCSV extends CSVLoader {
	private static final String TAG = RotenCSV.class.getSimpleName();
	private ArrayList<Roten>rotenList;
	
	public RotenCSV(String filename) throws Exception {
		super(filename);
		// TODO Auto-generated constructor stub
		ArrayList<StringTokenizer> tokenizerList = super.loadCsv(filename);
		this.rotenList = analyzeCsv(tokenizerList);
	}
	
	private ArrayList<Roten> analyzeCsv(ArrayList<StringTokenizer> tokenizerList){
		ArrayList<Roten> rotenList = new ArrayList<Roten>();
		for(StringTokenizer tokenizer : tokenizerList){
			int number = Integer.parseInt(tokenizer.nextToken());
			String title = tokenizer.nextToken();
			String description = tokenizer.nextToken();
			rotenList.add(new Roten(number, title, description));
		}
		return rotenList;
	}

	public ArrayList<Roten> getRotenList() {
		return rotenList;
	}

	public void setRotenList(ArrayList<Roten> rotenList) {
		this.rotenList = rotenList;
	}

}
