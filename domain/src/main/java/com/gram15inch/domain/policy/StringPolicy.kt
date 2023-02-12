package com.clone.mycoupang.domain.policy

import java.text.DecimalFormat

fun toWonPayString(price:Int): String {
    return DecimalFormat("#,###원").format(price)
}

