package ru.amaev.budget.controllers

import io.swagger.annotations.Api
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*
import ru.amaev.budget.data.Category
import ru.amaev.budget.errors.DataNotFound
import ru.amaev.budget.repositories.CategoryRepository


@RestController
@Api
@RequestMapping("/categories")
class CategoryAccountController {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    // TODO Disable after start
    @GetMapping
    fun getAllCategories(): Iterable<Category> {
        return categoryRepository.findAll()
    }

    @GetMapping("get/{id}")
    fun getCategory(@PathVariable id: String): Category = categoryRepository.findByIdOrNull(id.toLong())
            ?: throw DataNotFound()

    @PutMapping("add")
    fun addCategory(@RequestBody category: Category) {
        categoryRepository.save(category)
    }

    @DeleteMapping("remove/{id}")
    fun removeUser(@PathVariable id: String) {
        val category = categoryRepository.findByIdOrNull(id.toLong()) ?: throw DataNotFound()
        category.setArchived()
        categoryRepository.save(category)
    }

    @PatchMapping("update")
    fun updateCategory(@RequestBody category: Category) {
        if (category.getId() != null) {
            val categoryFromDB = categoryRepository.findByIdOrNull(category.getId()!!.toLong()) ?: throw DataNotFound()
            if (category.categoryName != null) categoryFromDB.categoryName = category.categoryName
            categoryRepository.save(categoryFromDB)
        } else throw DataNotFound()
    }
}