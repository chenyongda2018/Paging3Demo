package com.example.paging3.model

import com.google.gson.annotations.SerializedName

class RepoResponse(
        @SerializedName("items") val items : List<Repo> = emptyList()
)