package jp.teres.numa08.liability2011.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.teres.numa08.chofufesdata.ChofufesData;
import jp.teres.numa08.chofufesdata.ChofufesKey;
import jp.teres.numa08.chofufesdata.Roten;
import jp.teres.numa08.chofufesdata.Shitsunai;
import jp.teres.numa08.chofufesdata.TimeTable;
import jp.teres.numa08.csvloader.AozoraCSV;
import jp.teres.numa08.csvloader.RotenCSV;
import jp.teres.numa08.csvloader.ShitsunaiCSV;
import jp.teres.numa08.objects.Command;
import jp.teres.numa08.objects.File;
import jp.teres.numa08.utils.DatastoreManager;
import net.arnx.jsonic.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.datastore.Key;

@SuppressWarnings("serial")
public class Liability2011ServerServlet extends HttpServlet {
	private static final Logger log = LoggerFactory.getLogger(Liability2011ServerServlet.class);
	//�X�V���s������UPDATE_NUM��ύX���邱��
	//yyyymmddnn
	private final int UPDATE_NUM = 2011111701;
	private PrintWriter writer;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.setContentType("text/plain; charset=utf-8");
			writer = resp.getWriter();

			// ����R�}���h���擾����,�������������.
			String command = getCommand(req);
			if (command.equals(Command.SAVE_DATA.toString())) {
				// �f�[�^�Z�[�u����
				Long id = onSaveDate();
				writer.print(id + "");
				//writer.print("���̉ł͗��q����ŊԈႢ�Ȃ��̂����A��������̖��������B��v���Ȃ͉��̒��ł͔F�߂��Ȃ��E�E�E�B����́A�ǂ��������̂��B�Ǝv������A���q����ƈꏏ�ɍ���̎��Ƃ��󂯂閲���݂�����A���̖����Ȃ��Ƃ���B");
			} else if (command.equals(Command.LOAD_DATA.toString())) {
				// �f�[�^���[�h����
				Long id = Long.parseLong(req.getParameter("id"));
				if (id != null) {
					String jsonData = onLoadData(id);
					resp.setContentType("application/json; charset=utf-8");
					writer.print(jsonData);
				} else {
					writer.print("prease input key");
				}

			} else if (command.equals(Command.GET_KEY.toString())) {
				//�L�[��������
				Long id = DatastoreManager.searchChofufesKey(UPDATE_NUM);
				LastId lastId = new LastId(id);
				resp.setContentType("application/json; charset=utf-8");
				writer.print(JSON.encode(lastId));
			} else {
				writer.print("command is not");
			}
		} catch (Exception e) {
			//e.printStackTrace(writer);
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			log.error(sw.toString());
			writer.print("oh... error");
		}
	}

	private Long onSaveDate() throws Exception {
		// �t�@�C���ǂݍ���
		AozoraCSV aozoraCsv = new AozoraCSV("WEB-INF/files/" + File.aozora
				+ ".csv");
		writer.println(aozoraCsv.test);
		RotenCSV rotenCsv = new RotenCSV("WEB-INF/files/" + File.roten + ".csv");
		ShitsunaiCSV shitsunaiCsv = new ShitsunaiCSV("WEB-INF/files/"
				+ File.shitsunai + ".csv");
		// csv���
		ArrayList<TimeTable> aozoraList = aozoraCsv.getAozoraList();
		ArrayList<Roten> rotenList = rotenCsv.getRotenList();
		ArrayList<Shitsunai> shitsunaiList = shitsunaiCsv.getShitsunaiList();
		// �f�[�^�X�g�A�ɕۑ�
		return saveDate(aozoraList, rotenList, shitsunaiList);
	}

	private String onLoadData(Long id) throws Exception {
		ChofufesKey chofufesKey = DatastoreManager.loadChofufesKey(id);
		ArrayList<TimeTable> aozoraList = new ArrayList<TimeTable>();
		ArrayList<Roten> rotenList = new ArrayList<Roten>();
		ArrayList<Shitsunai> shitsunaiList = new ArrayList<Shitsunai>();
		for (Key key : chofufesKey.getAozoraKeyList()) {
			aozoraList.add((TimeTable) DatastoreManager.loadData(
					TimeTable.class, key.getId()));
		}
		for (Key key : chofufesKey.getRotenKeyList()) {
			rotenList.add((Roten) DatastoreManager.loadData(Roten.class,
					key.getId()));
		}
		for (Key key : chofufesKey.getShitsunaiKeyList()) {
			shitsunaiList.add((Shitsunai) DatastoreManager.loadData(
					Shitsunai.class, key.getId()));
		}
		ChofufesData chofufesData = new ChofufesData(aozoraList, rotenList,
				shitsunaiList, this.UPDATE_NUM);
		return JSON.encode(chofufesData);
	}

	private String getCommand(HttpServletRequest req) {
		String command = req.getParameter("command");
		if (!command.equals(null)) {
			return command;
		} else {
			return "Please input true command!!";
		}
	}

	private Long saveDate(ArrayList<TimeTable> aozoraList,
			ArrayList<Roten> rotenList, ArrayList<Shitsunai> shitsunaiList) {
		ArrayList<Key> aozoraKeyList = new ArrayList<Key>();
		ArrayList<Key> rotenKeyList = new ArrayList<Key>();
		ArrayList<Key> shitsunaiKeyList = new ArrayList<Key>();
		for (TimeTable aozora : aozoraList) {
			aozoraKeyList.add(DatastoreManager.saveDate(aozora));
		}
		for (Roten roten : rotenList) {
			rotenKeyList.add(DatastoreManager.saveDate(roten));
		}
		for (Shitsunai shitsunai : shitsunaiList) {
			shitsunaiKeyList.add(DatastoreManager.saveDate(shitsunai));
		}
		ChofufesKey chofufesKey = new ChofufesKey(aozoraKeyList, rotenKeyList,
				shitsunaiKeyList, this.UPDATE_NUM);
		testViewer(chofufesKey.getAozoraKeyList());
		testViewer(chofufesKey.getRotenKeyList());
		testViewer(chofufesKey.getShitsunaiKeyList());

		return DatastoreManager.saveChofufesKey(chofufesKey);
	}

	private void testViewer(ArrayList<Key> keyList) {
		for (Key key : keyList) {
			writer.print(key.getId() + ",");
		}
		writer.print("\n");
	}
	
	public class LastId{
		private Long id;
		public LastId(Long id){
			this.id = id;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		
	}

}
