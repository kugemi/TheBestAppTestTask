package com.example.newsapplication.di

import android.app.Application
import androidx.room.Room
import com.example.newsapplication.infrastructure.ServerClient
import com.example.newsapplication.infrastructure.database.NewsDatabase
import com.example.newsapplication.infrastructure.interfaces.IServerClient
import com.example.newsapplication.repositories.RemoteNewsRepository
import com.example.newsapplication.repositories.interfaces.IArticleRepository
import com.example.newsapplication.repositories.interfaces.INewsRepository
import com.example.newsapplication.repositories.LocalArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    @Singleton
    fun providesServerClient() : IServerClient {
        return ServerClient()
    }

    @Provides
    fun provideNewsRepository(serverClient: IServerClient) : INewsRepository {
        return RemoteNewsRepository(serverClient)
    }

    @Provides
    fun provideRoom(application: Application) : NewsDatabase {
        return Room.databaseBuilder(
            application,
            NewsDatabase::class.java,
            "NewsDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideArticlesRepository(database: NewsDatabase) : IArticleRepository {
        return LocalArticlesRepository(database)
    }
}