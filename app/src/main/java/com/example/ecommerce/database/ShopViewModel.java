package com.example.ecommerce.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ShopViewModel extends AndroidViewModel {
    private ShopRepository shopRepository;
    private LiveData<List<Product>> products;
    private LiveData<List<Review>> reviews;
    private LiveData<List<OrderItem>> orderItems;
    private LiveData<List<Order>> orders;

    public ShopViewModel(@NonNull Application application) {
        super(application);

        shopRepository = new ShopRepository(application);
        products = shopRepository.findAllProducts();
        reviews = shopRepository.findAllReviews();
        orderItems = shopRepository.findAllOrderItems();
        orders = shopRepository.findAllOrders();
    }

    // @TODO: add list filters

    public LiveData<List<Product>> findAllProducts() {
        return products;
    }

    public void insertProduct(Product product) {
        shopRepository.insertProduct(product);
    }

    public void updateProduct(Product product) {
        shopRepository.updateProduct(product);
    }

    public void deleteProduct(Product product) {
        shopRepository.deleteProduct(product);
    }

    public LiveData<List<Review>> findAllReviews() {
        return reviews;
    }

    public void insertReview(Review review) {
        shopRepository.insertReview(review);
    }

    public void updateReview(Review review) {
        shopRepository.updateReview(review);
    }

    public void deleteReview(Review review) {
        shopRepository.deleteReview(review);
    }

    public LiveData<List<OrderItem>> findAllOrderItems() {
        return orderItems;
    }

    public void insertOrderItem(OrderItem item) {
        shopRepository.insertOrderItem(item);
    }

    public void updateOrderItem(OrderItem item) {
        shopRepository.updateOrderItem(item);
    }

    public void deleteOrderItem(OrderItem item) {
        shopRepository.deleteOrderItem(item);
    }

    public LiveData<List<Order>> findAllOrders() {
        return orders;
    }

    public void insertOrder(Order order) {
        shopRepository.insertOrder(order);
    }

    public void updateOrder(Order order) {
        shopRepository.updateOrder(order);
    }

    public void deleteOrder(Order order) {
        shopRepository.deleteOrder(order);
    }
}
