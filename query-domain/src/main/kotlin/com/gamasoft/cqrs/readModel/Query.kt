package com.gamasoft.cqrs.readModel

import com.gamasoft.cqrs.readModel.ReadEntity

sealed class Query<T: ReadEntity>

//sealed class OrderQuery: Query<ReadOrder>()
//object GetAllOpenOrders: OrderQuery()
//data class GetOrder(val phoneNum: String): OrderQuery()
// etc