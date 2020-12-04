package com.diegopardo.transformersbattle.model.pojo

import android.os.Parcel
import android.os.Parcelable

data class Transformer(
    val id: String?,
    val name: String?,
    val team: String?, //    TODO: change to enum
    val strength: Int,
    val intelligence: Int,
    val speed: Int,
    val endurance: Int,
    val rank: Int,
    val courage: Int,
    val firepower: Int,
    val skill: Int,
    val team_icon: String?,
) : Parcelable {
//    enum class Team(val abbreviation: String) {
//        AUTOBOT("A"),
//        DECEPTICON("B"),
//    }

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    fun getOverallRating() = this.strength + this.intelligence + this.speed + this.endurance + this.firepower

    override fun equals(other: Any?): Boolean {
        return other is Transformer && id.equals(other.id)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(team)
        parcel.writeInt(strength)
        parcel.writeInt(intelligence)
        parcel.writeInt(speed)
        parcel.writeInt(endurance)
        parcel.writeInt(rank)
        parcel.writeInt(courage)
        parcel.writeInt(firepower)
        parcel.writeInt(skill)
        parcel.writeString(team_icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Transformer> {
        override fun createFromParcel(parcel: Parcel): Transformer {
            return Transformer(parcel)
        }

        override fun newArray(size: Int): Array<Transformer?> {
            return arrayOfNulls(size)
        }
    }
}