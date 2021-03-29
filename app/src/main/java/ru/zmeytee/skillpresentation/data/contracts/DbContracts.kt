package ru.zmeytee.skillpresentation.data.contracts

object DbContracts {

    object User {
        const val TABLE_NAME = "users"
        const val ID = "id"
        const val NAME = "name"
        const val USER_NAME = "user_name"
        const val EMAIL = "email"
        const val PHONE = "phone"
        const val WEBSITE = "website"

        const val ADDRESS_STREET = "address_street"
        const val ADDRESS_SUITE = "address_suite"
        const val ADDRESS_CITY = "address_city"
        const val ADDRESS_ZIP_CODE = "address_zip_code"

        const val GEO_LATITUDE = "geo_lat"
        const val GEO_LONGITUDE = "geo_lng"

        const val COMPANY_NAME = "company_name"
        const val COMPANY_CATCH_PHRASE = "company_catch_phrase"
        const val COMPANY_BS = "company_bs"
    }
}