package ru.amaev.budget.data

import org.springframework.data.util.ProxyUtils
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass


@MappedSuperclass
abstract class AbstractJpaEntity<T> {

    companion object {
        private val serialVersionUID = -5554308939380869754L
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private var id: T? = null

    @get:JvmName("isArchived")
    private var archived: Boolean = false

    fun getId(): T? {
        return id
    }

    fun setArchived() {
        archived = true
    }

    override fun equals(other: Any?): Boolean {
        other ?: return false

        if (this === other) return true

        if (javaClass != ProxyUtils.getUserClass(other)) return false

        other as AbstractJpaEntity<*>

        return if (null == this.getId()) false else this.getId() == other.getId()
    }

    override fun hashCode(): Int {
        return 31
    }

    override fun toString() = "Entity of type ${this.javaClass.name} with id: $id"

}