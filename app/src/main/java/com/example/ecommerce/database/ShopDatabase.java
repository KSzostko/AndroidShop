package com.example.ecommerce.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class, Review.class, OrderItem.class, Order.class}, version = 1, exportSchema = true)
public abstract class ShopDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract ReviewDao reviewDao();
    public abstract OrderItemDao orderItemDao();
    public abstract OrderDao orderDao();

    private static volatile ShopDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static ShopDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized(ShopDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShopDatabase.class, "shop_db").build();
                }
            }
        }

        return INSTANCE;
    }
}
