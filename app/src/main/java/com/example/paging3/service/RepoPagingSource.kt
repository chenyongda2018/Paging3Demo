package com.example.paging3.service

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.model.Repo
import java.lang.Exception

class RepoPagingSource(private val githubService: GithubService): PagingSource<Int,Repo>() {

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return try {
            val page = params.key?: 1
            val pageSize = params.loadSize
            val repoResponse = githubService.searchRepos(pageSize,page)
            val repoItems = repoResponse.items
            val preKey = if(page > 1) page - 1 else null //前一页页码
            val nextKey = if(repoItems.isNotEmpty()) page + 1 else null  //后一页页码
            LoadResult.Page(repoItems,preKey,nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}