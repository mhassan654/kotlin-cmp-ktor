package com.example.data.database

import com.example.data.database.Entities.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

   fun init() {
        Database.connect(
            url =  "jdbc:postgresql://127.0.0.1:5433/travenor_db", //postgresql://postgres:root@127.0.0.1:5433/travenor_db
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "root"
        )

        transaction{
            SchemaUtils.create(
                UserTable
            )
            TransactionManager.manager.defaultIsolationLevel=java.sql.Connection.TRANSACTION_SERIALIZABLE
        }
    }
}