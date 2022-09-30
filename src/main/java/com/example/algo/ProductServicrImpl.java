package com.example.algo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServicrImpl implements ProductServic {

	private static final String BigDecimal = null;
	@Autowired
	private ProductRepository productRepository;

	public void addProduct(Product product) throws Exception {
		 product = getProductById(product.getProductId());
		Double discount = 0.0;
		Double gst = 0.0;
		Double deliveyCharge = 0.0;
		Double charges = 0.0;
		product.setProductCategory(product.getProductCategory());
		product.setProductName(product.getProductName());
		product.setProductType(product.getProductType());
		product.setBasePrice(product.getBasePrice());
		if (product.getProductCategory().equalsIgnoreCase("ELECTRONICS")) {
			Double gstE = 18.0;
			discount = 15.0;
			Double finalAmount = 0.0;
			gst = product.getBasePrice().doubleValue() * gstE / 100;
			deliveyCharge = 350.0;
			product.setGst(gst.longValue());
			product.setDiscount(product.getBasePrice().doubleValue() + gst);
			product.setDeliveyCharge(Double.valueOf(deliveyCharge));
			finalAmount = gst + deliveyCharge + product.getBasePrice().doubleValue();
			product.setFinalAmount(finalAmount.doubleValue());
		} else if (product.getProductCategory().equalsIgnoreCase("Home Appliances")) {
			Double gstE = 24.0;
			discount = 22.0;
			Double finalAmount = 0.0;
			gst = product.getBasePrice().doubleValue() * gstE / 100;
			deliveyCharge = 800.0;
			product.setGst(gst.longValue());
			product.setDiscount(product.getBasePrice().doubleValue() + gst);
			product.setDeliveyCharge(Double.valueOf(deliveyCharge));
			finalAmount = gst + deliveyCharge + product.getBasePrice().doubleValue();
			product.setFinalAmount(finalAmount.doubleValue());
		} else if (product.getProductCategory().equalsIgnoreCase("Clothing")) {
			Double gstE = 12.0;
			discount = 40.0;
			Double finalAmount = 0.0;
			gst = product.getBasePrice().doubleValue() * gstE / 100;
			deliveyCharge = 0.0;
			product.setGst(gst.longValue());
			product.setDiscount(product.getBasePrice().doubleValue() + gst);
			product.setDeliveyCharge(Double.valueOf(deliveyCharge));
			finalAmount = gst + deliveyCharge + product.getBasePrice().doubleValue();
			product.setFinalAmount(finalAmount.doubleValue());
		}else if (product.getProductCategory().equalsIgnoreCase("Furniture")) {
			Double gstE = 18.0;
			discount = 10.0;
			Double finalAmount = 0.0;
			gst = product.getBasePrice().doubleValue() * gstE / 100;
			deliveyCharge = 300.0;
			product.setGst(gst.longValue());
			product.setDiscount(product.getBasePrice().doubleValue() + gst);
			product.setDeliveyCharge(Double.valueOf(deliveyCharge));
			finalAmount = gst + deliveyCharge + product.getBasePrice().doubleValue();
			product.setFinalAmount(finalAmount.doubleValue());
		}

		productRepository.save(product);
	}

	public Product updateProduct(Product product, Long productId) throws Exception {
		Product checkproduct = getProductById(productId);
		checkproduct.setProductName(product.getProductName());
		checkproduct.setProductCategory(product.getProductCategory());
		checkproduct.setBasePrice(product.getBasePrice());
		checkproduct.setProductType(product.getProductType());
		return productRepository.save(checkproduct);
	}

	@Override	
	public Product getProductById(Long productId) throws Exception {
		return productRepository.findById(productId).orElseThrow(() -> new Exception("Product Not Found"));
	}

	public void deleteById(Long productId) throws Exception {
		Product product = productRepository.findById(productId).orElseThrow(() -> new Exception("Product Not Found"));
		productRepository.deleteById(productId);
	}

}