package com.example.productosapp.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.productosapp.BuildConfig

@Database(entities = [Product::class, User::class], version = 2, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao

    companion object {

        private const val DATABASE_NAME = BuildConfig.APPLICATION_ID
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getAppDatabase(context: Context): AppDatabase? {

            if (INSTANCE == null) {

                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext ?: context,
                        AppDatabase::class.java, DATABASE_NAME
                    )
                        .build()
                }

            }

            return INSTANCE

        }

    }

}
