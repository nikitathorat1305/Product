package DAO;

import DTO.ProductDTO;

import java.util.List;

public interface ProductDAO {
    List<ProductDTO> displayProducts();

    int removeProducts(int productId);
}
