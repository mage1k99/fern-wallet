package com.fern.data.model.sync

interface Identifiable<ID : UniqueId> {
    val id: ID
}
