package com.example.Radhebe.Controllers;

import com.example.Radhebe.DTO.AddToCartRequest;
import com.example.Radhebe.DTO.UpdateCartItemRequest;
import com.example.Radhebe.Entity.CartItem;
import com.example.Radhebe.Services.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems() {
        List<CartItem> cartItems = cartService.getCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartItem>> getUserCartItems(@PathVariable UUID userId) {
        List<CartItem> cartItems = cartService.getCartItemsByUser(userId);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping
    public ResponseEntity<CartItem> addToCart(@Valid @RequestBody AddToCartRequest request) {
        CartItem cartItem = cartService.addToCart(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateCartItemRequest request) {
        CartItem cartItem = cartService.updateCartItem(id, request);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFromCart(@PathVariable UUID id) {
        cartService.removeFromCart(id);
        return ResponseEntity.noContent().build();
    }
}
