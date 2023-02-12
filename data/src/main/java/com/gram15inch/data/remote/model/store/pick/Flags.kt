package com.clone.mycoupang.data.remote.model.store.pick

data class Flags(
    val blueRibbon: Boolean,
    val canDelivery: Boolean,
    val canTakeOut: Boolean,
    val cheetah: Boolean,
    val eatsOriginal: Boolean,
    val newStore: Boolean,
    val sellingDrink: Boolean
)