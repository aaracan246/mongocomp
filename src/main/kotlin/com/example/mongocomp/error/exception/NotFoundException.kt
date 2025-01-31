package com.example.mongocomp.error.exception

class NotFoundException(message: String): RuntimeException("Not found exception. 404. $message.") {
}