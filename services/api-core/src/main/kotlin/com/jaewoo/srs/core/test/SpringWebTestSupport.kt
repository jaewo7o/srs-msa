package com.jaewoo.srs.core.test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
class SpringWebTestSupport : SpringTestSupport() {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    protected val classpath = "classpath:"
}
