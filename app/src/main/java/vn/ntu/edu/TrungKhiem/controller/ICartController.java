package vn.ntu.edu.TrungKhiem.controller;

import java.util.List;

import vn.ntu.edu.TrungKhiem.model.Product;

public interface ICartController {
    List<Product> listAllProducts();
    List<Product> listShoppingCart();
    public boolean addToCart(Product p);
    public void emptyCart();
}
