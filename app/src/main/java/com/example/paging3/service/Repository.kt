package com.example.paging3.service

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.paging3.model.Repo
import kotlinx.coroutines.flow.Flow

object Repository {

    private const val PAGE_SIZE = 10

    private val githubService = GithubService.create()

    /**
     * 协程的Flow，你可以简单将它理解成协程中对标RxJava的一项技术
     */
    fun getPagingData(): Flow<PagingData<Repo>> {
        return Pager(
                config = PagingConfig(PAGE_SIZE),
                pagingSourceFactory = {RepoPagingSource(githubService)}
        ).flow
    }
}