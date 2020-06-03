package vn.edu.ntu.thanhhai.recycleview;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vn.edu.ntu.thanhhai.controller.ICartController;
import vn.edu.ntu.thanhhai.model.Product;

public class ShoppingCartActivity extends AppCompatActivity {
    TextView txtShoppingCart;
    ICartController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        controller = (ICartController) getApplication();
        addViews();
    }

    private void addViews()
    {
        txtShoppingCart = findViewById(R.id.txtCart);
        displayShoppingCart();
    }

    private void displayShoppingCart()
    {
        List<Product> shoppingCart;
        shoppingCart = controller.getShoppingCart();
        StringBuilder builder = new StringBuilder();
        for(Product p:shoppingCart)
        {
            builder.append(p.getName())
                    .append("\t\t\t")
                    .append(p.getPrice())
                    .append("vnd\n");

        }
        if (builder.toString().length()>0)
            txtShoppingCart.setText(builder.toString());
        else
            txtShoppingCart.setText("khong co mat hang nao trong gio");
    }

}
