package ru.avtoropova.tetahomework1.utils

import android.content.Context
import androidx.work.*
import ru.avtoropova.tetahomework1.model.retrofit.RetrofitMoviesRepository
import ru.avtoropova.tetahomework1.model.room.AppDB
import java.util.concurrent.TimeUnit

class SimpleWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val db = AppDB.getAppDB(context)
    private val retrofitRepo = RetrofitMoviesRepository()
    override fun doWork(): Result {
        try {
            val movies = retrofitRepo.getMovies()
            db?.movieDao()?.addMovies(movies)

        } catch (exception: Exception) {
        }

        return Result.success()
    }
}

fun startWorker(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresCharging(true)
        .setRequiresStorageNotLow(true)
        .build()
    val workRequest: PeriodicWorkRequest =
        PeriodicWorkRequestBuilder<SimpleWorker>(24, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "PeriodicMyWorker",
        ExistingPeriodicWorkPolicy.KEEP,
        workRequest
    )
}

