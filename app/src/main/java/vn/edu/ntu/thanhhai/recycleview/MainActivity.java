package vn.edu.ntu.thanhhai.recycleview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vn.edu.ntu.thanhhai.controller.ICartController;
import vn.edu.ntu.thanhhai.model.Product;

public class MainActivity extends AppCompatActivity {

    //Adapter viet rieng ra
    //hoac Adapter viet trong giao dien do luon
    //kiem tra loi o Logcat
    //Verbose
    //Error: kiem tra loi thoi
    RecyclerView rvListMH;
    Adapter adapter;
    List<Product> ListProducts;
    ICartController controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
    }

    private void addView()
    {
        rvListMH = findViewById(R.id.rvMatHang);
        rvListMH.setLayoutManager(new LinearLayoutManager(this));
        ICartController controller = (ICartController) getApplication(); // tra ve duoi tuong (extends application)
        // ctrl + nhan chuot
        // getApplicaition()
        // loi vi 2 bien khong cung kieu
        // phai thuc hien ep kieu

        ListProducts = controller.getAllProduct();
        // khoi tao adapter
        adapter = new Adapter(ListProducts);
        //set adapter
        //truyen vao bien adapter
        rvListMH.setAdapter(adapter);
    }

    //dua menu vao


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //bat ki cai layout nao muon dua vao thi phai co Inflater
        MenuInflater inflater = getMenuInflater(); // nhung cai inflater nay phai dc tao thong qua bien context(get chu khong phai new)
        inflater.inflate(R.menu.menu_main, menu); // chuyen cai layout vao doi tuong menu
        return super.onCreateOptionsMenu(menu);
    }

    //khi nhan vao item cua menu thi no thuc hien
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // kiem tra id (get: lay ra)
        int id = item.getItemId();
        switch (id)
        {
            case R.id.mnu_close: // kiem tra co phai id cua id thoat hay k
                finish();
            case R.id.mnu_cart:
                hienThiGioHang();
                break; // break cho lich su
        }
        return super.onOptionsItemSelected(item);
    }

    private void hienThiGioHang(){
        Intent intent = new Intent( this, ShoppingCartActivity.class);
        startActivity(intent);
        //sang Icontroller
    }

    // khong viết các phương thức của main activity phía dưới này
    // Lop cai dat cho viec hien thi 1 product
    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
        //OnclickListener la bo lam nghe su kien
    {

        TextView txtName, txtPrice, txtDesc;
        ImageView imvAddToCart;
        Product product;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            // this o day la ProductViewHolder
            txtName = this.itemView.findViewById(R.id.txtNamePro);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc = this.itemView.findViewById(R.id.txtDesc);
            imvAddToCart = this.itemView.findViewById(R.id.imvAddToCart);
            //bam vao imvAddTocart thi no co bo lang nghe
            imvAddToCart.setOnClickListener(this); // this la product view holder

        }

        public void bind(Product p)
        {
            this.product = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
            //imvAddToCart.setImageResource(R.drawable.ic_action_add_to_cart);
        }

        //thiet lap su kien click
        //implement
        @Override
        public void onClick(View v) {
            if (controller.addToCart(product)) // them thanh cong thi hien thi thong bao
            {
                // dang o product view holder nen phai chuyen sang MainActivity
                Toast.makeText(MainActivity.this, "Da them: "+product.getName()+" vao gio hang",
                        Toast.LENGTH_SHORT)
                        .show();
            }
            else
            {
                // dang o product view holder nen phai chuyen sang MainActivity
                Toast.makeText(MainActivity.this, product.getName()+" da co trong gio hang",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    //Lop adapter ket noi recycleView va du lieu
    private class Adapter extends RecyclerView.Adapter<ProductViewHolder>
    {
        List<Product> productList;

        //construc
        public Adapter(List<Product> productList) {
            this.productList = productList;
        }

        // implement
        // tao 1 view holder de hien thi du lieu
        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //LayoutInflater duoc khai bao boi cac bien context
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product, parent , false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.bind(productList.get(position));
        }

        // so luong cac muc recycle view hien thi
        @Override
        public int getItemCount() {
            return productList.size();
        }
    }
}
