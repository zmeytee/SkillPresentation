package ru.zmeytee.networkingsample.ui

import ru.zmeytee.networkingsample.data.enums.ItemAction

interface FabActionListener {

    fun setFabAction(action: ItemAction)
}