package com.example.transactionaltest.repo;

import com.example.transactionaltest.entity.OrganizationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizationEntityRepository : JpaRepository<OrganizationEntity, Long>