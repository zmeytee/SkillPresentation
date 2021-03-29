package ru.zmeytee.skillpresentation.utils

import ru.zmeytee.skillpresentation.data.models.Address
import ru.zmeytee.skillpresentation.data.models.Company
import ru.zmeytee.skillpresentation.data.models.Geo
import ru.zmeytee.skillpresentation.data.models.User

object Test {

    private val geo = listOf(
        Geo(
            lat = 13.3,
            lng = 31.1
        ),
        Geo(
            lat = 13.3,
            lng = 31.1
        ),
        Geo(
            lat = 13.3,
            lng = 31.1
        ),
    )

    private val addresses = listOf(
        Address(
            street = "street1",
            suite = "suite1",
            city = "City1",
            zipcode = "zipcode1",
            geo = geo[0]
        ),
        Address(
            street = "street2",
            suite = "suite2",
            city = "City2",
            zipcode = "zipcode2",
            geo = geo[1]
        ),
        Address(
            street = "street3",
            suite = "suite3",
            city = "City3",
            zipcode = "zipcode3",
            geo = geo[2]
        ),
    )

    private val companies = listOf(
        Company(
            name = "Name1",
            catchPhrase = "Catch Phrase1",
            bs = "BS1"
        ),
        Company(
            name = "Name2",
            catchPhrase = "Catch Phrase2",
            bs = "BS2"
        ),
        Company(
            name = "Name3",
            catchPhrase = "Catch Phrase3",
            bs = "BS3"
        ),
    )

    val users = listOf(
        User(
            id = 0,
            name = "name1",
            userName = "userName1",
            email = "email1",
            phone = "phone1",
            website = "website1",
            address = addresses[0],
            company = companies[0]
        ),
        User(
            id = 0,
            name = "name2",
            userName = "userName2",
            email = "email2",
            phone = "phone2",
            website = "website2",
            address = addresses[1],
            company = companies[1]
        ),
        User(
            id = 0,
            name = "name3",
            userName = "userName3",
            email = "email3",
            phone = "phone3",
            website = "website3",
            address = addresses[2],
            company = companies[2]
        ),
    )
}