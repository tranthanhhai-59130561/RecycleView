package vn.edu.ntu.thanhhai.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.thanhhai.model.Product;

public class CartController extends Application implements ICartController {

    List<Product> productList;
    List<Product> shoppingCart = new ArrayList<>();
    //phai de CartController vao manifests -> AndroidManifest
    public CartController() {
        productList = new ArrayList<>();
        productList.add(new Product("chuối đà lạt", 25000,"Chuối to, chín vàng"));
        productList.add(new Product("sầu riêng khánh sơn", 32000,"Cực to"));
        productList.add(new Product("Cốc không ruột", 26000,"Cốc ngọt lịm"));
        productList.add(new Product("Khoai lang", 35000,"Khoai to, đen"));
        productList.add(new Product("Nho không hạt", 28000,"Nho nha Trang"));
        productList.add(new Product("chuối Nha Trang", 12000,"Chuối to nhất việt nam"));
    }

    // + implement
    @Override
    public List<Product> getAllProduct() {
        return productList;
    }

    @Override
    public boolean addToCart(Product p) {
        if (shoppingCart.contains(p))
        return false;
        shoppingCart.add(p);
        return true;
    }

    @Override
    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void clearShoppingCart() {
        shoppingCart.clear();
    }
    //sang main activity
    //kiem Viewholder
}
