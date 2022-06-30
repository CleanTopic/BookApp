package com.whynot.domain.repositories.books

import com.whynot.domain.Entities.Volume
import com.whynot.domain.api.BooksApi
import com.whynot.domain.api.BooksApiResponse
import com.whynot.domain.mappers.BookApiResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.reflect.jvm.internal.impl.resolve.ResolutionAnchorProvider
import com.whynot.domain.common.Result

class BooksRemoteDataSourceImpl(
    private val service: BooksApi,
    private val mapper: BookApiResponseMapper
) : BooksRemoteDataSource {
    override suspend fun getBooks(author: String): Result<List<Volume>> = withContext(Dispatchers.IO){
        try{
            val response = service.getBooks(author)
            if(response.isSuccessful){
                return@withContext Result.Success(mapper.toVolumeList(response.body()!!))
            } else {
                return@withContext Result.Error(Exception(response.message()))
            }
        } catch (e: Exception){
            return@withContext Result.Error(e)
        }
    }


}