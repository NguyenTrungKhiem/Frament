package vn.ntu.edu.TrungKhiem.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.ntu.edu.TrungKhiem.model.Product;

public class CartController extends Application implements ICartController {
    List<Product> listProducts = new ArrayList<>();
    List<Product> shoppingCart = new ArrayList<>();

    public CartController() {
        listProducts.add(new Product("Bưởi Năm Roi ", 100000, "Ký sử"));
        listProducts.add(new Product("Sầu Riêng Cơm Vàng Hạt Lép", 100000, "Khoa học"));
        listProducts.add(new Product("Mận Bắc", 100000, "Kinh tế"));
        listProducts.add(new Product("Ổi sống Long An", 100000, "Xã hội"));
        listProducts.add(new Product("Xoài Sài Gòn", 100000, "Truyện ngắn"));
    }

    @Override
    public List<Product> listAllProducts() {
        return listProducts;
    }

    @Override
    public List<Product> listShoppingCart() {
        return shoppingCart;
    }

    @Override
    public boolean addToCart(Product p) {
        if(shoppingCart.contains(p)) {
            return false;
        }

        shoppingCart.add(p);
        return true;
    }

    @Override
    public void emptyCart() {
        shoppingCart = new ArrayList<>();
    }
}
