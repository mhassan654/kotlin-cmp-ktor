package com.example.repositories
import com.example.data.database.Entities.UserTable
import com.example.models.User
import com.example.models.UserRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import sun.security.util.Password

class UserRepository {
    suspend fun findUserByEmail(email: String): User?=transaction{
        UserTable.select(UserTable.email).where{
            UserTable.email eq email
        }.map {
            User(
                it[UserTable.name],
                it[UserTable.email],
                it[UserTable.id]
            )
        }.singleOrNull()
    }

    suspend fun register(user: UserRequest): User=transaction{
        val id = UserTable.insert {
            it[name]=user.name
            it[email]=user.email
            it[password]=user.password
        } get UserTable.id
        User(user.name,user.email,id )
    }

    suspend fun loginIn(email: String,password: String): User? = transaction{
        UserTable.select(UserTable.email).where { UserTable.email eq email}.where{UserTable.password eq password }.map {
            User(
                it[UserTable.name],
                it[UserTable.email],
                it[UserTable.id]
            )
        }.singleOrNull()
    }
}