package com.example.kotlinlaravelapirestful.repository

import com.example.demo.core.Resource
import com.example.kotlinlaravelapirestful.core.UserPreferences
import com.example.kotlinlaravelapirestful.data.model.LoginResponse
import com.example.kotlinlaravelapirestful.data.model.RegisterResponse
import com.example.kotlinlaravelapirestful.data.model.ReportsResponse
import com.example.kotlinlaravelapirestful.data.model.UserInfoResponse
import com.example.kotlinlaravelapirestful.data.remote.UserDataSource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.File

//Esta clase implementa la interfaz del repositorio "UserRepository"
//implementa sus metodos que buscaran la info en el server
//Esta clase hace la llamada a data/remote/UserDataSource

class UserRepositoryImpl(
            private val dataSource: UserDataSource,
            private val preferences: UserPreferences
) : UserRepository {

/*
    - Return forma normal
    override suspend fun registerUser(): RegisterResponse {
        return dataSource.registerUser()
    }*/

    //Aprovechando kotlin

    override suspend fun registerUser(name: String, email: String, password: String) : RegisterResponse = dataSource.registerUser(name, email,password)

    override suspend fun loginUser(email: String, password: String): LoginResponse = dataSource.loginUser(email, password)

    override suspend fun userInfo(access_token:String): UserInfoResponse = dataSource.userInfo(access_token)

    override suspend fun saveAuthToken(token: String) {
        preferences.saveAuthToken(token)
    }

    override suspend fun registerReport(access_token: String, description: RequestBody, photo: MultipartBody.Part, ): RegisterResponse = dataSource.registerReport(access_token, description, photo)

    override suspend fun getReports(access_token: String): ReportsResponse = dataSource.getReports(access_token)


}