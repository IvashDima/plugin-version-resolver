package com.example.repository

import com.example.model.Plugin

interface PluginRepository {
    fun findById(id: String): Plugin?
    fun save(plugin: Plugin)
    fun findAll(): List<Plugin>
}
