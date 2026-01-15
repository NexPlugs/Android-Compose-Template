package com.goz247.database.domain.user

import com.example.core.model.UserModel


fun UserEntity.toModel(): UserModel {
    return UserModel(
        id = this.id,
        name = this.name,
        email = this.email
    )
}


fun UserModel.toEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        email = this.email
    )
}