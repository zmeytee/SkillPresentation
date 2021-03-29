package ru.zmeytee.skillpresentation.data.models

import androidx.room.ColumnInfo
import com.squareup.moshi.JsonClass
import ru.zmeytee.skillpresentation.data.contracts.DbContracts

@JsonClass(generateAdapter = true)
data class Geo(
    @ColumnInfo(name = DbContracts.User.GEO_LATITUDE)
    val lat: Double? = null,

    @ColumnInfo(name = DbContracts.User.GEO_LONGITUDE)
    val lng: Double? = null
)