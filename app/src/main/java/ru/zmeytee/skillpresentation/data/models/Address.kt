package ru.zmeytee.skillpresentation.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.squareup.moshi.JsonClass
import ru.zmeytee.skillpresentation.data.contracts.DbContracts

@JsonClass(generateAdapter = true)
data class Address(
    @ColumnInfo(name = DbContracts.User.ADDRESS_STREET)
    val street: String? = null,

    @ColumnInfo(name = DbContracts.User.ADDRESS_SUITE)
    val suite: String? = null,

    @ColumnInfo(name = DbContracts.User.ADDRESS_CITY)
    val city: String? = null,

    @ColumnInfo(name = DbContracts.User.ADDRESS_ZIP_CODE)
    val zipcode: String? = null,

    @Embedded
    val geo: Geo? = null
)