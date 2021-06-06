package com.example.catatan

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApps : Application(){
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("realmconfig").deleteRealmIfMigrationNeeded().schemaVersion(0).build()
        Realm.setDefaultConfiguration(config)
    }
}