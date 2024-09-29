package com.example.jollycat.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.jollycat.Cats
import com.example.jollycat.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "user.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val queryCreateUser = "CREATE TABLE IF NOT EXISTS User(" +
                "userID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "userName TEXT, " +
                "userPhone TEXT, " +
                "userPw TEXT" +
                ")"
        db?.execSQL(queryCreateUser)

        val queryCreateCat = "CREATE TABLE IF NOT EXISTS Cat(" +
                "catId TEXT PRIMARY KEY AUTOINCREMENT, " +
                "catName TEXT, " +
                "catDescription TEXT, " +
                "catImage TEXT," +
                "catPrice INTEGER " +
                ")"
        db?.execSQL(queryCreateCat)

        val queryCreateCart = "CREATE TABLE IF NOT EXISTS Cart(" +
                "cartId TEXT PRIMARY KEY AUTOINCREMENT, " +
                "catId FOREIGN KEY," +
                "checkoutId TEXT, " +
                "userID FOREIGN KEY," +
                "quantity INTEGER " +
                ")"
    }

    fun insertUser(user: User) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("userName", user.userName)
            put("userPhone", user.phoneNumber)
            put("userPw", user.password)
        }
        db.insert("User", null, values)
        db.close()
    }

    fun insertCat(cat: Cats) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("catName", cat.catName)
            put("catDescription", cat.catDescription)
            put("catImage", cat.catImage)
            put("catPrice", cat.catPrice)
        }
        db.insert("Cat", null, values)
        db.close()
    }
    fun getCats(): ArrayList<Cats> {
        val cats = ArrayList<Cats>()
        val db = readableDatabase
        val query = "SELECT * FROM Cat"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        if (cursor.count > 0) {
            do {
                val cat = Cats(
                    catID = cursor.getString(cursor.getColumnIndexOrThrow("catId")),
                    catName = cursor.getString(cursor.getColumnIndexOrThrow("catName")),
                    catDescription = cursor.getString(cursor.getColumnIndexOrThrow("catDescription")),
                    catImage = cursor.getString(cursor.getColumnIndexOrThrow("catImage")),
                    catPrice = cursor.getInt(cursor.getColumnIndexOrThrow("catPrice"))
                )
                cats.add(cat)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return cats
    }

    fun getUser(): ArrayList<User> {
        val users = ArrayList<User>()
        val db = readableDatabase
        val query = "SELECT * FROM User"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        if (cursor.count > 0) {
            do {
                val user = User()
                user.userID = cursor.getInt(cursor.getColumnIndexOrThrow("userID"))
                user.userName = cursor.getString(cursor.getColumnIndexOrThrow("userName"))
                user.phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("userPhone"))
                user.password = cursor.getString(cursor.getColumnIndexOrThrow("userPw"))
                users.add(user)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return users
    }

    fun deleteUser(userID: Int) {
        val db = writableDatabase
        db.delete("User", "userID=?", arrayOf(userID.toString()))
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS User")
        db?.execSQL("DROP TABLE IF EXISTS Cat")
        onCreate(db)
    }
}