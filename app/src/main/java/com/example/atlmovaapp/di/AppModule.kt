package com.example.atlmovaapp.di

import android.content.Context
import androidx.room.Room
import com.example.atlmovaapp.api.ApiServices
import com.example.atlmovaapp.local.card.CardDatabase
import com.example.atlmovaapp.local.download.DownloadDatabase
import com.example.atlmovaapp.local.mylist.MyListDatabase
import com.example.atlmovaapp.util.Constants.BASE_URL
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebase(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiServices {
        return retrofit.create(ApiServices::class.java)
    }

    /// MyList Room database

    @Singleton
    @Provides
    fun provideMyListDatabase(@ApplicationContext context: Context): MyListDatabase {
        return Room.databaseBuilder(context, MyListDatabase::class.java, "my_list.db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideMyListDao(myListDatabase: MyListDatabase) = myListDatabase.myListDao()


    /// Card Room database

    @Singleton
    @Provides
    fun provideCardDatabase(@ApplicationContext context: Context): CardDatabase {
        return Room.databaseBuilder(context, CardDatabase::class.java, "card_db")
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCardDao(cardDatabase: CardDatabase) = cardDatabase.cardDao()


    /// Download Room database

    @Singleton
    @Provides
    fun provideDownloadDatabase(@ApplicationContext context: Context): DownloadDatabase {
        return Room.databaseBuilder(context, DownloadDatabase::class.java, "download_db")
            .fallbackToDestructiveMigration().build()
    }


    @Singleton
    @Provides
    fun provideDownloadDao(downloadDatabase: DownloadDatabase) = downloadDatabase.downloadDao()


}