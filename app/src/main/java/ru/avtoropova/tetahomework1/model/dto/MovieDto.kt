package ru.avtoropova.tetahomework1.model.dto

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class MovieDto(
    val title: String?,
    val description: String?,
    val rateScore: Int,
    val ageRestriction: Int,
    val imageUrl: String?,
    val actors: ArrayList<ActorDto>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.createTypedArrayList(ActorDto)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(rateScore)
        parcel.writeInt(ageRestriction)
        parcel.writeString(imageUrl)
        parcel.writeTypedList(actors)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDto> {
        override fun createFromParcel(parcel: Parcel): MovieDto {
            return MovieDto(parcel)
        }

        override fun newArray(size: Int): Array<MovieDto?> {
            return arrayOfNulls(size)
        }
    }
}