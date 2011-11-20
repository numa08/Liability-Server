package jp.teres.numa08.objects;

public enum Command {
	SAVE_DATA("Save_data"),
	LOAD_DATA("Load_data"),
	GET_KEY("Get_key");
	
	private String command;
	
	private Command(String com){
		this.command = com;
	}
	
	public String toString(){
		return command;
	}

}
