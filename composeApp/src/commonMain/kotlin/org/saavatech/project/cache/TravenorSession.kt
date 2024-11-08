package org.saavatech.project.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TravenorSession(val dataStore: DataStore<Preferences>) {
    val tokenKey = stringPreferencesKey("token")
    val userNameKey = stringSetPreferencesKey("userName")

    suspend fun saveToken(token: String){
        dataStore.edit{preferences->
            preferences[tokenKey]=token
        }
    }

    suspend fun getToken():String{
        return dataStore.data.map{
            preferences->preferences[tokenKey]?:""
        }.first()
    }

    // user name section
    suspend fun saveUserName(userName: Set<String>){
        dataStore.edit{preferences->
            preferences[userNameKey]=userName
        }
    }

    suspend fun getUserName():String{
        return dataStore.data.map{ preferences->preferences[userNameKey]?:""
        }.first().toString()
    }
}