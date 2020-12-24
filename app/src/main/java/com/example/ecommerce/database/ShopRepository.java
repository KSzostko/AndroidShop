package com.example.ecommerce.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ShopRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> products;

    private ReviewDao reviewDao;
    private LiveData<List<Review>> reviews;

    private OrderItemDao orderItemDao;
    private LiveData<List<OrderItem>> orderItems;

    private OrderDao orderDao;
    private LiveData<List<Order>> orders;

    public ShopRepository(Application application) {
        ShopDatabase database = ShopDatabase.getDatabase(application);

        productDao = database.productDao();
        products = productDao.findAll();

        reviewDao = database.reviewDao();
        reviews = reviewDao.findAll();

        orderItemDao = database.orderItemDao();
        orderItems = orderItemDao.findAll();

        orderDao = database.orderDao();
        orders = orderDao.findAll();
    }

    public LiveData<List<Product>> findAllProducts() {
        return products;
    }

    // @TODO: How to filter LiveData list?
//    public LiveData<List<Product>> findProductsWithName(String name) {
//    }

    public void insertProduct(Product product) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            productDao.insert(product);
        });
    }

    public void updateProduct(Product product) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            productDao.update(product);
        });
    }

    public void deleteProduct(Product product) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            productDao.delete(product);
        });
    }

    public LiveData<List<Review>> findAllReviews() {
        return reviews;
    }

    public void insertReview(Review review) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            reviewDao.insert(review);
        });
    }

    public void updateReview(Review review) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            reviewDao.update(review);
        });
    }

    public void deleteReview(Review review) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            reviewDao.delete(review);
        });
    }

    // @TODO: filter list by score and product

    public LiveData<List<OrderItem>> findAllOrderItems() {
        return orderItems;
    }

    public void insertOrderItem(OrderItem item) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            orderItemDao.insert(item);
        });
    }

    public void updateOrderItem(OrderItem item) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            orderItemDao.update(item);
        });
    }

    public void deleteOrderItem(OrderItem item) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            orderItemDao.delete(item);
        });
    }

    // @TODO: filter list by product and order

    public LiveData<List<Order>> findAllOrders() {
        return orders;
    }

    public void insertOrder(Order order) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            orderDao.insert(order);
        });
    }

    public void updateOrder(Order order) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            orderDao.update(order);
        });
    }

    public void deleteOrder(Order order) {
        ShopDatabase.databaseWriteExecutor.execute(() -> {
            orderDao.delete(order);
        });
    }

    // @TODO: filter list by status
}