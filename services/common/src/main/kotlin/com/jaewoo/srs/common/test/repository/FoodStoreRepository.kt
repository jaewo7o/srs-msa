package com.jaewoo.srs.common.test.repository

import com.jaewoo.srs.common.test.domain.entity.FoodStore
import org.springframework.data.jpa.repository.JpaRepository

interface FoodStoreRepository : JpaRepository<FoodStore, Int> {

}
