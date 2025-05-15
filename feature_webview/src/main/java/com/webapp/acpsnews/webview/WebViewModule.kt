package com.webapp.acpsnews.webview


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WebViewModule {

    @Provides
    fun provideWebViewViewModel(): WebViewViewModel {
        return WebViewViewModel()
    }
}
