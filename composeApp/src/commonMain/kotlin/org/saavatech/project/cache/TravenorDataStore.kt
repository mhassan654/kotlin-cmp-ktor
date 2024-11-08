package org.saavatech.project.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

lateinit var dataStore: DataStore<Preferences>

fun createDataStore(producePath:()-> String): DataStore<Preferences>{
        if (::dataStore.isInitialized){
        return dataStore
    }else{
        dataStore = PreferenceDataStoreFactory.createWithPath(produceFile = { producePath().toPath() })
        return dataStore
    }
}

val dataStoreFileName ="travenor.preferences_pb"