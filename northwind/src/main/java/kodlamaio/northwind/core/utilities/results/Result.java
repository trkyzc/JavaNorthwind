package kodlamaio.northwind.core.utilities.results;

public class Result { //super type = interface gibi soyut görevi görüyor.
	
	private boolean success;
	private String message;
	
	public Result(boolean success) {
		this.success=success;
	}
	
	public Result(boolean success,String message) {
		this(success); //tek parametreli ctor'ı çağırmış oluyor.alttaki satırı yazmaya gerek yok.
		//this.success = success;
		this.message=message;
	}
	
	public boolean isSuccess() { //getSuccess
		return this.success;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	
}
