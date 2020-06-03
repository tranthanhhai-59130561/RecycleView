package vn.edu.ntu.thanhhai.controller;

import java.util.List;

import vn.edu.ntu.thanhhai.model.Product;

public interface ICartController {
    List<Product> getAllProduct();

    // nhung cai nay bat thanh phan controller xu ly
    // gio hang phai co ds sp chua gio hang
    public boolean addToCart(Product p); //them vao
    public List<Product> getShoppingCart(); // tra ve gio hang
    public void clearShoppingCart(); //xoa gio hang
    //sang Cart Controller
}
