package kodlamaio.northwind.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;



@RestController
@RequestMapping(value="/api/users")
public class UsersController {
	
	private UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<?> add(@Valid @RequestBody User user) { //? = Ne geleceğine ben karar vermek istemiyorum işlem sonucuna göre sen onu ekle(Result yazmıştık ilk başta).İşlem illa başarılı olacak diye bir şey yok.Hata da dönebilir. //@Valid= user'ı validasyondan geçir.
		
		return ResponseEntity.ok(this.userService.add(user)); //ok=http200 işlem oldu demek.
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class) //Sistemde bir exception oluşursa alttaki metodu çağır.Sonra oraya da hataları parametre olarak geçtim. 
	@ResponseStatus(HttpStatus.BAD_REQUEST) //Bu metod default olarak bad request(500 hatası) dönsün.
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){ //Bütün metodlarımızı buradan geçirmiş olacağız.
		Map<String,String> validationErrors = new HashMap<String, String>(); //Map=Dictionary  //Anahtar değerim hangi alan mesela email alanı.Hata string mesela email alanı formata uygun değil.Dict oluşturduk.
		for(FieldError fieldError :  exceptions.getBindingResult().getFieldErrors()) { //Alanlarda oluşan (şuan user) alanlar için tüm hataları oku liste dönüyor.
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
		return errors;
				
	}
	
	

}
