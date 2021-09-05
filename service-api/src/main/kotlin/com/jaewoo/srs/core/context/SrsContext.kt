package com.jaewoo.srs.core.context

class SrsContext {
    companion object {
        private val threadLocal = ThreadLocal<SessionUser>()

        fun setCurrentUser(sessionUser: SessionUser) = threadLocal.set(sessionUser)
        fun getCurrentUser() = threadLocal.get()
    }
}