package com.example.ecommerce.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class, Review.class, OrderItem.class, Order.class}, version = 2, exportSchema = true)
public abstract class ShopDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract ReviewDao reviewDao();
    public abstract OrderItemDao orderItemDao();
    public abstract OrderDao orderDao();

    private static volatile ShopDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // prepopulating db with products
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                ProductDao dao = INSTANCE.productDao();
                // deleteAll probably should be removed later
                dao.deleteAll();

                // TODO: generate separate method for this
                Product product1 = new Product("MSI GeForce RTX 3090 GAMING X TRIO 24GB GDDR6X", 8080.00,
                        "https://www.nvidia.com/content/dam/en-zz/Solutions/geforce/ampere/rtx-3090/geforce-rtx-3090-shop-300-t.png",
                        "The latest iteration of MSI’s iconic GAMING series once again brings performance, low-noise efficiency, and aesthetics that hardcore" +
                                " gamers have come to recognize and trust. " + "Now you too can enjoy all your favorite games with a powerful graphics card " +
                                "that stays cool and silent. Just the way you like it.");
                Product product2 = new Product("Huawei Matebook X Pro", 6999.00,
                        "https://image.ceneostatic.pl/data/products/93456841/i-huawei-matebook-x-pro-2020-13-9-i7-16gb-1tb-win10-53011agg.jpg",
                        "With an industry-leading processor and 3K FullView Display, HUAWEI MateBook X Pro takes your experience beyond the extraordinary. ");

                dao.insert(product1);
                dao.insert(product2);

                OrderItemDao orderItemDao = INSTANCE.orderItemDao();
                OrderDao orderDao = INSTANCE.orderDao();
                ReviewDao reviewDao = INSTANCE.reviewDao();
//
                orderItemDao.deleteAll();
                orderDao.deleteAll();
                reviewDao.deleteAll();
            });
        }
    };

    public static ShopDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized(ShopDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShopDatabase.class, "shop_db")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
