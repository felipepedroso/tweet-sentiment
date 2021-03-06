package br.pedroso.tweetsentiment.app.di.storage

import br.pedroso.tweetsentiment.app.di.storage.cache.cacheModule
import br.pedroso.tweetsentiment.app.di.storage.database.databaseModule
import br.pedroso.tweetsentiment.app.di.storage.settings.settingsModule

val storageModules = listOf(
    cacheModule,
    databaseModule,
    settingsModule
)
