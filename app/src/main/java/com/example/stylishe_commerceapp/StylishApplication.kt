package com.example.stylishe_commerceapp
import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp
import okio.Path.Companion.toOkioPath


@HiltAndroidApp
class StylishApplication : Application(),
    SingletonImageLoader.Factory{//for Caching
override fun onCreate() {
    super.onCreate()
}

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .memoryCache {
                MemoryCache.Builder()
                    .maxSizePercent(context,0.25)//take 25% of ram storage
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("image_cache").toOkioPath())
                    .maxSizeBytes(512*1024*1024)//512mb
                    .build()
            }
            .crossfade(true)//fade animation
            .logger(DebugLogger())//show the status in logcat
            .build()



    }

}