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
    public abstract OrderItemProductDao orderItemProductDao();

    private static volatile ShopDatabase INSTANCE;
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // prepopulating db with products
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                populateProducts();

//                OrderItemDao orderItemDao = INSTANCE.orderItemDao();
//                OrderDao orderDao = INSTANCE.orderDao();
//                ReviewDao reviewDao = INSTANCE.reviewDao();
//
//                orderItemDao.deleteAll();
//                orderDao.deleteAll();
//                reviewDao.deleteAll();
            });
        }
    };

    public static ShopDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
            synchronized(ShopDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShopDatabase.class, "shop_db")
//                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static void populateProducts() {
        ProductDao dao = INSTANCE.productDao();

//        dao.deleteAll();

        Product product1 = new Product("MSI GeForce RTX 3090 GAMING X TRIO 24GB GDDR6X", 8080.00,
                "https://www.nvidia.com/content/dam/en-zz/Solutions/geforce/ampere/rtx-3090/geforce-rtx-3090-shop-300-t.png",
                "The latest iteration of MSI’s iconic GAMING series once again brings performance, low-noise efficiency, and aesthetics that hardcore" +
                        " gamers have come to recognize and trust. " + "Now you too can enjoy all your favorite games with a powerful graphics card " +
                        "that stays cool and silent. Just the way you like it.");

        Product product2 = new Product("Huawei Matebook X Pro", 6999.00,
                "https://image.ceneostatic.pl/data/products/93456841/i-huawei-matebook-x-pro-2020-13-9-i7-16gb-1tb-win10-53011agg.jpg",
                "With an industry-leading processor and 3K FullView Display, HUAWEI MateBook X Pro takes your experience beyond the extraordinary. ");

        Product product3 = new Product("Lenovo Legion Y540-15", 5499.00,
                "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2018/7/pr_2018_7_30_22_9_28_870_04.jpg",
                "Ten 15,6-calowy laptop zapewnia dokładnie to, czego potrzebujesz do jeszcze bardziej wciągającej i płynniejszej gry. Najpotężniejsza " +
                        "generacja mobilnych procesorów Intel Core i7 9-tej generacji to gwarancja najwyższej wydajności. Wyposażony w mocny układ graficzny "+
                        "Nvidia GTX1660Ti pozwoli Ci grać w najnowsze gry. Zdominuj pole bitwy, zamów swój egzemplarz już dziś");

        Product product4 = new Product("OnePlus 7T Pro", 2799.00,
                "https://cdn.x-kom.pl/i/setup/images/prod/big/product-new-big,,2020/8/pr_2020_8_12_7_26_38_379_00.jpg",
                "Poznaj OnePlus 7T Pro 8/256GB Haze Blue. Weź go do ręki i poczuj niedoścignioną moc potężnego procesora Qualcomm® Snapdragon™ 855+w grach " +
                        "oraz multimediach. Odkryj także niesamowite wrażenia wizualne w projekcjach QHD+ na ekranie AMOLED 6,67” oraz inteligencję " +
                        "potrójnego aparatu 48 Mpix gwarantującego ujęcia, które zapierają dech w piersiach. Masz przed sobą sprzęt, w którym nic nie " +
                        "znalazło się przypadkowo. Tutaj wszystko jest Pro.");

        Product product5 = new Product("IPhone 12 Pro", 5699.00,
                "https://www.mediaexpert.pl/media/cache/gallery/product/1/633/956/83/7ekax059/images/26/2607281/Smartfon-APPLE-iPhone-12-Pro-Max-5G" +
                        "-Pacyficzny-1.jpg",
                "Apple iPhone 12 Pro 128GB Gold 5G to smartfon premium, który spełni najwyższe wymagania. Napędzany przez najsilniejszy na rynku procesor A14" +
                        " Bionic zapewnia wielozadaniowość i pracę tak szybką, jak nigdy dotąd. Profesjonalny potrójny aparat ze skanerem LIDAR, wykona " +
                        "najwyższej jakości zdjęcia także w nocy. Wszystko podziwiać będziesz na wysokiej jakości wyświetlaczu Super Retina XDR z matrycą " +
                        "OLED.");

        dao.insert(product1);
        dao.insert(product2);
        dao.insert(product3);
        dao.insert(product4);
        dao.insert(product5);
    }
}
