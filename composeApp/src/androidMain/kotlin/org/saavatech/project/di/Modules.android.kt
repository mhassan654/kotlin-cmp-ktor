package org.saavatech.project.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.saavatech.project.cache.dataStore

actual val platformModule: Module=module{
    single{
        dataStore(get())
    }
}