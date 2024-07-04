package com.example.WebBanHang.controller;
import com.example.WebBanHang.model.CartItem;
import com.example.WebBanHang.model.Order;
import com.example.WebBanHang.model.Product;
import com.example.WebBanHang.service.CartService;
import com.example.WebBanHang.service.OrderService;
import com.example.WebBanHang.service.ProductService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @GetMapping("/checkout")
    public String checkout() {
        return "/cart/checkout";
    }
    @PostMapping("/submit")
    public String submitOrder(String customerName) {
        List<CartItem> cartItems = cartService.getCartItems();
        if (cartItems.isEmpty()) {
            return "redirect:/cart"; // Redirect if cart is empty
        }
        orderService.createOrder(customerName, cartItems);

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int currentStock = productService.getProductQuantity(product.getId());
            int quantityInCart = cartItem.getQuantity();
            int newStock = currentStock - quantityInCart;
            productService.updateProductQuantity(product.getId(), newStock);
        }

        cartService.clearCart();
        return "redirect:/order/confirmation";
    }
    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("message", "Your order has been successfully placed.");
        return "cart/order-confirmation";
    }
}
