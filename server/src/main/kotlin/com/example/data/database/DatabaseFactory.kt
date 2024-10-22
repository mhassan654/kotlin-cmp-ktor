package com.example.data.database

import com.example.data.database.Entities.UserTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

   fun init() {
        Database.connect(
            url =  "jdbc:postgresql://localhost:5432/travenor_db",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "password"
        )-m

        transaction{
            SchemaUtils.create(
                UserTable
            )
            TransactionManager.manager.defaultIsolationLevel=java.sql.Connection.TRANSACTION_SERIALIZABLE
        }
    }
}