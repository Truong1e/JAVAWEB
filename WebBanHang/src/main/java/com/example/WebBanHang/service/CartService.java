package com.example.WebBanHang.service;
import com.example.WebBanHang.model.CartItem;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.util.ArrayList;
import java.util.List;
@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();
    @Autowired
    private ProductRepository productRepository;
    public void addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
                        cartItems.add(new CartItem(product, quantity));
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void updateCartItem(Long productId, int newQuantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(newQuantity);
                break;
            }
        }
    }

    public int getCartItemQuantity(Long productId) {
        int quantity = 0;
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                quantity += item.getQuantity();
            }
        }
        return quantity;
    }

    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }
    public void clearCart() {
        cartItems.clear();
    }
}

