package com.example.Radhebe.Services;

import com.example.Radhebe.DTO.AddToCartRequest;
import com.example.Radhebe.DTO.UpdateCartItemRequest;
import com.example.Radhebe.Entity.CartItem;
import com.example.Radhebe.Entity.CartStatus;
import com.example.Radhebe.Repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProfileService profileService;

    public List<CartItem> getCartItemsByUser(UUID userId) {
        return cartItemRepository.findByUserIdOrderByAddedAtDesc(userId);
    }
    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }


    public CartItem addToCart(AddToCartRequest request) {
        // Validate that user and product exist
        profileService.findById(request.getUserId());
        productService.findById(request.getProductId());

        // Check if item already exists in cart
        Optional<CartItem> existingItem = cartItemRepository
                .findByUserIdAndProductIdAndStatus(request.getUserId(), request.getProductId(), "Pending");

        if (existingItem.isPresent()) {
            // Update quantity of existing item
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            return cartItemRepository.save(item);
        }

        // Create new cart item
        CartItem cartItem = new CartItem();
        cartItem.setUserId(request.getUserId());
        cartItem.setProductId(request.getProductId());
        cartItem.setQuantity(request.getQuantity());
        cartItem.setNeededGrossWeight(request.getNeededGrossWeight());
        cartItem.setNeededNetWeight(request.getNeededNetWeight());
        cartItem.setStatus(CartStatus.PENDING);

        return cartItemRepository.save(cartItem);
    }

    public CartItem updateCartItem(UUID id, UpdateCartItemRequest request) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + id));

        if (request.getQuantity() != null) cartItem.setQuantity(request.getQuantity());
        if (request.getNeededGrossWeight() != null) cartItem.setNeededGrossWeight(request.getNeededGrossWeight());
        if (request.getNeededNetWeight() != null) cartItem.setNeededNetWeight(request.getNeededNetWeight());
        if (request.getStatus() != null) cartItem.setStatus(CartStatus.valueOf(request.getStatus()));

        return cartItemRepository.save(cartItem);
    }

    public void removeFromCart(UUID id) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found with id: " + id));
        cartItemRepository.delete(cartItem);
    }
}
