package ru.zmeytee.skillpresentation.data.models

import androidx.room.ColumnInfo
import com.squareup.moshi.JsonClass
import ru.zmeytee.skillpresentation.data.contracts.DbContracts

@JsonClass(generateAdapter = true)
data class Company(
    @ColumnInfo(name = DbContracts.User.COMPANY_NAME)
    val name: String? = null,

    @ColumnInfo(name = DbContracts.User.COMPANY_CATCH_PHRASE)
    val catchPhrase: String? = null,

    @ColumnInfo(name = DbContracts.User.COMPANY_BS)
    val bs: String? = null
)