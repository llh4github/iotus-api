package io.github.llh4github.iotus.service.auth.impl

import io.github.llh4github.iotus.dal.auth.User
import io.github.llh4github.iotus.dal.auth.UserDao
import io.github.llh4github.iotus.service.BaseServiceImpl
import io.github.llh4github.iotus.service.auth.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(userDao: UserDao) :
    UserService, BaseServiceImpl<User, UserDao>(userDao) {

}