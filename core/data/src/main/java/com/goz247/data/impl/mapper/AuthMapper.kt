package com.goz247.data.impl.mapper

import com.example.core.network.response.ApiResponse
import com.example.core.network.response.mapper.app.UserResponse
import com.example.core.network.response.mapper.base.ApiSuccessResponseMapper
import com.goz247.data.models.UserModel


/**
 * Mapper to convert UserResponse to UserModel
 */
fun UserResponse.toModel(): UserModel {
    return UserModel(
        id = this.id,
        name = this.name,
        email = this.email
    )
}


/**
 * * Mapper class to convert ApiResponse.Success<UserResponse> to UserModel
 */
class UserInfoMapper: ApiSuccessResponseMapper<UserResponse, UserModel> {

    override fun map(apiSuccessResponse: ApiResponse.Success<UserResponse>): UserModel =
        apiSuccessResponse.data.toModel()

}