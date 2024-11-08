package org.saavatech.project.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*

@OptIn(ExperimentalForeignApi::class)
fun dataStore():DataStore<Preferences> = createDataStore(producePath = {
    val documentDirectory: NSURL?=NSFileManager.defaultManager.URLForDirectory(
        directory=NSDocumentDirectory,
        inDomain=NSUserDomainMask,
        appropriateForURL=null,
        create=false,
        error=null
    )
    requireNotNull(documentDirectory).path.plus("/") +dataStoreFileName
})