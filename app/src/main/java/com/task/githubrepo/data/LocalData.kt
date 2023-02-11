package com.task.githubrepo.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.task.githubrepo.REPO_KEY
import com.task.githubrepo.SHARED_PREFERENCES
import com.task.githubrepo.data.dtos.Repository

class LocalData(private val context : Context) : ILocalSource {
    override fun getLocallySaveRepo(): Repository? {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES, 0)
        return Gson().fromJson(
            sharedPref.getString(REPO_KEY, null),
            Repository::class.java
        )
    }

    override fun saveRepoLocally(repo: Repository): Boolean {
        val sharedPref = context.getSharedPreferences(SHARED_PREFERENCES, 0)
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(REPO_KEY, Gson().toJson(repo))
        editor.apply()
        return editor.commit()
    }

}
