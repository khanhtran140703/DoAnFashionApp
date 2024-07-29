package com.example.doanfashionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doanfashionapp.DAO.DAO_Images;
import com.example.doanfashionapp.DAO.DAO_Order_Details;
import com.example.doanfashionapp.DAO.DAO_Orders;
import com.example.doanfashionapp.DTO.Order;
import com.example.doanfashionapp.DTO.Order_Detail;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ActivityXuLiThanhToan extends AppCompatActivity {
    private TextView txtTenSP, txtGiaSP, txtTongTien, txtSoLuong;
    private ImageView anhSP;
    private EditText edtDiaChi;
    private Button btnXacNhan, btnHuy, btnTru, btnCong;

    private int giaSanPham;
    private int soLuong = 1;
    private int tongTien;
    private DAO_Images dao_images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xu_li_thanh_toan);

        // Initialize views
        txtTenSP = findViewById(R.id.txtTenSP);
        txtGiaSP = findViewById(R.id.txtGiaSP);
        txtTongTien = findViewById(R.id.txtTongTien);
        txtSoLuong = findViewById(R.id.txtSoLuong);
        anhSP = findViewById(R.id.anhSP);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        btnHuy = findViewById(R.id.btnHuy);
        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        dao_images = new DAO_Images(this);

        Intent intent = getIntent();
        String productVariationId = intent.getStringExtra("productVariationId");
        String maSP = intent.getStringExtra("maSP");
        String tenSP = intent.getStringExtra("tenSP");
        giaSanPham = intent.getIntExtra("giaSP", 0);
        int imageResource = intent.getIntExtra("anhSP", 0);
        int selectedImageIndex = intent.getIntExtra("selectedImageIndex", 0);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String diaChi = sharedPreferences.getString("diachi", "");
        txtTenSP.setText(tenSP);
        int giaSP = intent.getIntExtra("giaSP", 0);
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        String formattedGiaSP = numberFormat.format(giaSP) + " đ";
        txtGiaSP.setText(formattedGiaSP);
//        anhSP.setImageResource(imageResource);
        edtDiaChi.setText(diaChi);

        // Assuming you have the images array stored as a static field in a utility class or similar
        ArrayList<Integer> images = dao_images.getImagesByProductId(maSP);
        if (images != null && !images.isEmpty() && selectedImageIndex < images.size()) {
            anhSP.setImageResource(images.get(selectedImageIndex));
        }
//        int tongtien = intent.getIntExtra("TongTien", 0);
//
//        String formattedTongTien = numberFormat.format(giaSP) + " đ";
//        txtTongTien.setText(formattedTongTien);
        updateTotalPrice();

        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soLuong++;
                txtSoLuong.setText(String.valueOf(soLuong));
                updateTotalPrice();
            }
        });

        btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (soLuong > 1) {
                    soLuong--;
                    txtSoLuong.setText(String.valueOf(soLuong));
                    updateTotalPrice();
                }
            }
        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dkMua(username, productVariationId);
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void dkMua(String username, String productVariationId) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String diaChi = sharedPreferences.getString("diaChi","");
        if (diaChi.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập địa chỉ giao hàng", Toast.LENGTH_SHORT).show();
            return;
        }

        tongTien = giaSanPham * soLuong;

        // Save order and order details to database
        saveOrderToDatabase(username, diaChi, productVariationId, tongTien, soLuong);

        Toast.makeText(this, "Mua hàng thành công!", Toast.LENGTH_SHORT).show();
        finish();  // Close the payment activity
    }

    private void updateTotalPrice() {
        tongTien = giaSanPham * soLuong;
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        String formattedTongTien = numberFormat.format(tongTien) + " đ";
        txtTongTien.setText(String.format("Tổng tiền: %s", formattedTongTien));

    }

    private void saveOrderToDatabase(String username, String diaChi, String productVariationId, int totalPrice, int quantity) {
        // Lấy ngày hiện tại
        String orderDate = getCurrentDate();

        // Lưu vào bảng ORDERS
        DAO_Orders daoOrders = new DAO_Orders(this);
        String orderId = String.valueOf(System.currentTimeMillis()); // Tạo ID đơn hàng duy nhất
        Order order = new Order(orderId, username, orderDate, totalPrice);
        daoOrders.insertOrder(order);

        // Lưu vào bảng ORDER_DETAILS
        DAO_Order_Details daoOrderDetails= new DAO_Order_Details(this);
//        String orderDetailId = String.valueOf(System.currentTimeMillis()); // Tạo ID chi tiết đơn hàng duy nhất
        Order_Detail orderDetail = new Order_Detail(orderId, productVariationId, quantity, totalPrice);
        daoOrderDetails.insertOrderDetail(orderDetail);
    }


    private String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
    }
}
