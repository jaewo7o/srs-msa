package com.jaewoo.srs.common.test.repository

import com.jaewoo.srs.common.test.domain.entity.FoodType
import org.springframework.data.jpa.repository.JpaRepository

interface FoodTypeRepository : JpaRepository<FoodType, Int> {

}
