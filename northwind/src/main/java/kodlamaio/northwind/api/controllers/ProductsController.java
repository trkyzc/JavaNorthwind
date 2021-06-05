package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

//Controller isimlendirme kuralı 's' gelir sona =Products
//Dış dünyayla iletişim kurduğumuz yer.

@RestController //Sen bir controllersin.
@RequestMapping("/api/products") //Eğer dış dünyadan kodlama.io/api/products böyle bir adres üzerinden istek gelirse bu controller karşılayacak.
public class ProductsController {
	
	private ProductService productService; //Api ile business arasındaki bağlantıyı sağlıyoruz.
	
	@Autowired //Projeyi tarıyor ProductService'i implemente edeni buluyor.Autowired (Spring) arka planda ProductManager manager=new ProductManager() yapıyor aslında.Bizim newlememize gerek kalmıyor.Newleyip oraya yerleştiriyor kendisi.
	public ProductsController(ProductService productService) {
		super();
		this.productService = productService;
	}

	@GetMapping("/getall") //Veri istediğimiz için bunu yazıyoruz.kodlama.io/api/products/getall böyle bir istekte bulunursam getall metodu çalışacak.
	public DataResult<List<Product>> getAll(){ //Böyle bir metod desteğini controller vasıtasıyla veriyoruz.
		
		return this.productService.getAll(); //aslında burada productManager.getall() yapmış olduk.
		
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Product product) {
		
		return this.productService.add(product);
	}
	
	@GetMapping("/getByProductName")
	public DataResult<Product> getByProductName(@RequestParam String productName){ //RequestParam=Yapılan isteğin parametrelerine bak orada productName diye bir şey olacak onu oku atamasını yap.
		return this.productService.getByProductName(productName);
	}
	
	@GetMapping("/getByProductNameAndCategoryId")
	public DataResult<Product> getByProductNameAndCategoryId(@RequestParam("productName") String productName, @RequestParam("categoryId") int categoryId){
		return this.productService.getByProductNameAndCategoryId(productName, categoryId);
	} 
	
	@GetMapping("/getByProductNameOrCategoryId")
	public DataResult<List<Product>> getByProductNameOrCategoryId(@RequestParam String productName,@RequestParam  int categoryId){
		return this.productService.getByProductNameOrCategoryId(productName, categoryId);
	}
	
	@GetMapping("/getByCategoryIdIn")
	public DataResult<List<Product>> getByCategoryIdIn(@RequestParam List<Integer> categories){ 
		return this.productService.getByCategoryIdIn(categories);
	}
	
	@GetMapping("/getByProductNameContains")
	public DataResult<List<Product>> getByProductNameContains(@RequestParam String productName){
		return this.productService.getByProductNameContains(productName);
	}
	
	@GetMapping("/getByProductNameStartsWith")
	public DataResult<List<Product>> getByProductNameStartsWith(@RequestParam String productName){
		return this.productService.getByProductNameStartsWith(productName);
	}
	
	@GetMapping("/getByNameAndCategory")
	public DataResult<List<Product>> getByNameAndCategory(@RequestParam String productName, @RequestParam int categoryId){
		return this.productService.getByNameAndCategory(productName, categoryId);
	}
	
	@GetMapping("/getAllByPage")
	public DataResult<List<Product>> getAll(int pageNo, int pageSize){
		return this.productService.getAll(pageNo, pageSize);
	}
	
	@GetMapping("/getAllDesc")
	public DataResult<List<Product>> getAllSorted(){
		return this.productService.getAllSorted();
	}
	
	@GetMapping("/getProductWithCategoryDetails")
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails(){
		return this.productService.getProductWithCategoryDetails();
	}
}
