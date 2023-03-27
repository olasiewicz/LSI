package com.example.userwithhilt_retrofit.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class UserItem(

    @SerializedName("login")
    var login: String? = null,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int? = null,

    @ColumnInfo(name = "nodeId")
    @SerializedName("node_id")
    var nodeId: String? = null,

    @ColumnInfo(name = "avatar_url")
    @SerializedName("avatar_url")
    var avatarUrl: String? = null,

    @ColumnInfo(name = "gravatar_id")
    @SerializedName("gravatar_id")
    var gravatarId: String? = null,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String? = null,

    @ColumnInfo(name = "url")
    @SerializedName("url")
    var url: String? = null,

    @ColumnInfo(name = "repos_url")
    @SerializedName("repos_url")
    var reposUrl: String? = null

): Parcelable