package com.example.WebBanHang.controller;
import com.example.WebBanHang.service.CartService;
import com.example.WebBanHang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        return "/cart/cart";
    }
    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, RedirectAttributes redirectAttributes, Model model) {
        int availableQuantity = productService.getProductQuantity(productId);

        int cartQuantity = cartService.getCartItemQuantity(productId);

        int maxQuantityToAdd = availableQuantity - cartQuantity;

        if (quantity > maxQuantityToAdd) {
            // Nếu số lượng vượt quá số lượng tối đa, chuyển hướng với thông báo lỗi
            //redirectAttributes.addFlashAttribute("error", "Cannot add more than " + maxQuantityToAdd + " items to cart.");
            model.addAttribute("error", "Cannot add more than " + maxQuantityToAdd + " items to cart.");
            return "redirect:/products"; // Chuyển hướng đến trang sản phẩm
        }

        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }
    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @PostMapping("/update/{productId}")
    public String updateCartItem(@PathVariable Long productId, @RequestParam int quantity) {
        cartService.updateCartItem(productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
}