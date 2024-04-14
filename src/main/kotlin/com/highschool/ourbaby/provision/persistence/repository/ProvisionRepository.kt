package com.highschool.ourbaby.provision.persistence.repository

import com.highschool.ourbaby.provision.persistence.entity.ProvisionEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ProvisionRepository : JpaRepository<ProvisionEntity, Long> {
}
