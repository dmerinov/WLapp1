package com.example.myapplication.mapper

import com.example.myapplication.model.*

fun MonumentDto.toMonumentVO() = MonumentVO(
    id = id,
    geocoordinates = geocoordinates,
    title = title
)

fun MonumentDto.toMonumentDomainModel() = MonumentDomainModel(
    id, geocoordinates, title
)

fun MonumentVO.toMonumentDTO() = MonumentDto(
    geocoordinates = geocoordinates,
    id = id,
    title = title
)

fun MonumentVO.toMonumentDomainModel() = MonumentDomainModel(
    id, geocoordinates, title
)

fun MonumentDetailDto.toMonumentDetailVO() = MonumentDetailVO(
    id = id,
    address = address,
    description = description,
    email = email,
    geocoordinates = geocoordinates,
    phone = phone,
    title = title,
    transport = transport
)

fun MonumentDetailVO.toMonumentDomainDetailModel() = MonumentDomainDetailModel(
    id = id,
    address = address,
    description = description,
    email = email,
    geocoordinates = geocoordinates,
    phone = phone,
    title = title,
    transport = transport,
    isFavourite = false
)

fun MonumentDetailVO.toMonumentDomainModel() = MonumentDomainDetailModel (
    id = id,
    address = address,
    description = description,
    email = email,
    geocoordinates = geocoordinates,
    phone = phone,
    title = title,
    transport = transport,
    isFavourite = false
        )