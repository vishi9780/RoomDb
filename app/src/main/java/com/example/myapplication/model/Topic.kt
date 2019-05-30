package com.example.myapplication.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Topic(
        @SerializedName("isSuccess") val isSuccess: String,
        @SerializedName("data") val data: Data,
        @SerializedName("message") val message: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readParcelable<Data>(Data::class.java.classLoader),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(isSuccess)
        writeParcelable(data, 0)
        writeString(message)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Topic> = object : Parcelable.Creator<Topic> {
            override fun createFromParcel(source: Parcel): Topic = Topic(source)
            override fun newArray(size: Int): Array<Topic?> = arrayOfNulls(size)
        }
    }
}


