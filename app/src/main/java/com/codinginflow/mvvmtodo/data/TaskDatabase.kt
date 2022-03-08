package com.codinginflow.mvvmtodo.data

import androidx.room.Database
import androidx.room.Insert
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codinginflow.mvvmtodo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Layout through figma"))
                dao.insert(Task("Completing Port System Assignment"))
                dao.insert(Task("Em Assignment"))
                dao.insert(Task("Making a Resume"))
                dao.insert(Task("Complete this Project",important = true))
                dao.insert(Task("Leetcode Question",completed = true))
                dao.insert(Task("Complete DE"))
            }
        }
    }
}