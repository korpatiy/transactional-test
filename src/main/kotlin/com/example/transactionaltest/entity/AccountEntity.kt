package com.example.transactionaltest.entity

import jakarta.persistence.*

@Entity
@Table(name = "t_account")
class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0,

    @Column
    val userName: String,

    @Column
    var balance: Long,

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    val organization: OrganizationEntity,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AccountEntity) return false

        if (id != other.id) return false
        if (userName != other.userName) return false
        if (balance != other.balance) return false
        if (organization != other.organization) return false
       // if (version != other.version) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userName.hashCode()
        result = 31 * result + balance.hashCode()
        result = 31 * result + organization.hashCode()
       // result = 31 * result + version.hashCode()
        return result
    }

    override fun toString(): String {
        return "AccountEntity(id=$id, userName='$userName', balance=$balance, organization=$organization, )"
    }
}