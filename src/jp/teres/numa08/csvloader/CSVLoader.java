package jp.teres.numa08.csvloader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CSVLoader {
	private static final String TAG = CSVLoader.class.getSimpleName();
	
	CSVLoader(String filename) throws Exception{
		ArrayList<StringTokenizer> tokenizerList= loadCsv(filename);
	}
	
	public ArrayList<StringTokenizer> loadCsv(String filename) throws Exception{
		ArrayList<StringTokenizer> tokenizerList = new ArrayList<StringTokenizer>();
		//FileReader reader = new FileReader(fileName);
		//BufferedReader bf = new BufferedReader(reader);
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
		String line;
		while((line = bf.readLine()) != null){
			tokenizerList.add(new StringTokenizer(line, ","));
		}
		return tokenizerList;
	}
}
