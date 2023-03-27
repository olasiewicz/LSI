package com.example.userwithhilt_retrofit.domain.model

import com.google.gson.annotations.SerializedName

data class UserBasic (

    @SerializedName("login")
    var login: String? = null,


        @SerializedName("id")
        var id: Int? = null,

        @SerializedName("node_id")
        var nodeId: String? = null,

        @SerializedName("avatar_url")
        var avatarUrl: String? = null,

        @SerializedName("gravatar_id")
        var gravatarId: String? = null,

        @SerializedName("url")
        var url: String? = null,

        @SerializedName("repos_url")
        var reposUrl: String? = null



)