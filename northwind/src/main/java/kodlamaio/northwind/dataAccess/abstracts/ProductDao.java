package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

//interface interface'i extends eder.
//JpaRepository verdiğin veri tipi için dataAccess'de crud operasyonlarını hazırlıyor.Integer, id (primary key)'i temsil ediyor.C# daki yazdığımız entityframeworkbaserepository 'e karşılık geliyor
public interface ProductDao extends JpaRepository<Product,Integer> {
	//Bunların hepsi hazır.Bunlar için extra bir kod yazmıyoruz.İsimlendirme kurallarına uymamız yeterli.Bizim için JpaRepository tarafaından geliyor.
	
	Product getByProductName(String productName); //getBy'ı gördüğü anda tablolara bakıyor ilgili kolona göre bir ver koşulu yapıştırıyor hazır bir şekilde geliyor.
	
	Product getByProductNameAndCategory_CategoryId(String productName, int categoryId); //Bizim yerimize bir tane and operatörü olan where koşulu yazıyor.
	
	List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId); 
	
	List<Product> getByCategory_CategoryIdIn(List<Integer> categories); //select * from products where category_id in(1,2,3,4) arka planda çalışan.
	
	List<Product> getByProductNameContains(String productName);
	
	List<Product> getByProductNameStartsWith(String productName);
	
	//JPQL - Yazarken objelerdeki,entitylerdeki ilişkiye göre yazarız.
	@Query("From Product where productName=:productName and category.categoryId=:categoryId") //:parametre. select * from products where product_name=bisey and categoryId=bişey
	List<Product> getByNameAndCategory(String productName, int categoryId);
	
	//select p.productId, p.productName, c.categoryName from Category c inner join Product on c.categoryId = p.categoryId
	@Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) From Category c Inner Join c.products p ") //neye göre join ettiğimizi yazmaya gerek yok onetomany vs.ile belirtmiştik.
	List<ProductWithCategoryDto> getProductWithCategoryDetails();
}
