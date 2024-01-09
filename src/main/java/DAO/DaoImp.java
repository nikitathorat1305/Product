package DAO;

import DTO.ProductDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoImp implements ProductDAO{
    private static Connection conn;
    static {
        String url = "jdbc:mysql://localhost:3306/shoppingdb";
        String user = "root";
        String password = "sql123";
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.err.println("Connection Failed");
        }
    }


    @Override
    public List<ProductDTO> displayProducts() {
        String displayQuery = "SELECT * FROM product_info";
        List<ProductDTO> shoppingList = new ArrayList<>();
        try {
            Statement stmt =conn.createStatement();
            ResultSet rs = stmt.executeQuery(displayQuery);

            while (rs.next()){
                int productId =rs.getInt(1);
                String productName = rs.getString(2);
                int productQty = rs.getInt(3);
                double productPrice = rs.getDouble(4);

                ProductDTO s =new ProductDTO(productName,productQty,productPrice);
                shoppingList.add(s);
            }
        } catch (SQLException e) {
            System.err.println("Product Not Displayed !!");
        }


        return shoppingList;
    }

    @Override
    public int removeProducts(int productId) {
        PreparedStatement pstmt = null;
        String removeQuery = "delete from product_info where product_id = ?";
        int count = 0;

        try {
            pstmt = conn.prepareStatement(removeQuery);
            pstmt.setInt(1,productId);
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return count;
    }
}

