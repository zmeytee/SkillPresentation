package ru.zmeytee.skillpresentation.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.zmeytee.skillpresentation.data.contracts.DbContracts

@JsonClass(generateAdapter = true)
@Entity(tableName = DbContracts.User.TABLE_NAME)
data class User(
    @PrimaryKey
    @ColumnInfo(name = DbContracts.User.ID, index = true)
    val id: Long,

    @ColumnInfo(name = DbContracts.User.NAME)
    val name: String,

    @ColumnInfo(name = DbContracts.User.USER_NAME)
    @Json(name = "username")
    val userName: String,

    @ColumnInfo(name = DbContracts.User.EMAIL)
    val email: String? = null,

    @Embedded
    val address: Address? = null,

    @ColumnInfo(name = DbContracts.User.PHONE)
    val phone: String? = null,

    @ColumnInfo(name = DbContracts.User.WEBSITE)
    val website: String? = null,

    @Embedded
    val company: Company? = null
)





