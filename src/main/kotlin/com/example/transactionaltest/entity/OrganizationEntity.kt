package com.example.transactionaltest.entity

import jakarta.persistence.*

@Entity
@Table(name = "t_organization")
class OrganizationEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @Column
    var balance: Long,

    @Column
    var userCount: Long,

    /*@OneToMany
    @JoinColumn(name = "organization_id")
    var users: MutableList<AccountEntity>,*/
)