package com.fern.data.db.dao.read

import androidx.room.Dao
import androidx.room.Query
import com.fern.data.db.entity.AccountEntity
import java.util.*

@Dao
interface AccountDao {
    @Query("SELECT * FROM accounts WHERE isDeleted = :deleted AND isArchived = 0 ORDER BY orderNum ASC")
    suspend fun findAll(deleted: Boolean = false): List<AccountEntity>

    @Query("SELECT * FROM accounts WHERE isDeleted = :deleted ORDER BY orderNum ASC")
    suspend fun findAllWithArchived(deleted: Boolean = false): List<AccountEntity>

    @Query("SELECT * FROM accounts WHERE id = :id")
    suspend fun findById(id: UUID): AccountEntity?

    @Query("SELECT MAX(orderNum) FROM accounts")
    suspend fun findMaxOrderNum(): Double?
}
