package kodlamaio.northwind.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity //sen bir veritabanı nesenesisin demek.
@Table(name="products") //veri tabanında hangi tabloya karşılık geldiğini söylüyoruz.
@AllArgsConstructor //argümanlı ctor'u oluşturdu.(Lombok)
@NoArgsConstructor //argümansız ctor'u oluşturdu.(Lombok)
public class Product {
	
	@Id  //İşlemler id ' ye göre yapılacak. Primary key.
	@GeneratedValue(strategy=GenerationType.IDENTITY)//id otomatik birer birer artıyor.
	@Column(name="product_id") //Bu alan veritabanında hangi kolona karşılık geliyor onu söylüyoruz.
	private int id;
	
//	@Column(name="category_id")
//	private int categoryId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="units_in_stock")
	private short unitsInStock;
	
	@Column(name="quantity_per_unit")
	private String quantityPerUnit;
	
	@ManyToOne()
	@JoinColumn(name="category_id") //Product'ın kategorisini tutmuş oluyoruz üstteki category_id'ye gerek kalmıyor
	private Category category; 
	
//	public Product() {}
//	
//	public Product(int id, int categoryId, String productName, double unitPrice, short unitsInStock,
//			String quantityPerUnit) {
//		super();
//		this.id = id;
//		this.categoryId = categoryId;
//		this.productName = productName;
//		this.unitPrice = unitPrice;
//		this.unitsInStock = unitsInStock;
//		this.quantityPerUnit = quantityPerUnit;
//	}
//	
	

}
