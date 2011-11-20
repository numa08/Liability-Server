package jp.teres.numa08.csvloader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;

import jp.teres.numa08.chofufesdata.TimeTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AozoraCSV extends CSVLoader {
	private static final Logger log = LoggerFactory.getLogger(AozoraCSV.class.getName());
	private ArrayList<TimeTable>aozoraList;
	public String test = "";
	public AozoraCSV(String filename) throws Exception {
		// TODO Auto-generated constructor stub
		super(filename);
		ArrayList<StringTokenizer> tokenizerList = super.loadCsv(filename);
		this.aozoraList = analyzeCsv(tokenizerList);
	}
	
	public ArrayList<TimeTable> getAozoraList() {
		return aozoraList;
	}

	public void setAozoraList(ArrayList<TimeTable> aozoraList) {
		this.aozoraList = aozoraList;
	}

	private ArrayList<TimeTable> analyzeCsv(ArrayList<StringTokenizer> tokenizerList){
		ArrayList<TimeTable> aozoraList = new ArrayList<TimeTable>();
		for(StringTokenizer tokenizer : tokenizerList){
			while(tokenizer.hasMoreElements()){
				int date = Integer.parseInt(tokenizer.nextToken());
				int field = Integer.parseInt(tokenizer.nextToken());
				int startHour = Integer.parseInt(tokenizer.nextToken());
				int startMinute = Integer.parseInt(tokenizer.nextToken());
				int endHour = Integer.parseInt(tokenizer.nextToken());
				int endMinute = Integer.parseInt(tokenizer.nextToken());
				String title = tokenizer.nextToken();
				String description = tokenizer.nextToken();
				TimeTable aozora = makeObject(date, field, startHour, startMinute, endHour, endMinute, title, description);
				aozoraList.add(aozora);
			}
		}
		return aozoraList;
	}
	
	private TimeTable makeObject (int date, int field, int startH, int startM, int endH, int endM, String title, String description){
		log.debug(date + "," + field + "," + startH + "," + startM + "," + endH + "," + endM + "," + title +"," + description);
		Long startDate = toDate(date, startH, startM);
		Long endDate = toDate(date, endH, endM);
		TimeTable aozora = new TimeTable(startDate, endDate, field, title, description);
		return aozora;
	}
	
	private Long toDate(int dd, int hour, int minute){
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tokyo"));
		cal.set(2010, 11, dd, hour, minute);
		test = cal.get(Calendar.DAY_OF_MONTH) + "“ú" + cal.get(Calendar.HOUR_OF_DAY) + "Žž" + cal.get(Calendar.MINUTE) + "•ª " + cal.getTimeInMillis() + "\n";
		Long date = cal.getTimeInMillis();
		return date;
	}
	
	

}
